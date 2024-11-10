package model;

public class Group_Model {
    String group_name, group_id, date_created;

    public Group_Model(String group_name, String group_id, String date_created) {
        this.group_name = group_name;
        this.group_id = group_id;
        this.date_created = date_created;
    }

    public Group_Model() {
        this.group_name = "SGU Group 1";
        this.group_id = "#GR001";
        this.date_created = "01/01/2021";
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }
}
