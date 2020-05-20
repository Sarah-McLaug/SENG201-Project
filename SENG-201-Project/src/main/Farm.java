package main;
import java.util.ArrayList;

import main.Animal;
import main.Crop;
import main.CropItem;
import main.FoodItem;
import main.Item;

public class Farm {
	private String name;
	private String type;
	private Farmer farmer;
	private ArrayList<Crop> crops = new ArrayList<Crop>();
	private ArrayList<Animal> animals = new ArrayList<Animal>();
	private ArrayList<Item> ownedItems = new ArrayList<Item>();
	private double balance;
	private int cropGrowthBonus;
	private double animalBonusFactor;
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
			animalBonusFactor = 1.25;
			cropYieldFactor = 1.0;
		case "fast":
			balance = 10000.00;
			cropGrowthBonus = 1;
			animalBonusFactor = 1.0;
			cropYieldFactor = 1.0;
		case "fertile":
			balance = 10000.00;
			cropGrowthBonus = 1;
			animalBonusFactor = 1.0;
			cropYieldFactor = 1.25;
		case "rich":
			balance = 15000.00;
			cropGrowthBonus = 1;
			animalBonusFactor = 1.0;
			cropYieldFactor = 1.0;
		default:
			balance = 12000.00;
			cropGrowthBonus = 1;
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
	
	public static ArrayList<String> getOwnedCropTypes(ArrayList<Crop> c) {
		ArrayList<String> ownedCropTypes = new ArrayList<String>();
		int cropQuantity = c.size();
		
		boolean newSpecies = true;
		for (int j = 0; j < cropQuantity; j++) {
			for (int i = 0; i < ownedCropTypes.size(); i++) {
				if (c.get(j).getSpecies() == ownedCropTypes.get(i)) {
					newSpecies = false;
					break;
				}
			}
			if (newSpecies) {
				ownedCropTypes.add(c.get(j).getSpecies());
			}
			
		}
		return ownedCropTypes;
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
		animalBonusFactor += 0.5;
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
		ownedItems.add(item);
	}
}
