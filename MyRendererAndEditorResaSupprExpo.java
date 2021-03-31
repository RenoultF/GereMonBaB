import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.CellEditorListener;
import java.awt.Component;
import java.awt.event.*;
import java.util.EventObject;
import java.awt.*;


class MyRendererAndEditorResaSupprExpo extends AbstractAction implements TableCellRenderer, TableCellEditor 
{
  private JButton btn;
  private int row;
  private JTable table;

  public MyRendererAndEditorResaSupprExpo(JTable table) {
    btn = new JButton("Supprimer");
    btn.addActionListener(this);
    this.table = table;
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
      //On recupere l'idval_suppr qui est dans le tableau
      index = (int)FenetreUIExposant.tabReservation.getValueAt(row, 0);
      //supprimer de la liste des reservé
      Reservation resa = FenetreUI.getSystem().getListeReservation().get(index);
      //on supprime la ligne dans le tableau
      FenetreUIExposant.tabReservation.removeRow(row);
      //supprime de la liste des reservation
      FenetreUIExposant.reservationsTmp.remove(index);
      //actualise le tableau
      FenetreUIExposant.actualiserTabSupprResa(index);
      //ajoute la suppression dans le tableau des suppressions
      FenetreUI.getSystem().getListeReservationSuppr().addLast(resa);
    }
  }

}

