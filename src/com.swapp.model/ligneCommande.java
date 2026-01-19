package com.swapp.model;

public class ligneCommande {
    private int idLigneCommande;
    private int quantiteCommandee;

    public ligneCommande(int idLigneCommande, int quantiteCommandee) {
        this.idLigneCommande = idLigneCommande;
        this.quantiteCommandee = quantiteCommandee;
    }

    public int getIdLigneCommande() {
        return idLigneCommande;
    }

    public void setIdLigneCommande(int idLigneCommande) {
        this.idLigneCommande = idLigneCommande;
    }

    public int getQuantiteCommandee() {
        return quantiteCommandee;
    }

    public void setQuantiteCommandee(int quantiteCommandee) {
        this.quantiteCommandee = quantiteCommandee;
    }
}
