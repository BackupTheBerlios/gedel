package ecole;

import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import javax.swing.JTable;
import java.awt.Color;

public class CustomRenderer extends DefaultTableCellRenderer{
  public Component getTableCellRendererComponent(
               JTable table,
               java.lang.Object value,
               boolean isSelected,
               boolean hasFocus,
               int row, int column) {
      if( !isSelected ) {
         Color c = table.getBackground();
         if( (row%2)==0 &&
                 c.getRed()>10 && c.getGreen()>10 && c.getBlue()>10 )
            setBackground(new Color( c.getRed()-10,
                                     c.getGreen()-10,
                                     c.getBlue()-10));
         else
            setBackground(c);
      }
      return super.getTableCellRendererComponent( table,
                                value,isSelected,hasFocus,row,column);
   }


}