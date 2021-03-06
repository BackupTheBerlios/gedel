/*
 * Created on 5 sept. 2004
 *
 */
package ecole.datametier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ecole.databean.EleveDatabean;
import ecole.databean.DatabeanGeneric;
import ecole.db.DBTools;

/**
 * @author jemore
 *
 */
public class ElevesMetier extends MetierGeneric
{
    public final static String SEXE_H = "H";
    public final static String SEXE_F = "F";
	public ElevesMetier()
	{
		super();
	}
	
	/**
	 * Retourne la list de tout les �l�ves
	 * @return List de EleveDatabean
	 * @author jemore
	 * @see ecole.datametier.GenericMetier#getAll()
	 */
	public List getAll() throws SQLException
	{
		ResultSet rs = executeQuery("select * from eleve order by nom");
		List list = getResults(rs);
		logInfo(list.size() + " �l�ves charg�s");
		rs.close();
		return list;
	}


	/* (non-Javadoc)
	 * @see ecole.datametier.GenericMetier#populateAllField(java.sql.ResultSet)
	 */
	protected DatabeanGeneric populateAllField(ResultSet rs) throws SQLException 
	{
		EleveDatabean e = new EleveDatabean();
		e.setId(rs.getInt("id"));
		e.setNom(rs.getString("nom"));
		e.setPrenom(rs.getString("prenom"));
		String sexe = rs.getString("sexe");
		if (!"".equals(sexe))
			e.setSexe(sexe.charAt(0));
		e.setDob(rs.getDate("dob"));
		e.setRue(rs.getString("rue"));
		e.setCodepostal(rs.getString("codepostal"));
		e.setVille(rs.getString("ville"));
		e.setTelephone1(rs.getString("telephone1"));
		e.setTelephone2(rs.getString("telephone2"));
		e.setTelephone3(rs.getString("telephone3"));
		e.setClasseid(rs.getInt("classeid"));
		e.setDateentree(rs.getDate("dateentree"));
		return e;	
	}
	
	public final static String getNomPrenom(EleveDatabean e)
	{
		return e.getNom() + " " + e.getPrenom();
	}
	
	/**
	 * Retourne le sexe de l'eleve pass� en parametre (SEXE_H ou SEXE_H)
	 * @param e
	 * @return "H" ou "F" ou null 
	 * @author jemore
	 */
	public final static String getSexe(EleveDatabean e)
	{
        String sexe = "" + e.getSexe();
        if (SEXE_H.equals(sexe))    return SEXE_H;
        if (SEXE_F.equals(sexe))    return SEXE_F;
        return "";
	}

    /**
     * @param e
     * @author jemore
     */
    public void insert(EleveDatabean e) throws SQLException
    {
        PreparedStatement pst = prepareStatementAutoInc("insert into eleve set " +            " nom=?, prenom=?, sexe=?, dob=?, rue=?, codepostal=?, ville=?, telephone1=?," +            " telephone2=?, telephone3=?, classeid=?, dateentree=?");
        int i = 1;
        pst.setString(i++, e.getNom());
        pst.setString(i++, e.getPrenom());
        pst.setString(i++, ""+e.getSexe());
        pst.setDate(i++, DBTools.JavaDateToSqlDate(e.getDob()));
        pst.setString(i++, e.getRue());
        pst.setString(i++, e.getCodepostal());
        pst.setString(i++, e.getVille());
        pst.setString(i++, e.getTelephone1());
        pst.setString(i++, e.getTelephone2());
        pst.setString(i++, e.getTelephone3());
        pst.setInt(i++, e.getClasseid());
        pst.setDate(i++, DBTools.JavaDateToSqlDate(e.getDateentree()));
        
        pst.executeUpdate();
        
        e.setId(getAutoincrement(pst));
		pst.close();
    }

	/**
	 * @param i
	 * @author jemore
	 */
	public void delete(int eleveid) throws SQLException
	{
		PreparedStatement pst = prepareStatement("delete from eleve where id=?");
		pst.setInt(1, eleveid);
		pst.execute();
	}

