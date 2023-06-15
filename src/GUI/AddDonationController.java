/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Donation;
import Services.ServicesDonation;
import java.awt.Dimension;
import java.awt.Font;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import java.util.Properties;
import javafx.scene.control.Button;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author AbdenasserBouallegue
 */
public class AddDonationController implements Initializable {

      @FXML
      private Button btn_add;
      @FXML
      private TextField txt_description;
      @FXML
      private TextField txt_email;
      @FXML
      private TextField txt_don;
      @FXML
      private TextField txt_phone;
      @FXML
      private DatePicker txt_date;
      @FXML
      private TextField txt_id;
      int count = 0;

      @FXML
      private void addDonation() {

            ServicesDonation service = new ServicesDonation();
            Donation objectToAdd = new Donation();

            if (txt_description.getText().isEmpty()) {
                  UIManager.put("OptionPane.minimumSize", new Dimension(500, 200));
                  UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(
                            "Arial", Font.BOLD, 30)));
                  JOptionPane.showMessageDialog(null, "Description field doesnt must be empty", "Alert!", JOptionPane.ERROR_MESSAGE);
                  return;
            }
            if (txt_description.getText().length() < 20 || txt_description.getText().length() > 250) {
                  UIManager.put("OptionPane.minimumSize", new Dimension(500, 200));
                  UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(
                            "Arial", Font.BOLD, 30)));
                  JOptionPane.showMessageDialog(null, "Description at least must be between 20 carracters", "Alert!", JOptionPane.ERROR_MESSAGE);
                  return;
            }
            if (txt_don.getText().isEmpty()) {
                  UIManager.put("OptionPane.minimumSize", new Dimension(500, 200));
                  UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(
                            "Arial", Font.BOLD, 30)));
                  JOptionPane.showMessageDialog(null, "Don Location field doesnt must be empty", "Alert!", JOptionPane.ERROR_MESSAGE);
                  return;
            }
            if (txt_don.getText().length() < 10 || txt_don.getText().length() > 40) {
                  UIManager.put("OptionPane.minimumSize", new Dimension(500, 200));
                  UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(
                            "Arial", Font.BOLD, 30)));
                  JOptionPane.showMessageDialog(null, "Location must be between 10 and 40 carracters", "Alert!", JOptionPane.ERROR_MESSAGE);
                  return;
            }
            String email = txt_email.getText();
            if (email.isEmpty()) {
                  UIManager.put("OptionPane.minimumSize", new Dimension(500, 200));
                  UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 30)));
                  JOptionPane.showMessageDialog(null, "Please enter an email address", "Alert!", JOptionPane.ERROR_MESSAGE);
                  return;
            }

// Regular expression pattern for email validation
            String emailPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

