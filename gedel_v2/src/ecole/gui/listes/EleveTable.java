/*
 * Created on 30 sept. 2004 for gedel_v2
 *
 * 
 */
package ecole.gui.listes;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import ecole.databean.ClasseDatabean;
import ecole.databean.EleveDatabean;
import ecole.datametier.ElevesMetier;
import ecole.gui.listes.renderer.*;
import ecole.utils.DateTools;

/**
 * EleveTable
 * @author jerome forestier @ sqli
 */
public class EleveTable extends GenericEcoleListe
{
    /** Composant metier pour extraire les données **/
    private ElevesMetier metier = new ElevesMetier();
    
    private static final String[] COLUMN_NAMES = new String[] { "Nom", "Prenom", "Sexe", "Date de naissance" }; 
    
    
    public EleveTable()
    {
        super();
        this.columnNames = COLUMN_NAMES;
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
        this.setData(listEleve.toArray()); // Tableau de EleveDatabean
    }
    
    
    /* (non-Javadoc)
     * @see ecole.gui.listes.GenericEcoleListe#getColumnCount()
     */
    public int getNumberOfColumns()
    {
        return  columnNames.length;
    }

    /* (non-Javadoc)
     * @see ecole.gui.listes.GenericEcoleListe#getValueField(java.lang.Object, int)
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
     * @see ecole.gui.listes.GenericEcoleListe#getColumnWidth(javax.swing.table.TableColumn)
     */
    public int getColumnWidth(int columnIndex)
    {
        if (columnIndex == 2) // Colonne sexe
            return 20;
        else
            return -1;
    }

    /* (non-Javadoc)
     * @see ecole.gui.listes.GenericEcoleListe#getColumnRenderer(javax.swing.table.TableColumn, int)
     */
    public TableCellRenderer getColumnRenderer(TableColumn col, int i)
    {
       if (i == 3) // Colonne Date
       {
           return new DateFormatRenderer(DateTools.SDF_D2M2Y4);
       }
       else
        return null;
    }

    /* (non-Javadoc)
     * @see ecole.gui.listes.GenericEcoleListe#itemDoubleClicked(int, java.lang.Object)
     */
    public void itemDoubleClicked(int idx, Object dataSelected)
    {
        System.out.println("Double clique sur " + idx + " " + dataSelected);
        
    }

    /* (non-Javadoc)
     * @see ecole.gui.listes.GenericEcoleListe#itemSelected(int, java.lang.Object)
     */
    public void itemSelected(int idx, Object dataSelected)
    {
        System.out.println("Selection " + idx + " " + ElevesMetier.getNomPrenom((EleveDatabean)dataSelected));
        
    }




}
