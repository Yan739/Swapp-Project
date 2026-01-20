package com.swapp.repository;

import com.swapp.config.DataBaseConnector;
import com.swapp.model.Client;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository {

    public void save(Client client) {
        String sql = "INSERT INTO Client (nom, prenom, adresse, contact, bon_achat, montant) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DataBaseConnector.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, client.getNom());
            pstmt.setString(2, client.getPrenom());
            pstmt.setString(3, client.getAdresse());
            pstmt.setInt(4, client.getContact());
            pstmt.setBoolean(5, client.isBonAchat());
            pstmt.setDouble(6, client.getMontant());

            pstmt.executeUpdate();

            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) client.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            System.err.println("Erreur Save Client: " + e.getMessage());
        }
    }

    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM Client";
        try (Connection conn = DataBaseConnector.getInstance();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                clients.add(mapResultSetToClient(rs));
            }
        } catch (SQLException e) {
            System.err.println("Erreur FindAll Clients: " + e.getMessage());
        }
        return clients;
    }

    public Client findById(int id) {
        String sql = "SELECT * FROM Client WHERE id_client = ?";
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

    public void update(Client client) {
        String sql = "UPDATE Client SET nom=?, prenom=?, adresse=?, contact=?, bon_achat=?, montant=? WHERE id_client=?";
        try (Connection conn = DataBaseConnector.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, client.getNom());
            pstmt.setString(2, client.getPrenom());
            pstmt.setString(3, client.getAdresse());
            pstmt.setInt(4, client.getContact());
            pstmt.setBoolean(5, client.isBonAchat());
            pstmt.setDouble(6, client.getMontant());
            pstmt.setInt(7, client.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur Update Client: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Client WHERE id_client = ?";
        try (Connection conn = DataBaseConnector.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur Delete Client: " + e.getMessage());
        }
    }

    public List<Client> findByName(String nom) {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM Client WHERE nom LIKE ?";
        try (Connection conn = DataBaseConnector.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + nom + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) clients.add(mapResultSetToClient(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return clients;
    }

    public void saveWithAdresse(Client c, String rue, String numero, String bp, String ville) {
        String sql = "{call AddAdresseClient(?, ?, ?, ?, ?, ?, ?)}";

        try (Connection conn = DataBaseConnector.getInstance();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, c.getNom());
            cstmt.setString(2, c.getPrenom());
            cstmt.setString(3, String.valueOf(c.getContact())); // num_telephone
            cstmt.setString(4, rue);
            cstmt.setString(5, numero);
            cstmt.setString(6, bp);
            cstmt.setString(7, ville);

            cstmt.execute();
            System.out.println("Client et Adresse enregistrés via Procédure !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Client mapResultSetToClient(ResultSet rs) throws SQLException {
        return new Client(
                rs.getInt("id_client"),
                rs.getString("nom"),
                rs.getString("prenom"),
                rs.getString("adresse"),
                rs.getInt("contact"),
                rs.getBoolean("bon_achat"),
                rs.getDouble("montant")
        );
    }
}
