import java.sql.Connection;

public class DataBaseConnector {
    private static Connection connect;

    private String url = ${JDBC_URL}
    private String name = ${DB_USER};
    private String password = ${DB_PASSWORD};

    private DataBaseConnector(){
        try {
            connect = DriverManager.getConnection();
        }
    }

    public static Connection getInstance(){
        if (connect == null){

        }
    }
}