/*
 * Created on 30 sept. 2004 for gedel_v2
 *
 * 
 */
package ecole.gui.listes.renderer;

import java.text.DateFormat;
import java.util.Date;

import javax.swing.table.DefaultTableCellRenderer;

/**
 * Cette classe permet d'effectuer le rendu d'un objet de type Date dans un tableau.
 * 
 * @author jerome forestier @ sqli
 */
public class DateFormatRenderer extends DefaultTableCellRenderer
{

    /** Formateur par défaut **/
    private DateFormat formatter ; 
    
    /**
     * Constructeur en spécifiant un formateur de date.
     * @param format peut provenir de <code>DateTools.SDF_*</code>
     */
    public DateFormatRenderer(DateFormat format)
    {
        
        this.formatter = format;
    }

    /**
     * 
     * Constructeur utilisant un formateur de date par défaut (par rapport a Locale)
     */
    public DateFormatRenderer()
    {
        super();        
        formatter = DateFormat.getDateInstance();
    }
    
    /**
     * Positionne la valeur
     */
    public void setValue(Object value) {        
        if (value instanceof Date)
            setText((value == null ) ? "" : formatter.format(value));
    }    

}
