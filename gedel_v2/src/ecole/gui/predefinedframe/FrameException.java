package ecole.gui.predefinedframe;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DebugGraphics;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import ecole.gui.elements.JButtonOK;

/**
* This code was generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a
* for-profit company or business) then you should purchase
* a license - please visit www.cloudgarden.com for details.
*/
public final class FrameException extends javax.swing.JFrame {

	private JButtonOK jButtonOK1;
	private JScrollPane jScrollPane1;
	private JEditorPane editorPanelMessage;
	private JPanel pannelForButton;
	
	private static FrameException instance;
	
	public FrameException() {
		initGUI();
	}

	/**
	* Initializes the GUI.
	* Auto-generated code - any changes you make will disappear.
	*/
	public void initGUI(){
		try {
			preInitGUI();
	
			jScrollPane1 = new JScrollPane();
			editorPanelMessage = new JEditorPane();
			pannelForButton = new JPanel();
			jButtonOK1 = new JButtonOK();
	
			BorderLayout thisLayout = new BorderLayout();
			this.getContentPane().setLayout(thisLayout);
			thisLayout.setHgap(0);
			thisLayout.setVgap(0);
			this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Fenêtre d'erreur");
			this.setName("NAME ICI");
			this.setSize(new java.awt.Dimension(507,277));
			this.setEnabled(true);
	
			jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			jScrollPane1.setAutoscrolls(false);
			this.getContentPane().add(jScrollPane1, BorderLayout.CENTER);
	
			editorPanelMessage.setContentType("text/plain");
			editorPanelMessage.setText("Entrez votre texte ici");
			editorPanelMessage.setEditable(false);
			editorPanelMessage.setPreferredSize(new java.awt.Dimension(368,213));
			editorPanelMessage.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			editorPanelMessage.setDebugGraphicsOptions(DebugGraphics.BUFFERED_OPTION);
			jScrollPane1.add(editorPanelMessage);
			jScrollPane1.setViewportView(editorPanelMessage);
	
			BorderLayout pannelForButtonLayout = new BorderLayout();
			pannelForButton.setLayout(pannelForButtonLayout);
			pannelForButtonLayout.setHgap(0);
			pannelForButtonLayout.setVgap(0);
			pannelForButton.setPreferredSize(new java.awt.Dimension(368,28));
			this.getContentPane().add(pannelForButton, BorderLayout.SOUTH);
	
			jButtonOK1.setText("O K");
			jButtonOK1.setPreferredSize(new java.awt.Dimension(74,21));
			pannelForButton.add(jButtonOK1, BorderLayout.EAST);
			jButtonOK1.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jButtonOK1ActionPerformed(evt);
				}
			});
	
			postInitGUI();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** Add your pre-init code in here 	*/
	private void preInitGUI(){
	}

	/** Add your post-init code in here 	*/
	private void postInitGUI(){
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
	public static synchronized void showMessage(String message) {
		try
		{
			if (null == instance)
			{
				instance = new FrameException();
			}
			instance.setVisible(false);
			instance.editorPanelMessage.setText(message);
			instance.editorPanelMessage.setCaretPosition(0);
			instance.setVisible(true);			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static boolean isFrameVisible()
	{
		if (null == instance)
		{
			return false;
		}
		return instance.isVisible();
	}
	
	public static void setFrameVisible(boolean visible)
	{
		if (null != instance)
		{
			instance.setVisible(visible);
		}
	}

	public static void showException(Exception ex) 
	{
		ex.printStackTrace();
		StringBuffer sb = new StringBuffer("/!\\ Une erreur s'est produite : /!\\\n");
		sb.append(ex.toString()+"\n");		
		//sb.append("<font size=\"-1\">");
		StackTraceElement[] ste = ex.getStackTrace();
		for ( int i = 0; i < ste.length; i++)
		{
			sb.append("    "+ste[i].toString()+"\n");
		}
		//sb.append("</font>");
		showMessage(sb.toString());
	}


	/** Auto-generated event handler method */
	protected void jButtonOK1ActionPerformed(ActionEvent evt){
		this.dispose();
		evt.getClass(); // Consommation de l'évenement		
	}
}
