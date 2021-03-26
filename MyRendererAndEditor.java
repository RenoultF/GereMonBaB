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
        Stand empCourantStand = (Stand) empCourant;
        //on supprime la ligne dans le tableau
        FenetreUI.tabStands.removeRow(row);
        //on ajoute le stand au emplacement supprimé de la session
        FenetreUI.getSystem().getListeStandSuppr().add(empCourantStand);
        System.out.println(FenetreUI.getSystem().getListeStandSuppr());
        //on suprrime le stand de la liste
        FenetreUI.getSystem().supprimerStand(empCourant.getIdType());
        //suppression du boutons de la liste stands
        FenetreUI.listButStands.remove(FenetreUI.listButStands.get(empCourant.getIdType()));
        //Permet d'actualiseer le bouton qui correspond à l'emplacement
        this.actualiserBtn(FenetreUI.listBut.get(empCourant.getCoordonneeX()*FenetreUI.dimX + empCourant.getCoordonneeY()),empCourant.getType());
      }
      else{
        //On recupere l'idType qui est dans le tableau
        index = (int)FenetreUI.tabAutres.getValueAt(row, 0);
        //On va chercher l'emplacement qui correspond à l'index
        empCourant = FenetreUI.getSystem().getListeAutre().get(index);
        int indBase = empCourant.getCoordonneeX()*FenetreUI.dimX + empCourant.getCoordonneeY();
        Autre empCourantAutre = (Autre) empCourant;
        //on supprime la ligne dans le tableau
        FenetreUI.tabAutres.removeRow(row);
        //on ajoute l'emplacement autre au emplacement supprimé de la session
        FenetreUI.getSystem().getListeAutreSuppr().add(empCourantAutre);
        System.out.println(FenetreUI.getSystem().getListeAutreSuppr());
        //on suprrime le stand de la liste
        FenetreUI.getSystem().supprimerAutre(empCourantAutre.getIdType());
        //suppression du boutons de la liste autres
        FenetreUI.listButAutres.remove(FenetreUI.listButAutres.get(empCourantAutre.getIdType()));
        for(int i = 0;i<empCourantAutre.getLargeur();i++){
          for(int j = indBase;j<indBase+empCourantAutre.getLongueur();j++){
            actualiserBtn(FenetreUI.listBut.get(i * FenetreUI.getSystem().getDimX() + j),empCourant.getType());
          }
        }
      }
      //on actualise les données dans le tableaux
      FenetreUI.actualiserTab(empCourant.getType(),index);
    }
  }

  public void actualiserBtn(JButton but, String typeString){
    //On récupère les indices voulues
    int indBase;
    int indexBut = FenetreUI.listBut.indexOf(but);
    //on le met de base à vide
    but.setEnabled(true);
    but.setBackground(Color.WHITE);
    but.setText("");
    if(!typeString.equals("Stand")){
      System.out.println("Pas la");
      //on regarde parmis tous les butous pris si est est était déjà pris
      for(Emplacement e : FenetreUI.getSystem().getListeAutre()){
        Autre e1 = (Autre) e;
        indBase = e1.getCoordonneeX()*FenetreUI.getSystem().getDimX() + e1.getCoordonneeY();
        for(int i = 0;i<e1.getLargeur();i++){
          for(int j = indBase;j<indBase+e1.getLongueur();j++){
            if(i * FenetreUI.getSystem().getDimX() + j == indexBut){
              //on est deja pris donc on met le bouton à jour
              but.setEnabled(false);
              but.setBackground(Color.BLACK);
              but.setText(String.valueOf(e1.getIdType()));
            }
          }
        }
      }
      for(Emplacement e : FenetreUI.getSystem().getListeStand()){
        if(e.getCoordonneeX() * FenetreUI.getSystem().getDimX() + e.getCoordonneeY() == indexBut){
          //on est deja pris donc on met le bouton à jour
          but.setEnabled(false);
          but.setBackground(Color.GREEN);
          but.setText(String.valueOf(e.getIdType()));
        }
      } 
    }
  } 
}
