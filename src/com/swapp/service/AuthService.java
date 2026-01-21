package com.swapp.service;

import org.mindrot.jbcrypt.BCrypt;

public class AuthService {

    public boolean verifierAuthentification(String motDePasseSaisi, String motDePasseHache) {
        if (motDePasseHache == null || !motDePasseHache.startsWith("$2a$")) {
            return motDePasseSaisi.equals(motDePasseHache);
        }
        try {
            return BCrypt.checkpw(motDePasseSaisi, motDePasseHache);
        } catch (Exception e) {
            return false;
        }
    }
}