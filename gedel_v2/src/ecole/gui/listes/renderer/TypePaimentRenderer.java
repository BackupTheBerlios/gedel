/*
 * Created on 13 oct. 2004 for gedel_v2
 *
 * 
 */
package ecole.gui.listes.renderer;

import javax.swing.table.DefaultTableCellRenderer;

/**
 * TypePaimentRenderer
 * @author jerome forestier @ sqli
 */
public class TypePaimentRenderer extends DefaultTableCellRenderer
{
    /**
     * Positionne la valeur
     */
    public void setValue(Object value) {        
        if (value instanceof String)
        {
            String t = (String) value;
            if ("\0".equals(t))
                setText("");
            else
                setText(t);
        }           
        else
            setText("???" + value);
    }    
}
