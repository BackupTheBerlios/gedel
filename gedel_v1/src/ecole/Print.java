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

public class Print {

  JTable table;
  JButton print;
  private JScrollPane scrollPane = null;
  protected AtDocumentPrinter printer;
  protected AtPdfPrinter pdfPrinter;
  protected AtTablePrinter tablePrinter;
  protected AtGenericPrinter genericPrinter;

  public Print(Object toPrint) {
    SmartJPrint.setLicenseKey("AJET-LGFH-K68E-C30B");
    table = (JTable)toPrint;
         printer = new AtDocumentPrinter();
      tablePrinter = new AtTablePrinter();
      genericPrinter = new AtGenericPrinter();
      addPageHeaderFooterListener(tablePrinter);
      tablePrinter.print(table, PageNoPainter.LOWER_CENTER, false);
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
