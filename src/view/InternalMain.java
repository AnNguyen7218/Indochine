/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.Accounts;

import entities.Functions;

import java.awt.Dimension;
import java.awt.Font;

import java.beans.PropertyVetoException;
import java.net.URL;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import models.EmployeeEntityManager;
import models.ActionEntityManager;
import models.ProductEntityManager;
import models.RestaurantBillEntityManager;
import models.RoleEntityManager;
import models.RoomEntityManager;
import models.SerEntityManager;
import utils.Converter;

/**
 *
 * @author ADMIN
 */
public final class InternalMain extends javax.swing.JFrame {

    ProductEntityManager proModel = new ProductEntityManager();
    EmployeeEntityManager empModel = new EmployeeEntityManager();
    RestaurantBillEntityManager resBillModel = new RestaurantBillEntityManager();

    /**
     * Creates new form InternalMain
     */
    public InternalMain(LoginForm login, Accounts a) {
        initComponents();
        clock();
        setIcon();
        setLocationRelativeTo(null);
        dtpProgram.setPreferredSize(new Dimension(1359, 612));
        lblUserName.setText(a.getAccountName());
        lblUserName.setFont(new Font("Tahoma", Font.BOLD, 11));
        HomeFormDB hf = new HomeFormDB();
        this.dtpProgram.add(hf);
        try {
            hf.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(InternalMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        hf.show();

        grantPermission();

    }

    public void clock() {
        Thread clock = new Thread() {
            public void run() {
                try {
                    while (true) {

                        lblClock.setText(Converter.dateToTime(new Date()));
                        sleep(1000);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(HomeFormDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        clock.start();
    }

    public void setIcon() {
        this.setIconImage(new ImageIcon(getClass().getResource("/image/logoTitle.png")).getImage());
    }

    /**
     * Display menu in admin role.
     */
    void grantPermission() {
        RoleEntityManager roleModel = new RoleEntityManager();
        ActionEntityManager funModel = new ActionEntityManager();
        Set<Functions> list = roleModel.getFunctionList(EmployeeEntityManager.currentEmployee.getRoles());

        //Manage account
        if (list.contains(funModel.find(ActionEntityManager.ACCOUNT_MANAGEMENT))) {
            mniUserManagement.setVisible(true);
        }
        //Give role
        if (list.contains(funModel.find(ActionEntityManager.ACCOUNT_PERMISSION))) {
            mniUserPermission.setVisible(true);
        }
        //Manage stock
        if (list.contains(funModel.find(ActionEntityManager.PRODUCT_MANAGEMENT))) {
            mniProductManagement.setVisible(true);
        }
        //View bills
        if (list.contains(funModel.find(ActionEntityManager.BILL_OVERVIEW))) {
            mniBill.setVisible(true);
        }
        //Manage supplier
        if (list.contains(funModel.find(ActionEntityManager.CUSTOMER_MANAGEMENT))) {
            mniCustomer.setVisible(true);
        }
        //Payment
        if (list.contains(funModel.find(ActionEntityManager.RES_CHECKOUT))) {
            mniPay.setVisible(true);
        }
        //Change pass for user
        if (list.contains(funModel.find(ActionEntityManager.CHANGE_PASSWORD))) {
            mniChange.setVisible(true);
        }

        //Manage category
        if (list.contains(funModel.find(ActionEntityManager.CATEGORY_MANAGEMENT))) {
            mniCategory.setVisible(true);
        }
        //statistics
        if (!list.contains(funModel.find(ActionEntityManager.REPORT))) {
            mnuReport.setVisible(false);
        }
        //Backup
        if (list.contains(funModel.find(ActionEntityManager.BACKUP))) {
            mniBackup.setVisible(true);
        }
        //Restore
        if (list.contains(funModel.find(ActionEntityManager.RESTORE))) {
            mniRestore.setVisible(true);
        }
        //View diary
        if (list.contains(funModel.find(ActionEntityManager.DIARY))) {
            mniDiary.setVisible(true);
        }

        //Res supplier
        if (list.contains(funModel.find(ActionEntityManager.RES_SUPPLIER))) {
            jMenuItem2.setVisible(true);
        }

        //Ser order
        if (list.contains(funModel.find(ActionEntityManager.SER_ORDER))) {
            jMenuItem7.setVisible(true);
        }

        //Table
        if (list.contains(funModel.find(ActionEntityManager.TABLE))) {
            jMenuItem8.setVisible(true);
        }

        //Room
        if (list.contains(funModel.find(ActionEntityManager.ROOM))) {
            jMenuItem6.setVisible(true);
        }
        //Hotel Check in
        if (list.contains(funModel.find(ActionEntityManager.HOTEL_CHECKIN))) {
            jMenuItem4.setVisible(true);
        }

//HOTEL_CHECKOUT 
        if (list.contains(funModel.find(ActionEntityManager.HOTEL_CHECKOUT))) {
            jMenuItem5.setVisible(true);
        }

//HOTEL_CUS
        if (list.contains(funModel.find(ActionEntityManager.HOTEL_CUS))) {
            jMenuItem3.setVisible(true);
        }

        if (list.contains(funModel.find(ActionEntityManager.HOTEL_ORDER_SER))) {
            jMenuItem9.setVisible(true);
        }
        showGrantedFunctions();
    }

    /**
     * Disable menu items
     */
    void showGrantedFunctions() {
        for (int j = 0; j < mnuCoffe.getMenuCount(); j++) {
            JMenu menu = mnuCoffe.getMenu(j);

            //Check if there 've menu item 
            if (menu.getItemCount() > 0) {
                int i = 0;
                for (; i < menu.getItemCount(); i++) {
                    JMenuItem item = menu.getItem(i);
                    if (item.isVisible()) {
                        break;
                    }
                }

                //Check if there havent menu item
                if (i == menu.getItemCount()) {
                    menu.setVisible(false);
                }
            }
        }
    }

    public InternalMain() {
        initComponents();
        setLocationRelativeTo(null);
        dtpProgram.setPreferredSize(new Dimension(1359, 628));
        HomeFormDB hf = new HomeFormDB();
        this.dtpProgram.add(hf);
        try {
            hf.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(InternalMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        hf.show();

    }

    //set time for system
    public String curentTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String timeString = dateFormat.format(date);
        return timeString;
    }

    //run help file
    public void help() {
        String FILE_HELP = "myhelp/sample.hs";
        HelpSet helpset;
        HelpBroker helpbroker = null;
        ClassLoader cl = getClass().getClassLoader();
        try {
            URL url = HelpSet.findHelpSet(cl, FILE_HELP);
            helpset = new HelpSet(null, url);
            helpbroker = helpset.createHelpBroker();
            helpbroker.setDisplayed(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * .
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlStatus = new javax.swing.JPanel();
        pnlStatusContent = new javax.swing.JPanel();
        lblClock = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        pnlWelcome = new javax.swing.JPanel();
        lblWelcome = new javax.swing.JLabel();
        lblUserName = new javax.swing.JLabel();
        pnlSystem = new javax.swing.JPanel();
        lblSystemContent = new javax.swing.JLabel();
        dtpProgram = new javax.swing.JDesktopPane();
        mnuCoffe = new javax.swing.JMenuBar();
        mnuHome = new javax.swing.JMenu();
        mnuAccount = new javax.swing.JMenu();
        mniUserManagement = new javax.swing.JMenuItem();
        mniUserPermission = new javax.swing.JMenuItem();
        mnuProduct = new javax.swing.JMenu();
        mniProductManagement = new javax.swing.JMenuItem();
        mnuBill = new javax.swing.JMenu();
        mniBill = new javax.swing.JMenuItem();
        mniCustomer = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mniCategory = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        mniPay = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        mnuReport = new javax.swing.JMenu();
        mniMonth = new javax.swing.JMenuItem();
        mniYear = new javax.swing.JMenuItem();
        mniDay = new javax.swing.JMenuItem();
        mnuSystem = new javax.swing.JMenu();
        mniBackup = new javax.swing.JMenuItem();
        mniRestore = new javax.swing.JMenuItem();
        mniDiary = new javax.swing.JMenuItem();
        mniChange = new javax.swing.JMenuItem();
        mnuHelp = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        mniAbout = new javax.swing.JMenuItem();
        mnuExit = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Payment System - Indochine Palace Hotel");
        setIconImages(null);
        setMinimumSize(new java.awt.Dimension(1366, 728));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        pnlStatus.setMinimumSize(new java.awt.Dimension(1359, 34));
        pnlStatus.setPreferredSize(new java.awt.Dimension(1359, 34));
        pnlStatus.setLayout(new java.awt.GridBagLayout());

        pnlStatusContent.setBackground(new java.awt.Color(175, 175, 175));
        pnlStatusContent.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(175, 175, 175), new java.awt.Color(175, 175, 175)));
        pnlStatusContent.setForeground(new java.awt.Color(175, 175, 175));
        pnlStatusContent.setMinimumSize(new java.awt.Dimension(145, 34));
        pnlStatusContent.setPreferredSize(new java.awt.Dimension(145, 34));
        pnlStatusContent.setLayout(new java.awt.GridBagLayout());

        lblClock.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        pnlStatusContent.add(lblClock, gridBagConstraints);

        lblTime.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTime.setText("Timer:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        pnlStatusContent.add(lblTime, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        pnlStatus.add(pnlStatusContent, gridBagConstraints);

        pnlWelcome.setBackground(new java.awt.Color(175, 175, 175));
        pnlWelcome.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(175, 175, 175), new java.awt.Color(175, 175, 175)));
        pnlWelcome.setForeground(new java.awt.Color(175, 175, 175));
        pnlWelcome.setMinimumSize(new java.awt.Dimension(614, 34));
        pnlWelcome.setPreferredSize(new java.awt.Dimension(614, 34));
        pnlWelcome.setLayout(new java.awt.GridBagLayout());

        lblWelcome.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblWelcome.setText("Welcome: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 400, 0, 0);
        pnlWelcome.add(lblWelcome, gridBagConstraints);

        lblUserName.setText("Thái Thành Nam");
        pnlWelcome.add(lblUserName, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        pnlStatus.add(pnlWelcome, gridBagConstraints);

        pnlSystem.setBackground(new java.awt.Color(175, 175, 175));
        pnlSystem.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(175, 175, 175), new java.awt.Color(175, 175, 175)));
        pnlSystem.setForeground(new java.awt.Color(175, 175, 175));
        pnlSystem.setMinimumSize(new java.awt.Dimension(600, 34));
        pnlSystem.setPreferredSize(new java.awt.Dimension(600, 34));
        pnlSystem.setLayout(new java.awt.GridBagLayout());

        lblSystemContent.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblSystemContent.setText("Fair Deal - Furniture System");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 400, 0, 0);
        pnlSystem.add(lblSystemContent, gridBagConstraints);

        pnlStatus.add(pnlSystem, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        getContentPane().add(pnlStatus, gridBagConstraints);

        dtpProgram.setBackground(new java.awt.Color(89, 89, 89));
        dtpProgram.setMinimumSize(new java.awt.Dimension(1359, 612));
        getContentPane().add(dtpProgram, new java.awt.GridBagConstraints());

        mnuCoffe.setBackground(new java.awt.Color(175, 175, 175));
        mnuCoffe.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(175, 175, 175)));
        mnuCoffe.setForeground(new java.awt.Color(175, 175, 175));

        mnuHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/home.png"))); // NOI18N
        mnuHome.setText("Home");
        mnuHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mnuHome.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mnuHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuHomeMouseClicked(evt);
            }
        });
        mnuCoffe.add(mnuHome);

        mnuAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/user_menu.png"))); // NOI18N
        mnuAccount.setText("Accounts");
        mnuAccount.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mnuAccount.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        mniUserManagement.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mniUserManagement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/account_menu.png"))); // NOI18N
        mniUserManagement.setText("Account manager");
        mniUserManagement.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mniUserManagement.setVisible(false);
        mniUserManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniUserManagementActionPerformed(evt);
            }
        });
        mnuAccount.add(mniUserManagement);

