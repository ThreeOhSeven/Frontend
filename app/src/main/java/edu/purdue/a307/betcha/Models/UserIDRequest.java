package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 10/6/17.
 */

public class UserIDRequest {
    public String authToken;
    public String id;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
