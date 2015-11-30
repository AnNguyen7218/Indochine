/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backup_Restore;

import java.sql.ResultSet;
import java.sql.DatabaseMetaData;

import java.sql.SQLException;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import utils.DatabaseConnector;

/**
 *
 * @author Ashley
 */
public class GetMetaData {

    public static DatabaseMetaData metadata;

    /**
     * Get meta data.
     */
    public GetMetaData() {
        try {
            GetMetaData.metadata = DatabaseConnector.getConnection().getMetaData();
        } catch (SQLException ex) {
            Logger.getLogger(GetMetaData.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    /**
     * Get all table name of data from meta data.
     * @return Vector<String> of table name.
     */
    public  Vector<String> getTableName() {
        Vector<String> allField = new Vector<String>();
        try {
            String[] tableTypes = {"TABLE", "VIEW"};
            ResultSet table = metadata.getTables(null, "dbo", null, tableTypes);

            String TableName = "";
            while (table.next()) {
                TableName = table.getString("TABLE_NAME");
                allField.addElement(TableName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return allField;
    }

    /**
     * Get all column structure of data from meta data.
     * @return Vector<cRowInfos> of column structure.
     */
    public Vector<cRowInfos> getColumns(String tableName) {
        Vector<cRowInfos> tblInfo = new Vector<cRowInfos>();

        try {
            ResultSet columnName = metadata.getColumns(null, null, tableName, null);
            while (columnName.next()) {
                String n = (columnName.getString("COLUMN_NAME"));
                String t = "" + (columnName.getString("TYPE_NAME"));
                String s = "" + columnName.getString("COLUMN_SIZE");
                String nu = "" + columnName.getString("IS_NULLABLE");
                tblInfo.addElement(new cRowInfos(n, t, s, nu));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tblInfo;
    }

    /**
     * Get primary key of table.
     * @param  tableName 
     * @return primary key column name
     */
    public  String getPK(String tableName) {
        String colPK = null;
        try {

            ResultSet colName = metadata.getPrimaryKeys(null, null, tableName);
            while (colName.next()) {
                colPK = colName.getString("COLUMN_NAME");
            }

        } catch (SQLException ex) {
            Logger.getLogger(GetMetaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return colPK;
    }

    /**
     * Get all information of foreign key of data from meta data.
     * @return Vector<foreignKeyInfo> of information of foreign key.
     */
    public Vector<foreignKeyInfo> getForeignKey(String tableName) throws SQLException {
        
        Vector<foreignKeyInfo> fkList = new Vector<>();
        String table_name = "";
        String col_name = "";
        String  pk_col_name="";
        ResultSet rs = metadata.getImportedKeys(null, null, tableName);
        while (rs.next()) {
            //tên bảng được tableName liên kết tới
            table_name = rs.getString("PKTABLE_NAME");
            //khóa ngoại của tableName
            col_name = rs.getString("FKCOLUMN_NAME");
            //khóa chính của bảng được tableName liên kết tới
            pk_col_name = rs.getString("PKCOLUMN_NAME");
            fkList.add(new foreignKeyInfo(table_name, col_name,pk_col_name));
        }
        return fkList;
    }

    /**
     * Define a class about foreign key info:
     * - table name of reference table
     * - foreign column name
     * - reference column name
     * 
     */
    public class foreignKeyInfo {

        protected String table_name;
        protected String col_name;
        protected String pk_col_name;

        public foreignKeyInfo(String table_name, String col_name,String pk_col_name) {
            this.table_name = table_name;
            this.col_name = col_name;
            this.pk_col_name = pk_col_name;
        }

        public String getPk_col_name() {
            return pk_col_name;
        }

        public void setPk_col_name(String pk_col_name) {
            this.pk_col_name = pk_col_name;
        }

        
        
        public String getTable_name() {
            return table_name;
        }

        public void setTable_name(String table_name) {
            this.table_name = table_name;
        }

        public String getCol_name() {
            return col_name;
        }

        public void setCol_name(String col_name) {
            this.col_name = col_name;
        }

    }

    /**
     * Define of a column information
     * -name of column
     * -size of column
     * -type of column
     * -null type
     */
    public class cRowInfos {

        protected String rName;
        protected String rLength;
        protected String rType;
        protected String rNullable;

        cRowInfos(String n,String t, String l, String na) {
            rName = n;
            rLength = l;
            rType = t;
            rNullable = na;
        }

        public String getName() {
            return rName;
        }

        public String getLength() {
            return rLength;
        }

        public String getType() {
            return rType;
        }

        public String getNull() {
            return rNullable;
        }
    }

//    public static void main(String[] args) throws SQLException {
//        GetMetaData a = new GetMetaData();
//        
//        for(String table : a.getTableName() ){
//            for(cRowInfos column : a.getColumns(table)){
//                System.out.println(column.getType());
//                System.out.println(column.getNull());
//                
//            }
//        }
//    }
}
