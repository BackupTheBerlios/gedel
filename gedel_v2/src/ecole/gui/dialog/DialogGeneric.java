/*
 * Created on 4 oct. 2004
 *
 */
package ecole.gui.dialog;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import ecole.databean.DatabeanGeneric;
import ecole.gui.predefinedframe.NiceDialogAlert;
import ecole.gui.utils.GUITools;

/**
 * Classe ancetre des boites de dialogues.
 * Les implémentations des boites de dialogues doivent implementées DialogGeneric et les méthodes abstraites.
 * 
 * @author jemore @ home
 */
public abstract class DialogGeneric
{
	/** Libellés des champs de saisie **/
	private JLabel[] dialog_fields_label;

	/** Composant de saisie **/
	private JComponent[] dialog_fields_input;
	
	private int nbChampsDeSaisie;
	private final JPanel mainPanel = new JPanel();
	private final BorderLayout thisLayout = new BorderLayout();
	private GridLayout panelLayout;
	private final JPanel panelGauche = new JPanel();
	private final JPanel panelDroite = new JPanel();
	private JLabel errmsg = new JLabel();
    
    private int id = -1;

	/** Fenêtre parente **/
	private Window mainWindow;
    
    private String titleWindow = " titleWindow ";
    private String textWindow = " textWindow ";

    /**
     * Initialise les champs de saisie avec les valeurs présentes dans le bean.
     * @param databean
     * @author jerome forestier @ sqli
     * @date 4 oct. 2004
     */    
    public abstract void initInput(DatabeanGeneric databean);
    
    /**
     * Initialise les champs de saisie avec les valeurs par défaut (en général, a vide)
     * @author jerome forestier @ sqli
     * @date 4 oct. 2004
     */
    public abstract void initInput();
    
    /**
     * Récupere dans les composants d'interface, les valeurs saisies, et rempli un bean 
     * @return un héritier de DatabeanGeneric dont les champs contiennent les valeurs saisies
     * @author jerome forestier @ sqli
     * @date 4 oct. 2004
     */
    public abstract DatabeanGeneric populateDatabean();
    
    /**
     * Controle la validité des éléments saisis.
     * @param databean héritier de DatabeanGeneric a controler
     * @return null is tout est OK, sinon, une chaine d'erreur
     * @author jerome forestier @ sqli
     * @date 4 oct. 2004
     */
    public abstract String isDatabeanValid(DatabeanGeneric databean);

    /**
     * Constructeur
     * @param mainWindow fenêtre parente. Peut etre null
     * @param labels tableau contenant des instances de JLabel indiquant les libellés des champs de saisie
     * @param inputs tableau contenant des instances de JComponent qui sont les champs Swing de saisies
     */
	public DialogGeneric(
		Window mainWindow,
		JLabel[] labels,
		JComponent[] inputs)
	{
		if (labels.length != inputs.length)
			throw new IllegalStateException("Labels and Inputs must have same size");
		this.mainWindow = mainWindow;
		this.dialog_fields_input = inputs;
		this.dialog_fields_label = labels;
		nbChampsDeSaisie = dialog_fields_label.length;
		panelLayout = new GridLayout(nbChampsDeSaisie, 1);

		initGUI();
	}

    /**
     * Initialisation de l'interface.
     * Construit les panels et les layout, avec dedans les champs libellés et les champs de saisie
     * 
     * @author jerome forestier @ sqli
     * @date 4 oct. 2004
     */
	private void initGUI()
	{		
        mainPanel.setLayout(thisLayout);
        panelLayout.setRows(nbChampsDeSaisie);
        panelLayout.setColumns(1);
        panelGauche.setLayout(panelLayout);

        panelDroite.setLayout(panelLayout);

        // Ajout des composants
        for (int i = 0; i < nbChampsDeSaisie; i++)
        {
            panelGauche.add(dialog_fields_label[i]);
            panelDroite.add(dialog_fields_input[i]);
        }       

        mainPanel.add(panelGauche, BorderLayout.WEST);
        mainPanel.add(panelDroite, BorderLayout.CENTER);
        mainPanel.add(errmsg, BorderLayout.SOUTH);

        mainPanel.setAutoscrolls(true);
        mainPanel.setBorder(new TitledBorder(null, "--- placer le texte ici ---", TitledBorder.LEADING, TitledBorder.TOP));
		
	}
    
