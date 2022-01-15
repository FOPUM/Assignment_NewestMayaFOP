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
public class studentListTextModel {
    private SimpleStringProperty matricLabel;
    private SimpleStringProperty studentLabel;
    private SimpleStringProperty facultyLabel;

    public studentListTextModel(String matricLabel, String studentLabel, String facultyLabel) {
        this.matricLabel = new SimpleStringProperty(matricLabel);
        this.studentLabel = new SimpleStringProperty(studentLabel);
        this.facultyLabel = new SimpleStringProperty(facultyLabel);
    }

    public String getMatricLabel() {
        return matricLabel.get();
    }

    public String getStudentLabel() {
        return studentLabel.get();
    }

    public String getFacultyLabel() {
        return facultyLabel.get();
    }
    
    
}
