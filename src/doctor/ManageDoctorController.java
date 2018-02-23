/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import patient.DodajPacjenta;


/**
 * FXML Controller class
 *
 * @author Paweł
 */
public class ManageDoctorController implements Initializable {

    
    
    
    @FXML
    private TableView<DoctorList> tableLekarz;
    @FXML
    private TableColumn<?, ?> colImie;
    @FXML
    private TableColumn<?, ?> colNazwisko;
    @FXML
    private TableColumn<?, ?> colNazwaSpecjalisty;
    @FXML
    private TableColumn<?, ?> colNrGabinetu;
    @FXML
    private TableColumn<?, ?> colTelefon;
    @FXML
    private TableColumn<?, ?> colEmail;
    @FXML
    private TextField txt_Search;
    @FXML
    private Button btn_Home;
    
    
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ObservableList<DoctorList> data;
    
    
    Stage dialogStage = new Stage();
    Scene scene;
    
    
    
 private void loadDataFromDatabase(){
     
     String sql = "select * from [WykazLekarzy]";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
              data.add(new DoctorList( rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
                  
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageDoctorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableLekarz.setItems(data);
    
    }
   
    private void setCellTable(){
        
        //colID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colImie.setCellValueFactory(new PropertyValueFactory<>("Imie"));
        colNazwisko.setCellValueFactory(new PropertyValueFactory<>("Nazwisko"));
        colNazwaSpecjalisty.setCellValueFactory(new PropertyValueFactory<>("NazwaSpecjalisty"));
        colNrGabinetu.setCellValueFactory(new PropertyValueFactory<>("NrGabinetu"));
        colTelefon.setCellValueFactory(new PropertyValueFactory<>("Telefon"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colTelefon.setCellValueFactory(new PropertyValueFactory<>("Telefon"));
        
   
    }
 
    private void searchDoctor(){
        
            String sql = "Select * from [WykazLekarzy] where Imie LIKE '%"+txt_Search.getText()+"%'"
                    + "Union Select * from [WykazLekarzy] where Nazwisko LIKE '%"+txt_Search.getText()+"%'"
                    + "Union Select * from [WykazLekarzy] where Specjalista LIKE '%"+txt_Search.getText()+"%'"
                    + "Union Select * from [WykazLekarzy] where NrGabinetu LIKE '%"+txt_Search.getText()+"%'"
                    + "Union Select * from [WykazLekarzy] where Telefon LIKE '%"+txt_Search.getText()+"%'"
                    + "Union Select * from [WykazLekarzy] where Email LIKE '%"+txt_Search.getText()+"%'";
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
                               System.out.println(""+rs.getString(1));
                                    data.add(new DoctorList( rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));


                            }
                            tableLekarz.setItems(data);

                        } catch (SQLException ex) {
                            Logger.getLogger(DoctorList.class.getName()).log(Level.SEVERE, null, ex);
                        }


                    }
            });
    
    
    
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = Database.DBConnection.WizytyPrzychoniaConnection();
        data = FXCollections.observableArrayList();
        setCellTable();
        searchDoctor();
        loadDataFromDatabase();
        
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
    
}
