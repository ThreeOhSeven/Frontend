package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 11/29/17.
 */

public class BetCommentAddRequest {
    String authToken;
    String betId;
    String text;

    public BetCommentAddRequest(String authToken, String betId, String text) {
        this.authToken = authToken;
        this.betId = betId;
        this.text = text;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getBetId() {
        return betId;
    }

    public void setBetId(String betId) {
        this.betId = betId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
