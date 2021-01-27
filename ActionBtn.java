import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


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