/*
 * Created on 14 sept. 2004
 *
 */
package ecole.utils;

/**
 * Classe utilitaire travaillant sur les chaines de caractères
 * 
 * @author jemore
 */
public final class StringTools
{
    /**
     * Test si une chaine est vide
     * @param str
     * @return true si str == "" ou str == null
     * @author jerome forestier @ sqli
     * @date 29 sept. 2004
     */
	public static final boolean isEmptyStr(String str)
	{
		if (null == str || "".equals(str))
			return true;
		else
			return false;
	}
    
    /**
     * Retourne une plurielisation de chaine
     * @param singulier chaine au singulier
     * @param pluriel chaine au pluriel
     * @param n nombre 
     * @return si n > 1, retourne plurieul, sinon singulier
     * @author jerome forestier @ sqli
     * @date 29 sept. 2004
     */
    public static final String pluriel(String singulier, String pluriel, int n)
    {
        if (n > 1) return pluriel;
        else return singulier;
    }
    

}
