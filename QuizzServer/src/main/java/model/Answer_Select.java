package model;

public class Answer_Select extends Answer {
    private static final long serialVersionUID = 1L;
    private boolean correct_Save;

    public Answer_Select(Answer answer, boolean isChoice) {
        super(answer.getAnswerId(), answer.getQuestionId(), answer.getContent(), answer.isCorrect());
        this.correct_Save = isChoice;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public boolean isChoice() {
        return correct_Save;
    }

    public void setChoice(boolean isChoice) {
        this.correct_Save = isChoice;
    }

}
