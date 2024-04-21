package business.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import utils.DateTime;

public class Score {
	
	private DateTime examID;
	private double score;

	public Score(DateTime examID, double score) {
		this.examID = examID;
		this.score = score;
	}
	
	public Score(ResultSet rs) throws SQLException {
		this.examID = new DateTime(rs.getString("ExamID"));
		this.score = rs.getInt("Score");
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

}
