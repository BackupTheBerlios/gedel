/*
 * Created on 24 sept. 2004
 *
 * 
 */
package ecole.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Classe utilitaire contenant des methodes statiques
 * pour le formatage de nombre ou de texte
 * @author jemore
 */
public class Formatter
{
    
    private static DecimalFormatSymbols symbols = null;
    private static NumberFormat montantFormatter = null;
    private static NumberFormat montantFormatterLocal = null;
    
    /**
     * Instanciation et reglage des formatteurs.
     * Si les formateurs sont déja instanciés et réglés, rien ne se passe.
     * @author jemore
     */
    private static void initFormatter()
    {
        if (null == symbols)
        {
            symbols = new DecimalFormatSymbols();      
            symbols.setDecimalSeparator('.');
            montantFormatter = new DecimalFormat("0.00", symbols);
            montantFormatterLocal = new DecimalFormat("0.00");
        }
    }
    
    
    /**
     * Constructeur privé. Cette classe utilitaire ne peut
     * pas etre instanciée.
     */
    private Formatter()
    {
    }
    
    /**
     * Conversion d'un double en chaine de caractere, en conservant 2 chiffres apres la virgules
     * @param d nombre a convertir
     * @return chaine representant d au format %.02d
     * @author jemore
     */    
    public static String doubleToString(double d)
    {
        initFormatter();
        return montantFormatter.format(d);
    }
    
    /**
     * Conversion d'un double en chaine de caractere, en conservant 2 chiffres apres la virgules
     * et en formattant en fonction du Locale
     * @param d nombre a convertir
     * @return chaine representant d au format %.02d
     * @author jemore
     */    
    public static String doubleToStringLocale(double d)
    {
        initFormatter();
        return montantFormatterLocal.format(d);
    }    
    
    public static double stringToDouble(String str) throws ParseException
    {
        initFormatter();
        try
        {
            str = str.replaceAll(",", ".");
            str = str.replaceAll(" ", "");
            return montantFormatter.parse(str).doubleValue();
        }
        catch (ParseException e)
        {
            try
            {
                return montantFormatter.parse(str).doubleValue();
            }
            catch (ParseException e1)
            {                
                try {
                    return new Double(str).doubleValue();
                } catch( Exception e2)
                {
                    throw new ParseException(str +" n'est pas un double", 0);
                }
            }
        }
    }


    /**
     * @param double1
     * @return
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    public static String doubleToStringLocale(Double double_)
    {
        return doubleToStringLocale(double_.doubleValue());
    }
    
    


}
