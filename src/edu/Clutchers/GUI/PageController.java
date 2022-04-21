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
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author sinda
 */
public class PageController implements Initializable {

    @FXML
    private Button btnRedrec;
    @FXML
    private Button btnRedrating;
    @FXML
    private Button btnRetour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Redrec(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionReclamation.fxml"));
         Parent root = loader.load();
         GestionReclamationController ap = loader.getController();
         btnRedrec.getScene().setRoot(root);
    }

    @FXML
    private void Redrating(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionRating.fxml"));
         Parent root = loader.load();
        GestionRatingController ap = loader.getController();
         btnRedrating.getScene().setRoot(root);
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FirstPage.fxml"));
         Parent root = loader.load();
         FirstPageController ap = loader.getController();
         btnRetour.getScene().setRoot(root);
    }
    
}
