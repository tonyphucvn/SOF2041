/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reponsitory.Sp;

import Model.Sp.NSX;
import Model.Sp.DongSP;
import Model.Sp.MauSac;
import Model.Sp.SanPham;
import Model.Sp.ChiTietSanPham;
import Utilities.DBConnection;
import java.math.BigDecimal;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author latha
 */
public class CtSpRepo implements IctspRepo {
// Chi tiết sản phẩm -----------------------------------------------------------

    final String Insert_Ctsp_Sql = "INSERT INTO [dbo].[ChiTietSP]\n"
            + "           ([Id],[IdSP]\n"
            + "           ,[IdNsx] ,[IdMauSac]\n"
            + "           ,[IdDongSP],[NamBH]\n"
            + "           ,[MoTa],[SoLuongTon]\n"
            + "           ,[GiaNhap],[GiaBan])\n"
            + "     VALUES (?,?,?,?,?,?,?,?,?,?)";
    final String Update_CtSp_Sql = "UPDATE [dbo].[ChiTietSP]\n"
            + "   SET [IdNsx] = ? ,[IdMauSac] = ?\n"
            + "      ,[IdDongSP] = ? ,[NamBH] = ?\n"
            + "      ,[MoTa] = ? ,[SoLuongTon] = ?\n"
            + "      ,[GiaNhap] = ? ,[GiaBan] = ?\n"
            + " WHERE [Id] = ?";

    final String Delete_CtSp_Sql = "DELETE FROM [dbo].[ChiTietSP]\n"
            + "      WHERE Id = ? ";

    final String SelectByid_Ctsp_sql = "SELECT SanPham.Id AS IdSP,"
            + " SanPham.Ma AS MaSP, SanPham.Ten AS TenSP,\n"
            + "		NSX.Id AS IdNSX, NSX.Ma AS MaNSX, NSX.Ten AS TenNSX,\n"
            + "		MauSac.Id AS IdMauSac, MauSac.Ma AS MaMS, MauSac.Ten AS TenMS,\n"
            + "		DongSP.Id AS IdDongSP, DongSP.Ma AS MaDSP, DongSP.Ten AS TenDSP, \n"
            + "		ChiTietSP.Id AS IDCTSP,ChiTietSP.NamBH,ChiTietSP.MoTa,ChiTietSP.SoLuongTon \n"
            + "		, ChiTietSP.GiaNhap,ChiTietSP.GiaBan\n"
            + "		FROM SanPham INNER JOIN\n"
            + "                      ChiTietSP ON SanPham.Id = ChiTietSP.IdSP INNER JOIN\n"
            + "                      NSX ON ChiTietSP.IdNsx = NSX.Id INNER JOIN\n"
            + "                      MauSac ON ChiTietSP.IdMauSac = MauSac.Id INNER JOIN\n"
            + "                      DongSP ON ChiTietSP.IdDongSP = DongSP.Id	where  SanPham.Ma = ? ";

    final String SelectAll_sql = "SELECT SanPham.Id AS IdSP, SanPham.Ma AS MaSP, SanPham.Ten AS TenSP,\n"
            + "		NSX.Id AS IdNSX, NSX.Ma AS MaNSX, NSX.Ten AS TenNSX,\n"
            + "		MauSac.Id AS IdMauSac, MauSac.Ma AS MaMS, MauSac.Ten AS TenMS,\n"
            + "		DongSP.Id AS IdDongSP, DongSP.Ma AS MaDSP, DongSP.Ten AS TenDSP, \n"
            + "		ChiTietSP.Id AS IDCTSP,ChiTietSP.NamBH,ChiTietSP.MoTa,ChiTietSP.SoLuongTon \n"
            + "		, ChiTietSP.GiaNhap,ChiTietSP.GiaBan\n"
            + "		FROM SanPham INNER JOIN\n"
            + "                      ChiTietSP ON SanPham.Id = ChiTietSP.IdSP INNER JOIN\n"
            + "                      NSX ON ChiTietSP.IdNsx = NSX.Id INNER JOIN\n"
            + "                      MauSac ON ChiTietSP.IdMauSac = MauSac.Id INNER JOIN\n"
            + "                      DongSP ON ChiTietSP.IdDongSP = DongSP.Id";
//------------------------------------------------------------------------------    
    private List<ChiTietSanPham> lctsp;
    private ChiTietSanPham ctsp;
    private DongSP idDongSP;
    private SanPham idsp;
    private MauSac idmauSac;
    private NSX idNsx;

    @Override
    public List<ChiTietSanPham> findAll() {
        lctsp = new ArrayList<>();
        lctsp = getSelectSql(SelectAll_sql);
        return lctsp;
    }

    @Override
    public List<ChiTietSanPham> findById(String id) {
        lctsp = new ArrayList<>();
        return getSelectSql(SelectByid_Ctsp_sql, id);
    }



    @Override
    public ChiTietSanPham Save(ChiTietSanPham ctsp) {

        DBConnection.Excute(Insert_Ctsp_Sql, ctsp.getId(),
                ctsp.getIdsp().getId(), ctsp.getIdNsx().getId(),
                ctsp.getIdmauSac().getId(), ctsp.getIdDongSP().getId(),
                ctsp.getNamBh(), ctsp.getMota(), ctsp.getSoluongTon(),
                ctsp.getGiaNhap(), ctsp.getGiaBan()
        );
        return ctsp;
    }

    @Override
    public String delete(String id) {
        DBConnection.Excute(Delete_CtSp_Sql, id);
        return id;
    }

    @Override
    public long totalCount() {
        long total = 0;
        total = lctsp.size();
        return total;
    }

    @Override
    public ChiTietSanPham Update(ChiTietSanPham ctsp, String id) {
        DBConnection.Excute(Update_CtSp_Sql,
                ctsp.getIdNsx().getId(),
                ctsp.getIdmauSac().getId(), ctsp.getIdDongSP().getId(),
                ctsp.getNamBh(), ctsp.getMota(), ctsp.getSoluongTon(),
                ctsp.getGiaNhap(), ctsp.getGiaBan(), id
        );
        return ctsp;
    }

    private List<ChiTietSanPham> getSelectSql(String sql, Object... args) {
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                idsp = new SanPham(rs.getString(1), rs.getString(2),
                        rs.getString(3));
                idNsx = new NSX(rs.getString(4), rs.getString(5),
                        rs.getString(6));
                idmauSac = new MauSac(rs.getString(7), rs.getString(8),
                        rs.getString(9));
                idDongSP = new DongSP(rs.getString(10), rs.getString(11),
                        rs.getString(12));
                ctsp = new ChiTietSanPham(rs.getString(13), idsp, idNsx, idmauSac, idDongSP,
                        rs.getInt(14), rs.getString(15),
                        rs.getInt(16), rs.getBigDecimal(17),
                        rs.getBigDecimal(18));
                lctsp.add(ctsp);
            }
            return lctsp;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }



}
