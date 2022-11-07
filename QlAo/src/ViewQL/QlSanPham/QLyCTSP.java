/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ViewQL.QlSanPham;

import Model.Sp.SanPham;
import ViewModel.ctspView;
import Model.Sp.ChiTietSanPham;
import Model.Sp.DongSP;
import Model.Sp.MauSac;
import Model.Sp.NSX;
import Service.Sp.QlLoaiSpService;
import Service.Sp.QlctSpService;
import ViewModel.ttCtsp;
import java.awt.Color;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author latha
 */
public class QLyCTSP extends javax.swing.JFrame {

    private final QlLoaiSpService loaiSpSer = new QlLoaiSpService();
    private final QlctSpService ctSpSer = new QlctSpService();

    /**
     * Creates new form QLyAo
     */
    public QLyCTSP() {
        initComponents();
        loadtbSanPham();
        loadCb();
        setLocationRelativeTo(null);
        yearPicker.setStartYear(2015);
        yearPicker.setEndYear(2022);
        yearPicker.enableInputMethods(false);
    }

    private void loadCb() {
//=======================DongSP CBOX ==============================================
        cBoxDongSp.setBackground(Color.WHITE);
        cBoxDongSp.removeAllItems();
        List<ttCtsp> Ldsp = loaiSpSer.getDongSp();
        for (ttCtsp dongSP : Ldsp) {
            cBoxDongSp.addItem(dongSP);
        }
//=======================NSX CBOX ==============================================
        cBoxNsx.setBackground(Color.WHITE);
        cBoxNsx.removeAllItems();
        List<ttCtsp> Lnsx = loaiSpSer.getNSX();
        for (ttCtsp nsx : Lnsx) {
            cBoxNsx.addItem(nsx);
        }
//=======================MauSac CBOX ==============================================
        cBoxMauSac.setBackground(Color.WHITE);
        cBoxMauSac.removeAllItems();
        List<ttCtsp> Lms = loaiSpSer.getMauSac();
        for (ttCtsp Lm : Lms) {
            cBoxMauSac.addItem(Lm);
        }
//=======================SanPham CBOX ==============================================      
        cBoxSP.setBackground(Color.WHITE);
        cBoxSP.removeAllItems();
        List<ttCtsp> Lmsp = loaiSpSer.getSanPham();
        for (ttCtsp ctsp : Lmsp) {
            cBoxSP.addItem(ctsp);
        }
    }

    private void ClearTxt() {
        cBoxDongSp.setSelectedIndex(0);
        cBoxMauSac.setSelectedIndex(0);
        cBoxNsx.setSelectedIndex(0);
        cBoxSP.setSelectedIndex(0);
        yearPicker.setYear(2022);
        txtIdctsp.setText(null);
        txtGiaBan.setText(null);
        txtGiaNhap.setText(null);
        txtMoTa.setText(null);
        txtSoLuong.setText(null);
    }

    private ChiTietSanPham GetDLtuTxt() {

        ChiTietSanPham ctsp = new ChiTietSanPham();
        UUID uuid = UUID.randomUUID();
        String idctsp = uuid.toString();

//        Sản Phẩm 
        ttCtsp ttsp = (ttCtsp) cBoxSP.getSelectedItem();
        SanPham sp = new SanPham(ttsp.getId(), ttsp.getMa(), ttsp.getTen());
//       Thuộc tính Chi Tiết Sản Phẩm
        ttCtsp ttDsp = (ttCtsp) cBoxDongSp.getSelectedItem();
        DongSP dsp = new DongSP(ttDsp.getId(), ttDsp.getMa(), ttDsp.getTen());
        ttCtsp ttNSX = (ttCtsp) cBoxNsx.getSelectedItem();
        NSX nsx = new NSX(ttNSX.getId(), ttNSX.getMa(), ttNSX.getTen());
        ttCtsp ttMs = (ttCtsp) cBoxMauSac.getSelectedItem();
        MauSac ms = new MauSac(ttMs.getId(), ttMs.getMa(), ttMs.getTen());
//        Chi Tiết Sản phẩm
        ctsp.setId(idctsp);
        ctsp.setIdsp(sp);
        ctsp.setIdDongSP(dsp);
        ctsp.setIdNsx(nsx);
        ctsp.setIdmauSac(ms);
        ctsp.setGiaNhap(BigDecimal.valueOf(Double.parseDouble(txtGiaNhap.getText())));
        ctsp.setGiaBan(BigDecimal.valueOf(Double.parseDouble(txtGiaBan.getText())));
        ctsp.setSoluongTon(Integer.valueOf(txtSoLuong.getText()));
        ctsp.setMota(txtMoTa.getText());
        ctsp.setNamBh(yearPicker.getYear());
        return ctsp;
    }

