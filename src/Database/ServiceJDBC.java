/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Reception_DataStore.Rep_makeBooking_table;
import HotelService_DataStore.Staff_Service_add;
import classes.Global;
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
public class ServiceJDBC {

    String HOST = "localhost";
    int PORT = 3306;
    String USER = "root";
    String PASSWORD = "";
    String DBNAME = "hotel_booking_system";
    String DRIVER = "com.mysql.cj.jdbc.Driver";
    String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DBNAME;

    private Connection connect() {
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

    public boolean insert(Staff_Service_add staffservice) {
        boolean res = false;
        try {
            //connect with database to insert booking details
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "UPDATE service SET StaffId = ?, LaundryPrice = ? , TelePhone = ?, SwimmingPrice = ?, GymPrice = ?, BarPrice = ? WHERE BookingId = ?";
            PreparedStatement prest = conn.prepareStatement(query);
            //String customerID = Global.customerID.toString();
            prest.setInt(1, (Global.staffID));
            prest.setInt(2, staffservice.getLaundryPrice());
            prest.setInt(3, staffservice.getTelephone());
            prest.setInt(4, staffservice.getSwimmingPrice());
            prest.setInt(5, staffservice.getGymPrice());
            prest.setInt(6, staffservice.getBarService());
            prest.setInt(7, staffservice.getBookingId());
            prest.executeUpdate();//Inserting Record, Updating Record, Deleting Record
            prest.close();

            //updating the total value in the service table 
            String query2 = "UPDATE service SET total = (LaundryPrice + SwimmingPrice+TelePhone+GymPrice+BarPrice)";
            PreparedStatement prest2 = conn.prepareStatement(query2);
            prest2.executeUpdate();//Inserting Record, Updating Record, Deleting Record
            prest2.close();

            conn.close();
            res = true;
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
            res = false;
        }
        return res;
    }

    
    public boolean delete(int bookingID) {
        boolean res = false;
        try {
            //connect with database
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            //insert record on database
            String sql = "DELETE FROM service WHERE BookingId = ?";
            PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setInt(1, bookingID);
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

    public List search(int customer_ID) {
        List booking = new ArrayList<Rep_makeBooking_table>();
        try {
            //try to connect with datbase
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            
            if (customer_ID > 0) {
                String sql = "SELECT DISTINCT C.Customerid , B.BookingId ,C.Name , B.CheckInDate ,B.CheckOutDate ,B.RoomTypeBk , R.RoomNo , B.BookingStatus , B.Days FROM ((customer C INNER JOIN booking B ON C.Customerid = B.Customerid ) INNER JOIN room R ON B.RoomNo = R.RoomNo) WHERE C.Customerid = ?";
                PreparedStatement pstat = conn.prepareStatement(sql);
                pstat.setInt(1, customer_ID);
                ResultSet rs = pstat.executeQuery();

                while (rs.next()) {
                    booking.add(new Rep_makeBooking_table(rs.getInt("customerId"), rs.getInt("BookingId"), rs.getString("Name"), rs.getString("RoomTypeBk"), rs.getString("CheckInDate"), rs.getString("CheckOutDate"), rs.getInt("RoomNo"), rs.getString("BookingStatus"), rs.getInt("Days")));
                }
                pstat.close();
                rs.close();
                
            } else if (customer_ID == 0) {
                System.out.println("entered customer id = 0");
                String sql2 = "SELECT DISTINCT B.customerId, B.BookingId, C.Name, B.RoomTypeBk, B.CheckInDate, B.CheckOutDate, B.RoomNo, B.BookingStatus, B.Days FROM booking B INNER JOIN customer C ON B.Customerid = C.Customerid ORDER BY `B`.`BookingId` DESC";
                PreparedStatement pstat2 = conn.prepareStatement(sql2);
                ResultSet rs = pstat2.executeQuery();
                while (rs.next()) {
                    booking.add(new Rep_makeBooking_table(rs.getInt("customerId"), rs.getInt("BookingId"), rs.getString("Name"), rs.getString("RoomTypeBk"), rs.getString("CheckInDate"), rs.getString("CheckOutDate"), rs.getInt("RoomNo"), rs.getString("BookingStatus"), rs.getInt("Days")));
                    System.out.println("booking returned : " + booking);
                }
                pstat2.close();
                rs.close();
            }

            conn.close();
        } catch (Exception ex) {
            //if any errors occured error message will display
            System.out.println("Error : " + ex.getMessage());
        }
        return booking;
    }

}

