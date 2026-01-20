DROP DATABASE IF EXISTS swapp_db;
CREATE DATABASE swapp_db CHARACTER SET 'utf8mb4';
USE swapp_db;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE Adresse(
   Id_Adresse INT AUTO_INCREMENT,
   rue VARCHAR(100),
   numero VARCHAR(20),
   BP VARCHAR(20),
   ville VARCHAR(100),
   CONSTRAINT adresse_id_pk PRIMARY KEY(Id_Adresse)
) ENGINE=InnoDB;

CREATE TABLE Client (
   Id_Client INT AUTO_INCREMENT,
   nom VARCHAR(50) NOT NULL,
   prenom VARCHAR(50),
   num_telephone VARCHAR(20) NOT NULL,
   bon_Achat BOOLEAN DEFAULT FALSE,
   Montant_bon DECIMAL(10, 2) DEFAULT 0.00,
   Id_Adresse INT NOT NULL,
   CONSTRAINT client_id_pk PRIMARY KEY(Id_Client),
   CONSTRAINT client_id_adresse_fk FOREIGN KEY(Id_Adresse) REFERENCES Adresse(Id_Adresse)
) ENGINE=InnoDB;

CREATE TABLE Article (
   Id_Article INT AUTO_INCREMENT,
   nom VARCHAR(100) NOT NULL,
   statut VARCHAR(50),
   type VARCHAR(50),
   etat VARCHAR(50),
   annee INT,
   num_serie VARCHAR(100),
   garantie BOOLEAN DEFAULT FALSE,
   Quantite_Disponible INT NOT NULL DEFAULT 0,
   prix DECIMAL(10, 2) NOT NULL,
   CONSTRAINT article_id_pk PRIMARY KEY(Id_Article)
) ENGINE=InnoDB;

CREATE TABLE Etat(
   Id_Etat INT AUTO_INCREMENT,
   statut VARCHAR(50),
   date_changement DATETIME DEFAULT CURRENT_TIMESTAMP,
   Id_Article INT NOT NULL,
   CONSTRAINT etat_id_pk PRIMARY KEY(Id_Etat),
   CONSTRAINT etat_id_article_fk FOREIGN KEY(Id_Article) REFERENCES Article(Id_Article)
) ENGINE=InnoDB;

CREATE TABLE gestion_achat_d_article_client(
   Id_Gestion_arch_art_client INT AUTO_INCREMENT,
   bon_d_achat BOOLEAN,
   Argent BOOLEAN,
   date_echange DATE,
   decision VARCHAR(50),
   CONSTRAINT gestion_ach_art_client_id_pk PRIMARY KEY(Id_Gestion_arch_art_client)
) ENGINE=InnoDB;

CREATE TABLE Paiement (
   Id_Paiement INT AUTO_INCREMENT,
   date_paiement DATETIME DEFAULT CURRENT_TIMESTAMP,
   montant DECIMAL(10,2),
   mode_paiement VARCHAR(50),
   statut VARCHAR(50),
   CONSTRAINT paiement_id_pk PRIMARY KEY(Id_Paiement)
) ENGINE=InnoDB;

CREATE TABLE Fournisseur (
   Id_Fournisseur INT AUTO_INCREMENT,
   nom VARCHAR(100),
   adresse VARCHAR(255),
   telephone VARCHAR(20),
   pays VARCHAR(50),
   domaine_fournisseur VARCHAR(100),
   CONSTRAINT fournisseur_id_pk PRIMARY KEY(Id_Fournisseur)
) ENGINE=InnoDB;

CREATE TABLE Achat_materielle (
   Id_Achat_materielle INT AUTO_INCREMENT,
   num_suivi INT,
   num_com_fournisseur INT,
   montant DECIMAL(10, 2),
   caisse DECIMAL(10, 2),
   Id_Fournisseur INT NOT NULL,
   CONSTRAINT achat_materielle_id_pk PRIMARY KEY(Id_Achat_materielle),
   CONSTRAINT achat_materielle_id_fournisseur_fk FOREIGN KEY(Id_Fournisseur) REFERENCES Fournisseur(Id_Fournisseur)
) ENGINE=InnoDB;

