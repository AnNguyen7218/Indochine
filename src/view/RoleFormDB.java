/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.Functions;
import entities.Roles;
import java.util.HashSet;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import models.ActionEntityManager;
import models.RoleEntityManager;
import utils.CustomCheckBoxList;

/**
 *
 * @author ADMIN
 */
public final class RoleFormDB extends javax.swing.JInternalFrame {

    /**
     * Creates new form RoleFormDB
     */
    static RoleFormDB instance = null;
    ActionEntityManager funcModel = new ActionEntityManager();
    RoleEntityManager roleModel = new RoleEntityManager();

    public RoleFormDB() {
        initComponents();
        setIcon();
        loadFunctionListToTable();
        loadRoleListToTable();
        lblId.setVisible(false);
        lblIdFun.setVisible(false);
        txtRoleId.setVisible(false);
        txtIdFun.setVisible(false);
        //adjust column
        tblRole.getColumn("ID").setMaxWidth(0);
        tblRole.getColumn("ID").setMinWidth(0);
        tblFunction.getColumn("ID").setMaxWidth(0);
        tblFunction.getColumn("ID").setMinWidth(0);
        //fill form when chose a record on table
        tblFunction.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedIndex = tblFunction.getSelectedRow();
                if (selectedIndex != -1) {

                    txtIdFun.setText(tblFunction.getValueAt(selectedIndex, 0).toString());
                    txtNameFun.setText(tblFunction.getValueAt(selectedIndex, 1).toString());

                }
            }
        });
        //fill form when a record's choosen
        tblRole.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedIndex = tblRole.getSelectedRow();
                if (selectedIndex != -1) {

                    txtRoleId.setText(tblRole.getValueAt(selectedIndex, 0).toString());
                    txtRoleName.setText(tblRole.getValueAt(selectedIndex, 1).toString());

                    loadEnabledFunction();

                }
            }
        });
