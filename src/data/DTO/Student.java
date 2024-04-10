package data.DTO;

public class Student extends Person {

	private String studentID;
	
	public Student(String personID, String firstName, String lastName, String email, String phone, String studentID) {
		super(personID, firstName, lastName, email, phone);
		this.studentID = studentID;
	}

	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
}
