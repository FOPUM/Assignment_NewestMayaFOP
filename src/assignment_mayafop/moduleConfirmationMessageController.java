/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Ming
 */
public class moduleConfirmationMessageController implements Initializable, ControlledScreen{
    ScreenController myController;
    animation Animation;
    searchModule SMControl;
    
    @FXML 
    private VBox vCourseConfirmed;
    ArrayList<confirmPickedModuleModel> confirmCourses = new ArrayList<>();    
    
    Node[] nodes = new Node[10];
    private int i =0;
    
    
    boolean upScreenStatus = false;
    
    @FXML
    private AnchorPane moduleConfirmationScreen;
    @FXML
    private Button confirmButton;
    @FXML
    private Button exit_button;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Animation = new animation();
        
        
        //SMControl is null
        if(!upScreenStatus){
            Animation.fading(moduleConfirmationScreen);
        }
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Assignment_MayaFOP/searchModule.fxml"));
            loader.load();
            SMControl= loader.getController();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
        try {
            
            confirmCourses.clear();
            for (int j = 0; j < SMControl.getCourseNames().size(); j++) {
                confirmPickedModuleModel adder = new confirmPickedModuleModel(SMControl.getCourseIDcheck(j),SMControl.getCourseNames(j),SMControl.getOccurenceID(j));
                confirmCourses.add(adder);
            }
            
            Node[] nodes = new Node[confirmCourses.size()];
            
            for (int j = 0; j < nodes.length; j++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Assignment_MayaFOP/confirmPickedModule.fxml"));
                nodes[j] = loader.load();
                
                confirmedPickedModuleController controller = loader.getController();
                controller.setCourseIDLabel(confirmCourses.get(j).getCourseIDLabel());
                controller.setCourseNameLabel(confirmCourses.get(j).getCourseNameLabel());
                controller.setOccLabel(confirmCourses.get(j).getOccLabel());
                
                final int h = j;
                vCourseConfirmed.getChildren().add(nodes[j]);
            }

        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(moduleConfirmationMessageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
      
    }
    
    public void confirmModule(ActionEvent event) {
        //Click on exit button to exit       
        SMControl.setConfirmedtake(true);
        System.out.println("After set: " + SMControl.getisConfirmedtake());
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();     
    }
    
    public void exitButton(ActionEvent event) {
        //Click on exit button to exit       
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    } 
    
//    
}
