package model;

import java.io.Serializable;
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
public class HostExam implements Serializable {

    private static final long serialVersionUID = 1L;

    private int hostExamId;
    private int examId;
    private int groupId;
    private int timeLimit;
    private int maxScore;
    private boolean isShuffle;
    private ArrayList<Question> examQuestions;
}
