/*
 * Created on 7 oct. 2004 for gedel_v2
 *
 * 
 */
package ecole.databean;

import java.util.Date;
import java.util.List;

/**
 * Contient les ateliers auxquels un eleve est inscrit.
 * @author jerome forestier @ sqli
 */
public class AtelierInscritDatabean extends DatabeanGeneric
{

    private int eleve_id;
    private String prixName;
    private double prix;
    private Date datevalidite;
    private int nbrJours; 
    private List listAtelierId; // List d'Integer contenant les ID des ateliers
    /* (non-Javadoc)
     * @see ecole.databean.DatabeanGeneric#getPrimaryKey()
     */
    String getPrimaryKey()
    {        
        return null; // Pas de PK :)
    }
    

    /**
     * @return
     * @author jerome forestier @ sqli
     * @date 7 oct. 2004
     */
    public Date getDatevalidite()
    {
        return datevalidite;
    }

    /**
     * @return
     * @author jerome forestier @ sqli
     * @date 7 oct. 2004
     */
    public int getEleve_id()
    {
        return eleve_id;
    }

    /**
     * @return List d'Integer des ID des ateliers
     * @author jerome forestier @ sqli
     * @date 7 oct. 2004
     */
    public List getListAtelierId()
    {
        return listAtelierId;
    }

    /**
     * @return
     * @author jerome forestier @ sqli
     * @date 7 oct. 2004
     */
    public int getNbrJours()
    {
        return nbrJours;
    }

    /**
     * @return
     * @author jerome forestier @ sqli
     * @date 7 oct. 2004
     */
    public double getPrix()
    {
        return prix;
    }

    /**
     * @return
     * @author jerome forestier @ sqli
     * @date 7 oct. 2004
     */
    public String getPrixName()
    {
        return prixName;
    }

    /**
     * @param date
     * @author jerome forestier @ sqli
     * @date 7 oct. 2004
     */
    public void setDatevalidite(Date date)
    {
        datevalidite = date;
    }

    /**
     * @param i
     * @author jerome forestier @ sqli
     * @date 7 oct. 2004
     */
    public void setEleve_id(int i)
    {
        eleve_id = i;
    }

    /**
     * @param list
     * @author jerome forestier @ sqli
     * @date 7 oct. 2004
     */
    public void setListAtelierId(List list)
    {
        listAtelierId = list;
    }

    /**
     * @param i
     * @author jerome forestier @ sqli
     * @date 7 oct. 2004
     */
    public void setNbrJours(int i)
    {
        nbrJours = i;
    }

    /**
     * @param d
     * @author jerome forestier @ sqli
     * @date 7 oct. 2004
     */
    public void setPrix(double d)
    {
        prix = d;
    }

    /**
     * @param string
     * @author jerome forestier @ sqli
     * @date 7 oct. 2004
     */
    public void setPrixName(String string)
    {
        prixName = string;
    }

}
