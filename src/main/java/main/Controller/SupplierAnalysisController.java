//Клас створено для відображення аналізу постачальників
package main.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.Model.Repository;
import main.Model.Supplier;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierAnalysisController {
    private Stage stage;
    private Scene scene;
    @FXML
    TableView<Supplier> suppliersTable = new TableView<>();
    @FXML
    TableColumn ID = new TableColumn();
    @FXML
    TableColumn Name = new TableColumn();
    @FXML
    TableColumn Phone = new TableColumn();
    @FXML
    TableColumn Address = new TableColumn();
    @FXML
    Button newSupplier = new Button();


    @FXML
    public void initialize() throws SQLException {
        viewSupplierTable();
        if (Repository.autorizedUser.getPrivilege() == "Admin"){
            newSupplier.setDisable(false);
        }
    }
    public void switchToAccount(MouseEvent event) throws IOException {
        URL url = new File("src/main/java/main/View/account.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCreateSupplier(Event event) throws IOException {
        URL url = new File("src/main/java/main/View/createSupplier.fxml").toURI().toURL();
        URL cssURL = new File("src/main/java/main/View/application.css").toURI().toURL();
        String css = cssURL.toExternalForm();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void viewSupplierTable() throws SQLException {
        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Phone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        Address.setCellValueFactory(new PropertyValueFactory<>("Address"));

        ResultSet info = Repository.getSuppliers();
        ObservableList<Supplier> list = FXCollections.observableArrayList();
        while (info.next()){
            list.add(new Supplier(
                    info.getInt("supID"),
                    info.getString("Name"),
                    info.getString("Phone"),
                    info.getString("Address")
            ));
            suppliersTable.setItems(list);
        }
        info.close();
    }
}
