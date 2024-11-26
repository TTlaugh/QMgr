package services;

import java.lang.reflect.Array;
import java.util.ArrayList;

import data.StudentDAO;
import model.Student;

public class StudentManager {
    private static StudentManager instance = null;

    private StudentManager() {
    }

    public static StudentManager getInstance() {
        if (instance == null) {
            instance = new StudentManager();
        }
        return instance;
    }


    public ArrayList<Student> getAllStudent() {
        return new StudentDAO().getAll();
    }

    public ArrayList<Student> getAllStudentInGroup(int groupId) {
        return new StudentDAO().getAllByGroupId(groupId);
    }

    public boolean updateStudent(Student student) {
        return new StudentDAO().update(student);
    }

    public boolean createStudent(Student student) {
        if (new StudentDAO().getByID(student.getStudentId()) == null)
            return new StudentDAO().create(student);

        return false;
    }

    public Student getStudentbyId(String studentId) {
        return new StudentDAO().getByID(studentId);
    }

    public boolean deleteStudent(Student student) {
        return new StudentDAO().delete(student.getUid());
    }

    public ArrayList<Student> searchStudent(int groupId, String keyword) {
        return new StudentDAO().searchStudent(groupId, keyword);
    }

}
