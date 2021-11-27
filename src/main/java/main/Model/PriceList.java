//Клас створенго для зберігання даних прайс-листу
package main.Model;

public class PriceList {
    private String Name;
    private String fromCountry;
    private String Size;
    private int Price;
    private String Category;

    public PriceList(String Name, String fromCountry, String Size, int Price, String Category){
        this.Name = Name;
        this.fromCountry = fromCountry;
        this.Size = Size;
        this.Price = Price;
        this.Category = Category;
    }

    public String getName(){
        return this.Name;
    }

    public String getFromCountry(){
        return this.fromCountry;
    }

    public String getSize(){
        return this.Size;
    }

    public int getPrice(){
        return this.Price;
    }

    public String getCategory(){
        return this.Category;
    }

}
