package ecole.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DebugGraphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
import com.jgoodies.plaf.FontSizeHints;
import com.jgoodies.plaf.Options;

import ecole.databean.CantineDatabean;
import ecole.databean.ClasseDatabean;
import ecole.databean.EleveDatabean;
import ecole.datametier.AteliersMetier;
import ecole.datametier.CantineMetier;
import ecole.datametier.ClassesMetier;
import ecole.datametier.ElevesMetier;
import ecole.datametier.GenericMetier;
import ecole.datametier.TarifsAteliersMetier;
import ecole.datametier.TarifsCantinesMetier;
import ecole.db.DatabaseConnection;
import ecole.gui.cantine.DialogCantine;
import ecole.gui.classe.DialogClasse;
import ecole.gui.config.DialogConfig;
import ecole.gui.eleve.DialogEleve;
import ecole.gui.eleve.FicheEleve;
import ecole.gui.listes.EleveTable;
import ecole.gui.predefinedframe.DialogAlert;
import ecole.gui.predefinedframe.FrameException;
import ecole.gui.predefinedframe.SplashScreen;
import ecole.gui.utils.Callbacker;
import ecole.gui.utils.ComboBoxFiller;
import ecole.gui.utils.GUITools;
import ecole.utils.logger.Logger;


/**
* This code was generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* *************************************
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED
* for this machine, so Jigloo or this code cannot be used legally
* for any corporate or commercial purpose.
* *************************************
*/
/**
* This code was generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a
* for-profit company or business) then you should purchase
* a license - please visit www.cloudgarden.com for details.
*/

public class EcoleApp extends javax.swing.JFrame
{
	private JMenuItem menuLnF;
	private JMenuItem menutOutilsBD;
	private JMenuItem menuAideJava;
	private JMenuItem menuOutilsGC;
	private JSeparator jSeparator8;
	private JMenuItem menuCantineTarifSuppr;
	private JMenuItem menuCantineTarifModif;
	private JMenuItem menuCantineTarifNew;
	private JMenu jMenu2;
	private JSeparator jSeparator7;
	private JMenuItem menuCantineListeEleve;
	private JMenuItem menuCantineAjouter;
	private JMenuItem menuAtelierTarifSuppr;
	private JMenuItem menuAtelierTarifModif;
	private JMenuItem menuAtelierTarifNew;
	private JMenu jMenu1;
	private JSeparator jSeparator6;
	private JMenuItem menuAtelierListeEleve;
	private JSeparator jSeparator5;
	private JMenuItem menuAtelierSuppr;
	private JSeparator jSeparator4;
	private JMenuItem menuAtelierModif;
	private JMenuItem menuAtelierNew;
	private JMenuItem menuClasseListeEleveAtelier;
	private JMenuItem menuClasseListeEleve;
	private JSeparator jSeparator3;
	private JMenuItem menuClasseSuppr;
	private JMenuItem menuClasseModif;
	private JMenuItem menuClassesNew;
	private JMenuItem menuElevesInscrire;
	private JMenuItem menuElevesSupprimer;
	private JMenuItem menuElevesModif;
	private JMenuItem menuElevesAjouter;
	private JMenuItem menuColler;
	private JMenuItem menuCouper;
	private JMenuItem menuCopier;
	private JSeparator jSeparator2;
	private JMenuItem menuApercu;
	private JMenuItem menuImprimer;
	private JSeparator jSeparator1;
	public JProgressBar jProgressBar;
	private JMenuItem menuReinit;
	private JButton bTarifsAteliersSupprTarif;
	private JButton bTarifsAteliersModifTarif;
	private JComboBox cbTarifsAteliers;
	private JPanel panelTarifsAteliers;
	private JButton jButton6;
	private JButton jButton5;
	private JComboBox cbAteliers;
	private JPanel panelAteliers;
	private JButton bTarifCantineSuppr;
	private JButton bTarifsCantineModif;
	private JComboBox cbTarifsCantine;
	private JPanel panelTarifsCantine;
	private JButton bClassesListeAtelier;
	private JButton bClassesListeEleves;
	private JComboBox cbClasses;
	private JPanel panelClasses;
	private JButton bElevesCantine;
	private JButton bElevesAtelier;
	private JComboBox cbEleves;
	private JPanel panelEleves;
	private JPanel jPanelGauche;
	private JMenu menuAide;
	private JMenu menuOutils;
	private JMenu menuGestion;
	private JMenu menuCantine;
	private JMenu menuAteliers;
	private JMenu menuClasses;
	private JMenu menuEleves;
	private JLabel jStatusBar;
	private JMenuItem menuQuitter;
	private JScrollPane jScrollPanDroite;
	private JScrollPane jScrollPanGauche;
	private JSplitPane jSplitPane1;
	private JPanel jPanel2;
	private JToolBar jToolBar;
	private JPanel jPanel1;
	private JMenu menuEdition;
	private JMenu menuFichier;
	private JMenuBar jMenuBar1;
	public EcoleApp()
	{
		initGUI();
	}

	/**
	 * Données présentes dans les liste déroulantes
	 */
	private List listEleves;
    private List listClasses;

