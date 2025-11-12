// -------------------------------------------------------------
// PROGRAM: TCP Client and Server - Say Hello to Each Other
// LANGUAGE: Java
// -------------------------------------------------------------

// SERVER CODE
// Save this file as TCPServer.java

import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        try {
            //  Create a ServerSocket that listens on a specific port (e.g., 8080)
            // The ServerSocket waits for incoming connection requests from clients
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server started. Waiting for a client to connect...");

            //  Accept a client connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected!");

            //  Create input and output streams to communicate with the client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            //  Send a greeting message to the client
            out.println("Hello from Server!");

            //  Receive a message from the client
            String clientMessage = in.readLine();
            System.out.println("Client: " + clientMessage);

            // Enable continuous communication between server and client
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String message;
            while (true) {
                System.out.print("Enter message to client: ");
                message = userInput.readLine();
                out.println(message);

                // if user types "exit", break connection
                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Server closed connection.");
                    break;
                }

                clientMessage = in.readLine();
                System.out.println("Client: " + clientMessage);
            }

            //  Close all sockets and streams
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


// -------------------------------------------------------------
// CLIENT CODE
// Save this file as TCPClient.java
// -------------------------------------------------------------

import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        try {
            //  Create a socket and connect to the server running on localhost (127.0.0.1) and port 8080
            Socket socket = new Socket("127.0.0.1", 8080);
            System.out.println("Connected to the server.");

            //  Create input and output streams for communication
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            //  Receive and print message from the server
            String serverMessage = in.readLine();
            System.out.println("Server: " + serverMessage);

            //  Send response to server
            out.println("Hello from Client!");

            //  Allow two-way communication
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String message;
            while (true) {
                System.out.print("Enter message to server: ");
                message = userInput.readLine();
                out.println(message);

                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Client closed connection.");
                    break;
                }

                serverMessage = in.readLine();
                System.out.println("Server: " + serverMessage);
            }

            //  Close connections
            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


// -------------------------------------------------------------
// HOW TO RUN THE PROGRAM (Step-by-Step)
// -------------------------------------------------------------
/*
1️. Open your terminal or command prompt.

2️. Navigate to the folder where both files (TCPServer.java and TCPClient.java) are saved.

3️. Compile both Java programs:
    javac TCPServer.java
    javac TCPClient.java

4️. Run the server first:
    java TCPServer

   It will display:
   "Server started. Waiting for a client to connect..."

5️. Open another terminal window (or command prompt) in the same directory and run:
    java TCPClient

   You’ll see:
   "Connected to the server."

6️. Now type messages on both sides and see them appear on the opposite terminal.

7️. Type “exit” on either side to close the connection.

*/


// -------------------------------------------------------------
// THEORY NOTES FOR ORAL / VIVA
// -------------------------------------------------------------

/*
1️. TCP (Transmission Control Protocol):
   - TCP is a **connection-oriented** and **reliable** protocol.
   - It establishes a connection before data transfer.
   - Ensures data is delivered in correct order and without loss.

2️. SOCKET:
   - A socket is an endpoint of a two-way communication link between two programs running on the network.
   - In Java:
        - Server uses `ServerSocket` to listen.
        - Client uses `Socket` to connect.

3️. Important Classes Used:
   - `ServerSocket`: Listens for incoming client requests on a specific port.
   - `Socket`: Represents the connection between client and server.
   - `BufferedReader` and `InputStreamReader`: Read data from input streams.
   - `PrintWriter`: Sends data to output streams.

4️. Port Number:
   - A unique logical address used to identify a specific process/service on a machine.
   - Example: Port 8080 commonly used for local applications.

5️. Flow of Communication:
   ServerSocket → waits for connection  
   ↓  
   Client Socket → connects to ServerSocket  
   ↓  
   Data exchange (two-way communication)  
   ↓  
   Close connection  

6️. Why TCP for Communication:
   - Reliable (guarantees delivery)
   - Ordered (maintains message sequence)
   - Error-checked (verifies integrity of data)

7️. Difference Between TCP and UDP:
   | Feature       | TCP                      | UDP                      |
   |----------------|--------------------------|---------------------------|
   | Type           | Connection-oriented      | Connectionless            |
   | Reliability    | Reliable, ordered        | Unreliable, unordered     |
   | Speed          | Slower due to overhead   | Faster, less overhead     |
   | Use Cases      | Web, Email, File transfer| Video streaming, DNS, VoIP|

8️. Real-Life Example:
   - TCP is like a phone call (you first connect, then talk).
   - UDP is like sending letters (no confirmation if received).

*/


// -------------------------------------------------------------
// END OF PROGRAM
// -------------------------------------------------------------
