package com.swapp.service;

import com.swapp.config.DataBaseConnector;
import com.swapp.repository.ArticleRepository;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class StatistiqueService {

    private ArticleRepository articleRepo = new ArticleRepository();

    public Map<String, Double> getVentesParMois() {
        Map<String, Double> stats = new HashMap<>();
        String sql = "SELECT DATE_FORMAT(date_facturation, '%Y-%m') as mois, SUM(montant) as total " +
                "FROM Facture GROUP BY mois ORDER BY mois ASC";

        try (Connection conn = DataBaseConnector.getInstance();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                stats.put(rs.getString("mois"), rs.getDouble("total"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return stats;
    }

    public double getChiffreAffaireJour() {
        String sql = "SELECT SUM(montant) FROM Facture WHERE date_facturation = CURDATE()";
        try (Connection conn = DataBaseConnector.getInstance();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getDouble(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0.0;
    }

    public int getNbAlertesStock() {
        int seuilCritique = 5;

        return articleRepo.countArticlesSousSeuil(seuilCritique);
    }
}