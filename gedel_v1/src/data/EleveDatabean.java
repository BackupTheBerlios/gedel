/*
 * Created on 25 août 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package data;

import java.util.Date;

/**
 * @author Jerome
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EleveDatabean extends GenericDatabean {
    private	int 	id;			// int 10
    private	String	nom;		// varchar(50)
    private	String	prenom;		// varchar(50)
    private	char	sexe;		// char(1)
    private	Date	dob;		// DATETIME
    private	String	rue;		// varchar(50)
    private	String	codepostal;	// varchar(6)
    private	String	ville;		// varchar(30)
    private	String  telephone1;	// varchar(11)
    private	String  telephone2;	// varchar(11)
    private	String  telephone3;	// varchar(11)
    private	int		classeid;	// int 10
    private String  classe_nom;
    private	Date	dateentree; // datetime
    	
    /**
     * @return Returns the classeid.
     */
    public int getClasseid() {
        return classeid;
    }
    /**
     * @param classeid The classeid to set.
     */
    public void setClasseid(int classeid) {
        this.classeid = classeid;
    }
    /**
     * @return Returns the codepostal.
     */
    public String getCodepostal() {
        return codepostal;
    }
    /**
     * @param codepostal The codepostal to set.
     */
    public void setCodepostal(String codepostal) {
        this.codepostal = codepostal;
    }
    /**
     * @return Returns the dateentree.
     */
    public Date getDateentree() {
        return dateentree;
    }
    /**
     * @param dateentree The dateentree to set.
     */
    public void setDateentree(Date dateentree) {
        this.dateentree = dateentree;
    }
    /**
     * @return Returns the dob.
     */
    public Date getDob() {
        return dob;
    }
    /**
     * @param dob The dob to set.
     */
    public void setDob(Date dob) {
        this.dob = dob;
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
     * @return Returns the nom.
     */
    public String getNom() {
        return nom;
    }
    /**
     * @param nom The nom to set.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    /**
     * @return Returns the prenom.
     */
    public String getPrenom() {
        return prenom;
    }
    /**
     * @param prenom The prenom to set.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    /**
     * @return Returns the rue.
     */
    public String getRue() {
        return rue;
    }
    /**
     * @param rue The rue to set.
     */
    public void setRue(String rue) {
        this.rue = rue;
    }
    /**
     * @return Returns the sexe.
     */
    public char getSexe() {
        return sexe;
    }
    /**
     * @param sexe The sexe to set.
     */
    public void setSexe(char sexe) {
        this.sexe = sexe;
    }
    /**
     * @return Returns the telephone1.
     */
    public String getTelephone1() {
        return telephone1;
    }
    /**
     * @param telephone1 The telephone1 to set.
     */
    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }
    /**
     * @return Returns the telephone2.
     */
    public String getTelephone2() {
        return telephone2;
    }
    /**
     * @param telephone2 The telephone2 to set.
     */
    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }
    /**
     * @return Returns the telephone3.
     */
    public String getTelephone3() {
        return telephone3;
    }
    /**
     * @param telephone3 The telephone3 to set.
     */
    public void setTelephone3(String telephone3) {
        this.telephone3 = telephone3;
    }
    /**
     * @return Returns the ville.
     */
    public String getVille() {
        return ville;
    }
    /**
     * @param ville The ville to set.
     */
    public void setVille(String ville) {
        this.ville = ville;
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
}
