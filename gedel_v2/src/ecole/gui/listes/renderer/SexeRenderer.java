/*
 * Created on 1 oct. 2004 for gedel_v2
 *
 * 
 */
package ecole.gui.listes.renderer;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;

import ecole.datametier.ElevesMetier;

/**
 * Objet de rendu pour un JTable, pour afficher le sexe de l'élève. 
 * Un icone représentant un garcon ou une fille est affiché.
 * @author jerome forestier @ sqli
 */
public class SexeRenderer extends DefaultTableCellRenderer
{
    private final static ImageIcon GirlIcon = new ImageIcon(SexeRenderer.class.getClassLoader().getResource("icons/girl_nb.gif"));
    private final static ImageIcon BoyIcon = new ImageIcon(SexeRenderer.class.getClassLoader().getResource("icons/boy_nb.gif"));
    /**
     * Positionne la valeur
     */
    public void setValue(Object value)
    {
        if (value instanceof String && value != null)
        {
            
            if (ElevesMetier.SEXE_F == value)
            {
                setIcon(GirlIcon);
                
            }
            else if (ElevesMetier.SEXE_H == value)
            {
                setIcon(BoyIcon);
            }
            else
            {
                setIcon(null);
                setText("");
            }
        }
    }
}
