package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 10/6/17.
 */

public class UserEmailRequest {
    public String authToken;
    public String email;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
