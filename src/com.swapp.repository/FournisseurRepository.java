package com.swapp.repository;

import com.swapp.config.DataBaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FournisseurRepository {
    public void save(String nom, String adresse, String tel, String pays, String domaine) {
        String sql = "INSERT INTO Fournisseur (nom, adresse, telephone, pays, domaine_fournisseur) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DataBaseConnector.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nom);
            pstmt.setString(2, adresse);
            pstmt.setString(3, tel);
            pstmt.setString(4, pays);
            pstmt.setString(5, domaine);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> findAllNames() {
        List<String> noms = new ArrayList<>();
        String sql = "SELECT nom FROM Fournisseur";
        try (Connection conn = DataBaseConnector.getInstance();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) noms.add(rs.getString("nom"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return noms;
    }
}