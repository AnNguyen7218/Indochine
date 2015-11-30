/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.CustomerOrderService;
import entities.CustomerOrderTable;
import entities.Customers;
import entities.OrderTable;
import entities.OrderedDishes;
import entities.OrderedService;
//import entities.HamBillOrder;
import entities.Products;
import entities.RestaurantBill;
import entities.RestaurantTable;
import entities.Services;
//import entities.HamOrderLine;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import models.EmployeeEntityManager;
import models.OrderDishEntityManager;
import models.OrderSerEntityManager;
import models.OrderTableEntityManager;

import models.ProductEntityManager;
import models.ReportManager;
import models.ResCustomerEntityManager;
import models.RestaurantBillEntityManager;
import models.SerCustomerEntityManager;
import models.SerEntityManager;
import models.TableEntityManager;
import utils.ButtonColumn;
import utils.Converter;

/**
 *
 * @author lehai
 */
public final class OrderServices extends javax.swing.JInternalFrame {

    //Singleton object
    static OrderServices instance;

    //ProductEntityManager proModel = new ProductEntityManager();
//    OrderDishEntityManager orderLineModel = new OrderDishEntityManager();
    OrderSerEntityManager orderSerModel = new OrderSerEntityManager();
    OrderTableEntityManager orderTableModel = new OrderTableEntityManager();
    RestaurantBillEntityManager orderModel = new RestaurantBillEntityManager();
    ResCustomerEntityManager rescCusModel = new ResCustomerEntityManager();
    SerCustomerEntityManager serCusModel = new SerCustomerEntityManager();
    TableEntityManager tableModel = new TableEntityManager();
    SerEntityManager serModel = new SerEntityManager();

    ReportManager rpDAO = new ReportManager();
    final String searchTextPlaceHolder = "Enter keyword...";

    Services selectedProduct = null;
    RestaurantTable selectedTable = null;
    DefaultTableModel cartModel;

    //List of customers
    List<RestaurantTable> customerList = null;
    List<CustomerOrderTable> customerResList = null;
    List<CustomerOrderService> customerSerList = null;
    //Order's total
    double total = 0;

    /**
     * Gets the singleton instance
     *
     * @return the instance
     */
    public static OrderServices getIns() {
        if (instance == null) {
            instance = new OrderServices();
        }
        return instance;

    }

    public void setIcon() {
        this.setFrameIcon(new ImageIcon(getClass().getResource("/image/logoTitle.png")));
    }

    void changeModel(JTable table) {
        DefaultTableModel currentModel = (DefaultTableModel) table.getModel();
        currentModel.fireTableStructureChanged();
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
    public OrderServices() {
        initComponents();
        setIcon();
        txtPrice.setVisible(false);
        lblPrice.setVisible(false);

        txtSeller.setText(EmployeeEntityManager.currentEmployee.getAccountName());

        //Updates content every time a new product gets selected
        tblProduct.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedIndex = tblProduct.getSelectedRow();
                if (selectedIndex != -1) {

                    selectedProduct = serModel.find((int) tblProduct.getValueAt(selectedIndex, 0));
                    selectedTable = tableModel.find((int) tblProduct.getValueAt(selectedIndex, 0));
                    spnQuantity.setValue(1);

                }
            }
        });
        cbbCustomer.addItem("Service");
        cbbCustomer.addItem("Restaurant");
        cbbCustomer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbbCustomer.getSelectedItem().equals("Service")) {
                    spnQuantity.setVisible(true);
                   loadCbbSer();
                    loadSerListToTable(serModel.getAllFromDB());
                    setButonCartSer();
                    //Search box
                    txtSearch.setText(searchTextPlaceHolder);
                    txtSearch.getDocument().addDocumentListener(new DocumentListener() {

                        @Override
                        public void insertUpdate(DocumentEvent e) {
                            filterSerList();
                        }

                        @Override
                        public void removeUpdate(DocumentEvent e) {
                            filterSerList();
                        }

                        @Override
                        public void changedUpdate(DocumentEvent e) {
                            filterSerList();
                        }
                    });
                } else if (cbbCustomer.getSelectedItem().equals("Restaurant")) {

                    cbbCustomer1.removeAllItems();
                    spnQuantity.setVisible(false);
                    for (CustomerOrderTable cus : rescCusModel.getAll()) {
                        cbbCustomer1.addItem(cus.getCustomerName());
                    }
                    customerResList = rescCusModel.getAll();
                    loadTableListToTable(tableModel.getAllFromDB());
                    setButonCartTable();
                    //Search box
                    txtSearch1.setText(searchTextPlaceHolder);

                    txtSearch1.getDocument().addDocumentListener(new DocumentListener() {

                        @Override
                        public void insertUpdate(DocumentEvent e) {
                            filterTableList();
                        }

                        @Override
                        public void removeUpdate(DocumentEvent e) {
                            filterTableList();
                        }

                        @Override
                        public void changedUpdate(DocumentEvent e) {
                            filterTableList();
                        }
                    });
                }
            }
        });

    }
    
    void loadCbbSer(){
         cbbCustomer1.removeAllItems();
                    for (CustomerOrderService cus : serCusModel.getAll()) {
                        cbbCustomer1.addItem(cus.getCustomerName());
                    }
                    customerSerList = serCusModel.getAll();

    }

    void setButonCartTable() {
        changeModel(tblCart);
        cartModel = new DefaultTableModel();

        cartModel.addColumn("ID");
        cartModel.addColumn("Chair");
        cartModel.addColumn("Description");
        cartModel.addColumn("");
        tblCart.setModel(cartModel);
        //cartModel = (DefaultTableModel) tblCart.getModel();
        //The delete button in cart view
        Action addAction = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();

                int modelRow = Integer.valueOf(e.getActionCommand());
//                double itemTotal = Double.valueOf(cartModel.getValueAt(modelRow, 3).toString());
//
                ((DefaultTableModel) table.getModel()).removeRow(modelRow);
//
//                total -= itemTotal;
//                updateTotalLabel();
            }
        };

        new ButtonColumn(tblCart, addAction, 3);
    }

    void setButonCartSer() {
        changeModel(tblCart);
        cartModel = new DefaultTableModel();
        cartModel.addColumn("ID");
        cartModel.addColumn("Name");
        cartModel.addColumn("Quantity");
        cartModel.addColumn("Price");
        cartModel.addColumn("Total");
        cartModel.addColumn("");
        tblCart.setModel(cartModel);
        tblProduct.getColumn("ID").setMinWidth(0);
        tblProduct.getColumn("ID").setMaxWidth(0);
        //cartModel = (DefaultTableModel) tblCart.getModel();
        //The delete button in cart view
        Action addAction = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());
