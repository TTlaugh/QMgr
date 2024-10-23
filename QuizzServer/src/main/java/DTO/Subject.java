package DTO;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Subject {
    private int subjectId;
    private int workspaceId;
    private String subjectName;
    private LocalDateTime createDate;
    private boolean archive;
}
