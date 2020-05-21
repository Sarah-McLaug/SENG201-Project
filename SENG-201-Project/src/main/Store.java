package main;
import java.util.ArrayList;

public class Store {
	private ArrayList<Crop> crops = new ArrayList<Crop>();
	private ArrayList<Animal> animals = new ArrayList<Animal>();
	private ArrayList<Item> items = new ArrayList<Item>();
	
	public Store() {
		
	}
	
	public ArrayList<Crop> getCrops(){
		return crops;
	}
	
	public void addCrop(Crop crop) {
		crops.add(crop);
	}
	
	public void sellCrop(int id) {
		for(int i = 0; i < crops.size(); i++) {
			if(crops.get(i).getId() == id) {
				crops.remove(i);
				break;
			}
		}
	}
	
	public ArrayList<Animal> getAnimals() {
		return animals;
	}
	
	public ArrayList<Item> getFoodItems() {
		ArrayList<Item> foodItems = new ArrayList<Item>();
		for (int j = 0; j < items.size(); j++) {
			if (items.get(j).isFoodItem()) {
				foodItems.add(items.get(j));
			}
		}
		return foodItems;
	}
	
	public ArrayList<Item> getCropItems() {
		ArrayList<Item> cropItems = new ArrayList<Item>();
		for (int j = 0; j < items.size(); j++) {
			if (!items.get(j).isFoodItem()) {
				cropItems.add(items.get(j));
			}
		}
		return cropItems;
	}
	
	/*
	public ArrayList<Animal> getAnimalSpecies(String species) {
		ArrayList<Animal> speciesInStock = new ArrayList<Animal>();
		for (int j = 0; j < animals.size(); j++) {
			if (animals.get(j).getSpecies() == species) {
				speciesInStock.add(animals.get(j));
			}
		}
		return speciesInStock;
	}
	
	public ArrayList<Crop> getCropSpecies(String species) {
		ArrayList<Crop> speciesInStock = new ArrayList<Crop>();
		for (int j = 0; j < crops.size(); j++) {
			if (crops.get(j).getSpecies() == species) {
				speciesInStock.add(crops.get(j));
			}
		}
		return speciesInStock;
	}
	
	public ArrayList<Item> getItemType(String type) {
		ArrayList<Item> itemsInStock = new ArrayList<Item>();
		for (int j = 0; j < items.size(); j++) {
			if (items.get(j).getName() == type) {
				itemsInStock.add(items.get(j));
			}
		}
		return itemsInStock;
	}
	*/
	
	
	public int getItemStockCount(String description) {
		int count = 0;
		for (int j  = 0; j < items.size(); j++) {
			if (items.get(j).getName().equals(description)) {
				count++;
			}
		}
		return count;
	}
	
	public void addAnimal(Animal animal) {
		animals.add(animal);
	}
	
	public void sellAnimal(int id) {
		for(int i = 0; i < animals.size(); i++) {
			if(animals.get(i).getId() == id) {
				animals.remove(i);
				break;
			}
		}
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	public void sellItem(int id) {
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).getId() == id) {
				items.remove(i);
				break;
			}
		}
	}
	
	
	//Perhaps these methods can be implemented as an interface they do the same thing just with different types
	//---------------------------------------------------------------------------------------------------------
	public boolean itemExists(int id) {
		boolean exists = false;
		for (int j = 0; j < items.size(); j++) {
			if (items.get(j).getId() == id) {
				exists = true;
			}
		}
		return exists;
	}
	
	public boolean cropExists(int id) {
		boolean exists = false;
		for (int j = 0; j < crops.size(); j++) {
			if (crops.get(j).getId() == id) {
				exists = true;
			}
		}
		return exists;
	}
	
	public boolean animalExists(int id) {
		boolean exists = false;
		for (int j = 0; j < animals.size(); j++) {
			if (animals.get(j).getId() == id) {
				exists = true;
			}
		}
		return exists;
	}
	//---------------------------------------------------------------------------------------------------------
	
	//and with these...?
	//---------------------------------------------------------------------------------------------------------
	public Item fetchItem(int id) {
		
		for (int j = 0; j < items.size(); j++) {
			if (items.get(j).getId() == id) {
				return items.get(j);
			}
		}
		return null;
	}
	
	public Animal fetchAnimal(int id) {
		for (int j = 0; j < animals.size(); j++) {
			if (animals.get(j).getId() == id) {
				return animals.get(j);
			}
		}
		return null;
	}
	
	public Crop fetchCrop(int id) {
		for (int j = 0; j < crops.size(); j++) {
			if (crops.get(j).getId() == id) {
				return crops.get(j);
			}
		}
		return null;
	}
	//---------------------------------------------------------------------------------------------------------
}
