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
  private String type;

  MyRendererAndEditor(JTable table, String type) {
    btn = new JButton("Supprimer");
    btn.addActionListener(this);
    this.table = table;
    this.type = type;
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
    if(e.getSource() == this.btn){
      int index;
      Emplacement empCourant;
      if(type.equals("Stand")){
        //On recupere l'idType qui est dans le tableau
        index = (int)FenetreUI.tabStands.getValueAt(row, 0);
        //On va chercher l'emplacement qui correspond à l'index
        empCourant = FenetreUI.getSystem().getListeStand().get(index);
        //on supprime la ligne dans le tableau
        FenetreUI.tabStands.removeRow(row);
        //on suprrime le stand de la liste
        FenetreUI.getSystem().supprimerStand(empCourant.getIdType());
        //suppression du boutons de la liste stands
        FenetreUI.listButStands.remove(FenetreUI.listButStands.get(empCourant.getIdType()));
      }
      else{
        //On recupere l'idType qui est dans le tableau
        index = (int)FenetreUI.tabAutres.getValueAt(row, 0);
        //On va chercher l'emplacement qui correspond à l'index
        empCourant = FenetreUI.getSystem().getListeAutre().get(index);
        //on supprime la ligne dans le tableau
        FenetreUI.tabAutres.removeRow(row);
        //on suprrime le stand de la liste
        FenetreUI.getSystem().supprimerAutre(empCourant.getIdType());
        //suppression du boutons de la liste autres
        FenetreUI.listButAutres.remove(FenetreUI.listButAutres.get(empCourant.getIdType()));
      }
      //Permet d'actualiseer le bouton qui correspond à l'emplacement
      this.actualiserBtn(FenetreUI.listBut.get(empCourant.getCoordonneeX()*FenetreUI.dimX + empCourant.getCoordonneeY()));
      //on actualise les données dans le tableaux
      FenetreUI.actualiserTab(empCourant.getType(),index);
    }
  }

  public void actualiserBtn(JButton but){
    but.setEnabled(true);
    but.setBackground(Color.WHITE);
    but.setText("");
  }
}
