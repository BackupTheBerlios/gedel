package ecole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

 /**
 * contains the same fields as the table atelier.
 * Used to interact with the table "atelier".
 */

public class Atelier {
  Connection _Con = null;
  int _Atelier_Id;
  int _Eleve_Id;
  String _PriceName;
  double _Price;
  String _DateValidite;
  String _NbrJoursEtudes;


  /**
     * Connect the ecole database.
     */
    public Atelier(Connection con) {
      _Con = con;
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
   * Connect the ecole database.
   * Set the eleve_id.
   * @param eleve_id
   */
  public Atelier(int eleve_id ,Connection con) {
    _Con = con;
    _Eleve_Id = eleve_id;
    Statement st = null;
    //Cree Connection
    /*try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      con = DriverManager.getConnection("jdbc:mysql:///ecole", "root", "");

    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }*/
  }

  /**
   * Creates an instance in the table "atelier" for each selected Atelier,
   * inserting the AtelierID and the eleve_id passed in the constructor.
   * @param atelier_id
   */
  public void setEleveIdAndAtelier(int atelier_id) {
    _Atelier_Id = atelier_id;
    Statement st = null;
    String result = "";

    try {

      if (!_Con.isClosed())
        //System.out.println("setsex method");
      st = _Con.createStatement();
      int recordsUpdated;
      recordsUpdated = st.executeUpdate(
          "INSERT INTO atelier (eleve_id, atelier_id) values(" + _Eleve_Id +
          "," + atelier_id + ")");
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }

  /**
   * Delete all the datas for the eleve_id (passed in the constructor) in the "atelier" Table.
   * @param eleve_id
   */
  public void eraseStudentAteliers() {
    Statement st = null;
    try {

      if (!_Con.isClosed())
        //System.out.println("eraseAll method");
      st = _Con.createStatement();
      int recordsUpdated;
      recordsUpdated = st.executeUpdate("DELETE FROM atelier  WHERE eleve_id=" +
                                        _Eleve_Id);
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }

  /**
   * get all the atelier_id's for the eleve_id and put then in a Vector.
   * @return A Vector containing all the atelier_id's for the student_id
   */
  public Vector getAteliersByEleve() {
    Statement st = null;
    String result = "";
    Vector ateliers = new Vector();

    try {

      if (!_Con.isClosed())
        //System.out.println("Successfully connected to MySQL server...");
      st = _Con.createStatement();
      ResultSet rs = null;
      rs = st.executeQuery("SELECT atelier_id FROM atelier WHERE eleve_id=" +
                           _Eleve_Id);
      while (rs.next()) {
        ateliers.add(rs.getString(1));
      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    return ateliers;
  }

  public Vector getAteliersByEleve(int eleveID) {
   Statement st = null;
   String result = "";
   Vector ateliers = new Vector();

   try {

     if (!_Con.isClosed())
       //System.out.println("Successfully connected to MySQL server...");
     st = _Con.createStatement();
     ResultSet rs = null;
     rs = st.executeQuery("SELECT atelier_id FROM atelier WHERE eleve_id=" +
                          eleveID);
     while (rs.next()) {
       ateliers.add(rs.getString(1));
     }
   }
   catch (Exception e) {
     System.err.println("Exception: " + e.getMessage());
   }
   return ateliers;
 }


  /**
   * set the price (name) chosen and update the "atelier" Table putting this price
   * for every atelier of the student_id given by the constructor.
   * @param price_Name
   */
  public void setPriceName(String price_Name) {
    _PriceName = price_Name;
    Statement st = null;
    String result = "";

    try {

      if (!_Con.isClosed())
        //System.out.println("setPriceNAme method");
      st = _Con.createStatement();
      int recordsUpdated;
      recordsUpdated = st.executeUpdate("UPDATE atelier set prixname='" +
                                        _PriceName +
                                        "' WHERE eleve_id=" + _Eleve_Id);
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }

  }

  /**
   * get the price (name) for the current eleve_id.
   * @return The price name.
   */
  public String getPriceName() {
   Statement st = null;
   String result = "";
   try {

         if (!_Con.isClosed())
           //System.out.println("Successfully connected to MySQL server...");
         st = _Con.createStatement();
         ResultSet rs = null;
         rs = st.executeQuery("SELECT prixname FROM atelier WHERE eleve_id=" +
                              _Eleve_Id);
         while (rs.next()) {
           _PriceName = rs.getString(1);
         }
       }
       catch (Exception e) {
         System.err.println("Exception: " + e.getMessage());
       }
       return _PriceName;
 }

 public String getPriceName(int eleveID) {
   Statement st = null;
   String result = "";
   try {

         if (!_Con.isClosed())
           //System.out.println("Successfully connected to MySQL server...");
         st = _Con.createStatement();
         ResultSet rs = null;
         rs = st.executeQuery("SELECT DISTINCT prixname FROM atelier WHERE eleve_id=" +
                              eleveID);
         while (rs.next()) {
           _PriceName = rs.getString(1);
         }
       }
       catch (Exception e) {
         System.err.println("Exception: " + e.getMessage());
       }
       return _PriceName;
 }


 /**
  * Passed the name of the price, it sets the actual price for the current Eleve.
  * @param tarif_nom
  */
  public void setPrice(String tarif_nom) {
    Statement st = null;
    String result = "";
    int recordsUpdated;
    ResultSet rs = null;
    _Price = 0;
    try {

      if (!_Con.isClosed())
        //System.out.println("setPrice method");
      st = _Con.createStatement();
      rs = st.executeQuery(
          "SELECT tarif_prix FROM tarifsateliers WHERE tarif_nom='" + tarif_nom +
          "'");
      while (rs.next()) {
        _Price = rs.getDouble(1);
        //System.out.println(_Price);
      }

      recordsUpdated = st.executeUpdate("UPDATE atelier set prix='" + _Price +
                                        "' WHERE eleve_id=" + _Eleve_Id);
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }

  /**
   * set the date for every line for the eleve_id.
   * @param date
   */
  public void setDateValidite(String date) {
    Statement st = null;
    String result = "";

    try {

      if (!_Con.isClosed())
        //System.out.println("setDateValidite method");
      st = _Con.createStatement();
      int recordsUpdated = st.executeUpdate("UPDATE atelier set datevalidite='" +
                                            date +
                                            "' WHERE eleve_id=" + _Eleve_Id);
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }

  /**
   *get the date de validite for the current eleve.
   * @return the Date de Validite
   */
  public String getDateValidite() {
    Statement st = null;
    String result = "";
    try {

      if (!_Con.isClosed())
        //System.out.println("Successfully connected to MySQL server...");
      st = _Con.createStatement();
      ResultSet rs = null;
      rs = st.executeQuery("SELECT datevalidite FROM atelier WHERE eleve_id=" +
                           _Eleve_Id);
      while (rs.next()) {
        _DateValidite = rs.getString(1);
        rs = st.executeQuery("select date_format('"+_DateValidite+"', '%d/%m/%Y')");
        rs.next();
        _DateValidite = rs.getString(1);

      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    return _DateValidite;
  }

  public String getDateValidite(int eleveID) {
    Statement st = null;
    String result = "";
    try {

      if (!_Con.isClosed())
        //System.out.println("Successfully connected to MySQL server...");
      st = _Con.createStatement();
      ResultSet rs = null;
      rs = st.executeQuery("SELECT datevalidite FROM atelier WHERE eleve_id=" +
                           eleveID);
      while (rs.next()) {
        _DateValidite = rs.getString(1);
        rs = st.executeQuery("select date_format('"+_DateValidite+"', '%d/%m/%Y')");
        rs.next();
        _DateValidite = rs.getString(1);
      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    return _DateValidite;
  }


  /**
   * Set the nombre de jours for all the atelier_id's but the atelier of type "e"
   * @param jours
   */
  public void setNbrJours(int nbrJours) {
    Statement st = null;
    try {

      if (!_Con.isClosed())
        //System.out.println("setNbrJours method");
      st = _Con.createStatement();
      int recordsUpdated = st.executeUpdate("UPDATE atelier set nbrjours='" +
                                            nbrJours +
                                            "' eleve_id=" + _Eleve_Id);
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }

  /**
   * Set the nombre de jours for all the atelier_id of type "e".
   * @param nbrJours
   * @param etudes_id
   */
  public void setNbrJoursEtude(int nbrJours, int etudes_id) {
    Statement st = null;
    String result = "";

    try {

      if (!_Con.isClosed())
        //System.out.println("setNbrJours method");
      st = _Con.createStatement();
      int recordsUpdated = st.executeUpdate("UPDATE atelier set nbrjours='" +
                                            nbrJours +
                                            "' WHERE eleve_id=" + _Eleve_Id +
                                            " AND atelier_id=" + etudes_id);
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }

  /**
   * Get the nombre de jours for the atelier type "e".
   * Could be used to get any nombre de jours pour un atelier if the atelier_id
   * is provided.
   * @param eleve_id
   * @param atelier_id
   * @return The nombre de jours for the atelier_id
   */
  public String getNbrJoursEtudes(int eleve_id, int atelier_id) {
    Statement st = null;
    try {

      if (!_Con.isClosed())
        //System.out.println("Successfully connected to MySQL server...");
      st = _Con.createStatement();
      ResultSet rs = null;
      rs = st.executeQuery("SELECT nbrjours FROM atelier WHERE eleve_id=" +
                           eleve_id+" AND atelier_id="+atelier_id);
      while (rs.next()) {
        _NbrJoursEtudes = rs.getString(1);
      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    return _NbrJoursEtudes;
  }

  public Vector getElevesid(int atelierid){
    Vector elevesid = new Vector();
    Statement st = null;
    try {
      if (!_Con.isClosed())
      st = _Con.createStatement();
      ResultSet rs = null;
      rs = st.executeQuery("SELECT eleve_id FROM atelier WHERE atelier_id=" +
                           atelierid);
      while (rs.next()) {
        elevesid.add(rs.getString(1));
      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }


    return elevesid;
  }

  public Vector getDisctinctElevesid(){
    Vector elevesid = new Vector();
    Statement st = null;
    try {
      if (!_Con.isClosed())
      st = _Con.createStatement();
      ResultSet rs = null;
      rs = st.executeQuery("SELECT DISTINCT eleve_id FROM atelier");
      while (rs.next()) {
        elevesid.add(rs.getString(1));
      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    return elevesid;
  }

    /**
     * Controle la validité d'une saisie
     * @param date saisie
     * @param NbrJours saisie
     * @return null si OK, ou un message d'erreur sinon
     * @author JFORESTIER
     */
    public static String isInputValid(String date, String NbrJours, int tarifIndex)
    {  
        if (tarifIndex < 0)
            return "Vous devez choisir un tarif";    
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.parse(date);            
        }
        catch (ParseException e)
        {
            return "La date n'est pas au format jj/mm/aaaa";
        }
        try
        {
            if ("".equals(NbrJours))
                return "Vous devez indiquer un nombre de jours";
            Integer.parseInt(NbrJours);
        }
        catch(Exception e)
        {
            return "Le nombre de jours n'est pas valide";
        }
        
        return null;
    }


}