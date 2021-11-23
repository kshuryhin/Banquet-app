package main.Model;

public class Customer {
    private int ID;
    private String Name;
    private String LastName;
    private String Address;
    private String Phone;
    private String Email;

    public Customer(String Name, String LastName, String Address, String Phone, String Email){
        this.Name = Name;
        this.LastName = LastName;
        this.Address = Address;
        this.Phone = Phone;
        this.Email = Email;
    }



    public int getID(){
        return  this.ID;
    }

    public void setID(int ID){
        this.ID = ID;
    }

    public String getName(){
        return this.Name;
    }

    public String getLastName(){
        return this.LastName;
    }

    public String getAddress(){
        return this.Address;
    }

    public String getPhone(){
        return  this.Phone;
    }

    public String getEmail(){
        return this.Email;
    }

}
