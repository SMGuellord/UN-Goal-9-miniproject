package uj.acsse.csc3a.miniproject.models;

/**
 * @author MG Sumba
 *
 */
public class DBConnection {
	
	
	
	/**
	 * Loading and registering Oracle JDBC driver class.
	 */
	public static void loadDriverClass() {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		} catch (ClassNotFoundException e) {
			System.err.println("Could not load or register MS Access JDBC driver.");
			e.printStackTrace();
		}
	}
}
