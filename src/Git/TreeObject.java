package Git;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class TreeObject {
	ArrayList<String> data;
	String dataSha;
	public TreeObject(ArrayList<String> data) throws FileNotFoundException
	{
	 data = new ArrayList<String>();
	 this.data = data;
	 dataSha = encryptThisString(makeToOneString());
	 writeDataFile();
	}
	
	
	
	
	public static String encryptThisString(String input)
	{
		try {	         
			MessageDigest md = MessageDigest.getInstance("SHA-1");	      
			byte[] messageDigest = md.digest(input.getBytes());	     
			BigInteger no = new BigInteger(1, messageDigest);         
			String hashtext = no.toString(16); 
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}	       
			return hashtext;
		}	 
		// For specifying wrong message digest algorithms
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	private String makeToOneString() {
		String allData = "";
		for(int i = 0; i < data.size(); i++) {
			allData += data.get(i) + (i<data.size()-1 ? "\n" : "");
		}
		return allData;
	}
	
	
	private void writeDataFile() throws FileNotFoundException {
		PrintWriter pw = new PrintWriter("objects/" + dataSha);
		
		for (String dataPiece : data)
		{
			pw.write(dataPiece);
			
		}
		pw.close();
	}
	
}
