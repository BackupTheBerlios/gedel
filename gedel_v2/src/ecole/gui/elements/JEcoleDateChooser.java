/*
 * Created on 24 sept. 2004
 *
 * 
 */
package ecole.gui.elements;

import java.awt.Color;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

/**
 * Composant de selection d'une date (J/m/a). Ce composant hérite
 * de JDateChooser, et corrige quelques problèmes avec ce composant.
 * Spécialisation : le format de la date est d MMMMM yyyy (ex : 14 juin 1975).
 * La combo des mois affiche 12 elements
 * 
 * @author jemore
 */
public class JEcoleDateChooser extends JDateChooser
{

    private static final String DATE_FORMAT = "d MMMMM yyyy";


    public JEcoleDateChooser(JCalendar calendar)
    {
        super(
            calendar, 
            DATE_FORMAT , 
            false, 
            new ImageIcon(JDateChooser.class.getResource("images/JDateChooserIcon.gif")) // On recupere l'icon orignale du JDateChooser                    
        );
            
            
            
        JComboBox cbMonth = (JComboBox)this.jcalendar.getMonthChooser().getComboBox();
        cbMonth.setMaximumRowCount(12);
        
        this.jcalendar.getDayChooser().setSelectedColor(new Color(0,255,0));
    }
    
    public JEcoleDateChooser()
    {
        this((JCalendar)null);
    }



    public JEcoleDateChooser(Date date)
    {
        this((JCalendar)null);
        JCalendar cal = new JCalendar();
        cal.setDate(date);
    }
    
    public void setSelectedColor(Color selectedColor)
    {
        this.jcalendar.getDayChooser().setSelectedColor(selectedColor);
    }

    /**
     * Creates a JFrame with a JEcoleDateChooser inside and can be used for testing.
     *
     * @param s The command line arguments
     */
    public static void main(String[] s) {
        JFrame frame = new JFrame("JEcoleDateChooser");
        JEcoleDateChooser dateChooser = new JEcoleDateChooser();
        frame.getContentPane().add(dateChooser);
        frame.pack();
        frame.setVisible(true);
    }
    
}
