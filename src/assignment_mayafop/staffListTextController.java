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
public class staffListTextController implements Initializable{
    MiscFunc misc = new MiscFunc();
    @FXML
    private Label staffIdLabel;

    @FXML
    private Label staffNameLabel;

    @FXML
    private Label umMailLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        
    }
    
    public void setContentInfo(String matricid, String studname, String ummail){
        staffIdLabel.setText(matricid);
        staffNameLabel.setText(misc.upperLetter(studname));
        umMailLabel.setText(ummail);
    }
    
}
