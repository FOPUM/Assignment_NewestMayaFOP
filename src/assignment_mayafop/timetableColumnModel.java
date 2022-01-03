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
public class timetableColumnModel {
    private SimpleStringProperty courseCodeLabel;
    private SimpleStringProperty courseNameLabel;    
    
    public timetableColumnModel(String courseCodeLabel, String courseNameLabel) {
        this.courseCodeLabel = new SimpleStringProperty(courseCodeLabel);
        this.courseNameLabel = new SimpleStringProperty(courseNameLabel);
//        this.courseMode = new SimpleStringProperty(courseMode);
    }
    
    public String getCourseCodeLabel() {
        return courseCodeLabel.get();
    }

    public String getCourseNameLabel() {
        return courseNameLabel.get();
    }
    
//    public String getCourseNameLabel() {
//        return courseNameLabel.get();
//    }
}
