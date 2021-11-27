//Клас описує логіку регістрації користувача
package main.Controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Model.Repository;
import main.Model.User;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class RegistrationController {
    private Stage stage;
    private Scene scene;
    @FXML
    TextField login = new TextField();
    @FXML
    PasswordField password = new PasswordField();

    public void registration(Event event) throws IOException {
        User user = new User();
        user.setLogin(login.getText());
        user.setPassword(password.getText());

            if (login.getText().trim().isEmpty() || password.getText().trim().isEmpty()){
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

        } else if (Repository.checkLogin(user) == -1){
                  Image icon = new Image("file:src/main/java/main/View/logo.png");
                  Stage errorStage = new Stage();
                  Label errorLabel = new Label("This login is already exists!");
                  errorLabel.setFont(new Font("Arial", 18));
                  errorLabel.setStyle("-fx-text-fill: red");
                  errorLabel.setAlignment(Pos.CENTER);
                  Scene errorScene = new Scene(errorLabel);

                  errorStage.setScene(errorScene);
                  errorStage.getIcons().add(icon);
                  errorStage.setWidth(400);
                  errorStage.setHeight(200);
                  errorStage.show();
              } else {
            Repository.registration(user);
            switchToEnter(event);
        }

    }

    public void switchToEnter(Event event) throws IOException {
        URL url = new File("src/main/java/main/View/autor.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




}
