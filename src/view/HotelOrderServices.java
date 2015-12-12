/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.HotelOrderDish;
import entities.HotelOrderService;
import entities.Products;
import entities.RestaurantTable;
import entities.Rooms;
import entities.Services;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import models.HotelDishEntityManager;
import models.HotelSerEntityManager;
import models.ProductEntityManager;
import models.RoomEntityManager;
import models.SerEntityManager;

/**
 *
 * @author Ashley
 */
public class HotelOrderServices extends javax.swing.JInternalFrame {

    /**
     * Creates new form HotelOrderServices
     */
    static HotelOrderServices instance = null;
    RoomEntityManager roomModel = new RoomEntityManager();
    ProductEntityManager promodel = new ProductEntityManager();
    HotelSerEntityManager hsModel = new HotelSerEntityManager();
    HotelDishEntityManager hdModel = new HotelDishEntityManager();
    SerEntityManager serModel = new SerEntityManager();
//    int selectedDefIndex ;
//    int selectedDetailsInx ;
    Services s = new Services();
    Products p = new Products();
    Rooms selectedRoom = new Rooms();
    HotelOrderService hsObj = new HotelOrderService();
    HotelOrderDish hdObj = new HotelOrderDish();
    String searchTextPlaceHolder = "Type room id";

    public HotelOrderServices() {
        initComponents();
        loadProductListToTable(roomModel.getAllBookedRoom());

        cbbType.addItem("Services");
        cbbType.addItem("Restaurant");

        cbbType.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbbType.getSelectedItem().equals("Restaurant")) {
                    loadDishistToTable(promodel.getAllFromDB());
                    loadResListStructure();
                    tblProduct.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

                        @Override
                        public void valueChanged(ListSelectionEvent e) {
                            int selectedIndex = tblProduct.getSelectedRow();
                            if (selectedIndex != -1) {

                                selectedRoom = roomModel.find(tblProduct.getValueAt(selectedIndex, 1).toString());

                            }
                            if (cbbType.getSelectedItem().equals("Restaurant")) {
                                loadDetailsResListToTable(hdModel.getByRoomId(selectedRoom.getRoomId()));
                            } else {
                                loadDetailsSerListToTable(hsModel.getByRoomID(selectedRoom.getRoomId()));
                            }
                        }
                    });

                    tblDefault.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

                        @Override
                        public void valueChanged(ListSelectionEvent e) {
                            int selectedDefIndex = tblDefault.getSelectedRow();
                            if (selectedDefIndex < 0) {
                                System.out.println("zzzzzzzz");
                                return;
                            }
                            if (cbbType.getSelectedItem().equals("Restaurant")) {
                                hdObj = hdModel.find(tblDefault.getValueAt(selectedDefIndex, 0));
                            } else {
                                hsObj = hsModel.find(tblDefault.getValueAt(selectedDefIndex, 0));
                            }
                        }
                    });

                    tblDetails.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

                        @Override
                        public void valueChanged(ListSelectionEvent e) {
                            int selectedDetailsInx = tblDetails.getSelectedRow();
                            if (selectedDetailsInx < 0) {
                                System.out.println("zzz");
                                return;
                            }
                            if (cbbType.getSelectedItem().equals("Restaurant")) {
                                p = promodel.find(tblDetails.getValueAt(selectedDetailsInx, 0));
                            } else {
                                s = serModel.find(tblDetails.getValueAt(selectedDetailsInx, 0));
                            }
                        }
                    });
                } else {
                    loadSerListStructure();
                    loadSerListToTable(serModel.getAllFromDB());
                    tblProduct.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

                        @Override
                        public void valueChanged(ListSelectionEvent e) {
                            int selectedIndex = tblProduct.getSelectedRow();
                            if (selectedIndex != -1) {

                                selectedRoom = roomModel.find(tblProduct.getValueAt(selectedIndex, 1).toString());

                            }
                            if (cbbType.getSelectedItem().equals("Restaurant")) {
                                loadDetailsResListToTable(hdModel.getByRoomId(selectedRoom.getRoomId()));
                            } else {
                                loadDetailsSerListToTable(hsModel.getByRoomID(selectedRoom.getRoomId()));
                            }
                        }
                    });

                    tblDefault.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

                        @Override
                        public void valueChanged(ListSelectionEvent e) {
                            int selectedDefIndex = tblDefault.getSelectedRow();
                            if (selectedDefIndex < 0) {
                                System.out.println("zzzzzzz");
                                return;
                            }
                            if (cbbType.getSelectedItem().equals("Restaurant")) {
                                hdObj = hdModel.find(tblDefault.getValueAt(selectedDefIndex, 0));
                            } else {
                                hsObj = hsModel.find(tblDefault.getValueAt(selectedDefIndex, 0));
                            }
                        }
                    });

                    tblDetails.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

                        @Override
                        public void valueChanged(ListSelectionEvent e) {
                            int selectedDetailsInx = tblDetails.getSelectedRow();
                             if (selectedDetailsInx < 0) {
                                System.out.println("zzzzzzz");
                                return;
                            }
                            if (cbbType.getSelectedItem().equals("Restaurant")) {
                                p = promodel.find(tblDetails.getValueAt(selectedDetailsInx, 0));
                            } else {
                                s = serModel.find(tblDetails.getValueAt(selectedDetailsInx, 0));
                            }
                        }
                    });
                }

            }

        });

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

    }
