package main;

public class Animal {
	private String species;
	private double purchasePrice;
	private double dailyBonus;
	private double happiness;
	private double health;
	private static int count = 1;
	private int id;
	
	public Animal(String species, double purchasePrice, double dailyBonus, double happiness, double health) {
		this.species = species;
		this.purchasePrice = purchasePrice;
		this.dailyBonus = dailyBonus;
		this.happiness = happiness;
		this.health = health;
		this.id = count++;
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
	
	public double getDailyBonus() {
		return this.dailyBonus;
	}
	
	public void setDailyBonus(double animalHappinessFactor, double animalHealthFactor) {
		//The daily bonus from an animal must be a function of it's happiness and it's health
		//These decimals should be happiness/health factors from the farm
		this.dailyBonus = dailyBonus + (0.05 * this.happiness) + (0.25 * this.health);
	}
	
	public double getHappiness() {
		return this.happiness;
	}
	
	public void updateHappiness(double happinessValue) {
		this.happiness += happinessValue;
	}
	
	public double getHealth() {
		return this.health;
	}
	
	public void setHealth(double health) {
		this.health = health;
	}
	
	public int getId() {
		return this.id;
	}
	//END getters and setters
	
	/*
	public void addHealth(double health) {
		this.health += health;
	}
	
	public void raiseHappiness(double happiness) {
		this.happiness += happiness;
	}
	*/
	
	public void updateHealth(double health) {
		this.health += health;
	}
}
