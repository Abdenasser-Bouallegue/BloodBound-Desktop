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
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/**
 * FXML Controller class
 *
 * @author AbdenasserBouallegue
 */
public class EditEmergencyController implements Initializable {

      @FXML
      private TextField txt_title;
      @FXML
      private TextField txt_description;
      @FXML
      private TextField txt_location;
      @FXML
      private TextField txt_status;
      @FXML
      private DatePicker txt_deadline;
      @FXML
      private Button btn_edit;
      @FXML
      private TextField txt_id;
      @FXML
      private Button btn_delete1;
      
       ServicesEmergency se=new ServicesEmergency();
      @FXML
      private ComboBox<String> txt_bloodtype;
       int id=   Current_Emergency.Current_emergency ;

      /**
       * Initializes the controller class.
       */
      @Override
      public void initialize(URL url, ResourceBundle rb) {
            ServicesEmergency se=new ServicesEmergency();
             Emergency e=new Emergency();
            e= se.getByid(id);
          txt_bloodtype.setValue(e.getBloodType());

            txt_title.setText(e.getTitle());
            txt_description.setText(e.getDescription());
            txt_location.setText(e.getLocation());
            txt_status.setText(e.getStatus());
            txt_deadline.setValue(e.getDeadline());
             ObservableList<String> bloodTypes = FXCollections.observableArrayList(
    "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"
);
txt_bloodtype.setItems(bloodTypes);
      }      
void setData(Emergency p) {
    int id = p.getId();
    List<Emergency> pr = se.afficherById(id);
    if (!pr.isEmpty()) {
        txt_id.setText(Integer.toString(pr.get(0).getId()));
        System.out.println("AAA");
        txt_bloodtype.setValue(pr.get(0).getBloodType());
        System.out.println("BB");
        txt_title.setText(pr.get(0).getTitle());
        System.out.println("CC");
        txt_description.setText(pr.get(0).getDescription());
        System.out.println("DD");
        txt_location.setText(pr.get(0).getLocation());
        System.out.println("AAQA");
        txt_status.setText(pr.get(0).getStatus());
        System.out.println("QQ");
        txt_deadline.setValue(pr.get(0).getDeadline());
        System.out.println("WW");
    }
}

      @FXML
     private void delete (){
        ServicesEmergency se = new ServicesEmergency();
          se.supprimerByID(id);
                  JOptionPane.showMessageDialog(null, "Emergency has deleted with  successfully!");
          

}

      @FXML
      private void editEmergency(ActionEvent event) {
            
        ServicesEmergency service =new ServicesEmergency();
          Emergency objectToAdd=new Emergency();
             if (txt_title.getText().isEmpty()||txt_title.getText().length() < 5 || txt_title.getText().length() > 20) {
            UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 
            UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
          "Arial", Font.BOLD, 30)));
            JOptionPane.showMessageDialog(null, "Fix your Title", "Alert!", JOptionPane.ERROR_MESSAGE);
            return;
        }
            if (txt_description.getText().isEmpty()||txt_description.getText().length() < 20 || txt_description.getText().length() > 256) {
            UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 
            UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
          "Arial", Font.BOLD, 30)));
            JOptionPane.showMessageDialog(null, "Fix your Description", "Alert!", JOptionPane.ERROR_MESSAGE);
            return;
        }
         
             if (txt_location.getText().isEmpty()||txt_location.getText().length() < 5 || txt_location.getText().length() > 50) {
            UIManager.put("OptionPane.minimumSize",new Dimension(500,200)); 
            UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
          "Arial", Font.BOLD, 30)));
            JOptionPane.showMessageDialog(null, "Please fix your lcoation", "Alert!", JOptionPane.ERROR_MESSAGE);
            return;
        }
            if (txt_deadline.getValue() == null) {
    UIManager.put("OptionPane.minimumSize", new Dimension(500, 200)); 
    UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 30)));
    JOptionPane.showMessageDialog(null, "Please enter your Deadline", "Alert!", JOptionPane.ERROR_MESSAGE);
    return;
}
         
           objectToAdd.setDeadline(txt_deadline.getValue());
            objectToAdd.setId(id);
          
         
          objectToAdd.setDescription(txt_description.getText());
          objectToAdd.setLocation(txt_location.getText());
          objectToAdd.setStatus(txt_status.getText());
          objectToAdd.setTitle(txt_title.getText());
         objectToAdd.setBloodType(txt_bloodtype.getSelectionModel().getSelectedItem().toString());

          service.modifier(objectToAdd);
          JOptionPane.showMessageDialog(null, "Emergency modified successfully!");
          ServicesEmergency se = new ServicesEmergency();
                  
               
      }

      
}
