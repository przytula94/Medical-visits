/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient;



import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import java.sql.Date;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;  
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import wizytyprzychodnia.MainController;
import static login.LoginController.infoBox;

/**
 * FXML Controller class
 *
 * @author Paweł
 */
public class DodajPacjenta implements Initializable {

    @FXML
    private Button btn_addPatient;
    @FXML
    private Button btn_DeletePatient;
    @FXML
    private Button btn_EditPatient;
    @FXML
    private TextField txt_Imie;
    @FXML
    private TextField txt_Nazwisko;
    @FXML
    private TextField txt_PESEL;
    @FXML
    private TextField txt_Ulica;
    @FXML
    private TextField txt_Miejscowosc;
    @FXML
    private TextField txt_KodPocztowy;
    @FXML
    private TextField txt_Telefon;
    @FXML
    private TextField txt_Email;
    //@FXML
   // private DatePicker txt_DataUrodzenia;

    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ObservableList<PacjentLista> data;
    
    @FXML
    private TableView<PacjentLista> tablePacjent;
    @FXML
    private TableColumn<?, ?> colImie;
    @FXML
    private TableColumn<?, ?> colNazwisko;
    @FXML
    private TableColumn<?, ?> colPESEL;
    @FXML
    private TableColumn<?, ?> colUlica;
    @FXML
    private TableColumn<?, ?> colMiejscowosc;
    @FXML
    private TableColumn<?, ?> colKodPocztowy;
    @FXML
    private TableColumn<?, ?> colTelefon;
    @FXML
    private TableColumn<?, ?> colEmail;
    //@FXML
    //private TableColumn<?, ?> colDataUrodzenia;
    
    
    @FXML
    private Label error_Name;
    @FXML
    private Label error_Surname;
    @FXML
    private Label error_PESEL;
    @FXML
    private Label error_Street;
    @FXML
    private Label error_City;
    @FXML
    private Label error_PostalCode;
    @FXML
    private Label error_Phone;
    @FXML
    private Label error_Email;
    @FXML
    private TextField txt_Search;
    @FXML
    private Button btn_BookAppointment;
    @FXML
    private Button btn_EditVisit;
    @FXML
    private Button btn_DeleteVisit;
   
    
    
    
    
