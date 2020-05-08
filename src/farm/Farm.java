package farm;

public class Farm {
	private String name;
	private String type;
	private Farmer farmer;
	//TODO: add a list of crops the farm has
	//TODO: add a list of animals the farm has
	private double balance;
	private double cropGrowthFactor;
	private double animalBonusFactor;
	private double cropYieldFactor;
	
	public Farm(String name, String type, Farmer farmer) {
		this.name = name;
		this.type = type;
		this.farmer = farmer;
		setType(this.type);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
		switch(this.type) {
		//TODO check if these cases are okay
		case "Friendly Farm":
			balance = 10000;
			cropGrowthFactor = 1.0;
			animalBonusFactor = 1.25;
			cropYieldFactor = 1.0;
		case "Fast Farm":
			balance = 10000;
			cropGrowthFactor = 1.25;
			animalBonusFactor = 1.0;
			cropYieldFactor = 1.0;
		case "Fertile Farm":
			balance = 10000;
			cropGrowthFactor = 1.0;
			animalBonusFactor = 1.0;
			cropYieldFactor = 1.25;
		case "Money Farm":
			balance = 15000;
			cropGrowthFactor = 1.0;
			animalBonusFactor = 1.0;
			cropYieldFactor = 1.0;
		default:
			balance = 12000;
			cropGrowthFactor = 1.0;
			animalBonusFactor = 1.0;
			cropYieldFactor = 1.0;
		}
	}
	
	//TODO: actually do the Farmer getter/setter correctly
	public Farmer getFarmer() {
		return farmer;
	}
	
	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}
	
	//TODO: getters and setters for crops and animals
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double capital) {
		balance = capital;
	}
	
	public double getCropGrowthFactor() {
		return cropGrowthFactor;
	}
	
	public void setCropGrowthFactor(double factor) {
		cropGrowthFactor = factor;
	}
	
	public double getAnimalBonusFactor() {
		return animalBonusFactor;
	}
	
	public void setAnimalBonusFactor(double factor) {
		animalBonusFactor = factor;
	}
	
	public double getCropYieldFactor() {
		return cropYieldFactor;
	}
	
	public void setCropYieldFactor(double factor) {
		cropYieldFactor = factor;
	}
	
}
