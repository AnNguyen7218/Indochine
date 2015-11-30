/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.Accounts;
import entities.Roles;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import models.EmployeeEntityManager;
import models.RoleEntityManager;
import utils.MD5Encryption;
import utils.RegEx;

/**
 *
 * @author ADMIN
 */
public final class AccountFormDB extends javax.swing.JInternalFrame {

    /**
     * Creates new form AccountFormDB
     *
     *
     *
     */
    static AccountFormDB instance = null;
    DefaultComboBoxModel<String> dlm = new DefaultComboBoxModel<>();
    RoleEntityManager roleModel = new RoleEntityManager();
    EmployeeEntityManager accModel = new EmployeeEntityManager();
    Accounts selectedAcc = null;
    String id;
    final String searchTextPlaceHolder = "Enter keyword...";

    public AccountFormDB() {
        initComponents();
        setIcon();
        loadAccountListTable();
        loadRoleList();
        //lblWarn.setVisible(false);
        lblWarnPasss.setVisible(false);
        cbPass.setSelected(true);
        txtPassword.setEnabled(true);
        cbPass.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    txtPassword.setEnabled(true);
                } else {
                    txtPassword.setEnabled(false);
                };
            }
        });
        tblAccount.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedIndex = tblAccount.getSelectedRow();
                if (selectedIndex != -1) {
                    id = tblAccount.getValueAt(selectedIndex, 0).toString();
                    txtUserName.setText(tblAccount.getValueAt(selectedIndex, 1).toString());
                    //txtPassword.setText(dtm.getValueAt(selected, 5).toString());
                    cbbRole.setSelectedItem(tblAccount.getValueAt(selectedIndex, 3).toString());
                }
            }
        });
        txtPassword.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                lblWarnPasss.setVisible(true);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                lblWarnPasss.setVisible(true);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                lblWarnPasss.setVisible(true);
            }
        });

        txtSearch.setText(searchTextPlaceHolder);

        txtSearch.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                filterAccountList();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterAccountList();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterAccountList();
            }
        });

        tblAccount.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                cbPass.setSelected(false);
                txtPassword.setEnabled(false);
                int selectedIndex = tblAccount.getSelectedRow();
                String pass = new String(txtPassword.getPassword());
                if (selectedIndex != -1) {
                    selectedAcc = accModel.find((int) tblAccount.getValueAt(selectedIndex, 0));
                }

            }
        });

    }

    void setFormEditable(boolean editable) {
//        txtId.setEditable(editable);
        txtUserName.setEnabled(editable);
        txtPassword.setEnabled(editable);
        cbbRole.setEnabled(editable);

    }

    public void setIcon() {
        this.setFrameIcon(new ImageIcon(getClass().getResource("/image/logoTitle.png")));
    }

    void centerTextCell(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    /**
     * singleton Account Form
     *
     * @return
     */
    public static AccountFormDB getIns() {
        if (instance == null) {
            instance = new AccountFormDB();
        }
        return instance;

    }

    /**
     *
     * create Table to show Account list set table model
     *
     */
//    void createTable() {
//
//        dtm = new DefaultTableModel() {
//            @Override
//            public boolean isCellEditable(int i, int i1) {
//                return false; //To change body of generated methods, choose Tools | Templates.
//            }
//        };
//
//        dtm.addColumn("ID");
//        dtm.addColumn("Name");
//        dtm.addColumn("Address");
//        dtm.addColumn("Contact No");
//        dtm.addColumn("Username");
//        dtm.addColumn("Password");
//        dtm.addColumn("Role");
//        dtm.addColumn("Department");
//
//        tblAccount.setModel(dtm);
//    }
    /**
     *
     * Fill data to table list of account with button column and their action
     *
     */
    void filterAccountList() {
        String searchText = txtSearch.getText().toLowerCase();
        if (searchText.equalsIgnoreCase(searchTextPlaceHolder)) {
            return;
        }
        List<Accounts> l = new ArrayList<>();
        for (Accounts ins : accModel.getAll()) {
            if (ins.getAccountName().toLowerCase().contains(searchText)
                    || searchText.contains(String.valueOf(ins.getAccountId()))) {
                l.add(ins);
            }
        }
        loadAccountListTable(l);
    }

    void loadAccountListTable() {
        loadAccountListTable(accModel.getAll());
    }

    void loadAccountListTable(List<Accounts> l) {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }
        };
        dtm.addColumn("ID");
        dtm.addColumn("Username");
        dtm.addColumn("Password");
        dtm.addColumn("Role");

