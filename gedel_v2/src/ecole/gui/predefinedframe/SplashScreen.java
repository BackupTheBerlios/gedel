package ecole.gui.predefinedframe;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import ecole.gui.utils.GUITools;

/**
* This code was generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a
* for-profit company or business) then you should purchase
* a license - please visit www.cloudgarden.com for details.
*/
public class SplashScreen extends javax.swing.JDialog {

	private JLabel jLabel1;
	public SplashScreen() {
		initGUI();
	}

	private static SplashScreen inst;
	/**
	* Initializes the GUI.
	* Auto-generated code - any changes you make will disappear.
	*/
	public void initGUI(){
		try {
			preInitGUI();
	
			jLabel1 = new JLabel();
	
			this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setResizable(false);
			this.setUndecorated(true);
			this.setModal(false);
			this.setSize(new java.awt.Dimension(302,136));
	
			jLabel1.setText("Initialisation en cours ...");
			jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
			jLabel1.setPreferredSize(new java.awt.Dimension(244,96));
			this.getContentPane().add(jLabel1);
	
			postInitGUI();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** Add your pre-init code in here 	*/
	public void preInitGUI(){
		
	}

	/** Add your post-init code in here 	*/
	public void postInitGUI(){
		GUITools.centerWindow(this);		
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
	public static void showSplash(){
		try {
			inst = new SplashScreen();
			inst.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void endSplash(){
		
			try {			
				if (null != inst) {
					inst.setVisible(false);
					inst.dispose();
					inst = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
	}	
}
