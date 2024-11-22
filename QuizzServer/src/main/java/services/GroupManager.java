package services;

import java.util.ArrayList;

import data.GroupDAO;
import model.Group;

public class GroupManager {
    private static GroupManager instance = null;

    public static GroupManager getInstance() {
        if (instance == null) {
            instance = new GroupManager();
        }
        return instance;
    }

    private GroupManager() {
    }

    public ArrayList<Group> getAllGroup() {
        return new GroupDAO().getAll();
    }

    public boolean createGroup(Group group) {
        return new GroupDAO().create(group);
    }

    public boolean updateGroup(Group group) {
        return new GroupDAO().update(group);
    }

    public boolean deleteGroup(int id) {
        return new GroupDAO().delete(id);
    }

    public Group getGroupByID(int id) {
        return new GroupDAO().getByID(id);
    }
}
