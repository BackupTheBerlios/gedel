/*
 * Created on 6 sept. 2004
 *
 */
package ecole.datametier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import ecole.databean.AtelierDatabean;
import ecole.databean.AtelierInscritDatabean;
import ecole.databean.DatabeanGeneric;
import ecole.databean.EleveDatabean;
import ecole.databean.TarifAtelierDatabean;
import ecole.db.DBTools;

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
	public AteliersMetier()
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
	
    /**
     * Retourne le nom de l'atelier du bean. Utilisez notemment pour le remplissage de la combo.
     * @param atelier
     * @return
     * @author jerome forestier @ sqli
     * @date 7 oct. 2004
     */    
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

    /**
     * Insertion d'un nouvel atelier
     * le bean est modifié pour affecter l'ID inseré
     * @return int l'id de l'atelier inseré.
     * @author jerome forestier @ sqli
     * @date 5 oct. 2004
     */
    public int insert(AtelierDatabean atelier) throws SQLException
    {
        String q = null;
        
        q = "insert into refatelier set atelier_nom='" + escape_string(atelier.getAtelier_nom())+"', " +            " type='" + escape_string(atelier.getType()+"")+ "', " +            " jour='" + escape_string(atelier.getJour())+ "'";
        PreparedStatement pst = prepareStatementAutoInc(q);
        pst.executeUpdate(q);
        int id = getAutoincrement(pst);          
        atelier.setId(id);
        pst.close();
        return id;
    }

    /**
     * Mise a jour d'un atelier (par son ID)
     * @param atelier atelier a mettre a jour.
     * @author jerome forestier @ sqli
     * @date 7 oct. 2004
     */
    public void update(AtelierDatabean atelier) throws SQLException
    {
        String q = "update refatelier set atelier_nom=?, type=?, jour=? where id=?";
        int i = 1;
        PreparedStatement pst = prepareStatement(q);
        pst.setString(i++, atelier.getAtelier_nom());
        pst.setString(i++, ""+atelier.getType());
        pst.setString(i++, atelier.getJour());
        pst.setInt(i++, atelier.getId());
        pst.executeUpdate();
        pst.close();
    }

    /**
     * Suppression d'un atelier
     * @param refatelier_id id dans la table REFATELIER a supprimer
     * @author jerome forestier @ sqli
     * @date 7 oct. 2004
     */
    public void delete(int refatelier_id) throws SQLException
    {
        String q = "delete from refatelier where id=?";
        PreparedStatement pst = prepareStatement(q);
        pst.setInt(1, refatelier_id);
        pst.executeUpdate();
        pst.close();        
    }
    
    /**
     * Supprime tout les ateliers où l'élève est inscrit
     * @param eleve
     * @author jerome forestier @ sqli
     * @date 7 oct. 2004
     */
    public void deleteAllAteliersForEleve(EleveDatabean eleve) throws SQLException
    {
        String q = "delete from atelier where eleve_id=?";
        PreparedStatement pst = prepareStatement(q);
        pst.setInt(1, eleve.getId());
        pst.executeUpdate();
        pst.close();
    }

    /**
     * Met a jour (ajoute ou supprime) les ateliers auxquels un eleve est inscrit.
     * On commence par enelver tout les ateliers auxquels l'eleve est inscrit, pui
     * on ajoute seulement ceux présent dans la liste.
     * @param listAtelierChoisi une liste de AtelierDatabean
     * @param eleve l'eleve a inscire
     * @author jerome forestier @ sqli
     * @date 7 oct. 2004
     */
    public void updateAteliersForEleve(List listAtelierChoisi,
        Date dateValidite,
        int nbrJours, 
        EleveDatabean eleve, 
        TarifAtelierDatabean tarifAtelier) throws SQLException
    {
        deleteAllAteliersForEleve(eleve);
        
        Iterator i = listAtelierChoisi.iterator();
        while(i.hasNext())
        {
            AtelierDatabean atelier = (AtelierDatabean)i.next();
            PreparedStatement pst = prepareStatement("insert into atelier " +                " set eleve_id=?, " +                " atelier_id=?, " +                " prixname=?," +                " prix=?," +                " datevalidite=?," +                " nbrjours=?");
            int n = 1;
            pst.setInt(n++, eleve.getId());
            pst.setInt(n++, atelier.getId());
            pst.setString(n++, tarifAtelier.getTarif_nom());
            pst.setDouble(n++, tarifAtelier.getPrix());
            pst.setDate(n++, DBTools.JavaDateToSqlDate(dateValidite));
            pst.setInt(n++, nbrJours);
            pst.executeUpdate();
            pst.close();
        }
    }

    /**
     * Retourne la liste des ateliers auxquels un eleve est inscrit
     * @param eleve
     * @return AtelierInscritDatabean
     * @author jerome forestier @ sqli
     * @date 7 oct. 2004
     */
    public AtelierInscritDatabean getAteliersInscritForEleve(EleveDatabean eleve) throws SQLException
    {
        /*
        AtelierInscritDatabean atelierInscrit = new AtelierInscritDatabean();
        atelierInscrit.setEleve_id(eleve.getId());
        
        List list = new ArrayList();
        PreparedStatement pst = prepareStatement("select * from atelier where eleve_id = ?");
        pst.setInt(1, eleve.getId());
        ResultSet rs = pst.executeQuery();
        boolean flag = false; // 
        while(rs.next())
        {
            Integer id = new Integer(rs.getInt("atelier_id"));
            list.add(id);
            if (!flag)
            {
                flag = true;
                atelierInscrit.setDatevalidite(rs.getDate("datevalidite"));
                atelierInscrit.setNbrJours(rs.getInt("nbrjours"));
                atelierInscrit.setPrix(rs.getDouble("prix"));
                atelierInscrit.setPrixName(rs.getString("prixname"));
            }
        }
        atelierInscrit.setListAtelierId(list);        
        return atelierInscrit; 
        */
        
        String q = "select eleve_id, atelier_id, prixname, prix, datevalidite, nbrjours, atelier_nom, type, jour "
        + " from atelier A, refatelier R "
        + " where eleve_id = ? "
        + " and A.atelier_id = R.id";
        AtelierInscritDatabean atelierInscrit = new AtelierInscritDatabean();
        List list = new ArrayList();
        
        PreparedStatement pst = prepareStatement(q);
        pst.setInt(1, eleve.getId());
        ResultSet rs = pst.executeQuery();
        boolean flag = false; // Detection du premier passage
        while(rs.next())
        {        
            if (!flag)
            {
                flag = true;
                atelierInscrit.setDatevalidite(rs.getDate("datevalidite"));
                atelierInscrit.setNbrJours(rs.getInt("nbrjours"));
                atelierInscrit.setPrix(rs.getDouble("prix"));
                atelierInscrit.setPrixName(rs.getString("prixname"));
            }
            AtelierDatabean atelier = new AtelierDatabean();
            atelier.setAtelier_nom(rs.getString("atelier_nom"));
            atelier.setId(rs.getInt("atelier_id"));
            atelier.setJour(rs.getString("jour"));
            atelier.setType(getTypeFromString(rs.getString("type")));
            list.add(atelier);
        }
        atelierInscrit.setListAtelierDatabean(list);        
        return atelierInscrit;      
    }
    
    


}
