package ORM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ManagerDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/campNow";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root";

    private static Connection instance = null;

    private static Connection createConnection() throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e){
            System.out.println("Driver JDBC non trovato!");
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static Connection getConnection() throws SQLException {
        if(instance==null){
            instance = createConnection();
        }
        return instance;
    }
}
