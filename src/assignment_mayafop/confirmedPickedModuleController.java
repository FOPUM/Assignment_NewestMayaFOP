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
public class confirmedPickedModuleController implements Initializable{
    @FXML
    private Label OccLabel;

    @FXML
    private Label courseIDLabel;

    @FXML
    private Label courseNameLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        
    }

    public void setOccLabel(String occ) {
        OccLabel.setText(occ);
    }

    public void setCourseIDLabel(String courseID) {
        courseIDLabel.setText(courseID);
    }

    public void setCourseNameLabel(String courseName) {
        courseNameLabel.setText(courseName);
    }
}
