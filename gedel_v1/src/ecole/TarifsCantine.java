package ecole;

import java.sql.*;
import java.util.Vector;

public class TarifsCantine {

  String _Nom;
  double _Montant;
  int _Id;
  Connection _Con = null;
  /**
    * Creates the connection to the Database ecole.
    */

  public TarifsCantine(Connection con) {
    _Con = con;
    String result = "";
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
      * Creates the connection to the Database ecole.
      */
 public TarifsCantine(String nom, Connection con) {
   _Con = con;
      //Cree Connection
      /*try {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        con = DriverManager.getConnection("jdbc:mysql:///ecole", "root", "");
      }
      catch (Exception e) {
        System.err.println("Exception: " + e.getMessage());
      }*/
      _Nom = nom;
      _Id = getID();
 }
 /**
    * Set the Name fot the Tarif.
    * Also call the getID method and stores the id into _Id field
    * @param tarif_nom
    */
   public void setNom(String tarif_nom) {
     Statement st = null;
     String result = "";
     _Nom = tarif_nom;
     try {

       if (!_Con.isClosed())
         //System.out.println("settarifnom method");
       st = _Con.createStatement();
       int recordsUpdated;
       recordsUpdated = st.executeUpdate(
           "INSERT INTO tarifscantine (tarif_nom) values ('" + _Nom + "')");
     }
     catch (Exception e) {
       System.err.println("Exception: " + e.getMessage());
     }
     _Id = getID();
   }

   /**
   * update the Name fot the Tarif.
   * Also call the getID method and stores the id into _Id field
   * @param tarif_nom
   */
  public void updateNom(String tarif_nom) {
    Statement st = null;
    String result = "";
    _Nom = tarif_nom;
    try {

      if (!_Con.isClosed())
        //System.out.println("settarifnom method");
      st = _Con.createStatement();
      int recordsUpdated;
      recordsUpdated = st.executeUpdate(
          "UPDATE tarifscantine SET tarif_nom='" + _Nom + "' WHERE id="+_Id);
     // _Id = getID();
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }


   /**
   * Set the Name fot the Tarif.
   * Also call the getID method and stores the id into _Id field
   * @param tarif_nom
   */
  public void setPrix(double prix) {
    Statement st = null;
    String result = "";
    _Montant = prix;
    try {

      if (!_Con.isClosed())
        //System.out.println("setprix method");
      st = _Con.createStatement();
      int recordsUpdated;
      recordsUpdated = st.executeUpdate(
          "UPDATE tarifscantine SET tarif_prix ='" + _Montant + "' WHERE id="+_Id);
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }

    /**
      * Get the prix
      * @return the prix
      */
     public double getPrix() {
       Statement st = null;
       try {

         if (!_Con.isClosed())
           //System.out.println("getclassenom method");
         st = _Con.createStatement();
         ResultSet rs = null;
         rs = st.executeQuery("SELECT tarif_prix FROM tarifscantine WHERE id="+_Id);
          while (rs.next()) {
           _Montant = rs.getDouble(1);
         }
       }
       catch (Exception e) {
         System.err.println("Exception: " + e.getMessage());
       }
       //System.out.println("sdfsdf "+_Montant);
       return _Montant;
     }

     public double getPrix(String prixNom) {
            Statement st = null;

            try {

              if (!_Con.isClosed())
                //System.out.println("getclassenom method");
              st = _Con.createStatement();
              ResultSet rs = null;
              rs = st.executeQuery("SELECT tarif_prix FROM tarifscantine WHERE tarif_nom='"+prixNom+"'");
               while (rs.next()) {
                _Montant = rs.getDouble(1);
              }
            }
            catch (Exception e) {
              System.err.println("Exception: " + e.getMessage());
            }
            return _Montant;
          }


   /**
   * return the Id  of the tarif atelier.
   * Corresponds to the id field of the table tarifsateliers.
   * @return the id as an int.
   */
  public int getID() {
    Statement st = null;
    try {

      if (!_Con.isClosed())
        //System.out.println("getID method");
      st = _Con.createStatement();
      ResultSet rs = null;

      rs = st.executeQuery("SELECT id FROM tarifscantine WHERE tarif_nom='" + _Nom + "'");
      while (rs.next()) {
       _Id = rs.getInt(1);
        //System.out.println("id from tarifscantine" + _Id);
      }
    }
    catch (Exception e) {
      System.err.println("Exceptionee: " + e.getMessage());
    }

    return _Id;
  }


  /**
   * Get all the tarif_noms in the Table and put then in a Vector.
   * @return The Vector containing the Tarifs' name.
   */
  public Vector getComboTarifsCantine(){
    Statement st = null;
      String result = "";
      Vector tarifsAteliers = new Vector();

      try {

        if (!_Con.isClosed())
        st = _Con.createStatement();
        ResultSet rs = null;
        rs = st.executeQuery("SELECT tarif_nom FROM tarifscantine ORDER BY tarif_nom");
         while (rs.next()) {
          tarifsAteliers.add(rs.getString(1));
        }
      }
      catch (Exception e) {
        System.err.println("Exception: " + e.getMessage());
      }
      return tarifsAteliers;
  }

  /**
   * Delete all the datas for the classe_id (passed in the constructor) in the "classe" Table.
   * @param eleve_id
   */
  public void eraseTarif() {
    Statement st = null;
    try {

      if (!_Con.isClosed())
        //System.out.println("erasetarifmethod");
      st = _Con.createStatement();
      int recordsUpdated;
      recordsUpdated = st.executeUpdate("DELETE FROM tarifscantine WHERE id=" + _Id);
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }
  public String getPrixnom(int id) {
    String prix = null;
     Statement st = null;
     try {

       if (!_Con.isClosed())
         //System.out.println("getID method");
       st = _Con.createStatement();
       ResultSet rs = null;

       rs = st.executeQuery("SELECT tarif_nom FROM tarifscantine WHERE id="+id);
       while (rs.next()) {
        prix = rs.getString(1);
         //System.out.println("id from tarifscantine" + _Id);
       }
     }
     catch (Exception e) {
       System.err.println("Exceptionee: " + e.getMessage());
     }

     return prix;
   }


}
