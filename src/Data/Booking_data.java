/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author raaz4
 */
public class Booking_data {
    String customerID, roomTypeBk;
    String checkIn, checkOut;
    
    public Booking_data(){
        this.customerID = "";
        this.roomTypeBk = "";
        this.checkIn = "";
        this.checkOut = "";
}
    public Booking_data(String customerID, String roomTypeBk, String checkIn, String checkOut){
        this.customerID = customerID;
        this.roomTypeBk = roomTypeBk;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public String getCustomerID() {
        System.out.print("customer ID from booking Data : " + customerID);
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
    
    public String getRoomTypeBk() {
        return roomTypeBk;
    }

    public void setRoomTypeBk(String roomTypeBk) {
        this.roomTypeBk = roomTypeBk;
    }


    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "Booking_data{" + "customerID=" + customerID + ", roomTypeBk=" + roomTypeBk + ", checkIn=" + checkIn + ", checkOut=" + checkOut + '}';
    }

    

    
}
