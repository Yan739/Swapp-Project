import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnector {
    private static Connection connect;

    private String url = ${JDBC_URL}
    private String user = ${DB_USER};
    private String password = ${DB_PASSWORD};

    private DataBaseConnector(){
        try {

            connect = DriverManager.getConnection(url, user, password);

        }catch (SQLException e){
            System.out.printf("Erreur de connection ! \nNous avons rencontrer cette érreur : %s", e);
        }
    }

    public static Connection getInstance(){
        if (connect == null){
            new DataBaseConnector();
        }
    }
}