    @FXML
    public void handleAddPatient(ActionEvent event) throws SQLException {
        
          boolean isNameEmpty = checkdata.TextFieldCheck.isTextFieldNotEmpty(txt_Imie, error_Name, "Imię jest wymagane");
          boolean isSurnameEmpty = checkdata.TextFieldCheck.isTextFieldNotEmpty(txt_Nazwisko, error_Surname, "Nazwisko jest wymagane");
          boolean isPESELEmpty = checkdata.TextFieldCheck.isTextFieldTypeNumber(txt_PESEL, error_PESEL, "PESEl jest wymagany, może zawierać tylko cyfry");
          boolean isStreetEmpty = checkdata.TextFieldCheck.isTextFieldNotEmpty(txt_Ulica, error_Street, "Ulica jest wymagana");
          boolean isCityEmpty = checkdata.TextFieldCheck.isTextFieldNotEmpty(txt_Miejscowosc, error_City, "Miejscowosc jest wymagana");
          boolean isPostalCodeEmpty = checkdata.TextFieldCheck.isTextFieldNotEmpty(txt_KodPocztowy, error_PostalCode, "Kod Pocztowy jest wymagany");
          boolean isPhoneEmpty = checkdata.TextFieldCheck.isTextFieldNotEmpty(txt_Telefon, error_Phone, "Telefon jest wymagany");
          boolean isEmailEmpty = checkdata.TextFieldCheck.isTextFieldNotEmpty(txt_Email, error_Email, "Email jest wymagany");
          if(isNameEmpty && isSurnameEmpty && isPESELEmpty && isStreetEmpty && isCityEmpty && isPostalCodeEmpty && isPhoneEmpty && isEmailEmpty){
        
        String sql = "Insert into pacjent(Imie,Nazwisko,Pesel,Ulica,Miejscowosc,KodPocztowy,Telefon, Email) Values(?,?,?,?,?,?,?,?)";
        String Imie = txt_Imie.getText();
        String Nazwisko = txt_Nazwisko.getText();
        String PESEL = txt_PESEL.getText();
        String Ulica = txt_Ulica.getText();
        String Miejscowosc = txt_Miejscowosc.getText();
        String KodPocztowy = txt_KodPocztowy.getText();
        String Telefon = txt_Telefon.getText();
        String Email = txt_Email.getText();
     

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, Imie);
            pst.setString(2, Nazwisko);
            pst.setString(3, PESEL);
            pst.setString(4, Ulica);
            pst.setString(5, Miejscowosc);
            pst.setString(6, KodPocztowy);
            pst.setString(7, Telefon);
            pst.setString(8, Email);
            //pst.setDate(9, java.sql.Date.valueOf(txt_DataUrodzenia.getValue()));
            
            int i = pst.executeUpdate();
            
            if(i==1){
                System.out.println("Dodano rekord do tabeli");
                setCellTable();
                clearTextField();
                loadDataFromDatabase();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DodajPacjenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            infoBox("Dodano pacjenta do bazy", null, "Dodawanie pacjenta" );
            pst.close();
            
        }
    }
    }
    
    @FXML
    private void handleDeletePatient(ActionEvent event) {
        boolean isPESELEmpty = checkdata.TextFieldCheck.isTextFieldTypeNumber(txt_PESEL, error_PESEL, "PESEl jest wymagany do usuniecia rekordu");
        if(isPESELEmpty){
        String sql = "delete from [pacjent] where Pesel = ?";
        try {
           
            pst = con.prepareStatement(sql);
            pst.setString(1, txt_PESEL.getText());
            int i = pst.executeUpdate();
            if(i == 1){
                
                System.out.println("Dane zostały usunięte");
                loadDataFromDatabase();
                clearTextField();
         }
      
        } catch (SQLException ex) {
            Logger.getLogger(DodajPacjenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        }
        infoBox("Pomyślnie usunięto pacjenta z bazy", null, "Usuwanie pacjenta" );
     
    }

    
    @FXML
    private void handleEditPatient(ActionEvent event) {
         boolean isNameEmpty = checkdata.TextFieldCheck.isTextFieldNotEmpty(txt_Imie, error_Name, "Imię jest wymagane");
         boolean isSurnameEmpty = checkdata.TextFieldCheck.isTextFieldNotEmpty(txt_Nazwisko, error_Surname, "Nazwisko jest wymagane");
         boolean isPESELEmpty = checkdata.TextFieldCheck.isTextFieldTypeNumber(txt_PESEL, error_PESEL, "PESEL jest wymagany i może zawierać tylko cyfry");
       if(isNameEmpty && isSurnameEmpty && isPESELEmpty){
         String sql = "Update pacjent set Imie = ?, Nazwisko = ?, Ulica = ?, Miejscowosc = ?, KodPocztowy = ?, Telefon = ?, Email= ? where Pesel = ?";
        try {
            
            
            String Imie = txt_Imie.getText();
            String Nazwisko = txt_Nazwisko.getText();
            String PESEL = txt_PESEL.getText();
            String Ulica = txt_Ulica.getText();
            String Miejscowosc = txt_Miejscowosc.getText();
            String KodPocztowy = txt_KodPocztowy.getText();
            String Telefon = txt_Telefon.getText();
            String Email = txt_Email.getText();
            
            
            
            pst = con.prepareStatement(sql);
            pst.setString(1, Imie);
            pst.setString(2, Nazwisko);
            pst.setString(3, Ulica);
            pst.setString(4, Miejscowosc);
            pst.setString(5, KodPocztowy);
            pst.setString(6, Telefon);
            pst.setString(7, Email);
            pst.setString(8, PESEL);
                  
            
                int i = pst.executeUpdate();
            
                    if(i==1){
                                System.out.println("Dane zostały zmienione");
                                loadDataFromDatabase();
                                clearTextField();
                            }  
                 
                    
                    } catch (SQLException ex) {
            Logger.getLogger(DodajPacjenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
        
        infoBox("Dane zostały zmienione", null, "Edycja danych" );
    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        con = Database.DBConnection.WizytyPrzychoniaConnection();
        data = FXCollections.observableArrayList();
        setCellTable();
        loadDataFromDatabase();
        setCallValueFromTableToTextfield();
        searchPatient();
        
        
        
    }    
    
    private void setCellTable(){
        
        //colID.setCellValueFactory(new PropertyValueFactory<>("PacjentID"));
        colImie.setCellValueFactory(new PropertyValueFactory<>("Imie"));
        colNazwisko.setCellValueFactory(new PropertyValueFactory<>("Nazwisko"));
        colPESEL.setCellValueFactory(new PropertyValueFactory<>("Pesel"));
        colUlica.setCellValueFactory(new PropertyValueFactory<>("Ulica"));
        colMiejscowosc.setCellValueFactory(new PropertyValueFactory<>("Miejscowosc"));
        colKodPocztowy.setCellValueFactory(new PropertyValueFactory<>("KodPocztowy"));
        colTelefon.setCellValueFactory(new PropertyValueFactory<>("Telefon"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        //colDataUrodzenia.setCellValueFactory(new PropertyValueFactory<>("DataUrodzenia"));
   
    }
    
    
    private void loadDataFromDatabase(){
     data.clear();
        try {
            pst = con.prepareStatement("Select * from [pacjent]");
            rs = pst.executeQuery();
            while(rs.next()){
                data.add(new PacjentLista( rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
                  
            }
        } catch (SQLException ex) {
            Logger.getLogger(DodajPacjenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        tablePacjent.setItems(data);
    
    }
    
    private void setCallValueFromTableToTextfield(){
        
        tablePacjent.setOnMouseClicked((MouseEvent event) -> {
            PacjentLista pl = tablePacjent.getItems().get(tablePacjent.getSelectionModel().getSelectedIndex());
            
            txt_Imie.setText(pl.getImie());
            txt_Nazwisko.setText(pl.getNazwisko());
            txt_PESEL.setText(pl.getPesel());
            txt_Ulica.setText(pl.getUlica());
            txt_Miejscowosc.setText(pl.getMiejscowosc());
            txt_KodPocztowy.setText(pl.getKodPocztowy());
            txt_Telefon.setText(pl.getTelefon());
            txt_Email.setText(pl.getEmail());
        });
    
    
    }
    
    private MainController mainController;
    Stage dialogStage = new Stage();
    Scene scene;
    

    @FXML
    private void handleBookAppointment(ActionEvent event) throws IOException {
                Node node = (Node)event.getSource();
                dialogStage = (Stage) node.getScene().getWindow();
                dialogStage.close();
                scene = new Scene (FXMLLoader.load(getClass().getResource("/bookvisit/BookAppointment.fxml")));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setFullScreen(true);
                dialogStage.setScene(scene);
                dialogStage.show();
  
    }
    @FXML
    private void handleEditVisit(ActionEvent event) throws IOException {
                Node node = (Node)event.getSource();
                dialogStage = (Stage) node.getScene().getWindow();
                dialogStage.close();
                scene = new Scene (FXMLLoader.load(getClass().getResource("/bookvisit/EditVisit.fxml")));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setFullScreen(true);
                dialogStage.setScene(scene);
                dialogStage.show();
  
    }
    
  

    private void clearTextField(){

        txt_Imie.clear();
        txt_Nazwisko.clear();
        txt_PESEL.clear();
        txt_Ulica.clear();
        txt_Miejscowosc.clear();
        txt_KodPocztowy.clear();
        txt_Telefon.clear();
        txt_Email.clear();
            

    }
    
    
    private void searchPatient(){
            String sql = "Select * from [pacjent] where PESEL LIKE '%"+txt_Search.getText()+"%'";
            txt_Search.setOnKeyPressed(e->{
                if(txt_Search.getText().equals("")){
                    loadDataFromDatabase();
                }
                        else{
                        data.clear();

                        try {
                            pst = con.prepareStatement(sql);
                            rs = pst.executeQuery();

                            while(rs.next()){
                               System.out.println(""+rs.getString(2));
                                    data.add(new PacjentLista( rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));


                            }
                            tablePacjent.setItems(data);

                        } catch (SQLException ex) {
                            Logger.getLogger(DodajPacjenta.class.getName()).log(Level.SEVERE, null, ex);
                        }


                    }
            });

    }

    @FXML
    private void handleDeleteVisit(ActionEvent event) throws IOException {
                Node node = (Node)event.getSource();
                dialogStage = (Stage) node.getScene().getWindow();
                dialogStage.close();
                scene = new Scene (FXMLLoader.load(getClass().getResource("/bookvisit/DeleteVisit.fxml")));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setFullScreen(true);
                dialogStage.setScene(scene);
                dialogStage.show();
  
    }

}
    



