/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.Diary;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import models.DiaryEntityManager;

/**
 *
 * @author ADMIN
 */
public final class DiaryFormDB extends javax.swing.JInternalFrame {

    /**
     * Creates new form AccountFormDB
     */
    static DiaryFormDB instance = null;
    DiaryEntityManager diaryModel = new DiaryEntityManager();
    final String searchTextPlaceHolder = "Type in any keyword...";

    public DiaryFormDB() {
        initComponents();
        setIcon();
        //load data to table
        loadDiaryListToTable(diaryModel.getAll());
        //search actions
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
        //adjust columns
        tblDiary.getColumn("ID").setMaxWidth(0);
        tblDiary.getColumn("ID").setMinWidth(0);

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

    public static DiaryFormDB getIns() {
        if (instance == null) {
            instance = new DiaryFormDB();
        }
        return instance;

    }

    //auto-complete by search key words
    void filterProductList() {
        String searchText = txtSearch.getText().toLowerCase();
        if (searchText.equalsIgnoreCase(searchTextPlaceHolder)) {
            return;
        }
        List<Diary> l = new ArrayList<>();
        for (Diary ins : diaryModel.getAll()) {
            if (ins.getAccounts().getAccountName().toLowerCase().contains(searchText)
                    || ins.getDescription().toLowerCase().contains(searchText)) {
                l.add(ins);
            }
        }

        loadDiaryListToTable(l);
        //adjust columns
        tblDiary.getColumn("ID").setMaxWidth(0);
        tblDiary.getColumn("ID").setMinWidth(0);
    }

    //load diary data to table
    void loadDiaryListToTable(List<Diary> list) {

        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }
        };

        dtm.addColumn("ID");
        dtm.addColumn("Account");
        dtm.addColumn("Date");

        dtm.addColumn("Description");
        for (Diary diary : list) {
            dtm.addRow(new Object[]{
                diary.getDiaryId(),
                diary.getAccounts().getAccountName(),
                diary.getDateTime().toString(),
                diary.getDescription()
            });
        }

        tblDiary.setModel(dtm);
        //adjust columns
        tblDiary.getColumn("ID").setMaxWidth(0);
        tblDiary.getColumn("ID").setMinWidth(0);
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

        pnlOutput = new javax.swing.JPanel();
        scrUser = new javax.swing.JScrollPane();
        tblDiary = new javax.swing.JTable();
        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        lblSearchIcon = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(175, 175, 175), 4));
        setClosable(true);
        setTitle("Diary Manager");
        setMinimumSize(new java.awt.Dimension(0, 0));
        setNormalBounds(new java.awt.Rectangle(0, 0, 126, 0));
        setPreferredSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        pnlOutput.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Diary Data"));
        pnlOutput.setMinimumSize(new java.awt.Dimension(472, 477));
        pnlOutput.setPreferredSize(new java.awt.Dimension(600, 477));
        pnlOutput.setLayout(new java.awt.GridBagLayout());

        scrUser.setPreferredSize(new java.awt.Dimension(580, 400));

        tblDiary.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblDiary.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblDiary.getTableHeader().setReorderingAllowed(false);
        scrUser.setViewportView(tblDiary);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlOutput.add(scrUser, gridBagConstraints);

        lblSearch.setText("Search: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlOutput.add(lblSearch, gridBagConstraints);

        txtSearch.setPreferredSize(new java.awt.Dimension(300, 25));
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
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 120);
        pnlOutput.add(txtSearch, gridBagConstraints);

        lblSearchIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 150);
        pnlOutput.add(lblSearchIcon, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        getContentPane().add(pnlOutput, gridBagConstraints);

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
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblSearchIcon;
    private javax.swing.JPanel pnlOutput;
    private javax.swing.JScrollPane scrUser;
    private javax.swing.JTable tblDiary;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
