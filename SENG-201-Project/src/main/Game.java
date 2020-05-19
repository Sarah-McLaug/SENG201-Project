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
		//Start game
		String junk = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("How many days would you like your game to last? (Between 5 and 10): ");
		int days = sc.nextInt();
		junk = sc.nextLine();
		System.out.println("Please enter your name: ");
		String farmerName = sc.nextLine();
		System.out.println("Please enter your age: ");
		int age = sc.nextInt();
		junk = sc.nextLine();
		System.out.println("Pick a type of farm: \nFriendly Farm (1)\nFast Farm (2)\nFertile Farm (3)\nRich Farm (4)");
		int typeInput = 0;
		while(typeInput < 1 || typeInput > 4) {
			typeInput = sc.nextInt();
			if(typeInput < 1 || typeInput > 4) {
				System.out.println("Not a valid farm type.");
			}
		}
		String type = "not entered";
		switch(typeInput) {
		case 1: 
			type = "friendly";
			break;
		case 2: 
			type = "fast";
			break;
		case 3: 
			type = "fertile";
			break;
		case 4: 
			type = "rich";
			break;
		default: 
			type = "";
			break;
		}

		junk = sc.nextLine();
		System.out.println("Please enter a name for your " + type + " farm: ");
		String farmName = sc.nextLine();
		Farm farm = new Farm(farmName, type, new Farmer(farmerName, age));
		Store store = new Store();
		
		//stock the store
		for(int i = 0; i < 10; i++) {
			store.addCrop(new Crop("corn", 100, 100, 3, 10));
			store.addAnimal(new Animal("Llama", 150, 35, 7.3, 9.0));
			if(i%2 == 0) store.addItem(new CropItem("Fertilizer", 85, 1));
			else store.addItem(new FoodItem("Broccoli", 76, 2.0));
		}
		
		System.out.println("Game is starting! You have " + days + " days to make as much money as possible!");
		
		boolean playGame = true;
		int option = 0;
		int actionsRemaining = 2;
		while(playGame) {
			System.out.println("Please choose an option: \n(1)View the status of the farm's crops and animals"
					+ "\n(2)View the status of the farm"
					+ "\n(3)Go to the store"
					+ "\n(4)Take an action (You have " + actionsRemaining + " action remaining"
					+ "\n(5)Move to next day"
					+ "\n(10)Exit");
			option = sc.nextInt();
			junk = sc.nextLine();
			switch(option) {
			case 1:
				if(farm.getAnimals().size() == 0) System.out.println("No animals owned");
				else {
					System.out.println("Animals owned: ");
					for(int i = 0; i < farm.getAnimals().size(); i++) {
						System.out.println("Species: " + farm.getAnimals().get(i).getSpecies()
								+ ", Happiness: " + farm.getAnimals().get(i).getHappiness() + "/10.0"
								+ ", Health: " + farm.getAnimals().get(i).getHealth() + "/10.0");
					}
				}
				if(farm.getCrops().size() == 0) System.out.println("No crops growing");
				else {
					System.out.println("Crops growing: ");
					for(int i = 0; i < farm.getCrops().size(); i++) {
						System.out.println("Species: "+ farm.getCrops().get(i).getSpecies() 
									+ ", Time until harvest: " + farm.getCrops().get(i).getDaysUntilMature() + " days");
					}
				}
				break;
			case 2:
				System.out.println(farm.getName() + " has $" + farm.getBalance() + " in the bank");
				break;
			case 3:
				boolean inStore = true;
				while(inStore) {
					int storeAction = 0;
					System.out.println("(1)View crops for sale"
							+ "\n(2)View animals for sale"
							+ "\n(3)View farming supplies for sale"
							+ "\n(4)View owned farming supplies"
							+ "\n(5)Exit");
					storeAction = sc.nextInt();
					junk = sc.nextLine();
					switch(storeAction) {
					case 1:
						int buying = -1;
						while (buying != 0) {
							System.out.println("(0)Exit back to Store menu"
									+ "\nType the number of the crop you want to buy");
							for(int i = 0; i < store.getCrops().size(); i++) {
								Crop c = store.getCrops().get(i);
								System.out.println("("+ (i+1) + ")" + c.getSpecies() 
													+ ", Maturity Time: " 
													+ c.getDaysUntilMature() 
													+ ", Cost: " 
													+ c.getPurchasePrice());
							}
							buying = sc.nextInt();
							junk = sc.nextLine();
							if(buying != 0 && buying <= store.getCrops().size()+1) {
								Crop c = store.getCrops().get(buying-1);
								farm.addCrop(c);
								farm.removeBalance(c.getPurchasePrice());
								store.sellCrop(c);
							}
						}
						break;
					case 2:
						buying = -1;
						while (buying != 0) {
							System.out.println("(0)Exit back to Store menu"
									+ "\nType the number of the animal you want to buy");
							for(int i = 0; i < store.getAnimals().size(); i++) {
								Animal a = store.getAnimals().get(i);
								System.out.println("("+ (i+1) + ")" + a.getSpecies() 
													+ ", Maturity Time: " 
													+ a.getHappiness() 
													+ ", Cost: " 
													+ a.getPurchasePrice());
							}
							buying = sc.nextInt();
							junk = sc.nextLine();
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
							System.out.println("(0)Exit back to Store menu"
									+ "\nType the number of the farming supply you want to buy");
							for(int i = 0; i < store.getItems().size(); i++) {
								Item item = store.getItems().get(i);
								System.out.println("("+ (i+1) + ")" + item.getName());
								/*System.out.println("("+ (i+1) + ")" + item.getName() //TODO: I think we could use an abstract implementation to
													+ ", Maturity Time: " 			   //get the item's specific bonus. need to figure out
													+ item.getHappiness() 			   //best way to do so
													+ ", Cost: " 
													+ item.getPurchasePrice());*/
							}
							buying = sc.nextInt();
							junk = sc.nextLine();
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
							System.out.println("(0)Exit back to Store menu"
									+ "\nFarming supplies you own: ");
							for(int i = 0; i < farm.getOwnedItems().size(); i++) {
								System.out.println("Type: "+ farm.getOwnedItems().get(i).getName());
							}
							looking = sc.nextInt();
							junk = sc.nextLine();
						}
						break;
					case 5:
						inStore = false;
						break;
					default:
						break;
					}
				}
				break;
			case 5:
				days--;
				actionsRemaining = 2;
				//TODO: other things to change over the day
				System.out.println("You now have " + days + " days remaining.");
				break;
			case 10:
				playGame = false;
				break;
			default:
				break;
			}
		}
	}
}
