/*
 * Created on 23 ao�t 2004
 *
 */
package db;

import java.sql.*;

/**
 * @author Jerome
 * 
 * Objet assurant la connection � la base
 * @pattern : singleton  
 */
public class DavidSchoolConnection {
    /**
     * Instance du singleton, cr�er lors de la 1ere utilisation
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
     * Consutructeur priv�
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
     * Si la connexion est ferm�, on la r�ouvre.
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
                    System.err.println("Impossible de se connecter � la base");
                    e.printStackTrace();
                    return null;
                }
                System.out.println("Connexion � la base OK ");
        }
        try 
        {
	        if (conn.isClosed())
	        {
	            System.out.println("Connexion � la base perdue");
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
     * Ferme la connexion � la base
     *
     */
    public void closeConnection()
    {
        try {
            conn.close();
            System.out.println("Connexion � la base ferm�e.");
        } catch (SQLException e) {
            System.err.println("Impossible de fermer la connexion � la base");
            e.printStackTrace();
        }
        conn = null;
    }
}