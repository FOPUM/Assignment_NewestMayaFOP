/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Ming
 */
public class dashboard implements Initializable, ControlledScreen{
    
    private Button exit_button;
    ScreenController myController;
    
    login_controller loginControl = new login_controller();
    databaseConnection connectNow = new databaseConnection();
    Connection connectDB = connectNow.getConnection();
    
    String staffID = loginControl.getUsername();
    char accStatus = loginControl.getAccStatus();
    
    @FXML
    private BarChart<String, Number> famousModuleBarChart;

    @FXML
    private LineChart<String, Number> OccurenceLineChart;

    @FXML
    private VBox VBoxFamousTuto;

    @FXML
    private TextField courseCodeTextField;

    @FXML
    private PieChart creditHourPieChart;

    @FXML
    private Label noOfStudentLabel;
    
    @FXML private Label studentLabel;
    
    @FXML private Label mostFamousTutoLabel;
    @FXML private Label mostFamousTutoNumber;
    
    String courseCode = "WIX1001";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        courseCodeTextField.setOnKeyPressed( event -> {
//            if( event.getCode() == KeyCode.ENTER && courseCode != null) {
//                courseCode = courseCodeTextField.getText();
//                courseCode = courseCode.toUpperCase();
//                getFamousOcc();
//                famousOccBarChart();  
//            }
//        });
        courseCodeTextField.setText("WIX1001");
        getFamousOcc();
        displayFamousOccLineChart(); 
        
        getFamousModule();
        displayFamousModuleBarChart();
        
        if(accStatus == 'S'){
            getNumberOfStudentInUm();
        }else{
            getNoOfStudentUnderStaff();
        }
 
        getFamousTutor();
        mostFamousTutoLabel.setText(famousTutorName);
        mostFamousTutoNumber.setText(famousTutorStudentNo);
        
        getMostCreditHour();
        showPieChart();
        
    }
    
    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }
    
    public void exit_button(ActionEvent event) {
        //Click on exit button to exit       
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
        Platform.exit();
    } 

