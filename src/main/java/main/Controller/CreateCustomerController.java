//Клас описує логіку створення нового клієнта
package main.Controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.Model.Customer;
import main.Model.Repository;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class CreateCustomerController {
    private Stage stage;
    private Scene scene;
    @FXML
    TextField custName;
    @FXML
    TextField custLastName;
    @FXML
    TextField custEmail;
    @FXML
    TextField custPhone;
    @FXML
    TextField custAddress;

    public void switchToFunctions(Event event) throws IOException {
        URL url = new File("src/main/java/main/View/functions.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void createCustomer(Event event) throws IOException {
        if (!custName.getText().trim().isEmpty() && !custLastName.getText().trim().isEmpty() &&
                !custEmail.getText().trim().isEmpty() && !custPhone.getText().trim().isEmpty() && !custAddress.getText().trim().isEmpty()){
            Customer customer =  new Customer(custName.getText(), custLastName.getText(), custAddress.getText(),
                    custPhone.getText(), custEmail.getText());
            Repository.createCustomer(customer);
            switchToFunctions(event);
        } else {
            Image icon = new Image("file:src/main/java/main/View/logo.png");
            Stage errorStage = new Stage();


            Label errorLabel = new Label("Login or password are empty!");
            errorLabel.setFont(new Font("Arial", 18));
            errorLabel.setStyle("-fx-text-fill: red");
            errorLabel.setAlignment(Pos.CENTER);
            Scene errorScene = new Scene(errorLabel);

            errorStage.setScene(errorScene);
            errorStage.getIcons().add(icon);
            errorStage.setWidth(400);
            errorStage.setHeight(200);
            errorStage.show();
        }


    }
}
