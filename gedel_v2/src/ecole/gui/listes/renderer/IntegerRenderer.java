/*
 * Created on 12 oct. 2004 for gedel_v2
 *
 * 
 */
package ecole.gui.listes.renderer;

import javax.swing.table.DefaultTableCellRenderer;

/**
 * IntegerRenderer
 * @author jerome forestier @ sqli
 */
public class IntegerRenderer extends DefaultTableCellRenderer
{
    public void setValue(Object value) {
        if (value == null)
        {
            setText("null");
        }
        else
        {
            setText("" + value);
            this.setHorizontalAlignment(RIGHT);
        }
    }
}
