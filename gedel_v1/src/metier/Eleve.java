/*
 * Created on 25 août 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package metier;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import data.EleveDatabean;

/**
 * @author Jerome
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Eleve extends GenericMetier {

    /**
     * @throws SQLException
     */
    public Eleve() throws SQLException {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Insertion d'un objet Eleve
     * @param classe /!\ met a jour l'ID 
     * @throws SQLException
     */
    public void insertEleve(EleveDatabean eleve) throws SQLException
    {
        PreparedStatement pst = prepareStatementAutoInc(
                "insert into eleve " +
                "(nom, prenom, sexe, dob, rue, codepostal, ville, telephone1, telephone2, telephone3, classeid, dateentree)" + 
                " values "  +
                " ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        pst.setString(1, eleve.getNom());
        pst.setString(2, eleve.getPrenom());
        pst.setString(3, new StringBuffer(eleve.getSexe()).toString());
        pst.setDate(4, new java.sql.Date(eleve.getDob().getTime()));
        pst.setString(5, eleve.getRue());
        pst.setString(6, eleve.getCodepostal());
        pst.setString(7, eleve.getVille());
        pst.setString(8, eleve.getTelephone1());
        pst.setString(9, eleve.getTelephone2());
        pst.setString(10, eleve.getTelephone3());
        pst.setInt(11, eleve.getClasseid());
        pst.setDate(12, new java.sql.Date(eleve.getDateentree().getTime()));
        
        pst.executeUpdate(); 
        eleve.setId(getAutoincrement(pst));
        pst.close();
    }

}
