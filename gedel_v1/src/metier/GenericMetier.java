/*
 * Created on 23 août 2004
 *
 */
package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DavidSchoolConnection;

/**
 * @author Jerome
 *
 * Cette classe s'occupe de la connection a la base 
 * et du débugage.
 */
public class GenericMetier {

    
    /**
     * @throws SQLException
     * 
     */    
    public GenericMetier() throws SQLException {
        super();
    }
    
    /**
     * 
     * @param query
     */
    private void debug(String query)
    {
        System.out.println("q:>"+query);
    }

    /**
     * Retourne la connection à la base a partir du singleton
     * @return
     */
    protected Connection getConnection()
    {
        return DavidSchoolConnection.getInstance().getConnection();
    }
    
    /**
     * Execute une requete Select
     * @param query
     * @return ResultSet
     * @throws SQLException
     */
    protected ResultSet executeQuery(String query) throws SQLException
    {
        debug(query);
        return getConnection().createStatement().executeQuery(query);
    }
    
    /**
     * Execute une requete insert
     * @param query
     * @return nb de données inséré
     * @throws SQLException
     */
    protected int executeInsert(String query) throws SQLException
    {
        debug(query);
        return getConnection().createStatement().executeUpdate(query);
    }   
    
    /**
     * Retourne un PreparedStatement
     * @param query
     * @return PreparedStatement
     * @throws SQLException
     */
    protected PreparedStatement prepareStatement(String query) throws SQLException
    {
        debug(query);
        return getConnection().prepareStatement(query);
    }
    
    /**
     * Retourne un PreparedStatement avec lequel ont peut connaitre l'id autoincrement
     * @param query
     * @param st_param
     * @throws SQLException
     */
    protected PreparedStatement prepareStatementAutoInc(String query) throws SQLException
    {
        debug(query);
        return getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
    }
    
    /**
     * Retourne un id d'autoincrement, si le pst a été créer
     * avec prepareStatementAutoInc
     * @param pst
     * @return
     * @throws SQLException
     * @see prepareStatementAutoInc
     * @see http://dev.mysql.com/tech-resources/articles/autoincrement-with-connectorj.html
     */
    protected int getAutoincrement(PreparedStatement pst) throws SQLException
    {
        int autoIncKeyFromApi = -1;
        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            autoIncKeyFromApi = rs.getInt(1);
        } else {
            // throw an exception from here
        }
        rs.close();
        return autoIncKeyFromApi;
    }
}
