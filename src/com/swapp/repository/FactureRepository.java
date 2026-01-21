package com.swapp.repository;

import com.swapp.config.DataBaseConnector;
import java.sql.*;

public class FactureRepository {

    public void genererFactureFinale(int idCommande, double montantTotal, String modePaiement) {
        String sql = "{call FacturerEtPayer(?, ?, ?, ?, ?)}";

        try (Connection conn = DataBaseConnector.getInstance();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, idCommande);
            cstmt.setDouble(2, montantTotal);
            cstmt.setString(3, modePaiement);

            cstmt.registerOutParameter(4, Types.INTEGER);
            cstmt.registerOutParameter(5, Types.INTEGER);

            cstmt.execute();
            System.out.println("Facture n°" + cstmt.getInt(4) + " générée.");

        } catch (SQLException e) {
            System.err.println("Erreur facturation : " + e.getMessage());
        }
    }
}