/*
 * Created on 23 août 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package metier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.ClasseDatabean;

/**
 * @author Jerome
 *
 * Cette classe permet la manipulation de la table Classe
 */
public class Classe extends GenericMetier {

    /**
     * @throws SQLException
     * 
     */
    public Classe() throws SQLException {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Retourne un element de Classe, par son ID.
     * @param id
     * @return ClasseDatabean ou null si pas trouvé
     * @throws SQLException
     */
    public ClasseDatabean getClasseById(int id) throws SQLException
    {
        ResultSet rs = executeQuery("select classe_nom, instituteur from classe where id='"+id+"'");
        ClasseDatabean c = new ClasseDatabean();
        if (rs.next())
        {      
	        c.setId(id);
	        c.setClasse_nom(rs.getString("classe_nom"));
	        c.setInstituteur(rs.getString("instituteur"));
	        rs.close();
        }
        else
        {
            return null;
        }            
        return c;
    }
    
    /**
     * Retourne tout le contenu de Classe, non trié, dans une List de ClasseDatabean
     * @return List de ClasseDatabean
     * @throws SQLException
     */
    public List getAllClasses() throws SQLException {
        ResultSet rs = executeQuery("select id, classe_nom, instituteur from classe");
        List list = new ArrayList();
        while (rs.next())
        {
            ClasseDatabean c = populate(rs);
            list.add(c);
        }
        rs.close();
        return list;
    }
    
    /**
     * Retourne les element de la table Classe qui
     * ont l'instituteur demandé
     * @param instit
     * @return List de ClasseDatabean
     * @throws SQLException
     */
    public List getByInstituteur(String instit) throws SQLException
    {
        ResultSet rs = executeQuery("select id, classe_nom, instituteur from classe " +
        		"where instituteur='"+instit+"'");
        List list = new ArrayList();
        while (rs.next())
        {
            ClasseDatabean c = populate(rs);
            list.add(c);
        }
        rs.close();
        return list;
       
    }

    /**
     * Insertion d'un objet ClasseDatabean
     * @param classe /!\ met a jour l'ID 
     * @throws SQLException
     */
    public void insertClasse(ClasseDatabean classe) throws SQLException
    {
        PreparedStatement pst = prepareStatementAutoInc(
                "insert into classe (classe_nom, instituteur) values " +
                " ( ?, ?)");
        pst.setString(1, classe.getClasse_nom());
        pst.setString(2, classe.getInstituteur());
        pst.executeUpdate(); 
        classe.setId(getAutoincrement(pst));
        pst.close();
    }
    
    /**
     * Creer un objet classe, et rempli les champs
     * avec les champs retournée dans la reqeute.
     * Les champs doivent etre nommé id, classe_nom, instituteur
     * @param rs
     * @throws SQLException
     */
    private ClasseDatabean populate(ResultSet rs) throws SQLException 
    {
        ClasseDatabean c = new ClasseDatabean(
                rs.getInt("id"),
                rs.getString("classe_nom"),
                rs.getString("instituteur"));
        return c;
    }
}
