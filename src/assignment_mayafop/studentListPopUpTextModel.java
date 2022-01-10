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
public class studentListPopUpTextModel {

    private SimpleStringProperty matricLabel;
    private SimpleStringProperty nameLabel;
    private SimpleStringProperty siswamailLabel;
    private SimpleStringProperty batchLabel;
    private SimpleStringProperty facultyLabel;
    private SimpleStringProperty muetLabel;
    private SimpleStringProperty programmeLabel;
    private SimpleStringProperty genderLabel;
    private SimpleStringProperty raceLabel;
    private SimpleStringProperty birthDateLabel;
    private SimpleStringProperty yearLabel;
    private SimpleStringProperty semLabel;
    private SimpleStringProperty nationalityLabel;
    private SimpleStringProperty specialisationLabel;
    private SimpleStringProperty icLabel;
    private SimpleStringProperty creditLabel;
    private SimpleStringProperty statusLabel;

    public studentListPopUpTextModel(String matricLabel, String nameLabel, String siswamailLabel, String batchLabel, String facultyLabel, String muetLabel, String programmeLabel, String genderLabel, String raceLabel, String birthDateLabel, String yearLabel, String semLabel, String nationalityLabel, String specialisationLabel, String icLabel, String creditLabel, String statusLabel) {
        this.matricLabel = new SimpleStringProperty(matricLabel);
        this.nameLabel = new SimpleStringProperty(nameLabel);
        this.siswamailLabel = new SimpleStringProperty(siswamailLabel);
        this.batchLabel = new SimpleStringProperty(batchLabel);
        this.facultyLabel = new SimpleStringProperty(facultyLabel);
        this.muetLabel = new SimpleStringProperty(muetLabel);
        this.programmeLabel = new SimpleStringProperty(programmeLabel);
        this.genderLabel = new SimpleStringProperty(genderLabel);
        this.raceLabel = new SimpleStringProperty(raceLabel);
        this.birthDateLabel = new SimpleStringProperty(birthDateLabel);
        this.yearLabel = new SimpleStringProperty(yearLabel);
        this.semLabel = new SimpleStringProperty(semLabel);
        this.nationalityLabel = new SimpleStringProperty(nationalityLabel);
        this.specialisationLabel = new SimpleStringProperty(specialisationLabel);
        this.icLabel = new SimpleStringProperty(icLabel);
        this.creditLabel = new SimpleStringProperty(creditLabel);
        this.statusLabel = new SimpleStringProperty(statusLabel);
    }

    public String getMatricLabel() {
        return matricLabel.get();
    }

    public String getNameLabel() {
        return nameLabel.get();
    }

    public String getSiswamailLabel() {
        return siswamailLabel.get();
    }

    public String getBatchLabel() {
        return batchLabel.get();
    }

    public String getFacultyLabel() {
        return facultyLabel.get();
    }

    public String getMuetLabel() {
        return muetLabel.get();
    }

    public String getProgrammeLabel() {
        return programmeLabel.get();
    }

    public String getGenderLabel() {
        return genderLabel.get();
    }

    public String getRaceLabel() {
        return raceLabel.get();
    }

    public String getYearLabel() {
        return yearLabel.get();
    }

    public String getSemLabel() {
        return semLabel.get();
    }

    public String getNationalityLabel() {
        return nationalityLabel.get();
    }

    public String getSpecialisationLabel() {
        return specialisationLabel.get();
    }

    public String getIcLabel() {
        return icLabel.get();
    }

    public String getCreditLabel() {
        return creditLabel.get();
    }

    public String getStatusLabel() {
        return statusLabel.get();
    }

    public String getBirthDateLabel() {
        return birthDateLabel.get();
    }
    
    
}
