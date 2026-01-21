package com.swapp.repository;

import com.swapp.config.DataBaseConnector;
import java.sql.*;


public class CommandeRepository {

    public int passerCommande(int idClient, int idArticle, int quantite) {
        String sql = "{call PasserCommande(?, ?, ?, ?)}";
        try (Connection conn = DataBaseConnector.getInstance();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, idClient);
            cstmt.setInt(2, idArticle);
            cstmt.setInt(3, quantite);
            cstmt.registerOutParameter(4, Types.INTEGER); // Récupère le p_Id_Commande OUT

            cstmt.execute();
            int idGeneré = cstmt.getInt(4);
            System.out.println("Commande #" + idGeneré + " créée avec succès.");
            return idGeneré;

        } catch (SQLException e) {
            System.err.println("Erreur Procédure PasserCommande: " + e.getMessage());
            return -1;
        }
    }

    public int finaliserPaiement(int idCommande, double montant, String modePaiement) {
        String sql = "{call FacturerEtPayer(?, ?, ?, ?, ?)}";
        int idFacture = -1;

        try (Connection conn = DataBaseConnector.getInstance();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, idCommande);
            cstmt.setDouble(2, montant);
            cstmt.setString(3, modePaiement);

            cstmt.registerOutParameter(4, Types.INTEGER);
            cstmt.registerOutParameter(5, Types.INTEGER);

            cstmt.execute();

            idFacture = cstmt.getInt(4);
            System.out.println("Paiement validé. Facture n°" + idFacture + " générée.");

        } catch (SQLException e) {
            System.err.println("Erreur Procédure FacturerEtPayer : " + e.getMessage());
        }

        return idFacture;
    }

    public void afficherToutesLesCommandes() {
        String sql = "SELECT c.Id_Commande, cl.nom, a.nom as article, c.Quantite_Demandee, c.statut " +
                "FROM Commande c " +
                "JOIN Client cl ON c.Id_Client = cl.Id_Client " +
                "JOIN Ligne_commande lc ON c.Id_Commande = lc.Id_Commande " +
                "JOIN Article a ON lc.Id_Article = a.Id_Article";

        try (Connection conn = DataBaseConnector.getInstance();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.printf("Commande #%d | Client: %s | Article: %s | Qté: %d | Statut: %s%n",
                        rs.getInt("Id_Commande"),
                        rs.getString("nom"),
                        rs.getString("article"),
                        rs.getInt("Quantite_Demandee"),
                        rs.getString("statut")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int idCommande) {
        String sql = "DELETE FROM Commande WHERE Id_Commande = ?";
        try (Connection conn = DataBaseConnector.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCommande);
            pstmt.executeUpdate();
            System.out.println("Commande supprimée.");
        } catch (SQLException e) {
            System.err.println("Erreur Delete Commande: " + e.getMessage());
        }
    }
}