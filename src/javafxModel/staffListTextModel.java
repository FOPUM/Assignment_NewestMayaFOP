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
public class staffListTextModel {
    private SimpleStringProperty staffIdLabel;
    private SimpleStringProperty staffNameLabel;
    private SimpleStringProperty umMailLabel;
    
    public staffListTextModel(String staffIdLabel, String staffNameLabel, String umMailLabel) {
        this.staffIdLabel = new SimpleStringProperty(staffIdLabel);
        this.staffNameLabel = new SimpleStringProperty(staffNameLabel);
        this.umMailLabel = new SimpleStringProperty(umMailLabel);
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

}
