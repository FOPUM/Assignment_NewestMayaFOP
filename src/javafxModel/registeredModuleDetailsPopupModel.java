/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author Ming
 */
public class registeredModuleDetailsPopupModel {
    
    private SimpleStringProperty academicYearLabel;
    private SimpleStringProperty courseCodeLabel;
    private SimpleStringProperty courseNameLabel;
    private SimpleStringProperty creditHourLabel;
    private SimpleStringProperty occLabel;
    private SimpleStringProperty periodSlotLabel;
    private SimpleStringProperty statusLabel;

    public registeredModuleDetailsPopupModel(String academicYearLabel, String periodSlotLabel, String courseCodeLabel, String courseNameLabel, String creditHourLabel, String occLabel, String statusLabel) {
        this.academicYearLabel = new SimpleStringProperty(academicYearLabel);
        this.periodSlotLabel = new SimpleStringProperty(periodSlotLabel);
        this.courseCodeLabel = new SimpleStringProperty(courseCodeLabel);
        this.courseNameLabel = new SimpleStringProperty(courseNameLabel);
        this.creditHourLabel = new SimpleStringProperty(creditHourLabel);
        this.occLabel = new SimpleStringProperty(occLabel);
        this.statusLabel = new SimpleStringProperty(statusLabel);
    }

    public String getAcademicYearLabel() {
        return academicYearLabel.get();
    }

    public String getCourseCodeLabel() {
        return courseCodeLabel.get();
    }

    public String getCourseNameLabel() {
        return courseNameLabel.get();
    }

    public String getCreditHourLabel() {
        return creditHourLabel.get();
    }

    public String getOccLabel() {
        return occLabel.get();
    }

    public String getPeriodSlotLabel() {
        return periodSlotLabel.get();
    }

    public String getStatusLabel() {
        return statusLabel.get();
    }
    
    
    
    
}
