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

/**
 *
 * @author Ming
 */
public class searchModule implements Initializable, ControlledScreen {

    ScreenController myController;
    
    

    @FXML
    private BorderPane searchScreen;

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
    private VBox vCourseNames;
    @FXML
    private Label warningLabel;
    
    ArrayList<pickedModuleModel> courses = new ArrayList<>();
    private int i = 0;

    private String course = "SELECT \n"
            + "courseID, courseName, creditHour,\n"
            + "occName,\n"
            + "tutoDay, tutoStartTime, tutoEndTime,\n"
            + "tutoStaff,\n"
            + "lectureDay, lectureStartTime,lectureEndTime,\n"
            + "lectStaff\n"
            + "FROM\n"
            + "(SELECT * FROM\n"
            + "(SELECT course.course_id AS courseID, course.course_name AS courseName, course.credit_hour AS creditHour,\n"
            + "occ.occ_name AS occName,occ.occ_id AS occ1,\n"
            + "tutorial.tutorial_day AS tutoDay, tutorial.tutorial_start_time AS tutoStartTime, tutorial.tutorial_end_time AS tutoEndTime,\n"
            + "staff.staff_name AS tutoStaff\n"
            + "FROM occ \n"
            + "JOIN course ON course.course_id=SUBSTR(occ.occ_id, 1, 7)\n"
            + "INNER JOIN tutorial ON occ.tutorial_id=tutorial.tutorial_id\n"
            + "INNER JOIN staff_teach_tutorial ON occ.tutorial_id=staff_teach_tutorial.tutorial_id\n"
            + "INNER JOIN staff on staff.staff_id=staff_teach_tutorial.staff_id) AS tuto\n"
            + "\n"
            + "INNER JOIN \n"
            + "\n"
            + "(SELECT occ.occ_id AS occ2,\n"
            + "lecture.lecture_day AS lectureDay, lecture.lecture_start_time AS lectureStartTime,lecture.lecture_end_time AS lectureEndTime,\n"
            + "staff.staff_name AS lectStaff\n"
            + "FROM occ \n"
            + "JOIN course ON course.course_id=SUBSTR(occ.occ_id, 1, 7)\n"
            + "INNER JOIN lecture ON lecture.lecture_id=occ.lecture_id\n"
            + "INNER JOIN staff_teach_lecture ON occ.lecture_id=staff_teach_lecture.lecture_id\n"
            + "INNER JOIN staff on staff.staff_id=staff_teach_lecture.staff_id) AS lec\n"
            + "\n"
            + "ON tuto.occ1=lec.occ2) AS total";

    databaseConnection connectNow = new databaseConnection();
    Connection connectDB = connectNow.getConnection();

    private int totalCreditHours = 0;

