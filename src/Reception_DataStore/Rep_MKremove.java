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
public class Rep_MKremove {
    int booking;
    public Rep_MKremove(){
        this.booking = 0;
    }
    public Rep_MKremove(int bookingID){
        this.booking = bookingID;
    }

    public int getBooking() {
        return booking;
    }

    public void setBooking(int booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "Rep_remove{" + "booking=" + booking + '}';
    }
    
}
