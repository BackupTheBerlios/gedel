package ecole;
import java.sql.*;
import java.util.Vector;
public class HistoCantine {
  Connection _Con = null;
  String _Month;


  public HistoCantine(Connection con) {
    _Con = con;
 }

  public HistoCantine(Connection con, String month) {
     _Con = con;
     _Month = month;
  }

  public void setFullRecord(int id_eleve, int id_classe, String type_payment, int nbrjours,
                            int id_tarifcantine, String datevalidite) {
   Statement st = null;
   String result = "";

   try {
     if (!_Con.isClosed())
       st = _Con.createStatement();
     int recordsUpdated;
     recordsUpdated = st.executeUpdate(
         "INSERT INTO histocantine (mois_histo, id_eleve, id_classe, type_payment,"+
        " nbrjours, id_tarifcantine, datevalidite) values('" + _Month + "', "+id_eleve+", "+
        id_classe+", '"+type_payment+"', "+nbrjours+", "+id_tarifcantine+", '"+datevalidite+"')");
   }
   catch (Exception e) {
     System.err.println("Exception: " + e.getMessage());
   }
 }

 public Vector getFullRecord(String m) {
   Statement st = null;
   String result = "";
   Vector records = new Vector();
   try {
     if (!_Con.isClosed())
       st = _Con.createStatement();
     ResultSet rs = null;
      rs = st.executeQuery("SELECT id_eleve, id_classe, type_payment, nbrjours, id_tarifcantine,"+
                           " date_format(datevalidite,'%d/%m/%Y') FROM histocantine WHERE mois_histo ='"+m+"' ORDER BY id");
      while (rs.next()) {
        HistoCantineRecord record = new HistoCantineRecord(rs.getInt(1), rs.getInt(2),rs.getString(3),
            rs.getInt(4), rs.getInt(5), rs.getString(6), m);
        records.add(record);
      }
   }
   catch (Exception e) {
     System.err.println("Exception: " + e.getMessage());
   }
   return records;
 }


 public Vector getcomboSauvegardes() {
    //String[] eleves;
    Statement st = null;
    String result = "";
    Vector sauvegardes = new Vector();

    try {

      if (!_Con.isClosed())
        //System.out.println("getcomboeleves");
      st = _Con.createStatement();
      ResultSet rs = null;
      rs = st.executeQuery("SELECT DISTINCT mois_histo FROM histocantine ORDER BY id");
      while (rs.next()) {
        sauvegardes.add(rs.getString(1));
      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    return sauvegardes;
  }

  public void eraseSauvegarde(String m){
    Statement st = null;
    try {

      if (!_Con.isClosed())
        //System.out.println("eraseAll method");
      st = _Con.createStatement();
      int recordsUpdated;
      recordsUpdated = st.executeUpdate("DELETE FROM histocantine  WHERE mois_histo='" +m+"'");
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }
}