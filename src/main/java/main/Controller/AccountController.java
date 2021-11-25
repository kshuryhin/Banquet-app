package main.Controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.Model.Repository;
import main.Model.User;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AccountController {
    private Stage stage;
    private Scene scene;
    URL cssURL;

    {
        try {
            cssURL = new File("src/main/java/main/View/application.css").toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    String css = cssURL.toExternalForm();
    @FXML
    Button analysisSups = new Button();
    @FXML
    Button job = new Button();
    @FXML
    Label showLogin = new Label();
    @FXML
    Label showPassword = new Label();
    @FXML
    public void initialize(){

        showUserInfo(Repository.autorizedUser);
        if (Repository.autorizedUser.getPrivilege()!="Admin"){
            job.setDisable(true);
            analysisSups.setDisable(true);
        }
    }

    public void showSupplyNumber(Event event){
        Image icon = new Image("file:src/main/java/main/View/logo.png");
        Stage numStage = new Stage();
        int number = Repository.orderNumber();

        Label numLabel = new Label("Загальна кількість замовлень: " + number);
        numLabel.setFont(new Font("Arial", 18));
        numLabel.setStyle("-fx-text-fill: #A17F06");
        numLabel.setAlignment(Pos.CENTER);
        Scene numScene = new Scene(numLabel);


        numStage.setScene(numScene);
        numStage.getIcons().add(icon);
        numStage.setWidth(400);
        numStage.setHeight(200);
        numStage.show();
    }

    public void showUserInfo(User user){

        showLogin.setText("Логін: " + user.getLogin());
        showPassword.setText("Пароль: " + user.getPassword());
    }

    public void switchToAClientAnalysis(ActionEvent event) throws IOException {
        URL url = new File("src/main/java/main/View/client-analysis.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSupplyAnalysis(ActionEvent event) throws IOException {
            URL url = new File("src/main/java/main/View/supply-analysis.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
    }

    public void switchToASuppliers(ActionEvent event) throws IOException {
        URL url = new File("src/main/java/main/View/suppliers.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToJob(ActionEvent event) throws IOException {
            URL url = new File("src/main/java/main/View/job-analysis.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
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
