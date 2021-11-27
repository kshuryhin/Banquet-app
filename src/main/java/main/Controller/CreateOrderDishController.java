//Клас описує логіку опису блюд під час створення замовлення
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.Model.Dish;
import main.Model.Repository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateOrderDishController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    ChoiceBox<String> choiceBox = new ChoiceBox<>();
    @FXML
    TextField dishNum = new TextField();
    @FXML
    public void initialize() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rs = Repository.getDishes();
        while (rs.next()){
            list.add(rs.getString("Name"));
        }
        choiceBox.setItems(list);

    }

    public boolean createDishInfo() throws SQLException {
        if (!dishNum.getText().matches("^[0-9]+$")){
            Image icon = new Image("file:src/main/java/main/View/logo.png");
            Stage errorStage = new Stage();


            Label errorLabel = new Label("Number of dishes is incorrect!");
            errorLabel.setFont(new Font("Arial", 18));
            errorLabel.setStyle("-fx-text-fill: red");
            errorLabel.setAlignment(Pos.CENTER);
            Scene errorScene = new Scene(errorLabel);

            errorStage.setScene(errorScene);
            errorStage.getIcons().add(icon);
            errorStage.setWidth(400);
            errorStage.setHeight(200);
            errorStage.show();
            return false;
        }
        if (choiceBox.getValue() == null || dishNum.getText().trim().isEmpty()){
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
            return false;
        }else {
            int dishID = Repository.getDishIndexByName(choiceBox.getValue());
            Dish dish = new Dish(dishID, Integer.parseInt(dishNum.getText()));
            if (Repository.containsDish(dish, Repository.getLastOrder()) > 0){
                Image icon = new Image("file:src/main/java/main/View/logo.png");
                Stage errorStage = new Stage();


                Label errorLabel = new Label("You have already ordered this dish!");
                errorLabel.setFont(new Font("Arial", 18));
                errorLabel.setStyle("-fx-text-fill: red");
                errorLabel.setAlignment(Pos.CENTER);
                Scene errorScene = new Scene(errorLabel);

                errorStage.setScene(errorScene);
                errorStage.getIcons().add(icon);
                errorStage.setWidth(400);
                errorStage.setHeight(200);
                errorStage.show();
                return false;
            }else{
                Repository.createOrderDish(dish, Repository.getLastOrder());
                return true;
            }

        }

    }

    public void switchToMoreDish(Event event) throws IOException, SQLException {
        if (createDishInfo()){
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

    public void switchToFunctions(Event event) throws IOException, SQLException {
        if (createDishInfo()){
            URL url = new File("src/main/java/main/View/functions.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void closeOrder(Event event) throws IOException {

        URL url = new File("src/main/java/main/View/confirm.fxml").toURI().toURL();
        URL cssURL = new File("src/main/java/main/View/application.css").toURI().toURL();
        String css = cssURL.toExternalForm();

        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
}
