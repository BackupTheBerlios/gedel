package ecole.gui.predefinedframe;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.accessibility.Accessible;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;


public class NiceDialogAlert extends JComponent implements Accessible /*extends javax.swing.JDialog*/
{

    private Component parentComponent;
    /** GUI components **/
    private final JDialog dialog;
    private JScrollPane jScrollPane1;
    private JLabel jLabel1;
    private JButton bCancel;
    private JButton bNo;
    private JButton bYes;
    private JButton bOK;
    private JPanel jPanel2;
    private JPanel jPanel1;
    
    private int buttonPressed;
    private int messageType;
    private int defaultButton;
    
    private static final Dimension buttonDimension = new Dimension(96, 32);
    
    /** Affiche un bouton OK, ou on a cliqué sur OK **/
    public static final int OK_BUTTON = 1;
    
    /** Affiche un bouton YES, ou on a cliqué sur YES **/
    public static final int YES_BUTTON = 2;
    
    /** Affiche un bouton NO, ou on a cliqué sur NO **/
    public static final int NO_BUTTON = 4;
    
    /** Affiche un bouton CANCEL, ou on a cliqué sur CANCEL **/
    public static final int CANCEL_BUTTON = 8;

    /** Clique sur la X pour fermer la fenetre (équivalent à CANCEL)**/
    public static final int CLOSE_BUTTON = CANCEL_BUTTON;
    
    
    public NiceDialogAlert(Component parentComponent, String title)
    {
        this.parentComponent = parentComponent;
        
        Window window = getWindowForComponent(parentComponent);
        if (window instanceof Frame) {
            dialog = new JDialog((Frame)window, title, true);   
        } else {
            dialog = new JDialog((Dialog)window, title, true);
        }    
        initGUI();        
    }
    
    
    /**
         * Returns the specified component's toplevel <code>Frame</code> or
         * <code>Dialog</code>.
         * 
         * @param parentComponent the <code>Component</code> to check for a 
         *      <code>Frame</code> or <code>Dialog</code>
         * @return the <code>Frame</code> or <code>Dialog</code> that
         *      contains the component, or the default
         *          frame if the component is <code>null</code>,
         *      or does not have a valid 
         *          <code>Frame</code> or <code>Dialog</code> parent
         * @exception HeadlessException if
         *   <code>GraphicsEnvironment.isHeadless</code> returns
         *   <code>true</code>
         * @see java.awt.GraphicsEnvironment#isHeadless
         */
        static Window getWindowForComponent(Component parentComponent)
            throws HeadlessException {
            if (parentComponent == null)
                return JOptionPane.getRootFrame();
            if (parentComponent instanceof Frame || parentComponent instanceof Dialog)
                return (Window)parentComponent;
            return NiceDialogAlert.getWindowForComponent(parentComponent.getParent());
        }    

