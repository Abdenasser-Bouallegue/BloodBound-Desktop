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
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AbdenasserBouallegue
 */

public class EmergencyFrontController implements Initializable {
 ServicesEmergency ps = new ServicesEmergency();
      @FXML
      private ScrollPane scroll;
      @FXML
      private GridPane grid;
      @FXML
      private TextField search;
        ServicesEmergency se = new ServicesEmergency();
      private Button toEmergency;
      @FXML
      private Button toAddEmergency;
      /**
       * Initializes the controller class.
       */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
    int column = 0;
    int row = 1;
    try {
        List<Emergency> p = ps.afficher();
        // sort the list by deadline
        Collections.sort(p, (e1, e2) -> e2.getDeadline().compareTo(e1.getDeadline()));
        for (int i = 0; i < p.size(); i++) {

            FXMLLoader fxmlloader = new FXMLLoader();

            fxmlloader.setLocation(getClass().getResource("../GUI/EmergencyFrontItem.fxml"));               
            AnchorPane anchorPane = fxmlloader.load();   

            EmergencyFrontItemController pc = fxmlloader.getController();

            //System.out.println(fxmlloader.getLocation());
            pc.setData(p.get(i));

            System.out.println(p.get(i)); 
            if (column == 3) {
                column = 0;
                row++;
            }
            grid.add(anchorPane, column++, row);

            grid.setMinWidth(Region.USE_COMPUTED_SIZE);
            grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
            grid.setMaxWidth(Region.USE_PREF_SIZE);

            grid.setMinHeight(Region.USE_COMPUTED_SIZE);
            grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
            grid.setMaxHeight(Region.USE_PREF_SIZE);

            GridPane.setMargin(anchorPane, new Insets(10));
        }
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
}
   

      private void router(ActionEvent event) {
             try {

                              FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEmergency.fxml"));
                              Parent root = loader.load();
                              Scene scene = new Scene(root);
                              Stage stage = new Stage();
                              stage.setScene(scene);
                              stage.show();
                        } catch (IOException ex) {
                              System.err.println("Failed to load scene2.fxml: " + ex.getMessage());
                        }
      }
      private void setEmergencyList(List<Emergency> emergencyList) {
        grid.getChildren().clear();
        int column = 0;
        int row = 1;
        for (int i = 0; i < emergencyList.size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../GUI/EmergencyFrontItem.fxml"));               
                AnchorPane anchorPane = fxmlLoader.load();               
                EmergencyFrontItemController itemController = fxmlLoader.getController();
                itemController.setData(emergencyList.get(i));
                
                if (column == 3) {
                    column = 0;
                    row++;
                }
                
                grid.add(anchorPane, column++, row);
                
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            } catch (IOException ex) {
                Logger.getLogger(EmergencyFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     @FXML
    private void filter(KeyEvent event) {
        ObservableList<Emergency> filteredPeople = FXCollections.observableArrayList(se.afficher()); 

        ObservableList<Emergency> newdata = filteredPeople.stream()
                .filter(n -> n.getLocation().toLowerCase().contains(search.getText().toLowerCase())
                || n.getLocation().toLowerCase().equals(search.getText()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        setEmergencyList(newdata);
    }
      
          @FXML
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
    private void toEmergency(MouseEvent event) {
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EmergencyFront.fxml"));
            Parent root = loader.load();
            EmergencyFrontController controller = loader.getController();
            toEmergency.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(EmergencyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

