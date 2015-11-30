/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backup_Restore;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Converter;
import utils.DatabaseConnector;
import utils.FileOperator;

/**
 *
 * @author Ashley
 */
public class Restore {

    public static boolean RecoveryData(String pathIn) {
      
        try {
            String sql = "";
            String db_name="Hotel";
            String path=pathIn;
            sql = "alter database " + db_name + " set offline with rollback immediate;";
            sql += "restore database " + db_name + " from disk = '" + path+"'";
            sql += " with replace ";
            sql += "alter database " + db_name + " set onLine with rollback immediate;";
            System.out.println("sql query: "+sql);
            PreparedStatement pr = DatabaseConnector.getConnection().prepareStatement(sql);
            pr.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Restore.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    /**
     * Excute all sql query in file.
     *
     * @param pathIn of file.
     */
    public static void RecoveryDatabase(String pathIn) {

        String sql = FileOperator.readFile(pathIn);
        for (String s : Converter.getLineByLine(sql)) {
            try {
                System.out.println(s);
                PreparedStatement pr = DatabaseConnector.getConnection().prepareStatement(s);
                pr.execute();

            } catch (SQLException ex) {
                Logger.getLogger(Restore.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * Drop and restore all data in file.
     *
     * @param path of file
     */
    public static void RestoreData(String path) {
        GetMetaData a = new GetMetaData();
        Backup b = new Backup();
        //drop database
        try {
            Statement st = DatabaseConnector.getConnection().createStatement();

            for (String table : a.getTableName()) {

                for (String key : b.getFKConstraintName(table)) {
                    System.out.println(key);

                    String temp = "ALTER TABLE " + table + " ";
                    temp += "DROP CONSTRAINT " + key;
                    System.out.println(temp);
                    st.execute(temp);

                }

            }
            for (String table : a.getTableName()) {
                st.execute("DROP TABLE " + table);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Restore.class.getName()).log(Level.SEVERE, null, ex);
        }
        //restore data on file
        RecoveryDatabase(path);

    }
 
}
