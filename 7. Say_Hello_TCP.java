// ===================================================================
// EXPERIMENT: TCP SOCKET PROGRAMMING – "SAY HELLO TO EACH OTHER"
// ===================================================================
// LANGUAGE: Java
// PROGRAM TYPE: Client-Server Communication using TCP Socket
// ===================================================================

// ==========================================================
// SERVER CODE
// Create Server.java
// ==========================================================
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server started. Waiting for client connection...");

            // Wait for the client to connect
            try (Socket clientSocket = serverSocket.accept()) {
                System.out.println("Client connected successfully!");

                // Create input and output streams for communication
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Step 1: Send a greeting message to the client
                out.println("Hello from Server!");

                // Step 2: Receive response from client
                String clientMessage = in.readLine();
                System.out.println("Client: " + clientMessage);

                // Step 3: Enable two-way communication
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
                String message;

                while (true) {
                    System.out.print("Enter message to client: ");
                    message = userInput.readLine();   // Read from server’s console
                    out.println(message);              // Send to client

                    if (message.equalsIgnoreCase("exit")) {
                        System.out.println("Connection closed by server.");
                        break;
                    }

                    // Receive client response
                    clientMessage = in.readLine();
                    System.out.println("Client: " + clientMessage);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



// ==========================================================
// CLIENT CODE
// Create Client.java
// ==========================================================
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080)) {
            System.out.println("Connected to the server.");

            // Create input and output streams for communication
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Step 1: Receive greeting from server
            String serverMessage = in.readLine();
            System.out.println("Server: " + serverMessage);

            // Step 2: Send response to server
            out.println("Hello from Client!");

            // Step 3: Enable continuous two-way chat
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String message;

            while (true) {
                System.out.print("Enter message to server: ");
                message = userInput.readLine();  // Read from client console
                out.println(message);            // Send to server

                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Connection closed by client.");
                    break;
                }

                // Receive message from server
                serverMessage = in.readLine();
                System.out.println("Server: " + serverMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


























// ==========================================================
// HOW TO RUN THIS PROGRAM
// ==========================================================
//
// Step 1: Open any code editor or terminal.
// Step 2: Save both files in the same directory with names:
//         -> Server.java
//         -> Client.java
//
// Step 3: Compile both files using the terminal:
//         javac Server.java
//         javac Client.java
//
// Step 4: First, run the server program:
//         java Server
//         (The server starts and waits for a client to connect.)
//
// Step 5: Then, in another terminal window, run the client program:
//         java Client
//
// Step 6: You will see both programs exchange greetings:
//         Server: "Hello from Server!"
//         Client: "Hello from Client!"
//
// Step 7: Both can now chat back and forth by typing messages.
//         Type "exit" to close the connection gracefully.








// ==========================================================
//  THEORY / NOTES
// ==========================================================
//
// 1. **WHAT IS SOCKET PROGRAMMING?**
//    Socket programming enables communication between two systems
//    over a network (LAN/WAN). It provides endpoints called "sockets"
//    for sending and receiving data.
//
// 2. **WHAT IS A SOCKET?**
//    A socket is an endpoint for communication between two machines.
//    In Java, a socket is represented by the `Socket` class for clients
//    and the `ServerSocket` class for servers.
//
// 3. **TCP (Transmission Control Protocol):**
//    - TCP is a *connection-oriented* protocol.
//    - It guarantees reliable data transmission.
//    - Data is sent in sequence and verified for delivery.
//    - Common uses: Web (HTTP), File transfer (FTP), Email (SMTP).
//
// 4. **HOW TCP CONNECTION WORKS:**
//    a) Server creates a `ServerSocket` on a specific port (e.g., 8080).
//    b) Server waits using `accept()` until a client requests connection.
//    c) Client connects using `Socket("hostname", port)`.
//    d) A reliable, full-duplex connection is established.
//
// 5. **STREAMS USED:**
//    - `InputStreamReader` + `BufferedReader`: to receive text data.
//    - `PrintWriter`: to send data to the connected socket.
//
// 6. **KEY FEATURES OF THIS PROGRAM:**
//    - Demonstrates how a TCP server and client can greet each other.
//    - Shows full-duplex (two-way) communication.
//    - Connection remains open until "exit" is typed.
//
// 7. **DIFFERENCE BETWEEN TCP AND UDP:**
//    | Feature         | TCP (Used Here)           | UDP                     |
//    |-----------------|---------------------------|--------------------------|
//    | Type            | Connection-oriented       | Connectionless           |
//    | Reliability     | Reliable, ordered         | Unreliable, unordered    |
//    | Speed           | Slower (due to checks)    | Faster (no handshake)    |
//    | Example Uses     | HTTP, FTP, Email         | DNS, Video Streaming     |
//
// 8. **APPLICATIONS OF TCP SOCKETS:**
//    - Web servers (HTTP communication)
//    - Chat applications
//    - File transfer tools
//    - Remote access programs (Telnet, SSH)
//
// 9. **ADVANTAGES OF TCP:**
//    - Reliable and accurate data delivery
//    - Error detection and correction
//    - Ensures packet order and integrity
//
// 10. **LIMITATIONS:**
//    - Slower than UDP due to error-checking overhead
//    - More resource consumption
//
// ==========================================================
// END OF PROGRAM
// ==========================================================
