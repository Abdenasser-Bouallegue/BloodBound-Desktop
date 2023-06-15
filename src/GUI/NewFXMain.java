/*
Abdenasser bouallegue
 */
package GUI;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Safe
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {

        //  MenuEmrg
             Parent p = FXMLLoader.load(getClass().getResource("emergencyfront.fxml"));

       
        
        Scene scene = new Scene(p);
        
        primaryStage.setTitle("Welcome in BloodBound!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
