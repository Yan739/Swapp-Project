package com.swapp.model;

import java.util.Date;

public class Reparation {
    private int idReparation;
    private String nomReparateur;
    private Date dateEntree;
    private Date dateSortie;
    private String statut;
    private String commentaire;
    private int idTypeReparation;
    private int idFacture;
    private int idArticle;

    public Reparation(int idReparation, String nomReparateur, Date dateEntree, Date dateSortie, String statut, String commentaire, int idTypeReparation, int idFacture, int idArticle) {
        this.idReparation = idReparation;
        this.nomReparateur = nomReparateur;
        this.dateEntree = dateEntree;
        this.dateSortie = dateSortie;
        this.statut = statut;
        this.commentaire = commentaire;
        this.idTypeReparation = idTypeReparation;
        this.idFacture = idFacture;
        this.idArticle = idArticle;
    }

    public int getIdReparation() {
        return idReparation;
    }

    public void setIdReparation(int idReparation) {
        this.idReparation = idReparation;
    }

    public String getNomReparateur() {
        return nomReparateur;
    }

    public void setNomReparateur(String nomReparateur) {
        this.nomReparateur = nomReparateur;
    }

    public Date getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(Date dateEntree) {
        this.dateEntree = dateEntree;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getIdTypeReparation() {
        return idTypeReparation;
    }

    public void setIdTypeReparation(int idTypeReparation) {
        this.idTypeReparation = idTypeReparation;
    }

    public int getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }
}
