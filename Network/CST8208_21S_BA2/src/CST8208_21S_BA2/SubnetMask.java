package CST8208_21S_BA2;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class SubnetMask {
	StringBuilder a = new StringBuilder(); //for subnet mask binary
	String b; //String type subnet mask binary
	StringBuilder c = new StringBuilder(); //for wildcard binary
	String d; //String type wildcard binary
	double e; //Double type subnet binary
	String subnetBinary;
	static String subnetMaskFull; //subnet binary full (with dots)
	static String subnetDecimalString; 
	static String wildcardStr;
	static byte[] subnetBinaryArr; //for subnet (array)
	String[] subnetSepArr = new String[4]; //for subnetmask calculate (binary to decimal)
	String[] subnetSepString = new String[4]; //string type - seperated
	int[] subnetSepDArr = new int[4]; //subnet separated decimal array
	
	public String subnetMaskBinary(int subnetPrefix) { //binary from subnet prefix
		
//		try {
//			subnetBinaryArr = InetAddress.getByName(ip).getAddress();
//			subnetBinary = new BigInteger(1, subnetBinaryArr).toString(2); //calculate subnet to binary (String type)
//			e = Double.parseDouble(subnetBinary);
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}
		
		for(int i=0;i<subnetPrefix;i++) {
			a = a.append('1');	
		}
		for(int j=subnetPrefix;j<32;j++) {
			a = a.append('0');
		}
		b = a.toString(); //subnet mask in binary (String type)
		subnetMaskFull = b.substring(0,8) + "." + b.substring(8,16) + "." + b.substring(16,24) + "." + b.substring(24,32);
		
		subnetDecimalCalculate(subnetPrefix);
		
		return b;
	}
	
	public String subnetDecimalCalculate(int subnetPrefix) { //calculating subnet mask in ip format
		subnetSepArr[0] = b.substring(0,8); //string type (String)
		subnetSepArr[1]	= b.substring(8,16);
		subnetSepArr[2] = b.substring(16,24);
		subnetSepArr[3] = b.substring(24,32);			
		
		int intBeforeStr;
		subnetSepString = new String[4];

		for(int i=0;i<4;i++) {
			intBeforeStr = Integer.valueOf(subnetSepArr[i], 2);
			subnetSepString[i] = String.valueOf(intBeforeStr); //to String type
		}
		subnetMaskFull = subnetSepArr[0] + "." + subnetSepArr[1] + "."  + subnetSepArr[2] + "." + subnetSepArr[3]; //binary subnetmask full
		subnetDecimalString = subnetSepString[0] + "." + subnetSepString[1] + "." + subnetSepString[2] + "." + subnetSepString[3]; //subnetmask ip full
		wildcardCalculate(subnetPrefix);
		return subnetDecimalString;
	} //subnetDecimalCalculate end
	
	public String wildcardCalculate(int subnetPrefix) {
		int[] wildcardArr = new int[4];

		for(int i=0;i<4;i++) {
			wildcardArr[i] = 255 - Integer.parseInt(subnetSepString[i]);
		}
		wildcardStr = String.valueOf(wildcardArr[0]) + "." + String.valueOf(wildcardArr[1]) + "." + String.valueOf(wildcardArr[2]) + "." +String.valueOf(wildcardArr[3]); //in ipformats with dots
		for(int x=0;x<subnetPrefix;x++) {
			c = c.append('0');
		}
		for(int y=subnetPrefix;y<32;y++) {
			c = c.append('1');
		}
		
		d=c.toString();
		d=d.substring(0,8) + "." + d.substring(8,16) + "." + d.substring(16,24) + "." + d.substring(24,32); //wildcard binary full
		return d;
	} //wildcardCalculate end
	
}
