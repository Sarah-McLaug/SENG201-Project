package main;
// Note: should consider giving each item a unique id so they can be directly accessed and deleted from the jtable rather than repopulating every time an item is used
public abstract class Item {
	private String name;
	private double price;
	private String purpose;
	private static int count = 0;
	private int id;
	
	public Item(String name, double price, String purpose) {
		this.name = name;
		this.price = price;
		this.purpose = purpose;
		id = count++;
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
	//END getters and setters
}
