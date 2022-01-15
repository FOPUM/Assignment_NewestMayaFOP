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
public class ModuleDetailsModel {
    
    private SimpleStringProperty lectDayLabel;
    private SimpleStringProperty lectEndTimeLabel;
    private SimpleStringProperty lectIDLabel;
    private SimpleStringProperty lectStartTimeLabel;
    private SimpleStringProperty occLabel;
    private SimpleStringProperty capacityLabel;
    private SimpleStringProperty tutoDayLabel;
    private SimpleStringProperty tutoEndTimeLabel;
    private SimpleStringProperty tutoIDLabel;
    private SimpleStringProperty tutoStartTimeLabel;
    private SimpleStringProperty labDayLabel;
    private SimpleStringProperty labEndTimeLabel;
    private SimpleStringProperty labIDLabel;
    private SimpleStringProperty labStartTimeLabel;
    private SimpleStringProperty tutoLocationLabel;
    private SimpleStringProperty lectLocationLabel;
    private SimpleStringProperty labLocationLabel;
    private SimpleStringProperty lectStaffIDLabel;
    private SimpleStringProperty lectStaffNameLabel;
    private SimpleStringProperty tutoStaffIDLabel;
    private SimpleStringProperty tutoStaffNameLabel;
    private SimpleStringProperty labStaffIDLabel;
    private SimpleStringProperty labStaffNameLabel;
    

    public ModuleDetailsModel(String occLabel, String capacityLabel, 
                                String lectIDLabel, String lectDayLabel, String lectStartTimeLabel, String lectEndTimeLabel, String lectLocationLabel, String lectStaffIDLabel, String lectStaffNameLabel,
                                String tutoIDLabel, String tutoDayLabel, String tutoStartTimeLabel, String tutoEndTimeLabel, String tutoLocationLabel, String tutoStaffIDLabel, String tutoStaffNameLabel,
                                String labIDLabel, String labDayLabel, String labStartTimeLabel, String labEndTimeLabel, String labLocationLabel, String labStaffIDLabel, String labStaffNameLabel) {
        this.lectDayLabel = new SimpleStringProperty(lectDayLabel);
        this.lectEndTimeLabel = new SimpleStringProperty(lectEndTimeLabel);
        this.lectIDLabel = new SimpleStringProperty(lectIDLabel);
        this.lectStartTimeLabel = new SimpleStringProperty(lectStartTimeLabel);
        this.occLabel = new SimpleStringProperty(occLabel);
        this.tutoDayLabel = new SimpleStringProperty(tutoDayLabel);
        this.tutoEndTimeLabel = new SimpleStringProperty(tutoEndTimeLabel);
        this.tutoIDLabel = new SimpleStringProperty(tutoIDLabel);
        this.tutoStartTimeLabel = new SimpleStringProperty(tutoStartTimeLabel);
        this.labDayLabel = new SimpleStringProperty(tutoDayLabel);
        this.labEndTimeLabel = new SimpleStringProperty(tutoEndTimeLabel);
        this.labIDLabel = new SimpleStringProperty(tutoIDLabel);
        this.labStartTimeLabel = new SimpleStringProperty(tutoStartTimeLabel);
        this.capacityLabel = new SimpleStringProperty(capacityLabel);
        this.lectLocationLabel = new SimpleStringProperty(lectLocationLabel);
        this.tutoLocationLabel = new SimpleStringProperty(tutoLocationLabel);
        this.labLocationLabel = new SimpleStringProperty(labLocationLabel);
        this.lectStaffIDLabel = new SimpleStringProperty(lectStaffIDLabel);
        this.lectStaffNameLabel = new SimpleStringProperty(lectStaffNameLabel);
        this.tutoStaffIDLabel = new SimpleStringProperty(tutoStaffIDLabel);
        this.tutoStaffNameLabel = new SimpleStringProperty(tutoStaffNameLabel);
        this.labStaffIDLabel = new SimpleStringProperty(labStaffIDLabel);
        this.labStaffNameLabel = new SimpleStringProperty(labStaffNameLabel);
        
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

    public String getLabDayLabel() {
        return labDayLabel.get();
    }

    public String getLabEndTimeLabel() {
        return labEndTimeLabel.get();
    }

    public String getLabIDLabel() {
        return labIDLabel.get();
    }

    public String getLabStartTimeLabel() {
        return labStartTimeLabel.get();
    }

    public String getCapacityLabel() {
        return capacityLabel.get();
    }

    public String getTutoLocationLabel() {
        return tutoLocationLabel.get();
    }

    public String getLectLocationLabel() {
        return lectLocationLabel.get();
    }

    public String getLabLocationLabel() {
        return labLocationLabel.get();
    }

    public String getLectStaffIDLabel() {
        return lectStaffIDLabel.get();
    }

    public String getLectStaffNameLabel() {
        return lectStaffNameLabel.get();
    }

    public String getTutoStaffIDLabel() {
        return tutoStaffIDLabel.get();
    }

    public String getTutoStaffNameLabel() {
        return tutoStaffNameLabel.get();
    }

    public String getLabStaffIDLabel() {
        return labStaffIDLabel.get();
    }

    public String getLabStaffNameLabel() {
        return labStaffNameLabel.get();
    }
    
    
}
