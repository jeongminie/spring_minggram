package com.jeongmini.minggram.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {
	
	public static String md5(String message) {
		String encData = "";
		
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] bytes = message.getBytes();
			md.update(bytes);
			byte[] digest = md.digest();
			
			for(int i = 0; i < digest.length; i++) {
				encData += Integer.toHexString(digest[i]&0xff);
			}
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return encData;
	}

}