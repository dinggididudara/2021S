package Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ClientThread extends Thread{
	private InputStream input;
	
	ClientThread(InputStream input){
		this.input = input;
	}
	
	@Override
	public void run() {
		DataInputStream dis = null;
		try {
			dis = new DataInputStream(input);
			String message = "";
			while(true) {
				message = dis.readUTF();
				System.out.println(message);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
