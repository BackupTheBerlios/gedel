package ecole;

import javax.swing.table.AbstractTableModel;
import java.util.*;
import java.text.NumberFormat;
import java.sql.*;

class TableModel extends AbstractTableModel {
      private String[] columnNames = {"Nom","Pr\u00E9nom", "Classe", "Tarif", "Date de validit\u00E9","Total", "Mode de Paiement","Pay\u00E9"};
      Object[][] mydata;
      Connection _Con = null;
      public TableModel(Connection con){
        _Con=con;
        int n = 0;
     double total = 0;
     double grandTotal = 0;
     double totalPeri = 0;
     double tarif = 0;
     String prixNom=null;
     Atelier atelierBilan = new Atelier(_Con);
     RefAtelier refatelier = new RefAtelier(_Con);
     TarifsAteliers tarifs = new TarifsAteliers(_Con);
     Student eleve = new Student(_Con);
     Classe classe = new Classe(_Con);
     Vector elevesID = atelierBilan.getDisctinctElevesid();
     int size = elevesID.size();
     mydata = new Object[size+3][8];
     NumberFormat formatFrance = NumberFormat.getInstance(Locale.FRANCE);
     for (Enumeration e = elevesID.elements(); e.hasMoreElements();) {
              total = 0;
              int id = Integer.parseInt((String)e.nextElement());
              prixNom = atelierBilan.getPriceName(id);
              mydata[n][0] = eleve.getLName(id);
              mydata[n][1] = eleve.getFName(id);
              mydata[n][2] = classe.getClasseNom(eleve.getClasseId(id));
              mydata[n][3] = prixNom;
              mydata[n][4] = atelierBilan.getDateValidite(id);
              for (Enumeration a = atelierBilan.getAteliersByEleve(id).elements(); a.hasMoreElements();){
                String atelierType = refatelier.getAtelierType(Integer.parseInt((String)a.nextElement()));
                tarif = tarifs.getPrix(prixNom);
                if (!atelierType.equalsIgnoreCase("E")){
                  if (atelierType.equalsIgnoreCase("p")){
                    totalPeri += tarif;
                    total += tarif;
                    grandTotal += tarif;
                  }else{
                    total += tarif;
                    grandTotal += tarif;
                  }
                }
              }
              mydata[n][5] = formatFrance.format(total);
              n++;
          }
          mydata[n][4] = "Total =";
          mydata[n][5] = formatFrance.format(grandTotal);
          n++;
          mydata[n][4] = "Total p\u00E9riscolaire =";
          mydata[n][5] = formatFrance.format(totalPeri);
          n++;
          mydata[n][4] = "Total ville =";
          mydata[n][5] = formatFrance.format(grandTotal-totalPeri);

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
      if (col <= 7) {
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