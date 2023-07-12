import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This method launch the frame and manage the connection to the server.
 * 
 * @author emalianakasmuri
 *
 */

public class ClientDateApplication {

	public static void main(String[] args) 
			throws UnknownHostException, IOException {
		
		// Launch client-side frame
		ClientDateFrame clientDateFrame = new ClientDateFrame();
		clientDateFrame.setVisible(true);
		
		// Connect to the server @ localhost, port 4228
		Socket socket = new Socket(InetAddress.getLocalHost(), 4228);
		
		// Update the status of the connection
		clientDateFrame.updateConnectionStatus(socket.isConnected());
		
		// Read from network
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
		
		// Display the current date
		String currentDate = bufferedReader.readLine();
		clientDateFrame.updateServerDate(currentDate);
		
		// Close everything
		bufferedReader.close();
		socket.close();
		
		//
		ClientTCP client = new ClientTCP();
        client.connect("localhost", 9999);

        // Send the text to the server
        String text = "Hello, this is a sample text.";
        client.sendText(text);

        // Receive the count from the server
        int count = client.receiveCount();
        System.out.println("Number of words: " + count);

        // Close the client
        client.close();
    }

}
