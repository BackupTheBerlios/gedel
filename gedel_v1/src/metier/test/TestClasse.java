/*
 * Created on 23 août 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package metier.test;

import java.util.Iterator;
import java.util.List;

import metier.Classe;
import data.ClasseDatabean;
import db.DavidSchoolConnection;

/**
 * @author Jerome
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestClasse {

    /**
     * 
     */
    public TestClasse() {
        super();
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        System.out.println("Test de l'objet metier Classe");
        
        try {
            // Creation d'un objet manipulation de la table
            Classe classe = new Classe();
            
            // Contenu de la table Classe
            List list = classe.getAllClasses();
            System.out.println("Contenu de la table Classe :");
            Iterator i = list.iterator();
            while (i.hasNext())
            {
                ClasseDatabean c = (ClasseDatabean) i.next();
                System.out.println(i + ":" + c);
            }
            
            // Insertion d'une classe
            ClasseDatabean c = new ClasseDatabean();
            c.setClasse_nom("CP1");
            c.setInstituteur("Jerome"+Math.random());
            classe.insertClasse(c);
            System.out.println("ID inséré " + c.getId());
            
            // Fermeture de la base
            DavidSchoolConnection.getInstance().closeConnection();
            ClasseDatabean c2 = classe.getClasseById(c.getId());
            System.out.println("J'ai retrouvé " + c2);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }
}