    /**
     * Initialisation des champs de saisie avec le bean.
     * Appel la methode abstraite <code>initInput</code>
     * @param databean
     * @author jerome forestier @ sqli
     * @see initInput
     * @date 4 oct. 2004
     */
    public void abstractInitInput(DatabeanGeneric databean)
    {
        this.id = -1;
        initInput(databean);
        if (this.id == -1) {
            System.err.println("Warning : databean " + databean + " doesn't have a unique id ?");
        }
    }
    
    /**
     * Initialisation des champs de saisie avec les valeurs par défaut
     * Appel la methode abstraite <code>initInput</code>
     * @author jerome forestier @ sqli
     * @date 4 oct. 2004
     */
    public void abstractInitInput()
    {
        this.id = -1;
        initInput();
    }
    
	/**
     * Affichage de la fenêtre de saisie.
     * Crée une boite de dialogue de saisie modal. Tant que l'utilisateur ne clique par sur Annuler
     * ou ne rentre pas des données valide, la boite est affichée.
	 * @return un héritier de DatabeanGeneric
	 * @author jerome forestier @ sqli
	 * @date 4 oct. 2004
	 */
	public DatabeanGeneric saisir()
	{
        try 
        {
            GUITools.setCursorWait(mainWindow);        
            abstractInitInput();            
            mainPanel.setBorder(
                new TitledBorder(
                    null, 
                    textWindow, 
                    TitledBorder.LEADING, 
                    TitledBorder.TOP));
            GUITools.setCursorNormal(mainWindow);
            while (true)
            {
                
                int value =
                    NiceDialogAlert.showMessageDialog(
                        mainWindow,
                        titleWindow,
                        mainPanel,
                        JOptionPane.INFORMATION_MESSAGE,
                        NiceDialogAlert.OK_BUTTON | NiceDialogAlert.CANCEL_BUTTON,
                        NiceDialogAlert.CANCEL_BUTTON);

                if (NiceDialogAlert.OK_BUTTON == value)
                {
                    // On a cliqué sur ok
                    DatabeanGeneric databean = populateDatabean();
                    String err = isDatabeanValid(databean);
                    if (null != err)
                    {
                        errmsg.setText(err);
                        errmsg.setForeground(new java.awt.Color(255, 0, 0));
                    } 
                    else
                    {                        
                        return databean;
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
	
    /**
     * @return l'id du bean
     * @author jerome forestier @ sqli
     * @date 4 oct. 2004
     */
    public int getId()
    {
        return id;
    }

    /**
     * @param i l'id du bean
     * @author jerome forestier @ sqli
     * @date 4 oct. 2004
     */
    public void setId(int i)
    {
        id = i;
    }

    /**
     * @return le texte de la fenêtre
     * @author jerome forestier @ sqli
     * @date 4 oct. 2004
     */
    public String getTextWindow()
    {
        return textWindow;
    }

    /**
     * @return le titre de la fenêtre
     * @author jerome forestier @ sqli
     * @date 4 oct. 2004
     */
    public String getTitleWindow()
    {
        return titleWindow;
    }

    /**
     * @param string le texte de la fenetre
     * @author jerome forestier @ sqli
     * @date 4 oct. 2004
     */
    public void setTextWindow(String string)
    {
        textWindow = string;
    }

    /**
     * @param string le titre de la fenetre
     * @author jerome forestier @ sqli
     * @date 4 oct. 2004
     */
    public void setTitleWindow(String string)
    {
        titleWindow = string;
    }

    /**
     * Affecte les composants de saisie
     * @param components
     * @author jerome forestier @ sqli
     * @date 4 oct. 2004
     */
    public void setDialog_fields_input(JComponent[] components)
    {
        dialog_fields_input = components;
    }

    /**
     * Affecte les libellés des champs de saisie
     * @param labels
     * @author jerome forestier @ sqli
     * @date 4 oct. 2004
     */
    public void setDialog_fields_label(JLabel[] labels)
    {
        dialog_fields_label = labels;
    }

}
