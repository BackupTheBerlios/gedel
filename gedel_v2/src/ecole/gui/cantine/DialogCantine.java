/*
 * Created on 24 sept. 2004
 *
 * 
 */
package ecole.gui.cantine;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.sql.SQLException;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import ecole.databean.CantineDatabean;
import ecole.databean.EleveDatabean;
import ecole.databean.TarifCantineDatabean;
import ecole.datametier.TarifsCantinesMetier;
import ecole.gui.elements.JEcoleDateChooser;
import ecole.gui.predefinedframe.NiceDialogAlert;
import ecole.gui.utils.ComboBoxFiller;
import ecole.gui.utils.GUITools;

/**
 * @author jemore
 */
public class DialogCantine
{
    // Libellé des champs de saisie
    private final static JLabel[] allFieldLabels = { new JLabel("Cantine"), new JLabel("Tarif"), new JLabel("Date de validité"), new JLabel("Nombre de jours")};

    // Champs de saisie
    private final JCheckBox cbOui = new JCheckBox("Cochez si oui");
    private JComboBox cbTarif = new JComboBox();
    private JDateChooser dateValidite = new JEcoleDateChooser();
    private JTextField nbJours = new JTextField();

    // Objet nécessaires à l'affichage
    private final int nbChampsDeSaisie = allFieldLabels.length;
    private final JPanel mainPanel = new JPanel();
    private final BorderLayout thisLayout = new BorderLayout();
    private final GridLayout panelLayout = new GridLayout(nbChampsDeSaisie, 1);
    private final JPanel panelGauche = new JPanel();
    private final JPanel panelDroite = new JPanel();
    
    // Databean liés
    private int eleve_id = 0;

    private JLabel errmsg = new JLabel();

    private final static DialogCantine instance = new DialogCantine();

    private DialogCantine()
    {
        initGUI();
    }

    public static DialogCantine getInstance()
    {
        // return instance;
        return new DialogCantine();
    }

    private void initAllField()
    {
        try
        {

            TarifsCantinesMetier metier = new TarifsCantinesMetier();
            ComboBoxFiller.addItems(cbTarif, metier.getAll(), metier.getClass(), "getTarifNom");
            metier = null;
            cbOui.setSelected(false);
            nbJours.setText("0");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    
    /**
     * @param c
     * @author jemore
     */
    private void initAllField(CantineDatabean c)
    {
        if (null == c) 
        {
            initAllField();
            return;
        }
        try
        {
            TarifsCantinesMetier metier = new TarifsCantinesMetier();
            ComboBoxFiller.addItems(cbTarif, metier.getAll(), metier.getClass(), "getTarifNom");
            metier = null;
            cbTarif.setSelectedItem(c.getPrixname().toString());
            cbOui.setSelected(c.isAffecte());
            dateValidite.setDate(c.getDatevalidite());
            nbJours.setText(c.getNbrjours() + "");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }      

    /**
     * Construit la GUI (dans mainPanel)
     * 
     * @author jemore
     */
    private void initGUI()
    {
        initAllField();
        
        mainPanel.setLayout(thisLayout);
        panelLayout.setRows(nbChampsDeSaisie);
        panelLayout.setColumns(1);
        panelGauche.setLayout(panelLayout);

        panelDroite.setLayout(panelLayout);

        // Ajout des composants
        for (int i = 0; i < allFieldLabels.length; i++)
        {
            panelGauche.add(allFieldLabels[i]);
        }
        panelDroite.add(cbOui);
        panelDroite.add(cbTarif);
        panelDroite.add(dateValidite);
        panelDroite.add(nbJours);

        mainPanel.add(panelGauche, BorderLayout.WEST);
        mainPanel.add(panelDroite, BorderLayout.CENTER);
        mainPanel.add(errmsg, BorderLayout.SOUTH);

        mainPanel.setAutoscrolls(true);
        mainPanel.setBorder(new TitledBorder(null, "--- placer le texte ici ---", TitledBorder.LEADING, TitledBorder.TOP));
    }

    private String isDatabeanValid(CantineDatabean c)
    {
        if (null == c)
            return "La saisie est incorrect";
        if ("".equals(c.getPrixname()))
            return "Le libellé du prix est invalide";
        if (c.getNbrjours() <= 0)
            return "Le nombre de jours est invalide";
        return null;
    }
    
    private CantineDatabean populateCantineDatabean()
    {
        CantineDatabean c = null;
        try
        {
            c = new CantineDatabean();
            c.setDatevalidite(dateValidite.getDate());
            c.setEleve_id(this.eleve_id);
            c.setNbrjours(Integer.parseInt(nbJours.getText()));
            c.setPrixname(cbTarif.getSelectedItem().toString());           
        }
        catch(Exception e)
        {
            return null;
        }
       
        return c;
    }
    
  

    /**
     * @param c
     * @param e
     * @return
     * @author jemore
     */
    private CantineDatabean completeCantineDatabean(CantineDatabean c, EleveDatabean e) throws SQLException
    {   
        TarifsCantinesMetier metier = new TarifsCantinesMetier();
        String nomTarif = c.getPrixname();
        TarifCantineDatabean tarif = metier.getTarifByTarifNom(nomTarif);
        c.setPrix(tarif.getPrix());
        c.setEleve_id(e.getId());
        c.setPrixname(tarif.getTarif_nom());
        return c;
    }
    

    /**
     * @param app
     * @param e
     * @param e1
     * @return
     * @author jemore
     */
    public CantineDatabean saisir(Window mainWindow, CantineDatabean c, EleveDatabean e) throws SQLException
    {
        try
        {
            GUITools.setCursorWait(mainWindow);
            this.eleve_id = e.getId();
            initAllField(c);
            String text = "Cantine " + e.getNomPrenom();
            String title = "Cantine";
            mainPanel.setBorder(new TitledBorder(null, text, TitledBorder.LEADING, TitledBorder.TOP));

            GUITools.setCursorNormal(mainWindow);

            while (true)
            {

                int value =
                    NiceDialogAlert.showMessageDialog(
                        mainWindow,
                        title,
                        mainPanel,
                        JOptionPane.INFORMATION_MESSAGE,
                        NiceDialogAlert.OK_BUTTON | NiceDialogAlert.CANCEL_BUTTON,
                        NiceDialogAlert.CANCEL_BUTTON);

                if (NiceDialogAlert.OK_BUTTON == value)
                {
                    // On a cliqué sur ok
                    c = populateCantineDatabean();
                    String err = isDatabeanValid(c);
                    if (null != err)
                    {
                        errmsg.setText(err);
                        errmsg.setForeground(new java.awt.Color(255, 0, 0));
                    } 
                    else
                    {
                        c = completeCantineDatabean(c, e);
                        c.setAffecte(cbOui.isSelected());
                        return c;
                    }                    
                }
                else
                {
                    return null;
                }
            }

        }
        finally
        {
            GUITools.setCursorNormal(mainWindow);
        }
    }



}
