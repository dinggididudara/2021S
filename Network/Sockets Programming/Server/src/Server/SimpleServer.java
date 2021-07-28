package Server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
	
	ServerSocket sSocket = null; //empty it
	Socket socket; //socket is port
	OutputStream output;
	
	public void start() {
		try {
			sSocket = new ServerSocket(1254); //set port number 1254
			System.out.println("Server is activated now");
			
			while(true) { 
				socket = sSocket.accept(); //accept client to write
			
				output = socket.getOutputStream(); //read
			
				String send = "Hello";
				
				output.write(send.getBytes()); //write
			} //while end
			
		}catch(IOException io) {
			io.printStackTrace();
		}finally { //close socket and output
			try {
				if(output != null) sSocket.close();
				if(socket != null) socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}//try-catch-finally end
	} //start() end
} //Server class end
