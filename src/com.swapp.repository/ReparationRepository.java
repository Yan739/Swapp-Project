package com.swapp.repository;

import com.swapp.config.DataBaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReparationRepository {
    public void save(String numSuivi, String reparateur, int idArticle, int idTypeReparation) {
        String sql = "INSERT INTO Reparation (num_suivi, nom_reparateur, date_entree, statut, Id_Article, Id_Type_reparation) " +
                "VALUES (?, ?, CURDATE(), 'En attente', ?, ?)";
        try (Connection conn = DataBaseConnector.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, numSuivi);
            pstmt.setString(2, reparateur);
            pstmt.setInt(3, idArticle);
            pstmt.setInt(4, idTypeReparation);
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void updateStatut(int idReparation, String nouveauStatut) {
        String sql = "UPDATE Reparation SET statut = ?, date_sortie = (IF(? = 'Terminée', CURDATE(), NULL)) WHERE Id_Reparation = ?";
        try (Connection conn = DataBaseConnector.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nouveauStatut);
            pstmt.setString(2, nouveauStatut);
            pstmt.setInt(3, idReparation);
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}