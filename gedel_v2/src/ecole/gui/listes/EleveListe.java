/*
 * Created on 30 sept. 2004 for gedel_v2
 *
 * 
 */
package ecole.gui.listes;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import ecole.databean.ClasseDatabean;
import ecole.databean.EleveDatabean;
import ecole.datametier.ElevesMetier;
import ecole.gui.EcoleApp;
import ecole.gui.elements.TableSorter;
import ecole.gui.listes.renderer.DateFormatRenderer;
import ecole.gui.listes.renderer.SexeRenderer;
import ecole.utils.DateTools;


/**
 * EleveTable
 * @author jerome forestier @ sqli
 */
public class EleveListe extends ListeGeneric
{
	/** Libellés des colonnes **/
	private final static String[] COLUMN_NAMES = 
		new String[] {"Nom", "Prénom", "Sexe", "Date"};
		
	/** Class des colonnes (pour les comparateurs) **/
	private final static Class[]  COLUMN_CLASSES = 
		new Class[] {String.class, String.class, String.class, Date.class };
		
	private final static DefaultTableCellRenderer[]  COLUMN_RENDERER =
		new DefaultTableCellRenderer[] {null, null, new SexeRenderer(), new DateFormatRenderer(DateTools.SDF_D2M2Y4)};
		
	/** fenetre parente **/
    private EcoleApp ecoleApp;
    /**
     * @param app
     */
    public EleveListe(EcoleApp app)
    {
        this();
        this.ecoleApp = app;
    }

    /**
     * @param columnNames
     */
    public EleveListe()
    {
        super(COLUMN_NAMES);
    }

    /* (non-Javadoc)
     * @see ecole.gui.listes.ListeGeneric#getValueField(java.lang.Object, int)
     */
    public Object getValueField(Object object, int fieldIndex)
    {
        EleveDatabean e = (EleveDatabean)object;
        switch (fieldIndex)
        {
            case 0 : return e.getNom(); 
            case 1 : return e.getPrenom(); 
            case 2 : return ElevesMetier.getSexe(e); 
            case 3 : return (Date)e.getDob(); 
            default : return "???";                
        }
    }

    /* (non-Javadoc)
     * @see ecole.gui.listes.ListeGeneric#getColumnWidth(int)
     */
    public int getColumnWidth(int columnIndex)
    {
        if (columnIndex == 2) // Colonne sexe
            return 20;
        else
            return -1;
    }

    /* (non-Javadoc)
     * @see ecole.gui.listes.ListeGeneric#getColumnRenderer(javax.swing.table.TableColumn, int)
     */
    public TableCellRenderer getColumnRenderer(TableColumn col, int columnIndex)
    {
    	return COLUMN_RENDERER[columnIndex];
    }

    /**
     * Charge la liste avec les eleves inscrit dans la classe spécifié
     * @param classeDatabean (seul le champs id est obligatoire)
     * @throws SQLException Si base de données HS ou erreur. 
     * @author jerome forestier @ sqli
     * @date 1 oct. 2004
     */
    public void parClasse(ClasseDatabean classeDatabean) throws SQLException
    {
        ElevesMetier metier = new ElevesMetier(); 
        List listEleve = metier.getElevesByClasse(classeDatabean.getId());
        this.setData(listEleve.toArray()); // Tableau de EleveDatabean
        
    }
    
     /* (non-Javadoc)
     * @see ecole.gui.listes.ListeGeneric#handleDoubleClick(int, java.lang.Object)
     */
    public void handleDoubleClick(int indexSelected, Object objectSelected)
    {
        EleveDatabean e = (EleveDatabean)objectSelected;
        //System.out.println("click " + e);
        ecoleApp.selectEleve(e.getId());
        
    }

    /* (non-Javadoc)
     * @see ecole.gui.listes.ListeGeneric#handleSelected(int, java.lang.Object)
     */
    public void handleSelected(int indexSelected, Object objectSelected)
    {
        ecoleApp.setCurrentEleve((EleveDatabean)objectSelected);
    }

    /* (non-Javadoc)
     * Colonne de tri initial (Nom)
     * @see ecole.gui.listes.ListeGeneric#getIntialSortedColumn()
     */
    public int getIntialSortedColumn()
    {        
        return 0; 
    }

    /* (non-Javadoc)
     * ordre de tri initial
     * @see ecole.gui.listes.ListeGeneric#getIntialSortedOrder()
     */
    public int getIntialSortedOrder()
    {
        
        return TableSorter.ASCENDING;
    }
    
    /**
     * Retourne la classe correspondant a la colonne demandée.
     * Utilise le tableau COLUMN_CLASSES
     */
    public Class getCustomColumnClass(int columnIndex)
    {
    	return COLUMN_CLASSES[columnIndex];
    }
    
}
