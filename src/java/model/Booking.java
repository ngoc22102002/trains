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
public class Booking {
    private String ID,ID_user,ID_ve,ID_toa_tau;
    private Date ngay_mua,ngay_khoi_hanh;

    public Booking() {
    }

    public Booking(String ID, String ID_user, String ID_ve, String ID_toa_tau, Date ngay_mua, Date ngay_khoi_hanh) {
        this.ID = ID;
        this.ID_user = ID_user;
        this.ID_ve = ID_ve;
        this.ID_toa_tau = ID_toa_tau;
        this.ngay_mua = ngay_mua;
        this.ngay_khoi_hanh = ngay_khoi_hanh;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID_user() {
        return ID_user;
    }

    public void setID_user(String ID_user) {
        this.ID_user = ID_user;
    }

    public String getID_ve() {
        return ID_ve;
    }

    public void setID_ve(String ID_ve) {
        this.ID_ve = ID_ve;
    }

    public String getID_toa_tau() {
        return ID_toa_tau;
    }

    public void setID_toa_tau(String ID_toa_tau) {
        this.ID_toa_tau = ID_toa_tau;
    }

    public Date getNgay_mua() {
        return ngay_mua;
    }

    public void setNgay_mua(Date ngay_mua) {
        this.ngay_mua = ngay_mua;
    }

    public Date getNgay_khoi_hanh() {
        return ngay_khoi_hanh;
    }

    public void setNgay_khoi_hanh(Date ngay_khoi_hanh) {
        this.ngay_khoi_hanh = ngay_khoi_hanh;
    }
    
}
