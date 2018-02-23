/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wizytyprzychodnia;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Paweł
 */
public class MainController implements Initializable {
    
    @FXML
    private BorderPane borderPane;
    @FXML
    private TopMenuButtonsController topMenuButtonsController;
    
    
    

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        topMenuButtonsController.setMainController(this);
        
        
    }    
    
    public void setCentre(String fxmlPath){
        
        try {    
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxmlPath));
            Parent parent = loader.load();
            borderPane.setTop(parent);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.centerOnScreen();
            
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }

}
