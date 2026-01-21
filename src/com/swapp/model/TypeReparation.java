package com.swapp.model;

public class TypeReparation {
    private String idTypeReparation;
    private boolean vente;
    private boolean clientExt;
    private boolean garanti;
    private String Domaine;

    public TypeReparation(String idTypeReparation, boolean vente, boolean clientExt, boolean garanti, String domaine) {
        this.idTypeReparation = idTypeReparation;
        this.vente = vente;
        this.clientExt = clientExt;
        this.garanti = garanti;
        Domaine = domaine;
    }

    public String getIdTypeReparation() {
        return idTypeReparation;
    }

    public void setIdTypeReparation(String idTypeReparation) {
        this.idTypeReparation = idTypeReparation;
    }

    public boolean isVente() {
        return vente;
    }

    public void setVente(boolean vente) {
        this.vente = vente;
    }

    public boolean isClientExt() {
        return clientExt;
    }

    public void setClientExt(boolean clientExt) {
        this.clientExt = clientExt;
    }

    public boolean isGaranti() {
        return garanti;
    }

    public void setGaranti(boolean garanti) {
        this.garanti = garanti;
    }

    public String getDomaine() {
        return Domaine;
    }

    public void setDomaine(String domaine) {
        Domaine = domaine;
    }
}
