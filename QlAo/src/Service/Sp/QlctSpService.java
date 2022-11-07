/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Sp;

import Model.Sp.ChiTietSanPham;
import Model.Sp.SanPham;
import Reponsitory.Sp.CtSpRepo;
import Reponsitory.Sp.IctspRepo;
import ViewModel.ctspView;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author latha
 */
public class QlctSpService implements IqlctspService {

    private final IctspRepo ctspRepo;
    private List<ctspView> lctspV;

    public QlctSpService() {
        ctspRepo = new CtSpRepo();
    }

    @Override
    public List<ctspView> findAll() {
            List<ChiTietSanPham> Lctsp = ctspRepo.findAll();
        List<ctspView> lctspV = new ArrayList();

        for (ChiTietSanPham ct : Lctsp) {
            ctspView ctv = new ctspView(ct.getId(), ct.getIdsp(),
                    ct.getIdNsx(), ct.getIdmauSac(),
                    ct.getIdDongSP(), ct.getNamBh(), ct.getMota(),
                    ct.getSoluongTon(), ct.getGiaNhap(), ct.getGiaBan()
            );
            lctspV.add(ctv);
        }

        return lctspV;
    }

    @Override
    public List<ctspView> findById(String id) {
        List<ChiTietSanPham> Lctsp = ctspRepo.findById(id);
        List<ctspView> lctspV = new ArrayList();

        for (ChiTietSanPham ct : Lctsp) {
            ctspView ctv = new ctspView(ct.getId(), ct.getIdsp(),
                    ct.getIdNsx(), ct.getIdmauSac(),
                    ct.getIdDongSP(), ct.getNamBh(), ct.getMota(),
                    ct.getSoluongTon(), ct.getGiaNhap(), ct.getGiaBan()
            );
            lctspV.add(ctv);
        }

        return lctspV;

    }



    @Override
    public ChiTietSanPham Save(ChiTietSanPham ctsp) {
        return ctspRepo.Save(ctsp);
    }

    @Override
    public ChiTietSanPham Update(ChiTietSanPham ctsp, String id) {
        return ctspRepo.Update(ctsp, id);
    }

    @Override
    public String delete(String id) {
        return ctspRepo.delete(id);
    }

    @Override
    public long totalCount() {
        return ctspRepo.totalCount();
    }

}
