/*
 * Created on 29 sept. 2004 for gedel_v2
 *
 * 
 */
package ecole.gui.listes;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import ecole.databean.ClasseDatabean;
import ecole.databean.EleveDatabean;
import ecole.datametier.ElevesMetier;
import ecole.gui.elements.TableSorter;
import ecole.utils.DateTools;

/**
 * EleveTable - Composant graphique représentant un tableau des élèves.
 * 
 * 
 * http://java.sun.com/docs/books/tutorial/uiswing/components/table.html#data
 * @author jerome forestier @ sqli
 */
public class EleveTable_old extends AbstractTableModel
{

    /** Composant metier pour extraire les données **/
    private ElevesMetier metier = new ElevesMetier();

    /** Données du tableau **/
    private Object[][] data;

    /** Libellés des colonnes */
    private String[] columnNames = { /*"#",*/
        "Nom", "Prenom", "Sexe", "Date de naissance" };



    /**
     * Transforme un liste de EleveDatabean en tableau d'objet
     * @param listEleve
     * @return tableau d'objet. 0 : nom, 1 : prenom, 2 : sexe, 3 : Date (de type java.util.Date)
     * @author jerome forestier @ sqli
     * @date 29 sept. 2004
     */
    private Object[][] listEleveToData(List listEleve)
    {
        Object[][] data = new Object[listEleve.size()][columnNames.length];
        int row = 0;
        Iterator i = listEleve.iterator();
        while (i.hasNext())
        {
            EleveDatabean e = (EleveDatabean) i.next();
            //data[row][0] = "" + (row + 1);
            data[row][0] = e.getNom();
            data[row][1] = e.getPrenom();
            data[row][2] = ElevesMetier.getSexe(e);
            data[row][3] = e.getDob(); //DateTools.SDF_D2M2Y4.format(e.getDob());
            row++;
        }
        return data;
    }

    /**
     * Effectue une recherche des eleves par classes
     * @param c ClasseDatabean (seul le champs id doit etre reseigné)
     * @throws SQLException
     * @author jerome forestier @ sqli
     * @date 29 sept. 2004
     */
    public void parClasse(ClasseDatabean c) throws SQLException
    {
        List listEleve = metier.getElevesByClasse(c.getId());
        data = listEleveToData(listEleve);
        listEleve = null;
    }

    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getRowCount()
     */
    public int getRowCount()
    {
        return data.length;
    }

    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    public int getColumnCount()
    {
        return columnNames.length;
    }

    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    public Object getValueAt(int rowIndex, int columnIndex)
    {

        Object d = data[rowIndex][columnIndex];
        if (d instanceof Date)
            return DateTools.SDF_D2M2Y4.format((Date) d);
        else
            return d.toString();
    }

    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getColumnName(int, int)
     */
    public String getColumnName(int col)
    {
        return columnNames[col];
    }

    /**
     * Créer l'objet <code>JTable</code> afin de l'ajouter a l'interface.
     * Le tableau crée est triable et selectionne une seule ligne.
     * @return
     * @author jerome forestier @ sqli
     * @date 29 sept. 2004
     */
    public JTable getTable()
    {
        
        JTable table = new JTable(this);
        
        TableColumn column = null;
        column = table.getColumnModel().getColumn(2); // Colonne sexe
        column.setPreferredWidth(20);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Ajoute les listener pour déclencher les actions
        table.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                
                if (e.getClickCount() == 2)
                {
                    doubleClick(e);
                }
            }
        });

        ListSelectionModel rowSM = table.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent e)
            {
                //Ignore extra messages.
                if (e.getValueIsAdjusting())
                    return;

                ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                if (lsm.isSelectionEmpty())
                {
                    
                }
                else
                {
                    int selectedRow = lsm.getMinSelectionIndex();
                    System.out.println(selectedRow);
                }
            }
        });

        return table;
    }

    /**
     * @param e
     * @author jerome forestier @ sqli
     * @date 30 sept. 2004
     */
    protected void doubleClick(MouseEvent e)
    {
        System.out.println("Double click " + getSelectedIndex());

    }
    
    public int getSelectedIndex()
    {
        return this.getTable().getSelectionModel().getMinSelectionIndex();    
    }
    
    public Object getSelectedItem()
    {
        return null;
    }
    
    public Object[][] getData()
    {
        return data;
       
    }
    
    public Object[] getColumnIdentifiers()
    {
        return columnNames;
    }
    

}
