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
import ecole.databean.TarifCantineDatabean;
import ecole.utils.Formatter;

/**
 * TarifCantineDialog
 * @author jerome forestier @ sqli
 */
public class TarifCantineDialog extends DialogGeneric
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
    public TarifCantineDialog(Window mainWindow)
    {
        super(mainWindow, LABELS, COMPONENTS);
        this.setTitleWindow("Tarif cantine");
    }


    /* (non-Javadoc)
     * @see ecole.gui.dialog.DialogGeneric#initInput(ecole.databean.DatabeanGeneric)
     */
    public void initInput(DatabeanGeneric databean)
    {
        TarifCantineDatabean tarifCantine = (TarifCantineDatabean)databean;
        this.setId(tarifCantine.getId());
        nom.setText(tarifCantine.getTarif_nom());
        montant.setText(Formatter.doubleToStringLocale(tarifCantine.getPrix()));

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
        TarifCantineDatabean tarifCantine = null;
        try
        {
            tarifCantine = new TarifCantineDatabean();
            tarifCantine.setId(this.getId());
            tarifCantine.setPrix(Formatter.stringToDouble(montant.getText()));
            tarifCantine.setTarif_nom(nom.getText());
        }
        catch(Exception e)
        {
            return null;
        }
        return tarifCantine;
    }

    /* (non-Javadoc)
     * @see ecole.gui.dialog.DialogGeneric#isDatabeanValid(ecole.databean.DatabeanGeneric)
     */
    public String isDatabeanValid(DatabeanGeneric databean)
    {/*
        if ("".equals(nom.getText()))
            return "Vous devez saisir un nom de tarif";
        if ("".equals(montant.getText()))
            return "Vous devez saisir un tarif";
        try {
            double mnt = Formatter.stringToDouble(montant.getText());
            if (mnt <= 0)
            {
                return "Le montant saisie est incorrect";
            }
        }
        catch(Exception e)
        {
            return "Le montant saisie est incorrect";
        }
        return null;
        */
        TarifCantineDatabean tarif = (TarifCantineDatabean) databean;
        if ("".equals(tarif.getTarif_nom()))
            return "Veuillez saisir un nom de tarif";
        if (tarif.getPrix() <= 0 )
            return "Le montant saisi est incorrect";
        return null;        
    }
    
    public DatabeanGeneric saisir()
    {
        this.setTextWindow("Saisissez un tarif de cantine");
        return (TarifCantineDatabean)super.saisir();
    }

    public boolean supprimer(TarifCantineDatabean tarif)
    {
        return this.supprimer(tarif, "Voulez-vous supprimer le tarif "+tarif.getTarif_nom()+" ?");
    }
    
    public TarifCantineDatabean modifier(TarifCantineDatabean tarif)
    {
        this.setTextWindow("Modifiez le tarif " + tarif.getTarif_nom());
        return (TarifCantineDatabean)super.modifier(tarif);
    }
}
