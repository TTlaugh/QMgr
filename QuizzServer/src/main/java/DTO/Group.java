package DTO;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Group {
    private int groupId;
    private int workspaceId;
    private String groupName;
    private LocalDateTime createDate;
    private boolean archive;
}
