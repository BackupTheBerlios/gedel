/*
 * Created on 23 août 2004
 *
 */
package db;

import java.sql.*;

/**
 * @author Jerome
 * 
 * Objet assurant la connection à la base
 * @pattern : singleton  
 */
public class DavidSchoolConnection {
    /**
     * Instance du singleton, créer lors de la 1ere utilisation
     */
    private static DavidSchoolConnection instance = new DavidSchoolConnection();

    /**
     * Retourne l'instance du singleton 
     * @return
     */
    static public DavidSchoolConnection getInstance() {
        return instance;
    }
    
    /**
     * Consutructeur privé
     *
     */
    private DavidSchoolConnection()
    {    
    }

    /**
     * Objet connection
     */
    private Connection conn = null;

    /**
     * Retourne la connexion en cours.
     * Si la connexion est fermé, on la réouvre.
     * @return
     */
    public Connection getConnection() {
        if (null == conn) // Doublecheck locking here ?
        {
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                } catch (Exception e) {
                    System.err.println("Impossible d'instancier le driver JDBC");
                    e.printStackTrace();
                    return null;
                }
                try {
                    conn = DriverManager.getConnection(
                            "jdbc:mysql:///ecole",
                            "root", 
                            "");
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    System.err.println("Impossible de se connecter à la base");
                    e.printStackTrace();
                    return null;
                }
                System.out.println("Connexion à la base OK ");
        }
        try 
        {
	        if (conn.isClosed())
	        {
	            System.out.println("Connexion à la base perdue");
	        }
        }
        catch( SQLException e)
        {
            e.printStackTrace();
            return null;
        }       
        return conn;
    }
    
    /**
     * Ferme la connexion à la base
     *
     */
    public void closeConnection()
    {
        try {
            conn.close();
            System.out.println("Connexion à la base fermée.");
        } catch (SQLException e) {
            System.err.println("Impossible de fermer la connexion à la base");
            e.printStackTrace();
        }
        conn = null;
    }
}