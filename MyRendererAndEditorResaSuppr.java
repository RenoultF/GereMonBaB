import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.CellEditorListener;
import java.awt.Component;
import java.awt.event.*;
import java.util.EventObject;
import java.awt.*;


class MyRendererAndEditorResaSuppr extends AbstractAction implements TableCellRenderer, TableCellEditor 
{
  private JButton btn;
  private int row;
  private JTable table;

  public MyRendererAndEditorResaSuppr(JTable table) {
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
      index = (int)FenetreUI.tabReservation.getValueAt(row, 0);
      //on supprime la ligne dans le tableau
      FenetreUI.tabReservation.removeRow(row);
      //supprimer de la liste des reservé
      Reservation resa = FenetreUI.getSystem().getListeReservation().get(index);
      //actualise le bouton
      actualiserBtnSiBesoin(resa);
      //supprime de la liste des reservation
      FenetreUI.getSystem().getListeReservation().remove(index);
      //actualise le tableau
      FenetreUI.actualiserTabSupprResa(index);
      //ajoute la suppression dans le tableau des suppressions
      //System.out.println(resa.getNom());
      FenetreUI.getSystem().getListeReservationSuppr().addLast(resa);
    }
  }

  public void actualiserBtn(JButton but){
    but.setBackground(Color.RED);
    but.setEnabled(false);
  }

  public void actualiserBtnSiBesoin(Reservation resa){
    for(Reservation resaTmp : FenetreUI.getSystem().getListeReservation()){
      if(resaTmp.getIdReservation()!=resa.getIdReservation()){
        if(resaTmp.getEmplacement().getIdType() == resa.getEmplacement().getIdType()){
          return;
        }
      }
    }
    JButton btnModif = FenetreUI.listBut.get(resa.getEmplacement().getCoordonneeX()*FenetreUI.getSystem().getDimX() + resa.getEmplacement().getCoordonneeY());
    btnModif.setBackground(Color.GREEN);
    resa.getEmplacement().setReservation("libre");
    FenetreUI.tabStands.setValueAt(resa.getEmplacement().getReservation(), resa.getEmplacement().getIdType(), 2);
  }
}

