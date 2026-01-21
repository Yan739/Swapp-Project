package com.swapp.view;

import com.swapp.model.*;
import com.swapp.repository.*;
import com.swapp.service.PdfService;
import java.util.Scanner;

public class VenteView {
    private ArticleRepository articleRepo = new ArticleRepository();
    private ClientRepository clientRepo = new ClientRepository();
    private CommandeRepository commandeRepo = new CommandeRepository();
    private FactureRepository factureRepo = new FactureRepository();
    private PdfService pdf = new PdfService();

    public void nouvelleVente(Scanner scanner) {
        System.out.println("\n--- SYSTÈME DE VENTE ---");

        try {
            System.out.print("ID Article : ");
            int idArt = Integer.parseInt(scanner.nextLine());
            Article art = articleRepo.findById(idArt);
            if (art == null) { System.out.println("Article inconnu."); return; }

            System.out.print("ID Client : ");
            int idCli = Integer.parseInt(scanner.nextLine());
            Client cli = clientRepo.findById(idCli);
            if (cli == null) { System.out.println("Client inconnu."); return; }

            System.out.print("Quantité : ");
            int qte = Integer.parseInt(scanner.nextLine());
            if (qte > art.getQuantiteDisponible()) { System.out.println("Stock insuffisant !"); return; }

            double total = art.getPrix() * qte;
            System.out.println("Total : " + String.format("%.2f", total) + " €");
            System.out.print("Confirmer la commande ? (1: Oui / 2: Non) : ");

            if (scanner.nextLine().equals("1")) {
                traiterVenteComplete(cli, art, qte, total, scanner);
            }
        } catch (NumberFormatException e) {
            System.out.println("[!] Erreur de saisie numérique.");
        }
    }

    private void traiterVenteComplete(Client cli, Article art, int qte, double total, Scanner scanner) {
        String modePaiement = selectionnerModePaiement(scanner);

        int idCommande = commandeRepo.passerCommande(cli.getId(), art.getIdArticle(), qte);

        if (idCommande != -1) {
            factureRepo.genererFactureFinale(idCommande, total, modePaiement);

            pdf.genererFacturePdf(idCommande, cli, art, qte, total);

            System.out.println("\nVente enregistrée, payée par " + modePaiement + " et PDF généré.");
        } else {
            System.out.println("Échec de la création de la commande.");
        }
    }

    private String selectionnerModePaiement(Scanner scanner) {
        while (true) {
            System.out.println("\nMode de paiement :");
            System.out.println("1. Espèces");
            System.out.println("2. Carte Bancaire");
            System.out.print("Choix : ");
            String choix = scanner.nextLine();

            if (choix.equals("1")) return "Espèces";
            if (choix.equals("2")) return "Carte";
            System.out.println("[!] Veuillez choisir 1 ou 2.");
        }
    }
}