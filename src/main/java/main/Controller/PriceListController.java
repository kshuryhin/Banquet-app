package main.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Model.PriceList;
import main.Model.Repository;
import main.Model.Supplier;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PriceListController {
    private Stage stage;
    private Scene scene;
    @FXML
    TableView<PriceList> priceListTable = new TableView<>();
    @FXML
    TableColumn dishName = new TableColumn();
    @FXML
    TableColumn fromCountry = new TableColumn();
    @FXML
    TableColumn size = new TableColumn();
    @FXML
    TableColumn price = new TableColumn();
    @FXML
    TableColumn category = new TableColumn();
    @FXML
    public void initialize() throws SQLException {
        viewPriceList();
    }



    public void switchToFunctions(Event event) throws IOException {
        URL url = new File("src/main/java/main/View/functions.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void viewPriceList() throws SQLException {
        dishName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        fromCountry.setCellValueFactory(new PropertyValueFactory<>("fromCountry"));
        size.setCellValueFactory(new PropertyValueFactory<>("Size"));
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        category.setCellValueFactory(new PropertyValueFactory<>("Category"));

        ResultSet info = Repository.getPriceList();
        ObservableList<PriceList> list = FXCollections.observableArrayList();
        while (info.next()){
            list.add(new PriceList(
                    info.getString("Name"),
                    info.getString("fromCountry"),
                    info.getString("Size"),
                    info.getInt("Price"),
                    info.getString(5)

            ));
            priceListTable.setItems(list);
        }
        info.close();
    }

}