CREATE TABLE Facture (
   Id_Facture INT AUTO_INCREMENT,
   date_facturation DATE,
   montant DECIMAL(10,2),
   garantie BOOLEAN DEFAULT FALSE,
   Id_Paiement INT NOT NULL,
   CONSTRAINT facture_id_pk PRIMARY KEY(Id_Facture),
   CONSTRAINT facture_id_paiement_fk FOREIGN KEY(Id_Paiement) REFERENCES Paiement(Id_Paiement)
) ENGINE=InnoDB;

CREATE TABLE Commande (
   Id_Commande INT AUTO_INCREMENT,
   date_commande DATE,
   statut VARCHAR(50),
   Quantite_Demandee INT NOT NULL,
   Id_Facture INT NOT NULL,
   Id_Client INT NOT NULL,
   CONSTRAINT commande_id_pk PRIMARY KEY(Id_Commande),
   CONSTRAINT commande_id_facture_fk FOREIGN KEY(Id_Facture) REFERENCES Facture(Id_Facture),
   CONSTRAINT commande_id_client_fk FOREIGN KEY(Id_Client) REFERENCES Client(Id_Client)
) ENGINE=InnoDB;

CREATE TABLE Type_reparation(
   Id_Type_reparation INT AUTO_INCREMENT,
   vente BOOLEAN,
   client_ext BOOLEAN,
   garanti BOOLEAN,
   domaine VARCHAR(50),
   CONSTRAINT type_reparation_id_pk PRIMARY KEY(Id_Type_reparation)
) ENGINE=InnoDB;

CREATE TABLE Reparation (
   Id_Reparation INT AUTO_INCREMENT,
   num_suivi VARCHAR(50),
   nom_reparateur VARCHAR(50),
   date_entree DATE,
   date_sortie DATE,
   statut VARCHAR(50),
   commentaire TEXT,
   Id_Type_reparation INT NOT NULL,
   Id_Facture INT,
   Id_Article INT NOT NULL,
   CONSTRAINT reparation_id_pk PRIMARY KEY(Id_Reparation),
   CONSTRAINT reparation_id_type_reparation_fk FOREIGN KEY(Id_Type_reparation) REFERENCES Type_reparation(Id_Type_reparation),
   CONSTRAINT reparation_id_facture_fk FOREIGN KEY(Id_Facture) REFERENCES Facture(Id_Facture),
   CONSTRAINT reparation_id_article_fk FOREIGN KEY(Id_Article) REFERENCES Article(Id_Article)
) ENGINE=InnoDB;

CREATE TABLE DEPOSER(
   Id_Client INT,
   Id_Article INT,
   Id_Gestion_arch_art_client INT,
   date_depot DATE,
   CONSTRAINT deposer_id_pk PRIMARY KEY(Id_Client, Id_Article, Id_Gestion_arch_art_client),
   CONSTRAINT deposer_id_client_fk FOREIGN KEY(Id_Client) REFERENCES Client(Id_Client),
   CONSTRAINT deposer_id_article_fk FOREIGN KEY(Id_Article) REFERENCES Article(Id_Article),
   CONSTRAINT deposer_id_gestion_ach_art_client_fk FOREIGN KEY(Id_Gestion_arch_art_client) REFERENCES gestion_achat_d_article_client(Id_Gestion_arch_art_client)
) ENGINE=InnoDB;

CREATE TABLE PASSER(
   Id_Client INT,
   Id_Commande INT,
   prix DECIMAL(10,2),
   date_commande DATE,
   CONSTRAINT passer_id_pk PRIMARY KEY(Id_Client, Id_Commande),
   CONSTRAINT passer_id_client_fk FOREIGN KEY(Id_Client) REFERENCES Client(Id_Client),
   CONSTRAINT passer_id_commande_fk FOREIGN KEY(Id_Commande) REFERENCES Commande(Id_Commande)
) ENGINE=InnoDB;

CREATE TABLE SOLLICITER(
   Id_Reparation INT,
   Id_Achat_materielle INT,
   prix DECIMAL(10,2),
   quantite INT,
   CONSTRAINT solliciter_id_pk PRIMARY KEY(Id_Reparation, Id_Achat_materielle),
   CONSTRAINT solliciter_id_reparation_fk FOREIGN KEY(Id_Reparation) REFERENCES Reparation(Id_Reparation),
   CONSTRAINT solliciter_id_achat_materielle_fk FOREIGN KEY(Id_Achat_materielle) REFERENCES Achat_materielle(Id_Achat_materielle)
) ENGINE=InnoDB;

