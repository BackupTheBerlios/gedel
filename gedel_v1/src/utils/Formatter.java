/*
 * Created on 24 sept. 2004
 *
 * 
 */
package utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

/**
 * Classe utilitaire contenant des methodes statiques
 * pour le formatage de nombre ou de texte
 * @author JFORESTIER
 */
public class Formatter
{
    
    private static DecimalFormatSymbols symbols = null;
    private static NumberFormat montantFormatter = null;
    
    /**
     * Instanciation et reglage des formatteurs.
     * Si les formateurs sont déja instanciés et réglés, rien ne se passe.
     * @author JFORESTIER
     */
    private static void initFormatter()
    {
        if (null == symbols)
        {
            symbols = new DecimalFormatSymbols();      
            symbols.setDecimalSeparator('.');
            montantFormatter = new DecimalFormat("0.00", symbols);
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
     * @author JFORESTIER
     */    
    public static String doubleToString(double d)
    {
        initFormatter();
        return montantFormatter.format(d);
    }
}
