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
public class Rep_billing {
    int customerID , bookingID, RoomNo, Days, RoomPrice,ExtraPrice, Discount, paidAmt, GrandTotal;
    String Name,  BookingStatus;
    public Rep_billing(){
        this.customerID = 0;
        this.bookingID = 0 ;
        this.Name = "";
        this.RoomNo = 0;
        this.Days = 0;
        this.RoomPrice = 0;
        this.ExtraPrice = 0;
        this.Discount = 0;
        this.GrandTotal = 0;
        this.paidAmt = 0;
        this.BookingStatus = "";
    }
    public Rep_billing(int customerID, int bookingID, String name, int roomNo, int days, int roomCharge, 
            int extraCharge, int discount, int grandTotal, int paidAmt, String bookingStatus){
        this.customerID = customerID;
        this.bookingID = bookingID;
        this.Name = name;
        this.RoomNo = roomNo;
        this.Days = days;
        this.RoomPrice = roomCharge;
        this.ExtraPrice = extraCharge;
        this.Discount = discount;
        this.GrandTotal = grandTotal;
        this.paidAmt = paidAmt;
        this.BookingStatus = bookingStatus;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
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
        return Days;
    }

    public void setDays(int Days) {
        this.Days = Days;
    }

    public int getRoomPrice() {
        return RoomPrice;
    }

    public void setRoomPrice(int RoomPrice) {
        this.RoomPrice = RoomPrice;
    }

    public int getExtraPrice() {
        return ExtraPrice;
    }

    public void setExtraPrice(int ExtraPrice) {
        this.ExtraPrice = ExtraPrice;
    }

    public int getDiscount() {
        return Discount;
    }

    public void setDiscount(int Discount) {
        this.Discount = Discount;
    }

    public int getPaidAmt() {
        return paidAmt;
    }

    public void setPaidAmt(int paidAmt) {
        this.paidAmt = paidAmt;
    }

    public int getGrandTotal() {
        return GrandTotal;
    }

    public void setGrandTotal(int GrandTotal) {
        this.GrandTotal = GrandTotal;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getBookingStatus() {
        return BookingStatus;
    }

    public void setBookingStatus(String BookingStatus) {
        this.BookingStatus = BookingStatus;
    }

    @Override
    public String toString() {
        return "Rep_billing{" + "customerID=" + customerID + ", bookingID=" + bookingID + ", RoomNo=" + RoomNo + ", Days=" + Days + ", RoomPrice=" + RoomPrice + ", ExtraPrice=" + ExtraPrice + ", Discount=" + Discount + ", paidAmt=" + paidAmt + ", GrandTotal=" + GrandTotal + ", Name=" + Name + ", BookingStatus=" + BookingStatus + '}';
    }
}
