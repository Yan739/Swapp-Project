package com.swapp.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.swapp.model.Article;
import com.swapp.model.Client;
import java.io.FileOutputStream;

public class PdfService {

    public void genererFacturePdf(int idFacture, Client client, Article article, int qte, double total) {
        Document document = new Document();
        try {
            String fileName = "Facture_SWAPP_" + idFacture + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(fileName));

            document.open();

            Font fontTitre = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLUE);
            Paragraph titre = new Paragraph("SWAPP - LOGICIEL DE GESTION\n\n", fontTitre);
            titre.setAlignment(Element.ALIGN_CENTER);
            document.add(titre);

            // Infos Client et Facture
            document.add(new Paragraph("Facture n° : " + idFacture));
            document.add(new Paragraph("Client : " + client.getNom() + " " + client.getPrenom()));
            document.add(new Paragraph("Adresse : " + client.getAdresse()));
            document.add(new Paragraph("Date : " + java.time.LocalDate.now()));
            document.add(new Paragraph("\n"));

            // Tableau des articles
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);

            PdfPCell headerCell;
            String[] headers = {"Article", "Prix Unitaire", "Quantité", "Total"};

            for (String columnTitle : headers) {
                headerCell = new PdfPCell(new Phrase(columnTitle, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
                headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(headerCell);
            }

            table.addCell(article.getNom());
            table.addCell(String.format("%.2f €", article.getPrix()));
            table.addCell(String.valueOf(qte));
            table.addCell(String.format("%.2f €", total));

            document.add(table);

            // Total final
            Paragraph montantFinal = new Paragraph("\nTOTAL À PAYER : " + String.format("%.2f €", total),
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
            montantFinal.setAlignment(Element.ALIGN_RIGHT);
            document.add(montantFinal);

            document.close();
            System.out.println("PDF généré : " + fileName);

        } catch (Exception e) {
            System.err.println("Erreur génération PDF : " + e.getMessage());
        }
    }
}