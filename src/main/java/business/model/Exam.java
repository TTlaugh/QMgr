package business.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.DateTime;

public class Exam implements Serializable {

	private static final long serialVersionUID = 1L;

	private DateTime examID;
	private Subject subject;
	private DateTime startDateTime;
	private int timeLimit;
	private double maxScore;
	private String name;
	private String description;
	private boolean isShuffled;
	private List<Question> questions;
	
	public Exam(DateTime examID, Subject subject, DateTime dateTime, int timeLimit, double maxScore, String name,
			String note, boolean isShuffled, List<Question> questions) {
		this.examID = examID;
		this.subject = subject;
		this.startDateTime = dateTime;
		this.timeLimit = timeLimit;
		this.maxScore = maxScore;
		this.name = name;
		this.description = note;
		this.isShuffled = isShuffled;
		this.questions = questions;
	}
	
	public Exam(ResultSet rs) throws SQLException {
		this.examID = new DateTime(rs.getString("ExamID"));
		this.subject = new Subject(
				rs.getString("SubjectID"),
				null,
				rs.getString("SubjectName")
				);
		this.startDateTime = new DateTime(rs.getString("StartDateTime"));
		this.timeLimit = rs.getInt("TimeLimit");
		this.maxScore = rs.getDouble("MaxScore");
		this.name = rs.getString("Name");
		this.description = rs.getString("Description");
		this.isShuffled = rs.getBoolean("IsShuffled");
		this.questions = new ArrayList<Question>();
	}
	
	public Exam() { }

	public DateTime getExamID() {
		return examID;
	}
	public void setExamID(DateTime examID) {
		this.examID = examID;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public DateTime getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(DateTime dateTime) {
		this.startDateTime = dateTime;
	}
	public int getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}
	public double getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(double maxScore) {
		this.maxScore = maxScore;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String note) {
		this.description = note;
	}
	public boolean isShuffled() {
		return isShuffled;
	}
	public void setShuffled(boolean isShuffled) {
		this.isShuffled = isShuffled;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public String getQuestionIDs() {
		return questions.stream().map(q -> q.getQuestionID()).toList().toString();
	}

	@Override
	public String toString() {
		return "Exam [examID=" + examID + ", subject=" + subject + ", startDateTime=" + startDateTime + ", timeLimit="
				+ timeLimit + ", maxScore=" + maxScore + ", name=" + name + ", description=" + description
				+ ", isShuffled=" + isShuffled + ", questions=" + questions + "]";
	}

	
}
