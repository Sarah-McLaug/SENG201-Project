package farm;

public class Farmer {
	private String firstName;
	private String lastName;
	private int age;
    //TODO: add list of items once Items class implemented
	
	public Farmer(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}
	
	public String getName() {
		return firstName + " " + lastName;
	}
	
	public void setName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	//TODO: Decide if this is where the farmer functions should go
	public void feedAnimal() {}
	
	public void playAnimal() {}
	
	public void tendCrop() {}
	
	public void harvestCrop() {}
	
	public void tendFarm() {}
	
	public void addItem() {
		//TODO: Implement item class in order to make this work
	}
}
