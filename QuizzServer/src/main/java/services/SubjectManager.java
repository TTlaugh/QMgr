package services;

import java.util.ArrayList;

import data.SubjectDAO;
import model.Subject;

public class SubjectManager {
    private static SubjectManager instance = null;

    public static SubjectManager getInstance() {
        if (instance == null) {
            instance = new SubjectManager();
        }
        return instance;
    }

    public ArrayList<Subject> getAllSubject(int workspaceId) {
        return new SubjectDAO().getAllSubjectInWorkSpace(workspaceId);
    }

    public boolean createSubject(Subject subject) {
        return new SubjectDAO().create(subject);
    }

    public boolean updateSubject(Subject subject) {
        return new SubjectDAO().update(subject);
    }

    public boolean deleteSubject(int subjectId) {
        return new SubjectDAO().delete(subjectId);
    }

    public Subject getSubject(int subjectId) {
        return new SubjectDAO().getByID(subjectId);
    }
}
