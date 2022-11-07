/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Reponsitory.Nv_KH;

import Model.Nv_KH.NhanVien;
import java.util.List;

/**
 *
 * @author latha
 */
public interface INVrepo {
    List<NhanVien> findAll();
    
    List<NhanVien> findById(String Ma);
    
    NhanVien saveNV(NhanVien Nv);
    
    NhanVien UpdateNv(NhanVien Nv);
    
    String DeleteNV(String Id);
}
