/*
 * Created on 24 sept. 2004
 *
 * 
 */
package ecole.databean;

import java.util.Date;

/**
 * Représente un élément de la table cantine
 * @author jemore
 */
public class CantineDatabean extends GenericDatabean
{
    private int eleve_id;
    private String prixname;
    private double prix;
    private Date datevalidite;
    private int nbrjours;
    
    private boolean isAffecte;
    /**
     * @return
     * @author jemore
     */
    public Date getDatevalidite()
    {
        return datevalidite;
    }

    /**
     * @return
     * @author jemore
     */
    public int getEleve_id()
    {
        return eleve_id;
    }

    /**
     * @return
     * @author jemore
     */
    public int getNbrjours()
    {
        return nbrjours;
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
    public String getPrixname()
    {
        return prixname;
    }

    /**
     * @param date
     * @author jemore
     */
    public void setDatevalidite(Date date)
    {
        datevalidite = date;
    }

    /**
     * @param i
     * @author jemore
     */
    public void setEleve_id(int i)
    {
        eleve_id = i;
    }

    /**
     * @param i
     * @author jemore
     */
    public void setNbrjours(int i)
    {
        nbrjours = i;
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
    public void setPrixname(String string)
    {
        prixname = string;
    }

    /**
     * @return
     * @author jemore
     */
    public boolean isAffecte()
    {
        return isAffecte;
    }

    /**
     * @param b
     * @author jemore
     */
    public void setAffecte(boolean b)
    {
        isAffecte = b;
    }

}
