/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ViewQL.QlKhachHang;

import Service.Nv_KH.IKhService;
import Service.Nv_KH.QLkhService;
import ViewModel.khView;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author latha
 */
public class QLyKhachHang extends javax.swing.JFrame {

    private final IKhService khrepo = new QLkhService();

    /**
     * Creates new form QLyAo
     */
    public QLyKhachHang() {
        initComponents();
        setLocationRelativeTo(null);
        SetttingYear();
        LoadTb();
    }

    private void SetttingYear() {
        txtSn.setDate(LocalDate.parse("2005-12-31"));
        txtSn.getSettings().setDateRangeLimits(
                LocalDate.parse("1980-01-01"),
                LocalDate.parse("2005-12-31"));
        txtSn.getSettings().setAllowKeyboardEditing(false);
    }

    private void ClearTxt() {
        txtDiaChi.setText(null);
        txtHo.setText(null);
        txtId.setText(null);
        txtMK.setText(null);
        txtMa.setText(null);
        txtQGia.setText(null);
        txtSdt.setText(null);
        txtSn.setDate(LocalDate.parse("2005-12-31"));
        txtTen.setText(null);
        txtTenDem.setText(null);
        txtThanhPho.setText(null);
    }

    private void LoadTb() {
        List<khView> lkh = khrepo.findAll();
        if (lkh == null) {
            JOptionPane.showMessageDialog(this, "Lỗi không lấy đc dữ liệu");
        } else if (lkh.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lỗi không có dữ liệu");
        }
        DefaultTableModel Model = (DefaultTableModel) tbKhachHang.getModel();
        Model.setColumnCount(0);
        Model.setColumnIdentifiers(new String[]{"Id", "Mã", "Họ", "Tên Đệm", "Tên",
            "Sinh Nhật", "SDT", "Mật Khẩu", "Thành Phố", "Quốc Gia",
            "Địa Chỉ"
        });
        Model.setRowCount(0);
        for (khView v : lkh) {
            Object[] row = new Object[]{v.getId(), v.getMa(), v.getHo(),
                v.getTenDem(), v.getTen(), v.getNgaysinh(), v.getSdt(), v.getMk(),
                v.getThanhpho(), v.getQuocgia(), v.getDiachi()};
            Model.addRow(row);
        }
    }

    private void LoadKhByMa() {
        String ma = JOptionPane.showInputDialog("Nhập Mã Khách hàng");
        List<khView> lkh = khrepo.findByMa(ma);
        if (lkh == null) {
            JOptionPane.showMessageDialog(this, "Lỗi không lấy đc dữ liệu");
        } else if (lkh.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lỗi không có dữ liệu");
        }
        DefaultTableModel Model = (DefaultTableModel) tbKhachHang.getModel();
        Model.setColumnCount(0);
        Model.setColumnIdentifiers(new String[]{"Id", "Mã", "Họ", "Tên Đệm", "Tên",
            "Sinh Nhật", "SDT", "Mật Khẩu", "Thành Phố", "Quốc Gia",
            "Địa Chỉ"
        });
        Model.setRowCount(0);
        for (khView v : lkh) {
            Object[] row = new Object[]{v.getId(), v.getMa(), v.getHo(),
                v.getTenDem(), v.getTen(), v.getNgaysinh(), v.getSdt(), v.getMk(),
                v.getThanhpho(), v.getQuocgia(), v.getDiachi()};
            Model.addRow(row);
        }

    }

    private boolean HienTxt() {
        int Index = tbKhachHang.getSelectedRow();
        if (Index < 0) {
            JOptionPane.showMessageDialog(this, "Dữ liệu trống");
            return false;
        } else {
            txtId.setText((String) tbKhachHang.getValueAt(Index, 0));
            txtMa.setText((String) tbKhachHang.getValueAt(Index, 1));
            txtHo.setText((String) tbKhachHang.getValueAt(Index, 2));
            txtTenDem.setText((String) tbKhachHang.getValueAt(Index, 3));
            txtTen.setText((String) tbKhachHang.getValueAt(Index, 4));
            txtSn.setDate(LocalDate.parse((String) tbKhachHang.getValueAt(Index, 5)));
            txtSdt.setText((String) tbKhachHang.getValueAt(Index, 6));
            txtMK.setText((String) tbKhachHang.getValueAt(Index, 7));
            txtThanhPho.setText((String) tbKhachHang.getValueAt(Index, 8));
            txtQGia.setText((String) tbKhachHang.getValueAt(Index, 9));
            txtDiaChi.setText((String) tbKhachHang.getValueAt(Index, 10));
            return true;
        }
    }

