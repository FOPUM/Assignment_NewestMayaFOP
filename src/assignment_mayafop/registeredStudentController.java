/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import javafxModel.registeredStudentDetailsPopupModel;
import javafxModel.registeredStuentDetailsPopupTextModel;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Ming
 */
public class registeredStudentController implements Initializable, ControlledScreen{
    ScreenController myController;
    animation Animation;
    login_controller loginControl = new login_controller();
    MiscFunc misc = new MiscFunc();
    
    databaseConnection connectNow = new databaseConnection();
    Connection connectDB = connectNow.getConnection();

    String matric_num = loginControl.getUsername();
    char accStatus = loginControl.getAccStatus();
    
    @FXML
    private Button exit_button;

    @FXML
    private AnchorPane registeredStudentScreen;

    @FXML
    private VBox vContainersPopUpRegisteredStudent;
    
    private static ArrayList<String> occIDStaff = new ArrayList<String>();
    private static ArrayList<String> courseIDStaff = new ArrayList<String>();
    private static ArrayList<String> courseNameStaff = new ArrayList<String>();
    private static ArrayList<String> courseOccStaff = new ArrayList<String>();
    private static ArrayList<String> courseCapacity = new ArrayList<String>();
    private static ArrayList<String> courseModeStaff = new ArrayList<String>();
    private static ArrayList<String> courseDayStaff = new ArrayList<String>();
    private static ArrayList<String> courseTimeStaff = new ArrayList<String>();
    private static ArrayList<String> courseLocation = new ArrayList<String>();
    List<registeredStudentDetailsPopupModel> courseDetails = new ArrayList<>();
    
    private static ArrayList<String> matricID = new ArrayList<String>();
    private static ArrayList<String> studentName = new ArrayList<String>();
    List<registeredStuentDetailsPopupTextModel> studentDetails = new ArrayList<>();
    
