/*
 * Created on 30 sept. 2004 for gedel_v2
 *
 * 
 */
package ecole.gui.listes.renderer;

import java.text.DateFormat;

import javax.swing.table.DefaultTableCellRenderer;

/**
 * DateFormatRenderer
 * @author jerome forestier @ sqli
 */
public class DateFormatRenderer extends DefaultTableCellRenderer
{

    DateFormat formatter = DateFormat.getDateInstance();
    /**
     * @param format
     */
    public DateFormatRenderer(DateFormat format)
    {
        
        this.formatter = format;
    }

    /**
     * 
     */
    public DateFormatRenderer()
    {
        super();        
    }
    
    public void setValue(Object value) {        
        setText((value == null) ? "" : formatter.format(value));
    }    

}
