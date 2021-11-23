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
import main.Model.ProductList;
import main.Model.Repository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductListController {
    private Stage stage;
    private Scene scene;
    @FXML
    TableView<ProductList> prodListID = new TableView<>();
    @FXML
    TableColumn dishName = new TableColumn();
    @FXML
    TableColumn prodName = new TableColumn();
    @FXML
    TableColumn shelfLife = new TableColumn();
    @FXML
    TableColumn fromCountry = new TableColumn();
    @FXML
    TableColumn prodNum = new TableColumn();
    @FXML
    public void initialize() throws SQLException {
        viewProductList();
    }


    public void switchToFunctions(Event event) throws IOException {
        URL url = new File("src/main/java/main/View/functions.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void viewProductList() throws SQLException {
        dishName.setCellValueFactory(new PropertyValueFactory<>("dishName"));
        prodName.setCellValueFactory(new PropertyValueFactory<>("prodName"));
        shelfLife.setCellValueFactory(new PropertyValueFactory<>("shelfLife"));
        fromCountry.setCellValueFactory(new PropertyValueFactory<>("fromCountry"));
        prodNum.setCellValueFactory(new PropertyValueFactory<>("prodNum"));

        ResultSet info = Repository.getProductList();
        ObservableList<ProductList> list = FXCollections.observableArrayList();
        while (info.next()){
            list.add(new ProductList(
                    info.getString(1),
                    info.getString(2),
                    info.getDate(3),
                    info.getString(4),
                    info.getInt(5)
            ));
            prodListID.setItems(list);
        }
        info.close();
    }

}
