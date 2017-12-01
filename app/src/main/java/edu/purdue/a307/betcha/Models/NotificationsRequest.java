package edu.purdue.a307.betcha.Models;

/**
 * Created by Peter on 11/30/17.
 */

public class NotificationsRequest {
    private String authToken;
    private int userId;

    public NotificationsRequest(String authToken, int userId) {
        this.authToken = authToken;
        this.userId = userId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
