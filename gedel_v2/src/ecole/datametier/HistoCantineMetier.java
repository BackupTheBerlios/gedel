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

import ecole.databean.CantineDatabean;
import ecole.databean.ClasseDatabean;
import ecole.databean.DatabeanGeneric;
import ecole.databean.EleveDatabean;
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

    /**
     * Retourne la liste des élèves inscrit a la cantine
     * @return List de HistoCantineDatabean
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    public List getElevesALaCantine() throws SQLException
    {
        List list = new ArrayList();
        PreparedStatement pst = prepareStatement("select E.id eleve_id, E.nom, E.prenom, CL.id classe_id, " +            " CL.classe_nom, C.prixname, C.prix, C.datevalidite, C.nbrjours" +            " from cantine C, eleve E, classe CL" +            " where E.id = C.eleve_id" +            " and E.classeid = CL.id");
        ResultSet rs = pst.executeQuery();
        while(rs.next())
        {
            int eleve_id = rs.getInt("eleve_id");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            int classe_id = rs.getInt("classe_id");
            String classe_nom = rs.getString("classe_nom");
            String prixname = rs.getString("prixname");
            double prix = rs.getDouble("prix");
            java.util.Date dateValidite = rs.getDate("datevalidite");
            int nbrjours = rs.getInt("nbrjours");
            
            EleveDatabean eleve = new EleveDatabean();
            eleve.setClasseid(classe_id);
            eleve.setId(eleve_id);
            eleve.setNom(nom);
            eleve.setPrenom(prenom);
            
            ClasseDatabean classe = new ClasseDatabean();
            classe.setId(classe_id);
            classe.setClasse_nom(classe_nom);
            
            CantineDatabean cantine = new CantineDatabean();
            cantine.setPrixname(prixname);
            cantine.setPrix(prix);
            cantine.setEleve_id(eleve_id);
            cantine.setDatevalidite(dateValidite);
            cantine.setNbrjours(nbrjours);
            
            
            HistoCantineDatabean histo = new HistoCantineDatabean();
            histo.setEleve(eleve);
            histo.setId_eleve(eleve_id);
            histo.setId_classe(classe_id);
            histo.setClasse(classe);
            histo.setCantine(cantine);   
            histo.setNbrjours(nbrjours);
            histo.setDateValidite(dateValidite);  
            
            list.add(histo);
        }     
        return list;
    }

}
