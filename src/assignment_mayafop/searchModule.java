/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import static com.mysql.cj.conf.PropertyKey.logger;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;



/**
 *
 * @author Ming
 */
public class searchModule implements Initializable, ControlledScreen {

    ScreenController myController = new ScreenController();
    login_controller loginControl = new login_controller();
    MiscFunc misc = new MiscFunc();
    
    private static int selectedTableView_index;
    

    @FXML
    private BorderPane searchScreen;
    boolean showing;

    @FXML
    private TableColumn<modelCourse, String> courseCodeColumn;
    @FXML
    private TableColumn<modelCourse, String> courseNameColumn;
    @FXML
    private TableColumn<modelCourse, String> courseOccurenceColumn;
    @FXML
    private TableView<modelCourse> courseTableView;
    @FXML
    private TextField searchTextField;

    @FXML
    private Label courseCodeLabel;
    @FXML
    private Label courseNameLabel;
    @FXML
    private Label creditsLabel;
    @FXML
    private Label occurenceLabel;
    @FXML
    private Label lectureLecturerLabel;
    @FXML
    private Label lectureTimeLabel;
    @FXML
    private Label tutorialLecturerLabel;
    @FXML
    private Label tutorialTimeLabel;
    @FXML
    private Label labLecturerLabel;
    @FXML
    private Label labTimeLabel;
    @FXML
    private VBox vCourseNames;
    @FXML
    private Label warningLabel;
    @FXML
    private Button editCourseButton;
    @FXML
    private Button addCourseButton;
    @FXML
    private Button removeCourseButton;
    @FXML
    private Button nextButton;
    @FXML
    private Rectangle rightRect;
    @FXML 
    private Button addButton;
    @FXML
    private Label courseWarningLabel;


    @FXML 
    private Label creditHourLabel;
    
    static ArrayList<pickedModuleModel>  coursesModel = new ArrayList<>();    
    
    static ArrayList<Integer> creditHour = new ArrayList<Integer>();
      
    private static ArrayList<String> courseIDarray = new ArrayList<String>();
    private static ArrayList<String> occurenceID = new ArrayList<String>();
    private static ArrayList<String> courseNames = new ArrayList<String>();

    private static ArrayList<String> occurenceIDcheck = new ArrayList<String>();
    private static ArrayList<String> courseIDcheck = new ArrayList<String>();
    
    private static ArrayList<String> dayCheck = new ArrayList<String>();
    private static ArrayList<String> startTimeCheck = new ArrayList<String>();
    private static ArrayList<String> endTimeCheck = new ArrayList<String>();
        
    Node[] nodes = new Node[10];
    public int totalCreditHours;
    public int credithourcheck;
    private int i = 0;
    
    private static boolean confirmedtake;
    private static boolean confirmeddelete;
    
    String matric_num = loginControl.getUsername();
    char accStatus = loginControl.getAccStatus();
    
    String occIDCheck = new String();
    
    
    
    private String confirmedcourses = "SELECT occ_id, course_id\n" +
                                        "FROM student_take_course\n" +
                                        "WHERE matric_num='" + matric_num + "'";
    
    private String studentQualifications = "SELECT student_muet_band,student_nationality,\n" +
                                        "student_studysem,student_studyyear,student_programme, credit_hour\n" +
                                        "FROM student\n" +
                                        "WHERE matric_num='"+matric_num+"'";
    
    static boolean editingMode = false;
    
    
    databaseConnection connectNow = new databaseConnection();
    Connection connectDB = connectNow.getConnection();

    ObservableList<modelCourse> courseSearchModelObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        
        if(accStatus == 'S'){
            editCourseButton.setVisible(false);
            addCourseButton.setVisible(false);
            removeCourseButton.setVisible(false);
        }else if (accStatus == 'T'){
            addCourseButton.setVisible(false);
            removeCourseButton.setVisible(false);
            creditHourLabel.setVisible(false);
            rightRect.setVisible(false);
            nextButton.setVisible(false);
            addButton.setVisible(false);
        }else if (accStatus == 'A'){
            creditHourLabel.setVisible(false);
            rightRect.setVisible(false);
            nextButton.setVisible(false);
            addButton.setVisible(false);
        }
        

