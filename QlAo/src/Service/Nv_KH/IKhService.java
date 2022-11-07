/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service.Nv_KH;


import ViewModel.khView;
import java.util.List;

/**
 *
 * @author latha
 */
public interface IKhService {

    List<khView> findAll();

    List<khView> findByMa(String Ma);

    khView save(khView kh);

    khView Update(khView kh);

    String Delete(String Id);
}
