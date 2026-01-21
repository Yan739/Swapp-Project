package com.swapp.test;

import com.swapp.config.DataBaseConnector;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class TestInsert {
    public static void main(String[] args) {
        String user = "admin";
        String pass = "1234";
        String role = "ADMIN";

        String hashedPass = BCrypt.hashpw(pass, BCrypt.gensalt());

        String sql = "INSERT INTO Utilisateur (Nom_utilisateur, Mot_de_passe, Droit) VALUES (?, ?, ?)";

        try (Connection conn = DataBaseConnector.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user);
            pstmt.setString(2, hashedPass);
            pstmt.setString(3, role);

            pstmt.executeUpdate();
            System.out.println("Utilisateur de test inséré avec succès !");
            System.out.println("Login: " + user + " | Pass: " + pass);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}