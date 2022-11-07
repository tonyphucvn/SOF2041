/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ViewQL.QlSanPham;

import Service.Sp.QlLoaiSpService;
import ViewModel.ttCtsp;
import java.util.List;
import java.util.UUID;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author latha
 */
public class QLySP extends javax.swing.JFrame {

    private final QlLoaiSpService qlRepo = new QlLoaiSpService();

    /**
     * Creates new form QLySP
     */
    public QLySP() {
        initComponents();
        setLocationRelativeTo(null);
        Loadtb();
    }

    private void Loadtb() {
        List<ttCtsp> Ltt = qlRepo.getSanPham();
        DefaultTableModel Model = (DefaultTableModel) tbSanPham.getModel();
        Model.setColumnCount(0);
        Model.setColumnIdentifiers(new String[]{"Id", "Mã", "Tên"});
        Model.setRowCount(0);
        for (ttCtsp ctsp : Ltt) {
            Object[] row = new Object[]{ctsp.getId(),
                ctsp.getMa(), ctsp.getTen()};
            Model.addRow(row);
        }
    }

    private void HienTTtuBang() {
        int Index = tbSanPham.getSelectedRow();
        if (Index < 0) {
            JOptionPane.showMessageDialog(this,
                    "Dữ liệu trống");
        } else {
            txtIdSp.setText((String) tbSanPham.getValueAt(Index, 0));
            txtMaSp.setText((String) tbSanPham.getValueAt(Index, 1));
            txtTenSp.setText((String) tbSanPham.getValueAt(Index, 2));
        }
    }

    private ttCtsp getTextFiled() {
        ttCtsp getTxt = new ttCtsp();
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        getTxt.setId(id);
        getTxt.setMa(txtMaSp.getText().toUpperCase());
        getTxt.setTen(txtTenSp.getText());
        return getTxt;
    }

    private boolean validateText() {
        if (txtMaSp.getText().isBlank()) {
            JOptionPane.showMessageDialog(this,
                    "Chưa Nhập Mã");
            return false;
        }
        if (txtTenSp.getText().isBlank()) {
            JOptionPane.showMessageDialog(this,
                    "Chưa Nhập Tên");
            return false;
        }
        return true;
    }

    private boolean SaveSp() {
        try {
            if (validateText()) {
                ttCtsp dsp = getTextFiled();
                qlRepo.SaveSp(dsp);
                JOptionPane.showMessageDialog(this,
                        "Lưu Thành công");
                Loadtb();
                ClearText();
                return true;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Lưu Thất bại bởi:" + e.getMessage());
            return false;
        }
        return false;
    }

    private boolean UpdateSp() {
        try {
            if (validateText()) {
                ttCtsp dsp = getTextFiled();
                dsp.setId(txtIdSp.getText());
                qlRepo.UpdateSp(dsp);
                JOptionPane.showMessageDialog(this,
                        "Sửa Dsp Thành công");
                Loadtb();
                ClearText();
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Sửa Thất bại bởi:" + e.getMessage());
            return false;
        }
        return false;
    }

    private boolean DeleteSp() {
        try {
            if (txtIdSp.getText().isBlank()) {
                JOptionPane.showMessageDialog(this,
                        "Không Có ID");
                return false;
            }
            if (JOptionPane.showConfirmDialog(this,
                    "Bạn muốn xóa thật ko",
                    "Xóa CTSP",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                qlRepo.DeleteSp(txtIdSp.getText());
                JOptionPane.showMessageDialog(this, 
                        "Xóa Thành Công");
                Loadtb();
                ClearText();
                return true;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Xóa Thất bại vì Lỗi:" + e.getMessage());
            return false;
        }
        return false;
    }

    private void ClearText() {
        txtIdSp.setText(null);
        txtMaSp.setText(null);
        txtTenSp.setText(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnLoad = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtIdSp = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtMaSp = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTenSp = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("ViewQL/Bundle"); // NOI18N
        setTitle(bundle.getString("QLySP.title")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(560, 320));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tbSanPham.setModel(new javax.swing.table.DefaultTableModel(
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
        tbSanPham.setName("tbSanPham"); // NOI18N
        tbSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbSanPham);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        java.util.ResourceBundle bundle1 = java.util.ResourceBundle.getBundle("ViewQL/QlSanPham/Bundle"); // NOI18N
        jLabel1.setText(bundle1.getString("QLySP.jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        btnAdd.setText(bundle1.getString("QLySP.btnAdd.text")); // NOI18N
        btnAdd.setName("btnAdd"); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText(bundle1.getString("QLySP.btnUpdate.text")); // NOI18N
        btnUpdate.setName("btnUpdate"); // NOI18N
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText(bundle1.getString("QLySP.btnDelete.text")); // NOI18N
        btnDelete.setName("btnDelete"); // NOI18N
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnLoad.setText(bundle1.getString("QLySP.btnLoad.text")); // NOI18N
        btnLoad.setName("btnLoad"); // NOI18N
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(bundle1.getString("QLySP.jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        txtIdSp.setText(bundle1.getString("QLySP.txtIdSp.text")); // NOI18N
        txtIdSp.setEnabled(false);
        txtIdSp.setName("txtIdSp"); // NOI18N

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText(bundle1.getString("QLySP.jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        txtMaSp.setText(bundle1.getString("QLySP.txtMaSp.text")); // NOI18N
        txtMaSp.setName("txtMaSp"); // NOI18N

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText(bundle1.getString("QLySP.jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        txtTenSp.setText(bundle1.getString("QLySP.txtTenSp.text")); // NOI18N
        txtTenSp.setName("txtTenSp"); // NOI18N

        btnClear.setText(bundle1.getString("QLySP.btnClear.text")); // NOI18N
        btnClear.setName("btnClear"); // NOI18N
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnLoad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(19, 19, 19))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnClear)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTenSp, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                            .addComponent(txtIdSp, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaSp, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(31, 31, 31))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdSp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaSp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenSp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLoad)
                    .addComponent(btnClear))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        // TODO add your handling code here:
        Loadtb();
    }//GEN-LAST:event_btnLoadActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        SaveSp();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        UpdateSp();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        DeleteSp();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        ClearText();
    }//GEN-LAST:event_btnClearActionPerformed

    private void tbSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSanPhamMouseClicked
        // TODO add your handling code here:
        HienTTtuBang();
    }//GEN-LAST:event_tbSanPhamMouseClicked

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
            java.util.logging.Logger.getLogger(QLySP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLySP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLySP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLySP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLySP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTextField txtIdSp;
    private javax.swing.JTextField txtMaSp;
    private javax.swing.JTextField txtTenSp;
    // End of variables declaration//GEN-END:variables
}
