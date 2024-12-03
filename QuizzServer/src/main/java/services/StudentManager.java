package services;

import java.util.ArrayList;

import data.StudentDAO;
import model.Student;

public class StudentManager {
    private static StudentManager instance = null;

    StudentManager() {
    }

    public static StudentManager getInstance() {
        if (instance == null) {
            instance = new StudentManager();
        }
        return instance;
    }

    public int getMaxUid() {
        return new StudentDAO().getMaxUid();
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
        if (new StudentDAO().getByStudentId(student.getStudentId()) == null)
            return new StudentDAO().create(student);

        return false;
    }

    public Student getStudentbyIdfromGroup(String studentId, int groupId) {
        return new StudentDAO().getByStudentIdfromGroup(studentId, groupId);
    }

    public Student getStudentbyId(String studentId) {
        return new StudentDAO().getByStudentId(studentId);
    }

    public boolean deleteStudent(Student student) {
        return new StudentDAO().delete(student.getUid());
    }

    public ArrayList<Student> searchStudent(int groupId, String keyword) {
        return new StudentDAO().searchStudent(groupId, keyword);
    }

    public Student getStudentByUid(int uid) {
        return new StudentDAO().getByID(uid);
    }

}
