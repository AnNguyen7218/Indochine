/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.regex.Pattern;

/**
 *
 * @author Ashley
 */
public class RegEx {

    /**
     * check contact number:
     *
     * @param num
     *
     */
    public static boolean checkContactNum(String num) {
        Pattern digit = Pattern.compile("^(0\\d{9,10})");
        return digit.matcher(num).matches();

    }

    public static boolean checkIDCard(String num) {
        Pattern digit = Pattern.compile("^(\\d{9})");
        return digit.matcher(num).matches();

    }

    /**
     * check integer type
     *
     * @param s
     */
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            //return false;
            //System.err.println("Loi Integer");
        }
        return false;
    }

    /**
     * check double type
     *
     * @param s
     */
    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {

            //System.err.println("Loi double");
        }
        return false;
    }
}
