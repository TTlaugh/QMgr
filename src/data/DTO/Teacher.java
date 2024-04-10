package data.DTO;

public class Teacher extends Person {

	private String teacherID;
	
	public Teacher(String personID, String firstName, String lastName, String email, String phone,
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
