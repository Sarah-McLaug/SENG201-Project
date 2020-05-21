package main;

public class FoodItem extends Item{
	private double healthPoints;
	
	public FoodItem(String name, double price, double healthPoints) {
		super(name, price);
		this.healthPoints = healthPoints;
	}
	
	//BEGIN getters and setters
	public double getHealthPoints() {
		return this.healthPoints;
	}
	
	public void setHealthPoints(double healthPoints) {
		this.healthPoints = healthPoints;
	}
	//END getters and setters
	
	public int getBenefit() {
		return (int) healthPoints;
	}

}
