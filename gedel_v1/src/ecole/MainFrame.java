package ecole;
import com.activetree.resource.AtImageList;
import com.activetree.print.*;
import com.activetree.utils.AtDebug;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.io.OutputStream;

public class MainFrame
    extends JFrame
    implements ActionListener {
  protected JFrame frame = new JFrame();
  JTable table;
  JButton print;
  private JScrollPane scrollPane = null;
  protected AtDocumentPrinter printer;
  protected AtPdfPrinter pdfPrinter;
  protected AtTablePrinter tablePrinter;
  protected AtGenericPrinter genericPrinter;

  public MainFrame() {
    super("test");
    SmartJPrint.setLicenseKey("AJET-LGFH-K68E-C30B");
    Container c = getContentPane();
    c.setLayout(new BorderLayout());
    print = new JButton("Print");
    print.addActionListener(this);
    String[] columnNames = {
        "First Name",
        "Last Name",
        "Sport",
        "# of Years",
        "Vegetarian"};

    Object[][] data = {
        {
        "Mary", "Campione",
        "Snowboarding", new Integer(5), new Boolean(true)}
        , {
        "Alison", "Huml",
        "Rowing", new Integer(3), new Boolean(true)}
        , {
        "Kathy", "Walrath",
        "Knitting", new Integer(2), new Boolean(false)}
        , {
        "Sharon", "Zakhour",
        "Speed reading", new Integer(20), new Boolean(true)}
        , {
        "Philip", "Milne",
        "Pool", new Integer(10), new Boolean(false)}
        , {
        "Mary", "Campione",
        "Snowboarding", new Integer(5), new Boolean(true)}
        , {
        "Alison", "Huml",
        "Rowing", new Integer(3), new Boolean(true)}
        , {
        "Kathy", "Walrath",
        "Knitting", new Integer(2), new Boolean(false)}
        , {
        "Sharon", "Zakhour",
        "Speed reading", new Integer(20), new Boolean(true)}
        , {
        "Philip", "Milne",
        "Pool", new Integer(10), new Boolean(false)}
        , {
        "Mary", "Campione",
        "Snowboarding", new Integer(5), new Boolean(true)}
        , {
        "Alison", "Huml",
        "Rowing", new Integer(3), new Boolean(true)}
        , {
        "Kathy", "Walrath",
        "Knitting", new Integer(2), new Boolean(false)}
        , {
        "Sharon", "Zakhour",
        "Speed reading", new Integer(20), new Boolean(true)}
        , {
        "Philip", "Milne",
        "Pool", new Integer(10), new Boolean(false)}
        , {
        "Mary", "Campione",
        "Snowboarding", new Integer(5), new Boolean(true)}
        , {
        "Alison", "Huml",
        "Rowing", new Integer(3), new Boolean(true)}
        , {
        "Kathy", "Walrath",
        "Knitting", new Integer(2), new Boolean(false)}
        , {
        "Sharon", "Zakhour",
        "Speed reading", new Integer(20), new Boolean(true)}
        , {
        "Philip", "Milne",
        "Pool", new Integer(10), new Boolean(false)}
        , {
        "Mary", "Campione",
        "Snowboarding", new Integer(5), new Boolean(true)}
        , {
        "Alison", "Huml",
        "Rowing", new Integer(3), new Boolean(true)}
        , {
        "Kathy", "Walrath",
        "Knitting", new Integer(2), new Boolean(false)}
        , {
        "Sharon", "Zakhour",
        "Speed reading", new Integer(20), new Boolean(true)}
        , {
        "Philip", "Milne",
        "Pool", new Integer(10), new Boolean(false)}
        , {
        "Mary", "Campione",
        "Snowboarding", new Integer(5), new Boolean(true)}
        , {
        "Alison", "Huml",
        "Rowing", new Integer(3), new Boolean(true)}
        , {
        "Kathy", "Walrath",
        "Knitting", new Integer(2), new Boolean(false)}
        , {
        "Sharon", "Zakhour",
        "Speed reading", new Integer(20), new Boolean(true)}
        , {
        "Philip", "Milne",
        "Pool", new Integer(10), new Boolean(false)}
        , {
        "Mary", "Campione",
        "Snowboarding", new Integer(5), new Boolean(true)}
        , {
        "Alison", "Huml",
        "Rowing", new Integer(3), new Boolean(true)}
        , {
        "Kathy", "Walrath",
        "Knitting", new Integer(2), new Boolean(false)}
        , {
        "Sharon", "Zakhour",
        "Speed reading", new Integer(20), new Boolean(true)}
        , {
        "Philip", "Milne",
        "Pool", new Integer(10), new Boolean(false)}
        , {
        "Mary", "Campione",
        "Snowboarding", new Integer(5), new Boolean(true)}
        , {
        "Alison", "Huml",
        "Rowing", new Integer(3), new Boolean(true)}
        , {
        "Kathy", "Walrath",
        "Knitting", new Integer(2), new Boolean(false)}
        , {
        "Sharon", "Zakhour",
        "Speed reading", new Integer(20), new Boolean(true)}
        , {
        "Philip", "Milne",
        "Pool", new Integer(10), new Boolean(false)}
        , {
        "Mary", "Campione",
        "Snowboarding", new Integer(5), new Boolean(true)}
        , {
        "Alison", "Huml",
        "Rowing", new Integer(3), new Boolean(true)}
        , {
        "Kathy", "Walrath",
        "Knitting", new Integer(2), new Boolean(false)}
        , {
        "Sharon", "Zakhour",
        "Speed reading", new Integer(20), new Boolean(true)}
        , {
        "Philip", "Milne",
        "Pool", new Integer(10), new Boolean(false)}
        , {
        "Mary", "Campione",
        "Snowboarding", new Integer(5), new Boolean(true)}
        , {
        "Alison", "Huml",
        "Rowing", new Integer(3), new Boolean(true)}
        , {
        "Kathy", "Walrath",
        "Knitting", new Integer(2), new Boolean(false)}
        , {
        "Sharon", "Zakhour",
        "Speed reading", new Integer(20), new Boolean(true)}
        , {
        "Philip", "Milne",
        "Pool", new Integer(10), new Boolean(false)}
        , {
        "Mary", "Campione",
        "Snowboarding", new Integer(5), new Boolean(true)}
        , {
        "Alison", "Huml",
        "Rowing", new Integer(3), new Boolean(true)}
        , {
        "Kathy", "Walrath",
        "Knitting", new Integer(2), new Boolean(false)}
        , {
        "Sharon", "Zakhour",
        "Speed reading", new Integer(20), new Boolean(true)}
        , {
        "Philip", "Milne",
        "Pool", new Integer(10), new Boolean(false)}
        , {
        "Mary", "Campione",
        "Snowboarding", new Integer(5), new Boolean(true)}
        , {
        "Alison", "Huml",
        "Rowing", new Integer(3), new Boolean(true)}
        , {
        "Kathy", "Walrath",
        "Knitting", new Integer(2), new Boolean(false)}
        , {
        "Sharon", "Zakhour",
        "Speed reading", new Integer(20), new Boolean(true)}
        , {
        "Philip", "Milne",
        "Pool", new Integer(10), new Boolean(false)}
        , {
        "Mary", "Campione",
        "Snowboarding", new Integer(5), new Boolean(true)}
        , {
        "Alison", "Huml",
        "Rowing", new Integer(3), new Boolean(true)}
        , {
        "Kathy", "Walrath",
        "Knitting", new Integer(2), new Boolean(false)}
        , {
        "Sharon", "Zakhour",
        "Speed reading", new Integer(20), new Boolean(true)}
        , {
        "Philip", "Milne",
        "Pool", new Integer(10), new Boolean(false)}
        , {
        "Mary", "Campione",
        "Snowboarding", new Integer(5), new Boolean(true)}
        , {
        "Alison", "Huml",
        "Rowing", new Integer(3), new Boolean(true)}
        , {
        "Kathy", "Walrath",
        "Knitting", new Integer(2), new Boolean(false)}
        , {
        "Sharon", "Zakhour",
        "Speed reading", new Integer(20), new Boolean(true)}
        , {
        "Philip", "Milne",
        "Pool", new Integer(10), new Boolean(false)}
        , {
        "Mary", "Campione",
        "Snowboarding", new Integer(5), new Boolean(true)}
        , {
        "Alison", "Huml",
        "Rowing", new Integer(3), new Boolean(true)}
        , {
        "Kathy", "Walrath",
        "Knitting", new Integer(2), new Boolean(false)}
        , {
        "Sharon", "Zakhour",
        "Speed reading", new Integer(20), new Boolean(true)}
        , {
        "Philip", "Milne",
        "Pool", new Integer(10), new Boolean(false)}
        , {
        "Mary", "Campione",
        "Snowboarding", new Integer(5), new Boolean(true)}
        , {
        "Alison", "Huml",
        "Rowing", new Integer(3), new Boolean(true)}
        , {
        "Kathy", "Walrath",
        "Knitting", new Integer(2), new Boolean(false)}
        , {
        "Sharon", "Zakhour",
        "Speed reading", new Integer(20), new Boolean(true)}
        , {
        "Philip", "Milne",
        "Pool", new Integer(10), new Boolean(false)}
        , {
        "Mary", "Campione",
        "Snowboarding", new Integer(5), new Boolean(true)}
        , {
        "Alison", "Huml",
        "Rowing", new Integer(3), new Boolean(true)}
        , {
        "Kathy", "Walrath",
        "Knitting", new Integer(2), new Boolean(false)}
        , {
        "Sharon", "Zakhour",
        "Speed reading", new Integer(20), new Boolean(true)}
        , {
        "Philip", "Milne",
        "Pool", new Integer(10), new Boolean(false)}
        , {
        "Mary", "Campione",
        "Snowboarding", new Integer(5), new Boolean(true)}
        , {
        "Alison", "Huml",
        "Rowing", new Integer(3), new Boolean(true)}
        , {
        "Kathy", "Walrath",
        "Knitting", new Integer(2), new Boolean(false)}
        , {
        "Sharon", "Zakhour",
        "Speed reading", new Integer(20), new Boolean(true)}
        , {
        "Philip", "Milne",
        "Pool", new Integer(10), new Boolean(false)}
        , {
        "Mary", "Campione",
        "Snowboarding", new Integer(5), new Boolean(true)}
        , {
        "Alison", "Huml",
        "Rowing", new Integer(3), new Boolean(true)}
        , {
        "Kathy", "Walrath",
        "Knitting", new Integer(2), new Boolean(false)}
        , {
        "Sharon", "Zakhour",
        "Speed reading", new Integer(20), new Boolean(true)}
        , {
        "Philip", "Milne",
        "Pool", new Integer(10), new Boolean(false)}
        , {
        "Mary", "Campione",
        "Snowboarding", new Integer(5), new Boolean(true)}
        , {
        "Alison", "Huml",
        "Rowing", new Integer(3), new Boolean(true)}
        , {
        "Kathy", "Walrath",
        "Knitting", new Integer(2), new Boolean(false)}
        , {
        "Sharon", "Zakhour",
        "Speed reading", new Integer(20), new Boolean(true)}
        , {
        "Philip", "Milne",
        "Pool", new Integer(10), new Boolean(false)}
        , {
        "Mary", "Campione",
        "Snowboarding", new Integer(5), new Boolean(true)}
        , {
        "Alison", "Huml",
        "Rowing", new Integer(3), new Boolean(true)}
        , {
        "Kathy", "Walrath",
        "Knitting", new Integer(2), new Boolean(false)}
        , {
        "Sharon", "Zakhour",
        "Speed reading", new Integer(20), new Boolean(true)}
        , {
        "Philip", "Milne",
        "Pool", new Integer(10), new Boolean(false)}
        , {
        "Mary", "Campione",
        "Snowboarding", new Integer(5), new Boolean(true)}
        , {
        "Alison", "Huml",
        "Rowing", new Integer(3), new Boolean(true)}
        , {
        "Kathy", "Walrath",
        "Knitting", new Integer(2), new Boolean(false)}
        , {
        "Sharon", "Zakhour",
        "Speed reading", new Integer(20), new Boolean(true)}
        , {
        "Philip", "Milne",
        "Pool", new Integer(10), new Boolean(false)}
        , {
        "Mary", "Campione",
        "Snowboarding", new Integer(5), new Boolean(true)}
        , {
        "Alison", "Huml",
        "Rowing", new Integer(3), new Boolean(true)}
        , {
        "Kathy", "Walrath",
        "Knitting", new Integer(2), new Boolean(false)}
        , {
        "Sharon", "Zakhour",
        "Speed reading", new Integer(20), new Boolean(true)}
        , {
        "Philip", "Milne",
        "Pool", new Integer(10), new Boolean(false)}
        , {
        "Mary", "Campione",
        "Snowboarding", new Integer(5), new Boolean(true)}
        , {
        "Alison", "Huml",
        "Rowing", new Integer(3), new Boolean(true)}
        , {
        "Kathy", "Walrath",
        "Knitting", new Integer(2), new Boolean(false)}
        , {
        "Sharon", "Zakhour",
        "Speed reading", new Integer(20), new Boolean(true)}
        , {
        "Philip", "Milne",
        "Pool", new Integer(10), new Boolean(false)}
        , {
        "Mary", "Campione",
        "Snowboarding", new Integer(5), new Boolean(true)}
        , {
        "Alison", "Huml",
        "Rowing", new Integer(3), new Boolean(true)}
        , {
        "Kathy", "Walrath",
        "Knitting", new Integer(2), new Boolean(false)}
        , {
        "Sharon", "Zakhour",
        "Speed reading", new Integer(20), new Boolean(true)}
        , {
        "Philip", "Milne",
        "Pool", new Integer(10), new Boolean(false)}
        , {
        "Mary", "Campione",
        "Snowboarding", new Integer(5), new Boolean(true)}
        , {
        "Alison", "Huml",
        "Rowing", new Integer(3), new Boolean(true)}
        , {
        "Kathy", "Walrath",
        "Knitting", new Integer(2), new Boolean(false)}
        , {
        "Sharon", "Zakhour",
        "Speed reading", new Integer(20), new Boolean(true)}
        , {
        "Philip", "Milne",
        "Pool", new Integer(10), new Boolean(false)}
        , {
        "Mary", "Campione",
        "Snowboarding", new Integer(5), new Boolean(true)}
        , {
        "Alison", "Huml",
        "Rowing", new Integer(3), new Boolean(true)}
        , {
        "Kathy", "Walrath",
        "Knitting", new Integer(2), new Boolean(false)}
        , {
        "Sharon", "Zakhour",
        "Speed reading", new Integer(20), new Boolean(true)}
        , {
        "Philip", "Milne",
        "Pool", new Integer(10), new Boolean(false)}
        ,

    };

    table = new JTable(data, columnNames);

    JScrollPane scroll = new JScrollPane(table);
    c.add(print, BorderLayout.NORTH);
    c.add(scroll, BorderLayout.SOUTH);
    pack();
    setVisible(true);

  }

  public static void main(String[] args) {
    MainFrame mainFrame1 = new MainFrame();
  }

  /**
   * actionPerformed
   *
   * @param e ActionEvent
   */
  public void actionPerformed(ActionEvent e) {

    if (e.getActionCommand().equals("Print")) {
      printer = new AtDocumentPrinter();
      tablePrinter = new AtTablePrinter();
      genericPrinter = new AtGenericPrinter();
      addPageHeaderFooterListener(tablePrinter);
      tablePrinter.print(table, PageNoPainter.LOWER_CENTER, false);
      //  tablePrinter.print(table);
    }

  }

  private void addPageHeaderFooterListener(AtAbstractPrinter printerClass) {
    printerClass.setPageHeaderFooterListener(new PageHeaderFooterListener() {
      int pageCount = 0;
      public void setPageHeaderAndFooter(PageHeaderFooterRenderer r) {
        //System.out.println("setPageHeaderAndFooter()...totalPages=" + r.getTotalPageCount());
        AtHeaderFooterElement header
            = new AtHeaderFooterElement(null,
                                        new Font("Times New Roman", Font.BOLD,
                                                 20),
                                        Color.decode("#333366"),
                                        SystemColor.white,
                                        false,
                                        AtHeaderFooterElement.LEFT);
        AtHeaderFooterElement footer
            = new AtHeaderFooterElement("Page " + r.getPageNumber() + "/" +
                                        r.getTotalPageCount(),
                                        new Font("Arial", Font.PLAIN, 10),
                                        Color.decode("#000033"),
                                        SystemColor.white,
                                        false,
                                        AtHeaderFooterElement.CENTER);
        if (r.getPageNumber() == 1) {
          header.setStr("Employee Information - Manufacturing Division");
        }
        r.setHeader(header);
        r.setFooter(footer);
      }
    });

  }

}
