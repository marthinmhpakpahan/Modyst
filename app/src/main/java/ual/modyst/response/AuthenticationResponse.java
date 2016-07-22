package ual.modyst.response;

/**
 * Created by kargo on 7/21/2016.
 */
public class AuthenticationResponse {
    private String result;
    private String id_users;

    public AuthenticationResponse(String result, String id_users) {
        this.result = result;
        this.id_users = id_users;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getId_users() {
        return id_users;
    }

    public void setId_users(String id_users) {
        this.id_users = id_users;
    }
}
