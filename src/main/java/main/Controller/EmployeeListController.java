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
import main.Model.EmployeeList;
import main.Model.ProductList;
import main.Model.Repository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeListController {
    private Stage stage;
    private Scene scene;
    @FXML
    TableView<EmployeeList> empListTable = new TableView<>();
    @FXML
    TableColumn name = new TableColumn();
    @FXML
    TableColumn lastName = new TableColumn();
    @FXML
    TableColumn phone = new TableColumn();
    @FXML
    TableColumn address = new TableColumn();
    @FXML
    TableColumn email = new TableColumn();
    @FXML
    TableColumn employeeBanquetName = new TableColumn();
    @FXML
    TableColumn employeeDate = new TableColumn();
    @FXML
    public void initialize() throws SQLException {
        viewEmployeeList();
    }

    public void switchToFunctions(Event event) throws IOException {
        URL url = new File("src/main/java/main/View/functions.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void viewEmployeeList() throws SQLException {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        employeeBanquetName.setCellValueFactory(new PropertyValueFactory<>("banquetName"));
        employeeDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        ResultSet info = Repository.getEmployeeList();
        ObservableList<EmployeeList> list = FXCollections.observableArrayList();
        while (info.next()){
            list.add(new EmployeeList(
                    info.getString(1),
                    info.getString(2),
                    info.getString(3),
                    info.getString(4),
                    info.getString(5),
                    info.getString(6),
                    info.getDate(7)
            ));
            empListTable.setItems(list);
        }
        info.close();
    }
}
