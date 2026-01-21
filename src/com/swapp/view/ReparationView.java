package com.swapp.view;

import com.swapp.repository.ReparationRepository;
import java.util.Scanner;
import java.util.List;

public class ReparationView {
    private ReparationRepository reparationRepo = new ReparationRepository();

    public void afficherMenu(Scanner scanner) {
        boolean retour = false;

        while (!retour) {
            System.out.println("\n--- SERVICE RÉPARATIONS ---");
            System.out.println("1. Créer un bon de prise en charge");
            System.out.println("2. Modifier le statut d'une réparation");
            System.out.println("3. Liste des appareils en cours/attente");
            System.out.println("0. Retour");
            System.out.print("Choix : ");

            String choix = scanner.nextLine();

            switch (choix) {
                case "1" -> creerBonPriseEnCharge(scanner);
                case "2" -> modifierStatut(scanner);
                case "3" -> listerReparations();
                case "0" -> retour = true;
                default -> System.out.println("[!] Choix invalide.");
            }
        }
    }

    private void creerBonPriseEnCharge(Scanner scanner) {
        System.out.println("\n--- NOUVEAU BON DE RÉPARATION ---");

        System.out.print("Numéro de suivi (ex: REP-101) : ");
        String numSuivi = scanner.nextLine();

        System.out.print("Nom du technicien : ");
        String reparateur = scanner.nextLine();

        System.out.print("ID de l'article (Appareil) : ");
        int idArticle = Integer.parseInt(scanner.nextLine());

        System.out.print("ID du type de réparation (1:Ecran, 2:Batterie...) : ");
        int idType = Integer.parseInt(scanner.nextLine());

        boolean succes = reparationRepo.save(numSuivi, reparateur, idArticle, idType);

        if (succes) {
            System.out.println("\nBon créé avec succès ! Statut par défaut : 'En attente'");
        } else {
            System.out.println("\nErreur lors de la création du bon (Vérifiez les IDs).");
        }
    }

    private void modifierStatut(Scanner scanner) {
        System.out.print("\nID de la réparation (Id_Reparation) : ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.println("Sélectionnez le nouveau statut :");
        System.out.println("1. En cours");
        System.out.println("2. Terminée");
        System.out.println("3. Livrée");
        System.out.print("Choix : ");

        String choix = scanner.nextLine();
        String statut = switch (choix) {
            case "1" -> "En cours";
            case "2" -> "Terminée";
            case "3" -> "Livrée";
            default -> null;
        };

        if (statut != null) {
            boolean succes = reparationRepo.updateStatut(id, statut);
            if (succes) {
                System.out.println("Statut mis à jour : " + statut);
            } else {
                System.out.println("Erreur : ID introuvable.");
            }
        } else {
            System.out.println("[!] Choix de statut invalide.");
        }
    }

    private void listerReparations() {
        System.out.println("\n--- SUIVI DES RÉPARATIONS ACTIVES ---");

        List<String[]> reparations = reparationRepo.findAllEnCours();

        if (reparations.isEmpty()) {
            System.out.println("Aucune réparation active trouvée.");
        } else {
            System.out.printf("%-5s | %-15s | %-15s | %-12s%n", "ID", "NUM SUIVI", "TECHNICIEN", "STATUT");
            System.out.println("------------------------------------------------------------");
            for (String[] rep : reparations) {
                System.out.printf("%-5s | %-15s | %-15s | %-12s%n", rep[0], rep[1], rep[2], rep[3]);
            }
        }
    }
}