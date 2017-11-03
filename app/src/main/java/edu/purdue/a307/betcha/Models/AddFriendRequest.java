package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 11/2/17.
 */

public class AddFriendRequest {
    public String authToken;
    public String id;

    public AddFriendRequest(String authToken, String id) {
        this.authToken = authToken;
        this.id = id;
    }

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
