package com.groupe3.user;

import com.groupe3.user.User;

import java.sql.*;
import java.util.ArrayList;

public class UserManager {
    private static Connection connexion = com.address.model.DBConnexion.getConnexion();

    /**----------------------------
     Ajouter un utilisateur
     -----------------------------*/
    public static User addUser(User user){
        //instancier un Objet User null
        User userAdd = new User();
        try{
            //Connection à la BDD...
            Statement stmt = connexion.createStatement();

            //requête SQL
            String sql = "INSERT INTO users (nom, prenom, email, password) " + "VALUES (?, ?, ?, ?)";

            //Préparation de la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);

            //Bind des paramètres
            preparedStatement.setString(1, user.getNom());
            preparedStatement.setString(2, user.getPrenom());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());

            //Exécution de la requête
            int addedRows = preparedStatement.executeUpdate();

            //test si l'enregistrement est ok
            if (addedRows > 0) {
                //Création d'un Objet User
                userAdd.setNom(user.getNom());
                userAdd.setPrenom(user.getPrenom());
                userAdd.setEmail(user.getEmail());
                userAdd.setPassword(user.getPassword());
            }
            //fermeture de la connexion BDD
            stmt.close();
//                connexion.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        //Retourne un Objet User
        return userAdd;
    }
    /**----------------------------
     Trouver un user
     -----------------------------*/
    public static User findUserByEmail(User user){
        //instancier un Objet User null
        User userByEmail = new User();
        try{
            //Connection à la BDD...
            Statement stmt = connexion.createStatement();

            //requête SQL
            String sql = "SELECT id, nom, prenom, email, password FROM users WHERE email= ?";

            //Préparation de la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);

            //Bind des paramètres
            preparedStatement.setString(1, user.getEmail());

            //Résultat de la requête
            ResultSet rs = preparedStatement.executeQuery();

            //boucler sur le résultat
            while (rs.next()){
                //test si la colonne id a une valeur
                if (rs.getString("nom") != null){
                    userByEmail.setId(rs.getInt(1));
                    userByEmail.setNom(rs.getString("nom"));
                    userByEmail.setPrenom(rs.getString("prenom"));
                    userByEmail.setEmail(rs.getString("email"));
                    userByEmail.setPassword(rs.getString("password"));
                }
            }
            //fermeture de la connexion BDD
            stmt.close();
//            connexion.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        //Retourne un Objet User
        return userByEmail;
    }


    /**----------------------------
     update un user
     -----------------------------*/
    public static User updateUser(User user){
        User userUpdate = new User();
        try{
            //Connection à la BDD...
            Statement stmt = connexion.createStatement();

            //requête
            String sql = "UPDATE users SET nom = ?, prenom = ? WHERE email = ?";

            //Préparation de la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);

            //Bind des paramètres
            preparedStatement.setString(1, user.getNom());
            preparedStatement.setString(2, user.getPrenom());
            preparedStatement.setString(3, user.getEmail());

            int nbRow = preparedStatement.executeUpdate();

            if(nbRow>0){
                userUpdate.setNom(user.getNom());
                userUpdate.setPrenom(user.getPrenom());
                userUpdate.setEmail(user.getEmail());
                userUpdate.setPassword(user.getPassword());
            }

            //fermeture de la connexion BDD
            stmt.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return userUpdate;
    }
    /**----------------------------
     Afficher liste des user
     Créer une nouvelle méthode static et public getAllUser qui va :
     avoir une sortie au format arrayList (User),
     Elle va effectuer une requête de consultation (SELECT) et renvoyer une ArrayList d'objet User.
     Afficher le contenu de la liste dans le terminal (boucle for)
     -----------------------------*/
    public static ArrayList<User> getAllUser(){
        ArrayList<User> allUsers = new ArrayList<>();

        try{
            //Connection à la BDD...
            Statement stmt = connexion.createStatement();

            //requête
            String sql = "SELECT id, nom, prenom, email, password FROM users";
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);

            // Résultat de la requête
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                User userSeul = new User();
                userSeul.setId(rs.getInt(1));
                userSeul.setNom(rs.getString("nom"));
                userSeul.setPrenom(rs.getString("prenom"));
                userSeul.setEmail(rs.getString("email"));
                userSeul.setPassword(rs.getString("password"));
                allUsers.add(userSeul);
            }
            //fermeture de la connexion BDD
            stmt.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return allUsers;
    }
}

