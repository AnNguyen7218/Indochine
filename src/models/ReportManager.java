/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Administrator
 */
public class ReportManager {

    public ReportManager() {
    }

    static Connection con;
//    public void report1() {
//        ProductEntityManager prModel = new ProductEntityManager();
//        List<Map<String, ?>> dataSource = new ArrayList<Map<String, ?>>();
//        for (Product p : prModel.getAll()) {
//            Map<String, Object> m = new HashMap<String, Object>();
//            m.put("ID", p.getId());
//            m.put("Name", p.getName());
//            m.put("ImporDate", p.getImportDate());
//            m.put("Quantity", p.getQuantity());
//            m.put("Category", p.getCategory());
//            m.put("PublisherID", p.getPublisher());
//            m.put("UnitID", p.getUnit());
//            dataSource.add(m);
//        }
//        JRDataSource jrDataSource =  new JRMapCollectionDataSource(dataSource);
//        String sourceName = System.getProperty("user.dir")+"\\src\\port\\ReportProduct.jrxml";
//        try {
//            JasperReport  report = JasperCompileManager.compileReport(sourceName);
//            JasperPrint fillReport = JasperFillManager.fillReport(report, null, jrDataSource);
//            JasperViewer.viewReport(fillReport);
//        } catch (JRException ex) {
//            Logger.getLogger(ReportManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    //DE CHO BAN DUNG CO XOA NHA

    public void reportCheckOutRestaurant(Map<String, Object> par) {
        con = utils.DatabaseConnector.getConnection();
        String sourceName = System.getProperty("user.dir") + "\\src\\report\\CheckOutRestaurant.jrxml";
        try {
            JasperReport report = JasperCompileManager.compileReport(sourceName);
            JasperPrint fillReport = JasperFillManager.fillReport(report, par, con);
            JasperViewer.viewReport(fillReport, false);
        } catch (JRException ex) {
            Logger.getLogger(ReportManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void reportBill(Map<String, Object> par) {
        con = utils.DatabaseConnector.getConnection();
        String sourceName = System.getProperty("user.dir") + "\\src\\report\\Bill.jrxml";
        try {
            JasperReport report = JasperCompileManager.compileReport(sourceName);
            JasperPrint fillReport = JasperFillManager.fillReport(report, par, con);
            JasperViewer.viewReport(fillReport, false);
        } catch (JRException ex) {
            Logger.getLogger(ReportManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ReportByMonth(Map<String, Object> par) {
        con = utils.DatabaseConnector.getConnection();
        String sourceName = System.getProperty("user.dir") + "\\src\\report\\StatisMonth.jrxml";
        try {
            JasperReport report = JasperCompileManager.compileReport(sourceName);
            JasperPrint fillReport = JasperFillManager.fillReport(report, par, con);
            JasperViewer.viewReport(fillReport, false);
        } catch (JRException ex) {
            Logger.getLogger(ReportManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ReportByYear(Map<String, Object> par) {
        con = utils.DatabaseConnector.getConnection();
        String sourceName = System.getProperty("user.dir") + "\\src\\report\\StatisYear.jrxml";
        try {
            JasperReport report = JasperCompileManager.compileReport(sourceName);
            JasperPrint fillReport = JasperFillManager.fillReport(report, par, con);
            JasperViewer.viewReport(fillReport, false);
        } catch (JRException ex) {
            Logger.getLogger(ReportManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ReportByDay(Map<String, Object> par) {
        con = utils.DatabaseConnector.getConnection();
        String sourceName = System.getProperty("user.dir") + "\\src\\report\\ReportDay.jrxml";
        try {
            JasperReport report = JasperCompileManager.compileReport(sourceName);
            JasperPrint fillReport = JasperFillManager.fillReport(report, par, con);
            JasperViewer.viewReport(fillReport, false);
        } catch (JRException ex) {
            Logger.getLogger(ReportManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
