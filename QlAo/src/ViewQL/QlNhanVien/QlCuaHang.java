/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ViewQL.QlNhanVien;

import Service.CV_CH.Cv_Ch_service;
import Service.CV_CH.Icv_ch_service;
import ViewModel.chView;
import java.util.List;
import java.util.UUID;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author latha
 */
public class QlCuaHang extends javax.swing.JFrame {

    private final Icv_ch_service chrepo = new Cv_Ch_service();

    /**
     * Creates new form QlCuaHang
     */
    public QlCuaHang() {
        initComponents();
        setLocationRelativeTo(null);
        LoadTB();
    }

    private void ClearText() {
        txtDC.setText(null);
        txtID.setText(null);
        txtMA.setText(null);
        txtQG.setText(null);
        txtTP.setText(null);
        txtTenCH.setText(null);
    }

    private void LoadTB() {
        List<chView> lch = chrepo.findAllCH();
        if (lch == null) {
            JOptionPane.showMessageDialog(this, "Lỗi không lấy đc dữ liệu");
        } else if (lch.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lỗi không có dữ liệu");
        }
        DefaultTableModel Model = (DefaultTableModel) TbCuaHang.getModel();
        Model.setColumnCount(0);
        Model.setColumnIdentifiers(new String[]{"Id", "Mã CH", "Tên CH",
            "Thành Phô", "Quốc Gia", "Địa Chỉ"});
        Model.setRowCount(0);
        for (chView vi : lch) {
            Object[] row = new Object[]{vi.getId(), vi.getMa(), vi.getTen(),
                vi.getThanhpho(), vi.getQuocgia(), vi.getDiaChi()};
            Model.addRow(row);
        }
    }

    private void HienTxt() {
        int Index = TbCuaHang.getSelectedRow();
        if (Index < 0) {
            JOptionPane.showMessageDialog(this, "Dữ Liệu Trống");
        } else {
            txtID.setText(TbCuaHang.getValueAt(Index, 0).toString());
            txtMA.setText(TbCuaHang.getValueAt(Index, 1).toString());
            txtTenCH.setText(TbCuaHang.getValueAt(Index, 2).toString());
            txtTP.setText(TbCuaHang.getValueAt(Index, 3).toString());
            txtQG.setText(TbCuaHang.getValueAt(Index, 4).toString());
            txtDC.setText(TbCuaHang.getValueAt(Index, 5).toString());
        }
    }

    private chView getText() {
        chView ch = new chView();
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        ch.setId(id);
        ch.setMa(txtMA.getText());
        ch.setTen(txtTenCH.getText());
        ch.setQuocgia(txtQG.getText());
        ch.setThanhpho(txtTP.getText());
        ch.setDiaChi(txtDC.getText());
        return ch;
    }

    private boolean validatetxt() {
        if (txtMA.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập Mã");
            return false;
        }
        if (txtTenCH.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập Tên");
            return false;
        }
        if (txtTP.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập Thành Phố");
            return false;
        }
        if (txtQG.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập Quốc Gia");
            return false;
        }
        if (txtDC.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập Địa Chỉ");
            return false;
        }
        return true;
    }

