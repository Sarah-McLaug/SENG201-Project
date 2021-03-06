package main;
import java.util.ArrayList;

public class Store {
	private ArrayList<Crop> crops = new ArrayList<Crop>();
	private ArrayList<Animal> animals = new ArrayList<Animal>();
	private ArrayList<Item> items = new ArrayList<Item>();
	
	/**
	 * 
	 * @return list of crops the store is selling
	 */
	public ArrayList<Crop> getCrops(){
		return crops;
	}
	
	/**
	 * 
	 * @param crop - crop to add to store
	 */
	public void addCrop(Crop crop) {
		crops.add(crop);
	}
	
	/**
	 * Remove a crop from the store
	 * @param id - unique ID of crop to remove from the store
	 */
	public void sellCrop(int id) {
		for(int i = 0; i < crops.size(); i++) {
			if(crops.get(i).getId() == id) {
				crops.remove(i);
				break;
			}
		}
	}
	
	/**
	 * 
	 * @return list of animals the store is selling
	 */
	public ArrayList<Animal> getAnimals() {
		return animals;
	}
	
	/**
	 * 
	 * @return list of food items the store is selling
	 */
	public ArrayList<Item> getFoodItems() {
		ArrayList<Item> foodItems = new ArrayList<Item>();
		for (int j = 0; j < items.size(); j++) {
			if (items.get(j).isFoodItem()) {
				foodItems.add(items.get(j));
			}
		}
		return foodItems;
	}
	
	/**
	 * 
	 * @return list of crop items the store is selling
	 */
	public ArrayList<Item> getCropItems() {
		ArrayList<Item> cropItems = new ArrayList<Item>();
		for (int j = 0; j < items.size(); j++) {
			if (!items.get(j).isFoodItem()) {
				cropItems.add(items.get(j));
			}
		}
		return cropItems;
	}
	
	/**
	 * Get the stock count for items
	 * @param description - name of the type of item
	 * @return number of items of that type
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
	
	/**
	 * 
	 * @param animal - animal to add to store
	 */
	public void addAnimal(Animal animal) {
		animals.add(animal);
	}
	
	/**
	 * Remove an animal from the store
	 * @param id - unique ID of the animal to remove from the store
	 */
	public void sellAnimal(int id) {
		for(int i = 0; i < animals.size(); i++) {
			if(animals.get(i).getId() == id) {
				animals.remove(i);
				break;
			}
		}
	}
	
	/**
	 * 
	 * @return list of items the store is selling
	 */
	public ArrayList<Item> getItems() {
		return items;
	}
	
	/**
	 * 
	 * @param item - item to add to store
	 */
	public void addItem(Item item) {
		items.add(item);
	}
	
	/**
	 * Remove an item from the store
	 * @param id - unique ID of the item to remove from the store
	 */
	public void sellItem(int id) {
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).getId() == id) {
				items.remove(i);
				break;
			}
		}
	}
	
	/**
	 * Detect if the store has a specific item
	 * @param id - id of the item to search for
	 * @return - true if the item is in the store, false otherwise
	 */
	public boolean itemExists(int id) {
		boolean exists = false;
		for (int j = 0; j < items.size(); j++) {
			if (items.get(j).getId() == id) {
				exists = true;
			}
		}
		return exists;
	}
	
	/**
	 * Detect if the store has a specific crop
	 * @param id - id of the crop to search for
	 * @return - true if the crop is in the store, false otherwise
	 */
	public boolean cropExists(int id) {
		boolean exists = false;
		for (int j = 0; j < crops.size(); j++) {
			if (crops.get(j).getId() == id) {
				exists = true;
			}
		}
		return exists;
	}

	/**
	 * Detect if the store has a specific animal
	 * @param id - id of the animal to search for
	 * @return - true if the animal is in the store, false otherwise
	 */
	public boolean animalExists(int id) {
		boolean exists = false;
		for (int j = 0; j < animals.size(); j++) {
			if (animals.get(j).getId() == id) {
				exists = true;
			}
		}
		return exists;
	}

	/**
	 * Retrieve an item from the store
	 * @param id - id of the item to retrieve
	 * @return - item retrieved, null if it doesn't exist in the store
	 */
	public Item fetchItem(int id) {
		
		for (int j = 0; j < items.size(); j++) {
			if (items.get(j).getId() == id) {
				return items.get(j);
			}
		}
		return null;
	}
	
	/**
	 * Retrieve an animal from the store
	 * @param id - id of the animal to retrieve
	 * @return - animal retrieved, null if it doesn't exist in the store
	 */
	public Animal fetchAnimal(int id) {
		for (int j = 0; j < animals.size(); j++) {
			if (animals.get(j).getId() == id) {
				return animals.get(j);
			}
		}
		return null;
	}
	
	/**
	 * Retrieve a crop from the store
	 * @param id - id of the crop to retrieve
	 * @return - crop retrieved, null if it doesn't exist in the store
	 */
	public Crop fetchCrop(int id) {
		for (int j = 0; j < crops.size(); j++) {
			if (crops.get(j).getId() == id) {
				return crops.get(j);
			}
		}
		return null;
	}
}
