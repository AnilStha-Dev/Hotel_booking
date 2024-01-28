/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerAndBooking_DataStore;

/**
 *
 * @author raaz4
 */
public class Booking_data {
    int customerID;
    String checkIn, checkOut, roomTypeBk;
    
    public Booking_data(){
        this.customerID = 0;
        this.roomTypeBk = "";
        this.checkIn = "";
        this.checkOut = "";
}
    public Booking_data(int customerID, String roomTypeBk, String checkIn, String checkOut){
        this.customerID = customerID;
        this.roomTypeBk = roomTypeBk;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
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
        return "Booking_data{" + "customerID=" + customerID + ", checkIn=" + checkIn + ", checkOut=" + checkOut + ", roomTypeBk=" + roomTypeBk + '}';
    }

    

    
}
