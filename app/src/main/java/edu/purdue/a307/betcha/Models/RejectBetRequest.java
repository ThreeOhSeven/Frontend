package edu.purdue.a307.betcha.Models;

/**
 * Created by kyleohanian on 10/30/17.
 */

public class RejectBetRequest {
    public String betID;
    public String authToken;

    public RejectBetRequest(String betID, String authToken) {
        this.betID = betID;
        this.authToken = authToken;
    }

    public String getBetID() {
        return betID;
    }

    public void setBetID(String betID) {
        this.betID = betID;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
