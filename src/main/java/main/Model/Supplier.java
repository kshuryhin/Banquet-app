//Клас створенго для зберігання даних постачальника
package main.Model;

public class Supplier {
    private int ID;
    private String Name;
    private String Phone;
    private String Address;

    public Supplier(){

    }

    public Supplier(int supID, String Name, String Phone, String Address){
        this.ID = supID;
        this.Name = Name;
        this.Phone = Phone;
        this.Address = Address;
    }

    public void setName(String name){
        this.Name = name;
    }

    public void setPhone(String phone){
        this.Phone = phone;
    }

    public void setAddress(String address){
        this.Address = address;
    }

    public int getID(){
        return this.ID;
    }

    public String getName(){
        return this.Name;
    }

    public String getPhone(){
        return this.Phone;
    }

    public String getAddress(){
        return this.Address;
    }

}
