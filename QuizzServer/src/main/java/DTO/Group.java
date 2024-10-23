package DTO;

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
public class Group {
    private int groupId;
    private int workspaceId;
    private String groupName;
    private LocalDateTime createDate;
    private boolean archive;
}
