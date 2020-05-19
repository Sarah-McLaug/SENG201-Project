package main;
import java.util.ArrayList;
import java.util.Scanner;

import Farm.*;

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
		//Variables for handling data input and input defence 
		Scanner sc = new Scanner(System.in);
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
		
		//BEGIN Initialisation
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
		
		Farm farm = new Farm(farmName, farmType, new Farmer(farmerName, age));
		Store store = new Store();
		
		//stock the store
		for(int i = 0; i < 10; i++) {
			store.addCrop(new Crop("corn", 100, 100, 3, 10));
			store.addCrop(new Crop("spinach", 100, 100, 3, 10));
			store.addCrop(new Crop("kiwi", 100, 100, 2, 10));
			store.addAnimal(new Animal("Llama", 150, 35, 7.3, 9.0));
			if(i%2 == 0) store.addItem(new CropItem("Fertilizer", 85, 1));
			else store.addItem(new FoodItem("Broccoli", 76, 2.0));
		}
		//END Initialisation
		
		System.out.println();
		
		//BEGIN game
		System.out.println("The game has begun! You have " + days + " days to make as much money as possible!");
		System.out.println("----------------------------------------------------------------------");
		System.out.println();
		
		boolean playingGame = true;
		int option = 0;
		int actionsRemaining = 2;
		
		System.out.println("\t (Enter the index of an option to execute.)");
		System.out.println();
		
		String format3 = "%-20s%-20s%s%n";
		String format2 = "%-20s%-20s%n";
		String format4 = "%-20s%-20s%s%-20s%n";
		int selection;
		
		while(playingGame) {
			System.out.println(farm.getName()
					+ "\n---------------------------------------------------"
					+ "\n(1) View the status of the farm's crops and animals"
					+ "\n(2) View the status of the farm"
					+ "\n(3) Go to the store"
					+ "\n(4) Take an action (You have " + actionsRemaining + " action remaining"
					+ "\n(5) Move to next day"
					+ "\n(10) Quit game");
			System.out.println();
			System.out.print("Please enter an option: ");
			option = sc.nextInt();
			sc.nextLine();
			switch(option) {
			case 1:
				//View farm investments status
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
				System.out.println();
				System.out.println(farm.getName() + " has $" + farm.getBalance() + " in the bank.");
				System.out.println();
				break;
			case 3:
				System.out.println();
				boolean inStore = true;
				while(inStore) {
					int storeAction = 0;
					System.out.println("Store"
							+ "\n-----------------------------------"
							+ "\n(1) View crops for sale"
							+ "\n(2) View animals for sale"
							+ "\n(3) View farming supplies for sale"
							+ "\n(4) View owned farming supplies"
							+ "\n(5) Main menu");
					System.out.println();
					System.out.print("Please enter an option: ");
					storeAction = sc.nextInt();
					System.out.println();
					sc.nextLine();
					switch(storeAction) {
					case 1:
						int buying = -1;
						while (buying != 0) {
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
							buying = sc.nextInt();
							sc.nextLine();
							if(buying != 0 && buying <= store.getCrops().size()+1) {
								Crop c = store.getCrops().get(buying-1);
								farm.addCrop(c);
								farm.removeBalance(c.getPurchasePrice());
								store.sellCrop(c);
							}
						}
						System.out.println();
						break;
					case 2:
						buying = -1;
						while (buying != 0) {
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
							buying = sc.nextInt();
							sc.nextLine();
							if(buying != 0 && buying <= store.getAnimals().size()+1) {
								Animal a = store.getAnimals().get(buying-1);
								farm.addAnimal(a);
								farm.removeBalance(a.getPurchasePrice());
								store.sellAnimal(a);
							}
						}
						break;
					case 3:
						buying = -1;
						while (buying != 0) {
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
							}
							System.out.println();
							System.out.println("Enter the index of the animal you would like to buy, alternatively enter 0 to go back. \n");
							System.out.print("Please enter an option: ");
							buying = sc.nextInt();
							sc.nextLine();
							if(buying != 0 && buying <= store.getItems().size()+1) {
								Item item = store.getItems().get(buying-1);
								farm.addItem(item);
								farm.removeBalance(item.getPrice());
								store.sellItem(item);
							}
						}
						break;
					case 4:
						int looking = -1;
						while(looking != 0) {
							System.out.println("Inventory");
							System.out.println("------------------------------------------");
							System.out.printf( "%-6s%-15s%-20s%-10s%n","i:", "?: ", "?: ", "?: ");
							System.out.println("------------------------------------------");
							for(int i = 0; i < farm.getOwnedItems().size(); i++) {
								System.out.printf("%-6s%-15s%-20s%-10s%n", "("+ (i+1) + ")", farm.getOwnedItems().get(i).getName(), "?", "?");
							}
							System.out.println();
							System.out.println("Enter 0 to go back.");
							System.out.println();
							System.out.print("Please enter an option: ");
							System.out.println();
							looking = sc.nextInt();
							sc.nextLine();
						}
						break;
					case 5:
						inStore = false;
						System.out.println();
						break;
						
					default:
						break;
					}
				}
				break;
			case 4:
				//list actions
				/*
				 * Tend to crops ---> lessen their maturity time
				 * 			- but only 1 crop per action i.e. just spinach crops, just broccoli crops
				 * 			A. Water them (free)
				 * 			B. Use item
				 * 
				 * Feed animals ---> increase health
				 * 				(use food items)
				 * 
				 * Play with animals ---> increase happiness
				 * 
				 * Harvest crops ---> only fully matured crops are harvested in exchange for money
				 * 
				 * Tend to farm land ---> allows for more crops to be grown? keeps animals happier for longer.
				 */
				boolean viewingActionTypes = true;
				while(viewingActionTypes) {
					selection = 0;
					System.out.println("Actions"
							+ "\n-----------------------------------"
							+ "\n(1) Tend to crops"
							+ "\n(2) Tend to animals"
							+ "\n(3) Tend to farm"
							+ "\n(0) Go back");
					System.out.println();
					System.out.print("Please enter an option: ");
					selection = sc.nextInt();
					System.out.println();
					sc.nextLine();
					switch(selection) {
					case 0:
						viewingActionTypes = false;
						break;
					case 1:
						boolean viewingCropActions = true;
						while(viewingCropActions) {
							System.out.println("Crop Actions"
									+ "\n-----------------------------------"
									+ "\n(1) Water crops"
									+ "\n(2) Use crop item"
									+ "\n(0) Go back");
							System.out.println();
							System.out.print("Please enter an option: ");
							selection = sc.nextInt();
							System.out.println("selection: " + selection);
							sc.nextLine();
						
						switch(selection) {
						case 0:
							viewingCropActions = false;
							break;
						case 1:
							
							boolean selectingCrop = true;
							while(selectingCrop) {
								System.out.println("inside");
								//Which crop do you want to water?
								//find the crops that the farmer owns
								ArrayList<String> ownedCropTypes = new ArrayList<String>();
								ArrayList<Crop> crops = farm.getCrops();
								System.out.println("These are your crops: " + crops.toString());
								int cropQuantity = crops.size();
								
								boolean newSpecies = true;
								for (int j = 0; j < cropQuantity; j++) {
									for (int i = 0; i < ownedCropTypes.size(); i++) {
										if (crops.get(j).getSpecies() == ownedCropTypes.get(i)) {
											newSpecies = false;
											break;
										}
									}
									if (newSpecies) {
										ownedCropTypes.add(crops.get(j).getSpecies());
										
									}
									
									
								}
								System.out.println(ownedCropTypes);
								System.out.println("Owned Crops");
								System.out.println("-----------------------------------");
								for (int j = 0; j < ownedCropTypes.size(); j++) {
									System.out.println("("+ (j+1) + ") " + ownedCropTypes.get(j));
								}	
								
								System.out.println();
								System.out.println("Enter the index of the crop you want to water, alternatively enter 0 to go back.");
								System.out.println();
								System.out.print("Please enter an option: ");
								selection = sc.nextInt();
								System.out.println();
								sc.nextLine();
								for (int j = 0; j < cropQuantity; j++) {
									if (crops.get(j).getSpecies() == ownedCropTypes.get(selection - 1)) {
										//water the crop
										//TODO: investigate get/set days till mature. They are dodgy.
										crops.get(j).setDaysUntilMature(crops.get(j).getDaysUntilMature() - 1, 0);
									}
								}
								actionsRemaining--;
								break;
								
							}
							break;
							
						}
						}
						break;
					case 2:
						break;
					case 3:
						
					case 4:
						break;
					}
					
				}
			case 5:
				//days--;
				actionsRemaining = 2;
				//TODO: other things to change over the day
				System.out.println("You now have " + days + " days remaining.");
				break;
			case 10:
				playingGame = false;
				break;
			default:
				break;
			}
			
		}
		sc.close();
		//END game
	}
}