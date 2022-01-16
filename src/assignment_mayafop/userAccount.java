/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import javafxModel.registeredModuleDetailsTextModel;
import javafxModel.studentListTextModel;
import javafxModel.staffListTextModel;
import javafxModel.registeredStudentDetailsTextModel;
import java.io.IOException;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Ming
 */
public class userAccount implements Initializable, ControlledScreen{
    
    ScreenController myController;
    login_controller loginControl = new login_controller();
    MiscFunc misc = new MiscFunc();
    
    @FXML
    private Button exit_button;
    @FXML
    private Button studentButton;
    @FXML
    private Button staffButton;
    @FXML
    private BorderPane userScreen;
    @FXML
    private VBox vContainerRegisteredModule;
    @FXML
    private Label academicYearLabel;
    @FXML
    private Label enrollmentStatusLabel;
    @FXML
    private Label matricIDLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label programmeLabel;
    @FXML
    private Label specialisationLabel;
    @FXML
    private Label registeredCourseLabel;
    @FXML
    private Label staffIDLabel;
    @FXML
    private Label staffNameLabel;
    @FXML
    private Label UMMailLabel;
    @FXML
    private Line line;
    @FXML
    private Rectangle staffRect;
    @FXML
    private HBox studentHBox;
    @FXML
    private HBox staffHBox;
    @FXML
    private Rectangle studentRect;
    @FXML
    private Label staffTitleLabel;
    @FXML
    private Rectangle staffRectangle;
    @FXML
    private VBox vContainerStaff;
    @FXML
    private Label studentTitleLabel;
    @FXML
    private Rectangle studentRectangle;
    @FXML
    private VBox vContainerStudent;
   
    
    boolean showing;
    
    String matric_num = loginControl.getUsername();
    char accStatus = loginControl.getAccStatus();
    
    databaseConnection connectNow = new databaseConnection();
    Connection connectDB = connectNow.getConnection();
 
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(accStatus == 'S'){
            getUserDetailsStudent();
            getCourseDetailsStudent();
            insertModuleDetailsStudent();
            registeredCourseLabel.setText("Registered Courses");
            staffHBox.setVisible(false);
            staffRect.setVisible(false);
            line.setVisible(true);
            
            staffTitleLabel.setVisible(false);
            studentRectangle.setVisible(false);
            vContainerStaff.setVisible(false);
            staffButton.setVisible(false);
            
            studentTitleLabel.setVisible(false);
            staffRectangle.setVisible(false);
            vContainerStudent.setVisible(false);
            studentButton.setVisible(false);
            
        }else if(accStatus == 'T'){
            getUserDetailsStaff();
            getRegisteredStudentDetailsStaff();
            insertCourseDetailsStaff();
            registeredCourseLabel.setText("Registered Students");
            studentHBox.setVisible(false);
            studentRect.setVisible(false);
            line.setVisible(false); 
            
            staffTitleLabel.setVisible(false);
            staffRectangle.setVisible(false);
            vContainerStaff.setVisible(false);
            staffButton.setVisible(false);
            
            studentTitleLabel.setVisible(false);
            studentRectangle.setVisible(false);
            vContainerStudent.setVisible(false);
            studentButton.setVisible(false);
            
        }else if(accStatus == 'A'){
            getUserDetailsStaff();
            getRegisteredStudentDetailsStaff();
            insertCourseDetailsStaff();
            registeredCourseLabel.setText("Registered Students");
            getStudents();
            insertStudents();
            String staffQueryText="SELECT staff_id AS staffID, staff_email AS umMail, staff_name AS staffName FROM staff WHERE staff_id!='NONE' LIMIT 7";
            getStaffs(staffQueryText);
            insertStaffs("/Assignment_MayaFOP/staffListText.fxml");
            studentHBox.setVisible(false);
            studentRect.setVisible(false);
            line.setVisible(false); 
            
            staffTitleLabel.setVisible(true);
            staffRectangle.setVisible(true);
            vContainerStaff.setVisible(true);
            staffButton.setVisible(true);
            
            studentTitleLabel.setVisible(true);
            studentRectangle.setVisible(true);
            vContainerStudent.setVisible(true);
            studentButton.setVisible(true);
        }
        
