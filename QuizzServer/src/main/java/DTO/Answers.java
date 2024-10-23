package DTO;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Answers implements Serializable {
    private int answerId;
    private int questionId;
    private String content;
    private boolean isCorrect;
}
