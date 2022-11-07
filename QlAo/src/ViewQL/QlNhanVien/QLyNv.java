/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ViewQL.QlNhanVien;

import Model.Cv_Ch.ChucVu;
import Model.Cv_Ch.CuaHang;
import Service.CV_CH.Cv_Ch_service;
import Service.Nv_KH.QLnVienService;
import ViewModel.chView;
import ViewModel.cvView;
import ViewModel.nvView;
import java.awt.Color;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author latha
 */
public class QLyNv extends javax.swing.JFrame {

    private final Cv_Ch_service CV_CH_repo = new Cv_Ch_service();
    private final QLnVienService Nv_repo = new QLnVienService();

    /**
     * Creates new form QLyAo
     */
    public QLyNv() {
        initComponents();
        loadCb();
        setLocationRelativeTo(null);
        LoadTB();
        SetttingYear();
    }

    private void SetttingYear() {
        txtSn.setDate(LocalDate.parse("2005-12-31"));
        txtSn.getSettings().setDateRangeLimits(
                LocalDate.parse("1980-01-01"),
                LocalDate.parse("2005-12-31"));
        txtSn.getSettings().setAllowKeyboardEditing(false);
    }

    private void loadCb() {
//=======================DongSP CBOX ==============================================
        cBoxCH.setBackground(Color.WHITE);
        cBoxCH.removeAllItems();
        List<chView> lchv = CV_CH_repo.findAllCH();
        for (chView v : lchv) {
            cBoxCH.addItem(v);
        }

//=======================NSX CBOX ==============================================
        cBoxCV.setBackground(Color.WHITE);
        cBoxCV.removeAllItems();
        List<cvView> Lcv = CV_CH_repo.findAllCV();
        for (cvView vi : Lcv) {
            cBoxCV.addItem(vi);
        }
    }

    private boolean validateTxt() {
        if (txtMaNV.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập Mã NV");
            return false;
        }
        if (txtHoNV.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập Họ");
            return false;
        }
        if (txtTenDem.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập Tên Đệm");
            return false;
        }
        if (txtTen.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập tên");
            return false;
        }
        if (!grRdGioiTinh.getSelection().isSelected()) {
            JOptionPane.showMessageDialog(this, "Chưa Chọn giới tính");
            return false;
        }
        if (txtSdt.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập SDT");
            return false;
        }
        if (txtMK.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập Mật khẩu");
            return false;
        }
        if (txtDiaChi.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập Địa Chỉ");
            return false;
        }
        return true;
    }

    private void LoadTB() {
        List<nvView> lnv = Nv_repo.findAll();
        if (lnv == null) {
            JOptionPane.showMessageDialog(this, "Lỗi không lấy đc dữ liệu");
        } else if (lnv.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lỗi không có dữ liệu");
        }
        DefaultTableModel Model = (DefaultTableModel) tbNhanVien.getModel();
        Model.setColumnCount(0);
        Model.setColumnIdentifiers(new String[]{"Id", "Mã", "Họ", "Tên Đẹm", "Tên",
            "Giới Tính", "Sinh Nhật", "Địa Chỉ", "SDT", "MK", "Chức Vụ", "Cửa hàng",
            "IdGửiBC", "Trạng Thái"
        });
        Model.setRowCount(0);
        for (nvView v : lnv) {
            Object[] row = new Object[]{
                v.getId(), v.getMa(), v.getHo(), v.getTenDem(), v.getTen(),
                v.getGioiTinh(), v.getSinhNhat(), v.getDiaChi(), v.getSdt(),
                v.getMK(), v.getIdCV().getMa(), v.getIdCuaHang().getMa(),
                v.getIdguibc(), v.getTrangthai() == 1 ? "Đang Làm" : "Đã Nghỉ"};
            Model.addRow(row);
        }
    }

