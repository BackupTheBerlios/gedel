/*
 * Created on 12 oct. 2004 for gedel_v2
 *
 * 
 */
package ecole.gui.listes.editor;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;

/**
 * TypePaimentEditor
 * @author jerome forestier @ sqli
 */
public class TypePaimentEditor 
{

    JComboBox comboBox = new JComboBox();
    /**
     * @param comboBox
     */
    private TypePaimentEditor()
    {
        super();
        comboBox.addItem("");
        comboBox.addItem("Cheque");
        comboBox.addItem("Espèce");
    }
    
    private DefaultCellEditor getEditor()
    {
        return new DefaultCellEditor(comboBox);
    }


    public static final DefaultCellEditor EDITOR = new TypePaimentEditor().getEditor(); 
}
