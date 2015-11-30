/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.OrderedDishes;
//import entities.HamBillOrder;
import entities.Products;
import entities.RestaurantBill;
import entities.RestaurantTable;
//import entities.HamOrderLine;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import models.EmployeeEntityManager;
import models.OrderDishEntityManager;

import models.ProductEntityManager;
import models.ReportManager;
import models.RestaurantBillEntityManager;
import models.TableEntityManager;
import utils.ButtonColumn;
import utils.Converter;

/**
 *
 * @author lehai
 */
public final class CheckOutForm extends javax.swing.JInternalFrame {

    //Singleton object
    static CheckOutForm instance;
    
    ProductEntityManager proModel = new ProductEntityManager();
    OrderDishEntityManager orderLineModel = new OrderDishEntityManager();
    RestaurantBillEntityManager orderModel = new RestaurantBillEntityManager();

    TableEntityManager tableModel = new TableEntityManager();

    ReportManager rpDAO = new ReportManager();
    final String searchTextPlaceHolder = "Enter keyword...";

    Products selectedProduct = null;

    DefaultTableModel cartModel;

    //List of customers
    List<RestaurantTable> customerList = null;
    //Order's total
    double total = 0;

    /**
     * Gets the singleton instance
     *
     * @return the instance
     */
    public static CheckOutForm getIns() {
        if (instance == null) {
            instance = new CheckOutForm();
        }
        return instance;

    }

    /**
     * Process for buttons availability
     */
    void enableButtons() {
        if (txtPrice.getText().isEmpty()) {
            btnAddToCart.setEnabled(false);
        } else {
            btnAddToCart.setEnabled(true);
        }
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
     * Updates the total label
     */
    void updateTotalLabel() {
        lblTotal.setText("TOTAL: $" + total);

        btnSubmit.setEnabled(tblCart.getRowCount() > 0);
    }

    /**
     * Creates new form CheckOutForm
     */
    public CheckOutForm() {
        initComponents();
        setIcon();
        loadProductListToTable(proModel.getAll());
        loadCustomers();
        cartModel = (DefaultTableModel) tblCart.getModel();
        txtSeller.setText(EmployeeEntityManager.currentEmployee.getAccountName());
        txtDate.setText(Converter.dateToString(new Date()));

        //Search box
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

        //The delete button in cart view
        Action addAction = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();

                int modelRow = Integer.valueOf(e.getActionCommand());
                double itemTotal = Double.valueOf(cartModel.getValueAt(modelRow, 4).toString());

                ((DefaultTableModel) table.getModel()).removeRow(modelRow);

                total -= itemTotal;
                updateTotalLabel();
            }
        };

        new ButtonColumn(tblCart, addAction, 5);

        //Updates content every time a new product gets selected
        tblProduct.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedIndex = tblProduct.getSelectedRow();
                if (selectedIndex != -1) {
                    selectedProduct = proModel.find((int) tblProduct.getValueAt(selectedIndex, 0));
                    spnQuantity.setValue(1);
                    txtPrice.setText(selectedProduct.getPrice().toString());
                    enableButtons();
                }
            }
        });

    }

    /**
     * Load customer list to the combo box
     */
    void loadCustomers() {
        cbbCustomer.removeAllItems();
        customerList = tableModel.getAll();
        for (RestaurantTable c : tableModel.getAll()) {
            cbbCustomer.addItem(c.getTableId());
        }

    }

    /**
     * Filter the product list with search phrase
     */
    void filterProductList() {
        String searchText = txtSearch.getText().toLowerCase();
        if (searchText.isEmpty() || searchText.equalsIgnoreCase(searchTextPlaceHolder)) {
            return;
        }
        List<Products> l = new ArrayList<>();
        for (Products ins : proModel.getAll()) {
            if (ins.getProductName().toLowerCase().contains(searchText)
                    || ins.getCategoryOfProduct().getCateName().toLowerCase().contains(searchText)) {
                l.add(ins);
            }
        }
        loadProductListToTable(l);
    }

    /**
     * Load product list to table
     *
     * @param list product list
     */
    void loadProductListToTable(List<Products> list) {

        //Disable inline editing in the table
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };

        //Populate the data
        dtm.addColumn("ID");
        dtm.addColumn("Name");

        dtm.addColumn("In stock");
        dtm.addColumn("Category");

        for (Products pro : list) {
            dtm.addRow(new Object[]{
                pro.getProductId(),
                pro.getProductName(),
                pro.getQuantity(),
                pro.getCategoryOfProduct().getCateName()
            });

        }

        //Adjust column width
        tblProduct.setModel(dtm);
