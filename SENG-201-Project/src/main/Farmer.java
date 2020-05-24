package main;

public class Farmer {
	private String name;
	int age;
	
	/**
	 * Farmer constructor
	 * @param name - name of the farmer, character length 3-15 (inclusive)
	 * @param age - age of the farmer, in years
	 */
	public Farmer(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	/**
	 * 
	 * @return the name of the farmer
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @param name - the name of the farmer to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return the age of the farmer
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * 
	 * @param age - the age of the famer to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
}
