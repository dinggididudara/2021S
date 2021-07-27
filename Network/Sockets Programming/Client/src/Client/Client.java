package Client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		try {
			Socket cSocket = new Socket("192.168.0.2", 1254); //connect to ip address
			
			//reading information
			InputStream input = cSocket.getInputStream();
			
			byte[] buffer = new byte[100];
			input.read(buffer);
			
			System.out.println(new String(buffer));
			
			//close socket
			cSocket.close(); 
		} catch(IOException io) {
			io.printStackTrace();
		} //try-catch end
	} //main end
} //client class end
