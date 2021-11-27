//Клас створенго для зберігання даних продукту
package main.Model;

import java.util.Date;

public class Product {
    private String name;
    private String fromCountry;
    private Date shelfLife;
    private Date supplyDate;
    private String supplyPrice;

    public Product(String name, String fromCountry, Date shelfLife, Date supplyDate, String supplyPrice){
        this.name = name;
        this.fromCountry = fromCountry;
        this.shelfLife = shelfLife;
        this.supplyDate = supplyDate;
        this.supplyPrice = supplyPrice;
    }

    public String getName(){
        return this.name;
    }

    public String getFromCountry(){
        return this.fromCountry;
    }

    public Date getShelfLife(){
        return this.shelfLife;
    }

    public Date getSupplyDate(){
        return this.supplyDate;
    }

    public String getSupplyPrice(){
        return this.supplyPrice;
    }

}
