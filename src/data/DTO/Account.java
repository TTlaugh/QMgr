package data.DTO;

public class Account {

	private String personID;
	private String password;
	
	public Account(String personID, String password) {
		this.personID = personID;
		this.password = password;
	}

	public String getPersonID() {
		return personID;
	}

	public void setPersonID(String accountName) {
		this.personID = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
