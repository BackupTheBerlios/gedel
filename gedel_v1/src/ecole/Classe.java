package ecole;

import java.sql.*;
import java.util.Vector;

/**
 * contains the same fields as the table classe.
 * Used to interact with the table "classe".
 */

public class Classe {
  int _Id;
  String _Instituteur;
  String _Nom;
  Connection _Con = null;

  /**
   * Connect the ecole database.
   * Set the classe_nom and the instituteur_name.
   * @param nom
   * @param instituteur
   */
  public Classe(String nom, String instituteur, Connection con) {
    _Con = con;
    _Instituteur = instituteur;
    _Nom = nom;

    String result = "";
    Statement st = null;
    //Cree Connection
    try {
      /*Class.forName("com.mysql.jdbc.Driver").newInstance();
      con = DriverManager.getConnection("jdbc:mysql:///ecole", "root", "");*/
      if (!_Con.isClosed())
        //System.out.println("Successfully connected to MySQL server...");
      st = _Con.createStatement();
      int recordsUpdated;
      recordsUpdated =
          st.executeUpdate("INSERT INTO classe (classe_nom, instituteur) values ('" + _Nom +"','"+_Instituteur+"')");
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }

  /**
     * Connect the ecole database.
     * Set the classe_nom and the instituteur_name.
     * @param nom
     * @param instituteur
     */
    public Classe(String nom, Connection con) {
      _Con = con;
   //   String result = "";
      Statement st = null;
      //Cree Connection
      /*try {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        con = DriverManager.getConnection("jdbc:mysql:///ecole", "root", "");
        if (!_Con.isClosed())
          System.out.println("Successfully connected to MySQL server...");
      }
      catch (Exception e) {
        System.err.println("Exception: " + e.getMessage());
      }*/
      _Nom = nom;
      _Id = getID();
    }


  /**
   * Connects to the "ecole" database.
   * Used to retrieve info about the classe
   */
  public Classe(Connection con) {
    _Con = con;

    Statement st = null;
    //Cree Connection
   /* try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      con = DriverManager.getConnection("jdbc:mysql:///ecole", "root", "");

    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }*/
  }

  /**
   * get all the classe_Noms in the Table and put then in a Vector.
   * @return A Vector containing all the classe_Noms's in the Table
   */
  public Vector getcomboClasses() {
      Statement st = null;
      String result = "";
      Vector classeNom = new Vector();

      try {

        if (!_Con.isClosed())
          //System.out.println("getcomboclass method");
        st = _Con.createStatement();
        ResultSet rs = null;
        rs = st.executeQuery("SELECT classe_nom FROM classe");
         while (rs.next()) {
          classeNom.add(rs.getString(1));
        }
      }
      catch (Exception e) {
        System.err.println("Exception: " + e.getMessage());
      }
      return classeNom;
    }

    /**
     * Get the class name with the classe ID
     * @param classID
     * @return the Classe Name
     */
    public String getClasseNom(/*int classID*/) {
     /* _Id = classID;
      Statement st = null;

      try {

        if (!_Con.isClosed())
          System.out.println("getclassenom method");
        st = _Con.createStatement();
        ResultSet rs = null;
        rs = st.executeQuery("SELECT classe_nom FROM classe WHERE id="+_Id);
         while (rs.next()) {
          _Nom = rs.getString(1);
        }
      }
      catch (Exception e) {
        System.err.println("Exception: " + e.getMessage());
      }*/
      return _Nom;
    }

    /**
     * Get the class name with the classe ID
     * @param classID
     * @return the Classe Name
     */
    public String getClasseNom(int classID) {
      _Id = classID;
      Statement st = null;

      try {

        if (!_Con.isClosed())
          //System.out.println("getclassenom method");
        st = _Con.createStatement();
        ResultSet rs = null;
        rs = st.executeQuery("SELECT classe_nom FROM classe WHERE id="+_Id);
         while (rs.next()) {
          _Nom = rs.getString(1);
        }
      }
      catch (Exception e) {
        System.err.println("Exception: " + e.getMessage());
      }
      return _Nom;
    }


   /**
     * Get the instituteur name for the classe ID
     */
    public String getInstituteur(/*int classID*/) {
      Statement st = null;
      try {

        if (!_Con.isClosed())
          //System.out.println("getInstituteur method");
        st = _Con.createStatement();
        ResultSet rs = null;
        rs = st.executeQuery("SELECT instituteur FROM classe WHERE id="+_Id);
         while (rs.next()) {
          _Instituteur = rs.getString(1);
        }
      }
      catch (Exception e) {
        System.err.println("Exception: " + e.getMessage());
      }
      return _Instituteur;
    }

    /**
   * return the Id  of the classe.
   * Corresponds to the id field of the table classe.
   * @param classe_nom
   * @return the id as an int.
   */
  public int getID(/*String classe_nom*/) {
    //_Nom = classe_nom;
    Statement st = null;
   // int result = 0;
    try {

      if (!_Con.isClosed())
        //System.out.println("getID method");
      st = _Con.createStatement();
      ResultSet rs = null;

      rs = st.executeQuery("SELECT id FROM classe WHERE classe_nom='" + _Nom+"'");
      while (rs.next()) {
        _Id = rs.getInt(1);
      }
    }
    catch (Exception e) {
      System.err.println("Exceptionee: " + e.getMessage());
    }

    return _Id;
  }


  public void setClasseNom(String classeNom){
      _Nom = classeNom;
      Statement st = null;
      String result = "";

    try {

      if (!_Con.isClosed())
        //System.out.println("setclassnom method");
      st = _Con.createStatement();
      int recordsUpdated;
      recordsUpdated = st.executeUpdate("UPDATE classe set classe_nom='" + _Nom+"' WHERE id="+_Id);
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }

    }

    public void setInstituteur(String instituteur) {

      _Instituteur = instituteur;
      Statement st = null;
      String result = "";

    try {

      if (!_Con.isClosed())
        //System.out.println("setinstit method");
      st = _Con.createStatement();
      int recordsUpdated;
      recordsUpdated = st.executeUpdate("UPDATE classe set instituteur='" + _Instituteur +
                                        "' WHERE id=" + _Id);
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }

  /**
   * Delete all the datas for the classe_id (passed in the constructor) in the "classe" Table.
   * @param eleve_id
   */
  public void eraseClasse() {
    Statement st = null;
    try {

      if (!_Con.isClosed())
        //System.out.println("eraseAll method");
      st = _Con.createStatement();
      int recordsUpdated;
      recordsUpdated = st.executeUpdate("DELETE FROM classe WHERE id=" + _Id);
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }

}