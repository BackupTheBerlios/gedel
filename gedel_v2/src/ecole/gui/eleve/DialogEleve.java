package ecole.gui.eleve;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import ecole.Constants;
import ecole.databean.ClasseDatabean;
import ecole.databean.EleveDatabean;
import ecole.datametier.ClassesMetier;
import ecole.gui.elements.JEcoleDateChooser;
import ecole.gui.predefinedframe.NiceDialogAlert;
import ecole.gui.utils.ComboBoxFiller;
import ecole.gui.utils.GUITools;
import ecole.utils.DateTools;
import ecole.utils.StringTools;

/**
 *
 * Fenetre de saisie d'un élève. Utilisé pour  la creation, la consultation et la modification
 * d'une fichier élève.
 * Cette classe est un singleton.
 * <code>
 * DialogEleve d = DialogEleve.getInstance();
 * d.saisirEleve();
 * </code>
 * @pattern singleton
 * @author jemore
 *
 */
public final class DialogEleve
{

	private final static JLabel[] allFieldLabels =
		{
			new JLabel("Nom"),
			new JLabel("Prénom"),
			new JLabel("Sexe"),
			new JLabel("Date de naissance"),
			new JLabel("Adresse"),
			new JLabel("Code postal"),
			new JLabel("Ville"),
			new JLabel("Téléphone (1)"),
			new JLabel("Téléphone (2)"),
			new JLabel("Téléphone (3)"),
			new JLabel("Classe"),
			new JLabel("Date d'entrée"),
			};
	private final static DialogEleve instance = new DialogEleve();

	private DialogEleve()
	{
		initGUI();
	}

	public static DialogEleve getInstance()
	{
		return instance;
	}

	private JLabel errmsg = new JLabel();

	private int id = -1; // Conserve l'id de l'éleve
	private final JTextField nom = new JTextField();
	private final JTextField prenom = new JTextField();
	private final JRadioButton sex_h = new JRadioButton("Garçon");
	private final JRadioButton sex_f = new JRadioButton("Fille");
	private final JEcoleDateChooser dob =
		new JEcoleDateChooser(
			new JCalendar(
				DateTools.dateStringToDate(DateTools.SDF_Y4M2D2, "19980101")));
	private final JTextField rue = new JTextField();
	private final JTextField codepostal = new JTextField(5);
	private final JTextField ville = new JTextField();
	private final JTextField telephone1 = new JTextField(10);
	private final JTextField telephone2 = new JTextField(10);
	private final JTextField telephone3 = new JTextField(10);
	private final JComboBox classes = new JComboBox();
	private final JEcoleDateChooser dateentree =
		new JEcoleDateChooser(
			new JCalendar(
				DateTools.dateStringToDate(DateTools.SDF_Y4M2D2, "20040109")));
	private final int nbChampsDeSaisie = 12;
	private final JPanel mainPanel = new JPanel();
	private final BorderLayout thisLayout = new BorderLayout();
	private final GridLayout panelLayout = new GridLayout(nbChampsDeSaisie, 1);
	private final JPanel panelGauche = new JPanel();
	private final JPanel panelDroite = new JPanel();

	List listClassesDisponibles = null;

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

		panelDroite.add(nom);
		panelDroite.add(prenom);
		{
			// Panneau du radio bouton Garcon/Fille
			ButtonGroup but = new ButtonGroup();
			but.add(sex_h);
			but.add(sex_f);
			JPanel panel = new JPanel();
			panel.add(sex_h);
			panel.add(sex_f);
			panelDroite.add(panel);
		}

		panelDroite.add(dob);
		panelDroite.add(rue);
		panelDroite.add(codepostal);
		panelDroite.add(ville);
		panelDroite.add(telephone1);
		panelDroite.add(telephone2);
		panelDroite.add(telephone3);
		panelDroite.add(classes);
		panelDroite.add(dateentree);

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

	private void initAllField() throws SQLException, NoSuchMethodException
	{
        id = -1;
		errmsg.setText(" ");
		// Un espace pour rendre visible la la zone du composant
		nom.setText("");
		prenom.setText("");
		sex_h.setSelected(false);
		sex_f.setSelected(false);
		rue.setText("");
		codepostal.setText("");
		ville.setText("");
		telephone1.setText("");
		telephone2.setText("");
		telephone3.setText("");

		// On garde les dob et dateentree a la dernier valeur affichée

		// Remplissage des composants
		ClassesMetier metier = new ClassesMetier();
		listClassesDisponibles = metier.getAll();
		try
		{
			ComboBoxFiller.addItems(
				classes,
				listClassesDisponibles,
				metier.getClass(),
				"getClasseNom");
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new NoSuchMethodException("Impossible d'apeler la methode de remplissage de la combo box");
		}
	}

