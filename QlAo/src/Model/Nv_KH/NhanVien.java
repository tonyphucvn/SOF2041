/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Nv_KH;

import Model.Cv_Ch.ChucVu;
import Model.Cv_Ch.CuaHang;

/**
 *
 * @author latha
 */
public class NhanVien {
//
   private String Id;
   private String Ma;
   private String Ten;
   private String TenDem;
   private String Ho;
   private String GioiTinh;
   private String SinhNhat;
   private String DiaChi ;
   private String sdt ;
   private String MK ;
   private CuaHang idCuaHang ;
   private ChucVu idCV ;
   private String idguibc;
   private Integer trangthai ;

    public NhanVien() {
    }

    public NhanVien(String Id, String Ma, String Ten, String TenDem, String Ho, String GioiTinh, String SinhNhat, String DiaChi, String sdt, String MK, CuaHang idCuaHang, ChucVu idCV, String idguibc, Integer trangthai) {
        this.Id = Id;
        this.Ma = Ma;
        this.Ten = Ten;
        this.TenDem = TenDem;
        this.Ho = Ho;
        this.GioiTinh = GioiTinh;
        this.SinhNhat = SinhNhat;
        this.DiaChi = DiaChi;
        this.sdt = sdt;
        this.MK = MK;
        this.idCuaHang = idCuaHang;
        this.idCV = idCV;
        this.idguibc = idguibc;
        this.trangthai = trangthai;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getMa() {
        return Ma;
    }

    public void setMa(String Ma) {
        this.Ma = Ma;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public String getTenDem() {
        return TenDem;
    }

    public void setTenDem(String TenDem) {
        this.TenDem = TenDem;
    }

    public String getHo() {
        return Ho;
    }

    public void setHo(String Ho) {
        this.Ho = Ho;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getSinhNhat() {
        return SinhNhat;
    }

    public void setSinhNhat(String SinhNhat) {
        this.SinhNhat = SinhNhat;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getMK() {
        return MK;
    }

    public void setMK(String MK) {
        this.MK = MK;
    }

    public CuaHang getIdCuaHang() {
        return idCuaHang;
    }

    public void setIdCuaHang(CuaHang idCuaHang) {
        this.idCuaHang = idCuaHang;
    }

    public ChucVu getIdCV() {
        return idCV;
    }

    public void setIdCV(ChucVu idCV) {
        this.idCV = idCV;
    }

    public String getIdguibc() {
        return idguibc;
    }

    public void setIdguibc(String idguibc) {
        this.idguibc = idguibc;
    }

    public Integer getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(Integer trangthai) {
        this.trangthai = trangthai;
    }

    @Override
    public String toString() {
        return "NhanVien{" + "Id=" + Id + ", Ma=" + Ma + ", Ten=" + Ten + ", TenDem=" + TenDem + ", Ho=" + Ho + ", GioiTinh=" + GioiTinh + ", SinhNhat=" + SinhNhat + ", DiaChi=" + DiaChi + ", sdt=" + sdt + ", MK=" + MK + ", idCuaHang=" + idCuaHang + ", idCV=" + idCV + ", idguibc=" + idguibc + ", trangthai=" + trangthai + '}';
    }

   

}
