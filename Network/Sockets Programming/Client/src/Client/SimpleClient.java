package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SimpleClient extends JFrame implements Runnable{
	private InputStream input;
	private Socket cSocket;
	private String name; //client name
	
	private JTextArea textArea = new JTextArea();
	private JTextField nameField = new JTextField();
	private JTextField textField = new JTextField();
	private PrintWriter writer;
	private BufferedReader reader;
	
	public SimpleClient(){ //GUI
		setTitle("Client");
		setSize(740, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setVisible(true);
		
		textArea.setBounds(10,10,700,300);
		add(textArea);
		
		nameField.setBounds(10,320,200,50);
		add(nameField);
		
		textField.setBounds(10,380,700,50);
		add(textField);
		
		textField.addActionListener(new ActionListener() { //if click enter keyboard
			public void actionPerformed(ActionEvent e) {
				try {
					writer.println(nameField.getText() + " : " + textField.getText()); //send message
					textField.setText("");
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}
	
	void connect() {
		try {
			
//			ip = InetAddress.getByName(""); //get address
			
			cSocket = new Socket(InetAddress.getLocalHost().getHostAddress(), 1254); //connect to ip address
			System.out.println("SocketYou are in Client now.");
			reader = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
			writer = new PrintWriter(cSocket.getOutputStream(), true);
			
			new Thread(this).start();
//			
//			System.out.println("Type your name: ");
//			Scanner sc = new Scanner(System.in);
//			name = sc.next();
			
			//reading information
//			input = cSocket.getInputStream();
//			
//			
//			byte[] buffer = new byte[100];
//			input.read(buffer);
//			
//			System.out.println(new String(buffer));
			
			//close socket
			cSocket.close(); 
		} catch(IOException io) {
			io.printStackTrace();
		} //try-catch end
	} //start end
	@Override
	public void run() {
		while(true) {
			try {
				textArea.append(reader.readLine() + "\n"); //message print
			} catch (Exception e) {
				
			}
		}
		
	}
} //client class end
