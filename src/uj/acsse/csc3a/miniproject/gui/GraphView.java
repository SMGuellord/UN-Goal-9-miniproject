/**
 * 
 */
package uj.acsse.csc3a.miniproject.gui;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uj.acsse.csc3a.miniproject.models.Country;
import uj.acsse.csc3a.miniproject.models.UndirectedGraph;

/**
 * @author MG Sumba
 *
 */
public class GraphView extends JFrame{

	private static final long serialVersionUID = 1L;
	
	
	private GraphPanel graphPanel = null;
	private Button btnRefresh;
	
	private UndirectedGraph<Country> countryGraph= null;
	
	public GraphView (String title, UndirectedGraph<Country> countryGraph) {
		graphPanel = new GraphPanel();
		this.setTitle(title);
		this.setLayout(new BorderLayout());
		this.countryGraph = countryGraph;
		
		btnRefresh = new Button("Refresh");
	
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				drawConnectionLine();				
			}
			
		});
		
		
		drawConnectionLine();
		
		add(graphPanel, BorderLayout.CENTER);
		add(btnRefresh, BorderLayout.SOUTH);
	}
	
	/**
	 * Redraw the map with lines representing partnership between countries.
	 */
	public void drawConnectionLine() {
		if(countryGraph != null) {
			graphPanel.paintMap(countryGraph);
		}else {
			JOptionPane.showMessageDialog(null, "The graph was not initialized because no region was selected! ", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}

}
