/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

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

    public void reportCheckinHotel(Map<String, Object> par){
        con = utils.DatabaseConnector.getConnection();
         //  System.setProperty("jasper.reports.compile.class.path", System.getProperty("user.dir")+"\\src\\report\\"); 
        String sourceName = System.getProperty("user.dir") + "\\src\\report\\CheckinHotel.jrxml";
        try {
            JasperReport report = JasperCompileManager.compileReport(sourceName);
            JasperPrint fillReport = JasperFillManager.fillReport(report, par, con);
            JasperViewer.viewReport(fillReport, false);
//
//            ByteArrayOutputStream output = new ByteArrayOutputStream();
//            OutputStream outputfile = new FileOutputStream(new File(System.getProperty("user.dir") + "\\excel\\CheckinHotel.xls"));
//            JRXlsExporter exporterXls = new JRXlsExporter();
//            exporterXls.setParameter(JRXlsExporterParameter.JASPER_PRINT, fillReport);
//            exporterXls.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output);
//
//            exporterXls.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
//            exporterXls.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
//            exporterXls.exportReport();
//            outputfile.write(output.toByteArray());

        } catch (JRException ex) {
            Logger.getLogger(ReportManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reportCheckoutHotel(Map<String, Object> par) {
        con = utils.DatabaseConnector.getConnection();
       // System.setProperty("jasper.reports.compile.class.path", System.getProperty("user.dir") + "\\src\\report\\");
        String sourceName = System.getProperty("user.dir") + "\\src\\report\\CheckoutHotel.jrxml";
        try {
            JasperReport report = JasperCompileManager.compileReport(sourceName);
            JasperPrint fillReport = JasperFillManager.fillReport(report, par, con);
            JasperViewer.viewReport(fillReport, false);

            //auto gen xls file
//            ByteArrayOutputStream output = new ByteArrayOutputStream();
//            OutputStream outputfile = new FileOutputStream(new File(System.getProperty("user.dir") + "\\excel\\CheckoutHotel.xls"));
//            JRXlsExporter exporterXls = new JRXlsExporter();
//            exporterXls.setParameter(JRXlsExporterParameter.JASPER_PRINT, fillReport);
//            exporterXls.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output);
//            exporterXls.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
//            exporterXls.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
//            exporterXls.exportReport();
//            outputfile.write(output.toByteArray());
        } catch (JRException ex) {
            Logger.getLogger(ReportManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reportCheckoutOrderTable(Map<String, Object> par) {
        con = utils.DatabaseConnector.getConnection();
        //System.setProperty("jasper.reports.compile.class.path", System.getProperty("user.dir") + "\\src\\report\\");
        String sourceName = System.getProperty("user.dir") + "\\src\\report\\CheckoutOrderTable.jrxml";
        try {
            JasperReport report = JasperCompileManager.compileReport(sourceName);
            JasperPrint fillReport = JasperFillManager.fillReport(report, par, con);
            JasperViewer.viewReport(fillReport, false);

            //auto gen xls file
//            ByteArrayOutputStream output = new ByteArrayOutputStream();
//            OutputStream outputfile = new FileOutputStream(new File(System.getProperty("user.dir") + "\\excel\\CheckoutOrderTable.xls"));
//            JRXlsExporter exporterXls = new JRXlsExporter();
//            exporterXls.setParameter(JRXlsExporterParameter.JASPER_PRINT, fillReport);
//            exporterXls.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output);
//            exporterXls.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
//            exporterXls.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
//            exporterXls.exportReport();
//            outputfile.write(output.toByteArray());
        } catch (JRException ex) {
            Logger.getLogger(ReportManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reportCheckoutOrderService(Map<String, Object> par) {
        con = utils.DatabaseConnector.getConnection();
       // System.setProperty("jasper.reports.compile.class.path", System.getProperty("user.dir") + "\\src\\report\\");
        String sourceName = System.getProperty("user.dir") + "\\src\\report\\CheckoutOrderService.jrxml";
        try {
            JasperReport report = JasperCompileManager.compileReport(sourceName);

            JasperPrint fillReport = JasperFillManager.fillReport(report, par, con);
            JasperViewer.viewReport(fillReport, false);

            //auto gen xls file
//            ByteArrayOutputStream output = new ByteArrayOutputStream();
//            OutputStream outputfile = new FileOutputStream(new File(System.getProperty("user.dir") + "\\excel\\CheckoutOrderService.xls"));
//            JRXlsExporter exporterXls = new JRXlsExporter();
//            exporterXls.setParameter(JRXlsExporterParameter.JASPER_PRINT, fillReport);
//            exporterXls.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output);
//            exporterXls.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
//            exporterXls.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
//            exporterXls.exportReport();
//            outputfile.write(output.toByteArray());
        } catch (JRException ex) {
            Logger.getLogger(ReportManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reportCheckOutRestaurant(Map<String, Object> par) {
        con = utils.DatabaseConnector.getConnection();
       // System.setProperty("jasper.reports.compile.class.path", System.getProperty("user.dir") + "\\src\\report\\");
        String sourceName = System.getProperty("user.dir") + "\\src\\report\\CheckOutRestaurant.jrxml";
        System.out.println(sourceName);
        try {
            JasperReport report = JasperCompileManager.compileReport(sourceName);
            JasperPrint fillReport = JasperFillManager.fillReport(report, par, con);
            JasperViewer.viewReport(fillReport, false);

            //auto gen xls file
//            ByteArrayOutputStream output = new ByteArrayOutputStream();
//            OutputStream outputfile = new FileOutputStream(new File(System.getProperty("user.dir") + "\\excel\\CheckOutRestaurant.xls"));
//            JRXlsExporter exporterXls = new JRXlsExporter();
//            exporterXls.setParameter(JRXlsExporterParameter.JASPER_PRINT, fillReport);
//            exporterXls.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output);
//            exporterXls.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
//            exporterXls.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
//            exporterXls.exportReport();
//            outputfile.write(output.toByteArray());
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

            //auto gen xls file
//            ByteArrayOutputStream output = new ByteArrayOutputStream();
//            OutputStream outputfile = new FileOutputStream(new File(System.getProperty("user.dir") + "\\excel\\Bill.xls"));
//            JRXlsExporter exporterXls = new JRXlsExporter();
//            exporterXls.setParameter(JRXlsExporterParameter.JASPER_PRINT, fillReport);
//            exporterXls.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output);
//            exporterXls.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
//            exporterXls.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
//            exporterXls.exportReport();
//            outputfile.write(output.toByteArray());
        } catch (JRException ex) {
            Logger.getLogger(ReportManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ReportByMonth(Map<String, Object> par) {
        con = utils.DatabaseConnector.getConnection();
        String sourceName = System.getProperty("user.dir") + "\\src\\report\\StatisMonth.jrxml";
        System.out.println(sourceName);
        try {
            JasperReport report = JasperCompileManager.compileReport(sourceName);
            JasperPrint fillReport = JasperFillManager.fillReport(report, par, con);
            JasperViewer.viewReport(fillReport, false);

            //auto gen xls file
//            ByteArrayOutputStream output = new ByteArrayOutputStream();
//            OutputStream outputfile = new FileOutputStream(new File(System.getProperty("user.dir") + "\\excel\\StatisMonth.xls"));
//            JRXlsExporter exporterXls = new JRXlsExporter();
//            exporterXls.setParameter(JRXlsExporterParameter.JASPER_PRINT, fillReport);
//            exporterXls.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output);
//            exporterXls.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
//            exporterXls.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
//            exporterXls.exportReport();
//            outputfile.write(output.toByteArray());
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

            //auto gen xls file
//            ByteArrayOutputStream output = new ByteArrayOutputStream();
//            OutputStream outputfile = new FileOutputStream(new File(System.getProperty("user.dir") + "\\excel\\StatisYear.xls"));
//            JRXlsExporter exporterXls = new JRXlsExporter();
//            exporterXls.setParameter(JRXlsExporterParameter.JASPER_PRINT, fillReport);
//            exporterXls.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output);
//            exporterXls.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
//            exporterXls.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
//            exporterXls.exportReport();
//            outputfile.write(output.toByteArray());
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

            ////auto gen xls file
//            ByteArrayOutputStream output = new ByteArrayOutputStream();
//            OutputStream outputfile = new FileOutputStream(new File(System.getProperty("user.dir") + "\\excel\\ReportDay.xls"));
//            JRXlsExporter exporterXls = new JRXlsExporter();
//            exporterXls.setParameter(JRXlsExporterParameter.JASPER_PRINT, fillReport);
//            exporterXls.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output);
//            exporterXls.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
//            exporterXls.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
//            exporterXls.exportReport();
//            outputfile.write(output.toByteArray());
        } catch (JRException ex) {
            Logger.getLogger(ReportManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
