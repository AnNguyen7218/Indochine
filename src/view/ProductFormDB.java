/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.CategoryOfProduct;
import entities.Products;
import entities.Suppliers;
import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import models.CateOfProEntityManager;

import models.ProductEntityManager;

import models.ReportManager;
import models.SupplierEntityManager;
import utils.Converter;
import utils.RegEx;

/**
 *
 * @author ADMIN
 */
public final class ProductFormDB extends javax.swing.JInternalFrame {

    /**
     * Creates new form ProductFormDB
     */
    static ProductFormDB instance = null;
    ReportManager rpDAO = new ReportManager();
    CateOfProEntityManager cateModel = new CateOfProEntityManager();
    SupplierEntityManager supModel = new SupplierEntityManager();
    ProductEntityManager proModel = new ProductEntityManager();
    Products selectedProduct = null;
    Double selectedPrice = null;
    final String searchTextPlaceHolder = "Type a product name...";

    public ProductFormDB() {
        initComponents();
        setLableVisible(false);
        lblQuan.setVisible(false);
        setIcon();
        loadProductListToTable();
        jxdImportDate.setDate(new Date());
        //search text field action
        txtSearch.setText(searchTextPlaceHolder);
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                filterProductList();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterProductList();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterProductList();
            }

        });

        fillCbb();
        //validate quantity's value
        txtQuantity.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (RegEx.isDouble(txtQuantity.getText())) {
                    lblQuan.setVisible(false);
                } else {
                    lblQuan.setVisible(true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (RegEx.isDouble(txtQuantity.getText())) {
                    lblQuan.setVisible(false);
                } else {
                    lblQuan.setVisible(true);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (RegEx.isDouble(txtQuantity.getText())) {
                    lblQuan.setVisible(false);
                } else {
                    lblQuan.setVisible(true);
                }
            }
        });

        //validate price's value
        txtSalePrice.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (RegEx.isDouble(txtSalePrice.getText())) {
                    lblPrice2.setVisible(false);
                } else {
                    lblPrice2.setVisible(true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (RegEx.isDouble(txtSalePrice.getText())) {
                    lblPrice2.setVisible(false);
                } else {
                    lblPrice2.setVisible(true);
                }

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (RegEx.isDouble(txtSalePrice.getText())) {
                    lblPrice2.setVisible(false);
                } else {
                    lblPrice2.setVisible(true);
                }

            }

        });
        //Update form content on change product
        tblProduct.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedIndex = tblProduct.getSelectedRow();
                if (selectedIndex != -1) {
                    selectedProduct = proModel.find((int) tblProduct.getValueAt(selectedIndex, 0));
                    txtName.setText(selectedProduct.getProductName());
                    txtQuantity.setText(String.valueOf(selectedProduct.getQuantity()));
                    cbbCategory.setSelectedItem(selectedProduct.getCategoryOfProduct().getCateName());
                    jxdImportDate.setDate(selectedProduct.getImportDate());
                    txtSalePrice.setText(selectedProduct.getPrice().toString());

                    selectedPrice = null;
                    setEnabledPriceButton(false);
                    //loadPriceListToTable();
                }
            }
        });

    }

    public void setLableVisible(boolean b) {

        lblPrice2.setVisible(b);

    }

    public void setIcon() {
        this.setFrameIcon(new ImageIcon(getClass().getResource("/image/logoTitle.png")));
    }

    public static ProductFormDB getIns() {
        if (instance == null) {
            instance = new ProductFormDB();
        }
        return instance;

    }

    //check empty field 
    boolean validateProductFields() {

        if (checkIfTextFieldNull(txtName)) {
            return false;
        }

        if (checkIfTextFieldNull(txtName)) {
            return false;
        }
        if (jxdImportDate.getDate() == null) {
            return false;
        }

        return true;
    }

    boolean validatePriceFields() {
        try {
            //txtPriceValue.setText(Double.valueOf(txtPriceValue.getText()).toString());
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    boolean checkIfTextFieldNull(JTextField t) {
        if (t.getText().isEmpty()) {
            t.requestFocus();
            t.selectAll();
            return true;
        }
        return false;
    }

    void setEnabledPriceButton(boolean enabled) {
//        btnDeletePrice.setEnabled(enabled);
//        btnUpdatePrice.setEnabled(enabled);
    }

    //fill out data in combo box
    void fillCbb() {
        cbbCategory.removeAllItems();
        for (CategoryOfProduct ac : cateModel.getAll()) {
            cbbCategory.addItem(ac.getCateName());
        }
        cbbSup.removeAllItems();
        for (Suppliers ac : supModel.getAll()) {
            cbbSup.addItem(ac.getSupplierName());
        }
    }

    //autocomplete search by update the record on table
    void filterProductList() {
        String searchText = txtSearch.getText().toLowerCase();
        if (searchText.equalsIgnoreCase(searchTextPlaceHolder)) {
            return;
        }
        List<Products> l = new ArrayList<>();
        for (Products ins : proModel.getAll()) {
            if (ins.getProductName().toLowerCase().contains(searchText)
                    || searchText.contains(ins.getProductId().toString())) {
                l.add(ins);
            }
        }
        loadProductListToTable(l);
    }

//    void loadPriceListToTable() {
//        if (selectedProduct == null) {
//            return;
//        }
//
//        DefaultTableModel dtm = new DefaultTableModel() {
//            @Override
//            public boolean isCellEditable(int i, int i1) {
//                return false; //To change body of generated methods, choose Tools | Templates.
//            }
//        };
//
//        dtm.addColumn("ID");
//        dtm.addColumn("Price");
//        dtm.addColumn("Start date");
//        dtm.addColumn("End date");
//
//    }
    void loadProductListToTable() {
        loadProductListToTable(proModel.getAll());
    }

    void loadProductListToTable(List<Products> list) {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }
        };

        dtm.addColumn("ID");
        dtm.addColumn("Name");
        //dtm.addColumn("Description");
        //dtm.addColumn("UnitPrice");
        dtm.addColumn("SalePrice");
        dtm.addColumn("Quantity");
        dtm.addColumn("Category");
        //dtm.addColumn("ImportPrice");
        //dtm.addColumn("ImportDate");

        // cbbProduct.removeAllItems();
        for (Products pro : list) {
            dtm.addRow(new Object[]{
                pro.getProductId(),
                pro.getProductName(),
                pro.getPrice(),
                pro.getQuantity(),
                pro.getCategoryOfProduct().getCateName(),});

        }

        tblProduct.setModel(dtm);
        //adjust column
        tblProduct.getColumn("ID").setMinWidth(0);
        tblProduct.getColumn("ID").setMaxWidth(0);
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
        pnlField = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        lblImportDate = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jxdImportDate = new org.jdesktop.swingx.JXDatePicker();
        txtSalePrice = new javax.swing.JTextField();
        lblSalePrice = new javax.swing.JLabel();
        pnlSale = new javax.swing.JPanel();
        lblPrice2 = new javax.swing.JLabel();
        pnlFieldRight = new javax.swing.JPanel();
        lblCategory = new javax.swing.JLabel();
        cbbCategory = new javax.swing.JComboBox();
        btnAddCate = new javax.swing.JButton();
        pnlQuan = new javax.swing.JPanel();
        btnAddSup = new javax.swing.JButton();
        cbbSup = new javax.swing.JComboBox();
        lblSu = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        lblQuantity = new javax.swing.JLabel();
        lblQuan = new javax.swing.JLabel();
        pnlOutput = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduct = new javax.swing.JTable();
        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        lblSearchIcon = new javax.swing.JLabel();
        pnlFun = new javax.swing.JPanel();
        btCreateProduct = new javax.swing.JButton();
        btUpdateProduct = new javax.swing.JButton();
        btDeleteProduct = new javax.swing.JButton();
        btResetProduct = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(175, 175, 175)));
        setClosable(true);
        setTitle("Product Manager");
        setEnabled(false);
        setMinimumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        pnlInput.setMinimumSize(new java.awt.Dimension(1206, 250));
        pnlInput.setName(""); // NOI18N
        pnlInput.setPreferredSize(new java.awt.Dimension(1206, 250));
        pnlInput.setLayout(new java.awt.GridBagLayout());

        pnlField.setMinimumSize(new java.awt.Dimension(500, 200));
        pnlField.setPreferredSize(new java.awt.Dimension(500, 200));
        pnlField.setLayout(new java.awt.GridBagLayout());

        lblName.setText("Product Name: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 10);
        pnlField.add(lblName, gridBagConstraints);

        lblImportDate.setText("ImportDate: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 10);
        pnlField.add(lblImportDate, gridBagConstraints);

        txtName.setMinimumSize(new java.awt.Dimension(200, 25));
        txtName.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 16, 5, 0);
        pnlField.add(txtName, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 16, 5, 0);
        pnlField.add(jxdImportDate, gridBagConstraints);

        txtSalePrice.setMinimumSize(new java.awt.Dimension(200, 25));
        txtSalePrice.setPreferredSize(new java.awt.Dimension(200, 25));
        txtSalePrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSalePriceActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 16, 5, 0);
        pnlField.add(txtSalePrice, gridBagConstraints);

        lblSalePrice.setText("Sales price:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 27);
        pnlField.add(lblSalePrice, gridBagConstraints);

        pnlSale.setMinimumSize(new java.awt.Dimension(110, 25));
        pnlSale.setPreferredSize(new java.awt.Dimension(110, 25));
        pnlSale.setLayout(new java.awt.GridBagLayout());

        lblPrice2.setForeground(new java.awt.Color(255, 0, 0));
        lblPrice2.setText("*Must be number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        pnlSale.add(lblPrice2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        pnlField.add(pnlSale, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 2, 0);
        pnlInput.add(pnlField, gridBagConstraints);

        pnlFieldRight.setMinimumSize(new java.awt.Dimension(500, 210));
        pnlFieldRight.setPreferredSize(new java.awt.Dimension(500, 210));
        pnlFieldRight.setRequestFocusEnabled(false);
        pnlFieldRight.setLayout(new java.awt.GridBagLayout());

        lblCategory.setText("Category:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 10);
        pnlFieldRight.add(lblCategory, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 16, 5, 0);
        pnlFieldRight.add(cbbCategory, gridBagConstraints);

        btnAddCate.setText("+");
        btnAddCate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        pnlFieldRight.add(btnAddCate, gridBagConstraints);

        pnlQuan.setMinimumSize(new java.awt.Dimension(100, 25));
        pnlQuan.setPreferredSize(new java.awt.Dimension(100, 25));
        pnlQuan.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        pnlFieldRight.add(pnlQuan, gridBagConstraints);

        btnAddSup.setText("+");
        btnAddSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSupActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        pnlFieldRight.add(btnAddSup, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 16, 5, 0);
        pnlFieldRight.add(cbbSup, gridBagConstraints);

        lblSu.setText("Supplier:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 10);
        pnlFieldRight.add(lblSu, gridBagConstraints);

        txtQuantity.setMinimumSize(new java.awt.Dimension(200, 25));
        txtQuantity.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        pnlFieldRight.add(txtQuantity, gridBagConstraints);

        lblQuantity.setText("Quantity: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        pnlFieldRight.add(lblQuantity, gridBagConstraints);

        lblQuan.setForeground(new java.awt.Color(255, 0, 0));
        lblQuan.setText("* Must be number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        pnlFieldRight.add(lblQuan, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlInput.add(pnlFieldRight, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        getContentPane().add(pnlInput, gridBagConstraints);

        pnlOutput.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Product Data"));
        pnlOutput.setMinimumSize(new java.awt.Dimension(1200, 350));
        pnlOutput.setPreferredSize(new java.awt.Dimension(1200, 350));
        pnlOutput.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(1170, 270));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1170, 270));

        tblProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblProduct.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblProduct.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblProduct);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        pnlOutput.add(jScrollPane1, gridBagConstraints);

        lblSearch.setText("Search:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlOutput.add(lblSearch, gridBagConstraints);

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
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 45, 5, 45);
        pnlOutput.add(txtSearch, gridBagConstraints);

        lblSearchIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 895);
        pnlOutput.add(lblSearchIcon, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        getContentPane().add(pnlOutput, gridBagConstraints);

        pnlFun.setBackground(new java.awt.Color(175, 175, 175));
        pnlFun.setForeground(new java.awt.Color(175, 175, 175));
        pnlFun.setMinimumSize(new java.awt.Dimension(152, 609));
        pnlFun.setPreferredSize(new java.awt.Dimension(152, 609));
        pnlFun.setLayout(new java.awt.GridBagLayout());

        btCreateProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        btCreateProduct.setText("Create");
        btCreateProduct.setAlignmentX(0.5F);
        btCreateProduct.setMaximumSize(new java.awt.Dimension(130, 32));
        btCreateProduct.setMinimumSize(new java.awt.Dimension(130, 32));
        btCreateProduct.setPreferredSize(new java.awt.Dimension(130, 32));
        btCreateProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCreateProductActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlFun.add(btCreateProduct, gridBagConstraints);

        btUpdateProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/edit.png"))); // NOI18N
        btUpdateProduct.setText("Update");
        btUpdateProduct.setAlignmentX(0.5F);
        btUpdateProduct.setMaximumSize(new java.awt.Dimension(130, 32));
        btUpdateProduct.setMinimumSize(new java.awt.Dimension(130, 32));
        btUpdateProduct.setPreferredSize(new java.awt.Dimension(130, 32));
        btUpdateProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUpdateProductActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlFun.add(btUpdateProduct, gridBagConstraints);

        btDeleteProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/delete.png"))); // NOI18N
        btDeleteProduct.setText("Delete");
        btDeleteProduct.setAlignmentX(0.5F);
        btDeleteProduct.setMaximumSize(new java.awt.Dimension(130, 32));
        btDeleteProduct.setMinimumSize(new java.awt.Dimension(130, 32));
        btDeleteProduct.setPreferredSize(new java.awt.Dimension(130, 32));
        btDeleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteProductActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlFun.add(btDeleteProduct, gridBagConstraints);

        btResetProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/refresh.png"))); // NOI18N
        btResetProduct.setText("Reset");
        btResetProduct.setAlignmentX(0.5F);
        btResetProduct.setMaximumSize(new java.awt.Dimension(130, 32));
        btResetProduct.setMinimumSize(new java.awt.Dimension(130, 32));
        btResetProduct.setPreferredSize(new java.awt.Dimension(130, 32));
        btResetProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btResetProductActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 400, 10);
        pnlFun.add(btResetProduct, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        getContentPane().add(pnlFun, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCreateProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCreateProductActionPerformed
        // TODO add your handling code here:
        if (!validateProductFields()) {
            JOptionPane.showMessageDialog(rootPane, "Please fill this form completely.", "Warning", 2);
            return;
        }

        Products pro = new Products();
        //check input value 
        if (RegEx.isInteger(txtQuantity.getText()) && RegEx.isDouble(txtSalePrice.getText())) {

            pro.setProductName(txtName.getText());
            pro.setImportDate(jxdImportDate.getDate() == null ? new Date() : jxdImportDate.getDate());
            pro.setQuantity(Integer.valueOf(txtQuantity.getText()));
            pro.setCategoryOfProduct(cateModel.find(cbbCategory.getSelectedItem().toString()));
            pro.setPrice(BigDecimal.valueOf(Double.valueOf(txtSalePrice.getText())));
            //add new product
            if (proModel.addNew(pro)) {
                JOptionPane.showMessageDialog(null, "Added new product successfully!", "Success", 1);

                txtName.setText("");
                txtQuantity.setText("");
                txtSalePrice.setText("");

                jxdImportDate.setDate(new Date());

                lblPrice2.setVisible(false);

                lblQuan.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add new product!", "Error", 0);
            }

            loadProductListToTable();

        } else {
            JOptionPane.showMessageDialog(null, "Check your information again.", "Warning", 2);

        }
    }//GEN-LAST:event_btCreateProductActionPerformed

    private void btUpdateProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUpdateProductActionPerformed
        // TODO add your handling code here:
        if (!validateProductFields()) {
            JOptionPane.showMessageDialog(rootPane, "Please check the product's input data.", "Warning", 2);
            return;
        }

        Products pro = new Products();
        //check input value
        if (RegEx.isInteger(txtQuantity.getText()) && RegEx.isDouble(txtSalePrice.getText())) {

            pro.setProductId(selectedProduct.getProductId());
            pro.setProductName(txtName.getText());
            pro.setImportDate(jxdImportDate.getDate() == null ? new Date() : jxdImportDate.getDate());
            pro.setQuantity(Integer.valueOf(txtQuantity.getText()));
            pro.setCategoryOfProduct(cateModel.find(cbbCategory.getSelectedItem().toString()));

            pro.setPrice(BigDecimal.valueOf(Double.valueOf(txtSalePrice.getText())));
            //edit product
            if (proModel.edit(pro)) {
                JOptionPane.showMessageDialog(null, "Edited new product successfully!", "Success", 1);

                txtName.setText("");
                txtQuantity.setText("");
                txtSalePrice.setText("");

                jxdImportDate.setDate(new Date());

                lblPrice2.setVisible(false);

                lblQuan.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update the product!", "Failed", 0);
            }
            //reload data to table
            loadProductListToTable();

        } else {
            JOptionPane.showMessageDialog(null, "Check your information again.", "Warning", 2);
        }
    }//GEN-LAST:event_btUpdateProductActionPerformed

    private void btDeleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteProductActionPerformed
        // TODO add your handling code here:

        int re = JOptionPane.showConfirmDialog(rootPane, "Are you sure ?", "Warning", 2);
        if (re == JOptionPane.YES_OPTION) {
            proModel.delete(selectedProduct);
            JOptionPane.showMessageDialog(rootPane, "Deleted successfully!", "Success", 1);
            jxdImportDate.setDate(new Date());
            loadProductListToTable();

        }
    }//GEN-LAST:event_btDeleteProductActionPerformed

    private void btResetProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btResetProductActionPerformed
        // TODO add your handling code here:

        txtName.setText("");
        txtQuantity.setText("");
        txtSalePrice.setText("");

        setLableVisible(false);
        lblQuan.setVisible(false);
    }//GEN-LAST:event_btResetProductActionPerformed

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

    private void txtSalePriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSalePriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSalePriceActionPerformed

    private void btnAddCateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCateActionPerformed
        // TODO add your handling code here:
        CategoryFormDB cus = CategoryFormDB.getIns();
        JInternalFrame[] frmList = InternalMain.dtpProgram.getAllFrames();
        boolean found = false;
        for (JInternalFrame fr : frmList) {
            if (fr == cus) {
                found = true;
                break;
            }
        }
        if (!found) {
            InternalMain.dtpProgram.add(cus);

        }
        InternalMain.dtpProgram.getDesktopManager().maximizeFrame(cus);
        cus.show();


    }//GEN-LAST:event_btnAddCateActionPerformed

    private void btnAddSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSupActionPerformed
        // TODO add your handling code here:
        SupplierForm cf = SupplierForm.getIns();

        JInternalFrame[] frmList = InternalMain.dtpProgram.getAllFrames();
        boolean found = false;
        for (JInternalFrame fr : frmList) {
            if (fr == cf) {
                found = true;
                break;
            }
        }
        if (!found) {
            InternalMain.dtpProgram.add(cf);
        }
        InternalMain.dtpProgram.getDesktopManager().maximizeFrame(cf);
        cf.show();
    }//GEN-LAST:event_btnAddSupActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCreateProduct;
    private javax.swing.JButton btDeleteProduct;
    private javax.swing.JButton btResetProduct;
    private javax.swing.JButton btUpdateProduct;
    private javax.swing.JButton btnAddCate;
    private javax.swing.JButton btnAddSup;
    private javax.swing.JComboBox cbbCategory;
    private javax.swing.JComboBox cbbSup;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXDatePicker jxdImportDate;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblImportDate;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPrice2;
    private javax.swing.JLabel lblQuan;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblSalePrice;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblSearchIcon;
    private javax.swing.JLabel lblSu;
    private javax.swing.JPanel pnlField;
    private javax.swing.JPanel pnlFieldRight;
    private javax.swing.JPanel pnlFun;
    private javax.swing.JPanel pnlInput;
    private javax.swing.JPanel pnlOutput;
    private javax.swing.JPanel pnlQuan;
    private javax.swing.JPanel pnlSale;
    private javax.swing.JTable tblProduct;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtSalePrice;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
