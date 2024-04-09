package model.DTO;

import java.util.HashMap;

public class SubmissionsDTO {

	private String submissionID;
	private ExamsDTO exam;
	private StudentsDTO student;
	private int timeTaken;
	private double score;
	private HashMap<String, Integer> answerSelecteds;

	public SubmissionsDTO(String submissionID, ExamsDTO exam, StudentsDTO student, int timeTaken, double score,
			HashMap<String, Integer> answerSelecteds) {
		this.submissionID = submissionID;
		this.exam = exam;
		this.student = student;
		this.timeTaken = timeTaken;
		this.score = score;
		this.answerSelecteds = answerSelecteds;
	}

	public String getSubmissionID() {
		return submissionID;
	}

	public void setSubmissionID(String submissionID) {
		this.submissionID = submissionID;
	}

	public ExamsDTO getExam() {
		return exam;
	}

	public void setExam(ExamsDTO exam) {
		this.exam = exam;
	}

	public StudentsDTO getStudent() {
		return student;
	}

	public void setStudent(StudentsDTO student) {
		this.student = student;
	}

	public int getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(int timeTaken) {
		this.timeTaken = timeTaken;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public HashMap<String, Integer> getAnswerSelecteds() {
		return answerSelecteds;
	}

	public void setAnswerSelecteds(HashMap<String, Integer> answerSelecteds) {
		this.answerSelecteds = answerSelecteds;
	}
	
}