    /**
    * Initializes the GUI.
    */
    private void initGUI()
    {
        try
        {
            preInitGUI();

            jPanel1 = new JPanel();
            jScrollPane1 = new JScrollPane();
            jPanel2 = new JPanel();
            jLabel1 = new JLabel();
            bOK = new JButton();
            bYes = new JButton();
            bNo = new JButton();
            bCancel = new JButton();

            BorderLayout thisLayout = new BorderLayout();
            dialog.getContentPane().setLayout(thisLayout);            
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dialog.setName("Le nom de la fenetre ici");
            //this.setSize(new java.awt.Dimension(464, 262));

            BorderLayout jPanel1Layout = new BorderLayout();
            jPanel1.setLayout(jPanel1Layout);
            jPanel1.setVisible(true);
			jPanel1.setBorder(new EmptyBorder(new Insets(2,2,2,2)));
            //jPanel1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 0, 0)));
            jPanel1.setMaximumSize(new java.awt.Dimension(300, 300));
            dialog.getContentPane().add(jPanel1, BorderLayout.CENTER);

            jScrollPane1.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
            jPanel1.add(jScrollPane1, BorderLayout.CENTER);

            //jPanel2.setPreferredSize(new java.awt.Dimension(338, 40));
            dialog.getContentPane().add(jPanel2, BorderLayout.SOUTH);

            jLabel1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/about.gif")));
            jLabel1.setVisible(true);
            //jLabel1.setPreferredSize(new java.awt.Dimension(41, 31));
            jPanel2.add(jLabel1);

            bOK.setText("OK");
            bOK.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/ok.gif")));
            bOK.setSize(buttonDimension);
            jPanel2.add(bOK);
            bOK.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    bOKActionPerformed(evt);
                }
            });

            bYes.setText("Oui");
            bYes.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/ok.gif")));
            bYes.setSize(buttonDimension);
            jPanel2.add(bYes);
            bYes.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    bYesActionPerformed(evt);
                }
            });

            bNo.setText("Non");
            bNo.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/annul.gif")));
            bNo.setSize(buttonDimension);
            jPanel2.add(bNo);
            bNo.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    bNoActionPerformed(evt);
                }
            });

            bCancel.setText("Annuler");
            bCancel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/annul.gif")));
			bCancel.setSize(buttonDimension);
            jPanel2.add(bCancel);
            bCancel.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    bCancelActionPerformed(evt);
                }
            });

			bOK.setDisplayedMnemonicIndex(0);
			bYes.setDisplayedMnemonicIndex(0);
			bCancel.setDisplayedMnemonicIndex(0);
			bNo.setDisplayedMnemonicIndex(0);
            postInitGUI();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    /** Add your pre-init code in here 	*/
    private void preInitGUI()
    {
    }

    /** Add your post-init code in here 	*/
    private void postInitGUI()
    { 
        this.buttonPressed = CLOSE_BUTTON;
        
    }


    /**
     * Positionne le titre de la fenetre
     */
    public void setTitle(String title)
    {
        dialog.setName(title);
    }

    /**
     * Positionne le texte de la fenetre
     * @param text
     * @author jemore
     */
    public void setText(String text)
    {
    	/*
        TitledBorder border = (TitledBorder) this.jPanel1.getBorder();
        border.setTitle(text);
        */
    }

    /** Auto-generated event handler method */
    protected void bOKActionPerformed(ActionEvent evt)
    {
        this.buttonPressed = OK_BUTTON;
        dialog.hide();
    }

    /** Auto-generated event handler method */
    protected void bYesActionPerformed(ActionEvent evt)
    {
        this.buttonPressed = YES_BUTTON;
        dialog.hide();
    }

    /** Auto-generated event handler method */
    protected void bNoActionPerformed(ActionEvent evt)
    {
        this.buttonPressed = NO_BUTTON;
        dialog.hide();
    }

    /** Auto-generated event handler method */
    protected void bCancelActionPerformed(ActionEvent evt)
    {
        this.buttonPressed = CANCEL_BUTTON;
        dialog.hide();
    }

    /**
     * Retourne le bouton sur lequel on a cliqué
     * @return OK_BUTTON, YES_BUTTON, NO_BUTTON, CANCEL_BUTTON ou CLOSE_BUTTON
     * @author jemore
     */
    public int getResult()
    {
        return this.buttonPressed;
    }

    /**
     * Visibilité du bouton OK
     * @param visible
     * @author jemore
     */
    private void setButtonOkVisible(boolean visible)
    {
        this.bOK.setVisible(visible);
    }

    /**
     * Visibilité du bouton YES
     * @param visible
     * @author jemore
     */
    private void setButtonYesVisible(boolean visible)
    {
        this.bYes.setVisible(visible);
    }

    /**
     * Visibilité du bouton NO
     * @param visible
     * @author jemore
     */
    private void setButtonNoVisible(boolean visible)
    {
        this.bNo.setVisible(visible);
    }

    /**
     * Visibilité du bouton CANCEL
     * @param visible
     * @author jemore
     */
    private void setButtonCancelVisible(boolean visible)
    {
        this.bCancel.setVisible(visible);
    }

    /**
     * Affichage d'une boite de dialogue modal. Si <code>message</code> est de type String, 
     * le message est affiché. Si c'est de type <code>component</code>, les composants sont affichés. 
     * @param parentWindow null ou fenetre parente
     * @param title titre de la fenetre
     * @param message 
     * @param type JOptionDialog.WARNING_MESSAGE, ERROR_MESSAGE, QUESTION_MESSAGE, INFORMATION_MESSAGE
     * @param buttons combinaison binaire de OK_BUTTON, YES_BUTTON, NO_BUTTON, CANCEL_BUTTON
     * @return OK_BUTTON, YES_BUTTON, NO_BUTTON, CANCEL_BUTTON ou CLOSE_BUTTON
     * @author jemore
     */
    public static final int showMessageDialog(
    	Component parentComponent, 
    	String title, 
    	Object message, 
    	int type, 
    	int buttons,
    	int defaultButton)
    {
        try
        { 
            NiceDialogAlert inst = new NiceDialogAlert(parentComponent, title);
            inst.setMessageType(type, buttons, defaultButton);
            inst.setText(title);
            inst.setMessage(message);            
            inst.showModal();
            return inst.getResult();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {

        }
        return 0;
    }

    /**
     * Préselectionne le bouton par défaut
	 * @param defaultButton OK_BUTTON, YES_BUTTON, NO_BUTTON, CANCEL_BUTTON
	 * @author jemore
	 */
	private void setDefaultButton(int defaultButton)
	{
		if (OK_BUTTON == defaultButton)
		{
			this.bOK.setSelected(true);
		}
		else if (YES_BUTTON == defaultButton)
		{
			this.bYes.setSelected(true);
		}
		else if (NO_BUTTON == defaultButton)
		{
			this.bNo.setSelected(true);
		}
		else if (CANCEL_BUTTON == defaultButton)
		{
			this.bCancel.setSelected(true);
		}
	}


	/**
     * Creer les composant JLabel si message est de type String, ou ajoute les composants présents dans message
     * @param message
     * @author jemore
     */
    private void setMessage(Object message)
    {
        if (message instanceof String)
        {
            StringTokenizer stok = new StringTokenizer((String)message, "\n");
            JPanel pan = new JPanel();
            BoxLayout boxLayout = new BoxLayout(pan, 1 );
            pan.setLayout(boxLayout);                                   
             
            while(stok.hasMoreTokens())
            {
                String str = stok.nextToken();
                JLabel label = new JLabel(str);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setHorizontalTextPosition(SwingConstants.CENTER);
                pan.add(label);               
            }
            jScrollPane1.add(pan);
            jScrollPane1.setViewportView(pan);            
        }
        else if (message instanceof Component)
        {
            jScrollPane1.add((Component) message);
            jScrollPane1.setViewportView((Component) message);
        }
        else
        {
            throw new RuntimeException("Unable to add message of type " + message.getClass().getName() + " in a NiceDialogAlert");
        }

    }
    /**
     * Affiche la boite de manière modal
     * @author jemore
     */
    public void showModal()
    {
        dialog.setModal(true);
        dialog.toFront();
        dialog.pack();
        dialog.setLocationRelativeTo(parentComponent);
		setDefaultButton(defaultButton);
        dialog.show(); 
        dialog.dispose();       
    }

    /**
     * Retourne la ressource icone associé au type demandé
     * @param type JOptionDialog.WARNING_MESSAGE, ERROR_MESSAGE, QUESTION_MESSAGE, INFORMATION_MESSAGE
     * @return
     * @author jemore
     */
    private Icon getIconForMessageType(int type)
    {
        if (JOptionPane.WARNING_MESSAGE == type)
            return UIManager.getIcon("OptionPane.warningIcon");
        else if (JOptionPane.ERROR_MESSAGE == type)
            return UIManager.getIcon("OptionPane.errorIcon");
        else if (JOptionPane.QUESTION_MESSAGE == type)
            return UIManager.getIcon("OptionPane.questionIcon");
        else if (JOptionPane.INFORMATION_MESSAGE == type)
            return UIManager.getIcon("OptionPane.informationIcon");
        else
            return null;
    }

    /**
     * Positionne le type de boite de dialogue et les boutons a afficher
     * @param type JOptionDialog.WARNING_MESSAGE, ERROR_MESSAGE, QUESTION_MESSAGE, INFORMATION_MESSAGE
     * @param buttons combinaison binaire de OK_BUTTON, YES_BUTTON, NO_BUTTON, CANCEL_BUTTON
     * @param defaultButton bouton selectionné par défaut (OK_BUTTON, YES_BUTTON, NO_BUTTON ou CANCEL_BUTTON)
     * @author jemore
     */
    private void setMessageType(int type, int buttons, int defaultButton)
    {
        setButtonOkVisible((buttons & OK_BUTTON) == OK_BUTTON);
        setButtonYesVisible((buttons & YES_BUTTON) == YES_BUTTON);
        setButtonNoVisible((buttons & NO_BUTTON) == NO_BUTTON);
        setButtonCancelVisible((buttons & CANCEL_BUTTON) == CANCEL_BUTTON);
		
        Icon ic = getIconForMessageType(type);
        this.jLabel1.setIcon(ic);
        this.messageType = type;
        this.defaultButton = defaultButton;
    }

    /**
     * Methode de test
     * @param args
     * @author jemore
     */
    public static void main(String[] args)
    {
        try
        {
            showMessageDialog(null, "Le titre", "Appuyez sur OK", 
            JOptionPane.INFORMATION_MESSAGE, 
            NiceDialogAlert.OK_BUTTON | NiceDialogAlert.CANCEL_BUTTON, 
            NiceDialogAlert.CANCEL_BUTTON);
            System.exit(0);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
