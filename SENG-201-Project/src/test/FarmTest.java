package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Animal;
import main.Crop;
import main.CropItem;
import main.Farm;
import main.Farmer;
import main.FoodItem;
import main.Item;

class FarmTest {
	private Farm testFarm;
	
	@BeforeEach
	public void init() {
		testFarm = new Farm("Billy Bob's Friendly Farm", "friendly", new Farmer("Billy Bob", 52));
		testFarm.addCrop(new Crop("banana", 2000, 2, 4, 5, 0), 0);
		testFarm.addCrop(new Crop("corn", 250, 1, 1, 5, 0), 0);
		testFarm.addCrop(new Crop("kiwi", 3000, 1, 2, 5, 0), 0);
		
		testFarm.addAnimal(new Animal("llama", 250, 100, 9, 10));
		testFarm.addAnimal(new Animal("koala", 5000, 300, 6, 1));
		testFarm.addAnimal(new Animal("panda", 9999, 5000, 1, 1));
		
		testFarm.addItem(new FoodItem("sugar cane", 2, 2500));
	}
	
	@Test
	public void testSetType() {
		testFarm.setType("friendly");
		assertEquals("friendly", testFarm.getType());
		assertEquals(10000, testFarm.getBalance());
		assertEquals(10000, testFarm.getCapital());
		assertEquals(0, testFarm.getCropGrowthBonus());
		assertEquals(1.25, testFarm.getAnimalHappinessFactor());
		assertEquals(1.25, testFarm.getAnimalHealthFactor());
		assertEquals(1, testFarm.getCropYieldFactor());
		testFarm.setType("fast");
		assertEquals("fast", testFarm.getType());
		assertEquals(10000, testFarm.getBalance());
		assertEquals(10000, testFarm.getCapital());
		assertEquals(1, testFarm.getCropGrowthBonus());
		assertEquals(1, testFarm.getAnimalHappinessFactor());
		assertEquals(1, testFarm.getAnimalHealthFactor());
		assertEquals(1, testFarm.getCropYieldFactor());
		testFarm.setType("fertile");
		assertEquals("fertile", testFarm.getType());
		assertEquals(10000, testFarm.getBalance());
		assertEquals(10000, testFarm.getCapital());
		assertEquals(0, testFarm.getCropGrowthBonus());
		assertEquals(1, testFarm.getAnimalHappinessFactor());
		assertEquals(1, testFarm.getAnimalHealthFactor());
		assertEquals(1.25, testFarm.getCropYieldFactor());
		testFarm.setType("rich");
		assertEquals("rich", testFarm.getType());
		assertEquals(15000, testFarm.getBalance());
		assertEquals(15000, testFarm.getCapital());
		assertEquals(0, testFarm.getCropGrowthBonus());
		assertEquals(1, testFarm.getAnimalHappinessFactor());
		assertEquals(1, testFarm.getAnimalHealthFactor());
		assertEquals(1, testFarm.getCropYieldFactor());
	}
	
	@Test
	public void testTendFarm() {
		int[] initialAnimalHappiness = {9, 6, 1};
		testFarm.tendFarm();
		assertEquals(1.1, testFarm.getCropYieldFactor());
		assertEquals(1.35, testFarm.getAnimalHappinessFactor());
		for(int i = 0; i < testFarm.getAnimals().size(); i++) {
			assertEquals(initialAnimalHappiness[i] + 1, testFarm.getAnimals().get(i).getHappiness());
		}
	}
	
	@Test
	public void testOwnCrop() {
		assertTrue(testFarm.ownsCrop("banana"));
		assertFalse(testFarm.ownsCrop("pineapple"));
	}
	
	@Test
	public void testCropsReady() {
		assertFalse(testFarm.cropsReady());
		testFarm.getCrops().get(0).setDaysUntilMature(0, 0);
		assertTrue(testFarm.cropsReady());
	}
	
	@Test
	public void testFetchItem() {
		assertEquals(testFarm.getInventory().get(0).getId(), 
				testFarm.fetchItem(testFarm.getInventory().get(0).getId()).getId());
	}
}
