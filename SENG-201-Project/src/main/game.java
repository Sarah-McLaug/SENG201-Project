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
		
		System.out.println("Game is starting! You have " + days + " days to make as much money as possible!");
		
		boolean playGame = true;
		int action = 0;
		while(playGame) {
			System.out.println("Please choose an option: \n(1)View the status of the farm's crops and animals"
					+ "\n(2)View the status of the farm"
					+ "\n(10)Exit");
			action = sc.nextInt();
			junk = sc.nextLine();
			switch(action) {
			case 1:
				for(int i = 0; i < farm.getAnimals().size(); i++) {
					//print out the animals properties
				}
				for(int i = 0; i < farm.getCrops().size(); i++) {
					//print out crop properties
				}
				for(int i = 0; i < farm.getOwnedItems().size(); i++) {
					//print out item properties
				}
				break;
			case 2:
				System.out.println(farm.getName() + " has $" + farm.getBalance() + " in the bank");
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
