/*
 * Created on 6 sept. 2004
 *
 */
package ecole.datametier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ecole.databean.AtelierDatabean;
import ecole.databean.DatabeanGeneric;

/**
 * Manipulation de la table Atelier
 * @author jemore
 *
 */
public class AteliersMetier extends MetierGeneric
{
	public final static String ATELIER_P = "P"; // Périscolaire
	public final static String ATELIER_E = "E"; // Etude
	public final static String ATELIER_V = "V"; // Ville
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
	protected DatabeanGeneric populateAllField(ResultSet rs)
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

	/**
	 * Retourne le type d'atelier
	 * @param e
	 * @return ATELIER_P ou ATELIER_E ou ATELIER_V ou ""
	 * @author jemore
	 */
	public final static String getType(AtelierDatabean a)
	{
		String type = "" + a.getType();
		if (ATELIER_P.equals(type)) return ATELIER_P;
		if (ATELIER_E.equals(type)) return ATELIER_E;
		if (ATELIER_V.equals(type)) return ATELIER_V;
        return "";
	}
	
	public final static char getTypeFromString(String type)
	{
		if (ATELIER_P.equals(type)) return AtelierDatabean.ATELIER_P;
		if (ATELIER_E.equals(type)) return AtelierDatabean.ATELIER_E;
		if (ATELIER_V.equals(type)) return AtelierDatabean.ATELIER_V;
		return '\0';
	}
}
