/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reponsitory.Nv_KH;

import Model.Nv_KH.KhachHang;
import Utilities.DBConnection;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author latha
 */
public class KhachHangRepo implements IKHrepo {
//    String Kết  nối JDBC

    final String Select_all_sql = "SELECT [Id],[Ma],[Ten],[TenDem],[Ho],[NgaySinh],[Sdt]\n"
            + "      ,[DiaChi],[ThanhPho],[QuocGia],[MatKhau]\n"
            + "  FROM [dbo].[KhachHang]";

    final String Select_ByMa_sql = "SELECT [Id],[Ma],[Ten],[TenDem],[Ho],[NgaySinh],[Sdt]\n"
            + "      ,[DiaChi],[ThanhPho],[QuocGia],[MatKhau]\n"
            + "  FROM [dbo].[KhachHang] WHERE Ma = ?";

    final String Insert_sql = "INSERT INTO [dbo].[KhachHang]\n"
            + "           ([Id],[Ma],[Ten],[TenDem],[Ho],[NgaySinh]\n"
            + "           ,[Sdt],[DiaChi],[ThanhPho],[QuocGia]\n"
            + "           ,[MatKhau])\n"
            + "     VALUES (?,?,?,?,?,?,?,?,?,?,?)";

    final String Update_ByMa_sql = "UPDATE [dbo].[KhachHang]\n"
            + "   SET [Ten] =	?\n"
            + "      ,[TenDem] = ?,[Ho] = ?\n"
            + "      ,[NgaySinh] = ?,[Sdt] = ?\n"
            + "      ,[DiaChi] = ?,[ThanhPho] = ?\n"
            + "      ,[QuocGia] = ? ,[MatKhau] = ?\n"
            + " WHERE [Id] = ?";

    final String Delete_KH_sql = "DELETE FROM [dbo].[KhachHang]\n"
            + "      WHERE [Id] = ?";

//    =-==-=====================================================================
    @Override
    public List<KhachHang> findAll() {
        return getSelectNv(Select_all_sql);
    }

    @Override
    public List<KhachHang> findByMa(String Ma) {
        return getSelectNv(Select_ByMa_sql, Ma);
    }

    @Override
    public KhachHang save(KhachHang kh) {
        DBConnection.Excute(Insert_sql, kh.getId(), kh.getMa(),
                kh.getTen(), kh.getTenDem(), kh.getHo(),
                kh.getNgaysinh(), kh.getSdt(), kh.getDiachi(),
                kh.getThanhpho(), kh.getQuocgia(), kh.getMk());
        return kh;
    }

    @Override
    public KhachHang Update(KhachHang kh) {
        DBConnection.Excute(Update_ByMa_sql, kh.getTen(), kh.getTenDem(),
                kh.getHo(), kh.getNgaysinh(), kh.getSdt(), kh.getDiachi(),
                kh.getThanhpho(), kh.getQuocgia(), kh.getMk(), kh.getId());

        return kh;

    }

    private List<KhachHang> getSelectNv(String sql, Object... args) {
        try {
            List<KhachHang> LNV = new ArrayList<>();
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                LNV.add(new KhachHang(rs.getString("Id"),
                        rs.getString("Ma"), rs.getString("Ten"),
                        rs.getString("TenDem"), rs.getString("Ho"),
                        rs.getString("NgaySinh"), rs.getString("Sdt"),
                        rs.getString("DiaChi"), rs.getString("ThanhPho"),
                        rs.getString("QuocGia"), rs.getString("MatKhau")));
            }
            return LNV;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

    @Override
    public String Delete(String Id) {
        DBConnection.Excute(Delete_KH_sql, Id);
        return Id;
    }
}
