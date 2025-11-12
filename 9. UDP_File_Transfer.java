// ======================================================================
// EXPERIMENT NO. 9
// PROGRAM TITLE: FILE TRANSFER USING UDP SOCKET PROGRAMMING
// ======================================================================
// LANGUAGE  : Java
// NETWORK   : Wired (Localhost)
// PROTOCOL  : UDP (User Datagram Protocol)
// ======================================================================
// AIM:
// Write a program using UDP sockets to enable file transfer between
// a client and a server.
// ======================================================================


// ==========================================================
// SERVER CODE – FileServer.java
// ==========================================================

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class FileServer {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        Scanner scanner = new Scanner(System.in);

        try {
            // Define the server port
            int port = 9876;

            // Get the file path from user
            System.out.print("Enter the path of the file to send: ");
            String filePath = scanner.nextLine();

            // Create a DatagramSocket (UDP communication)
            socket = new DatagramSocket();

            // Read file into byte array
            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] fileData = new byte[(int) file.length()];
            fileInputStream.read(fileData);
            fileInputStream.close();

            // Client address and port (localhost for demo)
            InetAddress clientAddress = InetAddress.getByName("127.0.0.1");
            int packetSize = 1024; // bytes per packet
            int totalPackets = (int) Math.ceil(fileData.length / (double) packetSize);

            // Send the file in chunks (packets)
            for (int i = 0; i < totalPackets; i++) {
                int start = i * packetSize;
                int length = Math.min(packetSize, fileData.length - start);

                // Extract chunk of data to send
                byte[] packetData = new byte[length];
                System.arraycopy(fileData, start, packetData, 0, length);

                // Create and send packet
                DatagramPacket packet = new DatagramPacket(packetData, length, clientAddress, port);
                socket.send(packet);
                System.out.println("Sent packet " + (i + 1) + " of " + totalPackets);
            }

            // Send an empty packet as end-of-transfer signal
            byte[] endSignal = new byte[0];
            DatagramPacket endPacket = new DatagramPacket(endSignal, 0, clientAddress, port);
            socket.send(endPacket);

            System.out.println("File sent successfully via UDP.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}



// ==========================================================
// CLIENT CODE – FileClient.java
// ==========================================================

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class FileClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        FileOutputStream fileOutputStream = null;
        Scanner scanner = new Scanner(System.in);

        try {
            // Define the port for listening (must match server port)
            int port = 9876;

            // Get destination file path
            System.out.print("Enter the path to save the received file: ");
            String outputFilePath = scanner.nextLine();

            // Create socket to receive data
            socket = new DatagramSocket(port);
            byte[] receiveBuffer = new byte[1024];

            // Open output stream to save received data
            fileOutputStream = new FileOutputStream(outputFilePath);
            System.out.println("Receiving file...");

            while (true) {
                // Receive packets
                DatagramPacket packet = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(packet);

                // Check for empty packet (end of file signal)
                if (packet.getLength() == 0) {
                    break;
                }

                // Write received data to file
                fileOutputStream.write(packet.getData(), 0, packet.getLength());
                System.out.println("Received packet of size " + packet.getLength());
            }

            System.out.println("File received successfully via UDP.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}





















// ==========================================================
// HOW TO RUN THIS PROGRAM (STEP-BY-STEP)
// ==========================================================
//
// 1. Save both files in the same directory:
//      - FileServer.java
//      - FileClient.java
//
// 2. Create a sample file to send, for example: "sample.txt"
//
// 3. Open two terminal windows.
//
// 4. In the first terminal, compile and run the client (it must be ready to receive):
//      javac FileClient.java
//      java FileClient
//      (Enter the desired output file name, e.g. received.txt)
//
// 5. In the second terminal, compile and run the server:
//      javac FileServer.java
//      java FileServer
//      (Enter the path of the file to send, e.g. sample.txt)
//
// 6. Observe the console logs – the server will send data in packets,
//    and the client will receive and reconstruct the file.
//
// 7. Verify that received.txt matches the original sample.txt.


// ==========================================================
// THEORY AND ORAL NOTES
// ==========================================================
//
// 1. **UDP (User Datagram Protocol)**:
//    - A connectionless and unreliable protocol at the transport layer.
//    - It sends data as independent packets (Datagrams) without establishing
//      a connection beforehand.
//
// 2. **Why UDP for File Transfer?**
//    - Faster than TCP because it has minimal overhead.
//    - Suitable for simple or real-time applications where small packet loss
//      can be tolerated (e.g., voice, video).
//    - Not ideal for guaranteed delivery like file transfer, but this experiment
//      demonstrates how it can still be implemented manually.
//
// 3. **Program Working:**
//    - The **server** reads a file and sends it as multiple UDP packets.
//    - The **client** listens on a port and writes received packets to a file.
//    - When an empty packet is received, it indicates transfer completion.
//
// 4. **Key Classes Used:**
//    - `DatagramSocket`: Provides methods for sending and receiving datagrams.
//    - `DatagramPacket`: Represents individual UDP packets.
//    - `InetAddress`: Identifies the destination IP address.
//    - `FileInputStream` / `FileOutputStream`: For reading and writing file data.
//
// 5. **Characteristics of UDP:**
//    - Connectionless: No handshake between sender and receiver.
//    - No guarantee of packet delivery, order, or duplication control.
//    - Uses port numbers for communication between applications.
//
// 6. **Advantages:**
//    - High speed and low latency.
//    - Simple and lightweight communication mechanism.
//
// 7. **Disadvantages:**
//    - No built-in error checking or retransmission.
//    - Unsuitable for reliable large file transfers.
//
// 8. **Applications:**
//    - Video streaming, VoIP, DNS lookups, multiplayer games.
//
// 9. **Comparison: TCP vs UDP**
//
//      | Feature           | TCP                            | UDP                            |
//      |-------------------|----------------------------------|--------------------------------|
//      | Type              | Connection-oriented              | Connectionless                 |
//      | Reliability       | Reliable (ACKs & retransmission) | Unreliable (no delivery check) |
//      | Speed             | Slower due to overhead           | Faster, lightweight            |
//      | Ordering          | Maintains packet sequence        | No packet order guarantee      |
//      | Error Control     | Built-in                         | None                           |
//      | Example Protocols | HTTP, FTP, SMTP                  | DNS, VoIP, Streaming           |
//      | Use Case          | File transfer, web browsing      | Live data, gaming, voice apps  |
//
// ==========================================================
// END OF PROGRAM
// ==========================================================
