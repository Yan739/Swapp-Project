package com.swapp.view;

import com.swapp.service.StatistiqueService;
import java.util.Scanner;

public class DashboardView {
    private StatistiqueService statService = new StatistiqueService();

    public void afficher(Scanner scanner) {
        System.out.println("\n********** TABLEAU DE BORD **********");
        System.out.println("Ventes du jour : " + statService.getChiffreAffaireJour() + " €");
        System.out.println("Alertes stock : " + statService.getNbAlertesStock());
        System.out.println("*************************************");
        System.out.println("1. Gérer les Achats Fournisseurs");
        System.out.println("2. Effectuer une Vente");
        System.out.println("3. Gérer les Réparations");
        System.out.println("4. Gestion Utilisateurs");
        System.out.println("0. Quitter");
        System.out.print("Choix : ");
    }
}