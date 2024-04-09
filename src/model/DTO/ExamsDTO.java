package model.DTO;

import java.util.ArrayList;

import utils.DateTime;

public class ExamsDTO {

	private String examID;
	private SubjectsDTO subject;
	private DateTime dateTime;
	private int timeLimit;
	private double maxScore;
	private String name;
	private String note;
	private boolean isShuffle;
	private ArrayList<QuestionsDTO> questions;
	
	public ExamsDTO(String examID, SubjectsDTO subject, DateTime dateTime, int timeLimit, double maxScore, String name,
			String note, boolean isShuffle, ArrayList<QuestionsDTO> questions) {
		super();
		this.examID = examID;
		this.subject = subject;
		this.dateTime = dateTime;
		this.timeLimit = timeLimit;
		this.maxScore = maxScore;
		this.name = name;
		this.note = note;
		this.isShuffle = isShuffle;
		this.questions = questions;
	}

	public String getExamID() {
		return examID;
	}
	public void setExamID(String examID) {
		this.examID = examID;
	}
	public SubjectsDTO getSubject() {
		return subject;
	}
	public void setSubject(SubjectsDTO subject) {
		this.subject = subject;
	}
	public DateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(DateTime dateTime) {
		this.dateTime = dateTime;
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public boolean isShuffle() {
		return isShuffle;
	}
	public void setShuffle(boolean isShuffle) {
		this.isShuffle = isShuffle;
	}
	public ArrayList<QuestionsDTO> getQuestions() {
		return questions;
	}
	public void setQuestions(ArrayList<QuestionsDTO> questions) {
		this.questions = questions;
	}

	
}
