/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backup_Restore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.ActionEntityManager;
import models.CategoryEntityManager;
import models.CustomerEntityManager;
import models.DiaryEntityManager;
import models.EmployeeEntityManager;
import models.OrderEntityManager;
import models.OrderLineEntityManager;
import models.ProductEntityManager;
import models.RoleActionEntityManager;
import models.RoleEntityManager;
import utils.DatabaseConnector;

import utils.FileOperator;

/**
 *
 * @author Ashley
 */
public class Backup {

    ActionEntityManager actionModel = new ActionEntityManager();
    CategoryEntityManager cateModel = new CategoryEntityManager();
    CustomerEntityManager cusModel = new CustomerEntityManager();
    DiaryEntityManager diaryModel = new DiaryEntityManager();
    EmployeeEntityManager empModel = new EmployeeEntityManager();
    OrderEntityManager orderModel = new OrderEntityManager();
    OrderLineEntityManager ordLineModel = new OrderLineEntityManager();
    ProductEntityManager proModel = new ProductEntityManager();
    RoleActionEntityManager roleAcModel = new RoleActionEntityManager();
    RoleEntityManager roleModel = new RoleEntityManager();

    GetMetaData get_meta_data = new GetMetaData();
    static String s = "";

    public static boolean BackupDatabase(String path) {

        try {
            PreparedStatement pr = DatabaseConnector.getConnection().prepareStatement("BACKUP DATABASE Hotel TO DISK = '" + path + "' ");
            pr.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Create a 'CREATE TABLE' SQL query
     *
     * @param tableName
     * @return A 'CREATE TABLE' SQL query
     */
    public String createTable(String tableName) {

        String sql = "CREATE TABLE [dbo].[" + tableName + "](";
        for (GetMetaData.cRowInfos col : get_meta_data.getColumns(tableName)) {
            String nullable = "";
            if (col.getNull().equals("NO")) {
                nullable = "NOT NULL";
            } else {
                nullable = "NULL";
            }
            sql += "[" + col.rName + "]";
            if (col.rType.equals("int identity")) {
                col.rType = "int";
            }
            if (col.rType.equals("int") || col.rType.equals("bit") || col.rType.equals("datetime")) {
                sql += "[" + col.rType + "] ";
            } else {
                sql += "[" + col.rType + "] " + "(" + col.rLength + ") ";
            }
            sql += nullable + ",";

        }
        sql += "CONSTRAINT[PK_" + tableName + "] PRIMARY KEY";
        sql += " CLUSTERED([" + get_meta_data.getPK(tableName) + "] ASC)";
        sql += "WITH(PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON[PRIMARY]";
        sql += ") ON[PRIMARY] ;\n";

        return sql;
    }

    /**
     * Get a list of foreign key constraint name
     *
     *
     * @param tableName
     * @return List<String> list of foreign key constraint name
     */
    public List<String> getFKConstraintName(String tableName) throws SQLException {
        List<String> FKList = new ArrayList<String>();
        for (GetMetaData.foreignKeyInfo f : get_meta_data.getForeignKey(tableName)) {
            String a = "FK_" + tableName + "_" + f.table_name;
            FKList.add(a);
        }
        return FKList;
    }

    /**
     * Create a list of 'ADD CONSTRAINT' SQL query
     *
     * @param tableName
     * @return List<String> : A string list of 'ADD CONSTRAINT' SQL query
     */
    public List<String> createFKConstrain(String tableName) throws SQLException {
        List<String> cons = new ArrayList<>();
        String sql = "";
        for (GetMetaData.foreignKeyInfo f : get_meta_data.getForeignKey(tableName)) {
            String fk_table_name = f.table_name;
            String fk = f.col_name;
            String pk = f.pk_col_name;
            sql = "ALTER TABLE [dbo].[" + tableName + "]  WITH CHECK ADD  CONSTRAINT [FK_" + tableName + "_" + fk_table_name + "] FOREIGN KEY([" + fk + "])";
            sql += "REFERENCES [dbo].[" + fk_table_name + "] ([" + pk + "]) ;\n";
            cons.add(sql);
        }

        return cons;
    }

    /**
     * Create a list of 'INSERT' SQL query about HamAction Object
     *
     * @param
     * @return List<String> : A string list of 'INSERT' SQL query about
     * HamAction Object
     */
    public List<String> inserAction() {
        List<String> rs = new ArrayList<>();
        String sql = "";
        String table_name = "Ham_Action";
        //list of column
        ArrayList<String> col_name = new ArrayList<>();
        for (GetMetaData.cRowInfos col : get_meta_data.getColumns(table_name)) {
            col_name.add(col.rName);

        }
        //list of record
//        List<HamAction> list = actionModel.getAllFromDB();
//
//        for (HamAction a : list) {
//            sql = "INSERT INTO " + table_name + "(";
//            for (int i = 0; i < col_name.size(); i++) {
//                if (i == (col_name.size()) - 1) {
//                    sql += col_name.get(i);
//                } else {
//                    sql += col_name.get(i) + ",";
//                }
//            }
//            sql += ") VALUES(";
//            sql += a.getId() + ",'" + a.getName() + "','" + a.getIsActive() + "') ;\n";
//            rs.add(sql);
//        }

        return rs;

    }

    /**
     * Create a list of 'INSERT' SQL query about HamRole Object
     *
     * @param
     * @return List<String> : A string list of 'INSERT' SQL query about HamRole
     * Object
     */
    public List<String> insertValueRole() {
        List<String> result = new ArrayList<>();
        String sql = "";
        String table_name = "Ham_Role";
        //list of column
        ArrayList<String> col_name = new ArrayList<>();
        for (GetMetaData.cRowInfos col : get_meta_data.getColumns(table_name)) {
            col_name.add(col.rName);

        }
        //list of record
//        List<HamRole> list = roleModel.getAllFromDB();
//
//        for (HamRole a : list) {
//
//            sql = "INSERT INTO " + table_name + "(";
//            for (int i = 0; i < col_name.size(); i++) {
//                if (i == (col_name.size()) - 1) {
//                    sql += col_name.get(i);
//                } else {
//                    sql += col_name.get(i) + ",";
//                }
//            }
//            sql += ") VALUES(";
//            sql += a.getId() + ",'" + a.getName() + "','" + a.getIsActive() + "') ;\n";
//            result.add(sql);
//        }
       return result;
    }

    /**
     * Create a list of 'INSERT' SQL query about Role_Action Object
     *
     * @param
     * @return List<String> : A string list of 'INSERT' SQL query about
     * Role_Action Object
     */
    public List<String> inserRoleAction() {
        List<String> rs = new ArrayList<>();
        String sql = "";
        String table_name = "Ham_Role_Action";
        //list of column
        ArrayList<String> col_name = new ArrayList<>();
        for (GetMetaData.cRowInfos col : get_meta_data.getColumns(table_name)) {
            col_name.add(col.rName);

        }
        //list of record
//        List<HamRoleAction> list = roleAcModel.getAllFromDB();
//
//        for (HamRoleAction a : list) {
//            sql = "INSERT INTO " + table_name + "(";
//            for (int i = 0; i < col_name.size(); i++) {
//                if (i == (col_name.size()) - 1) {
//                    sql += col_name.get(i);
//                } else {
//                    sql += col_name.get(i) + ",";
//                }
//            }
//            sql += ") VALUES(";
//            sql += a.getId() + "," + a.getHamRole().getId() + "," + a.getHamAction().getId() + ") ;\n";
//            rs.add(sql);
//        }

        return rs;

    }

    /**
     * Create a list of 'INSERT' SQL query about HamCategory Object
     *
     * @param
     * @return List<String> : A string list of 'INSERT' SQL query about
     * HamCategory Object
     */
    public List<String> insertValueCategory() {
        List<String> result = new ArrayList<>();
        String sql = "";
        String table_name = "Ham_Category";
        //list of column
        ArrayList<String> col_name = new ArrayList<>();
        for (GetMetaData.cRowInfos col : get_meta_data.getColumns(table_name)) {
            col_name.add(col.rName);

        }
        //list of record
//        List<HamCategory> list = cateModel.getAllFromDB();
//
//        for (HamCategory a : list) {
//
//            sql = "INSERT INTO " + table_name + "(";
//            for (int i = 0; i < col_name.size(); i++) {
//                if (i == (col_name.size()) - 1) {
//                    sql += col_name.get(i);
//                } else {
//                    sql += col_name.get(i) + ",";
//                }
//            }
//            sql += ") VALUES(";
//            sql += a.getId() + ",'" + a.getName() + "','" + a.getIsActive() + "') ;\n";
//            result.add(sql);
//        }
        return result;
    }

    /**
     * Create a list of 'INSERT' SQL query about HamProduct Object
     *
     * @param
     * @return List<String> : A string list of 'INSERT' SQL query about
     * HamProduct Object
     */
    public List<String> insertValueProduct() {
        List<String> result = new ArrayList<>();
        String sql = "";
        String table_name = "Ham_Product";
        //list of column
        ArrayList<String> col_name = new ArrayList<>();
        for (GetMetaData.cRowInfos col : get_meta_data.getColumns(table_name)) {
            col_name.add(col.rName);

        }
        //list of record
//        List<HamProduct> list = proModel.getAllFromDB();
//        if (list == null) {
//        } else {
//            for (HamProduct a : list) {
//                sql = "INSERT INTO " + table_name + "(";
//                for (int i = 0; i < col_name.size(); i++) {
//                    if (i == (col_name.size()) - 1) {
//                        sql += col_name.get(i);
//                    } else {
//                        sql += col_name.get(i) + ",";
//                    }
//                }
//                sql += ") VALUES(";
//                sql += a.getId() + ",'" + a.getProductName() + "','" + a.getProductDescription() + "',"
//                        + a.getExpectedSalePrice() + "," + a.getQuantity() + "," + a.getHamCategory().getId()
//                        + "," + a.getImportPrice() + ",'" + a.getIsActive();
//                if (a.getImportDate() != null) {
//                    sql += "','" + a.getImportDate() + "') ;\n";
//                } else {
//                    sql += "'," + null + ") ;\n";
//                }
//                result.add(sql);
//            }
//        }
        return result;

    }

    /**
     * Create a list of 'INSERT' SQL query about HamEmployee Object
     *
     * @param
     * @return List<String> : A string list of 'INSERT' SQL query about
     * HamEmployee Object
     */
    public List<String> insertEmployee() {
        List<String> result = new ArrayList<>();
        String sql = "";
        String table_name = "Ham_Employee";
        //list of column
        ArrayList<String> col_name = new ArrayList<>();
        for (GetMetaData.cRowInfos col : get_meta_data.getColumns(table_name)) {
            col_name.add(col.rName);

        }
        //list of record
//        List<HamEmployee> list = empModel.getAllFromDB();
//
//        for (HamEmployee a : list) {
//            sql = "INSERT INTO " + table_name + "(";
//            for (int i = 0; i < col_name.size(); i++) {
//                if (i == (col_name.size()) - 1) {
//                    sql += col_name.get(i);
//                } else {
//                    sql += col_name.get(i) + ",";
//                }
//            }
//            sql += ") VALUES(";
//            sql += a.getId() + ",'" + a.getName() + "','" + a.getAddress() + "','"
//                    + a.getContactNo() + "','" + a.getUsername()
//                    + "','" + a.getPassword() + "','" + a.getDepartment() + "'," + a.getHamRole().getId()
//                    + ",'" + a.getIsActive() + "') ;\n";
//            result.add(sql);
//        }

        return result;

    }

    /**
     * Create a list of 'INSERT' SQL query about HamDiary Object
     *
     * @param
     * @return List<String> : A string list of 'INSERT' SQL query about HamDiary
     * Object
     */
    public List<String> insertDiary() {
        List<String> result = new ArrayList<>();
        String sql = "";
        String table_name = "Ham_Diary";
        //list of column
        ArrayList<String> col_name = new ArrayList<>();
        for (GetMetaData.cRowInfos col : get_meta_data.getColumns(table_name)) {
            col_name.add(col.rName);

        }
        //list of record
//        List<HamDiary> list = diaryModel.getAllFromDB();
//
//        for (HamDiary a : list) {
//            sql = "INSERT INTO " + table_name + "(";
//            for (int i = 0; i < col_name.size(); i++) {
//                if (i == (col_name.size()) - 1) {
//                    sql += col_name.get(i);
//                } else {
//                    sql += col_name.get(i) + ",";
//                }
//            }
//            sql += ") VALUES(";
//            sql += a.getId() + "," + a.getHamEmployee().getId() + ",'" + a.getTime() + "','" + a.getDescription() + "') ;\n";
//            result.add(sql);
//        }

        return result;

    }

    /**
     * Create a list of 'INSERT' SQL query about HamBillOrder Object
     *
     * @param
     * @return List<String> : A string list of 'INSERT' SQL query about
     * HamBillOrder Object
     */
    public List<String> insertOrder() {
        List<String> result = new ArrayList<>();
        String sql = "";
        String table_name = "Ham_BillOrder";
        //list of column
        ArrayList<String> col_name = new ArrayList<>();
        for (GetMetaData.cRowInfos col : get_meta_data.getColumns(table_name)) {
            col_name.add(col.rName);

        }
        //list of record
//        List<HamBillOrder> list = orderModel.getAllFromDB();
//        if (list == null) {
//        } else {
//            for (HamBillOrder a : list) {
//                sql = "INSERT INTO " + table_name + "(";
//                for (int i = 0; i < col_name.size(); i++) {
//                    if (i == (col_name.size()) - 1) {
//                        sql += col_name.get(i);
//                    } else {
//                        sql += col_name.get(i) + ",";
//                    }
//                }
//                sql += ") VALUES(";
//                sql += a.getId() + ",'" + a.getOrderDate() + "'," + a.getHamEmployee().getId()
//                        + "," + a.getHamCustomer().getId() + ",'" + a.getIsActive() + "') ;\n";
//                result.add(sql);
//            }
//        }
        return result;

    }

    /**
     * Create a list of 'INSERT' SQL query about HamOrderLine Object
     *
     * @param
     * @return List<String> : A string list of 'INSERT' SQL query about
     * HamOrderLine Object
     */
    public List<String> insertOrderLine() {
        List<String> result = new ArrayList<>();
        String sql = "";
        String table_name = "Ham_OrderLine";
        //list of column
        ArrayList<String> col_name = new ArrayList<>();
        for (GetMetaData.cRowInfos col : get_meta_data.getColumns(table_name)) {
            col_name.add(col.rName);

        }
        //list of record
//        List<HamOrderLine> list = ordLineModel.getAllFromDB();
//        if (list == null) {
//        } else {
//            for (HamOrderLine a : list) {
//                sql = "INSERT INTO " + table_name + "(";
//                for (int i = 0; i < col_name.size(); i++) {
//                    if (i == (col_name.size()) - 1) {
//                        sql += col_name.get(i);
//                    } else {
//                        sql += col_name.get(i) + ",";
//                    }
//                }
//                sql += ") VALUES(";
//                sql += a.getId() + "," + a.getHamBillOrder().getId() + "," + a.getHamProduct().getId() + ","
//                        + a.getQuantity() + "," + a.getActualSalePrice() + ") ;\n";
//                result.add(sql);
//            }
//        }
        return result;

    }

    /**
     * Create a list of 'INSERT' SQL query about HamCustomer Object
     *
     * @param
     * @return List<String> : A string list of 'INSERT' SQL query about
     * HamCustomer Object
     */
    public List<String> insertCustomer() {
        List<String> result = new ArrayList<>();
        String sql = "";
        String table_name = "Ham_Customer";
        //list of column
        ArrayList<String> col_name = new ArrayList<>();
        for (GetMetaData.cRowInfos col : get_meta_data.getColumns(table_name)) {
            col_name.add(col.rName);

        }
        //list of record
//        List<HamCustomer> list = cusModel.getAllFromDB();
//
//        for (HamCustomer a : list) {
//
//            sql = "INSERT INTO " + table_name + "(";
//            for (int i = 0; i < col_name.size(); i++) {
//                if (i == (col_name.size()) - 1) {
//                    sql += col_name.get(i);
//                } else {
//                    sql += col_name.get(i) + ",";
//                }
//            }
//            sql += ") VALUES(";
//            sql += a.getId() + ",'" + a.getCustomerName() + "','" + a.getContactNo() + "','"
//                    + a.getAddress() + "','" + a.getIsActive() + "') ;\n";
//            result.add(sql);
//        }
        return result;
    }

    /**
     * Save all data into file.
     *
     * @param path of file
     */
    public static void BackUpData(String path) throws SQLException {
        Backup a = new Backup();
        Vector<String> tableList = a.get_meta_data.getTableName();
        s = "USE [FairDeal] ;\n";

        FileOperator.appendToFile(s, path);

        for (String table : tableList) {
            System.out.println("ad" + table);
            if (table.equals("sysdiagrams")) {
                continue;
            } else {
                String sql = a.createTable(table);
                FileOperator.appendToFile(sql, path);
            }
        }
        for (String data
                : a.insertEmployee()) {
            FileOperator.appendToFile(data, path);
        }
        for (String data
                : a.inserAction()) {
            FileOperator.appendToFile(data, path);
        }

        for (String data
                : a.insertCustomer()) {
            FileOperator.appendToFile(data, path);
        }

        for (String data
                : a.insertValueCategory()) {
            FileOperator.appendToFile(data, path);
        }

        for (String data
                : a.insertValueProduct()) {
            FileOperator.appendToFile(data, path);
        }

        for (String data
                : a.insertValueRole()) {
            FileOperator.appendToFile(data, path);
        }

        for (String data
                : a.inserRoleAction()) {
            FileOperator.appendToFile(data, path);
        }

        for (String data
                : a.insertDiary()) {
            FileOperator.appendToFile(data, path);
        }

        for (String data
                : a.insertOrder()) {
            FileOperator.appendToFile(data, path);
        }
        for (String data
                : a.insertOrderLine()) {
            FileOperator.appendToFile(data, path);
        }

        for (String table : tableList) {
            for (String sql : a.createFKConstrain(table)) {
                FileOperator.appendToFile(sql, path);
            }
        }
    }

}
