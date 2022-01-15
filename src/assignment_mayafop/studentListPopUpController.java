/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import javafxModel.registeredModuleDetailsPopupModel;
import javafxModel.studentListPopUpTextModel;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 *
 * @author Ming
 */
public class studentListPopUpController implements Initializable, ControlledScreen{
    ScreenController myController;
    animation Animation;
    
    @FXML
    private Button exit_button;
    @FXML
    private VBox vContainerStudent;
    
    databaseConnection connectNow = new databaseConnection();
    Connection connectDB = connectNow.getConnection();
    
    private static ArrayList<String> enrollmentStatus = new ArrayList<String>();
    List<registeredModuleDetailsPopupModel> moduleDetails = new ArrayList<>();
    
    @FXML
    private ScrollPane studentPane;
    
    boolean upScreenStatus = false;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        Animation = new animation();
        if(!upScreenStatus){
            Animation.fading(studentPane);
        }
        getStudents();
        insertStudents();
    }
    
    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }
    
    public void exitButton(ActionEvent event) {
        //Click on exit button to exit       
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    } 
    
        //Admin part
    private static ArrayList<String> matric = new ArrayList<String>();
    private static ArrayList<String> siswa = new ArrayList<String>();
    private static ArrayList<String> name = new ArrayList<String>();
    private static ArrayList<String> batch = new ArrayList<String>();
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static ArrayList<String> muet = new ArrayList<String>();
    private static ArrayList<String> programme = new ArrayList<String>();
    private static ArrayList<String> gender = new ArrayList<String>();
    private static ArrayList<String> race = new ArrayList<String>();
    private static ArrayList<String> date = new ArrayList<String>();
    private static ArrayList<String> year = new ArrayList<String>();
    private static ArrayList<String> sem = new ArrayList<String>();
    private static ArrayList<String> nationality = new ArrayList<String>();
    private static ArrayList<String> specialisation = new ArrayList<String>();
    private static ArrayList<String> ic = new ArrayList<String>();
    private static ArrayList<String> credit = new ArrayList<String>();
    private static ArrayList<String> enrollstatus = new ArrayList<String>();
    List<studentListPopUpTextModel> fullStudentDetails = new ArrayList<>();
    
    public void getStudents(){
        matric.clear();
        siswa.clear();
        name.clear();
        batch.clear();
        faculty.clear();
        muet.clear();
        programme.clear();
        gender.clear();
        race.clear();
        date.clear();
        year.clear();
        sem.clear();
        nationality.clear();
        specialisation.clear();
        ic.clear();
        credit.clear();
        enrollstatus.clear();
        String staffQueryText="SELECT \n" +
                                "matric_num AS matricNo, siswamail AS siswaMail, student_name AS studentName, student_batch AS studentBatch,\n" +
                                "student_faculty AS studentFaculty, student_muet_band AS MUET, student_programme AS studentProgramme, \n" +
                                "student_gender AS Gender, student_race AS Race, student_date_of_birth AS dateOfBirth, student_studyyear AS studyYear, \n" +
                                "student_studysem AS studySem, student_nationality AS nationality, student_specialisation AS specialisation, \n" +
                                "student_ic_passport AS ic, credit_hour AS creditHour, enrolled_status AS enrolledStatus\n" +
                                "FROM student";
        try {
            ResultSet staffQuery = connectDB.createStatement().executeQuery(staffQueryText);
            while(staffQuery.next()) {
                matric.add(staffQuery.getString("matricNo"));
                siswa.add(staffQuery.getString("siswaMail"));
                name.add(staffQuery.getString("studentName"));
                batch.add(staffQuery.getString("studentBatch"));
                faculty.add(staffQuery.getString("studentFaculty"));
                muet.add(staffQuery.getString("MUET"));
                programme.add(staffQuery.getString("studentProgramme"));
                gender.add(staffQuery.getString("Gender"));
                race.add(staffQuery.getString("Race"));
                date.add(staffQuery.getString("dateOfBirth"));
                year.add(staffQuery.getString("studyYear"));
                sem.add(staffQuery.getString("studySem"));
                nationality.add(staffQuery.getString("nationality"));
                specialisation.add(staffQuery.getString("specialisation"));
                ic.add(staffQuery.getString("ic"));
                credit.add(staffQuery.getString("creditHour"));
                enrollstatus.add(staffQuery.getString("enrolledStatus"));
            }
        } catch (SQLException e) {
            Logger.getLogger(userAccount.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

    }
    
    public void insertStudents(){
        try {
            fullStudentDetails.clear();
            for (int j = 0; j < matric.size(); j++) {
                fullStudentDetails.add(new studentListPopUpTextModel(   matric.get(j),siswa.get(j), name.get(j), batch.get(j),
                                                                        faculty.get(j), muet.get(j), programme.get(j),gender.get(j), 
                                                                        race.get(j), date.get(j),year.get(j), sem.get(j),
                                                                        nationality.get(j),specialisation.get(j), ic.get(j), credit.get(j), enrollstatus.get(j)));
            }
            Node[] nodes = new Node[fullStudentDetails.size()];
            
            for (int j = 0; j < nodes.length; j++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Assignment_MayaFOP/studentListPopUpText.fxml"));
                nodes[j] = loader.load();
                
                final int h = j;
                
                studentListPopUpTextController student = loader.getController();
                //customise content
                student.setContentInfo( fullStudentDetails.get(j).getMatricLabel(), fullStudentDetails.get(j).getSiswamailLabel(), fullStudentDetails.get(j).getNameLabel(),
                                        fullStudentDetails.get(j).getBatchLabel(), fullStudentDetails.get(j).getFacultyLabel(), fullStudentDetails.get(j).getMuetLabel(),
                                        fullStudentDetails.get(j).getProgrammeLabel(), fullStudentDetails.get(j).getGenderLabel(), fullStudentDetails.get(j).getRaceLabel(),
                                        fullStudentDetails.get(j).getBirthDateLabel(), fullStudentDetails.get(j).getYearLabel(), fullStudentDetails.get(j).getSemLabel(),
                                        fullStudentDetails.get(j).getNationalityLabel(), fullStudentDetails.get(j).getSpecialisationLabel(), fullStudentDetails.get(j).getIcLabel(),
                                        fullStudentDetails.get(j).getCreditLabel(), fullStudentDetails.get(j).getStatusLabel());               
                
                vContainerStudent.getChildren().add(nodes[j]);
            }
        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(moduleConfirmationMessageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
