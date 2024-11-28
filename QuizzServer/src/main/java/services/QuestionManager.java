package services;

import java.util.ArrayList;

import data.QuestionDAO;
import model.Question;

public class QuestionManager {
    private static QuestionManager instance = null;

    public static QuestionManager getInstance() {
        if (instance == null) {
            instance = new QuestionManager();
        }
        return instance;
    }

    public boolean createQuestion(Question question) {
        return new QuestionDAO().create(question);
    }

    public boolean updateQuestion(Question question) {
        return new QuestionDAO().update(question);
    }

    public boolean deleteQuestion(int questionId) {
        return new QuestionDAO().delete(questionId);
    }

    public Question getQuestion(int questionId) {
        return new QuestionDAO().getByID(questionId);
    }

    public ArrayList<Question> getAllQuestions() {
        return new QuestionDAO().getAll();
    }

    public ArrayList<Question> getAllQuestionsBySubject(int subjectId) {
        return new QuestionDAO().getAllBySubject(subjectId);
    }

    public ArrayList<Question> searchQuestions(int subjectId, String keyWord) {
        return new QuestionDAO().searchQuestions(subjectId, keyWord);
    }
}