//        centerTextCell(tblFunction);
//        centerTextCell(tblRole);
    }

    public static RoleFormDB getIns() {
        if (instance == null) {
            instance = new RoleFormDB();
        }
        return instance;

    }

    public void setIcon() {
        this.setFrameIcon(new ImageIcon(getClass().getResource("/image/logoTitle.png")));
    }

    //set text to center
    void centerTextCell(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    /**
     * Load data in to table
     */
    void loadFunctionListToTable() {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }
        };

        dtm.addColumn("ID");
        dtm.addColumn("Name");

        DefaultListModel listModel = new DefaultListModel<JCheckBox>();
        //fill data
        for (Functions func : funcModel.getAll()) {
            dtm.addRow(new Object[]{func.getFunctionId(), func.getFunctionName()});
            listModel.addElement(new JCheckBox(func.getFunctionName()));
        }

        tblFunction.setModel(dtm);

        lstFunction = new CustomCheckBoxList(listModel);
        scrFunctionList.setViewportView(lstFunction);
        //adjust column
        tblFunction.getColumn("ID").setMaxWidth(0);
        tblFunction.getColumn("ID").setMinWidth(0);
        //reload data
        loadEnabledFunction();
    }

    //load function which isActive = true
    void loadEnabledFunction() {
        lstFunction.clearSelection();
        Set<Functions> funcList = roleModel.getFunctionList(roleModel.find(txtRoleName.getText()));
        for (Functions func : funcModel.getAll()) {
            if (funcList != null && funcList.contains(func)) {
                ((CustomCheckBoxList) lstFunction).setSelectedItem(func.getFunctionName(), true);
            }
        }

    }

    void loadRoleListToTable() {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }
        };

        dtm.addColumn("ID");
        dtm.addColumn("Name");

        for (Roles role : roleModel.getAll()) {
            dtm.addRow(new Object[]{role.getRoleId(), role.getRoleName()});
        }

        tblRole.setModel(dtm);
        //adjust column
        tblRole.getColumn("ID").setMaxWidth(0);
        tblRole.getColumn("ID").setMinWidth(0);
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

        pnlRoleLeft = new javax.swing.JPanel();
        pnlInput = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        txtRoleName = new javax.swing.JTextField();
        btncreate = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lblId = new javax.swing.JLabel();
        txtRoleId = new javax.swing.JTextField();
        pnlOutput = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRole = new javax.swing.JTable();
        scrFunctionList = new javax.swing.JScrollPane();
        lstFunction = new javax.swing.JList();
        pnlRoleRight = new javax.swing.JPanel();
        pnlInputFun = new javax.swing.JPanel();
        lblIdFun = new javax.swing.JLabel();
        lblNameFun = new javax.swing.JLabel();
        txtIdFun = new javax.swing.JTextField();
        txtNameFun = new javax.swing.JTextField();
        btnUpdateFun = new javax.swing.JButton();
        pnlOutputFun = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFunction = new javax.swing.JTable();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(175, 175, 175), 4));
        setClosable(true);
        setTitle("Role Manager");
        setMinimumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        pnlRoleLeft.setBorder(javax.swing.BorderFactory.createTitledBorder("Role"));
        pnlRoleLeft.setLayout(new java.awt.GridBagLayout());

        pnlInput.setToolTipText("");
        pnlInput.setMinimumSize(new java.awt.Dimension(600, 230));
        pnlInput.setPreferredSize(new java.awt.Dimension(600, 230));
        pnlInput.setLayout(new java.awt.GridBagLayout());

        lblName.setText("Name: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        pnlInput.add(lblName, gridBagConstraints);

        txtRoleName.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        pnlInput.add(txtRoleName, gridBagConstraints);

        btncreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        btncreate.setText("Create");
        btncreate.setMaximumSize(new java.awt.Dimension(100, 32));
        btncreate.setMinimumSize(new java.awt.Dimension(100, 32));
        btncreate.setPreferredSize(new java.awt.Dimension(100, 32));
        btncreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncreateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        pnlInput.add(btncreate, gridBagConstraints);

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/edit.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.setMaximumSize(new java.awt.Dimension(100, 32));
        btnUpdate.setMinimumSize(new java.awt.Dimension(100, 32));
        btnUpdate.setPreferredSize(new java.awt.Dimension(100, 32));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        pnlInput.add(btnUpdate, gridBagConstraints);

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/delete.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setMaximumSize(new java.awt.Dimension(100, 32));
        btnDelete.setMinimumSize(new java.awt.Dimension(100, 32));
        btnDelete.setPreferredSize(new java.awt.Dimension(100, 32));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        pnlInput.add(btnDelete, gridBagConstraints);

        lblId.setText("Id: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        pnlInput.add(lblId, gridBagConstraints);

        txtRoleId.setEditable(false);
        txtRoleId.setEnabled(false);
        txtRoleId.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlInput.add(txtRoleId, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        pnlRoleLeft.add(pnlInput, gridBagConstraints);

        pnlOutput.setMinimumSize(new java.awt.Dimension(600, 230));
        pnlOutput.setPreferredSize(new java.awt.Dimension(600, 230));
        pnlOutput.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(400, 180));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(400, 180));

        tblRole.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblRole.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblRole.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblRole);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        pnlOutput.add(jScrollPane1, gridBagConstraints);

        scrFunctionList.setPreferredSize(new java.awt.Dimension(150, 180));
        scrFunctionList.setViewportView(lstFunction);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        pnlOutput.add(scrFunctionList, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        pnlRoleLeft.add(pnlOutput, gridBagConstraints);

        getContentPane().add(pnlRoleLeft, new java.awt.GridBagConstraints());

        pnlRoleRight.setBorder(javax.swing.BorderFactory.createTitledBorder("Function"));
        pnlRoleRight.setLayout(new java.awt.GridBagLayout());

        pnlInputFun.setMinimumSize(new java.awt.Dimension(600, 230));
        pnlInputFun.setPreferredSize(new java.awt.Dimension(600, 230));
        pnlInputFun.setLayout(new java.awt.GridBagLayout());

        lblIdFun.setText("Id:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        pnlInputFun.add(lblIdFun, gridBagConstraints);

        lblNameFun.setText("Name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        pnlInputFun.add(lblNameFun, gridBagConstraints);

        txtIdFun.setEditable(false);
        txtIdFun.setEnabled(false);
        txtIdFun.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlInputFun.add(txtIdFun, gridBagConstraints);

        txtNameFun.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlInputFun.add(txtNameFun, gridBagConstraints);

        btnUpdateFun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/edit.png"))); // NOI18N
        btnUpdateFun.setText("Update");
        btnUpdateFun.setMaximumSize(new java.awt.Dimension(100, 32));
        btnUpdateFun.setMinimumSize(new java.awt.Dimension(100, 32));
        btnUpdateFun.setPreferredSize(new java.awt.Dimension(100, 32));
        btnUpdateFun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateFunActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        pnlInputFun.add(btnUpdateFun, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        pnlRoleRight.add(pnlInputFun, gridBagConstraints);

        pnlOutputFun.setMinimumSize(new java.awt.Dimension(600, 230));
        pnlOutputFun.setPreferredSize(new java.awt.Dimension(600, 230));
        pnlOutputFun.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setPreferredSize(new java.awt.Dimension(450, 180));

        tblFunction.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblFunction.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblFunction.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblFunction);

        pnlOutputFun.add(jScrollPane2, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        pnlRoleRight.add(pnlOutputFun, gridBagConstraints);

        getContentPane().add(pnlRoleRight, new java.awt.GridBagConstraints());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateFunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateFunActionPerformed
        // TODO add your handling code here:
        //check empty field
        if (txtNameFun.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Function name cannot be empty!", "Warning", 2);
            return;
        }

        Functions function = new Functions();
        function.setFunctionId(Integer.valueOf(txtIdFun.getText()));
        function.setFunctionName(txtNameFun.getText());
        function.setIsActive(true);

        if (!funcModel.edit(function)) {
            JOptionPane.showMessageDialog(null, "Failed to update function!", "Error", 0);
        } else {
            JOptionPane.showMessageDialog(null, "Function updated successfully!", "Success", 1);
            loadFunctionListToTable();
             tblFunction.getColumn("ID").setMaxWidth(0);
        tblFunction.getColumn("ID").setMinWidth(0);

        }


    }//GEN-LAST:event_btnUpdateFunActionPerformed

    private void btncreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncreateActionPerformed
        // TODO add your handling code here:
        //check empty field
        if (txtRoleName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Role name cannot be empty!", "Warning", 2);
            return;
        }

        Roles role = new Roles();
        role.setRoleName(txtRoleName.getText());
        role.setIsActive(true);
        Set<Functions> funcList = new HashSet<Functions>();
        //get checked  items
        for (JCheckBox cb : ((CustomCheckBoxList) lstFunction).getCheckedItems()) {
            funcList.add(funcModel.find(cb.getText()));
        }

        if (!roleModel.addNew(role, funcList)) {
            JOptionPane.showMessageDialog(null, "Failed to add new role!", "Error", 0);
        } else {
            JOptionPane.showMessageDialog(null, "Role added successfully!", "Success", 1);
            loadRoleListToTable();
        }
    }//GEN-LAST:event_btncreateActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        //check empty field
        if (txtRoleName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Role name cannot be empty!", "Warning", 2);
            return;
        }

        Roles role = new Roles();
        role.setRoleId(Integer.valueOf(txtRoleId.getText()));
        role.setRoleName(txtRoleName.getText());
        role.setIsActive(true);

        Set<Functions> funcList = new HashSet<Functions>();
        //get checked items
        for (JCheckBox cb : ((CustomCheckBoxList) lstFunction).getCheckedItems()) {
            funcList.add(funcModel.find(cb.getText()));
        }

        role.setRoleFunctions(null);

        if (!roleModel.edit(role, funcList)) {
            JOptionPane.showMessageDialog(null, "Failed to update role!", "Error", 0);
        } else {
            JOptionPane.showMessageDialog(null, "Role updated successfully! System need to restart to update data.", "Successfully", 1);
            loadRoleListToTable();
            tblRole.getColumn("ID").setMaxWidth(0);
            tblRole.getColumn("ID").setMinWidth(0);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Are you sure ?", "Question", 3);
        Roles role = new Roles();
        role.setRoleId(Integer.valueOf(txtRoleId.getText()));
        role.setRoleName(txtRoleName.getText());

        roleModel.delete(role);
        loadRoleListToTable();
        JOptionPane.showMessageDialog(null, "Function deleted successfully!", "Successfully", 1);
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdateFun;
    private javax.swing.JButton btncreate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblIdFun;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNameFun;
    private javax.swing.JList lstFunction;
    private javax.swing.JPanel pnlInput;
    private javax.swing.JPanel pnlInputFun;
    private javax.swing.JPanel pnlOutput;
    private javax.swing.JPanel pnlOutputFun;
    private javax.swing.JPanel pnlRoleLeft;
    private javax.swing.JPanel pnlRoleRight;
    private javax.swing.JScrollPane scrFunctionList;
    private javax.swing.JTable tblFunction;
    private javax.swing.JTable tblRole;
    private javax.swing.JTextField txtIdFun;
    private javax.swing.JTextField txtNameFun;
    private javax.swing.JTextField txtRoleId;
    private javax.swing.JTextField txtRoleName;
    // End of variables declaration//GEN-END:variables
}
