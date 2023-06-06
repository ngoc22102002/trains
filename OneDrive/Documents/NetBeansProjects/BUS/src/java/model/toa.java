/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author phamn
 */
public class toa {
    private String ID,name;
    private int type;
    private ArrayList<ghe> ghe;

    public toa() {
    }

    public toa(String ID, String name, int type, ArrayList<ghe> ghe) {
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.ghe = ghe;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<ghe> getGhe() {
        return ghe;
    }

    public void setGhe(ArrayList<ghe> ghe) {
        this.ghe = ghe;
    }
    
}
