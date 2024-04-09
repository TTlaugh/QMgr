package model.DTO;

import java.util.ArrayList;

public class StudentsDTO extends PersonDTO {

	private String studentID;
	private ArrayList<GroupsDTO> groups;
	
	public StudentsDTO(String personID, String firstName, String lastName, String email, String phone, String studentID,
			ArrayList<GroupsDTO> groups) {
		super(personID, firstName, lastName, email, phone);
		this.studentID = studentID;
		this.groups = groups;
	}

	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public ArrayList<GroupsDTO> getGroups() {
		return groups;
	}
	public void setGroups(ArrayList<GroupsDTO> groups) {
		this.groups = groups;
	}

}
