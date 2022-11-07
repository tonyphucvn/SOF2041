/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reponsitory.CV_CH;

import Model.Cv_Ch.ChucVu;
import Model.Cv_Ch.CuaHang;
import Utilities.DBConnection;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author latha
 */
public class ChucVu_CuaHangRepo implements IgetList_CV_CH {
//    String kết nối JDBC với Database
//============================== Chức vụ========================================

    final String Select_all_CV = "SELECT [Id],[Ma],[Ten] FROM [dbo].[ChucVu] ORDER BY Ma ";
//============================== Cửa Hàng ======================================
    final String Select_all_CH = "SELECT [Id],[Ma],[Ten],[DiaChi]\n"
            + "      ,[ThanhPho],[QuocGia]\n"
            + "  FROM [dbo].[CuaHang] ORDER BY Ma ";
    final String Insert_CH_sql = "INSERT INTO [dbo].[CuaHang]\n"
            + "           ([Id],[Ma],[Ten],[DiaChi],[ThanhPho],[QuocGia])     \n"
            + "		   VALUES (?,?,?,?,?,?)";
    final String Update_CH_sql = "UPDATE [dbo].[CuaHang]\n"
            + "   SET [Ten] = ? ,[DiaChi] = ?\n"
            + "      ,[ThanhPho] = ? ,[QuocGia] = ?\n"
            + " WHERE [Id] = ?";
    final String Delete_Ch_sql = "DELETE FROM [dbo].[CuaHang]\n"
            + "      WHERE [Id] = ?";
//    

    @Override
    public List<ChucVu> findAllCV() {
        return getSelectCV(Select_all_CV);
    }

    @Override
    public List<CuaHang> findAllCH() {
        return getSelectCH(Select_all_CH);
    }

    private List<ChucVu> getSelectCV(String sql, Object... args) {
        try {
            List<ChucVu> LNV = new ArrayList<>();
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                LNV.add(new ChucVu(rs.getString("Id"), rs.getString("Ma"),
                        rs.getString("Ten")));
            }
            return LNV;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

    private List<CuaHang> getSelectCH(String sql, Object... args) {
        try {
            List<CuaHang> LNV = new ArrayList<>();
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                LNV.add(new CuaHang(rs.getString("Id"), rs.getString("Ma"),
                        rs.getString("Ten"), rs.getString("DiaChi"),
                        rs.getString("ThanhPho"), rs.getString("QuocGia")));
            }
            return LNV;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

    @Override
    public CuaHang SaveCH(CuaHang ch) {
        DBConnection.Excute(Insert_CH_sql, ch.getId(), ch.getMa(),
                ch.getTen(), ch.getDiaChi(), ch.getThanhpho(),
                ch.getQuocgia());
        return ch;
    }

    @Override
    public CuaHang UpdateCH(CuaHang ch) {
        DBConnection.Excute(Update_CH_sql, ch.getTen(),
                ch.getDiaChi(), ch.getThanhpho(),
                ch.getQuocgia(), ch.getId());
        return ch;
    }

    @Override
    public String DeleteCH(String id) {
        DBConnection.Excute(Delete_Ch_sql, id);
        return id;
    }
}
