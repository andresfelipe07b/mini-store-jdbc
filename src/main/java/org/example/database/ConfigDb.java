package org.example.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConfigDb {
    public static Connection objConnection = null;
    private static final String url;
    private static final String user;
    private static final String password;
    private static final String driver;


    static {
        try (InputStream input = ConfigDb.class.getResourceAsStream("/db.properties")) {
            if (input == null) {
                throw new RuntimeException("The file db.properties is missing");
            }

            Properties props = new Properties();
            props.load(input);

            driver = props.getProperty("driver");
            url = props.getProperty("url");
            user = props.getProperty("user");
            password = props.getProperty("password");

            Class.forName(driver);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error cargando configuración de la BD", e);
        }
    }
    public static Connection getConnection() {
        try {
            objConnection = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("connected successfully");

        }catch (SQLException error) {
            System.out.println("Connection error: " + error.getMessage());
        }
        return objConnection;
    }
//    public static void closeConnection(){
//        try{
//            if(objConnection != null) {
//                objConnection.close();
//                objConnection = null;
//                System.out.println("Connection closed successfully");
//            }
//
//        }catch (SQLException error){
//            System.out.println("Error closing connection: " + error.getMessage());
//        }
//    }
}
