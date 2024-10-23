package DTO;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Question {
    private int questionId;
    private int subjectId;
    private String chapter;
    private int difficulty; //1-5
    private String content;
    private ArrayList<Answers> answers;
    private boolean archive;
}
