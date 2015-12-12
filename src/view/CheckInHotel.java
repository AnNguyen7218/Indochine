/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.BookedRooms;
import entities.BookingRoom;
import entities.Customers;
import entities.HotelOrderDish;
import entities.HotelOrderService;
import entities.Products;
import entities.Rooms;
import entities.Services;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import models.CustomerEntityManager;
import models.EmployeeEntityManager;
import models.HotelDishEntityManager;
import models.HotelSerEntityManager;
import models.OrderEntityManager;
import models.OrderLineEntityManager;
import models.ProductEntityManager;
import models.ReportManager;
import models.RoomEntityManager;
import models.SerEntityManager;
import utils.Converter;
import utils.RegEx;

/**
 *
 * @author Ashley
 */
public class CheckInHotel extends javax.swing.JInternalFrame {

    /**
     * Creates new form CheckInHotel
     */
    public static CheckInHotel instance;
    CustomerEntityManager cusModel = new CustomerEntityManager();
    List<Customers> customerList = null;
    DefaultListModel<String> dlm = new DefaultListModel<String>();
    OrderEntityManager bookingModel = new OrderEntityManager();
    OrderLineEntityManager bookedModel = new OrderLineEntityManager();
    RoomEntityManager roomModel = new RoomEntityManager();
    SerEntityManager serModel = new SerEntityManager();
    ProductEntityManager proModel = new ProductEntityManager();

    HotelSerEntityManager hs = new HotelSerEntityManager();
    HotelDishEntityManager hd = new HotelDishEntityManager();

    DefaultTableModel dtm = new DefaultTableModel();
    DefaultTableModel dtm1 = new DefaultTableModel();

    public CheckInHotel() {
        initComponents();
//add model to table
        dtm.addColumn("Room");
        dtm.addColumn("Service Name");
        dtm.addColumn("Quantity");

        tblSer.setModel(dtm);

        dtm1.addColumn("Room");
        dtm1.addColumn("Product Name");
        dtm1.addColumn("Quantity");

        tblRes.setModel(dtm1);
//set warning label default status        
        lblWarn.setVisible(false);
        lblWarn1.setVisible(false);
        lblWarn2.setVisible(false);

        txtSeller.setText(EmployeeEntityManager.currentEmployee.getAccountName());
        txtDate.setText(String.valueOf(new Date()));
        loadCustomer();

        txtNuAdult.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (RegEx.isInteger(txtNuAdult.getText())) {
                    lblWarn1.setVisible(false);
                } else {
                    lblWarn1.setVisible(true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (RegEx.isInteger(txtNuAdult.getText())) {
                    lblWarn1.setVisible(false);
                } else {
                    lblWarn1.setVisible(true);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (RegEx.isInteger(txtNuAdult.getText())) {
                    lblWarn1.setVisible(false);
                } else {
                    lblWarn1.setVisible(true);
                }
            }
        });

        txtNumChil.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (RegEx.isInteger(txtNumChil.getText())) {
                    lblWarn.setVisible(false);
                } else {
                    lblWarn.setVisible(true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (RegEx.isInteger(txtNumChil.getText())) {
                    lblWarn.setVisible(false);
                } else {
                    lblWarn.setVisible(true);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (RegEx.isInteger(txtNumChil.getText())) {
                    lblWarn.setVisible(false);
                } else {
                    lblWarn.setVisible(true);
                }
            }
        });

        txtTraTruoc.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (RegEx.isInteger(txtTraTruoc.getText())) {
                    lblWarn2.setVisible(false);
                } else {
                    lblWarn2.setVisible(true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (RegEx.isInteger(txtTraTruoc.getText())) {
                    lblWarn2.setVisible(false);
                } else {
                    lblWarn2.setVisible(true);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (RegEx.isInteger(txtTraTruoc.getText())) {
                    lblWarn2.setVisible(false);
                } else {
                    lblWarn2.setVisible(true);
                }
            }
        });

    }

    void loadSelectedRoom() {
        for (Rooms r : RoomForm.selectedList) {
            dlm.addElement(r.getRoomName());
            cbbRoom.addItem(r.getRoomName());
            cbbResRoom.addItem(r.getRoomName());
        }
        RoomList.setModel(dlm);
        for (Services s : serModel.getAllFromDB()) {
            cbbSer.addItem(s.getServiceName());
        }
        for (Products p : proModel.getAllFromDB()) {
            cbbProduct.addItem(p.getProductName());
        }

    }

