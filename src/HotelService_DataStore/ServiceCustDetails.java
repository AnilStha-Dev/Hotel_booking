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
public class ServiceCustDetails {
    int CustomerId , BookingId , Age ;
    long ContactNo , CreditCard ;
    String Name , Address , Email , Password;
    
     public ServiceCustDetails() {
        this.CustomerId = 0 ;
        this.BookingId = 0 ;
        this.Age = 0 ;
        this.ContactNo = 0 ;
        this.CreditCard = 0 ;
        this.Name = "" ;
        this.Address = "" ;
        this.Email = "" ;
        this.Password = "" ;
    }

    public ServiceCustDetails(int CustomerId, int BookingId, int Age, long ContactNo, long CreditCard, String Name, String Address, String Email, String Password) {
        this.CustomerId = CustomerId;
        this.BookingId = BookingId;
        this.Age = Age;
        this.ContactNo = ContactNo;
        this.CreditCard = CreditCard;
        this.Name = Name;
        this.Address = Address;
        this.Email = Email;
        this.Password = Password;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int CustomerId) {
        this.CustomerId = CustomerId;
    }

    public int getBookingId() {
        return BookingId;
    }

    public void setBookingId(int BookingId) {
        this.BookingId = BookingId;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public long getContactNo() {
        return ContactNo;
    }

    public void setContactNo(long ContactNo) {
        this.ContactNo = ContactNo;
    }

    public long getCreditCard() {
        return CreditCard;
    }

    public void setCreditCard(long CreditCard) {
        this.CreditCard = CreditCard;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    @Override
    public String toString() {
        return "ServiceCustDetails{" + "CustomerId=" + CustomerId + ", BookingId=" + BookingId + ", Age=" + Age + ", ContactNo=" + ContactNo + ", CreditCard=" + CreditCard + ", Name=" + Name + ", Address=" + Address + ", Email=" + Email + ", Password=" + Password + '}';
    }
    
    
    
    
    
}
