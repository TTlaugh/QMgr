package business.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.DateTime;

public class Score implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private DateTime examID;
	private double score;
	private String subjectID;

	public Score(DateTime examID, double score, String subjectID) {
		this.examID = examID;
		this.score = score;
		this.subjectID = subjectID;
	}
	
	public Score(ResultSet rs) throws SQLException {
		this.examID = new DateTime(rs.getString("ExamID"));
		this.score = rs.getInt("Score");
		this.subjectID = rs.getString("SubjectID");
	}
	
	public DateTime getExamID() {
		return examID;
	}
	public void setExamID(DateTime examID) {
		this.examID = examID;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}

	public String getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}

}
