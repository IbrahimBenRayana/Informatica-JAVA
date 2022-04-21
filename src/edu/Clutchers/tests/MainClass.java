/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Clutchers.tests;


import edu.Clutchers.entities.Rating;
import edu.Clutchers.entities.Reclamation;
import edu.Clutchers.services.RatingCRUD;
import edu.Clutchers.services.ReclamationCRUD;
import edu.Clutchers.utils.MyConnection;

/**
 *
 * @author sinda
 */
public class MainClass {
    public static void main(String[] args){
      MyConnection mc = MyConnection.getInstance();
      MyConnection mc2 = MyConnection.getInstance();
      System.out.println(mc.hashCode()+"-"+mc2.hashCode() );
    
   /*  ReclamationCRUD pcd = new ReclamationCRUD();
      Reclamation r2 = new Reclamation("b","c","sinda","e");
     pcd.ajouterRec2(r2);
     System.out.println(pcd.afficherRec());
     pcd.modifier(r2);
    pcd.supprimer(1);*/
      
   
     Rating C = new Rating("nour");
      RatingCRUD cc = new RatingCRUD();
   //cc.ajouterR(C);
   // System.out.println(cc.afficherR());
   //cc.modifierR(C);
     //cc.supprimerR(2);
    // System.out.println(cc.afficherCategories());*/
    
     
     
     
    }
     

}
    

