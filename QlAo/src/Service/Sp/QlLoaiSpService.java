/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Sp;

import Model.Sp.DongSP;
import Model.Sp.MauSac;
import Model.Sp.NSX;
import Model.Sp.SanPham;
import Reponsitory.Sp.ILoaiSpRepo;
import Reponsitory.Sp.LoaiSpRepo;
import ViewModel.ttCtsp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author latha
 */
public class QlLoaiSpService implements ILoaiSPservice {

    private final ILoaiSpRepo LoaiSpRepo;

    public QlLoaiSpService() {
        LoaiSpRepo = new LoaiSpRepo();
    }

    @Override
    public List<ttCtsp> getDongSp() {
        List<ttCtsp> lttsp = new ArrayList<>();
        List<DongSP> ds = LoaiSpRepo.getDongSp();
        for (DongSP d : ds) {
            ttCtsp tt = new ttCtsp(d.getId(), d.getMa(), d.getTen());
            lttsp.add(tt);
        }

        return lttsp;
    }

    @Override
    public List<ttCtsp> getMauSac() {
        List<ttCtsp> lttsp = new ArrayList<>();
        List<MauSac> ds = LoaiSpRepo.getMauSac();
        for (MauSac d : ds) {
            ttCtsp tt = new ttCtsp(d.getId(), d.getMa(), d.getTen());
            lttsp.add(tt);
        }
        return lttsp;

    }

    @Override
    public List<ttCtsp> getNSX() {
        List<ttCtsp> lttsp = new ArrayList<>();
        List<NSX> ds = LoaiSpRepo.getNSX();
        for (NSX d : ds) {
            ttCtsp tt = new ttCtsp(d.getId(), d.getMa(), d.getTen());
            lttsp.add(tt);
        }
        return lttsp;
    }

    @Override
    public List<ttCtsp> getSanPham() {
        List<ttCtsp> lttsp = new ArrayList<>();
        List<SanPham> ds = LoaiSpRepo.getSp();
        for (SanPham d : ds) {
            ttCtsp tt = new ttCtsp(d.getId(), d.getMa(), d.getTen());
            lttsp.add(tt);
        }
        return lttsp;
    }

    @Override
    public ttCtsp SaveDsp(ttCtsp dsp) {
        DongSP ds = new DongSP(dsp.getId(), dsp.getMa(), dsp.getTen());
        LoaiSpRepo.SaveDsp(ds);
        return dsp;
    }

    @Override
    public ttCtsp UpdateDsp(ttCtsp dsp) {
        DongSP ds = new DongSP(dsp.getId(), dsp.getMa(), dsp.getTen());
        LoaiSpRepo.UpdateDsp(ds);
        return dsp;
    }

    @Override
    public ttCtsp SaveMS(ttCtsp ms) {
        MauSac lms = new MauSac(ms.getId(), ms.getMa(), ms.getTen());
        LoaiSpRepo.SaveMS(lms);
        return ms;
    }

    @Override
    public ttCtsp UpdateMS(ttCtsp ms) {
        MauSac lms = new MauSac(ms.getId(), ms.getMa(), ms.getTen());
        LoaiSpRepo.UpdateMS(lms);
        return ms;
    }

    @Override
    public ttCtsp SaveNSX(ttCtsp nsx) {
        NSX Lnsx = new NSX(nsx.getId(), nsx.getMa(), nsx.getTen());
        LoaiSpRepo.SaveNSX(Lnsx);
        return nsx;
    }

    @Override
    public ttCtsp UpdateNSX(ttCtsp nsx) {
        NSX Lnsx = new NSX(nsx.getId(), nsx.getMa(), nsx.getTen());
        LoaiSpRepo.UpdateNSX(Lnsx);
        return nsx;
    }

    @Override
    public ttCtsp SaveSp(ttCtsp sp) {
        SanPham lsp = new SanPham(sp.getId(), sp.getMa(), sp.getTen());
        LoaiSpRepo.SaveSp(lsp);
        return sp;
    }

    @Override
    public ttCtsp UpdateSp(ttCtsp sp) {
        SanPham lsp = new SanPham(sp.getId(), sp.getMa(), sp.getTen());
        LoaiSpRepo.UpdateSp(lsp);
        return sp;
    }

    @Override
    public String DeleteDsp(String id) {
        LoaiSpRepo.DeleteDsp(id);
        return id;
    }

    @Override
    public String DeleteMs(String id) {
        LoaiSpRepo.DeleteMs(id);
        return id;
    }

    @Override
    public String DeleteNSX(String id) {
        LoaiSpRepo.DeleteNSX(id);
        return id;
    }

    @Override
    public String DeleteSp(String id) {
        LoaiSpRepo.DeleteSP(id);
        return id;
    }

}
