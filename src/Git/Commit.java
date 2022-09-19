package Git;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

import java.security.MessageDigest;

import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar;  

public class Commit {
	Commit parent;
	Commit child;
	
	String pTree;
	String summary, author, date;
	
	public Commit(String _pTree, String _summary, String _author, Commit _parent) {
		pTree = _pTree;
		summary = _summary;
		author = _author;
		parent = _parent;
	}
	
    public String generateSHAfromString(String input) {
        try {
            // getInstance() method is called with algorithm SHA-1
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        } catch(Exception e) {
        	System.out.println(e.toString());
        	return null;
        }
    }
    
    public String getDate() {
    	Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
        String strDate = dateFormat.format(date);  
        return strDate;
        
    }
    
    public String createMySHA() {
    	return generateSHAfromString(pTree + summary);
    }
    
    public void writeFile() throws Exception {
    	String sha = "objects/" + createMySHA();
    	PrintWriter writer = new PrintWriter(sha);
		writer.println(pTree);
		writer.println(parent == null ? "null" : parent.pTree);
		writer.println(child == null ? "null" : child.pTree);
		writer.println(author);
		writer.println(getDate());
		writer.println(summary);
		
		writer.close();
    }
    
    
}
