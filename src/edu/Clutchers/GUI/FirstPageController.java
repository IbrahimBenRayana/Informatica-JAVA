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
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.application.Application;


/**
 * FXML Controller class
 *
 * @author sinda
 */
public class FirstPageController implements Initializable {

    @FXML
    private Button btnFront;
    @FXML
    private Button btnBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Redirectfront(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FrontRecProd.fxml"));
         Parent root = loader.load();
         FrontRecProdController ap = loader.getController();
         btnFront.getScene().setRoot(root);
       
    
    }

    @FXML
    private void ReditectBack(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Page.fxml"));
         Parent root = loader.load();
        PageController ap = loader.getController();
         btnBack.getScene().setRoot(root);
    }
    
}
