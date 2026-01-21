# SWAPP - Système de Gestion Boutique & Réparations

SWAPP est une application console robuste développée en Java SE. Ce projet marque une étape importante de rafraîchissement des compétences techniques, en mettant l'accent sur l'architecture logicielle propre (Clean Architecture) et l'intégration avancée avec une base de données relationnelle.

---

## Objectifs du Projet

L'objectif principal était de reconstruire un système de gestion complet en appliquant les standards de développement modernes :
* Architecture MVC/Repository : Séparation claire des responsabilités pour faciliter la maintenance.
* Sécurité des données : Authentification sécurisée via le hachage BCrypt.
* Logique Métier SQL : Utilisation intensive de Procédures Stockées et Triggers pour garantir l'intégrité des transactions financières et des stocks.
* Automatisation : Génération automatique de documents PDF (factures) lors des ventes.

---

## Stack Technique

* Langage : Java 17+
* Base de données : MySQL 8.0
* Tests & Qualité : JUnit 5
* Bibliothèques tierces :
    * mysql-connector-j : Connexion JDBC.
    * jBCrypt : Sécurisation des mots de passe.
    * iText : Génération de fichiers PDF.

---

## Fonctionnalités Clés

### Sécurité & Authentification
- Connexion sécurisée avec gestion des rôles (ADMIN, EMPLOYE).
- Mots de passe stockés sous forme de hash non réversibles via BCrypt.

### Gestion des Ventes (Module Vente)
- Recherche d'articles et de clients en temps réel.
- Processus transactionnel : Création de commande, facturation, paiement et mise à jour des stocks synchronisés via MySQL.
- Exportation automatique de la facture en PDF.

### Service de Réparations
- Prise en charge d'appareils avec numéro de suivi unique.
- Suivi du cycle de vie : En attente, En cours, Terminée, Livrée.

### Dashboard & Inventaire
- Alertes de seuil de stock (automatisées via triggers SQL).
- Vue globale de l'état du magasin.

---

## Tests Automatisés

Le projet intègre une suite de tests unitaires et d'intégration pour garantir la fiabilité de la logique métier :
* Framework : JUnit 5.
* Tests Unitaires : Validation de la logique de sécurité (AuthServiceTest) et des calculs financiers.
* Tests d'Intégration : Vérification de la persistance des données et du bon fonctionnement des requêtes SQL (ClientRepositoryTest, ArticleRepositoryTest).
* Couverture : Utilisation des outils de Code Coverage d'IntelliJ IDEA pour assurer la qualité du code.

---

## Architecture de la Base de Données

Le schéma swapp_db repose sur une structure normalisée :
- Clients & Adresses : Structure éclatée pour une meilleure flexibilité.
- Articles & Stocks : Suivi précis des quantités.
- Transactions : Tables Commande, Paiement et Facture liées pour une traçabilité totale.

---

## Installation & Configuration

1. Base de données :
    - Importez le fichier init.sql fourni.
    - Configurez vos accès dans com.swapp.config.DataBaseConnector.

2. Dépendances :
    - Ajoutez les librairies .jar (MySQL Connector, BCrypt, iText, JUnit 5) à votre classpath.

3. Exécution & Tests :
    - Pour l'application : Lancez Main.java.
    - Pour les tests : Faites un clic droit sur le dossier src/test/java -> Run 'All Tests'.

---

## À propos
Ce projet a été conçu comme un laboratoire pour réviser les fondamentaux de Java (JDBC, Collections, Exceptions, POO) tout en explorant des problématiques réelles de gestion d'entreprise.