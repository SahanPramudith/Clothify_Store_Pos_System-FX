package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EventObject;

public class LoginFormController {

    private Parent parent;
    private Stage stage;
    private EventObject actionEvent;
    private Scene scene;
    @FXML
    void btnLoginDashBoardOnAction(ActionEvent event) throws IOException {


        parent= FXMLLoader.load(getClass().getResource("../view/dashboard.fxml"));
        stage= (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();


//        Stage stage = new Stage();
//        try {
//            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/dashboard.fxml"))));
//            stage.show();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        stage.setResizable(false);
//
    }

}
