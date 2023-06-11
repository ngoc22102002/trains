/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author phamn
 */
public class Destination {
    public String id,station_from,station_to,train_id,time,status,fare,last_activity,last_modify_by;

    public Destination() {
    }

    public Destination(String id, String station_from, String station_to, String train_id, String time, String status, String fare, String last_activity, String last_modify_by) {
        this.id = id;
        this.station_from = station_from;
        this.station_to = station_to;
        this.train_id = train_id;
        this.time = time;
        this.status = status;
        this.fare = fare;
        this.last_activity = last_activity;
        this.last_modify_by = last_modify_by;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStation_from() {
        return station_from;
    }

    public void setStation_from(String station_from) {
        this.station_from = station_from;
    }

    public String getStation_to() {
        return station_to;
    }

    public void setStation_to(String station_to) {
        this.station_to = station_to;
    }

    public String getTrain_id() {
        return train_id;
    }

    public void setTrain_id(String train_id) {
        this.train_id = train_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public String getLast_activity() {
        return last_activity;
    }

    public void setLast_activity(String last_activity) {
        this.last_activity = last_activity;
    }

    public String getLast_modify_by() {
        return last_modify_by;
    }

    public void setLast_modify_by(String last_modify_by) {
        this.last_modify_by = last_modify_by;
    }

}
