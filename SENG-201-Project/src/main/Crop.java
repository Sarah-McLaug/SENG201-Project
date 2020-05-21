package main;

public class Crop {
	private String species;
	private double purchasePrice;
	private double sellingPrice;
	private int daysUntilMature;
	private double health;
	
	public Crop(String species, double purchasePrice, double sellingPrice, int daysUntilMature, double health, int growthBonus) {
		this.species = species;
		this.purchasePrice = purchasePrice;
		this.sellingPrice = sellingPrice;
		this.daysUntilMature = daysUntilMature - growthBonus;
		this.health = health;
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
	
	public int getDaysUntilMature() {
		return this.daysUntilMature;
	}
	
	public void setDaysUntilMature(int days, int growthBonus) {
		this.daysUntilMature = days - growthBonus;
		if(daysUntilMature < 0) daysUntilMature = 0;
	}
	
	public double getHealth() {
		return this.health;
	}
	
	public void setHealth(double health) {
		this.health = health;
	}
	//END getters and setters
	
	//BEGIN methods
	public void reduceDaysUntilMature(int reduction) {
		daysUntilMature -= reduction;
		if(daysUntilMature < 0) daysUntilMature = 0;
	}
	//END methods
}
