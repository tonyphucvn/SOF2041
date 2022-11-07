/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service.CV_CH;

import Model.Cv_Ch.ChucVu;
import Model.Cv_Ch.CuaHang;
import ViewModel.chView;
import ViewModel.cvView;
import java.util.List;

/**
 *
 * @author latha
 */
public interface Icv_ch_service {

    List<cvView> findAllCV();

    List<chView> findAllCH();

    chView SaveCH(chView ch);

    chView UpdateCH(chView ch);

    String DeleteCH(String id);
}
