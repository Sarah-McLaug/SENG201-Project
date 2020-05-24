package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.CropItem;
import main.FoodItem;

class ItemTest {

	@Test
	public void testIsFoodItem() {
		FoodItem food = new FoodItem("grapes", 10, 1);
		assertTrue(food.isFoodItem());
		CropItem crop = new CropItem("dirt", 200, 2);
		assertFalse(crop.isFoodItem());
	}
}
