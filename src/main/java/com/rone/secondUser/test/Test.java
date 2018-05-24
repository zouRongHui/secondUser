package com.rone.secondUser.test;

import com.rone.secondUser.myUtil.MyStringUtil;

import java.security.NoSuchAlgorithmException;

public class Test {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub
//		String str = "1234";
//		int[] i = MyStringUtil.stringToInts(str);
//		System.out.println(i.toString());
		
//		int l = (int) (Math.random() * 10);
//		System.out.println(l);
		
		
//		StringTokenizer st = new StringTokenizer("a  b  c    d");
//		while (st.hasMoreTokens()) {
//			System.out.println(st.nextToken() + ".");
//		}
		
		
		String str = "000000";
		System.out.println(MyStringUtil.encryption(str));
		
		System.out.println(MyStringUtil.encryption("000000"));
		
		
	}

}
