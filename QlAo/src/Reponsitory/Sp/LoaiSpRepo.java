/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reponsitory.Sp;

import Model.Sp.NSX;
import Model.Sp.DongSP;
import Model.Sp.MauSac;
import Model.Sp.SanPham;
import java.util.List;
import Utilities.DBConnection;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author latha
 */
public class LoaiSpRepo implements ILoaiSpRepo {
// Dòng Sp======================================================================

    final String Select_DongSp = "SELECT [Id],[Ma],[Ten] FROM [dbo].[DongSP] ORDER BY Ma  ";
    final String Insert_DspSql = "INSERT INTO [dbo].[DongSP] ([Id],[Ma],[Ten])\n"
            + "     VALUES (?,?,?)";
    final String Update_DspSQL = "UPDATE [dbo].[DongSP]\n"
            + "   SET [Ma] = ?, [Ten] = ?\n"
            + " WHERE [Id] = ?";
    final String Delete_DspSQL = "DELETE FROM [dbo].[DongSP]\n"
            + "      WHERE Id = ?";
//    Màu Sắc===================================================================
    final String Select_MauSac = "SELECT [Id],[Ma],[Ten] FROM [dbo].[MauSac] ORDER BY Ma ";
    final String Insert_MS_Sql = "INSERT INTO [dbo].[MauSac] ([Id],[Ma],[Ten])\n"
            + "     VALUES (?,?,?)";
    final String Update_MS_Sql = "UPDATE [dbo].[MauSac]\n"
            + "   SET [Ma] = ?, [Ten] = ?\n"
            + " WHERE [Id] = ?";
    final String Delete_MsSQL = "DELETE FROM [dbo].[MauSac]\n"
            + "      WHERE Id = ?";
//    Nhà Sản Xuất==============================================================
    final String Select_NSX = "SELECT [Id],[Ma],[Ten] FROM [dbo].[NSX] ORDER BY Ma ";
    final String Insert_NSX_Sql = "INSERT INTO [dbo].[NSX] ([Id],[Ma],[Ten])\n"
            + "     VALUES (?,?,?)";
    final String Update_NSX_Sql = "UPDATE [dbo].[NSX]\n"
            + "   SET [Ma] = ?, [Ten] = ?\n"
            + " WHERE [Id] = ?";
    final String Delete_NsxSQL = "DELETE FROM [dbo].[NSX]\n"
            + "      WHERE Id = ?";
// Sản Phẩm ====================================================================   
    final String Select_SanPham = "SELECT [Id],[Ma],[Ten] FROM [dbo].[SanPham] ORDER BY Ma ";
    final String Insert_spSql = "INSERT INTO [dbo].[SanPham] ([Id],[Ma],[Ten])\n"
            + "     VALUES (?,?,?)";
    final String Update_SpSQL = "UPDATE [dbo].[SanPham]\n"
            + "   SET [Ma] = ?, [Ten] = ?\n"
            + " WHERE [Id] = ?";
    final String Delete_SpSQL = "DELETE FROM [dbo].[SanPham]\n"
            + "      WHERE Id = ?";
//==============================================================================

    @Override
    public List<DongSP> getDongSp() {
        return getSelectDongSP(Select_DongSp);
    }

    @Override
    public List<MauSac> getMauSac() {
        return getSelectMauSac(Select_MauSac);
    }

    @Override
    public List<NSX> getNSX() {
        return getSelectNSX(Select_NSX);
    }

    private List<DongSP> getSelectDongSP(String sql, Object... args) {
        try {
            List<DongSP> lstcategories = new ArrayList<>();
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                lstcategories.add(new DongSP(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            return lstcategories;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    private List<MauSac> getSelectMauSac(String sql, Object... args) {
        try {
            List<MauSac> lstcategories = new ArrayList<>();
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                lstcategories.add(new MauSac(rs.getString("Id"), rs.getString("Ma"), rs.getString("Ten")));
            }
            return lstcategories;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    private List<NSX> getSelectNSX(String sql, Object... args) {
        try {
            List<NSX> lstcategories = new ArrayList<>();
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                lstcategories.add(new NSX(rs.getString("Id"), rs.getString("Ma"), rs.getString("Ten")));
            }
            return lstcategories;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<SanPham> getSp() {
        return getSelectSp(Select_SanPham);
    }

    @Override
    public SanPham SaveSp(SanPham sp) {

        DBConnection.Excute(Insert_spSql, sp.getId(), sp.getMa(),
                sp.getTen());
        return sp;
    }

    private List<SanPham> getSelectSp(String sql, Object... args) {
        try {
            List<SanPham> lstcategories = new ArrayList<>();
            ResultSet rs = DBConnection.getDataFromQuery(sql, args);
            while (rs.next()) {
                lstcategories.add(new SanPham(rs.getString("Id"), rs.getString("Ma"), rs.getString("Ten")));
            }
            return lstcategories;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public SanPham UpdateSp(SanPham sp) {
        DBConnection.Excute(Update_SpSQL, sp.getMa(), sp.getTen(), sp.getId());
        return sp;

    }

    @Override
    public DongSP SaveDsp(DongSP dsp) {
        DBConnection.Excute(Insert_DspSql, dsp.getId(), dsp.getMa(),
                dsp.getTen());
        return dsp;
    }

    @Override
    public DongSP UpdateDsp(DongSP dsp) {
        DBConnection.Excute(Update_DspSQL, dsp.getMa(), dsp.getTen(), dsp.getId());
        return dsp;
    }

    @Override
    public MauSac SaveMS(MauSac ms) {
        DBConnection.Excute(Insert_MS_Sql, ms.getId(), ms.getMa(),
                ms.getTen());
        return ms;
    }

    @Override
    public MauSac UpdateMS(MauSac ms) {
        DBConnection.Excute(Update_MS_Sql, ms.getMa(), ms.getTen(), ms.getId());
        return ms;
    }

    @Override
    public NSX SaveNSX(NSX nsx) {
        DBConnection.Excute(Insert_NSX_Sql, nsx.getId(), nsx.getMa(),
                nsx.getTen());
        return nsx;
    }

    @Override
    public NSX UpdateNSX(NSX nsx) {
        DBConnection.Excute(Update_NSX_Sql, nsx.getMa(), nsx.getTen(), nsx.getId());
        return nsx;
    }

    @Override
    public String DeleteDsp(String id) {
        DBConnection.Excute(Delete_DspSQL, id);
        return id;
    }

    @Override
    public String DeleteMs(String id) {
        DBConnection.Excute(Delete_MsSQL, id);
        return id;
    }

    @Override
    public String DeleteNSX(String id) {
        DBConnection.Excute(Delete_NsxSQL, id);
        return id;
    }

    @Override
    public String DeleteSP(String id) {
        DBConnection.Excute(Delete_SpSQL, id);
        return id;
    }
}
