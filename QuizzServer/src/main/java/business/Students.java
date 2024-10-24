package business;

public class Students {
    private String uid;
    private String studentID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public Students(String uid, String studentID, String firstName, String lastName, String email, String phone) {
        this.uid = uid;
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public String getUid() {
        return uid;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
