package services;

import java.util.ArrayList;

import data.WorkspaceDAO;
import model.Workspace;

public class WorkspaceManager {
    private static WorkspaceManager instance = null;

    private WorkspaceManager() {
    }

    public static WorkspaceManager getInstance() {
        if (instance == null) {
            instance = new WorkspaceManager();
        }
        return instance;
    }

    public boolean setUpWorkspace(Workspace workspace) {
        return new WorkspaceDAO().create(workspace);
    }

    public boolean renameWorkspace(Workspace workspace) {
        return new WorkspaceDAO().update(workspace);
    }

    public boolean archiveWorkspace(Workspace workspace) {
        return new WorkspaceDAO().delete(workspace.getWorkspaceId());
    }

    public Workspace getWorkspace(int id) {
        return new WorkspaceDAO().getByID(id);
    }

    public ArrayList<Workspace> getAllWorkspace() {
        return new WorkspaceDAO().getAll();
    }

}
