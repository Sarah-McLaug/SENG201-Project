package main;
import java.util.ArrayList;
import java.util.Scanner;


public class Game {
	private int duration;
	private int actionCount;
	private Store store;
	
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
		int day = 5;
		
		Farm farm = new Farm(farmName, farmType, new Farmer(farmerName, age));
		Store store = new Store();
	
		//Define crop species
		ArrayList <String> cropSpecies = new ArrayList <String>();
		cropSpecies.add("banana");
		cropSpecies.add("corn");
		cropSpecies.add("kiwi");
		cropSpecies.add("kumera");
		cropSpecies.add("mango");
		cropSpecies.add("spinach");
		
		//Define animal species
		ArrayList <String> animalSpecies = new ArrayList <String>();
		animalSpecies.add("llama");
		animalSpecies.add("koala");
		animalSpecies.add("panda");
		
		
		for(int i = 0; i < 10; i++) {
			//TODO: add in appropriate values for crops/animals
			store.addCrop(new Crop("banana", 100, 100, 2, 10, farm.getCropGrowthFactor()));
			store.addCrop(new Crop("corn", 100, 100, 3, 10, farm.getCropGrowthFactor()));
			store.addCrop(new Crop("kiwi", 100, 100, 2, 10, farm.getCropGrowthFactor()));
			store.addCrop(new Crop("kumera", 100, 100, 2, 10, farm.getCropGrowthFactor()));
			store.addCrop(new Crop("mango", 100, 100, 2, 10, farm.getCropGrowthFactor()));
			store.addCrop(new Crop("spinach", 100, 100, 3, 10, farm.getCropGrowthFactor()));
			
			store.addAnimal(new Animal("Llama", 150, 35, 7.3, 9.0));
			
			if(i%2 == 0) store.addItem(new CropItem("Fertilizer", 85, 1));
			else store.addItem(new FoodItem("Broccoli", 76, 2.0));
		}
		//-----------------------------------------------------------------------------------------------------------------
		//END Initialisation
		
		System.out.println();
		
		//BEGIN game
		//-----------------------------------------------------------------------------------------------------------------
		System.out.println("The game has begun! You have " + day + " days to make as much money as possible!");
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
		
		
	
		//in game variables 
		
		ArrayList <Crop> ownedCrops = new ArrayList<Crop>();
		ArrayList <Animal> ownedAnimals = new ArrayList<Animal>();
		String selectedSpecies = "";
		boolean farmerOwnsCrop = false;
		ArrayList <Item> inventory = new ArrayList<Item>();
		Item item;
		int count;
		
