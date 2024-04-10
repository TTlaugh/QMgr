package data.DTO;

import java.util.HashMap;

public class Submission {

	private String submissionID;
	private Exam exam;
	private Student student;
	private int timeTaken;
	private double score;
	private HashMap<String, Integer> answerSelecteds;

	public Submission(String submissionID, Exam exam, Student student, int timeTaken, double score,
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

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
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
