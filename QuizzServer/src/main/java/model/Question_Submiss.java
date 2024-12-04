package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Question_Submiss extends Question {

    private Map<Integer, List<Integer>> answerSelectedMap;

    private ArrayList<Question> examQuestions;

    public Question_Submiss(Question question, Map<Integer, List<Integer>> answerSelectedMap,
            ArrayList<Question> examQuestions) {
        super(question.getQuestionId(), question.getSubjectId(), question.getChapter(), question.getDifficulty(),
                question.getContent(),
                question.getAnswers(), question.isArchive());
        this.answerSelectedMap = answerSelectedMap;
        this.examQuestions = examQuestions;

    }

    public Map<Integer, List<Integer>> getAnswerSelectedMap() {
        return answerSelectedMap;
    }

    public ArrayList<Question> getExamQuestions() {
        return examQuestions;
    }
}
