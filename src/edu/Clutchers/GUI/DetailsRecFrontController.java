/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Clutchers.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author sinda
 */
public class DetailsRecFrontController implements Initializable {

    @FXML
    private TextField tfSujet;
    @FXML
    private TextField tfContent;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfEmail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    public void setTextSujet(String msg){
        this.tfSujet.setText(msg); 
    }
     public void setTextContent(String msg){
        this.tfContent.setText(msg); 
    }
     public void setTextNom(String msg){
        this.tfNom.setText(msg); 
    }
     public void setTextEmail(String msg){
        this.tfEmail.setText(msg); 
    }
    
    

    @FXML
    private void RetourRec(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FrontRecProd.fxml"));
                Parent root;
      try {
            root = loader.load();
             tfNom.getScene().setRoot(root);
           
          
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
