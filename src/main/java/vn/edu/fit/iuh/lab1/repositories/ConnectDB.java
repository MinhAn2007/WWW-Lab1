package vn.edu.fit.iuh.lab1.repositories;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private Connection connection;
    private static ConnectDB instance = null;
    public ConnectDB() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");
        String url = "jdbc:mariadb://localhost:3306/mydb?createDatabaseIfNotExist=true";
        connection = DriverManager.getConnection(url, "root", "sapassword");
    }
    public Connection getConnection(){
        return connection;
    }
    public static ConnectDB getInstance() throws SQLException, ClassNotFoundException {
        if(instance == null) {
            instance = new ConnectDB();
            System.out.println("connect!!!");
        }
        return instance;
    }
}