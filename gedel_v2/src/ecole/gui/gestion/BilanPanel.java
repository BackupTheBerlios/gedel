/*
 * Created on 12 oct. 2004 for gedel_v2
 *
 * 
 */
package ecole.gui.gestion;

import java.awt.*;

import javax.swing.*;

import ecole.datametier.HistoCantineMetier;
import ecole.gui.utils.ComboBoxFiller;

/**
 * Bilan
 * @author jerome forestier @ sqli
 */
public class BilanPanel extends JPanel
{
    JPanel topPanel;
    JComboBox cbSauvegarde = new JComboBox();
    JButton butSauver = new JButton("Sauver", new ImageIcon(getClass().getClassLoader().getResource("icons/save.gif")));
    JButton butEffacer= new JButton("Effacer", new ImageIcon(getClass().getClassLoader().getResource("icons/clear.gif")));
    
    JPanel mainPanel;
    JPanel bottomPanel;
    
    HistoCantineMetier metier = new HistoCantineMetier();
    java.util.List listAvailableSauvegarde ;

    public BilanPanel()
    {
        initGUI();
    }

    private void initGUI()
    {
        reloadAvailableSauvegarde();
        topPanel = new JPanel();
        mainPanel = new JPanel();
        bottomPanel = new JPanel();
        BorderLayout thisLayout = new BorderLayout();
        this.setLayout(thisLayout);
        thisLayout.setHgap(0);
        thisLayout.setVgap(0);

        topPanel.add(cbSauvegarde);
        topPanel.add(butSauver);
        topPanel.add(butEffacer);
        topPanel.setVisible(true);
        //topPanel.setBackground(new java.awt.Color(255, 128, 128));
        this.add(topPanel, BorderLayout.NORTH);

        //mainPanel.setBackground(new java.awt.Color(128, 255, 128));
        this.add(mainPanel, BorderLayout.CENTER);

        //bottomPanel.setBackground(new java.awt.Color(128, 128, 255));
        this.add(bottomPanel, BorderLayout.SOUTH);
        
        cbSauvegarde.setToolTipText("Liste des sauvegardes disponibles");
        //cbSauvegarde.setPreferredSize(new Dimension(150,24));
    }

    /**
     * 
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    private void reloadAvailableSauvegarde()
    {
        try {
            cbSauvegarde.addItem("Selectionnez une sauvegarde ...");
            listAvailableSauvegarde = metier.getAvailableHisto();
            ComboBoxFiller.appendItems(
                cbSauvegarde,
                listAvailableSauvegarde,
                metier.getClass(),
                "getSauvevardeNom");            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
    }
}
