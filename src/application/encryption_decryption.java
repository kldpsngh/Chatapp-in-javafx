package application;

public class encryption_decryption {
	
	 private static final int shift=10;
	  
	    protected static String encryptData(String message) {
	        String code = "";
	        StringBuffer tempcode = new StringBuffer();
	        for (int i = 0; i < message.length(); i++) {
	        	
	        	int ascii_code=message.charAt(i);
	        	if((ascii_code>=65&&ascii_code<=90)||(ascii_code>=97&&ascii_code<=122))
	        	{
	        	
	            if (Character.isUpperCase(message.charAt(i))) {
	                char ch = (char) (((int) message.charAt(i) + shift - 65) % 26 + 65);
	                tempcode.append(ch);
	            } else {
	                char ch = (char) (((int) message.charAt(i) + shift - 97) % 26 + 97);
	                tempcode.append(ch);
	            }
	        }
	        	else
	        	{
	        		tempcode.append(message.charAt(i));
	        	}
	        }
	        code = tempcode.toString();
	        return code;
	    }

	    protected static String decryptData(String cipher) {
	        //return encryptData(cipher);
	    	String code = "";
	        StringBuffer tempcode = new StringBuffer();
	        for (int i = 0; i < cipher.length(); i++) {
	        	
	        	int ascii_code=cipher.charAt(i);
	        	if((ascii_code>=65&&ascii_code<=90)||(ascii_code>=97&&ascii_code<=122))
	        	{
	        	
	            if (Character.isUpperCase(cipher.charAt(i))) {
	                char ch = (char) (((int) cipher.charAt(i) + 26-shift - 65) % 26 + 65);
	                tempcode.append(ch);
	            } else {
	                char ch = (char) (((int) cipher.charAt(i) + 26-shift - 97) % 26 + 97);
	                tempcode.append(ch);
	            }
	        }
	        	else
	        	{
	        		tempcode.append(cipher.charAt(i));
	        	}
	        }
	        code = tempcode.toString();
	        return code;
	    }
	}
