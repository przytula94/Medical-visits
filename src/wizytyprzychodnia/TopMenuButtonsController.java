/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wizytyprzychodnia;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import wizytyprzychodnia.MainController;
import wizytyprzychodnia.MainController;

/**
 * FXML Controller class
 *
 * @author Paweł
 */
public class TopMenuButtonsController implements Initializable {

    
    
    
    @FXML
    private ToggleButton btn_addPatient;
    @FXML
    private ToggleButton btn_addDoctor;
    @FXML
    private ToggleButton btn_addvisit;
    @FXML
    private ToggleButton btn_addStatistic;
    
    @FXML
    private ToggleGroup toggleButtons;
    
    private MainController mainController;
    @FXML
    private ImageView btn_Statistics;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleOpenPatient(ActionEvent event) throws IOException {
       
        mainController.setCentre("/patient/AddPatient.fxml");
      
    }

    @FXML
    private void handleOpenDoctor(ActionEvent event) {
        
        mainController.setCentre("/doctor/ManageDoctor.fxml");
    }

    @FXML
    private void handleOpenVisit(ActionEvent event) {
        
         mainController.setCentre("/visit/visit.fxml");
        
        
    }
    
    @FXML
    private void handleOpenStatistic(MouseEvent event) {
        
         mainController.setCentre("/statistics/statistics.fxml");
        
    }
    
    
    

    public void setMainController(MainController mainController){
        
        this.mainController = mainController;
        
    }

   
    
}
