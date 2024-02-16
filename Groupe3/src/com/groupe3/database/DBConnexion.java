package com.groupe3.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.groupe3.database.Env.*;

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
