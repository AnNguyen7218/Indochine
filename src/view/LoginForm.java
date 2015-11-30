    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.Accounts;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import models.EmployeeEntityManager;

/**
 *
 * @author ADMIN
 */
public class LoginForm extends javax.swing.JFrame {

    boolean check = false;
    //Signup label original font
    Font originalFont;
    EmployeeEntityManager accModel = new EmployeeEntityManager();
    /**
     * Creates new form LoginForm
     */
    public LoginForm() {
        initComponents();
        for(Accounts ac:accModel.getAccFromDB()){
            cbbName.addItem(ac.getAccountName());
        }
        setIcon();

        setLocationRelativeTo(null);
        this.getRootPane().setDefaultButton(btnLogin);
    }

    private void login() {
        EmployeeEntityManager accModel = new EmployeeEntityManager();
        String pw = new String(txtPas.getPassword());
        //check user name and password
//        if (accModel.login(txtName.getText(), pw)) {
//            check = true;
//        }
        if (accModel.login(cbbName.getSelectedItem().toString(), pw)) {
            check = true;
        }
        if (check) {
            Accounts ac = new Accounts();
            //set role to prepare toolbar view
            for (Accounts a : accModel.getAll()) {
                ac.setAccountId(a.getAccountId());
                ac.setAccountName(cbbName.getSelectedItem().toString());
                ac.setAccountPass(a.getAccountPass());
                ac.setRoles(a.getRoles());
            }
            InternalMain hf = new InternalMain(this, ac);
            hf.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Login Failed....", "Failed", 2);
        }
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

        pnlTxt = new javax.swing.JPanel();
        lblMensenger = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblPass = new javax.swing.JLabel();
        txtPas = new javax.swing.JPasswordField();
        lblLogin = new javax.swing.JLabel();
        cbbName = new javax.swing.JComboBox();
        pnlButton = new javax.swing.JPanel();
        btnLogin = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setMinimumSize(new java.awt.Dimension(440, 430));
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
        lblLogin.setText("Login Account");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 0);
        pnlTxt.add(lblLogin, gridBagConstraints);

        cbbName.setMinimumSize(new java.awt.Dimension(200, 25));
        cbbName.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        pnlTxt.add(cbbName, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        getContentPane().add(pnlTxt, gridBagConstraints);

        pnlButton.setLayout(new java.awt.GridBagLayout());

        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/login.png"))); // NOI18N
        btnLogin.setText("Login");
        btnLogin.setMaximumSize(new java.awt.Dimension(87, 30));
        btnLogin.setMinimumSize(new java.awt.Dimension(87, 30));
        btnLogin.setPreferredSize(new java.awt.Dimension(87, 30));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 8;
        gridBagConstraints.ipady = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 20, 20, 20);
        pnlButton.add(btnLogin, gridBagConstraints);

        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cancel.png"))); // NOI18N
        btnReset.setText("Quit");
        btnReset.setMaximumSize(new java.awt.Dimension(87, 30));
        btnReset.setMinimumSize(new java.awt.Dimension(87, 30));
        btnReset.setPreferredSize(new java.awt.Dimension(87, 30));
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
        gridBagConstraints.insets = new java.awt.Insets(30, 20, 20, 20);
        pnlButton.add(btnReset, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        getContentPane().add(pnlButton, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        String pw = new String(txtPas.getPassword());
        if (cbbName.getSelectedItem().toString().isEmpty() || pw.isEmpty()) {
            lblMensenger.setText("Employee and Password Not Null...");
        } else {
            login();
        }

    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        this.dispose();
        System.exit(0);
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnReset;
    private javax.swing.JComboBox cbbName;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblMensenger;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPass;
    private javax.swing.JPanel pnlButton;
    private javax.swing.JPanel pnlTxt;
    private javax.swing.JPasswordField txtPas;
    // End of variables declaration//GEN-END:variables
}
