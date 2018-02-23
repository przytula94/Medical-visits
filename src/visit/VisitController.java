/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visit;

import doctor.DoctorList;
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
import javafx.stage.StageStyle;
import patient.DodajPacjenta;
import patient.PacjentLista;

/**
 * FXML Controller class
 *
 * @author Paweł
 */
public class VisitController implements Initializable {

    @FXML
    private Button btn_Home;
    
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    @FXML
    private TableColumn<?, ?> txt_Imie;
    @FXML
    private TableColumn<?, ?> txt_Nazwisko;
    @FXML
    private TableColumn<?, ?> txt_PESEL;
    @FXML
    private TableColumn<?, ?> txt_DataWizyty;
    @FXML
    private TableColumn<?, ?> txt_Lekarz;
    @FXML
    private TableView<VisitList> table_Wizyty;
    
    private ObservableList<VisitList> data;
    @FXML
    private TableColumn<?, ?> txt_GodzinaWizyty;
    @FXML
    private TextField txt_search;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        con = Database.DBConnection.WizytyPrzychoniaConnection();
        data = FXCollections.observableArrayList();
        setCellTable();
        loadDataFromDatabase();
        
        
        
        
        // TODO
    }    

    Stage dialogStage = new Stage();
    Scene scene;
    @FXML
    private void handleReturnToHome(ActionEvent event) throws IOException {
        
        Node node = (Node)event.getSource();
                dialogStage = (Stage) node.getScene().getWindow();
                //dialogStage.close();
                scene = new Scene (FXMLLoader.load(getClass().getResource("/wizytyprzychodnia/BorderPaneMain.fxml")));
                dialogStage.setScene(scene);
                dialogStage.show();
    }
    
    private void loadDataFromDatabase(){
     
        String sql = "Select * from [rezerwacja]";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                data.add(new VisitList( rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
                  
            }
        } catch (SQLException ex) {
            Logger.getLogger(DodajPacjenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        table_Wizyty.setItems(data);
    
    }
    private void setCellTable(){
        
        
        txt_Imie.setCellValueFactory(new PropertyValueFactory<>("Imie"));
        txt_Nazwisko.setCellValueFactory(new PropertyValueFactory<>("Nazwisko"));
        txt_PESEL.setCellValueFactory(new PropertyValueFactory<>("Pesel"));
        txt_DataWizyty.setCellValueFactory(new PropertyValueFactory<>("DataWizyty"));
        txt_GodzinaWizyty.setCellValueFactory(new PropertyValueFactory<>("GodzinaWizyty"));
        txt_Lekarz.setCellValueFactory(new PropertyValueFactory<>("Lekarz"));

    }  
    
    
    
   
    }

