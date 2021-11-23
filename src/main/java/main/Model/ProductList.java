package main.Model;

import java.util.Date;

public class ProductList {
    private String dishName;
    private String prodName;
    private Date shelfLife;
    private String fromCountry;
    private int prodNum;

    public ProductList(String dishName, String prodName, Date shelfLife, String fromCountry, int prodNum){
        this.dishName = dishName;
        this.prodName = prodName;
        this.shelfLife = shelfLife;
        this.fromCountry = fromCountry;
        this.prodNum = prodNum;
    }

    public String getDishName(){
        return this.dishName;
    }

    public String getProdName(){
        return this.prodName;
    }

    public Date getShelfLife(){
        return this.shelfLife;
    }

    public String getFromCountry(){
        return this.fromCountry;
    }

    public int getProdNum(){
        return this.prodNum;
    }
}
