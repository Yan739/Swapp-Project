package com.swapp.repository;

import com.swapp.config.DataBaseConnector;
import com.swapp.model.Article;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ArticleRepository {

    public Article findById(int id) {
        String sql = "SELECT * FROM Article WHERE Id_Article = ?";
        try(Connection connect = DataBaseConnector.getInstance();
            PreparedStatement pstm = connect.prepareStatement(sql)){
           pstm.setInt(1, id);
           try(ResultSet result = pstm.executeQuery()){
               if (result.next()){
                   return new Article(
                           result.getInt("Id_Article"),
                           result.getString("nom"),
                           result.getString("statut"),
                           result.getString("type"),
                           result.getString("etat"),
                           result.getInt("annee"),
                           result.getString("num_serie"),
                           result.getBoolean("garantie"),
                           result.getInt("Quantite_Disponible"),
                           result.getDouble("prix"));
               }
           }

        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la recherche de l'article : " + e.getMessage());
        }
        return null;
    }

    public void save(Article article){
        String sql = "INSERT INTO Article (nom, statut, type, etat, annee, num_serie, garantie, prix, Quantite_Disponible) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection connect = DataBaseConnector.getInstance();
            PreparedStatement pstm = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, article.getNom());
            pstm.setString(2, article.getStatut());
            pstm.setString(3, article.getType());
            pstm.setString(4, article.getEtat());
            pstm.setInt(5, article.getAnnee());
            pstm.setString(6, article.getNumSerie());
            pstm.setBoolean(7, article.isGarantie());
            pstm.setInt(8, article.getQuantiteDisponible());
            pstm.setDouble(9, article.getPrix());

            int result = pstm.executeUpdate();

            if (result > 0) {
                try(ResultSet generatedKeys = pstm.getGeneratedKeys()){
                    if (generatedKeys.next()){
                        article.setIdArticle(generatedKeys.getInt(1));
                        System.out.printf("Article insérer avec l'id : %s\n", article.getIdArticle());
                    }
                }catch (SQLException e){
                    System.err.println("Erreur SQL lors de l'enregistrement de l'article : " + e.getMessage());
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(String idArticle, String nom, String statut, String type, String etat,
                              int annee, int numSerie, String garantie, int quantite) {

        String sql = "UPDATE Article SET nom = '" + nom + "', statut = '" + statut
                + "', type = '" + type + "', etat = '" + etat + "', année = " + annee
                + ", num_série = " + numSerie + ", garantie = '" + garantie
                + "', Quantité_Disponible = " + quantite + " WHERE Id_Article = " + idArticle;

        try(Connection connect = DataBaseConnector.getInstance();
            PreparedStatement pstm = connect.prepareStatement(sql)) {

            int result = pstm.executeUpdate();

            System.out.printf("Article de nom : %s et ID : %s modifié avec succès !", nom, idArticle);

        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la modification de l'article : " + e.getMessage());

        }
    }

    public List<Article> findAll() {
        String sql = "SELECT * FROM Article";
        List<Article> articles = new ArrayList<>();

        try(Connection connect = DataBaseConnector.getInstance();
            PreparedStatement pstm = connect.prepareStatement(sql);
            ResultSet result = pstm.executeQuery()) {

            while (result.next()) {
                Article a = new Article(
                        result.getInt("Id_Article"),
                        result.getString("nom"),
                        result.getString("statut"),
                        result.getString("type"),
                        result.getString("etat"),
                        result.getInt("annee"),
                        result.getString("num_serie"),
                        result.getBoolean("garantie"),
                        result.getInt("Quantite_Disponible"),
                        result.getDouble("prix")
                );
                articles.add(a);
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la récupération de la liste d'articles : " + e.getMessage());
        }
        return articles;
    }

    public void delete(int id) {
        String sql = "DELETE FROM Article WHERE Id_Article = ?";

        try (Connection connect = DataBaseConnector.getInstance();
             PreparedStatement pstm = connect.prepareStatement(sql)) {

            pstm.setInt(1, id);
            int deletedLines = pstm.executeUpdate();

            if (deletedLines > 0) {
                System.out.println("Article #" + id + " supprimé avec succès.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de l'article : " + e.getMessage());
        }
    }

    public List<Article> findByStatut(String statut) {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT * FROM Article WHERE statut = ?";

        try (Connection connect = DataBaseConnector.getInstance();
             PreparedStatement pstm = connect.prepareStatement(sql)) {

            pstm.setString(1, statut);

            try (ResultSet result = pstm.executeQuery()) {
                while (result.next()) {
                    articles.add(new Article(
                            result.getInt("Id_Article"),
                            result.getString("nom"),
                            result.getString("statut"),
                            result.getString("type"),
                            result.getString("etat"),
                            result.getInt("annee"),
                            result.getString("num_serie"),
                            result.getBoolean("garantie"),
                            result.getInt("Quantite_Disponible"),
                            result.getDouble("prix")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur de filtrage par statut : " + e.getMessage());
        }
        return articles;
    }

    public void updateStock(int idArticle, int nouvelleQuantite) {
        String sql = "UPDATE Article SET Quantite_Disponible = ? WHERE Id_Article = ?";

        try (Connection connect = DataBaseConnector.getInstance();
             PreparedStatement pstm = connect.prepareStatement(sql)) {

            pstm.setInt(1, nouvelleQuantite);
            pstm.setInt(2, idArticle);

            int rows = pstm.executeUpdate();
            if (rows > 0) {
                System.out.println("Stock mis à jour pour l'article #" + idArticle);
            }
        } catch (SQLException e) {
            System.err.println("Erreur de mise à jour du stock : " + e.getMessage());
        }
    }

    public List<Article> searchByName(String nomRecherche) {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT * FROM Article WHERE nom LIKE ?";

        try (Connection connect = DataBaseConnector.getInstance();
             PreparedStatement pstm = connect.prepareStatement(sql)) {

            pstm.setString(1, "%" + nomRecherche + "%");

            try (ResultSet result = pstm.executeQuery()) {
                while (result.next()) {
                    articles.add(new Article(
                            result.getInt("Id_Article"),
                            result.getString("nom"),
                            result.getString("statut"),
                            result.getString("type"),
                            result.getString("etat"),
                            result.getInt("annee"),
                            result.getString("num_serie"),
                            result.getBoolean("garantie"),
                            result.getInt("Quantite_Disponible"),
                            result.getDouble("prix")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche par nom : " + e.getMessage());
        }
        return articles;
    }

}


