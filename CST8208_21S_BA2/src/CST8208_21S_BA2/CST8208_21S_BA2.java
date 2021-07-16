package CST8208_21S_BA2;
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
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;


public class CST8208_21S_BA2 {
	static int s;
	static int h;
	public static void main(String[] args) {
		String ip_string;
		char ipClass;//A or B or C
		
		String[] subnet;
		String[] ipArr = new String[4];
		
		int subnetIndex = s + 32;
		int subnetInt;
		
		String ipBinary;
		String binaryFormat;
		
		String networkIp;
		String networkBinary;
		String broadcaseIp;
		String broadcastBinary;

		byte[] ipBinaryArr; //for first ip (array)
		String subnetIndexStr; //for subnet index binary
		byte[] bytes4; //for

		DecimalFormat formatter = new DecimalFormat("########,########,########,########");
		DecimalFormat formatter2 = new DecimalFormat("########");
//		String ipFormat = formatter.toString().replace(",", ".");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.printf("Input: ");
		ip_string = sc.nextLine();
		
		String ip = ip_string.substring(0,ip_string.indexOf("/"));
		ipArr = ip.split("[.]"); //split to 4 parts and save into array
		subnet = ip_string.split("/"); //subnet=subnet[1]
		subnetInt = Integer.parseInt(subnet[1]); //subnet to integer type
//		System.out.println(ipArr.length);
//		for(int i=0;i<ipArr.length;i++) {
//			System.out.println(ipArr[i]);
//		} 
		
		
		try {
			System.out.println(ip);
			System.out.println(ip_string);
			System.out.println(subnet[1]);
			
			ipBinaryArr = InetAddress.getByName(ip).getAddress();
			ipBinary = new BigInteger(1, ipBinaryArr).toString(2); //calculate ip address to binary (String type)
			
			subnetIndexStr = Integer.toBinaryString(subnetIndex);
			
			subnetMask subnetMask = new subnetMask();
			subnetMask.subnetMaskBinary(subnet[1], subnetInt);
			
			System.out.println("Output :");
//		
//					//calculate 
			System.out.printf("Address :" + "%16s" + "%50s\n", ip , formatter.format(Double.parseDouble(ipBinary)).toString().replace(",","."));
			System.out.println(subnetMask.b);
			System.out.println(Double.parseDouble(subnetMask.b));
			System.out.println(formatter.format(subnetMask.b).toString());
			System.out.printf("Netmask: " + "%18s = %s" + "%45s\n", subnetMask.subnetDecimalCalculate(), subnet[1] , formatter.format(Double.parseDouble(subnetMask.b)).toString().replace(",","."));
			
			System.out.printf("Wildcard: %15s %53s\n", subnetMask.wildcardStr);
			
			ipClass(ipArr, subnetInt); //decide which ip class
			
//			System.out.println("=>\nSubnet (Network): %17s %45s (Class %c)",  ,  , ipClass);
//			System.out.println("Broadcast: " + "%23f" + "%53s");
//			System.out.println("HostMin (FHIP): %19f %53s",);
//			System.out.println("HostMax (LHIP): %19f %53s", );
			
			System.out.printf("s=%d\n", s); //subnet bits
			System.out.printf("S=%d\n", Math.pow(2,s)); //subnet total number
			System.out.printf("Subnet Index (%s) = %d\n", subnetIndexStr , formatter2.format(subnetIndex));
			System.out.printf("h= %d\n", h);
			System.out.printf("HIPs Hosts/Net: %d\n", Math.pow(2, h));
			
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static char ipClass(String[] ipArr, int subnetInt) {
		int c = Integer.parseInt(ipArr[0]);
		char ipClass = 0;
		if(c <= 127) {
			ipClass = 'A';
		}else if(c >= 128 && c < 192) {
			ipClass = 'B';
		}else if(c >= 192 && c < 224){
			ipClass = 'C';
			
		}
		subnetBits(ipClass, subnetInt);
		return ipClass;
	}
	public static void subnetBits(char ipClass, int subnetInt) { //calculate subnet bits, host bits
		if(ipClass == 'A') {
			s =  subnetInt - 8;
			h = 24 - s;
		}else if(ipClass == 'B') {
			s = subnetInt - 16;
			h = 16 - s;
		}else if(ipClass == 'C') {
			s = subnetInt -24;
			h = 8 - s;
		}
	}
}