package main.Model;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

public class Order {
    private LocalDate date;
    private int guestNum;
    private String banquetName;
    private int empNum;
    private int custID;
    public static int currentOrder;

    public Order(LocalDate date, int guestNum, String banquetName, int empNum, int custID){
        this.date = date;
        this.guestNum = guestNum;
        this.banquetName = banquetName;
        this.empNum = empNum;
        this.custID = custID;
    }

    public LocalDate getDate(){
        return this.date;
    }

    public int getGuestNum(){
        return this.guestNum;
    }

    public String getBanquetName(){
        return this.banquetName;
    }

    public int getEmpNum(){
        return  this.empNum;
    }

    public int getCustID(){
        return this.custID;
    }

}
