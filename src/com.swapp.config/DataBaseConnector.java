import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnector {
    private static Connection connect;
    Dotenv dotenv = Dotenv.load();

    private String url = dotenv.get("JDBC_URL");
    private String user = dotenv.get("DB_USER");
    private String password = dotenv.get("DB_PASSWORD");

    private DataBaseConnector(){
        try {

            connect = DriverManager.getConnection(url, user, password);

        }catch (SQLException e){
            System.out.printf("Erreur de connection ! \nNous avons rencontrer cette érreur : %s", e);
        }
    }

    public Connection getInstance(){
        if (connect == null){
            new DataBaseConnector();
            System.out.println("Connexion à MySQL établie !");
        }
        return connect;
    }
}