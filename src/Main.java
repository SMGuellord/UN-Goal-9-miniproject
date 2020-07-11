
import javax.swing.JFrame;

import uj.acsse.csc3a.miniproject.gui.GraphGUI;
/**
 * @author MG Sumba
 *
 */
public class Main {
	
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		GraphGUI graphGUI = new GraphGUI("UN SUSTAINABLE DEVELOPMENT GOAL 9");
		
		graphGUI.setSize(640, 460);
		graphGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		graphGUI.setLocationRelativeTo(null);
		graphGUI.setResizable(false);
		graphGUI.setVisible(true);
		
	}

}
