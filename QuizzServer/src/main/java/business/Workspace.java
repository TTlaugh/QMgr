package business;

public class Workspace {
    String workspaceID;
    String workspaceName;
    String pin;
    boolean archived;

    public Workspace(String workSpaceID, String workspaceName, String pin, boolean archived) {
        this.workspaceID = workSpaceID;
        this.workspaceName = workspaceName;
        this.pin = pin;
        this.archived = archived;
    }
}
