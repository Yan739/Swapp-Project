-- Création de la base de données
DROP DATABASE IF EXISTS Swapp_project;
CREATE DATABASE Swapp_project CHARACTER SET 'utf8';
USE Swapp_project;

-- Tables et relations

CREATE TABLE Adresse(
   Id_Adresse               INT AUTO_INCREMENT,
   rue                       VARCHAR(50),
   numero                    VARCHAR(50),
   BP                        VARCHAR(50),
   ville                     VARCHAR(50),
   CONSTRAINT adresse_id_pk PRIMARY KEY(Id_Adresse)
) ENGINE=InnoDB;

CREATE TABLE Client (
   Id_Client          INT AUTO_INCREMENT,
   nom                VARCHAR(50) NOT NULL,
   prenom             VARCHAR(50),
   num_telephone      INT NOT NULL,
   bon_Achat          BOOLEAN, 
   Montant_bon        DECIMAL(10, 2) DEFAULT 0.00,
   Id_Adresse         INT NOT NULL,
   CONSTRAINT client_id_pk PRIMARY KEY(Id_Client),
   CONSTRAINT client_id_adresse_fk FOREIGN KEY(Id_Adresse) REFERENCES Adresse(Id_Adresse)
) ENGINE=InnoDB;

CREATE TABLE Article (
   Id_Article          INT AUTO_INCREMENT,
   nom                 VARCHAR(50) NOT NULL,
   statut              VARCHAR(50),
   type                VARCHAR(50),
   etat                VARCHAR(50),
   année               INT,
   num_série           INT,
   garantie            BOOLEAN,
   Quantite_Disponible INT NOT NULL,
   prix                DECIMAL(10, 2) NOT NULL,
   CONSTRAINT article_id_pk PRIMARY KEY(Id_Article)
) ENGINE=InnoDB;

CREATE TABLE Etat(
   Id_Etat                     INT AUTO_INCREMENT,
   statut                      VARCHAR(50),
   date_changement             DATETIME,
   Id_Article                  INT NOT NULL,
   CONSTRAINT etat_id_pk PRIMARY KEY(Id_Etat),
   CONSTRAINT etat_id_article_fk FOREIGN KEY(Id_Article) REFERENCES Article(Id_Article)
) ENGINE=InnoDB;

CREATE TABLE gestion_achat_d_article_client(
   Id_Gestion_arch_art_client  INT AUTO_INCREMENT,
   bon_d_achat                 BOOLEAN, 
   Argent                      BOOLEAN, 
   date_échange                DATE,
   decision                    VARCHAR(50),
   CONSTRAINT gestion_ach_art_client_id_pk PRIMARY KEY(Id_Gestion_arch_art_client)
) ENGINE=InnoDB;

CREATE TABLE Paiement (
   Id_Paiement   INT AUTO_INCREMENT,
   date_paiement DATETIME,
   montant        DECIMAL(10,2),
   mode_paiement VARCHAR(50),
   statut         VARCHAR(50),
   CONSTRAINT paiement_id_pk PRIMARY KEY(Id_Paiement)
) ENGINE=InnoDB;

CREATE TABLE Fournisseur (
   Id_Fournisseur        INT AUTO_INCREMENT,
   nom                  VARCHAR(50),
   adresse              VARCHAR(50),
   telephone            INT,
   pays                 VARCHAR(50),
   domaine_fournisseur  VARCHAR(50),
   CONSTRAINT fournisseur_id_pk PRIMARY KEY(Id_Fournisseur)
) ENGINE=InnoDB;

CREATE TABLE Achat_materielle (
   Id_Achat_materielle      INT AUTO_INCREMENT,
   num_suivi               INT,
   num_com_fournisseur      INT,
   montant                 DECIMAL(10, 2),
   caisse                  DECIMAL(10, 2),
   Id_Fournisseur          INT NOT NULL,  
   CONSTRAINT achat_materielle_id_pk PRIMARY KEY(Id_Achat_materielle),
   CONSTRAINT achat_materielle_id_fournisseur_fk FOREIGN KEY(Id_Fournisseur) REFERENCES Fournisseur(Id_Fournisseur)
) ENGINE=InnoDB;


CREATE TABLE Facture (
   Id_Facture       INT AUTO_INCREMENT,
   date_facturation DATE,
   montant          DECIMAL(10,2),
   garantie         BOOLEAN,  
   Id_Paiement      INT NOT NULL,
   CONSTRAINT facture_id_pk PRIMARY KEY(Id_Facture),
   CONSTRAINT facture_id_paiement_fk FOREIGN KEY(Id_Paiement) REFERENCES Paiement(Id_Paiement)
) ENGINE=InnoDB;


