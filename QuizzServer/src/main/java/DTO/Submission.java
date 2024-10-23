package DTO;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Submission {
    private int submissionId;
    private int hostExamId; 
    private int studentId;
    private int timeTaken;
    private float score;
    private Map<Integer, List<Integer>> answerSelectedMap; //Map<QuestionId, List Answer>
}
