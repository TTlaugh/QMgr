package model.DTO;

public class TeachersDTO extends PersonDTO {

	private String teacherID;
	
	public TeachersDTO(String personID, String firstName, String lastName, String email, String phone,
			String teacherID) {
		super(personID, firstName, lastName, email, phone);
		this.teacherID = teacherID;
	}

	public String getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}

}
