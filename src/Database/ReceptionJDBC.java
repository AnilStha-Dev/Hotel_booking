/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import CustomerAndBooking_DataStore.Booking_data;
import CustomerAndBooking_DataStore.Customer_Data;
import Reception_DataStore.Rep_Bupdate;
import Reception_DataStore.Rep_Customer_Data;
import Reception_DataStore.Rep_searchCustomer;
import Reception_DataStore.Rep_makeBooking;
import Reception_DataStore.Rep_makeBooking_table;
import Reception_DataStore.Rep_MKremove;
import Reception_DataStore.Rep_billing;
import Reception_DataStore.Rep_update;
import classes.Global;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author DELL
 */
public class ReceptionJDBC {

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

    public boolean insert(Rep_searchCustomer reception) {
        boolean res = false;
        try {
            //registration for receptionist
            Class.forName(DRIVER);
            Connection conn = connect();
            //insert record on database
            String sql = "INSERT INTO receptionist(Name,Address,Phone,Email,Password) VALUES( ?, ?,?,?,?)";
            PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setString(1, reception.getName());
            pstat.setString(2, reception.getAddress());
            pstat.setLong(3, reception.getPhone());
            pstat.setString(4, reception.getEmail());
            pstat.setString(5, reception.getPassword());

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

    public boolean updateBooking(Rep_update updateBk) {
        boolean res = false;
        try {
            Class.forName(DRIVER);
            Connection conn = connect();
            //updating data's on existing records
            String sql = "UPDATE booking SET ReceptionistId = ? , RoomNo = ?, RoomTypeBk = ?,  CheckInDate = ? , CheckOutDate = ? , BookingStatus = ?, Days = ? WHERE BookingId = ?";
            PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setInt(1, Global.receptionID);
            pstat.setInt(2, updateBk.getRoomNo());
            pstat.setString(3, updateBk.getRoomType());
            pstat.setString(4, updateBk.getCheckIn());
            pstat.setString(5, updateBk.getCheckOut());
            pstat.setString(6, updateBk.getBookingStatus());
            pstat.setInt(7, updateBk.getDays());
            pstat.setInt(8, updateBk.getBookingID());
            
            String query2 = "UPDATE room SET RoomStatus = 1 WHERE RoomNo = ?";
            PreparedStatement prest2 = conn.prepareStatement(query2);
            prest2.setInt(1, updateBk.getRoomNo());
            prest2.executeUpdate();//Inserting Record, Updating Record, Deleting Record
            prest2.close();

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

    public boolean removeBooking(Rep_MKremove removeBk) {
        boolean res = false;
        try {
            //remove a booking from make a booking
            Class.forName(DRIVER);
            Connection conn = connect();
            //insert record on database
            String sql = "DELETE FROM booking WHERE BookingId = ?";
            PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setInt(1, removeBk.getBooking());
            
            
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

    public Vector get_freeRooms() {
        Rep_makeBooking booking = new Rep_makeBooking();
        Vector room_numbers = new Vector();
        try {
            //try to connect with datbase
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT * FROM `room` WHERE RoomStatus=0  ";
            PreparedStatement pstat = conn.prepareStatement(sql);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                room_numbers.add(rs.getInt("RoomNo"));
            }
            pstat.close();
            rs.close();
            conn.close();
        } catch (Exception ex) {
            //if any errors occured error message will display
            System.out.println("Error : " + ex.getMessage());
        }
        return room_numbers;
    }

    public Vector get_freeRooms(String roomType) {
        Rep_makeBooking booking = new Rep_makeBooking();
        Vector room_numbers = new Vector();
        try {
            //try to connect with datbase
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT * FROM `room` WHERE RoomStatus=0 and  RoomType ='" + roomType + "'";
            PreparedStatement pstat = conn.prepareStatement(sql);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                room_numbers.add(rs.getInt("RoomNo"));
            }
            pstat.close();
            rs.close();
            conn.close();
        } catch (Exception ex) {
            //if any errors occured error message will display
            System.out.println("Error : " + ex.getMessage());
        }
        return room_numbers;
    }

    public List get_forBilling() {
        List customer = new ArrayList<Customer_Data>();
        try {
            //try to connect with datbase and sending the values from customer table in database to java jtable
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT * FROM `customer` ORDER BY `customer`.`Customerid` DESC";
            PreparedStatement pstat = conn.prepareStatement(sql);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                customer.add(new Customer_Data(rs.getInt("customerid"), rs.getString("CustomerType"), rs.getString("Name"), rs.getString("Address"), rs.getString("Email"), rs.getString("Password"), rs.getLong("ContactNumber"), rs.getLong("CreditCardNumber"), rs.getInt("Age"), rs.getString("Gender"), rs.getInt("DiscountPercent")));
            }
            pstat.close();
            rs.close();
            conn.close();
        } catch (Exception ex) {
            //if any errors occured error message will display
            System.out.println("Error : " + ex.getMessage());
        }
        return customer;
    }
    
    public Booking_data search_BookingID(int bookingID) {
        Booking_data tmpBooking = new Booking_data();
        try {
            //try to connect with datbase
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            if (bookingID > 0) {
                String sql = "SELECT * FROM `booking`  WHERE BookingId = ? ";
                PreparedStatement pstat = conn.prepareStatement(sql);
                pstat.setInt(1, bookingID);
                ResultSet rs = pstat.executeQuery();
                while (rs.next()) {
                    tmpBooking.setCheckIn(rs.getString("CheckInDate"));
                    tmpBooking.setCheckOut(rs.getString("CheckOutDate"));
                    tmpBooking.setRoomTypeBk(rs.getString("RoomTypeBk"));
                    System.out.print(tmpBooking);
                }
                pstat.close();
                rs.close();
            }
            conn.close();
        } catch (Exception ex) {
            //if any errors occured error message will display
            System.out.println("Error : " + ex.getMessage());
        }
        //System.out.println(booking);
        return tmpBooking;
    }

    public boolean makeBooking(Rep_makeBooking booking) {
        boolean res = false;
        try {
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "Insert into booking (Customerid, ReceptionistId, RoomNo, RoomTypeBk, checkInDate, checkOutDate,BookingStatus, Days) values (?,?,?,?,?,?,?,?)";
            PreparedStatement prest = conn.prepareStatement(query);
            prest.setInt(1, booking.getCustomerID());
            prest.setInt(2, Global.receptionID);
            prest.setInt(3, booking.getRoomNo());
            prest.setString(4, booking.getRoomType());
            prest.setString(5, booking.getCheckIn());
            prest.setString(6, booking.getCheckOut());
            prest.setString(7, booking.getBookingStatus());
            prest.setInt(8, booking.getDays());
            //prest.executeQuery();

            String query2 = "UPDATE room SET RoomStatus = 1 WHERE RoomNo = ?";
            PreparedStatement prest2 = conn.prepareStatement(query2);
            prest2.setInt(1, booking.getRoomNo());
            prest2.executeUpdate();//Inserting Record, Updating Record, Deleting Record
            prest2.close();

            prest.executeUpdate();//Inserting Record, Updating Record, Deleting Record
            prest.close();
            conn.close();
            res = true;
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
            res = false;
        }
        return res;
    }

    public List get_bookings() {
        List booking = new ArrayList<Rep_makeBooking_table>();
        try {
            //try to connect with datbase
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT DISTINCT B.customerId, B.BookingId, C.Name, B.RoomTypeBk, B.CheckInDate, B.CheckOutDate, B.RoomNo, B.BookingStatus, B.Days FROM booking B INNER JOIN customer C ON B.Customerid = C.Customerid ORDER BY `B`.`BookingId` DESC";
            PreparedStatement pstat = conn.prepareStatement(sql);

            ResultSet rs = pstat.executeQuery();

            while (rs.next()) {
                booking.add(new Rep_makeBooking_table(rs.getInt("customerId"), rs.getInt("BookingId"), rs.getString("Name"), rs.getString("RoomTypeBk"), rs.getString("CheckInDate"), rs.getString("CheckOutDate"), rs.getInt("RoomNo"), rs.getString("BookingStatus"), rs.getInt("Days")));
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

    public List get_billingTable() {
        List booking = new ArrayList<Rep_billing>();
        try {
            //try to connect with datbase
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT C.Customerid , B.BookingId , C.Name , R.RoomNo , B.Days ,(B.Days*R.RoomPrice) AS RoomCharge ,S.Total AS ExtraCharge , C.DiscountPercent , (((B.Days * R.RoomPrice)+S.Total)+(((B.Days * R.RoomPrice)+S.Total)*13/100)) - ((B.Days * R.RoomPrice)*C.DiscountPercent/100) AS GrandTotal, Z.Paidamt, B.BookingStatus FROM booking B JOIN customer C ON C.Customerid = B.Customerid JOIN room R On B.RoomNo = R.RoomNo JOIN service S ON B.BookingId = S.BookingId JOIN billing Z ON B.BookingId = Z.BookingId ORDER BY `B`.`BookingId` DESC";
            PreparedStatement pstat = conn.prepareStatement(sql);
            
            ResultSet rs = pstat.executeQuery();

            while (rs.next()) {
                booking.add(new Rep_billing(rs.getInt("customerId"), rs.getInt("BookingId"), rs.getString("Name"), rs.getInt("RoomNo"),rs.getInt("Days"), rs.getInt("RoomCharge"), rs.getInt("ExtraCharge"), rs.getInt("DiscountPercent"), rs.getInt("GrandTotal"), rs.getInt("Paidamt"), rs.getString("BookingStatus")));
                System.out.println(booking);
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
                    System.out.println(booking);
                }
                pstat.close();
                rs.close();
            } else if (customer_ID == 0) {
                String sql = "SELECT DISTINCT C.Customerid , B.BookingId ,C.Name , B.CheckInDate ,B.CheckOutDate ,B.RoomTypeBk , R.RoomNo , B.BookingStatus , B.Days FROM ((customer C INNER JOIN booking B ON C.Customerid = B.Customerid ) INNER JOIN room R ON B.RoomNo = R.RoomNo) ORDER BY `C`.`Customerid` DESC";
                PreparedStatement pstat = conn.prepareStatement(sql);
                //pstat.setInt(1, customer_ID);
                ResultSet rs = pstat.executeQuery();

                while (rs.next()) {
                    booking.add(new Rep_makeBooking_table(rs.getInt("customerId"), rs.getInt("BookingId"), rs.getString("Name"), rs.getString("RoomTypeBk"), rs.getString("CheckInDate"), rs.getString("CheckOutDate"), rs.getInt("RoomNo"), rs.getString("BookingStatus"), rs.getInt("Days")));
                    System.out.println(booking);
                }
                pstat.close();
                rs.close();

            }

            conn.close();
        } catch (Exception ex) {
            //if any errors occured error message will display
            System.out.println("Error : " + ex.getMessage());
        }
        return booking;
    }

    
    public List get_bookingsRep() {
        List customer = new ArrayList<Customer_Data>();
        try {
            //try to connect with datbase and sending the values from customer table in database to java jtable
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT * FROM customer";
            PreparedStatement pstat = conn.prepareStatement(sql);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                customer.add(new Customer_Data(rs.getInt("customerid"), rs.getString("CustomerType"), rs.getString("Name"), rs.getString("Address"), rs.getString("Email"), rs.getString("Password"), rs.getLong("ContactNumber"), rs.getLong("CreditCardNumber"), rs.getInt("Age"), rs.getString("Gender"), rs.getInt("DiscountPercent")));
            }
            pstat.close();
            rs.close();
            conn.close();
        } catch (Exception ex) {
            //if any errors occured error message will display
            System.out.println("Error : " + ex.getMessage());
        }
        return customer;
    }

    

    public boolean Billing_update(Rep_Bupdate update) {
        boolean res = false;
        try {
            //remove a booking from make a booking
            Class.forName(DRIVER);
            Connection conn = connect();
            //insert record on database
            String sql = "UPDATE room INNER JOIN booking ON room.RoomNo = booking.RoomNo SET room.RoomStatus = 0 , booking.BookingStatus = ? WHERE booking.BookingId=?";
            PreparedStatement pstat = conn.prepareStatement(sql);
            String status = "Completed";
            pstat.setString(1, status);
            pstat.setInt(2, update.getBookingID());

            //Global.receptionID = "2";
            String sql2 = " UPDATE billing SET ReceptionistId = ? , Paidamt = ? , PaymentStatus = ? WHERE BookingId = ?";
            PreparedStatement pstat2 = conn.prepareStatement(sql2);
            pstat2.setInt(1, Global.receptionID);
            pstat2.setInt(2, update.getTender());
            pstat2.setString(3, update.getPaymentStatus());
            pstat2.setInt(4, update.getBookingID());

            pstat2.executeUpdate();//Inserting Record, Updating Record, Deleting Record
            pstat2.close();
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
    
//********************************************customer tab***************************************
    
    public boolean update_customerInfo(Customer_Data person){       
        boolean res = false;
        try{
            //connect with database
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            
            //insert record on database
            String sql ="UPDATE customer SET CustomerType=?, Name=? , Address =? , Email = ? , Password = ? ,ContactNumber = ? , CreditCardNumber = ? , Age = ? , Gender = ? , DiscountPercent=? WHERE CustomerId =?";
            PreparedStatement pstat = conn.prepareStatement(sql);
            
            pstat.setString(1, person.getCustomerType());
            pstat.setString(2, person.getName());
            pstat.setString(3, person.getAddress());
            pstat.setString(4, person.getEmail());
            pstat.setString(5, person.getPassword());
            pstat.setLong(6, person.getPhone());
            pstat.setLong(7, person.getCredit());
            pstat.setInt(8, person.getAge());
            pstat.setString(9, person.getGender());
            pstat.setInt(10, person.getDiscount());
            pstat.setInt(11, person.getCustomerid());
            pstat.executeUpdate();//Inserting Record, Updating Record, Deleting Record
            pstat.close();
            conn.close();
            res = true;
        }
        catch(Exception ex){
            System.out.println("Error : "+ ex.getMessage());
            res = false;
        }
        return res;
    }
    
    public List search_customerName(String customerName) {
        List booking = new ArrayList<Customer_Data>();
        try {
            //try to connect with datbase
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            if (customerName.length() > 0) {
                String sql = "SELECT * FROM `customer`  WHERE Name = ? ORDER BY `customer`.`Customerid` DESC";
                PreparedStatement pstat = conn.prepareStatement(sql);
                pstat.setString(1, customerName);
                ResultSet rs = pstat.executeQuery();
                while (rs.next()) {
                    booking.add(new Customer_Data(rs.getInt("customerid"), rs.getString("CustomerType"), rs.getString("Name"), rs.getString("Address"), rs.getString("Email"), rs.getString("Password"), rs.getLong("ContactNumber"), rs.getLong("CreditCardNumber"), rs.getInt("Age"), rs.getString("Gender"), rs.getInt("DiscountPercent")));
                    System.out.println(booking);
                }
                pstat.close();
                rs.close();
            }
            
            else if (customerName.length() == 0) {
                String sql2 = "SELECT * FROM `customer` ORDER BY `customer`.`Customerid` DESC";
                PreparedStatement pstat2 = conn.prepareStatement(sql2);
                ResultSet rs = pstat2.executeQuery();
                while (rs.next()) {
                    booking.add(new Customer_Data(rs.getInt("customerid"), rs.getString("CustomerType"), rs.getString("Name"), rs.getString("Address"), rs.getString("Email"), rs.getString("Password"), rs.getLong("ContactNumber"), rs.getLong("CreditCardNumber"), rs.getInt("Age"), rs.getString("Gender"), rs.getInt("DiscountPercent")));
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

        //System.out.println(booking);
        return booking;
    }
    public Rep_Customer_Data search_customerID(int customerID) {
        Rep_Customer_Data tmpCustomer = new Rep_Customer_Data();
        try {
            //try to connect with datbase
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            if (customerID > 0) {
                String sql = "SELECT * FROM `customer`  WHERE Customerid = ? ";
                PreparedStatement pstat = conn.prepareStatement(sql);
                pstat.setInt(1, customerID);
                ResultSet rs = pstat.executeQuery();
                while (rs.next()) {
                    tmpCustomer.setCustomerType(rs.getString("CustomerType"));
                    tmpCustomer.setName(rs.getString("Name"));
                    tmpCustomer.setAddress(rs.getString("Address"));
                    tmpCustomer.setEmail(rs.getString("Email"));
                    tmpCustomer.setPassword(rs.getString("Password"));
                    tmpCustomer.setPhone(rs.getString("ContactNumber"));
                    tmpCustomer.setCredit(rs.getString("CreditCardNumber"));
                    tmpCustomer.setAge(rs.getString("Age"));
                    tmpCustomer.setDiscount(rs.getString("DiscountPercent"));
                    tmpCustomer.setGender(rs.getString("Gender"));
                }
                pstat.close();
                rs.close();
            }
            conn.close();
        } catch (Exception ex) {
            //if any errors occured error message will display
            System.out.println("Error : " + ex.getMessage());
        }
        //System.out.println(booking);
        return tmpCustomer;
    }
    
    public boolean delete_customerInfo(int customerid){
        boolean res = false;
        try{
            //connect with database
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);            
            //insert record on database
            String sql ="DELETE FROM customer WHERE CustomerId=?";
            PreparedStatement pstat = conn.prepareStatement(sql);                        
            pstat.setInt(1, customerid);
            pstat.executeUpdate();//Inserting Record, Updating Record, Deleting Record
            pstat.close();
            conn.close();
            res = true;
        }
        catch(Exception ex){
            System.out.println("Error : "+ ex.getMessage());
            res = false;
        }
        return res;        
    }
}
