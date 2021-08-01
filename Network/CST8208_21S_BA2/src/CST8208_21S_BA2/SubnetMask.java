package CST8208_21S_BA2;
/**
 * 
 * {@summary SubnetMask class: calculating subnet mask ip, subnet mask binary, wildcard ip, wildcard binary. Any of variables with 'Full' is the result for printing} 
 *
 */
public class SubnetMask {
	StringBuilder subnetStrBuilder = new StringBuilder(); //for subnet mask binary (StringBuilder)
	String subnetBinaryStr; //String type subnet mask binary (String)
	double subnetBinaryDouble; //Double type subnet binary (double)
	static String subnetMaskBinaryFull; //subnet binary full (with dots) (String)
	
	String[] subnetIpArr = new String[4]; //for subnetmask ip calculate (binary to decimal)
	String[] subnetIpArrStr = new String[4]; //subnet mask ip seperated (String[])
	int[] subnetIpArrInt = new int[4]; //subnet ip array (int[])
	static String subnetIpString; //subnet ip full (with dots) (String)
	
	int[] wildcardArr = new int[4]; // wildcard ip (int[])
	StringBuilder wildcardBinStrBuilder = new StringBuilder(); //for wildcard binary (StringBuilder)
	String wildcardBinStrFull; //String type wildcard binary
	static String wildcardStrFull; //wildcard string (String)
	/**
	 * 
	 * @param subnetPrefix
	 * @return subnetBinaryFull
	 */
	public String subnetMaskBinary(int subnetPrefix) { //binary from subnet prefix
		
		for(int i=0;i<subnetPrefix;i++) {
			subnetStrBuilder = subnetStrBuilder.append('1');	
		}
		for(int j=subnetPrefix;j<32;j++) {
			subnetStrBuilder = subnetStrBuilder.append('0');
		}
		
		subnetBinaryStr = subnetStrBuilder.toString(); //subnet mask in binary (String type)
		subnetMaskBinaryFull = subnetBinaryStr.substring(0,8) + "." + subnetBinaryStr.substring(8,16) + "." + subnetBinaryStr.substring(16,24) + "." + subnetBinaryStr.substring(24,32); //subnet mask binary full (String)
		
		subnetIpCalculate(subnetPrefix);
		
		return subnetMaskBinaryFull;
	}
	
	public String subnetIpCalculate(int subnetPrefix) { //calculating subnet mask in IP format
		subnetIpArr[0] = subnetBinaryStr.substring(0,8); //string type (String)
		subnetIpArr[1]	= subnetBinaryStr.substring(8,16);
		subnetIpArr[2] = subnetBinaryStr.substring(16,24);
		subnetIpArr[3] = subnetBinaryStr.substring(24,32);			
		
		int intBeforeStr;

		for(int i=0;i<4;i++) {
			intBeforeStr = Integer.valueOf(subnetIpArr[i], 2);
			subnetIpArrStr[i] = String.valueOf(intBeforeStr); //to String type
		}
		subnetIpString = subnetIpArrStr[0] + "." + subnetIpArrStr[1] + "." + subnetIpArrStr[2] + "." + subnetIpArrStr[3]; //subnetmask ip full with dots (String)
		wildcardCalculate(subnetPrefix);
		return subnetIpString;
	} //subnetDecimalCalculate end
	
	public String wildcardCalculate(int subnetPrefix) {
		for(int i=0;i<4;i++) { //calculate wildcard ip (int[])
			wildcardArr[i] = 255 - Integer.parseInt(subnetIpArrStr[i]);
		}
		wildcardStrFull = String.valueOf(wildcardArr[0]) + "." + String.valueOf(wildcardArr[1]) + "." + String.valueOf(wildcardArr[2]) + "." +String.valueOf(wildcardArr[3]); //in ipformats with dots (String)
		
		for(int x=0;x<subnetPrefix;x++) {
			wildcardBinStrBuilder = wildcardBinStrBuilder.append('0');
		}
		for(int y=subnetPrefix;y<32;y++) {
			wildcardBinStrBuilder = wildcardBinStrBuilder.append('1');
		}
		wildcardBinStrFull=wildcardBinStrBuilder.toString();
		wildcardBinStrFull=wildcardBinStrFull.substring(0,8) + "." + wildcardBinStrFull.substring(8,16) + "." + wildcardBinStrFull.substring(16,24) + "." + wildcardBinStrFull.substring(24,32); //wildcard binary full with dots (String)
		
		return wildcardBinStrFull;
	} //wildcardCalculate end
	
}
