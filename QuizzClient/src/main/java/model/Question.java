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
public class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    private int questionId;
    private int subjectId;
    private String chapter;
    private int difficulty; // 1-5
    private String content;
    private ArrayList<Answer> answers;
    private boolean archive;
}
