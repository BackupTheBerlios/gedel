/*
 * Created on 24 sept. 2004
 *
 * 
 */
package ecole.gui.fiches;

import java.sql.SQLException;

import javax.swing.JEditorPane;

import ecole.databean.CantineDatabean;
import ecole.databean.ClasseDatabean;
import ecole.databean.EleveDatabean;
import ecole.datametier.CantineMetier;
import ecole.datametier.ClassesMetier;
import ecole.datametier.ElevesMetier;
import ecole.utils.DateTools;
import ecole.utils.Formatter;
import ecole.utils.StringTools;

/**
 * Construction de la fiche eleve qui apparait dans le panneau de droite.
 * Ce composant est un JEditorPane HTML.
 * @author jemore
 */
public class EleveFiche
{
    /** eleve a afficher **/
    private EleveDatabean eleve;
    
    /** composant swing contenant les infos **/
    private JEditorPane edit;
    
    /** objet d'acces aux information de Classe **/
    private ClassesMetier classeMetier;
    
    /** objet d'acces aux information de Cantine **/
    private CantineMetier cantineMetier;

    
    /**
     * Construction de l'objet. Crée le composant swing et les classes métiers. 
     */
    public EleveFiche(EleveDatabean eleve)
    {
        this.eleve = eleve;
        edit = new JEditorPane();
        edit.setEditable(false);
        edit.setContentType("text/html");
        classeMetier = new ClassesMetier();
        cantineMetier = new CantineMetier();
    }
    
    /**
     * Retourne une chaine représentant du HTML altérnant 2 couleurs (en fonction de nbline)
     * @param nbline
     * @return du html a utiliser dans un TD
     * @author jerome forestier @ sqli
     * @date 29 sept. 2004
     */
    private String bgColor(int nbline)
    {
    	if (nbline % 2 == 0)
    		return "bgcolor=#F0F0FF";
    	else
    		return "bgcolor=#FFFFFF";
    }
    
    private String dateToString(java.util.Date date)
    {
    	if (date == null) return "???";
    	return  DateTools.SDF_D2M2Y4.format(date); 
    }
    /**
     * Retourne le composant swing sur lequel on affiche les informations
     * de l'elève
     * @return JEditorPane
     * @throws Exception si erreur de parametrage interne
     * @throws SQLException si pb d'acces aux données
     * @author jerome forestier @ sqli
     * @date 29 sept. 2004
     */
    public JEditorPane getEditorPanel() throws SQLException, Exception
    {
        StringBuffer html = new StringBuffer();
		ClasseDatabean classe = classeMetier.getClasseByClasseId(eleve.getClasseid());
		
		String[] data_libelle = {
			"Nom", "Pr&eacute;nom", 
			"Sexe", "Date de naissance", 
			"Adresse", "Code postal", 
			"Ville", "T&eacute;l&eacute;phone 1", 
			"T&eacute;l&eacute;phone 2", "T&eacute;l&eacute;phone 3",			
			"Classe", "Date d'entr&eacute;e &agrave; l'&eacute;cole", 
			"Atelier(s)"};
		Object[] data_value = {
			eleve.getNom(), eleve.getPrenom(), 
			ElevesMetier.getSexe(eleve), dateToString(eleve.getDob()), 
			eleve.getRue(), eleve.getCodepostal(), 
			eleve.getVille(), eleve.getTelephone1(),
			eleve.getTelephone2(),eleve.getTelephone3(),
			classe.getClasse_nom(), dateToString(eleve.getDateentree()), 
			"?"};

		if (data_libelle.length != data_value.length)
			throw new Exception("Les libellés et les données sont de taille différentes");			
        html.append("<center><b>Fiche élève<b></center></font>");
        html.append("<table border=0 width=100% cellpading=0 cellspacing=0>");
        
        for(int i = 0; i < data_libelle.length; i++)
        {
        	html.append("<tr><td width=100 "+bgColor(i)+">");
        	html.append(data_libelle[i]);
        	html.append("</td><td width=100% "+bgColor(i)+">");
        	if (null == data_value[i])
        		html.append("");
        	else
        		html.append(data_value[i].toString());
        	html.append("</td></tr>");
        }
        html.append("</table>");
        
        // est-ce que cet eleve est a la cantine ?
        CantineDatabean c = cantineMetier.getCantineForEleve(eleve.getId());
        if (null != c)
        {
        	html.append("<b>Inscription à la cantine</b><br>");
        	html.append("tarif " + c.getPrixname() + " (" + Formatter.doubleToStringLocale(c.getPrix()) + "€); ");
        	html.append(" validité ");
        	html.append(dateToString(c.getDatevalidite()));
        	html.append("; "+c.getNbrjours()+ " jour" + StringTools.pluriel("", "s", c.getNbrjours()));
        	html.append("<br>");
        }
        
        
        edit.setText(html.toString());
        return edit;
    }
    
    
}
