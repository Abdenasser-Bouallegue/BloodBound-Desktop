/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Emergency;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AbdenasserBouallegue
 */
public class EmergencyFrontItemController implements Initializable {

      @FXML
      private Label em_title;
      @FXML
      private Label em_bloodtype;
      @FXML
      private Label em_location;
      @FXML
      private Label em_status;
      @FXML
      private Label em_id;
      @FXML
      private VBox showDetails;
      @FXML
      private Button showDetailssss;

      /**
       * Initializes the controller class.
       */
      @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     
    }    
    
    public void setData(Emergency p){
        em_id.setText(Integer.toString(p.getId()));
        em_title.setText(p.getTitle());
        em_bloodtype.setText((p.getBloodType()) );
        
        em_location.setText((p.getLocation()));
         em_status.setText((p.getStatus()));
       
       
       
        
    }

    



    private void showDetails(MouseEvent event) {
         try {
             Emergency p = new Emergency();
             p.setId(Integer.parseInt(em_id.getText()));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EmergencyFrontDetails.fxml"));
            Parent root = loader.load();
            EmergencyFrontDetailsController controller = loader.getController();
            controller.setData(p);
            showDetails.getScene().setRoot(root);
        } catch (java.io.IOException ex) {
            Logger.getLogger(EmergencyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

      @FXML
      private void showDetailssss(MouseEvent event) {
                     try {
             Emergency p = new Emergency();
             p.setId(Integer.parseInt(em_id.getText()));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EmergencyFrontDetails.fxml"));
            Parent root = loader.load();
            EmergencyFrontDetailsController controller = loader.getController();
            controller.setData(p);
            showDetails.getScene().setRoot(root);
        } catch (java.io.IOException ex) {
            Logger.getLogger(EmergencyController.class.getName()).log(Level.SEVERE, null, ex);
        }
      }


 
    
}
