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
public class BookingDetails_update {
    int bookingID;
    String roomType, checkIn , checkOut;
    
    
    public BookingDetails_update(){
        this.bookingID = 0;
        this.roomType = "";
        this.checkIn = "";
        this.checkOut ="";
}
    public BookingDetails_update(int bookingID , String roomType, String checkIn, String checkOut){
        this.bookingID = bookingID;
        this.roomType = roomType;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
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
        return "BookingDetails_update{" + "bookingID=" + bookingID + ", roomType=" + roomType + ", checkIn=" + checkIn + ", checkOut=" + checkOut + '}';
    }
    
}