    private boolean validateTxt() {
        if (txtGiaNhap.getText().isEmpty() || txtGiaBan.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập Giá");
            return false;
        }
        if (txtSoLuong.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập Số lượng");
            return false;
        }
        try {
            double giaNhap = Double.parseDouble(txtGiaNhap.getText());
            if (giaNhap <= 50000) {
                JOptionPane.showMessageDialog(this, "Giá tiền không đc nhỏ hơn 50000");
                return false;
            }
            double giaBan = Double.parseDouble(txtGiaBan.getText());
            if (giaBan <= 50000 || giaNhap >= giaBan) {
                JOptionPane.showMessageDialog(this, "Giá tiền không đc nhỏ hơn 50000");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Nhập sai giá trị bởi: " + e.getMessage());
            return false;
        }
        try {
            int sl = Integer.parseInt(txtSoLuong.getText());
            if (sl <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng không đc nhỏ hơn 0");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Nhập sai giá trị bởi: " + e.getMessage());
            return false;
        }

        return true;
    }

    private boolean SaveSP() {
        try {
            if (validateTxt()) {
                ChiTietSanPham ctsp = GetDLtuTxt();
                ctSpSer.Save(ctsp);
                JOptionPane.showMessageDialog(this, "Lưu Thành công");
                loadtbSanPham();
                ClearTxt();
                return true;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi bởi: " + e.toString());
            return false;
        }
        return false;

    }

    private boolean UpdateSp() {
        try {
            if (validateTxt()) {
                ChiTietSanPham ctsp = GetDLtuTxt();
                ctSpSer.Update(ctsp, txtIdctsp.getText());
                JOptionPane.showMessageDialog(this, "Sửa Thành công");
                loadtbSanPham();
                ClearTxt();
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi bởi: " + e.toString());
            return false;
        }
        return false;

    }

    private boolean DeleteCTsp() {
        try {
            if (txtIdctsp.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Chưa có Id Vui lòng Chọn CTSP");
                return false;
            }
            if (JOptionPane.showConfirmDialog(this, "Bạn muốn xóa thật ko", "Xóa CTSP", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                ctSpSer.delete(txtIdctsp.getText());
                JOptionPane.showMessageDialog(this, "Xóa Thành Công");
                loadtbSanPham();
                ClearTxt();
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Xóa Thất bại vì Lỗi:" + e.getMessage());
            return false;
        }
        return false;
    }

    private void FindByMa() {
        try {
            String ma = JOptionPane.showInputDialog("Nhập Mã SP muốn tìm");
            List<ctspView> lctsp = ctSpSer.findById(ma);
            if (lctsp == null) {
                JOptionPane.showMessageDialog(this, "Lỗi không lấy đc dữ liệu");
            } else if (lctsp.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Lỗi không có dữ liệu");
            }
            DefaultTableModel Model = (DefaultTableModel) tbSanPham.getModel();
            Model.setColumnCount(0);
            Model.setColumnIdentifiers(new String[]{"ID CTSP", "Mã SP", "Dòng SP",
                "NSX", "Màu Sắc", "Năm nhập hàng", "Số lượng", "Giá Nhập", "Giá bán", "Mô tả"});
            Model.setRowCount(0);
            for (ctspView ct : lctsp) {
                Object[] row = new Object[]{ct.getId(), ct.getIdsp(),
                    ct.getIdDongSP(), ct.getIdNsx(), ct.getIdmauSac(), ct.getNamBh(),
                    ct.getSoluongTon(), ct.getGiaNhap(), ct.getGiaBan(), ct.getMota()};
                Model.addRow(row);
            }

        } catch (Exception e) {
        }
    }

    private void loadtbSanPham() {
        List<ctspView> lctsp = ctSpSer.findAll();
        if (lctsp == null) {
            JOptionPane.showMessageDialog(this, "Lỗi không lấy đc dữ liệu");
        } else if (lctsp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lỗi không có dữ liệu");
        }
        DefaultTableModel Model = (DefaultTableModel) tbSanPham.getModel();
        Model.setColumnCount(0);
        Model.setColumnIdentifiers(new String[]{"ID CTSP", "Mã SP", "Dòng SP",
            "NSX", "Màu Sắc", "Năm nhập hàng", "Số lượng", "Giá Nhập", "Giá bán", "Mô tả"});
        Model.setRowCount(0);
        for (ctspView ct : lctsp) {
            Object[] row = new Object[]{ct.getId(), ct.getIdsp(),
                ct.getIdDongSP(), ct.getIdNsx(), ct.getIdmauSac(), ct.getNamBh(),
                ct.getSoluongTon(), ct.getGiaNhap(), ct.getGiaBan(), ct.getMota()};
            Model.addRow(row);
        }
    }

    private void HienttLenTXT() {
        int Index = tbSanPham.getSelectedRow();
        if (Index < 0) {
            JOptionPane.showMessageDialog(this, "Dữ liệu trống");
        } else {
            txtIdctsp.setText((String) tbSanPham.getValueAt(Index, 0));

            SanPham tsp = (SanPham) tbSanPham.getValueAt(Index, 1);
            int Lsp = cBoxSP.getItemCount();
            for (int i = 0; i < Lsp; i++) {
                ttCtsp n = cBoxSP.getItemAt(i);
                if (n.getMa().equals(tsp.getMa())) {
                    cBoxSP.setSelectedIndex(i);
                }
            }

            DongSP dsp = (DongSP) tbSanPham.getValueAt(Index, 2);
            int Ldsp = cBoxDongSp.getItemCount();
            for (int i = 0; i < Ldsp; i++) {
                ttCtsp n = cBoxDongSp.getItemAt(i);
                if (n.getTen().equals(dsp.getTen())) {
                    cBoxDongSp.setSelectedIndex(i);
                }
            }

            NSX ns = (NSX) tbSanPham.getValueAt(Index, 3);
            int Lns = cBoxNsx.getItemCount();
            for (int i = 0; i < Lns; i++) {
                ttCtsp n = cBoxNsx.getItemAt(i);
                if (n.getTen().equals(ns.getTen())) {
                    cBoxNsx.setSelectedIndex(i);
                }
            }
            MauSac ms = (MauSac) tbSanPham.getValueAt(Index, 4);
            int Lms = cBoxMauSac.getItemCount();
            for (int i = 0; i < Lms; i++) {
                ttCtsp n = cBoxMauSac.getItemAt(i);
                if (n.getTen().equals(ms.getTen())) {
                    cBoxMauSac.setSelectedIndex(i);
                }
            }
            yearPicker.setYear((int) tbSanPham.getValueAt(Index, 5));
            txtSoLuong.setText(tbSanPham.getValueAt(Index, 6).toString());
            txtGiaNhap.setText(tbSanPham.getValueAt(Index, 7).toString());
            txtGiaBan.setText(tbSanPham.getValueAt(Index, 8).toString());
            if (tbSanPham.getValueAt(Index, 9) != null) {
                txtMoTa.setText(tbSanPham.getValueAt(Index, 9).toString());
            } else {
                txtMoTa.setText(null);
            }
        }

    }

    ;
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
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtGiaNhap = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        btnAddCTSP = new javax.swing.JButton();
        btnSuaSP = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnTimMa = new javax.swing.JButton();
        btnQuayLai = new javax.swing.JButton();
        cBoxNsx = new javax.swing.JComboBox<ttCtsp>();
        cBoxMauSac = new javax.swing.JComboBox<ttCtsp>();
        cBoxDongSp = new javax.swing.JComboBox<ttCtsp>();
        yearPicker = new com.toedter.calendar.JYearChooser();
        BtnLoadtb = new javax.swing.JButton();
        btnAddDsp = new javax.swing.JButton();
        btnAddMau = new javax.swing.JButton();
        btnAddNSX = new javax.swing.JButton();
        btnAddSp = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        cBoxSP = new javax.swing.JComboBox<ttCtsp>();
        jLabel23 = new javax.swing.JLabel();
        txtIdctsp = new javax.swing.JTextField();
        BtnClearTxt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("ViewQL/Bundle"); // NOI18N
        setTitle(bundle.getString("QLyCTSP.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 750));

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

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setName("jPanel3"); // NOI18N

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        java.util.ResourceBundle bundle1 = java.util.ResourceBundle.getBundle("ViewQL/QlSanPham/Bundle"); // NOI18N
        jLabel14.setText(bundle1.getString("QLyCTSP.jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setPreferredSize(new java.awt.Dimension(90, 25));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText(bundle1.getString("QLyCTSP.jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(90, 25));

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText(bundle1.getString("QLyCTSP.jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(90, 25));

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText(bundle1.getString("QLyCTSP.jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(90, 25));

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText(bundle1.getString("QLyCTSP.jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N
        jLabel18.setPreferredSize(new java.awt.Dimension(90, 25));

        txtSoLuong.setText(bundle1.getString("QLyCTSP.txtSoLuong.text")); // NOI18N
        txtSoLuong.setName("txtSoLuong"); // NOI18N

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText(bundle1.getString("QLyCTSP.jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(90, 25));

        txtGiaNhap.setText(bundle1.getString("QLyCTSP.txtGiaNhap.text")); // NOI18N
        txtGiaNhap.setName("txtGiaNhap"); // NOI18N

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText(bundle1.getString("QLyCTSP.jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(90, 25));

        txtGiaBan.setText(bundle1.getString("QLyCTSP.txtGiaBan.text")); // NOI18N
        txtGiaBan.setName("txtGiaBan"); // NOI18N

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText(bundle1.getString("QLyCTSP.jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(90, 25));

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        txtMoTa.setName("txtMoTa"); // NOI18N
        jScrollPane2.setViewportView(txtMoTa);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(bundle1.getString("QLyCTSP.jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        btnAddCTSP.setText(bundle1.getString("QLyCTSP.btnAddCTSP.text")); // NOI18N
        btnAddCTSP.setName("btnAddCTSP"); // NOI18N
        btnAddCTSP.setPreferredSize(new java.awt.Dimension(120, 40));
        btnAddCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCTSPActionPerformed(evt);
            }
        });

        btnSuaSP.setText(bundle1.getString("QLyCTSP.btnSuaSP.text")); // NOI18N
        btnSuaSP.setName("btnSuaSP"); // NOI18N
        btnSuaSP.setPreferredSize(new java.awt.Dimension(80, 40));
        btnSuaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSPActionPerformed(evt);
            }
        });

        btnXoa.setText(bundle1.getString("QLyCTSP.btnXoa.text")); // NOI18N
        btnXoa.setName("btnXoa"); // NOI18N
        btnXoa.setPreferredSize(new java.awt.Dimension(80, 40));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnTimMa.setText(bundle1.getString("QLyCTSP.btnTimMa.text")); // NOI18N
        btnTimMa.setName("btnTimMa"); // NOI18N
        btnTimMa.setPreferredSize(new java.awt.Dimension(120, 40));
        btnTimMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimMaActionPerformed(evt);
            }
        });

        btnQuayLai.setText(bundle1.getString("QLyCTSP.btnQuayLai.text")); // NOI18N
        btnQuayLai.setName("btnQuayLai"); // NOI18N
        btnQuayLai.setPreferredSize(new java.awt.Dimension(80, 40));

        cBoxNsx.setModel(new javax.swing.DefaultComboBoxModel<ttCtsp>());
        cBoxNsx.setName("cBoxNsx"); // NOI18N

        cBoxMauSac.setModel(new javax.swing.DefaultComboBoxModel<ttCtsp>());
        cBoxMauSac.setName("cBoxMauSac"); // NOI18N

        cBoxDongSp.setModel(new javax.swing.DefaultComboBoxModel<ttCtsp>());
        cBoxDongSp.setName("cBoxDongSp"); // NOI18N

        yearPicker.setName("yearPicker"); // NOI18N

        BtnLoadtb.setText(bundle1.getString("QLyCTSP.BtnLoadtb.text")); // NOI18N
        BtnLoadtb.setName("BtnLoadtb"); // NOI18N
        BtnLoadtb.setPreferredSize(new java.awt.Dimension(110, 40));
        BtnLoadtb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLoadtbActionPerformed(evt);
            }
        });

        btnAddDsp.setText(bundle1.getString("QLyCTSP.btnAddDsp.text")); // NOI18N
        btnAddDsp.setName("btnAddDsp"); // NOI18N
        btnAddDsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDspActionPerformed(evt);
            }
        });

        btnAddMau.setText(bundle1.getString("QLyCTSP.btnAddMau.text")); // NOI18N
        btnAddMau.setName("btnAddMau"); // NOI18N
        btnAddMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMauActionPerformed(evt);
            }
        });

        btnAddNSX.setText(bundle1.getString("QLyCTSP.btnAddNSX.text")); // NOI18N
        btnAddNSX.setName("btnAddNSX"); // NOI18N
        btnAddNSX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNSXActionPerformed(evt);
            }
        });

        btnAddSp.setText(bundle1.getString("QLyCTSP.btnAddSp.text")); // NOI18N
        btnAddSp.setName("btnAddSp"); // NOI18N
        btnAddSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSpActionPerformed(evt);
            }
        });

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText(bundle1.getString("QLyCTSP.jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(90, 25));

        cBoxSP.setModel(new javax.swing.DefaultComboBoxModel<ttCtsp>());
        cBoxSP.setName("cBoxSP"); // NOI18N

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText(bundle1.getString("QLyCTSP.jLabel23.text")); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setPreferredSize(new java.awt.Dimension(90, 25));

        txtIdctsp.setText(bundle1.getString("QLyCTSP.txtIdctsp.text")); // NOI18N
        txtIdctsp.setEnabled(false);
        txtIdctsp.setName("txtIdctsp"); // NOI18N

        BtnClearTxt.setText(bundle1.getString("QLyCTSP.BtnClearTxt.text")); // NOI18N
        BtnClearTxt.setName("BtnClearTxt"); // NOI18N
        BtnClearTxt.setPreferredSize(new java.awt.Dimension(110, 40));
        BtnClearTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnClearTxtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtIdctsp))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yearPicker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSoLuong))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGiaNhap))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGiaBan))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cBoxSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAddSp, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cBoxDongSp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cBoxMauSac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cBoxNsx, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnAddDsp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnAddMau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnAddNSX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(21, 21, 21))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BtnLoadtb, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTimMa, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnClearTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAddCTSP, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addComponent(btnSuaSP, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnQuayLai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdctsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddSp)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cBoxSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cBoxNsx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddNSX))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cBoxMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddMau))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cBoxDongSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddDsp))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimMa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddCTSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSuaSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnLoadtb, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnClearTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addComponent(btnQuayLai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSanPhamMouseClicked
        // TODO add your handling code here:
        HienttLenTXT();
    }//GEN-LAST:event_tbSanPhamMouseClicked

    private void BtnLoadtbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLoadtbActionPerformed
        // TODO add your handling code here:
        loadtbSanPham();
        loadCb();
    }//GEN-LAST:event_BtnLoadtbActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        DeleteCTsp();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSPActionPerformed
        // TODO add your handling code here
        UpdateSp();
    }//GEN-LAST:event_btnSuaSPActionPerformed

    private void btnAddCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCTSPActionPerformed
        // TODO add your handling code here:
        SaveSP();
    }//GEN-LAST:event_btnAddCTSPActionPerformed

    private void btnTimMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimMaActionPerformed
        // TODO add your handling code here:
        FindByMa();
    }//GEN-LAST:event_btnTimMaActionPerformed

    private void btnAddSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSpActionPerformed
        // TODO add your handling code here:
        new QLySP().setVisible(true);

    }//GEN-LAST:event_btnAddSpActionPerformed

    private void btnAddNSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNSXActionPerformed
        // TODO add your handling code here:
        new QLyNSX().setVisible(true);
    }//GEN-LAST:event_btnAddNSXActionPerformed

    private void btnAddMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMauActionPerformed
        // TODO add your handling code here:
        new QLyMauSac().setVisible(true);
    }//GEN-LAST:event_btnAddMauActionPerformed

    private void btnAddDspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDspActionPerformed
        // TODO add your handling code here:
        new QLyDongSP().setVisible(true);
    }//GEN-LAST:event_btnAddDspActionPerformed

    private void BtnClearTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnClearTxtActionPerformed
        // TODO add your handling code here:
        ClearTxt();
    }//GEN-LAST:event_BtnClearTxtActionPerformed

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
            java.util.logging.Logger.getLogger(QLyCTSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLyCTSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLyCTSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLyCTSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLyCTSP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnClearTxt;
    private javax.swing.JButton BtnLoadtb;
    private javax.swing.JButton btnAddCTSP;
    private javax.swing.JButton btnAddDsp;
    private javax.swing.JButton btnAddMau;
    private javax.swing.JButton btnAddNSX;
    private javax.swing.JButton btnAddSp;
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JButton btnSuaSP;
    private javax.swing.JButton btnTimMa;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<ttCtsp> cBoxDongSp;
    private javax.swing.JComboBox<ttCtsp> cBoxMauSac;
    private javax.swing.JComboBox<ttCtsp> cBoxNsx;
    private javax.swing.JComboBox<ttCtsp> cBoxSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtIdctsp;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtSoLuong;
    private com.toedter.calendar.JYearChooser yearPicker;
    // End of variables declaration//GEN-END:variables
}
