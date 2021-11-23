package main.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.Model.Customer;
import main.Model.Repository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientAnalysisController {
    private Stage stage;
    private Scene scene;
    @FXML
    TableView<Customer> userTable = new TableView<>();
    @FXML
    TableColumn ID = new TableColumn();
    @FXML
    TableColumn Name = new TableColumn();
    @FXML
    TableColumn LastName = new TableColumn();
    @FXML
    TableColumn Address = new TableColumn();
    @FXML
    TableColumn Phone = new TableColumn();
    @FXML
    TableColumn Email = new TableColumn();
    @FXML
    public void initialize() throws SQLException {
        viewUserTable();
    }

    public void switchToAccount(MouseEvent event) throws IOException {
        URL url = new File("src/main/java/main/View/account.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void viewUserTable() throws SQLException {
        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        LastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        Address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        Phone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        Email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        ResultSet info = Repository.getClients();
        ObservableList<Customer> list = FXCollections.observableArrayList();


        while (info.next()){
            Customer customer = new Customer(
                    info.getString("Name"),
                    info.getString("LastName"),
                    info.getString("Address"),
                    info.getString("Phone"),
                    info.getString("Email")
            );
            customer.setID(info.getInt("custID"));
            list.add(customer);
            userTable.setItems(list);
        }
        info.close();
    }
}
