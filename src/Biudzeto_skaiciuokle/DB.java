package Biudzeto_skaiciuokle;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DB {

    public static void main(String[] args) {
        getConnection();
    }

    public static Connection getConnection(){
        try {
            Properties pr = new Properties();
            InputStream input = new FileInputStream("config.properties");
            pr.load(input);
            
            Class.forName("com.mysql.jdbc.Driver");
            String DB_URL = "jdbc:mysql://localhost/" + pr.getProperty("databaseName");
            String USER = pr.getProperty("login");
            String PASS = pr.getProperty("pass");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            return conn;                 
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }         
    }    
}
