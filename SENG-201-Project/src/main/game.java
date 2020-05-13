package main;

public class game {
	private int duration;
	private int actionCount;
	
	public game(int duration, int actionCount) {
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
}
