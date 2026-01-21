package com.swapp.view;

import java.util.Scanner;

public class AchatMatFournisseurView {

    public void afficherMenu(Scanner scanner) {
        boolean retour = false;

        while (!retour) {
            System.out.println("\n--- ACHATS MATÉRIEL FOURNISSEURS ---");
            System.out.println("1. Enregistrer un nouvel achat");
            System.out.println("2. Liste des fournisseurs");
            System.out.println("3. Historique des factures");
            System.out.println("0. Retour au menu principal");
            System.out.print("Choix : ");

            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    enregistrerNouvelAchat(scanner);
                    break;
                case "2":
                    listerFournisseurs();
                    break;
                case "3":
                    afficherHistoriqueFactures();
                    break;
                case "0":
                    retour = true;
                    break;
                default:
                    System.out.println("[!] Choix invalide.");
            }
        }
    }

    private void enregistrerNouvelAchat(Scanner scanner) {
        System.out.println("\n--- NOUVEL ACHAT ---");
        System.out.print("ID du Fournisseur : ");
        String fournisseurId = scanner.nextLine();

        System.out.print("Désignation du matériel : ");
        String nomMatos = scanner.nextLine();

        System.out.print("Prix d'achat unitaire : ");
        double prix = Double.parseDouble(scanner.nextLine());

        System.out.print("Quantité : ");
        int qte = Integer.parseInt(scanner.nextLine());

        System.out.println("\n[INFO] Achat de " + qte + "x " + nomMatos + " enregistré (Simulé).");
    }

    private void listerFournisseurs() {
        System.out.println("\n--- LISTE DES FOURNISSEURS ---");
        System.out.println("1. TechDistri SA");
        System.out.println("2. GlobalParts Corp");
    }

    private void afficherHistoriqueFactures() {
        System.out.println("\n--- HISTORIQUE DES FACTURES D'ACHAT ---");
        System.out.println("Aucune facture récente.");
    }
}