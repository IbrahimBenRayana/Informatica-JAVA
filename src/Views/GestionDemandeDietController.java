/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.DemandeDietController;
import Models.DemandeDiet;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
public class GestionDemandeDietController implements Initializable {

    @FXML
    private Button dr_add;
    @FXML
    private TableView<DemandeDiet> tableDietRequest;
    @FXML
    private TableColumn<DemandeDiet, Integer> user;
    @FXML
    private TableColumn<DemandeDiet, String> dietObj;
    @FXML
    private TextField id;
    @FXML
    private TableColumn<DemandeDiet, String> state;
    @FXML
    private Button dr_supp;
    @FXML
    private Button dr_mod;
    @FXML
    private Text showWeather;
    @FXML
    private TextArea d_id;
    @FXML
    private ComboBox<String> dr_user;
    @FXML
    private ComboBox<String> dr_object;
       Connection mc;
    PreparedStatement ste;
           ObservableList<DemandeDiet>DemandeDietList;
    @FXML
    private TableColumn<DemandeDiet, Integer> dr_id;
 DemandeDietController eq1 = new DemandeDietController();



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           mc=MaConnexion.getInstance().getCnx();

        DemandeDietList = FXCollections.observableArrayList();
        
        String sql="select * from demande_diet";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                DemandeDiet e = new DemandeDiet();
                e.setId(rs.getInt("id"));
                e.setUser_id(rs.getInt("user_id_id"));
                e.setDiet_obj(rs.getString("diet_object"));
                e.setEtatDemande(rs.getString("etat_demande"));
                DemandeDietList.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        

        
       //  idEquipe.setCellValueFactory(new PropertyValueFactory<Equipe, Integer>("id"));
               dr_id.setCellValueFactory(new PropertyValueFactory<DemandeDiet, Integer>("id"));

        user.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        dietObj.setCellValueFactory(new PropertyValueFactory<>("diet_obj"));
        state.setCellValueFactory(new PropertyValueFactory<>("etatDemande"));




        
        tableDietRequest.setItems(DemandeDietList);    
    loadDataIntoChoiBox();
    loadDataIntoChoiBox2();
    }   
     public void loadDataIntoChoiBox(){
        
           ObservableList<String> list = FXCollections.observableArrayList();
//         ComboBox comboBox = new ComboBox(options);
         //  comboBox.setMaxHeight(30);
            mc=MaConnexion.getInstance().getCnx();
           
           String sql = "select id from user";
                      
              try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
               //String nom = rs.getString("nom").toString();
             list.add(rs.getString("id"));
             
           
            }
//            ste.close();
//            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
              
              
              dr_user.setItems(null);
              dr_user.setItems(list);
        
       }     
     public void loadDataIntoChoiBox2(){
        
           ObservableList<String> list = FXCollections.observableArrayList();
//         ComboBox comboBox = new ComboBox(options);
         //  comboBox.setMaxHeight(30);
          list.add("Perte de masse");    
          list.add("Prise de masse");    
              dr_object.setItems(null);
              dr_object.setItems(list);
        
       } 

    @FXML
    private void ajouter(MouseEvent event) throws SQLException {
        
         // int id = Integer.parseInt(idJ.getText());           
       //     int combo_user;
      //  combo_user = Integer.valueOf(dr_user.getSelectionModel().getSelectedItem());
       int combo = Integer.valueOf(dr_user.getSelectionModel().getSelectedItem());
                String combo_obj = String.valueOf(dr_object.getSelectionModel().getSelectedItem());
            
          //  System.out.println(equipe_id);

          
          String sql="select * from demande_diet";
        ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
          
               DemandeDiet dd=new DemandeDiet(combo,combo_obj,"Non Traiter !");
             DemandeDietController ec = new DemandeDietController();
             ec.ajouterDemandeDiet(dd);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setHeaderText("Succes");
             alert.setContentText("Demande Envouyer !");
                alert.showAndWait();   
              
          
        
          
             refresh();          
          
          
            
        
    }
    public void refresh(){
        
         DemandeDietList.clear();
       
          
          mc=MaConnexion.getInstance().getCnx();

        DemandeDietList = FXCollections.observableArrayList();
        
        String sql="select * from demande_diet";
        try {
          ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                DemandeDiet e = new DemandeDiet();
                e.setId(rs.getInt("id"));
                e.setUser_id(rs.getInt("user_id_id"));
                e.setDiet_obj(rs.getString("diet_object"));
                e.setEtatDemande(rs.getString("etat_demande"));
                DemandeDietList.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         tableDietRequest.setItems(DemandeDietList);   
    }

    @FXML
    private void getEelemnts(MouseEvent event) {
                 DemandeDiet clickedArticle = tableDietRequest.getSelectionModel().getSelectedItem();
         id.setText(String.valueOf(clickedArticle.getId()));

    }

    @FXML
    private void supprimer(MouseEvent event) throws SQLException {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");

        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
               mc=MaConnexion.getInstance().getCnx();
            String sql = "delete from demande_diet where id = ?";
            ste=mc.prepareStatement(sql);
            ste.setString(1, id.getText());
            ste.execute();
            JOptionPane.showMessageDialog(null, "Demande supprimé" );
        
            refresh();
             }
        else{

          refresh();

        }

    }

    @FXML
    private void modifier(MouseEvent event) {
    }

    @FXML
    private void pdf(MouseEvent event) throws SQLException, FileNotFoundException, DocumentException, IOException {
        
    String sql = "SELECT * from demande_diet";
    ste=mc.prepareStatement(sql);
    ResultSet rs=ste.executeQuery();

    Document doc = new Document();
    PdfWriter.getInstance(doc, new FileOutputStream("./ListDemandeDiet.pdf"));

    doc.open();
   
    doc.add(new Paragraph("   "));
    doc.add(new Paragraph(" *************************************** Liste Des Demandes *************************************** "));
    doc.add(new Paragraph("   "));

    PdfPTable table = new PdfPTable(1);
    table.setWidthPercentage(50);
    PdfPCell cell;

    cell = new PdfPCell(new Phrase("Titre des articles", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    
    table.addCell(cell);
   
    cell = new PdfPCell(new Phrase("Description des articles", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    
    table.addCell(cell);

    while (rs.next()) {

        DemandeDiet e = new DemandeDiet();
      //  e.setId(rs.getInt("id"))
        e.setEtatDemande(rs.getString("etat_demande"));
       // e.setClassement(rs.getInt("classement"));
       
      
        cell = new PdfPCell(new Phrase(e.getEtatDemande(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
      
    }

    doc.add(table);
    doc.close();
    Desktop.getDesktop().open(new File("./ListeDesArticles.pdf"));

    }
    
}
