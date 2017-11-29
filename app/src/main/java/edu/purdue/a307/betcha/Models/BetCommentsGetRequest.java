package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 11/29/17.
 */

public class BetCommentsGetRequest {

    String authToken;
    String betId;

    public BetCommentsGetRequest() {
    }

    public BetCommentsGetRequest(String authToken, String betId) {
        this.authToken = authToken;
        this.betId = betId;
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
}
