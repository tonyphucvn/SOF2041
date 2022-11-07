/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Reponsitory.CV_CH;

import Model.Cv_Ch.ChucVu;
import Model.Cv_Ch.CuaHang;
import java.util.List;

/**
 *
 * @author latha
 */
public interface IgetList_CV_CH {
    List<ChucVu> findAllCV();
    
    List<CuaHang> findAllCH(); 
    
    CuaHang SaveCH(CuaHang ch);
    
    CuaHang UpdateCH(CuaHang ch);
    
    String DeleteCH(String id);
}