    boolean upScreenStatus = false;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Animation = new animation();
        if(!upScreenStatus){
            Animation.fading(registeredStudentScreen);
        }
        getCourseDetailsStudent();
        insertCourseDetails();
        
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
    public void getCourseDetailsStudent(){
        occIDStaff.clear();
        courseIDStaff.clear();
        courseNameStaff.clear();
        courseOccStaff.clear();
        courseCapacity.clear();
        courseModeStaff.clear();
        courseTimeStaff.clear();
        courseLocation.clear();
        
        matricID.clear();
        studentName.clear();
        
        try {
            //<editor-fold defaultstate="collapsed" desc="String for staff and admin">
            String courseDetailss="SELECT * FROM \n" +
                                "(SELECT occ.occ_id, course.course_id,course.course_name,occ.occ_name,'LECTURE' AS courseMode, \n" +
                                "lecture.lecture_day AS courseDay, lecture.lecture_start_time AS startingTime, \n" +
                                "lecture.lecture_end_time AS endingTime, lecture.lecture_location AS location,\n" +
                                "currentCapacity, occ.occ_capacity AS occCapacity\n" +
                                "\n" +
                                "FROM staff\n" +
                                "INNER JOIN staff_teach_lecture ON staff_teach_lecture.staff_id=staff.staff_id\n" +
                                "INNER JOIN lecture ON lecture.lecture_id=staff_teach_lecture.lecture_id\n" +
                                "INNER JOIN occ ON occ.lecture_id=staff_teach_lecture.lecture_id\n" +
                                "INNER JOIN course_occ ON course_occ.occ_id=occ.occ_id\n" +
                                "INNER JOIN course ON course_occ.course_id=course.course_id\n" +
                                "LEFT JOIN \n" +
                                "(SELECT occ_id, count(occ_id) AS currentCapacity\n" +
                                "FROM student_take_course\n" +
                                "WHERE course_status='Y'\n" +
                                "GROUP BY occ_id\n" +
                                ") AS currentCa ON currentCa.occ_id=occ.occ_id\n" +
                                "WHERE staff.staff_id='"+matric_num+"') AS lect\n" +
                                "\n" +
                                "UNION ALL \n" +
                                "\n" +
                                "SELECT * FROM\n" +
                                "(SELECT occ.occ_id, course.course_id,course.course_name,occ.occ_name,'tutorial' AS courseMode, \n" +
                                "tutorial.tutorial_day AS courseDay, tutorial.tutorial_start_time AS startingTime, \n" +
                                "tutorial.tutorial_end_time AS endingTime, tutorial.tutorial_location AS location,\n" +
                                "currentCapacity, occ.occ_capacity AS occCapacity\n" +
                                "\n" +
                                "FROM staff\n" +
                                "INNER JOIN staff_teach_tutorial ON staff_teach_tutorial.staff_id=staff.staff_id\n" +
                                "INNER JOIN tutorial ON tutorial.tutorial_id=staff_teach_tutorial.tutorial_id\n" +
                                "INNER JOIN occ ON occ.tutorial_id=staff_teach_tutorial.tutorial_id\n" +
                                "INNER JOIN course_occ ON course_occ.occ_id=occ.occ_id\n" +
                                "INNER JOIN course ON course_occ.course_id=course.course_id\n" +
                                "LEFT JOIN \n" +
                                "(SELECT occ_id, count(occ_id) AS currentCapacity\n" +
                                "FROM student_take_course\n" +
                                "WHERE course_status='Y'\n" +
                                "GROUP BY occ_id\n" +
                                ") AS currentCa ON currentCa.occ_id=occ.occ_id\n" +
                                "WHERE staff.staff_id='"+matric_num+"') AS tuto\n" +
                                "\n" +
                                "UNION ALL \n" +
                                "\n" +
                                "SELECT * FROM \n" +
                                "(SELECT occ.occ_id, course.course_id,course.course_name,occ.occ_name,'lab' AS courseMode, \n" +
                                "lab.lab_day AS courseDay, lab.lab_start_time AS startingTime, \n" +
                                "lab.lab_end_time AS endingTime, lab.lab_location AS location,\n" +
                                "currentCapacity, occ.occ_capacity AS occCapacity\n" +
                                "\n" +
                                "FROM staff\n" +
                                "INNER JOIN staff_teach_lab ON staff_teach_lab.staff_id=staff.staff_id\n" +
                                "INNER JOIN lab ON lab.lab_id=staff_teach_lab.lab_id\n" +
                                "INNER JOIN occ ON occ.lab_id=staff_teach_lab.lab_id\n" +
                                "INNER JOIN course_occ ON course_occ.occ_id=occ.occ_id\n" +
                                "INNER JOIN course ON course_occ.course_id=course.course_id\n" +
                                "LEFT JOIN \n" +
                                "(SELECT occ_id, count(occ_id) AS currentCapacity\n" +
                                "FROM student_take_course\n" +
                                "WHERE course_status='Y'\n" +
                                "GROUP BY occ_id\n" +
                                ") AS currentCa ON currentCa.occ_id=occ.occ_id\n" +
                                "WHERE staff.staff_id='"+matric_num+"') AS labb";
            //</editor-fold>
            
            ResultSet courseIDQuery = connectDB.createStatement().executeQuery(courseDetailss);
            while(courseIDQuery.next()) {
                occIDStaff.add(courseIDQuery.getString("occ_id"));
                courseIDStaff.add(courseIDQuery.getString("course_id"));
                courseNameStaff.add(courseIDQuery.getString("course_name"));
                courseOccStaff.add(courseIDQuery.getString("occ_name"));
                
                String currentcapacity = courseIDQuery.getString("currentCapacity");
                if(courseIDQuery.getString("currentCapacity") == null){
                    currentcapacity = "0";
                }
                
                String capacity = currentcapacity +"/" + courseIDQuery.getString("occCapacity");
                courseCapacity.add(capacity);
                courseModeStaff.add(courseIDQuery.getString("courseMode"));
                courseDayStaff.add(courseIDQuery.getString("courseDay"));
                
                String time = misc.formatTime(courseIDQuery.getString("startingTime")) + "-" + misc.formatTime(courseIDQuery.getString("endingTime"));
                courseTimeStaff.add(time);
                courseLocation.add(courseIDQuery.getString("location"));
            }    
        } catch (SQLException e) {
            Logger.getLogger(userAccount.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    public void insertCourseDetails(){
        try {
            courseDetails.clear();
            for (int j = 0; j < courseIDStaff.size(); j++) {
                courseDetails.add(new registeredStudentDetailsPopupModel(courseIDStaff.get(j),courseNameStaff.get(j), courseOccStaff.get(j), courseCapacity.get(j), courseModeStaff.get(j), courseDayStaff.get(j), courseTimeStaff.get(j), courseLocation.get(j)));
            }
            
            Node[] nodes = new Node[courseDetails.size()];
            
            for (int j = 0; j < nodes.length; j++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Assignment_MayaFOP/registeredStudentDetailsPopup.fxml"));
                nodes[j] = loader.load();
                
                final int h = j;
                
                registeredStudentDetailsPopupController studentDetailsPopupController = loader.getController();
                //customise content
                studentDetailsPopupController.setContentInfo(courseDetails.get(j).getCourseCodeLabel(),
                                                            courseDetails.get(j).getCourseNameLabel(),
                                                            courseDetails.get(j).getCourseOccLabel(),
                                                            courseDetails.get(j).getCourseCapacityLabel(),
                                                            courseDetails.get(j).getCourseModeLabel(),
                                                            courseDetails.get(j).getCourseDayLabel(),
                                                            courseDetails.get(j).getCourseTimeLabel(),
                                                            courseDetails.get(j).getCourseLocationLabel());               
                    
//                nodes[h].setOnMouseEntered(evt -> {
//                    //add effect
//                    nodes[h].setStyle("-fx-background-color: #b4baca");
//                });
//                nodes[h].setOnMouseExited(evt -> {
//                    //add effect
//                    nodes[h].setStyle("-fx-background-color: transparent");
//                });
//                nodes[h].setOnMousePressed(evt -> {
//                    //add effect
//                });

                vContainersPopUpRegisteredStudent.getChildren().add(nodes[j]);
//                insertStudentDetails();
            }
          


        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(moduleConfirmationMessageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static ArrayList<String> getOccIDStaff() {
        return occIDStaff;
    }
}
