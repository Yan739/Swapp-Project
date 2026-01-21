package com.swapp.config;

import com.swapp.model.Utilisateur;

public class SessionManager {
    private static Utilisateur utilisateurConnecte;

    public static void connecter(Utilisateur user) {
        utilisateurConnecte = user;
    }

    public static void deconnecter() {
        utilisateurConnecte = null;
    }

    public static boolean isConnected() {
        return utilisateurConnecte != null;
    }

    public static Utilisateur getUtilisateur() {
        return utilisateurConnecte;
    }
}