import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;

class Cst {
	// Dimension de la grille
	public static final int NB_LIGNE = 30;
	public static final int NB_COL = 100;
}

public class Quadrillage {
	public static void main(String [] args) {
		JFrame frame = new JFrame("Quadrillage");
		JPanel pan = new JPanel(new GridLayout(Cst.NB_LIGNE, Cst.NB_COL));
 
		for(int i = 0; i < Cst.NB_LIGNE; i++)
			for(int j = 0; j < Cst.NB_COL; j++)
				pan.add(new JButton(new ActionBtn(i, j)));
 
		// ***** Fenêtre *****
	    frame.getContentPane().add(pan);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 500);
	}
}

class ActionBtn extends AbstractAction {
	private int coord_x;
	private int coord_y;

	public ActionBtn(int x, int y) {
		coord_x = x;
		coord_y = y;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(coord_x + ":" + coord_y); // Pour mise au point
	}
}