//                double itemTotal = Double.valueOf(cartModel.getValueAt(modelRow, 3).toString());
                ((DefaultTableModel) table.getModel()).removeRow(modelRow);
//                total -= itemTotal;
//                updateTotalLabel();
            }
        };

        new ButtonColumn(tblCart, addAction, 5);
    }

    /**
     * Load customer list to the combo box
     */
    /**
     * Filter the product list with search phrase
     */
    void filterSerList() {
        String searchText = txtSearch.getText().toLowerCase();
        if (searchText.isEmpty() || searchText.equalsIgnoreCase(searchTextPlaceHolder)) {
            loadSerListToTable(serModel.getAllFromDB());
            return;
        }
        List<Services> l = new ArrayList<>();
        for (Services ins : serModel.getAll()) {
            if (ins.getServiceName().toLowerCase().contains(searchText)) {
                l.add(ins);
            }
        }
        loadSerListToTable(l);
    }

    void filterTableList() {
        String searchText = txtSearch1.getText().toLowerCase();
        if (searchText.isEmpty() || searchText.equalsIgnoreCase(searchTextPlaceHolder)) {
            loadTableListToTable(tableModel.getAllFromDB());
            return;
        }
        List<RestaurantTable> l = new ArrayList<>();
        for (RestaurantTable ins : tableModel.getAll()) {
            if (ins.getDescription().toLowerCase().contains(searchText) || ins.getTableId().equals(Integer.valueOf(searchText))
                    || ins.getNumOfChairs().equals(Integer.valueOf(searchText))) {
                l.add(ins);
            }
        }
        loadTableListToTable(l);
    }

    /**
     * Load product list to table
     *
     * @param list product list
     */
    void loadSerListToTable(List<Services> list) {
        changeModel(tblProduct);
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
        dtm.addColumn("Price");

        for (Services pro : list) {
            dtm.addRow(new Object[]{
                pro.getServiceId(),
                pro.getServiceName(),
                pro.getPrice()
            });

        }

        //Adjust column width
        tblProduct.setModel(dtm);

//        tblProduct.getColumn("ID").setMaxWidth(36);
        tblProduct.getColumn("ID").setMinWidth(0);
        tblProduct.getColumn("ID").setMaxWidth(0);

    }

    void loadTableListToTable(List<RestaurantTable> list) {
        changeModel(tblProduct);
        //Disable inline editing in the table
        DefaultTableModel dtm1 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };

        //Populate the data
        dtm1.addColumn("ID");
        dtm1.addColumn("Chair number");
        dtm1.addColumn("Description");

        for (RestaurantTable pro : list) {
            dtm1.addRow(new Object[]{
                pro.getTableId(),
                pro.getNumOfChairs(),
                pro.getDescription()

            });

        }

        //Adjust column width
        tblProduct.setModel(dtm1);

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

        scrProduct1 = new javax.swing.JScrollPane();
        tblProduct1 = new javax.swing.JTable();
        pnlSearch1 = new javax.swing.JPanel();
        lblSearch1 = new javax.swing.JLabel();
        txtSearch1 = new javax.swing.JTextField();
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
        cbbCustomer1 = new javax.swing.JComboBox();
        lblCustomer1 = new javax.swing.JLabel();
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
        DateIn = new org.jdesktop.swingx.JXDatePicker();
        jLabel2 = new javax.swing.JLabel();
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

        scrProduct1.setMinimumSize(new java.awt.Dimension(450, 280));
        scrProduct1.setPreferredSize(new java.awt.Dimension(450, 100));

        tblProduct1.setMinimumSize(new java.awt.Dimension(50, 64));
        tblProduct1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblProduct1.getTableHeader().setReorderingAllowed(false);
        scrProduct1.setViewportView(tblProduct1);

        pnlSearch1.setLayout(new javax.swing.BoxLayout(pnlSearch1, javax.swing.BoxLayout.LINE_AXIS));

        lblSearch1.setText("Search: ");
        pnlSearch1.add(lblSearch1);

        txtSearch1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearch1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSearch1FocusLost(evt);
            }
        });
        txtSearch1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSearch1MouseClicked(evt);
            }
        });
        pnlSearch1.add(txtSearch1);

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(175, 175, 175), 4));
        setClosable(true);
        setTitle("Check out");
        setMinimumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        pnlBottom.setLayout(new java.awt.GridBagLayout());

        btnSubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/submit.png"))); // NOI18N
        btnSubmit.setText("Submit");
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

        lblCustomer.setText("Type:");
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

        cbbCustomer1.setMinimumSize(new java.awt.Dimension(190, 20));
        cbbCustomer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbCustomer1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 0);
        pnlDate.add(cbbCustomer1, gridBagConstraints);

        lblCustomer1.setText("Customer:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        pnlDate.add(lblCustomer1, gridBagConstraints);

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
        gridBagConstraints.gridy = 9;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        pnlButtons.add(btnClearCart, gridBagConstraints);

        btnAddToCart.setText(">>");
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
        gridBagConstraints.gridy = 8;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        pnlButtons.add(DateIn, gridBagConstraints);

        jLabel2.setText("Date in:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        pnlButtons.add(jLabel2, gridBagConstraints);

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

        tblCart.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblCart.getTableHeader().setReorderingAllowed(false);
        scrCart.setViewportView(tblCart);

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

        pnlProduct.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "List"));
        pnlProduct.setInheritsPopupMenu(true);
        pnlProduct.setMinimumSize(new java.awt.Dimension(500, 400));
        pnlProduct.setPreferredSize(new java.awt.Dimension(500, 400));
        pnlProduct.setLayout(new java.awt.GridBagLayout());

        scrProduct.setMinimumSize(new java.awt.Dimension(450, 300));
        scrProduct.setPreferredSize(new java.awt.Dimension(450, 300));

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
        gridBagConstraints.gridy = 5;
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
        gridBagConstraints.gridy = 4;
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

        int quantity = 0;
        int index = -1, totalQuantity = 0, addedQuantity = 0;
        // double price=0;
        //Amount of product recently ordered
        try {
            quantity = Integer.valueOf(spnQuantity.getValue().toString());
            //price = Double.valueOf(txtPrice.getText());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Invalid input data", "Error", 0);
            return;
        }
        //set Cart Model 
        if (cbbCustomer.getSelectedItem().equals("Restaurant")) {
            //setButonCartTable();
            cartModel.addRow(new Object[]{
                selectedTable.getTableId(),
                selectedTable.getNumOfChairs(),
                selectedTable.getDescription(),
                "Remove"
            });

        } else {
            // setButonCartSer();

            //calculation
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
                    if (id == selectedProduct.getServiceId()) {
                        //If the item also has been sold for same price then save the corresponding row's index in order to 
                        //adding its quantity to the quantity column of the row later

                        index = i;
                        addedQuantity = Integer.valueOf(cartModel.getValueAt(i, 2).toString());

                        //The sum of quantity of that item in the cart (without adding new amount)
                        totalQuantity += Integer.valueOf(cartModel.getValueAt(i, 2).toString());
                    }
                }
            } catch (Exception ex) {
                //JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Process failed!", JOptionPane.ERROR_MESSAGE);
                total = 0;
                return;
            }

            if (index != -1) {
                cartModel.setValueAt(quantity + addedQuantity, index, 2);
                cartModel.setValueAt((quantity + addedQuantity) * selectedProduct.getPrice().doubleValue(), index, 4);

            } else {
                cartModel.addRow(new Object[]{
                    selectedProduct.getServiceId(),
                    selectedProduct.getServiceName(),
                    quantity,
                    selectedProduct.getPrice(),
                    (quantity) * selectedProduct.getPrice().doubleValue(),
                    "Remove"
                });

                tblProduct.getColumn("ID").setMinWidth(0);
                tblProduct.getColumn("ID").setMaxWidth(0);
            }
            total += quantity * selectedProduct.getPrice().doubleValue();
            updateTotalLabel();
        }

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
        if (cbbCustomer.getSelectedItem().equals("Restaurant")) {
            //Create bill
            for (int i = 0; i < tblCart.getRowCount(); i++) {
                RestaurantTable orderedTable = new RestaurantTable();
                orderedTable = tableModel.find(Integer.valueOf(tblProduct.getValueAt(i, 0).toString()));
                OrderTable bill = new OrderTable();
                bill.setRestaurantTable(orderedTable);
                bill.setDateOrder(new Date());
                bill.setDateIn(DateIn.getDate());
                bill.setCustomerOrderTable(rescCusModel.find(cbbCustomer1.getSelectedItem().toString()));
                bill.setAccounts(EmployeeEntityManager.currentEmployee);

                //Create orderline
                if (orderTableModel.addNew(bill)) {
                    try {
                        orderedTable.setStatus(3);
                        tableModel.update(orderedTable);

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error in payment: " + ex.getMessage(), "Failed ", JOptionPane.ERROR_MESSAGE);
                        orderTableModel.delete(bill);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Can not make bill.", "Failed", JOptionPane.ERROR_MESSAGE);
                }
            }

            int re = JOptionPane.showConfirmDialog(rootPane, "Payment completed! You may want to print the Bill", "Completed", 1);
            btnClearCartActionPerformed(evt);
        } else {
            for (int i = 0; i < tblCart.getRowCount(); i++) {
                OrderedService billSer = new OrderedService();
                billSer.setAccounts(EmployeeEntityManager.currentEmployee);
                billSer.setCustomerOrderService(serCusModel.find(cbbCustomer1.getSelectedItem().toString()));
                billSer.setDateOrder(DateIn.getDate());
                billSer.setServices(serModel.find(Integer.valueOf(cartModel.getValueAt(i, 0).toString())));
                billSer.setQuantity(Integer.valueOf(cartModel.getValueAt(i, 2).toString()));
                if (orderSerModel.addNew(billSer)) {
                    try {

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error in payment: " + ex.getMessage(), "Failed ", JOptionPane.ERROR_MESSAGE);
                        orderSerModel.delete(billSer);
                    }
                }
            }
            btnClearCartActionPerformed(evt);
            int re = JOptionPane.showConfirmDialog(rootPane, "Payment completed! You may want to print the Bill", "Completed", 1);
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void cbbCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbCustomerActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cbbCustomerActionPerformed

    private void cbbCustomer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbCustomer1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbCustomer1ActionPerformed

    private void txtSearch1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearch1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearch1MouseClicked

    private void txtSearch1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearch1FocusLost
        // TODO add your handling code here:
        if (txtSearch1.getText().isEmpty()) {
            txtSearch1.setText(searchTextPlaceHolder);
        }
    }//GEN-LAST:event_txtSearch1FocusLost

    private void txtSearch1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearch1FocusGained
        // TODO add your handling code here:
        if (txtSearch1.getText().equalsIgnoreCase(searchTextPlaceHolder)) {
            txtSearch1.setText("");
        }
    }//GEN-LAST:event_txtSearch1FocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXDatePicker DateIn;
    private javax.swing.JButton btnAddToCart;
    private javax.swing.JButton btnClearCart;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JComboBox cbbCustomer;
    private javax.swing.JComboBox cbbCustomer1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblCustomer;
    private javax.swing.JLabel lblCustomer1;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblQuan;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblSearch1;
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
    private javax.swing.JPanel pnlSearch1;
    private javax.swing.JPanel pnlSeller;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JScrollPane scrCart;
    private javax.swing.JScrollPane scrProduct;
    private javax.swing.JScrollPane scrProduct1;
    private javax.swing.JSpinner spnQuantity;
    private javax.swing.JTable tblCart;
    private javax.swing.JTable tblProduct;
    private javax.swing.JTable tblProduct1;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearch1;
    private javax.swing.JTextField txtSeller;
    // End of variables declaration//GEN-END:variables
}
