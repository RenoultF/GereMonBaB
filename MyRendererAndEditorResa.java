import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.CellEditorListener;
import java.awt.Component;
import java.awt.event.*;
import java.util.EventObject;
import java.awt.*;


class MyRendererAndEditorResa extends AbstractAction implements TableCellRenderer, TableCellEditor 
{
  private JButton btn;
  private int row;
  private JTable table;
  private String val_suppr;

  MyRendererAndEditorResa(JTable table, String val_suppr) {
    btn = new JButton(val_suppr);
    btn.addActionListener(this);
    this.table = table;
    this.val_suppr = val_suppr;
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
        if(val_suppr.equals("supprimer")){
            //on supprime la ligne dans le tableau
            FenetreUI.tabReservation.removeRow(row);
            //supprimer de la liste des reservé
            Reservation resa = FenetreUI.getSystem().getListeReservation().get(index);
            actualiserBtnSiBesoin(resa);
            FenetreUI.getSystem().getListeReservation().remove(resa);
            FenetreUI.actualiserTabSupprResa(resa);
        }
        else if(val_suppr.equals("valider")){
            //on supprime la ligne dans le tableau
            FenetreUI.tabReservation.removeRow(row);
            //supprimer de la liste des reservé
            Reservation resa = FenetreUI.getSystem().getListeReservation().get(index);
            //on actualise le bouton
            Emplacement empTmp = resa.getEmplacement();
            JButton btnTmp = FenetreUI.listBut.get(empTmp.getCoordonneeX()*FenetreUI.getSystem().getDimX() + empTmp.getCoordonneeY());
            actualiserBtn(btnTmp);
            //mise a jour de l'emplacement
            empTmp.setReservation("reserve");
            empTmp.setPaiement(resa.getMoyenPaiement());
            FenetreUI.actualiserTabValResa(resa);
        }
    }
  }

  public void actualiserBtn(JButton but){
    btn.setBackground(Color.RED);
    btn.setEnabled(false);
  }

  public void actualiserBtnSiBesoin(Reservation resa){
    for(Reservation resaTmp : FenetreUI.getSystem().getListeReservation()){
        if(resaTmp.getEmplacement().equals(resa)){
            return;
        }
    }
    JButton btnModif = FenetreUI.listBut.get(resa.getEmplacement().getCoordonneeX()*FenetreUI.getSystem().getDimX() + resa.getEmplacement().getCoordonneeY());
    btnModif.setBackground(Color.GREEN);
    resa.getEmplacement().setReservation("libre");
  }
}