    private boolean HienttLenTxt() {
        int Index = tbNhanVien.getSelectedRow();
        if (Index < 0) {
            JOptionPane.showMessageDialog(this, "Dữ liệu trống");
            return false;
        } else {
            txtIdNV.setText((String) tbNhanVien.getValueAt(Index, 0));
            txtMaNV.setText((String) tbNhanVien.getValueAt(Index, 1));
            txtHoNV.setText((String) tbNhanVien.getValueAt(Index, 2));
            txtTenDem.setText((String) tbNhanVien.getValueAt(Index, 3));
            txtTen.setText((String) tbNhanVien.getValueAt(Index, 4));
            if (tbNhanVien.getValueAt(Index, 5).equals("Nam")) {
                rdNam.setSelected(true);
            } else {
                rdNu.setSelected(true);
            }
            txtSn.setDate(LocalDate.parse((String) tbNhanVien.getValueAt(Index, 6)));
            txtDiaChi.setText((String) tbNhanVien.getValueAt(Index, 7));
            txtSdt.setText((String) tbNhanVien.getValueAt(Index, 8));
            txtMK.setText((String) tbNhanVien.getValueAt(Index, 9));
            String cv = (String) tbNhanVien.getValueAt(Index, 10);
            int lcv = cBoxCV.getItemCount();
            for (int i = 0; i < lcv; i++) {
                cvView v = cBoxCV.getItemAt(i);
                if (v.getMa().equals(cv)) {
                    cBoxCV.setSelectedIndex(i);
                }
            }
            String ch = (String) tbNhanVien.getValueAt(Index, 11);
            int lch = cBoxCH.getItemCount();
            for (int i = 0; i < lch; i++) {
                chView h = cBoxCH.getItemAt(i);
                if (h.getMa().equals(ch)) {
                    cBoxCH.setSelectedIndex(i);
                }
            }
            txtidGuiBC.setText((String) tbNhanVien.getValueAt(Index, 12));
            cbTT.setSelected(tbNhanVien.getValueAt(Index, 13).equals("Đang Làm"));
            return true;
        }
    }

    private nvView getTxt() {
        nvView nvm = new nvView();
        UUID uuid = UUID.randomUUID();
        String idnv = uuid.toString();
        chView chv = (chView) cBoxCH.getSelectedItem();
        CuaHang ch = new CuaHang(chv.getId(), chv.getMa(), chv.getTen(),
                chv.getDiaChi(), chv.getThanhpho(),
                chv.getQuocgia());
        cvView cvv = (cvView) cBoxCV.getSelectedItem();
        ChucVu cv = new ChucVu(cvv.getId(), cvv.getMa(), cvv.getTen());
        nvm.setId(idnv);
        nvm.setMa(txtMaNV.getText().toUpperCase());
        nvm.setHo(txtHoNV.getText());
        nvm.setTenDem(txtTenDem.getText());
        nvm.setTen(txtTen.getText());
        nvm.setIdguibc(idnv);
        nvm.setSinhNhat(txtSn.getText());
        if (rdNam.isSelected()) {
            nvm.setGioiTinh("Nam");
        } else if (rdNu.isSelected()) {
            nvm.setGioiTinh("Nữ");
        }
        if (cbTT.isSelected()) {
            nvm.setTrangthai(1);
        } else {
            nvm.setTrangthai(0);
        }
        nvm.setDiaChi(txtDiaChi.getText());
        nvm.setIdCV(cv);
        nvm.setIdCuaHang(ch);
        nvm.setMK(txtMK.getText());
        nvm.setSdt(txtSdt.getText());
        return nvm;
    }