CREATE TABLE Commande (
   Id_Commande       INT AUTO_INCREMENT,
   date_commande     DATE,
   statut            VARCHAR(50),
   Quantité_Demandée INT NOT NULL,
   Id_Facture        INT NOT NULL,
   Id_Client         INT NOT NULL,
   CONSTRAINT commande_id_pk PRIMARY KEY(Id_Commande),
   CONSTRAINT commande_id_facture_fk FOREIGN KEY(Id_Facture) REFERENCES Facture(Id_Facture),
   CONSTRAINT commande_id_client_fk FOREIGN KEY(Id_Client) REFERENCES Client(Id_Client)
) ENGINE=InnoDB;

CREATE TABLE Type_reparation(
   Id_Type_réparation  INT AUTO_INCREMENT,
   vente               BOOLEAN,  
   client_ext          BOOLEAN, 
   garanti             BOOLEAN,
   domaine             VARCHAR(50),
   CONSTRAINT type_reparation_id_pk PRIMARY KEY(Id_Type_réparation)
) ENGINE=InnoDB;

CREATE TABLE Reparation (
   Id_Réparation      INT AUTO_INCREMENT,
   num_suivi          VARCHAR(50), 
   nom_reparateur     VARCHAR(50),
   date_entree        DATE,
   date_sortie        DATE,
   statut             VARCHAR(50),
   commentaire        VARCHAR(50),
   Id_Type_réparation INT NOT NULL,
   Id_Facture         INT,
   Id_Article         INT NOT NULL,
   CONSTRAINT reparation_id_pk PRIMARY KEY(Id_Réparation),
   CONSTRAINT reparation_id_type_reparation_fk FOREIGN KEY(Id_Type_réparation) REFERENCES Type_reparation(Id_Type_réparation),
   CONSTRAINT reparation_id_facture_fk FOREIGN KEY(Id_Facture) REFERENCES Facture(Id_Facture),
   CONSTRAINT reparation_id_article_fk FOREIGN KEY(Id_Article) REFERENCES Article(Id_Article)
) ENGINE=InnoDB;

CREATE TABLE DEPOSER(
   Id_Client                    INT,
   Id_Article                   INT,
   Id_Gestion_arch_art_client   INT,
   date_depot                   DATE,
   CONSTRAINT deposer_id_pk PRIMARY KEY(Id_Client, Id_Article, Id_Gestion_arch_art_client),
   CONSTRAINT deposer_id_client_fk FOREIGN KEY(Id_Client) REFERENCES Client(Id_Client),
   CONSTRAINT deposer_id_article_fk FOREIGN KEY(Id_Article) REFERENCES Article(Id_Article),
   CONSTRAINT deposer_id_gestion_ach_art_client_fk FOREIGN KEY(Id_Gestion_arch_art_client) REFERENCES gestion_achat_d_article_client(Id_Gestion_arch_art_client)
) ENGINE=InnoDB;

CREATE TABLE PASSER(
   Id_Client           INT,
   Id_Commande        INT,
   prix                DECIMAL(10,2),
   date_commande      DATE,
   CONSTRAINT passer_id_pk PRIMARY KEY(Id_Client, Id_Commande),
   CONSTRAINT passer_id_client_fk FOREIGN KEY(Id_Client) REFERENCES Client(Id_Client),
   CONSTRAINT passer_id_commande_fk FOREIGN KEY(Id_Commande) REFERENCES Commande(Id_Commande)
) ENGINE=InnoDB;

CREATE TABLE SOLLICITER(
   Id_Réparation       INT,
   Id_Achat_materielle INT,
   prix                DECIMAL(10,2),
   quantité            INT,
   CONSTRAINT solliciter_id_pk PRIMARY KEY(Id_Réparation, Id_Achat_materielle),
   CONSTRAINT solliciter_id_reparation_fk FOREIGN KEY(Id_Réparation) REFERENCES Réparation(Id_Réparation),
   CONSTRAINT solliciter_id_achat_materielle_fk FOREIGN KEY(Id_Achat_materielle) REFERENCES Achat_materielle(Id_Achat_materielle)
) ENGINE=InnoDB;

