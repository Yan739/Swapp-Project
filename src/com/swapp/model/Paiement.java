package com.swapp.model;

import java.util.Date;

public class Paiement {
    private int idPaiement;
    private Date datePaiement;
    private String modePaiement;
    private String statut;

    public Paiement(int idPaiement, Date datePaiement, String modePaiement, String statut) {
        this.idPaiement = idPaiement;
        this.datePaiement = datePaiement;
        this.modePaiement = modePaiement;
        this.statut = statut;
    }

    public int getIdPaiement() {
        return idPaiement;
    }

    public void setIdPaiement(int idPaiement) {
        this.idPaiement = idPaiement;
    }

    public Date getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(Date datePaiement) {
        this.datePaiement = datePaiement;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}

