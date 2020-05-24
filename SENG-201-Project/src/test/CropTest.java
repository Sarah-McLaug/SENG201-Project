package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Crop;

class CropTest {
	private Crop testCrop;

	@BeforeEach
	public void init() {
		testCrop = new Crop("Wheat", 300, 400, 7, 10, 0);
	}
	
	@Test
	public void testSetDaysUntilMature() {
		testCrop.setDaysUntilMature(0, 1);
		assertEquals(0, testCrop.getDaysUntilMature());
	}
	
	@Test
	public void testDaysUntilMature() {
		testCrop.reduceDaysUntilMature(20);
		assertEquals(0, testCrop.getDaysUntilMature());
	}
}