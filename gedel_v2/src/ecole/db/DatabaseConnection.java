/*
 * Created on 23 ao�t 2004
 *
 */
package ecole.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ecole.config.ConfigApp;
import ecole.config.ConfigInterface;
import ecole.utils.logger.LoggerUseInterface;

/**
 * 
 * Objet assurant la connection � la base
 * @pattern : singleton
 * @see : http://dev.mysql.com/doc/connector/j/en/index.html
 * 
 * @author Jerome  
 */
public final class DatabaseConnection
{
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	/**
	 * Instance du singleton, cr�er lors de la 1ere utilisation
	 */
	private static DatabaseConnection instance = new DatabaseConnection();

	/**
	 * Retourne l'instance du singleton 
	 * @return
	 */
	static public DatabaseConnection getInstance()
	{
		return instance;
	}

	/**
	 * Consutructeur priv�
	 *
	 */
	private DatabaseConnection()
	{
	}

	/**
	 * Objet connection
	 */
	private Connection conn = null;

	private LoggerUseInterface log = null;

	private void logError(String str)
	{
		if (null == log)
		{
			System.err.println(str);
		} else
		{
			log.logError(str);
		}
	}

	private void logInfo(String str)
	{
		if (null == log)
		{
			System.out.println(str);
		} else
		{
			log.logInfo(str);
		}
	}

	/**
	 * Retourne la connexion en cours.
	 * Si la connexion est ferm�, on la r�ouvre.
	 * @return
	 */
	public synchronized Connection getConnection() throws SQLException
	{
		if (null == conn || conn.isClosed()) // Doublecheck locking here ?
		{
			try
			{
				Class.forName(JDBC_DRIVER).newInstance();
			} catch (Exception e)
			{
				e.printStackTrace();
				throw new SQLException("Le drive JDBC " + JDBC_DRIVER +" ne peux pas �tre instanci�. Verifier qu'il soit dans le classpath");
			}
			try
			{
                ConfigInterface configApp = new ConfigApp("app.conf");
                String db_host = configApp.getString("db.host", "localhost");
                String db_base = configApp.getString("db.name", "ecole");
                String db_user = configApp.getString("db.user", "root");
                String db_pass = configApp.getString("db.pass", "");
                String url = "jdbc:mysql://"+db_host+"/"+db_base;
				conn =
					DriverManager.getConnection(
						url,
						db_user,
						db_pass);
				conn.setAutoCommit(true);
			} 
			catch (SQLException e)
			{
				logError("Impossible de se connecter � la base");
				e.printStackTrace();
				throw (e);
			}
			logInfo("Connexion � la base OK ");
		}
		try
		{
			if (conn.isClosed())
			{
				logInfo("Connexion � la base perdue");
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		return conn;
	}

	/**
	 * Ferme la connexion � la base
	 *
	 */
	public synchronized void closeConnection()
	{
		try
		{
			if (null != conn)
				conn.close();
			logInfo("Connexion � la base ferm�e.");
		} 
		catch (SQLException e)
		{
			logError("Impossible de fermer la connexion � la base");
			e.printStackTrace();
		}
		conn = null;
	}

	/**
	 * Affecte un logger.
	 * @param log si Null, stdout et stderr sont utilis�s.
	 * @author jemore
	 */
	public void setLogger(LoggerUseInterface log)
	{
		this.log = log;
	}
}