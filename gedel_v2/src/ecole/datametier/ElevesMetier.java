/*
 * Created on 5 sept. 2004
 *
 */
package ecole.datametier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ecole.databean.EleveDatabean;
import ecole.databean.GenericDatabean;
import ecole.db.DBTools;

/**
 * @author jemore
 *
 */
public class ElevesMetier extends GenericMetier
{

	public ElevesMetier() throws SQLException
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
	protected GenericDatabean populateAllField(ResultSet rs) throws SQLException 
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
	 * Retourne le sexe de l'eleve pass� en parametre
	 * @param e
	 * @return "H" ou "F" ou null 
	 * @author jemore
	 */
	public final static String getSexe(EleveDatabean e)
	{
		if (e.getSexe() == '\0') return "";
		else
			return ""+e.getSexe();
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

}