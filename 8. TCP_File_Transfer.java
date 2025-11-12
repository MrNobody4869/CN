// ===================================================================
// EXPERIMENT: TCP SOCKET PROGRAMMING – FILE TRANSFER
// ===================================================================
// LANGUAGE: Java
// NETWORK TYPE: Wired (LAN/Localhost)
// PROGRAM TYPE: Client-Server File Transfer using TCP Socket
// ===================================================================


// ==========================================================
// SERVER CODE – FileReceiverServer.java
// ==========================================================

import java.io.*;
import java.net.*;

public class FileReceiverServer {
    public static void main(String[] args) {
        int port = 12345; // Port number for the server
        String saveFilePath = "received_file.txt"; // File where received data will be stored

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running and listening on port " + port + "...");

            // Wait until a client connects
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            // Create input stream to receive bytes from client
            InputStream inputStream = socket.getInputStream();

            // Create output stream to write received bytes to a file
            FileOutputStream fileOutputStream = new FileOutputStream(saveFilePath);

            byte[] buffer = new byte[4096];
            int bytesRead;

            // Continuously read bytes from client until end of stream
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }

            System.out.println("File received successfully and saved as: " + saveFilePath);

            // Close resources
            fileOutputStream.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}



// ==========================================================
// CLIENT CODE – FileSenderClient.java
// ==========================================================

import java.io.*;
import java.net.*;

public class FileSenderClient {
    public static void main(String[] args) {
        String hostname = "localhost"; // Server IP address or hostname
        int port = 12345; // Server port
        String filePath = "file_to_send.txt"; // Path of the file to send

        try (Socket socket = new Socket(hostname, port)) {
            System.out.println("Connected to server.");

            // Create stream to read file
            FileInputStream fileInputStream = new FileInputStream(filePath);

            // Get output stream to send data to server
            OutputStream outputStream = socket.getOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead;

            // Read file and send bytes to server in chunks
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            System.out.println("File sent successfully!");

            // Close resources
            fileInputStream.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println("Client exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}




























// ==========================================================
// HOW TO RUN THIS PROGRAM
// ==========================================================
//
// Step 1: Create two Java files in the same directory:
//         -> FileReceiverServer.java
//         -> FileSenderClient.java
//
// Step 2: Create a text file named "file_to_send.txt"
//         (Place some text content inside it.)
//
// Step 3: Open two terminal windows.
//
// Step 4: In the first terminal, compile and run the server:
//         javac FileReceiverServer.java
//         java FileReceiverServer
//
// Step 5: In the second terminal, compile and run the client:
//         javac FileSenderClient.java
//         java FileSenderClient
//
// Step 6: The client will send "file_to_send.txt" to the server.
//         The server will save it as "received_file.txt".
//
// Step 7: Verify that the file content matches in both files.




// ==========================================================
// DETAILED THEORY / NOTES
// ==========================================================
//
// 1. **What is Socket Programming?**
//    Socket programming allows communication between two computers
//    over a network (wired or wireless) using standard protocols
//    such as TCP or UDP.
//
// 2. **Why TCP for File Transfer?**
//    - TCP (Transmission Control Protocol) provides *reliable*,
//      *connection-oriented* communication.
//    - Ensures complete and ordered delivery of data packets.
//    - Ideal for file transfer where data integrity is essential.
//
// 3. **Program Working Principle:**
//    a) Server creates a `ServerSocket` and listens on a port.
//    b) Client establishes connection using a `Socket`.
//    c) Client reads a file and sends bytes through OutputStream.
//    d) Server receives those bytes via InputStream and writes to file.
//
// 4. **Classes Used:**
//    - `ServerSocket`: Listens for incoming connections (Server side).
//    - `Socket`: Enables communication between client and server.
//    - `InputStream` & `OutputStream`: Used to transfer file bytes.
//    - `FileInputStream` & `FileOutputStream`: For file handling.
//
// 5. **Buffering:**
//    Data is transferred in chunks (buffers) of 4096 bytes to improve efficiency.
//
// 6. **Closing Streams:**
//    Always close sockets and streams to free resources.
//
// 7. **Advantages of TCP File Transfer:**
//    - Reliable delivery
//    - Maintains sequence of data
//    - Ensures error detection and retransmission
//
// 8. **Disadvantages:**
//    - Slower than UDP due to connection overhead
//    - More complex setup
//
// 9. **Applications of TCP File Transfer:**
//    - FTP (File Transfer Protocol)
//    - HTTP (Web file downloads)
//    - Remote backups
//
// 10. **Difference Between TCP and UDP:**
//    | Feature           | TCP                         | UDP                        |
//    |-------------------|-----------------------------|-----------------------------|
//    | Type              | Connection-oriented          | Connectionless              |
//    | Reliability       | Reliable and ordered         | Unreliable and unordered    |
//    | Speed             | Slower                       | Faster                      |
//    | Error Checking    | Built-in                     | Optional                    |
//    | Example Protocols | HTTP, FTP                    | DNS, Video Streaming        |
//    | Suitable For      | File Transfer, Web, Email    | Real-time data, gaming      |
//
// ==========================================================
// END OF JAVA PROGRAM
// ==========================================================
