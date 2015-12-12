/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.CustomerOrderService;
import entities.Customers;

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
import models.CustomerEntityManager;

import utils.RegEx;

/**
 *
 * @author ADMIN
 */
public final class CustomerForm extends javax.swing.JInternalFrame {

    /**
     * Creates new form AccountFormDB
     */
    static CustomerForm instance = null;
    DefaultComboBoxModel<String> dlm = new DefaultComboBoxModel<>();
    CustomerEntityManager cusModel = new CustomerEntityManager();

    final String searchTextPlaceHolder = "Enter keyword...";
    Customers selectedCus = null;

    public CustomerForm() {
        initComponents();
        setIcon();
        loadCustomerListTable(cusModel.getAll());
        lblWarn.setVisible(false);
        txtSearch.setText(searchTextPlaceHolder);

        txtSearch.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                filterCustomerList();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterCustomerList();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterCustomerList();
            }
        });
        //Update form information when a customer' selected
        tblCustomer.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedIndex = tblCustomer.getSelectedRow();
                if (selectedIndex != -1) {
                    lblID.setText(tblCustomer.getValueAt(selectedIndex, 0).toString());
                    lblID.setVisible(false);
                    txtName.setText(tblCustomer.getValueAt(selectedIndex, 1).toString());
                    txtAddr.setText(tblCustomer.getValueAt(selectedIndex, 2).toString());
                    txtContact.setText(tblCustomer.getValueAt(selectedIndex, 3).toString());

                }
            }
        });

        //Validate contact number
        txtContact.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (RegEx.checkIDCard(txtContact.getText())) {
                    lblWarn.setVisible(false);
                } else {
                    lblWarn.setVisible(true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (RegEx.checkIDCard(txtContact.getText())) {
                    lblWarn.setVisible(false);
                } else {
                    lblWarn.setVisible(true);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (RegEx.checkIDCard(txtContact.getText())) {
                    lblWarn.setVisible(false);
                } else {
                    lblWarn.setVisible(true);
                }
            }
        });

        //search actions
    }

    void setFormEditable(boolean editable) {

        txtName.setEnabled(editable);

        txtContact.setEnabled(editable);

    }

    public void setIcon() {
        this.setFrameIcon(new ImageIcon(getClass().getResource("/image/logoTitle.png")));
    }

    /**
     * Centers text content in the table
     *
     * @param table
     */
    void centerTextCell(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    /**
     * singleton HamCustomer Form
     *
     * @return
     */
    public static CustomerForm getIns() {
        if (instance == null) {
            instance = new CustomerForm();
        }
        return instance;

    }

    /**
     *
     * Fill data to table list of customer
     *
     */
    void loadCustomerListTable(List<Customers> l) {
        //add record to table
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }
        };
        dtm.addColumn("ID");
        dtm.addColumn("Customer name");
        dtm.addColumn("Customer address");
        dtm.addColumn("Indentity");
        if (l != null) {
            for (Customers acc : l) {
                dtm.addRow(new Object[]{acc.getCustomerId(), acc.getCustomerName(), acc.getCustomerAddress(), acc.getIdentityCard()});

            }
        } else {
            for (Customers acc : cusModel.getAll()) {
                dtm.addRow(new Object[]{acc.getCustomerId(), acc.getCustomerName(), acc.getCustomerAddress(), acc.getIdentityCard()});

            }
        }
        tblCustomer.setModel(dtm);

        //adjust width
        tblCustomer.getColumn("ID").setMinWidth(0);
        tblCustomer.getColumn("ID").setMaxWidth(0);

        /*Action edit = new AbstractAction() {

         @Override
         public void actionPerformed(ActionEvent e) {
         setFormEditable(true);
         editMode = EditMode.editModeEdit;
         }
         };

         Action delete = new AbstractAction() {

         @Override
         public void actionPerformed(ActionEvent e) {
         Account a = accModel.find(Integer.valueOf(txtId.getText()));
         if (a != null) {
         accModel.delete(a);
         JOptionPane.showMessageDialog(rootPane, "Deleted");
         loadCustomerListTable(null);
         }

         }

         };
         new ButtonColumn(tblAccount, edit, 4);
         new ButtonColumn(tblAccount, delete, 5);*/
    }

    /**
     * Filter the customer list with search phrase
     */
    void filterCustomerList() {
        String searchText = txtSearch.getText().toLowerCase();
        if (searchText.equalsIgnoreCase(searchTextPlaceHolder)) {
            return;
        }
        List<Customers> l = new ArrayList<>();
        for (Customers ins : cusModel.getAll()) {
            if (ins.getCustomerName().toLowerCase().contains(searchText)
                    || ins.getIdentityCard().toLowerCase().contains(searchText)
                    || searchText.contains(ins.getCustomerId().toString())) {
                l.add(ins);
            }
        }
        loadCustomerListTable(l);
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
        pnlTxt = new javax.swing.JPanel();
        txtContact = new javax.swing.JTextField();
        lblContact = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblAdd = new javax.swing.JLabel();
        txtAddr = new javax.swing.JTextField();
        lblName2 = new javax.swing.JLabel();
        pnlButton = new javax.swing.JPanel();
        btncreate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        lblID = new javax.swing.JLabel();
        lblWarn = new javax.swing.JLabel();
        pnlOutput = new javax.swing.JPanel();
        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        lblSearchIcon = new javax.swing.JLabel();
        srcCustomer = new javax.swing.JScrollPane();
        tblCustomer = new javax.swing.JTable();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(175, 175, 175), 4));
        setClosable(true);
        setTitle("Customer Manager");
        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setMinimumSize(new java.awt.Dimension(0, 0));
        setNormalBounds(new java.awt.Rectangle(0, 0, 126, 0));
        setPreferredSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        pnlInput.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Customer Control"));
        pnlInput.setMinimumSize(new java.awt.Dimension(420, 479));
        pnlInput.setPreferredSize(new java.awt.Dimension(420, 479));
        pnlInput.setLayout(new java.awt.GridBagLayout());

        pnlTxt.setLayout(new java.awt.GridBagLayout());

        txtContact.setName(""); // NOI18N
        txtContact.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlTxt.add(txtContact, gridBagConstraints);

        lblContact.setText("Identity Card:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlTxt.add(lblContact, gridBagConstraints);

        txtName.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlTxt.add(txtName, gridBagConstraints);

        lblAdd.setText("Address:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlTxt.add(lblAdd, gridBagConstraints);

        txtAddr.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlTxt.add(txtAddr, gridBagConstraints);

        lblName2.setText("Custome Name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlTxt.add(lblName2, gridBagConstraints);

        pnlInput.add(pnlTxt, new java.awt.GridBagConstraints());

        pnlButton.setLayout(new java.awt.GridBagLayout());

        btncreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        btncreate.setText("Add");
        btncreate.setToolTipText("add");
        btncreate.setMaximumSize(new java.awt.Dimension(100, 32));
        btncreate.setMinimumSize(new java.awt.Dimension(100, 32));
        btncreate.setPreferredSize(new java.awt.Dimension(100, 32));
        btncreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncreateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlButton.add(btncreate, gridBagConstraints);

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
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlButton.add(btnDelete, gridBagConstraints);

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/edit.png"))); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.setMaximumSize(new java.awt.Dimension(100, 32));
        btnEdit.setMinimumSize(new java.awt.Dimension(100, 32));
        btnEdit.setPreferredSize(new java.awt.Dimension(100, 32));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlButton.add(btnEdit, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        pnlInput.add(pnlButton, gridBagConstraints);
        pnlInput.add(lblID, new java.awt.GridBagConstraints());

        lblWarn.setForeground(new java.awt.Color(255, 0, 0));
        lblWarn.setText("* Type correct your number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 79, 0, 0);
        pnlInput.add(lblWarn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 5, 3);
        getContentPane().add(pnlInput, gridBagConstraints);

        pnlOutput.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Customer Data"));
        pnlOutput.setMinimumSize(new java.awt.Dimension(462, 477));
        pnlOutput.setPreferredSize(new java.awt.Dimension(462, 477));
        pnlOutput.setLayout(new java.awt.GridBagLayout());

        lblSearch.setText("Search: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlOutput.add(lblSearch, gridBagConstraints);

        txtSearch.setText("type a name...");
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
        txtSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSearchMouseClicked(evt);
            }
        });
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 120);
        pnlOutput.add(txtSearch, gridBagConstraints);

        lblSearchIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 142);
        pnlOutput.add(lblSearchIcon, gridBagConstraints);

        srcCustomer.setMinimumSize(new java.awt.Dimension(420, 405));

        tblCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblCustomer.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        srcCustomer.setViewportView(tblCustomer);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        pnlOutput.add(srcCustomer, gridBagConstraints);

        getContentPane().add(pnlOutput, new java.awt.GridBagConstraints());

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btncreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncreateActionPerformed
        // TODO add your handling code here:

        //Check empty field
        if (txtName.getText().isEmpty() || txtContact.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Please fill out this form completely!", "Warning", 2);
        } else {
            Customers customer = new Customers();
            customer.setCustomerName(txtName.getText());
            customer.setCustomerAddress(txtAddr.getText());
            customer.setIdentityCard(txtContact.getText());

            if (cusModel.insert(customer)) {
                JOptionPane.showMessageDialog(rootPane, "Successfully added customer!", "Success", 1);
                loadCustomerListTable(cusModel.getAll());
            } else {
                JOptionPane.showMessageDialog(rootPane, "Customer has already existed", "Error", 0);

            }
        }
    }//GEN-LAST:event_btncreateActionPerformed

    private void txtSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMouseClicked

    }//GEN-LAST:event_txtSearchMouseClicked

    private void txtSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyTyped

    }//GEN-LAST:event_txtSearchKeyTyped

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        //Check empty field
        if (txtName.getText().isEmpty() || txtContact.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Please fill out this form completely!", "Warning", 2);
        } else {
            Customers customer = new Customers();
            customer.setCustomerName(txtName.getText());
            customer.setCustomerAddress(txtAddr.getText());
            customer.setIdentityCard(txtContact.getText());
            customer.setCustomerId(cusModel.find(txtName.getText()).getCustomerId());
            if (cusModel.update(customer)) {
                JOptionPane.showMessageDialog(rootPane, "Successfully added customer!", "Success", 1);
                loadCustomerListTable(cusModel.getAll());
            } else {
                JOptionPane.showMessageDialog(rootPane, "Cannot update information", "Error", 0);

            }
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        //Check empty field
        if (txtName.getText().isEmpty() || txtContact.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Please fill out this form completely!", "Warning", 2);
        } else {
            Customers customer = new Customers();
            customer.setCustomerAddress(txtAddr.getText());
            customer.setIdentityCard(txtContact.getText());
            customer.setCustomerId(cusModel.find(txtName.getText()).getCustomerId());
            if (cusModel.delete(customer)) {
                JOptionPane.showMessageDialog(rootPane, "Successfully added customer!", "Success", 1);
                loadCustomerListTable(cusModel.getAll());
            } else {
                JOptionPane.showMessageDialog(rootPane, "Cannot delete customer", "Error", 0);

            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btncreate;
    private javax.swing.JLabel lblAdd;
    private javax.swing.JLabel lblContact;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblName2;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblSearchIcon;
    private javax.swing.JLabel lblWarn;
    private javax.swing.JPanel pnlButton;
    private javax.swing.JPanel pnlInput;
    private javax.swing.JPanel pnlOutput;
    private javax.swing.JPanel pnlTxt;
    private javax.swing.JScrollPane srcCustomer;
    private javax.swing.JTable tblCustomer;
    private javax.swing.JTextField txtAddr;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
