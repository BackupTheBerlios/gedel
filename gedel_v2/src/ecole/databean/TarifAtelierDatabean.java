/*
 * Created on 6 sept. 2004
 *
 */
package ecole.databean;


/**
 * Représente un élément de la table tarif_atelier
 * @author jemore
 */
public class TarifAtelierDatabean extends GenericDatabean
{
	int id;
	String tarif_nom;
	double prix;
	
	public  TarifAtelierDatabean()
	{
		super();
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
	public double getPrix()
	{
		return prix;
	}

	/**
	 * @return
	 * @author jemore
	 */
	public String getTarif_nom()
	{
		return tarif_nom;
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
	 * @param d
	 * @author jemore
	 */
	public void setPrix(double d)
	{
		prix = d;
	}

	/**
	 * @param string
	 * @author jemore
	 */
	public void setTarif_nom(String string)
	{
		tarif_nom = string;
	}
    
    /**
     * Retourne la clé primaire sous forme de string
     */
    String getPrimaryKey()
    {
        return "" + this.getId();
    }

}
