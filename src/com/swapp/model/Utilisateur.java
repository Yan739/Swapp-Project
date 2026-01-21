package com.swapp.model;

public class Utilisateur {
    private int id;
    private String nomUtilisateur;
    private String motDePasse;
    private String droit;

    public Utilisateur(String nomUtilisateur, String motDePasse, String droit) {
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasse = motDePasse;
        this.droit = droit;
    }


    public Utilisateur(int id, String nomUtilisateur, String motDePasse, String droit) {
        this.id = id;
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasse = motDePasse;
        this.droit = droit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getDroit() {
        return droit;
    }

    public void setDroit(String droit) {
        this.droit = droit;
    }
}