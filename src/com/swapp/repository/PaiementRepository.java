package com.swapp.repository;

import com.swapp.config.DataBaseConnector;
import java.sql.*;

public class PaiementRepository {

    public int enregistrerPaiement(double montant, String modePaiement) {
        String sql = "INSERT INTO Paiement (montant, mode_paiement, statut) VALUES (?, ?, 'Payé')";
        try (Connection conn = DataBaseConnector.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setDouble(1, montant);
            pstmt.setString(2, modePaiement);
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}