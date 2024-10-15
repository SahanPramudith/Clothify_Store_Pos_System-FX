package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.EventObject;
import java.util.ResourceBundle;


public class Dashboard implements Initializable {
    private Parent parent;
    private Stage stage;
    private EventObject actionEvent;
    private Scene scene;



    @FXML
    private Label lbldate;

    @FXML
    private Label lbltieme;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayTime();
    }

    private void displayTime(){
        Timeline date=new Timeline(new KeyFrame(Duration.ZERO,
                e->lbldate.setText(LocalDate.now().
                        format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))),
                new KeyFrame((Duration.seconds(1))));
        date.setCycleCount(Animation.INDEFINITE);
        date.play();
        Timeline time=new Timeline(new KeyFrame(Duration.ZERO,
                e-> lbltieme.setText(LocalDateTime.now().
                        format(DateTimeFormatter.ofPattern("HH:mm:ss")))),
                new KeyFrame(Duration.seconds(1)));

        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void btnOdetOnAction(ActionEvent actionEvent) throws IOException {
        parent= FXMLLoader.load(getClass().getResource("../view/add_item.fxml"));
        stage= (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) {
    }

    public void btnItenOnAction(ActionEvent actionEvent) {
    }

    public void btnOrderDetailsnOnAction(ActionEvent actionEvent) {
    }

    public void btnSupplierOnAction(ActionEvent actionEvent) {
    }
}
