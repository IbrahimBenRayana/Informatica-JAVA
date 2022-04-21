/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Clutchers.services;

import edu.Clutchers.entities.Rating;
import edu.Clutchers.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sinda
 */
public class RatingCRUD {
    
     Connection cnx2;

        public RatingCRUD()
           {cnx2 = MyConnection.getInstance().getCnx();}
        
         public void ajouterR(Rating C){
        try {
            String requete = "INSERT INTO rating (rate_index)"+ "VALUES (?)";
            PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setString(1, C.getRate_index());
            
            
            pst.executeUpdate();
            System.out.println("La rating a ete ajoute avec succes");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
         
             public List<Rating> afficherR(){
        List<Rating> myList = new ArrayList<>();
        try {
            
            String requete2 = "SELECT * FROM rating";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete2);
            while(rs.next()){
                Rating c = new Rating();
                c.setId(rs.getInt(1));
                c.setRate_index(rs.getString("rate_index"));
                myList.add(c);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
             
              public void modifierR(Rating c) {
        try {
            String req= "update rating set rate_index= ? where id=?";
            PreparedStatement cc = cnx2.prepareStatement(req);
            cc.setString(1, c.getRate_index());
            cc.setInt(2, c.getId());
            cc.executeUpdate();
            System.out.println("\n Reponse mondifier contenu .");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
         
              
               public void supprimerR(int id) {
        try {
            String req = "delete from rating where id = ?";
            PreparedStatement ps = cnx2.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       
    
        
        
        
    
}
