import javax.swing.AbstractAction;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class TabQuadrillage extends DefaultTableModel{
    Object donnees[][];
    String titres[];
    
    public TabQuadrillage(Object donnees[][], String titres[]) { 
        this.donnees = donnees; 
        this.titres = titres; 
    }
    
}

    

