package com.swapp.model;

import java.util.Date;

public class Etat {
    private int idEtat;
    private String statut;
    private Date dateChangement;
    private int IdArticle;

    public Etat(int idEtat, String statut, Date dateChangement, int idArticle) {
        this.idEtat = idEtat;
        this.statut = statut;
        this.dateChangement = dateChangement;
        IdArticle = idArticle;
    }

    public int getIdEtat() {
        return idEtat;
    }

    public void setIdEtat(int idEtat) {
        this.idEtat = idEtat;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Date getDateChangement() {
        return dateChangement;
    }

    public void setDateChangement(Date dateChangement) {
        this.dateChangement = dateChangement;
    }

    public int getIdArticle() {
        return IdArticle;
    }

    public void setIdArticle(int idArticle) {
        IdArticle = idArticle;
    }
}
