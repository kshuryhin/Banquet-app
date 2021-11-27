//Клас створенго для зберігання даних списку співробітників
package main.Model;

import java.util.Date;

public class EmployeeList {
    private String name;
    private String lastName;
    private String phone;
    private String address;
    private String email;
    private String banquetName;
    private Date date;

    public EmployeeList(String name, String lastName, String phone, String address, String email, String banquetName, Date date){
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.banquetName = banquetName;
        this.date = date;
    }

    public String getName(){
        return this.name;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getPhone(){
        return this.phone;
    }

    public String getAddress(){
        return this.address;
    }

    public String getEmail(){
        return this.email;
    }

    public String getBanquetName(){
        return  this.banquetName;
    }

    public Date getDate(){
        return this.date;
    }
}