        mniUserPermission.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mniUserPermission.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/role_item.png"))); // NOI18N
        mniUserPermission.setText("Permission manager");
        mniUserPermission.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mniUserPermission.setVisible(false);
        mniUserPermission.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniUserPermissionActionPerformed(evt);
            }
        });
        mnuAccount.add(mniUserPermission);

        mnuCoffe.add(mnuAccount);

        mnuProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/seat_menu.png"))); // NOI18N
        mnuProduct.setText("Products");
        mnuProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mnuProduct.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        mniProductManagement.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mniProductManagement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/seat_item.png"))); // NOI18N
        mniProductManagement.setText("Product manager");
        mniProductManagement.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mniProductManagement.setVisible(false);
        mniProductManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniProductManagementActionPerformed(evt);
            }
        });
        mnuProduct.add(mniProductManagement);

        mnuCoffe.add(mnuProduct);

        mnuBill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cash_menu.png"))); // NOI18N
        mnuBill.setText("Services");
        mnuBill.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mnuBill.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        mniBill.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mniBill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bill_item.png"))); // NOI18N
        mniBill.setText("Bill overview");
        mniBill.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mniBill.setVisible(false);
        mniBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniBillActionPerformed(evt);
            }
        });
        mnuBill.add(mniBill);

        mniCustomer.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mniCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/customer_item.png"))); // NOI18N
        mniCustomer.setText("Customer manager");
        mniCustomer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mniCustomer.setVisible(false);
        mniCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniCustomerActionPerformed(evt);
            }
        });
        mnuBill.add(mniCustomer);

        jMenuItem7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/gym-icon.png"))); // NOI18N
        jMenuItem7.setText("OrderService");
        jMenuItem7.setVisible(false);
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        mnuBill.add(jMenuItem7);

        mnuCoffe.add(mnuBill);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Restaurant-icon.png"))); // NOI18N
        jMenu2.setText("Restaurant");
        jMenu2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        mniCategory.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mniCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/category_item.png"))); // NOI18N
        mniCategory.setText("Category manager");
        mniCategory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mniCategory.setVisible(false);
        mniCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniCategoryActionPerformed(evt);
            }
        });
        jMenu2.add(mniCategory);

        jMenuItem2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Coffee-break-icon.png"))); // NOI18N
        jMenuItem2.setText("Supplier manager");
        jMenuItem2.setVisible(false);
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        mniPay.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mniPay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/payment_item.png"))); // NOI18N
        mniPay.setText("Checkout");
        mniPay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mniPay.setVisible(false);
        mniPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniPayActionPerformed(evt);
            }
        });
        jMenu2.add(mniPay);

        jMenuItem8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/table.png"))); // NOI18N
        jMenuItem8.setText("Tables");
        jMenuItem8.setVisible(false);
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        mnuCoffe.add(jMenu2);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cancel.png"))); // NOI18N
        jMenu1.setText("Hotel");
        jMenu1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jMenuItem3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/customer_item.png"))); // NOI18N
        jMenuItem3.setText("Customer");
        jMenuItem3.setVisible(false);
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/edit.png"))); // NOI18N
        jMenuItem4.setText("Check in");
        jMenuItem4.setVisible(false);
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/clearcart.png"))); // NOI18N
        jMenuItem5.setText("Check out");
        jMenuItem5.setVisible(false);
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Household-Sleeping-Room-icon.png"))); // NOI18N
        jMenuItem6.setText("Rooms");
        jMenuItem6.setVisible(false);
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Shoppingcart-12-plus-icon.png"))); // NOI18N
        jMenuItem9.setText("Hotel Order Service");
        jMenuItem9.setVisible(false);
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem9);

        mnuCoffe.add(jMenu1);

        mnuReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/chart_menu.png"))); // NOI18N
        mnuReport.setText(" Restaurant Statistics");
        mnuReport.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mnuReport.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        mniMonth.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mniMonth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/printer.png"))); // NOI18N
        mniMonth.setText("Monthly report");
        mniMonth.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mniMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniMonthActionPerformed(evt);
            }
        });
        mnuReport.add(mniMonth);

        mniYear.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mniYear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/printer.png"))); // NOI18N
        mniYear.setText("Annual report");
        mniYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniYearActionPerformed(evt);
            }
        });
        mnuReport.add(mniYear);

        mniDay.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mniDay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/printer.png"))); // NOI18N
        mniDay.setText("Daily report");
        mniDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDayActionPerformed(evt);
            }
        });
        mnuReport.add(mniDay);

        mnuCoffe.add(mnuReport);

        mnuSystem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/system_menu.png"))); // NOI18N
        mnuSystem.setText("System");
        mnuSystem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mnuSystem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mnuSystem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuSystemMouseClicked(evt);
            }
        });

        mniBackup.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mniBackup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/backup_item.png"))); // NOI18N
        mniBackup.setText("Backup data");
        mniBackup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mniBackup.setVisible(false);
        mniBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniBackupActionPerformed(evt);
            }
        });
        mnuSystem.add(mniBackup);

        mniRestore.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mniRestore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/restore_item.png"))); // NOI18N
        mniRestore.setText("Restore data");
        mniRestore.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mniRestore.setVisible(false);
        mniRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniRestoreActionPerformed(evt);
            }
        });
        mnuSystem.add(mniRestore);

        mniDiary.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mniDiary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/diary_item.png"))); // NOI18N
        mniDiary.setText("View diary");
        mniDiary.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mniDiary.setVisible(false);
        mniDiary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDiaryActionPerformed(evt);
            }
        });
        mnuSystem.add(mniDiary);

        mniChange.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mniChange.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/account_menu.png"))); // NOI18N
        mniChange.setText("Change Password");
        mniChange.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mniChange.setVisible(false);
        mniChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniChangeActionPerformed(evt);
            }
        });
        mnuSystem.add(mniChange);

        mnuCoffe.add(mnuSystem);

        mnuHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/help_menu.png"))); // NOI18N
        mnuHelp.setText("Help");
        mnuHelp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mnuHelp.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/help.png"))); // NOI18N
        jMenuItem1.setText("Help");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        mnuHelp.add(jMenuItem1);

        mniAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/about.png"))); // NOI18N
        mniAbout.setText("About");
        mniAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniAboutActionPerformed(evt);
            }
        });
        mnuHelp.add(mniAbout);

        mnuCoffe.add(mnuHelp);

        mnuExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logout_menu.png"))); // NOI18N
        mnuExit.setText("Logout");
        mnuExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mnuExit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mnuExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuExitMouseClicked(evt);
            }
        });
        mnuCoffe.add(mnuExit);

        setJMenuBar(mnuCoffe);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mniProductManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniProductManagementActionPerformed
        // TODO add your handling code here:
        ProductFormDB pd = ProductFormDB.getIns();

        JInternalFrame[] frmList = this.dtpProgram.getAllFrames();
        boolean found = false;
        for (JInternalFrame fr : frmList) {
            if (fr == pd) {
                found = true;
                break;
            }
        }
        if (!found) {
            this.dtpProgram.add(pd);
            pd.fillCbb();
        } else {
            pd.fillCbb();
        }
        this.dtpProgram.getDesktopManager().maximizeFrame(pd);
        pd.show();
    }//GEN-LAST:event_mniProductManagementActionPerformed

    private void mniCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCategoryActionPerformed
        // TODO add your handling code here:
        CategoryFormDB cf = CategoryFormDB.getIns();

        JInternalFrame[] frmList = this.dtpProgram.getAllFrames();
        boolean found = false;
        for (JInternalFrame fr : frmList) {
            if (fr == cf) {
                found = true;
                break;
            }
        }
        if (!found) {
            this.dtpProgram.add(cf);
        }
        this.dtpProgram.getDesktopManager().maximizeFrame(cf);
        cf.show();
    }//GEN-LAST:event_mniCategoryActionPerformed

    private void mniCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCustomerActionPerformed
        // TODO add your handling code here:
        CustomerFormDB plf = CustomerFormDB.getIns();

        JInternalFrame[] frmList = this.dtpProgram.getAllFrames();
        boolean found = false;
        for (JInternalFrame fr : frmList) {
            if (fr == plf) {
                found = true;
                break;
            }
        }
        if (!found) {
            this.dtpProgram.add(plf);
            plf.filterCustomerList1();
        }
        this.dtpProgram.getDesktopManager().maximizeFrame(plf);
        plf.show();
        plf.filterCustomerList1();
    }//GEN-LAST:event_mniCustomerActionPerformed

    private void mniBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniBillActionPerformed
        // TODO add your handling code here:
        BillFormDB bf = BillFormDB.getIns();

        JInternalFrame[] frmList = this.dtpProgram.getAllFrames();
        boolean found = false;
        for (JInternalFrame fr : frmList) {
            if (fr == bf) {
                found = true;
                break;
            }
        }
        if (!found) {
            this.dtpProgram.add(bf);
            bf.loadBillListToTable(resBillModel.getAll());
        }
        this.dtpProgram.getDesktopManager().maximizeFrame(bf);
        bf.show();
        bf.loadBillListToTable(resBillModel.getAll());
    }//GEN-LAST:event_mniBillActionPerformed

    private void mnuExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuExitMouseClicked
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Are you sure ?", "Confirm", 2) == 0) {

            for (JInternalFrame fr : this.dtpProgram.getAllFrames()) {
                fr.dispose();
            }

            this.dispose();
            LoginForm lgf = new LoginForm();
            lgf.setVisible(true);
        }
    }//GEN-LAST:event_mnuExitMouseClicked

    private void mniDiaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDiaryActionPerformed

        DiaryFormDB drf = DiaryFormDB.getIns();

        JInternalFrame[] frmList = this.dtpProgram.getAllFrames();
        boolean found = false;
        for (JInternalFrame fr : frmList) {
            if (fr == drf) {
                found = true;
                break;
            }
        }
        if (!found) {
            this.dtpProgram.add(drf);
        }
        this.dtpProgram.getDesktopManager().maximizeFrame(drf);
        drf.show();
    }//GEN-LAST:event_mniDiaryActionPerformed

    private void mnuSystemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuSystemMouseClicked
    }//GEN-LAST:event_mnuSystemMouseClicked

    private void mniPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniPayActionPerformed
        // TODO add your handling code here:
        CheckOutForm pf = CheckOutForm.getIns();

        JInternalFrame[] frmList = this.dtpProgram.getAllFrames();
        boolean found = false;
        for (JInternalFrame fr : frmList) {
            if (fr == pf) {
                found = true;
                break;
            }
        }
        if (!found) {
            this.dtpProgram.add(pf);
            pf.loadProductListToTable(proModel.getAll());
            pf.loadCustomers();
        } else {
            pf.loadProductListToTable(proModel.getAll());
            pf.loadCustomers();
        }
        this.dtpProgram.getDesktopManager().maximizeFrame(pf);
        pf.show();

    }//GEN-LAST:event_mniPayActionPerformed

    private void mniBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniBackupActionPerformed
        // TODO add your handling code here:
        BackupForm bk = BackupForm.getIns();
        bk.setVisible(true);
    }//GEN-LAST:event_mniBackupActionPerformed

    private void mniRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniRestoreActionPerformed
        // TODO add your handling code here:
        RecoveryForm bk = RecoveryForm.getIns();
        bk.setVisible(true);
    }//GEN-LAST:event_mniRestoreActionPerformed

    private void mniUserPermissionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniUserPermissionActionPerformed
        // TODO add your handling code here:
        RoleFormDB rf = RoleFormDB.getIns();

        JInternalFrame[] frmList = this.dtpProgram.getAllFrames();
        boolean found = false;

        for (JInternalFrame fr : frmList) {
            if (fr == rf) {
                found = true;
                break;
            }
        }
        if (!found) {
            //            System.out.println("adding new ");
            this.dtpProgram.add(rf);
        }
        this.dtpProgram.getDesktopManager().maximizeFrame(rf);
        rf.show();
        try {
            rf.setSelected(true);
            rf.requestFocus();
            rf.grabFocus();
        } catch (PropertyVetoException ex) {
            Logger.getLogger(InternalMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniUserPermissionActionPerformed

    private void mniUserManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniUserManagementActionPerformed
        // TODO add your handling code here:
        AccountFormDB ac = AccountFormDB.getIns();

        JInternalFrame[] frmList = this.dtpProgram.getAllFrames();
        boolean found = false;
        for (JInternalFrame fr : frmList) {
            if (fr == ac) {
                found = true;
                break;
            }
        }
        if (!found) {
            this.dtpProgram.add(ac);
        }
        this.dtpProgram.getDesktopManager().maximizeFrame(ac);
        ac.show();
    }//GEN-LAST:event_mniUserManagementActionPerformed

    private void mniMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniMonthActionPerformed
        // TODO add your handling code here:
        ReportMonth rm = ReportMonth.getIns();
        rm.setVisible(true);
    }//GEN-LAST:event_mniMonthActionPerformed

    private void mniYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniYearActionPerformed
        // TODO add your handling code here:
        ReportYear ry = ReportYear.getIns();
        ry.setVisible(true);
    }//GEN-LAST:event_mniYearActionPerformed

    private void mnuHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuHomeMouseClicked
        // TODO add your handling code here:
        HomeFormDB hf = HomeFormDB.getIns();

        JInternalFrame[] frmList = this.dtpProgram.getAllFrames();
        boolean found = false;
        for (JInternalFrame fr : frmList) {
            if (fr == hf) {
                found = true;
                break;
            }
        }
        if (!found) {
            this.dtpProgram.add(hf);
        }
        this.dtpProgram.getDesktopManager().maximizeFrame(hf);
        hf.show();
    }//GEN-LAST:event_mnuHomeMouseClicked

    private void mniDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDayActionPerformed
        // TODO add your handling code here:
        ReportDay rm = ReportDay.getIns();
        rm.setVisible(true);
    }//GEN-LAST:event_mniDayActionPerformed

    private void mniAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniAboutActionPerformed
        // TODO add your handling code here:
        AboutForm af = AboutForm.getInstan();
        af.setVisible(true);
    }//GEN-LAST:event_mniAboutActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        help();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void mniChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniChangeActionPerformed
        // TODO add your handling code here:

