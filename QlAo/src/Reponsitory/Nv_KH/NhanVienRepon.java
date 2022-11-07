/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reponsitory.Nv_KH;

import Model.Nv_KH.NhanVien;
import Model.Cv_Ch.ChucVu;
import Model.Cv_Ch.CuaHang;
import Utilities.DBConnection;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author latha
 */
public class NhanVienRepon implements INVrepo {
//    String Nhân viên to SQL

    final String Select_all_sql = "SELECT  NhanVien.Id, NhanVien.Ma, NhanVien.Ten,\n"
            + "		NhanVien.TenDem, NhanVien.Ho, NhanVien.GioiTinh,\n"
            + "		NhanVien.NgaySinh, NhanVien.DiaChi, NhanVien.Sdt,\n"
            + "		NhanVien.MatKhau, CuaHang.Id AS IdCH, CuaHang.Ma AS MaCH, \n"
            + "       ChucVu.Id AS IdCV, ChucVu.Ma AS MaCV, NhanVien.IdGuiBC, NhanVien.TrangThai\n"
            + "FROM NhanVien INNER JOIN CuaHang ON NhanVien.IdCH = CuaHang.Id INNER JOIN\n"
            + "ChucVu ON NhanVien.IdCV = ChucVu.Id where TrangThai BETWEEN 0 and 1 ";

    final String Select_ByMa_sql = "SELECT  NhanVien.Id, NhanVien.Ma, NhanVien.Ten,\n"
            + "		NhanVien.TenDem, NhanVien.Ho, NhanVien.GioiTinh,\n"
            + "		NhanVien.NgaySinh, NhanVien.DiaChi, NhanVien.Sdt,\n"
            + "		NhanVien.MatKhau, CuaHang.Id AS IdCH, CuaHang.Ma AS MaCH, \n"
            + "       ChucVu.Id AS IdCV, ChucVu.Ma AS MaCV, NhanVien.IdGuiBC, NhanVien.TrangThai\n"
            + "FROM NhanVien INNER JOIN CuaHang ON NhanVien.IdCH = CuaHang.Id INNER JOIN\n"
            + "	ChucVu ON NhanVien.IdCV = ChucVu.Id WHERE TrangThai BETWEEN 0 and 1 AND NhanVien.Ma = ?";

    final String Insert_sql = "INSERT INTO [dbo].[NhanVien]\n"
            + "           ([Id],[Ma],[Ten],[TenDem],[Ho],[GioiTinh]\n"
            + "           ,[NgaySinh],[DiaChi],[Sdt],[MatKhau]\n"
            + "           ,[IdCH],[IdCV],[IdGuiBC],[TrangThai])\n"
            + "     VALUES\n"
            + "           (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    final String Update_sql = "UPDATE [dbo].[NhanVien]\n"
            + "   SET [Ten] = ?\n"
            + "      ,[TenDem] = ?\n"
            + "      ,[Ho] = ?\n"
            + "      ,[GioiTinh] = ?\n"
            + "      ,[NgaySinh] = ?\n"
            + "      ,[DiaChi] = ?\n"
            + "      ,[Sdt] = ?\n"
            + "      ,[MatKhau] = ?\n"
            + "      ,[IdCH] = ?\n"
            + "      ,[IdCV] = ?\n"
            + "      ,[TrangThai] = ?\n"
            + " WHERE Id = ?";
    final String Delete_sql = "UPDATE [dbo].[NhanVien]\n"
            + "   SET  [TrangThai] = null\n"
            + " WHERE Id = ?";
// -----------------------------------------------------------------------------

    @Override
    public List<NhanVien> findAll() {
        return getSelectNv(Select_all_sql);
    }

    @Override
    public List<NhanVien> findById(String Ma) {
        return getSelectNv(Select_ByMa_sql, Ma);
    }

    @Override
    public NhanVien saveNV(NhanVien Nv) {
        DBConnection.Excute(Insert_sql, Nv.getId(), Nv.getMa(),
                Nv.getTen(), Nv.getTenDem(), Nv.getHo(),
                Nv.getGioiTinh(), Nv.getSinhNhat(), Nv.getDiaChi(),
                Nv.getSdt(), Nv.getMK(), Nv.getIdCuaHang().getId(),
                Nv.getIdCV().getId(), Nv.getIdguibc(), Nv.getTrangthai());
        return Nv;
    }

    @Override
    public NhanVien UpdateNv(NhanVien Nv) {
        DBConnection.Excute(Update_sql, Nv.getTen(), Nv.getTenDem(),
                Nv.getHo(), Nv.getGioiTinh(), Nv.getSinhNhat(),
                Nv.getDiaChi(), Nv.getSdt(), Nv.getMK(),
                Nv.getIdCuaHang().getId(), Nv.getIdCV().getId(), 
                Nv.getTrangthai(), Nv.getId());
        return Nv;
    }

    private List<NhanVien> getSelectNv(String sql, Object... args) {
        try {
            List<NhanVien> LNV = new ArrayList<>();
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                NhanVien NV = new NhanVien(rs.getString("Id"),
                        rs.getString("Ma"), rs.getString("Ten"),
                        rs.getString("TenDem"), rs.getString("Ho"),
                        rs.getString("GioiTinh"),
                        rs.getString("NgaySinh"),
                        rs.getString("DiaChi"),
                        rs.getString("Sdt"),
                        rs.getString("MatKhau"),
                        new CuaHang(rs.getString("IdCH"),
                                rs.getString("MaCH"),
                                null, null, null, null),
                        new ChucVu(rs.getString("IdCV"),
                                rs.getString("MaCV"), null),
                        rs.getString("IdGuiBC"),
                        rs.getInt("TrangThai"));
                LNV.add(NV);
            }
            return LNV;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

    @Override
    public String DeleteNV(String Id) {
        DBConnection.Excute(Delete_sql, Id);
        return Id;
    }
}
