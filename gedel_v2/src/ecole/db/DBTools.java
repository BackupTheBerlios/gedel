/*
 * Created on 5 sept. 2004
 *
 */
package ecole.db;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe utilitaire pour manipuler des objets de la BD.
 * @author jemore
 *
 */
public final class DBTools
{
	private static final DateFormat SDF_DB = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	/**
	 * Format de la date : yyyy-mm-dd hh:mm:ss
	 * @param dateAsString
	 * @return
	 * @author jemore
	 */
	public final static Date DBDateToJavaDate(String dateAsString) throws ParseException
	{
		return SDF_DB.parse(dateAsString);
	}
    
    /**
     * Transforme une date java.util.Date (qui est le type utilisé dans les databean)
     * en une java.sql.Date (qui est le type stocké en base)
     * @param date
     * @return date aussi
     * @author jemore
     */
    public final static java.sql.Date JavaDateToSqlDate(java.util.Date date)
    {
        return new java.sql.Date(date.getTime());
    }
}
