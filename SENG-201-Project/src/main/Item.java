package main;

public class Item {
	private String name;
	private double price;
<<<<<<< HEAD
	private String purpose;
	private static int count = 0;
	private int id;
=======
>>>>>>> parent of 36ebf6b... abstract class for items, commenting, item processing structure
	
	public Item(String name, double price) {
		this.name = name;
		this.price = price;
<<<<<<< HEAD
		this.purpose = purpose;
		id = count++;
=======
>>>>>>> parent of 36ebf6b... abstract class for items, commenting, item processing structure
	}
	
	//BEGIN getters and setters
	public String getName() {
		return this.name;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
<<<<<<< HEAD
	
	public boolean isFoodItem() {
		return this.purpose == "food";
	}
	
	public int getId() {
		return this.id;
	}
	//we need to implement an interface here for the case that it is growthEnhancment (int) or HP (double)
	//for now HP is casted to int in the child class...
	public abstract int getBenefit();
	
	//get HP/growth enhancement interface?
=======
>>>>>>> parent of 36ebf6b... abstract class for items, commenting, item processing structure
	//END getters and setters
}
