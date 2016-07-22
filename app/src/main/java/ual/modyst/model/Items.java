package ual.modyst.model;

/**
 * Created by kargo on 7/21/2016.
 */
public class Items {
    private int unique_id;
    private int unique_id_categories;
    private String name;
    private int unique_id_color;
    private int unique_id_size;
    private String price;
    private String description;
    private String created;
    private String modified;

    public Items(){}

    public Items(int unique_id, int unique_id_categories, String name, int unique_id_color, int unique_id_size, String price, String description, String created, String modified) {
        this.unique_id = unique_id;
        this.unique_id_categories = unique_id_categories;
        this.name = name;
        this.unique_id_color = unique_id_color;
        this.unique_id_size = unique_id_size;
        this.price = price;
        this.description = description;
        this.created = created;
        this.modified = modified;
    }

    public int getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(int unique_id) {
        this.unique_id = unique_id;
    }

    public int getUnique_id_categories() {
        return unique_id_categories;
    }

    public void setUnique_id_categories(int unique_id_categories) {
        this.unique_id_categories = unique_id_categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnique_id_color() {
        return unique_id_color;
    }

    public void setUnique_id_color(int unique_id_color) {
        this.unique_id_color = unique_id_color;
    }

    public int getUnique_id_size() {
        return unique_id_size;
    }

    public void setUnique_id_size(int unique_id_size) {
        this.unique_id_size = unique_id_size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
