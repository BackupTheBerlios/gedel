/*
 * Created on 23 août 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package data;

/**
 * @author Jerome
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ClasseDatabean extends GenericDatabean {
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
    
}
