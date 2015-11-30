/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

import models.ReportManager;

/**
 *
 * @author Administrator
 */
public class ReportMonth extends javax.swing.JFrame {

    /**
     * Creates new form ReportMonth
     */
    ReportManager rpDAO = new ReportManager();
    static ReportMonth instance = null;

    public ReportMonth() {
        initComponents();
        setIcon();
        cb_Time.setSelectedIndex(10);
        cb_Year.setSelectedIndex(4);
        setLocationRelativeTo(null);
    }

    public static ReportMonth getIns() {
        if (instance == null) {
            instance = new ReportMonth();
        }
        return instance;

    }

    public void setIcon() {
        this.setIconImage(new ImageIcon(getClass().getResource("/image/logoTitle.png")).getImage());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblReport = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        pnlCombobox = new javax.swing.JPanel();
        cb_Time = new javax.swing.JComboBox();
        lblMonth = new javax.swing.JLabel();
        cb_Year = new javax.swing.JComboBox();
        pnlButton = new javax.swing.JPanel();
        bt_Cancel = new javax.swing.JButton();
        bt_Ok = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Report");
        setMinimumSize(new java.awt.Dimension(400, 250));
        setPreferredSize(new java.awt.Dimension(400, 250));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        lblReport.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblReport.setForeground(new java.awt.Color(0, 0, 204));
        lblReport.setText("REPORT BY MONTH");
        getContentPane().add(lblReport, new java.awt.GridBagConstraints());

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new java.awt.GridBagLayout());

        pnlCombobox.setLayout(new java.awt.GridBagLayout());

        cb_Time.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlCombobox.add(cb_Time, gridBagConstraints);

        lblMonth.setText("Month");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlCombobox.add(lblMonth, gridBagConstraints);

        cb_Year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020" }));
        cb_Year.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_YearActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlCombobox.add(cb_Year, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 15, 20, 10);
        jPanel3.add(pnlCombobox, gridBagConstraints);

        pnlButton.setLayout(new java.awt.GridBagLayout());

        bt_Cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cancel.png"))); // NOI18N
        bt_Cancel.setText("Exit");
        bt_Cancel.setMaximumSize(new java.awt.Dimension(110, 32));
        bt_Cancel.setMinimumSize(new java.awt.Dimension(110, 32));
        bt_Cancel.setPreferredSize(new java.awt.Dimension(110, 32));
        bt_Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_CancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlButton.add(bt_Cancel, gridBagConstraints);

        bt_Ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/printer.png"))); // NOI18N
        bt_Ok.setText("Report");
        bt_Ok.setMaximumSize(new java.awt.Dimension(130, 32));
        bt_Ok.setMinimumSize(new java.awt.Dimension(130, 32));
        bt_Ok.setPreferredSize(new java.awt.Dimension(130, 32));
        bt_Ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_OkActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlButton.add(bt_Ok, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel3.add(pnlButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        getContentPane().add(jPanel3, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_OkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_OkActionPerformed
        // TODO add your handling code here:
        //Get data from interface and declare some var
        String month = cb_Time.getSelectedItem().toString();
        String year = cb_Year.getSelectedItem().toString();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("month", month);
        param.put("year", year);
        rpDAO.ReportByMonth(param);
    }//GEN-LAST:event_bt_OkActionPerformed

    private void bt_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_CancelActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_bt_CancelActionPerformed

    private void cb_YearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_YearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_YearActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReportMonth.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportMonth.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportMonth.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportMonth.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReportMonth().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_Cancel;
    private javax.swing.JButton bt_Ok;
    private javax.swing.JComboBox cb_Time;
    private javax.swing.JComboBox cb_Year;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblMonth;
    private javax.swing.JLabel lblReport;
    private javax.swing.JPanel pnlButton;
    private javax.swing.JPanel pnlCombobox;
    // End of variables declaration//GEN-END:variables
}
