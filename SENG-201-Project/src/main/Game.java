package main;
import java.util.ArrayList;
import java.util.Scanner;


public class Game {
	private int duration;
	private int actionCount;
	private Store store;
	private Farm farm;
	
	//default constructor TODO: do we need this??
	public Game() {
		duration = 10;
		actionCount = 2;
	}
	
	public Game(int duration, int actionCount) {
		this.duration = duration;
		this.actionCount = actionCount;
	}
	
	//BEGIN getters and setters
	public int getActionCount() {
		return this.actionCount;
	}
	
	public void setActionCount(int actionCount) {
		this.actionCount = actionCount;
	}
	
	public int getDuration() {
		return this.duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public Farm getFarm() {
		return farm;
	}
	
	public void setFarm(Farm farm) {
		this.farm = farm;
	}
	//END getters and setters
	
	//BEGIN methods
	public void addActionCount() {
		this.actionCount = this.actionCount + 1;
	}
	
	public void skipDay() {
		this.duration = this.duration - 1;
	}
	
	/*
	public double score(int numCrops, int numAnimals, double animalStatus, double moneyEarned) {
		insert some func here
	}
	*/
	//END methods
	
	public static void populateStore(Store store, ArrayList<Animal> animalSpecies, ArrayList<Crop> cropSpecies, 
			ArrayList<Item> foodItems, ArrayList<Item> cropItems) {
		
		//TODO: make these random in a set range
		int amountCrops = 5;
		int amountAnimals = 1;
		int amountFoodItems = 3;
		int amountCropItems = 3;
		
		for (int j = 0; j < cropSpecies.size(); j++) {
			for (int i = 0; i < amountCrops; i++) {
				store.addCrop(cropSpecies.get(j));
			}
		}
		
		for (int j = 0; j < animalSpecies.size(); j++) {
			for (int i = 0; i < amountAnimals; i++) {
				store.addAnimal(animalSpecies.get(j));
			}
		}
		
		for (int j = 0; j < foodItems.size(); j++) {
			for (int i = 0; i < amountFoodItems; i++) {
				store.addItem(foodItems.get(j));
			}
		}
		
		for (int j = 0; j < cropItems.size(); j++) {
			for (int i = 0; i < amountCropItems; i++) {
				store.addItem(cropItems.get(j));
			}
		}
	}
	
	public static void main(String[] args) {
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
		*/
		
		//Defaults for easy testing:
		String farmName = "Incognito Farms";
		String farmType = "friendly";
		String farmerName = "Lollipop Chainsaw";
		int age = 22;
		int days = 5;
		
		Farm farm = new Farm(farmName, farmType, new Farmer(farmerName, age));
		int cropGrowthBonus = farm.getCropGrowthBonus();
		Store store = new Store();
	
		//Define game elements
		final ArrayList <Crop> cropSpecies = new ArrayList <Crop>();
		cropSpecies.add(new Crop("banana", 1, 1, 1, 1, cropGrowthBonus));
		cropSpecies.add(new Crop("corn", 1, 1, 1, 1, cropGrowthBonus));
		cropSpecies.add(new Crop("kiwi", 1, 1, 1, 1, cropGrowthBonus));
		cropSpecies.add(new Crop("kumera", 1, 1, 1, 1, cropGrowthBonus));
		cropSpecies.add(new Crop("mango", 1, 1, 1, 1, cropGrowthBonus));
		cropSpecies.add(new Crop("spinach", 1, 1, 1, 1, cropGrowthBonus));
		
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
		cropItems.add(new CropItem("pestiside", 1, 1));
		
		//fill the store with the elements
		populateStore(store, animalSpecies, cropSpecies, foodItems, cropItems);
		
		//-----------------------------------------------------------------------------------------------------------------
		//END Initialisation
		
		System.out.println();
		
		//BEGIN game
		//-----------------------------------------------------------------------------------------------------------------
		System.out.println("The game has begun! You have " + days + " days to make as much money as possible!");
		System.out.println("----------------------------------------------------------------------");
		System.out.println();
		System.out.println("\t (Enter the index of an option to execute.)");
		System.out.println();
		
		
		boolean playingGame = true;
		
		int option = 0;
		int actionsRemaining = 2;
		
		//CLI menus
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
		
		String gameMenuOptions = "\n" + farm.getName()
				+ "\n---------------------------------------------------"
				+ "\n(1) View the status of the farm's crops and animals"
				+ "\n(2) View the status of the farm"
				+ "\n(3) Go to the store"
				+ "\n(4) Take an action (You have %s actions remaining)"
				+ "\n(5) Move to the next day"
				+ "\n(10) Quit game "
				+ "\n"
				+ "\nPlease enter an option: ";
		
		String storeMenuOptions = "\n" + "Store"
				+ "\n-----------------------------------"
				+ "\n(1) View crops for sale"
				+ "\n(2) View animals for sale"
				+ "\n(3) View farming supplies for sale"
				+ "\n(4) View owned farming supplies"
				+ "\n(0) Go back"
				+ "\n"
				+ "\nPlease enter an option: ";
		
		
		String actionMenuOptions = "\n" + "Actions"
				+ "\n-----------------------------------"
				+ "\n(1) Tend to crops"
				+ "\n(2) Tend to animals"
				+ "\n(3) Tend to farm"
				+ "\n(0) Go back"
				+ "\n"
				+ "\nPlease enter an option: ";
		
		String cropActionMenuOptions = "\n" + "Crop Actions"
				+ "\n-----------------------------------"
				+ "\n(1) Water crops"
				+ "\n(2) Use crop item"
				+ "\n(3) Harvest crops"
				+ "\n(0) Go back"
				+ "\n"
				+ "\nPlease enter an option: ";
		
		String cropSelectMenu = "\n" + "Crops"
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
		
		String animalActionMenuOptions = "\n" + "Animal Actions"
				+ "\n-----------------------------------"
				+ "\n(1) Feed animals"
				+ "\n(2) Play with animals"
				+ "\n(0) Go back"
				+ "\n"
				+ "\nPlease enter an option: ";
		
		String farmActionMenuOptions = "\n" + "Farm Actions"
				+ "\n-----------------------------------"
				+ "\n(1) ???"
				+ "\n(2) ???"
				+ "\n(0) Go back"
				+ "\n"
				+ "\nPlease enter an option: ";
		
		
		String format3 = "%-20s%-20s%s%n";
		String format2 = "%-20s%-20s%n";
		
		
	
		//in game variables 
		
		ArrayList <Crop> ownedCrops = new ArrayList<Crop>();
		String selectedSpecies = "";
		int cropSize = 0;
		boolean farmerOwnsCrop = false;
		ArrayList <Item> inventory = new ArrayList<Item>();
<<<<<<< HEAD
		ArrayList <Item> storeItems = new ArrayList<Item>();
		ArrayList <Crop> storeCrops = new ArrayList<Crop>();
		ArrayList <Animal> storeAnimals = new ArrayList<Animal>();
		
		
		Item item;
		int count;
		Crop c;
		Animal a;
		int quantity = 0;
=======
>>>>>>> parent of 36ebf6b... abstract class for items, commenting, item processing structure
		
		while(playingGame) {
			System.out.printf(gameMenuOptions, actionsRemaining);
			option = sc.nextInt();
			sc.nextLine();
			switch(option) {
			case 1:
				//View the status of the farm's crops and animals
				System.out.println();
				System.out.println("\t \t Investment Status ");
				System.out.println("--------------------------------------------------");
				if(farm.getAnimals().size() == 0) System.out.println("\nYou own no animals.\n");
				else {
					System.out.println("Animals");
					System.out.println("**************************************************");
					System.out.printf(format3, "Species:", "Happiness/10:", "Health/10:");
					System.out.println("--------------------------------------------------");
					for(int i = 0; i < farm.getAnimals().size(); i++) {
						System.out.printf(format3, farm.getAnimals().get(i).getSpecies(), farm.getAnimals().get(i).getHappiness(), farm.getAnimals().get(i).getHealth());
						System.out.println("--------------------------------------------------");
					}
					System.out.println("**************************************************");
					System.out.println();
					
				}
				if(farm.getCrops().size() == 0) System.out.println("You own no crops \n");
				else {
					System.out.println("Crops");
					System.out.println("**************************************************");
					System.out.printf(format2, "Species:", "Time until mature:");
					for(int i = 0; i < farm.getCrops().size(); i++) {
						System.out.println("--------------------------------------------------");
						System.out.printf(format2, farm.getCrops().get(i).getSpecies() , farm.getCrops().get(i).getDaysUntilMature() );
						System.out.println("--------------------------------------------------");
					}
					System.out.println("**************************************************");
				}
				System.out.println("--------------------------------------------------");
				System.out.println();
				break;
			case 2:
				//View the status of the farm
				System.out.println(farm.getName() + " has $" + farm.getBalance() + " in the bank.");
				System.out.println();
				break;
			case 3:
				//Go to the store
				
				storeMenu = true;
				while(storeMenu) {
					System.out.print(storeMenuOptions);
					option = sc.nextInt();
					sc.nextLine();
					System.out.println();
					switch(option) {
					case 0:
						//Go back
						storeMenu = false;
						break;
					case 1:
						//View crops for sale
						cropsForSaleMenu = true;
						while (cropsForSaleMenu) {
							storeCrops = store.getCrops();
							System.out.println("Crops for sale");
							System.out.println("------------------------------------------");
							System.out.printf( "%-6s%-10s%-20s%-10s%n","i:", "Species: ", "Days to mature: ", "Price: ");
							System.out.println("------------------------------------------");
							for(int i = 0; i < storeCrops.size(); i++) {
								c = storeCrops.get(i);
								System.out.printf("%-6s%-10s%-20s%-10s%n", "("+ (i+1) + ")", c.getSpecies(), c.getDaysUntilMature(), c.getPurchasePrice());
								System.out.println("------------------------------------------");
							}
							System.out.println();
							System.out.println("Enter the index of the crop you would like to buy, alternatively enter 0 to go back. \n");
							System.out.print("Please enter an option: ");
							option = sc.nextInt();
							sc.nextLine();
							if (option == 0) {
								cropsForSaleMenu = false;
							}
							if(option != 0 && option <= store.getCrops().size()+1) {
<<<<<<< HEAD
								c = storeCrops.get(option-1);
								farm.addCrop(c, day);
=======
								Crop c = store.getCrops().get(option-1);
								farm.addCrop(c);
>>>>>>> parent of 36ebf6b... abstract class for items, commenting, item processing structure
								farm.updateBalance(-c.getPurchasePrice());
								store.sellCrop(c);
							}
						}
						break;
					case 2:
						//View animals for sale
						animalsForSaleMenu = true;
						while (animalsForSaleMenu) {
							storeAnimals = store.getAnimals();
							System.out.println("Animals for sale");
							System.out.println("------------------------------------------");
							System.out.printf( "%-6s%-10s%-20s%-10s%n","i:", "Species: ", "Happiness: ", "Price: ");
							System.out.println("------------------------------------------");
							
							for(int i = 0; i < storeAnimals.size(); i++) {
								a = storeAnimals.get(i);
								System.out.printf("%-6s%-10s%-20s%-10s%n", "("+ (i+1) + ")", a.getSpecies(), a.getHappiness() , a.getPurchasePrice());
								System.out.println("------------------------------------------");
							}
							System.out.println();
							System.out.println("Enter the index of the animal you would like to buy, alternatively enter 0 to go back. \n");
							System.out.print("Please enter an option: ");
							option = sc.nextInt();
							sc.nextLine();
							if (option == 0) {
								animalsForSaleMenu = false;
							}
							if(option != 0 && option <= storeAnimals.size()+1) {
								a = storeAnimals.get(option-1);
								farm.addAnimal(a);
								farm.updateBalance(-a.getPurchasePrice());
								store.sellAnimal(a);
							}
						}
						break;
					case 3:
						//View farming supplies for sale
						itemsForSaleMenu = true;
						while (itemsForSaleMenu) {
<<<<<<< HEAD
							//Should keep food/crop items separate in the GUI
							storeItems = store.getFoodItems();
							System.out.println("Items for sale");
							System.out.println("-------------------------------------------------------------");
							System.out.println();
							//BEGIN food items
							//----------------------------------------------------------------------------------------------------------------------------
							System.out.println("Food items");
							System.out.println("**************************************************");
							System.out.printf( "%-6s%-20s%-15s%-20s%n", "i:", "Description: ", "Health Points: ", "Quantity:");
							System.out.println("--------------------------------------------------");
							
							for (int j = 0; j < foodItems.size(); j++) {
								item = foodItems.get(j);
								System.out.printf( "%-6s%-20s%-15s%-20s%n","(" + item.getId() + ")", item.getName(), item.getBenefit(), store.getItemStockCount(item.getName()));
							}
							System.out.println("**************************************************");
							System.out.println();
							//----------------------------------------------------------------------------------------------------------------------------
							//END food items
							
							//BEGIN crop items
							//----------------------------------------------------------------------------------------------------------------------------
							storeItems = store.getCropItems();
							System.out.println("Crop items");
							System.out.println("*******************************************************");
							System.out.printf( "%-6s%-20s%-15s%-20s%n","i:", "Description: ", "Growth Enhancement: ", "Quantity:");
							System.out.println("-------------------------------------------------------");
							
							for (int j = 0; j < cropItems.size(); j++) {
								item = cropItems.get(j);
								System.out.printf( "%-6s%-20s%-15s%-20s%n", "(" + item.getId() + ")", item.getName(), item.getBenefit(), store.getItemStockCount(item.getName()));
=======
							System.out.println("Items for sale");
							System.out.println("------------------------------------------");
							System.out.printf( "%-6s%-15s%-20s%-10s%n","i:", "?: ", "?: ", "?: ");
							System.out.println("------------------------------------------");
							for(int i = 0; i < store.getItems().size(); i++) {
								Item item = store.getItems().get(i);
								System.out.printf("%-6s%-15s%-20s%-10s%n", "("+ (i+1) + ")", item.getName(), "?", "?");
								
								/*System.out.println("("+ (i+1) + ")" + item.getName() //TODO: I think we could use an abstract implementation to
													+ ", Maturity Time: " 			   //get the item's specific bonus. need to figure out
													+ item.getHappiness() 			   //best way to do so
													+ ", Cost: " 
													+ item.getPurchasePrice()); */
								System.out.println("------------------------------------------");
>>>>>>> parent of 36ebf6b... abstract class for items, commenting, item processing structure
							}
							System.out.println("*******************************************************");
							System.out.println();
							//----------------------------------------------------------------------------------------------------------------------------
							//END crop items
							System.out.println("Enter the id of the item you would like to buy, alternatively enter 0 to go back. \n");
							System.out.print("Please enter an option: ");
							option = sc.nextInt();
							sc.nextLine();
							if (option == 0) {
								itemsForSaleMenu = false;
							}
							if(option != 0 && option <= store.getItems().size()+1) {
<<<<<<< HEAD
								item = store.getItems().get(option - 1);
=======
								Item item = store.getItems().get(option-1);
>>>>>>> parent of 36ebf6b... abstract class for items, commenting, item processing structure
								farm.addItem(item);
								farm.updateBalance(-item.getPrice());
								store.sellItem(option);
							}
						}
						break;
					case 4:
						//View owned farming supplies
						inventoryMenu = true;
						while(inventoryMenu) {
							System.out.println("Inventory");
							System.out.println("------------------------------------------");
<<<<<<< HEAD
							System.out.println("Food items");
							System.out.println("******************************************");
							System.out.printf( "%-6s%-15s%-20s%n","id:", "Description: ", "Health Points: ");
							System.out.println("------------------------------------------");
							for(int i = 0; i < inventory.size(); i++) {
								item = inventory.get(i);
								if (item.isFoodItem()) {
									System.out.printf("%-6s%-15s%-20s%n", "("+ inventory.get(i).getId() + ")", inventory.get(i).getName(), inventory.get(i).getBenefit());
									System.out.println("------------------------------------------");
								}
							}
							System.out.println("****************************************** \n");
							System.out.println("Crop items");
							System.out.println("******************************************");
							System.out.printf( "%-6s%-15s%-20s%n","id:", "Description: ", "Growth Enhancement: ");
							System.out.println("------------------------------------------");
							for(int i = 0; i < inventory.size(); i++) {
								item = inventory.get(i);
								if (!item.isFoodItem()) {
									System.out.printf("%-6s%-15s%-20s%n", "("+ inventory.get(i).getId() + ")", inventory.get(i).getName(), inventory.get(i).getBenefit());
									System.out.println("------------------------------------------");
								}
=======
							System.out.printf( "%-6s%-15s%-20s%-10s%n","i:", "?: ", "?: ", "?: ");
							System.out.println("------------------------------------------");
							for(int i = 0; i < farm.getOwnedItems().size(); i++) {
								System.out.printf("%-6s%-15s%-20s%-10s%n", "("+ (i+1) + ")", farm.getOwnedItems().get(i).getName(), "?", "?");
>>>>>>> parent of 36ebf6b... abstract class for items, commenting, item processing structure
							}
							System.out.println();
							System.out.println("Enter 0 to go back.");
							System.out.println();
							System.out.print("Please enter an option: ");
							option = sc.nextInt();
							if (option == 0) {
								inventoryMenu = false;
							}
							sc.nextLine();
						}
						System.out.println();
						break;
					
						
					default:
						break;
					}
				}
				break;
			case 4:
				
				actionMenu = true;
				while(actionMenu) {
					System.out.print(actionMenuOptions);
					option = sc.nextInt();
					sc.nextLine();
					switch(option) {
					case 0:
						actionMenu = false;
						break;
					case 1:
						//Tend to crops
						ownedCrops = farm.getCrops();
						cropActionMenu = true;
						while(cropActionMenu) {
							System.out.print(cropActionMenuOptions);
							option = sc.nextInt();
							sc.nextLine();
						
						switch(option) {
						case 0:
							//Go back
							cropActionMenu = false;
							break;
						case 1:
							//Water crops
							fieldsMenu = true;
							while(fieldsMenu) {
								
								System.out.print(cropSelectMenu);
								
								if (actionsRemaining > 0) {
								option = sc.nextInt();
								System.out.println();
								sc.nextLine();
								
								switch(option) {
								case 1:
									selectedSpecies = cropSpecies.get(0).getSpecies();
									break;
								case 2:
									selectedSpecies = cropSpecies.get(1).getSpecies();
									break;
								case 3:
									selectedSpecies = cropSpecies.get(2).getSpecies();
									break;
								case 4:
									selectedSpecies = cropSpecies.get(3).getSpecies();
									break;
								case 5:
									selectedSpecies = cropSpecies.get(4).getSpecies();
									break;
								case 6:
									selectedSpecies = cropSpecies.get(5).getSpecies();
									break;
								}
								
								//if farm option is not out of range
								if (option-1 <= cropSpecies.size()) {
									
								}
									//check that the farmer owns this crop
									for (int j = 0; j < farm.getCrops().size(); j++) {
										if (ownedCrops.get(j).getSpecies() == selectedSpecies) {
											farmerOwnsCrop = true;
											break;
										}
									}
								
									if (farmerOwnsCrop) {
										//water that crop
										farm.waterCrop(selectedSpecies);
										actionsRemaining--;
										farmerOwnsCrop = false;
									} else {
										System.out.println();
										System.out.printf(" You do not own any %s.%n", selectedSpecies);
										System.out.println();
										System.out.print("Please enter an option: ");
										break;
									}
								
								
								} else {
									System.out.println();
									System.out.println(" You've used all your actions for today!");
									System.out.println();
									System.out.print("Please enter an option: ");
									break;
								}
							}
							break;
						case 2:
							//Use crop item
							//View owned crop items
							inventoryMenu = true;
							while(inventoryMenu) {
								System.out.println("Inventory");
								System.out.println("------------------------------------------");
								System.out.printf( "%-6s%-15s%-20s%-10s%n","i:", "?: ", "?: ", "?: ");
								System.out.println("------------------------------------------");
								for(int i = 0; i < farm.getOwnedItems().size(); i++) {
									//TODO: determine how to differentiate crop items from animal items
									/*
									if (inventory.get(i).getPurpose() == for crops)
									System.out.printf("%-6s%-15s%-20s%-10s%n", "("+ (i+1) + ")", farm.getOwnedItems().get(i).getName(), "?", "?");
									*/
								}
								System.out.println();
								System.out.println("Enter 0 to go back.");
								System.out.println();
								System.out.print("Please enter an option: ");
								option = sc.nextInt();
								if (option == 0) {
									inventoryMenu = false;
								}
								sc.nextLine();
							}
							System.out.println();
							break;
							
						}
					}
						break;
					case 2:
						//Tend to animals
						
						animalActionMenu = true;
						while(animalActionMenu) {
							System.out.print(animalActionMenuOptions);
							option = sc.nextInt();
							sc.nextLine();
							switch(option) {
							case 0:
								animalActionMenu = false;
								break;
							}
						}
						break;
					case 3:
						farmActionMenu = true;
						while(farmActionMenu) {
							System.out.print(farmActionMenuOptions);
							option = sc.nextInt();
							sc.nextLine();
							switch(option) {
							case 0:
								farmActionMenu = false;
								break;
							}
						}
						//Tend to farm
						break;
					case 4:
						break;
					}
				}
				break;
			case 5:
				days--;
				if(days > 0) {
					actionsRemaining = 2;
					//give animal money TODO: decide threshold for happy animal bonus
					for(int i = 0; i < farm.getAnimals().size(); i++) {
						if(farm.getAnimals().get(i).getHappiness() >= 8) {
							farm.updateBalance(farm.getAnimals().get(i).getDailyBonus());
						}
					}
					//lower animal happiness & health TODO: decide health/happiness lowering factor
					for(int i = 0; i < farm.getAnimals().size(); i++) {
						farm.getAnimals().get(i).addHealth(-1);
						farm.getAnimals().get(i).raiseHappiness(-1);
					}
					//reduce days until mature for crops
					for(int i = 0; i < farm.getCrops().size(); i++) {
						if(farm.getCrops().get(i).getDaysUntilMature() > 0) farm.getCrops().get(i).reduceDaysUntilMature(1);
					}
					
					//random quantities defined in this funciton
					populateStore(store, animalSpecies, cropSpecies, foodItems, cropItems);
					/*
					//re-stock the store TODO: decide if this is how to re-stock the store
					//TODO: ---> for all the species/animals (defined above in (cropSpecies/animalSpecies)) restock each species by a random number from x to y
					store = new Store();
					for(int i = 0; i < 10; i++) {
						store.addCrop(new Crop("corn", 100, 100, 3, 10, farm.getCropGrowthFactor()));
						store.addCrop(new Crop("spinach", 100, 100, 3, 10, farm.getCropGrowthFactor()));
						store.addCrop(new Crop("kiwi", 100, 100, 2, 10, farm.getCropGrowthFactor()));
						store.addAnimal(new Animal("Llama", 150, 35, 7.3, 9.0));
						if(i%2 == 0) store.addItem(new CropItem("Fertilizer", 85, 1));
						else store.addItem(new FoodItem("Broccoli", 76, 2.0));
					}
					*/
					//TODO: if we have time, this is where random events could occur
					System.out.println("You now have " + days + " days remaining.");
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
