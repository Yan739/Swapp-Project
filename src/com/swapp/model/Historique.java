package com.swapp.model;

import java.util.Date;

public class Historique {
    private int idHistorique;
    private String action;
    private Date dateAction;

    public Historique(int idHistorique, String action, Date dateAction) {
        this.idHistorique = idHistorique;
        this.action = action;
        this.dateAction = dateAction;
    }

    public int getIdHistorique() {
        return idHistorique;
    }

    public void setIdHistorique(int idHistorique) {
        this.idHistorique = idHistorique;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getDateAction() {
        return dateAction;
    }

    public void setDateAction(Date dateAction) {
        this.dateAction = dateAction;
    }
}
