/*
 * Created on 24 sept. 2004
 *
 * 
 */
package ecole.gui.eleve;

import javax.swing.JEditorPane;

import ecole.databean.CantineDatabean;
import ecole.databean.ClasseDatabean;
import ecole.databean.EleveDatabean;
import ecole.datametier.CantineMetier;
import ecole.datametier.ClassesMetier;
import ecole.datametier.ElevesMetier;
import ecole.utils.DateTools;
import ecole.utils.Formatter;

/**
 * Construction de la fiche eleve qui apparait dans le panneau de droite.
 * @author jemore
 */
public class FicheEleve
{
    private EleveDatabean eleve;
    private JEditorPane edit;
    
    private ClassesMetier classeMetier;
    private CantineMetier cantineMetier;

    
    /**
     * 
     */
    public FicheEleve(EleveDatabean eleve)
    {
        this.eleve = eleve;
        edit = new JEditorPane();
        edit.setEditable(false);
        edit.setContentType("text/html");
        classeMetier = new ClassesMetier();
        cantineMetier = new CantineMetier();
    }
    
    private String bgColor(int nbline)
    {
    	if (nbline % 2 == 0)
    		return "bgcolor=#F0F0FF";
    	else
    		return "bgcolor=#FFFFFF";
    }
    
    public JEditorPane getEditorPanel() throws Exception
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
			ElevesMetier.getSexe(eleve), DateTools.SDF_D2M2Y4.format(eleve.getDob()), 
			eleve.getRue(), eleve.getCodepostal(), 
			eleve.getVille(), eleve.getTelephone1(),
			eleve.getTelephone2(),eleve.getTelephone3(),
			classe.getClasse_nom(), DateTools.SDF_D2M2Y4.format(eleve.getDateentree()), 
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
        	html.append(DateTools.SDF_D2M2Y4.format(c.getDatevalidite()));
        	html.append("; "+c.getNbrjours()+ " jour(s)");
        	html.append("<br>");
        }
        
        
        edit.setText(html.toString());
        return edit;
    }
    
    
}
