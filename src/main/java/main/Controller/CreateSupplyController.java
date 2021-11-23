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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.Model.Repository;
import main.Model.Supply;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CreateSupplyController {
    private Stage stage;
    private Scene scene;
    @FXML
    ChoiceBox<String> productName = new ChoiceBox<>();
    @FXML
    DatePicker supplyDate = new DatePicker();
    @FXML
    ChoiceBox<String> supplierName = new ChoiceBox<>();
    @FXML
    TextField mass = new TextField();
    @FXML
    TextField supplyPrice = new TextField();
    @FXML
    public void initialize() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rs = Repository.getProducts();
        while (rs.next()){
            list.add( rs.getString("Name"));
        }
        productName.setItems(list);

        ObservableList<String> suppliers = FXCollections.observableArrayList();
        ResultSet rs2 = Repository.getSuppliers();
        while (rs2.next()){
            suppliers.add(rs2.getString("Name"));
        }
        supplierName.setItems(suppliers);
    }


    public void createSupply(Event event) throws IOException {
        int prodIndex = Repository.getProdIndexByName(productName.getValue());
        int supIndex = Repository.getSupplierIndexByName(supplierName.getValue());

        Supply supply = new Supply(supplyPrice.getText(), mass.getText(), supplyDate.getValue(), prodIndex, supIndex);
        if (productName.getValue() == null || supplyDate.getValue() == null || supplierName.getValue() == null || mass.getText().trim().isEmpty()
                || supplyPrice.getText().trim().isEmpty()){
            Image icon = new Image("file:src/main/java/main/View/logo.png");
            Stage errorStage = new Stage();


            Label errorLabel = new Label("Some fields are empty!");
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
            Repository.createSupply(supply);
            switchToFunctions(event);
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
