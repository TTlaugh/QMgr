package model.DTO;

public class SubjectsDTO {

	private String subjectID;
	private TeachersDTO teacher;
	private String name;
	
	public SubjectsDTO(String subjectID, TeachersDTO teacher, String name) {
		this.subjectID = subjectID;
		this.teacher = teacher;
		this.name = name;
	}

	public String getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
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
	
}
