/*
 * Created on 7 oct. 2004 for gedel_v2
 *
 * 
 */
package ecole.gui.dialog;

import java.awt.GridLayout;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import com.toedter.calendar.JDateChooser;

import ecole.databean.AtelierDatabean;
import ecole.databean.AtelierInscritDatabean;
import ecole.databean.DatabeanGeneric;
import ecole.databean.EleveDatabean;
import ecole.databean.TarifAtelierDatabean;
import ecole.datametier.AteliersMetier;
import ecole.datametier.TarifsAteliersMetier;
import ecole.gui.utils.ComboBoxFiller;

/**
 * Inscription d'un elever a un ou plusieurs ateliers.
 * Cet écran propose un ensemble checkbox represantant les ateliers dispo,
 * une combo avec le tarif dispo, la date de validité, et un nombre de jours.
 * @author jerome forestier @ sqli
 */
public class AtelierInscriptionDialog extends DialogGeneric
{
    // Libellé des champs de saisie
    private final static JLabel[] LABELS = 
        { new JLabel("Ateliers"), new JLabel("Tarif"), new JLabel("Date de validité "), new JLabel("Nombre de jours ") };

    // Champs de saisie (non static, car certains sont dynamique
    private JPanel panelCBatelier = new JPanel(); // Contiendra les checkbox des ateliers dispo
    private JComboBox cbTarif = new JComboBox();  // Contiendra les différents tarifs dispo
    private static JDateChooser dateValidite = new JDateChooser();
    private static JSpinner nbJours = new JSpinner();

    private  JComponent[] COMPONENTS = 
        {panelCBatelier, cbTarif, dateValidite, nbJours};
        
    private List listTarifAtelier; // List de TarifsAtelierDatabean, present dans la combo
    private List listAtelier ; // List de AtelierDatabean
    private List listAteliersChoisi; // List des AtelierDatabean choisi
    private AteliersMetier atelierMetier = new AteliersMetier();
    private TarifsAteliersMetier tarifsAteliersMetier = new TarifsAteliersMetier();
    
    private final static GridLayout checkBoxLayout = new GridLayout(2,4);
    private JCheckBox[] allCheckBoxes; // Contient touts les composants checkbox
    /**
     * @param mainWindow
     * @param labels
     * @param inputs
     */
    public AtelierInscriptionDialog(Window win) throws Exception
    {
        super(win, LABELS);
        this.setDialog_fields_input(COMPONENTS);
        listTarifAtelier = tarifsAteliersMetier.getAll();
        listAtelier = atelierMetier.getAll(); 
        initGui();
        this.setTitleWindow("Inscription aux ateliers");
    }
    
    /**
     * Rempli les panneaux, et les combobox
     * 
     * @author jerome forestier @ sqli
     * @date 7 oct. 2004
     */
    private void initGui() throws Exception
    {        
        allCheckBoxes = new JCheckBox[listAtelier.size()];
        panelCBatelier.setLayout(checkBoxLayout);
                        
        // Construction des ateliers dispo
        panelCBatelier.removeAll();       

        int nb = 0;            
        Iterator i = listAtelier.iterator();
        AtelierDatabean atelier;
        while (i.hasNext())
        {            
            atelier = (AtelierDatabean)i.next();
            String nom = atelier.getAtelier_nom();
            JCheckBox check = new JCheckBox(nom);
            check.setToolTipText(nom);
            panelCBatelier.add(check);            
            allCheckBoxes[nb] = check;
            nb++;
        }
        
        // Combo des tarifs dispo
        ComboBoxFiller.addItems(
            cbTarif,
            listTarifAtelier,
            tarifsAteliersMetier.getClass(),
            "getTarifNom");
    }

    /* (non-Javadoc)
     * @see ecole.gui.dialog.DialogGeneric#initInput(ecole.databean.DatabeanGeneric)
     */
    public void initInput(DatabeanGeneric databean)
    {
    }

    /* (non-Javadoc)
     * @see ecole.gui.dialog.DialogGeneric#initInput()
     */
    public void initInput()
    {
    }

    /**
     * Retourne uniquement non null, pour etre pris en compte
     * par inscrireAtelier() pour construire une liste de AtelierDatabean
     * @see ecole.gui.dialog.DialogGeneric#populateDatabean()
     */
    public DatabeanGeneric populateDatabean()
    {
        return new AtelierDatabean();
    }

    /* (non-Javadoc)
     * @see ecole.gui.dialog.DialogGeneric#isDatabeanValid(ecole.databean.DatabeanGeneric)
     */
    public String isDatabeanValid(DatabeanGeneric databean)
    {
        return null;
    }

    /**
     * @param eleve
     * @author jerome forestier @ sqli
     * @date 7 oct. 2004
     */
    public boolean inscrireAtelier(EleveDatabean eleve, AtelierInscritDatabean atelierInscrit)
    {
        // On pré-coche les ateliers inscrit
        List listAtelierInscrit = atelierInscrit.getListAtelierDatabean();
        Iterator i = listAtelierInscrit.iterator();
        while (i.hasNext())
        {
            int idAtelierInscrit = ((AtelierDatabean)i.next()).getId();
            for (int i_atelier = 0; i_atelier < listAtelier.size(); i_atelier++)
            {
                AtelierDatabean atelier = (AtelierDatabean)listAtelier.get(i_atelier);
                if (idAtelierInscrit == atelier.getId())
                {
                    // Cochons...
                    allCheckBoxes[i_atelier].setSelected(true);
                    break;
                }
            }
        }
        
        
        this.setTextWindow("Inscrire " + eleve.getNomPrenom() + " aux ateliers");
        if (this.saisir() != null)
        {
            // Creation de la liste des atelier choisi
            
            int j = 0;
            listAteliersChoisi = new ArrayList();
            while (j < allCheckBoxes.length)
            {
                JCheckBox check = allCheckBoxes[j];
                if (check.isSelected())
                {
                    listAteliersChoisi.add(listAtelier.get(j));
                }
                j++;
            }
            return true;
        }
        return false;
    }
    
    /**
     * Retourne la liste des ateliers coché
     * @return une List de AtelierDatabean
     * @author jerome forestier @ sqli
     * @date 7 oct. 2004
     */
    public List getListAteliersChoisi()
    {
        return listAteliersChoisi;
    }
    
    /**
     * Retourne la date de validité choisi dans la boite de dialogue
     * @return
     * @author jerome forestier @ sqli
     * @date 7 oct. 2004
     */
    public Date getDateValidite()
    {
        return dateValidite.getDate(); 
    }
    
    /**
     * Retourne le nombre de jours choisi dans la boite de dialogue
     * @return
     * @author jerome forestier @ sqli
     * @date 7 oct. 2004
     */
    public int getNbrJours()
    {        
        return Integer.parseInt(nbJours.getValue().toString());
    }

    /**
     * Retourne le tarif atelier choisi dans la combo
     * @return
     * @author jerome forestier @ sqli
     * @date 7 oct. 2004
     */
    public TarifAtelierDatabean getTarifAtelier()
    {
        int idx = cbTarif.getSelectedIndex();
        if (idx != -1)
        {
            return (TarifAtelierDatabean)listTarifAtelier.get(idx);
        }
        else
            return null;
    }

}
