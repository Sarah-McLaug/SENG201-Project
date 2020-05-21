package main;

public class CropItem extends Item {
	private int growthEnhancement;
	
	public CropItem(String name, double price, int growthEnhancement) {
		super(name, price, "crop");
		this.growthEnhancement = growthEnhancement;
	}
	
	//BEGIN getters and setters
	public int getGrowthEnhancement() {
		return this.growthEnhancement;
	}
	
	public void setGrowthEnhancement(int growthEnhancement) {
		this.growthEnhancement = growthEnhancement;
	}
	
	public int getBenefit() {
		//TODO: implementing interface in parent
		return this.growthEnhancement;
	};
}
