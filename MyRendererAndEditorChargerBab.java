import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.CellEditorListener;
import java.awt.Component;
import java.awt.event.*;
import java.util.EventObject;
import java.awt.*;


class MyRendererAndEditorChargerBab extends AbstractAction implements TableCellRenderer, TableCellEditor 
{
  private JButton btn;
  private int row;
  private JTable table;
  private String nom;

  public MyRendererAndEditorChargerBab(JTable table, String choix) {
  	this.nom = choix;
    switch(choix) {
    	case "Charger"	:
    		btn = new JButton("Charger");
    		break;
    	case "Supprimer":
    		btn = new JButton("Supprimer");
    		break;
    	case "Publier"	:
    		btn = new JButton("Publier");
    		break;
      case "Visiter" :
        btn = new JButton("Visiter");
        break;
    	default:
    		btn = new JButton("Error");
    }
    
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
		int index;
		if(e.getSource() == this.btn){
			if(nom.equals("Charger")) {
				//On recupere l'idType qui est dans le tableau
				index = (int)FenetreOrganisateur.tabMesBabs.getValueAt(row, 0);
				JDBC_BDD baseDeDonnees = new JDBC_BDD();
				baseDeDonnees.startJDBC();
				System.out.println("INDEX : " + index);
				BaB nvBaB = baseDeDonnees.chargerBab(index);
	    		new FenetreUI(nvBaB);
				
			}

      if(nom.equals("Visiter")) {
				//On recupere l'idType qui est dans le tableau
				index = (int)FenetreExposant.tabMesBabs.getValueAt(row, 0);
				JDBC_BDD baseDeDonnees = new JDBC_BDD();
				baseDeDonnees.startJDBC();
				System.out.println("INDEX : " + index);
				BaB nvBaB = baseDeDonnees.chargerBab(index);
	    	new FenetreUIExposant(nvBaB);
			}
		}
	}
}
