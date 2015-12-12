/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author lehai
 */
public class MD5Encryption {

    private static MessageDigest md;

    public static String encryptWithMD5(String pass) {
        try {
            //lấy thuật toán MD5
            md = MessageDigest.getInstance("MD5");
            //chuyển đổi pass dữ liệu thành dãy byte tương ứng
            byte[] passBytes = pass.getBytes();
            
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digested.length; i++) {
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            System.err.println("Error: "+ex.getMessage());
        }
        return null;

    }
    
}
