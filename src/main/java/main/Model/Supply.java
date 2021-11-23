package main.Model;

import java.time.LocalDate;
import java.util.Date;

public class Supply {
    private String price;
    private String number;
    private LocalDate date;
    private int supID;
    private int prodID;

    public Supply(String price, String number, LocalDate date, int supID, int prodID){
        this.price = price;
        this.number = number;
        this.date = date;
        this.supID = supID;
        this.prodID = prodID;
    }

    public String getPrice(){
        return this.price;
    }

    public String getNumber(){
        return this.number;
    }

    public LocalDate getDate(){
        return this.date;
    }

    public int getSupID(){
        return this.supID;
    }

    public int getProdID(){
        return this.prodID;
    }
}
