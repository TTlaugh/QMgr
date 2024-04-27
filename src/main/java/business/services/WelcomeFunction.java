package business.services;

import java.sql.SQLException;

import business.model.Account;
import business.model.Teacher;
import data.AccountAccess;
import data.TeacherAccess;

public class WelcomeFunction {

	public Teacher signUp(String teacherID, String password) {
		Teacher teacher = null;
		try {
			teacher = new TeacherAccess().get(teacherID);
			if (new AccountAccess().get(teacher.getPersonID()).getPassword().equals(password))
				teacher = null;
		} catch (SQLException e) {
			e.printStackTrace();
			teacher = null;
		}
		return teacher;
	}
	
	public boolean signIn(Teacher teacher, String password) {
		try {
			new TeacherAccess().insert(teacher);
			new AccountAccess().insert(new Account(teacher.getPersonID(), password));
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

}
