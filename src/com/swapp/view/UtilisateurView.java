package com.swapp.view;

import com.swapp.repository.UtilisateurRepository;
import com.swapp.model.Utilisateur;
import org.mindrot.jbcrypt.BCrypt;
import java.util.List;
import java.util.Scanner;

public class UtilisateurView {
    private UtilisateurRepository userRepo = new UtilisateurRepository();

    public void afficherGestion(Scanner scanner) {
        boolean retour = false;

        while (!retour) {
            System.out.println("\n--- GESTION DES UTILISATEURS ---");
            System.out.println("1. Lister les employés");
            System.out.println("2. Créer un nouvel utilisateur");
            System.out.println("3. Modifier les droits");
            System.out.println("0. Retour");
            System.out.print("Choix : ");

            String choix = scanner.nextLine();

            switch (choix) {
                case "1" -> listerEmployes();
                case "2" -> creerNouvelUtilisateur(scanner);
                case "3" -> modifierDroits(scanner);
                case "0" -> retour = true;
                default -> System.out.println("[!] Choix invalide.");
            }
        }
    }

    private void listerEmployes() {
        System.out.println("\n--- LISTE DES EMPLOYÉS ---");
        List<Utilisateur> utilisateurs = userRepo.findAll();

        if (utilisateurs.isEmpty()) {
            System.out.println("Aucun utilisateur enregistré.");
        } else {
            System.out.printf("%-5s | %-20s | %-10s%n", "ID", "NOM UTILISATEUR", "DROIT");
            System.out.println("------------------------------------------");
            for (Utilisateur u : utilisateurs) {
                System.out.printf("%-5d | %-20s | %-10s%n", u.getId(), u.getNomUtilisateur(), u.getDroit());
            }
        }
    }

    private void creerNouvelUtilisateur(Scanner scanner) {
        System.out.println("\n--- CRÉATION D'UN COMPTE ---");
        System.out.print("Nom d'utilisateur : ");
        String nom = scanner.nextLine();

        System.out.print("Mot de passe : ");
        String pass = scanner.nextLine();

        System.out.print("Droit (ADMIN / EMPLOYE) : ");
        String droit = scanner.nextLine().toUpperCase();

        String hashedPass = BCrypt.hashpw(pass, BCrypt.gensalt());

        Utilisateur nouveau = new Utilisateur(nom, hashedPass, droit);
        boolean succes = userRepo.save(nouveau);

        if (succes) {
            System.out.println("\n Utilisateur '" + nom + "' créé avec succès.");
        } else {
            System.out.println("\nErreur lors de l'enregistrement.");
        }
    }

    private void modifierDroits(Scanner scanner) {
        System.out.print("\nEntrez l'ID de l'utilisateur à modifier : ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Nouveau droit (ADMIN / EMPLOYE) : ");
        String nouveauDroit = scanner.nextLine().toUpperCase();

        boolean succes = userRepo.updateDroit(id, nouveauDroit);

        if (succes) {
            System.out.println("\nDroits mis à jour.");
        } else {
            System.out.println("\nUtilisateur introuvable ou erreur SQL.");
        }
    }
}