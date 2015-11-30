/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class FileOperator {

    /**
     * Append data into file
     *
     * @param s data
     * @param path file path
     * @return true/false
     */
    public static boolean appendToFile(String s, String path) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
            out.print(s);
            out.close();
            return true;
        } catch (Exception e) {
            System.err.println("Khong the them du lieu vao file \"" + path + "\"");
            return false;
        }
    }

    /**
     *Create new file with data
     *
     * @param s data
     * @param path  FilePath
     */
    public static boolean writeFile(String s, String path) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(path);
            fw.write(s);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(FileOperator.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(FileOperator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Read data from file
     * @param path file path
     * @return data
     */
    public static String readFile(String path) {
        FileInputStream i = null;
        String result = "";
        try {
            i = new FileInputStream(path);
            InputStreamReader a = new InputStreamReader(i);
            Scanner sc = new Scanner(i);

            while (sc.hasNextLine()) {
                result += sc.nextLine();
            }
            return result;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileOperator.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        } finally {
            try {
                i.close();
            } catch (IOException ex) {
                Logger.getLogger(FileOperator.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public class FilePath {

        public static final String directory = "data/";
        public static final String Backup = directory + "backup.txt";

    }
}
