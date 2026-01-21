package com.swapp.model;

public class AchatMatFournisseur {
    private int IdAchatMaterielle;
    private String numSuivi;
    private int numComFournisseur;
    private int montant;
    private double caisse;

    public AchatMatFournisseur(double caisse, int montant, int numComFournisseur, String numSuivi, int idAchatMaterielle) {
        this.caisse = caisse;
        this.montant = montant;
        this.numComFournisseur = numComFournisseur;
        this.numSuivi = numSuivi;
        IdAchatMaterielle = idAchatMaterielle;
    }

    public int getIdAchatMaterielle() {
        return IdAchatMaterielle;
    }

    public void setIdAchatMaterielle(int idAchatMaterielle) {
        IdAchatMaterielle = idAchatMaterielle;
    }

    public String getNumSuivi() {
        return numSuivi;
    }

    public void setNumSuivi(String numSuivi) {
        this.numSuivi = numSuivi;
    }

    public int getNumComFournisseur() {
        return numComFournisseur;
    }

    public void setNumComFournisseur(int numComFournisseur) {
        this.numComFournisseur = numComFournisseur;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public double getCaisse() {
        return caisse;
    }

    public void setCaisse(double caisse) {
        this.caisse = caisse;
    }
}