//        if (l != null) {
//            for (Accounts acc : l) {
//                dtm.addRow(new Object[]{acc.getAccountId(), acc.getName(), acc.getAddress(), acc.getContactNo(), acc.getUsername(), acc.getPassword(), acc.getRoles().getName(), acc.getDepartment()});
//
//            }
//        } else {
//            for (Accounts acc : accModel.getAll()) {
//                dtm.addRow(new Object[]{acc.getAccountId(), acc.getName(), acc.getAddress(), acc.getContactNo(), acc.getUsername(), acc.getPassword(), acc.getRoles().getName(), acc.getDepartment()});
        //
//            }
//        }
        for (Accounts ac : l) {
            dtm.addRow(new Object[]{
                ac.getAccountId(),
                ac.getAccountName(),
                ac.getAccountPass(),
                ac.getRoles().getRoleName(),});
        }
        tblAccount.setModel(dtm);
        tblAccount.getColumn("ID").setMinWidth(0);
        tblAccount.getColumn("ID").setMaxWidth(0);
        tblAccount.getColumn("Password").setMinWidth(0);
        tblAccount.getColumn("Password").setMaxWidth(0);
    }

    /**
     * Fill data to combo box of role
     */
    void loadRoleList() {

        for (Roles r : roleModel.getAll()) {
            dlm.addElement(r.getRoleName());
        }
        cbbRole.setModel(dlm);
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

        pnlInput = new javax.swing.JPanel();
        pnlInRight = new javax.swing.JPanel();
        pnlInLeft = new javax.swing.JPanel();
        txtUserName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pnlerr1 = new javax.swing.JPanel();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        pnlerr2 = new javax.swing.JPanel();
        lblWarnPasss = new javax.swing.JLabel();
        cbbRole = new javax.swing.JComboBox();
        lblId = new javax.swing.JLabel();
        cbPass = new javax.swing.JCheckBox();
        pnlOutput = new javax.swing.JPanel();
        pnlSearch = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        scrData = new javax.swing.JScrollPane();
        tblAccount = new javax.swing.JTable();
        pnlFun = new javax.swing.JPanel();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btncreate = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(175, 175, 175)));
        setClosable(true);
        setTitle("Account Manager");
        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setMinimumSize(new java.awt.Dimension(0, 0));
        setNormalBounds(new java.awt.Rectangle(0, 0, 126, 0));
        setPreferredSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        pnlInput.setMinimumSize(new java.awt.Dimension(1206, 260));
        pnlInput.setPreferredSize(new java.awt.Dimension(1206, 260));
        pnlInput.setLayout(new java.awt.GridBagLayout());

        pnlInRight.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        pnlInput.add(pnlInRight, gridBagConstraints);

        pnlInLeft.setLayout(new java.awt.GridBagLayout());

        txtUserName.setMinimumSize(new java.awt.Dimension(200, 25));
        txtUserName.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(5, 23, 0, 23);
        pnlInLeft.add(txtUserName, gridBagConstraints);

        jLabel4.setText("UserName:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 13);
        pnlInLeft.add(jLabel4, gridBagConstraints);

        pnlerr1.setMinimumSize(new java.awt.Dimension(200, 20));
        pnlerr1.setPreferredSize(new java.awt.Dimension(200, 20));
        pnlerr1.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        pnlInLeft.add(pnlerr1, gridBagConstraints);

        lblPassword.setText("Password: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 13);
        pnlInLeft.add(lblPassword, gridBagConstraints);

        txtPassword.setMinimumSize(new java.awt.Dimension(200, 25));
        txtPassword.setPreferredSize(new java.awt.Dimension(200, 25));
        txtPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPasswordFocusGained(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 23, 0, 23);
        pnlInLeft.add(txtPassword, gridBagConstraints);

        pnlerr2.setMinimumSize(new java.awt.Dimension(200, 20));
        pnlerr2.setPreferredSize(new java.awt.Dimension(200, 20));
        pnlerr2.setLayout(new java.awt.GridBagLayout());

        lblWarnPasss.setForeground(new java.awt.Color(255, 0, 0));
        lblWarnPasss.setText("* Your password will be changed");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        pnlerr2.add(lblWarnPasss, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        pnlInLeft.add(pnlerr2, gridBagConstraints);

        cbbRole.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 23, 10, 23);
        pnlInLeft.add(cbbRole, gridBagConstraints);

        lblId.setText("Role:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 43);
        pnlInLeft.add(lblId, gridBagConstraints);

        cbPass.setText("Check to enable");
        cbPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPassActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        pnlInLeft.add(cbPass, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 40);
        pnlInput.add(pnlInLeft, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        getContentPane().add(pnlInput, gridBagConstraints);

        pnlOutput.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "User Data"));
        pnlOutput.setMinimumSize(new java.awt.Dimension(1206, 340));
        pnlOutput.setName(""); // NOI18N
        pnlOutput.setPreferredSize(new java.awt.Dimension(1206, 340));
        pnlOutput.setLayout(new java.awt.GridBagLayout());

        pnlSearch.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Search: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        pnlSearch.add(jLabel1, gridBagConstraints);

        txtSearch.setMinimumSize(new java.awt.Dimension(200, 25));
        txtSearch.setPreferredSize(new java.awt.Dimension(200, 25));
        txtSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSearchFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        pnlSearch.add(txtSearch, gridBagConstraints);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        pnlSearch.add(jLabel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        pnlOutput.add(pnlSearch, gridBagConstraints);

        scrData.setMinimumSize(new java.awt.Dimension(1170, 260));
        scrData.setPreferredSize(new java.awt.Dimension(1170, 260));

        tblAccount.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblAccount.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblAccount.getTableHeader().setReorderingAllowed(false);
        tblAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAccountMouseClicked(evt);
            }
        });
        scrData.setViewportView(tblAccount);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        pnlOutput.add(scrData, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        getContentPane().add(pnlOutput, gridBagConstraints);

        pnlFun.setBackground(new java.awt.Color(175, 175, 175));
        pnlFun.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(175, 175, 175), new java.awt.Color(175, 175, 175)));
        pnlFun.setForeground(new java.awt.Color(175, 175, 175));
        pnlFun.setMinimumSize(new java.awt.Dimension(152, 609));
        pnlFun.setPreferredSize(new java.awt.Dimension(152, 609));
        pnlFun.setLayout(new java.awt.GridBagLayout());

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/edit.png"))); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.setMaximumSize(new java.awt.Dimension(130, 32));
        btnEdit.setMinimumSize(new java.awt.Dimension(130, 32));
        btnEdit.setPreferredSize(new java.awt.Dimension(130, 32));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlFun.add(btnEdit, gridBagConstraints);

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/delete.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setMaximumSize(new java.awt.Dimension(130, 32));
        btnDelete.setMinimumSize(new java.awt.Dimension(130, 32));
        btnDelete.setPreferredSize(new java.awt.Dimension(130, 32));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlFun.add(btnDelete, gridBagConstraints);

        btncreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        btncreate.setText("Add");
        btncreate.setToolTipText("add");
        btncreate.setMaximumSize(new java.awt.Dimension(130, 32));
        btncreate.setMinimumSize(new java.awt.Dimension(130, 32));
        btncreate.setPreferredSize(new java.awt.Dimension(130, 32));
        btncreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncreateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlFun.add(btncreate, gridBagConstraints);

        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/refresh.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.setMaximumSize(new java.awt.Dimension(130, 32));
        btnReset.setMinimumSize(new java.awt.Dimension(130, 32));
        btnReset.setPreferredSize(new java.awt.Dimension(130, 32));
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 400, 10);
        pnlFun.add(btnReset, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 1, 0);
        getContentPane().add(pnlFun, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Button create action: set visible for all JTextField
     *
     *
     */
    private void btncreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncreateActionPerformed
        // TODO add your handling code here:
        //  setFormEditable(true);

        String roleName = cbbRole.getSelectedItem().toString();
        String pass = new String(txtPassword.getPassword());
        //check empty field
        if (roleName.isEmpty() || pass.isEmpty() || txtUserName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Please fill out the form.", "Error", 0);
        } else {
            Accounts a = null;
            //validate contact number

            for (Roles rol : roleModel.getAll()) {
                if (rol.getRoleName().equals(roleName)) {
                    a = new Accounts();
                    a.setRoles(rol);
                    a.setAccountName(txtUserName.getText());
                    a.setAccountPass(pass);
                    a.setIsActive(true);
                }
            }
            //check add process
            if (accModel.addNew(a)) {
                JOptionPane.showMessageDialog(rootPane, "Successfully added new account", "Successfully", 1);
                txtUserName.setText("");
                txtPassword.setText("");
                lblWarnPasss.setVisible(false);
                loadAccountListTable(accModel.getAll());
            } else {
                JOptionPane.showMessageDialog(rootPane, "Failed to add new account", "Error", 0);
            }

    }//GEN-LAST:event_btncreateActionPerformed
    }
    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        String roleName = cbbRole.getSelectedItem().toString();
        String pass = new String(txtPassword.getPassword());
        if (roleName == null || txtUserName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Please fill required information!", "Warning", 2);
        } else {
            Accounts a = new Accounts();
            //validate contact number

            a.setAccountId(Integer.parseInt(id));
            a.setAccountName(txtUserName.getText());
            if (pass.equals("")) {
                a.setAccountPass(selectedAcc.getAccountPass());

            } else {
                a.setAccountPass(MD5Encryption.encryptWithMD5(pass));
            }
            a.setIsActive(true);
            for (Roles role : roleModel.getAll()) {
                if (role.getRoleName().equalsIgnoreCase(roleName)) {
                    a.setRoles(role);
                }
            }
            if (accModel.edit(a)) {
                JOptionPane.showMessageDialog(rootPane, "Successfully updated account!", "Success", 1);
                loadAccountListTable(accModel.getAll());

                txtUserName.setText("");
                txtPassword.setText("");
                lblWarnPasss.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Failed", "Error", 0);
            }

        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        txtUserName.setText("");
        txtPassword.setText("");
        lblWarnPasss.setVisible(false);
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        String roleName = cbbRole.getSelectedItem().toString();
        String pass = new String(txtPassword.getPassword());

        if (roleName == null || pass == null) {
            JOptionPane.showConfirmDialog(rootPane, "Please fill out this form completely!", "Error", 0);
        }
        Accounts a = new Accounts();
        a.setAccountId(Integer.parseInt(id));

        a.setAccountName(txtUserName.getText());
        a.setAccountPass(pass);
        a.setIsActive(true);
        for (Roles role : roleModel.getAll()) {
            if (role.getRoleName().equalsIgnoreCase(roleName)) {
                a.setRoles(role);
            }
        }
        int re = JOptionPane.showConfirmDialog(rootPane, "Are you sure ?", "Warning", 2);
        if (re == JOptionPane.YES_OPTION) {
            accModel.delete(a);

            txtUserName.setText("");
            txtPassword.setText("");
            lblWarnPasss.setVisible(false);
            loadAccountListTable(accModel.getAll());
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tblAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAccountMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblAccountMouseClicked

    private void txtSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusGained
        // TODO add your handling code here:
        if (txtSearch.getText().equalsIgnoreCase(searchTextPlaceHolder)) {
            txtSearch.setText("");
        }
    }//GEN-LAST:event_txtSearchFocusGained

    private void txtSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusLost
        // TODO add your handling code here:
        if (txtSearch.getText().isEmpty()) {
            txtSearch.setText(searchTextPlaceHolder);
        }
    }//GEN-LAST:event_txtSearchFocusLost

    private void txtPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPasswordFocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_txtPasswordFocusGained

    private void cbPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPassActionPerformed

    }//GEN-LAST:event_cbPassActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btncreate;
    private javax.swing.JCheckBox cbPass;
    private javax.swing.JComboBox cbbRole;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblWarnPasss;
    private javax.swing.JPanel pnlFun;
    private javax.swing.JPanel pnlInLeft;
    private javax.swing.JPanel pnlInRight;
    private javax.swing.JPanel pnlInput;
    private javax.swing.JPanel pnlOutput;
    private javax.swing.JPanel pnlSearch;
    private javax.swing.JPanel pnlerr1;
    private javax.swing.JPanel pnlerr2;
    private javax.swing.JScrollPane scrData;
    private javax.swing.JTable tblAccount;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
