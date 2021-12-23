/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 *
 * @author Ming
 */
public class animation {
    public void fade(BorderPane image){
        FadeTransition fading = new FadeTransition();
        fading.setNode(image);
        fading.setDuration(Duration.millis(500));
        fading.setCycleCount(TranslateTransition.INDEFINITE);
        fading.setInterpolator(Interpolator.LINEAR);
        fading.setFromValue(0);
        fading.setToValue(1);
        fading.play();
    }
}
