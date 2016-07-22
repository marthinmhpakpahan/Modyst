package ual.modyst.model;

/**
 * Created by kargo on 7/21/2016.
 */
public class Users {
    private int unique_id;
    private int unique_id_roles;
    private String full_name;
    private String email;
    private String password;
    private String sex;
    private String phone_number;
    private String address;
    private String registration_key;
    private String created;
    private String modified;

    public Users(){}

    public Users(int unique_id, int unique_id_roles, String full_name, String email, String password, String sex, String phone_number, String address, String registration_key, String created, String modified) {
        this.unique_id = unique_id;
        this.unique_id_roles = unique_id_roles;
        this.full_name = full_name;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.phone_number = phone_number;
        this.address = address;
        this.registration_key = registration_key;
        this.created = created;
        this.modified = modified;
    }

    public int getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(int unique_id) {
        this.unique_id = unique_id;
    }

    public int getUnique_id_roles() {
        return unique_id_roles;
    }

    public void setUnique_id_roles(int unique_id_roles) {
        this.unique_id_roles = unique_id_roles;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegistration_key() {
        return registration_key;
    }

    public void setRegistration_key(String registration_key) {
        this.registration_key = registration_key;
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
