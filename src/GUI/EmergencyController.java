
package GUI;
import Entities.Emergency;
import Services.ServicesEmergency;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javafx.scene.chart.PieChart;
import javafx.collections.FXCollections;

/**
 * FXML Controller class
 *
 * @author AbdenasserBouallegue
 */
public class EmergencyController implements Initializable {

      @FXML
      private TextField txt_title;
      @FXML
      private TextField txt_description;
      @FXML
      private TextField txt_location;
      @FXML
      private ComboBox<String> txt_bloodtype;
      @FXML
      private TextField txt_status;
      @FXML
      private DatePicker txt_deadline;
      @FXML
      private TableColumn<Emergency, String> cl_title;
      @FXML
      private TableColumn<Emergency, String> cl_description;
      @FXML
      private TableColumn<Emergency, String> cl_bloodtype;
      @FXML
      private TableColumn<Emergency, String> cl_location;
      @FXML
      private TableColumn<Emergency, String> cl_status;
      @FXML
      private TableColumn<Emergency, LocalDate> cl_deadline;
      @FXML
      private TableView<Emergency> table;
      @FXML
      private TextField txt_id;
      @FXML
      private TableColumn<Emergency, Integer> col_d;
      @FXML
      private AnchorPane pane;
      @FXML
      private Button btn_edit;
      @FXML
      private Button btn_delete;
      ServicesEmergency se = new ServicesEmergency();
      @FXML
      private Label productPage;
      
      @FXML
    private PieChart emergencyStatusPieChart;

   

       /**
       * Initializes the controller class.
       */
       public void table() {
        ServicesEmergency  service= new ServicesEmergency();
            
        table.setItems(FXCollections.observableArrayList(service.afficher()));
        col_d.setCellValueFactory(new PropertyValueFactory<>("id"));
        cl_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        cl_description.setCellValueFactory(new PropertyValueFactory<>("description"));
          cl_bloodtype.setCellValueFactory(new PropertyValueFactory<>("bloodType"));
        cl_location.setCellValueFactory(new PropertyValueFactory<>("location"));
        cl_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        cl_deadline.setCellValueFactory(new PropertyValueFactory<>("deadline"));

}
      @Override
      public void initialize(URL url, ResourceBundle rb) {
            table();
         ObservableList<String> bloodTypes = FXCollections.observableArrayList(
    "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"
);
txt_bloodtype.setItems(bloodTypes);

      ServicesEmergency service = new ServicesEmergency();
        
        int completCount = 0;
        int nonCompletCount = 0;
        
        try {
            completCount = service.CalculC();
            nonCompletCount = service.CalculNonC();
        } catch (SQLException ex) {
            // Handle the exception here
            ex.printStackTrace();
        }
        
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Emergencies completed", completCount),
                new PieChart.Data("Emergencies In Progress", nonCompletCount));
        
        emergencyStatusPieChart.setData(pieChartData);

      }   
      
    
      @FXML
      private void delete (){
          ServicesEmergency se = new ServicesEmergency();
          se.supprimer(new Emergency(Integer.parseInt(txt_id.getText())));
                  JOptionPane.showMessageDialog(null, "Emergency has deleted with  successfully!");
                  table();
                  clear();

}
int index1;
      @FXML
    public void getSelected() {
    index1 = table.getSelectionModel().getSelectedIndex();
    if (index1 <= -1) {
        return;
    }
    txt_id.setText(col_d.getCellData(index1).toString());
    txt_title.setText(cl_title.getCellData(index1).toString());
    txt_description.setText(cl_description.getCellData(index1).toString());
    txt_bloodtype.setValue(cl_bloodtype.getCellData(index1).toString());
    txt_location.setText(cl_location.getCellData(index1).toString());
    txt_status.setText(cl_status.getCellData(index1).toString());
    txt_deadline.setValue(LocalDate.parse(cl_deadline.getCellData(index1).toString()));
}

    @FXML
      private void edit() {
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

             
           objectToAdd.setDeadline(txt_deadline.getValue());
            objectToAdd.setId(Integer.parseInt(txt_id.getText()));
          
         
          objectToAdd.setDescription(txt_description.getText());
          objectToAdd.setLocation(txt_location.getText());
          objectToAdd.setStatus(txt_status.getText());
          objectToAdd.setTitle(txt_title.getText());
         objectToAdd.setBloodType(txt_bloodtype.getSelectionModel().getSelectedItem().toString());

          service.modifier(objectToAdd);
          JOptionPane.showMessageDialog(null, "Emergency modified successfully!");
          ServicesEmergency se = new ServicesEmergency();
                  
                  table();
                  clear();
      }
      
