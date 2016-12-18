/*
 * Copyright © <Pascal Fares @ ISSAE - Cnam Liban>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), 
 * to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions: 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * The Software is provided “as is”, without warranty of any kind, express or implied, including but not limited to the warranties of merchantability, 
 * fitness for a particular purpose and noninfringement. In no event shall the authors or copyright holders be liable for any claim, damages or other liability, 
 * whether in an action of contract, tort or otherwise, arising from, out of or in connection with the software or the use or other dealings in the Software. »
 */
package net.cofares;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pascalfares
 */
public class SHAStringHash {
    public static String convertToHexString(String str) {
        String res =null;
        try {
            return convertToHexString(str,"SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SHAStringHash.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    public static String convertToHexString2(String str) {
        String res =null;
        try {
            return convertToHexStringV2(str,"SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SHAStringHash.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    public static String convertToHexString(String str, String algo) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algo);
        md.update(str.getBytes());

        byte byteData[] = md.digest();
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        //System.out.println("Hex format : " + sb.toString());
        return sb.toString();
        
    }
    public static String convertToHexStringV2(String str, String algo) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algo);
        md.update(str.getBytes());

        byte byteData[] = md.digest();
        

        //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
    	for (int i=0;i<byteData.length;i++) {
    		String hex=Integer.toHexString(0xff & byteData[i]);
   	     	if(hex.length()==1) hexString.append('0');
   	     	hexString.append(hex);
    	}
    	//System.out.println("Hex format : " + hexString.toString());
        return hexString.toString();
    }
    
    public static void main(String a[]){
        System.out.printf("SHA-256(%s)=%s Methode1%n",a[0],convertToHexString(a[0]));
        System.out.printf("SHA-256(%s)=%s Methode2%n",a[0],convertToHexString2(a[0]));
    }
}
