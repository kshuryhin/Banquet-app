package main.Controller;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.Model.Repository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class ConfirmController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToFunctions(Event event) throws IOException, SQLException {
        Repository.deleteOrder(Repository.getLastOrder());
        URL url = new File("src/main/java/main/View/functions.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToOrderDish(Event event) throws IOException {
        URL url = new File("src/main/java/main/View/createOrder-dish.fxml").toURI().toURL();
        URL cssURL = new File("src/main/java/main/View/application.css").toURI().toURL();
        String css = cssURL.toExternalForm();

        root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
}
