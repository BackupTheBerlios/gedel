/*
 * Created on 1 oct. 2004 for gedel_v2
 *
 * 
 */
package ecole.gui.listes;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import ecole.gui.elements.TableSorter;

/**
 * Objet g�n�rique ancetre des objets d'affichage sous forme de tableau.
 * Les objets h�rit�s doivent impl�menter les methode abstract.
 * @author jerome forestier @ sqli
 */
public abstract class ListeGeneric
{
    private JTable table;

    private TableSorter sorter = new TableSorter();
    private String[] columnNames = null;
    private Object[] rowData = null;

    /**
     * Constructeur avec les libell�s des colonnes en parametres
     * @param columnNames
     */
    public ListeGeneric(String[] columnNames)
    {
        setColumnNames(columnNames);
    }
    
    /**
     * Doit retourner la valeur du champ n� fieldIndex de l'objet
     * @param object doit etre cast� 
     * @param fieldIndex numero du champs a retourn�
     * @return peut retourner un objet (pas forcement un String)
     * @author jerome forestier @ sqli
     * @date 30 sept. 2004
     */
    public abstract Object getValueField(Object object, int fieldIndex);
    
    /**
     * Doit retourner la taille de la colonne n� columnIndex. -1 si la taille est par d�faut
     * @param columnIndex
     * @return -1 si pas de modif de la taille, sinon la taille en pixel
     * @author jerome forestier @ sqli
     * @date 30 sept. 2004
     */
    public abstract int getColumnWidth(int columnIndex);
    
    /**
     * Doit retourner un moteur de rendu pour une colonne donn�e 
     * @param col
     * @param columnIndex 0 based
     * @return null si pas de renderer, ou un objet h�ritant de DefaultTableCellRenderer. @see DateFormatRenderer
     * @author jerome forestier @ sqli
     * @date 30 sept. 2004
     */
    public abstract TableCellRenderer getColumnRenderer(TableColumn col, int columnIndex);
    
    /**
     * Doit retourner le n� de la colonne a trier lors du 1er affichage
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
     * Appel�e quand l'utilisateur double clique sur une ligne
     * @param indexSelected le n� de ligne cliqu�
     * @param objectSelected l'objet cliqu�
     * @author jerome forestier @ sqli
     * @date 1 oct. 2004
     */
    public abstract void handleDoubleClick(int indexSelected, Object objectSelected);

    /**
     * Appel�e quand l'utilisateur double selectionne une ligne
     * @param indexSelected le n� de ligne cliqu�
     * @param objectSelected l'objet cliqu�
     * @author jerome forestier @ sqli
     * @date 1 oct. 2004
     */
    public abstract void handleSelected(int indexSelected, Object objectSelected);
    
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
     * Affecte les donn�es
     * @param rowData tableau d'objet (de databean par exemple)
     * @author jerome forestier @ sqli
     * @date 1 oct. 2004
     */
    protected final void setData(Object[] rowData)
    {
        this.rowData = rowData;
    }

    /**
     * Cr�e et retourne le composant JTable qui affiche les donn�es
     * @return
     * @author jerome forestier @ sqli
     * @date 1 oct. 2004
     */
    public final JTable getTable()
    {
		TableModel model = new NonEditableTableModel();
        sorter = new TableSorter(model);
        sorter.setColumnComparator(String.class, NOCASE_COMPARATOR);        
        table = new JTable(sorter);
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
				
            }
        }        

        table.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                if (e.getClickCount() == 2)
                {
                    int row = table.getSelectedRow();
                    int translated = sorter.modelIndex(row);
                    handleDoubleClick(translated, rowData[translated]);
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

                    if (row == -1)
                        return;
                    int translated = sorter.modelIndex(row);
                    handleSelected(translated, rowData[translated]);
                }
            }
        });
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sorter.setSortingStatus(getIntialSortedColumn(), getIntialSortedOrder());
        return table;
    }
    
	/**
	 * Retourne la classe contenue dans la colonne indiqu�e.
	 * Le comportement par d�faut consiste a retourner la Class de la colonne
	 * demand�e pour la 1ere ligne (indice 0).
	 * Cette m�thode peut etre surcharg�. 
	 * @param columnIndex
	 * @return
	 * @author jemore @ home
	 * @date 3 oct. 2004
	 */
	public Class getCustomColumnClass(int columnIndex)
    {
      	return getValueField(rowData[0], columnIndex).getClass();
    }


    /**
     * 
     * Classe interne d�finissant un TableModel non �ditable
     * @author jerome forestier @ sqli
     *
     */
    class NonEditableTableModel extends AbstractTableModel
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
            return false;
        }
        
        public Class getColumnClass(int columnIndex)
        {
        	//return getValueField(rowData[0], columnIndex).getClass();
        	return getCustomColumnClass(columnIndex);
        }
        /*
        public void setValueAt(Object value, int row, int col)
        {
            rowData[row][col] = value;
            fireTableCellUpdated(row, col);
        }
        */
    };

	/**
     * Classe interne d�finissant un comparateur pour les string 
     * sans tenir compte de la casse
     * @author jerome forestier @ sqli
	 */
	public static final Comparator NOCASE_COMPARATOR = new Comparator() {
        public int compare(Object s1, Object s2) {
            return ((String)s1).compareToIgnoreCase((String)s2);
        }
    };
}
