package ecole;

import java.sql.*;
import java.util.Vector;

/**
 * contains the same fields as the table refatelier.
 * Used to interact with the table "refatelier".
 */
public class RefAtelier {
  Connection _Con = null;
  int _Id;
  String _Name;
  String _AtelierType;
  String _Jour;

  /**
   * Connect the ecole database.
   */
  public RefAtelier(Connection con) {
    _Con = con;
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
   * Connect the ecole database.
   */
  public RefAtelier(String nom, Connection con) {
    _Con = con;
    _Name = nom;
    Statement st = null;
    //Cree Connection
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      con = DriverManager.getConnection("jdbc:mysql:///ecole", "root", "");
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    _Id = getAtelierId(_Name);
  }



  /**
   * get all the atelier_Noms in the Table and put then in a Vector.
   * @return A Vector containing all the atelier_Nom's in the Table
   */
  public Vector getAteliers() {
    Statement st = null;
    String result = "";
    Vector ateliers = new Vector();

    try {

      if (!_Con.isClosed())
        //System.out.println("Successfully connected to MySQL server...");
      st = _Con.createStatement();
      ResultSet rs = null;
      rs = st.executeQuery("SELECT atelier_nom FROM refatelier");
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
   * Get the atelier_id for an atelier_Name.
   * @param atelier
   * @return The atelier ID.
   */
  public int getAtelierId(String atelier) {
    Statement st = null;
    _Id = 0;

    try {

      if (!_Con.isClosed())
        //System.out.println("Successfully connected to MySQL server...");
      st = _Con.createStatement();
      ResultSet rs = null;
      rs = st.executeQuery("SELECT id FROM refatelier WHERE atelier_nom='" +
                           atelier + "'");
      while (rs.next()) {
        _Id = (rs.getInt(1));
      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    return _Id;
  }


  /**
   * Get the atelier_id for an atelier_Name.
   * @param atelier
   * @return The atelier ID.
   */
  public int getAtelierId() {
    Statement st = null;
    _Id = 0;

    try {

      if (!_Con.isClosed())
       // System.out.println("Successfully connected to MySQL server...");
      st = _Con.createStatement();
      ResultSet rs = null;
      rs = st.executeQuery("SELECT id FROM refatelier WHERE atelier_nom='"+_Name+"'");
      while (rs.next()) {
        _Id = (rs.getInt(1));
      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    return _Id;
  }


  /**
    * Set the Name of the Atelier.
    */
   public void setAtelierName(String atelier_name) {
     Statement st = null;
     _Name = atelier_name;

     try {

       if (!_Con.isClosed())
       //System.out.println("setAtelierName method");
       st = _Con.createStatement();
       int recordsUpdated;
      recordsUpdated = st.executeUpdate("INSERT INTO refatelier (atelier_nom) values ('"+_Name+"')");
       getAtelierId(_Name);
     }
     catch (Exception e) {
       System.err.println("Exception: " + e.getMessage());
     }
   }

   /**
    * Set the Name of the Atelier.
    */
   public void updateAtelierName(String atelier_name) {
     Statement st = null;
     _Name = atelier_name;

     try {

       if (!_Con.isClosed())
       //System.out.println("updateAtelierName method");
       st = _Con.createStatement();
       int recordsUpdated;
      recordsUpdated = st.executeUpdate("UPDATE refatelier SET atelier_nom='"+_Name+"' WHERE id="+_Id);
       //getAtelierId(_Name);
     }
     catch (Exception e) {
       System.err.println("Exception: " + e.getMessage());
     }
   }


   /**
       * Set the type of the Atelier.
       */
      public void setAtelierType(String type) {
        Statement st = null;
        _AtelierType = type;

        try {

          if (!_Con.isClosed())
          //System.out.println("setAtelierName method");
          st = _Con.createStatement();
          int recordsUpdated;
          recordsUpdated = st.executeUpdate("UPDATE refatelier set type='"+_AtelierType+"' WHERE id="+_Id);
        }
        catch (Exception e) {
          System.err.println("Exception: " + e.getMessage());
        }
      }

      /**
       * Set the jour of the Atelier.
       */
      public void setJour(String jour) {
        Statement st = null;
        _Jour = jour;

        try {

          if (!_Con.isClosed())
            //System.out.println("setJour method");
          st = _Con.createStatement();
          int recordsUpdated;
          recordsUpdated = st.executeUpdate("UPDATE refatelier set jour='" + _Jour +
                               "' WHERE id=" + _Id);
        }
        catch (Exception e) {
          System.err.println("Exception: " + e.getMessage());
        }
      }


      /**
       * Get the jour of the Atelier given an Atelier ID.
       * @param id
       * @return the Atelier Jour
       */
      public String getAtelierJour(int id) {
        Statement st = null;
        String _Jour = null;

        try {

          if (!_Con.isClosed())
          //System.out.println("Successfully connected to MySQL server...");
          st = _Con.createStatement();
          ResultSet rs = null;
          rs = st.executeQuery("SELECT jour FROM refatelier WHERE id='" + id +
                               "'");
          while (rs.next()) {
            _Jour = (rs.getString(1));
          }
        }
        catch (Exception e) {
          System.err.println("Exception: " + e.getMessage());
        }
        return _Jour;
      }


  /**
   * Get the Name of the Atelier given an Atelier ID.
   * @param id
   * @return the Atelier Name
   */
  public String getAtelierName(int id) {
    Statement st = null;
    String _Name = null;

    try {

      if (!_Con.isClosed())
      //System.out.println("Successfully connected to MySQL server...");
      st = _Con.createStatement();
      ResultSet rs = null;
      rs = st.executeQuery("SELECT atelier_nom FROM refatelier WHERE id='" + id +
                           "'");
      while (rs.next()) {
        _Name = (rs.getString(1));
      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    return _Name;
  }

  /**
   * Get the Atelier Type given an atelier id.
   * Used to make a distinction between Ateliers and Etudes and the different
   * types of Atelier.
   * @param atelier_id
   * @return The atelier type. e.g: Etudes is type "e".
   */
  public String getAtelierType(int atelier_id) {
    Statement st = null;
    _Id = atelier_id;

    try {
      if (!_Con.isClosed())
        //System.out.println("Successfully connected to MySQL server...");
      st = _Con.createStatement();
      ResultSet rs = null;
      rs = st.executeQuery("SELECT type FROM refatelier WHERE id='" +
                           atelier_id + "'");
      while (rs.next()) {
        _AtelierType = rs.getString(1);
      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    return _AtelierType;
  }
  /**
     * Delete all the datas for the classe_id (passed in the constructor) in the "classe" Table.
     * @param eleve_id
     */
    public void eraseAtelier() {
      Statement st = null;
      try {

        if (!_Con.isClosed())
          //System.out.println("eraseAll method");
        st = _Con.createStatement();
        int recordsUpdated;
        recordsUpdated = st.executeUpdate("DELETE FROM refatelier WHERE id=" + _Id);
      }
      catch (Exception e) {
        System.err.println("Exception: " + e.getMessage());
      }
    }

}