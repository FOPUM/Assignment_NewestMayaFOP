/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author kwany
 */
public class ScreenController extends StackPane{
    boolean showing;
    
    
    
    private HashMap<String, Node> screens = new HashMap<>();
    
    
    public ScreenController(){
        super();
    }
    
    public void addScreen(String name, Node screen){
        screens.put(name,screen);
    }
   
    public Node getScreen(String name){
        return screens.get(name);
    }
    
    public boolean loadScreen(String name, String resource){
        try{
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
            Parent loadScreen = (Parent) myLoader.load();
            ControlledScreen myScreenController = ((ControlledScreen) myLoader.getController());
            myScreenController.setScreenParent(this);
            addScreen(name,loadScreen);
            return true;
        }catch(Exception e){
        System.out.println(e.getMessage());
        return false;
    }
        
    }
        
        public boolean setStage (final String name) throws IOException{
         if (screens.get(name)!= null){
                final DoubleProperty opacity = opacityProperty();
                
                if(!getChildren().isEmpty()){
                    
                }else{
                    getChildren().add(screens.get(name));
                }
                return true;
            }else{
                    System.out.println("Screen hasn't been loaded");
                    return false;
                }
            }
    
    public boolean setScreen (final String name){
        if (screens.get(name)!= null){
            

            if(!getChildren().isEmpty()){
                        getChildren().remove(0);
                        getChildren().add(0, screens.get(name));
            }else{
                getChildren().add(screens.get(name));
            }
            return true;
        }else{
                System.out.println("Screen hasn't been loaded");
                return false;
            }
//           
        }

    public boolean unloadScreen(String name){
        if(screens.remove(name) == null){
            System.out.println("Screen didn't exist");
            return false;
        }else{
            return true;
        }
    }
    
    
    public void removeScreen(String name){
        getChildren().remove(screens.get(name));
        System.out.println(name + " has been removed");
    }
    
    public void showPopupStage(BorderPane screen, String resource){
        if (showing) {
            System.out.println("Already showing");
            return;
        }
        else if (!showing){
            try {
                System.out.println("not showing");
                BoxBlur boxBlur = new BoxBlur();
                boxBlur.setWidth(10);
                boxBlur.setHeight(10);
                boxBlur.setIterations(3);
                screen.setEffect(boxBlur);
                setShowing(true);
                System.out.println(showing);
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(resource));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED);
                
                stage.setScene(new Scene(root));
                stage.setAlwaysOnTop(true);
                stage.showAndWait();
                setShowing(false);
                if (!showing) {
                    screen.setEffect(null);
                }
                
            }catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    public void showPopupStage(AnchorPane screen, String resource){
        if (showing) {
            System.out.println("Already showing");
            return;
        }
        else if (!showing){
            try {
                System.out.println("not showing");
                BoxBlur boxBlur = new BoxBlur();
                boxBlur.setWidth(10);
                boxBlur.setHeight(10);
                boxBlur.setIterations(3);
                screen.setEffect(boxBlur);
                setShowing(true);
                System.out.println(showing);
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(resource));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED);
                
                stage.setScene(new Scene(root));
                stage.setAlwaysOnTop(true);
                stage.showAndWait();
                setShowing(false);
                if (!showing) {
                    screen.setEffect(null);
                }
                
            }catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    public void showPopupStage(ScrollPane screen, String resource){
        if (showing) {
            System.out.println("Already showing");
            return;
        }
        else if (!showing){
            try {
                System.out.println("not showing");
                BoxBlur boxBlur = new BoxBlur();
                boxBlur.setWidth(10);
                boxBlur.setHeight(10);
                boxBlur.setIterations(3);
                screen.setEffect(boxBlur);
                setShowing(true);
                System.out.println(showing);
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(resource));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED);
                
                stage.setScene(new Scene(root));
                stage.setAlwaysOnTop(true);
                stage.showAndWait();
                setShowing(false);
                if (!showing) {
                    screen.setEffect(null);
                }
                
            }catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    public void setShowing(boolean showingset){
        showing = showingset;
    }
    
    public boolean getShowing(){
        return showing;
    }
    
}


