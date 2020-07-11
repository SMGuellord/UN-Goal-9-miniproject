package uj.acsse.csc3a.miniproject.models;
/**
 * @author MG Sumba
 *
 */
public class Country implements Comparable<Country>{

	private String countryISO_Code;
	private String countryName;
	private int population;
	private String region;
	private SinglyLinkedList<String> listNeighbors;
	private double internetPenetrationPercentage;
	private int xPos;
	private int yPos;
	private int internetUsersDec2000;
	private int internetUsersToDate;
	
	
	public Country() {}
	/**
	 * @param countryISO_Code
	 * @param countryName
	 * @param population
	 * @param region
	 * @param listNeighobours
	 */
	public Country(String countryISO_Code, String countryName, int population, String region,
			SinglyLinkedList<String> listNeighbors, int xPos, int yPos, int internetUsersDec2000, int internetUsersToDate) {
		super();
		this.countryISO_Code = countryISO_Code;
		this.countryName = countryName;
		this.population = population;
		this.region = region;
		this.listNeighbors = listNeighbors;
		this.xPos = xPos;
		this.yPos = yPos;
		this.internetUsersDec2000 = internetUsersDec2000;
		this.internetUsersToDate = internetUsersToDate;
	}
	
	public void copyCountry(Country other) {
		this.countryISO_Code = other.countryISO_Code;
		this.countryName = other.countryName;
		this.population = other.population;
		this.region = other.region;
		this.listNeighbors = other.listNeighbors;
	}
	/**
	 * @return the countryISO_Code
	 */
	public String getCountryISO_Code() {
		return countryISO_Code;
	}
	/**
	 * @param countryISO_Code the countryISO_Code to set
	 */
	public void setCountryISO_Code(String countryISO_Code) {
		this.countryISO_Code = countryISO_Code;
	}
	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}
	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	/**
	 * @return the population
	 */
	public int getPopulation() {
		return population;
	}
	/**
	 * @param population the population to set
	 */
	public void setPopulation(int population) {
		this.population = population;
	}
	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * @return the listNeighobours
	 */
	public SinglyLinkedList<String> getListNeighbors() {
		return listNeighbors;
	}
	/**
	 * @param listNeighobours the listNeighobours to set
	 */
	public void setListNeighbors(SinglyLinkedList<String> listNeighobours) {
		this.listNeighbors = listNeighobours;
	}
	
	public int getxPos() {
		return xPos;
	}
	public int getyPos() {
		return yPos;
	}
	/**
	 * @return the internetPenetrationPercentage
	 */
	public double getInternetPenetrationPercentage() {
		
		internetPenetrationPercentage = calculateInternetUserPercentange(internetUsersToDate) -
				calculateInternetUserPercentange(internetUsersDec2000);
		return internetPenetrationPercentage;
	}
	
	private double calculateInternetUserPercentange(int users) {
		return (double)users/population * 100;
	}
	
	/**
	 * @return the internetUsersDec2000
	 */
	public int getInternetUsersDec2000() {
		return internetUsersDec2000;
	}
	/**
	 * @param internetUsersDec2000 the internetUsersDec2000 to set
	 */
	public void setInternetUsersDec2000(int internetUsersDec2000) {
		this.internetUsersDec2000 = internetUsersDec2000;
	}
	/**
	 * @return the internetUsersToDate
	 */
	public int getInternetUsersToDate() {
		return internetUsersToDate;
	}
	/**
	 * @param internetUsersToDate the internetUsersToDate to set
	 */
	public void setInternetUsersToDate(int internetUsersToDate) {
		this.internetUsersToDate = internetUsersToDate;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format(" Country ISO Code: %s \n Country Name: %s \n Region: %s \n Population: %d \n"+
				" Internet Users Dec 2000: %d \n Internet Users to date: %d \n Internet Penetration Percentage %.2f %% \n\n Neighbors: %s \n",
				getCountryISO_Code(), getCountryName(),  getRegion(),getPopulation(), getInternetUsersDec2000(), 
				getInternetUsersToDate(), getInternetPenetrationPercentage(), displayNeighbors());
	}
	
	/**
	 * Display the list of neighbors
	 */
	public String displayNeighbors() {
		String neighbors = "";
		for(String n : listNeighbors) {
			neighbors += " "+n+"/";
		}
		
		return neighbors;
	}
	
	@Override
	public int compareTo(Country other) {
		
		return this.getCountryISO_Code().compareTo(other.countryISO_Code);
	}
}
