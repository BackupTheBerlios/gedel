/*
 * Created on 23 août 2004
 *
 */
package ecole.datametier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ecole.databean.DatabeanGeneric;
import ecole.db.DBTools;
import ecole.gui.utils.Callbacker;
import ecole.utils.logger.Logger;

/**
 * Cette classe s'occupe de la connection a la base 
 * et du débugage.
 *  
 * @author Jerome
 *
 */
public abstract class MetierGeneric
{
	private Logger logger = Logger.getInstance();
	
	/**
	 * Cette methode doit etre implémentée par un ancetre de GenericMetier.
	 * Elle retourne un nouveau héritier de DatabeanGeneric a partir d'un resultset
	 * @param rs
	 * @return
	 * @throws SQLException
	 * @author jemore
	 */
	protected abstract DatabeanGeneric populateAllField(ResultSet rs) throws SQLException;
	
	/**
	 * Cette méthode doit etre implémentée par un ancêtre de GenericMetier.
	 * Elle retourne une List d'héritier de DatabeanGeneric, qui doivent
	 * correspondre a tout les élèments de la table de la BD, non filtré. 
	 * @return
	 * @throws SQLException
	 * @author jemore
	 */
	public abstract List getAll() throws SQLException;
	
	
	/**
	 * On a besoin d'un callbacker pour afficher une barre de progression
	 */
	private Callbacker callbacker = null;

	/**
	 * Constructeur ancetre.
	 * @throws SQLException
	 * 
	 */
	public MetierGeneric()
	{
		super();
	}

	/**
	 * Log une query en mode Debug 
	 * @param query
	 */
	private void debug(String query)
	{
		logger.logDebug("q:>" + query);
	}

	/**
	 * Retourne la connection à la base a partir du singleton
	 * @return
	 */
	protected Connection getConnection() throws SQLException
	{
		return ecole.db.DatabaseConnection.getInstance().getConnection();
	}

	/**
	 * Execute une requete Select. 
	 * Attention, les requetes sont en mode TYPE_SCROLL_INSENSITIVE et CONCUR_READ_ONLY pour permettre 
	 * le comptage du nombre d'occurence retournée par la requete.
	 * @param query
	 * @return ResultSet
	 * @throws SQLException
	 */
	protected ResultSet executeQuery(String query) throws SQLException
	{
		debug(query);
		return getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY ).executeQuery(query);
	}

	/**
	 * Execute une requete insert
	 * @param query
	 * @return nb de données inséré
	 * @throws SQLException
	 */
	protected int executeInsert(String query) throws SQLException
	{
		debug(query);
		return getConnection().createStatement().executeUpdate(query);
	}

	/**
	 * Retourne un PreparedStatement
	 * @param query
	 * @return PreparedStatement
	 * @throws SQLException
	 */
	protected PreparedStatement prepareStatement(String query)
		throws SQLException
	{
		debug(query);
		return getConnection().prepareStatement(query);
	}

	/**
	 * Retourne un PreparedStatement avec lequel ont peut connaitre l'id autoincrement
	 * @param query
	 * @param st_param
	 * @throws SQLException
	 */
	protected PreparedStatement prepareStatementAutoInc(String query)
		throws SQLException
	{
		debug(query);
		return getConnection().prepareStatement(
			query,
			Statement.RETURN_GENERATED_KEYS);
	}

	/**
	 * Retourne un id d'autoincrement, si le pst a été créer
	 * avec prepareStatementAutoInc
	 * @param pst
	 * @return
	 * @throws SQLException
	 * @see prepareStatementAutoInc
	 * @see http://dev.mysql.com/tech-resources/articles/autoincrement-with-connectorj.html
	 */
	protected int getAutoincrement(PreparedStatement pst) throws SQLException
	{
		int autoIncKeyFromApi = -1;
		ResultSet rs = pst.getGeneratedKeys();
		if (rs.next())
		{
			autoIncKeyFromApi = rs.getInt(1);
		} else
		{
			// throw an exception from here
		}
		rs.close();
		return autoIncKeyFromApi;
	}
	
	/**
	 * Retourne le nombre de ligne de la requete
	 * @param rs ResultSet sur lequel ont veut compter 
	 * @return
	 * @throws SQLException
	 * @author jemore
	 */
	protected int getNumberOfRowsReturned(ResultSet rs) throws SQLException
	{
		rs.last();
		int n = rs.getRow();
		rs.beforeFirst();
		return n;
	}

	/**
	 * Renvoi le callbacker utilisé pour suivre la progression de la récuperation
	 * des données d'une requete
	 * @return
	 * @author jemore
	 */
	public Callbacker getCallbacker()
	{
		return callbacker;
	}

	/**
	 * Affecte le callbacker utilisé pour suivre la progression de la récuperation
	 * des données d'une requete
	 * @param callbacker
	 * @author jemore
	 */
	public void setCallbacker(Callbacker callbacker)
	{
		this.callbacker = callbacker;
	}

	/**
	 * Log en mode Info
	 * @param string chaine de caractere a logger
	 * @author jemore
	 */
	public void logInfo(String str)
	{
		if (null == callbacker)
		{
			logger.logInfo(str);
		} else
		{
			callbacker.logInfo(str);
		}
	}

	/**
	 * Initialise la barre de progression (si un callbacker existe)
	 * @param max valeur maxi atteignable par la barre de progression. 
	 * Si < 0, on utilise le mode "non terminé" de la barre de progression 
	 * @author jemore
	 */
	public void progressBarInit(int max)
	{
		if (null != callbacker)
			callbacker.progressBarInit(max);
	}

	
	/**
	 * Affecte une valeur a la barre de progression, si un callbacker existe
	 * @param v valeur de la barre
	 * @author jemore
	 */
	public void progressBarSetValue(int v)
	{
		if (null != callbacker)
			callbacker.progressBarSetValue(v);
	}
	
	/**
	 * Parcours un resultset et applique la methode abstraite populateAllField()
	 * @param rs resultset 
	 * @return une List de DatabeanGeneric retournée par le populateAllField() abstrait
	 * @throws SQLException
	 * @author jemore
	 */
	protected List getResults(ResultSet rs) throws SQLException
	{
		int nbrow = getNumberOfRowsReturned(rs);
		progressBarInit(nbrow);
		List list = new ArrayList();
		int i = 0;
		while (rs.next())
		{
			DatabeanGeneric e = populateAllField(rs);
			list.add(e);
			i++;
			if (i % 25 == 0)
			{
				logInfo("" + i);
				progressBarSetValue(i);
			}
		}
		rs.close();
		progressBarSetValue(i);		
		return list;
	}
    
    /**
     * Echape une chaine de caratere.
     * Les caracteres échapés sont ' en \' , " en \".
     * @param str
     * @return la chaine echapper
     * @author jerome forestier @ sqli
     * @date 5 oct. 2004
     */
    protected String escape_string(String str)
    {
        return DBTools.escape_string(str);
    }
    
    /**
     * Echapement et mise en forme d'un double.
     * @param d
     * @return
     * @author jerome forestier @ sqli
     * @date 11 oct. 2004
     */
    protected String escape_string(double d)
    {
        return DBTools.doubleToString(d);
    }
	
}
