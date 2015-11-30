/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.CustomerOrderService;
import entities.CustomerOrderTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import models.CustomerEntityManager;
import models.ResCustomerEntityManager;
import models.SerCustomerEntityManager;
import utils.RegEx;

/**
 *
 * @author ADMIN
 */
public final class CustomerFormDB extends javax.swing.JInternalFrame {

    /**
     * Creates new form AccountFormDB
     */
    static CustomerFormDB instance = null;
    DefaultComboBoxModel<String> dlm = new DefaultComboBoxModel<>();
    ResCustomerEntityManager cusModel = new ResCustomerEntityManager();
    SerCustomerEntityManager cusModel1 = new SerCustomerEntityManager();
    final String searchTextPlaceHolder = "Enter keyword...";
    CustomerOrderTable selectedCus = null;
    CustomerOrderService selectedCus1 = null;

    public CustomerFormDB() {
        initComponents();
        setIcon();
        loadCustomerListTable(cusModel.getAll());
        lblWarn.setVisible(false);
        txtSearch.setText(searchTextPlaceHolder);
        cbbType.addItem("Service");
        cbbType.addItem("Restaurant");

        cbbType.getModel().addListDataListener(new ListDataListener() {

            @Override
            public void intervalAdded(ListDataEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void intervalRemoved(ListDataEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void contentsChanged(ListDataEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if (cbbType.getSelectedItem().equals("Restaurant")) {
                    loadCustomerListTable(cusModel.getAll());
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
                } else {
                    loadSerCustomerListTable(cusModel1.getAll());
                    txtSearch.getDocument().addDocumentListener(new DocumentListener() {

                        @Override
                        public void insertUpdate(DocumentEvent e) {
                            filterCustomerList1();
                        }

                        @Override
                        public void removeUpdate(DocumentEvent e) {
                            filterCustomerList1();
                        }

                        @Override
                        public void changedUpdate(DocumentEvent e) {
                            filterCustomerList1();
                        }
                    });
                }
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

                    txtContact.setText(tblCustomer.getValueAt(selectedIndex, 3).toString());

                }
            }
        });

        //Validate contact number
        txtContact.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (RegEx.checkContactNum(txtContact.getText())) {
                    lblWarn.setVisible(false);
                } else {
                    lblWarn.setVisible(true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (RegEx.checkContactNum(txtContact.getText())) {
                    lblWarn.setVisible(false);
                } else {
                    lblWarn.setVisible(true);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (RegEx.checkContactNum(txtContact.getText())) {
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
    public static CustomerFormDB getIns() {
        if (instance == null) {
            instance = new CustomerFormDB();
        }
        return instance;

    }

    /**
     *
     * Fill data to table list of customer
     *
     */
    void loadCustomerListTable(List<CustomerOrderTable> l) {
        //add record to table
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }
        };
        dtm.addColumn("ID");
        dtm.addColumn("Customer name");

        dtm.addColumn("Contact Number");
        if (l != null) {
            for (CustomerOrderTable acc : l) {
                dtm.addRow(new Object[]{acc.getId(), acc.getCustomerName(), acc.getCustomerNumber()});

            }
        } else {
            for (CustomerOrderTable acc : cusModel.getAll()) {
                dtm.addRow(new Object[]{acc.getId(), acc.getCustomerName(), acc.getCustomerNumber()});

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

    void loadSerCustomerListTable(List<CustomerOrderService> l) {
        //add record to table
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }
        };
        dtm.addColumn("ID");
        dtm.addColumn("Customer name");

        dtm.addColumn("Contact Number");
        if (l != null) {
            for (CustomerOrderService acc : l) {
                dtm.addRow(new Object[]{acc.getId(), acc.getCustomerName(), acc.getCustomerNumber()});

            }
        } else {
            for (CustomerOrderService acc : cusModel1.getAll()) {
                dtm.addRow(new Object[]{acc.getId(), acc.getCustomerName(), acc.getCustomerNumber()});

            }
        }
        tblCustomer.setModel(dtm);

        //adjust width
        tblCustomer.getColumn("ID").setMinWidth(0);
        tblCustomer.getColumn("ID").setMaxWidth(0);
    }

    /**
     * Filter the customer list with search phrase
     */
    void filterCustomerList() {
        String searchText = txtSearch.getText().toLowerCase();
        if (searchText.equalsIgnoreCase(searchTextPlaceHolder)) {
            return;
        }
        List<CustomerOrderTable> l = new ArrayList<>();
        for (CustomerOrderTable ins : cusModel.getAll()) {
            if (ins.getCustomerName().toLowerCase().contains(searchText)
                    || ins.getCustomerNumber().toLowerCase().contains(searchText)
                    || searchText.contains(ins.getId().toString())) {
                l.add(ins);
            }
        }
        loadCustomerListTable(l);
    }

    void filterCustomerList1() {
        String searchText = txtSearch.getText().toLowerCase();
        if (searchText.equalsIgnoreCase(searchTextPlaceHolder)) {
            return;
        }
        List<CustomerOrderService> l = new ArrayList<>();
        for (CustomerOrderService ins : cusModel1.getAll()) {
            if (ins.getCustomerName().toLowerCase().contains(searchText)
                    || ins.getCustomerNumber().toLowerCase().contains(searchText)
                    || searchText.contains(ins.getId().toString())) {
                l.add(ins);
            }
        }
        loadSerCustomerListTable(l);
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
        lblName = new javax.swing.JLabel();
        cbbType = new javax.swing.JComboBox();
        lblName1 = new javax.swing.JLabel();
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

        lblContact.setText("Contact No:");
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

        lblName.setText("Type:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlTxt.add(lblName, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(9, 9, 9, 9);
        pnlTxt.add(cbbType, gridBagConstraints);

        lblName1.setText("Custome Name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlTxt.add(lblName1, gridBagConstraints);

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

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        getContentPane().add(pnlOutput, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btncreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncreateActionPerformed
        // TODO add your handling code here:

        //Check empty field
        if (txtName.getText().isEmpty() || txtContact.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Please fill out this form completely!", "Warning", 2);
        } else {
            if (cbbType.getSelectedItem().equals("Restaurant")) {
                CustomerOrderTable customer = new CustomerOrderTable();
                customer.setCustomerName(txtName.getText());
                customer.setCustomerNumber(txtContact.getText());

                if (cusModel.insert(customer)) {
                    JOptionPane.showMessageDialog(rootPane, "Successfully added customer!", "Success", 1);
                    loadCustomerListTable(cusModel.getAll());
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Customer has already existed", "Error", 0);

                }
            } else {
                CustomerOrderService customer = new CustomerOrderService();
                customer.setCustomerName(txtName.getText());
                customer.setCustomerNumber(txtContact.getText());

                if (cusModel1.insert(customer)) {
                    JOptionPane.showMessageDialog(rootPane, "Successfully added customer!", "Success", 1);
                    loadSerCustomerListTable(cusModel1.getAll());
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Customer has already existed", "Error", 0);

                }
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
            if (cbbType.getSelectedItem().equals("Restaurant")) {
                CustomerOrderTable customer = new CustomerOrderTable();
                customer.setCustomerName(txtName.getText());
                customer.setCustomerNumber(txtContact.getText());

                if (cusModel.update(customer)) {
                    JOptionPane.showMessageDialog(rootPane, "Successfully added customer!", "Success", 1);
                    loadCustomerListTable(cusModel.getAll());
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Customer has already existed", "Error", 0);

                }
            } else {
                CustomerOrderService customer = new CustomerOrderService();
                customer.setCustomerName(txtName.getText());
                customer.setCustomerNumber(txtContact.getText());

                if (cusModel1.update(customer)) {
                    JOptionPane.showMessageDialog(rootPane, "Successfully added customer!", "Success", 1);
                    loadSerCustomerListTable(cusModel1.getAll());
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Customer has already existed", "Error", 0);

                }
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
            if (cbbType.getSelectedItem().equals("Restaurant")) {
                CustomerOrderTable customer = new CustomerOrderTable();
                customer.setCustomerName(txtName.getText());
                customer.setCustomerNumber(txtContact.getText());

                if (cusModel.delete(customer)) {
                    JOptionPane.showMessageDialog(rootPane, "Successfully added customer!", "Success", 1);
                    loadCustomerListTable(cusModel.getAll());
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Customer has already existed", "Error", 0);

                }
            } else {
                CustomerOrderService customer = new CustomerOrderService();
                customer.setCustomerName(txtName.getText());
                customer.setCustomerNumber(txtContact.getText());

                if (cusModel1.delete(customer)) {
                    JOptionPane.showMessageDialog(rootPane, "Successfully added customer!", "Success", 1);
                    loadSerCustomerListTable(cusModel1.getAll());
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Customer has already existed", "Error", 0);

                }
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
    private javax.swing.JComboBox cbbType;
    private javax.swing.JLabel lblContact;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblName1;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblSearchIcon;
    private javax.swing.JLabel lblWarn;
    private javax.swing.JPanel pnlButton;
    private javax.swing.JPanel pnlInput;
    private javax.swing.JPanel pnlOutput;
    private javax.swing.JPanel pnlTxt;
    private javax.swing.JScrollPane srcCustomer;
    private javax.swing.JTable tblCustomer;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
