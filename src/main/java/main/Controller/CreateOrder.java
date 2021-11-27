//Клас описує логіку створення безпосередньо замовлення
package main.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.Model.Order;
import main.Model.Repository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateOrder {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    ChoiceBox<String> customer = new ChoiceBox<>();
    @FXML
    DatePicker data = new DatePicker();
    @FXML
    TextField guests = new TextField();
    @FXML
    TextField banquet = new TextField();
    @FXML
    TextField employeenum = new TextField();

    @FXML
    public void initialize() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rs = Repository.getCustomers();
        while (rs.next()){
            list.add("Name: " + rs.getString("Name") + " ID: " + rs.getString("custID"));
        }


        customer.setItems(list);
    }

    public void switchToOrderDish(Event event) throws IOException {
        if (!guests.getText().matches("^[0-9]+$") || !employeenum.getText().matches("^[0-9]+$")){
            Image icon = new Image("file:src/main/java/main/View/logo.png");
            Stage errorStage = new Stage();


            Label errorLabel = new Label("Number of guests or employees is incorrect!");
            errorLabel.setFont(new Font("Arial", 18));
            errorLabel.setStyle("-fx-text-fill: red");
            errorLabel.setAlignment(Pos.CENTER);
            Scene errorScene = new Scene(errorLabel);

            errorStage.setScene(errorScene);
            errorStage.getIcons().add(icon);
            errorStage.setWidth(400);
            errorStage.setHeight(200);
            errorStage.show();
        } else if (data.getValue() == null || guests.getText().trim().isEmpty() || banquet.getText().trim().isEmpty()
                || employeenum.getText().trim().isEmpty() || customer.getValue() == null){
            Image icon = new Image("file:src/main/java/main/View/logo.png");
            Stage errorStage = new Stage();


            Label errorLabel = new Label("One of the fields is empty!");
            errorLabel.setFont(new Font("Arial", 18));
            errorLabel.setStyle("-fx-text-fill: red");
            errorLabel.setAlignment(Pos.CENTER);
            Scene errorScene = new Scene(errorLabel);

            errorStage.setScene(errorScene);
            errorStage.getIcons().add(icon);
            errorStage.setWidth(400);
            errorStage.setHeight(200);
            errorStage.show();
        } else{
            String s = customer.getValue();
            int custID = 0;
            int coef = 1;
            for (int i = 0; i < s.length(); i++) {
                if (Character.isDigit(s.charAt(i))){
                    custID*=coef;
                    custID += Character.getNumericValue(s.charAt(i));
                    coef*=10;
                }
            }
            Order order = new Order(data.getValue(), Integer.parseInt(guests.getText()), banquet.getText(), Integer.parseInt(employeenum.getText()), custID);
            Repository.createOrder(order);
            URL url = new File("src/main/java/main/View/createOrder-dish.fxml").toURI().toURL();
            URL cssURL = new File("src/main/java/main/View/application.css").toURI().toURL();
            String css = cssURL.toExternalForm();

            root = FXMLLoader.load(url);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
        }


    }

    public void switchToFunctions(Event event) throws IOException {
        URL url = new File("src/main/java/main/View/functions.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
