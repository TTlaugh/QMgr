package model.DTO;

import java.util.ArrayList;

public class GroupsDTO {

	private String groupID;
	private TeachersDTO teacher;
	private String name;
	private ArrayList<StudentsDTO> students;
	
	public GroupsDTO(String groupID, TeachersDTO teacher, String name, ArrayList<StudentsDTO> students) {
		super();
		this.groupID = groupID;
		this.teacher = teacher;
		this.name = name;
		this.students = students;
	}

	public String getGroupID() {
		return groupID;
	}
	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}
	public TeachersDTO getTeacher() {
		return teacher;
	}
	public void setTeacher(TeachersDTO teacher) {
		this.teacher = teacher;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<StudentsDTO> getStudents() {
		return students;
	}
	public void setStudents(ArrayList<StudentsDTO> students) {
		this.students = students;
	}
	
}
