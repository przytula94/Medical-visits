/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookvisit;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import doctor.DoctorList;
import static login.LoginController.infoBox;
import patient.PacjentLista;

/**
 * FXML Controller class
 *
 * @author Paweł
 */
public class BookAppointmentController implements Initializable {

    @FXML
    private Button btn_Home;
    @FXML
    private TextField txt_Search;
    @FXML
    private TextField txt_Imie;
    @FXML
    private TextField txt_Nazwisko;
    @FXML
    private TextField txt_Pesel;
    @FXML
    private DatePicker txt_WyborDzien;
    @FXML
    private ComboBox<doctor.DoctorList> txt_WyborLekarz;
    @FXML
    private Button btn_book;
    @FXML
    private ComboBox<HourList> txt_WyborGodzina;
  
    
    Stage dialogStage = new Stage();
    Scene scene;
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    private String WyborGodzina;
    private String WyborLekarz;
    private ObservableList<HourList> hour;
    private ObservableList<doctor.DoctorList> doctor;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        con = Database.DBConnection.WizytyPrzychoniaConnection();
        hour = FXCollections.observableArrayList();
        
        try {
            pst = con.prepareStatement("Select * from [godzina]");
           
            rs = pst.executeQuery();
            while(rs.next()){
                
                hour.add(new HourList(rs.getString(1), rs.getString(2)));
               
            }
            txt_WyborGodzina.setItems(hour);
           
        } catch (SQLException ex) {
            Logger.getLogger(BookAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        txt_WyborGodzina.setConverter(new StringConverter<HourList>(){
            
            @Override
            public String toString(HourList object){
                
                //throw new UnsupportedOperationException("Brak wsparcia");
                return object.getWyborGodzina();
            }
            
            @Override
            public HourList fromString(String string){
               // throw new UnsupportedOperationException("Brak wsparcia");
               return null;
            }

        });
        
        txt_WyborGodzina.valueProperty().addListener((ObservableValue<? extends HourList> obs, HourList oldValue, HourList newValue) -> {
            if(newValue!=null){
                //Alert alert = new Alert(Alert.AlertType.INFORMATION, newValue.getWyborGodzina(), ButtonType.OK);
                //alert.show();
                WyborGodzina = newValue.getWyborGodzina();
            }
        });
        
        
        
       fillComboBox();
 
    }    

    @FXML
    private void handleReturnToHome(ActionEvent event) throws IOException {
        
        Node node = (Node)event.getSource();
                dialogStage = (Stage) node.getScene().getWindow();
                //dialogStage.close();
                scene = new Scene (FXMLLoader.load(getClass().getResource("/wizytyprzychodnia/BorderPaneMain.fxml")));
                dialogStage.setScene(scene);
                dialogStage.show();
   
    }

    @FXML
    private void handleBook(ActionEvent event) throws SQLException {

        String sql = "Insert into rezerwacja(Imie,Nazwisko,PESEL,DataWizyty,GodzinaWizyty,Lekarz) Values(?,?,?,?,?,?)";
        String Imie = txt_Imie.getText();
        String Nazwisko = txt_Nazwisko.getText();
        String PESEL = txt_Pesel.getText();
        
        try {
            
  
         
            pst = con.prepareStatement(sql);
            
            //rs = pst.getGeneratedKeys();
            //rs.next();
            //Object key = rs.getObject(1);
            pst.setString(1, Imie);
            pst.setString(2, Nazwisko);
            pst.setString(3, PESEL);
            pst.setDate(4, java.sql.Date.valueOf(txt_WyborDzien.getValue()));
            pst.setString(5, WyborGodzina);
            pst.setString(6, WyborLekarz);
            int i = pst.executeUpdate();     
            if(i==1)
                         System.out.println("Wizyta została zarezerwowana");
                    
        } catch (SQLException ex) {
            Logger.getLogger(BookAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
            
            pst.close();
        }
        infoBox("Wizyta została zarezerwowana", null, "Edycja danych" );
        
     
    }

    @FXML
    private void handleSearchPesel(KeyEvent event) throws SQLException {
        
        pst = con.prepareStatement("Select * from [PacjentDoWizyty] where Pesel = ? ");
        pst.setString(1, txt_Search.getText());
        rs = pst.executeQuery();
                if(rs.next()){
                    String Imie;
                    String Nazwisko;
                    String Pesel;
                    Imie = rs.getString(1);
                    Nazwisko = rs.getString(2);
                    Pesel = rs.getString(3);
                    txt_Imie.setText(Imie);
                    txt_Nazwisko.setText(Nazwisko);
                    txt_Pesel.setText(Pesel);
                    
                }
      rs.close();
    }
    
    private void handleSearchVisit(KeyEvent event) throws SQLException {
        
        pst = con.prepareStatement("Select * from [PacjentDoWizyty] where Pesel = ? ");
        pst.setString(1, txt_Search.getText());
        rs = pst.executeQuery();
                if(rs.next()){
                    String Imie;
                    String Nazwisko;
                    String Pesel;
                    Imie = rs.getString(1);
                    Nazwisko = rs.getString(2);
                    Pesel = rs.getString(3);
                    txt_Imie.setText(Imie);
                    txt_Nazwisko.setText(Nazwisko);
                    txt_Pesel.setText(Pesel);
                    
                }
      rs.close();
    }
    
    
    
      
    private void fillComboBox(){
       
        doctor = FXCollections.observableArrayList();
        try {
            
            pst = con.prepareStatement("Select * from [lekarz] ");
            rs = pst.executeQuery();
            while(rs.next()){
                
               
               doctor.add(new doctor.DoctorList(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
          
           txt_WyborLekarz.setItems(doctor);
        } catch (SQLException ex) {
            Logger.getLogger(BookAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        txt_WyborLekarz.setConverter(new StringConverter<doctor.DoctorList>(){
            
            @Override
            public String toString(doctor.DoctorList object){
                
                //throw new UnsupportedOperationException("Brak wsparcia");
                return "dr " + object.getNazwaSpecjalisty() + " " + object.getNrGabinetu();
                 
            }
            
            @Override
            public doctor.DoctorList fromString(String string){
               // throw new UnsupportedOperationException("Brak wsparcia");
               return null;
            }
         
        });
        
        txt_WyborLekarz.valueProperty().addListener((ObservableValue<? extends DoctorList> obs, DoctorList oldValue, DoctorList newValue) -> {
            if(newValue!=null){
                
                WyborLekarz = "dr " + newValue.getNazwaSpecjalisty() + " " + newValue.getNrGabinetu();
            }
        });
    }
   
    
    
}
    
   
    
    
    

