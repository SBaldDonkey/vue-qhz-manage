package com.cainew.util;

import java.security.MessageDigest;
public class MD5 {

    public static final String enCode(String s){

        char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        try {
            byte[] btInput = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++){
                byte bt = md[i];
                str[(k++)] = hexDigits[(bt >>> 4 & 0xF)];
                str[(k++)] = hexDigits[(bt & 0xF)];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
    	 String enCode = MD5.enCode("123");
    	 System.out.println(enCode);
	}
}
