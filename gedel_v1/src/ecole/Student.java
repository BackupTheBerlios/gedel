package ecole;

import java.sql.*;
import java.util.Vector;

/**
 * contains the same fields as the table eleve
 */

public class Student {
  int _Id;
  String _FName;
  String _LName;
  String _Sex;
  String _Dob;
  String _Street;
  String _Zip;
  String _City;
  String _Tel1;
  String _Tel2;
  String _Tel3;
  int _ClasseId;
  String _DateEntree;
  Connection _Con = null;

  /**
   * Creates the connection to the Database ecole.
   */
  public Student(Connection con) {
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
   * Creates the connection to the Database ecole.
   * @param fName : First Name
   * @param lName : Last Name
   */
  public Student(String fName, String lName, Connection con) {
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
    _FName = fName;
    _LName = lName;
    _Id = getID();
  }

  /**
   * Update the First Name.
   * @param id
   * @param fname
   */
  public void updateFirstName(int id, String fname) {
    Statement st = null;
    String result = "";
    _FName = fname;
    _Id = id;
    try {

      if (!_Con.isClosed())
        //System.out.println("updateFName method");
      st = _Con.createStatement();
      int recordsUpdated;
      recordsUpdated = st.executeUpdate(
          "UPDATE eleve set prenom='" + _FName + "' WHERE id="+_Id);
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }

  /**
   * Update the Last Name.
   * @param id
   * @param lname
   */
    public void updateLastName(int id, String lname) {
      Statement st = null;
      String result = "";
      _LName = lname;
      _Id = id;
      try {

        if (!_Con.isClosed())
          //System.out.println("updateFName method");
        st = _Con.createStatement();
        int recordsUpdated;
        recordsUpdated = st.executeUpdate(
            "UPDATE eleve set nom='" + _LName + "' WHERE id="+_Id);
      }
      catch (Exception e) {
        System.err.println("Exception: " + e.getMessage());
      }
    }

  /*public void setID(int oldId, int newId){
    Statement st = null;
   _Id = newId;
    try {

      if (!_Con.isClosed())
        System.out.println("setFetLName method");
      st = _Con.createStatement();
      int recordsUpdated;
      recordsUpdated = st.executeUpdate(
          "UPDATE eleve set id=" + _Id + " WHERE id="+oldId+" AND nom='"+_LName+"' AND prenom='" + _FName);
      _Id = getID();
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }

  }*/

  /**
   * return the Id  of the student.
   * Corresponds to the id field of the table eleve.
   * @param fname
   * @param lname
   * @return the id as an int.
   */
  public int getID() {
    Statement st = null;
    int result = 0;
    try {

      if (!_Con.isClosed())
        //System.out.println("getID method");
      st = _Con.createStatement();
      ResultSet rs = null;

      rs = st.executeQuery("SELECT id FROM eleve WHERE nom='" + _LName +
                           "' AND prenom='" + _FName + "'");
      while (rs.next()) {
        int userId = rs.getInt(1);
        result = userId;
       // System.out.println("id from student" + result);
      }
    }
    catch (Exception e) {
      System.err.println("Exceptionee: " + e.getMessage());
    }

    return result;
  }

  /**
   * Set the First Name and the Last Name.
   * Also call the getID method and stores the id into _Id field
   * @param fname
   * @param lname
   */
  public void setFirstNameLastName(String fname, String lname) {
    Statement st = null;
    String result = "";
    _FName = fname;
    _LName = lname;
    try {

      if (!_Con.isClosed())
       // System.out.println("setFetLName method");
      st = _Con.createStatement();
      int recordsUpdated;
      recordsUpdated = st.executeUpdate(
          "INSERT INTO eleve (nom,prenom)values ('" + lname + "','" + fname +
          "')");
      _Id = getID();
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }

  /**
   * Get the first name of the current Student.
   * @return The First Name
   */
  public String getFName() {
    return _FName;
  }

  /**
   * Get the last name of the current Student.
   * @return The Last Name
   */
  public String getLName() {
    return _LName;
  }

  /**
   * Get the first name of the current Student.
   * @return The First Name
   */
  public String getFName(int id) {

    Statement st = null;
    String result = null;
    String fname=null;
    try {

      if (!_Con.isClosed())
        //System.out.println("getfnamemethod");
      st = _Con.createStatement();
      ResultSet rs = null;

      rs = st.executeQuery("SELECT prenom FROM eleve WHERE id=" + id);
      while (rs.next()) {
        fname = rs.getString(1);
        //System.out.println(fname);
      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    return fname;
  }

  /**
   * Get the last name of the current Student.
   * @return The Last Name
   */
  public String getLName(int id) {
    Statement st = null;
    String result = null;
    String lname=null;
    try {

      if (!_Con.isClosed())
       // System.out.println("getlnamemethod");
      st = _Con.createStatement();
      ResultSet rs = null;

      rs = st.executeQuery("SELECT nom FROM eleve WHERE id=" + id);
      while (rs.next()) {
        lname = rs.getString(1);
        //System.out.println(lname);
      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    return lname;

  }


  /**
   * set the the sexe of the current students object.
   * @param Sex
   */
  public void setSex(String sex) {
    _Sex = sex;
    Statement st = null;
    String result = "";

    try {

      if (!_Con.isClosed())
        //System.out.println("setsex method");
      st = _Con.createStatement();
      int recordsUpdated;
      recordsUpdated = st.executeUpdate("UPDATE eleve set sexe='" + _Sex +
                                        "' WHERE id=" + _Id);
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }

  /**
   * Get the Sex of the current Student
   * @return The sex of the Student.
   */
  public String getSex() {
    getID();
    Statement st = null;
    String result = null;
    try {

      if (!_Con.isClosed())
        //System.out.println("getsexmethod");
      st = _Con.createStatement();
      ResultSet rs = null;

      rs = st.executeQuery("SELECT sexe FROM eleve WHERE id=" + _Id);
      while (rs.next()) {
        String sex = rs.getString(1);
        _Sex = sex;
        //System.out.println(_Sex);
      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    return _Sex;
  }

  /**
   * Set the Date of Birth of the current Student.
   * @param dob. Date format yyyy-mm-dd
   */
  public void setDob(String dob) {
    _Dob = dob;
    Statement st = null;
    String result = "";
    try {

      if (!_Con.isClosed())
        //System.out.println("setdob method");
      st = _Con.createStatement();
      int recordsUpdated;
      recordsUpdated = st.executeUpdate("UPDATE eleve set dob='" + _Dob +
                                        "' WHERE id=" + _Id);
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }

  /**
   * Get the Date of Birth of the current Student.
   * @return The Date of Birth formated like : yyyy-mm-dd hh:mm:ss
   */
  public String getDob() {
    getID();
    Statement st = null;
    String result = null;
    try {

      if (!_Con.isClosed())
        //System.out.println("getdob method1");
      st = _Con.createStatement();
      ResultSet rs = null;

      rs = st.executeQuery("SELECT dob FROM eleve WHERE id=" + _Id);
      while (rs.next()) {
        String dob = rs.getString(1);
        rs = st.executeQuery("select date_format('"+dob+"', '%d/%m/%Y')");
        rs.next();
        dob = rs.getString(1);
        _Dob = dob;
        //System.out.println(_Dob);
      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    return _Dob;
  }

  /**
   * Set the name of the Street of the current Student
   * @param street
   */
  public void setStreet(String street) {
    _Street = street;
    Statement st = null;
    String result = "";
    try {

      if (!_Con.isClosed())
       // System.out.println("setstreet method.");
      st = _Con.createStatement();
      int recordsUpdated;
      recordsUpdated = st.executeUpdate("UPDATE eleve set rue='" + _Street +
                                        "' WHERE id=" + _Id);
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }

  /**
   * Get the name of the Street of the current Student
   * @return The name of the Street
   */
  public String getStreet() {
    getID();
    Statement st = null;
    String result = null;
    try {

      if (!_Con.isClosed())
        //System.out.println("getstreet method");
      st = _Con.createStatement();
      ResultSet rs = null;

      rs = st.executeQuery("SELECT rue FROM eleve WHERE id=" + _Id);
      while (rs.next()) {
        String street = rs.getString(1);
        _Street = street;
        //System.out.println(_Dob);
      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    return _Street;
  }

  /**
   * Set the Zip Code of the current Student
   * @param zip
   */
  public void setZip(String zip) {
    _Zip = zip;
    Statement st = null;
    String result = "";

    try {

      if (!_Con.isClosed())
        //System.out.println("setzipmethod");
      st = _Con.createStatement();
      int recordsUpdated;
      recordsUpdated = st.executeUpdate("UPDATE eleve set codepostal='" + _Zip +
                                        "' WHERE id=" + _Id);
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }

  /**
   * Get the Zip Code of the current Student
   * @return The Zip Code
   */
  public String getZip() {
    getID();
    Statement st = null;
    String result = null;
    try {

      if (!_Con.isClosed())
        //System.out.println("getzipmethod");
      st = _Con.createStatement();
      ResultSet rs = null;

      rs = st.executeQuery("SELECT codepostal FROM eleve WHERE id=" + _Id);
      while (rs.next()) {
        String zip = rs.getString(1);
        _Zip = zip;
        //System.out.println(_Zip);
      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    return _Zip;
  }

  /**
   * Set the Zip Code of the current Student
   * @param city
   */
  public void setCity(String city) {
    _City = city;
    Statement st = null;
    String result = "";

    try {

      if (!_Con.isClosed())
        //System.out.println("setcitymethod");
      st = _Con.createStatement();
      int recordsUpdated;
      recordsUpdated = st.executeUpdate("UPDATE eleve set ville='" + _City +
                                        "' WHERE id=" + _Id);
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }

  /**
   * Get the City of the current Student
   * @return The City
   */
  public String getCity() {
    getID();
    Statement st = null;
    String result = null;
    try {

      if (!_Con.isClosed())
        //System.out.println("getcitymethod");
      st = _Con.createStatement();
      ResultSet rs = null;

      rs = st.executeQuery("SELECT ville FROM eleve WHERE id=" + _Id);
      while (rs.next()) {
        String city = rs.getString(1);
        _City = city;
        //System.out.println(_City);
      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    return _City;
  }

  /**
   * Set the Phone Number(1) of the current Student
   * @param TelePhone Number 1
   */
  public void setTel1(String tel1) {
    _Tel1 = tel1;
    Statement st = null;
    String result = "";

    try {

      if (!_Con.isClosed())
        //System.out.println("settel1method");
      st = _Con.createStatement();
      int recordsUpdated;
      recordsUpdated = st.executeUpdate("UPDATE eleve set telephone1='" + _Tel1 +
                                        "' WHERE id=" + _Id);
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }

  /**
   * Get the Phone Number(1) of the current Student
   * @return The Phone Number(1)
   */
  public String getTel1() {
    getID();
    Statement st = null;
    String result = null;
    try {

      if (!_Con.isClosed())
        //System.out.println("gettel1method");
      st = _Con.createStatement();
      ResultSet rs = null;

      rs = st.executeQuery("SELECT telephone1 FROM eleve WHERE id=" + _Id);
      while (rs.next()) {
        String tel1 = rs.getString(1);
        _Tel1 = tel1;
        //System.out.println(_Tel1);
      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    return _Tel1;
  }

  /**
   * Set the Phone Number(2) of the current Student
   * @param TelePhone Number 2
   */
  public void setTel2(String tel2) {
    _Tel2 = tel2;
    Statement st = null;
    String result = "";

    try {

      if (!_Con.isClosed())
        //System.out.println("settel2method");
      st = _Con.createStatement();
      int recordsUpdated;
      recordsUpdated = st.executeUpdate("UPDATE eleve set telephone2='" + _Tel2 +
                                        "' WHERE id=" + _Id);
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }

  /**
   * Get the Phone Number(2) of the current Student
   * @return The Phone Number(2)
   */
  public String getTel2() {
    getID();
    Statement st = null;
    String result = null;
    try {

      if (!_Con.isClosed())
        //System.out.println("gettel2method");
      st = _Con.createStatement();
      ResultSet rs = null;

      rs = st.executeQuery("SELECT telephone2 FROM eleve WHERE id=" + _Id);
      while (rs.next()) {
        String tel2 = rs.getString(1);
        _Tel2 = tel2;
        //System.out.println(_Tel2);
      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    return _Tel2;
  }

  /**
   * Set the Phone Number(3) of the current Student
   * @param TelePhone Number 3
   */
  public void setTel3(String tel3) {
    _Tel3 = tel3;
    Statement st = null;
    String result = "";

    try {

      if (!_Con.isClosed())
        //System.out.println("settel3method");
      st = _Con.createStatement();
      int recordsUpdated;
      recordsUpdated = st.executeUpdate("UPDATE eleve set telephone3='" + _Tel3 +
                                        "' WHERE id=" + _Id);
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }

  /**
   * Get the Phone Number(3) of the current Student
   * @return The Phone Number(3)
   */
  public String getTel3() {
    getID();
    Statement st = null;
    String result = null;
    try {
      if (!_Con.isClosed())
        //System.out.println("gettel3method");
      st = _Con.createStatement();
      ResultSet rs = null;

      rs = st.executeQuery("SELECT telephone3 FROM eleve WHERE id=" + _Id);
      while (rs.next()) {
        String tel3 = rs.getString(1);
        _Tel3 = tel3;
        //System.out.println(_Tel3);
      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    return _Tel3;
  }

  /**
   * Set the Classe Id of the current Student
   * @param classeID
   */
  public void setClasseId(int classeID) {
    Statement st = null;
    String result = "";
    try {

      if (!_Con.isClosed())
        //System.out.println("setclasseidmethod");
      st = _Con.createStatement();
      int recordsUpdated;
      recordsUpdated = st.executeUpdate("UPDATE eleve set classeid='" +
                                        classeID +
                                        "' WHERE id=" + _Id);
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }

  /**
   * Get the Classe Id of the current Student
   * @return the Classe ID
   */
  public int getClasseId() {
    getID();
    Statement st = null;
    String result = null;
    try {
      if (!_Con.isClosed())
        //System.out.println("getclasseidmethod");
      st = _Con.createStatement();
      ResultSet rs = null;

      rs = st.executeQuery("SELECT classeid FROM eleve WHERE id=" + _Id);
      while (rs.next()) {
        _ClasseId = rs.getInt(1);
      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    return _ClasseId;
  }

  /**
   * Get the Classe Id of the current Student
   * @return the Classe ID
   */
  public int getClasseId(int eleveID) {
    int classe = 0;
    Statement st = null;
    String result = null;
    try {
      if (!_Con.isClosed())
        //System.out.println("getclasseidmethod");
      st = _Con.createStatement();
      ResultSet rs = null;

      rs = st.executeQuery("SELECT classeid FROM eleve WHERE id=" + eleveID);
      while (rs.next()) {
        classe = rs.getInt(1);
      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    return classe;
  }


  /**
     * Set the Date D'entree dans l'ecole of the current Student
     * @param dateEntree. Date format yyyy-mm-dd
     */
  public void setDateEntree(String dateEntree) {
    _DateEntree = dateEntree;
    Statement st = null;
    String result = "";
    try {

      if (!_Con.isClosed())
        //System.out.println("setdateentreemethod");
      st = _Con.createStatement();
      int recordsUpdated;
      recordsUpdated = st.executeUpdate("UPDATE eleve set dateentree='" +
                                        _DateEntree +
                                        "' WHERE id=" + _Id);
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }

  /**
   * Get the Date D'entree dans l'ecole of the current Student
   * @return Date format yyy-mm-dd hh:mm:ss
   */
  public String getDateEntree() {
    getID();
    Statement st = null;
    String result = null;
    try {
      if (!_Con.isClosed())
        //System.out.println("getdateentree");
      st = _Con.createStatement();
      ResultSet rs = null;
      rs = st.executeQuery("SELECT dateentree FROM eleve WHERE id=" + _Id);
      while (rs.next()) {
        String dateEntree = rs.getString(1);
        rs = st.executeQuery("select date_format('"+dateEntree+"', '%d/%m/%Y')");
        rs.next();
        dateEntree = rs.getString(1);
        _DateEntree = dateEntree;
        //System.out.println(_DateEntree);
      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    return _DateEntree;
  }

  /**
   * Get all the Eleves's first name and last name in the Table and put then in a Vector.
   * @return The Vector containing the first name and last name concatenated
   */
  public Vector getcomboEleves() {
    String[] eleves;
    Statement st = null;
    String result = "";
    Vector elevesNoms = new Vector();

    try {

      if (!_Con.isClosed())
        //System.out.println("getcomboeleves");
      st = _Con.createStatement();
      ResultSet rs = null;
      rs = st.executeQuery("SELECT nom, prenom FROM eleve ORDER BY nom");
      while (rs.next()) {
        String nom = rs.getString(1);
        String prenom = rs.getString(2);
        elevesNoms.add(nom + "," + prenom);
      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
    return elevesNoms;
  }

  /**
   * Delete all the datas for the eleve_id (passed in the constructor) in the "atelier" Table.
   * @param eleve_id
   */
  public void eraseStudentInfo() {
    Statement st = null;
    try {

      if (!_Con.isClosed())
        //System.out.println("eraseAll method");
      st = _Con.createStatement();
      int recordsUpdated;
      recordsUpdated = st.executeUpdate("DELETE FROM eleve  WHERE id=" + _Id);
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
  }

  public Student[] getList(int classeId){
    Statement st = null;
    String result = "";
    Vector nom = new Vector();
    Vector prenom = new Vector();
    Vector sexe = new Vector();
    Vector dob = new Vector();
    Student stud[];
    try {
      if (!_Con.isClosed())
        //System.out.println("getlist");
      st = _Con.createStatement();
      ResultSet rs = null;
      rs = st.executeQuery(
          "SELECT nom, prenom, sexe, dob FROM eleve WHERE classeid=" + classeId +
          " ORDER BY nom");
      while (rs.next()) {
        nom.add(rs.getString(1));
        prenom.add(rs.getString(2));
        sexe.add(rs.getString(3));
        dob.add(rs.getString(4));
      }
    }
    catch (Exception e) {
      System.err.println("Exception: " + e.getMessage());
    }
      stud = new Student[nom.size()];
      for (int i=0; i<stud.length;i++){
        stud[i] = new Student((String)prenom.elementAt(i), (String)nom.elementAt(i), _Con);
        stud[i].setSex((String)sexe.elementAt(i));
        stud[i].setDob((String)dob.elementAt(i));
      }
    return stud;
  }
}