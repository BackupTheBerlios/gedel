/*
 * Created on 6 sept. 2004
 *
 */
package ecole.databean;

/**
 * Représente un élément de la table atelier
 * @author jemore
 *
 */
public class AtelierDatabean extends DatabeanGeneric
{
	private int id;
	private String atelier_nom;
	private char type;
	private String jour;
	
	public final static char ATELIER_P = 'P'; // Périscolaire
	public final static char ATELIER_E = 'E'; // Etude
	public final static char ATELIER_V = 'V'; // Ville
	/**
	 * @return
	 * @author jemore
	 */
	public String getAtelier_nom()
	{
		return atelier_nom;
	}

	/**
	 * @return
	 * @author jemore
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @return
	 * @author jemore
	 */
	public String getJour()
	{
		return jour;
	}

	/**
	 * @return
	 * @author jemore
	 */
	public char getType()
	{
		return type;
	}

	/**
	 * @param string
	 * @author jemore
	 */
	public void setAtelier_nom(String string)
	{
		atelier_nom = string;
	}

	/**
	 * @param i
	 * @author jemore
	 */
	public void setId(int i)
	{
		id = i;
	}

	/**
	 * @param string
	 * @author jemore
	 */
	public void setJour(String string)
	{
		jour = string;
	}

	/**
	 * @param c
	 * @author jemore
	 */
	public void setType(char c)
	{
		type = c;
	}

    /**
     * Retourne la clé primaire sous forme de string
     */
    String getPrimaryKey()
    {
        return "" + this.getId();
    }

    /**
     * L'atelier est-il de type Etude
     * @return true si l'atelier est de type etude
     * @author jerome forestier @ sqli
     * @date 8 oct. 2004
     */
    public boolean isEtude()
    {  
    	/*
        String e = atelier_nom.toUpperCase();
        return e.indexOf("TUDE") != -1;
        */
        return this.type == ATELIER_E; 
    }

}
