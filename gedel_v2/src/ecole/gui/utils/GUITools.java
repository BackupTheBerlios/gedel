/*
 * Created on 6 sept. 2004
 *
 */
package ecole.gui.utils;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * @author jemore
 *
 */
public class GUITools
{
	public final static void centerWindow(JDialog window)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation(
			screenSize.width/2 - (window.getWidth()/2),
			screenSize.height/2 - (window.getHeight()/2)
		);		
	}
	
	public final static void centerWindow(JFrame window)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation(
			screenSize.width/2 - (window.getWidth()/2),
			screenSize.height/2 - (window.getHeight()/2)
		);		
	}
	
	public final static void setCursorWait(JFrame window)
	{
		window.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));		
	}
	
	public final static void setCursorNormal(JFrame window)
	{
		window.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

    /**
     * @param mainWindow
     * @author jemore
     */
    public static void setCursorWait(Window mainWindow)
    {
        mainWindow.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    }

    /**
     * @param mainWindow
     * @author jemore
     */
    public static void setCursorNormal(Window mainWindow)
    {
        mainWindow.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        
    }

}
