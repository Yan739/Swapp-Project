package com.swapp.repository;

import com.swapp.config.DataBaseConnector;
import com.swapp.model.Article;

import java.sql.*;


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
                       result.getInt("prix"));
           }
       }

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return null;
}




}
