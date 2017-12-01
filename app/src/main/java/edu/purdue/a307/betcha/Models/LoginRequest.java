package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 9/20/17.
 */

public class LoginRequest {
    String authToken;

    String photoUrl;

    public LoginRequest(String authToken) {
        this.authToken = authToken;
    }

    public LoginRequest(String authToken, String photoUrl) {
        this.authToken = authToken;
        this.photoUrl = photoUrl;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
