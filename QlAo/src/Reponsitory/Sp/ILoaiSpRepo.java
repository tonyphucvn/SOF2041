/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Reponsitory.Sp;

import Model.Sp.DongSP;
import Model.Sp.MauSac;
import Model.Sp.NSX;
import Model.Sp.SanPham;
import java.util.List;

/**
 *
 * @author latha
 */
public interface ILoaiSpRepo {

    List<DongSP> getDongSp();

    DongSP SaveDsp(DongSP dsp);

    DongSP UpdateDsp(DongSP dsp);

    String DeleteDsp(String id);

    List<MauSac> getMauSac();

    MauSac SaveMS(MauSac ms);

    MauSac UpdateMS(MauSac ms);

    String DeleteMs(String id);

    List<NSX> getNSX();

    NSX SaveNSX(NSX nsx);

    NSX UpdateNSX(NSX nsx);

    String DeleteNSX(String id);

    List<SanPham> getSp();

    SanPham SaveSp(SanPham sp);

    SanPham UpdateSp(SanPham sp);

    String DeleteSP(String id);
}