//        tblProduct.getColumn("ID").setMaxWidth(36);
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

        pnlBottom = new javax.swing.JPanel();
        btnSubmit = new javax.swing.JButton();
        pnlTop = new javax.swing.JPanel();
        pnlSeller = new javax.swing.JPanel();
        lblSeller = new javax.swing.JLabel();
        txtSeller = new javax.swing.JTextField();
        lbltemp = new javax.swing.JLabel();
        pnlCustomer = new javax.swing.JPanel();
        lblCustomer = new javax.swing.JLabel();
        cbbCustomer = new javax.swing.JComboBox();
        pnlDate = new javax.swing.JPanel();
        lblDate = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        pnlButtons = new javax.swing.JPanel();
        btnClearCart = new javax.swing.JButton();
        btnAddToCart = new javax.swing.JButton();
        lblPrice = new javax.swing.JLabel();
        lblQuan = new javax.swing.JLabel();
        SpinnerModel model =
        new SpinnerNumberModel(1, //initial value
            1, //min
            null, //max
            1);
        spnQuantity = new javax.swing.JSpinner(model);
        txtPrice = new javax.swing.JTextField();
        pnlCart = new javax.swing.JPanel();
        scrCart = new javax.swing.JScrollPane();
        tblCart = new javax.swing.JTable();
        lblTotal = new javax.swing.JLabel();
        pnlProduct = new javax.swing.JPanel();
        scrProduct = new javax.swing.JScrollPane();
        tblProduct = new javax.swing.JTable();
        pnlSearch = new javax.swing.JPanel();
        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(175, 175, 175), 4));
        setClosable(true);
        setTitle("Check out");
        setMinimumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        pnlBottom.setLayout(new java.awt.GridBagLayout());

        btnSubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/submit.png"))); // NOI18N
        btnSubmit.setText("Submit");
        btnSubmit.setEnabled(false);
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 7;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        pnlBottom.add(btnSubmit, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 15);
        getContentPane().add(pnlBottom, gridBagConstraints);

        pnlTop.setLayout(new java.awt.GridLayout(1, 3));

        pnlSeller.setLayout(new java.awt.GridBagLayout());

        lblSeller.setText("Seller:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlSeller.add(lblSeller, gridBagConstraints);

        txtSeller.setEnabled(false);
        txtSeller.setMinimumSize(new java.awt.Dimension(200, 20));
        txtSeller.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlSeller.add(txtSeller, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        pnlSeller.add(lbltemp, gridBagConstraints);

        pnlTop.add(pnlSeller);

        pnlCustomer.setLayout(new java.awt.GridBagLayout());

        lblCustomer.setText("Table:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlCustomer.add(lblCustomer, gridBagConstraints);

        cbbCustomer.setMinimumSize(new java.awt.Dimension(190, 20));
        cbbCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbCustomerActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 0);
        pnlCustomer.add(cbbCustomer, gridBagConstraints);

        pnlTop.add(pnlCustomer);

        pnlDate.setLayout(new java.awt.GridBagLayout());

        lblDate.setText("Date:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlDate.add(lblDate, gridBagConstraints);

        txtDate.setEnabled(false);
        txtDate.setMinimumSize(new java.awt.Dimension(200, 20));
        txtDate.setPreferredSize(new java.awt.Dimension(200, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlDate.add(txtDate, gridBagConstraints);

        pnlTop.add(pnlDate);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        getContentPane().add(pnlTop, gridBagConstraints);

        pnlButtons.setLayout(new java.awt.GridBagLayout());

        btnClearCart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/clearcart.png"))); // NOI18N
        btnClearCart.setText("Clear cart");
        btnClearCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearCartActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        pnlButtons.add(btnClearCart, gridBagConstraints);

        btnAddToCart.setText(">>");
        btnAddToCart.setEnabled(false);
        btnAddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToCartActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        pnlButtons.add(btnAddToCart, gridBagConstraints);

        lblPrice.setText("Price:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        pnlButtons.add(lblPrice, gridBagConstraints);

        lblQuan.setText("Quantity:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        pnlButtons.add(lblQuan, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        pnlButtons.add(spnQuantity, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        pnlButtons.add(txtPrice, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        getContentPane().add(pnlButtons, gridBagConstraints);

        pnlCart.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Cart"));
        pnlCart.setMinimumSize(new java.awt.Dimension(500, 400));
        pnlCart.setPreferredSize(new java.awt.Dimension(500, 400));
        pnlCart.setLayout(new java.awt.GridBagLayout());

        scrCart.setMinimumSize(new java.awt.Dimension(460, 360));
        scrCart.setPreferredSize(new java.awt.Dimension(460, 360));

        tblCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Quantity", "Price", "Total", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCart.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblCart.getTableHeader().setReorderingAllowed(false);
        scrCart.setViewportView(tblCart);
        if (tblCart.getColumnModel().getColumnCount() > 0) {
            tblCart.getColumnModel().getColumn(0).setMinWidth(0);
            tblCart.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblCart.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 4;
        pnlCart.add(scrCart, gridBagConstraints);

        lblTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(51, 51, 51));
        lblTotal.setText("Total: $0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        pnlCart.add(lblTotal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        getContentPane().add(pnlCart, gridBagConstraints);

        pnlProduct.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Products"));
        pnlProduct.setMinimumSize(new java.awt.Dimension(500, 400));
        pnlProduct.setPreferredSize(new java.awt.Dimension(500, 400));
        pnlProduct.setLayout(new java.awt.GridBagLayout());

        scrProduct.setMinimumSize(new java.awt.Dimension(450, 310));
        scrProduct.setPreferredSize(new java.awt.Dimension(450, 310));

        tblProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblProduct.setMinimumSize(new java.awt.Dimension(50, 64));
        tblProduct.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblProduct.getTableHeader().setReorderingAllowed(false);
        scrProduct.setViewportView(tblProduct);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        pnlProduct.add(scrProduct, gridBagConstraints);

        pnlSearch.setLayout(new javax.swing.BoxLayout(pnlSearch, javax.swing.BoxLayout.LINE_AXIS));

        lblSearch.setText("Search: ");
        pnlSearch.add(lblSearch);

        txtSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSearchFocusLost(evt);
            }
        });
        pnlSearch.add(txtSearch);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 10, 0);
        pnlProduct.add(pnlSearch, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        getContentPane().add(pnlProduct, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void btnAddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToCartActionPerformed
        // TODO add your handling code here:

        //Amount of product recently ordered
        int quantity;
        double price;
        try {
            quantity = Integer.valueOf(spnQuantity.getValue().toString());
            price = Double.valueOf(txtPrice.getText());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Invalid input data", "Error", 0);
            return;
        }

        //Items to be added to cart can be sold for various prices in an order.
        //Therefore, the cart may contain several items of the same type with different sale prices,
        //but the sum of their quantity should be less than or equal to the stock quantity, as they
        //actually are one same type of item.
        //index: the index of the same item that has been sold for same price if there's any
        //addedQuantity: the ammount of that item if there's any
        //totalQuantity: total ammount of that item in the order bill
        int index = -1, totalQuantity = 0, addedQuantity = 0;
        try {

            total = 0;
            //Get the sum of quantity of the same item as the selected item if there is any
            //Also take advantage of the loop to calculate order's total
            for (int i = 0; i < tblCart.getRowCount(); i++) {

                //Calculate total
                total += Double.valueOf(cartModel.getValueAt(i, 4).toString());

                //Get current item's ID
                int id = Integer.valueOf(cartModel.getValueAt(i, 0).toString());

                //Get current item's sale price
                double cartPrice = Double.valueOf(cartModel.getValueAt(i, 3).toString());

                //If the item has already existed in cart then add its quantity to the sum
                if (id == selectedProduct.getProductId()) {
                    //If the item also has been sold for same price then save the corresponding row's index in order to 
                    //adding its quantity to the quantity column of the row later
                    if (Double.compare(cartPrice, price) == 0) {
                        index = i;
                        addedQuantity = Integer.valueOf(cartModel.getValueAt(i, 2).toString());
                    }
                    //The sum of quantity of that item in the cart (without adding new amount)
                    totalQuantity += Integer.valueOf(cartModel.getValueAt(i, 2).toString());
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Process failed!", JOptionPane.ERROR_MESSAGE);
            total = 0;
            return;
        }

        //Check if there're enough product to provide for this order
        if (quantity + totalQuantity <= selectedProduct.getQuantity()) {
            //If the item with same price exists
            if (index != -1) {
                cartModel.setValueAt(quantity + addedQuantity, index, 2);
                cartModel.setValueAt((quantity + addedQuantity) * price, index, 4);

            } //Else add new row of the item
            else {
                cartModel.addRow(new Object[]{
                    selectedProduct.getProductId(),
                    selectedProduct.getProductName(),
                    quantity,
                    price,
                    quantity * price,
                    "Remove"
                });
            }

            //Add to order's total
            total += quantity * price;
            updateTotalLabel();
        } else {
            JOptionPane.showMessageDialog(null, "Not enough product to provide for this order!", "Error", 0);
        }

        //Adjust columns appearance
        tblCart.getColumn("ID").setMinWidth(0);
        tblCart.getColumn("ID").setMaxWidth(0);
        tblCart.getColumn("Quantity").setMaxWidth(72);
        tblCart.getColumn("").setMaxWidth(90);


    }//GEN-LAST:event_btnAddToCartActionPerformed

    private void btnClearCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearCartActionPerformed
        // TODO add your handling code here:
        while (cartModel.getRowCount() > 0) {
            cartModel.removeRow(0);
        }

        total = 0;
        updateTotalLabel();

    }//GEN-LAST:event_btnClearCartActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:

        //Create bill
        RestaurantBill bill = new RestaurantBill();
        bill.setDate(new Date());
        bill.setRestaurantTable(customerList.get(cbbCustomer.getSelectedIndex()));
        bill.setAccounts(EmployeeEntityManager.currentEmployee);
        bill.setTotal(BigDecimal.valueOf(total));
//
//        //Create orderline
        if (orderModel.addNew(bill)) {
            try {
                for (int i = 0; i < cartModel.getRowCount(); i++) {
                    OrderedDishes pb = new OrderedDishes();
                    Products product = proModel.find(Integer.valueOf(cartModel.getValueAt(i, 0).toString()));
                    int quantity = Integer.valueOf(cartModel.getValueAt(i, 2).toString());
                    product.setQuantity(product.getQuantity() - quantity);

                    if (proModel.edit(product)) {
                        pb.setRestaurantBill(bill);
                        pb.setProducts(product);
                        pb.setQuantity(quantity);
                        // pb.setActualSalePrice(BigDecimal.valueOf(Double.valueOf(cartModel.getValueAt(i, 3).toString())));

                        orderLineModel.insert(pb);

                        RestaurantTable orderedTable = tableModel.find(cbbCustomer.getSelectedItem());
                        orderedTable.setStatus(2);
                        tableModel.update(orderedTable);
                    } else {
                        throw new Exception();
                    }
                }
                JOptionPane.showConfirmDialog(pnlCustomer, "Complete! Print Bill? ", "Quesiton", JOptionPane.YES_NO_CANCEL_OPTION);
                int re = 0;
                if ( re == JOptionPane.YES_OPTION) {
                    String billId = bill.getId().toString();
//                    String seller = txtSeller.getText();
//                    String customer = cbbCustomer.getSelectedItem().toString();
//                    String date = txtDate.getText();
                    Map<String, Object> param = new HashMap<String, Object>();
                    param.put("billID", billId);
//                    param.put("seller", seller);
//                    param.put("customer", customer);
//                    param.put("date", date);
                    rpDAO.reportCheckOutRestaurant(param);
                }
                loadProductListToTable(proModel.getAll());
                btnClearCartActionPerformed(evt);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error in payment: " + ex.getMessage(), "Failed ", JOptionPane.ERROR_MESSAGE);
                orderModel.delete(bill);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Can not make bill.", "Failed", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnSubmitActionPerformed

    private void cbbCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbCustomerActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cbbCustomerActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddToCart;
    private javax.swing.JButton btnClearCart;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JComboBox cbbCustomer;
    private javax.swing.JLabel lblCustomer;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblQuan;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblSeller;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lbltemp;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlButtons;
    private javax.swing.JPanel pnlCart;
    private javax.swing.JPanel pnlCustomer;
    private javax.swing.JPanel pnlDate;
    private javax.swing.JPanel pnlProduct;
    private javax.swing.JPanel pnlSearch;
    private javax.swing.JPanel pnlSeller;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JScrollPane scrCart;
    private javax.swing.JScrollPane scrProduct;
    private javax.swing.JSpinner spnQuantity;
    private javax.swing.JTable tblCart;
    private javax.swing.JTable tblProduct;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSeller;
    // End of variables declaration//GEN-END:variables
}
