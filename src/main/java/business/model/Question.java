package business.model;

import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.JsonUtils;

public class Question implements Serializable {

	private static final long serialVersionUID = 1L;

	private String questionID;
	private Subject subject;
	private int chapter;
	private int difficulty;
	private String content;
	private List<String> answers;
	private List<Integer> correctAnswers;

	public Question(String questionID, Subject subject, int chapter, int difficulty, String content, List<String> answers,
			List<Integer> correctAnswers) {
		this.questionID = questionID;
		this.subject = subject;
		this.chapter = chapter;
		this.difficulty = difficulty;
		this.content = content;
		this.answers = answers;
		this.correctAnswers = correctAnswers;
	}
	
	public Question(ResultSet rs) throws SQLException {
		this.questionID = rs.getString("QuestionID");
		this.subject = new Subject(
				rs.getString("SubjectID"),
				null,
				rs.getString("SubjectName")
				);
		this.chapter = rs.getInt("Chapter");
		this.difficulty = rs.getInt("Difficulty");
		this.content = rs.getString("Content");
		this.answers = new ArrayList<>();
		for (int i = 1; i <= 4; i++)
			this.answers.add(rs.getString("Answer" + i));
		try {
			this.correctAnswers = JsonUtils.jsonToList(rs.getString("CorrectAnswers"), Integer.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Question() {
		this.questionID = null;
		this.subject = null;
		this.chapter = 0;
		this.difficulty = 0;
		this.content = null;
		this.answers = new ArrayList<>();
		this.correctAnswers = new ArrayList<>();
	}
	
	public String getQuestionID() {
		return questionID;
	}
	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public int getChapter() {
		return chapter;
	}
	public void setChapter(int chapter) {
		this.chapter = chapter;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<String> getAnswers() {
		return answers;
	}
	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}
	public List<Integer> getCorrectAnswers() {
		return correctAnswers;
	}
	public void setCorrectAnswers(List<Integer> correctAnswers) {
		this.correctAnswers = correctAnswers;
	}
	
}
