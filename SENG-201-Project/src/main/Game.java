package main;
import java.util.ArrayList;
import java.util.Scanner;


public class Game {
	//********************************************
	private Farm farm;
	private Store store = new Store();
	private int duration;
	private int actionCount;
	private int day = 0;
	
	private ArrayList<Animal> animalSpecies = new ArrayList<Animal>();
	private ArrayList<Crop> cropSpecies = new ArrayList<Crop>();
	private ArrayList<Item> foodItems = new ArrayList<Item>();
	private ArrayList<Item> cropItems = new ArrayList<Item>();
	
	public Farm getFarm() {
		return farm;
	}
	
	public void setFarm(Farm farm) {
		this.farm = farm;
	}
	
	public Store getStore() {
		return store;
	}
	
	public void setStore(Store store) {
		this.store = store;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public int getActionCount() {
		return actionCount;
	}
	
	public void setActionCount(int actionCount) {
		this.actionCount = actionCount;
	}
	
	public int getDay() {
		return day;
	}
	
	public void setDay(int day) {
		this.day = day;
	}
	
	public ArrayList<Crop> getCropSpecies(){
		return cropSpecies;
	}
	
	public void setSpecies(ArrayList<Animal> animalSpecies, ArrayList<Crop> cropSpecies, 
			ArrayList<Item> foodItems, ArrayList<Item> cropItems) {
		this.animalSpecies = animalSpecies;
		this.cropSpecies = cropSpecies;
		this.foodItems = foodItems;
		this.cropItems = cropItems;
	}
	//*********************************************
	//BEGIN methods
	//-----------------------------------------------------------------------------------------------------------------
	
	public double score() {
		return 0.0;
		//insert some func here
	}
	
	public double profit() {
		if(farm.getType().equals("friendly") || 
				farm.getType().equals("fast") ||
				farm.getType().equals("fertile")){
			return farm.getBalance() - 10000.00;
		} else if(farm.getType().equals("rich")){
			return farm.getBalance() - 15000.00;
		} else {
			return farm.getBalance() - 12000.00;
		}
	}
	
	public void populateStore() {
		//remove remaining stock
		//remove crops
		if(store.getCrops().size() != 0) {
			for(int i = 0; i < store.getCrops().size(); i++) {
				store.sellCrop(store.getCrops().get(i).getId());
			}
		}
		
		//remove animals
		for(int i = 0; i < store.getAnimals().size(); i++) {
			store.sellAnimal(store.getAnimals().get(i).getId());
		}
		
		//remove items
		for(int i = 0; i < store.getItems().size(); i++) {
			store.sellItem(store.getItems().get(i).getId());
		}
		
		int range = 3; //TODO: add appropriate range for each item
		
		//generate crops in store
		for(int i = 0; i < cropSpecies.size(); i++) {
			int amountCrops = (int) (Math.random() * range) + 1;
			for(int j = 0; j < amountCrops; j++) {
				Crop newCrop = cropSpecies.get(i);
				String species = newCrop.getSpecies();
				double purchasePrice = newCrop.getPurchasePrice();
				double sellingPrice = newCrop.getSellingPrice();
				int daysUntilMature = newCrop.getDaysUntilMature();
				double health = newCrop.getHealth();
				store.addCrop(new Crop(species, purchasePrice, sellingPrice, daysUntilMature, health, day));
			}
		}
		
		//generate animals in store
		for(int i = 0; i < animalSpecies.size(); i++) {
			int amountAnimals = (int) (Math.random() * range) + 1;
			for(int j = 0; j < amountAnimals; j++) {
				Animal newAnimal = animalSpecies.get(i);
				String species = newAnimal.getSpecies();
				double purchasePrice = newAnimal.getPurchasePrice();
				double health = newAnimal.getHealth();
				double dailyBonus = newAnimal.getDailyBonus();
				double happiness = newAnimal.getHappiness();
				store.addAnimal(new Animal(species, purchasePrice, dailyBonus, happiness, health));
			}
		}
		
		//generate foodItems in store
		for(int i = 0; i < foodItems.size(); i++) {
			int amountFoodItems = (int) (Math.random() * range) + 1;
			for(int j = 0; j < amountFoodItems; j++) {
				FoodItem newFoodItem = (FoodItem) foodItems.get(i);
				String name = newFoodItem.getName();
				double healthPoints = newFoodItem.getHealthPoints();
				double purchasePrice = newFoodItem.getPrice();
				store.addItem(new FoodItem(name, healthPoints, purchasePrice));
			}
		}
		
		//generate cropItems in store
		for(int i = 0; i < cropItems.size(); i++) {
			int amountCropItems = (int) (Math.random() * range) + 1;
			for(int j = 0; j < amountCropItems; j++) {
				CropItem newCropItem = (CropItem) cropItems.get(i);
				String name = newCropItem.getName();
				int growthEnhancement = newCropItem.getGrowthEnhancement();
				double purchasePrice = newCropItem.getPrice();
				store.addItem(new CropItem(name, purchasePrice, growthEnhancement));
			}
		}
	}
	
	public void advanceDay() {
		if(day - 1 > 0) {
			day--;
			actionCount = 2;
			//give animal money TODO: decide threshold for happy animal bonus
			for(int i = 0; i < farm.getAnimals().size(); i++) {
				if(farm.getAnimals().get(i).getHappiness() >= 8) {
					farm.updateBalance(farm.getAnimals().get(i).getDailyBonus());
				}
			}
			//lower animal happiness & health TODO: decide health/happiness lowering factor
			/*
			 * Note: 
			 * When a farmer plays with an animal it's happiness is increased by it's the farms animalHappinessFactor 
			 */
			for(int i = 0; i < farm.getAnimals().size(); i++) {
				farm.getAnimals().get(i).updateHealth(-1);
				farm.getAnimals().get(i).updateHappiness(-1);
			}
			//reduce days until mature for crops
			for(int i = 0; i < farm.getCrops().size(); i++) {
				if(farm.getCrops().get(i).getDaysUntilMature() > 0) farm.getCrops().get(i).reduceDaysUntilMature(1);
			}
			
			populateStore();
		} else {
			//end game
		}
	}
	
	public void launchEndScreen() {
		EndScreen endScreen = new EndScreen(this);
	}
	
	public void closeEndScreen(EndScreen endWindow) {
		endWindow.closeWindow();
	}
	
	public void launchMainScreen() {
		MainScreen mainWindow = new MainScreen(this);
	}
	
	public void closeMainScreen(MainScreen mainWindow) {
		mainWindow.closeWindow();
		launchEndScreen();
	}
	
	public void launchStartScreen() {
		StartScreen startWindow = new StartScreen(this);
	}
	
	public void closeStartScreen(StartScreen startWindow) {
		startWindow.closeWindow();
		launchMainScreen();
	}
	
	//Uses the predefined elements (see below) to populate the store with a random amount of each element
	public static void populateStore(Store store, ArrayList<Animal> animalSpecies, ArrayList<Crop> cropSpecies, 
			ArrayList<Item> foodItems, ArrayList<Item> cropItems, int day) {
		
		//Sell all items before adding re-stocking (remember the new quantities will be random!) 
		// ---> keeps the store from being over stocked and adds some volatility to the game.
		for (int j = 0; j < store.getItems().size(); j++) {
			store.sellItem(store.getItems().get(j).getId());
		}
				
		//Abstraction variables
		Crop newCrop;
		Animal newAnimal;
		FoodItem newFoodItem;
		CropItem newCropItem;
		String species;
		double purchasePrice;
		double sellingPrice;
		int daysUntilMature;
		double health;
		double dailyBonus;
		double happiness;
		String name;
		double healthPoints;
		int growthEnhancement;
		
		//TODO: add appropriate ranges for each item to stimulate game decision making
		//Ranges for generating random quantities
		int cropRange = 2;
		int animalRange = 2;
		int foodItemRange = 2;
		int cropItemRange = 2;
		
		//Generate random quantity of elements in their respective ranges
		int amountCrops = (int) (Math.random() * (cropRange));
		int amountAnimals = (int) (Math.random() * (animalRange));
		int amountFoodItems = (int) (Math.random() * (foodItemRange));
		int amountCropItems = (int) (Math.random() * (cropItemRange));
		
		//Generate crop species
		for (int j = 0; j < cropSpecies.size(); j++) {
			amountCrops = (int) (Math.random() * (cropRange));
			for (int i = 0; i < amountCrops; i++) {
				newCrop = cropSpecies.get(j);
				species = newCrop.getSpecies();
				purchasePrice = newCrop.getPurchasePrice();
				sellingPrice = newCrop.getSellingPrice();
				daysUntilMature = newCrop.getDaysUntilMature();
				health = newCrop.getHealth();
				store.addCrop(new Crop(species, purchasePrice, sellingPrice, daysUntilMature, health, day));
			}
		}
		
		//Generate animal species
		for (int j = 0; j < animalSpecies.size(); j++) {
			amountAnimals = (int) (Math.random() * (animalRange));
			for (int i = 0; i < amountAnimals; i++) {
				newAnimal = animalSpecies.get(j);
				species = newAnimal.getSpecies();
				purchasePrice = newAnimal.getPurchasePrice();
				health = newAnimal.getHealth();
				dailyBonus = newAnimal.getDailyBonus();
				happiness = newAnimal.getHappiness();
				store.addAnimal(new Animal(species, purchasePrice, dailyBonus, happiness, health));
			}
		}
		
		//Generate food items
		for (int j = 0; j < foodItems.size(); j++) {
			amountFoodItems = (int) (Math.random() * (foodItemRange));
			for (int i = 0; i < amountFoodItems; i++) {
				newFoodItem = (FoodItem) foodItems.get(j);
				name = newFoodItem.getName();
				healthPoints = newFoodItem.getHealthPoints();
				purchasePrice = newFoodItem.getPrice();
				store.addItem(new FoodItem(name, healthPoints, purchasePrice));
			}
		}
		
		//Generate crop items
		for (int j = 0; j < cropItems.size(); j++) {
			amountCropItems = (int) (Math.random() * (cropItemRange));
			for (int i = 0; i < amountCropItems; i++) {
				newCropItem = (CropItem) cropItems.get(j);
				name = newCropItem.getName();
				growthEnhancement = newCropItem.getGrowthEnhancement();
				purchasePrice = newCropItem.getPrice();
				store.addItem(new CropItem(name, purchasePrice, growthEnhancement));
			}
		}
	}
	
	
	//TODO: re-purpose
	//Can be changed to type ArrayList <Item> and used for populating jTables in GUI
	public static void printFoodItems(ArrayList<Item> items) {
		Item item;
		for (int j = 0; j < items.size(); j++) {
			item = items.get(j);
			if (item.isFoodItem()) {
				System.out.printf( "%-6s%-20s%-15s%-15s%n","(" + item.getId() + ")", item.getName(), item.getBenefit(), item.getPrice());
			}
		}
	}
	
	//TODO: re-purpose
	//Can be changed to type ArrayList <Item> and used for populating jTables in GUI
	public static void printCropItems(ArrayList<Item> items) {
		Item item;
		for (int j = 0; j < items.size(); j++) {
			item = items.get(j);
			if (!item.isFoodItem()) {
				System.out.printf( "%-6s%-20s%-15s%-15s%n","(" + item.getId() + ")", item.getName(), item.getBenefit(), item.getPrice());
			}
		}
	}
	//-----------------------------------------------------------------------------------------------------------------
	//END methods
	
	public static void main(String[] args) {
		Game game = new Game();
		ArrayList <Crop> cropSpecies = new ArrayList <Crop>();
		cropSpecies.add(new Crop("banana", 2, 2, 2, 2, game.getDay()));
		cropSpecies.add(new Crop("corn", 1, 1, 1, 1, game.getDay()));
		cropSpecies.add(new Crop("kiwi", 1, 1, 1, 1, game.getDay()));
		cropSpecies.add(new Crop("kumera", 1, 1, 1, 1, game.getDay()));
		cropSpecies.add(new Crop("mango", 1, 1, 1, 1, game.getDay()));
		cropSpecies.add(new Crop("spinach", 1, 1, 1, 1, game.getDay()));
		
		ArrayList <Animal> animalSpecies = new ArrayList <Animal>();
		animalSpecies.add(new Animal("llama", 1, 1, 1, 1));
		animalSpecies.add(new Animal("koala", 1, 1, 1, 1));
		animalSpecies.add(new Animal("panda", 1, 1, 1, 1));
		
		ArrayList <Item> foodItems = new ArrayList <Item>();
		foodItems.add(new FoodItem("sugar cane", 1, 1));
		foodItems.add(new FoodItem("eucalyptus leaves", 1, 1));
		foodItems.add(new FoodItem("brocooli", 1, 1));
		
		ArrayList <Item> cropItems = new ArrayList <Item>();
		cropItems.add(new CropItem("fertilizer", 1, 1));
		cropItems.add(new CropItem("pestiside", 2, 2));
		
		game.setSpecies(animalSpecies, cropSpecies, foodItems, cropItems);
		game.populateStore();
		
		game.launchStartScreen();
		//BEGIN Initialisation
		//-----------------------------------------------------------------------------------------------------------------
		//Variables for handling data input and input defence 
		Scanner sc = new Scanner(System.in);
		/* SKIP INITIALIZE QUESTIONS FOR EASY DEBUGGING, some defaults loaded below
		String input = "";
		int inputLength;
		char character;
		
		//Input variables
		int days = 0;
		String farmerName;
		int age;
		String farmName;
		int farmTypeIndex;
		
		//Restraints
		int minDays = 5;
		int maxDays = 10;
		int minNameLength = 3;
		int maxNameLength = 15;
		int minAge = 0;
		int maxAge = 100;
		
		String farmType = "";
		
		
		System.out.println("Welcome to Basic Farming Simulator.");
		System.out.println();
		System.out.println("Complete the following steps to begin your farming journey: \n");
		
		//BEGIN game duration input
		System.out.print("1. Enter the number of days you would like your game to last? (Between 5 and 10): ");
		while (true) {
			input = sc.next();
			
			try {
				days = Integer.parseInt(input);
				
				if (days < minDays || days > maxDays) {
					throw new IllegalArgumentException();
				}
				
				break;
				
			} catch (IllegalArgumentException e) {
				System.out.println();
				System.out.printf("\t - '%s' is not a valid number of days. Please enter a game duration in number of days between 5 to 10. (inclusive) %n", input);
				System.out.println();
				System.out.print("   Enter the number of days you would like your game to last? (Between 5 and 10): ");
			} 	
		}
		//END game duration input
		
		System.out.println();
		sc.nextLine();
		
		//BEGIN farmer name input
		System.out.print("2. Enter your name: ");
		while (true) {
	
			input = sc.nextLine();
			inputLength = input.length();
			try {
				//Test that the input is of valid length for a farmer name.
				if (inputLength < minNameLength ||  inputLength > maxNameLength) {
					throw new IllegalArgumentException("\t - Your name must be between 3 and 15 characters long. (inclusive)");
				}
				//Test that the input contains any characters other than letters.
				for (int j = 0; j < inputLength; j++) {
					character = input.charAt(j);
					if (character != ' ' && !Character.isLetter(character)) {
						throw new IllegalArgumentException("\t - Your name must contain letters and/or whitespaces only.");
					}
				}
				
				farmerName = input;
				break;
				
			} catch (IllegalArgumentException e) {
				System.out.println();
				System.out.println(e.getMessage());
				System.out.println();
				System.out.print("   Enter your name: ");
			}
			
		}
		//END farmer name input
		
		System.out.println();
		
		//BEGIN farmer age input
		System.out.print("3. Enter your age: ");
		while(true) {
			input = sc.next();
			try {
				age = Integer.parseInt(input);
				if (age <= minAge || age > maxAge) {
					throw new IllegalArgumentException();
				}
				break;
			} catch (IllegalArgumentException e) {
				System.out.println();
				System.out.printf("\t - '%s' is not a valid age. Ages exist in the number range %s to %s years. %n", input, minAge, maxAge);
				System.out.println();
				System.out.print("   Enter your age: ");
			} 
		}
		//END farmer age input
		
		System.out.println();
	
		//BEGIN select farm type
		System.out.println("4. These are the different farm types:");
		System.out.println();
		System.out.println("\t 1. Friendly");
		System.out.println("\t \t - Friendly farms have ...");
		System.out.println();
		System.out.println("\t 2. Fast");
		System.out.println("\t \t - Fast farms have ...");
		System.out.println();
		System.out.println("\t 3. Fertile");
		System.out.println("\t \t - Fertile farms have ...");
		System.out.println();
		System.out.println("\t 4. Rich");
		System.out.println("\t \t - Rich farms have ...");
		System.out.println();
		System.out.print("   Enter the index of the type of farm would you like to own: ");
		
		
		while(true) {
			input = sc.next();
			try {
				farmTypeIndex = Integer.parseInt(input);
				if (farmTypeIndex <= 0 || farmTypeIndex > 4) {
					throw new IllegalArgumentException();
				}
				break;
			} catch (IllegalArgumentException e) {
				System.out.println();
				System.out.printf("\t - '%s' is not a farm type. Please enter an index of a farm type from the list above. %n", input);
				System.out.println();
				System.out.print("   Enter the index of the type of farm would you like to own: ");
			} 
		}
		//END select farm type
	
		System.out.println();
		sc.nextLine();
		//BEGIN farm name input
		//Farm name restraints unspecified, sc.next ensures the farm name is not empty.
		System.out.print("5. Enter a name for your farm: ");
		//farmName = sc.nextLine();
		while (true) {
			
			input = sc.nextLine();
			inputLength = input.length();
			try {
				//Test that the input is of valid length for a farm name.
				if (inputLength < minNameLength ||  inputLength > maxNameLength) {
					throw new IllegalArgumentException("\t - Your farm's name must be between 3 and 15 characters long. (inclusive)");
				}
				//Test if the input contains any characters other than letters.
				for (int j = 0; j < inputLength; j++) {
					character = input.charAt(j);
					if (character != ' ' && !Character.isLetter(character)) {
						throw new IllegalArgumentException("\t - Your farm's name must contain letters and/or whitespaces only.");
					}
				}
				
				farmName = input;
				break;
				
			} catch (IllegalArgumentException e) {
				System.out.println();
				System.out.println(e.getMessage());
				System.out.println();
				System.out.print("   Enter a name for your farm: ");
			}
			
		}
		//END farm name input
		
		switch(farmTypeIndex) {
			case 1:
				farmType = "friendly";
				break;
			case 2:
				farmType = "fast";
				break;
			case 3:
				farmType = "fertile";
				break;
			case 4:
				farmType = "rich";
				break;
		}
		//End user input
		*/
		
		//Defaults for easy testing: (remove later)
		//--------------------------------------------------------------------
		String farmName = "Incognito Farms";
		String farmType = "friendly";
		String farmerName = "Lollipop Chainsaw";
		int age = 22;
		int day = 5;
		//--------------------------------------------------------------------
		
		Farm farm = new Farm(farmName, farmType, new Farmer(farmerName, age));
		Store store = new Store();
		int actionsRemaining = 2;
		boolean playingGame = true;
		
		//Define game elements -- commented out, probably need to make a copy of this class
		//-------------------------------------------------------------------------
		/*final ArrayList <Crop> cropSpecies = new ArrayList <Crop>();
		cropSpecies.add(new Crop("banana", 1, 1, 1, 1, farm.getCropGrowthBonus()));
		cropSpecies.add(new Crop("corn", 1, 1, 1, 1, farm.getCropGrowthBonus()));
		cropSpecies.add(new Crop("kiwi", 1, 1, 1, 1, farm.getCropGrowthBonus()));
		cropSpecies.add(new Crop("kumera", 1, 1, 1, 1, farm.getCropGrowthBonus()));
		cropSpecies.add(new Crop("mango", 1, 1, 1, 1, farm.getCropGrowthBonus()));
		cropSpecies.add(new Crop("spinach", 1, 1, 1, 1, farm.getCropGrowthBonus()));
		
		final ArrayList <Animal> animalSpecies = new ArrayList <Animal>();
		animalSpecies.add(new Animal("llama", 1, 1, 1, 1));
		animalSpecies.add(new Animal("koala", 1, 1, 1, 1));
		animalSpecies.add(new Animal("panda", 1, 1, 1, 1));
		
		final ArrayList <Item> foodItems = new ArrayList <Item>();
		foodItems.add(new FoodItem("sugar cane", 1, 1));
		foodItems.add(new FoodItem("eucalyptus leaves", 1, 1));
		foodItems.add(new FoodItem("brocooli", 1, 1));
		
		final ArrayList <Item> cropItems = new ArrayList <Item>();
		cropItems.add(new CropItem("fertilizer", 1, 1));
		cropItems.add(new CropItem("pestiside", 1, 1));*/
		//-------------------------------------------------------------------------
		
		//Fill the store with the elements
		populateStore(store, animalSpecies, cropSpecies, foodItems, cropItems, day);
		
		//-----------------------------------------------------------------------------------------------------------------
		//END Initialisation
		
		
		//BEGIN game
		//-----------------------------------------------------------------------------------------------------------------
		//Landing page
		System.out.println("\nThe game has begun! You have " + day + " days to make as much money as possible!");
		System.out.println("----------------------------------------------------------------------");
		System.out.println();
		System.out.println("\t (Enter the index of an option to execute.)");
		System.out.println();
		
		//CLI menus
		//--------------------------------------------------------------------------------------
		String mainMenuOptions = "\n" + farm.getName()
		+ "\n---------------------------------------------------"
		+ "\n(1) View the status of the farm's crops and animals"
		+ "\n(2) View the status of the farm"
		+ "\n(3) Go to the store"
		+ "\n(4) Take an action (You have %s actions remaining)"
		+ "\n(5) Move to the next day"
		+ "\n(10) Quit game "
		+ "\n"
		+ "\nPlease enter an option: ";

		String storeMenuOptions = "\nStore"
		+ "\n-----------------------------------"
		+ "\n(1) View crops for sale"
		+ "\n(2) View animals for sale"
		+ "\n(3) View farming supplies for sale"
		+ "\n(4) View owned farming supplies"
		+ "\n(0) Go back"
		+ "\n"
		+ "\nPlease enter an option: ";

		String actionMenuOptions = "\nActions"
		+ "\n-----------------------------------"
		+ "\n(1) Tend to crops"
		+ "\n(2) Tend to animals"
		+ "\n(3) Tend to farm"
		+ "\n(0) Go back"
		+ "\n"
		+ "\nPlease enter an option: ";

		String cropActionMenuOptions = "\nCrop Actions"
		+ "\n-----------------------------------"
		+ "\n(1) Water crops"
		+ "\n(2) Use crop item"
		+ "\n(3) Harvest crops"
		+ "\n(0) Go back"
		+ "\n"
		+ "\nPlease enter an option: ";

		String fieldSelectMenu = "\nCrops"
		+ "\n-----------------------------------"
		+ "\n(1) banana"
		+ "\n(2) corn"
		+ "\n(3) kiwi"
		+ "\n(4) kumera"
		+ "\n(5) mango"
		+ "\n(6) spinach"
		+ "\n(0) Go back"
		+ "\n"
		+ "\nEnter the index of the crop you want to water, alternatively enter 0 to go back."
		+ "\n"
		+ "\nPlease enter an option: ";

		String animalActionMenuOptions = "\nAnimal Actions"
		+ "\n-----------------------------------"
		+ "\n(1) Feed animals"
		+ "\n(2) Play with animals"
		+ "\n(0) Go back"
		+ "\n"
		+ "\nPlease enter an option: ";

		String farmActionMenuOptions = "\nFarm Actions"
		+ "\n-----------------------------------"
		+ "\n(1) ???"
		+ "\n(2) ???"
		+ "\n(0) Go back"
		+ "\n"
		+ "\nPlease enter an option: ";
		
		//For showing/hiding menus
		//A variable for each menu allows for cascading menus
		boolean storeMenu = false;
		boolean actionMenu = false;
		boolean cropActionMenu = false;
		boolean fieldsMenu = false;
		boolean cropsForSaleMenu = false;
		boolean animalsForSaleMenu = false;
		boolean itemsForSaleMenu = false;
		boolean inventoryMenu = false;
		boolean animalActionMenu = false;
		boolean farmActionMenu = false;
		//--------------------------------------------------------------------------------------
		
		//String formats
		//--------------------------------
		String format3 = "%-20s%-20s%s%n";
		//--------------------------------
		
		//In-game place-holders 
		//------------------------------------------------------------------
		//e.g. farm.getCrops.get(j) now becomes crops.get(index)
		//for each method the lists are redefined respectively
		//i.e. if I'm in the store, crops = store.getCrops();
		// ... if I'm looking at the farmers crops, crops = farm.getCrops();
		ArrayList <Crop> crops = new ArrayList<Crop>();
		ArrayList <Animal> animals = new ArrayList<Animal>();
		ArrayList <Item> items = new ArrayList<Item>();
		
		//For targeting a single element
		Item item;
		Crop crop;
		Animal animal;
		
		//Used for storing the option that was input throughout the keyboard
		//We do not require a different option variable for every menu because sc.nextInt() will update variable 'option' automatically.
		int option = 0;
		
		//When a user wants to use a crop item, they must select a crop
		//from a predefined menu - all the species defined above. In the
		//GUI, this menu can exist as a list of buttons. If for e.g. the
		//user try's to perform an action on a crop they do not possess,
		//an error message will be displayed.
		String species = "";
		
		//For detecting if a farmer owns a crop
		boolean farmerOwnsCrop = false;
		
		//Begin game logic
		while(playingGame) {
			
			//Display and input
			System.out.printf(mainMenuOptions, actionsRemaining);
			option = sc.nextInt();
			sc.nextLine();
			
			switch(option) {
			
			//View the status of the farm's crops and animals
			case 1:
				
				//Display
				System.out.println();
				System.out.println("\t \t Investment Status ");
				System.out.println("--------------------------------------------------");
				
				animals = farm.getAnimals();
				if(farm.getAnimals().size() == 0) {
					System.out.println("\nYou own no animals.\n");
				} else {
					
					//Display
					System.out.println("Animals");
					System.out.println("**************************************************");
					System.out.printf(format3, "Species:", "Happiness/10:", "Health/10:");
					System.out.println("--------------------------------------------------");
					
					
					for(int i = 0; i < animals.size(); i++) {
						System.out.printf(format3, animals.get(i).getSpecies(), animals.get(i).getHappiness(), animals.get(i).getHealth());
						System.out.println("--------------------------------------------------");
					}
					System.out.println("**************************************************");
					System.out.println();
					
				}
				crops = farm.getCrops();
				if(farm.getCrops().size() == 0) System.out.println("You own no crops \n");
				else {
					System.out.println("Crops");
					System.out.println("**************************************************");
					System.out.printf(format3, "Species:", "Time until mature:", "Age:");
					for(int i = 0; i < crops.size(); i++) {
						System.out.println("--------------------------------------------------");
						System.out.printf(format3, crops.get(i).getSpecies() , crops.get(i).getDaysUntilMature(), crops.get(i).getAge(day));
						System.out.println("--------------------------------------------------");
					}
					System.out.println("**************************************************");
				}
				System.out.println("--------------------------------------------------");
				System.out.println();
				break;
				
			//View the status of the farm
			case 2:
				
				System.out.println(farm.getName() + " has $" + farm.getBalance() + " in the bank.");
				System.out.println();
				break;
				
			//Go to the store
			case 3:
				
				storeMenu = true;
				while(storeMenu) {
					
					//Display and input
					System.out.print(storeMenuOptions);
					option = sc.nextInt();
					sc.nextLine();
					System.out.println();
					
					switch(option) {
					
					//Go back
					case 0:
						
						storeMenu = false;
						break;
					
					//View crops for sale
					case 1:
						
						cropsForSaleMenu = true;
						while (cropsForSaleMenu) {
							
							//Display 
							System.out.println("\nCrops for sale");
							System.out.println("------------------------------------------");
							System.out.printf( "%-6s%-10s%-20s%-10s%n","id:", "Species: ", "Days to mature: ", "Price: ");
							System.out.println("------------------------------------------");
							
							//Print the crops that are available in the store
							crops = store.getCrops();
							for(int i = 0; i < crops.size(); i++) {
								crop = crops.get(i);
								System.out.printf("%-6s%-10s%-20s%-10s%n", "("+ crop.getId() + ")", crop.getSpecies(), crop.getDaysUntilMature(), crop.getPurchasePrice());
								System.out.println("------------------------------------------");
							}
							
							//Display
							System.out.println();
							System.out.println("Enter the index of the crop you would like to buy, alternatively enter 0 to go back. \n");
							System.out.print("Please enter an option: ");
							
							//Input
							option = sc.nextInt();
							sc.nextLine();
							
							//Operations
							if (option == 0) {
								cropsForSaleMenu = false;
							}
							
							if(option != 0 && store.cropExists(option) == true) {
								crop = store.fetchCrop(option);
								farm.addCrop(crop, day);
								farm.updateBalance(-crop.getPurchasePrice());
								store.sellCrop(option);
							}
						}
						
						//Stop viewing crops for sale
						break;
					
					//View animals for sale
					case 2:
						
						animalsForSaleMenu = true;
						while (animalsForSaleMenu) {
							
							//Display
							System.out.println("Animals for sale");
							System.out.println("------------------------------------------");
							System.out.printf( "%-6s%-10s%-20s%-10s%n","id:", "Species: ", "Happiness: ", "Price: ");
							System.out.println("------------------------------------------");
							
							//Print all the animals that are available in the store
							animals = store.getAnimals();
							for(int i = 0; i < animals.size(); i++) {
								animal = animals.get(i);
								System.out.printf("%-6s%-10s%-20s%-10s%n", "("+ animal.getId()+ ")", animal.getSpecies(), animal.getHappiness() , animal.getPurchasePrice());
								System.out.println("------------------------------------------");
							}
							
							//Display
							System.out.println();
							System.out.println("Enter the index of the animal you would like to buy, alternatively enter 0 to go back. \n");
							System.out.print("Please enter an option: ");
							
							//Input
							option = sc.nextInt();
							sc.nextLine();
							
							//Operations
							if (option == 0) {
								animalsForSaleMenu = false;
							}
							
							if(option != 0 && store.animalExists(option) == true) {
								animal = store.fetchAnimal(option);
								store.sellAnimal(option);
								farm.addAnimal(animal);
								farm.updateBalance(-animal.getPurchasePrice());
							}
						}
						
						//Stop viewing animals for sale
						break;
						
					//View farming supplies for sale
					case 3:
						
						itemsForSaleMenu = true;
						while (itemsForSaleMenu) {
							
							//Display
							System.out.println("Items for sale");
							System.out.println("-------------------------------------------------------------");
							System.out.println();
							System.out.println("Food items");
							System.out.println("**************************************************");
							System.out.printf( "%-6s%-20s%-15s%-15s%n", "i:", "Description: ", "Health Points: ", "Price: ");
							System.out.println("--------------------------------------------------");
							
							//Print all the food items that are available in the store
							items = store.getItems();
							printFoodItems(items);
							
							//Display
							System.out.println("**************************************************");
							System.out.println();
							System.out.println("Crop items");
							System.out.println("*******************************************************");
							System.out.printf( "%-6s%-20s%-15s%-15s%n","i:", "Description: ", "Growth Enhancement: ", "Price: ");
							System.out.println("-------------------------------------------------------");
							
							//Print all the crop items that are available in the store
							items = store.getItems();
							printCropItems(items);
							
							//Display
							System.out.println("*******************************************************");
							System.out.println();
							System.out.println("Enter the id of the item you would like to buy, alternatively enter 0 to go back. \n");
							System.out.print("Please enter an option: ");
							
							//Input
							option = sc.nextInt();
							sc.nextLine();
							
							//operations
							if (option == 0) {
								itemsForSaleMenu = false;
								break;
							}
							
							if(option != 0 && store.itemExists(option) == true) {
								item = store.fetchItem(option);
								farm.addItem(item);
								farm.updateBalance(-item.getPrice());
								store.sellItem(option);
							}
						}
						
						//Stop viewing farming supplies for sale
						break;
						
					//View owned farming supplies	
					case 4:
						
						inventoryMenu = true;
						while(inventoryMenu) {
							
							//Display
							System.out.println("Inventory");
							System.out.println("------------------------------------------");
							System.out.println("Food items");
							System.out.println("******************************************");
							System.out.printf( "%-6s%-15s%-20s%n","id:", "Description: ", "Health Points: ");
							System.out.println("------------------------------------------");
							
							//Print food items that are available in inventory
							items = farm.getInventory();
							printFoodItems(items);
							
							//Display
							System.out.println("****************************************** \n");
							System.out.println("Crop items");
							System.out.println("******************************************");
							System.out.printf( "%-6s%-15s%-20s%n","id:", "Description: ", "Growth Enhancement: ");
							System.out.println("------------------------------------------");
							
							//Print crop items that are available in inventory
							printCropItems(items);
							
							//Display
							System.out.println("****************************************** \n");
							System.out.println("Enter 0 to go back.");
							System.out.println();
							System.out.print("Please enter an option: ");
							
							//Input
							option = sc.nextInt();
							sc.nextLine();
							
							//Operations
							if (option == 0) {
								inventoryMenu = false;
							}
						}
						
						System.out.println();
						//Stop viewing owned farm items
						break;
					}
				}
				
				//Stop viewing the store menu
				break;
				
			//Take an action
			case 4:
				
				actionMenu = true;
				while(actionMenu) {
					
					//Display and input
					System.out.print(actionMenuOptions);
					option = sc.nextInt();
					sc.nextLine();
					
					switch(option) {
					//Go back
					case 0:
						
						actionMenu = false;
						break;
						
					//Tend to crops
					case 1:
						
						cropActionMenu = true;
						while(cropActionMenu) {
							
							//Display and Input
							System.out.print(cropActionMenuOptions);
							option = sc.nextInt();
							sc.nextLine();
						
						switch(option) {
						
						//Go back
						case 0:
							
							cropActionMenu = false;
							break;
							
						//Water crops
						case 1:
							
							// - fields refers to each group of crop e.g. the mango field (all the mangos), the spinach field (all the spinach)
							fieldsMenu = true;
							while(fieldsMenu) {
								
								//Display
								System.out.print(fieldSelectMenu);
								
								
									
									//Input
									option = sc.nextInt();
									System.out.println();
									sc.nextLine();
									
									
									switch(option) {
									
									//Go back
									case 0:
										
										fieldsMenu = false;
										break;
									
									//This block determines which field the user 
									//has selected in the predefined list of fields ('cropSelectMenu') ---> these can be buttons
									case 1:
										species = cropSpecies.get(0).getSpecies();
										break;
									case 2:
										species = cropSpecies.get(1).getSpecies();
										break;
									case 3:
										species = cropSpecies.get(2).getSpecies();
										break;
									case 4:
										species = cropSpecies.get(3).getSpecies();
										break;
									case 5:
										species = cropSpecies.get(4).getSpecies();
										break;
									case 6:
										species = cropSpecies.get(5).getSpecies();
										break;
									}
									
									//Operations
									if(farm.ownsCrop(species) == true) {
										if (actionsRemaining > 0) {
											//Water that species
											farm.waterCrop(species);
											actionsRemaining--;
										System.out.printf("\t - The %s field has been watered. %n", species);
										} else {
											System.out.println("\n\t - You've used all your energy for today. \n");
											System.out.print("Please enter an option: ");
											break;
										}
									} else {
										//The farmer does not own that crop, display an error
										System.out.printf("\t - You do not own any %s.%n", species);
										System.out.println();
										System.out.print("Please enter an option: ");
									}
								} 
							
							//Stop displaying the list of fields 
							break;
						
						//Harvest crops
						case 3:
							
							//check if any crops are ready for harvest
							if (farm.cropsReady()) {
								farm.harvestCrop();
								System.out.println("\n\t - All your crops that were ready have been harvested...");
							} else {
								System.out.println();
								System.out.println("\t None of your crops are ready for harvest...");
								System.out.println();
							}
							
							break;
						
						//Use crop item
						case 2:
							
							//View owned crop items
							inventoryMenu = true;
							while(inventoryMenu) {
								
								//Display
								System.out.println("Crop items");
								System.out.println("******************************************");
								System.out.printf( "%-6s%-15s%-20s%n","id:", "Description: ", "Growth Enhancement: ");
								System.out.println("------------------------------------------");
								
								//Print all the crop items that are available in inventory
								items = farm.getInventory();
								printCropItems(items);
								
								//Display
								System.out.println("****************************************** \n");
								System.out.println("Enter 0 to go back.");
								System.out.println();
								System.out.print("Please enter an option: ");
								
								//Input
								option = sc.nextInt();
								sc.nextLine();
								if (option == 0) {
									inventoryMenu = false;
								}
								
								//Fetch the item from farm inventory
								item = farm.fetchItem(option);
								//If the id entered by the user is indeed an existing id...
								if (item != null) {
										
									//If the id entered is the id of a crop item...
									if (!item.isFoodItem()) {
										
										// ---> show the crop field menu as before...
											
										fieldsMenu = true;
										while(fieldsMenu) {
											
											//Display
											System.out.println();
											System.out.println("Please select a field to apply the item to:");
											System.out.println();
											System.out.print(fieldSelectMenu);
											
											//Input
											option = sc.nextInt();
											System.out.println();
											sc.nextLine();
											
											//This block determines which field the user 
											//has selected in the predefined list of fields ('cropSelectMenu') ---> these can be buttons
											switch(option) {
											case 0:
												fieldsMenu = false;
												break;
											case 1:
												species = cropSpecies.get(0).getSpecies();
												break;
											case 2:
												species = cropSpecies.get(1).getSpecies();
												break;
											case 3:
												species = cropSpecies.get(2).getSpecies();
												break;
											case 4:
												species = cropSpecies.get(3).getSpecies();
												break;
											case 5:
												species = cropSpecies.get(4).getSpecies();
												break;
											case 6:
												species = cropSpecies.get(5).getSpecies();
												break;
											}
											
											//Operations
											if(farm.ownsCrop(species) == true) {
												if (actionsRemaining > 0) {
													//can safely cast item because we already validated it is crop item above
													farm.tendCrop(species, (CropItem) item);
												
													actionsRemaining--;
													System.out.printf("\t - The %s field has been tended to with a %s item. %n", species, item.getName());
							
												} else {
													System.out.println("\n\t - You've used all your energy for today. \n");
													System.out.print("Please enter an option: ");
													break;
												}
											
											} else {
												//The farmer does not own that crop, display an error
												System.out.printf("\t - You do not own any %s.%n", species);
												System.out.println();
												System.out.print("Please enter an option: ");
											}
											
											
										}
											
									} else {
										//the id that was input was not shown in the list, it's an id of a crop item
										System.out.println();
										System.out.println(" The id of the item you entered is not a crop item.");
										System.out.println();
										System.out.print("Please enter an option: ");
									}
									
								}
								
							}
							
							System.out.println();
							
							//Stop showing the list of fields
							break;
							
						}
					}
						//Stop displaying the cropAction menu
						break;
					
					//Tend to animals
					case 2:
						
						animalActionMenu = true;
						while(animalActionMenu) {
							
							//Display and input
							System.out.print(animalActionMenuOptions);
							option = sc.nextInt();
							sc.nextLine();
							
							
							switch(option) {
							
							//Go back
							case 0:
								
								animalActionMenu = false;
								break;
							
							//Feed animals
							case 1:
							
								inventoryMenu = true;
								while(inventoryMenu) {
									
									
										//Display
										System.out.println("Food items");
										System.out.println("------------------------------------------");
										System.out.printf( "%-6s%-15s%-20s%n","i:", "Description: ", "Health Points: ");
										System.out.println("------------------------------------------");
										
										//Print the food items that are available in inventory
										items = farm.getInventory();
										printFoodItems(items);
										
										//Display
										System.out.println();
										System.out.println("Enter 0 to go back.");
										System.out.println();
										System.out.print("Please enter an option: ");
										
										//Input
										option = sc.nextInt();
										sc.nextLine();
										
										//Operations
										if (option == 0) {
											inventoryMenu = false;
											break;
										}
										
										
										if (option - 1 < foodItems.size()) {
											
											
											//Fetch the item from farm inventory
											item = farm.fetchItem(option);
											if (item != null) {
												if (!item.isFoodItem()) {
													if (actionsRemaining > 0) {
														//Can safely cast item to food item because it's already validated as food item above
														farm.feedAnimals((FoodItem) item);
														farm.removeItem(option);
														actionsRemaining--;
														System.out.printf("%n\t - The animals have all been fed with a %s item.%n", item.getName());
														break;
														
													} else {
														System.out.println();
														System.out.println(" You've used all your actions for today!");
														System.out.println();
														System.out.print("Please enter an option: ");
													}
												}
											}
										}
									
									
								}
								
								System.out.println();
								//Stop displaying animal action menu
								break;
							
							//Play animals
							case 2:
								
								if (actionsRemaining > 0) {
									farm.playAnimals();
									actionsRemaining--;
								} else {
									System.out.println();
									System.out.println(" You've used all your actions for today!");
									System.out.println();
									System.out.print("Please enter an option: ");
								}
								
								break;
								
							}
						}
						
						break;
						
					//Tend to farm
					case 3:
						
						farmActionMenu = true;
						while(farmActionMenu) {
							
							//Display and Input
							System.out.print(farmActionMenuOptions);
							option = sc.nextInt();
							sc.nextLine();
							
							switch(option) {
							
							//Go back
							case 0:
								farmActionMenu = false;
								break;
							}
						}
						
						break;
						
					case 4:
						
						break;
					}
				}
				
				break;
				
			//Skip to next day
			case 5:
				day--;
				if(day > 0) {
					actionsRemaining = 2;
					//give animal money TODO: decide threshold for happy animal bonus
					for(int i = 0; i < farm.getAnimals().size(); i++) {
						if(farm.getAnimals().get(i).getHappiness() >= 8) {
							farm.updateBalance(farm.getAnimals().get(i).getDailyBonus());
						}
					}
					//lower animal happiness & health TODO: decide health/happiness lowering factor
					/*
					 * Note: 
					 * When a farmer plays with an animal it's happiness is increased by it's the farms animalHappinessFactor 
					 */
					for(int i = 0; i < farm.getAnimals().size(); i++) {
						farm.getAnimals().get(i).updateHealth(-1);
						farm.getAnimals().get(i).updateHappiness(-1);
					}
					//reduce days until mature for crops
					for(int i = 0; i < farm.getCrops().size(); i++) {
						if(farm.getCrops().get(i).getDaysUntilMature() > 0) farm.getCrops().get(i).reduceDaysUntilMature(1);
					}
					
					//populateStore requires day because it creates new crop items which require a birthday
					populateStore(store, animalSpecies, cropSpecies, foodItems, cropItems, day);
					System.out.println("You now have " + day + " days remaining.");
				} else {
					playingGame = false;
				}
				break;
			case 10:
				playingGame = false;
				break;
			default:
				break;
			}
			
		}
		sc.close();
		//-----------------------------------------------------------------------------------------------------------------
		//END game
	}
}
