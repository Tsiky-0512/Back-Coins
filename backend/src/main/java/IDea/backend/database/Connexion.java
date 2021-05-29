package IDea.backend.database;

import java.sql.*;
import java.util.Properties;

public class Connexion {

    public static Connection Connection() throws SQLException, ClassNotFoundException {
    	try {
        	String url = "localhost";
            Connection conn;

            String pilote = "jdbc:postgresql://localhost:5432/cryptomonaie";
            
            Properties props = new Properties();
            props.setProperty("user","cryptomonaie");
            props.setProperty("password","123456");
//            props.setProperty("ssl","true");

            conn = (Connection) DriverManager.getConnection(pilote,props);
            return conn;
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
}
