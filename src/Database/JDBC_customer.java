  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import CustomerAndBooking_DataStore.Customer_Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raaz4
 */

public class JDBC_customer {
    String HOST = "localhost" ;
    int PORT  = 3306;
    String USER = "root";
    String PASSWORD = "";
    String DBNAME = "hotel_booking_system";
    String DRIVER = "com.mysql.cj.jdbc.Driver";
    String URL = "jdbc:mysql://"+HOST+":"+PORT+"/"+DBNAME;
    
    private Connection connect(){
        Connection conn = null;
        try{
            //connect with database
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch(Exception ex){
            System.out.println("Error : "+ex.getMessage());
        }
        return conn;
    } 
    public boolean Email_check(Customer_Data person){
        boolean res = false;
        try{
            //connect with database
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            
            String sql ="select Email from customer where Email = ? ";
            PreparedStatement pstat = conn.prepareStatement(sql);
            pstat.setString(1, person.getEmail());
            ResultSet rs =  pstat.executeQuery();//Inserting Record, Updating Record, Deleting Record
            while(rs.next()){
                res = true;
            }
            pstat.close();
            conn.close();
        }
        catch(ClassNotFoundException | SQLException ex){
            System.out.println("Error : "+ ex.getMessage());
            res = false;
        }
        return res;
    }
    public boolean insert(Customer_Data person){
        boolean res = false;
        try{
            //connect with database
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            //insert record on database
            String sql ="INSERT INTO customer(CustomerType, Name, Address, Email, Password, ContactNumber, CreditCardNumber, Age, Gender,DiscountPercent) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            pstat.executeUpdate();//Inserting Record, Updating Record, Deleting Record
            pstat.close();
            conn.close();
            res = true;
        }
        catch(ClassNotFoundException | SQLException ex){
            System.out.println("Error : "+ ex.getMessage());
            res = false;
        }
        return res;
    }
    
    public List get_all_for_reception(){        
        List customer = new ArrayList<Customer_Data>();
        try{
            //try to connect with datbase and sending the values from customer table in database to java jtable
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT * FROM `customer` ORDER BY `customer`.`Customerid` DESC";
            PreparedStatement pstat = conn.prepareStatement(sql);
            ResultSet rs = pstat.executeQuery();
            while(rs.next()){
                customer.add(new Customer_Data(rs.getInt("customerid"), rs.getString("CustomerType"), rs.getString("Name"), rs.getString("Address"), rs.getString("Email"), rs.getString("Password"), rs.getLong("ContactNumber"), rs.getLong("CreditCardNumber"), rs.getInt("Age"), rs.getString("Gender"), rs.getInt("DiscountPercent")));                
            }
            pstat.close();
            rs.close();
            conn.close();         
        }
        catch(Exception ex){
            //if any errors occured error message will display
           System.out.println("Error : "+ex.getMessage());
        }
        return customer;
    }
    
}