//search phong

    void filterProductList() {
        String searchText = txtSearch.getText().toLowerCase();
        if (searchText.isEmpty() || searchText.equalsIgnoreCase(searchTextPlaceHolder)) {
            return;
        }
        List<Rooms> l = new ArrayList<>();
        for (Rooms ins : roomModel.getAll()) {
            if (ins.getRoomName().toLowerCase().contains(searchText)) {

                l.add(ins);
            }
        }
        loadProductListToTable(l);
    }

    public static HotelOrderServices getIns() {
        if (instance == null) {
            instance = new HotelOrderServices();
        }
        return instance;

    }

    //load ds phong
    void loadProductListToTable(List<Rooms> list) {

        //Disable inline editing in the table
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };

        //Populate the data
        dtm.addColumn("ID");
        dtm.addColumn("RoomName");

        for (Rooms pro : list) {
            dtm.addRow(new Object[]{
                pro.getRoomId(),
                pro.getRoomName()
            });

        }

        //Adjust column width
        tblProduct.setModel(dtm);
//        tblProduct.getColumn("ID").setMaxWidth(36);
        tblProduct.getColumn("ID").setMinWidth(0);
        tblProduct.getColumn("ID").setMaxWidth(0);
    }
//kho

    void loadSerListToTable(List<Services> list) {
        changeModel(tblDetails);
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
        tblDetails.setModel(dtm);

//        tblProduct.getColumn("ID").setMaxWidth(36);
        tblDetails.getColumn("ID").setMinWidth(0);
        tblDetails.getColumn("ID").setMaxWidth(0);

    }

    //kho
    void loadDishistToTable(List<Products> list) {
        changeModel(tblDetails);
        //Disable inline editing in the table
        DefaultTableModel dtm1 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };

        //Populate the data
        dtm1.addColumn("ID");
        dtm1.addColumn("Name");
        dtm1.addColumn("Price");

        for (Products pro : list) {
            dtm1.addRow(new Object[]{
                pro.getProductId(),
                pro.getProductName(),
                pro.getPrice()

            });

        }

        //Adjust column width
        tblDetails.setModel(dtm1);
        tblDetails.getColumn("ID").setMinWidth(0);
        tblDetails.getColumn("ID").setMaxWidth(0);
    }

    void changeModel(JTable table) {
        DefaultTableModel currentModel = (DefaultTableModel) table.getModel();
        currentModel.fireTableStructureChanged();

    }

    void loadSerListStructure() {
        changeModel(tblDefault);
        //Disable inline editing in the table
        DefaultTableModel dtm1 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };

        //Populate the data
        dtm1.addColumn("ID");
        dtm1.addColumn("Service");
        dtm1.addColumn("Quantity");
        dtm1.addColumn("Price");

        tblDefault.setModel(dtm1);
        tblDefault.getColumn("ID").setMinWidth(0);
        tblDefault.getColumn("ID").setMaxWidth(0);
    }

    //load bang dich vu cua phong
    void loadDetailsSerListToTable(List<HotelOrderService> list) {
        changeModel(tblDefault);
        //Disable inline editing in the table
        DefaultTableModel dtm1 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };

        //Populate the data
        dtm1.addColumn("ID");
        dtm1.addColumn("Service");
        dtm1.addColumn("Quantity");
        dtm1.addColumn("Price");

        for (HotelOrderService pro : list) {
            dtm1.addRow(new Object[]{
                pro.getId(),
                pro.getServices().getServiceName(),
                pro.getQuantity(),
                pro.getServices().getPrice().doubleValue() * pro.getQuantity()

            });

        }

        //Adjust column width
        tblDefault.setModel(dtm1);
        tblDefault.getColumn("ID").setMinWidth(0);
        tblDefault.getColumn("ID").setMaxWidth(0);
    }

    void loadResListStructure() {
        changeModel(tblDefault);
        //Disable inline editing in the table
        DefaultTableModel dtm1 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };

        //Populate the data
        dtm1.addColumn("ID");
        dtm1.addColumn("Product");
        dtm1.addColumn("Quantity");
        dtm1.addColumn("Price");

        tblDefault.setModel(dtm1);
        tblDefault.getColumn("ID").setMinWidth(0);
        tblDefault.getColumn("ID").setMaxWidth(0);

    }

    //load bang res cua phong

    void loadDetailsResListToTable(List<HotelOrderDish> list) {
        changeModel(tblDefault);
        //Disable inline editing in the table
        DefaultTableModel dtm1 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };

        //Populate the data
        dtm1.addColumn("ID");
        dtm1.addColumn("Product");
        dtm1.addColumn("Quantity");
        dtm1.addColumn("Price");

        for (HotelOrderDish pro : list) {
            dtm1.addRow(new Object[]{
                pro.getId(),
                pro.getProducts().getProductName(),
                pro.getQuantity(),
                pro.getProducts().getPrice().doubleValue() * pro.getQuantity()

            });

        }

        //Adjust column width
        tblDefault.setModel(dtm1);
        tblDefault.getColumn("ID").setMinWidth(0);
        tblDefault.getColumn("ID").setMaxWidth(0);
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

        pnlProduct = new javax.swing.JPanel();
        scrProduct = new javax.swing.JScrollPane();
        tblProduct = new javax.swing.JTable();
        pnlSearch = new javax.swing.JPanel();
        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        pnlNav = new javax.swing.JPanel();
        cbbType = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtQuan = new javax.swing.JTextField();
        pnlShowDetails = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDefault = new javax.swing.JTable();
        pnlDefaultList = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDetails = new javax.swing.JTable();

        setTitle("Order From Hotel");
        setMaximumSize(new java.awt.Dimension(100, 25));
        setMinimumSize(new java.awt.Dimension(100, 25));
        setPreferredSize(new java.awt.Dimension(100, 25));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        pnlProduct.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Room"));
        pnlProduct.setMinimumSize(new java.awt.Dimension(200, 400));
        pnlProduct.setPreferredSize(new java.awt.Dimension(200, 400));
        pnlProduct.setLayout(new java.awt.GridBagLayout());

        scrProduct.setMinimumSize(new java.awt.Dimension(150, 310));
        scrProduct.setPreferredSize(new java.awt.Dimension(150, 310));

        tblProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblProduct.setAutoscrolls(false);
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
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(pnlProduct, gridBagConstraints);

        pnlNav.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Navigator"));
        pnlNav.setMinimumSize(new java.awt.Dimension(170, 400));
        pnlNav.setPreferredSize(new java.awt.Dimension(170, 400));
        pnlNav.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        pnlNav.add(cbbType, gridBagConstraints);

        jLabel1.setText("Type:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        pnlNav.add(jLabel1, gridBagConstraints);

        btnAdd.setText("Add Item");
        btnAdd.setMaximumSize(new java.awt.Dimension(100, 25));
        btnAdd.setMinimumSize(new java.awt.Dimension(100, 25));
        btnAdd.setPreferredSize(new java.awt.Dimension(100, 25));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        pnlNav.add(btnAdd, gridBagConstraints);

        btnEdit.setText("Edit Details");
        btnEdit.setMaximumSize(new java.awt.Dimension(100, 25));
        btnEdit.setMinimumSize(new java.awt.Dimension(100, 25));
        btnEdit.setPreferredSize(new java.awt.Dimension(100, 25));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        pnlNav.add(btnEdit, gridBagConstraints);

        btnDel.setText("Delete Details");
        btnDel.setMaximumSize(new java.awt.Dimension(100, 25));
        btnDel.setMinimumSize(new java.awt.Dimension(100, 25));
        btnDel.setPreferredSize(new java.awt.Dimension(100, 25));
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        pnlNav.add(btnDel, gridBagConstraints);

        jLabel2.setText("Quantity:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 6);
        pnlNav.add(jLabel2, gridBagConstraints);

        txtQuan.setMinimumSize(new java.awt.Dimension(50, 20));
        txtQuan.setPreferredSize(new java.awt.Dimension(50, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 6);
        pnlNav.add(txtQuan, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(pnlNav, gridBagConstraints);

        pnlShowDetails.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Details"));
        pnlShowDetails.setMinimumSize(new java.awt.Dimension(300, 400));
        pnlShowDetails.setPreferredSize(new java.awt.Dimension(300, 400));
        pnlShowDetails.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(270, 350));
        jScrollPane1.setName(""); // NOI18N
        jScrollPane1.setPreferredSize(new java.awt.Dimension(270, 350));

        tblDefault.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblDefault);

        pnlShowDetails.add(jScrollPane1, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(pnlShowDetails, gridBagConstraints);

        pnlDefaultList.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Default"));
        pnlDefaultList.setMinimumSize(new java.awt.Dimension(250, 400));
        pnlDefaultList.setPreferredSize(new java.awt.Dimension(250, 400));
        pnlDefaultList.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setMinimumSize(new java.awt.Dimension(230, 350));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(230, 350));

        tblDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblDetails);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(6, 4, 6, 4);
        pnlDefaultList.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(pnlDefaultList, gridBagConstraints);

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

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        if (cbbType.getSelectedItem().equals("Restaurant")) {
            hdObj.setQuantity(Integer.valueOf(txtQuan.getText()));

            if (hdModel.edit(hdObj)) {

                //chỗ ni nò
                loadDetailsResListToTable(hdModel.getByRoomId(selectedRoom.getRoomId()));
                JOptionPane.showMessageDialog(rootPane, "Edited", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            hsObj.setQuantity(Integer.valueOf(txtQuan.getText()));
            if (hsModel.edit(hsObj)) {

                loadDetailsSerListToTable(hsModel.getByRoomID(selectedRoom.getRoomId()));
                JOptionPane.showMessageDialog(rootPane, "Edited", "Success", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        // TODO add your handling code here:
        if (cbbType.getSelectedItem().equals("Restaurant")) {
            //hdObj.setQuantity(Integer.valueOf(tblDefault.getValueAt(selectedDefIndex, 2).toString()));
            if (hdModel.delete(hdObj)) {
                loadDetailsResListToTable(hdModel.getByRoomId(selectedRoom.getRoomId()));
                JOptionPane.showMessageDialog(rootPane, "Deleted", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            //hsObj.setQuantity(Integer.valueOf(tblDefault.getValueAt(selectedDefIndex, 2).toString()));
            if (hsModel.delete(hsObj)) {

                loadDetailsSerListToTable(hsModel.getByRoomID(selectedRoom.getRoomId()));
                JOptionPane.showMessageDialog(rootPane, "Deleted", "Success", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }//GEN-LAST:event_btnDelActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        if (cbbType.getSelectedItem().equals("Restaurant")) {
            HotelOrderDish orderObj = new HotelOrderDish();
            orderObj.setProducts(p);
            orderObj.setQuantity(1);
            orderObj.setRooms(selectedRoom);
            if (hdModel.addNew(orderObj)) {
                loadDetailsResListToTable(hdModel.getByRoomId(selectedRoom.getRoomId()));
                JOptionPane.showMessageDialog(rootPane, "Added", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            HotelOrderService orderObj = new HotelOrderService();
            orderObj.setServices(s);
            orderObj.setRooms(selectedRoom);
            orderObj.setQuantity(1);

            if (hsModel.addNew(orderObj)) {

                loadDetailsSerListToTable(hsModel.getByRoomID(selectedRoom.getRoomId()));
                JOptionPane.showMessageDialog(rootPane, "Added", "Success", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }//GEN-LAST:event_btnAddActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnEdit;
    private javax.swing.JComboBox cbbType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JPanel pnlDefaultList;
    private javax.swing.JPanel pnlNav;
    private javax.swing.JPanel pnlProduct;
    private javax.swing.JPanel pnlSearch;
    private javax.swing.JPanel pnlShowDetails;
    private javax.swing.JScrollPane scrProduct;
    private javax.swing.JTable tblDefault;
    private javax.swing.JTable tblDetails;
    private javax.swing.JTable tblProduct;
    private javax.swing.JTextField txtQuan;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
