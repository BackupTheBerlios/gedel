/*
 * Created on 12 oct. 2004 for gedel_v2
 *
 * 
 */
package ecole.gui.listes.renderer;

import java.awt.Color;

import javax.swing.table.DefaultTableCellRenderer;

import ecole.utils.Formatter;

/**
 * IntegerRenderer
 * @author jerome forestier @ sqli
 */
public class DoubleRenderer extends DefaultTableCellRenderer
{
    public void setValue(Object value) {
        if (value == null) setText("null");
        else
        {
            setText(Formatter.doubleToStringLocale((Double)value));      
            this.setHorizontalAlignment(RIGHT);
        }
    }
}
