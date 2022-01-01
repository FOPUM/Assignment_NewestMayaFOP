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
public class registeredModuleDetailsPopupController implements Initializable{
    
    @FXML
    private Label academicYearLabel;

    @FXML
    private Label courseCodeLabel;

    @FXML
    private Label courseNameLabel;

    @FXML
    private Label creditHourLabel;

    @FXML
    private Label occLabel;

    @FXML
    private Label periodSlotLabel;

    @FXML
    private Label statusLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        
    }
    
    public void setContentInfo(String acaYear, String periodslot, String coursecode, String coursename, String credithour, String occ, String status){
        academicYearLabel.setText(acaYear);
        periodSlotLabel.setText(periodslot);
        courseCodeLabel.setText(coursecode);
        courseNameLabel.setText(coursename);
        creditHourLabel.setText(credithour);
        occLabel.setText(occ);
        statusLabel.setText(status);
    }
    
    public void setAcademicYearLabel(String a) {
        academicYearLabel.setText(a);
    }
    
    public void setCourseCodeLabel(String a) {
        courseCodeLabel.setText(a);
    }
    
    public void setCourseNameLabel(String a) {
        courseNameLabel.setText(a);
    }
    
    public void setCreditHourLabel(String a) {
        creditHourLabel.setText(a);
    }
    
    public void setOccLabel(String a) {
        occLabel.setText(a);
    }
    
    public void setPeriodSlotLabel(String a) {
        periodSlotLabel.setText(a);
    }
    
    public void setStatusLabel(String a) {
        statusLabel.setText(a);
    }

}
