package data.DTO;

public class Subject {

	private String subjectID;
	private Teacher teacher;
	private String subjectName;
	
	public Subject(String subjectID, Teacher teacher, String subjectName) {
		this.subjectID = subjectID;
		this.teacher = teacher;
		this.subjectName = subjectName;
	}

	public String getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
}
