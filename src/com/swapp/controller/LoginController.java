package com.swapp.controller;

import com.swapp.model.Utilisateur;
import com.swapp.repository.UtilisateurRepository;
import com.swapp.service.AuthService;

public class LoginController {
    private UtilisateurRepository userRepo = new UtilisateurRepository();
    private AuthService authService = new AuthService();

    public boolean authentifier(String login, String password) {
        Utilisateur user = userRepo.findByUsername(login);

        if (user != null) {
            return authService.verifierAuthentification(password, user.getMotDePasse());
        }

        return false;
    }
}