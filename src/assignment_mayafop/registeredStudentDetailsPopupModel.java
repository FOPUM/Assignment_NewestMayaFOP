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
public class registeredStudentDetailsPopupModel {

    private SimpleStringProperty courseCapacityLabel;
    private SimpleStringProperty courseCodeLabel;
    private SimpleStringProperty courseLocationLabel;
    private SimpleStringProperty courseModeLabel;
    private SimpleStringProperty courseNameLabel;
    private SimpleStringProperty courseOccLabel;
    private SimpleStringProperty courseTimeLabel;
    private SimpleStringProperty courseDayLabel;
    
    public registeredStudentDetailsPopupModel(String courseCodeLabel, String courseNameLabel, String courseOccLabel, String courseCapacityLabel, String courseModeLabel,String courseDayLabel, String courseTimeLabel, String courseLocationLabel) {
        this.courseCapacityLabel = new SimpleStringProperty(courseCapacityLabel);
        this.courseCodeLabel = new SimpleStringProperty(courseCodeLabel);
        this.courseLocationLabel = new SimpleStringProperty(courseLocationLabel);
        this.courseModeLabel = new SimpleStringProperty(courseModeLabel);
        this.courseNameLabel = new SimpleStringProperty(courseNameLabel);
        this.courseOccLabel = new SimpleStringProperty(courseOccLabel);
        this.courseTimeLabel = new SimpleStringProperty(courseTimeLabel);
        this.courseDayLabel = new SimpleStringProperty(courseDayLabel);
    }

    public String getCourseCapacityLabel() {
        return courseCapacityLabel.get();
    }

    public String getCourseCodeLabel() {
        return courseCodeLabel.get();
    }

    public String getCourseLocationLabel() {
        return courseLocationLabel.get();
    }

    public String getCourseModeLabel() {
        return courseModeLabel.get();
    }

    public String getCourseNameLabel() {
        return courseNameLabel.get();
    }

    public String getCourseOccLabel() {
        return courseOccLabel.get();
    }

    public String getCourseTimeLabel() {
        return courseTimeLabel.get();
    }
    public String getCourseDayLabel() {
        return courseDayLabel.get();
    }
    
}