private void clear(){
        txt_deadline.setValue(null);
        txt_description.clear();
        txt_id.clear();
        txt_location.clear();
        txt_status.clear();
     txt_bloodtype.getSelectionModel().clearSelection();

        txt_title.clear();}
      
      @FXML
    private void onChooseFileButtonClicked(ActionEvent event) {
         // Create a new file chooser
         ServicesEmergency se= new ServicesEmergency();
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
      
     public static void writeExcel(String pathfile , List<Emergency> m) throws Exception {
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Emergencies");
    XSSFRow headerRow = sheet.createRow(0);
    
   
    XSSFCellStyle greenStyle = workbook.createCellStyle();
    greenStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
    greenStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    
   
    XSSFCellStyle redStyle = workbook.createCellStyle();
    redStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
    redStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

   
    XSSFCell cell = headerRow.createCell(0);
    cell.setCellValue("Title");
    cell.setCellStyle(greenStyle);
    cell = headerRow.createCell(1);
    cell.setCellValue("Description");
    cell = headerRow.createCell(2);
    sheet.setColumnWidth(1, 8000);
    cell.setCellValue("Bloodtype");
    cell = headerRow.createCell(3);
    cell.setCellValue("Location");
    sheet.setColumnWidth(3, 8000);
    cell = headerRow.createCell(4);
    cell.setCellValue("Status");
    cell = headerRow.createCell(5);
    cell.setCellValue("Deadline");
    cell.setCellStyle(redStyle);
    sheet.setColumnWidth(5, 4000);

    
    int rowNum = 1;
    for (Emergency obj : m) {
        XSSFRow row = sheet.createRow(rowNum++);
        cell = row.createCell(0);
        cell.setCellValue(obj.getTitle());
        cell.setCellStyle(greenStyle);
        cell = row.createCell(1);
        cell.setCellValue(obj.getDescription());
        cell = row.createCell(2);
        cell.setCellValue(obj.getBloodType());
        cell = row.createCell(3);
        cell.setCellValue(obj.getLocation().toString());
        cell = row.createCell(4);
        cell.setCellValue(obj.getStatus().toString());
        cell = row.createCell(5);
        cell.setCellValue(obj.getDeadline().toString());
        cell.setCellStyle(redStyle);
    }

    // Write to file
    FileOutputStream outputStream = new FileOutputStream(pathfile + ".xlsx");
    workbook.write(outputStream);
    workbook.close();
}

     public static void writepdf(String path,List<Emergency> m) throws Exception {
    Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream(path+".pdf"));
    document.open();
    
    Image logo = Image.getInstance("C:\\Users\\NASSYR\\Desktop\\bloodbound\\bloodbound\\src\\images/bblogo.png");
    logo.scaleToFit(400, 400); // Set image size
    document.add(logo);
    List<Emergency> pdf =m ;
    
    PdfPTable table = new PdfPTable(6); // 6 columns
table.setWidthPercentage(100);

PdfPCell cell1 = new PdfPCell(new Phrase("title"));
cell1.setBackgroundColor(BaseColor.GREEN);
table.addCell(cell1);


PdfPCell cell2 = new PdfPCell(new Phrase("description"));
cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
table.addCell(cell2);

PdfPCell cell3 = new PdfPCell(new Phrase("bloodtype"));
cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
table.addCell(cell3);

PdfPCell cell4 = new PdfPCell(new Phrase("location"));
cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
table.addCell(cell4);

PdfPCell cell5 = new PdfPCell(new Phrase("status"));
cell5.setBackgroundColor(BaseColor.LIGHT_GRAY);
table.addCell(cell5);

PdfPCell cell6 = new PdfPCell(new Phrase("deadline"));
cell6.setBackgroundColor(BaseColor.RED);
table.addCell(cell6);

              
    
    for (Emergency obj : pdf) {
          
        table.addCell(obj.getTitle());
        table.addCell(obj.getDescription());
        table.addCell(obj.getBloodType());
        table.addCell(obj.getLocation().toString());
         table.addCell(obj.getStatus());
         table.addCell(obj.getDeadline().toString());
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