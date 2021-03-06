/*
 * Created on 4 oct. 2004 for gedel_v2
 *
 * 
 */
package ecole.gui.dialog;

import java.awt.Window;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ecole.databean.AtelierDatabean;
import ecole.databean.DatabeanGeneric;
import ecole.datametier.AteliersMetier;

/**
 * Boite de dialogue pour la saisise des ateliers.
 * Cette classe h�rite de DialogGeneric
 * @author jerome forestier @ sqli
 */
public class AtelierDialog extends DialogGeneric
{
    private final static Object[] CBTYPE_VALUES = new Object[]{
        "P�riscolaire", "Etude", "Ville"
    };
    // Libell� des champs de saisie
    private final static JLabel[] LABELS = 
        { new JLabel("Nom de l'atelier"), new JLabel("Jour de l'atelier "), new JLabel("Type ")};
        
    // Champs de saisie
    private static JTextField nom = new JTextField();
    private static JTextField jour = new JTextField();
    private static JComboBox cbType = new JComboBox(CBTYPE_VALUES);
    private static JComponent[] COMPONENTS = 
        {nom, jour, cbType};
        
    public AtelierDialog(Window win)
    {
        super(win, LABELS, COMPONENTS);
        this.setTitleWindow("Atelier");
    }
    
    /* (non-Javadoc)
     * @see ecole.gui.dialog.DialogGeneric#initInput(ecole.databean.DatabeanGeneric)
     */
    public void initInput(DatabeanGeneric databean)
    {
        AtelierDatabean a = (AtelierDatabean) databean;
        this.setId(a.getId());
        nom.setText(a.getAtelier_nom());
        jour.setText(a.getJour());
        String type = AteliersMetier.getType(a);
        cbType.setSelectedItem(type);
    }

    /* (non-Javadoc)
     * @see ecole.gui.dialog.DialogGeneric#initInput()
     */
    public void initInput()
    {
        nom.setText("");
        jour.setText("");
        cbType.setSelectedIndex(0);
    }
    
    /**
     * Transforme la selection de la combo des type en un CHAR acceptable par la base
     * @return
     * @author jerome forestier @ sqli
     * @date 7 oct. 2004
     */
    private char cbTypeToType()
    {
        int idx = cbType.getSelectedIndex();
        if (idx == 0) return AtelierDatabean.ATELIER_P;
        if (idx == 1) return AtelierDatabean.ATELIER_E;
        if (idx == 2) return AtelierDatabean.ATELIER_V;
        return '\0';
    }    

    /* (non-Javadoc)
     * @see ecole.gui.dialog.DialogGeneric#populateDatabean()
     */
    public DatabeanGeneric populateDatabean()
    {
        AtelierDatabean a = null;
        try
        {
            a = new AtelierDatabean();
            a.setAtelier_nom(nom.getText());
            a.setJour(jour.getText().toUpperCase());
            a.setType(cbTypeToType());
            a.setId(this.getId());
        }
        catch(Exception e)
        {
            return null;
        }
        return (DatabeanGeneric)a;
    }

    /* (non-Javadoc)
     * @see ecole.gui.dialog.DialogGeneric#isDatabeanValid(ecole.databean.DatabeanGeneric)
     */
    public String isDatabeanValid(DatabeanGeneric databean)
    {
        AtelierDatabean a = (AtelierDatabean) databean;
        if (null == a)
            return "La saisie est incorrect";
        if ("".equals(a.getAtelier_nom()))
            return "Le nom de l'atelier est invalide";
        if ("".equals(a.getJour()))
            return "Le ou les jours de l'atelier sont incorrects";                  
        return null;
    }
    
    public DatabeanGeneric saisir()
    {        
        this.setTextWindow("Saisissez un atelier");
        return (AtelierDatabean)super.saisir();
    }
    
    public DatabeanGeneric modifier(AtelierDatabean atelier)
    {
        this.setId(atelier.getId());
        this.setTextWindow("Modifier un atelier");
        return (AtelierDatabean)super.modifier((DatabeanGeneric)atelier);
    }

    /**
     * @param atelier
     * @return
     * @author jerome forestier @ sqli
     * @date 7 oct. 2004
     */
    public boolean supprimer(AtelierDatabean atelier)
    {
        return super.supprimer(atelier, "Voulez-vous supprimer l'atelier " +atelier.getAtelier_nom()+" ?");
    }    

}
