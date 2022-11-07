/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Nv_KH;

import Model.Nv_KH.KhachHang;
import Reponsitory.Nv_KH.IKHrepo;
import Reponsitory.Nv_KH.KhachHangRepo;
import ViewModel.khView;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author latha
 */
public class QLkhService implements IKhService {

    private final IKHrepo khrepo;

    public QLkhService() {
        khrepo = new KhachHangRepo();
    }

    @Override
    public List<khView> findAll() {
        List<KhachHang> lkh = khrepo.findAll();
        List<khView> lkhv = new ArrayList<>();
        for (KhachHang kh : lkh) {
            khView khv = new khView(kh.getId(), kh.getMa(), kh.getTen(),
                    kh.getTenDem(), kh.getHo(), kh.getNgaysinh(),
                    kh.getSdt(), kh.getDiachi(), kh.getThanhpho(),
                    kh.getQuocgia(), kh.getMk());
            lkhv.add(khv);
        }

        return lkhv;
    }

    @Override
    public List<khView> findByMa(String Ma) {
        List<KhachHang> lkh = khrepo.findByMa(Ma);
        List<khView> lkhv = new ArrayList<>();
        for (KhachHang kh : lkh) {
            khView khv = new khView(kh.getId(), kh.getMa(), kh.getTen(),
                    kh.getTenDem(), kh.getHo(), kh.getNgaysinh(),
                    kh.getSdt(), kh.getDiachi(), kh.getThanhpho(),
                    kh.getQuocgia(), kh.getMk());
            lkhv.add(khv);
        }

        return lkhv;
    }

    @Override
    public khView save(khView kh) {
        KhachHang KH = new KhachHang(kh.getId(),kh.getMa(),kh.getTen(),
                kh.getTenDem(),kh.getHo(),kh.getNgaysinh(),
                kh.getSdt(),kh.getDiachi(),kh.getThanhpho(),
                kh.getQuocgia(),kh.getMk());
        
        khrepo.save(KH);
        return kh;
    }

    @Override
    public khView Update(khView kh) {
               KhachHang KH = new KhachHang(kh.getId(),kh.getMa(),kh.getTen(),
                kh.getTenDem(),kh.getHo(),kh.getNgaysinh(),
                kh.getSdt(),kh.getDiachi(),kh.getThanhpho(),
                kh.getQuocgia(),kh.getMk());
        
        khrepo.Update(KH);
        return kh;
    }

    @Override
    public String Delete(String Id) {
        return khrepo.Delete(Id);
    }

}
