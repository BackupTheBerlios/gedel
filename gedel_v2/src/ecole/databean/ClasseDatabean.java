/*
 * Created on 5 sept. 2004
 *
 */
package ecole.databean;

/**
 * Représente un élément de la table classe
 * @author jemore
 *
 */
public class ClasseDatabean extends DatabeanGeneric
{
	// Champs mappé sur la base
	private int id; 				// int 10 unsigned, pk
	private String classe_nom;		// varchar(10)
	private String instituteur;		// varchar(30)
    
	public ClasseDatabean()
	{
		super();
	}
    
	public ClasseDatabean(int id, String classe_nom, String instituteur)
	{
		super();
		this.id = id;
		this.classe_nom = classe_nom;
		this.instituteur = instituteur;
	}

	/**
	 * @return Returns the classe_nom.
	 */
	public String getClasse_nom() {
		return classe_nom;
	}
	/**
	 * @param classe_nom The classe_nom to set.
	 */
	public void setClasse_nom(String classe_nom) {
		this.classe_nom = classe_nom;
	}
	/**
	 * @return Returns the id.
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return Returns the instituteur.
	 */
	public String getInstituteur() {
		return instituteur;
	}
	/**
	 * @param instituteur The instituteur to set.
	 */
	public void setInstituteur(String instituteur) {
		this.instituteur = instituteur;
	}
    
	public String toString()
	{
		return this.hashCode() + " id=" + id + "; nom=" + classe_nom + "; inst="+instituteur;
	}

    /**
     * Retourne la clé primaire sous forme de string
     */
    String getPrimaryKey()
    {
        return "" + this.getId();
    }
}
