/**
 * @author Soomin L
 * {@summary CST8208_21S_BA2: subnetting program. Any variables named with 'Full' is the result for printing.}
 * @date 01/Aug/2021
 */
package CST8208_21S_BA2;

import java.util.Scanner;

import org.apache.commons.net.util.SubnetUtils;

public class Main {
	static int s; //subnet bits
	static int h; //how many bits borrowed //host bits
	
	static int subnetPrefix; //subnet prefix
	
	static String[] ipArr = new String[4]; //array for ip (String)
	static int[] ipArrInt = new int[4];  //array for ip (int)
	
	public static void main(String[] args) {
		String ip_string;
		char ipClass = 0;//A or B or C
	
		int subnetIndex;
		int subnetIndexBinary;
	
//		char ipClass;
		String networkIp;
		String broadcastIp;
		String ipBinaryFull;
		
		String networkIpBinaryFull;
		String[] networkIpArr;
		
		String broadcastIpBinaryFull;
		String[] broadcastIpArr;
		
		String hostMinIp;
		String[] hostMinIpArr;
		String hostMinBinaryFull;
		
		String hostMaxBinaryFull;
		String hostMaxIp;
		String[] hostMaxIpArr;
		
//-----------------------------------<start here>-------------------------------------------------------------------------------------------------------------------------------------------------------//	
		
		while(true) {
//--------1.get user's input---------------------------------------------------------------------------------------------------------------------------------------------------//		
		Scanner sc = new Scanner(System.in); //get input from user																											   //
		System.out.printf("Input: ");																																		   //
		ip_string = sc.nextLine();																																			   //
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------//		
		/**
		 * analyze user input ip
		 */
		String ip = ip_string.substring(0,ip_string.indexOf("/")); //input ip except prefix (String)
		ipArr = ip.split("[.]"); //split to 4 parts and save into array (String)
		for(int i=0;i<4;i++) { //ipArray: change to integer type (int)
			ipArrInt[i] = Integer.parseInt(ipArr[i]);
		}
		subnetPrefix = Integer.parseInt(ip_string.substring(ip_string.indexOf("/")+1,ip_string.length())); //subnet to integer type (int)
		
		
		/**
		 * calculate 
		 */
		try {
			//calculate class with subnet bits and host bits
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
			
			if(s>=0) {
			SubnetUtils subnetUtils = new SubnetUtils(ip_string);
			subnetUtils.setInclusiveHostCount(true);
			
			ipBinaryFull = String.format("%08d.%08d.%08d.%08d", Integer.parseInt(Integer.toBinaryString(ipArrInt[0])),Integer.parseInt(Integer.toBinaryString(ipArrInt[1])),Integer.parseInt(Integer.toBinaryString(ipArrInt[2])),Integer.parseInt(Integer.toBinaryString(ipArrInt[3]))); //ip Binary full (String)
			
			SubnetMask subnetMask = new SubnetMask();
			subnetMask.subnetMaskBinary(subnetPrefix);
//----------------2.print address, subnetmask, wildcard----------------------------------------------------------------------------------------------//	
			System.out.println("\nOutput:");																										 //
			System.out.printf("Address: %17s %47s\n", ip , ipBinaryFull);                                                                    		 //
			System.out.printf("Netmask: %17s = %s %42s\n", SubnetMask.subnetIpString, subnetPrefix, SubnetMask.subnetMaskBinaryFull);                 //
			System.out.printf("Wildcard: %16s %47s\n", SubnetMask.wildcardStrFull, subnetMask.wildcardBinStrFull);														 //			
			System.out.println("=>");																												 //	
//---------------------------------------------------------------------------------------------------------------------------------------------------//	
			
			
			
			
			
			
			/**
			 * calculating network ip and broadcast ip
			 */
			networkIp = subnetUtils.getInfo().getNetworkAddress();
			networkIpArr = networkIp.split("[.]");
			int[] networkIpArrInt = { Integer.parseInt(networkIpArr[0]),Integer.parseInt(networkIpArr[1]),Integer.parseInt(networkIpArr[2]),Integer.parseInt(networkIpArr[3])};
			networkIpBinaryFull = String.format("%08d.%08d.%08d.%08d", Integer.parseInt(Integer.toBinaryString(networkIpArrInt[0])),Integer.parseInt(Integer.toBinaryString(networkIpArrInt[1])),Integer.parseInt(Integer.toBinaryString(networkIpArrInt[2])),Integer.parseInt(Integer.toBinaryString(networkIpArrInt[3])));
			
			broadcastIp = subnetUtils.getInfo().getBroadcastAddress();
			broadcastIpArr = networkIp.split("[.]");
			int[] broadcastIpArrInt = { Integer.parseInt(broadcastIpArr[0]),Integer.parseInt(broadcastIpArr[1]) ,Integer.parseInt(broadcastIpArr[2]) ,Integer.parseInt(broadcastIpArr[3])};
			broadcastIpBinaryFull = String.format("%08d.%08d.%08d.%08d", Integer.parseInt(Integer.toBinaryString(broadcastIpArrInt[0])), Integer.parseInt(Integer.toBinaryString(broadcastIpArrInt[1])),Integer.parseInt(Integer.toBinaryString(broadcastIpArrInt[2])),Integer.parseInt(Integer.toBinaryString(broadcastIpArrInt[3])));
		
//----------------3.print subnet network, broadcast--------------------------------------------------------------------------------------------------//		
			System.out.printf("Subnet (Network):%13s/%d %40s (Class %c)\n", networkIp , subnetPrefix , networkIpBinaryFull , ipClass);					 //
			System.out.printf("Broadcast: %22s %40s\n", broadcastIp , broadcastIpBinaryFull);															 //
//---------------------------------------------------------------------------------------------------------------------------------------------------//		
			
			
			
			
			
			/**
			 * calculate host min, max, subnet bits, subnet total
			 */
			hostMinIp = subnetUtils.getInfo().getLowAddress();
			hostMinIpArr = hostMinIp.split("[.]");
			int[] hostMinIpArrInt = { Integer.parseInt(hostMinIpArr[0]),Integer.parseInt(hostMinIpArr[1]),Integer.parseInt(hostMinIpArr[2]),Integer.parseInt(hostMinIpArr[3])};
			hostMinBinaryFull = String.format("%08d.%08d.%08d.%08d", Integer.parseInt(Integer.toBinaryString(hostMinIpArrInt[0])),Integer.parseInt(Integer.toBinaryString(hostMinIpArrInt[1])),Integer.parseInt(Integer.toBinaryString(hostMinIpArrInt[2])),Integer.parseInt(Integer.toBinaryString(hostMinIpArrInt[3])));
			
			hostMaxIp = subnetUtils.getInfo().getHighAddress();
			hostMaxIpArr = hostMaxIp.split("[.]");
			int[] hostMaxIpArrInt = { Integer.parseInt(hostMaxIpArr[0]),Integer.parseInt(hostMaxIpArr[1]),Integer.parseInt(hostMaxIpArr[2]),Integer.parseInt(hostMaxIpArr[3])};
			hostMaxBinaryFull = String.format("%08d.%08d.%08d.%08d", Integer.parseInt(Integer.toBinaryString(hostMaxIpArrInt[0])),Integer.parseInt(Integer.toBinaryString(hostMaxIpArrInt[1])),Integer.parseInt(Integer.toBinaryString(hostMaxIpArrInt[2])),Integer.parseInt(Integer.toBinaryString(hostMaxIpArrInt[3])));
		
//----------------------4.host minimum ip and maximum ip,subnet bits, subnet total-------------------------------------------------------------------//	
			System.out.printf("HostMin (FHIP): %17s %40s\n", hostMinIp , hostMinBinaryFull);															 //
			System.out.printf("HostMax (LHIP): %17s %40s\n", hostMaxIp , hostMaxBinaryFull);															 //		
			System.out.printf("s=%d\n", s); //subnet bits																							 //
			System.out.printf("S=%.0f\n", Math.pow(2,s)); //subnet total number																		 //	
//---------------------------------------------------------------------------------------------------------------------------------------------------//
		
			
			
		
			
			/**
			 * calculate subnet index
			 */
			subnetIndex = s + 32;
			subnetIndexBinary = Integer.parseInt(Integer.toBinaryString(s+32));//subnet index to binary
			
//----------------------5.subnet index, host bits, host total----------------------------------------------------------------------------------------//		
			System.out.printf("Subnet Index (%08d) = %d\n", subnetIndexBinary , subnetIndex);														 //
			System.out.printf("h= %d\n", h);																										 //
			System.out.printf("HIPs Hosts/Net: %.0f\n", Math.pow(2, h));																			 //
//---------------------------------------------------------------------------------------------------------------------------------------------------//	
			sc.close();
			break;
			} else {
				System.err.println("Illegal subnet mask. please try again");
				continue;
			}//if end
		} catch(IllegalArgumentException iae) {
			System.err.println("Illegal subnet mask. please try again");
		}catch (Exception e) {
			System.err.println("Illegal subnet mask. please try again");
		} //try-catch end	
		} //while end
	}//main end
} //Main class end 