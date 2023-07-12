// Client.java
import java.io.*;
import java.net.*;

public class ClientTCP {
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public void connect(String serverIP, int serverPort) {
        try {
            // Connect to the server
            socket = new Socket(serverIP, serverPort);
            System.out.println("Connected to server");

            // Initialize the input and output streams
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendText(String text) {
        try {
            // Send the text to the server
            outputStream.writeObject(text);
            System.out.println("Sent text to server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int receiveCount() {
        int count = 0;
        try {
            // Receive the count from the server
            count = (int) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void close() {
        try {
            // Close the connection
            socket.close();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