//        ChangePassword cp = new ChangePassword(empModel.find(lblUserName.getText()));
//        cp.setVisible(true);
    }//GEN-LAST:event_mniChangeActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        SupplierForm cf = SupplierForm.getIns();

        JInternalFrame[] frmList = this.dtpProgram.getAllFrames();
        boolean found = false;
        for (JInternalFrame fr : frmList) {
            if (fr == cf) {
                found = true;
                break;
            }
        }
        if (!found) {
            this.dtpProgram.add(cf);
        }
        this.dtpProgram.getDesktopManager().maximizeFrame(cf);
        cf.show();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        CheckInHotel cf = CheckInHotel.getIns();
        JInternalFrame[] frmList = this.dtpProgram.getAllFrames();
        boolean found = false;
        for (JInternalFrame fr : frmList) {
            if (fr == cf) {
                found = true;
                break;
            }
        }
        if (!found) {
            this.dtpProgram.add(cf);
            cf.loadCustomer();
        }
        this.dtpProgram.getDesktopManager().maximizeFrame(cf);
        cf.show();
        cf.loadCustomer();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        CustomerForm cf = CustomerForm.getIns();

        JInternalFrame[] frmList = this.dtpProgram.getAllFrames();
        boolean found = false;
        for (JInternalFrame fr : frmList) {
            if (fr == cf) {
                found = true;
                break;
            }
        }
        if (!found) {
            this.dtpProgram.add(cf);
        }
        this.dtpProgram.getDesktopManager().maximizeFrame(cf);
        cf.show();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        RoomForm cf = RoomForm.getIns();

        JInternalFrame[] frmList = this.dtpProgram.getAllFrames();
        boolean found = false;
        for (JInternalFrame fr : frmList) {
            if (fr == cf) {
                found = true;
                break;
            }
        }
        if (!found) {
            this.dtpProgram.add(cf);
            cf.loadData();
        }
        this.dtpProgram.getDesktopManager().maximizeFrame(cf);
        cf.show();
        cf.loadData();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        OrderServices cf = OrderServices.getIns();
        SerEntityManager serModel = new SerEntityManager();
        JInternalFrame[] frmList = this.dtpProgram.getAllFrames();
        boolean found = false;
        for (JInternalFrame fr : frmList) {
            if (fr == cf) {
                found = true;
                break;
            }
        }
        if (!found) {
            this.dtpProgram.add(cf);
            cf.loadSerListToTable(serModel.getAllFromDB());
            cf.loadCbbSer();
            cf.setButonCartSer();

        }
        this.dtpProgram.getDesktopManager().maximizeFrame(cf);
        cf.show();
        cf.loadSerListToTable(serModel.getAllFromDB());
        cf.loadCbbSer();
        cf.setButonCartSer();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        CheckOutHotel cf = CheckOutHotel.getIns();
        RoomEntityManager roomModel = new RoomEntityManager();
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
            cf.loadProductListToTable(roomModel.getAllBookedRoom());
        }
        InternalMain.dtpProgram.getDesktopManager().maximizeFrame(cf);
        cf.show();cf.loadProductListToTable(roomModel.getAllBookedRoom());
        
        
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        Tables cf = Tables.getIns();

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
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        HotelOrderServices cf = HotelOrderServices.getIns();
        SerEntityManager serModel = new SerEntityManager();
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
            cf.loadSerListToTable(serModel.getAllFromDB());
      
        }
        InternalMain.dtpProgram.getDesktopManager().maximizeFrame(cf);
        cf.show();
        cf.loadSerListToTable(serModel.getAllFromDB());
     
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InternalMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InternalMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InternalMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InternalMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new InternalMain().setVisible(true);
            }
        });

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane dtpProgram;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JLabel lblClock;
    private javax.swing.JLabel lblSystemContent;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JMenuItem mniAbout;
    private javax.swing.JMenuItem mniBackup;
    private javax.swing.JMenuItem mniBill;
    private javax.swing.JMenuItem mniCategory;
    private javax.swing.JMenuItem mniChange;
    private javax.swing.JMenuItem mniCustomer;
    private javax.swing.JMenuItem mniDay;
    private javax.swing.JMenuItem mniDiary;
    private javax.swing.JMenuItem mniMonth;
    private javax.swing.JMenuItem mniPay;
    private javax.swing.JMenuItem mniProductManagement;
    private javax.swing.JMenuItem mniRestore;
    private javax.swing.JMenuItem mniUserManagement;
    private javax.swing.JMenuItem mniUserPermission;
    private javax.swing.JMenuItem mniYear;
    private javax.swing.JMenu mnuAccount;
    private javax.swing.JMenu mnuBill;
    private javax.swing.JMenuBar mnuCoffe;
    private javax.swing.JMenu mnuExit;
    private javax.swing.JMenu mnuHelp;
    private javax.swing.JMenu mnuHome;
    private javax.swing.JMenu mnuProduct;
    private javax.swing.JMenu mnuReport;
    private javax.swing.JMenu mnuSystem;
    private javax.swing.JPanel pnlStatus;
    private javax.swing.JPanel pnlStatusContent;
    private javax.swing.JPanel pnlSystem;
    private javax.swing.JPanel pnlWelcome;
    // End of variables declaration//GEN-END:variables
}
