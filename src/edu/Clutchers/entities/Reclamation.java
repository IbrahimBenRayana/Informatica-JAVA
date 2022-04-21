/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.Clutchers.entities;

/**
 *
 * @author sinda
 */
public class Reclamation {
    private int id;
    private String objet_rec;
    private String contenu_rec;
    private String nomadh;
    private String mailadh;
    private boolean statut;

    public Reclamation() {
    }

    public Reclamation(int id, String objet_rec, String contenu_rec, String nomadh, String mailadh, boolean statut) {
        this.id = id;
        this.objet_rec = objet_rec;
        this.contenu_rec = contenu_rec;
        this.nomadh = nomadh;
        this.mailadh = mailadh;
        this.statut = statut;
    }

    public Reclamation(String objet_rec, String contenu_rec, String nomadh, String mailadh, boolean statut) {
        this.objet_rec = objet_rec;
        this.contenu_rec = contenu_rec;
        this.nomadh = nomadh;
        this.mailadh = mailadh;
        this.statut = statut;
    }

    public Reclamation(String objet_rec, String contenu_rec, String nomadh, String mailadh) {
        this.objet_rec = objet_rec;
        this.contenu_rec = contenu_rec;
        this.nomadh = nomadh;
        this.mailadh = mailadh;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObjet_rec() {
        return objet_rec;
    }

    public void setObjet_rec(String objet_rec) {
        this.objet_rec = objet_rec;
    }

    public String getContenu_rec() {
        return contenu_rec;
    }

    public void setContenu_rec(String contenu_rec) {
        this.contenu_rec = contenu_rec;
    }

    public String getNomadh() {
        return nomadh;
    }

    public void setNomadh(String nomadh) {
        this.nomadh = nomadh;
    }

    public String getMailadh() {
        return mailadh;
    }

    public void setMailadh(String mailadh) {
        this.mailadh = mailadh;
    }

    public boolean getStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", objet_rec=" + objet_rec + ", contenu_rec=" + contenu_rec + ", nomadh=" + nomadh + ", mailadh=" + mailadh + ", statut=" + statut + '}';
    }
    
    

    
}