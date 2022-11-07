/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Reponsitory.Nv_KH;

import Model.Nv_KH.KhachHang;
import java.util.List;

/**
 *
 * @author latha
 */
public interface IKHrepo {
    List<KhachHang> findAll();
    
    List<KhachHang> findByMa(String Ma);
    
    KhachHang save(KhachHang kh);
    
    KhachHang Update(KhachHang kh);
    
    String Delete(String Id);
}
