package edu.purdue.a307.betcha.Models;

/**
 * Created by kushagra on 11/29/17.
 */

public class BetDeleteRequest {
    private String authToken;
    private int betId;

    public BetDeleteRequest(String authToken, int betId) {
        this.authToken = authToken;
        this.betId = betId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public int getBetId() {
        return betId;
    }

    public void setBetId(int betId) {
        this.betId = betId;
    }
}
