package ual.modyst.response;

import java.util.List;

import ual.modyst.model.Users;

/**
 * Created by kargo on 7/21/2016.
 */
public class UsersResponse {
    private String result;
    private List<Users> data;

    public UsersResponse(String result, List<Users> data) {
        this.result = result;
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Users> getData() {
        return data;
    }

    public void setData(List<Users> data) {
        this.data = data;
    }
}
