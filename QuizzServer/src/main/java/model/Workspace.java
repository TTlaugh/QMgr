package model;

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
public class Workspace {

    public Workspace() {
		this.pin = null;
		this.workspaceName = null;
		this.archive = false;
	}
	public Workspace(int workspaceId, String pin, String workspaceName, boolean archive) {
		super();
		this.workspaceId = workspaceId;
		this.pin = pin;
		this.workspaceName = workspaceName;
		this.archive = archive;
	}
	private int workspaceId;
    private String pin;
    private String workspaceName;
    private boolean archive;
	public int getWorkspaceId() {
		return workspaceId;
	}
	public void setWorkspaceId(int workspaceId) {
		this.workspaceId = workspaceId;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getWorkspaceName() {
		return workspaceName;
	}
	public void setWorkspaceName(String workspaceName) {
		this.workspaceName = workspaceName;
	}
	public boolean isArchive() {
		return archive;
	}
	public void setArchive(boolean archive) {
		this.archive = archive;
	}
}