// =============================================
// UDP SERVER PROGRAM
// =============================================

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String[] args) {
        try {
            // STEP 1: Create a DatagramSocket and bind it to a specific port (12345)
            // This socket will be used to listen for incoming UDP packets
            DatagramSocket serverSocket = new DatagramSocket(12345);
            System.out.println("UDP Server is running... Waiting for client data...");

            // STEP 2: Buffer (byte array) to store received data
            byte[] receiveData = new byte[1024];

            while (true) {
                // STEP 3: Create a DatagramPacket to receive data from clients
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // STEP 4: Wait and receive packet from client
                // (This is a blocking call — it waits until data is received)
                serverSocket.receive(receivePacket);

                // STEP 5: Convert received bytes into string message
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());

                // STEP 6: Display the message and client’s IP/port
                System.out.println("\nReceived from client: " + message);
                System.out.println("Client IP: " + receivePacket.getAddress() + 
                                   ", Port: " + receivePacket.getPort());

                // STEP 7: Clear buffer for next message
                receiveData = new byte[1024];
            }

        } catch (Exception e) {
            // STEP 8: Print exception details if any error occurs
            e.printStackTrace();
        }
    }
}

// =============================================
// UDP CLIENT PROGRAM
// =============================================

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) {
        try {
            // STEP 1: Get the server’s IP address (localhost for same system)
            InetAddress serverAddress = InetAddress.getByName("localhost");

            // STEP 2: Create DatagramSocket for client (no need to bind to port)
            DatagramSocket clientSocket = new DatagramSocket();

            // STEP 3: Create message to send
            String message = "Hello, UDP Server!";
            byte[] sendData = message.getBytes();

            // STEP 4: Create a DatagramPacket to send data to the server
            // Format: DatagramPacket(data, length, destination_IP, destination_port)
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
                    serverAddress, 12345);

            // STEP 5: Send packet to server
            clientSocket.send(sendPacket);
            System.out.println("Message sent to server: " + message);

            // STEP 6: Close the client socket
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
























// =============================================
//  HOW TO RUN THE PROGRAM (STEP-BY-STEP)
// =============================================
//
// 1️ Save both files as UDPServer.java and UDPClient.java
//     (in the same folder)
//
// 2️ Open Terminal (or Command Prompt) in that folder
//
// 3️ Compile both files using javac:
//     javac UDPServer.java
//     javac UDPClient.java
//
// 4️ Run the server first (it must be listening before client sends data):
//     java UDPServer
//
// 5️ Open another terminal window (in same folder) and run client:
//     java UDPClient
//
// 6️ You will see server terminal displaying the message received from client.

//



// =============================================
//  SHORT NOTES / THEORY (For Viva / Oral)
// =============================================

// 1. What is UDP (User Datagram Protocol)?
//    UDP is a connectionless and unreliable transport layer protocol used when 
//    speed is more important than reliability. It does not establish a connection 
//    before sending data and does not guarantee delivery or order of packets. 
//    Packets sent using UDP are called datagrams.
//
//    Common uses include: video streaming, online games, DNS lookups, and VoIP.
//
// ----------------------------------------------------------------------------
//
// 2. How UDP Communication Works:
//
//    - UDP does not require a connection between client and server.
//    - The server listens on a specific port and waits for incoming datagrams.
//    - The client sends datagrams to the server's IP and port.
//    - Each datagram is self-contained and independent.
//
// ----------------------------------------------------------------------------
//
// 3. UDP Server Workflow:
//
//    Step 1: Create a DatagramSocket and bind it to a port number (e.g., 12345).
//    Step 2: Wait to receive datagrams from clients using the receive() method.
//    Step 3: Store received data in a DatagramPacket object.
//    Step 4: Extract and display the message and sender’s address.
//    Step 5: Repeat the process in a loop for continuous listening.
//
// ----------------------------------------------------------------------------
//
// 4. UDP Client Workflow:
//
//    Step 1: Create a DatagramSocket (no need to bind a port manually).
//    Step 2: Prepare the message as a byte array using getBytes().
//    Step 3: Create a DatagramPacket with data, destination IP, and port.
//    Step 4: Send the packet using send() method of DatagramSocket.
//    Step 5: Close the socket after sending the message.
//
// ----------------------------------------------------------------------------
//
// 5. Key Java Classes Used:
//
//    • DatagramSocket – Used to send and receive UDP packets.
//    • DatagramPacket – Represents the packet to be sent or received.
//    • InetAddress – Represents an IP address and provides methods to resolve names.
//
// ----------------------------------------------------------------------------
//
// 6. Advantages of UDP:
//
//    • Faster and lightweight (no handshake or acknowledgment).
//    • Suitable for time-sensitive transmissions.
//    • Supports broadcasting and multicasting.
//
// 7. Disadvantages of UDP:
//
//    • Unreliable – packets may be lost or arrive out of order.
//    • No flow control or congestion control.
//    • No built-in error recovery.
//
// ----------------------------------------------------------------------------
//
// 8. When to Use UDP:
//
//    • Real-time applications like VoIP, video conferencing, and online gaming.
//    • Applications like DNS, DHCP, or network discovery.
//    • Scenarios where speed is preferred over guaranteed delivery.
//
// ----------------------------------------------------------------------------
//
// 9. Steps to Run UDP Client-Server Program in Terminal:
//
//    1. Save both files:
//       - UDPServer.java
//       - UDPClient.java
//
//    2. Compile both files:
//       javac UDPServer.java
//       javac UDPClient.java
//
//    3. Open two terminals:
//       - Terminal 1: Run the server using  java UDPServer
//       - Terminal 2: Run the client using  java UDPClient
//
//    4. Observe outputs:
//       - The server displays the received message and client IP.
//       - The client confirms message sent successfully.
//
// ----------------------------------------------------------------------------
//
// 10. Difference Between TCP and UDP:
//
//    ---------------------------------------------------------------
//    | Feature              | TCP                         | UDP     |
//    ---------------------------------------------------------------
//    | Connection Type       | Connection-oriented          | Connectionless |
//    | Reliability           | Reliable, ensures delivery   | Unreliable      |
//    | Error Handling        | Yes (acknowledgments, retransmission) | Minimal (checksum only) |
//    | Data Transmission     | Stream-based                 | Message-based   |
//    | Speed                 | Slower                      | Faster          |
//    | Flow Control          | Yes                         | No              |
//    | Overhead              | High                        | Low             |
//    | Typical Applications  | HTTP, FTP, SMTP              | DNS, VoIP, Video Streaming |
//    ---------------------------------------------------------------
//
// ----------------------------------------------------------------------------
//
// 11. Oral/Exam Notes:
//
//    • UDP uses DatagramSocket and DatagramPacket for communication.
//    • It is faster but unreliable compared to TCP.
//    • UDP is suitable when small packet loss is acceptable.
//    • TCP guarantees delivery, UDP focuses on speed.
//    • UDP is part of Layer 4 (Transport Layer) in the OSI model.
//
// ============================================================================
//
// END OF THEORY
// ============================================================================
