package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Animal;

class AnimalTest {
	private Animal testAnimal;

	@BeforeEach
	public void init() {
		testAnimal = new Animal("Baboon", 100, 30, 7, 7);
	}
	
	@Test
	public void updateHappinessTest() {
		testAnimal.updateHappiness(4);
		assertEquals(10, testAnimal.getHappiness());
		testAnimal.updateHappiness(-20);
		assertEquals(1, testAnimal.getHappiness());
		testAnimal.updateHappiness(6);
		assertEquals(7, testAnimal.getHappiness());
	}
	
	@Test
	public void updateHealthTest() {
		testAnimal.updateHealth(4);
		assertEquals(10, testAnimal.getHealth());
		testAnimal.updateHealth(-20);
		assertEquals(1, testAnimal.getHealth());
		testAnimal.updateHealth(6);
		assertEquals(7, testAnimal.getHealth());
	}

}
