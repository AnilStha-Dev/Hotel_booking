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
public class Rep_updateCustomer {
    int customerid, discount;
    long phone, credit;
    int age;
    String customerType, name, address, email, password, gender;

    public Rep_updateCustomer() {
        this.customerid = 0;
        this.discount = 0;
        this.phone = 0;
        this.credit = 0;
        this.age = 0;
        this.customerType = "";
        this.name = "";
        this.address = "";
        this.email = "";
        this.password = "";
        this.gender = "";
    }
    
    public Rep_updateCustomer(int customerid ,String customerType , String name , String address ,String Email , String Password , long phone ,long credit , int age , int discount , String gender) {
        this.customerid = customerid;
        this.discount = discount;
        this.phone = phone;
        this.credit = credit;
        this.age = age;
        this.customerType = customerType;
        this.name = name;
        this.address = address;
        this.email = Email;
        this.password = Password;
        this.gender = gender;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public long getCredit() {
        return credit;
    }

    public void setCredit(long credit) {
        this.credit = credit;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    @Override
    public String toString() {
        return "Rep_updateCustomer{" + "customerid=" + customerid + ", discount=" + discount + ", phone=" + phone + ", credit=" + credit + ", age=" + age + ", customerType=" + customerType + ", name=" + name + ", address=" + address + ", email=" + email + ", password=" + password + ", gender=" + gender + '}';
    }
    
    
    
}
