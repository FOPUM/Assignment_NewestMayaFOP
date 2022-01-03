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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author kwany
 */
public class timetable implements Initializable, ControlledScreen {
    ScreenController myController;
    login_controller loginControl = new login_controller();
    
    private Button exit_button;
    
    @FXML
    private GridPane timeTableGridPane;
    
    databaseConnection connectNow = new databaseConnection();
    Connection connectDB = connectNow.getConnection();
    
    String matric_num = loginControl.getUsername();
    char accStatus = loginControl.getAccStatus();
    
    private static ArrayList<String> courseMode = new ArrayList<String>();
    private static ArrayList<String> courseID = new ArrayList<String>();
    private static ArrayList<String> courseName = new ArrayList<String>();
    private static ArrayList<String> courseDay = new ArrayList<String>();
    private static ArrayList<String> courseStartTime = new ArrayList<String>();
    private static ArrayList<String> courseEndTime = new ArrayList<String>();
    List<timetableColumnModel> courseDetails = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getCourseDetails();
        arrangeTimeTable();
    }

    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }
    
    public void getCourseDetails(){
        try {
            String UserDetails="SELECT * FROM \n" +
                                "(SELECT 'Lecture' as courseMode, student_take_course.occ_id AS occID, course.course_id AS courseID, \n" +
                                "course.course_name AS courseName, lecture.lecture_day AS courseDay, \n" +
                                "lecture.lecture_start_time AS courseStartTime, lecture.lecture_end_time AS courseEndTime\n" +
                                "FROM occ\n" +
                                "INNER JOIN student_take_course ON occ.occ_id=student_take_course.occ_id\n" +
                                "INNER JOIN course_occ ON course_occ.occ_id=occ.occ_id\n" +
                                "INNER JOIN course ON course.course_id=course_occ.course_id\n" +
                                "INNER JOIN lecture ON lecture.lecture_id=occ.lecture_id\n" +
                                "WHERE student_take_course.matric_num='"+matric_num+"' AND student_take_course.course_status='Y') AS lect\n" +
                                "UNION ALL\n" +
                                "SELECT * FROM \n" +
                                "(SELECT 'Tutorial' as courseMode, student_take_course.occ_id AS occID, course.course_id AS courseID, \n" +
                                "course.course_name AS courseName, tutorial.tutorial_day AS courseDay, \n" +
                                "tutorial.tutorial_start_time AS courseStartTime, tutorial.tutorial_end_time AS courseEndTime\n" +
                                "FROM occ\n" +
                                "INNER JOIN student_take_course ON occ.occ_id=student_take_course.occ_id\n" +
                                "INNER JOIN course_occ ON course_occ.occ_id=occ.occ_id\n" +
                                "INNER JOIN course ON course.course_id=course_occ.course_id\n" +
                                "INNER JOIN tutorial ON tutorial.tutorial_id=occ.tutorial_id\n" +
                                "WHERE student_take_course.matric_num='"+matric_num+"' AND student_take_course.course_status='Y') AS tuto\n" +
                                "UNION ALL\n" +
                                "SELECT * FROM\n" +
                                "(SELECT 'Lab' as courseMode, student_take_course.occ_id AS occID, course.course_id AS courseID, \n" +
                                "course.course_name AS courseName, lab.lab_day AS courseDay, \n" +
                                "lab.lab_start_time AS courseStartTime, lab.lab_end_time AS courseEndTime\n" +
                                "FROM occ\n" +
                                "INNER JOIN student_take_course ON occ.occ_id=student_take_course.occ_id\n" +
                                "INNER JOIN course_occ ON course_occ.occ_id=occ.occ_id\n" +
                                "INNER JOIN course ON course.course_id=course_occ.course_id\n" +
                                "INNER JOIN lab ON lab.lab_id=occ.lab_id\n" +
                                "WHERE student_take_course.matric_num='"+matric_num+"' AND student_take_course.course_status='Y') AS labb";
            ResultSet queryForCourseDetails = connectDB.createStatement().executeQuery(UserDetails);
            while(queryForCourseDetails.next()) {
                courseMode.add(queryForCourseDetails.getString("courseMode"));
                courseID.add(queryForCourseDetails.getString("courseID"));
                courseName.add(queryForCourseDetails.getString("courseName"));
                courseDay.add(queryForCourseDetails.getString("courseDay"));
                courseStartTime.add(queryForCourseDetails.getString("courseStartTime"));
                courseEndTime.add(queryForCourseDetails.getString("courseEndTime"));
            }    
        } catch (SQLException e) {
            Logger.getLogger(userAccount.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }
    
    public void arrangeTimeTable(){
        try {
            courseDetails.clear();
            for (int j = 0; j < courseID.size(); j++) {
                courseDetails.add(new timetableColumnModel(courseID.get(j),courseName.get(j)));
            }
            Node[] nodes = new Node[courseDetails.size()];
            
            for (int i = 0; i < nodes.length; i++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Assignment_MayaFOP/timetableColumn.fxml"));
                nodes[i] = loader.load();

                timetableColumnController box = loader.getController();
                
                if(courseStartTime.get(i) != null){
                    //customise content
                    int duration = getDuration(courseStartTime.get(i), courseEndTime.get(i));
                    System.out.println(duration);
                    box.setBoxSize(courseID.get(i), courseName.get(i), courseMode.get(i), duration);           

                    timeTableGridPane.add(nodes[i], getxPosition(courseStartTime.get(i)), getyPosition(courseDay.get(i)));
                } 
            }


        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(moduleConfirmationMessageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    public void exit_button(ActionEvent event) {
        //Click on exit button to exit       
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
        Platform.exit();
    } 
    
    
    
    public int getyPosition(String day){
        if(day.equals("MON")){
            return 0;
        }else if(day.equals("TUES")){
            return 1;
        }else if(day.equals("WED")){
            return 2;
        }else if(day.equals("THURS")){
            return 3;
        }else if(day.equals("FRI")){
            return 4;
        } 
        return 0;
    }
    
    public int getxPosition(String startTime){
        if (startTime.equals("08:00:00")){
            return 0;
        }else if (startTime.equals("09:00:00")){
            return 1;
        }else if (startTime.equals("10:00:00")){
            return 2;
        }else if (startTime.equals("11:00:00")){
            return 3;
        }else if (startTime.equals("12:00:00")){
            return 4;
        }else if (startTime.equals("13:00:00")){
            return 5;
        }else if (startTime.equals("14:00:00")){
            return 6;
        }else if (startTime.equals("15:00:00")){
            return 7;
        }else if (startTime.equals("16:00:00")){
            return 8;
        }else if (startTime.equals("17:00:00")){
            return 9;
        }else if (startTime.equals("18:00:00")){
            return 10;
        }else if (startTime.equals("19:00:00")){
            return 11;
        }
        return 0;
    }
    
    public int getDuration(String startTime, String endTime){
        startTime = startTime.substring(0, 2);
        endTime = endTime.substring(0, 2);
        int duration = Integer.parseInt(endTime) - Integer.parseInt(startTime);
        return duration;
    }
    
    
    
    
}