		while(playingGame) {
			System.out.printf(gameMenuOptions, actionsRemaining);
			option = sc.nextInt();
			sc.nextLine();
			switch(option) {
			case 1:
				//View the status of the farm's crops and animals
				ownedCrops = farm.getCrops();
				ownedAnimals = farm.getAnimals();
				System.out.println();
				System.out.println("\t \t Investment Status ");
				System.out.println("--------------------------------------------------");
				if(farm.getAnimals().size() == 0) System.out.println("\nYou own no animals.\n");
				else {
					System.out.println("Animals");
					System.out.println("**************************************************");
					System.out.printf(format3, "Species:", "Happiness/10:", "Health/10:");
					System.out.println("--------------------------------------------------");
					for(int i = 0; i < ownedAnimals.size(); i++) {
						System.out.printf(format3, ownedAnimals.get(i).getSpecies(), ownedAnimals.get(i).getHappiness(), ownedAnimals.get(i).getHealth());
						System.out.println("--------------------------------------------------");
					}
					System.out.println("**************************************************");
					System.out.println();
					
				}
				if(farm.getCrops().size() == 0) System.out.println("You own no crops \n");
				else {
					System.out.println("Crops");
					System.out.println("**************************************************");
					System.out.printf(format3, "Species:", "Time until mature:", "Age:");
					for(int i = 0; i < ownedCrops.size(); i++) {
						System.out.println("--------------------------------------------------");
						System.out.printf(format3, ownedCrops.get(i).getSpecies() , ownedCrops.get(i).getDaysUntilMature(), ownedCrops.get(i).getAge(day));
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
							System.out.println("Crops for sale");
							System.out.println("------------------------------------------");
							System.out.printf( "%-6s%-10s%-20s%-10s%n","i:", "Species: ", "Days to mature: ", "Price: ");
							System.out.println("------------------------------------------");
							for(int i = 0; i < store.getCrops().size(); i++) {
								Crop c = store.getCrops().get(i);
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
								Crop c = store.getCrops().get(option-1);
								farm.addCrop(c, day);
								farm.updateBalance(-c.getPurchasePrice());
								store.sellCrop(c);
							}
						}
						break;
					case 2:
						//View animals for sale
						animalsForSaleMenu = true;
						while (animalsForSaleMenu) {
							System.out.println("Animals for sale");
							System.out.println("------------------------------------------");
							System.out.printf( "%-6s%-10s%-20s%-10s%n","i:", "Species: ", "Happiness: ", "Price: ");
							System.out.println("------------------------------------------");
							for(int i = 0; i < store.getAnimals().size(); i++) {
								Animal a = store.getAnimals().get(i);
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
							if(option != 0 && option <= store.getAnimals().size()+1) {
								Animal a = store.getAnimals().get(option-1);
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
							//Should keep food/crop items separate in the GUI
							System.out.println("Items for sale");
							System.out.println("-------------------------------------------------------------");
							System.out.printf( "%-6s%-15s%-20s%-10s%n","i:", "Description: ", "Health Points: ", "Growth Enhancement: ");
							System.out.println("-------------------------------------------------------------");
							for(int i = 0; i < store.getItems().size(); i++) {
								item = store.getItems().get(i);
								if (item.isFoodItem()) {
									System.out.printf("%-6s%-15s%-20s%-10s%n", "("+ (i+1) + ")", item.getName(), item.getBenefit(), 0);
								} else {
									System.out.printf("%-6s%-15s%-20s%-10s%n", "("+ (i+1) + ")", item.getName(), 0, item.getBenefit());
								}
								
								/*System.out.println("("+ (i+1) + ")" + item.getName() //TODO: I think we could use an abstract implementation to
													+ ", Maturity Time: " 			   //get the item's specific bonus. need to figure out
													+ item.getHappiness() 			   //best way to do so
													+ ", Cost: " 
													+ item.getPurchasePrice()); */
								System.out.println("-------------------------------------------------------------");
							}
							System.out.println();
							System.out.println("Enter the index of the item you would like to buy, alternatively enter 0 to go back. \n");
							System.out.print("Please enter an option: ");
							option = sc.nextInt();
							sc.nextLine();
							if (option == 0) {
								itemsForSaleMenu = false;
							}
							if(option != 0 && option <= store.getItems().size()+1) {
								item = store.getItems().get(option-1);
								farm.addItem(item);
								farm.updateBalance(-item.getPrice());
								store.sellItem(item);
							}
						}
						break;
					case 4:
						//View owned farming supplies
						inventoryMenu = true;
						while(inventoryMenu) {
							inventory = farm.getInventory();
							System.out.println("Inventory");
							System.out.println("------------------------------------------");
							System.out.println("Food items");
							System.out.println("******************************************");
							System.out.printf( "%-6s%-15s%-20s%n","i:", "Description: ", "Health Points: ");
							System.out.println("------------------------------------------");
							for(int i = 0; i < inventory.size(); i++) {
								item = inventory.get(i);
								if (item.isFoodItem()) {
									System.out.printf("%-6s%-15s%-20s%n", "("+ (i+1) + ")", inventory.get(i).getName(), inventory.get(i).getBenefit());
									System.out.println("------------------------------------------");
								}
							}
							System.out.println("****************************************** \n");
							System.out.println("Crop items");
							System.out.println("******************************************");
							System.out.printf( "%-6s%-15s%-20s%n","i:", "Description: ", "Growth Enhancement: ");
							System.out.println("------------------------------------------");
							for(int i = 0; i < inventory.size(); i++) {
								item = inventory.get(i);
								if (!item.isFoodItem()) {
									System.out.printf("%-6s%-15s%-20s%n", "("+ (i+1) + ")", inventory.get(i).getName(), inventory.get(i).getBenefit());
									System.out.println("------------------------------------------");
								}
							}
							System.out.println("****************************************** \n");
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
									selectedSpecies = cropSpecies.get(0);
									break;
								case 2:
									selectedSpecies = cropSpecies.get(1);
									break;
								case 3:
									selectedSpecies = cropSpecies.get(2);
									break;
								case 4:
									selectedSpecies = cropSpecies.get(3);
									break;
								case 5:
									selectedSpecies = cropSpecies.get(4);
									break;
								case 6:
									selectedSpecies = cropSpecies.get(5);
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
								inventory = farm.getInventory();
								System.out.println("Crop Items");
								System.out.println("------------------------------------------");
								System.out.printf( "%-6s%-15s%-20s%n","i:", "Description: ", "Growth Enhancement: ");
								System.out.println("------------------------------------------");
								count = 0;
								for(int i = 0; i < farm.getInventory().size(); i++) {
									
									if (!inventory.get(i).isFoodItem()) {
									System.out.printf("%-6s%-15s%-20s%n", "("+ (i+1) + ")", inventory.get(i).getName(), inventory.get(i).getBenefit());
									count++;
									}
								}
								System.out.println();
								System.out.println("Enter the index of the item you want to use.");
								System.out.println();
								System.out.print("Please enter an option: ");
								option = sc.nextInt();
								if (option == 0) {
									inventoryMenu = false;
								}
								sc.nextLine();
								
								//In GUI option can be the id of the item the user wants to use
								if (option < count && option >=0) {
									if (actionsRemaining > 0) {
										//find the item in the list
										//remove it from the inventory
										//apply it's benefits
										actionsRemaining--;
									} else {
										System.out.println();
										System.out.println(" You've used all your actions for today!");
										System.out.println();
										System.out.print("Please enter an option: ");
										break;
									}
								}
								
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
							case 1:
								//Feed animals
								if (actionsRemaining > 0) {
									inventoryMenu = true;
									while(inventoryMenu) {
										inventory = farm.getInventory();
										System.out.println("Food items");
										System.out.println("------------------------------------------");
										System.out.printf( "%-6s%-15s%-20s%-10s%n","i:", "Description: ", "Health Points: ");
										System.out.println("------------------------------------------");
										for(int i = 0; i < farm.getInventory().size(); i++) {
											
											if (inventory.get(i).isFoodItem()) {
											System.out.printf("%-6s%-15s%-20s%-10s%n", "("+ (i+1) + ")", inventory.get(i).getName(), inventory.get(i).getBenefit());
											}
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
										/*
										if (option < in range of foodItems) {
											
										}
										*/
									}
									System.out.println();
									
									
									
								} else {
									System.out.println();
									System.out.println(" You've used all your actions for today!");
									System.out.println();
									System.out.print("Please enter an option: ");
								}
								break;
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
					//TODO: if we have time, this is where random events could occur
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
