/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Emergency;
import Services.ServicesEmergency;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AbdenasserBouallegue
 */
public class EmergencyFrontDetailsController implements Initializable {

      private Button toAddEmergency;
      private Text title;
      @FXML
      private Label idLabel;
      @FXML
      private Text bloodtype;
      @FXML
      private TextArea description;
      @FXML
      private Text status;
      @FXML
      private Text location;
      @FXML
      private Text deadline;
      @FXML
      private Label em_id;
      ServicesEmergency se=new ServicesEmergency();
      @FXML
      private HBox titleXX;
      @FXML
      private Text em_title;
      private Button edit;
      /**
       * Initializes the controller class.
       */
      @Override
      public void initialize(URL url, ResourceBundle rb) {
        
      }      


      
   void setData(Emergency p) {
    int id = p.getId();
    List<Emergency> pr = se.afficherById(id);
    if (!pr.isEmpty()) {
        em_id.setText(Integer.toString(pr.get(0).getId()));
          System.out.println("AAA");
        bloodtype.setText(pr.get(0).getBloodType());
        System.out.println("BB");
        em_title.setText(pr.get(0).getTitle());
        System.out.println("CC");
        description.setText(pr.get(0).getDescription());
        System.out.println("DD");
        location.setText(pr.get(0).getLocation());
        System.out.println("AAQA");
        status.setText(pr.get(0).getStatus());
        System.out.println("QQ");
        deadline.setText(pr.get(0).getDeadline().toString());
        System.out.println("WW");
    }
}


     
 
   
      private void toAddEmergency(MouseEvent event) {
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEmergency.fxml"));
            Parent root = loader.load();
            AddEmergencyController controller = loader.getController();
            toAddEmergency.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(EmergencyController.class.getName()).log(Level.SEVERE, null, ex);
        }
      }

      @FXML
      private void donate(ActionEvent event) {
    try {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddDonation.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.err.println("Failed to load AddDonation.fxml: " + ex.getMessage());
    }
}

     

            
      }
        