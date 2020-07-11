/**
 * 
 */
package uj.acsse.csc3a.miniproject.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import uj.acsse.csc3a.miniproject.models.Country;
import uj.acsse.csc3a.miniproject.models.UndirectedGraph;




/**
 * @author SMG
 *
 */
@SuppressWarnings("serial")
public class GraphPanel extends JPanel {
	
	private UndirectedGraph<Country> countryGraph = null;

	public void paintComponent(Graphics g){
	super.paintComponent(g);
		Image mapAfrica = new ImageIcon("./doc/african-countries.jpg").getImage();
		g.drawImage(mapAfrica, 0, 0, this);
		g.setColor(Color.RED);
		//Position for Morocco
		g.fillOval(80, 25, 7, 7);
		g.setColor(Color.WHITE);
		g.drawLine(50, 60, 70, 60);
		g.setColor(Color.BLACK);
		g.drawString("Western", 20, 75);
		g.drawString("Sahara", 20, 85);
		g.setColor(Color.RED);
		//Position for Western Sahara
		g.fillOval(35, 75, 7, 7);
		//Position for South Soudan
		g.fillOval(307, 180, 7, 7);
		//Position for Algeria
		g.fillOval(150, 75, 7, 7);
		//Position for Tunisia
		g.fillOval(170, 25, 7, 7);
		//Position for Lybia
		g.fillOval(200, 75,7, 7);
		//Position for Egypt
		g.fillOval(280, 60, 7, 7);
		//Position for Mauritania
		g.fillOval(60, 95, 7, 7);
		//Position for Mali
		g.fillOval(100, 105, 7, 7);
		//Position for Niger
		g.fillOval(160, 110, 7, 7);
		//Position for Chad
		g.fillOval(230, 150, 7, 7);
		//Position for Sudan
		g.fillOval(310, 120, 7, 7);
		g.setColor(Color.WHITE);
		g.drawLine(260, 180, 331, 170);
		g.setColor(Color.BLACK);
		g.drawString("South", 300, 195);
		g.drawString("Sudan", 300, 205);
		g.setColor(Color.RED);
		//Position for South Soudan
		g.fillOval(307, 180, 7, 7);
		//Position for Eritrea
		g.fillOval(345, 128, 7, 7);
		//Position for Senegal
		g.fillOval(10, 135, 7, 7);
		//Position for Gambia
		g.fillOval(12, 150, 7, 7);
		//Position for Guinea-Bissau
		g.fillOval(15, 160, 7, 7);
		//Position for Guinea
		g.fillOval(25, 165, 7, 7);
		//Position for Sierra-Leone
		g.fillOval(35, 184, 7, 7);
		//Position for Liberia
		g.fillOval(55, 199, 7, 7);
		//Position for Cote d'ivoire
		g.fillOval(85, 184, 7, 7);
		//Position for Burkina Faso
		g.fillOval(107, 150, 7, 7);
		//Position for Ghana
		g.fillOval(107, 175, 7, 7);
		//Position for Togo
		g.fillOval(117, 179, 7, 7);
		//Position for Benin
		g.fillOval(129, 170, 7, 7);
		//Position for Nigeria
		g.fillOval(169, 160, 7, 7);
		//Position for Cameroon
		g.fillOval(189, 200, 7, 7);
		//Position for Central African Rep
		g.fillOval(247, 180, 7, 7);
		//Position for Ethiopia
		g.fillOval(347, 190, 7, 7);
		//Position for Djibouti
		g.fillOval(372, 163, 7, 7);
		//Position for Somalia
		g.fillOval(387, 210, 7, 7);
		//Position for Kenya
		g.fillOval(347, 240, 7, 7);
		//Position for Uganda
		g.fillOval(310, 225, 7, 7);
		//Position for DR.Congo
		g.fillOval(240, 258, 7, 7);
		//Position for Rep of Congo
		g.fillOval(210, 238, 7, 7);
		//Position for Equatorial Guinea
		g.fillOval(171, 223, 7, 7);
		//Position for Gabon
		g.fillOval(175, 243, 7, 7);
		//Position for Rwanda
		g.fillOval(300, 241, 7, 7);
		//Position for Burundi
		g.fillOval(298, 250, 7, 7);
		//Position for Tanzania
		g.fillOval(320, 265, 7, 7);
		//Position for Angola
		g.fillOval(225, 298, 7, 7);
		//Position for Namibia
		g.fillOval(215, 368, 7, 7);
		//Position for South Africa
		g.fillOval(235, 420, 7, 7);
		//Position for Botswana
		g.fillOval(255, 378, 7, 7);
		//Position for Lesotho
		g.fillOval(278, 416, 7, 7);
		//Position for Swaziland
		g.fillOval(303, 400, 7, 7);
		//Position for Zambia
		g.fillOval(278, 326, 7, 7);
		//Position for Zimbabwe
		g.fillOval(300, 363, 7, 7);
		//Position for Mozambique
		g.fillOval(348, 333, 7, 7);
		//Position for Malawi
		g.fillOval(322, 309, 7, 7);
		//Position for Comoros
		g.fillOval(372, 302, 7, 7);
		//Position for Seychelles
		g.fillOval(415, 269, 7, 7);
		//Position for Reunion
		g.fillOval(441, 348, 7, 7);
		//Position for Mauritus
		g.fillOval(433, 355, 7, 7);
		//Position for Madagascar
		g.fillOval(403, 357, 7, 7);
		
		if(countryGraph != null) {
			for(Country c : countryGraph.getAllVertices()) {
				for(Country n : countryGraph.getNeighbors(c)) {
					g.setColor(Color.BLUE);
					g.drawLine(c.getxPos()+3, c.getyPos()+3, n.getxPos()+3, n.getyPos()+3);//Dot size is 7, plus 3 just to adjust the position of the line.
				}
			}
		}
		
	}
	
	//Draw line to represent partnership between two countries.
	public void paintMap(UndirectedGraph<Country> countryGraph) {
		this.countryGraph = countryGraph;
		super.repaint();	
		
	}
}
