/*
 * Created on 5 sept. 2004
 *
 */
package ecole.datametier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import ecole.databean.ClasseDatabean;
import ecole.databean.DatabeanGeneric;

/**
 * @author jemore @ home
 *
 */
public class ClassesMetier extends MetierGeneric
{

	public ClassesMetier()
	{
		super();
	}

	/* (non-Javadoc)
	 * @see ecole.datametier.GenericMetier#populateAllField(java.sql.ResultSet)
	 */
	protected DatabeanGeneric populateAllField(ResultSet rs) throws SQLException
	{
		ClasseDatabean c = new ClasseDatabean();
		c.setId(rs.getInt("id"));
		c.setClasse_nom(rs.getString("classe_nom"));
		c.setInstituteur(rs.getString("instituteur"));
		return (DatabeanGeneric) c;
	}

	/* (non-Javadoc)
	 * @see ecole.datametier.GenericMetier#getAll()
	 */
	public List getAll() throws SQLException
	{
		ResultSet rs = executeQuery("select * from classe order by id");
		List list = getResults(rs);
		logInfo(list.size() + " classes chargées");
		rs.close();
		return list;
	}

	public static final String getClasseNom(ClasseDatabean c)
	{
		return c.getClasse_nom();
	}

	/**
	 * Recherche dans <code>listClassesDisponibles</code> la classe.
	 * Retourne le n°d'ordre
	 * @param listClassesDisponibles une liste de ClasseDatabean
	 * @param classeid l'id de la classe a chercher
	 * @return la position (l'index) dans la liste. -1 si pas trouvé
	 * @author jemore @ home
	 */
	public int getIdxOfClasseInList(List listClassesDisponibles, int classeid)
	{
		ClasseDatabean c;
		int idx = 0;
		Iterator i = listClassesDisponibles.iterator();
		while(i.hasNext())
		{
			c = (ClasseDatabean)i.next();
			if (c.getId() == classeid)
				return idx;
			idx++;
		}
		return -1;
	}

    /**
     * @param c
     * @author jemore @ home
     */
    public void insert(ClasseDatabean c) throws SQLException
    {
        PreparedStatement pst = prepareStatementAutoInc("insert into classe set " +
            " classe_nom=?, instituteur=?");
        int i = 1;
        pst.setString(i++, c.getClasse_nom());
        pst.setString(i++, c.getInstituteur());
        
        pst.executeUpdate();
        
        c.setId(getAutoincrement(pst));
        pst.close();
        
    }

    /**
     * @param nouvel_classe
     * @author jemore @ home
     */
    public void update(ClasseDatabean c) throws SQLException
    {
        PreparedStatement pst = prepareStatementAutoInc("update classe set" +            " classe_nom=?, instituteur=?" +            " where id=?");
        int i = 1;
        pst.setString(i++, c.getClasse_nom());
        pst.setString(i++, c.getInstituteur());
        pst.setInt(i++, c.getId());
        
        pst.executeUpdate();
        
        pst.close();
        
    }
    
       /**
     * @param i
     * @author jemore @ home
     */
    public void delete(int classeid) throws SQLException
    {
        PreparedStatement pst = prepareStatement("delete from classe where id=?");
        pst.setInt(1, classeid);
        pst.execute();
    }

	/**
	 * Retourne un ClasseDatabean correspondant au classe id. Null si n'existe pas
	 * @param classe_id id de la classe
	 * @return ClasseDatabean ou null
	 * @author jemore @ home
	 */
	public ClasseDatabean getClasseByClasseId(int classe_id) throws SQLException
	{
		ClasseDatabean c = null;
		PreparedStatement pst = prepareStatement("select * from classe where id=?");
		pst.setInt(1, classe_id);
		ResultSet rs = pst.executeQuery();
		if (rs.next())
		{
			c = new ClasseDatabean();
			c.setClasse_nom(rs.getString("classe_nom"));
			c.setId(rs.getInt("id"));
			c.setInstituteur(rs.getString("instituteur"));
		}
		rs.close();
		pst.close();
		return c;
	}    	
}
