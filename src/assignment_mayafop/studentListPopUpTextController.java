/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Ming
 */
public class studentListPopUpTextController implements Initializable{
    MiscFunc misc = new MiscFunc();
    
    @FXML
    private Label batchLabel;

    @FXML
    private Label creditLabel;

    @FXML
    private Label facultyLabel;

    @FXML
    private Label genderLabel;

    @FXML
    private Label icLabel;

    @FXML
    private Label matricLabel;

    @FXML
    private Label muetLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label nationalityLabel;

    @FXML
    private Label programmeLabel;

    @FXML
    private Label raceLabel;

    @FXML
    private Label semLabel;

    @FXML
    private Label siswamailLabel;

    @FXML
    private Label specialisationLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private Label yearLabel;
    
    @FXML
    private Label birthDateLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        
    }

    public void setContentInfo( String matric, String name, String siswamail, String batch, 
                                String faculty, String muet, String programme, String gender, 
                                String race, String date, String year, String sem, String nationality, 
                                String specialisation, String ic, String credit, String status) {
        matricLabel.setText(matric);    
        nameLabel.setText(misc.upperLetter(name));
        siswamailLabel.setText(siswamail);   
        batchLabel.setText(batch);
        
        if (faculty.equals("FSKTM")){
            facultyLabel.setText("Faculty of Computer Science and Information Technology");                   
        }
        
        muetLabel.setText(muet);
        
        programmeLabel.setText(programme);
        if (programme.equals("SE")) {
            programmeLabel.setText("Software Engineering");
        } else if (programme.equals("AI")) {
            programmeLabel.setText("Artificial Intelligence");
        }else if (programme.equals("DS")) {
            programmeLabel.setText("Data Science");
        }else if (programme.equals("CSN")) {
            programmeLabel.setText("Computer System and Networking");
        }else if (programme.equals("IS")) {
            programmeLabel.setText("Information System");
        }else if (programme.equals("MM")) {
            programmeLabel.setText("Multimedia");
        }
        
        if(gender.equals("M")){
            genderLabel.setText("Male");
        }else{
            genderLabel.setText("Female");
        }

        raceLabel.setText(race);
        birthDateLabel.setText(date);
        yearLabel.setText(year);
        semLabel.setText(sem);
        nationalityLabel.setText(misc.upperLetter(nationality));
        specialisationLabel.setText(misc.upperLetter(specialisation));
        icLabel.setText(ic);
        creditLabel.setText(credit);
        
        if(status.equals("Y")){
            statusLabel.setText("Enrolled");
        }else if(status.equals("N") || status == null){
            statusLabel.setText("Unenrolled");
        }
        
    }
//    if (studentProgrammeTemp.equals("SE")) {
//                    programmeLabel.setText("Software Engineering");
//                } else if (studentProgrammeTemp.equals("AI")) {
//                    programmeLabel.setText("Artificial Intelligence");
//                }else if (studentProgrammeTemp.equals("DS")) {
//                    programmeLabel.setText("Data Science");
//                }else if (studentProgrammeTemp.equals("CSN")) {
//                    programmeLabel.setText("Computer System and Networking");
//                }else if (studentProgrammeTemp.equals("IS")) {
//                    programmeLabel.setText("Information System");
//                }else if (studentProgrammeTemp.equals("MM")) {
//                    programmeLabel.setText("Multimedia");
//                }
}
