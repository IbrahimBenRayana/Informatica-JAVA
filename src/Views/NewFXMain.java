/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author mdhah
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        
       //  new dataProviderController().getData("France");
        
        Parent parent = FXMLLoader.load(getClass().getResource("GestionDiet.fxml"));
         Scene scene = new Scene(parent);

        
////////        
//       Parent parent = FXMLLoader.load(getClass().getResource("gestionEquipeInterface.fxml"));
//       Scene scene = new Scene(parent);

 
//       Parent parent = FXMLLoader.load(getClass().getResource("gestionJoueurInterface.fxml"));
//         Scene scene = new Scene(parent);
////
////
//         
//         
//           Parent parent = FXMLLoader.load(getClass().getResource("matchInterface.fxml"));
//         Scene scene = new Scene(parent);
//

      // scene.getStylesheets().add(getClass().getResource("ControlPanel.css").toExternalForm());
      // primaryStage.initStyle(StageStyle.UTILITY);
//        primaryStage.setTitle("Gestion des équipes");


      //  primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
          primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
