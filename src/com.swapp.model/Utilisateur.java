package com.swapp.model;

public class Utilisateur extends Employe{
    private String motDePasse;
    private String droit;

    public Utilisateur(int id, String nom, String prenom, String adresse, int contact, String poste, String droit, String motDePasse) {
        super(id, nom, prenom, adresse, contact, poste);
        this.droit = droit;
        this.motDePasse = motDePasse;
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
