/*
 * Created on 25 août 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package db.importation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import metier.Classe;
import metier.Eleve;
import utils.DateTools;
import data.ClasseDatabean;
import data.EleveDatabean;

/**
 * @author Jerome
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ImportListeEleves {

	/**
	 * Pose une question sur stdout, et attend la réponse.
	 * @param message à poser
	 * @return réponse
	 */
	private static String prompt(String message)
	{
		String reponse = "";
		try 
		{
			System.out.println(message);
			System.out.print(">");
			BufferedReader stdin = new BufferedReader (new InputStreamReader(System.in));
			reponse = stdin.readLine();
		} 
		catch (IOException ieox)
		{
			return null;
		}
		return reponse;
	}
	
    public static void main(String[] args) {
       try 
       {
		    System.out.println("Importation d'un fichier CSV d'élèves, en provenance de GIPI");
		    //String in_csv = prompt("Emplacement du fichier");
		    String in_csv = "D:\\workspace\\DavidSchool\\db\\data\\ecole119.csv";
		    BufferedReader r_csv =
				new BufferedReader(new InputStreamReader(new FileInputStream(in_csv)));
		    String line;
		    List liste_eleve = new ArrayList();
		    List liste_classe = new ArrayList();
		    Map map_classeBean = new HashMap();
		    Classe classe_metier = new Classe();
		    while ((line = r_csv.readLine()) != null)
		    {
		        if (null != line) 
		        {
		            line = line.trim();
		            EleveDatabean e = csvLineToEleveDatabean(line);
		            if (e != null) 
		            {
		                
		                String classe = e.getClasse_nom();
		                ClasseDatabean classedb = null;
		                if (!liste_classe.contains(classe))
		                {
		                    liste_classe.add(e.getClasse_nom());
		                    classedb = new ClasseDatabean();
		                    classedb.setClasse_nom(e.getClasse_nom());
		                    classedb.setInstituteur("");
		                    // Insertion de la classe en base
		                    classe_metier.insertClasse(classedb);
		                    map_classeBean.put(classe, classedb);
		                }
		                else
		                {
		                    classedb = (ClasseDatabean) map_classeBean.get(classe);
		                }
		                e.setClasseid(classedb.getId());
		                liste_eleve.add(e);
		            }
		        }
		    }
		    System.out.println(liste_eleve.size() + " elves");
		    System.out.println(liste_classe.size() + " classes");
		    
		    // Insertion des classes
		    
		    Iterator i = liste_eleve.iterator();
		    Eleve eleve_metier = new Eleve();
		    while (i.hasNext())
		    {
		        EleveDatabean eleve = (EleveDatabean)i.next();
		        eleve_metier.insertEleve(eleve);
		    }
       } 
       catch (Exception e)
       {
           e.printStackTrace();
       }
    }
    
    private static EleveDatabean csvLineToEleveDatabean(String line) throws ParseException
    {
        // Séparation des ;
        String[] elements = line.split(";");
        if (null == elements || 13 != elements.length )
            return null;
        String tmp = elements[0];
        String niveau = elements[1];
        String libelle = elements[2];
        String cycle = elements[3];
        String nom = elements[4];
        String prenom = elements[5];
        String adresse = elements[6];
        String datedenaissance = elements[10];
        String sexe = elements[11];
        String dateaffectation = elements[12];
        
        // Pour l'adresse, trouvé la ville et le CP
        String[] adrelem = adresse.split(" ");
        String codepostal = adrelem[adrelem.length - 2];
        String ville = adrelem[adrelem.length - 1];                
        adresse = adresse.substring(0, adresse.length() - codepostal.length() - ville.length() - 1);
        
        Date datenaissance_date = DateTools.SDF_D2M2Y4.parse(datedenaissance);
        Date dateaffectation_date = DateTools.SDF_D2M2Y4.parse(dateaffectation);

        EleveDatabean eleve = new EleveDatabean();
        eleve.setCodepostal(codepostal);
        eleve.setDateentree(dateaffectation_date);
        eleve.setDob(datenaissance_date);
        eleve.setNom(nom);
        eleve.setPrenom(prenom);
        eleve.setRue(adresse);
        eleve.setSexe(sexe.charAt(0));
        eleve.setVille(ville);
        eleve.setClasse_nom(libelle);
        return eleve;
    }
}
