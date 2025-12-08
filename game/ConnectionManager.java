package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionManager {
    private final boolean isServer;
    private final int port = 5555;
    private final String host = "localhost";

    private ServerSocket serverSocket;
    private Socket socket;
    PrintWriter out;
    BufferedReader in;

    public ConnectionManager(boolean isServer) {
        this.isServer = isServer;
    }

    public void setUpConnection() {
        try {
            if (isServer) {
                startAsServer();
            } 
            else {
                startAsClient();
            }
            setUpStreams();
        } 
        catch (IOException e) {
            System.err.println("Connection setup failed: " + e.getMessage());
        }
    }

    /* 
    A TCP connection requires one ServerSocket on the server side, whose only job is to listen for incoming connections on a specific port.
    When a client connects, the server’s ServerSocket.accept() returns a new Socket. That Socket and the client’s Socket form the TCP connection.    
    */
    private void startAsServer() throws IOException {
        System.out.println("Starting server on port " + port + "...");
        serverSocket = new ServerSocket(port);
        System.out.println("Server waiting for a client to connect...");
        socket = serverSocket.accept(); // server will stop at this line until client is connected, called blocking call.
        System.out.println("Client connected!");
    }

    private void startAsClient() throws IOException {
        System.out.println("Connecting to server " + host + ":" + port + "...");
        socket = new Socket(host, port);
        System.out.println("Connected to server!");
    }

    // Both sides then use their Sockets to create input and output streams for communication.
    private void setUpStreams() throws IOException {
        out = new PrintWriter(socket.getOutputStream(), true); // used to send text data
        in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // used to recieve text data
    }

    // wrapper for out.println and in.println. Basically hides the logic of using PrintWriter and BufferedReader. 
    public void sendMessage(String message) { 
        out.println(message);
    }
    
    public String receiveMessage() throws IOException { 
        return in.readLine();
    }

    public void closeSocket() {
        try {
            if (socket != null) {
                socket.close();
            }
            if (serverSocket != null) {
                serverSocket.close();
            }
        } 
        catch (IOException e) {
            System.err.println("Failed to close connection: " + e.getMessage());
        }
    }
}
