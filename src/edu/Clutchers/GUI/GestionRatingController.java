/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Clutchers.GUI;


import edu.Clutchers.entities.Rating;
import edu.Clutchers.services.RatingCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;

/**
 * FXML Controller class
 *
 * @author sinda
 */
public class GestionRatingController implements Initializable {

    @FXML
    private TableView<?> tvRating;
    @FXML
    private TableColumn<?, ?> tcRate;
    @FXML
    private TextField tfRateindex;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnRetour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       RatingCRUD rc = new RatingCRUD();
        List ratingg = rc.afficherR();
        ObservableList list2 = FXCollections.observableArrayList(ratingg);
        tvRating.setItems(list2);
        tcRate.setCellValueFactory(new PropertyValueFactory<>("rate_index"));
    }    

    @FXML
    private void select(MouseEvent event) {
          Rating av= (Rating) tvRating.getSelectionModel().getSelectedItem();
         
         
       int n=av.getId();
       String m=Integer.toString(n);
  
      tfRateindex.setText(av.getRate_index());
    }

     private void init()
    {  
      
      tfRateindex.setText("");
    }

    @FXML
    private void modifierRating(ActionEvent event) {
          if (tfRateindex.getText().isEmpty()) {
            tfRateindex.requestFocus();
            //Animations.shake(txtNom);
            Alert missingFields = new Alert(Alert.AlertType.INFORMATION);
                missingFields.setHeaderText(null);
                missingFields.setContentText("veuillez remplir tout les champs");
                missingFields.initModality(Modality.APPLICATION_MODAL);
                missingFields.showAndWait();
            return;
        }
       Rating av=(Rating) tvRating.getSelectionModel().getSelectedItem();
       int n=av.getId();
       String m=tfRateindex.getText();
     
       Rating c = new Rating();
       c.setId(n);
       c.setRate_index(m);
       RatingCRUD as=new RatingCRUD();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez vous modifier   " + c.getRate_index()+ " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
       alert.showAndWait();
       if (alert.getResult() == ButtonType.YES) {
       as.modifierR(c);
       
         List ratingg = as.afficherR();
       ObservableList list = FXCollections.observableArrayList(ratingg);
       tvRating.setItems(list);
         Alert a = new Alert(Alert.AlertType.CONFIRMATION,"  modifiée ",ButtonType.OK);
        tvRating.refresh();
       init();}
    }

    @FXML
    private void supprimerRating(ActionEvent event) {
         Rating c = (Rating) tvRating.getSelectionModel().getSelectedItem();
       RatingCRUD cc = new RatingCRUD();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez vous supprimer   " + c.getRate_index()+ " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
       alert.showAndWait();
       if (alert.getResult() == ButtonType.YES) {
       cc.supprimerR(c.getId());
        List ratingg = cc.afficherR();
       ObservableList list = FXCollections.observableArrayList(ratingg);
       tvRating.setItems(list);
       
       tvRating.refresh();}
         Alert a = new Alert(Alert.AlertType.CONFIRMATION," supprimée ",ButtonType.OK);
         a.show();
        tvRating.refresh();
        init();
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Page.fxml"));
         Parent root = loader.load();
         PageController ap = loader.getController();
         btnRetour.getScene().setRoot(root);
    }
    
    
}
