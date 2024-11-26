package model;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Submission {
    private int submissionId;
    private int hostExamId;
    private int studentId;
    private int timeTaken;
    private float score;
    private Map<Integer, List<Integer>> answerSelectedMap; // Map<QuestionId, List Answer>
	public int getSubmissionId() {
		return submissionId;
	}
	public void setSubmissionId(int submissionId) {
		this.submissionId = submissionId;
	}
	public int getHostExamId() {
		return hostExamId;
	}
	public void setHostExamId(int hostExamId) {
		this.hostExamId = hostExamId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getTimeTaken() {
		return timeTaken;
	}
	public void setTimeTaken(int timeTaken) {
		this.timeTaken = timeTaken;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public Map<Integer, List<Integer>> getAnswerSelectedMap() {
		return answerSelectedMap;
	}
	public void setAnswerSelectedMap(Map<Integer, List<Integer>> answerSelectedMap) {
		this.answerSelectedMap = answerSelectedMap;
	}
}
