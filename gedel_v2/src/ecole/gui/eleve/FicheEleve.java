/*
 * Created on 24 sept. 2004
 *
 * 
 */
package ecole.gui.eleve;

import javax.swing.JEditorPane;

import ecole.databean.EleveDatabean;

/**
 * Construction de la fiche eleve qui apparait dans le panneau de droite.
 * @author jemore
 */
public class FicheEleve
{
    private EleveDatabean eleve;
    private JEditorPane edit;

    
    /**
     * 
     */
    public FicheEleve(EleveDatabean eleve)
    {
        this.eleve = eleve;
        edit = new JEditorPane();
        edit.setEditable(false);
        edit.setContentType("text/html");
    }
    
    public JEditorPane getEditorPanel()
    {
        StringBuffer html = new StringBuffer();
        
        html.append("Fiche élève<br>");
        html.append("<table border=1 width=100%>");
        html.append("<tr>");
        html.append("<td width=1%>Nom</td><td><b>"+eleve.getNom()+"</b></td>");
        html.append("</tr><tr>");
        html.append("<td width=1%>Pr&eacute;nom</td><td><b>"+eleve.getPrenom()+"</b></td>");
        
        edit.setText(html.toString());
        return edit;
    }
    
    
}
