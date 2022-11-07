/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.HoaDon;

import java.math.BigDecimal;

/**
 *
 * @author latha
 */
public class HdChiTiet {
    private String idHoaDon,idChiTietSp;
    private Integer SoLuong;
    private BigDecimal DonGia;

    public HdChiTiet() {
    }

    public HdChiTiet(String idHoaDon, String idChiTietSp, Integer SoLuong, BigDecimal DonGia) {
        this.idHoaDon = idHoaDon;
        this.idChiTietSp = idChiTietSp;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
    }

    public String getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(String idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getIdChiTietSp() {
        return idChiTietSp;
    }

    public void setIdChiTietSp(String idChiTietSp) {
        this.idChiTietSp = idChiTietSp;
    }

    public Integer getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(Integer SoLuong) {
        this.SoLuong = SoLuong;
    }

    public BigDecimal getDonGia() {
        return DonGia;
    }

    public void setDonGia(BigDecimal DonGia) {
        this.DonGia = DonGia;
    }

    @Override
    public String toString() {
        return "HdChiTiet{" + "idHoaDon=" + idHoaDon + ", idChiTietSp=" + idChiTietSp + ", SoLuong=" + SoLuong + ", DonGia=" + DonGia + '}';
    }
    
    
}
