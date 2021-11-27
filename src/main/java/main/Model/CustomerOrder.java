//Клас створенго для зберігання даних замовлення відносно клієнта
package main.Model;

import java.util.Date;

public class CustomerOrder {
    private String name;
    private String banquetName;
    private Date banquetDate;

    public CustomerOrder(String name, String banquetName, Date banquetDate){
        this.name = name;
        this.banquetName = banquetName;
        this.banquetDate = banquetDate;
    }

    public String getName(){
        return this.name;
    }

    public String getBanquetName(){
        return this.banquetName;
    }

    public Date getBanquetDate(){
        return this.banquetDate;
    }
}
