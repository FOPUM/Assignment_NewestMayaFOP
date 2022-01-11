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
public class staffListPopUpTextController implements Initializable{
    MiscFunc misc = new MiscFunc();
    @FXML
    private Label adminLabel;

    @FXML
    private Label courseIdLabel;

    @FXML
    private Label courseNameLabel;

    @FXML
    private Label staffIdLabel;

    @FXML
    private Label staffNameLabel;

    @FXML
    private Label umMailLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        
    }
    
    public void setContentInfo(String id, String staffname, String ummail, String courseid, String coursename, String admin){
        staffIdLabel.setText(id);
        if(staffname != null){
            staffNameLabel.setText(misc.upperLetter(staffname));
        }else{
            staffNameLabel.setText("-");
        }
        
        umMailLabel.setText(ummail);
        if(courseid != null){
            courseIdLabel.setText(courseid);
        }else{
            courseIdLabel.setText("-");
        }
        
        if(coursename != null){
            courseNameLabel.setText(misc.upperLetter(coursename));
        }else{
            courseNameLabel.setText("-");
        }
        
        if(admin != null){
            adminLabel.setText(admin);
        }else{
            adminLabel.setText("-");
        }
    }
}
