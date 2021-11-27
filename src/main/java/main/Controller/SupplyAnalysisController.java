//Клас описує логіку аналізу постачань
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
import main.Model.Product;
import main.Model.Repository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplyAnalysisController {
    private Stage stage;
    private Scene scene;
    @FXML
    ChoiceBox<String> chooseSupplier = new ChoiceBox<>();
    @FXML
    public void initialize() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rs = Repository.getSuppliers();
        while (rs.next()){
            list.add("ID: " + rs.getInt("supID") + " Name: " + rs.getString("Name"));
        }
        chooseSupplier.setItems(list);
    }
    @FXML
    Label numberOfSupplies = new Label();
    @FXML
    DatePicker periodFrom = new DatePicker();
    @FXML
    DatePicker periodTo = new DatePicker();
    @FXML
    TableView<Product> suppliesBetweenDates = new TableView<>();
    @FXML
    TableColumn nameOfProduct = new TableColumn();
    @FXML
    TableColumn countryOfProduct = new TableColumn();
    @FXML
    TableColumn productShelfLife = new TableColumn();
    @FXML
    TableColumn productSupplyDate = new TableColumn();
    @FXML
    TableColumn productSupplyPrice = new TableColumn();


    public void switchToAccount(MouseEvent event) throws IOException {
        URL url = new File("src/main/java/main/View/account.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void showSupplyNumber(){
        if (chooseSupplier.getValue()==null){
            Image icon = new Image("file:src/main/java/main/View/logo.png");
            Stage errorStage = new Stage();


            Label errorLabel = new Label("Please, fill the field!");
            errorLabel.setFont(new Font("Arial", 18));
            errorLabel.setStyle("-fx-text-fill: red");
            errorLabel.setAlignment(Pos.CENTER);
            Scene errorScene = new Scene(errorLabel);

            errorStage.setScene(errorScene);
            errorStage.getIcons().add(icon);
            errorStage.setWidth(400);
            errorStage.setHeight(200);
            errorStage.show();
        }else {
            numberOfSupplies.setText("Кількість постачань: " + Repository.getNumberOfSuppliesBySupplier(getId()));
        }
    }

    public void showSuppliesBetweenDates() throws SQLException {
        if (periodFrom.getValue()==null || periodTo.getValue()==null){
            Image icon = new Image("file:src/main/java/main/View/logo.png");
            Stage errorStage = new Stage();


            Label errorLabel = new Label("Please, choose dates!");
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
            nameOfProduct.setCellValueFactory(new PropertyValueFactory<>("name"));
            countryOfProduct.setCellValueFactory(new PropertyValueFactory<>("fromCountry"));
            productShelfLife.setCellValueFactory(new PropertyValueFactory<>("shelfLife"));
            productSupplyDate.setCellValueFactory(new PropertyValueFactory<>("supplyDate"));
            productSupplyPrice.setCellValueFactory(new PropertyValueFactory<>("supplyPrice"));
            ResultSet info = Repository.getSupplyBetweenDates(periodFrom.getValue(), periodTo.getValue());
            ObservableList<Product> list = FXCollections.observableArrayList();

            while (info.next()){
                Product product = new Product(
                        info.getString("Name"),
                        info.getString("FromCountry"),
                        info.getDate("ShelfLife"),
                        info.getDate("Date"),
                        info.getString("Price")
                );
                list.add(product);
                suppliesBetweenDates.setItems(list);
            }
            info.close();
        }
    }

    public int getId(){
        String s = chooseSupplier.getValue();
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
