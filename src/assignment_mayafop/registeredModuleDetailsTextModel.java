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
public class registeredModuleDetailsTextModel {
    private SimpleStringProperty courseCodeDetailsLabel;
    private SimpleStringProperty courseNameDetailsLabel;

    public registeredModuleDetailsTextModel(String courseCodeDetailsLabel, String courseNameDetailsLabel) {
        this.courseCodeDetailsLabel = new SimpleStringProperty(courseCodeDetailsLabel);
        this.courseNameDetailsLabel = new SimpleStringProperty(courseNameDetailsLabel);
    }
    
    public String getCourseCodeDetailsLabel() {
        return courseCodeDetailsLabel.get();
    }

    public String getCourseNameDetailsLabel() {
        return courseNameDetailsLabel.get();
    }
  
    
}
