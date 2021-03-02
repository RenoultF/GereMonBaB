import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.CellEditorListener;
import java.awt.Component;
import java.awt.event.*;
import java.util.EventObject;
import java.awt.*;


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
    this.empCourant.afficher();
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
      if(!empCourant.getType().equals("Stand")){
        FenetreUI.tabAutres.removeRow(row);
      }
      else{
        FenetreUI.tabStands.removeRow(row);
      }
      //this.empCourant.afficheToi();
      System.out.println("Probleme ici 1");
      this.empCourant.afficher();
      System.out.println("indice ="+(this.empCourant.getCoordonneeX()*FenetreUI.dimX + this.empCourant.getCoordonneeY()));
      this.actualiserBtn(FenetreUI.listBut.get(this.empCourant.getCoordonneeX()*FenetreUI.dimX + this.empCourant.getCoordonneeY()));
      System.out.println("Probleme ici 2");
      empCourant.afficher();
      FenetreUI.getSystem().supprimerStand(empCourant.getIdType());
      System.out.println("Probleme ici 3");
      FenetreUI.actualiserTab(empCourant.getType());
      System.out.println("Probleme ici 4");
    }
  }

  public void actualiserBtn(JButton but){
    but.setEnabled(true);
    but.setBackground(Color.WHITE);
    but.setText("");
  }
}
