/*
 * Created on 8 oct. 2004 for gedel_v2
 *
 * 
 */
package ecole.gui.listes;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import ecole.databean.AtelierDatabean;
import ecole.datametier.ElevesMetier;
import ecole.gui.EcoleApp;
import ecole.gui.elements.TableSorter;

/**
 * Liste des élèves et leur classe, pour l'atelier selectionné.
 * Cette liste contient tout les eleves ainsi que leur classe qui sont
 * inscrit a l'atelier demandé.
 * @author jerome forestier @ sqli
 */
public class EleveAtelierListe extends ListeGeneric
{
    /** Libellés des colonnes **/
    private final static String[] COLUMN_NAMES = 
        new String[] {"Nom", "Prénom", "Classe"};
        
    /** Class des colonnes (pour les comparateurs) **/
    private final static Class[]  COLUMN_CLASSES = 
        new Class[] {String.class, String.class, String.class};
        
    private final static DefaultTableCellRenderer[]  COLUMN_RENDERER =
        new DefaultTableCellRenderer[] {null, null, null};

    /** fenetre parente **/
    private EcoleApp ecoleApp;
    
    /** map des eleves/atelier. La clé est un bean EleveDatabean, la value est un AtelierDatabean (ou null) **/
    Map mapEleveAtelier; 

    public EleveAtelierListe(EcoleApp app)
    {
        this();
        this.ecoleApp = app;
    }
    
    /**
     * @param columnNames
     */
    public EleveAtelierListe()
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
    public void parAtelier(AtelierDatabean atelier) throws SQLException
    {
        ElevesMetier metier = new ElevesMetier();
        List listEleve = metier.getElevesAndClassesByAtelier(atelier.getId());
        this.setData(listEleve.toArray()); // Tableau de EleveDatabean
    }
    


    /* (non-Javadoc)
     * @see ecole.gui.listes.ListeGeneric#getValueField(java.lang.Object, int)
     */
    public Object getValueField(Object object, int fieldIndex)
    {
    	String[] elem = (String[])object;
    	return elem[fieldIndex];    	
    }

    /* (non-Javadoc)
     * @see ecole.gui.listes.ListeGeneric#getColumnWidth(int)
     */
    public int getColumnWidth(int columnIndex)
    {
        if (columnIndex == 2)
            return 1; // classe taille minimale
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