CREATE TABLE COMMANDER(
   Id_Achat_materielle INT,
   Id_Fournisseur INT,
   CONSTRAINT commander_id_pk PRIMARY KEY(Id_Achat_materielle, Id_Fournisseur),
   CONSTRAINT commander_id_achat_materielle_fk FOREIGN KEY(Id_Achat_materielle) REFERENCES Achat_materielle(Id_Achat_materielle),
   CONSTRAINT commander_id_fournisseur_fk FOREIGN KEY(Id_Fournisseur) REFERENCES Fournisseur(Id_Fournisseur)
) ENGINE=InnoDB;

CREATE TABLE CONTENIR(
   Id_Article INT,
   Id_Commande INT,
   CONSTRAINT contenir_id_pk PRIMARY KEY(Id_Article, Id_Commande),
   CONSTRAINT contenir_id_article_fk FOREIGN KEY(Id_Article) REFERENCES Article(Id_Article),
   CONSTRAINT contenir_id_commande_fk FOREIGN KEY(Id_Commande) REFERENCES Commande(Id_Commande)
) ENGINE=InnoDB;

CREATE TABLE Ligne_commande(
   Id_Ligne_commande INT AUTO_INCREMENT,
   Id_Commande INT NOT NULL,
   Id_Article INT NOT NULL,
   Quantite_Commandee INT NOT NULL,
   CONSTRAINT ligne_commande_id_pk PRIMARY KEY(Id_Ligne_commande),
   CONSTRAINT ligne_commande_id_commande_fk FOREIGN KEY(Id_Commande) REFERENCES Commande(Id_Commande),
   CONSTRAINT ligne_commande_id_article_fk FOREIGN KEY(Id_Article) REFERENCES Article(Id_Article)
) ENGINE=InnoDB;

CREATE TABLE Historique(
   Id_Historique INT AUTO_INCREMENT,
   action VARCHAR(255),
   date_action DATETIME DEFAULT CURRENT_TIMESTAMP,
   CONSTRAINT historique_id_pk PRIMARY KEY(Id_Historique)
) ENGINE=InnoDB;

CREATE TABLE Employe (
    Id_Employe INT AUTO_INCREMENT,
    Nom VARCHAR(100),
    Prenom VARCHAR(100),
    Num_telephone VARCHAR(20),
    Poste VARCHAR(100),
    PRIMARY KEY(Id_Employe)
) ENGINE=InnoDB;

CREATE TABLE Utilisateur (
    Id_Utilisateur INT AUTO_INCREMENT,
    Nom_utilisateur VARCHAR(100),
    Mot_de_passe VARCHAR(100),
    Token VARCHAR(255),
    Droit VARCHAR(50),
    PRIMARY KEY(Id_Utilisateur)
) ENGINE=InnoDB;

CREATE TABLE reinitialisation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

SET FOREIGN_KEY_CHECKS = 1;


-- ========================================================================================
-- PROCEDURES STOCKEES
-- ========================================================================================

DELIMITER //

-- 1. Procédure pour ajouter un client avec une adresse
CREATE PROCEDURE AddAdresseClient (
   IN p_Nom VARCHAR(50),
   IN p_Prenom VARCHAR(50),
   IN p_NumTelephone VARCHAR(20),
   IN p_Rue VARCHAR(100),
   IN p_Numero VARCHAR(20),
   IN p_BP VARCHAR(20),
   IN p_Ville VARCHAR(100)
)
BEGIN
   DECLARE v_IdAdresse INT;

   START TRANSACTION;

   INSERT INTO Adresse (rue, numero, BP, ville)
   VALUES (p_Rue, p_Numero, p_BP, p_Ville);

   SET v_IdAdresse = LAST_INSERT_ID();

   INSERT INTO Client (nom, prenom, num_telephone, Id_Adresse)
   VALUES (p_Nom, p_Prenom, p_NumTelephone, v_IdAdresse);

   COMMIT;
END //

