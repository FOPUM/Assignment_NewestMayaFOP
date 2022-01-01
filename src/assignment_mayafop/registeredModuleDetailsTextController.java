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
public class registeredModuleDetailsTextController implements Initializable{
    
    @FXML
    private Label courseCodeDetailsLabel;

    @FXML
    private Label courseNameDetailsLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        
    }
    
    public void setContentInfo(String coursecode, String coursename){
        courseCodeDetailsLabel.setText(coursecode);
        courseNameDetailsLabel.setText(coursename);
    }
    
    public void setCourseCodeDetailsLabel(String a) {
        courseCodeDetailsLabel.setText(a);
    }

    public void setCourseNameDetailsLabel(String a) {
        courseNameDetailsLabel.setText(a);
    }

}
