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
public class registeredStuentDetailsPopupTextController implements Initializable{
    MiscFunc misc = new MiscFunc();
    @FXML
    private Label matricIDLabel;

    @FXML
    private Label studentNameLabel;
      
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        
    }
    
    public void setContentInfo(String matricid, String studname){
        if (matricid.equals("No student arh")) {
             matricIDLabel.setText("");
             studentNameLabel.setText("NO STUDENT");
        }
        else{
            matricIDLabel.setText(matricid);
            studentNameLabel.setText(misc.upperLetter(studname));
        }

    }

    public void setMatricIDLabel(String a) {
        matricIDLabel.setText(a); 
    }
    
    public void setStudentNameLabel(String a) {
        studentNameLabel.setText("Sohai"); 
    }
}
