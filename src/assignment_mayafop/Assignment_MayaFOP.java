/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assignment_mayafop;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author kwany
 */
public class Assignment_MayaFOP extends Application{
    boolean pp = true;
    
    public static String loginScreen = "login";
    public static String homepageScreen = "homePage";
    public static String searchScreen = "searchModule";
    public static String timetableScreen = "timetable";
    public static String dashboardScreen = "dashboard";
    public static String chatScreen = "chat";
    public static String userStudentScreen = "userAccount";
    public static String userStaffScreen = "userAccount";
    public static String controlCenter = "controlCenter";
    public static String validateLogout = "validateLogout";
    public static String registerStudent = "registerControlStudent";
    public static String registerStaff = "registerControlStaff";
    public static String Module = "ModuleController";
    public static String ModuleNext = "ModuleNextController";
    
    public static String loginScreenFile = "/Assignment_MayaFOP/login.fxml";
    public static String homepageScreenFile = "/Assignment_MayaFOP/homePage.fxml";
    public static String searchScreenFile = "/Assignment_MayaFOP/searchModule.fxml";
    public static String timetableScreenFile = "/Assignment_MayaFOP/timetable.fxml";
    public static String dashboardScreenFile = "/Assignment_MayaFOP/dashboard.fxml";
    public static String chatScreenFile = "/Assignment_MayaFOP/chat.fxml";
    public static String userStudentScreenFile = "/Assignment_MayaFOP/userAccount.fxml";
    public static String userStaffScreenFile = "/Assignment_MayaFOP/userAccountStaff.fxml";
    public static String navigationFile = "/Assignment_MayaFOP/navigationBar.fxml";
    public static String signupStudentFile = "/Assignment_MayaFOP/signupStudent.fxml";
    public static String signupStaffFile = "/Assignment_MayaFOP/signupStaff.fxml";
    public static String validateLogoutFile = "/Assignment_MayaFOP/logoutConfirmation.fxml";
    public static String moduleScreenFile = "/Assignment_MayaFOP/Module.fxml";
    public static String moduleNextScreenFile = "/Assignment_MayaFOP/ModuleNext.fxml";
    
    
    
//    public static String announcementScreenFile = "/Assignment_MayaFOP/announcement.fxml";
//    public static String moduleConfirmationScreenFile = "/Assignment_MayaFOP/moduleConfirmationMessage.fxml";
//    public static String logoutConfirmationScreenFile = "/Assignment_MayaFOP/logoutConfirmation.fxml";
//    public static String registeredModuleScreenFile = "/Assignment_MayaFOP/registeredModule.fxml";

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Start login page      
        
        //load main screen
        ScreenController mainContainer = new ScreenController();
        mainContainer.loadScreen(Assignment_MayaFOP.loginScreen, Assignment_MayaFOP.loginScreenFile);
        mainContainer.loadScreen(Assignment_MayaFOP.registerStudent, Assignment_MayaFOP.signupStudentFile);
        mainContainer.loadScreen(Assignment_MayaFOP.registerStaff, Assignment_MayaFOP.signupStaffFile);
        mainContainer.loadScreen(Assignment_MayaFOP.homepageScreen, Assignment_MayaFOP.homepageScreenFile);
        mainContainer.loadScreen(Assignment_MayaFOP.searchScreen, Assignment_MayaFOP.searchScreenFile);
        mainContainer.loadScreen(Assignment_MayaFOP.timetableScreen, Assignment_MayaFOP.timetableScreenFile);
        mainContainer.loadScreen(Assignment_MayaFOP.dashboardScreen, Assignment_MayaFOP.dashboardScreenFile);
        mainContainer.loadScreen(Assignment_MayaFOP.chatScreen, Assignment_MayaFOP.chatScreenFile);
        mainContainer.loadScreen(Assignment_MayaFOP.userStudentScreen, Assignment_MayaFOP.userStudentScreenFile);
        mainContainer.loadScreen(Assignment_MayaFOP.userStaffScreen, Assignment_MayaFOP.userStaffScreenFile);
        mainContainer.loadScreen(Assignment_MayaFOP.controlCenter, Assignment_MayaFOP.navigationFile);
        mainContainer.loadScreen(Assignment_MayaFOP.validateLogout, Assignment_MayaFOP.validateLogoutFile);
        mainContainer.loadScreen(Assignment_MayaFOP.Module, Assignment_MayaFOP.moduleScreenFile);
        mainContainer.loadScreen(Assignment_MayaFOP.ModuleNext, Assignment_MayaFOP.moduleNextScreenFile);
        
        haa haha = new haa();
        haha.query();
//        mainContainer.setScreen(Assignment_MayaFOP.loginScreen);
//        
//        Group root = new Group();
//        root.getChildren().addAll(mainContainer);
//        Scene scene = new Scene(root);
//        Image icon = new Image("/Assignment_MayaFOP/Images/icon.png");
//        primaryStage.initStyle(StageStyle.UNDECORATED);
//        primaryStage.getIcons().add(icon);
//        primaryStage.setScene(scene);
//        primaryStage.show(); 

    }
    
    
}