        showing = myController.getShowing();
        //Code below query all the things needed
        try {
            
            //Query for current registered course in an array
            ResultSet queryResultForCheck = connectDB.createStatement().executeQuery(confirmedcourses);
            while(queryResultForCheck.next()) {
                occurenceIDcheck.add(queryResultForCheck.getString("occ_id"));
                courseIDcheck.add(queryResultForCheck.getString("course_id"));
            }
            
            int studentBand = 0;
            String studentNationality = null; 
            int studentSem = 0;
            int studentYear = 0;
            String studentProgramme = null;
            
            //Query for qualification of the student
            ResultSet queryForStudentQualification = connectDB.createStatement().executeQuery(studentQualifications);
            while(queryForStudentQualification.next()) {
                studentBand = queryForStudentQualification.getInt("student_muet_band");
                studentNationality = queryForStudentQualification.getString("student_nationality");
                studentSem = queryForStudentQualification.getInt("student_studysem");
                studentYear = queryForStudentQualification.getInt("student_studyyear");
                studentProgramme = queryForStudentQualification.getString("student_programme");
                credithourcheck = queryForStudentQualification.getInt("credit_hour");
            }
            
            //Check for register course punya time to avoid crashing
            String timeQuery = "SELECT * FROM \n" +
                            "(SELECT \n" +
                            "lecture.lecture_day AS dayy, lecture.lecture_start_time AS start_time, lecture.lecture_end_time AS end_time\n" +
                            "FROM student_take_course\n" +
                            "INNER JOIN occ ON occ.occ_id=student_take_course.occ_id\n" +
                            "INNER JOIN lecture ON lecture.lecture_id=occ.lecture_id\n" +
                            "WHERE student_take_course.matric_num='"+matric_num+"' AND lecture.lecture_start_time!='NULL') AS lect\n" +
                            "UNION  \n" +
                            "SELECT * FROM \n" +
                            "(SELECT \n" +
                            "tutorial.tutorial_day, tutorial.tutorial_start_time, tutorial.tutorial_end_time\n" +
                            "FROM student_take_course\n" +
                            "INNER JOIN occ ON occ.occ_id=student_take_course.occ_id\n" +
                            "INNER JOIN tutorial ON tutorial.tutorial_id=occ.tutorial_id\n" +
                            "WHERE student_take_course.matric_num='"+matric_num+"' AND tutorial.tutorial_start_time!='NULL') AS tuto\n" +
                            "UNION \n" +
                            "SELECT * FROM \n" +
                            "(SELECT \n" +
                            "lab.lab_day, lab.lab_start_time, lab.lab_end_time\n" +
                            "FROM student_take_course\n" +
                            "INNER JOIN occ ON occ.occ_id=student_take_course.occ_id\n" +
                            "INNER JOIN lab ON lab.lab_id=occ.lab_id\n" +
                            "WHERE student_take_course.matric_num='"+matric_num+"' AND lab.lab_start_time!='NULL') AS labi";
            ResultSet queryForTime = connectDB.createStatement().executeQuery(timeQuery);
            while(queryForTime.next()) {
                dayCheck.add(queryForTime.getString("dayy"));
                startTimeCheck.add(queryForTime.getString("start_time"));
                endTimeCheck.add(queryForTime.getString("end_time"));
            }
            
            totalCreditHours = credithourcheck;
            creditHour.forEach((hour)-> totalCreditHours+=hour);
            creditHourLabel.setText("Credits Hours: " + totalCreditHours);
            
            //Query for items in search table
            //<editor-fold defaultstate="collapsed" desc="String for staff and admin">
                String course = "SELECT\n" +
                        "courseID, courseName, creditHour,\n" +
                        "occID, occName,\n" +
                        "tutoDay, tutoStartTime, tutoEndTime,\n" +
                        "tutoStaff, tutoLocation,\n" +
                        "lectureDay, lectureStartTime,lectureEndTime,\n" +
                        "lectStaff, lectLocation,\n" +
                        "lab.lab_day AS labDay, lab.lab_start_time AS labStartTime, lab.lab_end_time AS labEndTime,\n" +
                        "staff.staff_name AS labStaff, lab.lab_location AS labLocation\n" +
                        "\n" +
                        "FROM\n" +
                        "(SELECT * FROM\n" +
                        "(SELECT course.course_id AS courseID, course.course_name AS courseName, course.credit_hour AS creditHour,\n" +
                        "course.course_category AS courseCategory, course.course_year AS courseYear, course.course_sem AS courseSem,\n" +
                        "course.muet_band AS muetBand, course.nationality AS courseNationality, course.programme AS courseProgramme,\n" +
                        "occ.occ_name AS occName,occ.occ_id AS occID, occ.lab_id AS labID,\n" +
                        "tutorial.tutorial_day AS tutoDay, tutorial.tutorial_start_time AS tutoStartTime, tutorial.tutorial_end_time AS tutoEndTime,\n" +
                        "tutorial.tutorial_location AS tutoLocation, staff.staff_name AS tutoStaff\n" +
                        "\n" +
                        "FROM occ \n" +
                        "JOIN course ON course.course_id=SUBSTR(occ.occ_id, 1, 7)\n" +
                        "LEFT JOIN tutorial ON occ.tutorial_id=tutorial.tutorial_id\n" +
                        "LEFT JOIN staff_teach_tutorial ON occ.tutorial_id=staff_teach_tutorial.tutorial_id\n" +
                        "LEFT JOIN staff ON staff.staff_id=staff_teach_tutorial.staff_id) AS tuto\n" +
                        "\n" +
                        "LEFT JOIN \n" +
                        "\n" +
                        "(SELECT occ.occ_id AS occ2,\n" +
                        "lecture.lecture_day AS lectureDay, lecture.lecture_start_time AS lectureStartTime,lecture.lecture_end_time AS lectureEndTime,\n" +
                        "lecture.lecture_location AS lectLocation, staff.staff_name AS lectStaff\n" +
                        "FROM occ \n" +
                        "JOIN course ON course.course_id=SUBSTR(occ.occ_id, 1, 7)\n" +
                        "LEFT JOIN lecture ON lecture.lecture_id=occ.lecture_id\n" +
                        "LEFT JOIN staff_teach_lecture ON occ.lecture_id=staff_teach_lecture.lecture_id\n" +
                        "LEFT JOIN staff on staff.staff_id=staff_teach_lecture.staff_id) AS lec\n" +
                        "\n" +
                        "ON tuto.occID=lec.occ2) AS lectuto\n" +
                        "\n" +
                        "LEFT JOIN lab ON lab.lab_id=lectuto.labID\n" +
                        "LEFT JOIN staff_teach_lab ON staff_teach_lab.lab_id=lectuto.labID\n" +
                        "LEFT JOIN staff ON staff_teach_lab.staff_id=staff.staff_id";
                //</editor-fold>
            if(accStatus == 'S'){
                //<editor-fold defaultstate="collapsed" desc="String for student">
                course = "SELECT\n" +
                        "courseID, courseName, creditHour,\n" +
                        "occID, occName,\n" +
                        "tutoDay, tutoStartTime, tutoEndTime,\n" +
                        "tutoStaff, tutoLocation,\n" +
                        "lectureDay, lectureStartTime,lectureEndTime,\n" +
                        "lectStaff, lectLocation,\n" +
                        "lab.lab_day AS labDay, lab.lab_start_time AS labStartTime, lab.lab_end_time AS labEndTime,\n" +
                        "staff.staff_name AS labStaff, lab.lab_location AS labLocation,\n" +
                        "courseCategory,courseYear,courseSem,\n" +
                        "muetBand,courseNationality,courseProgramme\n" +
                        "FROM\n" +
                        "(SELECT * FROM\n" +
                        "(SELECT course.course_id AS courseID, course.course_name AS courseName, course.credit_hour AS creditHour,\n" +
                        "course.course_category AS courseCategory, course.course_year AS courseYear, course.course_sem AS courseSem,\n" +
                        "course.muet_band AS muetBand, course.nationality AS courseNationality, course.programme AS courseProgramme,\n" +
                        "occ.occ_name AS occName,occ.occ_id AS occID, occ.lab_id AS labID,\n" +
                        "tutorial.tutorial_day AS tutoDay, tutorial.tutorial_start_time AS tutoStartTime, tutorial.tutorial_end_time AS tutoEndTime,\n" +
                        "tutorial.tutorial_location AS tutoLocation, staff.staff_name AS tutoStaff\n" +
                        "\n" +
                        "FROM occ \n" +
                        "JOIN course ON course.course_id=SUBSTR(occ.occ_id, 1, 7)\n" +
                        "LEFT JOIN tutorial ON occ.tutorial_id=tutorial.tutorial_id\n" +
                        "LEFT JOIN staff_teach_tutorial ON occ.tutorial_id=staff_teach_tutorial.tutorial_id\n" +
                        "LEFT JOIN staff ON staff.staff_id=staff_teach_tutorial.staff_id) AS tuto\n" +
                        "\n" +
                        "LEFT JOIN \n" +
                        "\n" +
                        "(SELECT occ.occ_id AS occ2,\n" +
                        "lecture.lecture_day AS lectureDay, lecture.lecture_start_time AS lectureStartTime,lecture.lecture_end_time AS lectureEndTime,\n" +
                        "lecture.lecture_location AS lectLocation, staff.staff_name AS lectStaff\n" +
                        "FROM occ \n" +
                        "JOIN course ON course.course_id=SUBSTR(occ.occ_id, 1, 7)\n" +
                        "LEFT JOIN lecture ON lecture.lecture_id=occ.lecture_id\n" +
                        "LEFT JOIN staff_teach_lecture ON occ.lecture_id=staff_teach_lecture.lecture_id\n" +
                        "LEFT JOIN staff on staff.staff_id=staff_teach_lecture.staff_id) AS lec\n" +
                        "\n" +
                        "ON tuto.occID=lec.occ2) AS lectuto\n" +
                        "\n" +
                        "LEFT JOIN lab ON lab.lab_id=lectuto.labID\n" +
                        "LEFT JOIN staff_teach_lab ON staff_teach_lab.lab_id=lectuto.labID\n" +
                        "LEFT JOIN staff ON staff_teach_lab.staff_id=staff.staff_id\n" +
                        "\n" +
                        "WHERE \n" +
                        "(courseCategory='FCC' OR courseCategory ='UC' OR courseCategory ='PCC' OR courseCategory ='KELF' OR courseCategory ='FEC' OR courseCategory ='SEC') AND\n" +
                        "(courseYear='ALL' OR courseYear='"+studentYear+"') AND\n" +
                        "(courseSem='ALL' OR courseSem='"+studentSem+"') AND\n" +
                        "(muetBand='ALL' OR muetBand='"+studentBand+"') AND \n" +
                        "(courseNationality='ALL' OR courseNationality='"+studentNationality.toUpperCase()+"') AND\n" +
                        "(courseProgramme='ALL' OR courseProgramme='"+studentProgramme.toUpperCase()+"') ";
                //</editor-fold>
            }else if(accStatus == 'T' || accStatus == 'A'){
                //<editor-fold defaultstate="collapsed" desc="String for staff and admin">
                course = "SELECT\n" +
                        "courseID, courseName, creditHour,\n" +
                        "occID, occName,\n" +
                        "tutoDay, tutoStartTime, tutoEndTime,\n" +
                        "tutoStaff, tutoLocation,\n" +
                        "lectureDay, lectureStartTime,lectureEndTime,\n" +
                        "lectStaff, lectLocation,\n" +
                        "lab.lab_day AS labDay, lab.lab_start_time AS labStartTime, lab.lab_end_time AS labEndTime,\n" +
                        "staff.staff_name AS labStaff, lab.lab_location AS labLocation\n" +
                        "\n" +
                        "FROM\n" +
                        "(SELECT * FROM\n" +
                        "(SELECT course.course_id AS courseID, course.course_name AS courseName, course.credit_hour AS creditHour,\n" +
                        "course.course_category AS courseCategory, course.course_year AS courseYear, course.course_sem AS courseSem,\n" +
                        "course.muet_band AS muetBand, course.nationality AS courseNationality, course.programme AS courseProgramme,\n" +
                        "occ.occ_name AS occName,occ.occ_id AS occID, occ.lab_id AS labID,\n" +
                        "tutorial.tutorial_day AS tutoDay, tutorial.tutorial_start_time AS tutoStartTime, tutorial.tutorial_end_time AS tutoEndTime,\n" +
                        "tutorial.tutorial_location AS tutoLocation, staff.staff_name AS tutoStaff\n" +
                        "\n" +
                        "FROM occ \n" +
                        "JOIN course ON course.course_id=SUBSTR(occ.occ_id, 1, 7)\n" +
                        "LEFT JOIN tutorial ON occ.tutorial_id=tutorial.tutorial_id\n" +
                        "LEFT JOIN staff_teach_tutorial ON occ.tutorial_id=staff_teach_tutorial.tutorial_id\n" +
                        "LEFT JOIN staff ON staff.staff_id=staff_teach_tutorial.staff_id) AS tuto\n" +
                        "\n" +
                        "LEFT JOIN \n" +
                        "\n" +
                        "(SELECT occ.occ_id AS occ2,\n" +
                        "lecture.lecture_day AS lectureDay, lecture.lecture_start_time AS lectureStartTime,lecture.lecture_end_time AS lectureEndTime,\n" +
                        "lecture.lecture_location AS lectLocation, staff.staff_name AS lectStaff\n" +
                        "FROM occ \n" +
                        "JOIN course ON course.course_id=SUBSTR(occ.occ_id, 1, 7)\n" +
                        "LEFT JOIN lecture ON lecture.lecture_id=occ.lecture_id\n" +
                        "LEFT JOIN staff_teach_lecture ON occ.lecture_id=staff_teach_lecture.lecture_id\n" +
                        "LEFT JOIN staff on staff.staff_id=staff_teach_lecture.staff_id) AS lec\n" +
                        "\n" +
                        "ON tuto.occID=lec.occ2) AS lectuto\n" +
                        "\n" +
                        "LEFT JOIN lab ON lab.lab_id=lectuto.labID\n" +
                        "LEFT JOIN staff_teach_lab ON staff_teach_lab.lab_id=lectuto.labID\n" +
                        "LEFT JOIN staff ON staff_teach_lab.staff_id=staff.staff_id";
                //</editor-fold>
            }
            ResultSet courseQueryOutput = connectDB.createStatement().executeQuery(course);
            System.out.println("");
            while (courseQueryOutput.next()) {
                String courseID = courseQueryOutput.getString("courseID");
                String courseName = courseQueryOutput.getString("courseName");
                String creditHour = courseQueryOutput.getString("creditHour");
                String occID = courseQueryOutput.getString("occID");
                String occName = courseQueryOutput.getString("occName");
                String tutoDay = courseQueryOutput.getString("tutoDay");
                String tutoStartTime = courseQueryOutput.getString("tutoStartTime");
                String tutoEndTime = courseQueryOutput.getString("tutoEndTime");
                String tutoStaff = courseQueryOutput.getString("tutoStaff");
                String tutoLocation = courseQueryOutput.getString("tutoLocation");
                String lectDay = courseQueryOutput.getString("lectureDay");
                String lectStartTime = courseQueryOutput.getString("lectureStartTime");
                String lectEndTime = courseQueryOutput.getString("lectureEndTime");
                String lectStaff = courseQueryOutput.getString("lectStaff");
                String lectLocation = courseQueryOutput.getString("lectLocation");
                String labDay = courseQueryOutput.getString("labDay");
                String labStartTime = courseQueryOutput.getString("labStartTime");
                String labEndTime = courseQueryOutput.getString("labEndTime");
                String labStaff = courseQueryOutput.getString("labStaff");
                String labLocation = courseQueryOutput.getString("labLocation");
                //cant populate new course, still not add current capacity
                // Populate the ObservableList
                if (!courseIDcheck.contains(courseID)) {
                    courseSearchModelObservableList.add(new modelCourse(courseID, courseName, creditHour, occID, occName, tutoDay, tutoStartTime, tutoEndTime, tutoStaff, tutoLocation, lectDay, lectStartTime, lectEndTime, lectStaff, lectLocation, labDay, labStartTime, labEndTime, labStaff, labLocation));
                }        
            }

            //PropertyValueFactory corresponds to the new ProductSearchModel fields
            courseCodeColumn.setCellValueFactory(new PropertyValueFactory<>("courseID"));
            courseCodeColumn.setResizable(false);
            courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
            courseNameColumn.setResizable(false);
            courseOccurenceColumn.setCellValueFactory(new PropertyValueFactory<>("occName"));
            courseOccurenceColumn.setResizable(false);
            courseTableView.setItems(courseSearchModelObservableList);
            search();

            courseTableView.setOnMousePressed(new EventHandler<MouseEvent>() {
                
                @Override
                public void handle(MouseEvent event) {
                    if (!courseTableView.getSelectionModel().equals(null)) {
                        
                        occIDCheck =courseTableView.getSelectionModel().getSelectedItem().getOccID();

                        courseCodeLabel.setText(courseTableView.getSelectionModel().getSelectedItem().getCourseID());
                        courseNameLabel.setText(misc.upperLetter(courseTableView.getSelectionModel().getSelectedItem().getCourseName()));
                        creditsLabel.setText("Credits: " + courseTableView.getSelectionModel().getSelectedItem().getCreditHour());
                        occurenceLabel.setText("Occurence: " + courseTableView.getSelectionModel().getSelectedItem().getOccName().substring(3));

                        if(courseTableView.getSelectionModel().getSelectedItem().getLectDay() != null){
                        String lectureTime = misc.formatDay(courseTableView.getSelectionModel().getSelectedItem().getLectDay()) + "  "
                                + misc.formatTime(courseTableView.getSelectionModel().getSelectedItem().getLectStartTime()) + "-" 
                                + misc.formatTime(courseTableView.getSelectionModel().getSelectedItem().getLectEndTime());
                        lectureTimeLabel.setText(lectureTime);
                        lectureLecturerLabel.setText(misc.upperLetter(courseTableView.getSelectionModel().getSelectedItem().getLectStaff()));
                        }else{
                            lectureTimeLabel.setText("");
                            lectureLecturerLabel.setText("");
                        }

                        if(courseTableView.getSelectionModel().getSelectedItem().getTutoDay() != null){
                        String tutorialTime = misc.formatDay(courseTableView.getSelectionModel().getSelectedItem().getTutoDay()) + "  "
                                + misc.formatTime(courseTableView.getSelectionModel().getSelectedItem().getTutoStartTime()) + "-" 
                                + misc.formatTime(courseTableView.getSelectionModel().getSelectedItem().getTutoEndTime());
                        tutorialTimeLabel.setText(tutorialTime);
                        tutorialLecturerLabel.setText(misc.upperLetter(courseTableView.getSelectionModel().getSelectedItem().getTutoStaff()));
                        }else{
                            tutorialTimeLabel.setText("");
                            tutorialLecturerLabel.setText("");
                        }
                        
                        if(courseTableView.getSelectionModel().getSelectedItem().getLabDay() != null){
                            String labTime = misc.formatDay(courseTableView.getSelectionModel().getSelectedItem().getLabDay()) + "  "
                                + misc.formatTime(courseTableView.getSelectionModel().getSelectedItem().getLabStartTime()) + "-" 
                                + misc.formatTime(courseTableView.getSelectionModel().getSelectedItem().getLabEndTime());
                        labTimeLabel.setText(labTime);
                        labLecturerLabel.setText(misc.upperLetter(courseTableView.getSelectionModel().getSelectedItem().getLabStaff()));
                        }else{
                            labTimeLabel.setText("");
                            labLecturerLabel.setText("");
                        }
                    }
                    

                }
            });

        } catch (SQLException e) {
            Logger.getLogger(searchModule.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        
        try{
            for (int j = 0; j < courseIDarray.size(); j++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Assignment_MayaFOP/pickedModule.fxml"));
                nodes[j] = loader.load();
                pickedModuleController controller = loader.getController();
                controller.setCourseName(courseIDarray.get(j));
                vCourseNames.getChildren().add(nodes[j]);
//                for (int k = 0; k < courseIDarray.size(); k++) {
//                    try {
//                        String queryForCreditHour = "SELECT credit_hour FROM course WHERE course_id='"+courseIDarray.get(k)+"'";
//                        ResultSet creditQueryOutput = connectDB.createStatement().executeQuery(queryForCreditHour);
//                        while (creditQueryOutput.next()) {
//                            totalCreditHours +=creditQueryOutput.getInt("credit_hour");
//                        }
//                    } catch (SQLException e) {
//                        Logger.getLogger(searchModule.class.getName()).log(Level.SEVERE, null, e);
//                        e.printStackTrace();
//                    }
//                }
//                creditHourLabel.setText("Credits Hours: " + totalCreditHours);

                final int h = j;
                nodes[h].setOnMouseEntered(evt -> {
                    //add effect
                    nodes[h].setStyle("-fx-background-color: #084654");
                });
                nodes[h].setOnMouseExited(evt -> {
                    //add effect
                    nodes[h].setStyle("-fx-background-color: #FFFFFF");
                });
                nodes[h].setOnMousePressed(evt -> {
                    //add effect
                    nodes[h].setStyle("-fx-background-color: #000000");
                    deleteModule(h);

                    creditHour.remove(h);
                    totalCreditHours = credithourcheck;
                    creditHour.forEach((hour)-> totalCreditHours+=hour);
                    creditHourLabel.setText("Credits Hours: " + totalCreditHours);
                });
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }

    //module confirmation button
    public void goToModuleConfirmation(ActionEvent event) {
        
        if (!showing) {
            myController.showPopupStage(searchScreen, "/assignment_MayaFOP/moduleConfirmationMessage.fxml");
            
            myController.loadScreen(Assignment_MayaFOP.searchScreen, Assignment_MayaFOP.searchScreenFile);      
            myController.setScreen(Assignment_MayaFOP.searchScreen);
            showing = myController.getShowing();   
        }
        if(confirmedtake){
            confirmedtaken();
            clearMemory();
        }
    }
    
    public int checkTime(String startTimeString, String endTimeString, ArrayList<String> startlist, ArrayList<String> endlist){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println("start time " + startTimeString);
        System.out.println("end time " + endTimeString);
//        if(startTimeString != null || endTimeString != null || startTimeString.length() == 0 || endTimeString.length() == 0){
            try {
                Date startTime = sdf.parse(startTimeString);
                Date endTime = sdf.parse(endTimeString);
                for (int j = 0; j < startlist.size(); j++) {
                    Date startTimeForCheck = sdf.parse(startlist.get(j));
                    Date endTimeForCheck = sdf.parse(endlist.get(j));
                    if(isOverlapping(startTime, endTime, startTimeForCheck, endTimeForCheck)){
                        System.out.println("array start " + startlist.get(j));
                        System.out.println("array end " + endlist.get(j));
                        System.out.println("Crash Time!");
                        return 1;
                    }
                }
            } catch (ParseException | NullPointerException ex) {
//                Logger.getLogger(searchModule.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("No crash time!");
                return 0;
            }
            
//        }
        System.out.println("No crash time!");
        return 0;
    }
    
    public int checkDay(String studyDay, ArrayList<String> studyDayArray) {
        System.out.println("ori " + studyDay);
        if(studyDay != null){
            for (int j = 0; j < studyDayArray.size(); j++) {
                if(studyDay.equals(studyDayArray.get(j))){
                    System.out.println(studyDayArray.get(j) + " in array");
                    System.out.println("Crash Day");
                    return 1;
                }
            }
        }
        System.out.println("No crash day!");
        return 0;
    }
    
    public boolean isDayCrashingInDatabase(){       
        int checkDayInt = 0; // if larger than 0, measn crash
 
        String lectday = "" + courseTableView.getSelectionModel().getSelectedItem().getLectDay();
        checkDayInt += checkDay(lectday, dayCheck);
        if(checkDayInt > 0){
            if(isTimeCrashingInDatabase()){
                return true;
            }
        }
        String tutoday = "" + courseTableView.getSelectionModel().getSelectedItem().getTutoDay();
        checkDayInt += checkDay(tutoday, dayCheck);
        if(checkDayInt > 0){
            if(isTimeCrashingInDatabase()){
                return true;
            }
        }
        String labday = "" + courseTableView.getSelectionModel().getSelectedItem().getLabDay();
        checkDayInt += checkDay(labday, dayCheck);
        if(checkDayInt > 0){
            if(isTimeCrashingInDatabase()){
                return true;
            }
        }
        
        return false;
    }
    
    public boolean isTimeCrashingInDatabase(){
        int checkTimeInt = 0; //if larger then 0, means crash

        String lectStartTime = "" + courseTableView.getSelectionModel().getSelectedItem().getLectStartTime();
        String lectEndTime = "" + courseTableView.getSelectionModel().getSelectedItem().getLectEndTime();
        checkTimeInt += checkTime(lectStartTime, lectEndTime, startTimeCheck, endTimeCheck);
        if(checkTimeInt > 0){
            return true;
        }
        String tutoStartTime = "" + courseTableView.getSelectionModel().getSelectedItem().getTutoStartTime();
        String tutoEndTime = "" + courseTableView.getSelectionModel().getSelectedItem().getTutoEndTime();
        checkTimeInt += checkTime(tutoStartTime, tutoEndTime, startTimeCheck, endTimeCheck);
        if(checkTimeInt > 0){
            return true;
        }

        String labStartTime = "" + courseTableView.getSelectionModel().getSelectedItem().getLabStartTime();
        String labEndTime = "" + courseTableView.getSelectionModel().getSelectedItem().getLabEndTime();
        checkTimeInt += checkTime(labStartTime, labEndTime, startTimeCheck, endTimeCheck);
        if(checkTimeInt > 0){
            return true;
        }
        
        
        return false;
    }

    //add course to the right panel
    public void addModule(ActionEvent event) throws Exception { 
        
        int credithours = Integer.parseInt(courseTableView.getSelectionModel().getSelectedItem().getCreditHour());
        String courseID = "" + courseTableView.getSelectionModel().getSelectedItem().getCourseID();
        String occID = "" + courseTableView.getSelectionModel().getSelectedItem().getOccID();
        String courseName = "" + courseTableView.getSelectionModel().getSelectedItem().getCourseName();
        
        String lectday = "" + courseTableView.getSelectionModel().getSelectedItem().getLectDay();
        String tutoday = "" + courseTableView.getSelectionModel().getSelectedItem().getTutoDay();
        String labday = "" + courseTableView.getSelectionModel().getSelectedItem().getLabDay();
        
        String lectStartTime = "" + courseTableView.getSelectionModel().getSelectedItem().getLectStartTime();
        String lectEndTime = "" + courseTableView.getSelectionModel().getSelectedItem().getLectEndTime();
        String tutoStartTime = "" + courseTableView.getSelectionModel().getSelectedItem().getTutoStartTime();
        String tutoEndTime = "" + courseTableView.getSelectionModel().getSelectedItem().getTutoEndTime();
        String labStartTime = "" + courseTableView.getSelectionModel().getSelectedItem().getLabStartTime();
        String labEndTime = "" + courseTableView.getSelectionModel().getSelectedItem().getLabEndTime();
        
        

        if(courseIDcheck.contains(courseID) || occurenceIDcheck.contains(occID) || courseIDarray.contains(courseID) ){
            warningLabel.setText("Already picked!");
        }else{
            warningLabel.setText("");
            if(isDayCrashingInDatabase()){
                warningLabel.setText("Time crashing!");
            }else{
                warningLabel.setText("");
                if (totalCreditHours + Integer.parseInt(courseTableView.getSelectionModel().getSelectedItem().getCreditHour()) <= 22 ) {
                    warningLabel.setText("");
                    try{
                        pickedModuleModel addingCourse = new pickedModuleModel(courseID);
                        if (courseIDcheck.contains(courseID)) {
                            System.out.println("The coursed already picked");
                        }else{
                            courseIDarray.add(courseID);
                            coursesModel.add(addingCourse);
                            occurenceID.add(occID);
                            courseNames.add(courseName);
                                                        
                            dayCheck.add(lectday);
                            startTimeCheck.add(lectStartTime);
                            endTimeCheck.add(lectEndTime);
                            
                            dayCheck.add(tutoday);
                            startTimeCheck.add(tutoStartTime);
                            endTimeCheck.add(tutoEndTime);
                            
                            dayCheck.add(labday);
                            startTimeCheck.add(labStartTime);
                            endTimeCheck.add(labEndTime);
        
                            System.out.println(addingCourse + " Has been add");
                        }

                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/Assignment_MayaFOP/pickedModule.fxml"));
                        nodes[i] = loader.load();
                        final int h = i;

                        pickedModuleController controller = loader.getController();
                        controller.setCourseName(coursesModel.get(i).getCourseIDLabel());


                        vCourseNames.getChildren().add(nodes[i]);
                        i++;

                        nodes[h].setOnMouseEntered(evt -> {
                            //add effect
                            nodes[h].setStyle("-fx-background-color: #084654");
                        });
                        nodes[h].setOnMouseExited(evt -> {
                            //add effect
                            nodes[h].setStyle("-fx-background-color: #FFFFFF");
                        });
                        nodes[h].setOnMousePressed(evt -> {
                            //add effect
                            nodes[h].setStyle("-fx-background-color: #000000");
                            deleteModule(h);
                            i--;
                            creditHour.remove(h);
                            totalCreditHours = credithourcheck;
                            creditHour.forEach((hour)-> totalCreditHours+=hour);
                            creditHourLabel.setText("Credits Hours: " + totalCreditHours);
                        });

                        }catch (IOException e) {
                            e.printStackTrace();
                        }

                    creditHour.add(credithours);
                    totalCreditHours += creditHour.get(creditHour.size()-1); 
    //                creditHour.forEach((hour)-> totalCreditHours+=hour);
                    creditHourLabel.setText("Credits Hours: " + totalCreditHours);


                } else {
                    warningLabel.setText("Total credit hours exceed 22 hours!");
                }
            }
        }    
    }
    
    public void deleteModule(int i) {
        courseIDarray.remove(i);
        coursesModel.remove(i);
        occurenceID.remove(i);
        courseNames.remove(i);
        vCourseNames.getChildren().remove(nodes[i]);
        dayCheck.remove(i*3);
        dayCheck.remove(i*3+1);
        dayCheck.remove(i*3+2);
        startTimeCheck.remove(i*3);
        startTimeCheck.remove(i*3+1);
        startTimeCheck.remove(i*3+2);
        endTimeCheck.remove(i*3);
        endTimeCheck.remove(i*3+1);
        endTimeCheck.remove(i*3+2);
    }

    public void search() {
        // Initialise filtered list
        FilteredList<modelCourse> filteredData = new FilteredList<>(courseSearchModelObservableList, b -> true);

        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(courseSearchModel -> {

                // If no values input, no change to the list
                if (newValue.isEmpty() || newValue.isEmpty() || newValue == null) {
                    return true;
                }

                double unsimilarity = 0;
                String searchKeyword = newValue.toLowerCase();
                System.out.println("Seach keyword is: " + searchKeyword);
                System.out.println("The coursename is: " +courseSearchModel.getCourseName().toLowerCase());
                char[] courseNameCheckerArray = courseSearchModel.getCourseName().toLowerCase().toCharArray();
                if (searchKeyword.length() <= courseSearchModel.getCourseName().toLowerCase().length() ) {
                    for (int k = 0; k < searchKeyword.length(); k++) {
                        if (searchKeyword.charAt(k) != courseNameCheckerArray[k]) {
                            System.out.println(searchKeyword.charAt(k)+ " is different with " + courseNameCheckerArray[k]);
                            unsimilarity++;
                        }
                    }
                }
                else{
                    unsimilarity = searchKeyword.length();
                }
                
                unsimilarity = (unsimilarity / searchKeyword.length())*100.00;
                System.out.println("Unsimilarity%: " + unsimilarity);
                if (courseSearchModel.getCourseName().toLowerCase().indexOf(searchKeyword) > -1 || unsimilarity <=40) {
                    return true; // Found a match in course name
                } else if (courseSearchModel.getCourseID().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true; // Found a match in course name
                } else if (courseSearchModel.getOccName().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true; // Found a match in occ
                } else {
                    return false;
                }
            });
        });

        SortedList<modelCourse> sortedData = new SortedList<>(filteredData);

        //Bind sorted result with Table View
        sortedData.comparatorProperty().bind(courseTableView.comparatorProperty());
        courseTableView.setItems(sortedData);
    }
    
    //button at another scene, trigger by true in confirmedtake
    public void confirmedtaken(){
        ResultSet addModuleQuery;
        
        try {    
//            try {
//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(getClass().getResource("/Assignment_MayaFOP/login.fxml"));
//                loader.load();
//                loginControl = loader.getController();
//            } catch (IOException ex) {
//                Logger.getLogger(searchModule.class.getName()).log(Level.SEVERE, null, ex);
//            }
            
            
            for (int j = 0; j < courseIDarray.size(); j++) {
                String course_id = courseIDarray.get(j);
                String occ_id = occurenceID.get(j);
                char status = 'Y';
                String confirmedCourse = "INSERT INTO student_take_course(matric_num, course_id, occ_id, course_status)\n" +
                                    "VALUES ('" + matric_num + "', '" + course_id + "', '" + occ_id + "', '" + status + "');";
                String updateCredit = "UPDATE student SET credit_hour='"+totalCreditHours+"', enrolled_status='Y' WHERE matric_num='"+matric_num+"'";
                Statement statementUpdate = connectDB.createStatement();
                statementUpdate.executeUpdate(confirmedCourse);
                statementUpdate.executeUpdate(updateCredit);
                System.out.println("Done adding " + occ_id);
            }

            confirmedtake = false;
            
        } catch (SQLException ex) {
            Logger.getLogger(searchModule.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
    
    public void removeWholeCourse(){
        String occIDToRemove = courseTableView.getSelectionModel().getSelectedItem().getOccID();
        System.out.println(occIDToRemove);
        
        String checkLectID = "SELECT lecture_id FROM occ WHERE occ_id='"+occIDToRemove+"'";
        String checkTutoID = "SELECT tutorial_id FROM occ WHERE occ_id='"+occIDToRemove+"'";
        String checkLabID = "SELECT lab_id FROM occ WHERE occ_id='"+occIDToRemove+"'";
        
        String lectID = null;
        String tutoID = null;
        String labID = null;
        try {
            ResultSet lect = connectDB.createStatement().executeQuery(checkLectID);
            while(lect.next()){
                lectID = lect.getString("lecture_id");
            }
            ResultSet tuto = connectDB.createStatement().executeQuery(checkTutoID);
            while(tuto.next()){
                tutoID = tuto.getString("tutorial_id");
            }
            ResultSet lab = connectDB.createStatement().executeQuery(checkLabID);
            while(lab.next()){
                labID = lab.getString("lab_id");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(searchModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            String a = "DELETE FROM student_take_course WHERE occ_id='"+occIDToRemove+"'";
            String b = "DELETE FROM course_occ WHERE occ_id='"+occIDToRemove+"'";
            
            String c = "DELETE FROM staff_teach_lecture WHERE lecture_id='"+lectID+"'";
            String d = "DELETE FROM staff_teach_tutorial WHERE tutorial_id='"+tutoID+"'";
            String e = "DELETE FROM staff_teach_lab WHERE lab_id='"+labID+"'";
            
            String f = "DELETE FROM occ WHERE occ_id='"+occIDToRemove+"'";
            
            String g = "DELETE FROM lecture WHERE lecture_id='"+lectID+"'";
            String h = "DELETE FROM tutorial WHERE tutorial_id='"+tutoID+"'";
            String i = "DELETE FROM lab WHERE lab_id='"+labID+"'";
            
            Statement statementDelete = connectDB.createStatement();
            statementDelete.executeUpdate(a);
            statementDelete.executeUpdate(b);
            
            statementDelete.executeUpdate(c);
            statementDelete.executeUpdate(d);
            if(!labID.equals("NONE")){
                statementDelete.executeUpdate(e);
            }
        
            statementDelete.executeUpdate(f);
            
            statementDelete.executeUpdate(g);
            statementDelete.executeUpdate(h);
            if(!labID.equals("NONE")){
                statementDelete.executeUpdate(i);
            }
            
            System.out.println("Module deleted successfully.");
            
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        
    }
    
    //not yet done
    private static ArrayList<String> occIDStaff = new ArrayList<String>();
    
    public void editCourse(ActionEvent event){
        
        try {
            String queryForStaffOcc = "SELECT occ.occ_id\n" +
                                        "FROM occ \n" +
                                        "LEFT JOIN staff_teach_lecture ON staff_teach_lecture.lecture_id=occ.lecture_id\n" +
                                        "LEFT JOIN staff_teach_tutorial ON staff_teach_tutorial.tutorial_id=occ.tutorial_id\n" +
                                        "LEFT JOIN staff_teach_lab ON staff_teach_lab.lab_id=occ.lab_id\n" +
                                        "WHERE staff_teach_lecture.staff_id='"+matric_num+"' OR staff_teach_tutorial.staff_id='"+matric_num+"' OR staff_teach_lab.staff_id-'"+matric_num+"'";
            ResultSet queryForAdditionalCourseDetails = connectDB.createStatement().executeQuery(queryForStaffOcc);
            while(queryForAdditionalCourseDetails.next()) {
                occIDStaff.add(queryForAdditionalCourseDetails.getString("occ_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(searchModule.class.getName()).log(Level.SEVERE, null, ex);
        }
                     
        if(occIDStaff.contains(courseTableView.getSelectionModel().getSelectedItem().getOccID()) || accStatus == 'A'){
            courseWarningLabel.setText("");
            if (!showing) {
                try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Assignment_MayaFOP/Module.fxml"));
                loader.load();

                String courseCategory = null;
                String courseYear = null;
                String courseSem = null;
                String courseMuet = null;
                String courseNationality = null;
                String courseProgramme = null;

                String additionalCourseDetails = "SELECT course_category, course_year, course_sem, muet_band, nationality, programme FROM course WHERE course_id='"+courseTableView.getSelectionModel().getSelectedItem().getCourseID()+"'";
                ResultSet queryForAdditionalCourseDetails = connectDB.createStatement().executeQuery(additionalCourseDetails);
                while(queryForAdditionalCourseDetails.next()) {
                    courseCategory = queryForAdditionalCourseDetails.getString("course_category");
                    courseYear = queryForAdditionalCourseDetails.getString("course_year");
                    courseSem = queryForAdditionalCourseDetails.getString("course_sem");
                    courseMuet = queryForAdditionalCourseDetails.getString("muet_band");
                    courseNationality = queryForAdditionalCourseDetails.getString("nationality");
                    courseProgramme = queryForAdditionalCourseDetails.getString("programme");
                }

                ModuleController moduleController = loader.getController();
                moduleController.setCourseIdSetter(courseTableView.getSelectionModel().getSelectedItem().getCourseID());
                moduleController.setCourseNameSetter(courseTableView.getSelectionModel().getSelectedItem().getCourseName());
                moduleController.setCreditHourSetter(courseTableView.getSelectionModel().getSelectedItem().getCreditHour());
                moduleController.setCourseCategorySetter(courseCategory);
                moduleController.setCourseYearSetter(courseYear);
                moduleController.setCourseSemSetter(courseSem);
                moduleController.setMuetBandSetter(courseMuet);
                moduleController.setNationalitySetter(courseNationality);
                moduleController.setProgrammeSetter(courseProgramme);

                editingMode = true;

                } catch (Exception e) {
                }
                myController.showPopupStage(searchScreen, "/assignment_MayaFOP/Module.fxml");
                showing = myController.getShowing(); 
            }
        }else{
            courseWarningLabel.setText("Please edit your course only!");
        }
//        try {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("/Assignment_MayaFOP/Module.fxml"));
//            loader.load();
//            ModuleController module = loader.getController();
//            
            
//
//            module.setCourseIDTextField(courseTableView.getSelectionModel().getSelectedItem().getCourseID());
//            module.setCourseNameTextField(courseTableView.getSelectionModel().getSelectedItem().getCourseName());
//            module.setCreditHourTextField(courseTableView.getSelectionModel().getSelectedItem().getCreditHour());
//            module.setCourseCategoryComboBox("");
//            module.setCourseYearComboBox(matric_num);
//            module.setCourseSemComboBox();
//            module.setMuetBandComboBox(matric_num);
//            module.setNationalityComboBox(matric_num);
//            module.setProgrammeComboBox(matric_num);
//            
//        } catch (IOException e) {
//        }
    }
    
    public void removeModule(ActionEvent event){
        if (!showing) {
            myController.showPopupStage(searchScreen, "/assignment_MayaFOP/removeModule.fxml");
            showing = myController.getShowing();   
        }
        if(confirmeddelete){
            removeWholeCourse();
        }
    }
    
    public void addNewModule(ActionEvent event){
        if (!showing) {
            try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Assignment_MayaFOP/Module.fxml"));
            loader.load();
            
            ModuleController moduleController = loader.getController();
            moduleController.setCourseIdSetter("");
            moduleController.setCourseNameSetter("");
            moduleController.setCreditHourSetter("");
            moduleController.setCourseCategorySetter("University Course");
            moduleController.setCourseYearSetter("All");
            moduleController.setCourseSemSetter("All");
            moduleController.setMuetBandSetter("All");
            moduleController.setNationalitySetter("All");
            moduleController.setProgrammeSetter("All");
        } catch (Exception e) {
                
        }
            myController.showPopupStage(searchScreen, "/assignment_MayaFOP/Module.fxml");
            showing = myController.getShowing();   
        }
    }
        
    public void clearMemory(){
        courseIDarray.clear();
        occurenceID.clear();
        courseNames.clear();
        occurenceIDcheck.clear();
        courseIDcheck.clear();
        creditHour.clear();
        dayCheck.clear();
        startTimeCheck.clear();
        endTimeCheck.clear();
        vCourseNames.getChildren().clear();
    }
    
    public void clearMemoryWhenLogout(){
        clearMemory();
        totalCreditHours = 0;
        credithourcheck = 0;
        i = 0;
        occIDStaff.clear();
    }
    
    public static boolean isOverlapping(Date start1, Date end1, Date start2, Date end2) {
        return start1.before(end2) && start2.before(end1);
    }
    
    public String getCourseIDcheck(int i) {
        return courseIDarray.get(i);
    }

    public String getOccurenceID(int i) {
        return occurenceID.get(i);
    }

    public String getCourseNames(int i) {
        return courseNames.get(i);
    }
    
    public boolean getisConfirmedtake() {
        return confirmedtake;
    }
    
    public void setConfirmedtake(boolean confirmedtake) {
        this.confirmedtake = confirmedtake;
    }
    
    public static ArrayList<String> getCourseIDcheck() {
        return courseIDarray;
    }

    public static void setCourseIDcheck(ArrayList<String> courseIDcheck) {
        searchModule.courseIDarray = courseIDcheck;
    }

    public static ArrayList<String> getOccurenceID() {
        return occurenceID;
    }

    public static void setOccurenceID(ArrayList<String> occurenceID) {
        searchModule.occurenceID = occurenceID;
    }

    public static ArrayList<String> getCourseNames() {
        return courseNames;
    }

    public static void setConfirmeddelete(boolean confirmeddelete) {
        searchModule.confirmeddelete = confirmeddelete;
    }
    
    public static void setCourseNames(ArrayList<String> courseNames) {
        searchModule.courseNames = courseNames;
    }
    
    public String getOccIDCheck() {
        return occIDCheck;
    }

    public boolean isEditingMode() {
        return editingMode;
    }

    public void setEditingMode(boolean editingMode) {
        this.editingMode = editingMode;
    }
    
    
    
}
