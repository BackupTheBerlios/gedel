/*
 * Created on 6 sept. 2004
 *
 */
package ecole.datametier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ecole.databean.AtelierDatabean;
import ecole.databean.GenericDatabean;

/**
 * Manipulation de la table Atelier
 * @author jemore
 *
 */
public class AteliersMetier extends GenericMetier
{

	/**
	 * @throws SQLException
	 */
	public AteliersMetier() throws SQLException
	{
		super();		
	}

	/* (non-Javadoc)
	 * @see ecole.datametier.GenericMetier#populateAllField(java.sql.ResultSet)
	 */
	protected GenericDatabean populateAllField(ResultSet rs)
		throws SQLException
	{
		AtelierDatabean a = new AtelierDatabean();
		a.setId(rs.getInt("id"));
		a.setAtelier_nom(rs.getString("atelier_nom"));
		a.setJour(rs.getString("jour"));
		String type = rs.getString("type");
		if (!"".equals(type)) 
			a.setType(type.charAt(0));
		return a;
	}

	/* (non-Javadoc)
	 * @see ecole.datametier.GenericMetier#getAll()
	 */
	public List getAll() throws SQLException
	{
		ResultSet rs = executeQuery("select * from refatelier order by atelier_nom");
		List list = getResults(rs);
		logInfo(list.size() + " ateliers chargées");
		rs.close();
		return list;
	}
	
	public static final String getAtelierNom(AtelierDatabean a)
	{
		return a.getAtelier_nom();
	}

}
