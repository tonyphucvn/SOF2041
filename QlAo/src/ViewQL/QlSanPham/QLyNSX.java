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
public class QLyNSX extends javax.swing.JFrame {

    private final QlLoaiSpService qlRepo = new QlLoaiSpService();

    /**
     * Creates new form QLySP
     */
    public QLyNSX() {
        initComponents();
        setLocationRelativeTo(null);
        Loadtb();
    }

    private void Loadtb() {
        List<ttCtsp> Ltt = qlRepo.getNSX();
        DefaultTableModel Model = (DefaultTableModel) tbNSXSanPham.getModel();
        Model.setColumnCount(0);
        Model.setColumnIdentifiers(new String[]{"Id", "Mã", "Tên"});
        Model.setRowCount(0);
        for (ttCtsp ctsp : Ltt) {
            Object[] row = new Object[]{ctsp.getId(), ctsp.getMa(), ctsp.getTen()};
            Model.addRow(row);
        }
    }

    private void HienTTtuBang() {
        int Index = tbNSXSanPham.getSelectedRow();
        if (Index < 0) {
            JOptionPane.showMessageDialog(this, "Dữ liệu trống");
        } else {
            txtIdNSXSp.setText((String) tbNSXSanPham.getValueAt(Index, 0));
            txtMaNSXSp.setText((String) tbNSXSanPham.getValueAt(Index, 1));
            txtTenNSXSp.setText((String) tbNSXSanPham.getValueAt(Index, 2));
        }

    }

    private ttCtsp getTextFiled() {
        ttCtsp getTxt = new ttCtsp();
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        getTxt.setId(id);
        getTxt.setMa(txtMaNSXSp.getText().toUpperCase());
        getTxt.setTen(txtTenNSXSp.getText());
        return getTxt;
    }

    private boolean validateText() {
        if (txtMaNSXSp.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập Mã");
            return false;
        }
        if (txtTenNSXSp.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập Tên");
            return false;
        }
        return true;
    }

