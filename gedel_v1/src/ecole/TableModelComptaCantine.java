package ecole;

import java.sql.Connection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import utils.Formatter;
import ecole.dialog.Dialog;

class TableModelComptaCantine extends AbstractTableModel implements TableModelListener {
  private String[] columnNames = {
      "Nom", "Pr\u00E9nom", "Classe", "Tarif", "Date de validit\u00E9", "Jours",
      "Total", "Mode de Paiement"};
  Object[][] mydata;
  Connection _Con = null;
  double totalcheque = 0;
  double totalespece = 0;
  int n = 0;
  String month = null;
  Vector allTarifs;
  Hashtable paidDays = new Hashtable();
  Cantine cantinebilan;
  TarifsCantine tarifs;
  String[] totaux;
  int size;
  int sizeTarifs;
  Classe classe;
  Student eleve;
  Hashtable table = new Hashtable();
  public TableModelComptaCantine(Connection con) {
    this.addTableModelListener(this);
    _Con = con;

    double total = 0;
    double grandTotal = 0;
    double tarif = 0;
    String prixNom = null;
    cantinebilan = new Cantine(_Con);
    tarifs = new TarifsCantine(_Con);
    eleve = new Student(_Con);
    classe = new Classe(_Con);
    Vector elevesID = cantinebilan.getDisctinctElevesid();
    allTarifs = cantinebilan.getDistinctTarifs();
    sizeTarifs = allTarifs.size();
    size = elevesID.size();
    totaux = new String[sizeTarifs];
    int track = 0;
    mydata = new Object[size + (sizeTarifs + 5)][8];

    for (Enumeration e = elevesID.elements(); e.hasMoreElements(); ) {
      total = 0;
      int id = Integer.parseInt( (String) e.nextElement());
      prixNom = cantinebilan.getPriceName(id);
      String nbrjours = cantinebilan.getNbrJours(id);
      total = Integer.parseInt(nbrjours) * tarifs.getPrix(prixNom);

      mydata[n][0] = eleve.getLName(id);
      mydata[n][1] = eleve.getFName(id);
      mydata[n][2] = classe.getClasseNom(eleve.getClasseId(id));
      mydata[n][3] = prixNom;
      mydata[n][4] = cantinebilan.getDateValidite(id);
      mydata[n][5] = cantinebilan.getNbrJours(id);
      mydata[n][6] = Formatter.doubleToString(total);

      grandTotal += total;
      n++;
    }
    mydata[n][5] = "---------------";
    mydata[n][6] = "---------------";
    mydata[n][7] = "---------------";
    n++;
    mydata[n][5] = "Total =";
    mydata[n][6] = Formatter.doubleToString(grandTotal);
    n++;
    mydata[n][5] = "Total cheque =";
    mydata[n][6] = "0";
    n++;
    mydata[n][5] = "Total especes =";
    mydata[n][6] = "0";
    n++;
    mydata[n][5] = "Total Paye =";
    mydata[n][6] = "0";
    n++;
    for (Enumeration e = allTarifs.elements(); e.hasMoreElements(); ) {
      String tarifNom = (String) e.nextElement();
      mydata[n][5] = tarifNom;
      mydata[n][6] = cantinebilan.getDaysByTarif(tarifNom) + " /0 Repas";
      table.put(tarifNom, tarifs.getPrix(tarifNom)+"");
      totaux[track] = Formatter.doubleToString( Double.parseDouble(table.get(tarifNom)+"") * cantinebilan.getDaysByTarif(tarifNom)) + " /0 Euros";
      mydata[n][7] = totaux[track];
      n++;
      track++;
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
    if ( (col <= 4) || (col <= 7 && row > n - (sizeTarifs + 6))) {
      return false;
    }else if(col==5){
      return true;
    }else if (col==6){
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
    mydata[row][col] = (String) value;
    fireTableCellUpdated(row, col);
  }
  
  /**
   * Place une valeur de type String dans une cellule. Laisse le choix de tirer l'évenement.
   * @param value
   * @param row
   * @param col
   * @param fire true: la methode fireTableCellUpdated() est appelé
   * @author JFORESTIER
   */
  public void setValueAt(String value, int row, int col, boolean fire) {
    mydata[row][col] = (String) value;
    if (fire) fireTableCellUpdated(row, col);
  }  
  
  /**
   * Rempli une cellule avec un double. Le formatage en %.02d est assuré.
   * @param value
   * @param row
   * @param col
   * @author JFORESTIER
   */
  public void setValueAt(double value, int row, int col) {
      setValueAt(Formatter.doubleToString(value), row, col);
  }  

  public void tableChanged(TableModelEvent e) {
    int row = e.getFirstRow();
    int column = e.getColumn();
    TableModelComptaCantine model = (TableModelComptaCantine) e.getSource();
    String columnName = model.getColumnName(column);
    if (columnName.equalsIgnoreCase("Mode de paiement") &&
        e.getLastRow() <= (getRowCount() - (sizeTarifs + 5))) {
      String value = (String) model.getValueAt(row, column);
      value = value.toUpperCase();
      setValueAt(value, row, column, false);
      if (value.equals("C")) {
        computeTable();
        computedMeals();
      }
      else if (value.equals("E")) {
        computeTable();
        computedMeals();
      }
      else if (value.equals("")) {
        computeTable();
        computedMeals();
      }
      else {
          Dialog.ErreurDeSaisie(null, "Vous devez choisir ici uniquement :\"C\" ou \"E\"");
        model.setValueAt("", row, column, false);
      }
    }else if(columnName.equalsIgnoreCase("Jours")){
     double tot =  Double.parseDouble(table.get(getValueAt(e.getFirstRow(), 3))+"") *
          Integer.parseInt(getValueAt(row, 5)+"");
      setValueAt(tot, row, 6);
      computeTable();
      computedMeals();
    }
    Object data = model.getValueAt(row, column);
  }

  private void computeTable() {
    double computedTotal = 0;
    double computedCheck = 0;
    double computedCash = 0;
    double computedPaid = 0;
    for (int i = 0; i < getRowCount() - (sizeTarifs + 5); i++) {
      computedTotal += Double.parseDouble(getValueAt(i, 6) + "");
      if ( (getValueAt(i, 7) + "").equalsIgnoreCase("e")) {
        computedCash += Double.parseDouble(getValueAt(i, 6) + "");
        computedPaid += Double.parseDouble(getValueAt(i, 6) + "");
      }
      else if ( (getValueAt(i, 7) + "").equalsIgnoreCase("c")) {
        computedCheck += Double.parseDouble(getValueAt(i, 6) + "");
        computedPaid += Double.parseDouble(getValueAt(i, 6) + "");
      }
    }
    setValueAt(computedTotal, (n - (sizeTarifs + 4)), 6);
    setValueAt(computedCheck, (n - (sizeTarifs + 3)), 6);
    setValueAt(computedCash, (n - (sizeTarifs + 2)), 6);
    setValueAt(computedPaid, (n - (sizeTarifs + 1)), 6);
  }

  public void saveDB(String month) {

    Student stud;
    TarifsCantine tarifCantine;
    StringTokenizer st;
        HistoCantine histo = new HistoCantine(_Con, month);
        for (int i = 0; i < size; i++) {
          stud = new Student( (getValueAt(i, 1) + ""), (getValueAt(i, 0) + ""),
                             _Con);
          tarifCantine = new TarifsCantine( (getValueAt(i, 3) + ""), _Con);
          String date = getValueAt(i, 4) + "";
          st = new StringTokenizer(date, "/");
          String day = st.nextToken();
          String datemonth = st.nextToken();
          String year = st.nextToken();
          date = (year + "-" + datemonth + "-" + day);
          histo.setFullRecord(stud.getID(), stud.getClasseId(),
                              (getValueAt(i, 7) + ""),
                              Integer.parseInt(getValueAt(i, 5) + ""),
                              tarifCantine.getID(), date);
        }
  }

  private void computedMeals() {
    int i = 0;
    String tarifNom = null;
    Object[] tarifstemp = new Object[sizeTarifs];
    n = getRowCount() - sizeTarifs;
    for (Enumeration e = allTarifs.elements(); e.hasMoreElements(); ) {
      String tarifNoms = (String) e.nextElement();
      setValueAt("0/0 Repas", n, 6);
      setValueAt("0/0 Euros", n, 7);
      n++;
      i++;
    }
    for (i = 0; i < getRowCount() - (sizeTarifs + 5); i++) {
      int j = 0;
      if ( (getValueAt(i, 7) + "").equalsIgnoreCase("e") ||
          (getValueAt(i, 7) + "").equalsIgnoreCase("c")) {
        for (Enumeration e = allTarifs.elements(); e.hasMoreElements(); ) {
          tarifNom = (String) e.nextElement();
          if (getValueAt(i, 3).equals(tarifNom)) {
            StringTokenizer st = new StringTokenizer(getValueAt( (getRowCount() -
                sizeTarifs) + j, 6) + "", "/");
            int val1 = Integer.parseInt(st.nextToken());
            int temp0 = Integer.parseInt(getValueAt(i, 5)+"") + val1;

            String tempMeal = st.nextToken();
            st = new StringTokenizer(tempMeal);
            int val2 = Integer.parseInt(st.nextToken());
            int temp1 = Integer.parseInt(getValueAt(i, 5) + "") + val2;
            setValueAt(temp0 + "/" + temp1 + " repas",
                       (getRowCount() - sizeTarifs) + j, 6);

            st = new StringTokenizer(getValueAt( (getRowCount() - sizeTarifs) +
                                                j, 7) + "", "/");
            double val3 = Double.parseDouble(st.nextToken());
            double temp3 = Double.parseDouble(getValueAt(i, 6) + "") + val3;
            tempMeal = st.nextToken();
            st = new StringTokenizer(tempMeal);
            double val4 = Double.parseDouble(st.nextToken());
            double temp2 = Double.parseDouble(getValueAt(i, 6) + "") + val4;
            setValueAt(Formatter.doubleToString(temp3) + " / " + Formatter.doubleToString(temp2) + " Euros",
                       (getRowCount() - sizeTarifs) + j, 7);

          }
          else {
            j++;
          }
        }
      }else{
        for (Enumeration e = allTarifs.elements(); e.hasMoreElements(); ) {
          tarifNom = (String) e.nextElement();
          if (getValueAt(i, 3).equals(tarifNom)) {
            StringTokenizer st = new StringTokenizer(getValueAt( (getRowCount() -
                sizeTarifs) + j, 6) + "", "/");
            int val1 = Integer.parseInt(st.nextToken());
            int temp0 = Integer.parseInt(getValueAt(i, 5) + "") + val1;

            String tempMeal = st.nextToken();
            st = new StringTokenizer(tempMeal);
            int val2 = Integer.parseInt(st.nextToken());
            setValueAt(temp0 + "/" + val2 + " repas",
                       (getRowCount() - sizeTarifs) + j, 6);

            st = new StringTokenizer(getValueAt( (getRowCount() - sizeTarifs) +
                                                j, 7) + "", "/");
            double val3 = Double.parseDouble(st.nextToken());
            double temp3 = Double.parseDouble(getValueAt(i, 6) + "") + val3;
            tempMeal = st.nextToken();
            st = new StringTokenizer(tempMeal);
            double val4 = Double.parseDouble(st.nextToken());
            double temp2 = Double.parseDouble(getValueAt(i, 6) + "") + val4;
            setValueAt(temp3 + "/" + val4 + " Euros",
                       (getRowCount() - sizeTarifs) + j, 7);

          } else {
            j++;
          }

        }

      }
    }
  }

  public String getMonth() {
    return month;
  }

  public void loadDatas(String m){
    HistoCantine histoLoad = new HistoCantine(_Con);
    Vector rr = histoLoad.getFullRecord(m);
    int i = 0;
     for (Enumeration e = rr.elements(); e.hasMoreElements(); ) {
       HistoCantineRecord record = (HistoCantineRecord) e.nextElement();
       setValueAt(eleve.getLName(record.getIdeleve()),i,0);
       setValueAt(eleve.getFName(record.getIdeleve()),i,1);
       setValueAt(classe.getClasseNom(record.getIdclasse()),i,2);
       String tarifNom = tarifs.getPrixnom(record.getIdtarifcantine());
       setValueAt(tarifNom,i,3);
       setValueAt(record.getDatevalidite(),i,4);
       setValueAt(record.getNbrjours()+"",i,5);
       double total = Integer.parseInt(record.getNbrjours()+"") * tarifs.getPrix(tarifNom);
       setValueAt(total, i,6);
       String type = record.getTypepayment();
       if (type.equalsIgnoreCase("n")){
         setValueAt("",i,7);
       }else{
         setValueAt(type, i, 7);
       }
       i++;
     }
  }

  public void EraseData(String m){
    HistoCantine histoErase = new HistoCantine(_Con);
    histoErase.eraseSauvegarde(m);
  }

}