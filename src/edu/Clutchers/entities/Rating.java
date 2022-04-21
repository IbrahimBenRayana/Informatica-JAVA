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
public class Rating {
    private int id;
    private String rate_index;
    

    public Rating() {
        
    }

    public Rating(int id, String rate_index) {
        this.id = id;
        this.rate_index = rate_index;
    }

    public Rating(String rate_index) {
        this.rate_index = rate_index;
    }
    

    public String getRate_index() {
        return rate_index;
    }

    public void setRate_index(String rate_index) {
        this.rate_index = rate_index;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Rating{" + "id=" + id + ", rate_index=" + rate_index + '}';
    }

   

    
    
    
    
}
