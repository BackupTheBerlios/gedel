package ecole;

import java.sql.*;
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import com.activetree.print.*;

import ecole.dialog.Dialog;

public class EcoleApp
    extends JFrame
    implements ActionListener {
  Classe classe;
  Student mainEleve;
  RefAtelier mainAtelier;
  TarifsAteliers tarifs;
  TarifsCantine tarifsCantine;
  JComboBox comboLeftEleves;
  JComboBox comboLeftClasses;
  JComboBox comboLeftAtelier;
  JComboBox comboLeftTarifs;
  JComboBox comboLeftTarifsCantine;
  JScrollPane scrollpane;
  JEditorPane textAreaRight;
  JPanel mainInsertPanel;
  JPanel cantinePanel;
  JPanel insertPanel;
  JPanel insertClassePanel;
  JPanel insertAtelierPanel;
  JPanel insertTarifAtelierPanel;
  JPanel eleveAteliersMainPanel;
  JPanel eleveCantinePanel;
  JPanel insertTarifsCantinePanel;
  JTextArea text;
  JTextField _TextNom;
  JTextField _TextPrenom;
  JTextField _TextSex;
  JTextField _TextDob;
  JTextField _TextStreet;
  JTextField _TextZip;
  JTextField _TextCity;
  JTextField _TextTel1;
  JTextField _TextTel2;
  JTextField _TextTel3;
  JComboBox _ComboClass;
  JCheckBox chkCantine;
  JTextField _TextDateEntree;
  JTextField _TextCantineJours;
  JTextField _TextClasse;
  JTextField _TextInstituteur;
  JCheckBox[] chkAteliers;
  JComboBox _TarifsAteliers;
  JComboBox _TarifsCantine;
  JTextField _DateValiditeAtelier;
  JTextField _nbrJoursAteliers;
  JTextField _TextAtelierNom;
  JTextField _TextJourAtelier;
  JTextField _TextTypeAtelier;
  JTextField _TextTarifAtelierNom;
  JTextField _TextTarifAtelier;
  JTextField _TextTarifCantineNom;
  JTextField _TextTarifCantine;
  JTextField _TextCantineValidite;
  JButton modifyTarif;
  JButton suprTarif;
  JButton modifyTarifCantine;
  JButton suprTarifCantine;
  JButton printpreviewButton;
  JButton cantineEleve;
  JButton atelierEleve;
  JButton sauver;
  JButton effacer;
  JMenuItem _ListeEleve;
  JMenuItem _ListeEleveAtelier;
  JMenuItem insertCantineItem;
  JMenuItem insertEleveCantine;
  JMenuItem _ListeEleveAtelierByClass;
  JMenuItem listeCantine;
  JButton printButton;
  JButton listeEleve;
  JButton listeAteliers;
  JButton modifyAtelier;
  JButton listeElevesAtelier;
  boolean boolUpdateAteliers = false;
  boolean boolUpdateCantine = false;
  boolean tableprint = false;
  JScrollPane textPane;
  JTable table;
  protected AtDocumentPrinter printer;
  protected AtTablePrinter tablePrinter;
  protected AtGenericPrinter genericPrinter;
  JToolBar toolbar;
  Connection _Con;
  JLabel status;
  String printTitle = "";
  TableModelComptaCantine modelComptaCantine = null;
  JComboBox comboTable;
  HistoCantine histocantine;
  /**
   * builds the topmenu and the JSplitPane
   */
  public EcoleApp() {
    super("Gestion d'\u00E9cole");
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      _Con = DriverManager.getConnection("jdbc:mysql:///ecole", "root", "");

    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }

    SmartJPrint.setLicenseKey("AJET-LGFH-K68E-C30B");
    JMenuItem item;
    JMenuBar mainMenu = new JMenuBar();
    JMenu fileMenu = new JMenu("Fichier");
    fileMenu.setMnemonic('f');
    fileMenu.add(item = new JMenuItem("Imprimer", 'I'));
    item.addActionListener(this);
    fileMenu.add(item = new JMenuItem("Aper\u00E7u avant impression", 'A'));
    item.addActionListener(this);
    fileMenu.addSeparator();
    fileMenu.add(item = new JMenuItem("Fermer", 'F'));
    item.addActionListener(this);

    JMenu editMenu = new JMenu("Edition");
    editMenu.setMnemonic('E');
    editMenu.add(item = new JMenuItem("Copier"));
    item.addActionListener(this);
    editMenu.add(item = new JMenuItem("Couper"));
    item.addActionListener(this);
    editMenu.add(item = new JMenuItem("Coller"));
    item.addActionListener(this);

    JMenu elevesMenu = new JMenu("El\u00E8ves");
    elevesMenu.setMnemonic('L');
    elevesMenu.add(item = new JMenuItem("Ins\u00E9rer \u00E9l\u00E8ve"));
    item.addActionListener(this);
    elevesMenu.add(item = new JMenuItem("Modifier \u00E9l\u00E8ve"));
    item.addActionListener(this);
    elevesMenu.add(item = new JMenuItem("Suprimer \u00E9l\u00E8ve"));
    item.addActionListener(this);
    elevesMenu.add(item = new JMenuItem("Inscrire \u00E0 un atelier"));
    item.addActionListener(this);

    JMenu classesMenu = new JMenu("Classes");
    classesMenu.setMnemonic('C');
    JMenu submenuClasses = new JMenu("Actions");
    // submenu.setMnemonic(KeyEvent.VK_S);
    submenuClasses.add(item = new JMenuItem("Nouvelle classe"));
    item.addActionListener(this);
    //menuItem.setAccelerator(KeyStroke.getKeyStroke(
    //        KeyEvent.VK_2, ActionEvent.ALT_MASK));
    submenuClasses.add(item = new JMenuItem("Modifier classe"));
    item.addActionListener(this);
    submenuClasses.add(item = new JMenuItem("Suprimer classe"));
    item.addActionListener(this);
    classesMenu.add(submenuClasses);
    classesMenu.add(_ListeEleve = new JMenuItem(
        "Liste d'\u00E9l\u00E8ves par classe"));
    _ListeEleve.addActionListener(this);
    classesMenu.add(_ListeEleveAtelierByClass = new JMenuItem(
        "Liste d'\u00E9l\u00E8ves par classe + atelier"));
    _ListeEleveAtelierByClass.addActionListener(this);

    JMenu atelierMenu = new JMenu("Ateliers");
    atelierMenu.setMnemonic('T');
    JMenu submenuAteliers = new JMenu("Actions");
    // submenu.setMnemonic(KeyEvent.VK_S);
    submenuAteliers.add(item = new JMenuItem("Nouvel atelier"));
    item.addActionListener(this);
    submenuAteliers.add(item = new JMenuItem("Modifier atelier"));
    item.addActionListener(this);
    submenuAteliers.add(item = new JMenuItem("Suprimer atelier"));
    item.addActionListener(this);
    atelierMenu.add(submenuAteliers);
    atelierMenu.add(_ListeEleveAtelier = new JMenuItem(
        "Liste d'\u00E9l\u00E8ves par atelier"));
    _ListeEleveAtelier.addActionListener(this);
    JMenu submenuAteliersTarifs = new JMenu("Tarifs");
    // submenu.setMnemonic(KeyEvent.VK_S);
    submenuAteliersTarifs.add(item = new JMenuItem("Nouveau tarif atelier"));
    item.addActionListener(this);
    submenuAteliersTarifs.add(item = new JMenuItem("Modifier tarif atelier"));
    item.addActionListener(this);
    submenuAteliersTarifs.add(item = new JMenuItem("Suprimer tarif atelier"));
    item.addActionListener(this);
    atelierMenu.add(submenuAteliersTarifs);

    JMenu cantineMenu = new JMenu("Cantine");
    cantineMenu.setMnemonic('N');
    cantineMenu.add(item = new JMenuItem("Ajouter/Suprimer un \u00E9l\u00E8ve"));
    item.addActionListener(this);
    cantineMenu.add(listeCantine = new JMenuItem(
        "Liste d'\u00E9l\u00E8ves par classe/mois"));
    listeCantine.addActionListener(this);
    JMenu submenuCantineTarifs = new JMenu("Tarifs");
    submenuCantineTarifs.add(item = new JMenuItem("Nouveau tarif cantine"));
    item.addActionListener(this);
    submenuCantineTarifs.add(item = new JMenuItem("Modifier tarif cantine"));
    item.addActionListener(this);
    submenuCantineTarifs.add(item = new JMenuItem("Suprimer tarif cantine"));
    item.addActionListener(this);
    cantineMenu.add(submenuCantineTarifs);

    JMenu gestionMenu = new JMenu("Gestion");
    gestionMenu.setMnemonic('G');
    gestionMenu.add(item = new JMenuItem("Bilan"));
    item.addActionListener(this);
    JMenu aideMenu = new JMenu("Aide");
    aideMenu.setMnemonic('E');
    aideMenu.add(item = new JMenuItem("Conseils d'utilisation",
                                      new ImageIcon("aide.gif")));
    item.addActionListener(this);
    aideMenu.add(item = new JMenuItem("A propos", new ImageIcon("about.gif")));
    item.addActionListener(this);
    mainMenu.add(fileMenu);
    mainMenu.add(editMenu);
    mainMenu.add(elevesMenu);
    mainMenu.add(classesMenu);
    mainMenu.add(atelierMenu);
    mainMenu.add(cantineMenu);
    mainMenu.add(gestionMenu);
    mainMenu.add(aideMenu);
    setJMenuBar(mainMenu);

    toolbar = new JToolBar();
    printButton = new JButton(new ImageIcon("print.gif"));
    printButton.addActionListener(this);
    printButton.setToolTipText("Imprimer");
    printpreviewButton = new JButton(new ImageIcon("PrintPreview.gif"));
    printpreviewButton.addActionListener(this);
    printpreviewButton.setToolTipText("Aper\u00E7u avant impression");
    toolbar.add(printButton);
    toolbar.add(printpreviewButton);
    toolbar.addSeparator();
    toolbar.add(new JButton(new ImageIcon("cut.gif")));
    toolbar.add(new JButton(new ImageIcon("copy.gif")));
    toolbar.add(new JButton(new ImageIcon("paste.gif")));
    toolbar.addSeparator();
    toolbar.add(new JButton(new ImageIcon("aide.gif")));
    JPanel panelTool = new JPanel();
    comboTable = new JComboBox();
    comboTable.addItem("Selectionnez...");
    comboTable.setEditable(false);
    comboTable.setEnabled(false);
    comboTable.addActionListener(this);
    sauver = new JButton("Sauver");
    sauver.setIcon(new ImageIcon("save.gif"));
    sauver.setEnabled(false);
    sauver.addActionListener(this);
    effacer = new JButton("Effacer");
    effacer.setIcon(new ImageIcon("clear.gif"));
    effacer.setEnabled(false);
    effacer.addActionListener(this);
    toolbar.addSeparator();
    panelTool.add(comboTable);
    panelTool.add(sauver);
    panelTool.add(effacer);
    toolbar.add(panelTool);

    toolbar.addSeparator(new Dimension(350, 30));
    getContentPane().add(toolbar, BorderLayout.NORTH);
    JPanel panelLeft = new JPanel();
    panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));

    JPanel panelEleves = new JPanel();
    panelEleves.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(0, 5, 5, 5);
    gbc.fill = GridBagConstraints.NONE;
    gbc.anchor = GridBagConstraints.CENTER;
    mainEleve = new Student(_Con);
    comboLeftEleves = new JComboBox(mainEleve.getcomboEleves());
    comboLeftEleves.addActionListener(this);
    cantineEleve = new JButton("Cantine");
    cantineEleve.addActionListener(this);
    atelierEleve = new JButton("Atelier");
    atelierEleve.addActionListener(this);
    gbc.gridwidth = 2;
    panelEleves.add(comboLeftEleves, gbc);
    gbc.gridwidth = 1;
    gbc.gridy = 1;
    panelEleves.add(cantineEleve, gbc);
    gbc.gridx = 1;
    panelEleves.add(atelierEleve, gbc);
    panelEleves.setBorder(BorderFactory.createTitledBorder("\u00E9l\u00E8ves"));

    JPanel panelClasses = new JPanel();
    panelClasses.setLayout(new GridBagLayout());
    gbc.gridy = 0;
    classe = new Classe(_Con);
    comboLeftClasses = new JComboBox(classe.getcomboClasses());
    comboLeftClasses.addActionListener(this);
    listeEleve = new JButton("Liste \u00E9l\u00E8ves");
    listeEleve.addActionListener(this);
    listeAteliers = new JButton("Liste ateliers");
    listeAteliers.addActionListener(this);
    panelClasses.add(comboLeftClasses, gbc);
    gbc.gridy = 1;
    panelClasses.add(listeEleve, gbc);
    gbc.gridy = 2;
    panelClasses.add(listeAteliers, gbc);
    panelClasses.setBorder(BorderFactory.createTitledBorder("Classes"));

    JPanel panelAteliers = new JPanel();
    panelAteliers.setLayout(new GridBagLayout());
    gbc.gridy = 0;
    mainAtelier = new RefAtelier(_Con);
    comboLeftAtelier = new JComboBox(mainAtelier.getAteliers());
    comboLeftAtelier.addActionListener(this);
    modifyAtelier = new JButton("Modifier atelier");
    modifyAtelier.addActionListener(this);
    listeElevesAtelier = new JButton("Liste d'\u00E9l\u00E8ves");
    listeElevesAtelier.addActionListener(this);
    panelAteliers.add(comboLeftAtelier, gbc);
    gbc.gridy = 1;
    panelAteliers.add(modifyAtelier, gbc);
    gbc.gridy = 2;
    panelAteliers.add(listeElevesAtelier, gbc);
    panelAteliers.setBorder(BorderFactory.createTitledBorder("Ateliers"));

    JPanel panelTarifsAteliers = new JPanel();
    panelTarifsAteliers.setLayout(new GridBagLayout());
    gbc.gridy = 0;
    tarifs = new TarifsAteliers(_Con);
    comboLeftTarifs = new JComboBox(tarifs.getComboTarifsAteliers());
    comboLeftTarifs.addActionListener(this);
    modifyTarif = new JButton("Modifier tarif");
    modifyTarif.addActionListener(this);
    suprTarif = new JButton("Suprimer tarif");
    suprTarif.addActionListener(this);
    panelTarifsAteliers.add(comboLeftTarifs, gbc);
    gbc.gridy = 1;
    panelTarifsAteliers.add(modifyTarif, gbc);
    gbc.gridy = 2;
    panelTarifsAteliers.add(suprTarif, gbc);
    panelTarifsAteliers.setBorder(BorderFactory.createTitledBorder(
        "Tarifs Ateliers"));

    JPanel panelTarifsCantine = new JPanel();
    panelTarifsCantine.setLayout(new GridBagLayout());
    gbc.gridy = 0;
    tarifsCantine = new TarifsCantine(_Con);
    comboLeftTarifsCantine = new JComboBox(tarifsCantine.getComboTarifsCantine());
    comboLeftTarifsCantine.addActionListener(this);
    modifyTarifCantine = new JButton("Modifier tarif");
    modifyTarifCantine.addActionListener(this);
    suprTarifCantine = new JButton("Suprimer tarif");
    suprTarifCantine.addActionListener(this);
    panelTarifsCantine.add(comboLeftTarifsCantine, gbc);
    gbc.gridy = 1;
    panelTarifsCantine.add(modifyTarifCantine, gbc);
    gbc.gridy = 2;
    panelTarifsCantine.add(suprTarifCantine, gbc);
    panelTarifsCantine.setBorder(BorderFactory.createTitledBorder(
        "Tarifs Cantine"));

    // panelLeft.add(comboLeftEleves);
    //  panelLeft.add(modifyEleve);
    //   panelLeft.add(suprEleve);
    panelEleves.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    panelLeft.add(panelEleves);
    panelClasses.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    panelLeft.add(panelClasses);
    panelAteliers.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    panelLeft.add(panelAteliers);
    panelTarifsAteliers.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    panelLeft.add(panelTarifsAteliers);
    panelTarifsCantine.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    panelLeft.add(panelTarifsCantine);

    textAreaRight = new JEditorPane();
    textAreaRight.setEditable(false);
    //set the content type to HTML
    textAreaRight.setContentType("text/html");

    //textAreaRight.setForeground(Color.BLUE);
    // textAreaRight.setFont(new Font("Arial", Font.BOLD, 12));
    textPane = new JScrollPane(textAreaRight);
    textPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    //  textAreaRight.setEditable(false);
    //   panelRight.add(textAreaRight);
    JScrollPane leftScroll = new JScrollPane(panelLeft);

    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                          leftScroll, textPane);
    splitPane.setOneTouchExpandable(false);
    splitPane.setDividerLocation(200);

