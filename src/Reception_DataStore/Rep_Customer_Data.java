/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reception_DataStore;

import CustomerAndBooking_DataStore.*;

/**
 *
 * @author raaz4
 */
public class Rep_Customer_Data {
    String customerType, name, address, email, password, gender, age, discount, phone, credit;

    public Rep_Customer_Data(){
        
        this.customerType = "";
        this.name = "";
        this.address = "";
        this.email = "";
        this.password = "";
        this.phone = "";
        this.credit = "";
        this.age = "";
        this.gender = "";
        this.discount = "";
    }
    
    public Rep_Customer_Data( String customerType, String name, String address, String email, String password, String phone, String credit, String age, String gender, String discount){
        
        this.customerType = customerType;
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.credit = credit;
        this.age = age;
        this.gender = gender;
        this.discount = discount;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    
    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "Rep_Customer_Data{" + "customerType=" + customerType + ", name=" + name + ", address=" + address + ", email=" + email + ", password=" + password + ", gender=" + gender + ", age=" + age + ", discount=" + discount + ", phone=" + phone + ", credit=" + credit + '}';
    }
    
}