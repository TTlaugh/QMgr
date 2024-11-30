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
public class Workspace implements Serializable {

    private static final long serialVersionUID = 1L;
    private int workspaceId;
    private String pin;
    private String workspaceName;
    private boolean archive;
}