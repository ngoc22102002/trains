/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import controller.destinations;
import static java.lang.String.format;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import model.Booking;
import model.Destination;
import model.Stations;
import model.trains;
import model.user;
import java.time.LocalTime;
import java.util.Calendar;
import model.ga;

/**
 *
 * @author phamn
 */
public class dao extends DBconect {

    public String getDate() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(currentDate);
        return formattedDate;
    }

    public user doLogin(String argUser, String argPass) {
        user user = null;
        String queryString = "SELECT * from  [user] WHERE (phone = ? OR email = ? ) AND password = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(queryString);
            ps.setString(1, argUser);
            ps.setString(2, argUser);
            ps.setString(3, argPass);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                user = new user();
                user.setId(result.getString("id"));
                user.setName(result.getString("name"));
                user.setEmail(result.getString("email"));
                user.setPhone(result.getString("phone"));
                user.setPassword(result.getString("password"));
                user.setRule(result.getString("rule"));
                user.setDob(result.getString("dob"));
                user.setAddress(result.getString("address"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return user;
    }

    public ArrayList<HashMap<String, String>> searchticket(String from, String to, String date) {
        //2023-03-22
        LocalDateTime date1 = LocalDateTime.now();
        String[] dat = date.split("-");
        ArrayList<HashMap<String, String>> trains = new ArrayList<HashMap<String, String>>();
        ArrayList<Destination> des = getAll(from, "");
        for (Destination d : des) {
            String[] ti = d.getTime().split(":");
            LocalDateTime date2 = LocalDateTime.of(Integer.parseInt(dat[0]), Integer.parseInt(dat[1]), Integer.parseInt(dat[2]), Integer.parseInt(ti[0]), Integer.parseInt(ti[1]));
            if (date1.isAfter(date2)) {
                continue;
            }
            String time = d.getTime();
            int fare = Integer.parseInt(d.getFare());
            String id = d.getId();
            Destination b = d;
            while (true) {
                if (b.getStation_to().equals(to)) {
                    HashMap<String, String> hashMap = new HashMap<>();
                    trains tr = getTrains(b.getTrain_id());
                    hashMap.put("name", tr.getName());
                    hashMap.put("destination_id", id);
                    hashMap.put("coach", tr.getType());
                    hashMap.put("train_id", tr.getId());
                    hashMap.put("code", tr.getCode());
                    hashMap.put("time", time);
                    hashMap.put("fare", String.valueOf(fare));
                    trains.add(hashMap);
                    break;
                }
                ArrayList<Destination> a = getAll(b.getStation_to(), b.getTrain_id());
                if (a.size() == 0) {
                    break;
                }
                b = a.get(0);
                id += "," + b.getId();
                fare += Integer.parseInt(b.getFare());
            }
        }
        return trains;

    }

    public ArrayList<HashMap<String, String>> SearchTrainFromTo(String from, String to, String coach) {
        ArrayList<HashMap<String, String>> trains = new ArrayList<HashMap<String, String>>();
        String sql = null;
        if (coach != null && !coach.equals("any")) {
            sql = "SELECT destinations.*,trains.type as coach,trains.id as trainId,trains.name,trains.code,trains.type FROM trains"
                    + " INNER JOIN destinations ON "
                    + " trains.id = destinations.train_id"
                    + " WHERE destinations.station_from = '" + from + "'"
                    + " AND destinations.station_to = '" + to + "'"
                    + " AND trains.type = '" + coach + "'"
                    + " ORDER BY name ASC";
        } else {
            sql = "SELECT destinations.*,trains.type as coach,trains.id as trainId,trains.name,trains.code,trains.type FROM trains"
                    + " INNER JOIN destinations ON "
                    + " trains.id = destinations.train_id"
                    + " WHERE destinations.station_from = '" + from + "'"
                    + " AND destinations.station_to = '" + to + "'"
                    + " ORDER BY name ASC";
        }

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                HashMap<String, String> tempTrain = new HashMap<String, String>();
                tempTrain.put("name", result.getString("name"));
                tempTrain.put("destination_id", result.getString("id"));
                tempTrain.put("coach", result.getString("coach"));
                tempTrain.put("train_id", result.getString("trainId"));
                tempTrain.put("code", result.getString("code"));
                tempTrain.put("time", result.getString("time"));

                tempTrain.put("fare", result.getString("fare"));
                trains.add(tempTrain);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return trains;
    }

    public void InsertNewDestination(Destination d) {
        String sqlQury = "INSERT INTO destinations(id,station_from,station_to,train_id,time,status,fare,last_activity,last_modify_by) "
                + " VALUES('" + d.getId() + "','" + d.getStation_from() + "','" + d.getStation_to() + "','" + d.getTrain_id() + "','" + d.getTime() + "','" + d.getStatus() + "','" + d.getFare() + "','" + d.getLast_activity() + "','" + d.getLast_modify_by() + "')";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlQury);
            ps.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void DeleteDestination(String id) {
        String querySting = "DELETE FROM destinations WHERE id='" + id + "'";
        try {
            PreparedStatement ps = connection.prepareStatement(querySting);
            ps.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void CreateNewTrain(trains t) {

        String sqlQquery = "INSERT INTO trains (name,id,code,type,total_seat)"
                + " VALUES(?,?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sqlQquery);
            ps.setString(1, t.getName());
            ps.setString(2, t.getId());
            ps.setString(3, t.getCode());
            ps.setString(4, t.getType());
            ps.setString(5, t.getTotal_seat());
            ResultSet result = ps.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            //System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<Destination> getAll(String stationFrom, String trainId) {
        ArrayList<Destination> list = new ArrayList<>();
        String queryString = null;
        if (trainId.equals("")) {
            queryString = "SELECT * FROM  destinations WHERE station_from ='" + stationFrom + "'";
        } else {
            queryString = "SELECT * FROM destinations WHERE station_from ='" + stationFrom + "' AND train_id = '" + trainId + "'";
        }

        try {
            PreparedStatement ps = connection.prepareStatement(queryString);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                Destination d = new Destination();
                d.setStation_from(result.getString("station_from"));
                d.setId(result.getString("id"));
                d.setStation_to(result.getString("station_to"));
                d.setTrain_id(result.getString("train_id"));
                d.setTime(result.getString("time"));
                d.setStatus(result.getString("status"));
                d.setFare(result.getString("fare"));
                d.setLast_activity(result.getString("last_activity"));
                d.setLast_modify_by(result.getString("last_modify_by"));
                list.add(d);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    public int randomID(String tablename) {
        int i = 0;
        Random r = new Random();
        try {
            while (true) {
                i = r.nextInt(1000);
                String sqlQuery = "SELECT * FROM ? WHERE ID=?";
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
                ps.setString(1, tablename);
                ps.setString(2, String.valueOf(i));
                ResultSet result = ps.executeQuery();
                if (!result.next()) {
                    return i;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;

    }

    public ArrayList<ga> getAllStations() {
        ArrayList<ga> stations = new ArrayList<>();
        String sqlQuery = "SELECT * FROM ga ORDER BY name ASC";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                ga temp = new ga();
                temp.setID(result.getString("id"));
                temp.setName(result.getString("name"));
                temp.setAdd(result.getString("add"));
                temp.setMap(result.getString("map"));
                stations.add(temp);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return stations;
    }

    public ArrayList<trains> getAll() {
        ArrayList<trains> trains = new ArrayList<>();
        String sqlQuery = "SELECT * FROM trains ORDER BY code ASC, name ASC";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                trains temp = new trains();
                temp.setId(result.getString("id"));
                temp.setName(result.getString("name"));
                temp.setCode(result.getString("code"));
                temp.setType(result.getString("type"));
                temp.setTotal_seat(result.getString("total_seat"));
                trains.add(temp);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return trains;
    }

    public void InsertNewStations(ga s) {
        String query = "INSERT INTO ga VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, s.getID());
            ps.setString(2, s.getName());
            ps.setString(3, s.getAdd());
            ps.setString(4, s.getMap());
            ps.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void deleteStation(String id) {
        String query = "Delete ga where ID=" + id + "";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean InsertNew(user u) {

        if (this.isEmailOrPhoneExist(u.getEmail()) || this.isEmailOrPhoneExist(u.getPhone())) {
            return false;
        }
        String sqlQquery = "INSERT INTO [user] values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlQquery);
            ps.setString(1, u.getId());
            ps.setString(2, u.getName());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getPassword());
            ps.setString(5, u.getPhone());
            ps.setString(6, u.getRule());
            ps.setString(7, u.getDob());
            ps.setString(8, u.getAddress());
            ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("User.InsertNew: " + e.getMessage());
        }

        return true;
    }

    public boolean isEmailOrPhoneExist(String phoneOrEmail) {
        boolean isExist = false;
        String queryString = "SELECT * FROM user WHERE phone = '" + phoneOrEmail + "' OR email = '" + phoneOrEmail + "'";
        try {
            PreparedStatement ps = connection.prepareStatement(queryString);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                isExist = true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return isExist;
    }

    public Destination getDestination(String argId) {
        Destination d = new Destination();
        String sql = "SELECT * FROM destinations WHERE id = '" + argId + "'";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                d.setId(result.getString("id"));
                d.setStation_from(result.getString("station_from"));
                d.setStation_to(result.getString("station_to"));
                d.setTrain_id(result.getString("train_id"));
                d.setTime(result.getString("time"));
                d.setStatus(result.getString("status"));
                d.setFare(result.getString("fare"));
                d.setLast_activity(result.getString("last_activity"));
                d.setLast_modify_by(result.getString("last_modify_by"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return d;
    }

    public ArrayList<HashMap<String,String>> FindByUser(String id) {
        ArrayList<HashMap<String,String>> list = new ArrayList<>();
        String sql = "SELECT * FROM booking WHERE passenger_id='" + id + "' ORDER BY id DESC";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                HashMap<String,String> a=new HashMap<>();
                 Booking b = new Booking();
                 String[] str=rs.getString("destination_id").split(",");
                 Destination d1=getDestination(str[0]);
                 Destination d2=getDestination(str[str.length-1]);
                 Stations s1=getStation(d1.getStation_from());
                 Stations s2=getStation(d2.getStation_to());
                 trains tra=getTrains(d1.getTrain_id());
                 a.put("booking_date", rs.getString("booking_date"));
                 a.put("journey_date", rs.getString("journey_date"));
                 a.put("num", rs.getString("seat_numbers"));
                 a.put("from", s1.getName());
                 a.put("to", s2.getName());
                 a.put("name", tra.getName());
                 a.put("code", tra.getCode());
                 list.add(a);          
            }         
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    public void insetBooking(String user_id, String destination_id, String date, String num) {
        LocalDateTime date1 = LocalDateTime.now();;

        String sql = "INSERT INTO[booking]VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, String.valueOf(randomID("booking")));
            ps.setString(2, destination_id);
            ps.setString(3, String.valueOf(date1));
            ps.setString(4, date);
            ps.setString(5, num);
            ps.setString(6, user_id);
            ps.setString(7, "ye");
            ps.setString(8, "");
            ps.setString(9, "");
            ps.executeQuery();
        } catch (SQLException e) {
        }
    }

    public Destination stationFrom(String stationFrom, String trainId) {
        Destination d = null;
        String queryString = "SELECT * FROM destinations WHERE station_from ='" + stationFrom + "' AND train_id = '" + trainId + "'";

        try {
            PreparedStatement ps = connection.prepareStatement(queryString);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                d = new Destination();
                d.setStation_from(result.getString("station_from"));
                d.setId(result.getString("id"));
                d.setStation_to(result.getString("station_to"));
                d.setTrain_id(result.getString("train_id"));
                d.setTime(result.getString("time"));
                d.setStatus(result.getString("status"));
                d.setFare(result.getString("fare"));
                d.setLast_activity(result.getString("last_activity"));
                d.setLast_modify_by(result.getString("last_modify_by"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return d;
    }

    public Destination stationTo(String stationFrom, String trainId) {
        Destination d = null;
        String queryString = "SELECT * FROM destinations WHERE station_to ='" + stationFrom + "' AND train_id = '" + trainId + "'";

        try {
            PreparedStatement ps = connection.prepareStatement(queryString);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                d = new Destination();
                d.setStation_from(result.getString("station_from"));
                d.setId(result.getString("id"));
                d.setStation_to(result.getString("station_to"));
                d.setTrain_id(result.getString("train_id"));
                d.setTime(result.getString("time"));
                d.setStatus(result.getString("status"));
                d.setFare(result.getString("fare"));
                d.setLast_activity(result.getString("last_activity"));
                d.setLast_modify_by(result.getString("last_modify_by"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return d;
    }

    public ArrayList<Integer> getSeat(String destination_id, String journey_date) {

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        ArrayList<Integer> d = new ArrayList<>();
        String[] a = destination_id.split(",");
        for (int i = 0; i < a.length; i++) {
            d.add(Integer.parseInt(a[i]));
        }
        Destination xx;
        // bing.ai
        while (true) {
            xx = getDestination(String.valueOf(d.get(0)));
            Destination de = stationTo(xx.getStation_from(), xx.getTrain_id());
            if (de == null) {
                break;
            } else {
                d.add(0, Integer.parseInt(de.getId()));
            }
        }
        while (true) {
            xx = getDestination(String.valueOf(d.get(d.size() - 1)));
            Destination de = stationFrom(xx.getStation_to(), xx.getTrain_id());
            if (de == null) {
                break;
            } else {
                d.add(Integer.parseInt(de.getId()));
            }
        }
        for (int i = 0; i < d.size(); i++) {
            for (int j = 0; j < d.size() - i; j++) {
                ArrayList<Integer> al = new ArrayList<>();
                al.add(d.get(j));
                for (int k = 0; k < i; k++) {
                    al.add(d.get(j + k + 1));
                }
                list.add(al);

            }
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < a.length; j++) {
                if (list.get(i).contains(Integer.parseInt(a[j]))) {
                    break;
                }
                if (j == a.length - 1) {
                    list.remove(i);
                    i--;
                }
            }
        }
        String sql = "SELECT seat_numbers FROM [booking] where destination_id=? and journey_date=?";
        ArrayList<Integer> seat = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String s = String.valueOf(list.get(i).get(0));
            for (int j = 1; j < list.get(i).size(); j++) {
                s += "," + list.get(i).get(j);
            }
            try {
                System.out.println(s);
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1,s);
                ps.setString(2,journey_date);
                ResultSet result = ps.executeQuery();
                while (result.next()) {        
                    seat.add(Integer.parseInt(result.getString("seat_numbers")));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return seat;
    }

    public trains getTrains(String trnId) {
        trains t = new trains();
        String sql = "SELECT * FROM trains WHERE id='" + trnId + "'";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                t.setName(result.getString("name"));
                t.setId(result.getString("id"));
                t.setCode(result.getString("code"));
                t.setTotal_seat(result.getString("total_seat"));
                t.setType(result.getString("type"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return t;
    }

    public Stations getStation(String staionId) {
        Stations station = new Stations();
        String sqlQuery = "SELECT * FROM Stations WHERE id='" + staionId + "'";

        try {
            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                station.setName(result.getString("name"));
                station.setId(result.getString("id"));
                station.setAddress(result.getString("address"));
                station.setContact(result.getString("contact"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return station;
    }

    public void Delete(String id) {
        String sql = "DELETE FROM trains WHERE id = '" + id + "'";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        dao d = new dao();
        ga g=new ga("5","5", "5", "<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3833.8858464181694!2d108.2067638746842!3d16.071412439380357!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x314218495c4839ed%3A0x5c008faec7ffd1c0!2sDa%20Nang%20Railway%20Station!5e0!3m2!1sen!2s!4v1685936713187!5m2!1sen!2s\" width=\"600\" height=\"450\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe>");
        System.out.println(g.getMap());
        d.InsertNewStations(g);
    }
}
