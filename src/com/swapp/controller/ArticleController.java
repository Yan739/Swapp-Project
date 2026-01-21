package com.swapp.controller;

import com.swapp.repository.ArticleRepository;
import com.swapp.model.Article;
import java.util.List;

public class ArticleController {
    private ArticleRepository articleRepo = new ArticleRepository();

    public List<Article> chargerToutLeStock() {
        return articleRepo.findAll();
    }

    public String ajouterArticle(@org.jetbrains.annotations.NotNull Article a) {
        if (a.getNom().isEmpty() || a.getPrix() <= 0) {
            return "Données invalides !";
        }
        articleRepo.save(a);
        return "Article ajouté avec succès.";
    }

    public void changerStatut(int id, String nouveauStatut) {
        articleRepo.updateStatut(id, nouveauStatut);
    }
}