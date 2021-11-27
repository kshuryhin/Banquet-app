//Клас створенго для зберігання даних користувача
package main.Model;

public class User {
    private String login;
    private String password;
    private String Privilege = "User";

    public void setLogin(String login){
        this.login = login;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setPrivilege(String privilege){
        this.Privilege = privilege;
    }

    public String getLogin(){
        return  this.login;
    }

    public String getPassword(){
        return this.password;
    }

    public String getPrivilege(){
        return this.Privilege;
    }
}
