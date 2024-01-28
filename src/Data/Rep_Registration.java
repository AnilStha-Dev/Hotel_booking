/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author DELL
 */
public class Rep_Registration {
    String Name , Address , Email , Password  ;
    long Phone;
    
    public Rep_Registration() {
        this.Name = "";
        this.Address = "";
        this.Phone = 0;
        this.Email = "";
        this.Password = "";
        
        
    }

    public Rep_Registration(String Name, String Address,long Phone, String Email, String Password ) {
        this.Name = Name;
        this.Address = Address;
        this.Phone = Phone;
        this.Email = Email;
        this.Password = Password;
       
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

    public long getPhone() {
        return Phone;
    }

    public void setPhone(long Phone) {
        this.Phone = Phone;
    }

    @Override
    public String toString() {
        return "Reception{" + "Name=" + Name + ", Address=" + Address + ", Email=" + Email + ", Password=" + Password + ", Phone=" + Phone + '}';
    }
    
    
}
