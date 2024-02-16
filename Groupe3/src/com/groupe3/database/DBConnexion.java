package com.address.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.address.model.Env.*;

public class DBConnexion {
    //Connexion à la BDD
    private static Connection connexion;
    static {
        try {
            connexion = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**----------------------------
     Connexion
     -----------------------------*/
    public static Connection getConnexion(){return connexion;}
}
