/*
 * Created on 12 oct. 2004 for gedel_v2
 *
 * 
 */
package ecole.datametier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ecole.databean.DatabeanGeneric;
import ecole.databean.HistoCantineDatabean;

/**
 * HistoCantineMetier
 * @author jerome forestier @ sqli
 */
public class HistoCantineMetier extends MetierGeneric
{


    /* (non-Javadoc)
     * @see ecole.datametier.MetierGeneric#populateAllField(java.sql.ResultSet)
     */
    protected DatabeanGeneric populateAllField(ResultSet rs) throws SQLException
    {
        return null;
    }

    /* (non-Javadoc)
     * @see ecole.datametier.MetierGeneric#getAll()
     */
    public List getAll() throws SQLException
    {
        return null;
    }
    
    /**
     * Retourne une liste de HistoCantineDatabean où seul
     * le champs mois_histo est rempli
     * @return
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    public List getAvailableHisto() throws SQLException
    {
        List list = new ArrayList();
        PreparedStatement pst = prepareStatement("select distinct(mois_histo) from histocantine");
        ResultSet rs = pst.executeQuery();
        while(rs.next())
        {
            HistoCantineDatabean histo = new HistoCantineDatabean();
            histo.setMois_histo(rs.getString("mois_histo"));
            list.add(histo);
        }
        rs.close();
        pst.close();
        return list;
    }
    
    /**
     * Retourne le nom de la sauvegarde (utilisé par le ComboBoxFiller)
     * @param histo
     * @return
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    public static String getSauvevardeNom(HistoCantineDatabean histo)
    {
        return histo.getMois_histo();
    }

}