    void loadCustomer() {
        cbbCustomer.removeAllItems();
        customerList = cusModel.getAll();
        for (Customers c : cusModel.getAll()) {
            cbbCustomer.addItem(c.getCustomerName());
        }
    }

    public static CheckInHotel getIns() {
        if (instance == null) {
            instance = new CheckInHotel();
        }
        return instance;

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

        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jLabel8 = new javax.swing.JLabel();
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
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lblWarn1 = new javax.swing.JLabel();
        lblWarn = new javax.swing.JLabel();
        txtNuAdult = new javax.swing.JTextField();
        lblNuChild = new javax.swing.JLabel();
        txtNumChil = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTraTruoc = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblWarn2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        FromDate = new org.jdesktop.swingx.JXDatePicker();
        ToDate = new org.jdesktop.swingx.JXDatePicker();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        RoomList = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        cbGetRoom = new javax.swing.JCheckBox();
        jPanel6 = new javax.swing.JPanel();
        cbbProduct = new javax.swing.JComboBox();
        lblCustomer1 = new javax.swing.JLabel();
        lblCustomer2 = new javax.swing.JLabel();
        cbbResRoom = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txtResRoom = new javax.swing.JTextField();
        btnDelRes = new javax.swing.JButton();
        btnAddRes = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRes = new javax.swing.JTable();
        btnSubmitRes = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        cbbSer = new javax.swing.JComboBox();
        lblCustomer3 = new javax.swing.JLabel();
        lblCustomer4 = new javax.swing.JLabel();
        cbbRoom = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        txtSerQuan = new javax.swing.JTextField();
        bnDelSer = new javax.swing.JButton();
        btnAddSer = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSer = new javax.swing.JTable();
        btnSubmit = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel8.setText("jLabel6");

        setTitle("Check in");
        setPreferredSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        pnlSeller.setLayout(new java.awt.GridBagLayout());

        lblSeller.setText("Receptionist:");
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

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(pnlSeller, gridBagConstraints);

        pnlCustomer.setLayout(new java.awt.GridBagLayout());

        lblCustomer.setText("Customer:");
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

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 85, 0, 7);
        getContentPane().add(pnlCustomer, gridBagConstraints);

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

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(pnlDate, gridBagConstraints);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setMinimumSize(new java.awt.Dimension(1100, 500));
        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 500));
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel4.setMinimumSize(new java.awt.Dimension(350, 200));
        jPanel4.setPreferredSize(new java.awt.Dimension(350, 200));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        lblWarn1.setForeground(new java.awt.Color(255, 0, 0));
        lblWarn1.setText("*Must be number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel4.add(lblWarn1, gridBagConstraints);

        lblWarn.setForeground(new java.awt.Color(255, 0, 0));
        lblWarn.setText("*Must be number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel4.add(lblWarn, gridBagConstraints);

        txtNuAdult.setMinimumSize(new java.awt.Dimension(200, 25));
        txtNuAdult.setPreferredSize(new java.awt.Dimension(200, 25));
        txtNuAdult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNuAdultActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 12, 12);
        jPanel4.add(txtNuAdult, gridBagConstraints);

        lblNuChild.setText("Num of Children:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(9, 9, 9, 9);
        jPanel4.add(lblNuChild, gridBagConstraints);

        txtNumChil.setMinimumSize(new java.awt.Dimension(200, 25));
        txtNumChil.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        jPanel4.add(txtNumChil, gridBagConstraints);

        jLabel5.setText("Num of Adult:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        jPanel4.add(jLabel5, gridBagConstraints);

        txtTraTruoc.setMinimumSize(new java.awt.Dimension(200, 25));
        txtTraTruoc.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 12, 12);
        jPanel4.add(txtTraTruoc, gridBagConstraints);

        jLabel1.setText("Paid:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        jPanel4.add(jLabel1, gridBagConstraints);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        jPanel4.add(jPanel3, gridBagConstraints);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        jPanel4.add(jPanel2, gridBagConstraints);

        lblWarn2.setForeground(new java.awt.Color(255, 0, 0));
        lblWarn2.setText("*Must be number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel4.add(lblWarn2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jPanel4, gridBagConstraints);

        jPanel5.setMinimumSize(new java.awt.Dimension(300, 250));
        jPanel5.setPreferredSize(new java.awt.Dimension(300, 250));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel3.setText("From Date:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 11, 11);
        jPanel5.add(jLabel3, gridBagConstraints);

        jLabel4.setText("To Date:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 11, 11);
        jPanel5.add(jLabel4, gridBagConstraints);

        FromDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FromDateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 11, 11);
        jPanel5.add(FromDate, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 11, 11);
        jPanel5.add(ToDate, gridBagConstraints);

        jLabel10.setText("Rooms:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        jPanel5.add(jLabel10, gridBagConstraints);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 100));
        jScrollPane1.setViewportView(RoomList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        jPanel5.add(jScrollPane1, gridBagConstraints);

        jButton1.setText("+");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        jPanel5.add(jButton1, gridBagConstraints);

        cbGetRoom.setText("Get Room Later");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.insets = new java.awt.Insets(4, 7, 4, 7);
        jPanel5.add(cbGetRoom, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 1);
        jPanel1.add(jPanel5, gridBagConstraints);

        jPanel6.setMinimumSize(new java.awt.Dimension(550, 200));
        jPanel6.setPreferredSize(new java.awt.Dimension(550, 200));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        cbbProduct.setMinimumSize(new java.awt.Dimension(120, 20));
        cbbProduct.setPreferredSize(new java.awt.Dimension(120, 20));
        cbbProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbProductActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 0);
        jPanel6.add(cbbProduct, gridBagConstraints);

        lblCustomer1.setText("Room:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel6.add(lblCustomer1, gridBagConstraints);

        lblCustomer2.setText("Product:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel6.add(lblCustomer2, gridBagConstraints);

        cbbResRoom.setMinimumSize(new java.awt.Dimension(90, 20));
        cbbResRoom.setPreferredSize(new java.awt.Dimension(90, 20));
        cbbResRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbResRoomActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 0);
        jPanel6.add(cbbResRoom, gridBagConstraints);

        jLabel2.setText("Quantity");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 6);
        jPanel6.add(jLabel2, gridBagConstraints);

        txtResRoom.setMinimumSize(new java.awt.Dimension(100, 20));
        txtResRoom.setPreferredSize(new java.awt.Dimension(100, 20));
        txtResRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtResRoomActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 6);
        jPanel6.add(txtResRoom, gridBagConstraints);

        btnDelRes.setText("Delete");
        btnDelRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelResActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel6.add(btnDelRes, gridBagConstraints);

        btnAddRes.setText("Add");
        btnAddRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddResActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel6.add(btnAddRes, gridBagConstraints);

        jScrollPane2.setMinimumSize(new java.awt.Dimension(450, 80));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(450, 80));

        tblRes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblRes);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 11;
        jPanel6.add(jScrollPane2, gridBagConstraints);

        btnSubmitRes.setText("Submit");
        btnSubmitRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitResActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel6.add(btnSubmitRes, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jPanel6, gridBagConstraints);

        jPanel8.setMinimumSize(new java.awt.Dimension(550, 200));
        jPanel8.setPreferredSize(new java.awt.Dimension(550, 200));
        jPanel8.setLayout(new java.awt.GridBagLayout());

        cbbSer.setMinimumSize(new java.awt.Dimension(120, 20));
        cbbSer.setPreferredSize(new java.awt.Dimension(120, 20));
        cbbSer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSerActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 0);
        jPanel8.add(cbbSer, gridBagConstraints);

        lblCustomer3.setText("Room:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel8.add(lblCustomer3, gridBagConstraints);

        lblCustomer4.setText("Service:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel8.add(lblCustomer4, gridBagConstraints);

        cbbRoom.setMinimumSize(new java.awt.Dimension(90, 20));
        cbbRoom.setPreferredSize(new java.awt.Dimension(90, 20));
        cbbRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbRoomActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 0);
        jPanel8.add(cbbRoom, gridBagConstraints);

        jLabel6.setText("Quantity");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 6);
        jPanel8.add(jLabel6, gridBagConstraints);

        txtSerQuan.setMinimumSize(new java.awt.Dimension(100, 20));
        txtSerQuan.setPreferredSize(new java.awt.Dimension(100, 20));
        txtSerQuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSerQuanActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 6);
        jPanel8.add(txtSerQuan, gridBagConstraints);

        bnDelSer.setText("Delete");
        bnDelSer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnDelSerActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel8.add(bnDelSer, gridBagConstraints);

        btnAddSer.setText("Add");
        btnAddSer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSerActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel8.add(btnAddSer, gridBagConstraints);

        jScrollPane3.setMinimumSize(new java.awt.Dimension(450, 80));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(450, 80));

        tblSer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tblSer);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 11;
        jPanel8.add(jScrollPane3, gridBagConstraints);

        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel8.add(btnSubmit, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jPanel8, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(jPanel1, gridBagConstraints);

        jButton2.setText("Confirm");
        jButton2.setMinimumSize(new java.awt.Dimension(100, 25));
        jButton2.setPreferredSize(new java.awt.Dimension(100, 25));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 12, 100);
        getContentPane().add(jButton2, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbbCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbCustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbCustomerActionPerformed

    private void txtNuAdultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNuAdultActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNuAdultActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        RoomForm cf = RoomForm.getIns();

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
            cf.loadData();
        }
        InternalMain.dtpProgram.getDesktopManager().maximizeFrame(cf);
        cf.show();
        cf.loadData();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:. RoomForm cf = RoomForm.getIns();
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton1MouseClicked

    void clearDetails() {

        txtNuAdult.setText("");
        txtNumChil.setText("");
        txtResRoom.setText("");

        txtSerQuan.setText("");
        txtTraTruoc.setText("");

        cbbRoom.removeAllItems();
        cbbResRoom.removeAllItems();
        cbbProduct.removeAllItems();
        cbbSer.removeAllItems();

        dlm.clear();
        RoomList.setModel(dlm);

        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }

        while (dtm1.getRowCount() > 0) {
            dtm1.removeRow(0);
        }
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //Create bill

        BookingRoom bk = new BookingRoom();
        bk.setAccounts(EmployeeEntityManager.currentEmployee);
        bk.setCustomers(cusModel.find(cbbCustomer.getSelectedItem().toString()));
        bk.setAdvance(BigDecimal.valueOf(Double.valueOf(txtTraTruoc.getText())));
        bk.setDateIn(FromDate.getDate());
        bk.setDateOut(ToDate.getDate());
        bk.setNumOfAdult(Integer.valueOf(txtNuAdult.getText()));
        bk.setNumOfChildren(Integer.valueOf(txtNumChil.getText()));

        //Create booked list
        if (bookingModel.addNew(bk)) {
            try {
                if (cbGetRoom.isSelected()) {
                    for (Rooms i : RoomForm.selectedList) {
                        BookedRooms b = new BookedRooms();
                        b.setRooms(i);
                        b.setBookingRoom(bk);
                        b.setIsActive(true);
                        bookedModel.insert(b);
                        //change room status
                        i.setStatus(2);
                        roomModel.edit(i);
                    }
                    ReportManager rpDAO = new ReportManager();
                    int re = JOptionPane.showConfirmDialog(rootPane, "Complete! Print Bill? ", "Quesiton", JOptionPane.YES_NO_CANCEL_OPTION);

                    if (re == JOptionPane.YES_OPTION) {
                        String billId = bk.getId().toString();
                        Map<String, Object> param = new HashMap<String, Object>();
                        param.put("ID", billId);
                        rpDAO.reportCheckinHotel(param);
                    }

                    clearDetails();
                    RoomForm.clearSelectedList();
                } else {
                    for (Rooms i : RoomForm.selectedList) {
                        BookedRooms b = new BookedRooms();
                        b.setRooms(i);
                        b.setBookingRoom(bk);
                        b.setIsActive(true);
                        bookedModel.insert(b);
                        //change room status
                        i.setStatus(3);
                        roomModel.edit(i);
                    }
                    JOptionPane.showMessageDialog(rootPane, "Successfully", "Success", 1);

                    clearDetails();
                    RoomForm.clearSelectedList();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error in payment: " + ex.getMessage(), "Failed ", JOptionPane.ERROR_MESSAGE);
                bookingModel.delete(bk);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Can not booking.", "Failed", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbbProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbProductActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbProductActionPerformed

    private void cbbResRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbResRoomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbResRoomActionPerformed

    private void txtResRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtResRoomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtResRoomActionPerformed

    private void cbbSerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSerActionPerformed

    private void cbbRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbRoomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbRoomActionPerformed

    private void txtSerQuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSerQuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSerQuanActionPerformed

    private void btnAddSerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSerActionPerformed
        // TODO add your handling code here:

        dtm.addRow(new Object[]{
            cbbRoom.getSelectedItem(),
            cbbSer.getSelectedItem(),
            txtSerQuan.getText()
        });

//        
    }//GEN-LAST:event_btnAddSerActionPerformed

    private void btnAddResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddResActionPerformed
        // TODO add your handling code here:

        dtm1.addRow(new Object[]{
            cbbResRoom.getSelectedItem(),
            cbbProduct.getSelectedItem(),
            txtResRoom.getText()
        });

    }//GEN-LAST:event_btnAddResActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:

        int re = JOptionPane.showConfirmDialog(pnlCustomer, "Are you sure ?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (re == JOptionPane.YES_OPTION) {
            for (int i = 0; i < tblSer.getRowCount(); i++) {
                HotelOrderService orderSer = new HotelOrderService();
                orderSer.setQuantity(Integer.valueOf(tblSer.getValueAt(i, 2).toString()));
                orderSer.setRooms(roomModel.find(tblSer.getValueAt(i, 0).toString()));
                orderSer.setServices(serModel.find(tblSer.getValueAt(i, 1).toString()));
                orderSer.setIsActive(true);
                hs.addNew(orderSer);
            }
            JOptionPane.showMessageDialog(pnlCustomer, "Completed", "Successfully", 1);
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnSubmitResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitResActionPerformed
        // TODO add your handling code here:

        int re = JOptionPane.showConfirmDialog(pnlCustomer, "Are you sure ?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (re == JOptionPane.YES_OPTION) {
            for (int i = 0; i < tblRes.getRowCount(); i++) {
                HotelOrderDish orderSer = new HotelOrderDish();
                orderSer.setQuantity(Integer.valueOf(tblRes.getValueAt(i, 2).toString()));
                orderSer.setRooms(roomModel.find(tblRes.getValueAt(i, 0).toString()));
                orderSer.setProducts(proModel.find(tblRes.getValueAt(i, 1).toString()));
                orderSer.setIsActive(true);
                hd.addNew(orderSer);
            }
            JOptionPane.showMessageDialog(pnlCustomer, "Completed", "Successfully", 1);
        }
    }//GEN-LAST:event_btnSubmitResActionPerformed

    private void bnDelSerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnDelSerActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblSer.getSelectedRow();
        ((DefaultTableModel) tblSer.getModel()).removeRow(selectedRow);

    }//GEN-LAST:event_bnDelSerActionPerformed

    private void btnDelResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelResActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblRes.getSelectedRow();
        ((DefaultTableModel) tblRes.getModel()).removeRow(selectedRow);

    }//GEN-LAST:event_btnDelResActionPerformed

    private void FromDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FromDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FromDateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXDatePicker FromDate;
    private javax.swing.JList RoomList;
    private org.jdesktop.swingx.JXDatePicker ToDate;
    private javax.swing.JButton bnDelSer;
    private javax.swing.JButton btnAddRes;
    private javax.swing.JButton btnAddSer;
    private javax.swing.JButton btnDelRes;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton btnSubmitRes;
    private javax.swing.JCheckBox cbGetRoom;
    private javax.swing.JComboBox cbbCustomer;
    private javax.swing.JComboBox cbbProduct;
    private javax.swing.JComboBox cbbResRoom;
    private javax.swing.JComboBox cbbRoom;
    private javax.swing.JComboBox cbbSer;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private javax.swing.JLabel lblCustomer;
    private javax.swing.JLabel lblCustomer1;
    private javax.swing.JLabel lblCustomer2;
    private javax.swing.JLabel lblCustomer3;
    private javax.swing.JLabel lblCustomer4;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblNuChild;
    private javax.swing.JLabel lblSeller;
    private javax.swing.JLabel lblWarn;
    private javax.swing.JLabel lblWarn1;
    private javax.swing.JLabel lblWarn2;
    private javax.swing.JLabel lbltemp;
    private javax.swing.JPanel pnlCustomer;
    private javax.swing.JPanel pnlDate;
    private javax.swing.JPanel pnlSeller;
    private javax.swing.JTable tblRes;
    private javax.swing.JTable tblSer;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtNuAdult;
    private javax.swing.JTextField txtNumChil;
    private javax.swing.JTextField txtResRoom;
    private javax.swing.JTextField txtSeller;
    private javax.swing.JTextField txtSerQuan;
    private javax.swing.JTextField txtTraTruoc;
    // End of variables declaration//GEN-END:variables
}
