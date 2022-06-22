package model;

public class Person {

	private String firstName = "";
	private String lastName = "";
	private String id;
	private String dateOfBirth = "";
	public Person(String firstName, String lastName, String id, String dateOfBirth) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.dateOfBirth = dateOfBirth;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	
}
