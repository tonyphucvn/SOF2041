/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service.Sp;

import Model.Sp.ChiTietSanPham;
import Model.Sp.SanPham;
import ViewModel.ctspView;
import java.util.List;

/**
 *
 * @author latha
 */
public interface IqlctspService {

    List<ctspView> findAll();

    List<ctspView> findById(String id);

    ChiTietSanPham Save(ChiTietSanPham ctsp);

    ChiTietSanPham Update(ChiTietSanPham ctsp, String id);

    String delete(String id);

    long totalCount();
}
