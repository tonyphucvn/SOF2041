/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Reponsitory.HD;

import Model.HoaDon.HdChiTiet;
import Model.HoaDon.HoaDon;
import java.util.List;

/**
 *
 * @author latha
 */
public interface IhdChiTiet {

    List<HdChiTiet> findAllhdct();

    List<HdChiTiet> findHdCtbyid(String ma);
    
    List<HoaDon> findAllHd();
    
    List<HoaDon> findHdbyid(String ma);
    
    HoaDon SaveHd(HoaDon hd);
    
    HdChiTiet SaveHdct(HdChiTiet hdct);
    
    HdChiTiet UpdateHdct(HdChiTiet hdct,String Ma);
    
    HoaDon UpdateHoaDon(HoaDon hd,String Ma);
    
}
