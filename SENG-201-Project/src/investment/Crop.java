package investment;

public class Crop {
	private String species;
	private double purchasePrice;
	private double sellingPrice;
	private int maturityTime;
	private double health;
	private int dayPlanted;
	
	public Crop(String species, double purchasePrice, double sellingPrice, int maturityTime, double health, int dayPlanted, int growthBonus) {
		this.species = species;
		this.purchasePrice = purchasePrice;
		this.sellingPrice = sellingPrice;
		this.maturityTime = maturityTime - growthBonus;
		this.health = health;
		this.dayPlanted = dayPlanted;
	}
	
	//BEGIN getters and setters
	public String getSpecies() {
		return this.species;
	}
	
	public double getPurchasePrice() {
		return this.purchasePrice;
	}
	
	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	
	public double getSellingPrice() {
		return this.sellingPrice;
	}
	
	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	
	public int getMaturityTime() {
		return this.maturityTime;
	}
	
	public void setMaturityTime(int maturityTime, int growthBonus) {
		this.maturityTime = maturityTime - growthBonus;
	}
	
	public double getHealth() {
		return this.health;
	}
	
	public void setHealth(double health) {
		this.health = health;
	}
	//END getters and setters
	
	//BEGIN methods
	public int getAge(int currentDay) {
		return currentDay - dayPlanted;
	}
	
	public int getDaysUntilMature(int currentDay) {
		int age = this.getAge(currentDay);
		return (age < this.maturityTime) ? this.maturityTime - age : 0;
	}
	//END methods
}
