/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

/**
 *
 * @author latha
 */
public class chView {
        private String id, ma, ten, diaChi, thanhpho, quocgia;

    public chView() {
    }

    public chView(String id, String ma, String ten, String diaChi, String thanhpho, String quocgia) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.diaChi = diaChi;
        this.thanhpho = thanhpho;
        this.quocgia = quocgia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getThanhpho() {
        return thanhpho;
    }

    public void setThanhpho(String thanhpho) {
        this.thanhpho = thanhpho;
    }

    public String getQuocgia() {
        return quocgia;
    }

    public void setQuocgia(String quocgia) {
        this.quocgia = quocgia;
    }

    @Override
    public String toString() {
        return  ma + " : " + ten ;
    }

}
