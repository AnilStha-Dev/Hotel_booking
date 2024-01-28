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
public class Staff_Data {
    
    
    long ContactNo;
    String Name , Address , Email ,Password ;

    public Staff_Data() {
        
        this.ContactNo = 0;
        this.Name = "";
        this.Address = "";
        this.Email = "";
        this.Password = "";
    }
    
    public Staff_Data(  String Name, String Address,long ContactNo, String Email, String Password) {
       
        this.ContactNo = ContactNo;
        this.Name = Name;
        this.Address = Address;
        this.Email = Email;
        this.Password = Password;
    }

    public long getContactNo() {
        return ContactNo;
    }

    public void setContactNo(long ContactNo) {
        this.ContactNo = ContactNo;
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
        return "Staff{" + "ContactNo=" + ContactNo + ", Name=" + Name + ", Address=" + Address + ", Email=" + Email + ", Password=" + Password + '}';
    }

    long getPhone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
