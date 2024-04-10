package data.DTO;

import java.util.ArrayList;

import utils.DateTime;

public class Exam {

	private DateTime examID;
	private Subject subject;
	private DateTime startDateTime;
	private int timeLimit;
	private double maxScore;
	private String name;
	private String description;
	private boolean isShuffle;
	private ArrayList<Question> questions;
	
	public Exam(DateTime examID, Subject subject, DateTime dateTime, int timeLimit, double maxScore, String name,
			String note, boolean isShuffle, ArrayList<Question> questions) {
		super();
		this.examID = examID;
		this.subject = subject;
		this.startDateTime = dateTime;
		this.timeLimit = timeLimit;
		this.maxScore = maxScore;
		this.name = name;
		this.description = note;
		this.isShuffle = isShuffle;
		this.questions = questions;
	}

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
	public boolean isShuffle() {
		return isShuffle;
	}
	public void setShuffle(boolean isShuffle) {
		this.isShuffle = isShuffle;
	}
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

}
