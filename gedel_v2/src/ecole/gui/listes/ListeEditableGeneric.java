/*
 * Created on 12 oct. 2004 for gedel_v2
 *
 * 
 */
package ecole.gui.listes;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import ecole.gui.elements.TableSorter;

/**
 * ListeEditableGeneric
 * @author jerome forestier @ sqli
 */
public abstract class ListeEditableGeneric
{
    private JTable table;

    private TableSorter sorter = new TableSorter();
    private String[] columnNames = null;
    private Object[] rowData = null;

    /**
     * Constructeur avec les libellés des colonnes en parametres
     * @param columnNames
     */
    public ListeEditableGeneric(String[] columnNames)
    {
        setColumnNames(columnNames);
    }
    
    /**
     * Affecte le nom des colonnes
     * @param columnNames
     * @author jerome forestier @ sqli
     * @date 1 oct. 2004
     */
    protected final void setColumnNames(String[] columnNames)
    {
        this.columnNames = columnNames;
    }    
    
    
    /**
     * Doit retourner la valeur du champ n° fieldIndex de l'objet
     * @param object doit etre casté 
     * @param fieldIndex numero du champs a retourné
     * @return peut retourner un objet (pas forcement un String)
     * @author jerome forestier @ sqli
     * @date 30 sept. 2004
     */
    public abstract Object getValueField(Object object, int fieldIndex);    
    
    /**
     * Sert a affecter une valeur (modification)
     * @param newValue nouvelle valeur
     * @param oldValue oncien objet
     * @param fieldIndex n° du champs a updater
     * @return
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    public abstract boolean setValueField(Object newValue, Object oldValue, int fieldIndex);
         

    /**
     * Retourne la classe contenue dans la colonne indiquée.
     * Le comportement par défaut consiste a retourner la Class de la colonne
     * demandée pour la 1ere ligne (indice 0).
     * Cette méthode peut etre surchargé. 
     * @param columnIndex
     * @return
     * @author jemore @ home
     * @date 3 oct. 2004
     */
    public Class getCustomColumnClass(int columnIndex)
    {
        return getValueField(rowData[0], columnIndex).getClass();
    } 
    
    public abstract boolean  getCellEditable(int row, int column);  
    
    /**
     * Doit retourner la taille de la colonne n° columnIndex. -1 si la taille est par défaut
     * @param columnIndex
     * @return -1 si pas de modif de la taille, sinon la taille en pixel
     * @author jerome forestier @ sqli
     * @date 30 sept. 2004
     */
    public abstract int getColumnWidth(int columnIndex);
    
    
    /**
     * Doit retourner un moteur de rendu pour une colonne donnée 
     * @param col
     * @param columnIndex 0 based
     * @return null si pas de renderer, ou un objet héritant de DefaultTableCellRenderer. @see DateFormatRenderer
     * @author jerome forestier @ sqli
     * @date 30 sept. 2004
     */
    public abstract TableCellRenderer getColumnRenderer(TableColumn col, int columnIndex);
    
    /**
     * Doit retourner un moteur d'edition pour une colonne donnée 
     * @param col
     * @param columnIndex 0 based
     * @return null si pas de renderer, ou un objet héritant de DefaultTableCellRenderer. @see DateFormatRenderer
     * @author jerome forestier @ sqli
     * @date 30 sept. 2004
     */
    public abstract TableCellEditor getColumnEditor(TableColumn col, int columnIndex);    
    
    /**
     * Doit retourner le n° de la colonne a trier lors du 1er affichage
     * @return 0..
     * @author jerome forestier @ sqli
     * @date 1 oct. 2004
     */
    public abstract int getIntialSortedColumn();
    
    /**
     * Doit retourner l'ordre initial de tri.
     * @return TableSorter.DESCENDING, TableSorter.DESCENDING.ASCENDING, TableSorter.NOT_SORTED
     * @author jerome forestier @ sqli
     * @date 1 oct. 2004
     */
    public abstract int getIntialSortedOrder();        
    
    
    /**
     * Affecte les données
     * @param rowData tableau d'objet (de databean par exemple)
     * @author jerome forestier @ sqli
     * @date 1 oct. 2004
     */
    protected final void setData(Object[] rowData)
    {
        this.rowData = rowData;
    }
    
    public final Object[] getData()
    {
        return this.rowData;
    }
    
    
    /**
     * Crée et retourne le composant JTable qui affiche les données
     * @return
     * @author jerome forestier @ sqli
     * @date 1 oct. 2004
     */
    public final JTable getTable()
    {
        TableModel model = new EditableTableModel();
        sorter = new TableSorter(model);
        sorter.setColumnComparator(String.class, ListeGeneric.NOCASE_COMPARATOR);
        sorter.setColumnComparator(Integer.class, ListeGeneric.INTEGER_COMPARATOR);
        sorter.setColumnComparator(Double.class, ListeGeneric.DOUBLE_COMPARATOR);         
        table = new JTable(sorter);
        table.setFont(ListeGeneric.TABLE_FONT);
        table.getTableHeader().setFont(ListeGeneric.TABLE_FONT);
       
        sorter.setTableHeader(table.getTableHeader());
        
        for (int i = 0; i < this.columnNames.length; i++)
        {
            int width = getColumnWidth(i);
            if (width != -1)
            {
                table.getColumnModel().getColumn(i).setPreferredWidth(width);
            }
            TableColumn col = table.getColumn(this.columnNames[i]);
            if (null != table)
            {
                TableCellRenderer renderer = getColumnRenderer(col, i);
                if (renderer != null)
                    col.setCellRenderer(renderer);
                TableCellEditor editor = getColumnEditor(col, i);
                if (editor != null)
                    col.setCellEditor(editor);
                
            }
        } 
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sorter.setSortingStatus(getIntialSortedColumn(), getIntialSortedOrder());
        return table;       
        
    }    
    
    /**
     * 
     * Classe interne définissant un TableModel éditable
     * @author jerome forestier @ sqli
     *
     */
    class EditableTableModel extends AbstractTableModel
    {
        public String getColumnName(int column)
        {
            return columnNames[column].toString();
        }
        public int getRowCount()
        {
            return rowData.length;
        }
        public int getColumnCount()
        {
            return columnNames.length;
        }
        public Object getValueAt(int row, int col)
        {
            //return rowData[row][col];
            return getValueField(rowData[row], col);
        }
        public boolean isCellEditable(int row, int column)
        {
            return getCellEditable(row, column);
        }
        
        public Class getColumnClass(int columnIndex)
        {
            //return getValueField(rowData[0], columnIndex).getClass();
            return getCustomColumnClass(columnIndex);
        }
        
        public void setValueAt(Object value, int row, int col)
        {            
            if (setValueField(value, rowData[row], col))            
                fireTableCellUpdated(row, col);
        }
        
    };    
}
