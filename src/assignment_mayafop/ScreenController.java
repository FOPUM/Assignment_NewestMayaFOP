/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import java.io.IOException;
import java.util.HashMap;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author kwany
 */
public class ScreenController extends StackPane{
    
    
    
    
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
            final DoubleProperty opacity = opacityProperty();

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
    
    public void closeStage(){
        
    }

    }


