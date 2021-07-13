/*
Output:
Address:   32.20.130.0           00100000.00010100.1 0000010.00000000
Netmask:   255.255.128.0 = 17    11111111.11111111.1 0000000.00000000
Wildcard:  0.0.127.255           00000000.00000000.0 1111111.11111111
=>
Sunnet (Network):32.20.128.0/17  00100000.00010100.1 0000000.00000000 (Class A)
Broadcast:       32.20.255.255   00100000.00010100.1 1111111.11111111
HostMin (FHIP):  32.20.128.1     00100000.00010100.1 0000000.00000001
HostMax (LHIP):  32.20.255.254   00100000.00010100.1 1111111.11111110
s=9
S=512
Subnet Index (000101001) = 41
h=15
HIPs Hosts/Net: 32766  

Your code should be uploaded to Brightspace (via the link posted) in a single zip file obtained by:
1) In Eclipse, selecting the project name
(CST8208_20S_BA2)
2) right clicking on ‘Export’ and selecting General/Archive File; click Next;
3) in the Archive File menu make sure all of the project subfolders are selected (src, bin, .settings)
and the ‘Save in zip format’ and ‘Create in Directory Structure’ radio buttons are selected
4) In the ‘To Archive File’ window, save your zip file to a location you’ll remember. But make certain
the name of your zip file corresponds to the following format: BA_Yourlastname_Yourfirstname.zip
including the underscores and capitals, but with your last and first name inserted as indicated, and
where your last name and first name are the same is used in Brightspace.

 */

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.regex.Pattern;


public class CST8208_21S_BA2 {
	public static void main(String[] args) {
		String address;
		String subnet;
		
		String addressip;
		String addressBinary;
		String binaryFormat;
//		String a,b,c,d,e;
//		int ii;
		byte[] bytes;
		
		DecimalFormat formatter = new DecimalFormat("########,########,########,########");
		String ipFormat = formatter.toString().replace(",", ".");
//		a="^([1-255])$"+ ".";
//		b="^([1-255])$"+ ".";
//		c="^([1-255])$"+ ".";
//		d="^([1-255])$"+ "\\";
//		e="^([1-32])$";
		
		Scanner sc = new Scanner(System.in);
		
		System.out.printf("Input: ");
		address = sc.next();
		subnet = sc.next();
//		addBinary = 
	

//		i = Integer.parseInt(address, 10);
		try {
			
			
			addressip = "^([01]?\\d|2[0-4]\\d|25[0-5])\\." + "^([01]?\\d|2[0-4]\\d|25[0-5])\\."
						+ "^([01]?\\d|2[0-4]\\d|25[0-5])\\." + "^([01]?\\d|2[0-4]\\d|25[0-5])\\.";
//			addBinary = "^\d";
			bytes = InetAddress.getByName(address).getAddress();
			addressBinary = new BigInteger(1, bytes).toString(2);
			binaryFormat = formatter.format(Double.parseDouble(addressBinary)).toString().replace(",", ".");
			
			System.out.println("Output :");
		
					//calculate 
			System.out.printf("Address :" + "%15s" + "%40s\n", address, binaryFormat);
			System.out.printf("Netmask: " + "%25s = %s" + "%33s\n", subnet);
//			System.out.println("Wildcard: " + "%24f" + "%33f");
//			System.out.println("=>\nSubnet (Network):" + "%17f" + "%33f");
//			System.out.println("Broadcast: " + "%23f" + "%33f");
//			System.out.println("HostMin (FHIP): " + "%19f" + "%33f");
//			System.out.println("HostMax (LHIP): " + "%19f" + "%33f");
//			System.out.println("s=" +);
//			System.out.println("S=" +);
//			System.out.println("Subnet Index (%d) = " +);
//			System.out.println("h=" + );
//			System.out.println("HIPs Hosts/Net: " + );
		
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		sc.close();
	}
}