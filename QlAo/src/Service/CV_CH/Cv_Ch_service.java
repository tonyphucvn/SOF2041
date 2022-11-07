/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.CV_CH;

import Model.Cv_Ch.ChucVu;
import Model.Cv_Ch.CuaHang;
import Reponsitory.CV_CH.ChucVu_CuaHangRepo;
import Reponsitory.CV_CH.IgetList_CV_CH;
import ViewModel.chView;
import ViewModel.cvView;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author latha
 */
public class Cv_Ch_service implements Icv_ch_service {

    private final IgetList_CV_CH cv_chRepo;

    public Cv_Ch_service() {
        cv_chRepo = new ChucVu_CuaHangRepo();
    }

    @Override
    public List<cvView> findAllCV() {
        List<ChucVu> lcv = cv_chRepo.findAllCV();
        List<cvView> LcView = new ArrayList<>();
        for (ChucVu cV : lcv) {
            cvView cView = new cvView(cV.getId(), cV.getMa(), cV.getTen());
            LcView.add(cView);
        }

        return LcView;
    }

    @Override
    public List<chView> findAllCH() {
        List<CuaHang> lch = cv_chRepo.findAllCH();
        List<chView> lchv = new ArrayList<>();
        for (CuaHang cH : lch) {
            chView chv = new chView(cH.getId(), cH.getMa(), cH.getTen(),
                    cH.getDiaChi(), cH.getThanhpho(), cH.getQuocgia());
            lchv.add(chv);
        }

        return lchv;
    }

    @Override
    public chView SaveCH(chView ch) {
        CuaHang nch = new CuaHang(ch.getId(), ch.getMa(), ch.getTen(),
                ch.getDiaChi(), ch.getThanhpho(), ch.getQuocgia());
        cv_chRepo.SaveCH(nch);
        return ch;
    }

    @Override
    public chView UpdateCH(chView ch) {
        CuaHang nch = new CuaHang(ch.getId(), ch.getMa(), ch.getTen(),
                ch.getDiaChi(), ch.getThanhpho(), ch.getQuocgia());
        cv_chRepo.UpdateCH(nch);
        return ch;
    }

    @Override
    public String DeleteCH(String id) {
        cv_chRepo.DeleteCH(id);
        return id;
    }

}
