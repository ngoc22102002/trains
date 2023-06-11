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
public class tau {
    private String ID,name;
    private ArrayList<toa> toa;
    private ArrayList<ga> ga;
    private ArrayList<String> time_come,time_go;
    private ArrayList<Integer> distance;

    public tau() {
    }

    public tau(String ID, String name, ArrayList<toa> toa, ArrayList<ga> ga, ArrayList<String> time_come, ArrayList<String> time_go, ArrayList<Integer> distance) {
        this.ID = ID;
        this.name = name;
        this.toa = toa;
        this.ga = ga;
        this.time_come = time_come;
        this.time_go = time_go;
        this.distance = distance;
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

    public ArrayList<toa> getToa() {
        return toa;
    }

    public void setToa(ArrayList<toa> toa) {
        this.toa = toa;
    }

    public ArrayList<ga> getGa() {
        return ga;
    }

    public void setGa(ArrayList<ga> ga) {
        this.ga = ga;
    }

    public ArrayList<String> getTime_come() {
        return time_come;
    }

    public void setTime_come(ArrayList<String> time_come) {
        this.time_come = time_come;
    }

    public ArrayList<String> getTime_go() {
        return time_go;
    }

    public void setTime_go(ArrayList<String> time_go) {
        this.time_go = time_go;
    }

    public ArrayList<Integer> getDistance() {
        return distance;
    }

    public void setDistance(ArrayList<Integer> distance) {
        this.distance = distance;
    }
    
}
