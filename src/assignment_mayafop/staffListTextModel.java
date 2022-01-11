/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Ming
 */
public class staffListTextModel {
    private SimpleStringProperty staffIdLabel;
    private SimpleStringProperty staffNameLabel;
    private SimpleStringProperty umMailLabel;
    private SimpleStringProperty courseIdLabel;
    private SimpleStringProperty courseNameIdLabel;
    private SimpleStringProperty adminLabel;
    
    public staffListTextModel(String staffIdLabel, String staffNameLabel, String umMailLabel) {
        this.staffIdLabel = new SimpleStringProperty(staffIdLabel);
        this.staffNameLabel = new SimpleStringProperty(staffNameLabel);
        this.umMailLabel = new SimpleStringProperty(umMailLabel);
    }
    
    public staffListTextModel(String staffIdLabel, String staffNameLabel, String umMailLabel, String courseIdLabel, String courseNameIdLabel, String adminLabel) {
        this.staffIdLabel = new SimpleStringProperty(staffIdLabel);
        this.staffNameLabel = new SimpleStringProperty(staffNameLabel);
        this.umMailLabel = new SimpleStringProperty(umMailLabel);
        this.courseIdLabel = new SimpleStringProperty(courseIdLabel);
        this.courseNameIdLabel = new SimpleStringProperty(courseNameIdLabel);
        this.adminLabel = new SimpleStringProperty(adminLabel);
    }

    public String getStaffIdLabel() {
        return staffIdLabel.get();
    }

    public String getStaffNameLabel() {
        return staffNameLabel.get();
    }

    public String getUmMailLabel() {
        return umMailLabel.get();
    }

    public String getCourseIdLabel() {
        return courseIdLabel.get();
    }

    public String getCourseNameIdLabel() {
        return courseNameIdLabel.get();
    }

    public String getAdminLabel() {
        return adminLabel.get();
    }

    

    
}
