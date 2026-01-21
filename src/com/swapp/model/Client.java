package com.swapp.model;

public class Client extends Personne{
    private boolean bonAchat;
    private double montant;

    public Client(int id, String nom, String prenom, String adresse, int contact, boolean bonAchat, double montant) {
        super(id, nom, prenom, adresse, contact);
        this.bonAchat = bonAchat;
        this.montant = montant;
    }

    public boolean isBonAchat() {
        return bonAchat;
    }

    public void setBonAchat(boolean bonAchat) {
        this.bonAchat = bonAchat;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
}
