package ecole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class Cantine {

  Connection _Con = null;
  int _Eleve_Id;
  String _PriceName;
  double _Price;
  String _DateValidite;
  String _NbrJoursEtudes;

  public Cantine(Connection con) {
    _Con = con;
  }

  public Cantine(int eleve_id, Connection con) {
    _Con = con;
    _Eleve_Id = eleve_id;
  }

  public void setEleveId() {
    Statement st = null;
    String result = "";

    try {
      if (!_Con.isClosed())

        //System.out.println("setelevecantine method");
        st = _Con.createStatement();
      int recordsUpdated;
      recordsUpdated = st.executeUpdate(
          "INSERT INTO cantine (eleve_id) values(" + _Eleve_Id + ")");
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }

  /**
   * Delete all the datas for the eleve_id (the current student) in the "cantine" Table.
   */
  public void eraseStudentCantine() {
    Statement st = null;
    try {

      if (!_Con.isClosed())

        //System.out.println("eraseAll method");
        st = _Con.createStatement();
      int recordsUpdated;
      recordsUpdated = st.executeUpdate("DELETE FROM cantine  WHERE eleve_id=" +
                                        _Eleve_Id);
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }

  /**
       * set the price (name) chosen and update the "cantine" Table putting this price
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
      recordsUpdated = st.executeUpdate("UPDATE cantine set prixname='" +
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
      rs = st.executeQuery("SELECT prixname FROM cantine WHERE eleve_id=" +
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
      rs = st.executeQuery(
          "SELECT prixname FROM cantine WHERE eleve_id=" +
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
          "SELECT tarif_prix FROM tarifscantine WHERE tarif_nom='" + tarif_nom +
          "'");
      while (rs.next()) {
        _Price = rs.getDouble(1);
        //System.out.println(_Price);
      }

      recordsUpdated = st.executeUpdate("UPDATE cantine set prix='" + _Price +
                                        "' WHERE eleve_id=" + _Eleve_Id);
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }

  /**
   * set the date for the eleve_id.
   * @param date
   */
  public void setDateValidite(String date) {
    Statement st = null;
    String result = "";

    try {

      if (!_Con.isClosed())

        //System.out.println("setDateValidite method");
        st = _Con.createStatement();
      int recordsUpdated = st.executeUpdate("UPDATE cantine set datevalidite='" +
                                            date + "' WHERE eleve_id=" +
                                            _Eleve_Id);
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
      rs = st.executeQuery("SELECT datevalidite FROM cantine WHERE eleve_id=" +
                           _Eleve_Id);
      while (rs.next()) {
        _DateValidite = rs.getString(1);
        rs = st.executeQuery("select date_format('" + _DateValidite +
                             "', '%d/%m/%Y')");
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
     rs = st.executeQuery("SELECT datevalidite FROM cantine WHERE eleve_id=" +
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
   * Set the nombre de jours
   * @param jours
   */
  public void setNbrJours(int nbrJours) {
    Statement st = null;
    try {

      if (!_Con.isClosed())

        //System.out.println("setNbrJours method");
        st = _Con.createStatement();
      int recordsUpdated = st.executeUpdate("UPDATE cantine set nbrjours=" +
                                            nbrJours + " WHERE eleve_id=" +
                                            _Eleve_Id);
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }

  /**
   * Get the nombre de jours for the cantine
   * @return The nombre de jours for the cantine
   */
  public String getNbrJours() {
    Statement st = null;
    try {

      if (!_Con.isClosed())

        //System.out.println("getnbrejours");
        st = _Con.createStatement();
      ResultSet rs = null;
      rs = st.executeQuery("SELECT nbrjours FROM cantine WHERE eleve_id=" +
                           _Eleve_Id);
      while (rs.next()) {
        _NbrJoursEtudes = rs.getString(1);
      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    return _NbrJoursEtudes;
  }

  public String getNbrJours(int eleveID) {
    Statement st = null;
    try {

      if (!_Con.isClosed())

        //System.out.println("getnbrejours");
        st = _Con.createStatement();
      ResultSet rs = null;
      rs = st.executeQuery("SELECT nbrjours FROM cantine WHERE eleve_id=" +
                           eleveID);
      while (rs.next()) {
        _NbrJoursEtudes = rs.getString(1);
      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    return _NbrJoursEtudes;
  }


  public boolean getEleveStatus() {
    Statement st = null;
    int id = 0;
    try {
      if (!_Con.isClosed())

        //System.out.println("getStatus");
        st = _Con.createStatement();
      ResultSet rs = null;
      rs = st.executeQuery("SELECT eleve_id FROM cantine WHERE eleve_id=" +
                           _Eleve_Id);
      //System.out.println("hola "+rs.wasNull());
      while (rs.next()) {
        id = rs.getInt(1);
      }
      if ( (id + "").equalsIgnoreCase(_Eleve_Id + "")) {
        return true;
        // _NbrJoursEtudes = rs.getString(1);
      }
      else
        return false;
    }
    catch (Exception e) {
      //System.err.println("Exception: " + e.getMessage());
      return false;
    }
  }

  public Vector getDisctinctElevesid() {
    Vector elevesid = new Vector();
    Statement st = null;
    try {
      if (!_Con.isClosed())
        st = _Con.createStatement();
      ResultSet rs = null;
      rs = st.executeQuery("SELECT DISTINCT eleve_id FROM cantine");
      while (rs.next()) {
        elevesid.add(rs.getString(1));
      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    return elevesid;
  }

  public int getDaysByTarif(String tarif){
   // Vector nbrjours = new Vector();
    int nbrjours = 0;
    Statement st = null;
    try {
      if (!_Con.isClosed())
        st = _Con.createStatement();
      ResultSet rs = null;
      rs = st.executeQuery("SELECT nbrjours FROM cantine WHERE prixname='"+tarif+"'");
      while (rs.next()) {
        nbrjours += rs.getInt(1);
        //nbrjours.add(rs.getString(1));
      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    return nbrjours;
  }

  public Vector getDistinctTarifs(){
     Vector distinctTarifs = new Vector();
     Statement st = null;
     try {
       if (!_Con.isClosed())
         st = _Con.createStatement();
       ResultSet rs = null;
       rs = st.executeQuery("SELECT DISTINCT prixname FROM cantine");
       while (rs.next()) {
         distinctTarifs.add(rs.getString(1));

       }
     }
     catch (Exception e) {
       System.err.println("Exception: " + e.getMessage());
     }
     return distinctTarifs;
   }

    /**
     * Controle la validité d'une saisie
     * @param date saisie
     * @param NbrJours saisie
     * @return null si OK, ou un message d'erreur sinon
     * @author JFORESTIER
     */
    public static String isInputValid(String date, String NbrJours)
    {        
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