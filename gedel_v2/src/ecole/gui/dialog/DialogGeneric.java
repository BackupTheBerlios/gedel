/*
 * Created on 4 oct. 2004
 *
 */
package ecole.gui.dialog;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import ecole.databean.DatabeanGeneric;

/**
 * Classe ancetre des boites de dialogues
 * 
 * @author jemore @ home
 */
public abstract class DialogGeneric
{
	/** Libellés des champs de saisie **/
	private JLabel[] dialog_fields_label;

	/** Composant de saisie **/
	private JComponent[] dialog_fields_input;

	// Objet nécessaires à l'affichage
	private int nbChampsDeSaisie;
	private final JPanel mainPanel = new JPanel();
	private final BorderLayout thisLayout = new BorderLayout();
	private GridLayout panelLayout;
	private final JPanel panelGauche = new JPanel();
	private final JPanel panelDroite = new JPanel();
	private JLabel errmsg = new JLabel();

	/** Fenêtre parente **/
	private Window mainWindow;

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
	
	public DatabeanGeneric saisir()
	{
		
	}
	
}
