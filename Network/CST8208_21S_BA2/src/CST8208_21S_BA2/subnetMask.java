package CST8208_21S_BA2;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class subnetMask {
	String a;
	String b;
	
	double d;
	String subnetBinary;
	static String wildcardStr;
	static byte[] subnetBinaryArr; //for subnet (array)
	String[] subnetSepArr = new String[4]; //for subnetmask calculate (binary to decimal)
	String[] subnetSepString = new String[4]; //string type - seperated
	int[] subnetSepDArr = new int[4]; //subnet separated decimal array
	
	
	public String subnetMaskBinary(String subnetArrElement1, int subnetInt) { //binary from subnet prefix
		
		try {
			subnetBinaryArr = InetAddress.getByName(subnetArrElement1).getAddress();
			subnetBinary = new BigInteger(1, subnetBinaryArr).toString(2); //calculate subnet to binary (String type)
			d = Double.parseDouble(subnetBinary);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		
		StringBuilder a = new StringBuilder();
		for(int i=0;i<subnetInt;i++) {
			a = a.append('1');	
		}
		for(int j=subnetInt;j<32;j++) {
			a = a.append('0');
		}
		b = a.toString();
		subnetDecimalCalculate();
		
		return b;
	}
	
	public String subnetDecimalCalculate() {
		subnetSepArr[0] = b.substring(0,8); //string type
		subnetSepArr[1]	= b.substring(8,16);
		subnetSepArr[2] = b.substring(16,24);
		subnetSepArr[3] = b.substring(24,32);			
		
		int intBeforeStr;
		subnetSepString = new String[4];
		String subnetDecimalString;

		for(int i=0;i<4;i++) {
			intBeforeStr = Integer.valueOf(subnetSepArr[i], 2);
			subnetSepString[i] = String.valueOf(intBeforeStr);
		}
		subnetDecimalString = subnetSepString[0] + "." + subnetSepString[1] + "." + subnetSepString[2] + "." + subnetSepString[3];
		wildcardCalculate();
		return subnetDecimalString;
	}
	
	public String wildcardCalculate() {
		int[] wildcardArr = new int[4];

		for(int i=0;i<4;i++) {
			wildcardArr[i] = 255 - Integer.parseInt(subnetSepString[i]);
		}
		wildcardStr = String.valueOf(wildcardArr[0]) + "." + String.valueOf(wildcardArr[1]) + "." + String.valueOf(wildcardArr[2]) + "." +String.valueOf(wildcardArr[3]);
		return wildcardStr;
	}
	
}