	/**
	 * @param e
	 * @author jemore
	 */
	public void update(EleveDatabean e) throws SQLException
	{
		PreparedStatement pst = prepareStatementAutoInc("update eleve set " +
            " nom=?, prenom=?, sexe=?, dob=?, rue=?, codepostal=?, ville=?, telephone1=?," +
            " telephone2=?, telephone3=?, classeid=?, dateentree=?" +            " where id=?");
        int i = 1;
        pst.setString(i++, e.getNom());
        pst.setString(i++, e.getPrenom());
        pst.setString(i++, ""+e.getSexe());
        pst.setDate(i++, DBTools.JavaDateToSqlDate(e.getDob()));
        pst.setString(i++, e.getRue());
        pst.setString(i++, e.getCodepostal());
        pst.setString(i++, e.getVille());
        pst.setString(i++, e.getTelephone1());
        pst.setString(i++, e.getTelephone2());
        pst.setString(i++, e.getTelephone3());
        pst.setInt(i++, e.getClasseid());
        pst.setDate(i++, DBTools.JavaDateToSqlDate(e.getDateentree()));
        
        pst.setInt(i++, e.getId());
        
        pst.executeUpdate();
                
		pst.close();
		
	}

    /**
     * Liste des �l�ves inscrit dans une classe.
     * @param classeid id de la classe
     * @return List de EleveDatabean
     * @author jerome forestier @ sqli
     * @date 29 sept. 2004
     */
    public List getElevesByClasse(int classeid) throws SQLException
    {
        List res = new ArrayList();
        PreparedStatement pst = prepareStatement("select * from eleve where classeid=? order by nom");
        pst.setInt(1, classeid);
        ResultSet rs = pst.executeQuery();
        while(rs.next())
        {
            res.add(populateAllField(rs));
        }
        rs.close();
        pst.close();
        return res;
    }
    
    /**
     * Retourne une List de String[3] contenant le Nom, Prenom, Classe
     * des �l�ves inscrit a un atelier
     * @param atelier_id
     * @return
     * @author jemore @ home
     * @date 9 oct. 2004
     */
    public List getElevesAndClassesByAtelier(int atelier_id) throws SQLException 
    {
    	/**
    	 * select E.nom, E.prenom, C.classe_nom
from eleve E, atelier A, classe C where
A.atelier_id = 1 and
A.eleve_id = E.id and
E.classeid = C.ID

    	 */
    	List res = new ArrayList();
    	PreparedStatement pst = prepareStatement("select E.nom, E.prenom, C.classe_nom " +    		"from eleve E, atelier A, classe C where" +    		" A.atelier_id = ? and" +    		" A.eleve_id = E.id and" +    		" E.classeid = C.ID");
		pst.setInt(1, atelier_id);
 		ResultSet rs = pst.executeQuery();
        while(rs.next())
        {
        	String[] elem = new String[3];
        	elem[0] = rs.getString("nom");
        	elem[1] = rs.getString("prenom");
        	elem[2] = rs.getString("classe_nom");
        	res.add(elem);
        }        
        return res;		
    }
    /**
     * Liste des �l�ves inscrit dans une classe, en renseignant dans le bean
     * le nb d'atelier auxquels l'el�ve est inscrit. Seul ID, Nom, Prenom est renseign� dans
     * l'EleveDatabean
     * @param classeid id de la classe
     * @return List de EleveDatabean
     * @author jerome forestier @ sqli
     * @date 8 oct. 2004
     */
    public List getElevesAndNumberOfAtelierAtelierByClasse(int classeid) throws SQLException
    {
/**
select eleve.id, count(atelier.atelier_id) from eleve
left join atelier on eleve.id = atelier.eleve_id
where eleve.classeid=7
group by eleve.id
*/
        List res = new ArrayList();
        PreparedStatement pst = prepareStatement("select " +            " eleve.id, eleve.nom, eleve.prenom, count(atelier.atelier_id) nb_atelier" +            " from eleve" +            " left join atelier on eleve.id = atelier.eleve_id " +            " where eleve.classeid=?" +            " group by eleve.id"             ); 
        pst.setInt(1, classeid);
        ResultSet rs = pst.executeQuery();
        while(rs.next())
        {
            EleveDatabean e = new EleveDatabean();
            e.setId(rs.getInt("id"));
            e.setNom(rs.getString("nom"));
            e.setPrenom(rs.getString("prenom"));
            e.setNbAtelierInscrit(rs.getInt("nb_atelier"));
            res.add(e);
        }
        rs.close();
        pst.close();
        return res;                
    }
}