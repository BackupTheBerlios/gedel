package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateTools
 * methodes et objets statics pour jouer avec les dates
 *
 * @version V1
 *
 * @author Stéphane ECOCHARD
 * @copyright (c) La Poste 2003
 * @date 06/05/2003
 * @project T51 
 */
public final class DateTools {

	/**
	 * Formateur yyyyMMdd
	 */
	public static final DateFormat SDF_MDHMS =
		new SimpleDateFormat("MMddHHmmss");


	/**
	 * Formateur yyyyMMdd
	 */
	public static final DateFormat SDF_Y4M2D2 =
		new SimpleDateFormat("yyyyMMdd");

	/**
	 * Formateur dd/MM/yy
	 */
	public static final DateFormat SDF_D2M2Y2 =
		new SimpleDateFormat("dd/MM/yy");

	/**
	 * Formateur dd MMMM yyyy
	 */
	public static final DateFormat SDF_D2M4Y4 =
		new SimpleDateFormat("dd MMMM yyyy");

	/**
	 * Formateur dd/mm/yyyy
	 */
	public static final DateFormat SDF_D2M2Y4 =
		new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * Constructeur interdit pour une classe utilitaire
	 */
	private DateTools() {
	}

	/**
	 * Conversion d'une string de la forme yyyyMMdd en Date
	 * @param strDate String
	 * @return Date
	 * @throws ParseException
	 */
	public static Date getDateYear4Month2Day2(String strDate)
		throws ParseException {
		return SDF_Y4M2D2.parse(strDate);
	}

	/**
	 * Conversion d'une string de la forme dd/MM/yy en Date
	 * @param strDate String
	 * @return Date
	 * @throws ParseException
	 */
	public static Date getDateDay2Month2Year2(String strDate)
		throws ParseException {
		return SDF_D2M2Y2.parse(strDate);
	}


	/**
	 * renvoie un long correspondant à la date du jour dont le format est YYYYMMDD
	 * eg: le 16/03/2004 retourne 20040316
	 * @return un long correspondant à la date du jour dont le format est YYYYMMDD
	 */
	public static long nowToYear4Month2Day2AsLong() {
		String strDate = SDF_Y4M2D2.format(new Date());
		return Long.parseLong(strDate);
	}
	
}