	/**
	* Initializes the GUI.
	* Auto-generated code - any changes you make will disappear.
	*/
	public void initGUI(){
		try {
			preInitGUI();
	
			jPanel1 = new JPanel();
			jStatusBar = new JLabel();
			jProgressBar = new JProgressBar();
			jToolBar = new JToolBar();
			jPanel2 = new JPanel();
			jSplitPane1 = new JSplitPane();
			jScrollPanGauche = new JScrollPane();
			jPanelGauche = new JPanel();
			panelEleves = new JPanel();
			cbEleves = new JComboBox();
			bElevesAtelier = new JButton();
			bElevesCantine = new JButton();
			panelClasses = new JPanel();
			cbClasses = new JComboBox();
			bClassesListeEleves = new JButton();
			bClassesListeAtelier = new JButton();
			panelAteliers = new JPanel();
			cbAteliers = new JComboBox();
			jButton5 = new JButton();
			jButton6 = new JButton();
			panelTarifsAteliers = new JPanel();
			cbTarifsAteliers = new JComboBox();
			bTarifsAteliersModifTarif = new JButton();
			bTarifsAteliersSupprTarif = new JButton();
			panelTarifsCantine = new JPanel();
			cbTarifsCantine = new JComboBox();
			bTarifsCantineModif = new JButton();
			bTarifCantineSuppr = new JButton();
			jScrollPanDroite = new JScrollPane();
	
			GridLayout thisLayout = new GridLayout(1,1);
			this.getContentPane().setLayout(thisLayout);
			thisLayout.setHgap(0);
			thisLayout.setVgap(0);
			thisLayout.setRows(1);
			thisLayout.setColumns(1);
			this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			this.setDefaultLookAndFeelDecorated(false);
			this.setTitle("GEDEL - Gestion Des Elèves");
			this.setName("Gestion des élèves");
			this.setSize(new java.awt.Dimension(627,587));
			this.addWindowListener( new WindowAdapter() {
				public void windowClosing(WindowEvent evt) {
					EcoleAppWindowClosing(evt);
				}
			});
			this.addComponentListener( new ComponentAdapter() {
				public void componentShown(ComponentEvent evt) {
					EcoleAppComponentShown(evt);
				}
			});
	
			BorderLayout jPanel1Layout = new BorderLayout();
			jPanel1.setLayout(jPanel1Layout);
			jPanel1Layout.setHgap(0);
			jPanel1Layout.setVgap(0);
			this.getContentPane().add(jPanel1);
	
			BorderLayout jStatusBarLayout = new BorderLayout();
			jStatusBar.setLayout(jStatusBarLayout);
			jStatusBarLayout.setHgap(0);
			jStatusBarLayout.setVgap(0);
			jStatusBar.setText("jLabel1");
			jStatusBar.setBorder(new EtchedBorder(BevelBorder.LOWERED, null, null));
			jStatusBar.setName("statusBar");
			jPanel1.add(jStatusBar, BorderLayout.SOUTH);
	
			jProgressBar.setValue(0);
			jProgressBar.setStringPainted(false);
			jProgressBar.setIndeterminate(false);
			jProgressBar.setPreferredSize(new java.awt.Dimension(88,15));
			jProgressBar.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
			jProgressBar.setDoubleBuffered(false);
			jStatusBar.add(jProgressBar, BorderLayout.EAST);
	
			jToolBar.setPreferredSize(new java.awt.Dimension(426,21));
			jPanel1.add(jToolBar, BorderLayout.NORTH);
	
			BorderLayout jPanel2Layout = new BorderLayout();
			jPanel2.setLayout(jPanel2Layout);
			jPanel2Layout.setHgap(0);
			jPanel2Layout.setVgap(0);
			jPanel1.add(jPanel2, BorderLayout.CENTER);
	
			jSplitPane1.setDividerLocation(250);
			jPanel2.add(jSplitPane1, BorderLayout.CENTER);
	
			jScrollPanGauche.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			jScrollPanGauche.setBackground(new java.awt.Color(128,128,128));
			jScrollPanGauche.setPreferredSize(new java.awt.Dimension(418,118));
			jSplitPane1.add(jScrollPanGauche, JSplitPane.LEFT);
	
			AnchorLayout jPanelGaucheLayout = new AnchorLayout();
			jPanelGauche.setLayout(jPanelGaucheLayout);
			jPanelGauche.setPreferredSize(new java.awt.Dimension(195,473));
			jScrollPanGauche.add(jPanelGauche);
			jScrollPanGauche.setViewportView(jPanelGauche);
	
			GridBagLayout panelElevesLayout = new GridBagLayout();
			panelEleves.setLayout(panelElevesLayout);
			panelElevesLayout.columnWidths = new int[] {1,1};
			panelElevesLayout.rowHeights = new int[] {1,1};
			panelElevesLayout.columnWeights = new double[] {0.1,0.1};
			panelElevesLayout.rowWeights = new double[] {0.1,0.1};
			panelEleves.setVisible(true);
			panelEleves.setPreferredSize(new java.awt.Dimension(240,80));
			panelEleves.setBorder(new TitledBorder(null, "Elèves", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("MS Sans Serif",0,11), new java.awt.Color(0,0,0)));
			panelEleves.setBounds(new java.awt.Rectangle(0,0,410,80));
			jPanelGauche.add(panelEleves, new AnchorConstraint(0,5, 317, 0, 2, 2, 0, 2));
	
			cbEleves.setMaximumRowCount(20);
			panelEleves.add(cbEleves, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, 17, 2, new Insets(0, 5, 5, 5), 0, 0));
			cbEleves.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					cbElevesActionPerformed(evt);
				}
			});
	
			bElevesAtelier.setText("Atelier");
			bElevesAtelier.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/atelier.gif")));
			bElevesAtelier.setHorizontalTextPosition(SwingConstants.TRAILING);
			panelEleves.add(bElevesAtelier, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, 17, 0, new Insets(0, 5, 5, 5), 0, 0));
	
			bElevesCantine.setText("Cantine");
			bElevesCantine.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/cantine.gif")));
			bElevesCantine.setOpaque(false);
			bElevesCantine.setDoubleBuffered(true);
			bElevesCantine.setName("");
			panelEleves.add(bElevesCantine, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, 17, 0, new Insets(0, 5, 5, 5), 0, 0));
			bElevesCantine.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					bElevesCantineActionPerformed(evt);
				}
			});
	
			GridBagLayout panelClassesLayout = new GridBagLayout();
			panelClasses.setLayout(panelClassesLayout);
			panelClassesLayout.columnWidths = new int[] {1,1};
			panelClassesLayout.rowHeights = new int[] {1,1};
			panelClassesLayout.columnWeights = new double[] {0.1,0.1};
			panelClassesLayout.rowWeights = new double[] {0.1,0.1};
			panelClasses.setVisible(true);
			panelClasses.setPreferredSize(new java.awt.Dimension(240,90));
			panelClasses.setBorder(new TitledBorder(null, "Classes", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("MS Sans Serif",0,11), new java.awt.Color(0,0,0)));
			panelClasses.setBounds(new java.awt.Rectangle(0,83,407,90));
			jPanelGauche.add(panelClasses, new AnchorConstraint(170,981, 1001, 2, 1, 1, 0, 1));
	
			cbClasses.setVisible(true);
			panelClasses.add(cbClasses, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
	
			bClassesListeEleves.setText("Liste élèves");
			bClassesListeEleves.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/eleve.gif")));
			panelClasses.add(bClassesListeEleves, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
			bClassesListeEleves.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					bClassesListeElevesActionPerformed(evt);
				}
			});
	
			bClassesListeAtelier.setText("Liste ateliers");
			bClassesListeAtelier.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/atelier.gif")));
			bClassesListeAtelier.setHorizontalTextPosition(SwingConstants.TRAILING);
			panelClasses.add(bClassesListeAtelier, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
	
			GridBagLayout panelAteliersLayout = new GridBagLayout();
			panelAteliers.setLayout(panelAteliersLayout);
			panelAteliersLayout.columnWidths = new int[] {1,1};
			panelAteliersLayout.rowHeights = new int[] {1,1};
			panelAteliersLayout.columnWeights = new double[] {0.1,0.1};
			panelAteliersLayout.rowWeights = new double[] {0.1,0.1};
			panelAteliers.setVisible(true);
			panelAteliers.setPreferredSize(new java.awt.Dimension(240,90));
			panelAteliers.setBorder(new TitledBorder(null, "Ateliers", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("MS Sans Serif",0,11), new java.awt.Color(0,0,0)));
			panelAteliers.setBounds(new java.awt.Rectangle(0,176,407,90));
			jPanelGauche.add(panelAteliers, new AnchorConstraint(360,981, 1001, 2, 1, 1, 0, 1));
	
			cbAteliers.setVisible(true);
			panelAteliers.add(cbAteliers, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
	
			jButton5.setText("Modifier atelier");
			jButton5.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/atelier_modif.gif")));
			panelAteliers.add(jButton5, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
	
			jButton6.setText("Liste d'élèves");
			jButton6.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/eleve.gif")));
			panelAteliers.add(jButton6, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
	
			GridBagLayout panelTarifsAteliersLayout = new GridBagLayout();
			panelTarifsAteliers.setLayout(panelTarifsAteliersLayout);
			panelTarifsAteliersLayout.columnWidths = new int[] {1,1};
			panelTarifsAteliersLayout.rowHeights = new int[] {1,1};
			panelTarifsAteliersLayout.columnWeights = new double[] {0.1,0.1};
			panelTarifsAteliersLayout.rowWeights = new double[] {0.1,0.1};
			panelTarifsAteliers.setVisible(true);
			panelTarifsAteliers.setPreferredSize(new java.awt.Dimension(240,90));
			panelTarifsAteliers.setBorder(new TitledBorder(null, "Tarifs Ateliers", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("MS Sans Serif",0,11), new java.awt.Color(0,0,0)));
			panelTarifsAteliers.setBounds(new java.awt.Rectangle(0,270,407,90));
			jPanelGauche.add(panelTarifsAteliers, new AnchorConstraint(550,981, 1001, 2, 1, 1, 0, 1));
	
			cbTarifsAteliers.setVisible(true);
			panelTarifsAteliers.add(cbTarifsAteliers, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
	
			bTarifsAteliersModifTarif.setText("Modif. tarif");
			bTarifsAteliersModifTarif.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/tarif_modif.gif")));
			panelTarifsAteliers.add(bTarifsAteliersModifTarif, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
	
			bTarifsAteliersSupprTarif.setText("Suppr. tarif");
			bTarifsAteliersSupprTarif.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/tarif_suppr.gif")));
			panelTarifsAteliers.add(bTarifsAteliersSupprTarif, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
	
			GridBagLayout panelTarifsCantineLayout = new GridBagLayout();
			panelTarifsCantine.setLayout(panelTarifsCantineLayout);
			panelTarifsCantineLayout.columnWidths = new int[] {1,1};
			panelTarifsCantineLayout.rowHeights = new int[] {1,1};
			panelTarifsCantineLayout.columnWeights = new double[] {0.1,0.1};
			panelTarifsCantineLayout.rowWeights = new double[] {0.1,0.1};
			panelTarifsCantine.setPreferredSize(new java.awt.Dimension(240,90));
			panelTarifsCantine.setBorder(new TitledBorder(null, "Tarifs Cantine", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("MS Sans Serif",0,11), new java.awt.Color(0,0,0)));
			panelTarifsCantine.setBounds(new java.awt.Rectangle(0,363,407,90));
			jPanelGauche.add(panelTarifsCantine, new AnchorConstraint(741,981, 1001, 2, 1, 1, 0, 1));
	
			cbTarifsCantine.setVisible(true);
			panelTarifsCantine.add(cbTarifsCantine, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
	
			bTarifsCantineModif.setText("Modif. tarif");
			bTarifsCantineModif.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/tarif_modif.gif")));
			panelTarifsCantine.add(bTarifsCantineModif, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
	
			bTarifCantineSuppr.setText("Suppr. tarif");
			bTarifCantineSuppr.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/tarif_suppr.gif")));
			panelTarifsCantine.add(bTarifCantineSuppr, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
	
			jScrollPanDroite.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			jScrollPanDroite.setPreferredSize(new java.awt.Dimension(149,126));
			jSplitPane1.add(jScrollPanDroite, JSplitPane.RIGHT);
			jMenuBar1 = new JMenuBar();
			menuFichier = new JMenu();
			menuImprimer = new JMenuItem();
			menuApercu = new JMenuItem();
			jSeparator2 = new JSeparator();
			menuReinit = new JMenuItem();
			jSeparator1 = new JSeparator();
			menuQuitter = new JMenuItem();
			menuEdition = new JMenu();
			menuCopier = new JMenuItem();
			menuCouper = new JMenuItem();
			menuColler = new JMenuItem();
			menuEleves = new JMenu();
			menuElevesAjouter = new JMenuItem();
			menuElevesModif = new JMenuItem();
			menuElevesSupprimer = new JMenuItem();
			jSeparator4 = new JSeparator();
			menuElevesInscrire = new JMenuItem();
			menuClasses = new JMenu();
			menuClassesNew = new JMenuItem();
			menuClasseModif = new JMenuItem();
			menuClasseSuppr = new JMenuItem();
			jSeparator3 = new JSeparator();
			menuClasseListeEleve = new JMenuItem();
			menuClasseListeEleveAtelier = new JMenuItem();
			menuAteliers = new JMenu();
			menuAtelierNew = new JMenuItem();
			menuAtelierModif = new JMenuItem();
			menuAtelierSuppr = new JMenuItem();
			jSeparator5 = new JSeparator();
			menuAtelierListeEleve = new JMenuItem();
			jSeparator6 = new JSeparator();
			jMenu1 = new JMenu();
			menuAtelierTarifNew = new JMenuItem();
			menuAtelierTarifModif = new JMenuItem();
			menuAtelierTarifSuppr = new JMenuItem();
			menuCantine = new JMenu();
			menuCantineAjouter = new JMenuItem();
			jSeparator8 = new JSeparator();
			menuCantineListeEleve = new JMenuItem();
			jSeparator7 = new JSeparator();
			jMenu2 = new JMenu();
			menuCantineTarifNew = new JMenuItem();
			menuCantineTarifModif = new JMenuItem();
			menuCantineTarifSuppr = new JMenuItem();
			menuGestion = new JMenu();
			menuOutils = new JMenu();
			menuOutilsGC = new JMenuItem();
			menutOutilsBD = new JMenuItem();
			menuLnF = new JMenuItem();
			menuAide = new JMenu();
			menuAideJava = new JMenuItem();
	
			setJMenuBar(jMenuBar1);
	
			menuFichier.setText("Fichier");
			menuFichier.setDisplayedMnemonicIndex(0);
			menuFichier.setDoubleBuffered(true);
			jMenuBar1.add(menuFichier);
	
			menuImprimer.setText("Imprimer");
			menuImprimer.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/print.gif")));
			menuImprimer.setVisible(true);
			menuFichier.add(menuImprimer);
	
			menuApercu.setText("Aperçu avant impression");
			menuApercu.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/PrintPreview.gif")));
			menuApercu.setVisible(true);
			menuFichier.add(menuApercu);
	
			jSeparator2.setVisible(true);
			menuFichier.add(jSeparator2);
	
			menuReinit.setText("Ré-initialiser l'interface");
			menuReinit.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/reinit.gif")));
			menuReinit.setVisible(true);
			menuFichier.add(menuReinit);
			menuReinit.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					menuReinitActionPerformed(evt);
				}
			});
	
			jSeparator1.setVisible(true);
			menuFichier.add(jSeparator1);
	
			menuQuitter.setText("Quitter");
			menuQuitter.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/quitter.gif")));
			menuQuitter.setVisible(true);
			menuFichier.add(menuQuitter);
			menuQuitter.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					menuQuitterActionPerformed(evt);
				}
			});
	
			menuEdition.setText("Edition");
			menuEdition.setDisplayedMnemonicIndex(0);
			jMenuBar1.add(menuEdition);
	
			menuCopier.setText("Copier");
			menuCopier.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/copy.gif")));
			menuEdition.add(menuCopier);
	
			menuCouper.setText("Couper");
			menuCouper.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/cut.gif")));
			menuEdition.add(menuCouper);
	
			menuColler.setText("Coller");
			menuColler.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/paste.gif")));
			menuEdition.add(menuColler);
	
			menuEleves.setText("Elèves");
			menuEleves.setDisplayedMnemonicIndex(1);
			menuEleves.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/eleve.gif")));
			menuEleves.setHorizontalTextPosition(SwingConstants.TRAILING);
			jMenuBar1.add(menuEleves);
	
			menuElevesAjouter.setText("Ajouter un élève");
			menuElevesAjouter.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/eleve_new.gif")));
			menuEleves.add(menuElevesAjouter);
			menuElevesAjouter.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					menuElevesAjouterActionPerformed(evt);
				}
			});
	
			menuElevesModif.setText("Modifier l'élève sélectionné");
			menuElevesModif.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/eleve_modif.gif")));
			menuEleves.add(menuElevesModif);
			menuElevesModif.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					menuElevesModifActionPerformed(evt);
				}
			});
	
			menuElevesSupprimer.setText("Supprimer l'élève selectionné");
			menuElevesSupprimer.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/eleve_suppr.gif")));
			menuEleves.add(menuElevesSupprimer);
			menuElevesSupprimer.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					menuElevesSupprimerActionPerformed(evt);
				}
			});
	
			jSeparator4.setVisible(true);
			menuEleves.add(jSeparator4);
	
			menuElevesInscrire.setText("Inscrire l'élève sélectionné à un atelier");
			menuElevesInscrire.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/atelier.gif")));
			menuEleves.add(menuElevesInscrire);
	
			menuClasses.setText("Classes");
			menuClasses.setDisplayedMnemonicIndex(0);
			menuClasses.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/classe.gif")));
			jMenuBar1.add(menuClasses);
	
			menuClassesNew.setText("Créer une nouvelle classe");
			menuClassesNew.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/classe_new.gif")));
			menuClasses.add(menuClassesNew);
			menuClassesNew.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					menuClassesNewActionPerformed(evt);
				}
			});
	
			menuClasseModif.setText("Modifier la classe sélectionnée");
			menuClasseModif.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/classe_modif.gif")));
			menuClasses.add(menuClasseModif);
			menuClasseModif.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					menuClasseModifActionPerformed(evt);
				}
			});
	
			menuClasseSuppr.setText("Supprimer la classe sélectionnée");
			menuClasseSuppr.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/classe_suppr.gif")));
			menuClasses.add(menuClasseSuppr);
			menuClasseSuppr.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					menuClasseSupprActionPerformed(evt);
				}
			});
	
			menuClasses.add(jSeparator3);
	
			menuClasseListeEleve.setText("Liste des élèves pour la classe sélectionnée");
			menuClasseListeEleve.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/eleve.gif")));
			menuClasses.add(menuClasseListeEleve);
			menuClasseListeEleve.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					menuClasseListeEleveActionPerformed(evt);
				}
			});
	
			menuClasseListeEleveAtelier.setText("Liste des élèves pour la classe sélectionnée + les ateliers");
			menuClasseListeEleveAtelier.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/atelier.gif")));
			menuClasses.add(menuClasseListeEleveAtelier);
	
			menuAteliers.setText("Ateliers");
			menuAteliers.setDisplayedMnemonicIndex(0);
			menuAteliers.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/atelier.gif")));
			jMenuBar1.add(menuAteliers);
	
			menuAtelierNew.setText("Créer un nouvel atelier");
			menuAtelierNew.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/atelier_new.gif")));
			menuAteliers.add(menuAtelierNew);
	
			menuAtelierModif.setText("Modifier l'atelier selectionné");
			menuAtelierModif.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/atelier_modif.gif")));
			menuAteliers.add(menuAtelierModif);
	
			menuAtelierSuppr.setText("Supprimer l'atelier selectionné");
			menuAtelierSuppr.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/atelier_suppr.gif")));
			menuAteliers.add(menuAtelierSuppr);
	
			menuAteliers.add(jSeparator5);
	
			menuAtelierListeEleve.setText("Liste des élèves par ateliers");
			menuAtelierListeEleve.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/eleve.gif")));
			menuAteliers.add(menuAtelierListeEleve);
	
			menuAteliers.add(jSeparator6);
	
			jMenu1.setText("Tarifs");
			jMenu1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/tarif.gif")));
			menuAteliers.add(jMenu1);
	
			menuAtelierTarifNew.setText("Créer un tarif d'atelier");
			menuAtelierTarifNew.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/tarif_new.gif")));
			jMenu1.add(menuAtelierTarifNew);
	
			menuAtelierTarifModif.setText("Modifier le tarif de l'atelier selectionné");
			menuAtelierTarifModif.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/tarif_modif.gif")));
			jMenu1.add(menuAtelierTarifModif);
	
			menuAtelierTarifSuppr.setText("Supprimer le tarif de l'atelier selectionné");
			menuAtelierTarifSuppr.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/tarif_suppr.gif")));
			jMenu1.add(menuAtelierTarifSuppr);
	
			menuCantine.setText("Cantine");
			menuCantine.setDisplayedMnemonicIndex(2);
			menuCantine.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/cantine.gif")));
			jMenuBar1.add(menuCantine);
	
			menuCantineAjouter.setText("Ajouter ou supprimer un élève");
			menuCantineAjouter.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/cantine.gif")));
			menuCantine.add(menuCantineAjouter);
	
			jSeparator8.setVisible(true);
			menuCantine.add(jSeparator8);
	
			menuCantineListeEleve.setText("Liste d'élève par classe / mois");
			menuCantineListeEleve.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/eleve.gif")));
			menuCantine.add(menuCantineListeEleve);
	
			menuCantine.add(jSeparator7);
	
			jMenu2.setText("Tarifs");
			jMenu2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/tarif.gif")));
			menuCantine.add(jMenu2);
	
			menuCantineTarifNew.setText("Créer un tarif de cantine");
			menuCantineTarifNew.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/tarif_new.gif")));
			menuCantineTarifNew.setBounds(new java.awt.Rectangle(0,0,0,0));
			jMenu2.add(menuCantineTarifNew);
	
			menuCantineTarifModif.setText("Modifier le tarif de cantine selectionné");
			menuCantineTarifModif.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/tarif_modif.gif")));
			menuCantineTarifModif.setBounds(new java.awt.Rectangle(0,0,0,0));
			jMenu2.add(menuCantineTarifModif);
	
			menuCantineTarifSuppr.setText("Supprimer le tarif de cantine selectionné");
			menuCantineTarifSuppr.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/tarif_suppr.gif")));
			menuCantineTarifSuppr.setBounds(new java.awt.Rectangle(0,0,0,0));
			jMenu2.add(menuCantineTarifSuppr);
	
			menuGestion.setText("Gestion");
			menuGestion.setDisplayedMnemonicIndex(0);
			jMenuBar1.add(menuGestion);
	
			menuOutils.setText("Outils");
			menuOutils.setDisplayedMnemonicIndex(0);
			jMenuBar1.add(menuOutils);
	
			menuOutilsGC.setText("Garbage Collector");
			menuOutils.add(menuOutilsGC);
			menuOutilsGC.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					menuOutilsGCActionPerformed(evt);
				}
			});
	
			menutOutilsBD.setText("Connexion a la base...");
			menuOutils.add(menutOutilsBD);
			menutOutilsBD.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					menutOutilsBDActionPerformed(evt);
				}
			});
	
			menuLnF.setText("Configuration du LnF");
			menuOutils.add(menuLnF);
			menuLnF.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					menuLnFActionPerformed(evt);
				}
			});
	
			menuAide.setText("Aide");
			menuAide.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/aide.gif")));
			menuAide.setPreferredSize(new java.awt.Dimension(55,19));
			jMenuBar1.add(menuAide);
	
			menuAideJava.setText("A propos de Java...");
			menuAide.add(menuAideJava);
			menuAideJava.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					menuAideJavaActionPerformed(evt);
				}
			});
	
			postInitGUI();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    /**
     * Configures the UI; tries to set the system look on Mac, 
     * <code>ExtWindowsLookAndFeel</code> on general Windows, and
     * <code>Plastic3DLookAndFeel</code> on Windows XP and all other OS.<p>
     * 
     * The JGoodies Swing Suite's <code>ApplicationStarter</code>,
     * <code>ExtUIManager</code>, and <code>LookChoiceStrategies</code>
     * classes provide a much more fine grained algorithm to choose and
     * restore a look and theme.
     */
    private void configureUI() {
        UIManager.put(Options.USE_SYSTEM_FONTS_APP_KEY, Boolean.TRUE);
        Options.setGlobalFontSizeHints(FontSizeHints.MIXED);
        Options.setDefaultIconSize(new Dimension(18, 18));
/*
        String lafName =
            LookUtils.IS_OS_WINDOWS_XP
                ? Options.getCrossPlatformLookAndFeelClassName()
                : Options.getSystemLookAndFeelClassName();

        try {
            UIManager.setLookAndFeel(lafName);
        } catch (Exception e) {
            System.err.println("Can't set look & feel:" + e);
        }
        */
        DialogConfig d = new DialogConfig(this);
        d.setUserLooknfeelFromConfig();
        d = null;
    }

	/** Add your pre-init code in here 	*/
	public void preInitGUI()
	{
		SplashScreen.showSplash();
		DatabaseConnection.getInstance().setLogger(Logger.getInstance());
		Logger.getInstance().logInfo("Construction de la GUI");
		//javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");        
    /*
		try
		{
			javax.swing.UIManager.installLookAndFeel(
				"JGoodies Plastic 3D",
				"com.jgoodies.plaf.plastic.Plastic3DLookAndFeel");
			javax.swing.UIManager.setLookAndFeel(
				"com.jgoodies.plaf.plastic.Plastic3DLookAndFeel");
		} catch (Exception e)
		{
			Logger.getInstance().logWarning(
				"Impossible de trouver le look&feel");
			e.printStackTrace();
		}
        */
        configureUI();        
	}

	/** Add your post-init code in here 	*/
	public void postInitGUI()
	{
		GUITools.setCursorWait(this);
		try
		{
			Callbacker callbacker = new Callbacker(this);
			GenericMetier metier;
            reloadListClasses();

			metier = new AteliersMetier();
			metier.setCallbacker(callbacker);
			ComboBoxFiller.addItems(
				cbAteliers,
				metier.getAll(),
				metier.getClass(),
				"getAtelierNom");
			metier = null;

			metier = new TarifsAteliersMetier();
			metier.setCallbacker(callbacker);
			ComboBoxFiller.addItems(
				cbTarifsAteliers,
				metier.getAll(),
				metier.getClass(),
				"getTarifNom");
			metier = null;

			metier = new TarifsCantinesMetier();
			metier.setCallbacker(callbacker);
			ComboBoxFiller.addItems(
				cbTarifsCantine,
				metier.getAll(),
				metier.getClass(),
				"getTarifNom");
			metier = null;

			reloadListEleve();
			System.gc();
		} catch (Exception ex)
		{
			setStatus(ex.getMessage());
			FrameException.showException(ex);
			//DialogAlert.DialogError("Exception", ex.getMessage().toString());
		}
		GUITools.setCursorNormal(this);
		Logger.getInstance().logInfo("GUI construite");
		SplashScreen.endSplash();
		GUITools.setCursorNormal(this);
	}

	/** Auto-generated main method */
	public static void main(String[] args)
	{

		showGUI();
	}

	/**
	* This static method creates a new instance of this class and shows
	* it inside a new JFrame, (unless it is already a JFrame).
	*
	* It is a convenience method for showing the GUI, but it can be
	* copied and used as a basis for your own code.	*
	* It is auto-generated code - the body of this method will be
	* re-generated after any changes are made to the GUI.
	* However, if you delete this method it will not be re-created.	*/
	public static void showGUI(){
		try {
			EcoleApp inst = new EcoleApp();
			inst.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void shutdownAppli(Object evt)
	{
		try
		{
			System.out.println("L'application se ferme...");
			DatabaseConnection.getInstance().closeConnection();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("L'application est arrêtée");
		System.exit(0);

	}
	/** Auto-generated event handler method */
	protected void menuQuitterActionPerformed(ActionEvent evt)
	{
		shutdownAppli(evt);
		dispose();
	}

	/** Auto-generated event handler method */
	protected void EcoleAppWindowClosing(WindowEvent evt)
	{
		shutdownAppli(evt);
	}

	/** Auto-generated event handler method */
	protected void EcoleAppComponentShown(ComponentEvent evt)
	{
		if (FrameException.isFrameVisible())
		{
			FrameException.setFrameVisible(true);
		}
	}

	/** Auto-generated event handler method */
	protected void menuReinitActionPerformed(ActionEvent evt)
	{
		postInitGUI();
	}

	public void setStatus(String status)
	{
		jStatusBar.setText(status);
		jStatusBar.repaint();
		Rectangle progressRect = jStatusBar.getBounds();
		progressRect.x = 0;
		progressRect.y = 0;
		jStatusBar.paintImmediately(progressRect);
	}

	public void progressBarInit(int maxValue)
	{
		jProgressBar.setValue(0);
		if (maxValue < 0)
			jProgressBar.setIndeterminate(true);
		Rectangle progressRect = jProgressBar.getBounds();
		progressRect.x = 0;
		progressRect.y = 0;
		jProgressBar.paintImmediately(progressRect);
	}

	public void progressBarSetValue(int value)
	{
		jProgressBar.setValue(value);
		Rectangle progressRect = jProgressBar.getBounds();
		progressRect.x = 0;
		progressRect.y = 0;
		jProgressBar.paintImmediately(progressRect);
	}

	/** Auto-generated event handler method */
	protected void menuOutilsGCActionPerformed(ActionEvent evt)
	{
		GUITools.setCursorWait(this);
		setStatus("Passage du GC ...");
		long avantGC = Runtime.getRuntime().freeMemory();
		System.gc();
		long apresGC = Runtime.getRuntime().freeMemory();
		setStatus("avant GC : " + avantGC + ", après GC : " + apresGC);
		GUITools.setCursorNormal(this);
		DialogAlert.DialogOK(
			"Le garbage collector à liberer "
				+ (apresGC - avantGC)
				+ " octets");
	}

	/** Auto-generated event handler method */
	protected void menuAideJavaActionPerformed(ActionEvent evt)
	{
		String str =
			"Java : "
				+ System.getProperty("java.vendor")
				+ " "
				+ System.getProperty("java.version")
				+ "\n";
		str += "OS : "
			+ System.getProperty("os.arch")
			+ " "
			+ System.getProperty("os.name")
			+ " "
			+ System.getProperty("os.version")
			+ "\n";
		str += "Mémoire : "
			+ Runtime.getRuntime().freeMemory()
			+ " octets libre";
		DialogAlert.DialogOK("A propos de Java...", str);
	}

	/** Auto-generated event handler method */
	protected void menutOutilsBDActionPerformed(ActionEvent evt)
	{
		try
		{
			DialogConfig dialog = new DialogConfig(this);
			if (dialog.askUserDBConfig())
			{
				DatabaseConnection.getInstance().closeConnection();
				postInitGUI();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			setStatus(e.getMessage());
			FrameException.showException(e);
		} finally
		{
			GUITools.setCursorNormal(this);
		}
	}

	private void reloadListEleve()
		throws
			IllegalArgumentException,
			SecurityException,
			IllegalAccessException,
			InvocationTargetException,
			NoSuchMethodException,
			SQLException
	{
		GUITools.setCursorWait(this);
		ElevesMetier metier = new ElevesMetier();
		Callbacker callbacker = new Callbacker(this);
		metier.setCallbacker(callbacker);
		listEleves = metier.getAll();
		ComboBoxFiller.addItems(
			cbEleves,
			metier.getAll(),
			metier.getClass(),
			"getNomPrenom");
		metier = null;
		GUITools.setCursorNormal(this);
	}
    
    private void reloadListClasses()
        throws
            IllegalArgumentException,
            SecurityException,
            IllegalAccessException,
            InvocationTargetException,
            NoSuchMethodException,
            SQLException
    {
        GUITools.setCursorWait(this);
            ClassesMetier metier = new ClassesMetier();
            listClasses = metier.getAll();
            Callbacker callbacker = new Callbacker(this);
            metier.setCallbacker(callbacker);
            ComboBoxFiller.addItems(
                cbClasses,
                listClasses,
                metier.getClass(),
                "getClasseNom");
            metier = null;

        GUITools.setCursorNormal(this);
    }    

	/** Auto-generated event handler method */
	protected void menuElevesAjouterActionPerformed(ActionEvent evt)
	{
		try
		{
			EleveDatabean e = DialogEleve.getInstance().saisirEleve(this);
			if (null != e)
			{
				GUITools.setCursorWait(this);
				ElevesMetier metier = new ElevesMetier();
				metier.insert(e);
				String res =
					"Eleve " + e.getNom() + " " + e.getPrenom() + " ajouté";
				setStatus(res);
				DialogAlert.DialogOK(this, res);
				// Recharger la combo des éleves
				reloadListEleve();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			setStatus(e.getMessage());
			FrameException.showException(e);
		} finally
		{
			GUITools.setCursorNormal(this);
		}

	}

	/** Auto-generated event handler method */
	protected void menuElevesSupprimerActionPerformed(ActionEvent evt)
	{
		try
		{
			int idx = cbEleves.getSelectedIndex();
			EleveDatabean e = (EleveDatabean) listEleves.get(idx);
			if (DialogAlert
				.DialogWarningYesNo(
					this,
					"Voulez vous supprimer l'élève\n"
						+ e.getNomPrenom()
						+ " ?"))
			{
				GUITools.setCursorWait(this);
				ElevesMetier metier = new ElevesMetier();
				metier.delete(e.getId());
				cbEleves.removeItemAt(idx);
				listEleves.remove(idx);
				String res =
					"Eleve " + e.getNom() + " " + e.getPrenom() + " supprimé";
				setStatus(res);
				GUITools.setCursorNormal(this);
				DialogAlert.DialogOK(this, res);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			setStatus(e.getMessage());
			FrameException.showException(e);
		} finally
		{
			GUITools.setCursorNormal(this);
		}
	}

	/**
	 * Modification de l'eleve selectionné
	 * @param evt
	 * @author jemore
	 */
	protected void menuElevesModifActionPerformed(ActionEvent evt)
	{
		try
		{
			int idx = cbEleves.getSelectedIndex();
			EleveDatabean e = (EleveDatabean) listEleves.get(idx);
			if (null != e)
			{
				EleveDatabean nouvel_eleve = DialogEleve.getInstance().modifEleve(this, e);
				if (null != nouvel_eleve)
				{
					e = null;
					GUITools.setCursorWait(this);
					ElevesMetier metier = new ElevesMetier();
					metier.update(nouvel_eleve);					
					String res =
						"Eleve " + nouvel_eleve.getNom() + " " + nouvel_eleve.getPrenom() + " modifié";
					setStatus(res);
					reloadListEleve();
					GUITools.setCursorNormal(this);					
					cbEleves.setSelectedIndex(idx);
					DialogAlert.DialogOK(this, res);
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			setStatus(e.getMessage());
			FrameException.showException(e);
		} finally
		{
			GUITools.setCursorNormal(this);
		}
	}

	/** Auto-generated event handler method */
	protected void menuLnFActionPerformed(ActionEvent evt){
        try
        {
            DialogConfig dialog = new DialogConfig(this);
            dialog.askUserLooknfeel();
            {
                //DatabaseConnection.getInstance().closeConnection();
                //postInitGUI();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            setStatus(e.getMessage());
            FrameException.showException(e);
        }
        finally
        {
            GUITools.setCursorNormal(this);
        }
	}

	/** Auto-generated event handler method */
	protected void menuClassesNewActionPerformed(ActionEvent evt){
		try
        {
            ClasseDatabean c = DialogClasse.getInstance().saisirClasse(this);
            if (null != c)
            {
                GUITools.setCursorWait(this);
                ClassesMetier metier = new ClassesMetier();
                metier.insert(c);
                String res = "Classe de " + c.getClasse_nom() +" ajoutée";
                setStatus(res);
                DialogAlert.DialogOK(this, res);
                // Recharger la combo des classes
                reloadListClasses();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            setStatus(e.getMessage());
            FrameException.showException(e);
        } finally
        {
            GUITools.setCursorNormal(this);
        }
	}

	/** Auto-generated event handler method */
	protected void menuClasseModifActionPerformed(ActionEvent evt)
    {
        try
        {
            int idx = cbClasses.getSelectedIndex();
            ClasseDatabean c = (ClasseDatabean) listClasses.get(idx);
            if (null != c)
            {
                ClasseDatabean nouvel_classe = DialogClasse.getInstance().modifClasse(this, c);
                if (null != nouvel_classe)
                {
                    c = null;
                    GUITools.setCursorWait(this);
                    ClassesMetier metier = new ClassesMetier();
                    metier.update(nouvel_classe);                    
                    String res =
                        "Classe " + nouvel_classe.getClasse_nom() + " modifiée";
                    setStatus(res);
                    GUITools.setCursorNormal(this);
                    DialogAlert.DialogOK(this, res);
                    reloadListClasses();
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            setStatus(e.getMessage());
            FrameException.showException(e);
        } finally
        {
            GUITools.setCursorNormal(this);
        }
	}

	/** Auto-generated event handler method */
	protected void menuClasseSupprActionPerformed(ActionEvent evt){
        try
        {
            int idx = cbClasses.getSelectedIndex();
            ClasseDatabean c = (ClasseDatabean) listClasses.get(idx);
            if (DialogAlert
                .DialogWarningYesNo(
                    this,
                    "Voulez vous supprimer la classe\n"
                        + c.getClasse_nom()
                        + " ?"))
            {
                GUITools.setCursorWait(this);
                ClassesMetier metier = new ClassesMetier();
                metier.delete(c.getId());
                cbClasses.removeItemAt(idx);
                listEleves.remove(idx);
                String res =
                    "Classe " + c.getClasse_nom() + " supprimée";
                setStatus(res);
                GUITools.setCursorNormal(this);
                DialogAlert.DialogOK(this, res);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            setStatus(e.getMessage());
            FrameException.showException(e);
        } finally
        {
            GUITools.setCursorNormal(this);
        }		
	}

	/**
     * Affectation d'un tarif de cantine a un eleve
	 * @param evt
	 * @author jemore
	 */
	protected void bElevesCantineActionPerformed(ActionEvent evt){
        try
        {
            int idx = cbEleves.getSelectedIndex();
            EleveDatabean e = (EleveDatabean)listEleves.get(idx);
            
            CantineDatabean c;
            CantineMetier metier = new CantineMetier();
            c = metier.getCantineForEleve(e.getId());
            if (null != c) c.setAffecte(true);
            c = DialogCantine.getInstance().saisir(this, c, e);
            if (null != c)
            {
                    GUITools.setCursorWait(this);
                    
                    if (c.isAffecte())
                    {
                        metier.ajouter(c);
                    }
                    else
                    {
                        metier.retirer(c);
                    }
                    GUITools.setCursorNormal(this);
                	cbElevesActionPerformed(null);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            setStatus(e.getMessage());
            FrameException.showException(e);
        } finally
        {
            GUITools.setCursorNormal(this);
        }
	}

	/**
	 * Changement de la liste déroulante des eleves : on affiche une fiche
	 * eleve sur le panneau de droite;
	 * @param evt
	 * @author jemore
	 */
	protected void cbElevesActionPerformed(ActionEvent evt){
		try {		
			int idx = cbEleves.getSelectedIndex();
			if (-1 == idx) return;
	        EleveDatabean e = (EleveDatabean)listEleves.get(idx);
	        if (null != e)
	        {
	        	GUITools.setCursorWait(this);
	        	// Quel est la classe de cet eleve ?
	        	ClassesMetier metier = new ClassesMetier();	        	
	        	int classe_id = e.getClasseid();
	        	ClasseDatabean c = metier.getClasseByClasseId(classe_id);
	        	
	            FicheEleve fiche = new FicheEleve(e);
	            JEditorPane ficheEdit = fiche.getEditorPanel();
	            jScrollPanDroite.setViewportView(ficheEdit);
	            GUITools.setCursorNormal(this);           
	        }
		}catch(Exception e)
		{
            e.printStackTrace();
            setStatus(e.getMessage());
            FrameException.showException(e);
		}
		finally
        {
            GUITools.setCursorNormal(this);
        }		
	}

	/**
     * Liste des eleves pour la classe selectionnée
	 * @param evt
	 * @author jerome forestier @ sqli
	 * @date 29 sept. 2004
	 */
	protected void menuClasseListeEleveActionPerformed(ActionEvent evt){
		try
        {
            int idx = cbClasses.getSelectedIndex(); // Index classe selectionnée
            if (-1 == idx) return;
            ClasseDatabean c = (ClasseDatabean) listClasses.get(idx);
            if (null != c)
            {
                GUITools.setCursorWait(this);
                EleveTable eleveTable = new EleveTable();
                eleveTable.parClasse(c);
                //JTable table = new JTable(eleveTable);
                jScrollPanDroite.setViewportView(eleveTable.getTable());
            }
        }catch(Exception e)
        {
            e.printStackTrace();
            setStatus(e.getMessage());
            FrameException.showException(e);
        }
        finally
        {
            GUITools.setCursorNormal(this);
        }       
	}

	/** Auto-generated event handler method */
	protected void bClassesListeElevesActionPerformed(ActionEvent evt){
		menuClasseListeEleveActionPerformed(evt);
	}
}