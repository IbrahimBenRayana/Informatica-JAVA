/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Clutchers.GUI;

import edu.Clutchers.entities.Rating;
import edu.Clutchers.entities.Reclamation;
import edu.Clutchers.services.RatingCRUD;
import edu.Clutchers.services.ReclamationCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;

/**
 * FXML Controller class
 *
 * @author sinda
 */
public class FrontRecProdController implements Initializable {

    @FXML
    private TextField tfSujet;
    @FXML
    private TextField tfContent;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfEmail;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnRetour1;
    @FXML
    private TextField tfRate;
    @FXML
    private Button btnEnvoyerrate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void EnvoyerRec(ActionEvent event) {
        
        if (tfSujet.getText().isEmpty()) {
            tfSujet.requestFocus();
            //Animations.shake(txtNom);
            Alert missingFields = new Alert(Alert.AlertType.INFORMATION);
                missingFields.setHeaderText(null);
                missingFields.setContentText("veuillez remplir tout les champs");
                missingFields.initModality(Modality.APPLICATION_MODAL);
                missingFields.showAndWait();
            return;
        }
        
        if (tfContent.getText().isEmpty()) {
            tfContent.requestFocus();
            Alert missingFields = new Alert(Alert.AlertType.INFORMATION);
                missingFields.setHeaderText(null);
                missingFields.setContentText("veuillez remplir tout les champs");
                missingFields.initModality(Modality.APPLICATION_MODAL);
                missingFields.showAndWait();
            //Animations.shake(txtNom);
            return;
        }
        
        if (tfNom.getText().isEmpty()) {
            tfNom.requestFocus();
            Alert missingFields = new Alert(Alert.AlertType.INFORMATION);
                missingFields.setHeaderText(null);
                missingFields.setContentText("veuillez remplir tout les champs");
                missingFields.initModality(Modality.APPLICATION_MODAL);
                missingFields.showAndWait();
            //Animations.shake(txtNom);
            return;
        }
        
        int position1 =  tfEmail.getText().indexOf("@");
        int position2 =  tfEmail.getText().indexOf(".");
        if (tfEmail.getText().isEmpty() ) {
            tfEmail.requestFocus();
            Alert missingFields = new Alert(Alert.AlertType.INFORMATION);
                missingFields.setHeaderText(null);
                missingFields.setContentText("veuillez remplir tout les champs");
                missingFields.initModality(Modality.APPLICATION_MODAL);
                missingFields.showAndWait();
            //Animations.shake(txtNom);
            return;
        }
        System.out.println(position1);
        System.out.println(position2);
        if(position1 == -1 || position2 == -1 )
             {
            tfEmail.requestFocus();
            //Animations.shake(txtNom);
            return;
        }
       
        
        Reclamation r = new Reclamation(tfSujet.getText(),tfContent.getText(),tfNom.getText(),tfEmail.getText());
        ReclamationCRUD sr = new ReclamationCRUD();
        sr.ajouterRec2(r);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Reclamation envoyée ",ButtonType.OK);
        a.show();
         init();
      
    
         
    }

    @FXML
    private void DetailsRec(ActionEvent event) {
          Reclamation r = new Reclamation(tfSujet.getText(),tfContent.getText(),tfNom.getText(),tfEmail.getText());
         FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsRecFront.fxml"));
                Parent root;
      try {
            root = loader.load();
             tfNom.getScene().setRoot(root);
                
             DetailsRecFrontController arc = loader.getController();
            arc.setTextSujet(r.getObjet_rec());
            arc.setTextContent(r.getContenu_rec());
            arc.setTextNom(r.getNomadh());
            arc.setTextEmail(r.getMailadh());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FirstPage.fxml"));
         Parent root = loader.load();
         FirstPageController ap = loader.getController();
         btnRetour.getScene().setRoot(root);
    }

    @FXML
    private void EnvoyerR(ActionEvent event) {
        if (tfRate.getText().isEmpty()) {
            tfRate.requestFocus();
            Alert missingFields = new Alert(Alert.AlertType.INFORMATION);
                missingFields.setHeaderText(null);
                missingFields.setContentText("veuillez remplir ce champs");
                missingFields.initModality(Modality.APPLICATION_MODAL);
                missingFields.showAndWait();
            //Animations.shake(txtNom);
            return;
        }
          Rating r = new Rating(tfRate.getText());
        RatingCRUD sr = new RatingCRUD();
        sr.ajouterR(r);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Rating envoyée ",ButtonType.OK);
        a.show();
        init();
    }
    
     private void init()
    {  
      
      tfContent.setText("");
      tfEmail.setText("");
      tfNom.setText("");
      tfRate.setText("");
      tfSujet.setText("");
    }
    
}
