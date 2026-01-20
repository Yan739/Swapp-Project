package com.swapp.repository;

import com.swapp.config.DataBaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurRepository {
    public boolean login(String username, String password) {
        String sql = "SELECT * FROM Utilisateur WHERE Nom_utilisateur = ? AND Mot_de_passe = ?";
        try (Connection conn = DataBaseConnector.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void save(String username, String password, String droit) {
        String sql = "INSERT INTO Utilisateur (Nom_utilisateur, Mot_de_passe, Droit) VALUES (?, ?, ?)";
        try (Connection conn = DataBaseConnector.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, droit);
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}