// Server.java
import java.io.*;
import java.net.*;

public class ServerTCP {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public void start(int port) {
        try {
            // Start the server
            serverSocket = new ServerSocket(port);
            System.out.println("Server started");

            // Wait for a client to connect
            clientSocket = serverSocket.accept();
            System.out.println("Client connected");

            // Initialize the input and output streams
            inputStream = new ObjectInputStream(clientSocket.getInputStream());
            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());

            // Process the text and send the count back to the client
            String text = (String) inputStream.readObject();
            int count = processText(text);
            outputStream.writeObject(count);
            System.out.println("Sent count to client");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private int processText(String text) {
        // Count the number of words in the text
        String[] words = text.trim().split("\\s+");
        return words.length;
    }

    private void close() {
        try {
            // Close the connections
            serverSocket.close();
            clientSocket.close();
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
