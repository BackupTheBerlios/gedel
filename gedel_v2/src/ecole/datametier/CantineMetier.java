/*
 * Created on 24 sept. 2004
 *
 * 
 */
package ecole.datametier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ecole.databean.CantineDatabean;
import ecole.databean.GenericDatabean;
import ecole.db.DBTools;

/**
 * @author jemore
 */
public class CantineMetier extends GenericMetier
{

    /**
     * @throws SQLException
     */
    public CantineMetier()
    {
        super();
    }

    /* (non-Javadoc)
     * @see ecole.datametier.GenericMetier#populateAllField(java.sql.ResultSet)
     */
    protected GenericDatabean populateAllField(ResultSet rs) throws SQLException
    {
        throw new SQLException("Cette methode n'est pas implémentée");
    }

    /* (non-Javadoc)
     * @see ecole.datametier.GenericMetier#getAll()
     */
    public List getAll() throws SQLException
    {
        throw new SQLException("Cette methode n'est pas implémentée");
    }

    /**
     * Ajout ou update de l'affectation a la cantine
     * @param c
     * @author JFORESTIER
     */
    public void ajouter(CantineDatabean c) throws SQLException
    {
        String q = "";
        PreparedStatement pst;
        // Cherche si l'eleve a deja un tarif d'affecter        
        CantineDatabean ancien = getCantineForEleve(c.getEleve_id());
        if (null == ancien)
        {
            // Premiere affectation, insert
            q = "insert into cantine set eleve_id=?, prixname=?, prix=?, datevalidite=?, nbrjours=?"; 
            pst = prepareStatement(q);
            pst.setInt(1, c.getEleve_id());
            pst.setString(2, c.getPrixname());
            pst.setDouble(3, c.getPrix());
            pst.setDate(4, DBTools.JavaDateToSqlDate(c.getDatevalidite()));
            pst.setInt(5, c.getNbrjours());            
        }
        else
        {
            q = "update cantine set prixname=?, prix=?, datevalidite=?, nbrjours=? where eleve_id=?";
            pst = prepareStatement(q);
            pst.setString(1, c.getPrixname());
            pst.setDouble(2, c.getPrix());
            pst.setDate(3, DBTools.JavaDateToSqlDate(c.getDatevalidite()));
            pst.setInt(4, c.getNbrjours());            
            pst.setInt(5, c.getEleve_id());
        }
        pst.execute();
        pst.close();
    }

    /**
     * @param c
     * @author jemore
     */
    public void retirer(CantineDatabean c) throws SQLException
    {
        PreparedStatement pst = prepareStatement("delete from cantine where eleve_id=?");
        pst.setInt(1, c.getEleve_id());
        pst.execute();
    }
    
    /**
     * 
     * @param eleve_id
     * @return le bean ou null si n'est pas dans la table
     * @throws SQLException
     * @author jemore
     */
    public CantineDatabean getCantineForEleve(int eleve_id) throws SQLException
    {
        CantineDatabean c = new CantineDatabean();
        String q = "select * from cantine where eleve_id = ?";
        PreparedStatement pst = prepareStatement(q);
        pst.setInt(1, eleve_id);
        ResultSet rs = pst.executeQuery();
        if (rs.next())
        {
            c.setAffecte(true);
            c.setDatevalidite(rs.getDate("datevalidite"));
            c.setEleve_id(rs.getInt("eleve_id"));
            c.setNbrjours(rs.getInt("nbrjours"));
            c.setPrix(rs.getDouble("prix"));
            c.setPrixname(rs.getString("prixname"));
        }
        else
            c = null;
        rs.close();
        pst.close();
        return c;       
    }

}
