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
public class registeredStuentDetailsPopupTextModel {
    private SimpleStringProperty matricIDLabel;
    private SimpleStringProperty studentNameLabel;

    public registeredStuentDetailsPopupTextModel(String matricIDLabel, String studentNameLabel) {
        this.matricIDLabel = new SimpleStringProperty(matricIDLabel);
        this.studentNameLabel = new SimpleStringProperty(studentNameLabel);
    }

    public String getMatricIDLabel() {
        return matricIDLabel.get();
    }

    public String getStudentNameLabel() {
        return studentNameLabel.get();
    }

    
}
