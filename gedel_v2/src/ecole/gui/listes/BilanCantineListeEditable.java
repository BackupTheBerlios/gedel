/*
 * Created on 12 oct. 2004 for gedel_v2
 *
 * 
 */
package ecole.gui.listes;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import ecole.databean.HistoCantineDatabean;
import ecole.datametier.HistoCantineMetier;
import ecole.gui.EcoleApp;
import ecole.gui.elements.TableSorter;
import ecole.gui.listes.editor.IntegerEditor;
import ecole.gui.listes.editor.TypePaimentEditor;
import ecole.gui.listes.listener.ValueChangeListener;
import ecole.gui.listes.renderer.*;
import ecole.utils.DateTools;

/**
 * BilanCantineListeEditable
 * @author jerome forestier @ sqli
 */
public class BilanCantineListeEditable extends ListeEditableGeneric
{
    /** Libellés des colonnes **/
    private final static String[] COLUMN_NAMES = 
        new String[] {"Nom", "Prénom", "Classe", "Tarif", "Date de validité", "Jours", "Total", "Mode de paiement"};
        
    /** Class des colonnes (pour les comparateurs) **/
    private final static Class[]  COLUMN_CLASSES = 
        new Class[] {String.class, String.class, String.class, String.class,
            java.util.Date.class, Integer.class, Double.class, String.class };
            
    private final static DefaultTableCellRenderer[]  COLUMN_RENDERER =
        new DefaultTableCellRenderer[] {null, null, null, null, new DateFormatRenderer(DateTools.SDF_D2M2Y4), new IntegerRenderer(), new DoubleRenderer(), new TypePaimentRenderer()};            
    
            
    /** fenetre parente **/
    private EcoleApp ecoleApp;        
    
     /**
     * @param app
     */
    public BilanCantineListeEditable(EcoleApp app)
    {
        this();
        this.ecoleApp = app;
    }

    /**
     * @param columnNames
     */
    public BilanCantineListeEditable()
    {
        super(COLUMN_NAMES);
    }

    /**
     * 
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    public void process()
    {
        HistoCantineMetier metier = new HistoCantineMetier();
        try
        {
            List listEleveALaCantine = metier.getElevesALaCantine();
            this.setData(listEleveALaCantine.toArray()); // Tableau de HistoCantineDatabean
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }


    /* (non-Javadoc)
     * @see ecole.gui.listes.ListeEditableGeneric#getValueField(java.lang.Object, int)
     */
    public Object getValueField(Object object, int fieldIndex)
    {
        HistoCantineDatabean o = (HistoCantineDatabean)object;
        if (fieldIndex == 0) return o.getEleve().getNom();
        if (fieldIndex == 1) return o.getEleve().getPrenom();
        if (fieldIndex == 2) return o.getClasse().getClasse_nom();
        if (fieldIndex == 3) return o.getCantine().getPrixname();
        if (fieldIndex == 4) return o.getDateValidite();
        if (fieldIndex == 5) return new Integer(o.getNbrjours());
        if (fieldIndex == 6) return new Double(o.getNbrjours() * o.getCantine().getPrix());
        if (fieldIndex == 7) return HistoCantineMetier.getTypePaiment(o);
        //if (fieldIndex == 7) return "abc";
        return "???";
    }

    /* (non-Javadoc)
     * @see ecole.gui.listes.ListeEditableGeneric#getCellEditable(int, int)
     */
    public boolean getCellEditable(int row, int column)
    {
        return (column == 5 || column == 7);
    }

    /* (non-Javadoc)
     * @see ecole.gui.listes.ListeEditableGeneric#getColumnWidth(int)
     */
    public int getColumnWidth(int columnIndex)
    {
        return 0;
    }

    /* (non-Javadoc)
     * @see ecole.gui.listes.ListeEditableGeneric#getColumnRenderer(javax.swing.table.TableColumn, int)
     */
    public TableCellRenderer getColumnRenderer(TableColumn col, int columnIndex)
    {
        return COLUMN_RENDERER[columnIndex];
    }

    /* (non-Javadoc)
     * @see ecole.gui.listes.ListeEditableGeneric#getIntialSortedColumn()
     */
    public int getIntialSortedColumn()
    {
        return 0;
    }

    /* (non-Javadoc)
     * @see ecole.gui.listes.ListeEditableGeneric#getIntialSortedOrder()
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

    /* (non-Javadoc)
     * @see ecole.gui.listes.ListeEditableGeneric#getColumnEditor(javax.swing.table.TableColumn, int)
     */
    public TableCellEditor getColumnEditor(TableColumn col, int columnIndex)
    {
        if (columnIndex == 5)
        {
            return new IntegerEditor(0,100);
        }
        if (columnIndex == 7)
        {
            return new TypePaimentEditor();
        }
        else return null;
    }
    
    


    
    
    

    /* (non-Javadoc)
     * @see ecole.gui.listes.ListeEditableGeneric#setValueField(java.lang.Object, java.lang.Object, int)
     */
    public boolean setValueField(Object newValue, Object oldValue, int fieldIndex)
    {
        HistoCantineDatabean histo = (HistoCantineDatabean) oldValue;
        System.out.println("Champs "+oldValue+ " mise a jour du champ " +fieldIndex+ " -> "+newValue);
        if (fieldIndex == 7)
        {
            histo.setType_payment(TypePaimentEditor.checkBoxValueToBeanValue((String)newValue));
            return true;
        }
            
        return false;
    }
    

    
    
}
