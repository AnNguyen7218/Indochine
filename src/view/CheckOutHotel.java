/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.BookingRoom;
import entities.Customers;
import entities.HotelOrderDish;
import entities.HotelOrderService;
import entities.Products;
import entities.Rooms;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import models.CustomerEntityManager;
import models.HotelDishEntityManager;
import models.HotelSerEntityManager;
import models.OrderEntityManager;
import models.RoomEntityManager;
import utils.Converter;

/**
 *
 * @author Ashley
 */
public class CheckOutHotel extends javax.swing.JInternalFrame {

    static CheckOutHotel instance;
    RoomEntityManager roomModel = new RoomEntityManager();
    HotelDishEntityManager hdModel = new HotelDishEntityManager();
    HotelSerEntityManager hsModel = new HotelSerEntityManager();
    CustomerEntityManager cusModel = new CustomerEntityManager();
    OrderEntityManager bookingModel = new OrderEntityManager();

    Rooms selectedRoom = new Rooms();

    Double serTotal, resTotal, vat, finalTot;

    /**
     * Creates new form CheckOutHotel
     */
    final String searchTextPlaceHolder = "Enter keyword...";

    public CheckOutHotel() {
        initComponents();
        loadProductListToTable(roomModel.getAllBookedRoom());

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

        tblProduct.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedIndex = tblProduct.getSelectedRow();
                if (selectedIndex != -1) {
                    selectedRoom = roomModel.find(tblProduct.getValueAt(selectedIndex, 1).toString());
                }
                loadDetailsSerListToTable(hsModel.getByRoomID(selectedRoom.getRoomId()));
                loadDetailsResListToTable(hdModel.getByRoomId(selectedRoom.getRoomId()));
                updateLabel();
                filterCustomerInfo(selectedRoom);
            }
        });
    }

    void updateLabel(){
        lblResTot.setText(resTotal.toString());
        lblSerTot.setText(serTotal.toString());
        double vat = (resTotal+serTotal)*0.1;
        lblVATTot.setText(String.valueOf(vat));
        double finalT = (resTotal+serTotal+vat) ;
        lblTotAll.setText(String.valueOf(finalT));
        
    }
    void filterCustomerInfo(Rooms r) {
        BookingRoom bk = bookingModel.find(r);
        Customers currentCustomer = cusModel.find(bk.getCustomers().getCustomerId());

        txtAdd.setText(currentCustomer.getCustomerAddress());
        txtIDCard.setText(currentCustomer.getIdentityCard());
        txtName.setText(currentCustomer.getCustomerName());

        txtDateTo.setText(Converter.dateToString(bk.getDateOut()));
        txtFrom.setText(Converter.dateToString(bk.getDateIn()));
        txtPaid.setText(bk.getAdvance().toString());
    }

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

    public static CheckOutHotel getIns() {
        if (instance == null) {
            instance = new CheckOutHotel();
        }
        return instance;

    }

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

    void changeModel(JTable table) {
        DefaultTableModel currentModel = (DefaultTableModel) table.getModel();
        currentModel.fireTableStructureChanged();
    }

    //load bang dich vu cua phong
    void loadDetailsSerListToTable(List<HotelOrderService> list) {
        changeModel(tblSer);
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

        serTotal = 0.0;
        for (HotelOrderService pro : list) {
            dtm1.addRow(new Object[]{
                pro.getId(),
                pro.getServices().getServiceName(),
                pro.getQuantity(),
                pro.getServices().getPrice().doubleValue() * pro.getQuantity()

            });
            serTotal += pro.getServices().getPrice().doubleValue() * pro.getQuantity();
        }

        //Adjust column width
        tblSer.setModel(dtm1);
        tblSer.getColumn("ID").setMinWidth(0);
        tblSer.getColumn("ID").setMaxWidth(0);
    }

    //load bang res cua phong
    void loadDetailsResListToTable(List<HotelOrderDish> list) {
        changeModel(tblRes);
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
        resTotal = 0.0;
        for (HotelOrderDish pro : list) {
            dtm1.addRow(new Object[]{
                pro.getId(),
                pro.getProducts().getProductName(),
                pro.getQuantity(),
                pro.getProducts().getPrice().doubleValue() * pro.getQuantity()

            });
            resTotal += pro.getProducts().getPrice().doubleValue() * pro.getQuantity();
        }

        //Adjust column width
        tblRes.setModel(dtm1);
        tblRes.getColumn("ID").setMinWidth(0);
        tblRes.getColumn("ID").setMaxWidth(0);
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

        view = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDateTo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtAdd = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPaid = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtFrom = new javax.swing.JTextField();
        txtIDCard = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSer = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRes = new javax.swing.JTable();
        pnlProduct = new javax.swing.JPanel();
        scrProduct = new javax.swing.JScrollPane();
        tblProduct = new javax.swing.JTable();
        pnlSearch = new javax.swing.JPanel();
        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblSerTot = new javax.swing.JLabel();
        lblVATTot = new javax.swing.JLabel();
        lblTotAll = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblResTot = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JButton();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        view.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "View Details"));
        view.setMinimumSize(new java.awt.Dimension(600, 610));
        view.setPreferredSize(new java.awt.Dimension(600, 610));
        view.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Customer information"));
        jPanel1.setMinimumSize(new java.awt.Dimension(500, 150));
        jPanel1.setName(""); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 150));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Customer Name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(jLabel1, gridBagConstraints);

        txtName.setMinimumSize(new java.awt.Dimension(120, 20));
        txtName.setPreferredSize(new java.awt.Dimension(120, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(txtName, gridBagConstraints);

        jLabel2.setText("To:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(jLabel2, gridBagConstraints);

        txtDateTo.setMinimumSize(new java.awt.Dimension(120, 20));
        txtDateTo.setPreferredSize(new java.awt.Dimension(120, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(txtDateTo, gridBagConstraints);

        jLabel3.setText("Customer Address:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(jLabel3, gridBagConstraints);

        txtAdd.setMinimumSize(new java.awt.Dimension(120, 20));
        txtAdd.setPreferredSize(new java.awt.Dimension(120, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(txtAdd, gridBagConstraints);

        jLabel4.setText("Paid:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(jLabel4, gridBagConstraints);

        txtPaid.setMinimumSize(new java.awt.Dimension(120, 20));
        txtPaid.setPreferredSize(new java.awt.Dimension(120, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(txtPaid, gridBagConstraints);

        jLabel5.setText("From:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(jLabel5, gridBagConstraints);

        txtFrom.setMinimumSize(new java.awt.Dimension(120, 20));
        txtFrom.setPreferredSize(new java.awt.Dimension(120, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(txtFrom, gridBagConstraints);

        txtIDCard.setMinimumSize(new java.awt.Dimension(120, 20));
        txtIDCard.setPreferredSize(new java.awt.Dimension(120, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(txtIDCard, gridBagConstraints);

        jLabel6.setText("Idenity Card:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(jLabel6, gridBagConstraints);

        view.add(jPanel1, new java.awt.GridBagConstraints());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Service Information"));
        jPanel2.setToolTipText("");
        jPanel2.setMinimumSize(new java.awt.Dimension(500, 180));
        jPanel2.setName(""); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(500, 180));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(450, 150));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(450, 150));
        jScrollPane1.setViewportView(tblSer);

        jPanel2.add(jScrollPane1, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        view.add(jPanel2, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Restaurant Information"));
        jPanel3.setMinimumSize(new java.awt.Dimension(500, 180));
        jPanel3.setName(""); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(500, 180));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setMinimumSize(new java.awt.Dimension(450, 150));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(450, 150));
        jScrollPane2.setViewportView(tblRes);

        jPanel3.add(jScrollPane2, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        view.add(jPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        getContentPane().add(view, gridBagConstraints);

        pnlProduct.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Room"));
        pnlProduct.setMinimumSize(new java.awt.Dimension(250, 610));
        pnlProduct.setPreferredSize(new java.awt.Dimension(250, 610));
        pnlProduct.setLayout(new java.awt.GridBagLayout());

        scrProduct.setMinimumSize(new java.awt.Dimension(230, 250));
        scrProduct.setName(""); // NOI18N
        scrProduct.setPreferredSize(new java.awt.Dimension(230, 250));

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

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel4.setMinimumSize(new java.awt.Dimension(230, 200));
        jPanel4.setPreferredSize(new java.awt.Dimension(230, 200));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setMinimumSize(new java.awt.Dimension(150, 5));
        jSeparator1.setPreferredSize(new java.awt.Dimension(150, 5));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        jPanel4.add(jSeparator1, gridBagConstraints);

        jLabel7.setText("Service total:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        jPanel4.add(jLabel7, gridBagConstraints);

        jLabel8.setText("V.A.T (10%):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        jPanel4.add(jLabel8, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("TOTAL:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        jPanel4.add(jLabel9, gridBagConstraints);

        lblSerTot.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        jPanel4.add(lblSerTot, gridBagConstraints);

        lblVATTot.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        jPanel4.add(lblVATTot, gridBagConstraints);

        lblTotAll.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        jPanel4.add(lblTotAll, gridBagConstraints);

        jLabel13.setText("Restaurant total:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel4.add(jLabel13, gridBagConstraints);

        lblResTot.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        jPanel4.add(lblResTot, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlProduct.add(jPanel4, gridBagConstraints);

        btnSubmit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSubmit.setLabel("Submit");
        btnSubmit.setMaximumSize(new java.awt.Dimension(100, 25));
        btnSubmit.setMinimumSize(new java.awt.Dimension(100, 25));
        btnSubmit.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        pnlProduct.add(btnSubmit, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblResTot;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblSerTot;
    private javax.swing.JLabel lblTotAll;
    private javax.swing.JLabel lblVATTot;
    private javax.swing.JPanel pnlProduct;
    private javax.swing.JPanel pnlSearch;
    private javax.swing.JScrollPane scrProduct;
    private javax.swing.JTable tblProduct;
    private javax.swing.JTable tblRes;
    private javax.swing.JTable tblSer;
    private javax.swing.JTextField txtAdd;
    private javax.swing.JTextField txtDateTo;
    private javax.swing.JTextField txtFrom;
    private javax.swing.JTextField txtIDCard;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPaid;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JPanel view;
    // End of variables declaration//GEN-END:variables
}
