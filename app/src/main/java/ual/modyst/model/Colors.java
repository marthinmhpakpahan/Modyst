package ual.modyst.model;

/**
 * Created by kargo on 7/21/2016.
 */
public class Colors {
    private int unique_id;
    private String name;
    private String color;
    private String created;
    private String modified;

    public Colors(){}

    public Colors(int unique_id, String name, String color, String created, String modified) {
        this.unique_id = unique_id;
        this.name = name;
        this.color = color;
        this.created = created;
        this.modified = modified;
    }

    public int getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(int unique_id) {
        this.unique_id = unique_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
