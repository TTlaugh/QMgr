package business.model;

import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import utils.DateTime;
import utils.JsonUtils;

public class Submission implements Serializable {

	private static final long serialVersionUID = 1L;

	private Exam exam;
	private Student student;
	private int timeTaken;
	private double score;
	private String answerSelectedsJSON;
	
	public Submission(Exam exam, Student student, int timeTaken, double score, String answerSelecteds) {
		this.exam = exam;
		this.student = student;
		this.timeTaken = timeTaken;
		this.score = score;
		this.answerSelectedsJSON = answerSelecteds;
	}

	public Submission(String examID, String studentID, int timeTaken, double score, String answerSelecteds) {
		this.exam = new Exam(); this.exam.setExamID(new DateTime(examID));
		this.student = new Student(); this.student.setStudentID(studentID);
		this.timeTaken = timeTaken;
		this.score = score;
		this.answerSelectedsJSON = answerSelecteds;
	}
	
	public Submission(ResultSet rs) throws SQLException {
		this.exam = new Exam(rs);
		this.student = new Student(rs);
		this.timeTaken = rs.getInt("TimeTaken");
		this.score = rs.getDouble("Score");
		this.answerSelectedsJSON = rs.getString("AnswerSelecteds");
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

	public String getAnswerSelectedsJSON() {
		return answerSelectedsJSON;
	}

	public void setAnswerSelectedsJSON(String answerSelectedsJSON) {
		this.answerSelectedsJSON = answerSelectedsJSON;
	}

	public Map<String, List<Integer>> getAnswerSelectedsMap() {
		try {
			return JsonUtils.jsonToMap_String_List(this.answerSelectedsJSON);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String toString() {
		return "Submission [exam=" + exam + ", student=" + student + ", timeTaken=" + timeTaken + ", score=" + score
				+ ", answerSelectedsJSON=" + answerSelectedsJSON + "]";
	}

}