    private boolean SaveNV() {
        try {
            if (validateTxt()) {
                nvView nv = getTxt();
                Nv_repo.saveNV(nv);
                JOptionPane.showMessageDialog(this, "Lưu Thành Công");
                ClearTxt();
                return true;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lưu Thất bại" + e.getMessage());
            return false;
        }
        return false;
    }

    private boolean UpdateNv() {
        try {
            if (validateTxt()) {
                nvView nv = getTxt();
                nv.setId(txtIdNV.getText());
                nv.setIdguibc(txtidGuiBC.getText());
                Nv_repo.UpdateNv(nv);
                JOptionPane.showMessageDialog(this, "Sửa Thành Công");
                ClearTxt();
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sửa Thất bại" + e.getMessage());
            return false;
        }
        return false;
    }

    private boolean DeleteNv() {
        try {
            if (txtIdNV.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Lỗi ko có ID");
                return false;
            } else if (JOptionPane.showConfirmDialog(this,
                    "Xóa Nhân Viên này Thật Không !", "Xóa Nhân Viên",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                String Id = txtIdNV.getText();
                Nv_repo.DeleteNV(Id);
                JOptionPane.showMessageDialog(this, "Xóa Thành Công");
                ClearTxt();
                return true;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Xóa Lỗi vì: " + e.getMessage());
            return false;
        }
        return false;
    }

    private boolean FindNvByMa() {
        String Ma = JOptionPane.showInputDialog("Nhập Mã Muốn Tìm");
        List<nvView> lnv = Nv_repo.findById(Ma);
        if (lnv.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lỗi không tìm thấy");
            LoadTB();
            return false;
        } else {
            DefaultTableModel Model = (DefaultTableModel) tbNhanVien.getModel();
            Model.setColumnCount(0);
            Model.setColumnIdentifiers(new String[]{"Id", "Mã", "Họ", "Tên Đẹm", "Tên",
                "Giới Tính", "Sinh Nhật", "Địa Chỉ", "SDT", "MK", "Chức Vụ", "Cửa hàng",
                "IdGửiBC", "Trạng Thái"
            });
            Model.setRowCount(0);
            for (nvView v : lnv) {
                Object[] row = new Object[]{
                    v.getId(), v.getMa(), v.getHo(), v.getTenDem(), v.getTen(),
                    v.getGioiTinh(), v.getSinhNhat(), v.getDiaChi(), v.getSdt(),
                    v.getMK(), v.getIdCV().getMa(), v.getIdCuaHang().getMa(),
                    v.getIdguibc(), v.getTrangthai() == 1 ? "Đang Làm" : "Đã Nghỉ"};
                Model.addRow(row);
            }
            return true;
        }

    }

    private void ClearTxt() {
        txtDiaChi.setText(null);
        txtHoNV.setText(null);
        txtIdNV.setText(null);
        txtMK.setText(null);
        txtMaNV.setText(null);
        txtSdt.setText(null);
        txtSn.setDate(LocalDate.parse("2005-12-31"));
        txtTen.setText(null);
        txtTenDem.setText(null);
        txtidGuiBC.setText(null);
        grRdGioiTinh.clearSelection();
        cbTT.setSelected(false);
        cBoxCH.setSelectedIndex(0);
        cBoxCV.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grRdGioiTinh = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbNhanVien = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtHoNV = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtMK = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtTenDem = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnAddSP = new javax.swing.JButton();
        btnSuaSP = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnTimMa = new javax.swing.JButton();
        cBoxCV = new javax.swing.JComboBox<cvView>();
        cBoxCH = new javax.swing.JComboBox<chView>();
        BtnLoadtb = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtIdNV = new javax.swing.JTextField();
        rdNam = new javax.swing.JRadioButton();
        rdNu = new javax.swing.JRadioButton();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        jLabel36 = new javax.swing.JLabel();
        cbTT = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        txtidGuiBC = new javax.swing.JTextField();
        txtSn = new com.github.lgooddatepicker.components.DatePicker();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("ViewQL/Bundle"); // NOI18N
        setTitle(bundle.getString("QLyNv.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 750));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tbNhanVien.setModel(new javax.swing.table.DefaultTableModel(
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
        tbNhanVien.setName("tbNhanVien"); // NOI18N
        tbNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbNhanVien);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setName("jPanel3"); // NOI18N

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        java.util.ResourceBundle bundle1 = java.util.ResourceBundle.getBundle("ViewQL/QlNhanVien/Bundle"); // NOI18N
        jLabel12.setText(bundle1.getString("QLyNv.jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.setPreferredSize(new java.awt.Dimension(90, 25));

        txtMaNV.setText(bundle1.getString("QLyNv.txtMaNV.text")); // NOI18N
        txtMaNV.setName("txtMaNV"); // NOI18N

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText(bundle1.getString("QLyNv.jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setPreferredSize(new java.awt.Dimension(90, 25));

        txtHoNV.setText(bundle1.getString("QLyNv.txtHoNV.text")); // NOI18N
        txtHoNV.setName("txtHoNV"); // NOI18N

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText(bundle1.getString("QLyNv.jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setPreferredSize(new java.awt.Dimension(90, 25));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText(bundle1.getString("QLyNv.jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(90, 25));

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText(bundle1.getString("QLyNv.jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(90, 25));

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText(bundle1.getString("QLyNv.jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(90, 25));

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText(bundle1.getString("QLyNv.jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N
        jLabel18.setPreferredSize(new java.awt.Dimension(90, 25));

        txtSdt.setText(bundle1.getString("QLyNv.txtSdt.text")); // NOI18N
        txtSdt.setName("txtSdt"); // NOI18N

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText(bundle1.getString("QLyNv.jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(90, 25));

        txtMK.setText(bundle1.getString("QLyNv.txtMK.text")); // NOI18N
        txtMK.setName("txtMK"); // NOI18N

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText(bundle1.getString("QLyNv.jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(90, 25));

        txtTenDem.setText(bundle1.getString("QLyNv.txtTenDem.text")); // NOI18N
        txtTenDem.setName("txtTenDem"); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(bundle1.getString("QLyNv.jLabel1.text")); // NOI18N
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        btnAddSP.setText(bundle1.getString("QLyNv.btnAddSP.text")); // NOI18N
        btnAddSP.setName("btnAddSP"); // NOI18N
        btnAddSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSPActionPerformed(evt);
            }
        });

        btnSuaSP.setText(bundle1.getString("QLyNv.btnSuaSP.text")); // NOI18N
        btnSuaSP.setName("btnSuaSP"); // NOI18N
        btnSuaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSPActionPerformed(evt);
            }
        });

        btnXoa.setText(bundle1.getString("QLyNv.btnXoa.text")); // NOI18N
        btnXoa.setName("btnXoa"); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnTimMa.setText(bundle1.getString("QLyNv.btnTimMa.text")); // NOI18N
        btnTimMa.setName("btnTimMa"); // NOI18N
        btnTimMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimMaActionPerformed(evt);
            }
        });

        cBoxCV.setModel(new javax.swing.DefaultComboBoxModel<cvView>());
        cBoxCV.setName("cBoxCV"); // NOI18N

        cBoxCH.setModel(new javax.swing.DefaultComboBoxModel<chView>());
        cBoxCH.setName("cBoxCH"); // NOI18N

        BtnLoadtb.setText(bundle1.getString("QLyNv.BtnLoadtb.text")); // NOI18N
        BtnLoadtb.setName("BtnLoadtb"); // NOI18N
        BtnLoadtb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLoadtbActionPerformed(evt);
            }
        });

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText(bundle1.getString("QLyNv.jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(90, 25));

        txtTen.setText(bundle1.getString("QLyNv.txtTen.text")); // NOI18N
        txtTen.setName("txtTen"); // NOI18N

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setText(bundle1.getString("QLyNv.jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(90, 25));

        txtIdNV.setEditable(false);
        txtIdNV.setText(bundle1.getString("QLyNv.txtIdNV.text")); // NOI18N
        txtIdNV.setName("txtIdNV"); // NOI18N

        grRdGioiTinh.add(rdNam);
        rdNam.setText(bundle1.getString("QLyNv.rdNam.text")); // NOI18N
        rdNam.setName("rdNam"); // NOI18N

        grRdGioiTinh.add(rdNu);
        rdNu.setText(bundle1.getString("QLyNv.rdNu.text")); // NOI18N
        rdNu.setName("rdNu"); // NOI18N

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText(bundle1.getString("QLyNv.jLabel35.text")); // NOI18N
        jLabel35.setName("jLabel35"); // NOI18N
        jLabel35.setPreferredSize(new java.awt.Dimension(90, 25));

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        txtDiaChi.setColumns(20);
        txtDiaChi.setRows(5);
        txtDiaChi.setName("txtDiaChi"); // NOI18N
        jScrollPane2.setViewportView(txtDiaChi);

        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText(bundle1.getString("QLyNv.jLabel36.text")); // NOI18N
        jLabel36.setName("jLabel36"); // NOI18N
        jLabel36.setPreferredSize(new java.awt.Dimension(90, 25));

        cbTT.setText(bundle1.getString("QLyNv.cbTT.text")); // NOI18N
        cbTT.setName("cbTT"); // NOI18N

        jButton1.setText(bundle1.getString("QLyNv.jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnClear.setText(bundle1.getString("QLyNv.btnClear.text")); // NOI18N
        btnClear.setName("btnClear"); // NOI18N
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText(bundle1.getString("QLyNv.jLabel23.text")); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setPreferredSize(new java.awt.Dimension(90, 25));

        txtidGuiBC.setEditable(false);
        txtidGuiBC.setText(bundle1.getString("QLyNv.txtidGuiBC.text")); // NOI18N
        txtidGuiBC.setName("txtidGuiBC"); // NOI18N

        txtSn.setName("txtSn"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaNV)
                            .addComponent(txtHoNV)
                            .addComponent(cBoxCV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cBoxCH, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addComponent(txtSdt)
                            .addComponent(txtMK)
                            .addComponent(txtTenDem)
                            .addComponent(txtTen)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rdNam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(126, 126, 126)
                                .addComponent(rdNu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtIdNV)
                            .addComponent(jScrollPane2)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cbTT)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtSn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BtnLoadtb, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimMa))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnClear)
                            .addComponent(btnAddSP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSuaSP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoa))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtidGuiBC)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtIdNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtHoNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTenDem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdNam)
                            .addComponent(rdNu)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cBoxCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cBoxCH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtidGuiBC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTT)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimMa)
                    .addComponent(btnXoa)
                    .addComponent(btnAddSP)
                    .addComponent(btnSuaSP))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnLoadtb)
                    .addComponent(btnClear))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSPActionPerformed
        // TODO add your handling code here:
        SaveNV();
        LoadTB();
        loadCb();
        ClearTxt();
    }//GEN-LAST:event_btnAddSPActionPerformed

    private void BtnLoadtbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLoadtbActionPerformed
        // TODO add your handling code here:
        LoadTB();
        loadCb();
    }//GEN-LAST:event_BtnLoadtbActionPerformed

    private void tbNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNhanVienMouseClicked
        // TODO add your handling code here:
        HienttLenTxt();

    }//GEN-LAST:event_tbNhanVienMouseClicked

    private void btnSuaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSPActionPerformed
        // TODO add your handling code here
        UpdateNv();
        LoadTB();
        loadCb();
        ClearTxt();
    }//GEN-LAST:event_btnSuaSPActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        DeleteNv();
        LoadTB();
        loadCb();
        ClearTxt();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnTimMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimMaActionPerformed
        // TODO add your handling code here:
        FindNvByMa();
    }//GEN-LAST:event_btnTimMaActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        ClearTxt();
    }//GEN-LAST:event_btnClearActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new QlCuaHang().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(QLyNv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLyNv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLyNv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLyNv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLyNv().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnLoadtb;
    private javax.swing.JButton btnAddSP;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSuaSP;
    private javax.swing.JButton btnTimMa;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<chView> cBoxCH;
    private javax.swing.JComboBox<cvView> cBoxCV;
    private javax.swing.JCheckBox cbTT;
    private javax.swing.ButtonGroup grRdGioiTinh;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdNam;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JTable tbNhanVien;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtHoNV;
    private javax.swing.JTextField txtIdNV;
    private javax.swing.JTextField txtMK;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtSdt;
    private com.github.lgooddatepicker.components.DatePicker txtSn;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTenDem;
    private javax.swing.JTextField txtidGuiBC;
    // End of variables declaration//GEN-END:variables
}
