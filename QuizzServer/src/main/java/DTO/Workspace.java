package DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Workspace {
    private int workspaceId;
    private int pin;
    private String workspaceName;
    private boolean archive;
}
