package main.Model;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;

public class Repository {
    public static User autorizedUser;
    public static void main(String[] args) throws SQLException {
        getConnection();
        int x = Repository.getLastOrder();
        System.out.println(containsDish(new Dish(8, 34), x));
    }

    public static Connection getConnection(){
        Connection connection = null;
        String url = "jdbc:mysql://localhost:3307/banquet";
        String user = "root";
        String password = "root";

        try {
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null){
                System.out.println("Connected to MySQL server successfully!");
            } else {
                System.out.println("Failed to make connection");
            }
        } catch (SQLException sqlException){
            System.out.println(sqlException);
        }
        return connection;
    }

    public static int registration(User user){
        int status = 0;
        String Login = user.getLogin();
        String Password = user.getPassword();

        try {
            Connection connection = Repository.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO users(Login, Password) VALUES (?, ?)");
            ps.setString(1, Login);
            ps.setString(2, Password);

            status = ps.executeUpdate();
            connection.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return status;
    }

    public static int checkLogin(User user){
        HashSet<String> set = new HashSet<>();
        int status=  0;
        String Login = user.getLogin();

        try {
            Connection connection = Repository.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT Login FROM users");

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                set.add(rs.getString("Login"));
            }
            connection.close();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        for (String c:
             set) {
            if (Login.equals(c)){
                status = -1;
            }
        }
        return status;
    }

    public static int autorization(User user){
        HashMap<String, String> map = new HashMap<>();
        int status = 0;
        String Login = user.getLogin();
        String Password = user.getPassword();

        try {
            Connection connection = Repository.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM users WHERE Login=? AND Password=?");
            ps.setString(1, Login);
            ps.setString(2, Password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                status = rs.getInt(1);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return status;
    }

    public static int orderNumber(){
        int number = 0;

        try {
            Connection connection = Repository.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM orders");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                number = rs.getInt(1);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return number;
    }

    public static ResultSet getClients(){
        ResultSet rs = null;
        try {
            Connection connection = Repository.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT* FROM customer");
            rs = ps.executeQuery();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return rs;
    }

    public static ResultSet getDishes(){
        ResultSet rs = null;
        try{
            Connection connection = Repository.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT dishes.Name, dishes.dishID FROM dishes");
            rs = ps.executeQuery();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return rs;
    }

    public static ResultSet getCustomers(){
        ResultSet rs = null;

        try{
            Connection connection = Repository.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT customer.Name, customer.custID FROM customer");
            rs = ps.executeQuery();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return rs;
    }

    public static ResultSet getSuppliers(){
        ResultSet rs = null;
        try{
            Connection connection = Repository.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT* FROM suppliers");
            rs = ps.executeQuery();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return rs;
    }

    public static ResultSet getPriceList(){
        ResultSet rs = null;
        try {
            Connection connection = Repository.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT dishes.Name, dishes.fromCountry, dishes.Size, dishes.Price, " +
                    "category.Name FROM dishes JOIN category ON dishes.catID = category.catID ORDER BY dishes.Price ");
            rs =  ps.executeQuery();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return rs;
    }

    public static int getLastOrder() throws SQLException {
        int result = 0;
        ResultSet rs = null;
        try{
            Connection connection = Repository.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT MAX(orders.ordID) FROM orders");
            rs = ps.executeQuery();
            while (rs.next()){
                result = rs.getInt(1);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return result;
    }

    public static ResultSet getProductList(){
        ResultSet rs = null;
        try {
            Connection connection = Repository.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT dishes.Name, products.Name, products.ShelfLife, products.FromCountry, dish_product.NumberOfProducts FROM dish_product " +
                    "JOIN dishes ON dish_product.dishID = dishes.dishID " +
                    "JOIN products ON dish_product.prodID = products.prodID " +
                    "ORDER BY dishes.Name");
            rs = ps.executeQuery();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return rs;
    }

    public static ResultSet getEmployeeList(){
        ResultSet rs = null;
        try {
            Connection connection = Repository.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT employee.Name, employee.LastName, employee.Phone, employee.Address, employee.Email, orders.banquetName, orders.Date FROM order_employee " +
                    "JOIN employee ON order_employee.empID = employee.empID " +
                    "JOIN orders ON order_employee.ordID = orders.ordID " +
                    "ORDER BY orders.Date");
            rs = ps.executeQuery();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return rs;
    }

    public static ResultSet getProducts(){
        ResultSet rs = null;
        try {
            Connection connection = Repository.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT products.Name, products.prodID FROM products");
            rs = ps.executeQuery();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return rs;
    }

    public static int getProdIndexByName(String name){
        int index = 0;
        ResultSet rs = null;
        try{
            Connection connection = Repository.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT products.prodID FROM products WHERE Name=?");
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()){
                index = rs.getInt("prodID");
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return index;
    }

    public static int getSupplierIndexByName(String name){
        int index = 0;
        ResultSet rs = null;
        try{
            Connection connection = Repository.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT suppliers.supID FROM suppliers WHERE Name=?");
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()){
                index = rs.getInt("supID");
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return index;
    }
    public static int getDishIndexByName(String name){
        int index = 0;
        ResultSet rs = null;
        try{
            Connection connection = Repository.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT dishes.dishID FROM dishes WHERE Name=?");
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()){
                index = rs.getInt("dishID");
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return index;
    }





    public static void createCustomer(Customer customer){
        String name = customer.getName();
        String lastName = customer.getLastName();
        String address = customer.getAddress();
        String phone = customer.getPhone();
        String email = customer.getEmail();

        try{
            Connection connection = Repository.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO customer (Name, LastName, Address, Phone, Email) " +
                    "VALUES (?, ?, ?, ?, ?) ");
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setString(3, address);
            ps.setString(4, phone);
            ps.setString(5, email);
            ps.executeUpdate();
            connection.close();

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static void createOrder(Order order){
        LocalDate date = order.getDate();
        int guestsNum = order.getGuestNum();
        String banquetName = order.getBanquetName();
        int empNum = order.getEmpNum();
        int custID = order.getCustID();

        try {
            Connection connection = Repository.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT  INTO orders (Date, guestsNum, banquetName, empNum, custID)" +
                    "VALUES (?, ?, ?, ?, ?)");
            ps.setDate(1, Date.valueOf(date));
            ps.setInt(2, guestsNum);
            ps.setString(3, banquetName);
            ps.setInt(4, empNum);
            ps.setInt(5, custID);
            ps.executeUpdate();
            connection.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static void createOrderDish(Dish dish, int ordID){
        int dishID = dish.getDishID();
        int dishNun = dish.getDishNum();

        try{
            Connection connection = Repository.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO dish_order VALUES (?, ?, ?)");
            ps.setInt(1, dishID);
            ps.setInt(2, ordID);
            ps.setInt(3, dishNun);
            ps.executeUpdate();
            connection.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }

    }

    public static void createSupply(Supply supply){
        String price = supply.getPrice();
        String number = supply.getNumber();
        LocalDate date = supply.getDate();
        int supID = supply.getSupID();
        int prodID = supply.getProdID();

        try {
            Connection connection = Repository.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO supply(Price, Number, Date, supID, prodID)" +
                    "VALUES (?,?,?,?,?)");
            ps.setString(1, price);
            ps.setString(2, number);
            ps.setDate(3, Date.valueOf(date));
            ps.setInt(4, supID);
            ps.setInt(5, prodID);
            ps.executeUpdate();
            connection.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static void deleteOrder(int ordID){
        try {
            Connection connection = Repository.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM orders WHERE orders.ordID=?");
            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM dish_order WHERE dish_order.ordID=?");
            ps2.setInt(1, ordID);
            ps2.executeUpdate();
            ps.setInt(1, ordID);
            ps.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static int containsDish(Dish dish, int ordID){
        int dishID = dish.getDishID();

        ResultSet rs = null;
        int result = 0;

        try {
            Connection connection = Repository.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM dish_order WHERE dish_order.dishID=? AND dish_order.ordID=?");
            ps.setInt(1, dishID);
            ps.setInt(2, ordID);
            rs = ps.executeQuery();
            while (rs.next()){
                result = rs.getInt(1);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return result;
    }
}


