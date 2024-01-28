/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reception_DataStore;

/**
 *
 * @author raaz4
 */
public class Rep_update {
    int bookingID, RoomNo, days;
    String  CheckIn, CheckOut, RoomType, BookingStatus;
    
    public Rep_update(){
        this.bookingID = 0;
        this.CheckIn = "";
        this.CheckOut = "";
        this.RoomType = "";
        this.RoomNo = 0;
        this.days = 0;
        this.BookingStatus = "";
    }
    
    public Rep_update(int bookingID ,String RoomType, int RoomNo, String checkIn, String checkOut,  String bookingstatus, int days){
        this.bookingID = bookingID;
        this.CheckIn = checkIn;
        this.CheckOut = checkOut;
        this.RoomType = RoomType;
        this.RoomNo = RoomNo;
        this.BookingStatus = bookingstatus;
        this.days = days;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getRoomNo() {
        return RoomNo;
    }

    public void setRoomNo(int RoomNo) {
        this.RoomNo = RoomNo;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getCheckIn() {
        return CheckIn;
    }

    public void setCheckIn(String CheckIn) {
        this.CheckIn = CheckIn;
    }

    public String getCheckOut() {
        return CheckOut;
    }

    public void setCheckOut(String CheckOut) {
        this.CheckOut = CheckOut;
    }

    public String getRoomType() {
        return RoomType;
    }

    public void setRoomType(String RoomType) {
        this.RoomType = RoomType;
    }

    public String getBookingStatus() {
        return BookingStatus;
    }

    public void setBookingStatus(String BookingStatus) {
        this.BookingStatus = BookingStatus;
    }

    @Override
    public String toString() {
        return "Rep_update{" + "bookingID=" + bookingID + ", RoomNo=" + RoomNo + ", days=" + days + ", CheckIn=" + CheckIn + ", CheckOut=" + CheckOut + ", RoomType=" + RoomType + ", BookingStatus=" + BookingStatus + '}';
    }

    
}
