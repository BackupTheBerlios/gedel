/*
 * Created on 10 sept. 2004
 *
 */
package ecole.gui.predefinedframe;

import java.awt.Component;

import javax.swing.JOptionPane;

/**
 * Cette classe utilitaire permet d'afficher des boites de dialogues modales
 * @author jemore
 */
public class DialogAlert
{
	/**
	 * Affiche une simple alerte, avec un bouton OK
	 * @param mainFrame
	 * @param message
	 * @author jemore
	 */
	public static void DialogOK(String message)
	{
		//JOptionPane.showMessageDialog(null, message);
		NiceDialogAlert.showMessageDialog(null, "", message, JOptionPane.INFORMATION_MESSAGE, NiceDialogAlert.OK_BUTTON, NiceDialogAlert.OK_BUTTON);
	}

	/**
	 * Affiche une simple alerte, avec un bouton OK
	 * @param titre
	 * @param message
	 * @author jemore
	 */
	public static void DialogOK(String titre, String message)
	{
       /*
		JOptionPane.showMessageDialog(
			null,
			message,
			titre,
			JOptionPane.INFORMATION_MESSAGE);
          */  
        NiceDialogAlert.showMessageDialog(
        	null, 
        	titre, 
        	message, 
        	JOptionPane.INFORMATION_MESSAGE, 
        	NiceDialogAlert.OK_BUTTON,
			NiceDialogAlert.OK_BUTTON);
	}

	/**
	 * Pose une question Oui / Non
	 * @param mainFrame
	 * @param message
	 * @return true si Oui, false si Non
	 * @author jemore
	 */
	public static boolean DialogYesNo(String message)
	{
		return (
			NiceDialogAlert.showMessageDialog(
				null, 
				"", 
				message, 
				JOptionPane.QUESTION_MESSAGE, 
				NiceDialogAlert.YES_BUTTON | NiceDialogAlert.NO_BUTTON, 
				NiceDialogAlert.OK_BUTTON) == NiceDialogAlert.YES_BUTTON);
	}

	/**
	 * Affiche un message puis les boutons Oui / Non / Annuler
	 * @param message
	 * @return int : JOptionPane.YES_OPTION, JOptionPane.NO_OPTION, JOptionPane.CANCEL_OPTION, JOption_Pane.CLOSED_OPTION
	 * @author jemore
	 */
	public static int DialogYesNoCancel(String message)
	{
		
		return 	NiceDialogAlert.showMessageDialog(
						null, 
						"", 
						message, 
						JOptionPane.QUESTION_MESSAGE, 
						NiceDialogAlert.YES_BUTTON | NiceDialogAlert.NO_BUTTON | NiceDialogAlert.CANCEL_BUTTON, 
						NiceDialogAlert.CANCEL_BUTTON);
	}
	
	/**
	 * Affiche un message d'erreur, et un bouton OK
	 * @param titre
	 * @param message
	 * @author jemore
	 */
	public static void DialogError(String titre, String message)
	{
		NiceDialogAlert.showMessageDialog(
								null, 
								titre, 
								message, 
								JOptionPane.ERROR_MESSAGE, 
								NiceDialogAlert.OK_BUTTON, 
								NiceDialogAlert.OK_BUTTON);

	}
    
    
    public static void DialogError(Component parentWindow, String titre, String message)
    {
		NiceDialogAlert.showMessageDialog(
			parentWindow, 
			titre, 
			message,
			JOptionPane.ERROR_MESSAGE,
			NiceDialogAlert.OK_BUTTON,
			NiceDialogAlert.OK_BUTTON);
    }
    /*
    public static int NiceDialogYesNoWarning(JFrame parentWindow, String message)
    {
        return NiceDialogAlert.showMessageDialog(   
            parentWindow, 
            "Attention", 
            message, 
            JOptionPane.WARNING_MESSAGE, 
            NiceDialogAlert.YES_BUTTON | NiceDialogAlert.NO_BUTTON );
    }
*/
	public static boolean DialogWarningYesNo(Component parentWindow, String message)
	 {
		 //JButton[] options = {new JButtonOK(),
							 //new JButtonAnnul()};					
		
		return NiceDialogAlert.showMessageDialog(   
					parentWindow, 
					"Attention !", 
					message, 
					JOptionPane.WARNING_MESSAGE, 
					NiceDialogAlert.YES_BUTTON | NiceDialogAlert.NO_BUTTON,
					NiceDialogAlert.NO_BUTTON ) == NiceDialogAlert.YES_BUTTON;	
	 }

	/**
	 * @param app
	 * @param res
	 * @author jemore
	 */
	public static void DialogOK(Component parentWindow, String message)
	{
		NiceDialogAlert.showMessageDialog(
			parentWindow, 
			"", 
			message, 
			JOptionPane.INFORMATION_MESSAGE, 
			NiceDialogAlert.OK_BUTTON,
			NiceDialogAlert.OK_BUTTON);
	}

	/**
	 * @param config
	 * @param string
	 * @return
	 * @author jemore
	 */
	public static boolean DialogYesNo(Component parentWindow, String message)
	{
		return NiceDialogAlert.showMessageDialog(
			parentWindow, 
			"", 
			message, 
			JOptionPane.INFORMATION_MESSAGE, 
			NiceDialogAlert.YES_BUTTON | NiceDialogAlert.NO_BUTTON,
			NiceDialogAlert.NO_BUTTON) == NiceDialogAlert.YES_BUTTON;
	}

}
