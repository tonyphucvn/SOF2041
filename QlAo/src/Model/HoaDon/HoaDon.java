/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.HoaDon;

import java.util.Date;

/**
 *
 * @author latha
 */
public class HoaDon {

    private String id;
    private String idKh,idNV,MaHoaDon;
    private String NgayTao,NgayThanhToan;
    private String TenNgNhan,DiaChi,SDT;
    private Integer TrangThai; 

    public HoaDon() {
    }

    public HoaDon(String id, String idKh, String idNV, String MaHoaDon, String NgayTao, String NgayThanhToan, String TenNgNhan, String DiaChi, String SDT, Integer TrangThai) {
        this.id = id;
        this.idKh = idKh;
        this.idNV = idNV;
        this.MaHoaDon = MaHoaDon;
        this.NgayTao = NgayTao;
        this.NgayThanhToan = NgayThanhToan;
        this.TenNgNhan = TenNgNhan;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
        this.TrangThai = TrangThai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdKh() {
        return idKh;
    }

    public void setIdKh(String idKh) {
        this.idKh = idKh;
    }

    public String getIdNV() {
        return idNV;
    }

    public void setIdNV(String idNV) {
        this.idNV = idNV;
    }

    public String getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(String MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(String NgayTao) {
        this.NgayTao = NgayTao;
    }

    public String getNgayThanhToan() {
        return NgayThanhToan;
    }

    public void setNgayThanhToan(String NgayThanhToan) {
        this.NgayThanhToan = NgayThanhToan;
    }

    public String getTenNgNhan() {
        return TenNgNhan;
    }

    public void setTenNgNhan(String TenNgNhan) {
        this.TenNgNhan = TenNgNhan;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public Integer getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(Integer TrangThai) {
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "id=" + id + ", idKh=" + idKh + ", idNV=" + idNV + ", MaHoaDon=" + MaHoaDon + ", NgayTao=" + NgayTao + ", NgayThanhToan=" + NgayThanhToan + ", TenNgNhan=" + TenNgNhan + ", DiaChi=" + DiaChi + ", SDT=" + SDT + ", TrangThai=" + TrangThai + '}';
    }
    
    

    
}
