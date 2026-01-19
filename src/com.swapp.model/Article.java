package com.swapp.model;

public class Article {
    private int idArticle;
    private String nom;
    private String statut;
    private String type;
    private String etat;
    private int annee;
    private String numSerie;
    private boolean garantie;
    private int quantiteDisponible;
    private double prix;

    public Article(int idArticle, String nom, String type, String statut, String etat, int annee, String numSerie, boolean garantie, int quantiteDisponible, double prix) {
        this.idArticle = idArticle;
        this.nom = nom;
        this.type = type;
        this.statut = statut;
        this.etat = etat;
        this.annee = annee;
        this.numSerie = numSerie;
        this.garantie = garantie;
        this.quantiteDisponible = quantiteDisponible;
        this.prix = prix;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public boolean isGarantie() {
        return garantie;
    }

    public void setGarantie(boolean garantie) {
        this.garantie = garantie;
    }

    public int getQuantiteDisponible() {
        return quantiteDisponible;
    }

    public void setQuantiteDisponible(int quantiteDisponible) {
        this.quantiteDisponible = quantiteDisponible;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
