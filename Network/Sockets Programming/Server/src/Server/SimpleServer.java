package Server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SimpleServer {
	
	ServerSocket sSocket = null; //empty it
	Socket socket; //socket is port
	OutputStream output;
	List<Thread> list; //list for multi threads
	
	public void start() {
		list = new ArrayList<Thread>();
		System.out.println("Server is activated now");
		
		try {
			sSocket = new ServerSocket(1254); //set port number 1254
			System.out.println("Server Socket is created now.");
			sSocket.setReuseAddress(true); //re-using port number again
			
			while(true) { 
				socket = sSocket.accept(); //accept client to write
				ServerSocketThread thread = new ServerSocketThread(this, socket);
				addClient(thread);
				thread.start();
				
//				output = socket.getOutputStream(); //read
//				
//				System.out.print("Message: ");
//				Scanner sc = new Scanner(System.in);
//				String send = sc.nextLine(); //scaning messeage from clients
//				
//				output.write(send.getBytes()); //write
			} //while end
			
		}catch(IOException io) {
			System.err.print("connection failed");
		}//try-catch-finally end
	} //start() end
	
	private synchronized void addClient(ServerSocketThread thread) { //adding client
		list.add(thread);
		System.out.printf("Connected : Total %d Client(s)\n", list.size());
	} //addClient end
	
	public synchronized void removeClient(Thread thread) { //removing client(thread)
		list.remove(thread);
		System.out.printf("Disconnected : 1 client. Total %d connected now", list.size());
	} //removeClient end
	
	public synchronized void broadCasting (String s) { //send msg to all clients
		for(int i=0;i<list.size();i++) {
			ServerSocketThread thread = (ServerSocketThread) list.get(i);
			thread.sendMessage(s);
		}
	}
} //Server class end
