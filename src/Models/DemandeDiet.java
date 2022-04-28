/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author waelk
 */
public class DemandeDiet {
    int id;
    int user_id;
    String diet_obj;
    String EtatDemande;

    @Override
    public String toString() {
        return "DemandeDiet{" +  ", user_id=" + user_id + ", diet_obj=" + diet_obj + ", EtatDemande=" + EtatDemande + '}';
    }

    public DemandeDiet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDiet_obj() {
        return diet_obj;
    }

    public void setDiet_obj(String diet_obj) {
        this.diet_obj = diet_obj;
    }

    public String getEtatDemande() {
        return EtatDemande;
    }

    public void setEtatDemande(String EtatDemande) {
        this.EtatDemande = EtatDemande;
    }

    public DemandeDiet(int user_id, String diet_obj, String EtatDemande) {
        this.user_id = user_id;
        this.diet_obj = diet_obj;
        this.EtatDemande = EtatDemande;
    }

    public DemandeDiet(int id, int user_id, String diet_obj, String EtatDemande) {
        this.id = id;
        this.user_id = user_id;
        this.diet_obj = diet_obj;
        this.EtatDemande = EtatDemande;
    }
}

