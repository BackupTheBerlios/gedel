/*
 * Created on 12 oct. 2004 for gedel_v2
 *
 * 
 */
package ecole.databean;

import java.util.Date;

/**
 * HistoCantineDatabean
 * @author jerome forestier @ sqli
 */
public class HistoCantineDatabean extends DatabeanGeneric
{
    private EleveDatabean eleve;
    private ClasseDatabean classe;
    private CantineDatabean cantine;
    
    private int id;
    private String mois_histo; // Varchar 50
    private int id_eleve; 
    private int id_classe;
    private char type_payment; // N/E/\0
    private int nbrjours;
    private int id_tarifcantine;
    private Date dateValidite;
    
    public static final char TYPE_PAYMENT_C = 'C';
    public static final char TYPE_PAYMENT_E = 'E';
    /* (non-Javadoc)
     * @see ecole.databean.DatabeanGeneric#getPrimaryKey()
     */
    String getPrimaryKey()
    {
        return "" + id;
    }

    /**
     * @return
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    public java.util.Date getDateValidite()
    {
        return dateValidite;
    }

    /**
     * @return
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    public int getId()
    {
        return id;
    }

    /**
     * @return
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    public int getId_classe()
    {
        return id_classe;
    }

    /**
     * @return
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    public int getId_eleve()
    {
        return id_eleve;
    }

    /**
     * @return
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    public int getId_tarifcantine()
    {
        return id_tarifcantine;
    }

    /**
     * @return
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    public String getMois_histo()
    {
        return mois_histo;
    }

    /**
     * @return
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    public int getNbrjours()
    {
        return nbrjours;
    }

    /**
     * @return
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    public char getType_payment()
    {
        return type_payment;
    }

    /**
     * @param date
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    public void setDateValidite(Date date)
    {
        dateValidite = date;
    }

    /**
     * @param i
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    public void setId(int i)
    {
        id = i;
    }

    /**
     * @param i
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    public void setId_classe(int i)
    {
        id_classe = i;
    }

    /**
     * @param i
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    public void setId_eleve(int i)
    {
        id_eleve = i;
    }

    /**
     * @param i
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    public void setId_tarifcantine(int i)
    {
        id_tarifcantine = i;
    }

    /**
     * @param string
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    public void setMois_histo(String string)
    {
        mois_histo = string;
    }

    /**
     * @param i
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    public void setNbrjours(int i)
    {
        nbrjours = i;
    }

    /**
     * @param c
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    public void setType_payment(char c)
    {
        type_payment = c;
    }

    /**
     * @return
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    public ClasseDatabean getClasse()
    {
        return classe;
    }

    /**
     * @return
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    public EleveDatabean getEleve()
    {
        return eleve;
    }

    /**
     * @return
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    public CantineDatabean getCantine()
    {
        return cantine;
    }

    /**
     * @param databean
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    public void setClasse(ClasseDatabean databean)
    {
        classe = databean;
    }

    /**
     * @param databean
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    public void setEleve(EleveDatabean databean)
    {
        eleve = databean;
    }


    /**
     * @param cantine
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    public void setCantine(CantineDatabean cantine)
    {
       
        this.cantine = cantine;        
    }

}
