package model;

import java.time.LocalDateTime;

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
public class Subject {
	private int subjectId;
	private int workspaceId;
	private String subjectName;
	private LocalDateTime createDate;
	private boolean archive;
}
