package ecole.gui.elements;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class JButtonAnnul extends javax.swing.JButton
{

	public JButtonAnnul()
	{
		this.setText(" Annuler");
		this.setHorizontalTextPosition(SwingConstants.TRAILING);
		this.setIcon(
			new ImageIcon(
				getClass().getClassLoader().getResource("icons/annul.gif")));
		this.setIconTextGap(0);
		this.setPreferredSize(new java.awt.Dimension(100, 24));
		this.setAlignmentY(0.0f);
	}

}
