package ual.modyst.model;

/**
 * Created by kargo on 7/21/2016.
 */
public class Requests {
    private int unique_id;
    private int unique_id_item;
    private int unique_id_users;
    private String progress;
    private String created;
    private String modified;

    public Requests(){}

    public Requests(int unique_id, int unique_id_item, int unique_id_users, String progress, String created, String modified) {
        this.unique_id = unique_id;
        this.unique_id_item = unique_id_item;
        this.unique_id_users = unique_id_users;
        this.progress = progress;
        this.created = created;
        this.modified = modified;
    }

    public int getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(int unique_id) {
        this.unique_id = unique_id;
    }

    public int getUnique_id_item() {
        return unique_id_item;
    }

    public void setUnique_id_item(int unique_id_item) {
        this.unique_id_item = unique_id_item;
    }

    public int getUnique_id_users() {
        return unique_id_users;
    }

    public void setUnique_id_users(int unique_id_users) {
        this.unique_id_users = unique_id_users;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }
}
