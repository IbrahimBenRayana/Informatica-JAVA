/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Models.Diet;
import controllers.DietService;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;
import tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author waelk
 */
public class GestionDiet implements Initializable {

    @FXML
    private TableView<Diet> tableDiet;
    @FXML
    private TableColumn<Diet, String> titre;
    @FXML
    private TableColumn<Diet, String> content;
    @FXML
    private Text showWeather;
    Connection mc;
    PreparedStatement ste;
    ObservableList<Diet>dietList;
    @FXML
    private TextArea d_titre;
    @FXML
    private Button add;
    @FXML
    private Button supp;
    @FXML
    private Button mod;
    @FXML
    private TextArea d_content;
    @FXML
    private TableColumn<Diet, Integer> id;
    @FXML
    private TextArea d_id;
    @FXML
    private TextArea search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherDiet();
    }    
        void afficherDiet(){
            mc=MaConnexion.getInstance().getCnx();
        dietList = FXCollections.observableArrayList();
       
        String sql="select * from diet";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Diet e = new Diet();
                 e.setId(rs.getInt("diet_id"));
                e.setTitle(rs.getString("diet_title"));
                e.setContent(rs.getString("diet_content"));
                dietList.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         id.setCellValueFactory(new PropertyValueFactory<Diet,Integer>("id"));
        titre.setCellValueFactory(new PropertyValueFactory<Diet,String>("title"));
        content.setCellValueFactory(new PropertyValueFactory<Diet,String>("content"));
       
        tableDiet.setItems(dietList);
        search();
        
     
        
    }

    @FXML
    private void ajouter(MouseEvent event) throws SQLException {
        String titre = d_titre.getText();
        String content = d_content.getText();
     
         if (content.isEmpty() || titre.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             //alert.setHeaderText("ERROR");
             alert.setContentText("Donnees non disponible!!");
             alert.showAndWait();          
         }
         else{     
             Diet a=new Diet(titre,content,"2022-02-23 15:32:41","2022-02-23 15:32:41","",1,1);
             DietService ac = new DietService();
          
             ac.ajouterDiet(a);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
             //alert.setHeaderText("Succes");
             alert.setContentText("Diet Ajoutée avec succes!");
                alert.showAndWait();             
         }
         refresh();
    }
    
    public void refresh(){
        
         dietList.clear();
       
          
          mc=MaConnexion.getInstance().getCnx();

        dietList = FXCollections.observableArrayList();
        
        String sql="select * from diet";
        try {
          ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Diet e = new Diet();
                 e.setId(rs.getInt("diet_id"));
                e.setTitle(rs.getString("diet_title"));
                e.setContent(rs.getString("diet_content"));
                dietList.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         tableDiet.setItems(dietList);   
    }
    @FXML
    private void getEelemnts(MouseEvent event) {
         Diet clickedArticle = tableDiet.getSelectionModel().getSelectedItem();
         d_id.setText(String.valueOf(clickedArticle.getId()));
        d_titre.setText(String.valueOf(clickedArticle.getTitle()));
        d_content.setText(String.valueOf(clickedArticle.getContent()));
    }

    @FXML
    private void supprimer(MouseEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");

        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
               mc=MaConnexion.getInstance().getCnx();
            String sql = "delete from diet where diet_id = ?";
            ste=mc.prepareStatement(sql);
            ste.setString(1, d_id.getText());
            ste.execute();
            JOptionPane.showMessageDialog(null, "diet supprimé" );
        
            refresh();
             }
        else{

              d_titre.setText(null);
          d_content.setText(null);
          refresh();

        }
    }

    @FXML
    private void modifier(MouseEvent event) {
        Diet clickedEquipe = tableDiet.getSelectionModel().getSelectedItem();
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");
        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){ 
        try{
             mc=MaConnexion.getInstance().getCnx();
             String value1 = d_id.getText();
             String value2 = d_titre.getText();
             String value3 = d_content.getText();
             
             String sql = "update diet set diet_title = '"+value2+"', diet_content = '"+value3+"'  where diet_id  ='"+value1+"' ";
             ste=mc.prepareStatement(sql);
             ste.execute();
            JOptionPane.showMessageDialog(null, "Diet modifié");
        }catch(Exception e){
               JOptionPane.showMessageDialog(null,e);

        }
        refresh();
               }
        else{

            d_titre.setText(null);
            d_content.setText(null);

        }
        refresh();
     
    }
private void search() {      
        
        FilteredList<Diet>filteredData = new FilteredList<>(dietList, b->true);
        search.textProperty().addListener((observable, oldValue, newValue)->{
           
            filteredData.setPredicate(article->{
               
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
               
                String lowerCaseFilter = newValue.toLowerCase();
                 
                  if(String.valueOf(article.getId()).indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                  else if(String.valueOf(article.getTitle()).indexOf(lowerCaseFilter) != -1){
                    return true;
                }else{
                return false;
                }
            });          
        });
        SortedList<Diet>sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableDiet.comparatorProperty());
        tableDiet.setItems(sortedData);
    }
    
}
