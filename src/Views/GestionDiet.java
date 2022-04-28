/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Models.Diet;
import com.sun.speech.freetts.VoiceManager;
import controllers.DietService;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javax.speech.synthesis.Voice;
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
    ObservableList<Diet>priseMassList;
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
    @FXML
    private Button listen;
    @FXML
    private PieChart pieDiet;
    @FXML
    private Text lb1;
    @FXML
    private Button statBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherDiet();
                tableDiet.setVisible(true);
        search.setVisible(true);
        lb1.setVisible(true);
        pieDiet.setVisible(false);
        statBack.setVisible(false);

       
             DietService ac = new DietService();
        ObservableList<PieChart.Data> piecommentData = null;
        try {
            piecommentData = FXCollections.observableArrayList(
                    new PieChart.Data("Prise de masse", ac.countDiet()),
                    new PieChart.Data("Perte de Masse", ac.countDietLow()));
        } catch (SQLException ex) {
            Logger.getLogger(GestionDiet.class.getName()).log(Level.SEVERE, null, ex);
        }

        piecommentData.forEach(data
                -> data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), " amount: ", data.pieValueProperty()
                        )
                )
        );

        pieDiet.getData().addAll(piecommentData);
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
                //sendSMS sm = new sendSMS();
                // sm.sendSMS(a);
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
         Diet clickedDiet = tableDiet.getSelectionModel().getSelectedItem();
         d_id.setText(String.valueOf(clickedDiet.getId()));
        d_titre.setText(String.valueOf(clickedDiet.getTitle()));
        d_content.setText(String.valueOf(clickedDiet.getContent()));
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

    @FXML
    private void VoiceTTS(MouseEvent event) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        com.sun.speech.freetts.Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        voice.allocate();
        float vol = 100;
        voice.setVolume(vol);
        String voiceContent = d_content.getText();
        
        if(voiceContent.isEmpty()){
            voiceContent="Please select a diet to get it ready to review !";
        }else{
            voiceContent = d_content.getText();
        }
        voice.speak(voiceContent);
    }

    @FXML
    private void statShow(MouseEvent event) {
        tableDiet.setVisible(false);
        search.setVisible(false);
        lb1.setVisible(false);
        pieDiet.setVisible(true);
        statBack.setVisible(true);
    }

    @FXML
    private void backIndex(MouseEvent event) {
        tableDiet.setVisible(true);
        search.setVisible(true);
        lb1.setVisible(true);
        pieDiet.setVisible(false);
        statBack.setVisible(false);

    }
    
}
