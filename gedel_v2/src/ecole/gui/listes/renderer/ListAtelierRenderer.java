/*
 * Created on 8 oct. 2004 for gedel_v2
 *
 * 
 */
package ecole.gui.listes.renderer;

import java.util.*;

import javax.swing.table.DefaultTableCellRenderer;

import ecole.databean.AtelierDatabean;



/**
 * Renderer pour la liste des ateliers (List de AtelierDatabean).
 * 
 * @author jerome forestier @ sqli
 */
public class ListAtelierRenderer extends DefaultTableCellRenderer
{

    /**
     * Positionne la valeur
     */
    public void setValue(Object value) {        
        if (value instanceof List)
        {
            if (((List)value).size() == 0) setText("");
            String res = "";
            Iterator i = ((List)value).iterator();
            while (i.hasNext())
            {
                AtelierDatabean atelier = (AtelierDatabean)i.next();
                if (!atelier.isEtude())
                {
                    res += atelier.getAtelier_nom() + " (" + atelier.getJour()+"), ";
                }
                
            }
            if (res.length() > 2)
            	setText(res.substring(0, res.length() - 2));
        }
        else
            setText("");
            // setText((value == null ) ? "" : formatter.format(value));
    }    

}
