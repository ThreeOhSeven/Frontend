package edu.purdue.a307.betcha.Models;

/**
 * Created by Peter on 11/27/17.
 */

public class UserProfileRequest {
    private String authToken;
    private int id;

    public UserProfileRequest(String authToken, int id) {
        this.authToken = authToken;
        this.id = id;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