    private boolean SaveNSXsp() {
        try {
            if (validateText()) {
                ttCtsp dsp = getTextFiled();
                qlRepo.SaveNSX(dsp);
                JOptionPane.showMessageDialog(this, "Lưu Thành công");
                Loadtb();
                ClearText();
                return true;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lưu Thất bại bởi:" + e.getMessage());
            return false;
        }
        return false;
    }

    private boolean UpdateNSXsp() {
        try {
            if (validateText()) {
                ttCtsp dsp = getTextFiled();
                dsp.setId(txtIdNSXSp.getText());
                qlRepo.UpdateNSX(dsp);
                JOptionPane.showMessageDialog(this, "Sửa Dsp Thành công");
                Loadtb();
                ClearText();
                return true;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sửa Thất bại bởi:" + e.getMessage());
            return false;
        }
        return false;
    }

    private boolean DeleteNSXsp() {
        try {
            if (txtIdNSXSp.getText().isBlank()) {
                JOptionPane.showMessageDialog(this, "Không Có ID");
                return false;
            }
            if (JOptionPane.showConfirmDialog(this, "Bạn muốn xóa thật ko", "Xóa CTSP", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                qlRepo.DeleteNSX(txtIdNSXSp.getText());
                JOptionPane.showMessageDialog(this, "Xóa Thành Công");
                Loadtb();
                ClearText();
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Xóa Thất bại vì Lỗi:" + e.getMessage());
            return false;
        }
        return false;
    }

    private void ClearText() {
        txtIdNSXSp.setText(null);
        txtMaNSXSp.setText(null);
        txtTenNSXSp.setText(null);
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
        tbNSXSanPham = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnLoadBang = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtIdNSXSp = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtMaNSXSp = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTenNSXSp = new javax.swing.JTextField();
        btnCleartxt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("ViewQL/Bundle"); // NOI18N
        setTitle(bundle.getString("QLyNSX.title_1")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(560, 320));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tbNSXSanPham.setModel(new javax.swing.table.DefaultTableModel(
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
        tbNSXSanPham.setName("tbNSXSanPham"); // NOI18N
        tbNSXSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNSXSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbNSXSanPham);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 320));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        java.util.ResourceBundle bundle1 = java.util.ResourceBundle.getBundle("ViewQL/QlSanPham/Bundle"); // NOI18N
        jLabel1.setText(bundle1.getString("QLyNSX.jLabel1.text_1")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(256, 0, 328, 41));

        btnAdd.setText(bundle1.getString("QLyNSX.btnAdd.text_1")); // NOI18N
        btnAdd.setName("btnAdd"); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel1.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 229, 87, -1));

        btnUpdate.setText(bundle1.getString("QLyNSX.btnUpdate.text_1")); // NOI18N
        btnUpdate.setName("btnUpdate"); // NOI18N
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(403, 229, -1, -1));

        btnDelete.setText(bundle1.getString("QLyNSX.btnDelete.text_1")); // NOI18N
        btnDelete.setName("btnDelete"); // NOI18N
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 229, -1, -1));

        btnLoadBang.setText(bundle1.getString("QLyNSX.btnLoadBang.text_1")); // NOI18N
        btnLoadBang.setName("btnLoadBang"); // NOI18N
        btnLoadBang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadBangActionPerformed(evt);
            }
        });
        jPanel1.add(btnLoadBang, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 291, -1, -1));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(bundle1.getString("QLyNSX.jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(256, 53, -1, 30));

        txtIdNSXSp.setEditable(false);
        txtIdNSXSp.setText(bundle1.getString("QLyNSX.txtIdNSXSp.text")); // NOI18N
        txtIdNSXSp.setName("txtIdNSXSp"); // NOI18N
        jPanel1.add(txtIdNSXSp, new org.netbeans.lib.awtextra.AbsoluteConstraints(367, 53, 180, 30));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText(bundle1.getString("QLyNSX.jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(256, 101, -1, 30));

        txtMaNSXSp.setText(bundle1.getString("QLyNSX.txtMaNSXSp.text")); // NOI18N
        txtMaNSXSp.setName("txtMaNSXSp"); // NOI18N
        jPanel1.add(txtMaNSXSp, new org.netbeans.lib.awtextra.AbsoluteConstraints(367, 101, 180, 30));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText(bundle1.getString("QLyNSX.jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(256, 149, -1, 30));

        txtTenNSXSp.setText(bundle1.getString("QLyNSX.txtTenNSXSp.text")); // NOI18N
        txtTenNSXSp.setName("txtTenNSXSp"); // NOI18N
        jPanel1.add(txtTenNSXSp, new org.netbeans.lib.awtextra.AbsoluteConstraints(368, 149, 179, 30));

        btnCleartxt.setText(bundle1.getString("QLyNSX.btnCleartxt.text")); // NOI18N
        btnCleartxt.setName("btnCleartxt"); // NOI18N
        btnCleartxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCleartxtActionPerformed(evt);
            }
        });
        jPanel1.add(btnCleartxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(403, 291, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoadBangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadBangActionPerformed

        Loadtb();
    }//GEN-LAST:event_btnLoadBangActionPerformed

    private void tbNSXSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNSXSanPhamMouseClicked
        // TODO add your handling code here:
        HienTTtuBang();
    }//GEN-LAST:event_tbNSXSanPhamMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        SaveNSXsp();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        UpdateNSXsp();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        DeleteNSXsp();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnCleartxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCleartxtActionPerformed
        // TODO add your handling code here:
        ClearText();
    }//GEN-LAST:event_btnCleartxtActionPerformed

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
            java.util.logging.Logger.getLogger(QLyNSX.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLyNSX.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLyNSX.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLyNSX.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLyNSX().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCleartxt;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnLoadBang;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbNSXSanPham;
    private javax.swing.JTextField txtIdNSXSp;
    private javax.swing.JTextField txtMaNSXSp;
    private javax.swing.JTextField txtTenNSXSp;
    // End of variables declaration//GEN-END:variables
}
