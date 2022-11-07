/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import Model.Sp.DongSP;
import Model.Sp.MauSac;
import Model.Sp.NSX;
import Model.Sp.SanPham;
import java.math.BigDecimal;

/**
 *
 * @author latha
 */
public class ctspView {

    private String id;
    private SanPham idsp;
    private NSX idNsx;
    private MauSac idmauSac;
    private DongSP idDongSP;
    private Integer namBh;
    private String mota;
    private Integer soluongTon;
    private BigDecimal giaNhap, giaBan;


    public ctspView() {
    }

    public ctspView(String id, SanPham idsp, NSX idNsx, MauSac idmauSac, DongSP idDongSP, Integer namBh, String mota, Integer soluongTon, BigDecimal giaNhap, BigDecimal giaBan) {
        this.id = id;
        this.idsp = idsp;
        this.idNsx = idNsx;
        this.idmauSac = idmauSac;
        this.idDongSP = idDongSP;
        this.namBh = namBh;
        this.mota = mota;
        this.soluongTon = soluongTon;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
    }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SanPham getIdsp() {
        return idsp;
    }

    public void setIdsp(SanPham idsp) {
        this.idsp = idsp;
    }

    public NSX getIdNsx() {
        return idNsx;
    }

    public void setIdNsx(NSX idNsx) {
        this.idNsx = idNsx;
    }

    public MauSac getIdmauSac() {
        return idmauSac;
    }

    public void setIdmauSac(MauSac idmauSac) {
        this.idmauSac = idmauSac;
    }

    public DongSP getIdDongSP() {
        return idDongSP;
    }

    public void setIdDongSP(DongSP idDongSP) {
        this.idDongSP = idDongSP;
    }

    public Integer getNamBh() {
        return namBh;
    }

    public void setNamBh(Integer namBh) {
        this.namBh = namBh;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public Integer getSoluongTon() {
        return soluongTon;
    }

    public void setSoluongTon(Integer soluongTon) {
        this.soluongTon = soluongTon;
    }

    public BigDecimal getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(BigDecimal giaNhap) {
        this.giaNhap = giaNhap;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }



}
