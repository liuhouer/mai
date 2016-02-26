package com.mai.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Tools {
	public static String md5(String string){
		StringBuilder builder = new StringBuilder(""); 
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(string.getBytes()); 
			int i;
			byte b[] = md.digest(); 
			for(int offset=0;offset<b.length;offset++) { 
				i = b[offset]; 
				if(i<0) i+= 256; 
				if(i<16) builder.append("0"); 
				builder.append(Integer.toHexString(i)); 
			}
		} 
		catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		return builder.toString();
	}
}