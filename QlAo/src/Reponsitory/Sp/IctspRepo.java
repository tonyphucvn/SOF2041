 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Reponsitory.Sp;

import Model.Sp.ChiTietSanPham;
import Model.Sp.SanPham;
import java.util.List;

/**
 *
 * @author latha
 */
public interface IctspRepo {

    List<ChiTietSanPham> findAll();

    List<ChiTietSanPham> findById(String id);

    ChiTietSanPham Save(ChiTietSanPham ctsp);

    ChiTietSanPham Update(ChiTietSanPham ctsp, String id);

    String delete(String id);

    long totalCount();

}
