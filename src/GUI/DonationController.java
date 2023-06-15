/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Donation;
import Services.ServicesDonation;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * FXML Controller class
 *
 * @author AbdenasserBouallegue
 */
public class DonationController implements Initializable {

      @FXML
      private TextField txt_desc;
      @FXML
      private TextField txt_location;
      @FXML
      private TextField txt_phone;
      @FXML
      private TextField txt_email;
      @FXML
      private DatePicker txt_date;
      @FXML
      private Button btn_delete;
      @FXML
      private TableView<Donation> table;
      @FXML
      private TableColumn<?, ?> cl_desc;
      @FXML
      private TableColumn<?, ?> cl_location;
      @FXML
      private TableColumn<?, ?> cl_email;
      @FXML
      private TableColumn<?, ?> cl_phone;
      @FXML
      private TableColumn<?, ?> cl_date;
      @FXML
      private TableColumn<?, ?> col_d;
      @FXML
      private TextField txt_id;
      ServicesDonation  se= new ServicesDonation();
      @FXML
      private Label productPage;

      /**
       * Initializes the controller class.
       */
      public void table() {
        ServicesDonation  service= new ServicesDonation();
            
        table.setItems(FXCollections.observableArrayList(service.afficher()));
        col_d.setCellValueFactory(new PropertyValueFactory<>("id"));
        cl_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        cl_location.setCellValueFactory(new PropertyValueFactory<>("don_location"));
          cl_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        cl_phone.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        cl_date.setCellValueFactory(new PropertyValueFactory<>("donation_date"));

}
      int index1;
       @FXML
    public void getSelected(){
        index1 = table.getSelectionModel().getSelectedIndex();
        if (index1 <= -1){
            return;
        }
        txt_id.setText(col_d.getCellData(index1).toString());
        txt_desc.setText(cl_desc.getCellData(index1).toString());
        txt_location.setText(cl_location.getCellData(index1).toString());
        txt_email.setText(cl_email.getCellData(index1).toString());
        txt_phone.setText(cl_phone.getCellData(index1).toString());
        txt_date.setValue(LocalDate.parse(cl_date.getCellData(index1).toString()));
       
    }
      @Override
      public void initialize(URL url, ResourceBundle rb) {
            table();
      }      

      
      @FXML
      private void delete (){
          ServicesDonation se = new ServicesDonation();
          se.supprimer(new Donation(Integer.parseInt(txt_id.getText())));
                  JOptionPane.showMessageDialog(null, "Donation has deleted with  successfully!");
                  table();
                  clear();

}
      private void clear(){
        txt_date.setValue(null);
        txt_desc.clear();
        txt_id.clear();
        txt_location.clear();
        txt_email.clear();
       txt_phone.clear();
       }

       @FXML
    private void onChooseFileButtonClicked(ActionEvent event) {
         // Create a new file chooser
         ServicesDonation se= new ServicesDonation();
        FileChooser fileChooser = new FileChooser();

        
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

       
        fileChooser.setTitle("Choose a directory to save the file");

       
        File selectedDirectory = fileChooser.showSaveDialog(new Stage());

        
        if (selectedDirectory != null) {
            String directoryPath = selectedDirectory.getAbsolutePath();
            System.out.println("Selected directory: " + directoryPath);
            // Do something with the directory path...
        
           try {
         writeExcel(directoryPath,se.afficher());
} catch (Exception e) {
   

        }
    }
    
}
      
     public static void writeExcel(String pathfile , List<Donation> m) throws Exception {
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("donations");
    XSSFRow headerRow = sheet.createRow(0);
    
   
    XSSFCellStyle greenStyle = workbook.createCellStyle();
    greenStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
    greenStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    
   
    XSSFCellStyle redStyle = workbook.createCellStyle();
    redStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
    redStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

   
    XSSFCell cell = headerRow.createCell(0);
    cell.setCellValue("Description");
    cell.setCellStyle(greenStyle);
    cell = headerRow.createCell(1);
    cell.setCellValue("don_location");
    cell = headerRow.createCell(2);
    sheet.setColumnWidth(1, 8000);
    cell.setCellValue("email");
    cell = headerRow.createCell(3);
    cell.setCellValue("phone_number");
    sheet.setColumnWidth(3, 8000);
    cell = headerRow.createCell(4);
    cell.setCellValue("Donation date");
    cell.setCellStyle(greenStyle);
    sheet.setColumnWidth(5, 5000);

    
    int rowNum = 1;
    for (Donation obj : m) {
        XSSFRow row = sheet.createRow(rowNum++);
        cell = row.createCell(0);
        cell.setCellValue(obj.getDescription());
        cell.setCellStyle(greenStyle);
        cell = row.createCell(1);
        cell.setCellValue(obj.getDon_location());
        cell = row.createCell(2);
        cell.setCellValue(obj.getEmail());
        cell = row.createCell(3);
        cell.setCellValue(obj.getPhone_number().toString());
       
        cell = row.createCell(4);
        cell.setCellValue(obj.getDonation_date().toString());
        cell.setCellStyle(greenStyle);
    }

    // Write to file
    FileOutputStream outputStream = new FileOutputStream(pathfile + ".xlsx");
    workbook.write(outputStream);
    workbook.close();
}

     public static void writepdf(String path,List<Donation> m) throws Exception {
    Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream(path+".pdf"));
    document.open();
    
    Image logo = Image.getInstance("C:\\Users\\NASSYR\\Desktop\\bloodbound\\bloodbound\\src\\images/logobb.png");
    logo.scaleToFit(400, 400); // Set image size
    document.add(logo);
    List<Donation> pdf =m ;
    
    PdfPTable table = new PdfPTable(5); // 6 columns
table.setWidthPercentage(100);

PdfPCell cell1 = new PdfPCell(new Phrase("description"));
cell1.setBackgroundColor(BaseColor.GREEN);
table.addCell(cell1);


PdfPCell cell2 = new PdfPCell(new Phrase("don_location"));
cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
table.addCell(cell2);

PdfPCell cell3 = new PdfPCell(new Phrase("email"));
cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
table.addCell(cell3);

PdfPCell cell4 = new PdfPCell(new Phrase("phone_number"));
cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
table.addCell(cell4);

PdfPCell cell5 = new PdfPCell(new Phrase("donation date"));
cell5.setBackgroundColor(BaseColor.GREEN);
table.addCell(cell5);

              
    
    for (Donation obj : pdf) {
          
        table.addCell(obj.getDescription());
        table.addCell(obj.getDon_location());
        table.addCell(obj.getEmail());
         table.addCell(obj.getPhone_number());
         table.addCell(obj.getDonation_date().toString());
    }
    
    document.add(table);
    document.close();
}

    @FXML
    private void filechosserpdf(ActionEvent event) {
           
            // Create a new file chooser
        FileChooser fileChooser = new FileChooser();

        // Set the initial directory (optional)
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        // Set the title of the file chooser dialog
        fileChooser.setTitle("Choose a directory to save the file");

        // Show the file chooser dialog and wait for the user to select a directory
        File selectedDirectory = fileChooser.showSaveDialog(new Stage());

        // If the user selected a directory, get the absolute path of the directory as a string
        if (selectedDirectory != null) {
            String directoryPath = selectedDirectory.getAbsolutePath();
            System.out.println("Selected directory: " + directoryPath);
            // Do something with the directory path...
        
           try {
         writepdf(directoryPath,se.afficher());
} catch (Exception e) {
    System.out.println("Error sending email: " + e.getMessage());

        }
    }
    
        
        
    } 

      @FXML
      private void ProductPage(MouseEvent event) {
      }

      
      
}
