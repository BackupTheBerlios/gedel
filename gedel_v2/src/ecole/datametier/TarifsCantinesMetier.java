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
	public TarifsCantinesMetier()
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
	
    /**
     * 
     * @param t
     * @return
     * @author jerome forestier @ sqli
     * @date 11 oct. 2004
     */
	public static final String getTarifNom(TarifCantineDatabean t)
	{
		return t.getTarif_nom();
	}
    
    /**
     * Retourne un tarif, par son nom.
     * @param tarifNom le nom du tarif
     * @return TarifCantineDatabean
     * @throws SQLException
     * @author jerome forestier @ sqli
     * @date 11 oct. 2004
     */
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

    /**
     * Insertion d'un tarif
     * @param tarif
     * @author jerome forestier @ sqli
     * @date 11 oct. 2004
     */
    public int insert(TarifCantineDatabean tarif) throws SQLException
    {
        PreparedStatement pst = prepareStatement("insert into tarifscantine set " +            " tarif_nom=?, tarif_prix=?");
        pst.setString(1, tarif.getTarif_nom());
        pst.setDouble(2, tarif.getPrix());
        pst.executeUpdate();
        int id = getAutoincrement(pst);          
        tarif.setId(id);
        pst.close();
        return id;
    }

    /**
     * Modification d'un tarif
     * @param nouveau_tarif
     * @author jerome forestier @ sqli
     * @date 11 oct. 2004
     */
    public void update(TarifCantineDatabean tarif) throws SQLException
    {
        PreparedStatement pst = prepareStatement("update tarifscantine set " +
            " tarif_nom=?, tarif_prix=? where id=?");
        pst.setString(1, tarif.getTarif_nom());
        pst.setDouble(2, tarif.getPrix());
        pst.setInt(3, tarif.getId());
        pst.executeUpdate();
        pst.close();
        
    }

    /**
     * Suppression d'un tarif
     * @param tarif
     * @author jerome forestier @ sqli
     * @date 11 oct. 2004
     */
    public void delete(TarifCantineDatabean tarif) throws SQLException
    {
        PreparedStatement pst = prepareStatement("delete from tarifscantine where id=?");
        pst.setInt(1, tarif.getId());
        pst.execute();
        pst.close();
    }

}
