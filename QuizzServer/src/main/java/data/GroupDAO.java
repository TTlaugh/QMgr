package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Group;

import java.sql.Timestamp;

import utils.SQLUtils;

public class GroupDAO implements interfaceDAO<Group> {
    private ArrayList<Group> list;
    private Group group;
    Connection con;

    @Override
    public ArrayList<Group> getAll() {
        list = null;
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "SELECT * FROM Groups WHERE Archived = 0";
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    group = new Group();
                    group.setGroupId(rs.getInt("GroupID"));
                    group.setWorkspaceId(rs.getInt("WorkspaceID"));
                    group.setGroupName(rs.getString("GroupName"));
                    group.setCreateDate(rs.getTimestamp("DateCreated").toLocalDateTime());
                    group.setArchive(rs.getBoolean("Archived"));
                    list.add(group);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                SQLUtils.closeConnection(con);
            }
        }
        return list;
    }

    @Override
    public boolean create(Group t) {
        boolean b = false;
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "INSERT INTO Groups (GroupName, DateCreated, Archived, WorkspaceID) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, group.getGroupName());
                ps.setTimestamp(2, Timestamp.valueOf(group.getCreateDate()));
                ps.setBoolean(3, group.isArchive());
                ps.setInt(4, group.getWorkspaceId());
                b = ps.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                SQLUtils.closeConnection(con);
            }
        }
        return b;
    }

    @Override
    public Group getByID(int t) {
        con = SQLUtils.getConnection();
        group = null;
        if (con != null) {
            try {
                String query = "SELECT * FROM Groups WHERE GroupID = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, t);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    group = new Group();
                    group.setGroupId(rs.getInt("GroupID"));
                    group.setWorkspaceId(rs.getInt("WorkspaceID"));
                    group.setGroupName(rs.getString("GroupName"));
                    group.setCreateDate(rs.getTimestamp("DateCreated").toLocalDateTime());
                    group.setArchive(rs.getBoolean("Archived"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                SQLUtils.closeConnection(con);
            }
        }
        return group;
    }

    @Override
    public boolean update(Group t) {
        boolean b = false;
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "UPDATE Groups SET GroupName = ?, DateCreated = ?, Archived = ?, WorkspaceID = ? WHERE GroupID = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, group.getGroupName());
                ps.setTimestamp(2, Timestamp.valueOf(group.getCreateDate()));
                ps.setBoolean(3, group.isArchive());
                ps.setInt(4, group.getWorkspaceId());
                ps.setInt(5, group.getGroupId());
                b = ps.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                SQLUtils.closeConnection(con);
            }
        }
        return b;
    }

    @Override
    public boolean delete(int t) {
        boolean b = false;
        con = SQLUtils.getConnection();
        if (con != null) {
            try {
                String query = "UPDATE Groups SET Archived = TRUE WHERE GroupID = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, t);
                b = ps.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                SQLUtils.closeConnection(con);
            }
        }
        return b;
    }

	@Override
	public Group getByID(String t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(String t) {
		// TODO Auto-generated method stub
		return false;
	}

}
