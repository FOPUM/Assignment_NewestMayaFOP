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
public class ModuleDetailsController implements Initializable{

    @FXML
    Label lectDayLabel;
    @FXML
    Label lectEndTimeLabel;
    @FXML
    Label lectIDLabel;
    @FXML
    Label lectStartTimeLabel;
    @FXML
    Label occLabel;
    @FXML
    Label staffIDLabel;
    @FXML
    Label staffNameLabel;
    @FXML
    Label tutoDayLabel;
    @FXML
    Label tutoEndTimeLabel;
    @FXML
    Label tutoIDLabel;
    @FXML
    Label tutoStartTimeLabel;
    
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        
    }

    public void setLectDayLabel(String lectDay) {
        lectDayLabel.setText(lectDay);
    }

    public void setLectEndTimeLabel(String lectEndTime) {
        lectEndTimeLabel.setText(lectEndTime);
    }

    public void setLectIDLabel(String lectID) {
        lectIDLabel.setText(lectID);
    }

    public void setLectStartTimeLabel(String lectStartTime) {
        lectStartTimeLabel.setText(lectStartTime);
    }

    public void setOccLabel(String a) {
        occLabel.setText(a);
    }

    public void setStaffIDLabel(String a) {
        staffIDLabel.setText(a);
    }

    public void setStaffNameLabel(String a) {
        staffNameLabel.setText(a);
    }

    public void setTutoDayLabel(String a) {
        tutoDayLabel.setText(a);
    }

    public void setTutoEndTimeLabel(String a) {
        tutoEndTimeLabel.setText(a);
    }

    public void setTutoIDLabel(String a) {
        tutoIDLabel.setText(a);
    }

    public void setTutoStartTimeLabel(String a) {
        tutoStartTimeLabel.setText(a);
    }
    
    
}
