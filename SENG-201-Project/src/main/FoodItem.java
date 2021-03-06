package main;

public class FoodItem extends Item{
	private double healthPoints;

	/**
	 * Food Item constructor
	 * @param name - name of the food item
	 * @param price - price to purchase the food item
	 * @param healthPoints - how much health the food item gives when it is consumed
	 */
	public FoodItem(String name, double price, double healthPoints) {
		super(name, price, "food");
		this.healthPoints = healthPoints;
	}
	
	/**
	 * 
	 * @return health points the item gives when it is consumed
	 */
	public double getHealthPoints() {
		return this.healthPoints;
	}
	
	/**
	 * 
	 * @param healthPoints - health points of the item to set
	 */
	public void setHealthPoints(double healthPoints) {
		this.healthPoints = healthPoints;
	}

	/**
	 * Get this specific item's benefit
	 * @return health points of the item when consumed
	 */
	public int getBenefit() {
		//TODO: implementing interface in parent
		return (int) this.healthPoints;
	};
}
