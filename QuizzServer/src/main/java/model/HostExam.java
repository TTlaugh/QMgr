package model;

import java.util.ArrayList;

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
public class HostExam {
    private int hostExamId;
    private int examId;
    private int groupId;
    private int timeLimit;
    private int maxScore;
    private boolean isShuffle;
    private ArrayList<Question> examQuestions;
	public int getHostExamId() {
		return hostExamId;
	}
	public void setHostExamId(int hostExamId) {
		this.hostExamId = hostExamId;
	}
	public int getExamId() {
		return examId;
	}
	public void setExamId(int examId) {
		this.examId = examId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}
	public int getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}
	public boolean isShuffle() {
		return isShuffle;
	}
	public void setShuffle(boolean isShuffle) {
		this.isShuffle = isShuffle;
	}
	public ArrayList<Question> getExamQuestions() {
		return examQuestions;
	}
	public void setExamQuestions(ArrayList<Question> examQuestions) {
		this.examQuestions = examQuestions;
	}
}
