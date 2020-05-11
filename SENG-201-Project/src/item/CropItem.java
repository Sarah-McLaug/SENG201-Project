package item;

public class CropItem extends Item {
	private int growthEnhancement;
	
	public CropItem(String name, double price, int growthEnhancement) {
		super(name, price);
		this.growthEnhancement = growthEnhancement;
	}
	
	//BEGIN getters and setters
	public double getGrowthEnhancement() {
		return this.growthEnhancement;
	}
	
	public void setGrowthEnhancement(int growthEnhancement) {
		this.growthEnhancement = growthEnhancement;
	}
}
