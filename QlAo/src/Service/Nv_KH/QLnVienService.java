/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Nv_KH;

import Model.Nv_KH.NhanVien;
import Reponsitory.Nv_KH.INVrepo;
import Reponsitory.Nv_KH.NhanVienRepon;
import ViewModel.nvView;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author latha
 */
public class QLnVienService implements INvService {

    private final INVrepo nvrepo;

    public QLnVienService() {
        nvrepo = new NhanVienRepon();

    }

    @Override
    public List<nvView> findAll() {
        List<NhanVien> lnv = nvrepo.findAll();
        List<nvView> lnvv = new ArrayList<>();
        for (NhanVien nv : lnv) {
            nvView nvv = new nvView(nv.getId(), nv.getMa(), nv.getTen(),
                    nv.getTenDem(), nv.getHo(), nv.getGioiTinh(),
                    nv.getSinhNhat(), nv.getDiaChi(), nv.getSdt(),
                    nv.getMK(), nv.getIdCuaHang(), nv.getIdCV(),
                    nv.getIdguibc(), nv.getTrangthai());
            lnvv.add(nvv);
        }

        return lnvv;
    }

    @Override
    public List<nvView> findById(String Ma) {
        List<NhanVien> lnv = nvrepo.findById(Ma);
        List<nvView> lnvv = new ArrayList<>();
        for (NhanVien nv : lnv) {
            nvView nvv = new nvView(nv.getId(), nv.getMa(), nv.getTen(),
                    nv.getTenDem(), nv.getHo(), nv.getGioiTinh(),
                    nv.getSinhNhat(), nv.getDiaChi(), nv.getSdt(),
                    nv.getMK(), nv.getIdCuaHang(), nv.getIdCV(),
                    nv.getIdguibc(), nv.getTrangthai());
            lnvv.add(nvv);
        }

        return lnvv;
    }

    @Override
    public nvView saveNV(nvView Nv) {
        NhanVien nv = new NhanVien(Nv.getId(), Nv.getMa(), Nv.getTen(),
                Nv.getTenDem(), Nv.getHo(), Nv.getGioiTinh(),
                Nv.getSinhNhat(), Nv.getDiaChi(), Nv.getSdt(),
                Nv.getMK(), Nv.getIdCuaHang(), Nv.getIdCV(),
                Nv.getIdguibc(), Nv.getTrangthai());
        nvrepo.saveNV(nv);

        return Nv;
    }

    @Override
    public nvView UpdateNv(nvView Nv) {
        NhanVien nv = new NhanVien(Nv.getId(), Nv.getMa(), Nv.getTen(),
                Nv.getTenDem(), Nv.getHo(), Nv.getGioiTinh(),
                Nv.getSinhNhat(), Nv.getDiaChi(), Nv.getSdt(),
                Nv.getMK(), Nv.getIdCuaHang(), Nv.getIdCV(),
                Nv.getIdguibc(), Nv.getTrangthai());
        nvrepo.UpdateNv(nv);

        return Nv;
    }

    @Override
    public String DeleteNV(String Id) {
        return nvrepo.DeleteNV(Id);

    }

}
