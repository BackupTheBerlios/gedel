/*
 * Created on 12 oct. 2004 for gedel_v2
 *
 * 
 */
package ecole.gui.listes.editor;

import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;

import ecole.databean.HistoCantineDatabean;

/**
 * TypePaimentEditor
 * @author jerome forestier @ sqli
 */
/*
public class TypePaimentEditor 
{

    private final static String CHEQUE = "Cheque";
    private final static String ESPECE = "Espèce";

    JComboBox comboBox = new JComboBox();

    public TypePaimentEditor()
    {
        super();
        comboBox.addItem("");
        comboBox.addItem(CHEQUE);
        comboBox.addItem(ESPECE);
    }
    
    public DefaultCellEditor getEditor()
    {
        return new DefaultCellEditor(comboBox);
    }
    
    public static final char checkBoxValueToBeanValue(String value)
    {
        if (value == CHEQUE)
            return HistoCantineDatabean.TYPE_PAYMENT_C;
        if (value == ESPECE)
            return HistoCantineDatabean.TYPE_PAYMENT_E;
        return '\0';
    }


    public static final DefaultCellEditor EDITOR = new TypePaimentEditor().getEditor(); 
}.
*/

public class TypePaimentEditor extends DefaultCellEditor
{
    private final static String CHEQUE = "C";
    private final static String ESPECE = "E";
    JComboBox comboBox;

    /**
     * @param comboBox
     */
    private TypePaimentEditor(JComboBox comboBox)
    {
        super(comboBox);
    }

    /**
     * 
     */
    public TypePaimentEditor()
    {
        this(new JComboBox());
        comboBox = (JComboBox) super.getComponent();
        comboBox.addItem("");
        comboBox.addItem(CHEQUE);
        comboBox.addItem(ESPECE);
    }
    public static final char checkBoxValueToBeanValue(String value)
    {
        if (value == CHEQUE)
            return HistoCantineDatabean.TYPE_PAYMENT_C;
        if (value == ESPECE)
            return HistoCantineDatabean.TYPE_PAYMENT_E;
        return '\0';
    }

}
