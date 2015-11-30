/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

//import entities.RecoveryDatabase;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Ashley
 */
public class RecoveryForm extends javax.swing.JFrame {

    /**
     * Creates new form RecoveryForm
     */
    static RecoveryForm instance = null;
    JFileChooser fc = new JFileChooser();

    public RecoveryForm() {
        initComponents();
        setIcon();
        setLocationRelativeTo(null);
    }

    public static RecoveryForm getIns() {
        if (instance == null) {
            instance = new RecoveryForm();
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

        lblFile = new javax.swing.JLabel();
        txtFile = new javax.swing.JTextField();
        btnBrowse = new javax.swing.JButton();
        bnRes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Restore");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        lblFile.setText("File:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(18, 18, 18, 18);
        getContentPane().add(lblFile, gridBagConstraints);

        txtFile.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        getContentPane().add(txtFile, gridBagConstraints);

        btnBrowse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/browse.png"))); // NOI18N
        btnBrowse.setText("Browse");
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(btnBrowse, gridBagConstraints);

        bnRes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/restore_item.png"))); // NOI18N
        bnRes.setText("Restore");
        bnRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnResActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(8, 11, 8, 4);
        getContentPane().add(bnRes, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        // Get path
        fc.showOpenDialog(this);
        txtFile.setText(fc.getSelectedFile().getPath());
    }//GEN-LAST:event_btnBrowseActionPerformed

    private void bnResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnResActionPerformed
        //Do restore data from file
        String path = fc.getSelectedFile().getPath();
        Backup_Restore.Restore.RecoveryData(path);
        JOptionPane.showMessageDialog(rootPane, "Successfully!","Success",1);

    }//GEN-LAST:event_bnResActionPerformed

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
            java.util.logging.Logger.getLogger(RecoveryForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RecoveryForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RecoveryForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RecoveryForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RecoveryForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnRes;
    private javax.swing.JButton btnBrowse;
    private javax.swing.JLabel lblFile;
    private javax.swing.JTextField txtFile;
    // End of variables declaration//GEN-END:variables
}