/*
 * Created on 8 oct. 2004 for gedel_v2
 *
 * 
 */
package ecole.gui.listes;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import ecole.databean.AtelierDatabean;
import ecole.databean.AtelierInscritDatabean;
import ecole.databean.ClasseDatabean;
import ecole.databean.EleveDatabean;
import ecole.datametier.AteliersMetier;
import ecole.datametier.ElevesMetier;
import ecole.gui.EcoleApp;
import ecole.gui.elements.TableSorter;
import ecole.gui.listes.renderer.ListAtelierRenderer;
import ecole.gui.listes.renderer.NbrJoursRenderer;

/**
 * Liste des élèves et de leurs ateliers, pour la classe selectionnée.
 * Cette liste contient tout les eleves de la classe selectionnée, et 
 * indique les ateliers et l'etude pour chaque élève.
 * @author jerome forestier @ sqli
 */
public class EleveAtelierClasseListe extends ListeGeneric
{
    /** Libellés des colonnes **/
    private final static String[] COLUMN_NAMES = 
        new String[] {"Nom", "Prénom", "Ateliers", "Etude"};
        
    /** Class des colonnes (pour les comparateurs) **/
    private final static Class[]  COLUMN_CLASSES = 
        new Class[] {String.class, String.class, String.class, Integer.class };
        
    private final static DefaultTableCellRenderer[]  COLUMN_RENDERER =
        new DefaultTableCellRenderer[] {null, null, new ListAtelierRenderer(), new NbrJoursRenderer() };

    /** fenetre parente **/
    private EcoleApp ecoleApp;
    
    /** map des eleves/atelier. La clé est un bean EleveDatabean, la value est un AtelierDatabean (ou null) **/
    Map mapEleveAtelier; 

    public EleveAtelierClasseListe(EcoleApp app)
    {
        this();
        this.ecoleApp = app;
    }
    
    /**
     * @param columnNames
     */
    public EleveAtelierClasseListe()
    {
        super(COLUMN_NAMES);
    }
    
    /**
     * Charge la liste avec les eleves inscrit dans la classe spécifié, et récupere aussi 
     * les etudes/atelier pour chaque élèves
     * @param classeDatabean (seul le champs id est obligatoire)
     * @throws SQLException Si base de données HS ou erreur. 
     * @author jerome forestier @ sqli
     * @date 1 oct. 2004
     */
    public void parClasse(ClasseDatabean classeDatabean) throws SQLException
    {
        ElevesMetier metier = new ElevesMetier(); 
        AteliersMetier metierAtelier = new AteliersMetier();
        List listEleve = metier.getElevesAndNumberOfAtelierAtelierByClasse(classeDatabean.getId()); // Liste des élèves de la classe
        //this.setData(listEleve.toArray()); // Tableau de EleveDatabean
        // Pour tout ces élèves, on récupere les ateliers inscrits (on les mets dans une Map dont la clé est le bean eleve)
        mapEleveAtelier = new HashMap();
        Iterator i = listEleve.iterator();
        while (i.hasNext())
        {
            EleveDatabean e = (EleveDatabean)i.next();
            if (e.getNbAtelierInscrit() > 0) {
                AtelierInscritDatabean atelierInscrit = metierAtelier.getAteliersInscritForEleve(e);
                if (atelierInscrit.getListAtelierDatabean().size() != 0)
                {
                    mapEleveAtelier.put(e, atelierInscrit);
                }
            }
        }
        this.setData(listEleve.toArray()); // Tableau de EleveDatabean
    }
    


    /* (non-Javadoc)
     * @see ecole.gui.listes.ListeGeneric#getValueField(java.lang.Object, int)
     */
    public Object getValueField(Object object, int fieldIndex)
    {
        EleveDatabean e = (EleveDatabean)object;
        if (fieldIndex == 0) return e.getNom();
        if (fieldIndex == 1) return e.getPrenom();
        AtelierInscritDatabean atelierInscrit = (AtelierInscritDatabean)mapEleveAtelier.get(e);
        if (atelierInscrit == null) return null;
        if (fieldIndex == 2){            
            return atelierInscrit.getListAtelierDatabean();
        }
        if (fieldIndex == 3) {
            // Les ateliers sont il Etude ?
            Iterator i = atelierInscrit.getListAtelierDatabean().iterator();
            while (i.hasNext())
            {
                AtelierDatabean a = (AtelierDatabean)i.next();
                if (a.isEtude())
                {
                    //return new Boolean(true);
                    return ""+atelierInscrit.getNbrJours()+" j";
                }
                    
            }
            return "";
        }
        return null;
    }

    /* (non-Javadoc)
     * @see ecole.gui.listes.ListeGeneric#getColumnWidth(int)
     */
    public int getColumnWidth(int columnIndex)
    {
        if (columnIndex == 3)
            return 1; // Etude : taille minimale
        return -1;
    }

    /* (non-Javadoc)
     * @see ecole.gui.listes.ListeGeneric#getColumnRenderer(javax.swing.table.TableColumn, int)
     */
    public TableCellRenderer getColumnRenderer(TableColumn col, int columnIndex)
    {
        return COLUMN_RENDERER[columnIndex];
    }

    /* (non-Javadoc)
     * @see ecole.gui.listes.ListeGeneric#getIntialSortedColumn()
     */
    public int getIntialSortedColumn()
    {
        return 0;
    }

    /* (non-Javadoc)
     * @see ecole.gui.listes.ListeGeneric#getIntialSortedOrder()
     */
    public int getIntialSortedOrder()
    {
       return TableSorter.ASCENDING;
    }

    /* (non-Javadoc)
     * @see ecole.gui.listes.ListeGeneric#handleDoubleClick(int, java.lang.Object)
     */
    public void handleDoubleClick(int indexSelected, Object objectSelected)
    {
    }

    /* (non-Javadoc)
     * @see ecole.gui.listes.ListeGeneric#handleSelected(int, java.lang.Object)
     */
    public void handleSelected(int indexSelected, Object objectSelected)
    {
    }

}
