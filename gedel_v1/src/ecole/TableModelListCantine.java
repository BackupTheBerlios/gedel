package ecole;

import javax.swing.table.AbstractTableModel;
import java.util.*;
import java.text.NumberFormat;
import java.sql.*;

class TableModelListCantine extends AbstractTableModel {
      private String[] columnNames;
      Object[][] mydata;
      Connection _Con = null;
      String _RefAtelier=null;
      int numberOfStudents=0;
      Vector eleves = new Vector();
      public TableModelListCantine(Connection con, String classe, String mois, int jours){
        _Con = con;
        int compteurjours = 1;
        columnNames = new String[jours + 2];
        columnNames[0] = "Nom";
        columnNames[1] = "Prenom";
        for (int i = 2; i < columnNames.length; i++) {
          columnNames[i] = compteurjours + "";
          compteurjours++;
        }
        int n = 0;
        int total = 1;
        Classe classeForListe = new Classe(classe, _Con);
        Student listeStudent = new Student(_Con);
        Student[] liste = listeStudent.getList(classeForListe.getID());
        for (int j = 0; j < liste.length; j++) {
          Student eleve = listeStudent.getList(classeForListe.getID())[j];
          int id = eleve.getID();
          Cantine cantine = new Cantine(id, _Con);
          if (cantine.getEleveStatus()) {
            numberOfStudents++;
          }
        }

        mydata = new Object[numberOfStudents][columnNames.length];
        for (int j = 0; j < liste.length; j++) {
          Student eleve = listeStudent.getList(classeForListe.getID())[j];
          int id = eleve.getID();
          Cantine cantine = new Cantine(id, _Con);
          if (cantine.getEleveStatus()) {
            mydata[n][0] = eleve.getLName();
            mydata[n][1] = eleve.getFName();
            n++;
            total++;
          }
        }
      }


     /*
       for (int j = 0; j<liste.length;j++) {
         Student eleve = listeStudent.getList(classeForListe.getID())[j];
         mydata [n][0] = eleve.getLName();
         mydata [n][1] = eleve.getFName();
         n++;
         total++;
       }
      }*/

/*
             Student eleve = listeStudent.getList(classeForListe.getID())[i];
            liste += "<tr><td>" + eleve.getLName() + " " +
       */

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
