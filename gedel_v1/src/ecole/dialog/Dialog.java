/*
 * Created on 24 sept. 2004
 *
 * 
 */
package ecole.dialog;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

/**
 * Classe utilitaire contenant des methodes statiques
 * pour l'affichage de boite de dialogue
 * @author JFORESTIER
 */
public class Dialog
{
    /**
     * Constructeur privé. Cette classe utilitaire ne peut
     * pas etre instanciée.
     */
    private Dialog()
    {
    }
    
    /**
     * Affiche un avertissement correspondant a une erreur de saisie
     * @param parentComponent fenetre parente. Peut etre null
     * @param message a afficher
     * @author JFORESTIER
     */
    public static void ErreurDeSaisie(Component parentComponent, String message)
    {
        JOptionPane.showMessageDialog(parentComponent, message, "Erreur de saisie", JOptionPane.WARNING_MESSAGE);       
    }

    /**
     * Affichage d'une boite de saisie demandant le nom de la sauvegarde.
     * La zone de texte est prérempli avec le nom du mois + l'année (par rapport à la Locale).
     * La boite de saisie boucle tant qu'un nom n'est pas saisie, ou clique sur Annul.
     * @param parentComponent fenetre parente. Peut etre null
     * @return Le nom de la sauvegarde, != de "", ou null si annuler.
     * @author JFORESTIER
     */
    public static String EntrezLeNomDeLaSauvegarde(Component parentComponent)
    {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMMMM yyyy");
        String proposition = sdf.format(now);
        String saisie = proposition;
        while (true)
        {
            saisie = (String) JOptionPane.showInputDialog(parentComponent, "Entrez le nom de la sauvegarde (Ex:" + proposition + "):", saisie);
            if (null == saisie)
                return null;
            saisie = saisie.trim();
            if ("".equals(saisie))
            {
                ErreurDeSaisie(parentComponent, "Vous devez entrer un nom valide");
                saisie = proposition;
            }
            else
            {
                break;
            }
        }
        return saisie;
    }
}