CREATE TABLE COMMANDER(
   Id_Achat_materielle INT,
   Id_Fournisseur      INT,
   CONSTRAINT commander_id_pk PRIMARY KEY(Id_Achat_materielle, Id_Fournisseur),
   CONSTRAINT commander_id_achat_materielle_fk FOREIGN KEY(Id_Achat_materielle) REFERENCES Achat_materielle(Id_Achat_materielle),
   CONSTRAINT commander_id_fournisseur_fk FOREIGN KEY(Id_Fournisseur) REFERENCES Fournisseur(Id_Fournisseur)
) ENGINE=InnoDB;

CREATE TABLE CONTENIR(
   Id_Article          INT,
   Id_Commande         INT,
   CONSTRAINT contenir_id_pk PRIMARY KEY(Id_Article, Id_Commande),
   CONSTRAINT contenir_id_article_fk FOREIGN KEY(Id_Article) REFERENCES Article(Id_Article),
   CONSTRAINT contenir_id_commande_fk FOREIGN KEY(Id_Commande) REFERENCES Commande(Id_Commande)
) ENGINE=InnoDB;

CREATE TABLE Ligne_commande(
   Id_Ligne_commande   INT AUTO_INCREMENT,
   Id_Commande         INT NOT NULL,
   Id_Article          INT NOT NULL,
   Quantité_Commandée  INT NOT NULL,
   CONSTRAINT ligne_commande_id_pk PRIMARY KEY(Id_Ligne_commande),
   CONSTRAINT ligne_commande_id_commande_fk FOREIGN KEY(Id_Commande) REFERENCES Commande(Id_Commande),
   CONSTRAINT ligne_commande_id_article_fk FOREIGN KEY(Id_Article) REFERENCES Article(Id_Article)
) ENGINE=InnoDB;

CREATE TABLE Historique(
   Id_Historique       INT AUTO_INCREMENT,
   action              VARCHAR(50),
   date_action         DATETIME,
   CONSTRAINT historique_id_pk PRIMARY KEY(Id_Historique)
) ENGINE=InnoDB;

CREATE TABLE Employe (
    Id_Employe 			INT AUTO_INCREMENT,
    Nom 				VARCHAR(100),
    Prenom 				VARCHAR(100),
    Num_telephone 		INT,
    Poste 				VARCHAR(100),
    PRIMARY KEY(Id_Employe)
) ENGINE=InnoDB;

CREATE TABLE Utilisateur (
    Id_Utilisateur 		INT AUTO_INCREMENT,
    Nom_utilisateur 	VARCHAR(100),
    Mot_de_passe 		VARCHAR(100),
	Token 				VARCHAR(255),
    Droit 				VARCHAR(50),
    PRIMARY KEY(Id_Utilisateur)
) ENGINE=InnoDB;

