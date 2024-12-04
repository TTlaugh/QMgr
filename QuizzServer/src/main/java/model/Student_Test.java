package model;

import java.io.Serializable;
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
public class Student_Test implements Serializable {

    private static final long serialVersionUID = 1L;

    private String studentId;
    private String fullName;
    private int timeTaken;
    private float score;

}