// Check if the email matches the pattern
            if (!email.matches(emailPattern)) {
                  UIManager.put("OptionPane.minimumSize", new Dimension(500, 200));
                  UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 30)));
                  JOptionPane.showMessageDialog(null, "Please enter a valid email address", "Alert!", JOptionPane.ERROR_MESSAGE);
                  return;
            }
            if (txt_phone.getText().isEmpty()) {
                  UIManager.put("OptionPane.minimumSize", new Dimension(500, 200));
                  UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(
                            "Arial", Font.BOLD, 30)));
                  JOptionPane.showMessageDialog(null, "Please enter your phone number", "Alert!", JOptionPane.ERROR_MESSAGE);
                  return;
            }
            if (txt_phone.getText().length() != 8) {
                  UIManager.put("OptionPane.minimumSize", new Dimension(500, 200));
                  UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 30)));
                  JOptionPane.showMessageDialog(null, "Phone number must be 8 digits", "Alert!", JOptionPane.ERROR_MESSAGE);
                  return;
            }
            if (txt_date.getValue() == null) {
                  UIManager.put("OptionPane.minimumSize", new Dimension(500, 200));
                  UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 30)));
                  JOptionPane.showMessageDialog(null, "Please enter your Date", "Alert!", JOptionPane.ERROR_MESSAGE);
                  return;
            }

            objectToAdd.setDon_location(txt_don.getText());
            objectToAdd.setDonation_date(txt_date.getValue());
            objectToAdd.setDescription(txt_description.getText());
            objectToAdd.setEmail(txt_email.getText());
            objectToAdd.setPhone_number(txt_phone.getText());

            service.add(objectToAdd);
            ServicesDonation se = new ServicesDonation();

            JOptionPane.showMessageDialog(null, "Dontation Create successfully!");
            String subject = "Donation Certificate";
            if (service.foundOneByEmail(txt_email.getText()) == 1) {
                  try {
                        sendEmail("abdenasser.bouallegue@esprit.tn", subject);
                  } catch (MessagingException e) {
                        System.out.println("Error sending email: " + e.getMessage());
                  }
            }

      }

      public static void sendEmail(String recipientEmail, String subject) throws MessagingException {

            // Sender's email ID needs to be mentioned
            String senderEmail = "heelos.gcfhvgjbhknj@gmail.com";

            // Sender's email password needs to be mentioned
            String password = "fbubwijawfdcvesg";

            // SMTP server address
            String host = "smtp.gmail.com";

            // Get system properties
            Properties properties = System.getProperties();

            // Setup mail server
            properties.setProperty("mail.smtp.host", host);
            properties.setProperty("mail.smtp.port", "587");
            properties.setProperty("mail.smtp.starttls.enable", "true");
            properties.setProperty("mail.smtp.auth", "true");

            // Get the default Session object.
            Session session = Session.getInstance(properties, new Authenticator() {
                  protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderEmail, password);
                  }
            });

            // Create a default MimeMessage object.
            MimeMessage mimeMessage = new MimeMessage(session);

            // Set From: header field of the header.
            mimeMessage.setFrom(new InternetAddress(senderEmail));

            // Set To: header field of the header.
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));

            // Set Subject: header field
            mimeMessage.setSubject(subject);

            // Set HTML content
            mimeMessage.setContent("<html>\n"
                      + "    <head>\n"
                      + "        <style type='text/css'>\n"
                      + "            body, html {\n"
                      + "                margin: 0;\n"
                      + "                padding: 0;\n"
                      + "            }\n"
                      + "            body {\n"
                      + "                color: black;\n"
                      + "                display: table;\n"
                      + "                font-family: Georgia, serif;\n"
                      + "                font-size: 24px;\n"
                      + "                text-align: center;\n"
                      + "            }\n"
                      + "            .container {\n"
                      + "                border: 20px solid tan;\n"
                      + "                width: 750px;\n"
                      + "                height: 563px;\n"
                      + "                display: table-cell;\n"
                      + "                vertical-align: middle;\n"
                      + "            }\n"
                      + "            .logo {\n"
                      + "                color: tan;\n"
                      + "            }\n"
                      + "\n"
                      + "            .marquee {\n"
                      + "                color: #7B2323;\n"
                      + "                font-size: 48px;\n"
                      + "                margin: 150px;\n"
                      + "            }\n"
                      + "            .assignment {\n"
                      + "                margin: 20px;\n"
                      + "            }\n"
                      + "            .person {\n"
                      + "                border-bottom: 2px solid black;\n"
                      + "                font-size: 32px;\n"
                      + "                font-style: italic;\n"
                      + "                margin: 20px auto;\n"
                      + "                width: 400px;\n"
                      + "            }\n"
                      + "            .reason {\n"
                      + "                margin: 20px;\n"
                      +"  font-size: 24px;\n"
                      + "            }\n"
                      + "        </style>\n"
                      + "    </head>\n"
                      + "    <body>\n"
                      + "        <div class=\"container\">\n"
                      + "            <div class=\"logo\">\n"
                      + "                <C:\\Desktop\\bloodbound\\bloodbound\\src\\Images\\BBLogo.png>"
                      + "            </div>\n"
                      + "\n"
                      + "            <div class=\"marquee\">\n"
                      + "                 Donation Certificate\n"
                      + "            </div>\n"
                      + "\n"
                      + "            <div class=\"assignment\">\n"
                      + "               <br> We make a living by what we get, but we make a life by what we give<br/>\n"
                      + "            </div>\n"
                      + "\n"
                      + "            <div class=\"person\">\n"
                      + "                BloodBound Donor\n"
                      + "            </div>\n"
                      + "\n"
                      + "            <div class=\"reason\">\n"
                      + "              <br>Thank You..<br/>\n"
                          
                      + "            </div>\n"
                      + "        </div>\n"
                      + "    </body>\n"
                      + "</html>", "text/html");

            // Send message
            Transport.send(mimeMessage);

            System.out.println("Email sent successfully to " + recipientEmail);
      }

      @Override
      public void initialize(URL url, ResourceBundle rb) {
            // TODO
      }

}
