package Server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket sSocket = new ServerSocket(1254); //set port number 1254
			Socket socket = sSocket.accept();
			
			OutputStream output = socket.getOutputStream();
			
			String send = "Hello";
			output.write(send.getBytes());
			
			sSocket.close();
			socket.close();
		}catch(IOException io) {
			io.printStackTrace();
		} //try-catch end
	} //main end
} //Server class end
