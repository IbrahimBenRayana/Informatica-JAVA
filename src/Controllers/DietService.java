/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
public class DietService {
    
    public void ajouterDiet(Diet d) {
         try {
            String requete= "INSERT INTO diet (diet_id,nut_id_id,adh_id_id,diet_title,diet_content,diet_begin_date,diet_end_date,diet_obj)"
                    + "VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MaConnexion.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, d.getId());
            pst.setInt(2, d.getNut_id());
            pst.setInt(3, d.getAdh_id());
            pst.setString(4, d.getTitle());
            pst.setString(5,d.getContent());
            pst.setString(6,d.getBegin_date());
            pst.setString(7,d.getEnd_date());
            pst.setString(8,d.getObj());
            pst.executeUpdate();
            System.out.println("Diet inserÃ©e");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    
    public void supprimerDiet(Diet d) {
        try {
            String requete = "DELETE FROM diet where diet_id=?";
            PreparedStatement pst = MaConnexion.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, d.getId());
            pst.executeUpdate();
            System.out.println("Diet supprimÃ©e");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void updateDiet(Diet d) {
        try {
            String requete = "UPDATE diet SET nut_id_id=?,adh_id_id=?,diet_title=?,diet_content=?,diet_begin_date=?,diet_end_date=?,diet_obj=?  WHERE diet_id=?";
            PreparedStatement pst = MaConnexion.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, d.getNut_id());
            pst.setInt(2, d.getAdh_id());
            pst.setString(3, d.getTitle());
            pst.setString(4,d.getContent());
            pst.setString(5,d.getBegin_date());
            pst.setString(6,d.getEnd_date());
            pst.setString(7,d.getObj());
            pst.setInt(8, d.getId());
            
            pst.executeUpdate();
            System.out.println("Diet modifiÃ©e");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Diet> displayDiets() {
         List<Diet> dietList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM diet";
            Statement st = MaConnexion.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Diet d = new Diet();
                d.setId(rs.getInt("diet_id"));
                d.setNut_id(rs.getInt("nut_id_id"));
                d.setAdh_id(rs.getInt("adh_id_id"));
                d.setTitle(rs.getString("diet_title"));
                d.setContent(rs.getString("diet_content"));
                d.setBegin_date(rs.getString("diet_begin_date"));
                d.setEnd_date(rs.getString("diet_end_date"));
                d.setObj(rs.getString("diet_obj"));
                dietList.add(d);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return dietList;
    }
    
    
}
