/*package ecole;

import java.sql.*;
import java.util.Vector;
import java.util.Enumeration;
import javax.swing.*;
import java.awt.Dimension;

public class Text extends JFrame{
  private String _FName;
  private String _LName;
  Connection con = null;
  public Text() {
    super ("Test");
    JPanel panel = new JPanel();
    JTable table = new JTable(new TableModel());
    table.setPreferredScrollableViewportSize(new Dimension(500, 70));
    panel.add(table);
    getContentPane().add(panel);
    pack();
    setVisible(true);


    Statement st = null;

    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      con = DriverManager.getConnection("jdbc:mysql:///ecole", "root", "");

    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }

  public String getFirstName(int i) {
    Statement st = null;
    String result = "";

    try {

      if (!con.isClosed())
        //System.out.println("Successfully connected to MySQL server...");
      st = con.createStatement();
      ResultSet rs = null;
      rs = st.executeQuery("SELECT * FROM eleve WHERE id=" + i);
      while (rs.next()) {
        int userId = rs.getInt(1);
        _FName = rs.getString(2);
        _LName = rs.getString(3);
        // String countryCode = rs.getString(4);

        result = userId + ". " + _LName + ", " +
            _FName ;
      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }

    return result;
  }

  public void setFirstName(String name) {
    Statement st = null;
    String result = "";

    try {

      if (!con.isClosed())
        //System.out.println("Successfully connected to MySQL server...");
      st = con.createStatement();
      int recordsUpdated;
      recordsUpdated = st.executeUpdate(
          "INSERT INTO eleve (nom, prenom,sexe)values ('" + name +
          "', 'tttt','m')");
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }

  }


  public Vector getcomboClasses() {
    String[] classes;
    Statement st = null;
    String result = "";
    Vector classeID = new Vector();

    try {

      if (!con.isClosed())
        //System.out.println("Successfully connected to MySQL server...");
      st = con.createStatement();
      ResultSet rs = null;
      rs = st.executeQuery("SELECT classe_nom FROM classe");
       while (rs.next()) {
        classeID.add(rs.getString(1));
      }
     // for (Enumeration en = classeID.elements(); en.hasMoreElements(); ) {
      //        String s = (String) en.nextElement(); // Casts
       //       System.out.println(s);
     // }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    finally {
      try {
        if (con != null)
          con.close();
      }
      catch (SQLException e) {}
    }
    return classeID;
  }
  public static void main(String[] args) {
    Text text = new Text();
  }
}*/