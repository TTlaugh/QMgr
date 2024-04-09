package model.DTO;

public class AccountsDTO {

	private TeachersDTO teachers;
	private String password;
	
	public AccountsDTO(TeachersDTO teachers, String password) {
		this.teachers = teachers;
		this.password = password;
	}

	public TeachersDTO getTeachers() {
		return teachers;
	}

	public void setTeachers(TeachersDTO teachers) {
		this.teachers = teachers;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
