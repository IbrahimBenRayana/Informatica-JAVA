/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Clutchers.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import edu.Clutchers.entities.Reclamation;
import edu.Clutchers.services.ReclamationCRUD;
import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 *
 * @author sinda
 */
public class GestionReclamationController implements Initializable {

    @FXML
    private TextField tfSujet;
    @FXML
    private TextArea tfContent;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfStatut;
    @FXML
    private TableView<Reclamation> tableview;
    @FXML
    private TableColumn<Reclamation, String> tcSujet;
    @FXML
    private TableColumn<Reclamation, String> tcContent;
    @FXML
    private TableColumn<Reclamation, String> tcNom;
    @FXML
    private TableColumn<Reclamation, String> tcEmail;
    @FXML
    private TableColumn<Reclamation, String> tcStatus;
    @FXML
    private Button btnUpdaterec;
    @FXML
    private Button btnDeleterec;
    @FXML
    private Button btnRetour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ReclamationCRUD rc = new ReclamationCRUD();
        List reclamations = rc.afficherRec();
        ObservableList list2 = FXCollections.observableArrayList(reclamations);
        tableview.setItems(list2);
        tcSujet.setCellValueFactory(new PropertyValueFactory<>("objet_rec"));
        tcContent.setCellValueFactory(new PropertyValueFactory<>("contenu_rec"));
        tcNom.setCellValueFactory(new PropertyValueFactory<>("nomadh"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("mailadh"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("statut"));
        
    }    

    @FXML
    private void affInInputsRec(MouseEvent event) {
         Reclamation av= tableview.getSelectionModel().getSelectedItem();
         
         
       int n=av.getId();
       String m=Integer.toString(n);
  
      tfSujet.setText(av.getObjet_rec());
      tfContent.setText(av.getContenu_rec());
       tfNom.setText(av.getNomadh());
       tfEmail.setText(av.getMailadh());
      Boolean s=av.getStatut();
      String ss=Boolean.toString(s);
      tfStatut.setText(ss);
    }

    @FXML
    private void modifierReclamation(ActionEvent event) {
        Reclamation av=tableview.getSelectionModel().getSelectedItem();
       int n=av.getId();
       Boolean m=av.getStatut();
     
       Reclamation   c= new Reclamation();
     
       ReclamationCRUD as=new  ReclamationCRUD();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez vous modifier la réclamation de  " + c.getNomadh()+ " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
       alert.showAndWait();
       if (alert.getResult() == ButtonType.YES) {
             c.setId(n);
       c.setStatut(m);
       c.setStatut(true);
       as.modifier(c);
        List reclamations = as.afficherRec();
        ObservableList list = FXCollections.observableArrayList(reclamations );
        tableview.setItems(list);
       
       tableview.refresh();}
        Alert a = new Alert(Alert.AlertType.CONFIRMATION,"la reclamation de "+c.getNomadh()+" a été modifiée ",ButtonType.OK);
         a.show();
       
       init();
    }

    @FXML
    private void supprimerReclamation(ActionEvent event) {
        Reclamation p = tableview.getSelectionModel().getSelectedItem();
       ReclamationCRUD pc = new ReclamationCRUD();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez vous supprimer la reclamation de  " + p.getNomadh()+ " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
       alert.showAndWait();
       if (alert.getResult() == ButtonType.YES) {
       pc.supprimer(p.getId());
        List reclamations = pc.afficherRec();
       ObservableList list = FXCollections.observableArrayList(reclamations );
        tableview.setItems(list);
       
       tableview.refresh();}
        Alert a = new Alert(Alert.AlertType.CONFIRMATION,"la reclamation de "+p.getNomadh()+" a été supprimée ",ButtonType.OK);
         a.show();
       init();
    }
    
      private void init()
    {
      
      
      tfSujet.setText("");
      tfContent.setText("");
       tfNom.setText("");
       tfEmail.setText("");
      tfStatut.setText("");
     
    
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Page.fxml"));
         Parent root = loader.load();
         PageController ap = loader.getController();
         btnRetour.getScene().setRoot(root);
    }
    
}
