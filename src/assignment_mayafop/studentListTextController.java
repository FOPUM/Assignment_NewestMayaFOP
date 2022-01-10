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
public class studentListTextController implements Initializable{
    MiscFunc misc = new MiscFunc();
    @FXML
    private Label matricLabel;

    @FXML
    private Label studentLabel;

    @FXML
    private Label facultyLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        
    }
    
    public void setContentInfo(String matricid, String studname, String faculty){
        matricLabel.setText(matricid);
        studentLabel.setText(misc.upperLetter(studname));
        facultyLabel.setText(misc.upperLetter(faculty));
    }
    
    public void setFacultyLabel(String a) {
        facultyLabel.setText(a); 
    }

    public void setMatricLabel(String a) {
        matricLabel.setText(a); 
    }

    public void setStudentLabel(String a) {
        studentLabel.setText(a); 
    }
    
    
}
