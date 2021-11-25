package main.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.Model.Repository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JobAnalysisController {
    private Stage stage;
    private Scene scene;
    @FXML
    Label employeeInfo = new Label();
    @FXML
    ChoiceBox<String> chooseJob = new ChoiceBox<>();
    @FXML
    public void initialize() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rs = Repository.getJobs();
        while (rs.next()){
            list.add(rs.getString("Name"));
        }
        chooseJob.setItems(list);
    }

    public void switchToAccount(MouseEvent event) throws IOException {
        URL url = new File("src/main/java/main/View/account.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void showEmployeeInfo() throws SQLException {
        if (chooseJob.getValue()==null){
            Image icon = new Image("file:src/main/java/main/View/logo.png");
            Stage errorStage = new Stage();


            Label errorLabel = new Label("You did not choose the option!");
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
            String job = chooseJob.getValue();
            ResultSet rs = Repository.getEmployeeByJob(job);
            if (!rs.next()){
                employeeInfo.setText("Місце вакантне!");
            }else {
                    employeeInfo.setText("Ім'я: " + rs.getString("Name") + " Прізвище: " + rs.getString("LastName") +
                            " Ідентифікатор: " + rs.getInt("empID"));
            }
        }

    }
}
