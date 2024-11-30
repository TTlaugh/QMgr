package services;

import java.util.ArrayList;

import data.HostExamDAO;
import model.HostExam;

public class HostExamManager {
    private static HostExamManager instance = null;

    public static HostExamManager getInstance() {
        if (instance == null) {
            instance = new HostExamManager();
        }
        return instance;
    }

    public boolean createHostExam(HostExam hostExam) {
        return new HostExamDAO().create(hostExam);
    }

    public boolean updateHostExam(HostExam hostExam) {
        return new HostExamDAO().update(hostExam);
    }

    public boolean deleteHostExam(int hostExamId) {
        return new HostExamDAO().delete(hostExamId);
    }

    public HostExam getHostExamById(int hostExamId) {
        return new HostExamDAO().getByID(hostExamId);
    }

    public ArrayList<HostExam> getAllHostExams() {
        return new HostExamDAO().getAll();
    }
}
