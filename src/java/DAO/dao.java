/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import controller.destinations;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import model.Booking;
import model.Destination;
import model.Stations;
import model.trains;
import model.user;

/**
 *
 * @author phamn
 */
public class dao extends DBconect {

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
    public ArrayList<HashMap<String,String>> SearchTrainFromTo(String from,String to,String coach){
		ArrayList<HashMap<String,String>> trains = new ArrayList<HashMap<String,String>>();
		String sql = null;
		if(coach != null && !coach.equals("any")) {
			 sql = "SELECT destinations.*,trains.type as coach,trains.id as trainId,trains.name,trains.code,trains.type FROM trains"
					+ " INNER JOIN destinations ON "
					+ " trains.id = destinations.train_id"
					+ " WHERE destinations.station_from = '"+from+"'"
					+ " AND destinations.station_to = '"+to+"'"
					+ " AND trains.type = '"+coach+"'"
					+ " ORDER BY name ASC";
		}else {
			 sql = "SELECT destinations.*,trains.type as coach,trains.id as trainId,trains.name,trains.code,trains.type FROM trains"
					+ " INNER JOIN destinations ON "
					+ " trains.id = destinations.train_id"
					+ " WHERE destinations.station_from = '"+from+"'"
					+ " AND destinations.station_to = '"+to+"'"
					+ " ORDER BY name ASC";
		}
		
		try {  
                    PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet result = ps.executeQuery();
			while(result.next()) {
				HashMap<String,String> tempTrain = new HashMap<String,String>();
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
        String sqlQury = "INSERT INTO destinations(id,station_from,station_to,train_id,time,status,fare,last_activity,last_modify_by,total_seat,seat_range,type) "
                + " VALUES('"+d.getId()+"','" + d.getStation_from() + "','" + d.getStation_to() + "','" + d.getTrain_id() + "','" + d.getTime() + "','" + d.getStatus() + "','" + d.getFare() + "','" + d.getLast_activity() + "','" + d.getLast_modify_by() + "','" + d.getTotal_seat() + "','" + d.getSeat_range() + "','" + d.getType() + "')";
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

        String sqlQquery = "INSERT INTO trains (name,id,code,total_seat)"
                + " VALUES(?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sqlQquery);
            ps.setString(1, t.getName());
            ps.setString(2, t.getId());
            ps.setString(3, t.getCode());
            ps.setString(4, t.getTotal_seat());
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
                d.setTotal_seat(result.getString("total_seat"));
                d.setSeat_range(result.getString("seat_range"));
                d.setType(result.getString("type"));
                list.add(d);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
    public int randomID(String tablename){
        int i=0;
        Random r=new Random();
        try {
            while (true) {
                 i = r.nextInt(1000);
                String sqlQuery = "SELECT * FROM ? WHERE id=?";
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
                ps.setString(1, tablename);
                ps.setString(2, String.valueOf(i));
                ResultSet result = ps.executeQuery();
                if(!result.next()){
                    return i;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
        
    }

    public ArrayList<Stations> getAllStations() {
        ArrayList<Stations> stations = new ArrayList<>();
        String sqlQuery = "SELECT * FROM stations ORDER BY name ASC";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                Stations temp = new Stations();
                temp.setId(result.getString("id"));
                temp.setName(result.getString("name"));
                temp.setContact(result.getString("contact"));
                temp.setAddress(result.getString("address"));
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
        String sqlQuery = "SELECT * FROM trains";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                trains temp = new trains();
                temp.setId(result.getString("id"));
                temp.setName(result.getString("name"));
                temp.setCode(result.getString("code"));
                temp.setTotal_seat(result.getString("total_seat"));
                trains.add(temp);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return trains;
    }

    public void InsertNewStations(Stations s) {
        String query = "INSERT INTO stations(id,name, contact,address) VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, s.getId());
            ps.setString(2, s.getName());
            ps.setString(3, s.getContact());
            ps.setString(4, s.getAddress());
            ps.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
  public void deleteStation(String id) {
        String query = "Delete stations where id="+id+"";
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
            ps.setString(4, u.getPhone());
            ps.setString(5, u.getPassword());
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
                d.setTotal_seat(result.getString("total_seat"));
                d.setSeat_range(result.getString("seat_range"));
                d.setType(result.getString("type"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return d;
    }

    public List<Booking> FindByUser(String email) {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT * FROM booking WHERE passenger_id='" + email + "' ORDER BY id DESC";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Booking b = new Booking();
            b.setId(rs.getString("id"));
            b.setDestination_id(rs.getString("destination_id"));
            b.setBooking_date(rs.getString("booking_date"));
            b.setJourney_date(rs.getString("journey_date"));
            b.setTrain_id(rs.getString("train_id"));
            b.setSeat_numbers(rs.getString("seat_numbers"));
            b.setPassenger_id(rs.getString("passenger_id"));
            b.setNumber_of_seat(rs.getString("number_of_seat"));
            b.setPayment_status(rs.getString("payment_status"));
            b.setStatus(rs.getString("status"));
            b.setNote(rs.getString("note"));
            list.add(b);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
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
    public void Delete (String code) {
		String sql = "DELETE FROM trains WHERE code = '"+code+"'";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
                        ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    public static void main(String[] args) {
        dao d=new dao();
        d.InsertNew(new user("2", "2", "2", "2", "2", "2", "2", "2"));
    }
}