-- 2. Procédure pour passer une commande
CREATE PROCEDURE PasserCommande(
   IN p_Id_Client INT,
   IN p_Id_Article INT,
   IN p_Quantite_Commandee INT,
   OUT p_Id_Commande INT
)
BEGIN
   DECLARE v_Id_Facture INT;

   INSERT INTO Facture (date_facturation, montant, garantie, Id_Paiement)
   VALUES (CURDATE(), 0, FALSE, NULL);

   SET v_Id_Facture = LAST_INSERT_ID();

   INSERT INTO Commande (date_commande, statut, Quantite_Demandee, Id_Facture, Id_Client)
   VALUES (CURDATE(), 'En cours', p_Quantite_Commandee, v_Id_Facture, p_Id_Client);

   SET p_Id_Commande = LAST_INSERT_ID();

   INSERT INTO Ligne_commande (Id_Commande, Id_Article, Quantite_Commandee)
   VALUES (p_Id_Commande, p_Id_Article, p_Quantite_Commandee);

   INSERT INTO Historique (action, date_action)
   VALUES (CONCAT('Commande passée ID: ', p_Id_Commande), NOW());

END //

-- 3. Procédure pour vérifier l'existence d'une commande
CREATE PROCEDURE VerifierExistenceCommande(IN commandeId INT, OUT existe BOOL)
BEGIN
    DECLARE rowCount INT;

    SELECT COUNT(*) INTO rowCount
    FROM Commande
    WHERE Id_Commande = commandeId;

    IF rowCount > 0 THEN
        SET existe = TRUE;
    ELSE
        SET existe = FALSE;
    END IF;
END //

-- 4. Procédure pour la facturation et le paiement
CREATE PROCEDURE FacturerEtPayer(
   IN p_idCommande INT,
   IN p_montantFacture DECIMAL(10,2),
   IN p_modePaiement VARCHAR(50),
   OUT r_idFacture INT,
   OUT r_idPaiement INT
)
BEGIN
   DECLARE v_idFacture INT;

   DECLARE EXIT HANDLER FOR SQLEXCEPTION
   BEGIN
      ROLLBACK;
      SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Erreur lors de la facturation et du paiement.';
   END;

   START TRANSACTION;

   SELECT Id_Facture INTO v_idFacture FROM Commande WHERE Id_Commande = p_idCommande;
   SET r_idFacture = v_idFacture;

   INSERT INTO Paiement (date_paiement, montant, mode_paiement, statut)
   VALUES (NOW(), p_montantFacture, p_modePaiement, 'Payé');

   SET r_idPaiement = LAST_INSERT_ID();

   UPDATE Facture
   SET montant = p_montantFacture, Id_Paiement = r_idPaiement
   WHERE Id_Facture = v_idFacture;

   UPDATE Commande SET statut = 'Terminée' WHERE Id_Commande = p_idCommande;

   INSERT INTO Historique (action, date_action)
   VALUES (CONCAT('Paiement effectué pour commande: ', p_idCommande), NOW());

   COMMIT;
END //

-- 5. Procédure pour afficher les lignes de commandes
CREATE PROCEDURE AfficherLignesCommande()
BEGIN
   SELECT Id_Commande, Id_Article, Quantite_Commandee FROM Ligne_commande;
END //

DELIMITER ;

-- ========================================================================================
-- DÉCLENCHEURS (TRIGGERS)
-- ========================================================================================

DELIMITER //

-- Trigger pour mettre à jour la quantité d'un article (SANS SELECTS INUTILES)
CREATE TRIGGER update_stock AFTER INSERT ON Ligne_commande
FOR EACH ROW
BEGIN
    UPDATE Article
    SET Quantite_Disponible = Quantite_Disponible - NEW.Quantite_Commandee
    WHERE Id_Article = NEW.Id_Article;
END //

-- Triggers d'historisation
CREATE TRIGGER historisation_adresse AFTER INSERT ON Adresse
FOR EACH ROW
BEGIN
    INSERT INTO Historique (action, date_action) VALUES ('Nouvelle Adresse ajoutée', NOW());
END //

CREATE TRIGGER historisation_client AFTER INSERT ON Client
FOR EACH ROW
BEGIN
    INSERT INTO Historique (action, date_action) VALUES (CONCAT('Nouveau Client: ', NEW.nom), NOW());
END //

CREATE TRIGGER historisation_article AFTER INSERT ON Article
FOR EACH ROW
BEGIN
    INSERT INTO Historique (action, date_action) VALUES (CONCAT('Nouvel Article: ', NEW.nom), NOW());
END //

CREATE TRIGGER historisation_vente AFTER INSERT ON Commande
FOR EACH ROW
BEGIN
    INSERT INTO Historique (action, date_action) VALUES (CONCAT('Nouvelle Commande ID: ', NEW.Id_Commande), NOW());
END //

DELIMITER ;