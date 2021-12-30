/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


/**
 *
 * @author Ming
 */
public class ModuleDetailsModel {
    
    private SimpleStringProperty lectDayLabel;
    private SimpleStringProperty lectEndTimeLabel;
    private SimpleStringProperty lectIDLabel;
    private SimpleStringProperty lectStartTimeLabel;
    private SimpleStringProperty occLabel;
    private SimpleStringProperty staffIDLabel;
    private SimpleStringProperty staffNameLabel;
    private SimpleStringProperty tutoDayLabel;
    private SimpleStringProperty tutoEndTimeLabel;
    private SimpleStringProperty tutoIDLabel;
    private SimpleStringProperty tutoStartTimeLabel;

    public ModuleDetailsModel(String lectDayLabel, String lectEndTimeLabel, String lectIDLabel, String lectStartTimeLabel, String occLabel, String staffIDLabel, String staffNameLabel, String tutoDayLabel, String tutoEndTimeLabel, String tutoIDLabel, String tutoStartTimeLabel) {
        this.lectDayLabel = new SimpleStringProperty(lectDayLabel);
        this.lectEndTimeLabel = new SimpleStringProperty(lectEndTimeLabel);
        this.lectIDLabel = new SimpleStringProperty(lectIDLabel);
        this.lectStartTimeLabel = new SimpleStringProperty(lectStartTimeLabel);
        this.occLabel = new SimpleStringProperty(occLabel);
        this.staffIDLabel = new SimpleStringProperty(staffIDLabel);
        this.staffNameLabel = new SimpleStringProperty(staffNameLabel);
        this.tutoDayLabel = new SimpleStringProperty(tutoDayLabel);
        this.tutoEndTimeLabel = new SimpleStringProperty(tutoEndTimeLabel);
        this.tutoIDLabel = new SimpleStringProperty(tutoIDLabel);
        this.tutoStartTimeLabel = new SimpleStringProperty(tutoStartTimeLabel);
        
    }

    public String getLectDayLabel() {
        return lectDayLabel.get();
    }

    public String getLectEndTimeLabel() {
        return lectEndTimeLabel.get();
    }

    public String getLectIDLabel() {
        return lectIDLabel.get();
    }

    public String getLectStartTimeLabel() {
        return lectStartTimeLabel.get();
    }

    public String getOccLabel() {
        return occLabel.get();
    }

    public String getStaffIDLabel() {
        return staffIDLabel.get();
    }

    public String getStaffNameLabel() {
        return staffNameLabel.get();
    }

    public String getTutoDayLabel() {
        return tutoDayLabel.get();
    }

    public String getTutoEndTimeLabel() {
        return tutoEndTimeLabel.get();
    }

    public String getTutoIDLabel() {
        return tutoIDLabel.get();
    }

    public String getTutoStartTimeLabel() {
        return tutoStartTimeLabel.get();
    }
    
    
}
