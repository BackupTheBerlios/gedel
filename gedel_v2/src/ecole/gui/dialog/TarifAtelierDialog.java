/*
 * Created on 11 oct. 2004 for gedel_v2
 *
 * 
 */
package ecole.gui.dialog;

import java.awt.Window;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ecole.databean.DatabeanGeneric;
import ecole.databean.TarifAtelierDatabean;
import ecole.utils.Formatter;

/**
 * TarifCantineDialog
 * @author jerome forestier @ sqli
 */
public class TarifAtelierDialog extends DialogGeneric
{
    // Libellé des champs de saisie
    private final static JLabel[] LABELS = 
        { new JLabel("Nom du tarif "), new JLabel("Montant en Euros ")};

    // Champs de saisie
    private static JTextField nom = new JTextField();
    private static JTextField montant = new JTextField();
    private static JComponent[] COMPONENTS = 
        {nom, montant};    
    /**
     * @param mainWindow
     * @param labels
     * @param inputs
     */
    public TarifAtelierDialog(Window mainWindow)
    {
        super(mainWindow, LABELS, COMPONENTS);
        this.setTitleWindow("Tarif atelier");
    }


    /* (non-Javadoc)
     * @see ecole.gui.dialog.DialogGeneric#initInput(ecole.databean.DatabeanGeneric)
     */
    public void initInput(DatabeanGeneric databean)
    {
        TarifAtelierDatabean tarif = (TarifAtelierDatabean)databean;
        this.setId(tarif.getId());
        nom.setText(tarif.getTarif_nom());
        montant.setText(Formatter.doubleToStringLocale(tarif.getPrix()));

    }

    /* (non-Javadoc)
     * @see ecole.gui.dialog.DialogGeneric#initInput()
     */
    public void initInput()
    {
        this.setId(-1);
        nom.setText("");
        montant.setText(Formatter.doubleToStringLocale(0));

    }

    /* (non-Javadoc)
     * @see ecole.gui.dialog.DialogGeneric#populateDatabean()
     */
    public DatabeanGeneric populateDatabean()
    {
        TarifAtelierDatabean tarif = null;
        try
        {
            tarif = new TarifAtelierDatabean();
            tarif.setId(this.getId());
            tarif.setPrix(Formatter.stringToDouble(montant.getText()));
            tarif.setTarif_nom(nom.getText());
        }
        catch(Exception e)
        {
            return null;
        }
        return tarif;
    }

    /* (non-Javadoc)
     * @see ecole.gui.dialog.DialogGeneric#isDatabeanValid(ecole.databean.DatabeanGeneric)
     */
    public String isDatabeanValid(DatabeanGeneric databean)
    {
        if (databean == null) return "Les données saisies sont incorrectes";
        TarifAtelierDatabean tarif = (TarifAtelierDatabean) databean;
        if ("".equals(tarif.getTarif_nom()))
            return "Veuillez saisir un nom de tarif";
        if (tarif.getPrix() <= 0 )
            return "Le montant saisi est incorrect";
        return null;
    }
    
    
    public DatabeanGeneric saisir()
    {
        this.setTextWindow("Saisissez un tarif d'atelier");
        return (TarifAtelierDatabean)super.saisir();
    }
    
    public boolean supprimer(TarifAtelierDatabean tarif)
    {
        return this.supprimer(tarif, "Voulez-vous supprimer le tarif "+tarif.getTarif_nom()+" ?");
    }
    
    public TarifAtelierDatabean modifier(TarifAtelierDatabean tarif)
    {
        this.setTextWindow("Modifiez le tarif " + tarif.getTarif_nom());
        return (TarifAtelierDatabean)super.modifier(tarif);
    }

}
