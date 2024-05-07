package business.model;

import java.io.Serializable;

public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	private String personID;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	
	public Person(String personID, String firstName, String lastName, String phone, String email) {
		this.personID = personID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
	}
	public Person() {
		this.personID = null;
		this.firstName = null;
		this.lastName = null;
		this.phone = null;
		this.email = null;
	}

	public String getPersonID() {
		return personID;
	}

	public void setPersonID(String personID) {
		this.personID = personID;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Person [personID=" + personID + ", firstName=" + firstName + ", lastName=" + lastName + ", phone="
				+ phone + ", email=" + email + "]";
	}
	
}
