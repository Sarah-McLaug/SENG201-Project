package main;
import java.util.ArrayList;


public class Farm {
	private String name;
	private String type;
	private Farmer farmer;
	private ArrayList<Crop> crops = new ArrayList<Crop>();
	private ArrayList<Animal> animals = new ArrayList<Animal>();
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private double balance;
	private int cropGrowthBonus;
	private double animalHappinessFactor;
	private double animalHealthFactor;
	private double cropYieldFactor;

	public Farm(String name, String type, Farmer farmer) {
		this.name = name;
		this.type = type;
		this.farmer = farmer;
		this.type = type;
		setType();
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
	
	public void setType() {
		switch(type) {
		//TODO check if these cases are okay
		case "friendly":
			balance = 10000.00;
			cropGrowthBonus = 1;
			//animals are 25% more happy
			animalHappinessFactor = 1.25;
			animalHealthFactor = 1;
			cropYieldFactor = 1.0;
		case "fast":
			balance = 10000.00;
			cropGrowthBonus = 1;
			animalHappinessFactor = 1.0;
			animalHealthFactor = 1;
			cropYieldFactor = 1.0;
		case "fertile":
			balance = 10000.00;
			cropGrowthBonus = 1;
			animalHappinessFactor = 1.0;
			animalHealthFactor = 1;
			cropYieldFactor = 1.25;
		case "rich":
			balance = 15000.00;
			cropGrowthBonus = 1;
			animalHappinessFactor = 1.0;
			animalHealthFactor = 1;
			cropYieldFactor = 1.0;
		default:
			balance = 12000.00;
			cropGrowthBonus = 1;
			animalHappinessFactor = 1.0;
			animalHealthFactor = 1;
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
	
	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double capital) {
		balance = capital;
	}
	
	//?
	/*
	public void addBalance(double profit) {
		balance += profit;
	}
	
	public void removeBalance(double cost) {
		balance -= cost;
	}
	*/
	
	public void updateBalance(double value) {
		balance += value;
	}
	
	public int getCropGrowthFactor() {
		return cropGrowthBonus;
	}
	
	public void setCropGrowthFactor(int bonus) {
		cropGrowthBonus = bonus;
	}
	
	public double getAnimalHappinessFactor() {
		return animalHappinessFactor;
	}

	
	public double getAnimalHealthFactor() {
		return animalHealthFactor;
	}
	
	public double getCropYieldFactor() {
		return cropYieldFactor;
	}
	
	public void setCropYieldFactor(double factor) {
		cropYieldFactor = factor;
	}
	
	public void addCrop(Crop c, int currentDay) {
		c.setDayPlanted(currentDay);
		crops.add(c);
	}
	
	public void addAnimal(Animal animal) {
		animals.add(animal);
	}
	
	public void feedAnimals(FoodItem food) {
		for(int i = 0; i < animals.size(); i++) {
			animals.get(i).updateHealth(food.getHealthPoints());
		}
		
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).getName().equals(food.getName())) {
				inventory.remove(i);
				break;
			}
		}
	}
	
	public void playAnimals() {
		for(int i = 0; i < animals.size(); i++) {
			animals.get(i).updateHappiness(this.animalHappinessFactor); //TODO decide how much to raise animal happiness by ---> by the happiness factor?
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
		
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).getName().equals(cropItem.getName())) {
				inventory.remove(i);
				break;
			}
		}
	}
	
	//TODO: TEST THIS, I think i have to go back an index but i'm not sure
	public void harvestCrop() {
		//Must update the fields array in the case that we harvest all of one type of crop
		for(int i = 0; i < crops.size(); i++) {
			if(crops.get(i).getDaysUntilMature() == 0) {
				//'cropGrowthFactor' refactored to 'cropGrwothBonus' ---> it must be an integer. e.g. daysTillMature -= cropGrowthBonus 
				//balance += crops.get(i).getSellingPrice()*cropGrowthFactor;
				crops.remove(i);
				i--;
			}
		}
	}
	
	//TODO: Review to make sure these are okay
	public void tendCrop() {
		//cropGrowthFactor += 0.1;
		animalHappinessFactor += 0.5;
	}
	
	public void waterCrop(String targetSpecies) {
		int cropQuantity = this.crops.size();
		for (int j = 0; j < cropQuantity; j++) {
			if (crops.get(j).getSpecies() == targetSpecies) {
				//water the crop
				crops.get(j).setDaysUntilMature(crops.get(j).getDaysUntilMature() - 1, 0);
			}
		}
	}
	
	public void addItem(Item item) {
		inventory.add(item);
	}
}
