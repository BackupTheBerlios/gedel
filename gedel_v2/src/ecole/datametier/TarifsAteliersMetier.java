/*
 * Created on 6 sept. 2004
 *
 */
package ecole.datametier;

import java.sql.PreparedStatement;
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
	
    /**
     * Retourne le nom d'un tarif (pas tres utile cette fonction)
     * @param t
     * @return
     * @author jerome forestier @ sqli
     * @date 11 oct. 2004
     */
	public static final String getTarifNom(TarifAtelierDatabean t)
	{
		return t.getTarif_nom();
	}
    
    /**
     * Ajoute un nouveau tarif. Le bean passé en parametre est modifié (l'id est affecté)
     * @param t TarifAtelierDatabean
     * @return le nouvel id
     * @author jerome forestier @ sqli
     * @date 11 oct. 2004
     */
    public int insert(TarifAtelierDatabean t) throws SQLException
    {
        String q = null;
        //INSERT INTO tarifsateliers (id, tarif_nom, tarif_prix) VALUES (NULL, 'BOU', 34)
        q = "insert into tarifsateliers (tarif_nom, tarif_prix) values('" + escape_string(t.getTarif_nom())+"', '" + escape_string(t.getPrix()) +"')";
        PreparedStatement pst = prepareStatementAutoInc(q);
        pst.executeUpdate(q);
        int id = getAutoincrement(pst);          
        t.setId(id);
        pst.close();
        return id;
    }

    /**
     * @param nouveau_tarif
     * @author jerome forestier @ sqli
     * @date 11 oct. 2004
     */
    public void update(TarifAtelierDatabean nouveau_tarif) throws SQLException
    {
        String q = "update tarifsateliers set tarif_nom=?, tarif_prix=? where id=?";
        PreparedStatement pst = prepareStatement(q);
        pst.setString(1, nouveau_tarif.getTarif_nom());
        pst.setDouble(2, nouveau_tarif.getPrix());
        pst.setInt(3, nouveau_tarif.getId());
        pst.execute();
        pst.close();
    }

    /**
     * @param tarif
     * @author jerome forestier @ sqli
     * @date 11 oct. 2004
     */
    public void delete(TarifAtelierDatabean tarif) throws SQLException
    {
        PreparedStatement pst = prepareStatement("delete from tarifsateliers where id=?");
        pst.setInt(1, tarif.getId());
        pst.execute();
        pst.close();       
    }

}
