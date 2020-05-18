package main;
import java.util.ArrayList;

public class Farm {
	private String name;
	private String type;
	private Farmer farmer;
	private ArrayList<Crop> crops = new ArrayList<Crop>();
	private ArrayList<Animal> animals = new ArrayList<Animal>();
	private ArrayList<Item> ownedItems = new ArrayList<Item>();
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
		case "friendly":
			balance = 10000;
			cropGrowthFactor = 1.0;
			animalBonusFactor = 1.25;
			cropYieldFactor = 1.0;
		case "fast":
			balance = 10000;
			cropGrowthFactor = 1.25;
			animalBonusFactor = 1.0;
			cropYieldFactor = 1.0;
		case "fertile":
			balance = 10000;
			cropGrowthFactor = 1.0;
			animalBonusFactor = 1.0;
			cropYieldFactor = 1.25;
		case "rich":
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

	public ArrayList<Crop> getCrops() {
		return crops;
	}
	
	public ArrayList<Animal> getAnimals() {
		return animals;
	}
	
	public ArrayList<Item> getOwnedItems() {
		return ownedItems;
	}
	
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
	
	public void addCrop(Crop crop) {
		crops.add(crop);
	}
	
	public void addAnimal(Animal animal) {
		animals.add(animal);
	}
	
	public void feedAnimal(FoodItem food) {
		for(int i = 0; i < animals.size(); i++) {
			animals.get(i).addHealth(food.getHealthPoints());
		}
		
		for(int i = 0; i < ownedItems.size(); i++) {
			if(ownedItems.get(i).getName().equals(food.getName())) {
				ownedItems.remove(i);
				break;
			}
		}
	}
	
	public void playAnimal() {
		for(int i = 0; i < animals.size(); i++) {
			animals.get(i).raiseHappiness(2.0); //TODO decide how much to raise animal happiness by
		}
	}
	
	public void tendCrop(String species) {
		for(int i = 0; i < crops.size(); i++) {
			if(crops.get(i).getSpecies().equals(species)) {
				crops.get(i).reduceDaysUntilMature(1); //Water, no item
			}
		}
	}
	
	public void tendCrop(String species, CropItem cropItem) {
		for(int i = 0; i < crops.size(); i++) {
			if(crops.get(i).getSpecies().equals(species)) {
				crops.get(i).reduceDaysUntilMature(1 + cropItem.getGrowthEnhancement()); //with item
			}
		}
		
		for(int i = 0; i < ownedItems.size(); i++) {
			if(ownedItems.get(i).getName().equals(cropItem.getName())) {
				ownedItems.remove(i);
				break;
			}
		}
	}
	
	//TODO: TEST THIS, I think i have to go back an index but i'm not sure
	public void harvestCrop() {
		for(int i = 0; i < crops.size(); i++) {
			if(crops.get(i).getDaysUntilMature() == 0) {
				balance += crops.get(i).getSellingPrice()*cropGrowthFactor;
				crops.remove(i);
				i--;
			}
		}
	}
	
	//TODO: Review to make sure these are okay
	public void tendCrop() {
		cropGrowthFactor += 0.1;
		animalBonusFactor += 0.5;
	}
	
	public void addItem(Item item) {
		ownedItems.add(item);
	}
}