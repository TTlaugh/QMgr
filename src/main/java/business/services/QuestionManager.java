package business.services;

import java.sql.SQLException;
import java.util.List;

import business.model.Question;
import business.model.Subject;
import data.QuestionAccess;
import data.SubjectAccess;
import utils.SQLUtils;

public class QuestionManager {
	
	public List<Subject> getSubjects() {
		try {
			return new SubjectAccess().getAll();
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return null;
	}
	
	public boolean addSubject(Subject newSubject) {
		try {
			return new SubjectAccess().insert(newSubject);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return false;
	}

	public boolean editSubject(Subject newSubject) {
		try {
			return new SubjectAccess().update(newSubject);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return false;
	}

	public boolean deleteSubject(String subjectID) {
		try {
			return new SubjectAccess().delete(subjectID);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return false;
	}

	public List<Question> getQuestionsForSubject(Subject subject) {
		try {
			return new QuestionAccess().getQuestionsOfSubject(subject);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return null;
	}
	
	public boolean addQuestion(Question newQuestion) {
		try {
			return new QuestionAccess().insert(newQuestion);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return false;
	}
	
	public boolean editQuestion(Question newQuestion) {
		try {
			return new QuestionAccess().update(newQuestion);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return false;
	}
	
	public boolean deleteQuestion(String questionID) {
		try {
			return new QuestionAccess().delete(questionID);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return false;
	}

}
