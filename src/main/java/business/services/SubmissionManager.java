package main.java.business.services;

import java.sql.SQLException;
import java.util.List;

import main.java.business.model.SelectedQuestion;
import main.java.business.model.Submission;
import main.java.data.SubmissionAccess;
import main.java.utils.SQLUtils;

public class SubmissionManager {

	public List<Submission> getSubmissions() {
		try {
			return new SubmissionAccess().getAll();
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return null;
	}

	public boolean deleteSubmission(String submissionID) {
		try {
			return new SubmissionAccess().delete(submissionID);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return false;
	}

	public List<SelectedQuestion> getSelectedQuestions(Submission submission) {
		try {
			return new SubmissionAccess().getSelectedQuestions(submission);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
		return null;
	}

}
