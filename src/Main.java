import com.swapp.controller.LoginController;
import com.swapp.view.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginController loginController = new LoginController();

        DashboardView dashboardView = new DashboardView();
        VenteView venteView = new VenteView();
        AchatMatFournisseurView achatView = new AchatMatFournisseurView();
        ReparationView reparationView = new ReparationView();
        UtilisateurView utilisateurView = new UtilisateurView();

        System.out.println("******************************************");
        System.out.println("* BIENVENUE SUR SWAPP           *");
        System.out.println("******************************************");

        boolean connecte = false;
        while (!connecte) {
            System.out.print("\nUtilisateur : ");
            String login = scanner.nextLine();
            System.out.print("Mot de passe : ");
            String pass = scanner.nextLine();

            if (loginController.authentifier(login, pass)) {
                connecte = true;
                System.out.println("\n[OK] Connexion réussie !");
            } else {
                System.out.println("\n[ERREUR] Identifiants incorrects. Réessayez.");
            }
        }

        boolean execution = true;
        while (execution) {
            dashboardView.afficher(scanner);
            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    achatView.afficherMenu(scanner);
                    break;
                case "2":
                    venteView.nouvelleVente(scanner);
                    break;
                case "3":
                    reparationView.afficherMenu(scanner);
                    break;
                case "4":
                    utilisateurView.afficherGestion(scanner);
                    break;
                case "0":
                    System.out.println("\nDéconnexion en cours... Au revoir !");
                    execution = false;
                    break;
                default:
                    System.out.println("\n[!] Choix invalide, veuillez recommencer.");
            }
        }
        scanner.close();
    }
}