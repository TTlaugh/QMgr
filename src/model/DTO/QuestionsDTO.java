package model.DTO;

public class QuestionsDTO {

	private String questionID;
	private SubjectsDTO subject;
	private int chapter;
	private int difficulty;
	private String content;
	private String answers[];
	private int correctAnswers[];
	
	public QuestionsDTO(String questionID, SubjectsDTO subject, int chapter, int difficulty, String content,
			String[] answers, int[] correctAnswers) {
		this.questionID = questionID;
		this.subject = subject;
		this.chapter = chapter;
		this.difficulty = difficulty;
		this.content = content;
		this.answers = answers;
		this.correctAnswers = correctAnswers;
	}

	public String getQuestionID() {
		return questionID;
	}

	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}

	public SubjectsDTO getSubject() {
		return subject;
	}

	public void setSubject(SubjectsDTO subject) {
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

	public String[] getAnswers() {
		return answers;
	}

	public void setAnswers(String[] answers) {
		this.answers = answers;
	}

	public int[] getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(int[] correctAnswers) {
		this.correctAnswers = correctAnswers;
	}
}