	/**
	 * Pré-rempli les champs avec le contenu bean
	 * @param e EleveDatabean 
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @author jemore
	 */
	private void initAllField(EleveDatabean e)
		throws SQLException, NoSuchMethodException
	{
		id = e.getId();
		errmsg.setText(" ");
		// Un espace pour rendre visible la la zone du composant
		nom.setText(e.getNom());
		prenom.setText(e.getPrenom());
		sex_h.setSelected(e.getSexe() == EleveDatabean.SEXE_H);
		sex_f.setSelected(e.getSexe() == EleveDatabean.SEXE_F);
		rue.setText(e.getRue());
		codepostal.setText(e.getCodepostal());
		ville.setText(e.getVille());
		telephone1.setText(e.getTelephone1());
		telephone2.setText(e.getTelephone3());
		telephone3.setText(e.getTelephone3());
		if (e.getDob() != null)
			dob.setDate(e.getDob());
		if (e.getDateentree() != null)
			dateentree.setDate(e.getDateentree());
		ClassesMetier metier = new ClassesMetier();
		listClassesDisponibles = metier.getAll();
		try
		{
			ComboBoxFiller.addItems(
				classes,
				listClassesDisponibles,
				metier.getClass(),
				"getClasseNom");
		} catch (Exception ex)
		{
			ex.printStackTrace();
			throw new NoSuchMethodException("Impossible d'appeler la methode de remplissage de la combo box");
		}
		int idx =
			metier.getIdxOfClasseInList(
				listClassesDisponibles,
				e.getClasseid());
		classes.setSelectedIndex(idx);

	}

	/**
	 * Affiche la boite de dialogue de saisie d'un élève
	 * @param mainWindow fenêtre parent ou null
	 * @return un EleveDatabean si OK, ou null si Annuler
	 * @throws NoSuchMethodException si le remplissage de la combo de la classe a echouée
	 * @throws SQLException si pb de base de donnée pour aller chercher les classes
	 * @author jemore
	 */
	public EleveDatabean saisirEleve(Window mainWindow)
		throws NoSuchMethodException, SQLException
	{
		try
		{
			GUITools.setCursorWait(mainWindow);
			initAllField();
			String text = "Saisissez un nouvel élève";
			String title = "Fiche élève";
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
					EleveDatabean e = populateEleveDatabean();
					String err = isDatabeanValid(e);
					if (null != err)
					{
						errmsg.setText(err);
						errmsg.setForeground(new java.awt.Color(255, 0, 0));
					} else
					{
						return e;
					}
				} else
					return null;
			}

		} finally
		{
			GUITools.setCursorNormal(mainWindow);
		}
	}

	/**
	 * Verifie qu'un databean soit correctement renseigné
	 * @param e databean a verifier
	 * @return une String représentant l'erreur ou null si il n'y a pas d'erreur
	 * @author jemore
	 */
	private String isDatabeanValid(EleveDatabean e)
	{
		if (StringTools.isEmptyStr(e.getNom()))
			return "Vous devez saisir un nom";
		if (e.getSexe() == '\0')
			return "Vous devez saisir le sexe de l'élève :-) ";
		return null;
	}

	/**
	 * @return
	 * @author jemore
	 */
	private EleveDatabean populateEleveDatabean()
	{
		EleveDatabean e = new EleveDatabean();
		e.setId(id);
		e.setNom(nom.getText().trim());
		e.setPrenom(prenom.getText().trim());
		if (sex_f.isSelected())
			e.setSexe(EleveDatabean.SEXE_F);
		else if (sex_h.isSelected())
			e.setSexe(EleveDatabean.SEXE_H);
		e.setDob(dob.getDate());
		e.setRue(rue.getText().trim());
		e.setCodepostal(codepostal.getText().trim());
		e.setVille(ville.getText().trim());
		e.setTelephone1(telephone1.getText().trim());
		e.setTelephone2(telephone2.getText().trim());
		e.setTelephone3(telephone3.getText().trim());
		int classe_selected = classes.getSelectedIndex();
		ClasseDatabean c =
			(ClasseDatabean) listClassesDisponibles.get(classe_selected);
		//e.setClasse_nom(c.getClasse_nom());
		e.setClasseid(c.getId());
		e.setDateentree(dateentree.getDate());
		return e;
	}

	/**
	* Affiche la boite de dialogue de saisie d'un élève, pré rempli avec le <code>EleveDatabean e</code>
	* @param mainWindow fenêtre parent ou null
	* @return un EleveDatabean si OK, ou null si Annuler
	* @throws NoSuchMethodException si le remplissage de la combo de la classe a echouée
	* @throws SQLException si pb de base de donnée pour aller chercher les classes
	* @author jemore
	* @param mainWindow fenêtre parente
	* @param e EleveDatabean conteant les champs pré rempli
	* @return le bean ou null si on a annuler
	* @author jemore
	*/
	public EleveDatabean modifEleve(Window mainWindow, EleveDatabean e)
		throws SQLException, NoSuchMethodException
	{
		try
		{
			GUITools.setCursorWait(mainWindow);
			initAllField(e);
			String text = "Modifiez un nouvel élève";
			String title = "Fiche élève";

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
					EleveDatabean new_e = populateEleveDatabean();
					String err = isDatabeanValid(new_e);
					if (null != err)
					{
						errmsg.setText(err);
						errmsg.setForeground(new java.awt.Color(255, 0, 0));
					} else
					{
						return new_e;
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
