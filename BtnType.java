import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;

class BtnType extends AbstractAction  {
	private int coord_x;
	private int coord_y;
	private boolean clic = false;

	public ActionBtn(int x, int y, int i) {
		coord_x = x;
		coord_y = y;
		ind = i;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnTypeStand){
			this.type = "STAND";
			System.out.println("on passe au type :" + this.type);
		}
		else if(e.getSource() == btnTypeBuvette){
			this.type = "Buvette";
			System.out.println("on passe au type :" + this.type);
		}
		else if(e.getSource() == btnTypeBoulangerie){
			this.type = "Boulangerie";
			System.out.println("on passe au type :" + this.type);
		}
	}
}