        myController = new ScreenController();
        showing = myController.getShowing();
    }

    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }

    public void goToRegisteredModule(ActionEvent event){
        myController = new ScreenController();
        
        if(accStatus == 'S'){
            if (!showing) {
                myController.showPopupStage(userScreen, "/assignment_MayaFOP/registeredModule.fxml");
                showing = myController.getShowing();
                System.out.println("Hello bruh");
                getCourseDetailsStudent();
                insertModuleDetailsStudent();
            }  
        }else{
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Assignment_MayaFOP/registeredStudentDetailsPopup.fxml"));
                loader.load();
                registeredStudentDetailsPopupController SDPController = loader.getController();
                myController.showPopupStage(userScreen, "/assignment_MayaFOP/registeredStudent.fxml");
                SDPController.resetMemory();
            } catch (Exception e) {
                System.out.println(e);
            }   
        }
    }  
    
    public void goToStudent(){
        if (!showing) {
            myController.showPopupStage(userScreen, "/assignment_MayaFOP/studentListPopUp.fxml");
            showing = myController.getShowing();
        }
    }
    
    public void goToStaff(){
        if (!showing) {
            myController.showPopupStage(userScreen, "/assignment_MayaFOP/staffListPopUp.fxml");
            showing = myController.getShowing();
        }
    }
    
    public void exitButton(ActionEvent event) {
        //Click on exit button to exit       
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    } 

    
    //Students part
    private static ArrayList<String> courseID = new ArrayList<String>();
    private static ArrayList<String> courseName = new ArrayList<String>();
    List<registeredModuleDetailsTextModel> moduleDetails = new ArrayList<>();
    
    public void getUserDetailsStudent(){
        try {
            String UserDetails="SELECT student_name, matric_num, student_programme, student_specialisation, student_studyyear, enrolled_status\n" +
                            "FROM student\n" +
                            "WHERE matric_num='"+matric_num+"'";
            ResultSet queryResultForCheck = connectDB.createStatement().executeQuery(UserDetails);
            while(queryResultForCheck.next()) {

                nameLabel.setText(misc.upperLetter(queryResultForCheck.getString("student_name")));
                
                matricIDLabel.setText(queryResultForCheck.getString("matric_num").toUpperCase());              
                
                String studentProgrammeTemp = queryResultForCheck.getString("student_programme");

                if (studentProgrammeTemp.equals("SE")) {
                    programmeLabel.setText("Software Engineering");
                } else if (studentProgrammeTemp.equals("AI")) {
                    programmeLabel.setText("Artificial Intelligence");
                }else if (studentProgrammeTemp.equals("DS")) {
                    programmeLabel.setText("Data Science");
                }else if (studentProgrammeTemp.equals("CSN")) {
                    programmeLabel.setText("Computer System and Networking");
                }else if (studentProgrammeTemp.equals("IS")) {
                    programmeLabel.setText("Information System");
                }else if (studentProgrammeTemp.equals("MM")) {
                    programmeLabel.setText("Multimedia");
                }
                
                if(queryResultForCheck.getString("student_specialisation") == null){
                    specialisationLabel.setText("-");
                }
  
                academicYearLabel.setText(queryResultForCheck.getString("student_studyyear"));

                if (queryResultForCheck.getString("enrolled_status").equals("Y")) {
                    enrollmentStatusLabel.setText("Enrolled");
                } else if (queryResultForCheck.getString("enrolled_status").equals("N")) {
                    enrollmentStatusLabel.setText("Not enrolled");
                }
                
            }
            
        } catch (SQLException e) {
            Logger.getLogger(userAccount.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    public void getCourseDetailsStudent(){
        courseID.clear();
        courseName.clear();
        try {
            String registeredModuleCourseIDQuery="SELECT course_id FROM\n" +
                                "student_take_course WHERE matric_num='"+matric_num+"' AND course_status='Y'";
            
            ResultSet courseIDQuery = connectDB.createStatement().executeQuery(registeredModuleCourseIDQuery);
            while(courseIDQuery.next()) {
                String courseid = courseIDQuery.getString("course_id");
                courseID.add(courseid);
            }
            
            
            for (int j = 0; j < courseID.size(); j++) {
                String courseIDFromArray = courseID.get(j);
                String registeredModuleCourseNameQuery="SELECT course_name FROM course WHERE course_id='"+courseIDFromArray+"'";
                
                ResultSet courseNameQuery = connectDB.createStatement().executeQuery(registeredModuleCourseNameQuery);
                while(courseNameQuery.next()) {
                    String coursename = courseNameQuery.getString("course_name");
                    courseName.add(coursename);
                }
            }    
        } catch (SQLException e) {
            Logger.getLogger(userAccount.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }
    
        public void insertModuleDetailsStudent(){
        try {
            moduleDetails.clear();
            vContainerRegisteredModule.getChildren().clear();
            System.out.println("Has been cleared");
            for (int j = 0; j < courseID.size(); j++) {
                moduleDetails.add(new registeredModuleDetailsTextModel(courseID.get(j),misc.upperLetter(courseName.get(j))));
            }
            Node[] nodes = new Node[moduleDetails.size()];
            
            for (int j = 0; j < nodes.length; j++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Assignment_MayaFOP/registeredModuleDetailsText.fxml"));
                nodes[j] = loader.load();
                
//                final int h = j;
                
                registeredModuleDetailsTextController detailsController = loader.getController();
                //customise content
                detailsController.setContentInfo(moduleDetails.get(j).getCourseCodeDetailsLabel(),moduleDetails.get(j).getCourseNameDetailsLabel());               
                
                vContainerRegisteredModule.getChildren().add(nodes[j]);
            }


        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(moduleConfirmationMessageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
       
        
    //Staff part
    private static ArrayList<String> courseIDStaff = new ArrayList<String>();
    private static ArrayList<String> courseNameStaff = new ArrayList<String>();
    private static ArrayList<String> courseOccStaff = new ArrayList<String>();
    private static ArrayList<String> courseCapacity = new ArrayList<String>();
    List<registeredStudentDetailsTextModel> registeredStudentDetails = new ArrayList<>();
    
    void getUserDetailsStaff(){
        try {
            String UserDetails="SELECT staff_id, staff_email, staff_name\n" +
                            "FROM staff\n" +
                            "WHERE staff_id='"+matric_num+"'";
            ResultSet queryResultForCheck = connectDB.createStatement().executeQuery(UserDetails);
            while(queryResultForCheck.next()) {

                staffNameLabel.setText(misc.upperLetter(queryResultForCheck.getString("staff_name")));
                staffIDLabel.setText(queryResultForCheck.getString("staff_id").toUpperCase());              
                UMMailLabel.setText(queryResultForCheck.getString("staff_email"));
            }    
        } catch (SQLException e) {
            Logger.getLogger(userAccount.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    public void getRegisteredStudentDetailsStaff(){
        courseIDStaff.clear();
        courseNameStaff.clear();
        courseOccStaff.clear();
        courseCapacity.clear();

        String currentCapacity="SELECT occ.occ_id, course.course_id,course.course_name,occ.occ_name,\n" +
                                "currentCapacity, \n" +
                                "occ.occ_capacity\n" +
                                "\n" +
                                "FROM staff\n" +
                                "INNER JOIN staff_teach_lecture ON staff_teach_lecture.staff_id=staff.staff_id\n" +
                                "INNER JOIN occ ON occ.lecture_id=staff_teach_lecture.lecture_id\n" +
                                "INNER JOIN course_occ ON course_occ.occ_id=occ.occ_id\n" +
                                "INNER JOIN course ON course_occ.course_id=course.course_id\n" +
                                "LEFT JOIN \n" +
                                "(SELECT occ_id, count(occ_id) AS currentCapacity\n" +
                                "FROM student_take_course\n" +
                                "WHERE course_status='Y'\n" +
                                "GROUP BY occ_id\n" +
                                ") AS currentCa ON currentCa.occ_id=occ.occ_id\n" +
                                "WHERE staff.staff_id='"+matric_num+"'";
        try {
            ResultSet courseIDQuery = connectDB.createStatement().executeQuery(currentCapacity);
            while(courseIDQuery.next()) {
                courseIDStaff.add(courseIDQuery.getString("course_id"));
                courseNameStaff.add(courseIDQuery.getString("course_name"));
                courseOccStaff.add(courseIDQuery.getString("occ_name"));
                String currentcapacity = courseIDQuery.getString("currentCapacity");
                if(courseIDQuery.getString("currentCapacity") == null){
                    currentcapacity = "0";
                }
                String capacity = currentcapacity + "/" +courseIDQuery.getString("occ_capacity");
                courseCapacity.add(capacity);
            }
            
        } catch (SQLException e) {
            Logger.getLogger(userAccount.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }
       
    public void insertCourseDetailsStaff(){
        try {
            registeredStudentDetails.clear();
            for (int j = 0; j < courseCapacity.size(); j++) {
                registeredStudentDetails.add(new registeredStudentDetailsTextModel(courseIDStaff.get(j),misc.upperLetter(courseNameStaff.get(j)), courseOccStaff.get(j), courseCapacity.get(j)));
            }
            Node[] nodes = new Node[registeredStudentDetails.size()];
            
            for (int j = 0; j < nodes.length; j++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Assignment_MayaFOP/registeredStudentDetailsText.fxml"));
                nodes[j] = loader.load();
                
                final int h = j;
                
                registeredStudentDetailsTextController studentDetailsController = loader.getController();
                //customise content
                studentDetailsController.setContentInfo(registeredStudentDetails.get(j).getCourseCodeLabel(),registeredStudentDetails.get(j).getCourseNameLabel(),registeredStudentDetails.get(j).getCourseOccLabel(),registeredStudentDetails.get(j).getCourseCapacityLabel());               
                
                vContainerRegisteredModule.getChildren().add(nodes[j]);
            }


        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(moduleConfirmationMessageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    //Admin part
    private static ArrayList<String> matric = new ArrayList<String>();
    private static ArrayList<String> name = new ArrayList<String>();
    private static ArrayList<String> faculty = new ArrayList<String>();
    List<studentListTextModel> studentDetails = new ArrayList<>();
    
    public void getStudents(){
        matric.clear();
        name.clear();
        faculty.clear();
        String studentQueryText="SELECT matric_num AS matricNo, student_name AS studentName, student_faculty AS studentFaculty FROM student LIMIT 7";
        try {
            ResultSet staffQuery = connectDB.createStatement().executeQuery(studentQueryText);
            while(staffQuery.next()) {
                matric.add(staffQuery.getString("matricNo"));
                name.add(staffQuery.getString("studentName"));
                faculty.add(staffQuery.getString("studentFaculty"));
            }
        } catch (SQLException e) {
            Logger.getLogger(userAccount.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

    }
    
    public void insertStudents(){
        try {
            studentDetails.clear();
            for (int j = 0; j < matric.size(); j++) {
                studentDetails.add(new studentListTextModel(matric.get(j),name.get(j), faculty.get(j)));
            }
            Node[] nodes = new Node[studentDetails.size()];
            
            for (int j = 0; j < nodes.length; j++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Assignment_MayaFOP/studentListText.fxml"));
                nodes[j] = loader.load();
                
                final int h = j;
                
                studentListTextController student = loader.getController();
                //customise content
                student.setContentInfo(studentDetails.get(j).getMatricLabel(), studentDetails.get(j).getStudentLabel(), studentDetails.get(j).getFacultyLabel());               
                
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
    
    //Admin part
    private static ArrayList<String> staffid = new ArrayList<String>();
    private static ArrayList<String> staffname = new ArrayList<String>();
    private static ArrayList<String> ummail = new ArrayList<String>();
    List<staffListTextModel> staffDetails = new ArrayList<>();
    
    public void getStaffs(String query){
        staffid.clear();
        ummail.clear();
        staffname.clear();
        try {
            ResultSet staffQuery = connectDB.createStatement().executeQuery(query);
            while(staffQuery.next()) {
                if(staffid.equals("NONE")){
                    
                }else{
                    staffid.add(staffQuery.getString("staffID"));
                    ummail.add(staffQuery.getString("umMail"));
                    staffname.add(staffQuery.getString("staffName"));
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(userAccount.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }
    
    public void insertStaffs(String a){
        try {
            staffDetails.clear();
            for (int j = 0; j < staffid.size(); j++) {
                staffDetails.add(new staffListTextModel(staffid.get(j),staffname.get(j), ummail.get(j)));
            }
            Node[] nodes = new Node[staffDetails.size()];
            
            for (int j = 0; j < nodes.length; j++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(a));
                nodes[j] = loader.load();
                
                final int h = j;
                
                staffListTextController staff = loader.getController();
                //customise content
                staff.setContentInfo(staffDetails.get(j).getStaffIdLabel(), staffDetails.get(j).getStaffNameLabel(), staffDetails.get(j).getUmMailLabel());               
                
                vContainerStaff.getChildren().add(nodes[j]);
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
