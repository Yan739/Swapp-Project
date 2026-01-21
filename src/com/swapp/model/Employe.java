package com.swapp.model;

public class Employe extends Personne{
    private String poste;
    private int idUtilisateur;

    public Employe(int id, String nom, String prenom, String adresse, int contact, String poste) {
        super(id, nom, prenom, adresse, contact);
        this.poste = poste;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }
}
