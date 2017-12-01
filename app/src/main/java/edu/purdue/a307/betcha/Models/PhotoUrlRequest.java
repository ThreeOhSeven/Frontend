package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 11/30/17.
 */

public class PhotoUrlRequest {

    String authToken;
    String photoUrl;

    public PhotoUrlRequest(String authToken, String photoUrl) {
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
