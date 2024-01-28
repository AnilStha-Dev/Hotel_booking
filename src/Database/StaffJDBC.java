/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import HotelService_DataStore.Staff_Data;
import HotelService_DataStore.Staff_Service_Table;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class StaffJDBC {

    String HOST = "localhost";
    int PORT = 3306;
    String USER = "root";
    String PASSWORD = "";
    String DBNAME = "hotel_booking_system";
    String DRIVER = "com.mysql.cj.jdbc.Driver";
    String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DBNAME;

    public Connection connect() {
        Connection conn = null;
        try {
            //connect with database
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
        }
        return conn;
    }

    public boolean insert(Staff_Data staff) {
        boolean res = false;
        try {
            //connect with database
            Class.forName(DRIVER);
            //Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Connection conn = connect();
            //insert record on database
            String sql = "INSERT INTO staff (Name,Address,ContactNo,Email,Password) VALUES( ?, ?,?,?,?)";
            PreparedStatement pstat = conn.prepareStatement(sql);

            pstat.setString(1, staff.getName());
            pstat.setString(2, staff.getAddress());
            pstat.setLong(3, staff.getContactNo());
            pstat.setString(4, staff.getEmail());
            pstat.setString(5, staff.getPassword());
            pstat.executeUpdate();//Inserting Record, Updating Record, Deleting Record
            pstat.close();
            conn.close();
            res = true;
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
            res = false;
        }
        return res;
    }

    public List get_bookings() {
        List booking = new ArrayList<Staff_Service_Table>();
        try {
            //try to connect with datbase
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT DISTINCT B.BookingId , C.Name , S.LaundryPrice , S.TelePhone, S.SwimmingPrice , S.GymPrice , S.BarPrice FROM (( customer C INNER JOIN booking B ON C.Customerid = B.Customerid ) INNER JOIN service S ON B.BookingId = S.BookingId)ORDER BY `B`.`BookingId` DESC";
            PreparedStatement pstat = conn.prepareStatement(sql);
            ResultSet rs = pstat.executeQuery();

            while (rs.next()) {
                booking.add(new Staff_Service_Table(rs.getInt("BookingID"), rs.getString("Name"), rs.getInt("LaundryPrice"), rs.getInt("TelePhone"), rs.getInt("SwimmingPrice"), rs.getInt("GymPrice"),rs.getInt("BarPrice")));
            }
            pstat.close();
            rs.close();
            conn.close();
        } catch (Exception ex) {
            //if any errors occured error message will display
            System.out.println("Error : " + ex.getMessage());
        }
        //System.out.println(booking);
        return booking;
    }

}
