package javafxModel;


import javafx.beans.property.SimpleStringProperty;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ming
 */
public class pickedModuleModel {
    private SimpleStringProperty courseIDLabel;

    public pickedModuleModel(String courseIDLabel) {
        this.courseIDLabel = new SimpleStringProperty(courseIDLabel);
    }

    public String getCourseIDLabel() {
        return courseIDLabel.get();
    }
    
    
}
