/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.Accounts;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import models.EmployeeEntityManager;
import utils.MD5Encryption;

/**
 *
 * @author Ashley
 */
public class ChangePassword extends javax.swing.JFrame {

    EmployeeEntityManager empModel = new EmployeeEntityManager();
    static ChangePassword instance = null;

    /**
     * Creates new form ChangPassword
     */
    public ChangePassword(Accounts a) {
        initComponents();
        setIcon();
        setLocationRelativeTo(null);
        this.getRootPane().setDefaultButton(btnConfir);
        txtName.setText(a.getAccountName());
    }

    public ChangePassword() {
        initComponents();

        setIcon();
        setLocationRelativeTo(null);
        this.getRootPane().setDefaultButton(btnConfir);

    }

    public static ChangePassword getIns() {
        if (instance == null) {
            instance = new ChangePassword();
        }
        return instance;

    }

    public void setIcon() {
        this.setIconImage(new ImageIcon(getClass().getResource("/image/logoTitle.png")).getImage());
    }

    public void changePass(Accounts temp) {
        String pw = new String(txtPas.getPassword());
        temp.setAccountPass(MD5Encryption.encryptWithMD5(pw));
        int rep = JOptionPane.showConfirmDialog(rootPane, "Are you sure ?", "Warning", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if (rep == JOptionPane.YES_OPTION) {
            if (empModel.edit(temp)) {
                JOptionPane.showMessageDialog(rootPane, "Your password changed successfully!", "Success", 1);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Failed", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
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

        pnlTxt = new javax.swing.JPanel();
        lblMensenger = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblPass = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtPas = new javax.swing.JPasswordField();
        lblLogin = new javax.swing.JLabel();
        pnlButton = new javax.swing.JPanel();
        btnConfir = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(440, 430));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        pnlTxt.setLayout(new java.awt.GridBagLayout());

        lblMensenger.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblMensenger.setForeground(new java.awt.Color(255, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 11, 0);
        pnlTxt.add(lblMensenger, gridBagConstraints);

        lblName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblName.setText("Username: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        pnlTxt.add(lblName, gridBagConstraints);

        lblPass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPass.setText("Password: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        pnlTxt.add(lblPass, gridBagConstraints);

        txtName.setEditable(false);
        txtName.setMinimumSize(new java.awt.Dimension(200, 25));
        txtName.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        pnlTxt.add(txtName, gridBagConstraints);

        txtPas.setMinimumSize(new java.awt.Dimension(200, 25));
        txtPas.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        pnlTxt.add(txtPas, gridBagConstraints);

        lblLogin.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/user.png"))); // NOI18N
        lblLogin.setText("Change Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 0);
        pnlTxt.add(lblLogin, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        getContentPane().add(pnlTxt, gridBagConstraints);

        pnlButton.setLayout(new java.awt.GridBagLayout());

        btnConfir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/login.png"))); // NOI18N
        btnConfir.setText("Confirm");
        btnConfir.setMaximumSize(new java.awt.Dimension(87, 30));
        btnConfir.setMinimumSize(new java.awt.Dimension(87, 30));
        btnConfir.setPreferredSize(new java.awt.Dimension(100, 30));
        btnConfir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 8;
        gridBagConstraints.ipady = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(27, 20, 17, 20);
        pnlButton.add(btnConfir, gridBagConstraints);

        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cancel.png"))); // NOI18N
        btnReset.setText("Quit");
        btnReset.setMaximumSize(new java.awt.Dimension(87, 30));
        btnReset.setMinimumSize(new java.awt.Dimension(87, 30));
        btnReset.setPreferredSize(new java.awt.Dimension(100, 30));
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(27, 20, 17, 20);
        pnlButton.add(btnReset, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        getContentPane().add(pnlButton, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirActionPerformed
        // TODO add your handling code here:
        String pw = new String(txtPas.getPassword());
        if (pw.isEmpty()) {
            lblMensenger.setText("Type your new password here...");
        } else {
            if (empModel.find(txtName.getText()) != null) {
                changePass(empModel.find(txtName.getText()));
            }
        }
    }//GEN-LAST:event_btnConfirActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        this.dispose();

    }//GEN-LAST:event_btnResetActionPerformed

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
            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChangePassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfir;
    private javax.swing.JButton btnReset;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblMensenger;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPass;
    private javax.swing.JPanel pnlButton;
    private javax.swing.JPanel pnlTxt;
    private javax.swing.JTextField txtName;
    private javax.swing.JPasswordField txtPas;
    // End of variables declaration//GEN-END:variables
}