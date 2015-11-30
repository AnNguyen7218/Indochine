/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class Converter {

    static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Change a string into date format.
     * @param a date string
     * @return a date
     */
    public static Date stringToDate(String dateStr) {

        try {
            Date date = dateFormatter.parse(dateStr);
        } catch (ParseException ex) {
            Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
    /**
     * Convert a date into string format.
     * @param date
     * @param a date 
     * @return a date string
     */
    public static String dateToString(Date date) {
        if (date == null){
            return null;
        }
        return dateFormatter.format(date);
    }
    
    /**
     * Convert a date to time string
     * @param date
     * @return time string
     */
    public static String dateToTime(Date date){
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
         if (date == null){
            return null;
        }
        return timeFormatter.format(date);
    }
    
    /**
     * Divine a string data into each line
     * @param String data
     * @return List<String> of line
     */
    public static List<String> getLineByLine(String data) {
        if (data.isEmpty()) {
            return null;
        }
        List<String> resultList = new ArrayList<>();
        try {
            String[] s = data.split(";");
            for(int i=0;i<s.length;i++){
                resultList.add(s[i]);
            }
            return resultList;
        } catch (Exception ex) {
            System.err.print("Divine into line error: " + ex.getMessage());
            return null;
        }

    }
}