    private static ArrayList<String> occName = new ArrayList<String>();
    private static ArrayList<Integer> noOfStudentUnderOccName = new ArrayList<Integer>();
    public void getFamousOcc(){
        try {
            occName.clear();
            noOfStudentUnderOccName.clear();
            String MostFamousOcc = "SELECT occ.occ_name, COUNT(student_take_course.matric_num) AS noOfStudent\n" +
                                    "FROM occ\n" +
                                    "LEFT JOIN student_take_course ON occ.occ_id=student_take_course.occ_id\n" +
                                    "INNER JOIN course_occ ON course_occ.occ_id=occ.occ_id\n" +
                                    "WHERE course_occ.course_id='"+courseCode+"'";
            ResultSet queryForMostFamousOcc = connectDB.createStatement().executeQuery(MostFamousOcc);
            while(queryForMostFamousOcc.next()) {
                occName.add(queryForMostFamousOcc.getString("occ_name"));
                noOfStudentUnderOccName.add(queryForMostFamousOcc.getInt("noOfStudent"));
            }    
        } catch (SQLException e) {
            Logger.getLogger(userAccount.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }
 
    private static ArrayList<String> courseID = new ArrayList<String>();
    private static ArrayList<String> courseName = new ArrayList<String>();
    private static ArrayList<Number> noOfStudentForCourse = new ArrayList<Number>();
    public void getFamousModule(){
        try {
            courseID.clear();
            courseName.clear();
            noOfStudentForCourse.clear();
            String MostFamousModule = "SELECT * FROM \n" +
                                    "(SELECT course.course_id AS courseID, course.course_name AS courseName, \n" +
                                    "COUNT(student_take_course.course_id) AS numOfStudent\n" +
                                    "FROM course\n" +
                                    "LEFT JOIN student_take_course ON student_take_course.course_id=course.course_id\n" +
                                    "WHERE student_take_course.course_status='Y'\n" +
                                    "GROUP BY course.course_id) AS numstud\n" +
                                    "ORDER BY numOfStudent\n" +
                                    "LIMIT 10";
            ResultSet queryForMostFamousModule = connectDB.createStatement().executeQuery(MostFamousModule);
            while(queryForMostFamousModule.next()) {
                courseID.add(queryForMostFamousModule.getString("courseID"));
                courseName.add(queryForMostFamousModule.getString("courseName"));
                noOfStudentForCourse.add(queryForMostFamousModule.getInt("numOfStudent"));
            }    
        } catch (SQLException e) {
            Logger.getLogger(userAccount.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }
    
//    private static ArrayList<String> noOfStudentUnderLecturer = new ArrayList<String>();
    String noOfStudentUnderLecturer = null;
    public void getNoOfStudentUnderStaff(){
        try {
            String totalNumberOfStudent = "SELECT COUNT(student_take_course.matric_num) AS noOfStudentUnderYou\n" +
                                        "FROM student_take_course\n" +
                                        "INNER JOIN staff_teach_course ON staff_teach_course.course_id=student_take_course.course_id\n" +
                                        "INNER JOIN staff ON staff.staff_id=staff_teach_course.staff_id\n" +
                                        "WHERE staff.staff_id='"+staffID+"'";
            
            ResultSet queryForTotalNoOfStudent = connectDB.createStatement().executeQuery(totalNumberOfStudent);
            while(queryForTotalNoOfStudent.next()) {
//                noOfStudentUnderLecturer.add(queryForNoOfStudentUnderYou.getString("noOfStudentUnderYou"));
                noOfStudentUnderLecturer = queryForTotalNoOfStudent.getString("noOfStudentUnderYou");
            }    
            noOfStudentLabel.setText(noOfStudentUnderLecturer);
        } catch (SQLException e) {
            Logger.getLogger(userAccount.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }
    
    String NumberOfStudentInUm = null;
    public void getNumberOfStudentInUm(){
        try {
            String NoOfStudentUnderYou = "SELECT COUNT(matric_num) FROM student";
            ResultSet queryForNoOfStudentUnderYou = connectDB.createStatement().executeQuery(NoOfStudentUnderYou);
            while(queryForNoOfStudentUnderYou.next()) {
//                noOfStudentUnderLecturer.add(queryForNoOfStudentUnderYou.getString("noOfStudentUnderYou"));
                NumberOfStudentInUm = queryForNoOfStudentUnderYou.getString("COUNT(matric_num)");
            }
            noOfStudentLabel.setText(NumberOfStudentInUm);
            studentLabel.setText("students in UM");
        } catch (SQLException e) {
            Logger.getLogger(userAccount.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }
    
    String famousTutorName = null;
    String famousTutorStudentNo = null;
    public void getFamousTutor(){
        try {
            String famousTutor = "SELECT staff.staff_name, COUNT(student_take_course.matric_num) AS noOfStud\n" +
                                "FROM student_take_course\n" +
                                "INNER JOIN staff_teach_course ON staff_teach_course.course_id=student_take_course.course_id\n" +
                                "INNER JOIN staff ON staff.staff_id=staff_teach_course.staff_id";
            ResultSet queryForFamousTutor = connectDB.createStatement().executeQuery(famousTutor);
            while(queryForFamousTutor.next()) {
                famousTutorName = queryForFamousTutor.getString("staff_name");
                famousTutorStudentNo = queryForFamousTutor.getString("noOfStud");
            }    
        } catch (SQLException e) {
            Logger.getLogger(userAccount.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }
    
    private static ArrayList<String> creditHourName= new ArrayList<String>();
    private static ArrayList<String> creditHourOcc = new ArrayList<String>();
    ObservableList<Data> pieData = FXCollections.observableArrayList();
    public void getMostCreditHour(){
        try {
            creditHourName.clear();
            creditHourOcc.clear();
            String creditHour = "SELECT credit_hour, COUNT(credit_hour) AS occurence\n" +
                                "FROM student\n" +
                                "GROUP BY credit_hour";
            ResultSet queryForMostCreditHour = connectDB.createStatement().executeQuery(creditHour);
            while(queryForMostCreditHour.next()) {
                creditHourName.add(queryForMostCreditHour.getString("credit_hour"));
                creditHourOcc.add(queryForMostCreditHour.getString("occurence"));
            }    
        } catch (SQLException e) {
            Logger.getLogger(userAccount.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }
    
    public void displayFamousOccLineChart(){
        OccurenceLineChart.getData().clear();
        XYChart.Series<String, Number> lineData = new XYChart.Series<String, Number>();
        for (int i = 0; i < occName.size(); i++) {
            lineData.getData().add(new XYChart.Data<String, Number>(occName.get(i),noOfStudentUnderOccName.get(i)));
        }
        OccurenceLineChart.getData().add(lineData);
        OccurenceLineChart.setLegendVisible(false);
        
    }
    
    public void searchButton(ActionEvent event){
        courseCode = courseCodeTextField.getText();
        courseCode = courseCode.toUpperCase();
        getFamousOcc();
        displayFamousOccLineChart();  
    }
    
    public void displayFamousModuleBarChart(){
        famousModuleBarChart.getData().clear();
        XYChart.Series<String, Number> barData = new XYChart.Series<String, Number>();
        for (int i = 0; i < courseID.size(); i++) {
            barData.getData().add(new XYChart.Data<String, Number>(courseID.get(i),noOfStudentForCourse.get(i)));
        }
        famousModuleBarChart.getData().add(barData);
        famousModuleBarChart.setLegendVisible(false);
        
    }

    public void showPieChart(){
        creditHourPieChart.getData().clear();
        pieData.clear();
        for (int i = 0; i < creditHourName.size(); i++) {
            pieData.add(new Data(creditHourName.get(i), Integer.parseInt(creditHourOcc.get(i))));
        }
        creditHourPieChart.setData(pieData);
    }
}
