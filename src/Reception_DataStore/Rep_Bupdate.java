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
public class Rep_Bupdate {
    int bookingID, Tender ;
    String paymentStatus; 
    public Rep_Bupdate(){
        this.bookingID = 0;
        this.Tender = 0;
        this.paymentStatus = "";
    }
    public Rep_Bupdate(int bookingID, int tender, String payment){
        this.bookingID= bookingID;
        this.Tender = tender;
        this.paymentStatus = payment;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getTender() {
        return Tender;
    }

    public void setTender(int Tender) {
        this.Tender = Tender;
    }
    
    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "Rep_Bupdate{" + "bookingID=" + bookingID + ", Tender=" + Tender + ", paymentStatus=" + paymentStatus + '}';
    }
    
}
