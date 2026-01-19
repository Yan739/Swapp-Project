package com.swapp.model;

public class Fournisseur extends Personne{
    private String pays;
    private String Domaine;

    public Fournisseur(int id, String nom, String prenom, String adresse, int contact, String pays, String domaine) {
        super(id, nom, prenom, adresse, contact);
        this.pays = pays;
        Domaine = domaine;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getDomaine() {
        return Domaine;
    }

    public void setDomaine(String domaine) {
        Domaine = domaine;
    }
}
