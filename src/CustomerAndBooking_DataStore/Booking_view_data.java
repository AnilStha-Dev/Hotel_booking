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
public class Booking_view_data {
    int BookingID, roomNo;
    String  name , roomType, checkIn_date, checkOut_date, bookingStatus;
    
    public Booking_view_data(){
        this.BookingID = 0;
        this.name = "";
        this.roomType = "";
        this.checkIn_date = "";
        this.checkOut_date = "";
        this.roomNo = 0;
        this.bookingStatus = "";
    }
    public Booking_view_data(int BookingNo,String Name, String roomType, String checkIn_date, String checkOut_date, int roomNo,  String BookingStatus){
        this.BookingID = BookingNo;
        this.name  = Name;
        this.roomType = roomType;
        this.checkIn_date = checkIn_date;
        this.checkOut_date = checkOut_date;
        this.roomNo = roomNo;
        this.bookingStatus = BookingStatus;
    }

    public int getBookingID() {
        return BookingID;
    }

    public void setBookingID(int BookingID) {
        this.BookingID = BookingID;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getCheckIn_date() {
        return checkIn_date;
    }

    public void setCheckIn_date(String checkIn_date) {
        this.checkIn_date = checkIn_date;
    }

    public String getCheckOut_date() {
        return checkOut_date;
    }

    public void setCheckOut_date(String checkOut_date) {
        this.checkOut_date = checkOut_date;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    @Override
    public String toString() {
        return "Booking_view_data{" + "BookingID=" + BookingID + ", roomNo=" + roomNo + ", name=" + name + ", roomType=" + roomType + ", checkIn_date=" + checkIn_date + ", checkOut_date=" + checkOut_date + ", bookingStatus=" + bookingStatus + '}';
    }
}
