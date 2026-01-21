package com.swapp.repository;

import com.swapp.config.DataBaseConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReparationRepository {

    public boolean save(String numSuivi, String reparateur, int idArticle, int idTypeReparation) {
        String sql = "INSERT INTO Reparation (num_suivi, nom_reparateur, date_entree, statut, Id_Article, Id_Type_reparation) " +
                "VALUES (?, ?, CURDATE(), 'En attente', ?, ?)";
        try (Connection conn = DataBaseConnector.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, numSuivi);
            pstmt.setString(2, reparateur);
            pstmt.setInt(3, idArticle);
            pstmt.setInt(4, idTypeReparation);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStatut(int idReparation, String nouveauStatut) {
        String sql = "UPDATE Reparation SET statut = ?, date_sortie = CASE WHEN ? = 'Terminée' THEN CURDATE() ELSE date_sortie END WHERE Id_Reparation = ?";
        try (Connection conn = DataBaseConnector.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nouveauStatut);
            pstmt.setString(2, nouveauStatut);
            pstmt.setInt(3, idReparation);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String[]> findAllEnCours() {
        List<String[]> liste = new ArrayList<>();
        String sql = "SELECT Id_Reparation, num_suivi, nom_reparateur, statut FROM Reparation WHERE statut != 'Livrée'";
        try (Connection conn = DataBaseConnector.getInstance();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                liste.add(new String[]{
                        String.valueOf(rs.getInt("Id_Reparation")),
                        rs.getString("num_suivi"),
                        rs.getString("nom_reparateur"),
                        rs.getString("statut")
                });
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return liste;
    }
}