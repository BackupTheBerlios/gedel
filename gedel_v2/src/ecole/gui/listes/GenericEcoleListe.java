/*
 * Created on 30 sept. 2004 for gedel_v2
 *
 * 
 */
package ecole.gui.listes;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import de.qfs.lib.gui.SortedTableHelper;

/**
 * GenericEcoleListe . Cette classe est une classe générique d'affichage d'un tableau de type JTable<br>
 * Vous devez surcharger les methodes abstract.<br>
 * @author jerome forestier @ sqli
 */
public abstract class GenericEcoleListe extends AbstractTableModel
{
    private JTable table;
    /** Données du tableau **/
    private Object[] _data;
    private int nbColumns = 0;
    public String[] columnNames = null;
    SortedTableHelper helper;

    public int selectedRow = 0;

    public GenericEcoleListe()
    {
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
     * Doit retourner la taille de la colonne n° columnIndex. -1 si la taille est par défaut
     * @param columnIndex
     * @return -1 si pas de modif de la taille, sinon la taille en pixel
     * @author jerome forestier @ sqli
     * @date 30 sept. 2004
     */
    public abstract int getColumnWidth(int columnIndex);

    public abstract int getNumberOfColumns();

    public int getColumnCount()
    {
        int nb = getNumberOfColumns();
        this.nbColumns = nb;
        return nb + 1;
    }

    /**
     * Doit retourner un moteur de rendu pour une colonne donnée 
     * @param col
     * @param columnIndex
     * @return null si pas de renderer, ou un objet héritant de DefaultTableCellRenderer. @see DateFormatRenderer
     * @author jerome forestier @ sqli
     * @date 30 sept. 2004
     */
    public abstract TableCellRenderer getColumnRenderer(TableColumn col, int columnIndex);

    public abstract void itemDoubleClicked(int idx, Object dataSelected);

    public abstract void itemSelected(int idx, Object dataSelected);

    /**
     * Retourne la valeur a affichée dans la cellule. 
     * @return peut retourner un objet (pas forcement un String)
     */
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        if (columnIndex == 0)
        {
           // System.out.println("index "+rowIndex + " " +_data[rowIndex].toString());
        }
        if (columnIndex < this.nbColumns)
        {
            Object d = getValueField(_data[rowIndex], columnIndex);
            return d;
        }
        return _data[rowIndex];
    }

    /**
     * Affecte l'ensemble des données 
     * @param data
     * @author jerome forestier @ sqli
     * @date 30 sept. 2004
     */
    public void setData(Object[] data)
    {
        this._data = data;
    }

    /* (non-Javadoc)
    * @see javax.swing.table.TableModel#getColumnName(int, int)
    */
    public String getColumnName(int col)
    {
        if (col < this.nbColumns)
            return columnNames[col];
        else
            return "";
    }

    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getRowCount()
     */
    public int getRowCount()
    {

        return _data.length;
    }

    /**
     * Retourne l'objet JTable représentant les données
     * @return
     * @author jerome forestier @ sqli
     * @date 30 sept. 2004
     */
    public JTable getTable()
    {

        table = new JTable(this);             

        int width;

        // Desactivation de la derniere colonne
        TableColumn column = table.getColumnModel().getColumn(nbColumns);
        column.setPreferredWidth(0);
        column.setWidth(0);
        column.setMaxWidth(0);
        column.setMinWidth(0);
        column.setResizable(false);

        for (int i = 0; i < this.nbColumns; i++)
        {
            width = getColumnWidth(i);
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
            }
        }
        table.getColumn("").setCellRenderer(new DummyRenderer());

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Ajoute les listener pour déclencher les actions
        table.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {

                if (e.getClickCount() == 2)
                {
                    callDoubleClick(e);
                }
            }
        });

       
        ListSelectionModel rowSM = table.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent e)
            {
                if (!e.getValueIsAdjusting())
                {
                    int row = table.getSelectedRow();
                    
                    if (row == -1) return;
                    int idx = helper.getSortedTableModel().getMappedRow(row);
                    callSelectItem(idx);
                }
            }
        });


        helper = new SortedTableHelper (table);
        return table;
    }

    /**
     * Appel la methode abstraite de selection d'une ligne
     * @param listener
     * @author jerome forestier @ sqli
     * @date 30 sept. 2004
     */
    protected void callSelectItem(int idx)
    {
        this.selectedRow = idx;
        itemSelected(this.selectedRow, this._data[idx]);
    }
    
    protected void callSelectItem(int idx, Object obj)
    {
        
    }

    /**
     * Appel la methode abstraite de doubleclickage sur une ligne
     * @param e
     * @author jerome forestier @ sqli
     * @date 30 sept. 2004
     */
    protected void callDoubleClick(MouseEvent e)
    {
        int idx = this.getSelectedIndex();
        idx = helper.getSortedTableModel().getMappedRow(idx);
        itemDoubleClicked(idx, this._data[idx]);
    }

    /**
     * Retourne l'objet actuellement selectionné
     * @return
     * @author jerome forestier @ sqli
     * @date 30 sept. 2004
     */
    private Object getSelectedItems()
    {
        int idx = this.getSelectedIndex();
        idx = helper.getSortedTableModel().getMappedRow(idx);
        return this._data[idx];
    }

    /**
     * Retourne le n° de l'objet selectionné
     * @return
     * @author jerome forestier @ sqli
     * @date 30 sept. 2004
     */
    private int getSelectedIndex()
    {
        return this.selectedRow;
    }

    /**
     * 
     * Classe de rendu vide. Retourne toujours ""
     *
     * @author jerome forestier @ sqli
     *
     */
    static class DummyRenderer extends DefaultTableCellRenderer
    {
        public void setValue(Object value)
        {
            setText("");
        }
    }

}
