/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxModel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Ming
 */
public class confirmPickedModuleModel {

    
    private SimpleStringProperty courseIDLabel;
    private SimpleStringProperty courseNameLabel;
    private SimpleStringProperty OccLabel;

    public confirmPickedModuleModel(String courseIDLabel, String courseNameLabel, String OccLabel) {
        this.courseIDLabel = new SimpleStringProperty(courseIDLabel);
        this.courseNameLabel = new SimpleStringProperty(courseNameLabel);
        this.OccLabel = new SimpleStringProperty(OccLabel);
    }



    public String getCourseIDLabel() {
        return courseIDLabel.get();
    }
    
    public String getCourseNameLabel() {
        return courseNameLabel.get();
    }

    public String getOccLabel() {
        return OccLabel.get();
    }
}
