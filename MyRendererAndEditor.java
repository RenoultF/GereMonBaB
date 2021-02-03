import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.CellEditorListener;
import java.awt.Component;
import java.awt.event.*;
import java.util.EventObject;


class MyRendererAndEditor extends AbstractAction implements TableCellRenderer, TableCellEditor 
{
  private JButton btn;
  private int row;
  private JTable table;
  private Emplacement empCourant;

  MyRendererAndEditor(JTable table, Emplacement emp) {
    btn = new JButton("Supprimer");
    btn.addActionListener(this);
    this.table = table;
    this.empCourant = emp;
  }
  
  @Override
  public Component getTableCellRendererComponent(JTable table, Object 
  value, boolean isSelected, boolean hasFocus, int row, int column) 
  {
    return btn;
  }
  @Override
  public Component getTableCellEditorComponent(JTable table, Object 
  value, boolean isSelected, int row, int column) 
  {
    this.row = row;
    return btn;
  }
  @Override
  public Object getCellEditorValue() { return true; }
  @Override
  public boolean isCellEditable(EventObject anEvent) { return true; }
  @Override
  public boolean shouldSelectCell(EventObject anEvent) { return true; }
  @Override
  public boolean stopCellEditing() { return true; }
  @Override
  public void cancelCellEditing() {}
  @Override
  public void addCellEditorListener(CellEditorListener l) {}
  @Override
  public void removeCellEditorListener(CellEditorListener l) {}

  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == btn){
      if(empCourant.getType() != "STAND"){
        System.out.println("avant remove deco");
        FenetreUI.tabDecos.removeRow(row);
        System.out.println("apres remove deco");
      }
      else{
        System.out.println("avant remove Stand");
        FenetreUI.tabStands.removeRow(row);
        System.out.println("apres remove stand");
      }
      System.out.println("Ici 1");
      FenetreUI.emplacements.supprimerEmplacement(empCourant);
      System.out.println("Ici 2");
      FenetreUI.actualiserTab(empCourant.getType());
      System.out.println("Ici 3");
    }
  }
}