    private boolean validateTxt() {
        if (txtMa.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập Mã");
            return false;
        }
        if (txtHo.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập Họ");
            return false;
        }
        if (txtTenDem.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập Tên Đệm");
            return false;
        }
        if (txtTen.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập Tên");
            return false;
        }
        if (txtSdt.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập SDT");
            return false;
        }
        if (txtMK.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập MK");
            return false;
        }
        if (txtThanhPho.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập Thành Phố");
            return false;
        }
        if (txtQGia.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Chưa Nha");
            return false;
        }
        if (txtDiaChi.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "");
            return false;
        }
        return true;
    }

    private khView getData() {
        khView khv = new khView();
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        khv.setId(id);
        khv.setMa(txtMa.getText().toUpperCase());
        khv.setTenDem(txtTenDem.getText());
        khv.setTen(txtTen.getText());
        khv.setHo(txtHo.getText());
        khv.setQuocgia(txtQGia.getText());
        khv.setThanhpho(txtThanhPho.getText());
        khv.setDiachi(txtDiaChi.getText());
        khv.setNgaysinh(txtSn.getText());
        khv.setSdt(txtSdt.getText());
        khv.setMk(txtMK.getText());
        return khv;
    }

    private boolean SaveKH() {
        try {
            if (validateTxt()) {
                khView khv = getData();
                khrepo.save(khv);
                JOptionPane.showMessageDialog(this, "Lưu Thành Công");
                ClearTxt();
                return true;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi Bởi :" + e.getMessage());
            return false;
        }
        return false;
    }

    private boolean UpdateKH() {
        try {
            if (validateTxt()) {
                khView khv = getData();
                khv.setId(txtId.getText());
                khrepo.Update(khv);
                JOptionPane.showMessageDialog(this, "Lưu Thành Công");
                ClearTxt();
                return true;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi Bởi :" + e.getMessage());
            return false;
        }
        return false;
    }

    private boolean DeleteKh() {
        try {
            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Lỗi ko có ID");
                return false;
            } else if (JOptionPane.showConfirmDialog(this,
                    "Xóa Khách Hàng này Thật Không !", "Xóa Khách Hàng",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                String Id = txtId.getText();
                khrepo.Delete(Id);
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
        tbKhachHang = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtHo = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtMK = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtTenDem = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnTimMa = new javax.swing.JButton();
        BtnLoadtb = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        jLabel36 = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();
        txtSn = new com.github.lgooddatepicker.components.DatePicker();
        txtThanhPho = new javax.swing.JTextField();
        txtQGia = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("ViewQL/Bundle"); // NOI18N
        setTitle(bundle.getString("QLyNv.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 750));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tbKhachHang.setModel(new javax.swing.table.DefaultTableModel(
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
        tbKhachHang.setName("tbKhachHang"); // NOI18N
        tbKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbKhachHang);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setName("jPanel3"); // NOI18N

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        java.util.ResourceBundle bundle1 = java.util.ResourceBundle.getBundle("ViewQL/QlKhachHang/Bundle"); // NOI18N
        jLabel12.setText(bundle1.getString("QLyKhachHang.jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.setPreferredSize(new java.awt.Dimension(90, 25));

        txtMa.setText(bundle1.getString("QLyKhachHang.txtMa.text")); // NOI18N
        txtMa.setName("txtMa"); // NOI18N

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText(bundle1.getString("QLyKhachHang.jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setPreferredSize(new java.awt.Dimension(90, 25));

        txtHo.setText(bundle1.getString("QLyKhachHang.txtHo.text")); // NOI18N
        txtHo.setName("txtHo"); // NOI18N

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText(bundle1.getString("QLyKhachHang.jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setPreferredSize(new java.awt.Dimension(90, 25));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText(bundle1.getString("QLyKhachHang.jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(90, 25));

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText(bundle1.getString("QLyKhachHang.jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(90, 25));

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText(bundle1.getString("QLyKhachHang.jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N
        jLabel18.setPreferredSize(new java.awt.Dimension(90, 25));

        txtSdt.setText(bundle1.getString("QLyKhachHang.txtSdt.text")); // NOI18N
        txtSdt.setName("txtSdt"); // NOI18N

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText(bundle1.getString("QLyKhachHang.jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(90, 25));

        txtMK.setText(bundle1.getString("QLyKhachHang.txtMK.text")); // NOI18N
        txtMK.setName("txtMK"); // NOI18N

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText(bundle1.getString("QLyKhachHang.jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(90, 25));

        txtTenDem.setText(bundle1.getString("QLyKhachHang.txtTenDem.text")); // NOI18N
        txtTenDem.setName("txtTenDem"); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(bundle1.getString("QLyKhachHang.jLabel1.text")); // NOI18N
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        btnAdd.setText(bundle1.getString("QLyKhachHang.btnAdd.text")); // NOI18N
        btnAdd.setName("btnAdd"); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnSua.setText(bundle1.getString("QLyKhachHang.btnSua.text")); // NOI18N
        btnSua.setName("btnSua"); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText(bundle1.getString("QLyKhachHang.btnXoa.text")); // NOI18N
        btnXoa.setName("btnXoa"); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnTimMa.setText(bundle1.getString("QLyKhachHang.btnTimMa.text")); // NOI18N
        btnTimMa.setName("btnTimMa"); // NOI18N
        btnTimMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimMaActionPerformed(evt);
            }
        });

        BtnLoadtb.setText(bundle1.getString("QLyKhachHang.BtnLoadtb.text")); // NOI18N
        BtnLoadtb.setName("BtnLoadtb"); // NOI18N
        BtnLoadtb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLoadtbActionPerformed(evt);
            }
        });

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText(bundle1.getString("QLyKhachHang.jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(90, 25));

        txtTen.setText(bundle1.getString("QLyKhachHang.txtTen.text")); // NOI18N
        txtTen.setName("txtTen"); // NOI18N

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setText(bundle1.getString("QLyKhachHang.jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(90, 25));

        txtId.setEditable(false);
        txtId.setText(bundle1.getString("QLyKhachHang.txtId.text")); // NOI18N
        txtId.setName("txtId"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        txtDiaChi.setColumns(20);
        txtDiaChi.setRows(5);
        txtDiaChi.setName("txtDiaChi"); // NOI18N
        jScrollPane2.setViewportView(txtDiaChi);

        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText(bundle1.getString("QLyKhachHang.jLabel36.text")); // NOI18N
        jLabel36.setName("jLabel36"); // NOI18N
        jLabel36.setPreferredSize(new java.awt.Dimension(90, 25));

        btnClear.setText(bundle1.getString("QLyKhachHang.btnClear.text")); // NOI18N
        btnClear.setName("btnClear"); // NOI18N
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        txtSn.setName("txtSn"); // NOI18N

        txtThanhPho.setText(bundle1.getString("QLyKhachHang.txtThanhPho.text")); // NOI18N
        txtThanhPho.setName("txtThanhPho"); // NOI18N

        txtQGia.setText(bundle1.getString("QLyKhachHang.txtQGia.text")); // NOI18N
        txtQGia.setName("txtQGia"); // NOI18N

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
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMa)
                            .addComponent(txtHo)
                            .addComponent(txtTenDem)
                            .addComponent(txtTen)
                            .addComponent(txtId)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSdt)
                            .addComponent(txtMK)
                            .addComponent(jScrollPane2)
                            .addComponent(txtSn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtThanhPho, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtQGia))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTimMa)
                    .addComponent(BtnLoadtb, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd)
                    .addComponent(btnClear))
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoa)
                    .addComponent(btnSua))
                .addGap(16, 16, 16))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenDem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtThanhPho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimMa)
                    .addComponent(btnAdd)
                    .addComponent(btnSua))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa)
                    .addComponent(btnClear)
                    .addComponent(BtnLoadtb))
                .addGap(87, 87, 87))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        SaveKH();
    }//GEN-LAST:event_btnAddActionPerformed

    private void BtnLoadtbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLoadtbActionPerformed
        // TODO add your handling code here:
        LoadTb();
    }//GEN-LAST:event_BtnLoadtbActionPerformed

    private void tbKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKhachHangMouseClicked
        HienTxt();

    }//GEN-LAST:event_tbKhachHangMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        UpdateKH();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        DeleteKh();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnTimMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimMaActionPerformed
        LoadKhByMa();
    }//GEN-LAST:event_btnTimMaActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        ClearTxt();
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
            java.util.logging.Logger.getLogger(QLyKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLyKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLyKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLyKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLyKhachHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnLoadtb;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTimMa;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup grRdGioiTinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbKhachHang;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtHo;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtMK;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtQGia;
    private javax.swing.JTextField txtSdt;
    private com.github.lgooddatepicker.components.DatePicker txtSn;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTenDem;
    private javax.swing.JTextField txtThanhPho;
    // End of variables declaration//GEN-END:variables
}