    private boolean SaveCH() {
        try {
            if (validatetxt()) {
                chView ch = getText();
                chrepo.SaveCH(ch);
                JOptionPane.showMessageDialog(this, "Thêm Thành Công");
                LoadTB();
                return true;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thêm Bị Lỗi bởi: " + e.getMessage());
            return false;
        }
        return false;
    }

    private boolean UpdateCH() {
        try {
            if (validatetxt()) {
                chView ch = getText();
                ch.setId(txtID.getText());
                chrepo.UpdateCH(ch);
                JOptionPane.showMessageDialog(this, "Sửa Thành Công");
                LoadTB();
                return true;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sửa Bị Lỗi bởi: " + e.getMessage());
            return false;
        }
        return false;
    }

    private boolean XoaCH() {
        try {
            if (txtID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Đang trống ID");
                return false;
            }
            if (JOptionPane.showConfirmDialog(this,
                    "Xóa Cửa hàng này Thật Không !", "Xóa Cửa Hàng",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                String id = txtID.getText();
                chrepo.DeleteCH(id);
                JOptionPane.showMessageDialog(this, "Xóa Thành Công");
                LoadTB();
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, " Xõa Bị Lỗi bởi: " + e.getMessage());
            return false;
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TbCuaHang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMA = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTenCH = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTP = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtQG = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDC = new javax.swing.JTextArea();
        btnAdd = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLoadTB = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("ViewQL/QlNhanVien/Bundle"); // NOI18N
        setTitle(bundle.getString("QlCuaHang.title")); // NOI18N
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 450));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 450));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(800, 450));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        TbCuaHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TbCuaHang.setName("TbCuaHang"); // NOI18N
        TbCuaHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TbCuaHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TbCuaHang);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 450));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(bundle.getString("QlCuaHang.jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, 351, 35));

        jLabel2.setText(bundle.getString("QlCuaHang.jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 41, 80, 22));

        txtID.setEditable(false);
        txtID.setText(bundle.getString("QlCuaHang.txtID.text")); // NOI18N
        txtID.setName("txtID"); // NOI18N
        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });
        jPanel2.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(568, 41, 210, -1));

        jLabel3.setText(bundle.getString("QlCuaHang.jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 69, 80, 30));

        txtMA.setText(bundle.getString("QlCuaHang.txtMA.text")); // NOI18N
        txtMA.setName("txtMA"); // NOI18N
        jPanel2.add(txtMA, new org.netbeans.lib.awtextra.AbsoluteConstraints(568, 69, 210, 30));

        jLabel4.setText(bundle.getString("QlCuaHang.jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 111, 80, 30));

        txtTenCH.setText(bundle.getString("QlCuaHang.txtTenCH.text")); // NOI18N
        txtTenCH.setName("txtTenCH"); // NOI18N
        jPanel2.add(txtTenCH, new org.netbeans.lib.awtextra.AbsoluteConstraints(568, 111, 210, 30));

        jLabel5.setText(bundle.getString("QlCuaHang.jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 153, 80, 30));

        txtTP.setText(bundle.getString("QlCuaHang.txtTP.text")); // NOI18N
        txtTP.setName("txtTP"); // NOI18N
        jPanel2.add(txtTP, new org.netbeans.lib.awtextra.AbsoluteConstraints(568, 153, 210, 30));

        jLabel6.setText(bundle.getString("QlCuaHang.jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 189, 80, 30));

        txtQG.setText(bundle.getString("QlCuaHang.txtQG.text")); // NOI18N
        txtQG.setName("txtQG"); // NOI18N
        jPanel2.add(txtQG, new org.netbeans.lib.awtextra.AbsoluteConstraints(568, 189, 210, 30));

        jLabel7.setText(bundle.getString("QlCuaHang.jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 225, 80, 30));

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        txtDC.setColumns(20);
        txtDC.setRows(5);
        txtDC.setName("txtDC"); // NOI18N
        jScrollPane2.setViewportView(txtDC);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(568, 225, 210, 100));

        btnAdd.setText(bundle.getString("QlCuaHang.btnAdd.text")); // NOI18N
        btnAdd.setName("btnAdd"); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel2.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 329, -1, -1));

        btnSua.setText(bundle.getString("QlCuaHang.btnSua.text")); // NOI18N
        btnSua.setName("btnSua"); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel2.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(617, 329, -1, -1));

        btnXoa.setText(bundle.getString("QlCuaHang.btnXoa.text")); // NOI18N
        btnXoa.setName("btnXoa"); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel2.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(716, 329, -1, -1));

        btnLoadTB.setText(bundle.getString("QlCuaHang.btnLoadTB.text")); // NOI18N
        btnLoadTB.setName("btnLoadTB"); // NOI18N
        btnLoadTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadTBActionPerformed(evt);
            }
        });
        jPanel2.add(btnLoadTB, new org.netbeans.lib.awtextra.AbsoluteConstraints(458, 364, -1, -1));

        btnClear.setText(bundle.getString("QlCuaHang.btnClear.text")); // NOI18N
        btnClear.setName("btnClear"); // NOI18N
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        jPanel2.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(633, 364, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void TbCuaHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TbCuaHangMouseClicked
        // TODO add your handling code here:
        HienTxt();
    }//GEN-LAST:event_TbCuaHangMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        SaveCH();
        ClearText();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        UpdateCH();
        ClearText();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        XoaCH();
        ClearText();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnLoadTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadTBActionPerformed
        // TODO add your handling code here:
        LoadTB();
    }//GEN-LAST:event_btnLoadTBActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        ClearText();
    }//GEN-LAST:event_btnClearActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QlCuaHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QlCuaHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QlCuaHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QlCuaHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QlCuaHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TbCuaHang;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnLoadTB;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtDC;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtMA;
    private javax.swing.JTextField txtQG;
    private javax.swing.JTextField txtTP;
    private javax.swing.JTextField txtTenCH;
    // End of variables declaration//GEN-END:variables
}
