/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author phamn
 */
public class ve {
    private String ID,ID_lo_trinh,ID_ghe;
    private long gia;
    private Date ngay;

    public ve() {
    }

    public ve(String ID, String ID_lo_trinh, String ID_ghe, long gia, Date ngay) {
        this.ID = ID;
        this.ID_lo_trinh = ID_lo_trinh;
        this.ID_ghe = ID_ghe;
        this.gia = gia;
        this.ngay = ngay;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID_lo_trinh() {
        return ID_lo_trinh;
    }

    public void setID_lo_trinh(String ID_lo_trinh) {
        this.ID_lo_trinh = ID_lo_trinh;
    }

    public String getID_ghe() {
        return ID_ghe;
    }

    public void setID_ghe(String ID_ghe) {
        this.ID_ghe = ID_ghe;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }
    
}
