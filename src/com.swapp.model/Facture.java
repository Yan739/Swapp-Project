package com.swapp.model;

import java.util.Date;

public class Facture {
    private int idFacture;
    private Date dateFacturation;
    private double montant;
    private boolean garantie;
    private int idPaiement;

    public Facture(int idFacture, Date dateFacturation, double montant, boolean garantie, int idPaiement) {
        this.idFacture = idFacture;
        this.dateFacturation = dateFacturation;
        this.montant = montant;
        this.garantie = garantie;
        this.idPaiement = idPaiement;
    }

    public int getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public Date getDateFacturation() {
        return dateFacturation;
    }

    public void setDateFacturation(Date dateFacturation) {
        this.dateFacturation = dateFacturation;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public boolean isGarantie() {
        return garantie;
    }

    public void setGarantie(boolean garantie) {
        this.garantie = garantie;
    }

    public int getIdPaiement() {
        return idPaiement;
    }

    public void setIdPaiement(int idPaiement) {
        this.idPaiement = idPaiement;
    }
}
