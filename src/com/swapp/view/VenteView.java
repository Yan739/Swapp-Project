package com.swapp.view;

import com.swapp.model.*;
import com.swapp.repository.*;
import com.swapp.service.PdfService;
import java.util.Scanner;

public class VenteView {
    private ArticleRepository articleRepo = new ArticleRepository();
    private ClientRepository clientRepo = new ClientRepository();
    private CommandeRepository commandeRepo = new CommandeRepository();
    private PdfService pdf = new PdfService();

    public void nouvelleVente(Scanner scanner) {
        System.out.println("\n--- SYSTÈME DE VENTE ---");

        System.out.print("ID Article : ");
        int idArt = Integer.parseInt(scanner.nextLine());
        Article art = articleRepo.findById(idArt);
        if (art == null) { System.out.println("Article inconnu."); return; }

        System.out.print("ID Client (ou 1 pour Client Passager) : ");
        int idCli = Integer.parseInt(scanner.nextLine());
        Client cli = clientRepo.findById(idCli);
        if (cli == null) { System.out.println("Client inconnu."); return; }

        System.out.print("Quantité : ");
        int qte = Integer.parseInt(scanner.nextLine());
        if (qte > art.getQuantiteDisponible()) { System.out.println("Stock insuffisant !"); return; }

        double total = art.getPrix() * qte;
        System.out.println("Total : " + total + " €");
        System.out.print("Confirmer la commande ? (1: Oui / 2: Non) : ");

        if (scanner.nextLine().equals("1")) {
            traiterVenteComplete(cli, art, qte, total, scanner);
        }
    }

    private void traiterVenteComplete(Client cli, Article art, int qte, double total, Scanner scanner) {
        int idCommande = commandeRepo.passerCommande(cli.getId(), art.getIdArticle(), qte);

        if (idCommande != -1) {
            System.out.print("Mode de paiement (Espèces/Carte) : ");
            String mode = scanner.nextLine();

            commandeRepo.finaliserPaiement(idCommande, total, mode);

            pdf.genererFacturePdf(idCommande, cli, art, qte, total);

            System.out.println("\nVente terminée avec succès !");
        }
    }
}