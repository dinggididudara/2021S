package Client;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class SimpleClient {
	InputStream input;
	Socket cSocket;
	String name; //client name
	void start() {
		try {
			
//			ip = InetAddress.getByName(""); //get address
			
			cSocket = new Socket(InetAddress.getLocalHost().getHostAddress(), 1254); //connect to ip address
			System.out.println("You are in Client now.");
			System.out.println("Type your name: ");
			Scanner sc = new Scanner(System.in);
			name = sc.next();
			
			//reading information
			input = cSocket.getInputStream();
			
			
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
