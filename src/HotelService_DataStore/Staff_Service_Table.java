/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HotelService_DataStore;

/**
 *
 * @author SUDIP RAAZ
 */
public class Staff_Service_Table {
    int BookingId , LaundryPrice , Telephone , SwimmingPrice , GymPrice, BarPrice ;
    String Name;
    
    public Staff_Service_Table() {
        this.BookingId = 0 ;
        this.LaundryPrice = 0 ;
        this.Telephone = 0 ;
        this.SwimmingPrice = 0 ;
        this.GymPrice = 0 ;
        this.BarPrice = 0 ;
        this.Name = "";
    }
    
    public Staff_Service_Table(int BookingId, String Name, int LaundryService, int FoodService, int SwimmingService, int GymService, int BarService) {
        this.BookingId = BookingId;
        this.Name = Name;
        this.LaundryPrice = LaundryService;
        this.Telephone = FoodService;
        this.SwimmingPrice = SwimmingService;
        this.GymPrice = GymService;
        this.BarPrice = BarService;
        
        
    }

    public int getBookingId() {
        return BookingId;
    }

    public void setBookingId(int BookingId) {
        this.BookingId = BookingId;
    }

    public int getLaundryPrice() {
        return LaundryPrice;
    }

    public void setLaundryPrice(int LaundryPrice) {
        this.LaundryPrice = LaundryPrice;
    }

    public int getTelephone() {
        return Telephone;
    }

    public void setTelephone(int Telephone) {
        this.Telephone = Telephone;
    }

    public int getSwimmingPrice() {
        return SwimmingPrice;
    }

    public void setSwimmingPrice(int SwimmingPrice) {
        this.SwimmingPrice = SwimmingPrice;
    }

    public int getGymPrice() {
        return GymPrice;
    }

    public void setGymPrice(int GymPrice) {
        this.GymPrice = GymPrice;
    }

    public int getBarPrice() {
        return BarPrice;
    }

    public void setBarPrice(int BarPrice) {
        this.BarPrice = BarPrice;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @Override
    public String toString() {
        return "Staff_Service_Table{" + "BookingId=" + BookingId + ", LaundryPrice=" + LaundryPrice + ", Telephone=" + Telephone + ", SwimmingPrice=" + SwimmingPrice + ", GymPrice=" + GymPrice + ", BarPrice=" + BarPrice + ", Name=" + Name + '}';
    }
    
}
