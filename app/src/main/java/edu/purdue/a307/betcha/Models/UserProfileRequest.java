package edu.purdue.a307.betcha.Models;

/**
 * Created by Peter on 11/27/17.
 */

public class UserProfileRequest {
    private String authToken;
    private int userId;

    public UserProfileRequest(String authToken, int userId) {
        this.authToken = authToken;
        this.userId = userId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public int getId() {
        return userId;
    }

    public void setId(int userId) {
        this.userId = userId;
    }
}
