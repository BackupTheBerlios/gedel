/*
 * Created on 13 sept. 2004
 *
 */
package ecole.gui.predefinedframe;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * Classe utilitaire contenant des methodes pour faire apparaitre
 * des boites de dialogues modales de saisie.
 * 
 * @author jemore
 */
public final class DialogInput
{
    /**
     * Affiche une boite de dialogue contenant des zones de saisie de type texte,
     * éventuellement pré-rempli.
     * Si l'utilisateur clique sur Valider, le contenu des zones de saisies
     * sont retourné dans une List. Si il clique sur Annuler, la methode retourne
     * null.
     * Forme de la boite de dialogue :
     * <code>
     * [====Title================X]
     * [  /\                      ]
     * [ /? \  text               ]
     * [      questions[1] [____] ] 
     * [                          ]
     * [         [OK] [CANCEL]    ]
     * [__________________________]
     * </code>
     * @param mainWindow objet parent de la fenetre, ou null si la fenetre doit etre détaché (mais tjrs modal)
     * @param title Titre de la fenetre de dialogue
     * @param text Texte apparissant au dessus des zones de saisies
     * @param questions Tableau de question
     * @param defaultAnswer tableau des valeurs de pré-remplissages zones de saisie 
     * @return une List contenant le contenu des zone de saisie, ou Null si Annuler
     * @author jemore
     */
    public static final List AskUser(Window mainWindow, String title, String text, String[] questions, String[] defaultAnswer)
    {
        // La fenetre est composé de 2 panneaux (gauche et droite), avec un gridlayout dedans.
        // Les objets de saisie et les libellé sont ajoutés dynamiquement
        ArrayList res = new ArrayList();
        JPanel mainPanel = new JPanel();
        BorderLayout thisLayout = new BorderLayout();
        mainPanel.setLayout(thisLayout);

        GridLayout panelLayout = new GridLayout(questions.length, 1);
        panelLayout.setRows(questions.length);
        panelLayout.setColumns(1);

        JPanel panelGauche = new JPanel();
        panelGauche.setLayout(panelLayout);

        JPanel panelDroite = new JPanel();
        panelDroite.setLayout(panelLayout);

        for (int i = 0; i < questions.length; i++)
        {
            JLabel label_question = new JLabel(questions[i]);
            JTextField text_reponse = new JTextField(16);

            if (null != defaultAnswer && defaultAnswer.length != 0 && i < defaultAnswer.length)
            {

                text_reponse.setText(defaultAnswer[i]);
            }
            panelGauche.add(label_question);
            panelDroite.add(text_reponse);
        }
        mainPanel.add(panelGauche, BorderLayout.WEST);
        mainPanel.add(panelDroite, BorderLayout.CENTER);

        mainPanel.setAutoscrolls(true);
        mainPanel.setBorder(new TitledBorder(null, text, TitledBorder.LEADING, TitledBorder.TOP));
        // Affichage de la boite de dialogue
		int value = NiceDialogAlert.showMessageDialog(
			mainWindow,
			title,
			mainPanel,
			JOptionPane.QUESTION_MESSAGE,
			NiceDialogAlert.OK_BUTTON | NiceDialogAlert.CANCEL_BUTTON,
			NiceDialogAlert.CANCEL_BUTTON
		);
        // Recupération des résultats        
        if (NiceDialogAlert.OK_BUTTON == value)
        {
            res = new ArrayList(questions.length);
            for (int i = 0; i < questions.length; i++)
            {
                Component c = panelDroite.getComponent(i);
                String reponse = ((JTextField) c).getText();
                res.add(reponse);
            }
        }

        if (0 == res.size())
            return null;
        else
            return res;
    }

    public static void main(String[] argv)
    {
        try
        {

            List l =
                AskUser(
                    null,
                    "Le titre",
                    "Un texte un petit peu long passque faut remplir la fenetre",
                    new String[] { "Nom", "Prenom", "Des trucs longs", "ca va ?" },
                    new String[] { "FORESTIER", "Jerome" });
            System.out.println(l);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.exit(0);
    }

    /**
     * @param parentWindow
     * @param string
     * @param string2
     * @param combo
     * @param i
     * @author jemore
     */
    public static int AskUser(Window mainWindow, String title, String text, JComboBox combo, int i)
    {
        int res = -1;
        // La fenetre est composé de 2 panneaux (gauche et droite), avec un gridlayout dedans.
        // Les objets de saisie et les libellé sont ajoutés dynamiquement
        JPanel mainPanel = new JPanel();
        BorderLayout thisLayout = new BorderLayout();
        mainPanel.setLayout(thisLayout);
        mainPanel.setAutoscrolls(true);
        mainPanel.setBorder(new TitledBorder(null, text, TitledBorder.LEADING, TitledBorder.TOP));        
        mainPanel.add(combo);
        int value = NiceDialogAlert.showMessageDialog(
            mainWindow,
            title,
            mainPanel,
            JOptionPane.QUESTION_MESSAGE,
            NiceDialogAlert.OK_BUTTON | NiceDialogAlert.CANCEL_BUTTON,
            NiceDialogAlert.CANCEL_BUTTON
        );
        // Recupération des résultats        
        if (NiceDialogAlert.OK_BUTTON == value)
        {
            res = combo.getSelectedIndex();    
        }
        return res;       
    }
}
