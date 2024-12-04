package model;

import java.io.Serializable;
import java.util.List;

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
public class Exam implements Serializable {
    private static final long serialVersionUID = 1L;
    private int examId;
    private int subjectId;
    private String name;
    private String desc;
    private List<Integer> questionsIds;
    private boolean archive;
}
