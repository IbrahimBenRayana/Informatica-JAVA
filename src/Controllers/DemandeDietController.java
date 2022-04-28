/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.DemandeDiet;
import Models.Diet;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tools.MaConnexion;

/**
 *
 * @author waelk
 */
public class DemandeDietController {
    
    public void ajouterDemandeDiet(DemandeDiet d) {
         try {
            String requete= "INSERT INTO demande_diet (id,user_id_id,diet_object,etat_demande)"
                    + "VALUES (?,?,?,?)";
            PreparedStatement pst = MaConnexion.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, d.getId());
            pst.setInt(2, d.getUser_id());
            pst.setString(3, d.getDiet_obj());
            pst.setString(4, d.getEtatDemande());  
            pst.executeUpdate();
            System.out.println("Diet insere");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    
    public void supprimerDemandeDiet(DemandeDiet d) {
        try {
            String requete = "DELETE FROM demande_diet where id=?";
            PreparedStatement pst = MaConnexion.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, d.getId());
            pst.executeUpdate();
            System.out.println("Diet supprimee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void updateDemandeDiet(DemandeDiet d) {
        try {
            String requete = "UPDATE demande_diet SET diet_object=?,etat_demande=? WHERE id=?";
            PreparedStatement pst = MaConnexion.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1,d.getDiet_obj());
            pst.setString(2,d.getEtatDemande());
            pst.setInt(3, d.getId());
            
            pst.executeUpdate();
            System.out.println("Diet modifie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<DemandeDiet> displayDemandeDiets() {
         List<DemandeDiet> DemandedietList = new ArrayList<>();
        try {
            String requete = "SELECT user_id_id,diet_object,etat_demande FROM demande_diet";
            Statement st = MaConnexion.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                DemandeDiet d = new DemandeDiet();
                d.setUser_id(rs.getInt("user_id_id"));
                d.setDiet_obj(rs.getString("diet_object"));
                d.setEtatDemande(rs.getString("etat_demande"));
                DemandedietList.add(d);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return DemandedietList;
    }
    
}
