package ecole;

public class HistoCantineRecord {

  private int id_eleve;
  private int id_classe;
  private String _typepayment;
  private int nbrjours;
  private int id_tarifcantine;
  private String date_Validite;
  private String _Month;

  public HistoCantineRecord(int ideleve, int idclasse, String typepayment,
                            int nbrjour, int idtarifcantine,
                            String DateValidite, String month) {
    id_eleve = ideleve;
    id_classe = idclasse;
    _typepayment = typepayment;
    nbrjours = nbrjour;
    id_tarifcantine = idtarifcantine;
    date_Validite = DateValidite;
    _Month = month;
  }

  public int getIdeleve() {
    return id_eleve;
  }

  public int getIdclasse() {
    return id_classe;
  }

  public String getTypepayment() {
    return _typepayment;
  }

  public int getNbrjours() {
    return nbrjours;
  }

  public int getIdtarifcantine() {
    return id_tarifcantine;
  }

  public String getDatevalidite() {
    return date_Validite;
  }

  public String getMonth() {
    return _Month;
  }

}