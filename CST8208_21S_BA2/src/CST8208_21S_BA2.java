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
		String ip_string;
		char ipClass;//A or B or C
		
		String[] subnet;
		String[] ipArr = new String[5];
		
//		String addressip;
		String ipBinary;
		String subnetBinary;
		String binaryFormat;
//		String a,b,c,d,e;
//		int ii;
		byte[] bytes1;
		byte[] bytes2;
//		
		DecimalFormat formatter = new DecimalFormat("########,########,########,########");
//		String ipFormat = formatter.toString().replace(",", ".");
//		a="^([1-255])$"+ ".";
//		b="^([1-255])$"+ ".";
//		c="^([1-255])$"+ ".";
//		d="^([1-255])$"+ "\\";
//		e="^([1-32])$";
		
		Scanner sc = new Scanner(System.in);
		
		System.out.printf("Input: ");
		ip_string = sc.nextLine();
		
		String ip = ip_string.substring(ip_string.lastIndexOf("/"));
		ipArr = ip.split("[.]");
		subnet = ip_string.split("/"); //subnet=subnet[1]
//		System.out.println(ipArr.length);
//		for(int i=0;i<ipArr.length;i++) {
//			System.out.println(ipArr[i]);
//		} 
		sc.close();
		try {
			bytes1 = InetAddress.getByName(ip).getAddress();
			bytes2 = InetAddress.getByName(subnet[1]).getAddress();
//			bytes2 = InetAddress.getByName(subnet[1]).getAddress();
			ipBinary = new BigInteger(1, bytes1).toString(2);
			subnetBinary = new BigInteger(1, bytes2).toString(2);
//			subnetBinary = Integer.toHexString(Integer.parseInt(subnet[1]));//print 1 in subnet[1] times //change prefix to binary
			
			System.out.println("Output :");
//		
//					//calculate 
			System.out.printf("Address :" + "%15s" + "%40s\n", ip , formatter.format(Double.parseDouble(ipBinary)).toString().replace(",","."));
			System.out.printf("Netmask: " + "%15s = %s" + "%40s\n",  , subnet[1], formatter.format(Double.parseDouble(subnetBinary)).toString().replace(",","."));
//			System.out.println("Wildcard: " + "%20s" + "%40s");
//			System.out.println("=>\nSubnet (Network):" + "%17s" + "%40s" + "Class %c", , , ipClass);
//			System.out.println("Broadcast: " + "%23f" + "%40s");
//			System.out.println("HostMin (FHIP): " + "%19f" + "%40s");
//			System.out.println("HostMax (LHIP): " + "%19f" + "%40s");
//			System.out.println("s=" +);
//			System.out.println("S=" +);
//			System.out.println("Subnet Index (%d) = " +);
//			System.out.println("h=" + );
//			System.out.println("HIPs Hosts/Net: " + );
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}