package com.swapp.controller;

import com.swapp.repository.CommandeRepository;
import com.swapp.config.SessionManager;

public class VenteController {
    private CommandeRepository commandeRepo = new CommandeRepository();

    public String validerVente(int idClient, int idArticle, int quantite) {

        if (!SessionManager.isConnected()) {
            return "Erreur : Session expirée.";
        }

        int idCmd = commandeRepo.passerCommande(idClient, idArticle, quantite);

        if (idCmd != -1) {
            return "Vente réussie ! Numéro de commande : " + idCmd;
        } else {
            return "Erreur lors de la transaction SQL.";
        }
    }
}