/*
 * Created on 6 sept. 2004
 *
 */
package ecole.datametier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ecole.databean.TarifAtelierDatabean;
import ecole.databean.DatabeanGeneric;

/**
 * @author jemore
 *
 */
public class TarifsAteliersMetier extends MetierGeneric
{

	/**
	 * @throws SQLException
	 */
	public TarifsAteliersMetier()
	{
		super();
	}

	/* (non-Javadoc)
	 * @see ecole.datametier.GenericMetier#populateAllField(java.sql.ResultSet)
	 */
	protected DatabeanGeneric populateAllField(ResultSet rs) throws SQLException
	{
		TarifAtelierDatabean t = new TarifAtelierDatabean();
		t.setId(rs.getInt("id"));
		t.setTarif_nom(rs.getString("tarif_nom"));
		t.setPrix(rs.getDouble("tarif_prix"));
		return t;
	}

	/* (non-Javadoc)
	 * @see ecole.datametier.GenericMetier#getAll()
	 */
	public List getAll() throws SQLException
	{
		ResultSet rs = executeQuery("select * from tarifsateliers order by tarif_nom");
		List list = getResults(rs);
		logInfo(list.size() + " tarifs ateliers chargés");
		return list;
	}
	
	public static final String getTarifNom(TarifAtelierDatabean t)
	{
		return t.getTarif_nom();
	}

}
