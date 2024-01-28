/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import CustomerAndBooking_DataStore.BookingDetails_update;
import CustomerAndBooking_DataStore.Booking_data;
import CustomerAndBooking_DataStore.Booking_view_data;
import classes.Global;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raaz4
 */
public class JDBC_booking {

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
            // connect with database
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
        }
        return conn;
    }

    public boolean insert(Booking_data booking) {
        boolean res = false;
        try {
            // connect with database to insert booking details
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "Insert into booking (customerid,RoomTypeBk, checkInDate, checkOutDate) values (?,?,?,?)";
            PreparedStatement prest = conn.prepareStatement(query);
            prest.setInt(1, Global.customerID);// Integer.parseInt(Global.customerID)
            prest.setString(2, booking.getRoomTypeBk());
            prest.setString(3, booking.getCheckIn());
            prest.setString(4, booking.getCheckOut());

            prest.executeUpdate();// Inserting Record, Updating Record, Deleting Record
            prest.close();
            System.out.println("booking inserted ");

            // Inserting Record, Updating Record, Deleting Record
            // prest2.close();
            conn.close();
            res = true;
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
            res = false;
        }
        return res;
    }

    public int get_bookingID(Booking_data booking) {
        int bookingID = 0;
        try {
            // try to connect with datbase
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "select BookingId from booking where Customerid= ? AND RoomTypeBk = ? AND CheckInDate = ? AND CheckOutDate = ?";
            PreparedStatement prest = conn.prepareStatement(query);
            prest.setInt(1, Global.customerID);// Integer.parseInt(Global.customerID)
            prest.setString(2, booking.getRoomTypeBk());
            prest.setString(3, booking.getCheckIn());
            prest.setString(4, booking.getCheckOut());
            ResultSet rs = prest.executeQuery();

            while (rs.next()) {
                bookingID = rs.getInt("BookingId");
            }
            prest.close();
            rs.close();

            // Inserting the customer row with this booking ID into billing table
            String query2 = "INSERT INTO billing (BookingId) VALUES (?)";
            PreparedStatement prest2 = conn.prepareStatement(query2);
            prest2.setInt(1, bookingID);// Integer.parseInt(Global.customerID)
            prest2.executeUpdate();
            prest2.close();
            System.out.println("row inserted in billing with booking ID : " + bookingID);

            // creating a row in service table for this booking ID
            String query3 = "INSERT INTO service (BookingId) VALUES (?)";
            PreparedStatement prest3 = conn.prepareStatement(query3);
            prest3.setInt(1, bookingID);// Integer.parseInt(Global.customerID)

            prest3.executeUpdate();
            prest3.close();
            System.out.println("row inserted in service table with booking ID : " + bookingID);
            conn.close();
        } catch (Exception ex) {
            // if any errors occured error message will display
            System.out.println("Error : " + ex.getMessage());
        }
        // System.out.println(booking);
        return bookingID;
    }

    public List get_bookings() {
        List booking = new ArrayList<Booking_view_data>();
        try {
            // try to connect with datbase
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT DISTINCT B.BookingId, C.Name, B.RoomTypeBk, B.CheckInDate, B.CheckOutDate, B.RoomNo, B.BookingStatus FROM booking B INNER JOIN customer C ON B.Customerid = C.Customerid  WHERE B.customerId = ? ORDER BY `B`.`BookingId`  DESC";
            PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setInt(1, Global.customerID);
            ResultSet rs = pstat.executeQuery();

            while (rs.next()) {
                booking.add(new Booking_view_data(rs.getInt("BookingId"), rs.getString("Name"),
                        rs.getString("RoomTypeBk"), rs.getString("CheckInDate"), rs.getString("CheckOutDate"),
                        rs.getInt("RoomNo"), rs.getString("BookingStatus")));
            }
            pstat.close();
            rs.close();
            conn.close();
        } catch (Exception ex) {
            // if any errors occured error message will display
            System.out.println("Error : " + ex.getMessage());
        }
        // System.out.println(booking);
        return booking;
    }

    public boolean update_booking(BookingDetails_update person) {
        boolean res = false;
        try {
            // connect with database
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // insert record on database
            String sql = "UPDATE booking SET RoomTypeBk=?, CheckInDate =? , CheckOutDate = ? WHERE BookingId = ?";
            PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setString(1, person.getRoomType());
            pstat.setString(2, person.getCheckIn());
            pstat.setString(3, person.getCheckOut());
            pstat.setInt(4, person.getBookingID());
            pstat.executeUpdate();// Inserting Record, Updating Record, Deleting Record
            pstat.close();
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
            // connect with database
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            // insert record on database
            String sql = "DELETE FROM booking WHERE BookingId = ?";
            PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setInt(1, bookingID);
            System.out.println("Booking ID " + bookingID);
            pstat.executeUpdate();// Inserting Record, Updating Record, Deleting Record
            pstat.close();
            conn.close();
            res = true;
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
            res = false;
        }
        return res;
    }
}
