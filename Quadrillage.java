import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;

class Cst {
	// Dimension de la grille
	public static final int NB_LIGNE = 30;
	public static final int NB_COL = 100;
}

public class Quadrillage {
	static LinkedList<JButton> listBut = new LinkedList<>();

	public static void main(String [] args) {
		JFrame frame = new JFrame("Quadrillage");
		JPanel pan = new JPanel(new GridLayout(Cst.NB_LIGNE, Cst.NB_COL));
		int ind = 0; // Indice du bouton dans la liste
 
		for(int i = 0; i < Cst.NB_LIGNE; i++)
			for(int j = 0; j < Cst.NB_COL; j++) {
				JButton but = new JButton(new ActionBtn(i, j, ind));
				ind += 1;
				listBut.add(but);
				pan.add(but);
			}
 
		// ***** Fenêtre *****
	    frame.getContentPane().add(pan);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 500);
	}
}

class ActionBtn extends AbstractAction  {
	private int coord_x;
	private int coord_y;
	private int ind;
	private boolean clic = false;

	public ActionBtn(int x, int y, int i) {
		coord_x = x;
		coord_y = y;
		ind = i;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton but = Quadrillage.listBut.get(ind);

		// ***** Pas sélectionné *****
		if(clic == false) {
			clic = true;
			but.setBackground(Color.BLACK);
		}

		// ***** Deja sélectionné *****
		else {
			clic = false;
			but.setBackground(Color.GRAY); // Couleur par défaut
		}

		System.out.println(ind + "=" + coord_x + ":" + coord_y + "(" + clic + ")"); // Pour dev....
	}
}