package com.swapp.repository;

import com.swapp.config.DataBaseConnector;
import com.swapp.model.Utilisateur;
import com.swapp.utils.PasswordHasher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurRepository {


    public boolean save(Utilisateur u) {
        String sql = "INSERT INTO Utilisateur (Nom_utilisateur, Mot_de_passe, Droit) VALUES (?, ?, ?)";

        try (Connection conn = DataBaseConnector.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, u.getNomUtilisateur());
            pstmt.setString(2, u.getMotDePasse());
            pstmt.setString(3, u.getDroit());

            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.err.println("Erreur lors de la création de l'utilisateur : " + e.getMessage());
            return false;
        }
    }

    public boolean login(String username, String password) {
        String sql = "SELECT Mot_de_passe FROM Utilisateur WHERE Nom_utilisateur = ?";

        try (Connection conn = DataBaseConnector.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String hashStocke = rs.getString("Mot_de_passe");

                    return PasswordHasher.check(password, hashStocke);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Utilisateur> findAll() {
        List<Utilisateur> users = new ArrayList<>();
        String sql = "SELECT * FROM Utilisateur";

        try (Connection conn = DataBaseConnector.getInstance();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                users.add(new Utilisateur(
                        rs.getInt("Id_Utilisateur"),
                        rs.getString("Nom_utilisateur"),
                        rs.getString("Mot_de_passe"),
                        rs.getString("Droit")
                ));

            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public Utilisateur findByUsername(String username) {
        String sql = "SELECT * FROM Utilisateur WHERE Nom_utilisateur = ?";
        try (Connection conn = DataBaseConnector.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Utilisateur(
                        rs.getInt("Id_Utilisateur"),
                        rs.getString("Nom_utilisateur"),
                        rs.getString("Mot_de_passe"),
                        rs.getString("Droit")
                );
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public void updateRole(String username, String nouveauDroit) {
        String sql = "UPDATE Utilisateur SET Droit = ? WHERE Nom_utilisateur = ?";
        try (Connection conn = DataBaseConnector.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nouveauDroit);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void delete(String username) {
        String sql = "DELETE FROM Utilisateur WHERE Nom_utilisateur = ?";
        try (Connection conn = DataBaseConnector.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public boolean updateDroit(int id, String nouveauDroit) {
        String sql = "UPDATE Utilisateur SET Droit = ? WHERE Id_Utilisateur = ?";

        try (Connection conn = DataBaseConnector.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nouveauDroit);
            pstmt.setInt(2, id);

            int lignesModifiees = pstmt.executeUpdate();

            return lignesModifiees > 0;

        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour des droits : " + e.getMessage());
            return false;
        }
    }
}