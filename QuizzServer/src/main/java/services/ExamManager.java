package services;

import java.util.ArrayList;

import data.ExamDAO;
import model.Exam;

public class ExamManager {
    private static ExamManager examManager = null;

    public static ExamManager getInstance() {
        if (examManager == null) {
            examManager = new ExamManager();
        }
        return examManager;
    }

    public boolean createExam(Exam exam) {
        return new ExamDAO().create(exam);
    }

    public ArrayList<Exam> getAllExams() {
        return new ExamDAO().getAll();
    }

    public ArrayList<Exam> getAllExamsBySubject(int subjectId) {
        return new ExamDAO().getAllBySubject(subjectId);
    }

    public Exam getExam(int examId) {
        return new ExamDAO().getByID(examId);
    }

    public boolean updateExam(Exam exam) {
        return new ExamDAO().update(exam);
    }

    public boolean deleteExam(int examId) {
        return new ExamDAO().delete(examId);
    }

    public ArrayList<Exam> getAllExams_UnHosted() {
        return new ExamDAO().getAllExamsUnHosted();
    }

    public ArrayList<Exam> getAllExams_Hosted() {
        return new ExamDAO().getAllExamsHosted();
    }
}
