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
public class AtelierDatabean extends GenericDatabean
{
	private int id;
	private String atelier_nom;
	private char type;
	private String jour;
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

}
