package ecole;

import javax.swing.table.AbstractTableModel;
import java.util.*;
import java.text.NumberFormat;
import java.sql.*;

class TableModelListAtelierByClasse extends AbstractTableModel {
      private String[] columnNames = {"#", "Nom","Prenom", "Ateliers", "Etude"};
      Object[][] mydata;
      Connection _Con = null;
      String _Classe=null;
      public TableModelListAtelierByClasse(Connection con, String classe){
     _Con=con;
     _Classe = classe;
     int n = 0;
     int total = 1;
     String tousAteliers = "";
     Classe classeForListe = new Classe(_Classe, _Con);
     Student listeStudent = new Student(_Con);
     int numberOfStudents = listeStudent.getList(classeForListe.getID()).length;
     mydata = new Object[numberOfStudents][5];
  for (int i=0; i<listeStudent.getList(classeForListe.getID()).length; i++){
    tousAteliers = "";
    Student eleve = listeStudent.getList(classeForListe.getID())[i];
    mydata[n][0] = total+"";
    mydata[n][1] = eleve.getLName();
    mydata[n][2] = eleve.getFName();

    Atelier atelier = new Atelier(eleve.getID(),_Con);
    Vector ateliers = atelier.getAteliersByEleve();
    RefAtelier refatelier = new RefAtelier(_Con);
    if (ateliers != null) {
      for (Enumeration enu = ateliers.elements();
           enu.hasMoreElements(); ) {
        int atelier_id = Integer.parseInt( (String) enu.nextElement()); // Casts
        String atelierNom = refatelier.getAtelierName(atelier_id);
        String atelierJour = refatelier.getAtelierJour(atelier_id);

        if (refatelier.getAtelierType(atelier_id).equals("E") ||
              refatelier.getAtelierType(atelier_id).equals("e")) {
            mydata[n][4] = "Oui";
          }
          else {
            tousAteliers += atelierNom +" (" +atelierJour + "),\n";
          }
        }
        mydata[n][3] = tousAteliers;
      }
      n++;
    total++;
    }
  }


      public int getColumnCount() {
      return columnNames.length;
  }

      public int getRowCount() {
      return mydata.length;
  }

      public String getColumnName(int col) {
      return columnNames[col];
  }

      public Object getValueAt(int row, int col) {
      return mydata[row][col];
  }

      //public Class getColumnClass(int c) {
   //   return getValueAt(0, c).getClass();
//  }
      /*
       * Don't need to implement this method unless your table's
       * editable.
       */
      public boolean isCellEditable(int row, int col) {
      //Note that the data/cell address is constant,
      //no matter where the cell appears onscreen.
      if (col <= 4) {
    return false;
  }
  else {
    return true;
  }
  }
      /*
       * Don't need to implement this method unless your table's
       * data can change.
       */
      public void setValueAt(Object value, int row, int col) {
      mydata[row][col] = (String)value;
      fireTableCellUpdated(row, col);
  }
}
