/*
Name: Danielius Zurlys
Student ID: 20130611
*/

package HistogramApplication;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

//Class HistogramApplication
//Main class running the application
public class HistogramApplication extends Application {
    
    // start GUI function 
    @Override
    public void start(Stage primaryStage) throws Exception{
        
        //specifiy relevant system info
        System.out.println(("Java Version: " + System.getProperty("java.version")));
        
        System.out.println(("JavaFX Version: " + System.getProperty("javafx.version")));
        System.out.println(("JavaFX Runtime Version: " + System.getProperty("javafx.runtime.version")));
        //Assign FMXL file
        Parent root = FXMLLoader.load(getClass().getResource("HistogramApplication.fxml")); // load the fxml file
        
        //setup scene
        primaryStage.setTitle("Histogram Application (DanieliusZurlys_20130611)");
        Scene theScene = new Scene(root);
        theScene.getStylesheets().add("HistogramApplication_style.css");
        primaryStage.setScene(theScene);
        primaryStage.show();
    }

    //    
    public static void main(String[] args) {
        launch(args);
    }
    
}
