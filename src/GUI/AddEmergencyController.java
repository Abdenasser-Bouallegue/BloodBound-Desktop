/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Emergency;
import Services.ServicesEmergency;
import java.awt.Dimension;
import java.awt.Font;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/**
 * FXML Controller class
 *
 * @author AbdenasserBouallegue
 */
public class AddEmergencyController implements Initializable {

      @FXML
      private TextField txt_title;
      @FXML
      private TextField txt_description;
      @FXML
      private TextField txt_location;
      @FXML
      private DatePicker txt_deadline;
      @FXML
      private Button btn_add;
      @FXML
      private TextField txt_id;
      @FXML
      private ComboBox<String> txt_bloodtype;
      @FXML
      private Button toAddEmergency;
      @FXML
      private TextField search;
      @FXML
      private ScrollPane scroll;
      @FXML
      private GridPane grid;

      /**
       * Initializes the controller class.
       */
     @FXML
      private void addEmergency() {
            ServicesEmergency service =new ServicesEmergency();
          Emergency objectToAdd=new Emergency();
             if (txt_title.getText().isEmpty()) {
            UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 
            UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
          "Arial", Font.BOLD, 30)));
            JOptionPane.showMessageDialog(null, "Title field doesnt must be empty", "Alert!", JOptionPane.ERROR_MESSAGE);
            return;
        }
             if (txt_title.getText().length() < 5 || txt_title.getText().length() > 40) {
            UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 
            UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
          "Arial", Font.BOLD, 30)));
            JOptionPane.showMessageDialog(null, "Title must be between 5 and 40 carracters", "Alert!", JOptionPane.ERROR_MESSAGE);
            return;
        }
          if (txt_description.getText().isEmpty()) {
            UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 
            UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
          "Arial", Font.BOLD, 30)));
            JOptionPane.showMessageDialog(null, "Description field doesnt must be empty", "Alert!", JOptionPane.ERROR_MESSAGE);
            return;
        }
             if (txt_description.getText().length() < 20 || txt_description.getText().length() > 256) {
            UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 
            UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
          "Arial", Font.BOLD, 30)));
            JOptionPane.showMessageDialog(null, "Description field at least must be 20 carracters", "Alert!", JOptionPane.ERROR_MESSAGE);
            return;
        }   
           



             if (txt_location.getText().isEmpty()) {
            UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 
            UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
          "Arial", Font.BOLD, 30)));
            JOptionPane.showMessageDialog(null, "Location field dosnt must be void", "Alert!", JOptionPane.ERROR_MESSAGE);
            return;
        }
             if (txt_location.getText().length() < 5 || txt_title.getText().length() > 40) {
            UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 
            UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
          "Arial", Font.BOLD, 30)));
            JOptionPane.showMessageDialog(null, "Location must be between 5 and 40 carracters", "Alert!", JOptionPane.ERROR_MESSAGE);
            return;
        }
              if (txt_deadline.getValue() == null) {
    UIManager.put("OptionPane.minimumSize", new Dimension(500, 200)); 
    UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 30)));
    JOptionPane.showMessageDialog(null, "Please enter your Deadline", "Alert!", JOptionPane.ERROR_MESSAGE);
    return;}
            // Get the current date
LocalDate currentDate = LocalDate.now();

// Get the deadline entered by the user
String deadlineStr = txt_deadline.getValue().toString();

LocalDate deadline = LocalDate.parse(deadlineStr);
// Check if the deadline is less than or equal to the current date
if (deadline.isBefore(currentDate)) {
    UIManager.put("OptionPane.minimumSize", new Dimension(500, 200)); 
    UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 30)));
    JOptionPane.showMessageDialog(null, "Deadline that is after today's date"+""+currentDate, "Alert!", JOptionPane.ERROR_MESSAGE);
    return;
}


             
           objectToAdd.setBloodType(txt_bloodtype.getSelectionModel().getSelectedItem().toString());
           objectToAdd.setDeadline(txt_deadline.getValue());
            
          
         
          objectToAdd.setDescription(txt_description.getText());
          objectToAdd.setLocation(txt_location.getText());
          objectToAdd.setStatus("in progress");
          objectToAdd.setTitle(txt_title.getText());
         
          service.add(objectToAdd);
                              JOptionPane.showMessageDialog(null, "Emergency created with successfully!");

          ServicesEmergency se = new ServicesEmergency();

               

      }
       @Override
      public void initialize(URL url, ResourceBundle rb) {
             ObservableList<String> bloodTypes = FXCollections.observableArrayList(
    "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"
);
txt_bloodtype.setItems(bloodTypes);
      } 

      @FXML
      private void toAddEmergency(MouseEvent event) {
      }

      @FXML
      private void filter(KeyEvent event) {
      }

      @FXML
      private void toEmergency(MouseEvent event) {
      }
      
}
