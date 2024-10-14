package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {



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
}
