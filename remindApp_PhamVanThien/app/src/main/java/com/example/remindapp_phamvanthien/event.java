package com.example.remindapp_phamvanthien;

import java.io.Serializable;

public class event implements Serializable {
    String title;
    String descrition;
    String date;

    public event(String title, String descrition, String date) {
        this.title = title;
        this.descrition = descrition;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
