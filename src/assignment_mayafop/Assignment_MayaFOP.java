/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assignment_mayafop;

import java.lang.ModuleLayer.Controller;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author kwany
 */
public class Assignment_MayaFOP extends Application{
    public static String loginScreen = "login";
    public static String homepageScreen = "home_page";
    public static String searchScreen = "search_module";
    public static String timetableScreen = "timetable";
    public static String dashboardScreen = "dashboard";
    public static String userStudentScreen = "user_account";
    public static String userStaffScreen = "user_account_staff";
    public static String controlCenter = "control_center";
    
    public static String loginScreenFile = "login.fxml";
    public static String homepageScreenFile = "home_page.fxml";
    public static String searchScreenFile = "search_module.fxml";
    public static String timetableScreenFile = "timetable.fxml";
    public static String dashboardScreenFile = "dashboard.fxml";
    public static String userStudentScreenFile = "user_account.fxml";
    public static String userStaffScreenFile = "user_account_staff.fxml";
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Start login page      
        
        ScreenController mainContainer = new ScreenController();
        mainContainer.loadScreen(Assignment_MayaFOP.loginScreen, Assignment_MayaFOP.loginScreenFile);
        mainContainer.loadScreen(Assignment_MayaFOP.homepageScreen, Assignment_MayaFOP.homepageScreenFile);
        mainContainer.loadScreen(Assignment_MayaFOP.searchScreen, Assignment_MayaFOP.searchScreenFile);
        mainContainer.loadScreen(Assignment_MayaFOP.timetableScreen, Assignment_MayaFOP.timetableScreenFile);
        mainContainer.loadScreen(Assignment_MayaFOP.dashboardScreen, Assignment_MayaFOP.dashboardScreenFile);
        mainContainer.loadScreen(Assignment_MayaFOP.controlCenter, Assignment_MayaFOP.userStudentScreenFile);
        mainContainer.loadScreen(Assignment_MayaFOP.controlCenter, Assignment_MayaFOP.userStaffScreenFile);
        
        mainContainer.setScreen(Assignment_MayaFOP.loginScreen);
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        Image icon = new Image("/Assignment_MayaFOP/icon.png");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(scene);
        primaryStage.show(); 

    }

}
