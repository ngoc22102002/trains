/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author phamn
 */
public class lo_trinh {
    private String ID,name,ID_tau,ga_from,ga_to;

    public lo_trinh() {
    }

    public lo_trinh(String ID, String name, String ID_tau, String ga_from, String ga_to) {
        this.ID = ID;
        this.name = name;
        this.ID_tau = ID_tau;
        this.ga_from = ga_from;
        this.ga_to = ga_to;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID_tau() {
        return ID_tau;
    }

    public void setID_tau(String ID_tau) {
        this.ID_tau = ID_tau;
    }

    public String getGa_from() {
        return ga_from;
    }

    public void setGa_from(String ga_from) {
        this.ga_from = ga_from;
    }

    public String getGa_to() {
        return ga_to;
    }

    public void setGa_to(String ga_to) {
        this.ga_to = ga_to;
    }
    
}
