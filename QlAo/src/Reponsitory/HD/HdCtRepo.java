/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reponsitory.HD;

import Model.HoaDon.HdChiTiet;
import Model.HoaDon.HoaDon;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import Utilities.DBConnection;
import java.math.BigDecimal;
import java.util.AbstractList;

/**
 *
 * @author latha
 */
public class HdCtRepo implements IhdChiTiet {

    private List<HdChiTiet> lhdct;
    private HdChiTiet hdct;
    private List<HoaDon> lHd;
    private HoaDon hd;
//Hóa đơn Chi tiết String
    final String SelectAll_Hdct_sql = "SELECT [IdHoaDon],[IdChiTietSP],[SoLuong],[DonGia] FROM [dbo].[HoaDonChiTiet]";

    final String SelectByMa_Hdct_sql = "SELECT [IdHoaDon],[IdChiTietSP],[SoLuong],[DonGia] \n"
            + "FROM [dbo].[HoaDonChiTiet]\n"
            + "join HoaDon on HoaDon.Id = HoaDonChiTiet.IdHoaDon\n"
            + "where HoaDon.Ma = ?";
    final String InsertHdCt_sql = "INSERT INTO [dbo].[HoaDonChiTiet]\n"
            + "           ([IdHoaDon],[IdChiTietSP]\n"
            + "           ,[SoLuong],[DonGia])\n"
            + "     VALUES (?,?,?,?)";

    final String Update_Hdct_sql = "";
//------------------------------------------------------------------------------    
//Hóa đơn String    
    final String SelectAll_Hd_sql = "SELECT [Id],[IdKH],[IdNV],[Ma]\n"
            + "      ,[NgayTao],[NgayThanhToan],[TenNguoiNhan]\n"
            + "      ,[DiaChi],[Sdt],[TinhTrang]\n"
            + "  FROM [dbo].[HoaDon]";

    final String SelectByMa_Hd_sql = "SELECT [Id],[IdKH],[IdNV],[Ma]\n"
            + "      ,[NgayTao],[NgayThanhToan],[TenNguoiNhan]\n"
            + "      ,[DiaChi],[Sdt],[TinhTrang]\n"
            + "  FROM [dbo].[HoaDon] WHERE Ma = ?";
    final String InsertHd_sql = "INSERT INTO [dbo].[HoaDon]\n"
            + "           ([Id],[IdKH],[IdNV]\n"
            + "           ,[Ma],[NgayTao],[NgayThanhToan]\n"
            + "           ,[TenNguoiNhan],[DiaChi]\n"
            + "           ,[Sdt],[TinhTrang])\n"
            + "     VALUES (?,?,?,?,?,?,?,?,?,?)";

    final String Update_Hd_sql = "";
//------------------------------------------------------------------------------

    @Override
    public List<HdChiTiet> findAllhdct() {
        lhdct = new ArrayList<>();
        return getSelectHdct(SelectAll_Hdct_sql);
    }

    @Override
    public List<HdChiTiet> findHdCtbyid(String ma) {
        lhdct = new ArrayList<>();
        return getSelectHdct(SelectByMa_Hdct_sql, ma);
    }

    @Override
    public List<HoaDon> findAllHd() {
        lHd = new ArrayList<>();
        return getSelectHd(SelectAll_Hd_sql);
    }

    @Override
    public List<HoaDon> findHdbyid(String ma) {
        lHd = new ArrayList<>();
        return getSelectHd(SelectByMa_Hd_sql, ma);
    }

    @Override
    public HoaDon SaveHd(HoaDon hd) {

        DBConnection.Excute(InsertHd_sql, hd.getId(), hd.getIdKh(),
                hd.getIdNV(), hd.getMaHoaDon(), hd.getNgayTao(),
                hd.getNgayThanhToan(), hd.getTenNgNhan(), hd.getDiaChi(),
                hd.getSDT(), hd.getTrangThai());
        return hd;
    }

    @Override
    public HdChiTiet SaveHdct(HdChiTiet hdct) {

        DBConnection.Excute(InsertHdCt_sql, hdct.getIdHoaDon(),
                hdct.getIdChiTietSp(), hdct.getSoLuong(), hdct.getDonGia());
        return hdct;
    }

    @Override
    public HdChiTiet UpdateHdct(HdChiTiet hdct, String Ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HoaDon UpdateHoaDon(HoaDon hd, String Ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private List<HdChiTiet> getSelectHdct(String sql, Object... args) {
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                hdct = new HdChiTiet(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getBigDecimal(4));
                lhdct.add(hdct);
            }
            return lhdct;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

    private List<HoaDon> getSelectHd(String sql, Object... args) {
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                hd = new HoaDon(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getInt(10));
                lHd.add(hd);
            }
            return lHd;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

}
