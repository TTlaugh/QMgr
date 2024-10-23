package DTO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Exam {
    private int examId;
    private int subjectId;
    private String name;
    private String desc;
    private List<Integer> questionsIds;
    private boolean archive;
}
