package edu.purdue.a307.betcha.Models;

/**
 * Created by Peter on 11/30/17.
 */

public class BetRequest {
    private String authToken;
    private int betId;

    public BetRequest(String authToken, int betId) {
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
