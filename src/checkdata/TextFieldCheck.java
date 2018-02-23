/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkdata;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Paweł
 */
public class TextFieldCheck {
    
    public static boolean isTextFieldNotEmpty(TextField tf){
        
        boolean x = false;
        if(tf.getText().length() !=0 || !tf.getText().isEmpty())
            x =true;
        return x;
        
        
    }
    
    public static boolean isTextFieldNotEmpty(TextField tf, Label lb, String errorMessage){
        boolean x = true;
        String msg = null;
        tf.getStyleClass().remove("error");
        if(!isTextFieldNotEmpty(tf)){
            x =false;
            msg = errorMessage;
            tf.getStyleClass().add("error");
        }
        lb.setText(msg);
        return x;
 
    }
    
     public static boolean isTextFieldTypeNumber(TextField tf){
        
        boolean x = false;
        if(tf.getText().matches("([0-9]+(\\.[0-9]+)?)+"));
            x =true;
        return x;
        
        
    }
    
     public static boolean isTextFieldTypeNumber(TextField tf, Label lb, String errorMessage){
        boolean x = true;
        String msg = null;
        tf.getStyleClass().remove("error");
        if(!isTextFieldNotEmpty(tf)){
            x =false;
            msg = errorMessage;
            tf.getStyleClass().add("error");
        }
        lb.setText(msg);
        return x;
 
    }
     
     
     

}
