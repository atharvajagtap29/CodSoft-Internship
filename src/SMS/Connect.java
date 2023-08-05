package SMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Connect {

    private Connection connection;
    private Statement statement;

    public Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:///studentSystem", "root", "root");

            if (connection == null) {
                System.out.println("Not connected successfully");
            } else {
                System.out.println("Database connected successfully.");
            }

            statement = connection.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Statement getStatement() {
        return statement;
    }

    public Connection getConnection() {
        return connection;
    }
    
    public static void main(String[] args) {
		new Connect();  // testing my connection
	}
}
