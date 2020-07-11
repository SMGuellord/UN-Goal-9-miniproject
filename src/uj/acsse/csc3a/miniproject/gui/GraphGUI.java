package uj.acsse.csc3a.miniproject.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import uj.acsse.csc3a.miniproject.models.Country;
import uj.acsse.csc3a.miniproject.models.DBConnection;
import uj.acsse.csc3a.miniproject.models.SinglyLinkedList;
import uj.acsse.csc3a.miniproject.models.UndirectedGraph;

/**
 * @author MG Sumba
 *
 */

public class GraphGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	
	
	private Connection conn = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private String region="";
	private UndirectedGraph<Country> countryGraph;
	private SinglyLinkedList<Country> countries;
	private boolean isDisplayingInsight = false;
	
	private JButton btnNorthAfrica;
	private JButton btnSouthAfrica;
	private JButton btnCentralAfrica;
	private JButton btnEastAfrica;
	private JButton btnWestAfrica;
	private JButton btnAfrica;
	
	private JButton btnDetails;
	private JButton btnPartners;
	private JButton btnEndPartnership;
	private JButton btnVisualRep;
	
	
	private JLabel lblRegion;
	
	private JTextArea txtAreaCountryList;
	private JScrollPane scPane;
	
	/**
	 * No args constructor.
	 */
	public GraphGUI() {
		this.setTitle("No Title");
	}
	
	/**
	 * @param title
	 */
	
	public GraphGUI(String title) {
		this.setTitle(title);
		
		btnNorthAfrica = new JButton("North Africa");
		btnSouthAfrica = new JButton("South Africa");
		btnCentralAfrica = new JButton("Central Africa");
		btnEastAfrica = new JButton("East Africa");
		btnWestAfrica = new JButton ("West Africa");
		btnAfrica = new JButton ("Africa");
		
		btnDetails = new JButton("Insight");
		btnPartners = new JButton("Start Partnership");
		btnEndPartnership = new JButton("End Partnership");
		btnVisualRep = new JButton("View Graph");
		
		lblRegion = new JLabel();
		lblRegion.setText("Regional Grouping");
		
		txtAreaCountryList = new JTextArea();
		scPane = new JScrollPane(txtAreaCountryList);
		
		
		
		//Add actionListener to button btnNorthAfrica.
		btnNorthAfrica.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtAreaCountryList.setText("");
				region = "North Africa";
				constructGraph(region);
				
			}
			
		});
		
		//Add actionListener to button btnSouthAfrica.
		btnSouthAfrica.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				txtAreaCountryList.setText("");
				region = "South Africa";
				constructGraph(region);
				
			}
			
		});
		
		//Add actionListener to button centralAfrica.
		btnCentralAfrica.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				txtAreaCountryList.setText("");
				region = "Central Africa";
				constructGraph( region);
				
			}
			
		});
		
		//Add actionListener to button btnEastAfrica.
		btnEastAfrica.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				txtAreaCountryList.setText("");
				region = "East Africa";
				constructGraph( region);
				
			}
			
		});
		
		//Add actionListener to button btnWestAfrica.
		btnWestAfrica.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				txtAreaCountryList.setText("");
				region = "West Africa";
				constructGraph(region);
				
			}
			
		});
		
		//Add actionListener to button btnAfrica.
		btnAfrica.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				txtAreaCountryList.setText("");
				region = "Africa";
				constructGraph(region);
				
			}
			
		});
		
		
		//Add actionListener to button btnDetails.
		btnDetails.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(countryGraph == null) {
					JOptionPane.showMessageDialog(null, "No region selected!", "Warning", JOptionPane.WARNING_MESSAGE);
				}else {
					if(!isDisplayingInsight) {
						String cIsoCode = JOptionPane.showInputDialog("Enter Country's ISO CODE").toUpperCase();
						Country targetCountry = null;
						for(Country c : countryGraph.getAllVertices()) {
							String cCode = c.getCountryISO_Code();
							if(cCode.equals(cIsoCode)) {
								targetCountry = c;
							}
						}
						
						if(targetCountry.getCountryISO_Code().equals(cIsoCode)) {
							txtAreaCountryList.setText("");
							txtAreaCountryList.append(targetCountry.toString());
							txtAreaCountryList.append("\n");
							txtAreaCountryList.append("Partners: ");
							//Display list of partners for a specific country.
							for(Country n : countryGraph.getNeighbors(targetCountry)) {
								txtAreaCountryList.append("\t"+n.getCountryName()+"("+n.getCountryISO_Code()+") -"+n.getRegion()+"\n");
							}
							txtAreaCountryList.append("\n");
							txtAreaCountryList.append("Potential Partners: \n");
							for(Country n : countryGraph.getNeighbors(targetCountry)) {
								//Display list of potential partners for a specific country(Partners of partner).
								for(Country p : countryGraph.getNeighbors(n)) {
									//Exclude current country and its partners.
									if(p.compareTo(targetCountry) != 0 ) {
										if(!countryGraph.isAdjacent(p, targetCountry)) {
											txtAreaCountryList.append("\t"+p.getCountryName()+"("+p.getCountryISO_Code()+") -"+p.getRegion()+"\n");
										}
									}
								}
							}
							txtAreaCountryList.append("\n\n");
						}else {
							JOptionPane.showMessageDialog(null, cIsoCode+" was not found in the current region.", "Search result", JOptionPane.INFORMATION_MESSAGE);
						}
						
						isDisplayingInsight = true;
						btnDetails.setText("Go back");
						btnPartners.setEnabled(false);
						btnEndPartnership.setEnabled(false);
					}else {
						displayVertices(region);
						isDisplayingInsight = false;
						btnDetails.setText("Insight");
						btnPartners.setEnabled(true);
						btnEndPartnership.setEnabled(true);
						
					}
				}
	
			}
			
		});
		
		//Add actionListener to button btnPartners.
		btnPartners.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(countryGraph != null) {
					String c1 = JOptionPane.showInputDialog(" 1st Country ISO CODE").toUpperCase();
					String c2 = JOptionPane.showInputDialog(" 2st Country ISO CODE").toUpperCase();
					
					if(c1.equals(c2)) {
						JOptionPane.showMessageDialog(null, "A country cannot be partner to itself!", "Warning", JOptionPane.WARNING_MESSAGE);
					}else {
						startPartnership(c1, c2,region);
					}	
				}else {
					JOptionPane.showMessageDialog(null, "You must first select a regional grouping before you procede!", "Warning Message",
							JOptionPane.WARNING_MESSAGE);
				}
			}
			
		});
		
		btnEndPartnership.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(countryGraph != null) {
					String c1 = JOptionPane.showInputDialog(" 1st Country ISO CODE").toUpperCase();
					String c2 = JOptionPane.showInputDialog(" 2st Country ISO CODE").toUpperCase();
					
					if(c1.equals(c2)) {
						JOptionPane.showMessageDialog(null, "A country cannot be partner to itself!", "Warning", JOptionPane.WARNING_MESSAGE);
					}else {
						endPartnership(c1, c2,region);
					}	
				}else {
					JOptionPane.showMessageDialog(null, "You must first select a regional grouping before you procede!", "Warning Message",
							JOptionPane.WARNING_MESSAGE);
				}				
			}
			
		});
		
		//Add actionListener to button btnVisualRep.
		btnVisualRep.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				GraphView graphView = new GraphView("Showing Partnerships for African Countries", countryGraph);
	
				graphView.setSize(500, 510);
				graphView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				graphView.setLocationRelativeTo(null);
				graphView.setResizable(false);
				
				//Add windowLister to graphView Frame.
				graphView.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						new Thread() {
							public void run() {
								//Disable btnVisualRep after it has been clicked 
								btnVisualRep.setEnabled(true);
							}
						}.start();
						
					}
				});
				//Enable btnVisualRep after the Map frame has been closed.
				btnVisualRep.setEnabled(false);
				graphView.setVisible(true);
				
			}
			
		});
		
		
		//Add actionListner to txtAreaCountryList.
		
		
		
		//Instantiate top Panel.
		JPanel pnlTop = new JPanel();
		pnlTop.setLayout(new BorderLayout());
		pnlTop.setSize(600, 80);
		pnlTop.setBorder(new EmptyBorder(10,10,10,10));
		pnlTop.setBackground(Color.BLUE);
		
		JLabel lblTitle = new JLabel();
		lblTitle.setText("Universal Access to Information and Telecommunication Technology");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setAlignmentX(CENTER_ALIGNMENT);
		
		pnlTop.add(lblTitle, BorderLayout.CENTER);
		
		//Instantiate a West Panel.
		JPanel pnlWest = new JPanel();
		
		//Set Layout of pnlWest which is GridLayout with 7 rows and 1 column.
		pnlWest.setLayout(new GridLayout(7,1));
		pnlWest.setBorder(new EmptyBorder(10,10,10,10));
		
		//Add components to pnlWest
		pnlWest.add(lblRegion);
		pnlWest.add(btnNorthAfrica);
		pnlWest.add(btnSouthAfrica);
		pnlWest.add(btnCentralAfrica);
		pnlWest.add(btnEastAfrica);
		pnlWest.add(btnWestAfrica);
		pnlWest.add(btnAfrica);
		
		//Instantiate East Panel
		JPanel pnlEast = new JPanel();
		
		pnlEast.setLayout(new GridLayout(1,1));
		txtAreaCountryList.setEditable(false);
		txtAreaCountryList.setSize(240, 320);
		txtAreaCountryList.setMargin(new Insets(10,10,10,10));
		pnlEast.add(scPane);
		pnlEast.setBorder(new EmptyBorder(10,10,0,10));
		
		//Instantiate Wrapper panel.
		JPanel pnlWrapper = new JPanel();
		
		pnlWrapper.setLayout(new BorderLayout());
		
		pnlWest.setSize(100,320);
		pnlWrapper.add(pnlWest, BorderLayout.WEST);
		pnlWrapper.add(pnlEast);
		
		//Instantiate bottom panel.
		JPanel pnlBottom = new JPanel();
		
		pnlBottom.setLayout(new GridLayout(1, 4));
		pnlBottom.setBorder(new EmptyBorder(10,10,10,10));
		
		//AddComponent to pnlBottom.
		pnlBottom.add(btnDetails);
		pnlBottom.add(btnPartners);
		pnlBottom.add(btnEndPartnership);
		pnlBottom.add(btnVisualRep);
		
		
		//Set Layout for this current JFrame.
		this.setLayout(new BorderLayout());
		
		
		//Add pnlWrapper and pnlBottom to the frame.
		add(pnlTop, BorderLayout.NORTH);
		add(pnlWrapper, BorderLayout.CENTER);
		add(pnlBottom, BorderLayout.SOUTH);
	}
	
	
	/**
	 * Read country details from database.
	 * @param dbName database name
	 * @return result arrayList of countries.
	 */
	private SinglyLinkedList<Country> readFromDB(String dbName, String selectedRegion) {
		
		//load driver class.
		DBConnection.loadDriverClass();
		
		SinglyLinkedList<Country> countries = null;
	
		//database.
		File db = new File("./doc/africanCountries.accdb");
		
		//ConnectionString.
		String connectionString = "jdbc:ucanaccess://" +db.getAbsolutePath();
		try {
			countries = new SinglyLinkedList<Country>();
			//connect to the database.
			conn = DriverManager.getConnection(connectionString);
			statement = conn.createStatement();
			if(selectedRegion.equals("Africa")) {
				resultSet = statement.executeQuery("SELECT * FROM COUNTRIES");
			} else {
				resultSet = statement.executeQuery("SELECT * FROM COUNTRIES WHERE C_REGION = '"+selectedRegion+"'");
			}
			
			while(resultSet.next()) {
				String country_code = resultSet.getString(1);
				String country_name = resultSet.getString(2);
				int population = resultSet.getInt(3);
				String region = resultSet.getString(4);
				String neighbors = resultSet.getString(5);
				String[] neighborsArray = null;
				SinglyLinkedList<String> neighborsList = new SinglyLinkedList<>();
				int xPos = resultSet.getInt(6);
				int yPos = resultSet.getInt(7);
				int internetUsersDec2000 = resultSet.getInt(8);
				int internetUsersToDate = resultSet.getInt(9);
				if(neighbors != null) {
					neighborsArray = neighbors.split("/");
					for(String n: neighborsArray) {
						neighborsList.insert(n);
					}
					
				}
				
				Country country = new Country(country_code, country_name, population, region, neighborsList, xPos, yPos, 
						internetUsersDec2000, internetUsersToDate );
				countries.insert(country);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				
				try {
					resultSet.close();
					statement.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return countries;
	}
	

	/**
	 * Populate the graph.
	 * @param region
	 */
	private void constructGraph(String region) {
		
		countryGraph = new UndirectedGraph<Country>(); //Instantiate an undirected graph.
		
		countries = new SinglyLinkedList<Country>();
		
		countries = readFromDB("africanCountries.accdb", region); //Read data from the database.
		
		//Add Vertices to the graph
		//Loop through country list.
		for(Country c : countries) {			
			countryGraph.addVertex(c);	
		}
	
		displayVertices(region);
		
	}
	
	//Display the content of the graph.
	private void displayVertices(String region) {
		txtAreaCountryList.setText("");
		//Display all vertices to the textArea
		txtAreaCountryList.append("\t Current Region: "+region+"\n\n");
		for(Country c : countryGraph.getAllVertices()) {
			
			txtAreaCountryList.append("Country: "+c.getCountryName()+"("+c.getCountryISO_Code()+")\n");
			txtAreaCountryList.append("Neighbors: \n");
			//Display list on neighbors of a specific country.
			for(String sC : c.getListNeighbors()) {
				txtAreaCountryList.append("\t"+sC+"\n");
			}
			txtAreaCountryList.append("\n");
			
			txtAreaCountryList.append("Partners: ");
			//Display list of partners for a specific country.
			for(Country n : countryGraph.getNeighbors(c)) {
				txtAreaCountryList.append("\t"+n.getCountryName()+"("+n.getCountryISO_Code()+") -"+n.getRegion()+"\n");
			}
			txtAreaCountryList.append("\n\n");
		}
	}
	
	/**
	 * Start partnership between two countries
	 * @param c1 first country
	 * @param c2 second country
	 * @param region
	 */
	private void startPartnership(String c1, String c2, String region) {
		 managePartnerShip(c1, c2,"addEdge", region);
	}
	
	
	/**
	 * End partnership between two countries
	 * @param c1 first country
	 * @param c2 second country
	 * @param region
	 */
	private void endPartnership(String c1, String c2, String region) {
		
		 managePartnerShip(c1, c2,"removeEdge", region);
		
	}
	
	/**
	 * Helper method to start or end partnership between two countries.
	 * @param c1
	 * @param c2
	 * @param action
	 * @param region
	 */
	private void managePartnerShip(String c1, String c2,String action, String region) {
		Country firstCountry = null;
		Country secondCountry = null;
		for(Country c : countries) {
			if(c.getCountryISO_Code().equals(c1)) {
				firstCountry = c;
			}else if(c.getCountryISO_Code().equals(c2)) {
				secondCountry = c;
			}
		}
		
		if(firstCountry == null) {
			JOptionPane.showMessageDialog(null, c1+" was not found in the current region! \n Make sure you enter the correct ISO Code.", 
													"Result", JOptionPane.INFORMATION_MESSAGE);
		}else if(secondCountry == null) {
			JOptionPane.showMessageDialog(null, c2+" was not found in the current region! \n Make sure you enter the correct ISO Code.", 
													"Result", JOptionPane.INFORMATION_MESSAGE);
		}else {
			switch(action) {
			case "addEdge":
			{
				if(!countryGraph.isAdjacent(firstCountry, secondCountry)) {
					countryGraph.addEdge(firstCountry, secondCountry);
					JOptionPane.showMessageDialog(null, firstCountry.getCountryName()+"("+firstCountry.getCountryISO_Code()+") and "+
					secondCountry.getCountryName()+"("+secondCountry.getCountryISO_Code()+") are now partners!", "Confirmation Message",
													JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, firstCountry.getCountryName()+"("+firstCountry.getCountryISO_Code()+") and "+
					secondCountry.getCountryName()+"("+secondCountry.getCountryISO_Code()+") are already partners!", "Warning",
													JOptionPane.WARNING_MESSAGE);
				}
			}
				break;
			case "removeEdge":
			{
				int result =JOptionPane.showConfirmDialog(null, "Do you really want to end partnership between "+firstCountry.getCountryName()+
						" \n and "+secondCountry.getCountryName()+"?");
				switch(result) {
				
				case JOptionPane.YES_OPTION:
				{
					if(countryGraph.isAdjacent(firstCountry, secondCountry)) {
						countryGraph.removeEdge(firstCountry, secondCountry);
						JOptionPane.showMessageDialog(null, firstCountry.getCountryName()+"("+firstCountry.getCountryISO_Code()+") and "+
						secondCountry.getCountryName()+"("+secondCountry.getCountryISO_Code()+") are no longer partners!", "Confirmation Message",
															JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, firstCountry.getCountryName()+"("+firstCountry.getCountryISO_Code()+") and "+
						secondCountry.getCountryName()+"("+secondCountry.getCountryISO_Code()+") are not in a partnership!", "Warning Message",
																	JOptionPane.WARNING_MESSAGE);
					}
				}
					break;
				case JOptionPane.NO_OPTION:
					
					break;
				case JOptionPane.CANCEL_OPTION:
				
					break;
				
				}
			}
				break;
			}
			
		}
		displayVertices(region);
	}
}
