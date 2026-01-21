package com.swapp.repository;

import com.swapp.config.DataBaseConnector;
import com.swapp.model.Client;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository {

    private final String SELECT_BASE = "SELECT c.*, a.rue, a.numero, a.BP, a.ville FROM Client c " +
            "JOIN Adresse a ON c.Id_Adresse = a.Id_Adresse";

    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        try (Connection conn = DataBaseConnector.getInstance();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_BASE)) {
            while (rs.next()) {
                clients.add(mapResultSetToClient(rs));
            }
        } catch (SQLException e) {
            System.err.println("Erreur FindAll Clients: " + e.getMessage());
        }
        return clients;
    }

    public Client findById(int id) {
        String sql = SELECT_BASE + " WHERE c.Id_Client = ?";
        try (Connection conn = DataBaseConnector.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return mapResultSetToClient(rs);
            }
        } catch (SQLException e) {
            System.err.println("Erreur FindById Client: " + e.getMessage());
        }
        return null;
    }

    public List<Client> findByName(String nom) {
        List<Client> clients = new ArrayList<>();
        String sql = SELECT_BASE + " WHERE c.nom LIKE ?";
        try (Connection conn = DataBaseConnector.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + nom + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) clients.add(mapResultSetToClient(rs));
            }
        } catch (SQLException e) {
            System.err.println("Erreur FindByName Client: " + e.getMessage());
        }
        return clients;
    }

    public void saveWithAdresse(Client c, String rue, String numero, String bp, String ville) {
        String sql = "{call AddAdresseClient(?, ?, ?, ?, ?, ?, ?)}";
        try (Connection conn = DataBaseConnector.getInstance();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setString(1, c.getNom());
            cstmt.setString(2, c.getPrenom());
            cstmt.setString(3, String.valueOf(c.getContact()));
            cstmt.setString(4, rue);
            cstmt.setString(5, numero);
            cstmt.setString(6, bp);
            cstmt.setString(7, ville);

            cstmt.execute();
            System.out.println("Client et Adresse enregistrés via Procédure !");
        } catch (SQLException e) {
            System.err.println("Erreur procédure AddAdresseClient: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Client WHERE Id_Client = ?";
        try (Connection conn = DataBaseConnector.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Client supprimé.");
        } catch (SQLException e) {
            System.err.println("Erreur Delete Client (FK active ?): " + e.getMessage());
        }
    }

    private Client mapResultSetToClient(ResultSet rs) throws SQLException {
        String adresseComplete = rs.getString("numero") + " " +
                rs.getString("rue") + ", " +
                rs.getString("ville") + " (" + rs.getString("BP") + ")";

        int contactInt = 0;
        try {
            contactInt = Integer.parseInt(rs.getString("num_telephone"));
        } catch (NumberFormatException e) {
        }

        return new Client(
                rs.getInt("Id_Client"),
                rs.getString("nom"),
                rs.getString("prenom"),
                adresseComplete,
                contactInt,
                rs.getBoolean("bon_Achat"),
                rs.getDouble("Montant_bon")
        );
    }
}