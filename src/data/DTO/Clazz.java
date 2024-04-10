package data.DTO;

import java.util.ArrayList;

public class Clazz {

	private String classID;
	private Teacher teacher;
	private String className;
	private ArrayList<Student> students;
	
	public Clazz(String classID, Teacher teacher, String className, ArrayList<Student> students) {
		this.classID = classID;
		this.teacher = teacher;
		this.className = className;
		this.students = students;
	}
	
	public Clazz(String classID, Teacher teacher, String className) {
		this.classID = classID;
		this.teacher = teacher;
		this.className = className;
		this.students = new ArrayList<Student>();
	}

	public String getClassID() {
		return classID;
	}
	public void setClassID(String classID) {
		this.classID = classID;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public ArrayList<Student> getStudents() {
		return students;
	}
	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
	
}
