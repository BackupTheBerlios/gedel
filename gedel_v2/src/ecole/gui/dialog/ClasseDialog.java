/*
 * Created on 16 sept. 2004
 *
 * 
 */
package ecole.gui.dialog;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import ecole.databean.ClasseDatabean;
import ecole.gui.predefinedframe.NiceDialogAlert;
import ecole.gui.utils.GUITools;
import ecole.utils.StringTools;

/**
 * @author jemore
 */
public class ClasseDialog
{
    private final static JLabel[] allFieldLabels =
    {
        new JLabel("Nom de classe "),
        new JLabel("Instituteur"),
    };
    private JLabel errmsg = new JLabel();

    private int id = -1; // Conserve l'id de la classe
    private final JTextField classe_nom = new JTextField();
    private final JTextField instituteur = new JTextField();

    private final int nbChampsDeSaisie = 2;
    private final JPanel mainPanel = new JPanel();
    private final BorderLayout thisLayout = new BorderLayout();
    private final GridLayout panelLayout = new GridLayout(nbChampsDeSaisie, 1);
    private final JPanel panelGauche = new JPanel();
    private final JPanel panelDroite = new JPanel();


    private final static ClasseDialog instance = new ClasseDialog();

    private ClasseDialog()
    {
        initGUI();
    }

    public static ClasseDialog getInstance()
    {
        return instance;
    }

    /**
     * Construit la GUI (dans mainPanel)
     * 
     * @author jemore
     */
    private void initGUI()
    {
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
        panelDroite.add(classe_nom);
        panelDroite.add(instituteur);
        
        mainPanel.add(panelGauche, BorderLayout.WEST);
        mainPanel.add(panelDroite, BorderLayout.CENTER);
        mainPanel.add(errmsg, BorderLayout.SOUTH);

        mainPanel.setAutoscrolls(true);
        mainPanel.setBorder(
            new TitledBorder(
                null,
                "--- placer le texte ici ---",
                TitledBorder.LEADING,
                TitledBorder.TOP));                
    }
    
    private void initAllField()
    {
        id = -1;
        errmsg.setText(" ");
        classe_nom.setText("");
        instituteur.setText("");
    }
    
    private void initAllField(ClasseDatabean c)
    {
        errmsg.setText(" ");
        classe_nom.setText(c.getClasse_nom());
        instituteur.setText(c.getInstituteur());
        id = c.getId();
    }
    
    private ClasseDatabean populateClasseDatabean()
    {
        ClasseDatabean c = new ClasseDatabean();
        c.setId(id);
        c.setClasse_nom(classe_nom.getText().trim());
        c.setInstituteur(instituteur.getText().trim());
        return c;
    }
    
    private String isDatabeanValid(ClasseDatabean c)
    {
        if (StringTools.isEmptyStr(c.getClasse_nom()))
        {
            return "Indiquez un nom de classe (CE11, CM2B ...)";
        }
        else if (StringTools.isEmptyStr(c.getInstituteur()))
        {
            return "Indiquez un nom d'instituteur";
        }
        else
            return null;
    }
    
    public ClasseDatabean saisirClasse(Window mainWindow)
    {
        try
        {
            GUITools.setCursorWait(mainWindow);
            initAllField();
            String text = "Saisissez une nouvelle classe";
            String title = "Classe";
            mainPanel.setBorder(
                new TitledBorder(
                    null,
                    text,
                    TitledBorder.LEADING,
                    TitledBorder.TOP));

            GUITools.setCursorNormal(mainWindow);

            while (true)
            {

                int value =
                    NiceDialogAlert.showMessageDialog(
                        mainWindow,
                        title,
                        mainPanel,
                        JOptionPane.INFORMATION_MESSAGE,
                        NiceDialogAlert.OK_BUTTON
                            | NiceDialogAlert.CANCEL_BUTTON,
                        NiceDialogAlert.CANCEL_BUTTON);

                if (NiceDialogAlert.OK_BUTTON == value)
                {
                    // On a cliqué sur ok
                    ClasseDatabean c = populateClasseDatabean();
                    String err = isDatabeanValid(c);
                    if (null != err)
                    {
                        errmsg.setText(err);
                        errmsg.setForeground(new java.awt.Color(255, 0, 0));
                    } else
                    {
                        return c;
                    }
                } else
                    return null;
            }

        } finally
        {
            GUITools.setCursorNormal(mainWindow);
        }        
    }
    
    public ClasseDatabean modifClasse(Window mainWindow, ClasseDatabean c)
    {
        try
        {
            GUITools.setCursorWait(mainWindow);
            initAllField(c);
            String text = "Modifiez une classe";
            String title = "Classe";
            mainPanel.setBorder(
                new TitledBorder(
                    null,
                    text,
                    TitledBorder.LEADING,
                    TitledBorder.TOP));

            GUITools.setCursorNormal(mainWindow);

            while (true)
            {

                int value =
                    NiceDialogAlert.showMessageDialog(
                        mainWindow,
                        title,
                        mainPanel,
                        JOptionPane.INFORMATION_MESSAGE,
                        NiceDialogAlert.OK_BUTTON
                            | NiceDialogAlert.CANCEL_BUTTON,
                        NiceDialogAlert.CANCEL_BUTTON);

                if (NiceDialogAlert.OK_BUTTON == value)
                {
                    // On a cliqué sur ok
                    ClasseDatabean new_c = populateClasseDatabean();
                    String err = isDatabeanValid(new_c);
                    if (null != err)
                    {
                        errmsg.setText(err);
                        errmsg.setForeground(new java.awt.Color(255, 0, 0));
                    } else
                    {
                        return new_c;
                    }
                } else
                    return null;
            }

        } finally
        {
            GUITools.setCursorNormal(mainWindow);
        }        
    }    
}
