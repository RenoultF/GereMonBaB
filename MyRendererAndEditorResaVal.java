import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.CellEditorListener;
import java.awt.Component;
import java.awt.event.*;
import java.util.EventObject;
import java.awt.*;


class MyRendererAndEditorResaVal extends AbstractAction implements TableCellRenderer, TableCellEditor 
{
  private JButton btn;
  private int row;
  private JTable table;
  private String val_suppr;

  public MyRendererAndEditorResaVal(JTable table) {
    btn = new JButton("Valider");
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
      //System.out.println("ICI 1");
      index = (int)FenetreUI.tabReservation.getValueAt(row, 0);
      //on supprime la ligne dans le tableau
      //FenetreUI.tabReservation.removeRow(row);
      //supprimer de la liste des reservé
      Reservation resa = FenetreUI.getSystem().getListeReservation().get(index);
      //on actualise le bouton
      Emplacement empTmp = resa.getEmplacement();
      JButton btnTmp = FenetreUI.listBut.get(empTmp.getCoordonneeX()*FenetreUI.getSystem().getDimX() + empTmp.getCoordonneeY());
      actualiserBtn(btnTmp);
      //mise a jour de l'emplacement
      empTmp.setReservation("reserve");
      empTmp.setPaiement(resa.getMoyenPaiement());
      //mise a jour status de reservation et paiement
      FenetreUI.tabStands.setValueAt(empTmp.getReservation(), empTmp.getIdType(), 2);
      FenetreUI.tabStands.setValueAt(resa.getMoyenPaiement(), empTmp.getIdType(), 3);
      String nvProprietaire = ""+resa.getNom()+" "+resa.getPrenom();
      empTmp.setProprietaire(nvProprietaire);

      //supprime de la liste des reservation
      //FenetreUI.getSystem().getListeReservation().remove(index);

      //actualise le tableau
      FenetreUI.actualiserTabValResa(resa,index);
    }
  }

  public void actualiserBtn(JButton but){
    but.setBackground(Color.RED);
    but.setEnabled(false);
  }
}

