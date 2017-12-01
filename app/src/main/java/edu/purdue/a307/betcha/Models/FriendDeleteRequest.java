package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 11/30/17.
 */

public class FriendDeleteRequest {
    String authToken;
    String id;

    public FriendDeleteRequest(String authToken, String id) {
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
