//Клас створенго для зберігання даних блюд
package main.Model;

public class Dish {
    private int dishID;
    private int dishNum;
    private String dishName;

    public Dish(int dishID, int dishNum){
        this.dishID = dishID;
        this.dishNum = dishNum;
    }

    public int getDishID(){
        return this.dishID;
    }

    public int getDishNum(){
        return this.dishNum;
    }

    public void setDishName(){

    }
}
