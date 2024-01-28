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
public class ServiceData {
    int BookingId ,StaffId, LaundryPrice , FoodPrice , SwimmingPrice , GymPrice
            , BarService , Total ;
    String Name ; 
    
     public ServiceData() {
        this.BookingId = 0 ;
        this.StaffId = 0 ; 
        this.LaundryPrice = 0 ;
        this.FoodPrice = 0 ;
        this.SwimmingPrice = 0 ;
        this.GymPrice = 0 ;
        this.BarService = 0 ;
        this.Total = 0 ;
        this.Name = "";
    }
    

    public ServiceData(int BookingId, int LaundryPrice, int FoodPrice, int SwimmingPrice, int GymPrice, int BarService, int Total, String Name) {
        this.BookingId = BookingId;
        this.StaffId = StaffId ;
        this.LaundryPrice = LaundryPrice;
        this.FoodPrice = FoodPrice;
        this.SwimmingPrice = SwimmingPrice;
        this.GymPrice = GymPrice;
        this.BarService = BarService;
        this.Total = Total;
        this.Name = Name;
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

    public int getFoodPrice() {
        return FoodPrice;
    }

    public void setFoodPrice(int FoodPrice) {
        this.FoodPrice = FoodPrice;
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

    public int getBarService() {
        return BarService;
    }

    public void setBarService(int BarService) {
        this.BarService = BarService;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getStaffId() {
        return StaffId;
    }

    public void setStaffId(int StaffId) {
        this.StaffId = StaffId;
    }

    @Override
    public String toString() {
        return "ServiceData{" + "BookingId=" + BookingId + ", StaffId=" + StaffId + ", LaundryPrice=" + LaundryPrice + ", FoodPrice=" + FoodPrice + ", SwimmingPrice=" + SwimmingPrice + ", GymPrice=" + GymPrice + ", BarService=" + BarService + ", Total=" + Total + ", Name=" + Name + '}';
    }
    
    

    
    
    
    
}
