/*
 * Created on 3 oct. 2004
 *
 */
package ecole.gui.dialog;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import ecole.databean.AtelierDatabean;
import ecole.datametier.AteliersMetier;
import ecole.gui.predefinedframe.NiceDialogAlert;
import ecole.gui.utils.GUITools;

/**
 * Classe construisant les boites de dialogues de saisie/modif des Ateliers.
 * Cette classe n'est pas un singleton pour l'instant.
 * 
 * @author jemore @ home
 */
public class AtelierDialog
{
	private final static Object[] CBTYPE_VALUES = new Object[]{
		"Périscolaire", "Etude", "Ville"
	};
    // Libellé des champs de saisie
    private final static JLabel[] allFieldLabels = 
    	{ new JLabel("Nom de l'atelier"), new JLabel("Jour de l'atelier "), new JLabel("Date de validité "), new JLabel("Type ")};

    // Champs de saisie
    private JTextField nom = new JTextField();
    private JTextField jour = new JTextField();
    private JComboBox cbType = new JComboBox(CBTYPE_VALUES);

    // Objet nécessaires à l'affichage
    private final int nbChampsDeSaisie = allFieldLabels.length;
    private final JPanel mainPanel = new JPanel();
    private final BorderLayout thisLayout = new BorderLayout();
    private final GridLayout panelLayout = new GridLayout(nbChampsDeSaisie, 1);
    private final JPanel panelGauche = new JPanel();
    private final JPanel panelDroite = new JPanel();
    
    // Databean liés
    private int atelier_id = 0;

    private JLabel errmsg = new JLabel();
    private Window mainWindow;

    //private final static CantineDialog instance = new CantineDialog();
    
    public AtelierDialog(Window mainWindow)
    {
    	this.mainWindow = mainWindow;
        initGUI();
    }

	private void initAllField()
    {
        try
        {
			nom.setText("");
			jour.setText("");
			cbType.setSelectedIndex(0);
			atelier_id = -1;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    
	private void initAllField(AtelierDatabean a)
    {
        if (null == a) 
        {
            initAllField();
            return;
        }
        try
        {
        	atelier_id = a.getId();
            nom.setText(a.getAtelier_nom());
            jour.setText(a.getJour());
            String type = AteliersMetier.getType(a);
            cbType.setSelectedItem(type);
            	
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
        panelDroite.add(nom);
        panelDroite.add(jour);
        panelDroite.add(cbType);

        mainPanel.add(panelGauche, BorderLayout.WEST);
        mainPanel.add(panelDroite, BorderLayout.CENTER);
        mainPanel.add(errmsg, BorderLayout.SOUTH);

        mainPanel.setAutoscrolls(true);
        mainPanel.setBorder(new TitledBorder(null, "--- placer le texte ici ---", TitledBorder.LEADING, TitledBorder.TOP));
    }    
    
	private String isDatabeanValid(AtelierDatabean a)
    {
        if (null == a)
            return "La saisie est incorrect";
        if ("".equals(a.getAtelier_nom()))
            return "Le libellé de l'atelier est invalide";
        if ("".equals(a.getJour()))
            return "Le ou les jours de l'atelier sont incorrects";		            
        return null;
    }
    
    private char cbTypeToType()
    {
    	int idx = cbType.getSelectedIndex();
    	if (idx == 0) return AtelierDatabean.ATELIER_P;
    	if (idx == 1) return AtelierDatabean.ATELIER_E;
    	if (idx == 2) return AtelierDatabean.ATELIER_V;
    	return '\0';
    }
	private AtelierDatabean populateDatabean()
    {
        AtelierDatabean a = null;
        try
        {
            a = new AtelierDatabean();
            a.setAtelier_nom(nom.getText());
            a.setJour(jour.getText());
            a.setType(cbTypeToType());
        }
        catch(Exception e)
        {
            return null;
        }
       
        return a;
    }
    
    public AtelierDatabean saisir()
    {
    	try 
    	{
    		GUITools.setCursorWait(mainWindow);
    		initAllField();
			mainPanel.setBorder(
				new TitledBorder(
					null, 
					"Saisissez un atelier", 
					TitledBorder.LEADING, 
					TitledBorder.TOP));
			GUITools.setCursorNormal(mainWindow);
			while (true)
            {

                int value =
                    NiceDialogAlert.showMessageDialog(
                        mainWindow,
                        "Atelier",
                        mainPanel,
                        JOptionPane.INFORMATION_MESSAGE,
                        NiceDialogAlert.OK_BUTTON | NiceDialogAlert.CANCEL_BUTTON,
                        NiceDialogAlert.CANCEL_BUTTON);

                if (NiceDialogAlert.OK_BUTTON == value)
                {
                    // On a cliqué sur ok
                    AtelierDatabean a = populateDatabean();
                    String err = isDatabeanValid(a);
                    if (null != err)
                    {
                        errmsg.setText(err);
                        errmsg.setForeground(new java.awt.Color(255, 0, 0));
                    } 
                    else
                    {                        
                        return a;
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
