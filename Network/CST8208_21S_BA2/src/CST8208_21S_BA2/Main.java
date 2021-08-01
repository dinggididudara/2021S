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
import org.apache.commons.net.util.SubnetUtils;


public class Main {
	static int s; //subnet bits
	static int h; //how many bits borrowed //host bits
	static int host;
	static char ipClass;
	static int subnetPrefix;
	static String networkIp;
	static String broadcastIp;
	static String[] ipArr = new String[4];
	static int[] ipArrInt = new int[4];
	public static void main(String[] args) {
		String ip_string;
		char ipClass = 0;//A or B or C
		
		String[] subnet;
	
		int subnetIndex;
		int subnetIndexBinary;
		
		String ipBinary;
		String binaryFormat;
		
		String ipBinaryFull;
		
		String networkIpBinary;
		String[] networkIpArr;
		
		String broadcastIpBinary;
		String[] broadcastIpArr;
		
		String hostMinIp;
		String[] hostMinIpArr;
		String hostMinBinary;
		
		String hostMaxBinary;
		String hostMaxIp;
		String[] hostMaxIpArr;
		
//------------start here---------------------------------------------------------------------------------------------------------------------//	
		
		Scanner sc = new Scanner(System.in); //get input from user
		System.out.printf("Input: ");
		ip_string = sc.nextLine();
		
		String ip = ip_string.substring(0,ip_string.indexOf("/")); //input ip except prefix
		ipArr = ip.split("[.]"); //split to 4 parts and save into array (String)
		for(int i=0;i<4;i++) { //ipArray: change to integer type (int)
			ipArrInt[i] = Integer.parseInt(ipArr[i]);
		}
		subnetPrefix = Integer.parseInt(ip_string.substring(ip_string.indexOf("/")+1,ip_string.length())); //subnet to integer type
		
		if(ipArrInt[0] <= 127) {
			ipClass = 'A';
			s =  subnetPrefix - 8; //(subnet prefix) minus (class A's prefix) //2^s = total subnets
			h = 24 - s; //how many bits borrowed, 2^h = host total
		}else if(ipArrInt[0] >= 128 && ipArrInt[0] < 192) {
			ipClass = 'B';
			s = subnetPrefix - 16;
			h = 16 - s;
		}else if(ipArrInt[0] >= 192 && ipArrInt[0] < 224){
			ipClass = 'C';
			s = subnetPrefix -24;
			h = 8 - s;
		}
		
		try {
			SubnetUtils subnetUtils = new SubnetUtils(ip_string);
			subnetUtils.setInclusiveHostCount(true);
			
			ipBinaryFull = String.format("%08d.%08d.%08d.%08d", Integer.parseInt(Integer.toBinaryString(ipArrInt[0])),Integer.parseInt(Integer.toBinaryString(ipArrInt[1])),Integer.parseInt(Integer.toBinaryString(ipArrInt[2])),Integer.parseInt(Integer.toBinaryString(ipArrInt[3]))); //ip Binary full (String)
			
			SubnetMask subnetMask = new SubnetMask();
			subnetMask.subnetMaskBinary(subnetPrefix);
			
			System.out.println("\nOutput:");
			System.out.printf("Address: %17s %47s\n", ip , ipBinaryFull);
			System.out.printf("Netmask: %17s = %s %42s\n", SubnetMask.subnetDecimalString, subnetPrefix, SubnetMask.subnetMaskFull);
			System.out.printf("Wildcard: %16s %47s\n", SubnetMask.wildcardStr, subnetMask.d);
			System.out.println("=>");
			
			networkIp = subnetUtils.getInfo().getNetworkAddress();
			networkIpArr = networkIp.split("[.]");
			int[] networkIpArrInt = { Integer.parseInt(networkIpArr[0]),Integer.parseInt(networkIpArr[1]),Integer.parseInt(networkIpArr[2]),Integer.parseInt(networkIpArr[3])};
			networkIpBinary = String.format("%08d.%08d.%08d.%08d", Integer.parseInt(Integer.toBinaryString(networkIpArrInt[0])),Integer.parseInt(Integer.toBinaryString(networkIpArrInt[1])),Integer.parseInt(Integer.toBinaryString(networkIpArrInt[2])),Integer.parseInt(Integer.toBinaryString(networkIpArrInt[3])));
			
			broadcastIp = subnetUtils.getInfo().getBroadcastAddress();
			broadcastIpArr = networkIp.split("[.]");
			int[] broadcastIpArrInt = { Integer.parseInt(broadcastIpArr[0]),Integer.parseInt(broadcastIpArr[1]) ,Integer.parseInt(broadcastIpArr[2]) ,Integer.parseInt(broadcastIpArr[3])};
			broadcastIpBinary = String.format("%08d.%08d.%08d.%08d", Integer.parseInt(Integer.toBinaryString(broadcastIpArrInt[0])), Integer.parseInt(Integer.toBinaryString(broadcastIpArrInt[1])),Integer.parseInt(Integer.toBinaryString(broadcastIpArrInt[2])),Integer.parseInt(Integer.toBinaryString(broadcastIpArrInt[3])));
			
			System.out.printf("Subnet (Network):%13s/%d %40s (Class %c)\n", networkIp , subnetPrefix , SubnetMask.subnetMaskFull , ipClass);
			System.out.printf("Broadcast: %22s %40s\n", broadcastIp , broadcastIpBinary);
			
			hostMinIp = subnetUtils.getInfo().getLowAddress();
			hostMinIpArr = hostMinIp.split("[.]");
			int[] hostMinIpArrInt = { Integer.parseInt(hostMinIpArr[0]),Integer.parseInt(hostMinIpArr[1]),Integer.parseInt(hostMinIpArr[2]),Integer.parseInt(hostMinIpArr[3])};
			hostMinBinary = String.format("%08d.%08d.%08d.%08d", Integer.parseInt(Integer.toBinaryString(hostMinIpArrInt[0])),Integer.parseInt(Integer.toBinaryString(hostMinIpArrInt[1])),Integer.parseInt(Integer.toBinaryString(hostMinIpArrInt[2])),Integer.parseInt(Integer.toBinaryString(hostMinIpArrInt[3])));
			
			hostMaxIp = subnetUtils.getInfo().getHighAddress();
			hostMaxIpArr = hostMaxIp.split("[.]");
			int[] hostMaxIpArrInt = { Integer.parseInt(hostMaxIpArr[0]),Integer.parseInt(hostMaxIpArr[1]),Integer.parseInt(hostMaxIpArr[2]),Integer.parseInt(hostMaxIpArr[3])};
			hostMaxBinary = String.format("%08d.%08d.%08d.%08d", Integer.parseInt(Integer.toBinaryString(hostMaxIpArrInt[0])),Integer.parseInt(Integer.toBinaryString(hostMaxIpArrInt[1])),Integer.parseInt(Integer.toBinaryString(hostMaxIpArrInt[2])),Integer.parseInt(Integer.toBinaryString(hostMaxIpArrInt[3])));
			
			System.out.printf("HostMin (FHIP): %17s %40s\n", hostMinIp , hostMinBinary);
			System.out.printf("HostMax (LHIP): %17s %40s\n", hostMaxIp , hostMaxBinary);
			
			System.out.printf("s=%d\n", s); //subnet bits
			System.out.printf("S=%.0f\n", Math.pow(2,s)); //subnet total number
			
			subnetIndex = s + 32;
			subnetIndexBinary = Integer.parseInt(Integer.toBinaryString(s+32));//subnet index to binary
			
			System.out.printf("Subnet Index (%08d) = %d\n", subnetIndexBinary , subnetIndex);
			System.out.printf("h= %d\n", h);
			System.out.printf("HIPs Hosts/Net: %.0f\n", Math.pow(2, h));
			
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		} //try-catch end
	}//main end
} //Main class end 