/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Paweł
 */
public class LoginController implements Initializable {

    @FXML
    private JFXButton btn_Login;
    @FXML
    private JFXButton btn_Anuluj;
    @FXML
    private JFXTextField txt_Email;
    @FXML
    private JFXPasswordField txt_Password;
   
    Stage dialogStage = new Stage();
    Scene scene;
    
    
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null; 
    @FXML
    private AnchorPane anchorPane;
    
    public static void infoBox(String infoMessage, String headerText, String title){
            
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText(infoMessage);
            alert.setTitle(title);
            alert.setHeaderText(headerText);
            alert.showAndWait();
            
        }
    
    public LoginController(){
        con = Database.DBConnection.WizytyPrzychoniaConnection();
        
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        con = Database.DBConnection.WizytyPrzychoniaConnection();
                
        
        
        // TODO
    }    

    @FXML
    private void handleLogin(ActionEvent event) {
        
        
        
        String Email = txt_Email.getText().toString();
        String Haslo = txt_Password.getText().toString();
        
        String sql = "Select Email, Haslo From [user] WHERE Email = ? and Haslo = ? ";
        
        try{
            
            pst = con.prepareStatement(sql);
            pst.setString(1, Email);
            pst.setString(2, Haslo);
            rs = pst.executeQuery();
            
            if(!rs.next()){
                infoBox("Wprowadź poprawny adres email lub haslo", null, "Niepowodzenie" );
         
            }else{
                infoBox("Logowanie udane", null, "Zalogowano" );
                Node node = (Node)event.getSource();
                
                dialogStage = (Stage) node.getScene().getWindow();
                //dialogStage.close();
                scene = new Scene (FXMLLoader.load(getClass().getResource("/wizytyprzychodnia/BorderPaneMain.fxml")));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setFullScreen(true);
                dialogStage.setScene(scene);
                dialogStage.show();
                
            }
   
        }
        catch(IOException | SQLException e){
            
            e.printStackTrace();
            
        }
    
    }
       

    
    @FXML
    private void handleAnuluj(ActionEvent event){
        
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
        
    }
    
    
    
}
