/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxModel;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Ming
 */
public class registeredStudentDetailsTextModel {
    
    private SimpleStringProperty courseOccLabel;
    private SimpleStringProperty courseCapacityLabel;
    private SimpleStringProperty courseCodeLabel;
    private SimpleStringProperty courseNameLabel;

    public registeredStudentDetailsTextModel(String courseCodeLabel, String courseNameLabel, String courseOccLabel, String courseCapacityLabel) {
        this.courseOccLabel = new SimpleStringProperty(courseOccLabel);
        this.courseCapacityLabel = new SimpleStringProperty(courseCapacityLabel);
        this.courseCodeLabel = new SimpleStringProperty(courseCodeLabel);
        this.courseNameLabel = new SimpleStringProperty(courseNameLabel);
    }

    public String getCourseOccLabel() {
        return courseOccLabel.get();
    }

    public String getCourseCapacityLabel() {
        return courseCapacityLabel.get();
    }

    public String getCourseCodeLabel() {
        return courseCodeLabel.get();
    }

    public String getCourseNameLabel() {
        return courseNameLabel.get();
    }
}
