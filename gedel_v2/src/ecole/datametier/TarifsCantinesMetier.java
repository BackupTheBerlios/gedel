/*
 * Created on 6 sept. 2004
 *
 */
package ecole.datametier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ecole.databean.DatabeanGeneric;
import ecole.databean.TarifCantineDatabean;

/**
 * @author jemore
 *
 */
public class TarifsCantinesMetier extends MetierGeneric
{

	/**
	 * @throws SQLException
	 */
	public TarifsCantinesMetier() throws SQLException
	{
		super();
	}

	/* (non-Javadoc)
	 * @see ecole.datametier.GenericMetier#populateAllField(java.sql.ResultSet)
	 */
	protected DatabeanGeneric populateAllField(ResultSet rs) throws SQLException
	{
		TarifCantineDatabean t = new TarifCantineDatabean();
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
		ResultSet rs = executeQuery("select * from tarifscantine order by tarif_nom");
		List list = getResults(rs);
		logInfo(list.size() + " tarifs cantine chargés");
		return list;
	}
	
	public static final String getTarifNom(TarifCantineDatabean t)
	{
		return t.getTarif_nom();
	}
    
    public TarifCantineDatabean getTarifByTarifNom(String tarifNom) throws SQLException
    {
        TarifCantineDatabean t = new TarifCantineDatabean();
        String q = "select * from tarifscantine where tarif_nom=?";
        PreparedStatement pst = prepareStatement(q);
        pst.setString(1, tarifNom);
        ResultSet rs = pst.executeQuery();
        if (rs.next())
        {
            t.setId(rs.getInt("id"));
            t.setPrix(rs.getDouble("tarif_prix"));
            t.setTarif_nom(rs.getString("tarif_nom"));
        }
        rs.close();
        pst.close();
        return t;
    }

}
