package main.java.business.model;

import java.util.List;

public class SelectedQuestion extends Question {

	private List<Integer> selectedAnswers;

	public SelectedQuestion(Question question, List<Integer> selectedAnswers) {
		super(
				question.getQuestionID(),
				question.getSubject(),
				question.getChapter(),
				question.getDifficulty(),
				question.getContent(),
				question.getAnswers(),
				question.getCorrectAnswers());
		this.selectedAnswers = selectedAnswers;
	}

	public List<Integer> getSelectedAnswers() {
		return selectedAnswers;
	}

	public void setSelectedAnswers(List<Integer> selectedAnswers) {
		this.selectedAnswers = selectedAnswers;
	}

}
