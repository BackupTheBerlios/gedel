/*
 * Created on 12 oct. 2004 for gedel_v2
 *
 * 
 */
package ecole.gui.gestion;

import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import ecole.databean.ClasseDatabean;
import ecole.databean.HistoCantineDatabean;
import ecole.datametier.HistoCantineMetier;
import ecole.gui.EcoleApp;
import ecole.gui.listes.BilanCantineListeEditable;
import ecole.gui.listes.EleveListe;
import ecole.gui.utils.ComboBoxFiller;
import ecole.utils.Formatter;

/**
 * Bilan
 * @author jerome forestier @ sqli
 */
public class BilanPanel extends JPanel
{
    private EcoleApp ecoleApp;
    JPanel topPanel;
    JComboBox cbSauvegarde = new JComboBox();
    JButton butSauver = new JButton("Sauver", new ImageIcon(getClass().getClassLoader().getResource("icons/save.gif")));
    JButton butEffacer = new JButton("Effacer", new ImageIcon(getClass().getClassLoader().getResource("icons/clear.gif")));

    JScrollPane mainPanel;
    JPanel bottomPanel;
    JTextPane edit = new JTextPane();

    JTable table;

    HistoCantineMetier metier = new HistoCantineMetier();
    java.util.List listAvailableSauvegarde;
    
    BilanCantineListeEditable liste;
    HistoCantineCompuer histoComputer = new HistoCantineCompuer();

    public BilanPanel(EcoleApp app)
    {
        super();
        this.ecoleApp = app;
        initGUI();
    }

    private void initGUI()
    {
        /*
        reloadAvailableSauvegarde();
        topPanel = new JPanel();
        mainPanel = new JScrollPane();
        mainPanel.setBorder(new EmptyBorder(new Insets(0,0,0,0)));
        bottomPanel = new JPanel();
        
        BorderLayout bottomPanelLayout = new BorderLayout();
        bottomPanel.setLayout(bottomPanelLayout);
        bottomPanelLayout.setHgap(0);
        bottomPanelLayout.setVgap(0);
        
        BorderLayout thisLayout = new BorderLayout();
        this.setLayout(thisLayout);
        thisLayout.setHgap(0);
        thisLayout.setVgap(0);
        
        topPanel.add(cbSauvegarde);
        topPanel.add(butSauver);
        topPanel.add(butEffacer);
        topPanel.setVisible(true);
        this.add(topPanel, BorderLayout.NORTH);
        
        this.add(bottomPanel, BorderLayout.SOUTH);
        
        this.add(mainPanel, BorderLayout.CENTER);
        
        
        cbSauvegarde.setToolTipText("Liste des sauvegardes disponibles");
        //cbSauvegarde.setPreferredSize(new Dimension(150,24));
        
        BilanCantineListeEditable liste = new BilanCantineListeEditable(ecoleApp);
        liste.process();
        mainPanel.setViewportView(liste.getTable());
        computeTotal();
        */
        reloadAvailableSauvegarde();
        topPanel = new JPanel();
        bottomPanel = new JPanel();
        edit = new JTextPane();
        mainPanel = new JScrollPane();

        BorderLayout thisLayout = new BorderLayout();
        this.setLayout(thisLayout);
        thisLayout.setHgap(0);
        thisLayout.setVgap(0);
        //this.setPreferredSize(new java.awt.Dimension(341, 236));

        this.add(topPanel, BorderLayout.NORTH);

        topPanel.add(cbSauvegarde);
        topPanel.add(butSauver);
        topPanel.add(butEffacer);

        BorderLayout bottomPanelLayout = new BorderLayout();
        bottomPanel.setLayout(bottomPanelLayout);
        bottomPanelLayout.setHgap(0);
        bottomPanelLayout.setVgap(0);
        //bottomPanel.setBackground(new java.awt.Color(128, 255, 128));
        //bottomPanel.setPreferredSize(new java.awt.Dimension(341, 50));
        this.add(bottomPanel, BorderLayout.SOUTH);

        edit.setContentType("text/html");
        edit.setEditable(false);
        //edit.setText("jTextPane1");
        //edit.setPreferredSize(new java.awt.Dimension(341, 50));
        bottomPanel.add(edit, BorderLayout.CENTER);

        mainPanel.setPreferredSize(new java.awt.Dimension(341, 201));
        mainPanel.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
        this.add(mainPanel, BorderLayout.CENTER);
        liste = new BilanCantineListeEditable(ecoleApp);
        liste.process();
        mainPanel.setViewportView(liste.getTable());
        computeTotal();
    }

    /**
     * 
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    private void reloadAvailableSauvegarde()
    {
        try
        {
            cbSauvegarde.addItem("Selectionnez une sauvegarde ...");
            listAvailableSauvegarde = metier.getAvailableHisto();
            ComboBoxFiller.appendItems(cbSauvegarde, listAvailableSauvegarde, metier.getClass(), "getSauvevardeNom");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Recalcul les totaux, et les affiches dans le JEditorPane bottomPanel
     * 
     * @author jerome forestier @ sqli
     * @date 12 oct. 2004
     */
    private void computeTotal()
    {
        Object o[] = liste.getData();
        histoComputer.histos = o;
        histoComputer.compute();

        StringBuffer html = new StringBuffer();
        html.append("<table border=1>");
        
        html.append("<tr>");
        html.append("<td>Total</td>");
        html.append("<td>" + Formatter.doubleToStringLocale(histoComputer.total)+"</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<td>Total cheque</td>");
        html.append("<td>" + Formatter.doubleToStringLocale(histoComputer.totalCheque)+"</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<td>Total espèce</td>");
        html.append("<td>" + Formatter.doubleToStringLocale(histoComputer.totalEspece)+"</td>");
        html.append("</tr>");

        html.append("</table><br><br><br");
        
        edit.setVisible(false);
        //edit.setEditable(false);
        //edit.setContentType("text/html");
        edit.setText(html.toString());
        edit.setVisible(true);

        //bottomPanel.removeAll();
        bottomPanel.add(edit, BorderLayout.CENTER);
        

    }
    
    private class HistoCantineCompuer
    {   
        public Object[] histos;
        double total;
        double totalCheque;
        double totalEspece;
        double totalPaye;
        
        private void init()
        {
            total = totalCheque = totalEspece = totalPaye = 0.0;
        }
        
        public void compute()
        {
            init();
            int i = 0;
            HistoCantineDatabean h;
            while (i < histos.length)
            {
                h = (HistoCantineDatabean)histos[i];
                double d = h.getCantine().getPrix() * h.getNbrjours();
                if (h.getType_payment() == HistoCantineDatabean.TYPE_PAYMENT_E)
                {
                    totalEspece += d;
                }
                else
                if (h.getType_payment() == HistoCantineDatabean.TYPE_PAYMENT_C)
                {
                    totalCheque += d;
                }
                total += d;
                i++;
            }
            totalPaye = totalEspece + totalCheque;
        }
        
    }
}
