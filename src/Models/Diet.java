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
public class Diet {
    int id;
    String title;
    String content;
    String begin_date,end_date;
    String obj;
    int nut_id,adh_id;

    public Diet() {
    }

    public Diet(int id, String title, String content, String begin_date, String end_date, String obj, int nut_id, int adh_id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.obj = obj;
        this.nut_id = nut_id;
        this.adh_id = adh_id;
    }

    public Diet(String title, String content, String begin_date, String end_date, String obj, int nut_id, int adh_id) {
        this.title = title;
        this.content = content;
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.obj = obj;
        this.nut_id = nut_id;
        this.adh_id = adh_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(String begin_date) {
        this.begin_date = begin_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public int getNut_id() {
        return nut_id;
    }

    public void setNut_id(int nut_id) {
        this.nut_id = nut_id;
    }

    public int getAdh_id() {
        return adh_id;
    }

    public void setAdh_id(int adh_id) {
        this.adh_id = adh_id;
    }
    
    
    
    
}
