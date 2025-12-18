package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Manages the network connection for the Rock-Paper-Scissors game.
 * <p>
 * Handles both server and client setup, message sending,
 * and message receiving over a TCP connection.
 * </p>
 */

public class ConnectionManager {
    private final String isServer;
    private final int port = 5555; 
    private final String host = "192.168.1.91";
    private boolean serverConnected;

    private ServerSocket serverSocket;
    private Socket socket;
    PrintWriter out;
    BufferedReader in;
    String userName;
    String opponentUsername;

    /**
     * Constructor for the ConnectionManager.
     *
     * @param isServer "y" to host the game, "n" to connect as a client
     */

    public ConnectionManager(String isServer, String userName) {
        this.isServer = isServer;
        this.userName = userName;
    }

    /**
     * Sets up the network connection based on the chosen role.
     * <p>
     * If acting as a server, waits for a client to connect.
     * If acting as a client, repeatedly attempts to connect
     * until the server is available.
     * </p>
     */

    public void setUpConnection() {
        try {
            if (isServer.equals("y")) {
                startAsServer();
                sendMessage("READY");
            } 
            else {
                startAsClient();
            }
        } 
        catch (IOException e) { // Use IOException for easy error message. Using getMessage() shows the error. 
            System.err.println("Connection setup failed: " + e.getMessage());
        }
    }

    /**
     * Starts this instance as a server and waits for a client connection.
     *
     * @throws IOException if the server socket fails to start or accept a connection
     */

    /* NOTE:
    A TCP connection requires one ServerSocket on the server side, whose only job is to listen for incoming connections on a specific port.
    When a client connects, the server’s ServerSocket.accept() returns a new Socket. That Socket and the client’s Socket form the TCP connection.    
    */
    private void startAsServer() throws IOException {
        System.out.println("Starting server on port " + port + "...");
        serverSocket = new ServerSocket(port);
        System.out.println("Server waiting for a client to connect...");
        socket = serverSocket.accept(); // returns socket, server will stop at this line until client is connected, called blocking call.
        setUpStreams();
        opponentUsername = receiveMessage();
        sendMessage(userName);
        System.out.println(opponentUsername + " connected!");
    }

    /**
     * Starts this instance as a client and attempts to connect to the server.
     * <p>
     * The client waits until the server responds with a "READY" message
     * before continuing.
     * </p>
     *
     * @throws IOException if a connection attempt fails
     */

    private void startAsClient() throws IOException {
        System.out.println("Connecting to server " + host + ":" + port + "...");
        while (!serverConnected) {
            try {
                socket = new Socket(host, port); // can catch IOException if server is not ready. If ready -> continue with setUpStream()
                setUpStreams();
                sendMessage(userName);
                opponentUsername = receiveMessage();
                if (receiveMessage().equals("READY")) { // this makes the client have to wait for the server. 
                    serverConnected = true;
                    System.out.println("Connected to " + opponentUsername);
                }
            } 
            catch (IOException e) {
                //System.out.println("Server not ready yet");
            }
        }  
    }

    /**
     * Initializes input and output streams for the socket.
     *
     * @throws IOException if stream creation fails
     */
    // Both sides then use their Sockets to create input and output streams for communication.
    private void setUpStreams() throws IOException {
        out = new PrintWriter(socket.getOutputStream(), true); // used to send text data
        in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // used to recieve text data
    }

    /**
     * Sends a message to the connected peer.
     *
     * @param message the message to send
     */
    // wrapper for out.println and in.println. Basically hides the logic of using PrintWriter and BufferedReader. 
    public void sendMessage(String message) { 
        out.println(message);
    }
    
    /**
     * Receives a message from the connected peer.
     *
     * @return the received message
     * @throws IOException if reading from the stream fails
     */
    public String receiveMessage() throws IOException { // IOException also have to be thrown here due to it being thrown in the declaration of BufferedReader.readLine().
        return in.readLine();
    }

    public String getOpponentUsername() {
        return opponentUsername;
    }

    /**
     * Closes the socket and server socket if they are open.
     */
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
