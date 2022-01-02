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

/**
 *
 * @author Ming
 */
public class registeredStudentDetailsTextController implements Initializable{
    
    @FXML
    private Label courseOccLabel;

    @FXML
    private Label courseCapacityLabel;

    @FXML
    private Label courseCodeLabel;

    @FXML
    private Label courseNameLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        
    }
    
    public void setContentInfo(String coursecode, String coursename, String courseocc, String coursecapacity){
        courseOccLabel.setText(courseocc);
        courseCapacityLabel.setText(coursecapacity);
        courseCodeLabel.setText(coursecode);
        courseNameLabel.setText(coursename);
    }

    public void setCourseOccLabel(String a) {
        courseOccLabel.setText(a);  
    }

    public void setCourseCapacityLabel(String a) {
        courseCapacityLabel.setText(a);
    }

    public void setCourseCodeLabel(String a) {
        courseCodeLabel.setText(a);
    }

    public void setCourseNameLabel(String a) {
        courseNameLabel.setText(a);
    }
}
