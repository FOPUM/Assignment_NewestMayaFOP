/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Ming
 */
public class timetableColumnController implements Initializable{
    @FXML
    private Rectangle rect;

    @FXML
    private Label courseCodeLabel;

    @FXML
    private Label courseNameLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void setBoxSize(String a, String b, String courseMode, int courseDuration){
        courseCodeLabel.setText(a);
        courseNameLabel.setText(b);
        
        if(courseMode.equals("Lecture")){
            rect.setStyle("-fx-background-color:#FFFFFF;");
        } else if(courseMode.equals("Tutorial")){
            rect.setStyle("-fx-background-color:#c8d7ed;");
        } else if(courseMode.equals("Lab")){
            rect.setStyle("-fx-background-color:#dce1ea;");
        }
        rect.setWidth(80.0 * courseDuration);
        
        if(courseDuration == 1){
            courseNameLabel.setMaxWidth(60.0);
        }else if(courseDuration == 2){
            courseNameLabel.setMaxWidth(140.0);
        }else if(courseDuration == 3){
            courseNameLabel.setMaxWidth(220.0);
        }
       
    }

}
