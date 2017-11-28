package edu.purdue.a307.betcha.Models;

/**
 * Created by Peter on 11/26/17.
 */

public class FeedbackRequest {

    private String text;
    private String authToken;

    public FeedbackRequest() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
