package ecole;

import javax.swing.table.AbstractTableModel;
import java.util.*;
import java.text.NumberFormat;
import java.sql.*;

class TableModelListAtelier extends AbstractTableModel {
      private String[] columnNames = {"#", "Nom","Prenom", "Classe"};
      Object[][] mydata;
      Connection _Con = null;
      String _RefAtelier=null;
      public TableModelListAtelier(Connection con, String refAtelier){
     _Con=con;
     _RefAtelier = refAtelier;
     int n = 0;
     int total = 1;
     RefAtelier refatelier = new RefAtelier(_RefAtelier, _Con);
     int atelierid = refatelier.getAtelierId();
     Atelier atelier = new Atelier(_Con);
     Student eleve = new Student(_Con);
     Vector eleves = atelier.getElevesid(atelierid);
     int numberOfStudents = eleves.size();
     System.out.println(numberOfStudents);
     mydata = new Object[numberOfStudents][4];
       for (Enumeration e = eleves.elements(); e.hasMoreElements(); ) {
         int id = Integer.parseInt((String)e.nextElement()); // cast
         Classe classe = new Classe(_Con);
         String classenom = classe.getClasseNom(eleve.getClasseId(id));
         mydata [n][0] = total+"";
         mydata [n][1] = eleve.getLName(id);
         mydata [n][2] = eleve.getFName(id);
         mydata [n][3] = classenom;
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
      if (col <= 3) {
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
