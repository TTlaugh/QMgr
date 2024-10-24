package business;

public class Submission {
    String hostExamID;
    String uid;
    int timeTaken;
    double score;
    String answerSelected;

    public Submission(String hostExamID, String uid, int timeTaken, double score, String answerSelected) {
        this.hostExamID = hostExamID;
        this.uid = uid;
        this.timeTaken = timeTaken;
        this.score = score;
        this.answerSelected = answerSelected;
    }

    public String getHostExamID() {
        return hostExamID;
    }

    public void setHostExamID(String hostExamID) {
        this.hostExamID = hostExamID;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(int timeTaken) {
        this.timeTaken = timeTaken;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getAnswerSelected() {
        return answerSelected;
    }

    public void setAnswerSelected(String answerSelected) {
        this.answerSelected = answerSelected;
    }

}
