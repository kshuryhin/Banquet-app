//Клас створено для демонстрації замовлень відносно обраного клієнта
package main.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.Model.CustomerOrder;
import main.Model.Repository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerOrderController {
    private Stage stage;
    private Scene scene;
    @FXML
    ChoiceBox<String> ordersByClient = new ChoiceBox<>();
    @FXML
    TableView<CustomerOrder> CustomerOrders = new TableView<>();
    @FXML
    TableColumn orderName = new TableColumn();
    @FXML
    TableColumn BanquetDate = new TableColumn();
    @FXML
    TableColumn CustomerName = new TableColumn();
    @FXML
    Button show = new Button();

    @FXML
    public void initialize() throws SQLException {
        ObservableList<String> list1 = FXCollections.observableArrayList();
        ResultSet rs1 = Repository.getCustomers();
        while (rs1.next()){
            list1.add("ID: " + rs1.getInt("custID") + " Name: " + rs1.getString("Name"));
        }
        ordersByClient.setItems(list1);
    }
        public void switchToAccount(MouseEvent event) throws IOException {
        URL url = new File("src/main/java/main/View/account.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

        public void setShowOrderByClient() throws SQLException {
        if (ordersByClient.getValue()==null){
            Image icon = new Image("file:src/main/java/main/View/logo.png");
            Stage errorStage = new Stage();


            Label errorLabel = new Label("You did not choose the option!");
            errorLabel.setFont(new Font("Arial", 18));
            errorLabel.setStyle("-fx-text-fill: red");
            errorLabel.setAlignment(Pos.CENTER);
            Scene errorScene = new Scene(errorLabel);

            errorStage.setScene(errorScene);
            errorStage.getIcons().add(icon);
            errorStage.setWidth(400);
            errorStage.setHeight(200);
            errorStage.show();
        }else{
            orderName.setCellValueFactory(new PropertyValueFactory<>("banquetName"));
            BanquetDate.setCellValueFactory(new PropertyValueFactory<>("banquetDate"));
            CustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
            ResultSet info = Repository.getOrderByCustomer(getId());
            ObservableList<CustomerOrder> list = FXCollections.observableArrayList();

            while (info.next()){
                CustomerOrder customerOrder = new CustomerOrder(
                        info.getString("Name"),
                        info.getString("banquetName"),
                        info.getDate("Date")
                );
                list.add(customerOrder);
                CustomerOrders.setItems(list);
            }
            info.close();

        }

    }

    public int getId(){
        String s = ordersByClient.getValue();
        int custID = 0;
        int coef = 1;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))){
                custID*=coef;
                custID += Character.getNumericValue(s.charAt(i));
                coef*=10;
            }
        }
        return custID;
    }


}
