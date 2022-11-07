/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service.Nv_KH;


import ViewModel.nvView;
import java.util.List;

/**
 *
 * @author latha
 */
public interface INvService {

    List<nvView> findAll();

    List<nvView> findById(String Ma);

    nvView saveNV(nvView Nv);

    nvView UpdateNv(nvView Nv);
    
    String DeleteNV(String Id);
}
