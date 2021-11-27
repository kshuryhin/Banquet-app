//Клас описує логіку створення нового постачальника
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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.Model.Customer;
import main.Model.Repository;
import main.Model.Supplier;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class CreateSupplierController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    TextField supName = new TextField();
    @FXML
    TextField supPhone = new TextField();
    @FXML
    TextField supAddress = new TextField();

    public void switchToAccount(Event event) throws IOException {
        URL url = new File("src/main/java/main/View/account.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void createSupplier(Event event) throws IOException {
        if (!supName.getText().trim().isEmpty() && !supPhone.getText().trim().isEmpty() &&
                !supAddress.getText().trim().isEmpty()){
            Supplier supplier = new Supplier();
            supplier.setName(supName.getText());
            supplier.setPhone(supPhone.getText());
            supplier.setAddress(supAddress.getText());
            Repository.createSupplier(supplier);
            switchToAccount(event);
        } else {
            Image icon = new Image("file:src/main/java/main/View/logo.png");
            Stage errorStage = new Stage();


            Label errorLabel = new Label("Fill all the fields!");
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
