/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service.Sp;

import ViewModel.ttCtsp;
import java.util.List;

/**
 *
 * @author latha
 */
public interface ILoaiSPservice {

    List<ttCtsp> getDongSp();

    ttCtsp SaveDsp(ttCtsp dsp);

    ttCtsp UpdateDsp(ttCtsp dsp);

    String DeleteDsp(String id);

    List<ttCtsp> getMauSac();

    ttCtsp SaveMS(ttCtsp ms);

    ttCtsp UpdateMS(ttCtsp ms);

    String DeleteMs(String id);

    List<ttCtsp> getNSX();

    ttCtsp SaveNSX(ttCtsp nsx);

    ttCtsp UpdateNSX(ttCtsp nsx);

    String DeleteNSX(String id);

    List<ttCtsp> getSanPham();

    ttCtsp SaveSp(ttCtsp sp);

    ttCtsp UpdateSp(ttCtsp sp);

    String DeleteSp(String id);
}
