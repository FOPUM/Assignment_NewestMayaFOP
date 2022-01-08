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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    private Label courseDetailsLabel;
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
    private Label creditHourLabel;
    
    ArrayList<pickedModuleModel> courses = new ArrayList<>();    
    
    ArrayList<Integer> creditHour = new ArrayList<Integer>();
    
    private static ArrayList<String> courseIDcheck = new ArrayList<String>();
    private static ArrayList<String> occurenceIDcheck = new ArrayList<String>();
    private static ArrayList<String> occurenceID = new ArrayList<String>();
    private static ArrayList<String> courseNames = new ArrayList<String>();

        
    Node[] nodes = new Node[10];
    public int totalCreditHours;
    private int i = 0;
    private static boolean confirmedtake;
    
    String matric_num = loginControl.getUsername();
    char accStatus = loginControl.getAccStatus();
    
    String occIDCheck = new String();

    
    
    private String confirmedcourses = "SELECT matric_num, occ_id\n" +
                                        "FROM student_take_course\n" +
                                        "WHERE matric_num='" + matric_num + "'";
    
    private String studentQualifications = "SELECT student_muet_band,student_nationality,\n" +
                                        "student_studysem,student_studyyear,student_programme\n" +
                                        "FROM student\n" +
                                        "WHERE matric_num='"+matric_num+"'";
    

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
            //Query for current registed course in an array
            ResultSet queryResultForCheck = connectDB.createStatement().executeQuery(confirmedcourses);
            while(queryResultForCheck.next()) {
                String occIDcheck = queryResultForCheck.getString("occ_id");
                occurenceIDcheck.add(occIDcheck);
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
            }
            
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
                        "INNER JOIN tutorial ON occ.tutorial_id=tutorial.tutorial_id\n" +
                        "INNER JOIN staff_teach_tutorial ON occ.tutorial_id=staff_teach_tutorial.tutorial_id\n" +
                        "INNER JOIN staff ON staff.staff_id=staff_teach_tutorial.staff_id) AS tuto\n" +
                        "\n" +
                        "INNER JOIN \n" +
                        "\n" +
                        "(SELECT occ.occ_id AS occ2,\n" +
                        "lecture.lecture_day AS lectureDay, lecture.lecture_start_time AS lectureStartTime,lecture.lecture_end_time AS lectureEndTime,\n" +
                        "lecture.lecture_location AS lectLocation, staff.staff_name AS lectStaff\n" +
                        "FROM occ \n" +
                        "JOIN course ON course.course_id=SUBSTR(occ.occ_id, 1, 7)\n" +
                        "INNER JOIN lecture ON lecture.lecture_id=occ.lecture_id\n" +
                        "INNER JOIN staff_teach_lecture ON occ.lecture_id=staff_teach_lecture.lecture_id\n" +
                        "INNER JOIN staff on staff.staff_id=staff_teach_lecture.staff_id) AS lec\n" +
                        "\n" +
                        "ON tuto.occID=lec.occ2) AS lectuto\n" +
                        "\n" +
                        "INNER JOIN lab ON lab.lab_id=lectuto.labID\n" +
                        "INNER JOIN staff_teach_lab ON staff_teach_lab.lab_id=lectuto.labID\n" +
                        "INNER JOIN staff ON staff_teach_lab.staff_id=staff.staff_id";
                //</editor-fold>
            if(accStatus == 'S'){
                //<editor-fold defaultstate="collapsed" desc="String for student">
                course = "SELECT\n" +
                        "courseID, courseName, creditHour,\n" +
                        "occID, occName, occCapacity, \n" +
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
                        "occ.occ_name AS occName,occ.occ_id AS occID, occ.lab_id AS labID, occ.occ_capacity AS occCapacity,\n" +
                        "tutorial.tutorial_day AS tutoDay, tutorial.tutorial_start_time AS tutoStartTime, tutorial.tutorial_end_time AS tutoEndTime,\n" +
                        "tutorial.tutorial_location AS tutoLocation, staff.staff_name AS tutoStaff\n" +
                        "\n" +
                        "FROM occ \n" +
                        "JOIN course ON course.course_id=SUBSTR(occ.occ_id, 1, 7)\n" +
                        "INNER JOIN tutorial ON occ.tutorial_id=tutorial.tutorial_id\n" +
                        "INNER JOIN staff_teach_tutorial ON occ.tutorial_id=staff_teach_tutorial.tutorial_id\n" +
                        "INNER JOIN staff ON staff.staff_id=staff_teach_tutorial.staff_id) AS tuto\n" +
                        "\n" +
                        "INNER JOIN \n" +
                        "\n" +
                        "(SELECT occ.occ_id AS occ2,\n" +
                        "lecture.lecture_day AS lectureDay, lecture.lecture_start_time AS lectureStartTime,lecture.lecture_end_time AS lectureEndTime,\n" +
                        "lecture.lecture_location AS lectLocation, staff.staff_name AS lectStaff\n" +
                        "FROM occ \n" +
                        "JOIN course ON course.course_id=SUBSTR(occ.occ_id, 1, 7)\n" +
                        "INNER JOIN lecture ON lecture.lecture_id=occ.lecture_id\n" +
                        "INNER JOIN staff_teach_lecture ON occ.lecture_id=staff_teach_lecture.lecture_id\n" +
                        "INNER JOIN staff on staff.staff_id=staff_teach_lecture.staff_id) AS lec\n" +
                        "\n" +
                        "ON tuto.occID=lec.occ2) AS lectuto\n" +
                        "\n" +
                        "INNER JOIN lab ON lab.lab_id=lectuto.labID\n" +
                        "INNER JOIN staff_teach_lab ON staff_teach_lab.lab_id=lectuto.labID\n" +
                        "INNER JOIN staff ON staff_teach_lab.staff_id=staff.staff_id\n" +
                        "WHERE \n" +
                        "(courseCategory='FCC' OR courseCategory ='"+studentProgramme.toUpperCase()+"') AND\n" +
                        "(courseYear='ALL' OR courseYear='"+studentYear+"') AND\n" +
                        "(courseSem='ALL' OR courseSem='"+studentSem+"') AND\n" +
                        "(muetBand='ALL' OR muetBand='"+studentBand+"') AND \n" +
                        "(courseNationality='ALL' OR courseNationality='"+studentNationality.toUpperCase()+"') AND\n" +
                        "(courseProgramme='ALL') ";
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
                        "INNER JOIN tutorial ON occ.tutorial_id=tutorial.tutorial_id\n" +
                        "INNER JOIN staff_teach_tutorial ON occ.tutorial_id=staff_teach_tutorial.tutorial_id\n" +
                        "INNER JOIN staff ON staff.staff_id=staff_teach_tutorial.staff_id) AS tuto\n" +
                        "\n" +
                        "INNER JOIN \n" +
                        "\n" +
                        "(SELECT occ.occ_id AS occ2,\n" +
                        "lecture.lecture_day AS lectureDay, lecture.lecture_start_time AS lectureStartTime,lecture.lecture_end_time AS lectureEndTime,\n" +
                        "lecture.lecture_location AS lectLocation, staff.staff_name AS lectStaff\n" +
                        "FROM occ \n" +
                        "JOIN course ON course.course_id=SUBSTR(occ.occ_id, 1, 7)\n" +
                        "INNER JOIN lecture ON lecture.lecture_id=occ.lecture_id\n" +
                        "INNER JOIN staff_teach_lecture ON occ.lecture_id=staff_teach_lecture.lecture_id\n" +
                        "INNER JOIN staff on staff.staff_id=staff_teach_lecture.staff_id) AS lec\n" +
                        "\n" +
                        "ON tuto.occID=lec.occ2) AS lectuto\n" +
                        "\n" +
                        "INNER JOIN lab ON lab.lab_id=lectuto.labID\n" +
                        "INNER JOIN staff_teach_lab ON staff_teach_lab.lab_id=lectuto.labID\n" +
                        "INNER JOIN staff ON staff_teach_lab.staff_id=staff.staff_id";
                //</editor-fold>
            }
            ResultSet courseQueryOutput = connectDB.createStatement().executeQuery(course);
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
            courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
            courseOccurenceColumn.setCellValueFactory(new PropertyValueFactory<>("occName"));
            courseTableView.setItems(courseSearchModelObservableList);
            search();

            courseTableView.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    MiscFunc misc = new MiscFunc();
                    
                    occIDCheck =courseTableView.getSelectionModel().getSelectedItem().getOccID();
                    
                    courseCodeLabel.setText(courseTableView.getSelectionModel().getSelectedItem().getCourseID());
                    courseNameLabel.setText(misc.upperLetter(courseTableView.getSelectionModel().getSelectedItem().getCourseName()));
                    creditsLabel.setText("Credits: " + courseTableView.getSelectionModel().getSelectedItem().getCreditHour());
                    occurenceLabel.setText("Occurence: " + courseTableView.getSelectionModel().getSelectedItem().getOccName().substring(3));
                    
                    String lectureTime = misc.formatDay(courseTableView.getSelectionModel().getSelectedItem().getLectDay()) + "  "
                            + misc.formatTime(courseTableView.getSelectionModel().getSelectedItem().getLectStartTime()) + "-" 
                            + misc.formatTime(courseTableView.getSelectionModel().getSelectedItem().getLectEndTime());
                    lectureTimeLabel.setText(lectureTime);
                    lectureLecturerLabel.setText(misc.upperLetter(courseTableView.getSelectionModel().getSelectedItem().getLectStaff()));
                    
                    String tutorialTime = misc.formatDay(courseTableView.getSelectionModel().getSelectedItem().getTutoDay()) + "  "
                            + misc.formatTime(courseTableView.getSelectionModel().getSelectedItem().getTutoStartTime()) + "-" 
                            + misc.formatTime(courseTableView.getSelectionModel().getSelectedItem().getTutoEndTime());
                    tutorialTimeLabel.setText(tutorialTime);
                    tutorialLecturerLabel.setText(misc.upperLetter(courseTableView.getSelectionModel().getSelectedItem().getTutoStaff()));
                    
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
            });

        } catch (SQLException e) {
            Logger.getLogger(searchModule.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
 
    }

    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }

    public void goToModuleConfirmation(ActionEvent event) {
        
        if (!showing) {
            myController.showPopupStage(searchScreen, "/assignment_MayaFOP/moduleConfirmationMessage.fxml");
            showing = myController.getShowing();   
        }
        if(confirmedtake){
            confirmedtaken();
        }
    }

    //add course to the right panel
    public void addModule(ActionEvent event) throws Exception { 
        totalCreditHours = 0;
        int credithours = Integer.parseInt(courseTableView.getSelectionModel().getSelectedItem().getCreditHour());
        String courseID = "" + courseTableView.getSelectionModel().getSelectedItem().getCourseID();
        String occID = "" + courseTableView.getSelectionModel().getSelectedItem().getOccID();
        String courseName = "" + courseTableView.getSelectionModel().getSelectedItem().getCourseName();
        
        if(courseIDcheck.contains(courseID) || occurenceIDcheck.contains(occID)){
            warningLabel.setText("Already picked!");
        }else{
            warningLabel.setText("");
            if (totalCreditHours + Integer.parseInt(courseTableView.getSelectionModel().getSelectedItem().getCreditHour()) <= 22 ) {
                boolean check = true;
                try{

                pickedModuleModel addingCourse = new pickedModuleModel(courseID);


                    if (courseIDcheck.contains(courseID)) {
                        check=false;
                    }else{
                        System.out.println("Courseidcheck added");
                        courseIDcheck.add(courseID);
                        System.out.println(courseIDcheck.get(0));
                    }
                    if (check) {
                        courses.add(addingCourse);
                        occurenceID.add(occID);
                        courseNames.add(courseName);
                        System.out.println(addingCourse + " Has been add");
                    }
                    else{
                        System.out.println("The coursed already picked");

                    }

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/Assignment_MayaFOP/pickedModule.fxml"));
                    nodes[i] = loader.load();
                    final int h = i;

                    pickedModuleController controller = loader.getController();
                    controller.setCourseName(courses.get(i).getCourseIDLabel());


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
                        totalCreditHours =0;
                        creditHour.forEach((hour)-> totalCreditHours+=hour);
                        creditHourLabel.setText("Credits Hours: " + totalCreditHours);
                    });

                    }catch (IOException e) {
                        e.printStackTrace();
                    }

                creditHour.add(credithours);
                creditHour.forEach((hour)-> totalCreditHours+=hour);
                creditHourLabel.setText("Credits Hours: " + totalCreditHours);


            } else {
                warningLabel.setText("Total credit hours exceed 22 hours!");
            }
        }    
    }
    
    public void deleteModule(int i) {
        courseIDcheck.remove(i);
        courses.remove(i);
        occurenceID.remove(i);
        courseNames.remove(i);
        vCourseNames.getChildren().remove(nodes[i]);
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

                String searchKeyword = newValue.toLowerCase();

                if (courseSearchModel.getCourseName().toLowerCase().indexOf(searchKeyword) > -1) {
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
    
    public void confirmedtaken(){
        ResultSet addModuleQuery;
        
        try {    
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Assignment_MayaFOP/login.fxml"));
                loader.load();
                loginControl = loader.getController();
            } catch (IOException ex) {
                Logger.getLogger(searchModule.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            for (int j = 0; j < courseIDcheck.size(); j++) {
                String course_id = courseIDcheck.get(j);
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
    
    
    //lazy do le
    public void editCourse(ActionEvent event){
        if (!showing) {
            myController.showPopupStage(searchScreen, "/assignment_MayaFOP/Module.fxml");
            showing = myController.getShowing(); 
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Assignment_MayaFOP/ModuleController.fxml"));
                loader.load();
                ModuleController module = loader.getController();
                
                module.setCourseIDTextField("" + courseTableView.getSelectionModel().getSelectedItem().getCourseID());
                module.setCourseNameTextField("" + courseTableView.getSelectionModel().getSelectedItem().getCourseName());
                module.setCreditHourTextField("" + courseTableView.getSelectionModel().getSelectedItem().getCreditHour());
//                module.setProgrammeComboBox(courseCategoryComboBox);
//                module.setCourseSemComboBox(courseCategoryComboBox);
//                module.setMuetBandComboBox(courseCategoryComboBox);
//                module.setCourseYearComboBox(courseCategoryComboBox);
//                module.setCourseCategoryComboBox(courseCategoryComboBox);
//                module.setNationalityComboBox(courseCategoryComboBox);
            } catch (IOException e) {
            }

//            occurenceLabel.setText("" + courseTableView.getSelectionModel().getSelectedItem().getOccName());
//            String lectureTime = courseTableView.getSelectionModel().getSelectedItem().getLectDay()
//                    + courseTableView.getSelectionModel().getSelectedItem().getLectStartTime()
//                    + courseTableView.getSelectionModel().getSelectedItem().getLectEndTime();
//            lectureTimeLabel.setText(lectureTime);
//            lectureLecturerLabel.setText("" + courseTableView.getSelectionModel().getSelectedItem().getLectStaff());
//            String tutorialTime = courseTableView.getSelectionModel().getSelectedItem().getTutoDay()
//                    + courseTableView.getSelectionModel().getSelectedItem().getTutoStartTime()
//                    + courseTableView.getSelectionModel().getSelectedItem().getTutoEndTime();
//            tutorialTimeLabel.setText(tutorialTime);
//            tutorialLecturerLabel.setText("" + courseTableView.getSelectionModel().getSelectedItem().getTutoStaff());
//            String labTime = courseTableView.getSelectionModel().getSelectedItem().getLabDay()
//                    + courseTableView.getSelectionModel().getSelectedItem().getLabStartTime()
//                    + courseTableView.getSelectionModel().getSelectedItem().getLabEndTime();
//            labTimeLabel.setText(labTime);
//            labLecturerLabel.setText("" + courseTableView.getSelectionModel().getSelectedItem().getLabStaff());
        }
    }
    
    public void removeModule(ActionEvent event){
        if (!showing) {
            myController.showPopupStage(searchScreen, "/assignment_MayaFOP/removeModule.fxml");
            showing = myController.getShowing();   
        }
    }
    
    public void addNewModule(ActionEvent event){
        if (!showing) {
            myController.showPopupStage(searchScreen, "/assignment_MayaFOP/Module.fxml");
            showing = myController.getShowing();   
        }
    }
    
    public String getCourseIDcheck(int i) {
        return courseIDcheck.get(i);
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
        return courseIDcheck;
    }

    public static void setCourseIDcheck(ArrayList<String> courseIDcheck) {
        searchModule.courseIDcheck = courseIDcheck;
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

    public static void setCourseNames(ArrayList<String> courseNames) {
        searchModule.courseNames = courseNames;
    }
    
    public String getOccIDCheck() {
        return occIDCheck;
    }
    
    
}