CREATE TABLE reinitialisation (
    id 					INT AUTO_INCREMENT PRIMARY KEY,
    nom 				VARCHAR(255) NOT NULL,
    email 				VARCHAR(255) NOT NULL,
    message 			TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



---procedures stockées et triggers--------------------------------------------------------------------------------------------------------------------------------

---procedures stockées
----------------------------------------------------------------------------------------
-- Procédure stockée pour ajouter un client avec une adresse

DELIMITER //
CREATE PROCEDURE AddAdresseClient (
   IN p_Nom VARCHAR(50),
   IN p_Prenom VARCHAR(50),
   IN p_NumTelephone INT,
   IN p_Rue VARCHAR(50),
   IN p_Numero VARCHAR(50),
   IN p_BP VARCHAR(50),
   IN p_Ville VARCHAR(50)
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
DELIMITER ;


-- procedure stockée pour passer une commande

DELIMITER //
CREATE PROCEDURE PasserCommande(
   IN p_Id_Client INT,
   IN p_Id_Article INT,
   IN p_Quantité_Commandée INT,
   OUT p_Id_Commande INT
)
BEGIN
   DECLARE v_Id_Facture INT;

   -- Création d'une nouvelle facture
   INSERT INTO Facture (date_facturation, montant, garantie, Id_Paiement)
   VALUES (CURDATE(), 0, FALSE, NULL);

   -- Récupération de l'ID de la dernière facture créée
   SET v_Id_Facture = LAST_INSERT_ID();

   -- Insertion d'une nouvelle commande
   INSERT INTO Commande (date_commande, statut, Quantité_Demandée, Id_Facture)
   VALUES (CURDATE(), 'En cours', p_Quantité_Commandée, v_Id_Facture);

   -- Récupération de l'ID de la dernière commande créée
   SET p_Id_Commande = LAST_INSERT_ID();

   -- Insertion de la ligne de commande
   INSERT INTO Ligne_commande (Id_Commande, Id_Article, Quantité_Commandée)
   VALUES (p_Id_Commande, p_Id_Article, p_Quantité_Commandée);

   -- Décrémentation de la quantité disponible de l'article
   UPDATE Article
   SET Quantité_Disponible = Quantité_Disponible - p_Quantité_Commandée
   WHERE Id_Article = p_Id_Article;

   -- Insertion dans l'historique
   INSERT INTO Historique (action, date_action)
   VALUES ('Passer une commande', NOW());

   -- Si nécessaire, vous pouvez ajouter d'autres actions ou validations ici

END //
DELIMITER ;


-- procedure stockée pour verifier l'existence d'une commande

DELIMITER //
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
DELIMITER ;


-- procédure stockée pour la facturation et le paiement

DELIMITER //
CREATE PROCEDURE FacturerEtPayer(
   IN montantFacture INT,
   IN modePaiement VARCHAR(50),
   OUT idFacture INT,
   OUT idPaiement INT
)
BEGIN
   DECLARE idArticle INT;
   DECLARE idCommande INT;
   DECLARE exit handler for sqlexception
   BEGIN
      ROLLBACK;
      SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Une erreur s est produite lors de la facturation et du paiement.';
   END;
   
   START TRANSACTION;
   
   -- Insértion de la facture------------------
   INSERT INTO Facture (date_facturation, montant, garantie)
   VALUES (CURDATE(), montantFacture, 'Non');
   
   -- Récupérer l'ID de la facture insérée----
   SET idFacture = LAST_INSERT_ID();
   
   -- Insérer le paiement-------------------
   INSERT INTO Paiement (date_paiement, montant, mode_paiement, statut)
   VALUES (NOW(), montantFacture, modePaiement, 'En attente');
   
   -- Récupérer l'ID du paiement inséré
   SET idPaiement = LAST_INSERT_ID();
   
   -- Insérer la commande associée à la facture------
   INSERT INTO Commande (date_commande, statut, Quantité_Demandée, Id_Facture)
   VALUES (CURDATE(), 'En attente', 1, idFacture);
   
   -- Récupérer l'ID de la commande insérée-------------------
   SET idCommande = LAST_INSERT_ID();
   
   -- Insérer la ligne de commande associée à la commande-----------------
   INSERT INTO Ligne_commande (Id_Commande, Id_Article, Quantité_Commandée)
   VALUES (idCommande, idArticle, 1);
   
   -- Mettre à jour la quantité disponible de l'article--------------
   UPDATE Article SET Quantité_Disponible = Quantité_Disponible - 1 WHERE Id_Article = idArticle;
   
   -- Mettre à jour le statut de l'article------------------
   UPDATE Article SET statut = 'Vendu' WHERE Id_Article = idArticle;
   
   -- Mettre à jour le statut de la commande-----------------
   UPDATE Commande SET statut = 'Terminée' WHERE Id_Commande = idCommande;
   
   -- Mettre à jour le statut du paiement------------
   UPDATE Paiement SET statut = 'Payé' WHERE Id_Paiement = idPaiement;
   
   -- Insérer l'action dans l'historique------------
   INSERT INTO Historique (action, date_action)
   VALUES ('Facturation et paiement effectués', NOW());
   
   COMMIT;
END //
DELIMITER ;



-- procedure stockée pour afficher une facture
DELIMITER //
CREATE PROCEDURE AfficherFacture(
   IN idFacture INT
)
BEGIN
   DECLARE factureDate DATE;
   DECLARE factureMontant INT;
   DECLARE factureGarantie VARCHAR(50);
   DECLARE paiementDate DATETIME;
   DECLARE paiementMontant INT;
   DECLARE paiementMode VARCHAR(50);
   DECLARE commandeDate DATE;
   DECLARE commandeStatut VARCHAR(50);
   DECLARE articleNom VARCHAR(50);
   DECLARE articleStatut VARCHAR(50);
   DECLARE ligneQuantite INT;
   
   SELECT date_facturation, montant, garantie INTO factureDate, factureMontant, factureGarantie
   FROM Facture
   WHERE Id_Facture = idFacture;
   
   SELECT date_paiement, montant, mode_paiement INTO paiementDate, paiementMontant, paiementMode
   FROM Paiement
   WHERE Id_Paiement = (SELECT Id_Paiement FROM Facture WHERE Id_Facture = idFacture);
   
   SELECT date_commande, statut INTO commandeDate, commandeStatut
   FROM Commande
   WHERE Id_Facture = idFacture;
   
   SELECT Article.nom, Article.statut, Ligne_commande.Quantité_Commandée INTO articleNom, articleStatut, ligneQuantite
   FROM Article
   JOIN Ligne_commande ON Article.Id_Article = Ligne_commande.Id_Article
   WHERE Ligne_commande.Id_Commande = (SELECT Id_Commande FROM Commande WHERE Id_Facture = idFacture);
   
   -- Afficher les informations de la facture --
   SELECT 'Facture' AS Type, idFacture AS ID, factureDate AS Date, factureMontant AS Montant, factureGarantie AS Garantie;
   
   -- Afficher les informations de paiement --
   SELECT 'Paiement' AS Type, (SELECT Id_Paiement FROM Facture WHERE Id_Facture = idFacture) AS ID, paiementDate AS Date, paiementMontant AS Montant, paiementMode AS Mode;
   
   -- Afficher les informations de la commande --
   SELECT 'Commande' AS Type, (SELECT Id_Commande FROM Commande WHERE Id_Facture = idFacture) AS ID, commandeDate AS Date, commandeStatut AS Statut;
   
   -- Afficher les informations de l'article --
   SELECT 'Article' AS Type, (SELECT Id_Article FROM Ligne_commande WHERE Id_Commande = (SELECT Id_Commande FROM Commande WHERE Id_Facture = idFacture)) AS ID, articleNom AS Nom, articleStatut AS Statut, ligneQuantite AS Quantite;
END //
DELIMITER ;


--- procedure stockée pour afficher les lignes de commandes---
DELIMITER //

CREATE PROCEDURE AfficherLignesCommande()
BEGIN
   DECLARE done INT DEFAULT FALSE;
   DECLARE id_commande INT;
   DECLARE id_article INT;
   DECLARE quantite_commandee INT;
   DECLARE cur CURSOR FOR SELECT Id_Commande, Id_Article, Quantité_Commandée FROM Ligne_commande;
   DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

   OPEN cur;

   read_loop: LOOP
      FETCH cur INTO id_commande, id_article, quantite_commandee;
      IF done THEN
         LEAVE read_loop;
      END IF;

      -- Afficher les informations de la ligne de commande
      SELECT CONCAT('ID Commande: ', id_commande) AS `Commande`,
             CONCAT('ID Article: ', id_article) AS `Article`,
             CONCAT('Quantité Commandée: ', quantite_commandee) AS `Quantité`;

   END LOOP;

   CLOSE cur;
END //

DELIMITER ;

---Déclencheurs/TRIGGERS--------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------

-- Déclencheur pour mettre à jour la quantité d'un article après une commande (a modifier : suppression des selects)

DELIMITER //
CREATE TRIGGER update_stock AFTER INSERT ON Ligne_commande
FOR EACH ROW
BEGIN
    DECLARE article_id INT;
    DECLARE quantite_commandee INT;
    DECLARE quantite_disponible INT;

    -- Récupérer l'ID de l'article et la quantité commandée
    SELECT Id_Article, Quantité_Commandée INTO article_id, quantite_commandee
    FROM Ligne_commande
    WHERE Id_Ligne_commande = NEW.Id_Ligne_commande;

    -- Récupérer la quantité disponible de l'article
    SELECT Quantité_Disponible INTO quantite_disponible
    FROM Article
    WHERE Id_Article = article_id;

    -- Mettre à jour la quantité disponible de l'article
    SET quantite_disponible = quantite_disponible - quantite_commandee;

    -- Mettre à jour la table Article
    UPDATE Article
    SET Quantité_Disponible = quantite_disponible
    WHERE Id_Article = article_id;
END //
DELIMITER ;


-- declencheur d' historisation à chaque fois qu'une nouvelle ligne est insérée dans les tables Adresse, Client ou Article
DELIMITER //
CREATE TRIGGER historisation_adresse
AFTER INSERT ON Adresse
FOR EACH ROW
BEGIN
    INSERT INTO Historique (action, date_action)
    VALUES ('Insertion Adresse', NOW());
END //

CREATE TRIGGER historisation_client
AFTER INSERT ON Client
FOR EACH ROW
BEGIN
    INSERT INTO Historique (action, date_action)
    VALUES ('Insertion Client', NOW());
END //

CREATE TRIGGER historisation_article
AFTER INSERT ON Article
FOR EACH ROW
BEGIN
    INSERT INTO Historique (action, date_action)
    VALUES ('Insertion Article', NOW());
END //

CREATE TRIGGER historisation_vente
AFTER INSERT ON Commande
FOR EACH ROW
BEGIN
    INSERT INTO Historique (action, date_action)
    VALUES ('Nouvelle vente', NOW());
END //
DELIMITER ;

