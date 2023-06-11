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
import java.text.ParseException;
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
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javafx.scene.input.KeyCode;
import model.ga;
import model.ghe;
import model.lo_trinh;
import model.tau;
import model.toa;
import model.ve;

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

    public lo_trinh getLo_Trinh(String ga_from, String ga_to) {
        lo_trinh d = new lo_trinh();
        String sql = "SELECT * FROM lo_trinh WHERE ga_from = '" + ga_from + "' and ga_to='" + ga_to + "'";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                d.setID(result.getString("ID"));
                d.setID_tau(result.getString("ID_tau"));
                d.setGa_from(result.getString("ga_from"));
                d.setGa_to(result.getString("ga_to"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return d;
    }

    public ve getve(String ID_ghe, String ID_lo_trinh, String ngay) {
        ve v = new ve();
        String sql = "SELECT * FROM ve WHERE ID_lo_trinh='" + ID_lo_trinh + "'and ID_ghe='" + ID_ghe + "'and ngay='" + ngay + "';";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                v.setID(result.getString("ID"));
                v.setID_ghe(result.getString("ID_ghe"));
                v.setID_lo_trinh(result.getString("ID_lo_trinh"));
                v.setGia(Long.parseLong(result.getString("gia")));
                return v;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String sqll = "SELECT * FROM ve WHERE ID_lo_trinh='" + ID_lo_trinh + "'and ID_ghe='" + ID_ghe + "';";
        try {
            PreparedStatement ps = connection.prepareStatement(sqll);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                v.setID(result.getString("ID"));
                v.setID_ghe(result.getString("ID_ghe"));
                v.setID_lo_trinh(result.getString("ID_lo_trinh"));
                v.setGia(Long.parseLong(result.getString("gia")));
                return v;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public ghe getghe(String trnId, String ga_from, String ga_to, String ngay) {
        ghe g = new ghe();
        String sql = "SELECT * FROM ghe WHERE ID='" + trnId + "';";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                g.setID(result.getString("ID"));
                g.setName(result.getString("name"));
                lo_trinh l = getLo_Trinh(ga_from, ga_to);
                ve v = getve(g.getID(), l.getID(), ngay);
                System.out.println("");
                g.setGia(v.getGia());
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return g;
    }

    public ArrayList<ghe> getghee(String trnId, String ga_from, String ga_to, String ngay) {
        ArrayList<ghe> list = new ArrayList<>();
        String sql = "SELECT * FROM toa_ghe WHERE ID_toa='" + trnId + "' ORDER BY stt_ghe ASC;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                ghe g = getghe(result.getString("ID_ghe"), ga_from, ga_to, ngay);
                list.add(g);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    public toa getToa(String trnId, String ga_from, String ga_to, String ngay) {
        toa t = new toa();
        String sql = "SELECT * FROM toa WHERE ID='" + trnId + "';";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                t.setID(result.getString("ID"));
                t.setName(result.getString("name"));
                t.setType(Integer.parseInt(result.getString("type")));
                t.setGhe(getghee(trnId, ga_from, ga_to, ngay));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return t;
    }
    public ArrayList<ArrayList<Integer>> getc(int a,int b,int c){
        ArrayList<Integer>list;
        ArrayList<ArrayList<Integer>> listt=new ArrayList<>();
        for(int i=1;i<a;i++){
            for(int j=i+1;j<=a;j++){
                list=new ArrayList<>();
                list.add(i);
                list.add(j);
                listt.add(list);
            }
        }
        for(int i=0;i<listt.size();i++){
            if(listt.get(i).get(0)<=b&&listt.get(i).get(1)<=b||listt.get(i).get(0)>=c&&listt.get(i).get(1)>=c){
                listt.remove(i);
                i--;
            }
        }
        return listt;
        
    }
    public String getga(String ID_tau,int stt){
        String sql = "SELECT * FROM tau_ga WHERE ID_tau='" + ID_tau + "'and stt='"+stt+"';";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                        return result.getString("ID_ga");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    public int tong_ga(String ID_tau){
        String sql = "SELECT * FROM tau_ga WHERE ID_tau='" + ID_tau + "' ORDER BY stt ASC;";
        int i=0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                i++;                           
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return i;
    }
    
    public ArrayList<Integer> ghe_da_dat(String ID_toa_tau,String ngay,String ID_tau,String from,String to){
        ArrayList<ArrayList<Integer>>list=getc(tong_ga(ID_tau), getSttGa(ID_tau, from), getSttGa(ID_tau, to));
        ArrayList<lo_trinh> lst=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            lo_trinh l=getLo_Trinh(getga(ID_tau,list.get(i).get(0)), getga(ID_tau,list.get(i).get(1)));
            lst.add(l);
        }      
        String sql = "SELECT * FROM booking WHERE ID_toa_tau=? and ngay_di=? and lo_trinh =?;"; 
        ArrayList<Integer> al=new ArrayList<>();
        for(int i=0;i<lst.size();i++){
            try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,ID_toa_tau);
            ps.setString(2,ngay);
            ps.setString(3,lst.get(i).getID());
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                al.add(Integer.parseInt(result.getString("ghe")));              
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
            
        }
        return al;
    }

    public ArrayList<toa> getToaa(String trnId, String ga_from, String ga_to, String ngay) {
        ArrayList<toa> list = new ArrayList<>();

        String sql = "SELECT * FROM toa_tau WHERE ID_tau='" + trnId + "' ORDER BY stt ASC;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                toa toa = getToa(result.getString("ID_toa"), ga_from, ga_to, ngay);
                ArrayList<Integer>li=ghe_da_dat(result.getString("ID"), ngay, trnId, ga_from, ga_to);
                for(int i=0;i<toa.getGhe().size();i++){
                    if(li.contains(i+1)){
                        toa.getGhe().get(i).setStatus(1);
                    }
                }
                list.add(toa);
                
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<tau> getTauu(String ga_from, String ga_to, String ngay) {
        ArrayList<tau> list = new ArrayList<>();
        String sql = "SELECT * FROM lo_trinh WHERE ga_from='" + ga_from + "'and ga_to='" + ga_to + "';";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                tau tau = getTau(result.getString("ID_tau"), ga_from, ga_to, ngay);
                list.add(tau);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;
    }

    public tau getTau(String trnId, String ga_from, String ga_to, String ngay) {
        tau t = new tau();
        String sql = "SELECT * FROM tau WHERE ID='" + trnId + "'";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                t.setID(trnId);
                t.setName(result.getString("name"));
                t.setToa(getToaa(trnId, ga_from, ga_to, ngay));
                t.setGa(getGaa(trnId, ga_from, ga_to));
                t.setDistance(getDistance(trnId, ga_from, ga_to));
                t.setTime_go(getTimeGo(trnId, ga_from, ga_to));
                t.setTime_come(getTimecome(trnId, ga_from, ga_to));

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return t;
    }

    public ga getGa(String trnId) {
        ga t = new ga();
        String sql = "SELECT * FROM ga WHERE ID='" + trnId + "'";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                t.setID(trnId);
                t.setName(result.getString("name"));
                t.setAdd(result.getString("add"));
                t.setMap(result.getString("map"));

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return t;
    }

    public int getSttGa(String ID_tau, String ID_ga) {
        String sql = "SELECT * FROM tau_ga WHERE ID_tau='" + ID_tau + "' and ID_ga='" + ID_ga + "'ORDER BY stt ASC";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                return Integer.parseInt(result.getString("stt"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<ga> getGaa(String trnId, String ga_from, String ga_to) {
        ArrayList<ga> list = new ArrayList<>();
        int a = getSttGa(trnId, ga_from);
        int b = getSttGa(trnId, ga_to);
        String sql = "SELECT * FROM tau_ga WHERE ID_tau='" + trnId + "'and stt >='" + a + "' and stt<='" + b + "'ORDER BY stt ASC";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                ga ga = getGa(result.getString("ID_ga"));
                list.add(ga);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Integer> getDistance(String trnId, String ga_from, String ga_to) {
        ArrayList<Integer> list = new ArrayList<>();
        int a = getSttGa(trnId, ga_from);
        int b = getSttGa(trnId, ga_to);
        String sql = "SELECT * FROM tau_ga WHERE ID_tau='" + trnId + "'and stt >='" + a + "' and stt<='" + b + "'ORDER BY stt ASC";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while (result.next()) {

                list.add(Integer.parseInt(result.getString("distance")));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<String> getTimecome(String trnId, String ga_from, String ga_to) {
        ArrayList<String> list = new ArrayList<>();
        int a = getSttGa(trnId, ga_from);
        int b = getSttGa(trnId, ga_to);
        String sql = "SELECT * FROM tau_ga WHERE ID_tau='" + trnId + "'and stt >='" + a + "' and stt<='" + b + "'ORDER BY stt ASC";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while (result.next()) {

                list.add(result.getString("time_come"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<String> getTimeGo(String trnId, String ga_from, String ga_to) {
        ArrayList<String> list = new ArrayList<>();
        int a = getSttGa(trnId, ga_from);
        int b = getSttGa(trnId, ga_to);
        String sql = "SELECT * FROM tau_ga WHERE ID_tau='" + trnId + "'and stt >='" + a + "' and stt<='" + b + "'ORDER BY stt ASC";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while (result.next()) {

                list.add(result.getString("time_go"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
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
        tau t= d.getTau("SE1", "1", "7", "08/06/2023");
        for(int i=0;i<t.getToa().size();i++){
            for(int j=0;j<t.getToa().get(i).getGhe().size();j++){
                System.out.println(t.getToa().get(i).getGhe().get(j).getID()+":"+t.getToa().get(i).getGhe().get(j).getGia()+":"+t.getToa().get(i).getGhe().get(j).getStatus());
            }
        }
       

    }
}
