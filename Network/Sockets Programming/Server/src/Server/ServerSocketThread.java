package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerSocketThread extends Thread{
	Socket socket;
	SimpleServer server;
	PrintWriter output; //output
	BufferedReader input; //input
	String msg;
	String threadName;
	String name;
	
	public ServerSocketThread(SimpleServer server, Socket socket) { //get thread's name
		this.server = server;
		this.socket = socket;
		threadName = super.getName(); //getting thread's name
		System.out.println(socket.getInetAddress() + " is connected now."); //showing ip address
		System.out.println("Thread Name : " + threadName);
	}
	
	public void sendMessage(String s) {
		output.println(s);
		}
	
	@Override
	public void run() {
		try {
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true); //true for auto flush
			
			sendMessage("What is your name?");
			name = input.readLine();
			sendMessage("Hello!"+name);
//			server.broadCasting("Hello!" + name); //broadcasting
			
//			while(true) {
//				String str_in = input.readLine();
//				server.broadCasting(name + " : "+ str_in);
//			}
		} catch(IOException e) {
			System.out.println(threadName + " is disconnected now");
			server.removeClient(this);
		} finally {
			try {
				System.out.println("Client out now.");
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} //catch-try end
	} //run end
	
} //Thread end
