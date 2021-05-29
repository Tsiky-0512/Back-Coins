package databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectDB {
	public static Connection connectToDb() throws SQLException {
		Connection con=null;
        String url = "jdbc:postgresql://localhost/criptomonnaie";
        Properties props = new Properties();
        props.setProperty("user","postgres");
        props.setProperty("password","2048");
        try{
            con = DriverManager.getConnection(url, props);
            System.out.println("Java JDBC PostgreSQL Example");
            System.out.println("Connected to PostgreSQL database!");
        }catch (SQLException e) {
            System.out.println("Connection failure.");
        }
        return con;
	}
}
