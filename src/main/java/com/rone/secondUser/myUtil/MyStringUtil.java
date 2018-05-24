package com.rone.secondUser.myUtil;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class MyStringUtil {
	
	/**
	 * String拆分成int数组
	 * @param s
	 * @return
	 */
	public static int[] stringToInts(String s){
	   int[] n = new int[s.length()]; 
	   for(int i = 0;i<s.length();i++){
	     n[i] = Integer.parseInt(s.substring(i,i+1));
	   }
	   return n;
	}
	
	/**
	 * String用MD5加密
	 * @param s
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String encryption(String s) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(s.getBytes());
		return new BigInteger(1, md.digest()).toString(16);
	}
	
	/**
	 * 获取随机字符串
	 * @param length 获取的字符串长度
	 * @return
	 */
	public static String getNumber(int length) {
		String str = "";
		StringBuffer buffer = new StringBuffer("1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM");
		StringBuffer buffer2 = new StringBuffer();
		Random random = new Random();
		int len = buffer.length();
		for (int i = 0; i < length; i++) {
			buffer2.append(buffer.charAt(random.nextInt(len)));
		}
		str = buffer2.toString();
		
		return str;
	}
	
}