//Provide minimum sizes for the two components in the split pane
//Dimension minimumSize = new Dimension(100, 80);
    //   panelLeft.setMaximumSize(minimumSize);
    // panelRight.setMinimumSize(minimumSize);
//pictureScrollPane.setMinimumSize(minimumSize);
    Container c = getContentPane();
    c.setLayout(new BorderLayout());
    c.add(toolbar, BorderLayout.NORTH);
    c.add(splitPane, BorderLayout.CENTER);

    status = new JLabel("...");
    status.setBorder(new EtchedBorder());
    c.add(status, BorderLayout.SOUTH);
    table = new JTable();

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setSize(800, 600);
    Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = getSize();
    setLocation(scrnSize.width / 2 - (frameSize.width / 2),
                scrnSize.height / 2 - (frameSize.height / 2));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  /**
   * creates the InsertEleve Panel that will e inserted in the JOptionDialog
   * when inserteleve is clicked in the file menu
   */
  private void insertEleve() {

    mainInsertPanel = new JPanel();
    insertPanel = new JPanel();
    insertPanel.setVisible(true);
    insertPanel.setLayout(new GridLayout(12, 2));
    JLabel prenom = new JLabel("Pr\u00E9nom");
    _TextPrenom = new JTextField(16);
    JLabel nom = new JLabel("Nom");
    _TextNom = new JTextField(16);
    JLabel sex = new JLabel("Sexe");
    _TextSex = new JTextField(16);
    JLabel dob = new JLabel("Date de naissance (Format : jj/mm/aaaa)");
    _TextDob = new JTextField(16);
    JLabel street = new JLabel("Rue");
    _TextStreet = new JTextField(16);
    JLabel zip = new JLabel("Code postal");
    _TextZip = new JTextField(16);
    JLabel city = new JLabel("Ville");
    _TextCity = new JTextField(16);
    JLabel tel1 = new JLabel("T\u00E9l\u00E9phone (1)");
    _TextTel1 = new JTextField(16);
    JLabel tel2 = new JLabel("T\u00E9l\u00E9phone (2)");
    _TextTel2 = new JTextField(16);
    JLabel tel3 = new JLabel("T\u00E9l\u00E9phone (3)");
    _TextTel3 = new JTextField(16);
    JLabel classes = new JLabel("Classe");
    _ComboClass = new JComboBox(classe.getcomboClasses());
    JLabel dateEntree = new JLabel("Date d'entr\u00E9e  (Format : jj/mm/aaaa)");
    _TextDateEntree = new JTextField(16);
    JPanel subPanel2 = new JPanel();
    insertPanel.add(prenom);
    insertPanel.add(_TextPrenom);
    insertPanel.add(nom);
    insertPanel.add(_TextNom);
    insertPanel.add(sex);
    insertPanel.add(_TextSex);
    insertPanel.add(dob);
    insertPanel.add(_TextDob);
    insertPanel.add(street);
    insertPanel.add(_TextStreet);
    insertPanel.add(zip);
    insertPanel.add(_TextZip);
    insertPanel.add(city);
    insertPanel.add(_TextCity);
    insertPanel.add(tel1);
    insertPanel.add(_TextTel1);
    insertPanel.add(tel2);
    insertPanel.add(_TextTel2);
    insertPanel.add(tel3);
    insertPanel.add(_TextTel3);
    insertPanel.add(classes);
    insertPanel.add(_ComboClass);
    insertPanel.add(dateEntree);
    insertPanel.add(_TextDateEntree);
    mainInsertPanel.add(insertPanel);
  }

  /**
   * creates the InsertClasse Panel that will be inserted in the JOptionDialog
   * when insertClasse is clicked in the file menu
   */
  private void insertClasse() {
    insertClassePanel = new JPanel();
    insertClassePanel.setLayout(new GridLayout(2, 2));
    JLabel classeName = new JLabel("Nom de la Classe");
    _TextClasse = new JTextField(16);
    JLabel instituteur = new JLabel("Nom de l'instituteur");
    _TextInstituteur = new JTextField(16);
    insertClassePanel.add(classeName);
    insertClassePanel.add(_TextClasse);
    insertClassePanel.add(instituteur);
    insertClassePanel.add(_TextInstituteur);
  }

  /**
       * creates the InsertAteliler Panel that will be inserted in the JOptionDialog
   * when insertAtelier is clicked in the file menu
   */
  private void insertAtelier() {
    insertAtelierPanel = new JPanel();
    insertAtelierPanel.setLayout(new GridLayout(3, 2));
    JLabel atelierName = new JLabel("Nom de l'atelier");
    _TextAtelierNom = new JTextField(16);
    JLabel atelierJour = new JLabel("Jour de l'atelier");
    _TextJourAtelier = new JTextField(16);
    JLabel atelierType = new JLabel(
        "Type (p\u00E9riscolaire:\"P\"; \u00E9tude:\"E\"; ville:\"V\")");
    _TextTypeAtelier = new JTextField(16);
    insertAtelierPanel.add(atelierName);
    insertAtelierPanel.add(_TextAtelierNom);
    insertAtelierPanel.add(atelierJour);
    insertAtelierPanel.add(_TextJourAtelier);
    insertAtelierPanel.add(atelierType);
    insertAtelierPanel.add(_TextTypeAtelier);
  }

  /**
   * creates the InsertTarifAtelier Panel that will be inserted in the JOptionDialog
   * when insertTarifAtelier is clicked in the file menu
   */
  private void insertTarifAtelier() {
    insertTarifAtelierPanel = new JPanel();
    insertTarifAtelierPanel.setLayout(new GridLayout(2, 2));
    JLabel tarifName = new JLabel("Nom du Tarif");
    _TextTarifAtelierNom = new JTextField(16);
    JLabel atelierTarif = new JLabel("Montant en euros");
    _TextTarifAtelier = new JTextField(16);
    insertTarifAtelierPanel.add(tarifName);
    insertTarifAtelierPanel.add(_TextTarifAtelierNom);
    insertTarifAtelierPanel.add(atelierTarif);
    insertTarifAtelierPanel.add(_TextTarifAtelier);
  }

  /**
   * creates the InsertTarifAtelier Panel that will be inserted in the JOptionDialog
   * when insertTarifAtelier is clicked in the file menu
   */
  private void insertTarifCantine() {
    insertTarifsCantinePanel = new JPanel();
    insertTarifsCantinePanel.setLayout(new GridLayout(2, 2));
    JLabel tarifName = new JLabel("Nom du Tarif");
    _TextTarifCantineNom = new JTextField(16);
    JLabel cantineTarif = new JLabel("Montant en euros");
    _TextTarifCantine = new JTextField(16);
    insertTarifsCantinePanel.add(tarifName);
    insertTarifsCantinePanel.add(_TextTarifCantineNom);
    insertTarifsCantinePanel.add(cantineTarif);
    insertTarifsCantinePanel.add(_TextTarifCantine);
  }

  /**
   * created the panel to insert in the joptiondialog when an "Eleve" is picked
   * in the comboBox and the button "atelier" is clicked
   */
  private void eleveAteliers() {
    int i = 0;
    boolUpdateAteliers = false;
    int atelier_id;
    int eleve_id;

    Student eleve2 = getStudentFromCombo(); //Creates the student picked in the combobox
    eleve_id = eleve2.getID();
    Atelier atelier2 = new Atelier(eleve_id, _Con); //creates ateliermanager for the eleve_id
    RefAtelier refatelier = new RefAtelier(_Con);
    //generates the checkboxes with the names
    //of the ateliers
    chkAteliers = new JCheckBox[refatelier.getAteliers().size()];
    eleveAteliersMainPanel = new JPanel(new BorderLayout()); //mainPanel insere in the JOptionDialog
    JPanel checkBoxesPanel = new JPanel(); //Panel des checkboxes
    //bottompanel contains the labels and textfields
    JPanel eleveAteliersBottomPanel = new JPanel(new GridLayout(3, 2));
    JLabel empty = new JLabel("Choisir tarif");
    eleveAteliersBottomPanel.add(empty);
    TarifsAteliers tarifsAteliersNoms = new TarifsAteliers(_Con); //tarifsAteliers object
    _TarifsAteliers = new JComboBox(tarifsAteliersNoms.getComboTarifsAteliers()); //combobox tarifs
    eleveAteliersBottomPanel.add(_TarifsAteliers);
    JLabel dateValidite = new JLabel(
        "Date de validit\u00E9 (Format : jj/mm/aaaa)");
    _DateValiditeAtelier = new JTextField(16);
    JLabel nombreJours = new JLabel("Nombre de jours :");
    _nbrJoursAteliers = new JTextField(16);

    //adds in the bottompanel
    eleveAteliersBottomPanel.add(dateValidite);
    eleveAteliersBottomPanel.add(_DateValiditeAtelier);
    eleveAteliersBottomPanel.add(nombreJours);
    eleveAteliersBottomPanel.add(_nbrJoursAteliers);

    //generates checkboxes given the array from refateliers
    for (Enumeration en = refatelier.getAteliers().elements();
         en.hasMoreElements(); ) {
      String s = (String) en.nextElement(); // Casts
      chkAteliers[i] = new JCheckBox(s);
      checkBoxesPanel.add(chkAteliers[i]);
      //System.out.println(s);
      i++;
    }

    //checks if checkboxes must b checked, looks up in an array with all
    //the atelier for 1 given student
    for (Enumeration enu = atelier2.getAteliersByEleve().elements();
         enu.hasMoreElements(); ) {
      atelier_id = Integer.parseInt( (String) enu.nextElement()); // Casts
      String d = refatelier.getAtelierName(atelier_id);
      //System.out.println("d = " + d);
      for (i = 0; i < chkAteliers.length; i++) {
        if (d.equalsIgnoreCase(chkAteliers[i].getText())) {
          boolUpdateAteliers = true; //to know if the eleve already has ateliers or not
          chkAteliers[i].setSelected(true);
        }
      }
      //get the days of etudes and put it in the correct textfield
      if (refatelier.getAtelierType(atelier_id).equalsIgnoreCase("e")) {
        _nbrJoursAteliers.setText(atelier2.getNbrJoursEtudes(eleve_id,
            atelier_id));
      }
    }
    checkBoxesPanel.setLayout(new GridLayout(i / 2, 2));
    //adds the 2 panels to the main panel
    eleveAteliersMainPanel.add(checkBoxesPanel, BorderLayout.NORTH);
    eleveAteliersMainPanel.add(eleveAteliersBottomPanel, BorderLayout.SOUTH);
    //if eleve already has ateliers in DB precise the date de validite and select price
    if (boolUpdateAteliers = true) {
      _DateValiditeAtelier.setText(atelier2.getDateValidite());
      _TarifsAteliers.setSelectedItem(atelier2.getPriceName());
    }
  }

  public void eleveCantine() {
    int i = 0;
    boolUpdateCantine = false;
    int eleve_id;

    Student eleve2 = getStudentFromCombo(); //Creates the student picked in the combobox
    eleve_id = eleve2.getID();
    Cantine cantine2 = new Cantine(eleve_id, _Con); //creates ateliermanager for the eleve_id

    eleveCantinePanel = new JPanel(new GridLayout(4, 2)); //mainPanel insere in the JOptionDialog
    JLabel cantine = new JLabel("Cantine");
    chkCantine = new JCheckBox("Cochez si oui");
    JLabel tarif = new JLabel("Choisir tarif");
    TarifsCantine tarifsCantineNoms = new TarifsCantine(_Con); //tarifsAteliers object
    _TarifsCantine = new JComboBox(tarifsCantineNoms.getComboTarifsCantine()); //combobox tarifs
    JLabel dateValidite = new JLabel(
        "Date de validit\u00E9  (Format : jj/mm/aaaa)");
    _TextCantineValidite = new JTextField(16);
    JLabel nombreJours = new JLabel("Nombre de jours :");
    _TextCantineJours = new JTextField(16);

    //adds in the bottompanel
    eleveCantinePanel.add(cantine);
    eleveCantinePanel.add(chkCantine);
    eleveCantinePanel.add(tarif);
    eleveCantinePanel.add(_TarifsCantine);
    eleveCantinePanel.add(dateValidite);
    eleveCantinePanel.add(_TextCantineValidite);
    eleveCantinePanel.add(nombreJours);
    eleveCantinePanel.add(_TextCantineJours);

    //checks if checkboxes must b checked, looks up in an array with all
    //the atelier for 1 given student
    if (cantine2.getEleveStatus()) {
      chkCantine.setSelected(true);
      _TextCantineJours.setText(cantine2.getNbrJours());
      _TarifsCantine.setSelectedItem(cantine2.getPriceName());
      _TextCantineValidite.setText(cantine2.getDateValidite());
      boolUpdateCantine = true; //to know if the eleve already has ateliers or not
    }
  }

  /**
   *populates the class combobox
   */
  public void populateListClasses() {
    try {
      comboLeftClasses.setVisible(false);
      comboLeftClasses.removeAllItems();
    }
    catch (Exception e) {
      System.out.println(e);
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    for (Enumeration en = classe.getcomboClasses().elements();
         en.hasMoreElements(); ) {
      String s = (String) en.nextElement(); // Casts
      comboLeftClasses.addItem(s);
      // System.out.println(s);
    }
    comboLeftClasses.setVisible(true);
  }

  /**
   *populates the tarifs combobox
   */
  public void populateListTarifs() {
    try {
      comboLeftTarifs.setVisible(false);
      comboLeftTarifs.removeAllItems();
    }
    catch (Exception e) {
      System.out.println(e);
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    for (Enumeration en = tarifs.getComboTarifsAteliers().elements();
         en.hasMoreElements(); ) {
      String s = (String) en.nextElement(); // Casts
      comboLeftTarifs.addItem(s);
      //System.out.println(s);
    }
    comboLeftTarifs.setVisible(true);
  }

  /**
   *populates the tarifs cantine combobox
   */
  public void populateListTarifsCantine() {
    try {
      comboLeftTarifsCantine.setVisible(false);
      comboLeftTarifsCantine.removeAllItems();
    }
    catch (Exception e) {
      System.out.println(e);
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    for (Enumeration en = tarifsCantine.getComboTarifsCantine().elements();
         en.hasMoreElements(); ) {
      String s = (String) en.nextElement(); // Casts
      comboLeftTarifsCantine.addItem(s);
      //System.out.println(s);
    }
    comboLeftTarifsCantine.setVisible(true);
  }

  /**
   * populates the eleves combobox
   */
  public void populateListEleves() {
    try {
      comboLeftEleves.setVisible(false);
      comboLeftEleves.removeAllItems();
    }
    catch (Exception e) {
      System.out.println(e);
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    for (Enumeration en = mainEleve.getcomboEleves().elements();
         en.hasMoreElements(); ) {
      String s = (String) en.nextElement(); // Casts
      comboLeftEleves.addItem(s);
      //System.out.println(s);
    }
    comboLeftEleves.setVisible(true);
  }

  /**
   *populates the atelier combobox
   */
  public void populateListAtelier() {
    try {
      comboLeftAtelier.setVisible(false);
      comboLeftAtelier.removeAllItems();
    }
    catch (Exception e) {
      System.out.println(e);
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    for (Enumeration en = mainAtelier.getAteliers().elements();
         en.hasMoreElements(); ) {
      String s = (String) en.nextElement(); // Casts
      comboLeftAtelier.addItem(s);
      //System.out.println(s);
    }
    comboLeftAtelier.setVisible(true);
  }

  public void disable(){
    comboTable.setEnabled(false);
    effacer.setEnabled(false);
    sauver.setEnabled(false);
  }

  /**
   * Action Performed
   * @param Evt
   */
  public void actionPerformed(ActionEvent Evt) {
    int cnt = 0; //use in the "atelier eleve Action
    if (Evt.getActionCommand().equals("Ins\u00E9rer \u00E9l\u00E8ve")) {
      setCursor(new Cursor(Cursor.WAIT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      disable();
      StringTokenizer st;
      String dob;
      String day;
      String month;
      String year;

      insertEleve();
      JOptionPane pane = new JOptionPane(mainInsertPanel,
                                         JOptionPane.QUESTION_MESSAGE,
                                         JOptionPane.OK_CANCEL_OPTION);
      JDialog dialog = pane.createDialog(EcoleApp.this,
                                         "Nouvel \u00E9l\u00E8ve");
      dialog.show();
      Integer value = (Integer) pane.getValue();
      if (value.intValue() == JOptionPane.OK_OPTION) { //if boutton ok is pressed insert info in db
        table.setVisible(false);
        textAreaRight.setVisible(true);
        textPane.setViewportView(textAreaRight);
        if (_TextPrenom.getText().length() == 0 ||
            _TextNom.getText().length() == 0 ||
            _TextSex.getText().length() == 0 ||
            _TextDob.getText().length() == 0 ||
            _TextStreet.getText().length() == 0 ||
            _TextCity.getText().length() == 0 ||
            _TextZip.getText().length() == 0 ||
            _TextDateEntree.getText().length() == 0) {
          JOptionPane.showMessageDialog(this,
              "Certains champs (autres que les champs \"T\u00E9l\u00E9phone\") sont vides.",
              "Information",
              JOptionPane.INFORMATION_MESSAGE);
        }
        else {

          Student eleve = new Student(_Con);
          eleve.setFirstNameLastName(_TextPrenom.getText(), _TextNom.getText());
          eleve.setSex(_TextSex.getText());
          dob = _TextDob.getText();
          st = new StringTokenizer(dob, "/");
          day = st.nextToken();
          month = st.nextToken();
          year = st.nextToken();
          eleve.setDob(year + "-" + month + "-" + day);
          eleve.setStreet(_TextStreet.getText());
          eleve.setCity(_TextCity.getText());
          eleve.setZip(_TextZip.getText());
          eleve.setTel1(_TextTel1.getText());
          // System.out.println(_TextTel1.getText());
          eleve.setTel2(_TextTel2.getText());
          eleve.setTel3(_TextTel3.getText());
          int classeId = _ComboClass.getSelectedIndex() + 1;
          eleve.setClasseId(classeId);
          dob = _TextDateEntree.getText();
          st = new StringTokenizer(dob, "/");
          day = st.nextToken();
          month = st.nextToken();
          year = st.nextToken();
          eleve.setDateEntree(year + "-" + month + "-" + day);
          this.populateListEleves(); //repopulates the eleve combobox to refresh it
          comboLeftEleves.setSelectedItem(eleve.getLName() + "," +
                                          eleve.getFName());
          this.displayStudent();
        }
      }
      else {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        return;
      }
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    else if (Evt.getActionCommand().equals("Modifier \u00E9l\u00E8ve")) {
      setCursor(new Cursor(Cursor.WAIT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      disable();
      StringTokenizer st;
      String dob;
      String day;
      String month;
      String year;

      insertEleve();
      Student eleveToModify = getStudentFromCombo();
      //System.out.println("id" + eleveToModify.getID());
      //System.out.println(eleveToModify.getFName());
      //sets the known values in the proper textfield in the form
      _TextPrenom.setText(eleveToModify.getFName());
      _TextNom.setText(eleveToModify.getLName());
      _TextSex.setText(eleveToModify.getSex());
      _TextDob.setText(eleveToModify.getDob());
      _TextStreet.setText(eleveToModify.getStreet());
      _TextCity.setText(eleveToModify.getCity());
      _TextZip.setText(eleveToModify.getZip());
      _TextTel1.setText(eleveToModify.getTel1());
      _TextTel2.setText(eleveToModify.getTel2());
      _TextTel3.setText(eleveToModify.getTel3());
      Classe classeOfEleveToModify = new Classe(_Con); // to retrieve the class nom
      _ComboClass.setSelectedItem(classeOfEleveToModify.getClasseNom(
          eleveToModify.getClasseId()));
      _TextDateEntree.setText(eleveToModify.getDateEntree());
      //end---
      JOptionPane panel = new JOptionPane(mainInsertPanel,
                                          JOptionPane.QUESTION_MESSAGE,
                                          JOptionPane.OK_CANCEL_OPTION);
      JDialog dialog = panel.createDialog(EcoleApp.this,
                                          "Modifier \u00E9l\u00E8ve");
      dialog.show();
      Integer value = (Integer) panel.getValue();
      if (value.intValue() == JOptionPane.OK_OPTION) {
        table.setVisible(false);
        textAreaRight.setVisible(true);
        textPane.setViewportView(textAreaRight);
        if (_TextPrenom.getText().length() == 0 ||
            _TextNom.getText().length() == 0 ||
            _TextSex.getText().length() == 0 ||
            _TextDob.getText().length() == 0 ||
            _TextStreet.getText().length() == 0 ||
            _TextCity.getText().length() == 0 ||
            _TextZip.getText().length() == 0 ||
            _TextDateEntree.getText().length() == 0) {
          JOptionPane.showMessageDialog(this,
              "Certains champs (autres que les champs \"T\u00E9l\u00E9phone\") sont vides.",
              "Information",
              JOptionPane.INFORMATION_MESSAGE);
        }
        else {

          eleveToModify.updateFirstName(eleveToModify.getID(),
                                        _TextPrenom.getText());
          eleveToModify.updateLastName(eleveToModify.getID(), _TextNom.getText());
          eleveToModify.setSex(_TextSex.getText());
          dob = _TextDob.getText();
          st = new StringTokenizer(dob, "/");
          day = st.nextToken();
          month = st.nextToken();
          year = st.nextToken();
          eleveToModify.setDob(year + "-" + month + "-" + day);
          eleveToModify.setStreet(_TextStreet.getText());
          eleveToModify.setCity(_TextCity.getText());
          eleveToModify.setZip(_TextZip.getText());
          eleveToModify.setTel1(_TextTel1.getText());
          //System.out.println(_TextTel1);
          eleveToModify.setTel2(_TextTel2.getText());
          eleveToModify.setTel3(_TextTel3.getText());
          int classeId = _ComboClass.getSelectedIndex() + 1;
          eleveToModify.setClasseId(classeId);
          dob = _TextDateEntree.getText();
          st = new StringTokenizer(dob, "/");
          day = st.nextToken();
          month = st.nextToken();
          year = st.nextToken();
          eleveToModify.setDateEntree(year + "-" + month + "-" + day);
          this.populateListEleves(); //repopulates the eleve combobox to refresh it
          //System.out.println("\u00E9l\u00E8ve \u00E0 modifier : " +
          //                  eleveToModify.getLName() + " " +
          //                  eleveToModify.getFName());
          comboLeftEleves.setSelectedItem(eleveToModify.getLName() + "," +
                                          eleveToModify.getFName());
          displayStudent();
        }
      }
      else {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        return;
      }
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    else if (Evt.getActionCommand().equals("Suprimer \u00E9l\u00E8ve")) { // Delete student selected in comboBox
      setCursor(new Cursor(Cursor.WAIT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      disable();
      Student eleveToDelete = getStudentFromCombo();
      String studentName = eleveToDelete.getFName() + " " +
          eleveToDelete.getLName();
      int rslt = JOptionPane.showConfirmDialog(EcoleApp.this,
          "Etes-vous sur de vouloir suprimer " + studentName,
          "Supression d'\u00E9l\u00E8ve",
          JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE); //Confirmation dialog box
      if (rslt == JOptionPane.YES_OPTION) { //if yes is pressed
        eleveToDelete.eraseStudentInfo();
        this.populateListEleves(); //repopulates the eleve combobox to refresh it
        table.setVisible(false);
        textAreaRight.setVisible(true);
        textPane.setViewportView(textAreaRight);
        textAreaRight.setText("L'\u00E9l\u00E8ve " + eleveToDelete.getFName() +
                              " " + eleveToDelete.getLName() +
                              " \u00E0 \u00E9t\u00E9 suprim\u00E9.");
        printTitle = "Supression de l'\u00E9l\u00E8ve " +
            eleveToDelete.getFName() + " " + eleveToDelete.getLName();
        this.setTitle("Gestion d'\u00E9cole - Supression de l'\u00E9l\u00E8ve " +
                      eleveToDelete.getFName() + " " + eleveToDelete.getLName());
      }
      tableprint = false;
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    else if (Evt.getSource().equals(comboLeftEleves)) { //action of the eleves comboBox
      setCursor(new Cursor(Cursor.WAIT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      disable();
      table.setVisible(false);
      textAreaRight.setVisible(true);
      textPane.setViewportView(textAreaRight);
      //System.out.println("got name");
      displayStudent();
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    else if (Evt.getActionCommand().equals("Nouvelle classe")) {
      setCursor(new Cursor(Cursor.WAIT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      disable();
      insertClasse();
      JOptionPane panel = new JOptionPane(insertClassePanel,
                                          JOptionPane.QUESTION_MESSAGE,
                                          JOptionPane.OK_CANCEL_OPTION);
      JDialog dialog = panel.createDialog(EcoleApp.this, "Nouvelle Classe");
      dialog.show();
      Integer value = (Integer) panel.getValue();
      if (value.intValue() == JOptionPane.OK_OPTION) { //if ok button is pressed
        table.setVisible(false);
        textAreaRight.setVisible(true);
        textPane.setViewportView(textAreaRight);
        tableprint = false;
        if (_TextClasse.getText().length() == 0 ||
            _TextInstituteur.getText().length() == 0) {
          JOptionPane.showMessageDialog(this,
                                        "Certains champs sont vides.",
                                        "Information",
                                        JOptionPane.INFORMATION_MESSAGE);
        }
        else {

          String classeNom = _TextClasse.getText();
          Classe classe = new Classe(_TextClasse.getText(),
                                     _TextInstituteur.getText(), _Con);
          this.populateListClasses(); //refresh combobox
          comboLeftClasses.setSelectedItem(classeNom);
        }
      }
      else {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        return;
      }
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    else if (Evt.getActionCommand().equals("Modifier classe")) {
      setCursor(new Cursor(Cursor.WAIT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      disable();
      insertClasse();
      Classe classe = this.getClasseFromCombo();
      //System.out.println("classe from combo = " +
      //                  (String) comboLeftClasses.getSelectedItem());
      //    int ID = classe.getID( (String) comboLeftClasses.getSelectedItem()); //set the class selected in combo
      //System.out.println("instit" + classe.getInstituteur());
      _TextInstituteur.setText(classe.getInstituteur()); // set the instituteur text area
      // System.out.println("classe" + classe.getClasseNom());
      _TextClasse.setText(classe.getClasseNom()); // set the class text area
      JOptionPane panel = new JOptionPane(insertClassePanel,
                                          JOptionPane.QUESTION_MESSAGE,
                                          JOptionPane.OK_CANCEL_OPTION);
      JDialog dialog = panel.createDialog(EcoleApp.this, "Modifier Classe");
      dialog.show();
      Integer value = (Integer) panel.getValue();
      if (value.intValue() == JOptionPane.OK_OPTION) { //if ok button is pressed
        table.setVisible(false);
        textAreaRight.setVisible(true);
        textPane.setViewportView(textAreaRight);
        tableprint = false;
        if (_TextClasse.getText().length() == 0 ||
            _TextInstituteur.getText().length() == 0) {
          JOptionPane.showMessageDialog(this,
                                        "Certains champs sont vides.",
                                        "Information",
                                        JOptionPane.INFORMATION_MESSAGE);
        }
        else {

          classe.setClasseNom(_TextClasse.getText()); //change the classe nom
          classe.setInstituteur(_TextInstituteur.getText()); //change the insituteur nom
          this.populateListClasses(); //refresh combobox
          comboLeftClasses.setSelectedItem(classe.getClasseNom());
        }
      }
      else {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        return;
      }

      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    else if (Evt.getSource().equals(comboLeftClasses)) { //action of the classe comboBox
      setCursor(new Cursor(Cursor.WAIT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      disable();
      printTitle = comboLeftClasses.getSelectedItem() + "";
      this.setTitle("Gestion d'\u00E9cole - " + printTitle);
      table.setVisible(false);
      textAreaRight.setVisible(true);
      textPane.setViewportView(textAreaRight);
      String comboLeft = "";
      //System.out.println("got classe");
      Classe classeFromCombo = getClasseFromCombo();
      comboLeft = "<table border=0 align=center><tr><td colspan=2><center><h1>"
          + comboLeftClasses.getSelectedItem() + "</h1></center></td></tr>";
      //  int ID = classeFromCombo.getID( (String) comboLeftClasses.getSelectedItem()); //set the class selected in combo
      //    textAreaRight.setText("Classe : " + comboLeftClasses.getSelectedItem() +
      //                        "\n");
      if (classeFromCombo.getInstituteur() != null) {
        comboLeft += "<tr><td><b>Instituteur : </b></td><td>" +
            classeFromCombo.getInstituteur() + "</td></tr>";
        // textAreaRight.append("Instituteur : " + classeFromCombo.getInstituteur() +
        //                       "\n");
      }
      else {
        comboLeft += "<tr><td><b>Instituteur : </b></td><td>&nbsp;</td></tr>";
        //   textAreaRight.append("Instituteur :");
      }
      textAreaRight.setText(comboLeft += "</table>");
      tableprint = false;
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    else if (Evt.getActionCommand().equals("Suprimer classe")) {
      setCursor(new Cursor(Cursor.WAIT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      disable();
      Classe classeToSupr = getClasseFromCombo();
      int rslt = JOptionPane.showConfirmDialog(EcoleApp.this,
          "Etes-vous sur de vouloir suprimer " + classeToSupr.getClasseNom() +
          " ?", "Supression de classe",
          JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE); //Confirmation dialog box
      if (rslt == JOptionPane.YES_OPTION) { //if yes is pressed
        table.setVisible(false);
        textAreaRight.setVisible(true);
        textPane.setViewportView(textAreaRight);
        tableprint = false;
        classeToSupr.eraseClasse();
        this.populateListClasses(); //repopulates the classe combobox to refresh it
        textAreaRight.setText("La classe " + classeToSupr.getClasseNom() +
                              " a \u00E9t\u00E9e suprim\u00E8e.");
        printTitle = "Supression de la classe : " + classeToSupr.getClasseNom();
        this.setTitle("Gestion d'\u00E9cole - " + printTitle);
      }
      else {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        return;
      }
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    else if (Evt.getSource().equals(listeEleve) ||
             Evt.getActionCommand().equalsIgnoreCase(
        "Liste d'\u00E9l\u00E8ves par classe")) { //affiche liste eleve pour une classe
      setCursor(new Cursor(Cursor.WAIT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      disable();
      String currentClasse = comboLeftClasses.getSelectedItem() + "";
      table = new JTable(new TableModelListClasse(_Con, currentClasse)); //NEW
      //sorter.setTableHeader(table.getTableHeader()); //ADDED THIS
      // CustomRenderer mtr = new CustomRenderer();
      // table.setDefaultRenderer(Object.class, mtr);
      //  table.setDefaultRenderer(Integer.class, mtr);
      // table.getModel().addTableModelListener(this);
      textAreaRight.setVisible(false);
      table.setVisible(true);
      tableprint = true;
      status.setText("Liste d'\u00E9l\u00E8ves de : " + currentClasse);
      printTitle = currentClasse;
      this.setTitle("Gestion d'\u00E9cole - Liste d'\u00E9l\u00E8ves de : " +
                    currentClasse);
      textPane.setViewportView(table);

      //Classe classeForListe = getClasseFromCombo();
      // Student listeStudent = new Student(_Con);
      // String liste = "<table border=0 align=center cellpadding=5><tr><td colspan=4 align=center><h1>"
      //     +comboLeftClasses.getSelectedItem()+"</h1></td></tr>"+
      //     "<tr><td align=center><b>Nom</b></td><td align=center><b>Prenom</b></td><td align=center><b>Sexe</b></td><td align=center><b>Date de naissance</b></td></tr>";
      // for (int i=0; i<listeStudent.getList(classeForListe.getID()).length; i++){
      //    Student eleve = listeStudent.getList(classeForListe.getID())[i];
      //   liste += "<tr><td>"+eleve.getLName()+"</td><td>"+eleve.getFName()+"</td><td align=center>"
      //       +eleve.getSex()+"</td><td>"+eleve.getDob()+"</td></tr>";
      // }
      //  textAreaRight.setText(liste+"</table>");
      //  tableprint = false;
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    else if (Evt.getActionCommand().equals("Inscrire \u00E0 un atelier") ||
             Evt.getActionCommand().equals("Atelier")) {
      setCursor(new Cursor(Cursor.WAIT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      disable();
      StringTokenizer st;
      String date;
      String day;
      String month;
      String year;
      eleveAteliers();
      JOptionPane pane = new JOptionPane(eleveAteliersMainPanel,
                                         JOptionPane.QUESTION_MESSAGE,
                                         JOptionPane.OK_CANCEL_OPTION);
      JDialog dialog = pane.createDialog(EcoleApp.this,
                                         "Atelier \u00E9l\u00E8ve");
      
      Integer value = null;
      while(true)
      {                                   
        dialog.show();
        value = (Integer) pane.getValue();
        if (value.intValue() == JOptionPane.OK_OPTION) 
        {
            // Verification si les saisies sont OK
            String err = Atelier.isInputValid(_DateValiditeAtelier.getText(), _nbrJoursAteliers.getText(), _TarifsAteliers.getSelectedIndex());
            if (null == err)
            {
                break;
            }
            else
            {
                Dialog.ErreurDeSaisie(this, err);
            }
        }
        else
        {
            break; // Annuler
        }
      }      
      
      if (value.intValue() == JOptionPane.OK_OPTION) {
        Student ateliersOfeleve = getStudentFromCombo();
        Atelier atelier = new Atelier(ateliersOfeleve.getID(), _Con);
        RefAtelier refatelier = new RefAtelier(_Con);

        if (boolUpdateAteliers == true) { //if eleve already has ateliers
          atelier.eraseStudentAteliers(); //erase all in db to refresh
        }
        for (int i = 0; i < chkAteliers.length; i++) {
          if (chkAteliers[i].isSelected()) { //refresh ateliers info for current eleve
            cnt++;
            atelier.setEleveIdAndAtelier(refatelier.getAtelierId(chkAteliers[i].
                getText()));
            atelier.setPriceName( (String) _TarifsAteliers.getSelectedItem());
            atelier.setPrice( (String) _TarifsAteliers.getSelectedItem());
            date = _DateValiditeAtelier.getText();
            st = new StringTokenizer(date, "/");
            day = st.nextToken();
            month = st.nextToken();
            year = st.nextToken();
            atelier.setDateValidite(year + "-" + month + "-" + day);
            //  System.out.println("datevalidite:" + _DateValiditeAtelier.getText());
            //  System.out.println("checkbox=  " + chkAteliers[i].getText());
            // refresh the number of days of etudes. if no etude the textfield is blank
            if (chkAteliers[i].getText().equalsIgnoreCase("etude") ||
                chkAteliers[i].getText().equalsIgnoreCase("etudes") ||
                chkAteliers[i].getText().equalsIgnoreCase("\u00E8tudes") ||
                chkAteliers[i].getText().equalsIgnoreCase("\u00E8tude")) {
              //refatelier.getAtelierId(chkAteliers[i].getText());
              atelier.setNbrJoursEtude(Integer.parseInt(_nbrJoursAteliers.
                  getText()), refatelier.getAtelierId(chkAteliers[i].getText()));
            }
          }
        }
        comboLeftEleves.setSelectedItem(ateliersOfeleve.getLName() + "," +
                                        ateliersOfeleve.getFName()); // refresh the diplay
      }
      tableprint = false;
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    else if (Evt.getActionCommand().equals("Nouvel atelier")) {
      setCursor(new Cursor(Cursor.WAIT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      disable();
      insertAtelier();
      JOptionPane pane = new JOptionPane(insertAtelierPanel,
                                         JOptionPane.QUESTION_MESSAGE,
                                         JOptionPane.OK_CANCEL_OPTION);
      JDialog dialog = pane.createDialog(EcoleApp.this, "Nouvel Atelier");
      dialog.show();
      Integer value = (Integer) pane.getValue();
      if (value.intValue() == JOptionPane.OK_OPTION) {
        textAreaRight.setVisible(true);
        table.setVisible(false);
        textPane.setViewportView(textAreaRight);
        tableprint = false;
        if (_TextAtelierNom.getText().length() == 0 ||
            _TextTypeAtelier.getText().length() == 0) {
          JOptionPane.showMessageDialog(this,
                                        "Certains champs sont vides.",
                                        "Information",
                                        JOptionPane.INFORMATION_MESSAGE);
        }
        else {

          RefAtelier refatelier = new RefAtelier(_Con);
          refatelier.setAtelierName(_TextAtelierNom.getText());
          String atelierNom = _TextAtelierNom.getText();
          refatelier.setJour(_TextJourAtelier.getText());
          refatelier.setAtelierType(_TextTypeAtelier.getText());
          populateListAtelier();
          this.comboLeftAtelier.setSelectedItem(atelierNom);
        }
      }
      else {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        return;
      }

      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    else if (Evt.getSource().equals(listeElevesAtelier) ||
             Evt.getActionCommand().equalsIgnoreCase(
        "Liste d'\u00E9l\u00E8ves par atelier")) {
      setCursor(new Cursor(Cursor.WAIT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      disable();
      String currentAtelier = (String) comboLeftAtelier.getSelectedItem();
      RefAtelier refatelier = new RefAtelier(currentAtelier, _Con);
      int atelierID = refatelier.getAtelierId();
      String jour = refatelier.getAtelierJour(atelierID);
      table = new JTable(new TableModelListAtelier(_Con, currentAtelier)); //NEW
      //sorter.setTableHeader(table.getTableHeader()); //ADDED THIS
      // CustomRenderer mtr = new CustomRenderer();
      // table.setDefaultRenderer(Object.class, mtr);
      //  table.setDefaultRenderer(Integer.class, mtr);
      //  table.getModel().addTableModelListener(this);
      textAreaRight.setVisible(false);
      tableprint = true;
      status.setText("Liste d'\u00E9l\u00E8ves de l'atelier : " +
                     currentAtelier + " du " + jour);
      printTitle = currentAtelier + "\n" + jour;
      this.setTitle(
          "Gestion d'\u00E9cole - Liste d'\u00E9l\u00E8ves de l'atelier : " +
          currentAtelier + " du " + jour);
      textPane.setViewportView(table);

      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    else if (Evt.getActionCommand().equalsIgnoreCase(
        "Liste d'\u00E9l\u00E8ves par classe + atelier") ||
             Evt.getSource().equals(listeAteliers)) { // list atelier par class
      setCursor(new Cursor(Cursor.WAIT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      disable();
      textAreaRight.setVisible(false);
      String currentClasse = (String) comboLeftClasses.getSelectedItem();
      table = new JTable(new TableModelListAtelierByClasse(_Con, currentClasse)); //NEW
      //sorter.setTableHeader(table.getTableHeader()); //ADDED THIS
      // CustomRenderer mtr = new CustomRenderer();
      // table.setDefaultRenderer(Object.class, mtr);
      //  table.setDefaultRenderer(Integer.class, mtr);
      //  table.getModel().addTableModelListener(this);
      textAreaRight.setVisible(false);
      tableprint = true;
      status.setText("Liste d'\u00E9l\u00E8ves du : " + currentClasse +
                     " avec les ateliers");
      printTitle = currentClasse + "\nAteliers";
      this.setTitle("Gestion d'\u00E9cole - Liste d'\u00E9l\u00E8ves du : " +
                    currentClasse + " avec les ateliers");
      textPane.setViewportView(table);

      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    else if (Evt.getSource().equals(comboLeftAtelier)) { //action of the atelier comboBox
      setCursor(new Cursor(Cursor.WAIT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      disable();
      textAreaRight.setVisible(true);
      table.setVisible(false);
      textPane.setViewportView(textAreaRight);
      tableprint = false;
      printTitle = comboLeftAtelier.getSelectedItem() + "";
      this.setTitle("Gestion d'\u00E9cole - " + printTitle);
      String atelier = "";
      //System.out.println("got atelier");
      RefAtelier atelierFromCombo = getRefAtelierFromCombo();
      //  int ID = classeFromCombo.getID( (String) comboLeftClasses.getSelectedItem()); //set the class selected in combo
      atelier = "<table border=0 align=center width=100%><tr><td colspan=2><center><h1>Fiche Atelier</h1></center></td></tr><tr><td align=right width=50%><b>Atelier :</b></td><td>"
          + comboLeftAtelier.getSelectedItem() + "</td></tr>";
      //System.out.println(atelierFromCombo.getAtelierId());
      if (atelierFromCombo.getAtelierJour(atelierFromCombo.getAtelierId()) != null) {
        atelier += "<tr><td align=right width=50%><b>Jour :</b></td><td>" +
            atelierFromCombo.getAtelierJour(atelierFromCombo.getAtelierId())
            + "</td></tr> ";
        // textAreaRight.append("Jour : " +
        //                      atelierFromCombo.getAtelierJour(atelierFromCombo.getAtelierId())+
        //                      "\n");
      }
      else {
        atelier +=
            "<tr><td align=right width=50%><b>Jour :</b></td><td>&nbsp;</td></tr> ";
        //textAreaRight.append("Jour :\n");
      }
      if (atelierFromCombo.getAtelierType(atelierFromCombo.getAtelierId()) != null) {
        atelier += "<tr><td align=right width=50%><b>Type :</b></td><td>" +
            atelierFromCombo.getAtelierType(atelierFromCombo.getAtelierId())
            + "</td></tr> ";
        // textAreaRight.append("Type : " +
        //                      atelierFromCombo.getAtelierType(atelierFromCombo.getAtelierId())+
        //                       "\n");
      }
      else {
        atelier +=
            "<tr><td align=right width=50%><b>Type :</b></td><td>&nbsp;</td></tr> ";
        // textAreaRight.append("Type :");
      }
      textAreaRight.setText(atelier += "</table>");
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    else if (Evt.getActionCommand().equals("Suprimer atelier")) {
      setCursor(new Cursor(Cursor.WAIT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      disable();
      RefAtelier atelierToSupr = getRefAtelierFromCombo();
      String nomAtelier = (String) comboLeftAtelier.getSelectedItem();
      int rslt = JOptionPane.showConfirmDialog(EcoleApp.this,
          "Etes-vous sur de vouloir suprimer " + nomAtelier + " ?",
          "Supression d'atelier",
          JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE); //Confirmation dialog box
      if (rslt == JOptionPane.YES_OPTION) { //if yes is pressed
        textAreaRight.setVisible(true);
        table.setVisible(false);
        textPane.setViewportView(textAreaRight);
        tableprint = false;

        atelierToSupr.eraseAtelier();
        this.populateListAtelier(); //repopulates the classe combobox to refresh it
        textAreaRight.setText("L'atelier " + nomAtelier +
                              " a \u00E9t\u00E9 suprim\u00E9.");
        printTitle = "Supression de l'atelier : " + nomAtelier;
        this.setTitle("Gestion d'\u00E9cole - " + printTitle);
      }
      else {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        return;
      }
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    else if (Evt.getActionCommand().equals("Modifier atelier")) {
      setCursor(new Cursor(Cursor.WAIT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      disable();
      insertAtelier();
      String atelier = (String) comboLeftAtelier.getSelectedItem();
      RefAtelier atelierToModify = this.getRefAtelierFromCombo();
      //System.out.println("atelier from combo = " +
      //                   (String) comboLeftAtelier.getSelectedItem());
      //    int ID = classe.getID( (String) comboLeftClasses.getSelectedItem()); //set the class selected in combo
      //  System.out.println("instit" + classe.getInstituteur());
      _TextAtelierNom.setText(atelier); // set the instituteur text area
      //   System.out.println("classe" + classe.getClasseNom());
      _TextTypeAtelier.setText(atelierToModify.getAtelierType(atelierToModify.
          getAtelierId())); // set the class text area
      _TextJourAtelier.setText(atelierToModify.getAtelierJour(atelierToModify.
          getAtelierId()));
      JOptionPane panel = new JOptionPane(insertAtelierPanel,
                                          JOptionPane.QUESTION_MESSAGE,
                                          JOptionPane.OK_CANCEL_OPTION);
      JDialog dialog = panel.createDialog(EcoleApp.this,
                                          "Modifier atelier " + atelier);
      dialog.show();
      Integer value = (Integer) panel.getValue();
      if (value.intValue() == JOptionPane.OK_OPTION) { //if ok button is pressed
        textAreaRight.setVisible(true);
        table.setVisible(false);
        textPane.setViewportView(textAreaRight);
        tableprint = false;
        if (_TextAtelierNom.getText().length() == 0 ||
            _TextTypeAtelier.getText().length() == 0) {
          JOptionPane.showMessageDialog(this,
                                        "Certains champs sont vides.",
                                        "Information",
                                        JOptionPane.INFORMATION_MESSAGE);
        }
        else {

          String name = _TextAtelierNom.getText();
          atelierToModify.updateAtelierName(_TextAtelierNom.getText()); //change the atelier nom
          atelierToModify.setAtelierType(_TextTypeAtelier.getText()); //change the atelier type
          atelierToModify.setJour(_TextJourAtelier.getText()); //change the atelier jour
          this.populateListAtelier(); //refresh combobox
          comboLeftAtelier.setSelectedItem(name);
        }
      }
      else {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        return;
      }
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    else if (Evt.getActionCommand().equals("Nouveau tarif atelier")) {
      setCursor(new Cursor(Cursor.WAIT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      disable();
      insertTarifAtelier();
      JOptionPane panel = new JOptionPane(insertTarifAtelierPanel,
                                          JOptionPane.QUESTION_MESSAGE,
                                          JOptionPane.OK_CANCEL_OPTION);
      JDialog dialog = panel.createDialog(EcoleApp.this,
                                          "Nouveau Tarif Atelier");
      dialog.show();
      Integer value = (Integer) panel.getValue();
      if (value.intValue() == JOptionPane.OK_OPTION) { //if ok button is pressed
        textAreaRight.setVisible(true);
        table.setVisible(false);
        textPane.setViewportView(textAreaRight);
        tableprint = false;
        if (_TextTarifAtelierNom.getText().length() == 0 ||
            _TextTarifAtelier.getText().length() == 0) {
          JOptionPane.showMessageDialog(this,
                                        "Certains champs sont vides.",
                                        "Information",
                                        JOptionPane.INFORMATION_MESSAGE);
        }
        else {

          TarifsAteliers tarifs = new TarifsAteliers(_Con);
          tarifs.setNom(_TextTarifAtelierNom.getText());
          tarifs.setPrix(Double.parseDouble(_TextTarifAtelier.getText()));
          this.populateListTarifs(); //refresh combobox
          comboLeftTarifs.setSelectedItem(_TextTarifAtelierNom.getText());
        }
      }
      else {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        return;
      }

      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    else if (Evt.getSource().equals(comboLeftTarifs)) { //action of the tarifs comboBox
      setCursor(new Cursor(Cursor.WAIT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      disable();
      textAreaRight.setVisible(true);
      table.setVisible(false);
      textPane.setViewportView(textAreaRight);
      tableprint = false;
      printTitle = comboLeftTarifs.getSelectedItem() + "";
      this.setTitle("Gestion d'\u00E9cole - " + printTitle);
      //System.out.println("got tarif");
      String atelier =
          "<table border=0 align=center width=100%><tr><td colspan=2><center>"
          + "<h1>Tarif Atelier</h1></center></td></tr><tr><td align=right width=50%><b>Tarif :</b></td><td width=50%>"
          + comboLeftTarifs.getSelectedItem() + "</td></tr>";
      TarifsAteliers tarifFromCombo = getTarifsAteliersFromCombo();
      //  int ID = classeFromCombo.getID( (String) comboLeftClasses.getSelectedItem()); //set the class selected in combo
      // textAreaRight.setText("Tarif : " + comboLeftTarifs.getSelectedItem() +
      //                        "\n");
      if (tarifFromCombo.getPrix() + "" != null) {
        atelier +=
            "<tr><td align=right width=50%><b>Prix :</b></td><td width=50%>" +
            tarifFromCombo.getPrix() + " Euros</td></tr>";
        //   textAreaRight.append("Prix : " + tarifFromCombo.getPrix()+
        //                        " Euros\n");
      }
      else {
        atelier +=
            "<tr><td align=right width=50%>Prix :</td><td>&nbsp</td></tr>";
        //  textAreaRight.append("Prix :");
      }
      textAreaRight.setText(atelier + "</table>");
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    else if (Evt.getActionCommand().equals("Suprimer tarif atelier") ||
             Evt.getSource().equals(suprTarif)) {
      setCursor(new Cursor(Cursor.WAIT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      disable();
      TarifsAteliers tarifToDelete = getTarifsAteliersFromCombo();
      String nomTarif = (String) comboLeftTarifs.getSelectedItem();
      int rslt = JOptionPane.showConfirmDialog(EcoleApp.this,
          "Etes-vous sur de vouloir suprimer " + nomTarif + " ?",
          "Supression de tarif atelier",
          JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE); //Confirmation dialog box
      if (rslt == JOptionPane.YES_OPTION) { //if yes is pressed
        textAreaRight.setVisible(true);
        table.setVisible(false);
        textPane.setViewportView(textAreaRight);
        tableprint = false;
        tarifToDelete.eraseTarif();
        this.populateListTarifs(); //repopulates the classe combobox to refresh it
        textAreaRight.setText("Le tarif atelier " + nomTarif +
                              " \u00E0 \u00E9t\u00E9 suprim\u00E9.");
        printTitle = "Supression du : " + nomTarif + " des ateliers";
        this.setTitle("Gestion d'\u00E9cole - " + printTitle);
      }
      else {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        return;
      }
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    else if (Evt.getSource().equals(modifyTarif) ||
             Evt.getActionCommand().equalsIgnoreCase("Modifier tarif atelier")) {
      setCursor(new Cursor(Cursor.WAIT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      disable();
      insertTarifAtelier();
      String nomTarif = (String) comboLeftTarifs.getSelectedItem();
      TarifsAteliers tarifToModify = getTarifsAteliersFromCombo();
      // System.out.println("atelier from combo = " +
      //                   (String) comboLeftAtelier.getSelectedItem());
      //    int ID = classe.getID( (String) comboLeftClasses.getSelectedItem()); //set the class selected in combo
      //  System.out.println("instit" + classe.getInstituteur());
      _TextTarifAtelierNom.setText(nomTarif);
      _TextTarifAtelier.setText(tarifToModify.getPrix() + "");
      JOptionPane panel = new JOptionPane(insertTarifAtelierPanel,
                                          JOptionPane.QUESTION_MESSAGE,
                                          JOptionPane.OK_CANCEL_OPTION);
      JDialog dialog = panel.createDialog(EcoleApp.this,
                                          "Modifier tarif atelier");
      dialog.show();
      Integer value = (Integer) panel.getValue();
      if (value.intValue() == JOptionPane.OK_OPTION) { //if ok button is pressed
        textAreaRight.setVisible(true);
        table.setVisible(false);
        textPane.setViewportView(textAreaRight);
        tableprint = false;
        if (_TextTarifAtelierNom.getText().length() == 0 ||
            _TextTarifAtelier.getText().length() == 0) {
          JOptionPane.showMessageDialog(this,
                                        "Certains champs sont vides.",
                                        "Information",
                                        JOptionPane.INFORMATION_MESSAGE);
        }
        else {

          tarifToModify.updateNom(_TextTarifAtelierNom.getText()); //change the tarif nom
          tarifToModify.setPrix(Double.parseDouble(_TextTarifAtelier.getText())); //change the tarif prix
          this.populateListTarifs(); //refresh combobox
          comboLeftTarifs.setSelectedItem(nomTarif);
        }
      }
      else {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        return;
      }

      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    } //////////
    else if (Evt.getActionCommand().equalsIgnoreCase("Nouveau tarif cantine")) {
      setCursor(new Cursor(Cursor.WAIT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      disable();
      insertTarifCantine();
      JOptionPane panel = new JOptionPane(insertTarifsCantinePanel,
                                          JOptionPane.QUESTION_MESSAGE,
                                          JOptionPane.OK_CANCEL_OPTION);
      JDialog dialog = panel.createDialog(EcoleApp.this,
                                          "Nouveau Tarif Cantine");
      dialog.show();
      Integer value = (Integer) panel.getValue();
      if (value.intValue() == JOptionPane.OK_OPTION) { //if ok button is pressed
        textAreaRight.setVisible(true);
        table.setVisible(false);
        textPane.setViewportView(textAreaRight);
        tableprint = false;
        if (_TextTarifCantineNom.getText().length() == 0 ||
            _TextTarifCantine.getText().length() == 0) {
          JOptionPane.showMessageDialog(this,
                                        "Certains champs sont vides.",
                                        "Information",
                                        JOptionPane.INFORMATION_MESSAGE);
        }
        else {

          TarifsCantine tarifsCantine = new TarifsCantine(_Con);
          tarifsCantine.setNom(_TextTarifCantineNom.getText());
          tarifsCantine.setPrix(Double.parseDouble(_TextTarifCantine.getText()));
          this.populateListTarifsCantine(); //refresh combobox
          comboLeftTarifsCantine.setSelectedItem(_TextTarifCantineNom.getText());
        }
      }
      else {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        return;
      }
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    else if (Evt.getSource().equals(comboLeftTarifsCantine)) { //action of the tarifs comboBox
      setCursor(new Cursor(Cursor.WAIT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      disable();
      printTitle = comboLeftTarifsCantine.getSelectedItem() + "";
      this.setTitle("Gestion d'\u00E9cole - " + printTitle);
      //System.out.println("got tarif Cantine");
      String cantine =
          "<table border=0 align=center  width=100%><tr><td colspan=2><center>"
          + "<h1>Tarif Cantine</h1></center></td></tr><tr><td align=right width=50%><b>Tarif :</b></td><td width=50%>"
          + comboLeftTarifsCantine.getSelectedItem() + "</td></tr>";
      TarifsCantine tarifCantineFromCombo = getTarifsCantineFromCombo();
      //  int ID = classeFromCombo.getID( (String) comboLeftClasses.getSelectedItem()); //set the class selected in combo
      // textAreaRight.setText("Tarif : " + comboLeftTarifsCantine.getSelectedItem() +
      //                        "\n");
      if (tarifCantineFromCombo.getPrix() + "" != null) {
        cantine +=
            "<tr><td align=right width=50%><b>Prix :</b></td><td width=50%>" +
            tarifCantineFromCombo.getPrix() + " Euros</td></tr>";
        //                    " Euros\n"
        //  textAreaRight.append("Prix : " + tarifCantineFromCombo.getPrix()+
        //                    " Euros\n");
      }
      else {
        cantine +=
            "<tr><td align=right width=50%><b>Prix :</b></td><td>&nbsp;</td></tr>";
        //    textAreaRight.append("Prix :");
      }
      textAreaRight.setText(cantine + "</table>");
      textAreaRight.setVisible(true);
      table.setVisible(false);
      textPane.setViewportView(textAreaRight);
      tableprint = false;
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    else if (Evt.getSource().equals(suprTarifCantine) ||
             Evt.getActionCommand().equalsIgnoreCase("Suprimer tarif cantine")) {
      setCursor(new Cursor(Cursor.WAIT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      disable();
      TarifsCantine tarifCantineToDelete = getTarifsCantineFromCombo();
      String nomTarifCantine = (String) comboLeftTarifsCantine.getSelectedItem();
      int rslt = JOptionPane.showConfirmDialog(EcoleApp.this,
          "Etes-vous sur de vouloir suprimer " + nomTarifCantine + " ?",
          "Supression de tarif cantine",
          JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE); //Confirmation dialog box
      if (rslt == JOptionPane.YES_OPTION) { //if yes is pressed
        textAreaRight.setVisible(true);
        table.setVisible(false);
        textPane.setViewportView(textAreaRight);
        tableprint = false;
        tarifCantineToDelete.eraseTarif();
        this.populateListTarifsCantine(); //repopulates the tarif cantine combobox to refresh it
        textAreaRight.setText("Le tarif cantine " + nomTarifCantine +
                              " \u00E0 \u00E9t\u00E9 suprim\u00E9.");
        printTitle = "Supression du " + nomTarifCantine + " cantine";
        this.setTitle("Gestion d'\u00E9cole - " + printTitle);
      }
      else {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        return;
      }
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    else if (Evt.getSource().equals(modifyTarifCantine) ||
             Evt.getActionCommand().equalsIgnoreCase("Modifier tarif cantine")) { ////////////////////////////!!!!!!!!
      setCursor(new Cursor(Cursor.WAIT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      disable();
      insertTarifCantine();
      String nomTarif = (String) comboLeftTarifsCantine.getSelectedItem();
      TarifsCantine tarifCantineToModify = getTarifsCantineFromCombo();
      // System.out.println("atelier from combo = " +
      //                   (String) comboLeftAtelier.getSelectedItem());
      //    int ID = classe.getID( (String) comboLeftClasses.getSelectedItem()); //set the class selected in combo
      //  System.out.println("instit" + classe.getInstituteur());
      _TextTarifCantineNom.setText(nomTarif);
      _TextTarifCantine.setText(tarifCantineToModify.getPrix() + "");
      JOptionPane panel = new JOptionPane(insertTarifsCantinePanel,
                                          JOptionPane.QUESTION_MESSAGE,
                                          JOptionPane.OK_CANCEL_OPTION);
      JDialog dialog = panel.createDialog(EcoleApp.this,
                                          "Modifier tarif cantine");
      dialog.show();
      Integer value = (Integer) panel.getValue();
      if (value.intValue() == JOptionPane.OK_OPTION) { //if ok button is pressed
        textAreaRight.setVisible(true);
        table.setVisible(false);
        textPane.setViewportView(textAreaRight);
        tableprint = false;
        if (_TextTarifCantineNom.getText().length() == 0 ||
            _TextTarifCantine.getText().length() == 0) {
          JOptionPane.showMessageDialog(this,
                                        "Certains champs sont vides.",
                                        "Information",
                                        JOptionPane.INFORMATION_MESSAGE);
        }
        else {

          nomTarif = _TextTarifCantineNom.getText();
          tarifCantineToModify.updateNom(nomTarif); //change the tarif nom
          tarifCantineToModify.setPrix(Double.parseDouble(_TextTarifCantine.
              getText())); //change the tarif prix
          this.populateListTarifsCantine(); //refresh combobox
          comboLeftTarifsCantine.setSelectedItem(nomTarif);
        }
      }
      else {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        return;
      }
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    else if (Evt.getActionCommand().equalsIgnoreCase(
        "Ajouter/Suprimer un \u00E9l\u00E8ve") ||
             Evt.getSource().equals(cantineEleve)) {
      setCursor(new Cursor(Cursor.WAIT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      disable();
      String date;
      String day;
      String month;
      String year;
      StringTokenizer st;
      eleveCantine();
      Student cantineOfeleve = getStudentFromCombo();
      JOptionPane pane = new JOptionPane(eleveCantinePanel,
                                         JOptionPane.QUESTION_MESSAGE,
                                         JOptionPane.OK_CANCEL_OPTION);
      JDialog dialog = pane.createDialog(EcoleApp.this,
                                         "Cantine El\u00E8ve " +
                                         cantineOfeleve.getFName() + " " +
                                         cantineOfeleve.getLName());
                                         
      Integer value = null;
      while(true)
      {                                   
        dialog.show();
        value = (Integer) pane.getValue();
        if (value.intValue() == JOptionPane.OK_OPTION) 
        {
            // Verification si les saisies sont OK
            String err = Cantine.isInputValid(_TextCantineValidite.getText(), _TextCantineJours.getText());
            if (null == err)
            {
                break;
            }
            else
            {
                Dialog.ErreurDeSaisie(this, err);
            }
        }
        else
        {
            break; // Annuler
        }
      }
      
      
      if (value.intValue() == JOptionPane.OK_OPTION) {
        textAreaRight.setText("L'action cantine sur l'\u00e9l\u00E8ve " +
                              cantineOfeleve.getFName() + " " +
                              cantineOfeleve.getLName() +
                              " a \u00E9t\u00E9 enregistr\u00E9e");
        textAreaRight.setVisible(true);
        table.setVisible(false);
        textPane.setViewportView(textAreaRight);
        tableprint = false;

        Cantine cantine = new Cantine(cantineOfeleve.getID(), _Con);
        // TarifsCantine tarifcantine = new TarifsCantine((String) _TarifsCantine.getSelectedItem());
        //RefAtelier refatelier = new RefAtelier();

        if (boolUpdateCantine == true) { //if eleve already has cantine
          cantine.eraseStudentCantine(); //erase all in db to refresh
        }
        if (chkCantine.isSelected()) { //refresh ateliers info for current eleve
          cantine.setEleveId();
          cantine.setPriceName( (String) _TarifsCantine.getSelectedItem());
          cantine.setPrice( (String) _TarifsCantine.getSelectedItem());
          date = _TextCantineValidite.getText();
          st = new StringTokenizer(date, "/");
          day = st.nextToken();
          month = st.nextToken();
          year = st.nextToken();
          cantine.setDateValidite(year + "-" + month + "-" + day);
          cantine.setNbrJours(Integer.parseInt(_TextCantineJours.getText()));
        }
      }
      else {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        return;
      }
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    else if (Evt.getActionCommand().equalsIgnoreCase(
        "Liste d'\u00E9l\u00E8ves par classe/mois")) {
      setCursor(new Cursor(Cursor.WAIT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      disable();
      String strMonth = "";
      int days = 0;
      String month = (String) JOptionPane.showInputDialog(this,
          "Entrez le num\u00E9ro du mois (Ex:1 pour Janvier):");
      if (month.length() != 0) {
        try {
          int imonth = Integer.parseInt(month);
          if (imonth <= 12 || imonth >= 1) {
            Classe classeForListe = getClasseFromCombo();
            Student listeStudent = new Student(_Con);
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.get(GregorianCalendar.YEAR);
            calendar.set(GregorianCalendar.MONTH, imonth - 1);
            calendar.set(GregorianCalendar.DAY_OF_MONTH, 1);
            switch (imonth) {
              case 1:
                strMonth = "Janvier";
                break;
              case 2:
                strMonth = "F\u00E9vrier";
                break;
              case 3:
                strMonth = "Mars";
                break;
              case 4:
                strMonth = "Avril";
                break;
              case 5:
                strMonth = "Mai";
                break;
              case 6:
                strMonth = "Juin";
                break;
              case 7:
                strMonth = "Juillet";
                break;
              case 8:
                strMonth = "Aout";
                break;
              case 9:
                strMonth = "Septembre";
                break;
              case 10:
                strMonth = "Octobre";
                break;
              case 11:
                strMonth = "Novembre";
                break;
              case 12:
                strMonth = "D\u00E9cembre";
                break;
            }
            do {
              days += 1;
              //int day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
              //System.out.println("le jour "+days);
              calendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
            }
            while (calendar.get(GregorianCalendar.MONTH) == imonth - 1);
            String currentClasse = (String) comboLeftClasses.getSelectedItem();
            table = new JTable(new TableModelListCantine(_Con, currentClasse,
                strMonth, days)); //NEW
            //sorter.setTableHeader(table.getTableHeader()); //ADDED THIS
            // CustomRenderer mtr = new CustomRenderer();
            // table.setDefaultRenderer(Object.class, mtr);
            //  table.setDefaultRenderer(Integer.class, mtr);
            //  table.getModel().addTableModelListener(this);
            textAreaRight.setVisible(false);
            tableprint = true;
            status.setText("Liste cantine : " + currentClasse +
                           " pour le mois de : " + strMonth);
            printTitle = currentClasse + "\n" + strMonth;
            this.setTitle("Gestion d'\u00E9cole - Liste cantine : " +
                          currentClasse + " pour le mois de : " + strMonth);
            textPane.setViewportView(table);

          }
        }
        catch (NumberFormatException nfe) {
          setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
          textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
          // getToolkit().beep();
          JOptionPane.showMessageDialog(new JFrame(),
                                        "Must be a number",
                                        "Removed Info",
                                        JOptionPane.INFORMATION_MESSAGE);
          month = (String) JOptionPane.showInputDialog(this,
              "Entrez le numero du mois (Ex:1 pour Janvier):");
        }
      }
      else {
        JOptionPane.showMessageDialog(this,
                                      "Must be a number",
                                      "Removed Info",
                                      JOptionPane.INFORMATION_MESSAGE);
        month = (String) JOptionPane.showInputDialog(new JFrame(),
            "Entrez le numero du mois (Ex:1 pour Janvier):");
      }
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    else if (Evt.getActionCommand().equals("Bilan")) {
      setCursor(new Cursor(Cursor.WAIT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      modelComptaCantine = new TableModelComptaCantine(_Con);
      TableSorter sorter = new TableSorter(modelComptaCantine); //ADDED THIS
      //  modelComptaCantine = new TableModelComptaCantine(_Con);
      table = new JTable(sorter); //NEW
      sorter.setTableHeader(table.getTableHeader()); //ADDED THIS
      // CustomRenderer mtr = new CustomRenderer();
      // table.setDefaultRenderer(Object.class, mtr);
      //  table.setDefaultRenderer(Integer.class, mtr);
      //    table.getModel().addTableModelListener(table);
      textAreaRight.setVisible(false);
      tableprint = true;
      textPane.setViewportView(table);
      // add(pane);
      this.populateListeSauvegardesCantine();
      comboTable.setEnabled(true);
      sauver.setEnabled(true);
      effacer.setEnabled(true);
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    else if (Evt.getSource().equals(printButton) ||
             Evt.getActionCommand().equalsIgnoreCase("Imprimer")) {
      print(tableprint);
    }
    else if (Evt.getSource().equals(printpreviewButton) ||
             Evt.getActionCommand().equalsIgnoreCase(
        "Aper\u00E7u avant impression")) {
      printPreview(tableprint);
    }
    else if (Evt.getActionCommand().equalsIgnoreCase("Fermer")) {
      System.exit(0);
    }
    else if (Evt.getActionCommand().equalsIgnoreCase("Sauver")) {
      boolean boolSave = true;
      String month;
      try {
          month = Dialog.EntrezLeNomDeLaSauvegarde(this); // Nom de la sauvegarde
          if (null != month) 
          {
              for (int i = 0; i < comboTable.getItemCount(); i++) {
                if (month.equalsIgnoreCase( (String) comboTable.getItemAt(i))) {
                  boolSave = false;
                  Object[] options = {
                      "Oui", "Non"};
                  int choice = JOptionPane.showOptionDialog(this,
                      "Etes-vous sur de vouloir remplacer la sauvergarde " + month +
                      " existante?", "Attention",
                      JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                      options, options[0]);
                  if (choice == 0) {
                    histocantine.eraseSauvegarde(month);
                    modelComptaCantine.saveDB(month);
                    this.populateListeSauvegardesCantine();
                  }
                  else {
                    return;
                  }
                }
              }
              if (boolSave == true) {
                modelComptaCantine.saveDB(month);
                this.populateListeSauvegardesCantine();
              }
          }
      }
      catch (Exception ex1) {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        return;
      }
    }
    else if (Evt.getSource().equals(comboTable)) {
      if (comboTable.getSelectedIndex() > 0) {
        modelComptaCantine.loadDatas( (String) comboTable.getSelectedItem());
      }
    }
    else if (Evt.getActionCommand().equalsIgnoreCase("Effacer")) {
      Object[] options = {
                  "Oui", "Non"};
              int choice = JOptionPane.showOptionDialog(this,
                  "Etes-vous sur de vouloir effacer la sauvergarde " + (String) comboTable.getSelectedItem() +
                  " ?", "Attention",
                  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                  options, options[0]);
              if (choice == 0) {
                modelComptaCantine.EraseData( (String) comboTable.getSelectedItem());
                this.populateListeSauvegardesCantine();
              }
              else {
                return;
              }
    }
  }

  private void populateListeSauvegardesCantine() {
    comboTable.setVisible(false);
    try {
      histocantine = new HistoCantine(_Con);
      comboTable.removeAllItems();
      comboTable.addItem("Selectionnez...");
      for (Enumeration en = histocantine.getcomboSauvegardes().elements();
           en.hasMoreElements(); ) {
        String s = (String) en.nextElement(); // Casts
        comboTable.addItem(s);
        //System.out.println(s);
      }
      comboTable.setVisible(true);
    }
    catch (Exception e) {
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      textAreaRight.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      System.out.println(e);
    }

  }

  /**
   * Get the student selected in the comboBox
   * @return A Student Object whose id, first name and last name are set to the
   * eleve selected
   */
  private Student getStudentFromCombo() {
    //System.out.println("HELLO");
    String delim = ",";
    String lName = null;
    String fName = null;
    String s = (String) comboLeftEleves.getSelectedItem();
    StringTokenizer st = new StringTokenizer(s, delim);
    while (st.hasMoreTokens()) {
      if (lName == null) {
        lName = st.nextToken();
      }
      else {
        fName = st.nextToken();
      }
    }
    //System.out.println("Nom : "+lName+" Prenom : "+fName);
    Student eleveCombo = new Student(fName, lName, _Con);
    //System.out.println("fin combo");
    return eleveCombo;
  }

  /**
   * Get the classe selected in the comboBox
       * @return A Classe Object whose id, classe_nom and instituteur are set to the
   * classe selected
   */
  private Classe getClasseFromCombo() {
    // String instituteur = null;
    String s = (String) comboLeftClasses.getSelectedItem();
    Classe classeFromCombo = new Classe(s, _Con);
    //classeFromCombo.getID();
    return classeFromCombo;
  }

  private RefAtelier getRefAtelierFromCombo() {
    // String instituteur = null;
    String s = (String) comboLeftAtelier.getSelectedItem();
    RefAtelier AtelierFromCombo = new RefAtelier(s, _Con);
    //classeFromCombo.getID();
    return AtelierFromCombo;
  }

  private TarifsAteliers getTarifsAteliersFromCombo() {
    // String instituteur = null;
    String s = (String) comboLeftTarifs.getSelectedItem();
    TarifsAteliers TarifsAteliersFromCombo = new TarifsAteliers(s, _Con);
    //classeFromCombo.getID();
    return TarifsAteliersFromCombo;
  }

  private TarifsCantine getTarifsCantineFromCombo() {
    // String instituteur = null;
    String s = (String) comboLeftTarifsCantine.getSelectedItem();
    TarifsCantine tarifsCantineFromCombo = new TarifsCantine(s, _Con);
    //System.out.println("heyhey :"+tarifsCantineFromCombo.getPrix());
    //classeFromCombo.getID();
    return tarifsCantineFromCombo;
  }

  private void displayStudent() {
    tableprint = false;
    String display = "<table border=0 align=center><tr><td colspan=2><center><h1>Fiche Eleve</h1></center></td></tr>";
    Student eleveFromCombo = getStudentFromCombo();
    printTitle = "Fiche \u00E9l\u00E9ve " + eleveFromCombo.getLName() + " " +
        eleveFromCombo.getFName();
    this.setTitle("Gestion d'\u00E9cole - Fiche \u00E9l\u00E9ve " +
                  eleveFromCombo.getLName() + " " + eleveFromCombo.getFName());

    //System.out.println("f: " + fName + ", l: " + lName);
    //Student eleveCombo = new Student(fName, lName);
    // System.out.println("id" + eleveFromCombo.getID());

    //the following set the eleve info in the rightpanel's textarea
    display += "<tr><td><b>Nom :</b></td><td>" + eleveFromCombo.getLName() +
        "</td></tr><tr><td><b>Pr\u00E9nom :</b></td><td>" +
        eleveFromCombo.getFName() + "</td></tr>";
    // textAreaRight.setText("Nom : " + eleveFromCombo.getFName() +
    //                      "\n" +
    //                      "Prenom : " + eleveFromCombo.getLName() +
    //                      "\n");
    if (eleveFromCombo.getSex() != null) {
      //System.out.println(eleveFromCombo.getSex());
      display += "<tr><td><b>Sexe :</b></td><td>" + eleveFromCombo.getSex() +
          "</td></tr>";
      //  textAreaRight.append("Sexe : " + eleveFromCombo.getSex() +
      //                      "\n");
    }
    if (eleveFromCombo.getDob().equals("00/00/0000")) {
      display += "<tr><td><b>Date de naissance :</b></td><td>&nbsp;</td></tr>";
      // textAreaRight.append("Date de Naissance : \n");
    }
    else {
      display += "<tr><td><b>Date de naissance :</b></td><td>" +
          eleveFromCombo.getDob() + "</td></tr>";
      // System.out.println(eleveFromCombo.getDob());
      //  textAreaRight.append("Date de Naissance : " + eleveFromCombo.getDob() +
      //                       "\n");
    }

    if (eleveFromCombo.getStreet() != null) {
      display += "<tr><td><b>Adresse :</b></td><td>" + eleveFromCombo.getStreet() +
          "</td></tr>";
      //System.out.println(eleveFromCombo.getStreet());
      //    textAreaRight.append("Rue : " + eleveFromCombo.getStreet() +
      //                         "\n");
    }
    else {
      display += "<tr><td><b>Adresse :</b></td><td>&nbsp;</td></tr>";
    }
    if (eleveFromCombo.getZip() != null) {
      display += "<tr><td><b>Code Postal :</b></td><td>" +
          eleveFromCombo.getZip() + "</td></tr>";
      //System.out.println(eleveFromCombo.getZip());
      // textAreaRight.append("Code postal : " + eleveFromCombo.getZip() +
      //                      "\n");
    }
    else {
      display += "<tr><td><b>Code Postal :</b></td><td>&nbsp;</td></tr>";
    }
    if (eleveFromCombo.getCity() != null) {
      display += "<tr><td><b>Ville :</b></td><td>" + eleveFromCombo.getCity() +
          "</td></tr>";
      //System.out.println(eleveFromCombo.getCity());
      //   textAreaRight.append("Ville : " + eleveFromCombo.getCity() +
      //                        "\n");
    }
    else {
      display += "<tr><td><b>Ville :</b></td><td>&nbsp;</td></tr>";
    }
    if (eleveFromCombo.getTel1() != null) {
      display += "<tr><td><b>T\u00E9l\u00E9phone (1) :</b></td><td>" +
          eleveFromCombo.getTel1() + "</td></tr>";
      //System.out.println(eleveFromCombo.getTel1());
      //   textAreaRight.append("Telephone (1) : " + eleveFromCombo.getTel1() +
      //                       "\n");
    }
    else {
      display +=
          "<tr><td><b>T\u00E9l\u00E9phone (1) :</b></td><td>&nbsp;</td></tr>";
    }
    if (eleveFromCombo.getTel2() != null) {
      display += "<tr><td><b>T\u00E9l\u00E9phone (2) :</b></td><td>" +
          eleveFromCombo.getTel2() + "</td></tr>";
      //System.out.println(eleveFromCombo.getTel2());
      //   textAreaRight.append("Telephone (2) : " + eleveFromCombo.getTel2() +
      //                        "\n");
    }
    else {
      display +=
          "<tr><td><b>T\u00E9l\u00E9phone (2) :</b></td><td>&nbsp;</td></tr>";
    }
    if (eleveFromCombo.getTel3() != null) {
      display += "<tr><td><b>Telephone (3) :</b></td><td>" +
          eleveFromCombo.getTel3() + "</td></tr>";
      //System.out.println(eleveFromCombo.getTel3());
      //      textAreaRight.append("Telephone (3) : " + eleveFromCombo.getTel3() +
      //                          "\n");
    }
    else {
      display +=
          "<tr><td><b>T\u00E9l\u00E9phone (3) :</b></td><td>&nbsp;</td></tr>";
    }
    Classe classeOfEleveToDisplay = new Classe(_Con); // to retrieve the class nom
    if (classeOfEleveToDisplay.getClasseNom(eleveFromCombo.getClasseId()) != null) {
      display += "<tr><td><b>Classe :</b></td><td>"
          + classeOfEleveToDisplay.getClasseNom(eleveFromCombo.getClasseId()) +
          "</td></tr>";
      //       getClasseId())()+"</td></tr>";
      // textAreaRight.append("Classe : " +
      //                      classeOfEleveToDisplay.getClasseNom(eleveFromCombo.
      //       getClasseId()) +
      //                        "\n");
    }
    else {
      display += "<tr><td><b>Classe :</b></td><td>&nbsp;</td></tr>";
    }
    if (eleveFromCombo.getDateEntree().equals("00/00/0000")) {
      display +=
          "<tr><td><b>Date d'entr\u00E9e \u00E0 l'ecole :</b></td><td>&nbsp;</td></tr>";
      //  textAreaRight.append("Date d'Entree : \n");
    }
    else {
      display +=
          "<tr><td><b>Date d'entr\u00E9e \u00E0 l'\u00E9cole :</b></td><td>" +
          eleveFromCombo.getDateEntree() + "</td></tr>";
      // System.out.println(eleveFromCombo.getDob());
      //   textAreaRight.append("Date d'Entree : " + eleveFromCombo.getDateEntree() +
      //                         "\n");
    }
    Atelier atelier = new Atelier(eleveFromCombo.getID(), _Con);
    Vector ateliers = atelier.getAteliersByEleve();
    RefAtelier refatelier = new RefAtelier(_Con);
    if (ateliers != null) {
      display += "<tr><td valign=top><b>Atelier(s) :</b></td><td>";
      for (Enumeration enu = ateliers.elements();
           enu.hasMoreElements(); ) {
        int atelier_id = Integer.parseInt( (String) enu.nextElement()); // Casts
        String atelierNom = refatelier.getAtelierName(atelier_id);
        String atelierJour = refatelier.getAtelierJour(atelier_id);
        if (atelierJour != null) {
          if (refatelier.getAtelierType(atelier_id).equals("E") ||
              refatelier.getAtelierType(atelier_id).equals("e")) {
            display += atelierNom + "<br>";
          }
          else {
            display += atelierNom + " (" + atelierJour + ")<br>";
          }
        }
        else {
          display += atelierNom + "<br>";
        }
      }
      display += "</td></tr>";
    }
    display += "</table>";
    textAreaRight.setText(display);
    //end of the eleve's info display in the right panel's textarea
  }

  public void print(boolean tableprinting) {
    if (tableprinting == true) {
      printer = new AtDocumentPrinter();
      tablePrinter = new AtTablePrinter();
      genericPrinter = new AtGenericPrinter();
      addPageHeaderFooterListenerTable(tablePrinter);
      tablePrinter.setDrawCellBorder(true);
      // tablePrinter.print(table, PageNoPainter.LOWER_CENTER, false);
      tablePrinter.preview(table, this);
      //tablePrinter.print(table);
    }
    else {
      printer = new AtDocumentPrinter();
      genericPrinter = new AtGenericPrinter();
      addPageHeaderFooterListener(printer);
      printer.print(textAreaRight, PageNoPainter.LOWER_CENTER, false);

    }
  }

  public void printPreview(boolean tableprinting) {
    if (tableprinting == true) {
      printer = new AtDocumentPrinter();
      tablePrinter = new AtTablePrinter();
      genericPrinter = new AtGenericPrinter();
      addPageHeaderFooterListenerTable(tablePrinter);
      tablePrinter.setDrawCellBorder(true);
      //Set a custom size to the priview window.
      tablePrinter.setPreviewPaneSize(new Dimension(680,400));
       tablePrinter.preview(table, PageNoPainter.LOWER_CENTER, true,this);
      //tablePrinter.preview(table, this);
       //Get the preview frame handle.
      AtPreviewFrame previewFrame = tablePrinter.getPreviewWindow();


      //tablePrinter.print(table);
    }
    else {
      printer = new AtDocumentPrinter();
      genericPrinter = new AtGenericPrinter();
      printer.setPreviewPaneSize(new Dimension(680,400));
       //genericPrinter.preview(textAreaRight, PageNoPainter.LOWER_CENTER, true,this);
      //tablePrinter.preview(table, this);
       //Get the preview frame handle.
      //AtPreviewFrame previewFrame = tablePrinter.getPreviewWindow();

      addPageHeaderFooterListener(printer);
      printer.preview(textAreaRight, PageNoPainter.LOWER_CENTER, true,this);
     // printer.preview(textAreaRight, this);
    }
  }

  private void addPageHeaderFooterListenerTable(AtAbstractPrinter printerClass) {
    printerClass.setPageHeaderFooterListener(new PageHeaderFooterListener() {
      int pageCount = 0;
      public void setPageHeaderAndFooter(PageHeaderFooterRenderer r) {
        //System.out.println("setPageHeaderAndFooter()...totalPages=" + r.getTotalPageCount());
        AtHeaderFooterElement header
            = new AtHeaderFooterElement(null,
                                        new Font("Times New Roman", Font.BOLD,
                                                 20),
                                        Color.decode("#333366"),
                                        SystemColor.white,
                                        false,
                                        AtHeaderFooterElement.LEFT);
        AtHeaderFooterElement footer
            = new AtHeaderFooterElement("Page " + r.getPageNumber() + "/" +
                                        r.getTotalPageCount(),
                                        new Font("Arial", Font.PLAIN, 10),
                                        Color.decode("#000033"),
                                        SystemColor.white,
                                        false,
                                        AtHeaderFooterElement.CENTER);
        if (r.getPageNumber() == 1) {
          header.setStr(printTitle);
        }
        r.setHeader(header);
        r.setFooter(footer);
      }
    });

  }

  private void addPageHeaderFooterListener(AtAbstractPrinter printerClass) {
    printerClass.setPageHeaderFooterListener(new PageHeaderFooterListener() {
      int pageCount = 0;
      public void setPageHeaderAndFooter(PageHeaderFooterRenderer r) {
        //System.out.println("setPageHeaderAndFooter()...totalPages=" + r.getTotalPageCount());
        AtHeaderFooterElement header
            = new AtHeaderFooterElement(null,
                                        new Font("Times New Roman", Font.BOLD,
                                                 20),
                                        Color.decode("#333366"),
                                        SystemColor.white,
                                        false,
                                        AtHeaderFooterElement.LEFT);
        AtHeaderFooterElement footer
            = new AtHeaderFooterElement("Page " + r.getPageNumber() + "/" +
                                        r.getTotalPageCount(),
                                        new Font("Arial", Font.PLAIN, 10),
                                        Color.decode("#000033"),
                                        SystemColor.white,
                                        false,
                                        AtHeaderFooterElement.CENTER);
        if (r.getPageNumber() == 1) {
          header.setStr(printTitle);
        }
        r.setHeader(header);
        r.setFooter(footer);
      }
    });

  }

  /**
   * Main method, instantiates the EcoleApp class
   * @param args
   */
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(
          "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    }
    catch (Exception ex) {
      System.out.println("Exception: " + ex.getMessage());
    }

    EcoleApp ecoleApp1 = new EcoleApp();
  }

}