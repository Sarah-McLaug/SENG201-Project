package main;

public class CropItem extends Item {
	private int growthEnhancement;
	
	/**
	 * Crop Item constructor
	 * @param name - name of the crop item
	 * @param price - cost to buy the crop item
	 * @param growthEnhancement - how much the item speeds up crop's growth rate, in days
	 */
	public CropItem(String name, double price, int growthEnhancement) {
		super(name, price, "crop");
		this.growthEnhancement = growthEnhancement;
	}
	
	/**
	 * 
	 * @return return the growth enhancement of the item, in days
	 */
	public int getGrowthEnhancement() {
		return this.growthEnhancement;
	}
	
	/**
	 * 
	 * @param growthEnhancement value to set the growth enhancement of the itme to, in days
	 */
	public void setGrowthEnhancement(int growthEnhancement) {
		this.growthEnhancement = growthEnhancement;
	}
	
	/**
	 * Get this specific item's benefit
	 * @return growth enhancement of the item, in days
	 */
	public int getBenefit() {
		//TODO: implementing interface in parent
		return this.growthEnhancement;
	};
}
