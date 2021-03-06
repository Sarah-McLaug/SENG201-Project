package main;

public abstract class Item {
	private String name;
	private double price;
	private String purpose;
	private static int count = 1;
	private int id;
	
	/**
	 * Item constructor
	 * @param name - name of the item
	 * @param price - cost to buy the item, in dollars
	 * @param purpose - String to determine type of item, either food or crop
	 */
	public Item(String name, double price, String purpose) {
		this.name = name;
		this.price = price;
		this.purpose = purpose;
		id = count++;
	}
	
	/**
	 * 
	 * @return name of the item
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 
	 * @return cost to buy the item, in dollars
	 */
	public double getPrice() {
		return this.price;
	}
	
	/**
	 * 
	 * @param price - price of the item to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * 
	 * @return true if the item is a food item, return false if it is not
	 */
	public boolean isFoodItem() {
		return this.purpose.equals("food");
	}
	
	/**
	 * 
	 * @return unique ID of the item
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * 
	 * @return the specific benefit of the item when consumed
	 */
	public abstract int getBenefit();
}