    ObservableList<modelCourse> courseSearchModelObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Statement statementCourse = connectDB.createStatement();
            ResultSet courseQueryOutput = statementCourse.executeQuery(course);
            while (courseQueryOutput.next()) {
                String courseID = courseQueryOutput.getString("courseID");
                String courseName = courseQueryOutput.getString("courseName");
                String creditHour = courseQueryOutput.getString("creditHour");
                String occName = courseQueryOutput.getString("occName");
                String tutoDay = courseQueryOutput.getString("tutoDay");
                String tutoStartTime = courseQueryOutput.getString("tutoStartTime");
                String tutoEndTime = courseQueryOutput.getString("tutoEndTime");
                String tutoStaff = courseQueryOutput.getString("tutoStaff");
                String lectureDay = courseQueryOutput.getString("lectureDay");
                String lectureStartTime = courseQueryOutput.getString("lectureStartTime");
                String lectureEndTime = courseQueryOutput.getString("lectureEndTime");
                String lectStaff = courseQueryOutput.getString("lectStaff");

                // Populate the ObservableList
                courseSearchModelObservableList.add(new modelCourse(courseID, courseName, creditHour, occName, tutoDay, tutoStartTime, tutoEndTime, tutoStaff, lectureDay, lectureStartTime, lectureEndTime, lectStaff));
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

                    courseCodeLabel.setText("" + courseTableView.getSelectionModel().getSelectedItem().getCourseID());
                    courseNameLabel.setText("" + courseTableView.getSelectionModel().getSelectedItem().getCourseName());
                    creditsLabel.setText("" + courseTableView.getSelectionModel().getSelectedItem().getCreditHour());
                    occurenceLabel.setText("" + courseTableView.getSelectionModel().getSelectedItem().getOccName());
                    String lectureTime = courseTableView.getSelectionModel().getSelectedItem().getLectureDay()
                            + courseTableView.getSelectionModel().getSelectedItem().getLectureStartTime()
                            + courseTableView.getSelectionModel().getSelectedItem().getLectureEndTime();
                    lectureTimeLabel.setText(lectureTime);
                    lectureLecturerLabel.setText("" + courseTableView.getSelectionModel().getSelectedItem().getLectStaff());
                    String tutorialTime = courseTableView.getSelectionModel().getSelectedItem().getTutoDay()
                            + courseTableView.getSelectionModel().getSelectedItem().getTutoStartTime()
                            + courseTableView.getSelectionModel().getSelectedItem().getTutoEndTime();
                    tutorialTimeLabel.setText(tutorialTime);
                    tutorialLecturerLabel.setText("" + courseTableView.getSelectionModel().getSelectedItem().getTutoStaff());

                }
            });

        } catch (SQLException e) {
            Logger.getLogger(searchModule.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        
        
//        try{
//            String courseID = "" + courseTableView.getSelectionModel().getSelectedItem().getCourseID();
//            List<pickedModuleModel> courses = new ArrayList<>();
//            
//            courses.add(new pickedModuleModel(courseID));
//            
//            Node[] nodes = new Node[5];
//            
////            for (int i = 0; i < nodes.length; i++) {
//                FXMLLoader loader = new FXMLLoader();
//                nodes[i] = FXMLLoader.load(getClass().getResource("/Assignment_MayaFOP/pickedModule.fxml"));
//                final int h = i;
//                
//                pickedModuleController controller = loader.getController();
//                controller.setCourseName(courses.get(i).getCourseIDLabel());
//                
//                nodes[i].setOnMouseEntered(evt -> {
//                    //add effect
//                    nodes[h].setStyle("-fx-background-color: #084654");
//                });
//                nodes[i].setOnMouseExited(evt -> {
//                    //add effect
//                    nodes[h].setStyle("-fx-background-color: #FFFFFF");
//                });
//                nodes[i].setOnMousePressed(evt -> {
//                    //add effect
//                    nodes[h].setStyle("-fx-background-color: #000000");
//                });
//                vCourseNames.getChildren().add(nodes[i]);
//                i++;
//                
////            }
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }

    public void goToModuleConfirmation(ActionEvent event) {
        myController = new ScreenController();
        myController.showPopupStage(searchScreen, "/assignment_MayaFOP/moduleConfirmationMessage.fxml");
    }

    public void addModule(ActionEvent event) { 
        int credithours = Integer.parseInt(courseTableView.getSelectionModel().getSelectedItem().getCreditHour());
        if (totalCreditHours + Integer.parseInt(courseTableView.getSelectionModel().getSelectedItem().getCreditHour()) < 22) {

            try{
            String courseID = "" + courseTableView.getSelectionModel().getSelectedItem().getCourseID();
            
            
            courses.add(new pickedModuleModel(courseID));
            
            
            Node[] nodes = new Node[10];
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Assignment_MayaFOP/pickedModule.fxml"));
                nodes[i] = loader.load();
                final int h = i;
                
                pickedModuleController controller = loader.getController();
                controller.setCourseName(courses.get(i).getCourseIDLabel());
                
                nodes[i].setOnMouseEntered(evt -> {
                    //add effect
                    nodes[h].setStyle("-fx-background-color: #084654");
                });
                nodes[i].setOnMouseExited(evt -> {
                    //add effect
                    nodes[h].setStyle("-fx-background-color: #FFFFFF");
                });
                nodes[i].setOnMousePressed(evt -> {
                    //add effect
                    nodes[h].setStyle("-fx-background-color: #000000");
                });
                vCourseNames.getChildren().add(nodes[i]);
                i++;

                }catch (IOException e) {
                    e.printStackTrace();
                }

            
            totalCreditHours += credithours;
            
        } else {
            warningLabel.setText("Total credit hours exceed 22 hours!");
        }
    }

    public void search() {
        // Initialise filtered list
        FilteredList<modelCourse> filteredData = new FilteredList<>(courseSearchModelObservableList, b -> true);

        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(courseSearchModel -> {

                // If no values input, no change to the list
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
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
}
